package com.demo.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.utils.AppException;
import com.demo.dao.user.UserDao;
import com.demo.entity.user.UserInfo;

/**
 * ϵͳҵ����
 * @author Administrator
 *
 */
@Service
public class SystemBizImpl extends BaseBizImpl implements SystemBiz{
	@Resource
	private UserDao userDao;
	/* (non-Javadoc)
	 * @see com.exam.biz.SystemBiz#checkUserLogin(java.lang.String, java.lang.String, java.lang.String)
	 */
	public UserInfo checkUserLogin(String username, String pwd, String ip){
		UserInfo user = userDao.getByAccount(username);
		if(user == null){
			throw new AppException("�ʺ�" + username + "������!");
		}
		if(!user.getPwd().equals(pwd)){
			throw new AppException("�ʺ����벻��!");
		}
		//TODO:��¼��־
		return user;
	}
}
