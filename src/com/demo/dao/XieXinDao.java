package com.demo.dao;

import java.util.List;

import com.demo.entity.XieXinTable;

public interface XieXinDao extends BaseDao<XieXinTable, Long>{
	//�鿴��
	public String getAllKanXin(Long userid);
	//�鿴δ����Ϣ
	public List<XieXinTable> getAllWeiDu(Long userid);
	//�鿴ȫ��δ����Ϣ
	public String getAllXinXi(Long userid);
	//�����˲�ѯȫ��
	public String getFjjl(String fjr);
}
