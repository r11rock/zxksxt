package com.demo.file;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineUserStatsListener 
implements HttpSessionListener,HttpSessionActivationListener {
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("Session:" + arg0.getSession().getId() + "�Ѽ���!");
		
	}
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("Session:" + arg0.getSession().getId() + "������Ч!");
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		ServletContext application =
			arg0.getSession().getServletContext();
		Integer total = (Integer)application.getAttribute("onlineusernum");
		if(total == null){
			total = 1;
		}else{
			total ++;
		}
		application.setAttribute("onlineusernum", total);
		System.out.println("�����µĻỰ,��IDΪ:" + arg0.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		ServletContext application =
			arg0.getSession().getServletContext();
		Integer total = (Integer)application.getAttribute("onlineusernum");
		if(total != null){
			application.setAttribute("onlineusernum", total-1);
		}
		System.out.println("���ٻỰ,��IDΪ:" + arg0.getSession().getId());
	}

}
