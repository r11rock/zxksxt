package com.demo.dao;

import java.util.List;

import com.demo.entity.YunCun;
import com.demo.entity.order.OrderTable;

public interface YuCunDao extends BaseDao<YunCun, Long>{
	//�˿ͱ�Ų�ѯ
	public List<YunCun> getGuKeId(Long gukeid);
	//��ѯȫ��Ԥ��
	public String getYuCun(String time,String time1);
	//�˿ͱ�Ų�ѯ���һ������
	public List<YunCun> getLastNum(Long gukeid);
	//����ѯ �ÿͻ�userid��ѯ���һ������
	public List<YunCun> getUserLastNum(Long gukeid);
	//�鿴�˷�����
	public String getXqYuCun(Long userid);
}