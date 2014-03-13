package com.demo.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.dao.XieXinDao;
import com.demo.entity.XieXinTable;
@Repository
public class XieXinDaoImpl extends BaseDaoImpl<XieXinTable, Long> implements XieXinDao{
	public XieXinDaoImpl() {
		super(XieXinTable.class);
	}
	//�鿴��
	public String getAllKanXin(Long userid){
		String hql = "from XieXinTable where userid = "+userid+" order by time desc";
		return hql;
	}
	//�鿴δ����Ϣ
	public List<XieXinTable> getAllWeiDu(Long userid){
		List<XieXinTable> hql = ht.find("select count(*) from XieXinTable where userid = "+userid+" and (chakan = 0 or chakan is null)");
		return hql;
	}
	//�鿴ȫ��δ����Ϣ
	public String getAllXinXi(Long userid){
		String hql = "from XieXinTable where userid = "+userid+" and (chakan = 0 or chakan is null)";
		return hql;
	}
	//�����˲�ѯȫ��
	public String getFjjl(String fjr){
		System.out.println("++fjr++"+fjr);
		String hql = "from XieXinTable where fajianren='"+fjr+"'";
		return hql;
	}
}
