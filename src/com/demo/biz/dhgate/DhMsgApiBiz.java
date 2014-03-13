package com.demo.biz.dhgate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.DhMsgInfoDao;
import com.demo.dao.DhMsgTopicDao;
import com.demo.dao.ZhangHaoDao;
import com.demo.entity.DhMsgInfo;
import com.demo.entity.DhMsgTopic;
import com.demo.entity.ZhangHao;
import com.demo.utils.ApplicationUtils;
import com.demo.utils.DateUtils;
import com.demo.utils.HttpClientUtils;

/**
 * �ػ���վ���ŷ���
 * 
 */
@Service
@Transactional
public class DhMsgApiBiz {

	@Resource
	private DhCommonApiBiz dhCommonApiBiz;
	@Resource
	private DhMsgTopicDao dhMsgTopicDao;
	@Resource
	private DhMsgInfoDao dhMsgInfoDao;
	@Resource
	private ZhangHaoDao  zhangHaoDao;

	private final Integer pageSize = 20;
	private Integer updateCount = 0;

	/**
	 *  ͬ����������(�����Զ�ͬ��)
	 * @param dhAccount
	 * @return
	 */
	public String autoFetchMsg(ZhangHao dhAccount) {
		String msgType = "";
		Integer beforeDay =1;
		
		Long msgUpdateTime = dhAccount.getMsg_update_time();
		Date curTime = new Date();
		if (msgUpdateTime != null) {
			Integer intervalDays = DateUtils.getIntervalDays(new Date(msgUpdateTime), curTime);
			if (intervalDays > 1) {
				beforeDay = intervalDays;
			}
		} else {
			beforeDay = 5; // ��ʼ��5��ǰ�����ݿ�ʼȡ
		}
		
		String result = this.fetchMsg(dhAccount, msgType, beforeDay);
		if (!result.contains("����")) {
			// ����ͬ��ʱ��
			dhAccount.setMsg_update_time(curTime.getTime());
			zhangHaoDao.merge(dhAccount);
		}
		return result;
	}
	
	/**
	 * ͬ����������(����ҳ���ֶ�ͬ��)
	 * @param account
	 * @param msgType
	 * @param beforeDay
	 * @return
	 */
	public String fetchMsg(String account, String msgType, Integer beforeDay) {
		ZhangHao dhAccount = zhangHaoDao.findUnique(account, 
				DhCommonApiBiz.ACCOUNT_TYPE);
		return this.fetchMsg(dhAccount, msgType, beforeDay);
	}
	
	/**
	 * ͬ����������(����ҳ���ֶ�ͬ��)
	 * @param account
	 * @param msgType
	 * @param beforeDay
	 * @return
	 */
	public String fetchMsg(ZhangHao dhAccount, String msgType, Integer beforeDay) {
		try {
			updateCount = 0;
			String result = this.fetchMsg(dhAccount, msgType, beforeDay, 1);
			if (result.equals("success")) {
				return "���γɹ����� " + updateCount + " ��վ��������";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "��������δ֪�쳣";
		}
	}

	/**
	 * ץȡһҳ����
	 * 
	 * @param dhgateAccount
	 * @param msgType
	 * @param beforeDay
	 * @param pageNum
	 * @param updateCount
	 *            ���µļ�¼��
	 * @return
	 */
	private String fetchMsg(ZhangHao dhAccount, String msgType,
			Integer beforeDay, Integer pageNum) {
		String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!dhCommonApiBiz.putSystemParamsToParamMap(paramMap, dhAccount,
				"dh.messages.get")) {
			return "��������" + DhCommonApiBiz.ERR_TOKEN;
		}
		paramMap.put("appid", "");
		paramMap.put("beforeday", beforeDay.toString());
		paramMap.put("marked", "");
		paramMap.put("msgtype", msgType);
		paramMap.put("orderField", "");
		paramMap.put("orderType", "");
		paramMap.put("pagenum", pageNum.toString());
		paramMap.put("pagesize", this.pageSize.toString());
		paramMap.put("recivereaded", "");
		paramMap.put("searchContent", "");
		paramMap.put("state", "");
		paramMap.put("systemuserid", "");

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (respJson.containsKey("code")) {
				if (respJson.getString("code").equals("2")) {
					dhCommonApiBiz.clearAccessToken(dhAccount);
					return "��������" + DhCommonApiBiz.ERR_TOKEN;
				} else if (!respJson.getString("code").equals("0")) {
					return "��������" + respJson.getString("message");
				}
			}
			
			JSONObject statusObj = respJson.getJSONObject("status");
			if (statusObj.getString("message").equalsIgnoreCase("OK")) {
				if (respJson.getString("messageTopicList").equals("null") || 
						respJson.getString("messageTopicList").equals("[]") ) {
					return "success";
				}

				JSONArray msgTopics = respJson.getJSONArray("messageTopicList");
				for (int i = 0; i < msgTopics.size(); i++) {
					updateCount += this.updateMsgTopic(dhAccount,
							msgTopics.getJSONObject(i));
				}
				// ��ȡ����topic������pagesize���ʱ������ȡ��һҳ
				if (msgTopics.size() == this.pageSize) {
					this.fetchMsg(dhAccount, msgType, beforeDay,
							pageNum + 1);
				}
				return "success";
			} else {
				return "��������" + statusObj.getString("message");
			}
		}
		return "��������" + DhCommonApiBiz.CONN_ERR;
	}

	/**
	 * ����msgTopic����������
	 * 
	 * @param json
	 * @return �Ƿ��и���: 0-û�и��£�����Ҫ����1-�и���
	 */
	private int updateMsgTopic(ZhangHao dhAccount, JSONObject json) {
		boolean needUpdate = true;
		Long topicId = json.getLong("tdMessageTopicId");
		Long lastReplyTime = json.getLong("lastreplytime");
		DhMsgTopic msgTopic = dhMsgTopicDao.getByTopicId(topicId);
		if (msgTopic == null) {
			msgTopic = new DhMsgTopic();
			msgTopic.setTopicId(topicId);
			msgTopic.setMsgType(json.getInt("msgtype"));
			msgTopic.setMcLock(json.getInt("mcLock"));
			msgTopic.setParm(json.getString("parm"));
			msgTopic.setSenderId(json.getString("senderid"));
			msgTopic.setSenderReaded(json.getInt("senderreaded"));
			msgTopic.setRecieverId(json.getString("recieverid"));
			msgTopic.setRecieverReaded(json.getInt("recivereaded"));
			msgTopic.setDhAccount(dhAccount.getAccount());
			// �ǳƴ���
			msgTopic.setSenderNickname(this.getNickname(msgTopic.getSenderId(),
					json.getString("senderNickname"), dhAccount));
			msgTopic.setRecieverNickname(this.getNickname(msgTopic.getRecieverId(),
					json.getString("receiverNickname"), dhAccount));
		} else if (lastReplyTime.equals(msgTopic.getLastReplyTime())) {
			if (msgTopic.getSenderReaded() != json.getInt("senderreaded") ||
					msgTopic.getRecieverReaded() != json.getInt("recivereaded")) { //��ȡ״̬�����仯ʱ����
				msgTopic.setSenderReaded(json.getInt("senderreaded"));
				msgTopic.setRecieverReaded(json.getInt("recivereaded"));
			} else {
				needUpdate = false;
			}
		}

		if (needUpdate) {
			msgTopic.setTitle(json.getString("title"));
			msgTopic.setLastReplyTime(lastReplyTime);
			msgTopic.setMsgReplyCount(json.getInt("msgreplycount"));
			// �Ѷ�״̬����
			Integer readStatus = 0;
			if (msgTopic.getSenderReaded() == 1 
					&& msgTopic.getSenderNickname() != null 
					&& msgTopic.getSenderNickname().equals(dhAccount.getUser_nickname())) {
				readStatus = 1;
			} else if (msgTopic.getRecieverReaded() == 1 
					&& msgTopic.getRecieverNickname() != null
					&& msgTopic.getRecieverNickname().equals(dhAccount.getUser_nickname())) {
				readStatus = 1;
			}
			msgTopic.setReadStatus(readStatus);
			this.dhMsgTopicDao.merge(msgTopic);
			
			// ����վ����������Ϣ
			this.fetchTopicMsgInfo(dhAccount, msgTopic, false);
			return 1;
		}
		return 0;
	}

	/**
	 * ͬ��һ��վ��������
	 * @param topicId
	 */
	public String synchMsgTopicAndInfo(Long topicId) {
		try {
			DhMsgTopic msgTopic = dhMsgTopicDao.getByTopicId(topicId);
			if (msgTopic == null) {
				return "�����������ݲ�����";
			}
			String account = msgTopic.getDhAccount();
			if (account == null) {
				return "�����������ݴ���";
			}
			ZhangHao dhAccount = zhangHaoDao.findUnique(account, 
					DhCommonApiBiz.ACCOUNT_TYPE);
			if (dhAccount == null) {
				return "�������󣺶ػ��˺�δ���";
			}
			return this.fetchTopicMsgInfo(dhAccount, msgTopic, true);
		} catch (Exception e) {
			e.printStackTrace();
			return "��������δ֪�쳣";
		}
	}
	
	/**
	 * ץȡtopic��������Ϣ
	 * @param msgTopic	
	 * @param updateMsgTopic �Ƿ������������
	 */
	private String fetchTopicMsgInfo(ZhangHao dhAccount, DhMsgTopic msgTopic, 
			boolean updateMsgTopic) {
		String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!dhCommonApiBiz.putSystemParamsToParamMap(paramMap, dhAccount,
				"dh.message.detail.get")) {
			return "��������" + DhCommonApiBiz.ERR_TOKEN;
		}
		paramMap.put("appid", (String) ApplicationUtils.get("dhgateAppKey"));
		paramMap.put("messageTopicId", msgTopic.getTopicId().toString());

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (respJson.containsKey("code")) {
				if (respJson.getString("code").equals("2")) {
					dhCommonApiBiz.clearAccessToken(dhAccount);
					return "��������" + DhCommonApiBiz.ERR_TOKEN;
				} else if (!respJson.getString("code").equals("0")) {
					return "��������:" + respJson.getString("message");
				}
			}
		
			JSONObject statusObj = respJson.getJSONObject("status");
			if (statusObj.getString("message").equalsIgnoreCase("OK")) {
				JSONArray msgInfos = respJson.getJSONObject("message")
						.getJSONArray("messageInfo");
				for (int i = 0; i < msgInfos.size(); i++) {
					this.updateMsgInfo(msgInfos.getJSONObject(i), msgTopic);
				}
				
				// ������������
				if (updateMsgTopic) {
					JSONObject msgTopicJson = respJson.getJSONObject("message")
							.getJSONObject("messageTopic");
					msgTopic.setTitle(msgTopicJson.getString("title"));
					msgTopic.setLastReplyTime(msgTopicJson.getLong("lastreplytime"));
					msgTopic.setMsgReplyCount(msgTopicJson.getInt("msgreplycount"));
					msgTopic.setSenderReaded(msgTopicJson.getInt("senderreaded"));
					msgTopic.setRecieverReaded(msgTopicJson.getInt("recivereaded"));
					dhMsgTopicDao.merge(msgTopic);
				}
				return "success";
			}  else {
				return "��������" + statusObj.getString("message");
			}
		}
		return "��������" + DhCommonApiBiz.CONN_ERR;
	}
	
	/**
	 * ������Ϣ����
	 * @param json
	 * @param msgTopic
	 */
	private void updateMsgInfo(JSONObject json, DhMsgTopic msgTopic) {
		Long infoId = json.getLong("tdMessageInfoId");
		DhMsgInfo msgInfo = dhMsgInfoDao.getByInfoId(infoId);
		if (msgInfo == null) {
			msgInfo = new DhMsgInfo();
			msgInfo.setTopicId(msgTopic.getTopicId());
			msgInfo.setInfoId(infoId);
			msgInfo.setInfoContent(json.getString("content"));
			msgInfo.setSenderId(json.getString("senderid"));
			msgInfo.setRecieverId(json.getString("recieverid"));
			msgInfo.setCreateTime(json.getLong("createtime"));
			if (!json.getString("attatchment").equals("null")) {
				msgInfo.setAttachment(json.getString("attatchment"));
			}
			// �ǳƴ���
			msgInfo.setSenderNickname(this.getNickname(msgInfo.getSenderId(),
					json.getString("senderNickname"), msgTopic));
			msgInfo.setRecieverNickname(this.getNickname(msgInfo.getRecieverId(),
					json.getString("receiverNickname"), msgTopic));
			dhMsgInfoDao.merge(msgInfo);
		}
	}
	
	/**
	 * ȡ�����˻�����˵��ǳ�(����վ����Topicʱ����)
	 * 
	 * ��ǰapi���ص��ǳ�Ϊ��, ��Ҫ��������
	 * @param dhUserId
	 * @param dhUserNickname
	 * @param dhgateAccount
	 * @return
	 */
	private String getNickname(String dhUserId, String dhUserNickname,
			ZhangHao dhAccount) {
		if (!dhUserNickname.equals("null")) {
			return dhUserNickname;
		}
		
		if (dhUserId.equals(dhAccount.getUser_id())) {
			return dhAccount.getUser_nickname();
		} else {
			// TODO ����api��ȡȡ�û��ǳ���Ϣ, ��Ϊ�ٷ���δ�ṩ�ù���api����ʱ����null
			return null;
		}
	}

	/**
	 * ȡ�����˻�����˵��ǳ�(����վ��������ʱ����)
	 * @param dhUserId
	 * @param dhUserNickname
	 * @param msgTopic
	 * @return
	 */
	private String getNickname(String dhUserId, String dhUserNickname,
			DhMsgTopic msgTopic) {
		if (!dhUserNickname.equals("null")) {
			return dhUserNickname;
		}
		if (dhUserId.equals(msgTopic.getSenderId())) {
			return msgTopic.getSenderNickname();
		} else if (dhUserId.equals(msgTopic.getRecieverId())) {
			return msgTopic.getRecieverNickname();
		}
		return null;
	}
	
	/**
	 * �ظ�վ����
	 * @param topicId
	 * @param content
	 * @return
	 */
	public String sendReply(Long topicId, String content) {
		try {
			DhMsgTopic msgTopic = dhMsgTopicDao.getByTopicId(topicId);
			if (msgTopic == null) {
				return "�����������ݲ�����";
			}
			String account = msgTopic.getDhAccount();
			if (account == null) {
				return "�����������ݴ���";
			}
			ZhangHao dhAccount = zhangHaoDao.findUnique(account, 
					DhCommonApiBiz.ACCOUNT_TYPE);
			if (dhAccount == null) {
				return "�������󣺶ػ��˺�δ���";
			}
			
			String senderId = null;
			String recieverId = null;
			if (msgTopic.getSenderId().equals(dhAccount.getUser_id())) {
				senderId = msgTopic.getSenderId();
				recieverId = msgTopic.getRecieverId();
			} else if (msgTopic.getRecieverId().equals(dhAccount.getUser_id())) {
				senderId = msgTopic.getRecieverId();
				recieverId = msgTopic.getSenderId();
			}
			if (senderId == null) {
				return "�����������ݴ���";
			}
			
			String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
			Map<String, String> paramMap = new HashMap<String, String>();
			if (!dhCommonApiBiz.putSystemParamsToParamMap(paramMap, dhAccount,
					"dh.message.info.reply")) {
				return "��������" + DhCommonApiBiz.ERR_TOKEN;
			}
			paramMap.put("attatchmenturl", "");
			paramMap.put("content", content);
			paramMap.put("senderid", senderId);
			paramMap.put("tdMessageTemplatesId", "");
			paramMap.put("tdMessageTopicId", topicId.toString());
			paramMap.put("toSysuserbaseid", recieverId);
	
			JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
			if (respJson != null) {
				if (respJson.containsKey("code")) {
					if (respJson.getString("code").equals("2")) {
						return "��������" + DhCommonApiBiz.ERR_TOKEN;
					} else if (!respJson.getString("code").equals("0")) {
						return "��������" + respJson.getString("message");
					}
				}
				
				JSONObject statusObj = respJson.getJSONObject("status");
				if (statusObj.getString("message").equalsIgnoreCase("OK")) {
					return "success";
				} else {
					return "��������" + statusObj.getString("message");
				}
			} else {
				return "��������" + DhCommonApiBiz.CONN_ERR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "��������δ֪�쳣";
		}
	}
}
