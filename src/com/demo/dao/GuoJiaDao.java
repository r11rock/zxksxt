package com.demo.dao;

import java.util.List;

import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.order.OrderTable;

public interface GuoJiaDao  extends BaseDao<YunFeiTable,Long>{
	public abstract String getGuoJia(Long quyu,String guojia);//��ѯȫ�������˷�
	public abstract List<YunFeiTable> getAllGuoJia(String id);//�ù��Ҳ�ѯ
	public List<YunFeiTable> getAllGuoJia();//�鿴ȫ������
    //�鿴�˷ѱ���ĸ
    public List<YunFeiTable> getYunTable();
	public abstract YunFeiTable getGuoJiaAll(Long id);//��Ų�ѯ����
}
