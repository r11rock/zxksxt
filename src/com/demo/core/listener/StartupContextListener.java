package com.demo.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

import com.demo.core.config.ConfigHelper;
import com.demo.utils.ApplicationUtils;

/**
 * ��������ʱִ��
 *
 */
public class StartupContextListener extends ContextLoaderListener {

	private static final Log log = LogFactory
			.getLog(StartupContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("initializing context...");
		}
		ServletContext context = event.getServletContext();
		super.contextInitialized(event);

		ApplicationUtils.setServletContext(context);
		ConfigHelper.saveSiteConfig2Application(); // ��ȡsite-config.xml�����ļ�
		setupContext(context);
	}
	
	
	public static void setupContext(ServletContext context) {
		if (log.isDebugEnabled()) {
			log.debug("Initialization complete [OK]");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		super.contextDestroyed(arg0);
		
	}
	
}
