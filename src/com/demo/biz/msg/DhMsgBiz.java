package com.demo.biz.msg;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.biz.dhgate.DhOrderApiBiz;
import com.demo.biz.dhgate.DhProductApiBiz;
import com.demo.dao.DhMsgInfoDao;
import com.demo.dao.DhMsgTopicDao;
import com.demo.dao.LeiMuDao;
import com.demo.entity.DhMsgInfo;
import com.demo.entity.DhMsgTopic;
import com.demo.entity.ZhangHao;
import com.demo.entity.LeiMuTable;
import com.demo.page.PageBean;

/**
 * �ػ�վ����
 *
 */
@Service
@Transactional
public class DhMsgBiz {

	@Resource
	private DhMsgTopicDao dhMsgTopicDao;
	@Resource
	private DhMsgInfoDao dhMsgInfoDao;
	@Resource
	private DhOrderApiBiz dhOrderApiBiz;
	@Resource
	private DhProductApiBiz dhProductApiBiz;
	@Resource
	private LeiMuDao leiMuDao;
	
	/**
	 * ��ҳ��ѯվ����
	 * @param pageSize
	 * @param page
	 * @param userId
	 * @return
	 */
	public PageBean getAllByPage(int pageSize, int page, Long userId,
			String dhAccount, Integer msgType, Integer readStatus) {
		return dhMsgTopicDao.getAllByPage(pageSize, page, userId,
				dhAccount, msgType, readStatus);
	}
	
	public DhMsgTopic getMsgTopicByTopicId(Long topicId) {
		return dhMsgTopicDao.getByTopicId(topicId);
	}
	
	public List<DhMsgInfo> getMsgInfosByTopicId(Long topicId) {
		return dhMsgInfoDao.getAllByTopicId(topicId);
	}
	
	/**
	 * �Զ�����վ����
	 * @param dhAccount
	 */
	public void fenpeiMsg(ZhangHao dhAccount) {
		dhMsgTopicDao.fenpeiMsg(dhAccount.getAccount(), dhAccount.getBd_user_id(),
				dhAccount.getBd_user_name());
	}
	
	/**
	 * �Զ����䶩�����͵�վ����
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void fenpeiOrderMsg(DhMsgTopic topic, ZhangHao dhAccount) {
		// ȡ��������
		JSONObject order = dhOrderApiBiz.getOrder(topic.getParm(), dhAccount);
		if (order == null) {
			return;
		}
		// ȡ������Ʒ����
		String rfxid = order.getJSONObject("rfxDto").getString("rfxid");
		JSONObject orderProduct = dhOrderApiBiz.getOrderProduct(rfxid, dhAccount);
		if (orderProduct == null || orderProduct.getString("lstProductDTO").equals("null") || 
				orderProduct.getString("lstProductDTO").equals("[]") ) {
			return;
		}
		// ȡ��Ʒ����
		String itemCode = orderProduct.getJSONArray("lstProductDTO").getJSONObject(0).getString("itemcode");
		JSONObject product = dhProductApiBiz.getProduct(itemCode, dhAccount);
		if (product == null) {
			return;
		}
		// ȡ��ƷcatePubId��Ӧ��userId��ִ�з���
		String catePubId = product.getJSONObject("product").getString("catePubId");
		String parentCatePubId = catePubId.substring(0, 3);
		LeiMuTable leiMu = leiMuDao.getByCateId(parentCatePubId);
		if (leiMu == null) {
			return;
		}
		topic.setBdUserId(leiMu.getMsgFenpeiUserId());
		topic.setBdUserName(leiMu.getMsgFenpeiUserName());
		dhMsgTopicDao.merge(topic);
	}
}
