package com.demo.dao.user;

import java.util.List;

import com.demo.dao.BaseDao;
import com.demo.entity.user.YeWu;

public interface YeWuDao extends BaseDao<YeWu, Long>{
	YeWu getByUserId(Long userId);
	//��ѯȫ��ҵ��
	public List<YeWu> getYeWuAll();
}