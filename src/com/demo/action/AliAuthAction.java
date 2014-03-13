package com.demo.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.demo.biz.aliexpress.AliCommonApiBiz;
import com.demo.dao.ZhangHaoDao;
import com.demo.entity.ZhangHao;
import com.demo.utils.Struts2Utils;

@Controller("aliAuthAction")
@Scope("prototype")
public class AliAuthAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private AliCommonApiBiz aliCommonApiBiz;
	@Resource
	private ZhangHaoDao zhangHaoDao;

	private String code;
	private String account;
	
	// ������Ȩ��ַ
	public String authUrl() {
		ZhangHao aliAccount = zhangHaoDao.findUnique(account, 
				AliCommonApiBiz.ACCOUNT_TYPE);
		String url = aliCommonApiBiz.getAuthUrl(aliAccount);
		if (url == null) {
			Struts2Utils.renderJson("������������ϵ����Ա�����˺��Ƿ���д��App Key��App Secret��Ϣ", false);
		} else {
			Struts2Utils.getSession().setAttribute("authAliAcount", account);
			Struts2Utils.renderJson(url, true);
		}
		return null;
	}
	
	// �ص���ַ
	public String execute() {
		String result = null;
		if (code == null) {
			result = "�������󣺻�ȡCodeʧ��";
		} else {
			account = (String) Struts2Utils.getSession().getAttribute("authAliAcount");
			Struts2Utils.getSession().removeAttribute("authAliAcount");
			
			ZhangHao aliAccount = zhangHaoDao.findUnique(account, 
					AliCommonApiBiz.ACCOUNT_TYPE);
			result = aliCommonApiBiz.fetchAccessToken(code, aliAccount);
		}

		if (result.equals("success")) {
			result = "��ȡ��Ȩ�ɹ���";
		}
		String html = "<script type='text/javascript'>"
				+ "alert('" + result + "');"
				+ "parent.closeAliAuthDialog();"
				+ "</script>";
		Struts2Utils.renderHtml(html);
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
