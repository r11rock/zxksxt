package com.demo.dao;

import java.util.List;

import com.demo.entity.LeiMuTable;
import com.demo.entity.order.OrderTable;
import com.demo.entity.user.GuKeTable;


public interface LeiMuDao extends BaseDao<LeiMuTable,Long>
{
	//��ѯȫ����Ŀ
	public List<LeiMuTable> getAllLeiMu();
	//�ñ�Ų�ѯȫ��
    public abstract List<LeiMuTable> getSelId(Long long1);
	//�òɹ���Ų�ѯ��Ŀ
    public abstract LeiMuTable getLeiMuUser(Long long1);
	//����Ŀ���Ʋ�ѯȫ��
    public abstract List<LeiMuTable> getLmName(String name);
    //����Ŀ���Ʋ�ѯȫ��
    public abstract List<LeiMuTable> getAllName(String name);
    //����cateId��ѯ
    public LeiMuTable getByCateId(String cateId);
}