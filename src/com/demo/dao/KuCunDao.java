package com.demo.dao;


import java.util.List;

import com.demo.entity.KuCunTable;

public interface KuCunDao  extends BaseDao<KuCunTable,Long>{
	//��ǺŲ�ѯȫ��
	public KuCunTable getKuCunAll(String biaoji,String orderId);
	//�����Ų�ѯȫ��
	public KuCunTable getOrderAll(String orderid);
	//�ñ�Ų�ѯȫ��
	public KuCunTable getBiaoHao(String biaoji);
}
