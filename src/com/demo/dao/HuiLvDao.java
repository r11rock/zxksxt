package com.demo.dao;
import java.util.List;

import com.demo.entity.HuiLvTable;
import com.demo.entity.user.CaiGou;

public interface HuiLvDao extends BaseDao<HuiLvTable, Long>{
	//ʱ���ѯȫ��
	public abstract HuiLvTable getHuiLvTime(String time);
	//ʱ���ѯȫ��
	public abstract List<HuiLvTable>  getHuiTime(String time);
}