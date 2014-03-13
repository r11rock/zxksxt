package com.demo.dao.user;

import java.util.List;

import com.demo.dao.BaseDao;
import com.demo.entity.user.GuKeTable;


public interface GuKeDao extends BaseDao<GuKeTable,Long>
{

    public abstract GuKeTable getByUserId(Long long1);
    //��ѯȫ���˿�
    public abstract List<GuKeTable> getGuKeAll();
  //��Ų�ѯȫ���˿� 
    public abstract GuKeTable getSelAllGuKe(Long userid);
    //�����鿴ȫ��
    public abstract String getGuKeName(String name);
}