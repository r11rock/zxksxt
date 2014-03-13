package com.demo.dao.user;
import java.util.List;

import com.demo.dao.BaseDao;
import com.demo.entity.user.CaiGou;
import com.demo.entity.user.CaiWuTable;

public interface CaiWuDao extends BaseDao<CaiWuTable, Long>{
	CaiWuTable getByUserId(Long userId);
	//��ѯȫ���ɹ�
	List<CaiWuTable> getCaiWu();
}