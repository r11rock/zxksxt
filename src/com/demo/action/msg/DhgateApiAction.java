package com.demo.action.msg;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.demo.action.BaseAction;
import com.demo.biz.dhgate.DhCommonApiBiz;
import com.demo.biz.dhgate.DhMsgApiBiz;
import com.demo.biz.msg.DhMsgBiz;
import com.demo.dao.ZhangHaoDao;
import com.demo.entity.ZhangHao;
import com.demo.utils.DES;
import com.demo.utils.Struts2Utils;
import com.demo.vo.LoginInfo;

/**
 * �ػ���վ��������ͬ��
 *
 */
@Controller("msg.dhgateApiAction")
@Scope("prototype")
public class DhgateApiAction extends BaseAction {

	private static final long serialVersionUID = 1L;

    @Resource
    private ZhangHaoDao zhangHaoDao;
    @Resource
    private DhCommonApiBiz dhCommonApiBiz;
    @Resource
    private DhMsgApiBiz dhMsgApiBiz;
    @Resource
    private DhMsgBiz dhMsgBiz;

    private List<ZhangHao> accounts;
    private String account;
    private String readStatus;
    private String msgType;
    private Integer beforeDay;
    
    public String execute() {
    	Long queryUserId = null;
		if ((Integer) this.getFromSession("role") != 1) { // �ǹ���Ա�û�
			queryUserId = ((LoginInfo) this.getFromSession("logininfo")).getUserId();
		}
		accounts = dhCommonApiBiz.getDhAccounts(queryUserId);
    	return SUCCESS;
    }
    
    public String downMsg() {
    	String result = dhMsgApiBiz.fetchMsg(account, msgType, beforeDay);
    	boolean success = true;
    	if (result.contains("��������")) {
    		success = false;
    	}
    	
    	// ִ��һ�η���
    	if (success) {
    		ZhangHao dhAccount = zhangHaoDao.findUnique(account,
    				DhCommonApiBiz.ACCOUNT_TYPE);
    		dhMsgBiz.fenpeiMsg(dhAccount);
    	}
    	
    	Struts2Utils.renderJson(result, success);
    	return null;
    }
    
    public String getToken() {
    	String authAccount = Struts2Utils.getParameter("authAccount");
    	String authPass = Struts2Utils.getParameter("authPass");
    	String result = dhCommonApiBiz.fetchAccessToken(authAccount, authPass);
    	if (result == null) {
    		result = "��Ȩʧ��: δ֪����"; 
    	}
    	
    	boolean success = true;
    	if (result.contains("��Ȩʧ��")) {
    		success = false;
    	}
    	if (success) {
    		ZhangHao zhangHao = zhangHaoDao.findUnique(authAccount, 
    				DhCommonApiBiz.ACCOUNT_TYPE);
    		if (zhangHao != null) {
    			zhangHao.setPass(new DES().getEncString(authPass));
    			zhangHaoDao.merge(zhangHao);
    		}
    	}
    	
    	Struts2Utils.renderJson(result, success);
    	return null;
    }

	public List<ZhangHao> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<ZhangHao> accounts) {
		this.accounts = accounts;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getBeforeDay() {
		return beforeDay;
	}

	public void setBeforeDay(Integer beforeDay) {
		this.beforeDay = beforeDay;
	}
}
