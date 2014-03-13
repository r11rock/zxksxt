package com.demo.biz.aliexpress;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LeiMuDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.ZhangHaoDao;
import com.demo.entity.LeiMuTable;
import com.demo.entity.ZhangHao;
import com.demo.entity.order.OrderTable;
import com.demo.utils.ApplicationUtils;
import com.demo.utils.DateUtils;
import com.demo.utils.HttpClientUtils;

@Service
@Transactional
public class AliOrderApiBiz {

	@Resource
	private AliCommonApiBiz aliCommonApiBiz;
	@Resource
	private ZhangHaoDao zhangHaoDao;
	@Resource
	private OrderTableDao orderTableDao;
	@Resource
	private LeiMuDao leiMuDao;
	
	private final Integer pageSize = 20;
	private Integer updateCount = 0;
	
	/**
	 * ͬ����������(�����Զ�ͬ��)
	 * @return
	 */
	public String autoFetchOrders(ZhangHao aliAccount) {
		Date endDate = new Date();
		Date startDate = null;
		Long orderUpdateTime = aliAccount.getOrder_update_time();
		if (orderUpdateTime == null) {
			startDate = DateUtils.getAfterDaysDate(endDate, -5); // ��ʼ��5��ǰ�����ݿ�ʼȡ
		} else {
			startDate = new Date(orderUpdateTime);
		}
		
		String result = this.fetchOrders(aliAccount, startDate, endDate);
		if (!result.contains("����")) {
			// ����ͬ��ʱ��
			aliAccount.setOrder_update_time(endDate.getTime());
			zhangHaoDao.merge(aliAccount);
		}
		return result;
	}
	
	public String fetchOrders(String account, Date startDate, Date endDate) {
		ZhangHao aliAccount = zhangHaoDao.findUnique(account, 
				AliCommonApiBiz.ACCOUNT_TYPE);
		return this.fetchOrders(aliAccount, startDate, endDate);
	}
	
	public String fetchOrders(ZhangHao aliAccount, Date startDate, Date endDate) {
		try {
			updateCount = 0;
			String strStartDate =  new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.format(startDate);
			String strEndDate =  new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.format(endDate);
			String result = this.fetchOrders(aliAccount, strStartDate, strEndDate, 1);
			if (result.equals("success")) {
				return "���γɹ����� " + updateCount + " ����������";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "��������δ֪�쳣";
		}
	}
	
	/**
	 * ץȡһҳ��������
	 * @param aliAccount
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @return
	 */
	private String fetchOrders(ZhangHao aliAccount, String startDate, 
			String endDate, Integer pageNum) {
		String apiUrl = ApplicationUtils.get("aliApiUrl") 
				+ "param2/1/aliexpress.open/api.findOrderListQuery/"
				+ aliAccount.getApp_key();
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!aliCommonApiBiz.putSystemParamsToParamMap(paramMap, aliAccount)) {
			return "��������" + AliCommonApiBiz.ERR_TOKEN;
		}
		paramMap.put("page", pageNum.toString());
		paramMap.put("pageSize", pageSize.toString());
		paramMap.put("createDateStart", startDate);
		paramMap.put("createDateEnd", endDate);
		paramMap.put("orderStatus", "");
		
		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			System.out.println(respJson.toString());
			if (respJson.containsKey("error_code")) {
				if (respJson.getString("error_code").equals("401")) {
					aliCommonApiBiz.clearAccessToken(aliAccount);
					return "��������" + AliCommonApiBiz.ERR_TOKEN;
				} else {
					return "��������" + respJson.getString("error_message");
				}
			}
			
			if (respJson.getString("orderList").equals("null") || 
					respJson.getString("orderList").equals("[]") ) {
				return "success";
			}
			// �������
			JSONArray orders = respJson.getJSONArray("orderList");
			for (int i = 0; i < orders.size(); i++) {
				JSONObject json = orders.getJSONObject(i);
				updateCount += this.saveOrder(json, aliAccount);
			}
			
			// ��ȡ����������pagesize���ʱ������ȡ��һҳ
			if (orders.size() == this.pageSize) {
				this.fetchOrders(aliAccount, startDate, endDate,
						pageNum + 1);
			}
			return "success";
		}
		return "��������" + AliCommonApiBiz.CONN_ERR;
	}
	
	/**
	 * ����һ������
	 * @param json
	 * @param aliAccount
	 * @return
	 */
	private int saveOrder(JSONObject json, ZhangHao aliAccount) {
		String orderId = json.getString("orderId");
		List<OrderTable> orders = orderTableDao.getOrder(orderId);
		if (orders != null && !orders.isEmpty()) {
			return 0;
		}
		
		Double money = new Double("0"); // ���
		Double yunfei = new Double("0"); // �˷�
		String wuping = ""; // ��Ʒ
		JSONArray products = json.getJSONArray("productList");
		for (int i = 0; i < products.size(); i++) {
			// ������
			money += products.getJSONObject(i).getJSONObject("totalProductAmount")
					.getDouble("amount");
			// �����˷�
			yunfei += products.getJSONObject(i).getJSONObject("logisticsAmount")
					.getDouble("amount");
			// ������Ʒ
			if (!wuping.equals("")) {
				wuping += "+";
			}
			wuping += products.getJSONObject(i).getString("productName")
					+ "(����:" + products.getJSONObject(i).getString("productCount") + ")" ;
		}
		// ȡ��Ŀ
		Long leiMuId = null;
		String leiMuName = null;
		List<LeiMuTable> leiMus = leiMuDao.getAllName(aliAccount.getAccount());
		if (leiMus != null && !leiMus.isEmpty()) {
			leiMuId = leiMus.get(0).getId();
			leiMuName = leiMus.get(0).getLeimu();
		}
		
		OrderTable order = new OrderTable();
		order.setOrderId(orderId);
		order.setMoney(money);
		order.setYunfei(yunfei);
		order.setWuping(wuping);
		order.setLeimuid(leiMuId);
		order.setYunshu(leiMuName);
		order.setZhanghaoId(aliAccount.getId());
		order.setTime(new Date());
		orderTableDao.merge(order);
		return 1;
	}
	
}
