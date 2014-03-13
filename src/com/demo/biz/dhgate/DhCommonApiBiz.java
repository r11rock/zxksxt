package com.demo.biz.dhgate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ZhangHaoDao;
import com.demo.entity.ZhangHao;
import com.demo.utils.ApplicationUtils;
import com.demo.utils.DES;
import com.demo.utils.HttpClientUtils;

/**
 * �ػ�������API����
 *
 */
@Service
@Transactional
public class DhCommonApiBiz {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public final static String ERR_TOKEN = "��Ȩ��Ϣ������";
	public final static String CONN_ERR = "��ػ���������ͨ��ʧ��";

	public final static String ACCOUNT_TYPE = "dh";
	
	@Resource
	private ZhangHaoDao zhangHaoDao;

	/**
	 * ��ȡ�˺ŵ�access_token
	 * 
	 * @param account
	 * @return ����null��ʾ��ȡʧ��
	 */
	public String getAccessToken(String account) {
		ZhangHao dhAccount = zhangHaoDao.findUnique(account, ACCOUNT_TYPE);
		if (dhAccount == null) {
			return null;
		}

		return this.getAccessToken(dhAccount);
	}
	
	/**
	 * ��ȡ�˺ŵ�access_token
	 * @param dhgateAccount
	 * @return
	 */
	public String getAccessToken(ZhangHao dhAccount) {
		String accessToken = dhAccount.getAccess_token();
		if (accessToken != null && !accessToken.equals("")) {
			// ����Ƿ�����Ч����
			boolean inAvailableTime = true;
			Long expireIn = dhAccount.getExpires_in(); // ��λ: ����
			Long updateTime = dhAccount.getUpdate_time(); // ��λ: ��
			if (expireIn == null || updateTime == null) {
				inAvailableTime = false;
			} else if (updateTime * 1000 + expireIn <= new Date().getTime() - 300000l) {
				inAvailableTime = false;
			}

			// ����ʱʹ��refresh_token���»�ȡaccess_token
			if (!inAvailableTime && dhAccount.getRefresh_token() != null
					&& !dhAccount.getRefresh_token().equals("")) {
				accessToken = this.fetchAccessTokenByRefreshToken(dhAccount);
			}
		}
		// ǰ��Ĵ������Ϊ�գ���ʹ���˺�����ֱ�ӻ�ȡ
		if (accessToken == null && dhAccount.getPass() != null) {
			String result = this.fetchAccessToken(dhAccount.getAccount(), 
					new DES().getDesString(dhAccount.getPass()));
			if (result!= null && result.equals("success")) {
				accessToken = dhAccount.getAccess_token();
			}
		}
		return accessToken;
	}
	
	/**
	 * �����Ȩ��Ϣ������Ȩ��Ϣ������ʱ���
	 * @param dhAccount
	 */
	public void clearAccessToken(ZhangHao dhAccount) {
		dhAccount.setAccess_token(null);
		zhangHaoDao.merge(dhAccount);
	}

	/**
	 * ͨ������api��ȡ�û���access_token
	 * 
	 * @param account
	 * @param pass
	 * @return ����null���߰���error��ʾ��ȡʧ��, success-�ɹ�
	 */
	public String fetchAccessToken(String account, String pass) {
		ZhangHao dhAccount = zhangHaoDao.findUnique(account, ACCOUNT_TYPE);
		if (dhAccount == null) {
			dhAccount = new ZhangHao();
			dhAccount.setAccount(account);
			dhAccount
					.setCreate_time(new java.sql.Date(new Date().getTime()));
		}

		String apiUrl = (String) ApplicationUtils.get("dhgateGetTokenUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("grant_type", "password");
		paramMap.put("username", account);
		paramMap.put("password", pass);
		paramMap.put("client_id", (String) ApplicationUtils.get("dhgateAppKey"));
		paramMap.put("client_secret",
				(String) ApplicationUtils.get("dhgateAppSecret"));
		paramMap.put("scope", "basic");

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (!respJson.containsKey("error")) {
				logger.debug("respJson:" + respJson);
				String accessToken = respJson.getString("access_token");
				// ��������
				this.saveDhAccountTokenInfo(dhAccount, accessToken,
						respJson.getLong("expires_in"),
						respJson.getString("refresh_token"));
				try {
					this.fetchDhUserInfo(dhAccount);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "success";
			} else {
				return "��Ȩʧ��: " + respJson.getString("errorDescription");
			}
		}
		return null;
	}

	/**
	 * ʹ��refresh_token���»�ȡaccess_token
	 * 
	 * @param dhgateAccount
	 * @return
	 */
	private String fetchAccessTokenByRefreshToken(ZhangHao dhAccount) {
		String apiUrl = (String) ApplicationUtils.get("dhgateGetTokenUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("grant_type", "refresh_token");
		paramMap.put("refresh_token", dhAccount.getRefresh_token());
		paramMap.put("client_id", (String) ApplicationUtils.get("dhgateAppKey"));
		paramMap.put("client_secret",
				(String) ApplicationUtils.get("dhgateAppSecret"));
		paramMap.put("scope", "basic");

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null && !respJson.containsKey("error")) {
			logger.debug("respJson:" + respJson);
			String accessToken = respJson.getString("access_token");
			// ��������
			this.saveDhAccountTokenInfo(dhAccount, accessToken,
					respJson.getLong("expires_in"),
					respJson.getString("refresh_token"));
			this.fetchDhUserInfo(dhAccount);
			return accessToken;
		}
		return null;
	}

	/**
	 * ��ӹ������������
	 * 
	 * @param paramMap
	 * @param dhgateAccount
	 * @param apiMethod
	 */
	public boolean putSystemParamsToParamMap(Map<String, String> paramMap,
			ZhangHao dhAccount, String apiMethod) {
		return this.putSystemParamsToParamMap(paramMap, dhAccount, apiMethod, "1.0");
	}
	
	/**
	 * ��ӹ������������
	 * 
	 * @param paramMap
	 * @param dhgateAccount
	 * @param apiMethod
	 */
	public boolean putSystemParamsToParamMap(Map<String, String> paramMap,
			ZhangHao dhAccount, String apiMethod, String version) {
		String accessToken = this.getAccessToken(dhAccount);
		if (accessToken == null) {
			return false;
		}
		paramMap.put("access_token", accessToken);
		paramMap.put("method", apiMethod);
		paramMap.put("timestamp", String.valueOf(new Date().getTime()));
		paramMap.put("v", version);
		return true;
	}

	/**
	 * ��ȡ�������û�(�ػ���)id��nickname
	 * 
	 * @param dhgateAccount
	 * @param accessToken
	 */
	private void fetchDhUserInfo(ZhangHao dhAccount) {
		if (dhAccount.getAccess_token() == null
				|| dhAccount.getUser_id() != null) {
			return;
		}

		String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		this.putSystemParamsToParamMap(paramMap, dhAccount,
				"dh.user.base.get");

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null
				&& respJson.getJSONObject("status").getString("message")
						.equalsIgnoreCase("OK")) {
			logger.debug("respJson:" + respJson);
			String dhUserId = respJson.getJSONObject("systemuserbase")
					.getString("systemuserid");
			String dhUserNickname = respJson.getJSONObject("systemuserbase")
					.getString("nickname");
			dhAccount.setUser_id(dhUserId);
			dhAccount.setUser_nickname(dhUserNickname);
			this.zhangHaoDao.merge(dhAccount);
		}
	}

	/**
	 * �����û���Ȩ��Ϣ
	 * 
	 * @return
	 */
	private void saveDhAccountTokenInfo(ZhangHao dhAccount,
			String accessToken, Long expiresIn, String refreshToken) {
		dhAccount.setAccess_token(accessToken);
		dhAccount.setExpires_in(expiresIn);
		dhAccount.setRefresh_token(refreshToken);
		dhAccount.setScope("basic");
		dhAccount.setUpdate_time(new Date().getTime() / 1000);
		this.zhangHaoDao.merge(dhAccount);
	}
	
	/**
	 * ��ȡ�ػ��˺��б�
	 * @param bdUserId
	 * @return
	 */
	public List<ZhangHao> getDhAccounts(Long bdUserId) {
		return zhangHaoDao.getAll(ACCOUNT_TYPE, bdUserId);
	}
}
