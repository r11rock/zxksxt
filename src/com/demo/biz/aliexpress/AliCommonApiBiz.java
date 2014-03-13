package com.demo.biz.aliexpress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ZhangHaoDao;
import com.demo.entity.ZhangHao;
import com.demo.utils.ApplicationUtils;
import com.demo.utils.HttpClientUtils;

@Service
@Transactional
public class AliCommonApiBiz {

	public final static String ERR_TOKEN = "��Ȩ��Ϣ������";
	public final static String CONN_ERR = "������ͨ������ͨ��ʧ��";
	public final static String ACCOUNT_TYPE = "ali";
	
	@Resource
	private ZhangHaoDao zhangHaoDao;

	/**
	 * ��ȡ�˺ŵ�access_token
	 * @param account
	 * @return
	 */
	public String getAccessToken(String account) {
		ZhangHao aliAccount = zhangHaoDao.findUnique(account, ACCOUNT_TYPE);
		if (aliAccount == null) {
			return null;
		}
		
		return this.getAccessToken(aliAccount);
	}
	
	/**
	 * ��ȡ�˺ŵ�access_token
	 * @param aliAccount
	 * @return
	 */
	public String getAccessToken(ZhangHao aliAccount) {
		String accessToken = aliAccount.getAccess_token();
		if (accessToken != null && !accessToken.equals("")) {
			// ����Ƿ�����Ч����
			boolean inAvailableTime = true;
			Long expireIn = aliAccount.getExpires_in(); // ��λ: ����
			Long updateTime = aliAccount.getUpdate_time(); // ��λ: ��
			if (expireIn == null || updateTime == null) {
				inAvailableTime = false;
			} else if (updateTime * 1000 + expireIn <= new Date().getTime() - 300000l) {
				inAvailableTime = false;
			}

			// ����ʱʹ��refresh_token���»�ȡaccess_token
			if (!inAvailableTime && aliAccount.getRefresh_token() != null
					&& !aliAccount.getRefresh_token().equals("")) {
				accessToken = this.fetchAccessTokenByRefreshToken(aliAccount);
			}
		}
		return accessToken;
	}
	
	/**
	 * �����Ȩ��Ϣ������Ȩ��Ϣ������ʱ���
	 * @param dhAccount
	 */
	public void clearAccessToken(ZhangHao aliAccount) {
		aliAccount.setAccess_token(null);
		zhangHaoDao.merge(aliAccount);
	}

	
	/**
	 * ���ط�����Ȩ����ĵ�ַ
	 * @return
	 */
	public String getAuthUrl(ZhangHao aliAccount) {
		if (aliAccount.getApp_key() == null || aliAccount.getApp_key().equals("")) {
			return null;
		}
		String url = "http://gw.api.alibaba.com/auth/authorize.htm?client_id="
				+ aliAccount.getApp_key() + "&site=aliexpress&redirect_uri=" 
				+ ApplicationUtils.get("aliRedirectUri") +  "&state=1";
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("client_id", aliAccount.getApp_key());
		paramMap.put("site", "aliexpress");
		paramMap.put("redirect_uri", (String) ApplicationUtils.get("aliRedirectUri"));
		paramMap.put("state", "1");
		
		String sign = this.getAliSignature(null, paramMap, aliAccount.getApp_secret());
		
		url += "&_aop_signature=" + sign;
		return url;
	}
	
	/**
	 * ʹ��code��ȡtoken
	 * @param code
	 * @return
	 */
	public String fetchAccessToken(String code, ZhangHao aliAccount) {
		String apiUrl = "https://gw.api.alibaba.com/openapi/http/1/system.oauth2/getToken/" 
				+ aliAccount.getApp_key();
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("grant_type", "authorization_code");
		paramMap.put("need_refresh_token", "true");
		paramMap.put("client_id",  aliAccount.getApp_key());
		paramMap.put("client_secret",  aliAccount.getApp_secret());
		paramMap.put("redirect_uri", (String) ApplicationUtils.get("aliRedirectUri"));
		paramMap.put("code", code);
		
		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (respJson.containsKey("error_code")) {
				return "��������" + respJson.getString("error_message");
			}
			
			// ��������
			String accessToken = respJson.getString("access_token");
			this.saveAliAccountTokenInfo(aliAccount, accessToken,
					respJson.getLong("expires_in"),
					respJson.getString("refresh_token"),
					respJson.getString("refresh_token_timeout"),
					respJson.getString("aliId"));
			return "success";
		}
		return "��������" + CONN_ERR;
	}
	
	/**
	 * ͨ��refresh_token��ȡaccess_token
	 * @param aliAccount
	 * @return
	 */
	private String fetchAccessTokenByRefreshToken(ZhangHao aliAccount) {
		String apiUrl = "https://gw.api.alibaba.com/openapi/http/1/system.oauth2/getToken/" 
				+ aliAccount.getApp_key();
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("grant_type", "refresh_token");
		paramMap.put("client_id", aliAccount.getApp_key());
		paramMap.put("client_secret", aliAccount.getApp_secret());
		paramMap.put("refresh_token", aliAccount.getRefresh_token());
		
		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null && !respJson.containsKey("error_code")) {
			// ��������
			String accessToken = respJson.getString("access_token");
			this.saveAliAccountTokenInfo(aliAccount, accessToken,
					respJson.getLong("expires_in"),
					null,
					null,
					respJson.getString("aliId"));
			return accessToken;
		}
		return null;
	}

	/**
	 * ��鲢����refresh_token
	 */
	public void checkAndUpdateRefreshToken(ZhangHao aliAccount) {
		if (aliAccount.getAccess_token() == null ||
				aliAccount.getRefresh_token() == null) {
			return;
		}
		// �����ʱ��С��20��ʱ����
		if (new Date().getTime() - aliAccount.getRefresh_token_timeout() > 20 * 24 * 60 * 60 * 1000) { 
			return;
		}
		String apiUrl = "https://gw.api.alibaba.com/openapi/param2/1/system.oauth2/postponeToken/" 
				+ aliAccount.getApp_key();
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("client_id", aliAccount.getApp_key());
		paramMap.put("client_secret", aliAccount.getApp_secret());
		paramMap.put("access_token", aliAccount.getAccess_token());
		paramMap.put("refresh_token", aliAccount.getRefresh_token());
		
		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null && !respJson.containsKey("error_code")) {
			// ��������
			String accessToken = respJson.getString("access_token");
			this.saveAliAccountTokenInfo(aliAccount, accessToken,
					respJson.getLong("expires_in"),
					respJson.getString("refresh_token"),
					respJson.getString("refresh_token_timeout"),
					respJson.getString("aliId"));
		}
	}
	
	/**
	 * �����û���Ȩ��Ϣ
	 * 
	 * @return
	 */
	private void saveAliAccountTokenInfo(ZhangHao aliAccount,
			String accessToken, Long expiresIn, String refreshToken,
			String refreshTokenTimeout, String aliId) {
		aliAccount.setUser_id(aliId);
		aliAccount.setAccess_token(accessToken);
		aliAccount.setExpires_in(expiresIn * 1000);
		if (refreshToken != null) {
			// refresh_token_timeout��ʽ�滻
			Long longRefreshTokenTimeout = null;
			try {
				if (refreshTokenTimeout.contains("-")) {
					refreshTokenTimeout =refreshTokenTimeout.split("-")[0];
				} else if (refreshTokenTimeout.contains("+")) {
					refreshTokenTimeout = refreshTokenTimeout.split("+")[0];
				}
				if (refreshTokenTimeout.length() == 17) {
					longRefreshTokenTimeout = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.parse(refreshTokenTimeout.split("-")[0]).getTime();
				} else if (refreshTokenTimeout.length() == 14) {
					longRefreshTokenTimeout = new SimpleDateFormat("yyyyMMddHHmmss")
					.parse(refreshTokenTimeout.split("-")[0]).getTime();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			aliAccount.setRefresh_token(refreshToken);
			aliAccount.setRefresh_token_timeout(longRefreshTokenTimeout);
		}
		aliAccount.setUpdate_time(new Date().getTime() / 1000);
		this.zhangHaoDao.merge(aliAccount);
	}
	
	/**
	 * ��������ͨǩ��
	 * @param postUrl
	 * @param paramMap
	 * @return
	 */
	public String getAliSignature(String postUrl, Map<String, String> paramMap, 
			String appSecret) {
		String urlPart = "";
		if (postUrl != null) {
			urlPart = postUrl.split("\\?")[0].replace((String) ApplicationUtils.get("aliApiUrl"), "");
		}
		
		String paramStr = "";
		Object[] keyArr = paramMap.keySet().toArray();
		Arrays.sort(keyArr);
		for (int i = 0; i < keyArr.length; i++) {
			paramStr += keyArr[i].toString() + paramMap.get(keyArr[i].toString());
		}
		
		if (urlPart.equals("") && paramStr.equals("")) {
			return null;
		}
		
		String signData = urlPart + paramStr;
		String signKey = appSecret;
		try {
			String sign = hmac_sha1(signData, signKey);
			return sign.toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String hmac_sha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();           
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            String hexBytes = byte2hex(rawHmac);
            return hexBytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	private String byte2hex(final byte[] b){
	    String hs="";
	    String stmp="";
	    for (int n = 0; n < b.length; n++){
	        stmp=(java.lang.Integer.toHexString(b[n] & 0xFF));
	        if (stmp.length()==1) hs=hs+"0"+stmp;
	            else hs=hs+stmp;
	    }
	    return hs;
	} 
	
	/**
	 * ��ӹ������������
	 * 
	 * @param paramMap
	 * @param aliAccount
	 */
	public boolean putSystemParamsToParamMap(Map<String, String> paramMap, 
			ZhangHao aliAccount) {
		String accessToken = this.getAccessToken(aliAccount);
		if (accessToken == null) {
			return false;
		}
		
		paramMap.put("access_token", accessToken);
//	paramMap.put("_aop_timestamp", String.valueOf(new Date().getTime()));
		return true;
	}
	
	/**
	 * ��ȡ����ͨ�˺��б�
	 * @param bdUserId
	 * @return
	 */
	public List<ZhangHao> getAliAccounts(Long bdUserId) {
		return zhangHaoDao.getAll(ACCOUNT_TYPE, bdUserId);
	}
}
