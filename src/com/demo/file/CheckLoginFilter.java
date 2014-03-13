package com.demo.file;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entity.user.UserInfo;
import com.demo.vo.LoginInfo;
import bzscom.p_h_fun;

/**
 * �������ع�����
 * 
 * @author gent
 * @since 2012-04-13
 */
public class CheckLoginFilter implements Filter {
	protected FilterConfig filterConfig;
	protected boolean enabled;

	protected String eventMonitorEnabel;
	/**
	 * ����
	 */
	public CheckLoginFilter() {
		filterConfig = null;
		enabled = true;
	}

	/**
	 * ��ʼ��
	 */
	public void init(FilterConfig pFilterConfig) throws ServletException {
		this.filterConfig = pFilterConfig;
		String value = filterConfig.getInitParameter("enabled");
		if (value == null||value.compareTo("")==0) {
			this.enabled = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.enabled = true;
		} else {
			this.enabled = false;
		}
		eventMonitorEnabel = p_h_fun.GetSystemParam("requestMonitor");
	}

	/**
	 * ���˴���
	 */
	  public void doFilter(ServletRequest pRequest, ServletResponse pResponse, FilterChain fc)
      throws IOException, ServletException
      {
      HttpServletRequest request = (HttpServletRequest)pRequest;
      HttpServletResponse response = (HttpServletResponse)pResponse;
      String ctxPath = request.getContextPath();
      String requestUri = request.getRequestURI();
      String uri = requestUri.substring(ctxPath.length());
      Object acc = request.getSession().getAttribute("logininfo");
      LoginInfo account = null;
      if(acc != null){
          account = (LoginInfo)acc;
      }
      if(uri.equals("/index.jsp") || uri.equals("/system!login.do") || uri.equals("/system!registered.do") || uri.equals("/system!zuche.do")){
          fc.doFilter(pRequest, pResponse);
      }
      else
      if(account != null){
          fc.doFilter(pRequest, pResponse);
      }
      else
      if(account == null && !uri.equals("/index.jsp"))
      {
          response.setContentType("text/html; charset=UTF-8");
          PrintWriter out = response.getWriter();
          out.print("<script language=javascript>alert('�Ự��ʱ�����µ�¼');javascript:location='/order/index.jsp';</script>");
          out.flush();
          out.close();
          return;
      }
  }


	/**
	 * ����
	 */
	public void destroy() {
		filterConfig = null;
	}
}
