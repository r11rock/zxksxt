package com.demo.biz;

import com.demo.entity.user.UserInfo;

public interface SystemBiz {

	/**
	 * ����û���¼��ʧ��ʱ�׳��쳣
	 * @param account
	 * @param pwd
	 * @param ip
	 */
	public abstract UserInfo checkUserLogin(String username, String pwd, String ip);

}