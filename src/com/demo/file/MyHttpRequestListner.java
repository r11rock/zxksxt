package com.demo.file;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class MyHttpRequestListner implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
		System.out.println("����:" + request.getRequestURL() + "������.");
	}

	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest request = (HttpServletRequest)arg0.getServletRequest();
		String logstr = "IP:" + request.getRemoteAddr() + "��" 
		+ new java.sql.Timestamp(System.currentTimeMillis()) 
		+ "����" + request.getRequestURL();
		System.out.println(logstr);
	}

}
