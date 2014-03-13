package com.demo.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.demo.dao.Express.YunFeiTableDao;
import com.demo.entity.Express.YunFeiTable;
@Repository
public class YunFeiTableDaoImpl extends BaseDaoImpl<YunFeiTable, Long> implements YunFeiTableDao{
	public YunFeiTableDaoImpl() {
		super(YunFeiTable.class);
	}
	//���Ҷ�Ӧ�� ��ѯ
	public YunFeiTable getCorresponding(String Corresponding){
		return ht.findFirst("from YunFeiTable where Corresponding = ?", new Object[]{Corresponding});
	}
}
