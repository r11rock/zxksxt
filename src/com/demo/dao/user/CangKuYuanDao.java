package com.demo.dao.user;

import java.util.List;

import com.demo.dao.BaseDao;
import com.demo.entity.user.CangKuYuan;

public interface CangKuYuanDao extends BaseDao<CangKuYuan,Long>
{
    public abstract CangKuYuan getByUserId(Long long1);
    //��ѯȫ���ֿ�
    public abstract List<CangKuYuan> getCangKuYuanAll();
}
