/*jadclipse*/// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   OrderTableDao.java

package com.demo.dao;

import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.order.DhgateAccounts;
import com.demo.entity.order.OrderTable;
import com.demo.entity.order.Order_Detail;

import java.util.List;

// Referenced classes of package com.demo.dao:
//            BaseDao

public interface OrderTableDao extends BaseDao<OrderTable,Long>
{
	//�ɹ��鿴����С��0
    public abstract List<OrderTable> getCaiGouLiRun(Long long1, String s, String s1, String s2,String zhanghaoId);

    public abstract List<OrderTable> getCaiGouLiRunInterval(Long long1, String s, String s1, String s2);
    //�ɹ�����Ա�õ�����
    public abstract String getCaiGouAdminDeDaoOrder(Long long1, String s, String s1, String s2,String zhanghaoId);
    
    //��������
    public abstract List<OrderTable> getXiuGaiOrder(Long long1);
    
    public abstract List<OrderTable> getCaiGouAdminDeDaoOrder1(Long long1, String s, String s1);
    //�ɹ��鿴������
    public abstract String getDaiFaHuo(Long long1, String s, String s1, String s2,String caigoutime,String caigoutime1,String wuping);
    //���Ų�Ϊ��
    public abstract List<OrderTable> getChaXunDaiFaHuo(String s, String s1,String wuping);

    public abstract List<OrderTable> getChaXunDaiFaHuo1(String s, String s1);
    //�ֿ�鿴������
    public abstract List<OrderTable> getDaiFangQu(String s, String s1);

    public abstract List<OrderTable> getTopTen();

    public abstract List<OrderTable> getWeiKong(String s, String s1,String wuping);

    public abstract List<OrderTable> getGuoNeiDanHao(String s);
    //����Ա�鿴����ͨ����
    public abstract String getAdminSuMaiTong(String s, String s1, String s2);
    //ҵ��鿴�Ѿ��뵥
    public abstract String getYeWuOrder(String s, String s1, String s2);
    //ҵ��鿴δ�뵥��Ʒ
    public abstract String getWeiRuOrder(String s, String s1, String s2);
    //ҵ��鿴����
    public abstract String getChaKanDaiFa(String s, String s1, String s2);

    public abstract OrderTable getSelYunShu(String s);

    public abstract String getDaiFaHuo(String s,Long userId);

    public abstract String getYiJingFaHuo(String s,Long userId);
    //����Ա�鿴ȫ����ɶ���
    public abstract List<OrderTable> getSelWanCheng(String s, String s1);

    public abstract String getSelTwoTble();//����ѯ
    
    public abstract List<OrderTable> getDaoChu();
    //ҵ��鿴���뵥
    public List<OrderTable> getDaiRuDan(String guoneidanhao,String orderId);//��ѯ��¼��
    //�ɹ�Ա�鿴��������
    public List<OrderTable> getJiuFen(Long userId,String orderId,String time,String time1,Long leimus);
    //�ɹ�Ա��ѯ���׶���
    public String getDisputeOrder(Long userId,String orderId,String time,String time1,Long leimus);
    //�鿴�����Ƿ�һ��
    public List<OrderTable> getBianMa(String bianma);
    //�����ܽ��
    public List<OrderTable> getJiuFenMoney(Long userId,String orderId,String time,String time1);
    //�ͻ���ѯ��������
    public String  getChuHuoOrder(Long denglu,String orderId,String guoneidanhao);
    //����Ա��ѯȫ������
    public abstract List<OrderTable> getChaKanOrder(String orderId,String time,String time1,String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu);
    //����Ա�鿴����ͨ����
    public String getSuMaiTong(String orderId,String time,String time1);
    //ҵ��鿴Ҫ�޸����˵Ķ���
    public String getRuZhang(String orderId,String time,String time1);
    //ҵ��õ��˿�ȫ����ɶ���
    public String getWanChengOrder(String orderId);
    //���ʱ��
    public List<OrderTable> getDaoChuYunFei(String time,String time1);
    //�˿Ͳ鿴�˻� 
    public String getTuiHuoAll(Long userid,String orderId,String danhao,String chuli);
    //��ʱ���ѯ
    public List<OrderTable> getTime(String time);
    //�鿴ȫ���˻�
    public String getTuiHuoAll(String danhao,String chuli);
    //�鿴ҵ�񷵻ض���
    public String getFanHuiOrder(String guoneidanhao,String orderId);

    //��ѯ��������û�еĲɹ�
    public List<OrderTable> getCaiGouNull();
    //�鿴��涩��
    public abstract String getKuCunOrder(Long userid,String s);
    //ʱ���ѯȫ��
    public List<OrderTable> getHuiLv(String time,String time1);
    //����Ա�鿴�˷�Ϊ�ո���
    public List<OrderTable> getYunFeiAll(Long userid,String orderId,String time,String time1);
    //����Ϊ�ո���
    public List<OrderTable> getHuoKuanAll(Long userid,String orderId,String time,String time1);
    //�˷�Ϊ��
    public String getYunFeiAllNum(Long userid,String orderId,String time,String time1);
    //����Ϊ��
    public String getHuoKuanAllNum(Long userid,String orderId,String time,String time1);
    //�ɹ��鿴�˻�
    public String getTuiHuo(Long userid,String orderId,String danhao,String chuli);
    //����Ա�鿴���׸���
    public String getJiuFenNum(Long userid,String orderid,String time,String time1);
    //��¼��Ų�ѯȫ��
    public OrderTable getDenglu(Long userid);
    //��ѯʱ���
    public String getTime(String time,String caigoutime);
    //�鿴ȫ�����䶩��
    public String getAllFenPei(Long userid,String orderId);
    //�鿴ȫ�����䶩��
    public String getWenTiOrder(Long userid,String orderId);
   //�鿴ȫ������
    public List<OrderTable> getAllOrder(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu);
    //�ɹ��鿴ȫ����ɶ���
    public List<OrderTable> getAllWanChengOrder(Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma);
    //�����ܽ��
    public List<OrderTable> getAllMoney(String orderId,String time,String time1,Long selcaigouyuan,Long leimus);
    //�����ܻ���
    public List<OrderTable> getAllHuoKuan(String orderId,String time,String time1,Long selcaigouyuan,Long leimus);
    //�������˷�
    public List<OrderTable> getAllYunFei(String orderId,String time,String time1,Long selcaigouyuan,Long leimus);
    //�鿴ȫ������
    public String getYeAllOrder(String orderId,Long userid);
    //��ѯȫ������
    public List<OrderTable> getAllOrder();
    //��ѯ����ʱ��
    public List<OrderTable> getMonths();
    //δ���˺�δǩ�ղ�ѯ
    public List<OrderTable> getWrzWqs();
    //��ѯȫ������
    public List<OrderTable> getQbdd();
    //�������׶���
    public List<OrderTable> getJfOrder(Long userId);
    //�����Ų�ѯȫ��
    public Order_Detail getOrderIdAll(String orderId);
  //��ѯȫ����Ŀ
    public List<Order_Detail> getAllCgs();
    //�����ò�ѯȫ��
    public List<OrderTable> getOrder(String orderId);
    //�˺ű�Ų�ѯȫ��
    public DhgateAccounts getDhgateId(Long dhgateid);
    //����Ҫ�����
    public String getPayment();
    //��ѯ����Ϊ��
    public String PayEmpty(Long userid,String orderId,String time,String time1);
    //���ڵ���Ϊ��
    public String SingleNumberEmpty(String guoneidanhao,String orderId,String wuping);
    //�ֿ��ѯ���Ų�Ϊ��
    public String getSingleNumberNotEmpty(String guoneidanhao,String orderId,String wuping);
    //�ֿ��ѯ���Ų�Ϊ��
    public String getDaiFangQuOrder(String guoneidanhao,String orderId);
    //����鿴��¼��
    public String getSingle(String guoneidanhao,String orderId);
    //�鿴ȫ����ɶ���
    public String getComplete(String orderId,String guoneidanhao);
    //�鿴��������
    public String getOnOrder(String orderid);
    //��ѯ���׶���
    public String getOnDispute(String orderid,Long userid,Long chuli,String time,String time1,Long zhanghaoId);
}

