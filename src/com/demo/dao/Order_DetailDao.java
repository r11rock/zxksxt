package com.demo.dao;

import java.util.List;

import com.demo.entity.order.Order_Detail;

public interface Order_DetailDao extends BaseDao<Order_Detail, Long>{
	//�����Ų�ѯȫ��
	public List<Order_Detail> getAllOrder(String order);
	//��ѯȫ����Ŀ
	public String getAllCategory();
	//�ñ�Ų�ѯȫ��
	public List<Order_Detail> getAllSelId(Long id);
}
