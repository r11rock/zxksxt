package com.demo.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.demo.biz.aliexpress.AliCommonApiBiz;
import com.demo.biz.aliexpress.AliOrderApiBiz;
import com.demo.biz.dhgate.DhCommonApiBiz;
import com.demo.biz.dhgate.DhMsgApiBiz;
import com.demo.biz.dhgate.DhOrderApiBiz;
import com.demo.dao.ZhangHaoDao;
import com.demo.entity.ZhangHao;

public class SynchDataJob implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

	@Resource
	private DhOrderApiBiz dhOrderApiBiz;
	@Resource
	private DhMsgApiBiz dhMsgApiBiz;
	@Resource
	private AliCommonApiBiz aliCommonApiBiz;
	@Resource
	private AliOrderApiBiz aliOrderApiBiz;
	@Resource
	private ZhangHaoDao zhangHaoDao;
	
	public void execute() {
		System.out.println("----ͬ���ػͺ�����ͨ����----");
		
		List<ZhangHao> zhangHaos = zhangHaoDao.getAllZhangHao();
		for (ZhangHao zhangHao : zhangHaos) {
			if (zhangHao.getAccount_type().equals(DhCommonApiBiz.ACCOUNT_TYPE)) { // �ػ��˺�
				// ͬ����������
				String result = dhOrderApiBiz.autoFetchOrders(zhangHao);
				System.out.println("�˺�" + zhangHao.getAccount() + "��������ͬ�����: " + result);
				// ͬ��վ��������
//				result = dhMsgApiBiz.autoFetchMsg(zhangHao);
//				System.out.println("�˺�" + zhangHao.getAccount() + "վ��������ͬ�����: " + result);
			} else if (zhangHao.getAccount_type().equals(AliCommonApiBiz.ACCOUNT_TYPE)) { // ����ͨ�˺�
				// ͬ����������
//				String result = aliOrderApiBiz.autoFetchOrders(zhangHao);
//				System.out.println("�˺�" + zhangHao.getAccount() + "��������ͬ�����: " + result);
			}
		}
		
		// ÿ���23.30���Ҽ������ͨ��refreshToken�Ƿ����
		Integer curHour = Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
		Integer curMin = Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
		if (curHour == 23 && curMin > 20 && curMin < 40) {
			for (ZhangHao zhangHao : zhangHaos) {
				if (!zhangHao.getAccount_type().equals(AliCommonApiBiz.ACCOUNT_TYPE) ||
						zhangHao.getApp_key() == null ||
						zhangHao.getApp_key().equals("")) {
					continue;
				}
				
				aliCommonApiBiz.checkAndUpdateRefreshToken(zhangHao);
			}
		}
		
	}
}
