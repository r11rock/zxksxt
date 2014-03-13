package com.demo.biz.dhgate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
public class DhOrderApiBiz {
	
	@Resource
	private DhCommonApiBiz dhCommonApiBiz;
	@Resource
	private DhProductApiBiz dhProductApiBiz;
	@Resource
	private OrderTableDao orderTableDao;
	@Resource
	private ZhangHaoDao zhangHaoDao;
	@Resource
	private LeiMuDao leiMuDao;
	
	private final Integer pageSize = 20;
	private Integer updateCount = 0;
	
	/**
	 * 同步订单数据(用于自动同步)
	 * @return
	 */
	public String autoFetchOrders(ZhangHao dhAccount) {
		Date endDate = new Date();
		Date startDate = null;
		Long orderUpdateTime = dhAccount.getOrder_update_time();
		if (orderUpdateTime == null) {
			startDate = DateUtils.getAfterDaysDate(endDate, -5); // 初始从5天前的数据开始取
		} else {
			startDate = new Date(orderUpdateTime);
		}
		
		String strStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(startDate);
		String strEndDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.format(endDate);
		String result = this.fetchOrders(dhAccount, strStartDate, strEndDate);
		if (!result.contains("错误")) {
			// 更新同步时间
			dhAccount.setOrder_update_time(endDate.getTime());
			zhangHaoDao.merge(dhAccount);
		}
		return result;
	}
	
	public String fetchOrders(ZhangHao dhAccount, String startDate, String endDate) {
		try {
			updateCount = 0;
			String result = this.fetchOrders(dhAccount, startDate, endDate, 1);
			if (result.equals("success")) {
				return "本次成功更新 " + updateCount + " 条订单数据";
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "发生错误：未知异常";
		}
	}
	
	private String fetchOrders(ZhangHao dhAccount,  String startDate, 
			String endDate, Integer pageNum) {
		String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!dhCommonApiBiz.putSystemParamsToParamMap(paramMap, dhAccount,
				"dh.order.list.get", "1.1")) {
			return "发生错误：" + DhCommonApiBiz.ERR_TOKEN;
		}
		paramMap.put("startdate", startDate);
		paramMap.put("enddate", endDate);
		paramMap.put("statusList", "");
		paramMap.put("pagesize", pageSize.toString());
		paramMap.put("pages", pageNum.toString());
		
		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (respJson.containsKey("code")) {
				if (respJson.getString("code").equals("2")) {
					dhCommonApiBiz.clearAccessToken(dhAccount);
					return "发生错误：" + DhCommonApiBiz.ERR_TOKEN;
				} else if (!respJson.getString("code").equals("0")) {
					return "发生错误：" + respJson.getString("message");
				}
			}
			
			JSONObject statusObj = respJson.getJSONObject("status");
			if (statusObj.getString("message").equalsIgnoreCase("OK")) {
				if (respJson.getString("rfxlist").equals("null") || 
						respJson.getString("rfxlist").equals("[]") ) {
					return "success";
				}
				
				JSONArray orders = respJson.getJSONArray("rfxlist");
				for (int i = 0; i < orders.size(); i++) {
					updateCount += this.updateOrder(dhAccount, orders.getJSONObject(i));
				}
				
				// 当取到的数量与pagesize相等时，继续取下一页
				if (orders.size() == this.pageSize) {
					this.fetchOrders(dhAccount, startDate, endDate, pageNum + 1);
				}
				
				return "success";
			} else {
				return "发生错误：" + statusObj.getString("message");
			}
		}
		return "发生错误：" + DhCommonApiBiz.CONN_ERR;
	}
	
	/**
	 * 更新订单数据到本地数据库
	 * @param dhAccount
	 * @param json
	 * @return 是否有更新: 0-没有更新（不需要）；1-有更新
	 */
	private int updateOrder(ZhangHao dhAccount, JSONObject json) {
		String orderId = json.getString("rfxno");
		List<OrderTable> orders = orderTableDao.getOrder(orderId);
		if (orders != null && !orders.isEmpty()) { // 已存在, 不再重复入库
			return 0;
		}
		
		OrderTable order = new OrderTable();
		order.setOrderId(orderId);
		order.setOrdersn(this.getOrderSn());
		order.setYunfei(json.getDouble("shipcost")); // 运费
		order.setAddress1(json.getString("addressline1")); // 地址
		order.setGuowaidizhi(json.getString("addressline1"));
		order.setCountry(json.getString("country")); // 国家
		order.setCity(json.getString("city")); // 城市
		order.setProvince(json.getString("state")); // 州
		order.setPostcode(json.getString("postalcode")); // 邮编
		order.setTelephone(json.getString("tel")); // 电话
		order.setReceiver(json.getString("firstname") + " " + json.getString("lastname")); // 收货人
		order.setShippingtype(json.getString("shippingtype")); // 运输方式
		if (!json.getString("paydate").equals("null")) {
			order.setPaydate(json.getLong("paydate")); //付款时间
		}
		if (!json.getString("rfxremark").equals("null")) {
			order.setRemark(json.getString("rfxremark")); // 备注
		}
		// 取订单物品
		JSONObject orderProduct = this.getOrderProduct(json.getString("rfxid"), dhAccount);
		JSONArray products = orderProduct.getJSONArray("lstProductDTO");
		Double money = new Double("0");  // 金额
		String wuping = ""; // 物品
		String bianma = ""; // 编码
		for (int i = 0; i < products.size(); i++) {
			// 计算金额
			money += products.getJSONObject(i).getDouble("targetprice") *
					products.getJSONObject(i).getDouble("quantity");
			// 计算物品
			if (!wuping.equals("")) {
				wuping += "+";
			}
			wuping += products.getJSONObject(i).getString("productname")
					+  "(数量:" + products.getJSONObject(i).getString("quantity") + ")" ;
			// 取编码
			if (bianma.equals("")) {
				bianma = products.getJSONObject(i).getString("itemcode");
			}
		}
		order.setMoney(money);
		order.setWuping(wuping);
		order.setBianma(bianma);
		// 取类目
		LeiMuTable leiMu = this.getOrderLeiMu(orderProduct, dhAccount);
		if (leiMu != null) {
			order.setLeimuid(leiMu.getId());
			order.setYunshu(leiMu.getLeimu());
		}
		order.setZhanghaoId(dhAccount.getId());
		order.setTime(new Date());
		orderTableDao.merge(order);
		return 1;
	}
	
	/**
	 * 获取一个订单信息
	 * @param rfxno 订单号
	 * @param dhAccount
	 * @return
	 */
	public JSONObject getOrder(String rfxno, ZhangHao dhAccount) {
		String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!dhCommonApiBiz.putSystemParamsToParamMap(paramMap, dhAccount,
				"dh.order.get")) {
			return null;
		}
		paramMap.put("rfxno", rfxno);

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (respJson.containsKey("code") && !respJson.getString("code").equals("0")) {
				return null;
			}
			
			JSONObject statusObj = respJson.getJSONObject("status");
			if (statusObj.getString("message").equalsIgnoreCase("OK")) {
				return respJson;
			}
		}
		return null;
	}
	
	/**
	 * 获取订单类目
	 * @param orderProduct
	 * @return
	 */
	public LeiMuTable getOrderLeiMu(JSONObject orderProduct, ZhangHao dhAccount) {
		// 取产品详情
		String itemCode = orderProduct.getJSONArray("lstProductDTO").getJSONObject(0).getString("itemcode");
		JSONObject product = dhProductApiBiz.getProduct(itemCode, dhAccount);
		if (product == null) {
			return null;
		}
		// 取产品catePubId对应的userId，执行分配
		String catePubId = product.getJSONObject("product").getString("catePubId");
		String parentCatePubId = catePubId.substring(0, 3);
		LeiMuTable leiMu = leiMuDao.getByCateId(parentCatePubId);
		if (leiMu == null) {
			return null;
		}
		return leiMu;
	}
	
	/**
	 * 获取订单的产品信息
	 * @param rfxid 订单主键id
	 * @param dhAccount
	 * @return
	 */
	public JSONObject getOrderProduct(String rfxid, ZhangHao dhAccount) {
		String apiUrl = (String) ApplicationUtils.get("dhgateApiUrl");
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!dhCommonApiBiz.putSystemParamsToParamMap(paramMap, dhAccount,
				"dh.order.product.get")) {
			return null;
		}
		paramMap.put("rfxid", rfxid);

		JSONObject respJson = HttpClientUtils.doPost(apiUrl, paramMap);
		if (respJson != null) {
			if (respJson.containsKey("code") && !respJson.getString("code").equals("0")) {
				return null;
			}
			
			JSONObject statusObj = respJson.getJSONObject("status");
			if (statusObj.getString("message").equalsIgnoreCase("OK")) {
				return respJson;
			}
		}
		return null;
	}
	
	private String getOrderSn() {
		return new SimpleDateFormat("yyyy-MM-dd-HHmmss-SSS").format(new Date())
				+ new Random().nextInt(1000);
	}
}
