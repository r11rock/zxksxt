/*jadclipse*/// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   UserDao.java

package com.demo.dao.user;

import com.demo.dao.BaseDao;
import com.demo.entity.*;
import com.demo.entity.user.CaiGou;
import com.demo.entity.user.CaiGouAdmin;
import com.demo.entity.user.UserInfo;
import com.demo.entity.user.YeWu;

import java.util.List;

// Referenced classes of package com.demo.dao:
//            BaseDao

public interface UserDao extends BaseDao<UserInfo ,Long>
{

    public abstract UserInfo getByAccount(String s);

    public abstract UserInfo getByUserNameOnPwd(String s, String s1);

    public abstract UserInfo getUserId(Long long1);

    public abstract UserInfo getPwd(String s);
    //����Ա�鿴ȫ��Ա���˺�
    public abstract String getzhanghao(String s);

    public abstract CaiGou getcaigou(Long long1);

    public abstract CaiGouAdmin getcaigouadmin(Long long1);

    public abstract YeWu getyewu(Long long1);

    public abstract void getUser(Long long1);
    //�鿴ȫ���˺�
    public abstract List<UserInfo> getAllUser();
    //��ѯȫ��Ա��
    public abstract List<UserInfo> getYuanGong();
    //��ѯ����Ա
    public abstract List<UserInfo> getAdmin();
    //�鿴δ����
    public List<UserInfo> getWeiDuXin(Long userid);
}