
package com.demo.dao;

import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.order.OrderTable;
import com.demo.list.PageData;
import java.util.List;


public interface OrderDao extends BaseDao<OrderTable,Long>
{
	//�鿴ȫ������
    public abstract String getAllOrder(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu,Long chuli);

    public abstract OrderTable getDingDao(String s);

    public abstract String getOrder(String s, String s1, String s2);
    //���䶩��
    public abstract String getAllFenPei(String s);
    //����Ա�鿴�ɹ���ɵĶ���
    public abstract String getCaiGouAll(Long long1, String s, String s1, String s2);
    //�ɹ��õ�����
    public abstract String getDeOrder(Long long1,String s, String s1, String s2,String zhanghaoId,Long leimu);

    public abstract List<OrderTable> getCaiGouDaoChuOrder(Long long1);
    //ҵ��õ��޸Ķ���
    public abstract String getYeWuOrder(String s, String s1, Long s2, String s3);

    public abstract List<OrderTable> getWanChengOrder(String s);
    //����Ա�鿴�ܽ��
    public abstract List<OrderTable> getAllMoney(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);
    //����Ա�鿴���˷�
    public abstract List<OrderTable> getAllYunFei(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);
    //����Ա�鿴�ܻ���
    public abstract List<OrderTable> getAllHuoKuan(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);
    //����Ա�鿴���˿�
    public abstract List<OrderTable> getAllTuiKuan(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);
    //����Ա�鿴���׸���
    public abstract List<OrderTable> getJiuFenNum(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);

    public abstract List<OrderTable> getJiuFen(String s, String s1, String s2,Long selcaigouyuan,String leimus);
    //�鿴����
    public abstract String getDanHao(String s, String s1, String s2);

    public abstract List<OrderTable> getAllOrderId(String s);
    //����Ա�鿴�˷�Ϊ��
    public abstract List<OrderTable> getYunFeiNullNum(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);
    //����Ա�鿴����Ϊ��
    public abstract List<OrderTable> getHuoKuanNullNum(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);
    //ҵ���ѯ�˷�Ϊ��
    public abstract String getYunFeiNull(String s, String s1, String s2);

    public abstract List<OrderTable> getHuoKuanNull(String s, String s1, String s2);
    //�ñ�Ų�ѯȫ��
    public abstract List<OrderTable> getSelId(Long long1);
    //�ɹ���ɶ���
    public abstract String getDeDaoOrder(Long long1, String s, String s1, String s2,String caigoutime,String caigoutime1,String bianma);

    public abstract List<OrderTable> getDengHuiXin(Long long1, String s, String s1);

    public abstract List<OrderTable> getSuMaiTong(Long long1, String s, String s1);

    public abstract List<OrderTable> getSelTiaoJiao(String s, String s1, String s2);
    //�鿴�˻�����
    public abstract String getTuiKuanAll(String s, String danhao,Long chuli);
    //�鿴���ⶩ��
    public abstract String getWenTiOrder(String s);

    public abstract List<OrderTable> getCaiGouHuoKuanNull(Long long1, String s, String s1, String s2);
    //�鿴�ɹ�δ��ɶ���
    public abstract String getCaiGouWeiWanCheng(String s);
    //�ɹ�����Ա��ɶ���
    public abstract String getCaiGouAdminWanChengOrder(Long long1, String s, String s1, String s2,String caigoutime,String caigoutime1,String bianma,String gongyunshang,String wuping);

    public abstract List<OrderTable> getCaiGouAllMoney(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAllYunFei(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAllHuoKuan(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAllTuiKuan(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAllJiuFen(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAdminAllMoney(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getDaiFaHuoMoney(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAdminAllYunFei(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAdminAllHuoKuan(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAdminAllTuiKuan(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouAdminAllJiuFen(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouTuiKuan(Long long1, String s, String s1, String s2);

    public abstract List<OrderTable> getCaiGouJiuFenMoney(Long long1, String s, String s1, String s2);
    //����Ա�鿴�����ܶ�
    public abstract List<OrderTable> getCaiGouAllJiuFenMoney(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);

    public abstract List<OrderTable> getOldDaoRu(Long long1);

    public abstract List<OrderTable> getOldUnDaoRu(Long long1);
    //����С��0
    public abstract List<OrderTable> getLiRun(String s, String s1, String s2,Double tuikuan
    	    ,Double huokuan,Double yunfei,Double money,Double huilv);

    public abstract List<OrderTable> getLiRunInterval(String s, String s1, String s2);

    public abstract List<OrderTable> getZongLiRun(String s, String s1, String s2, String s3,String danhao,String sumaitong,String bianma,String leimu);

    public abstract List<OrderTable> getSelAllId(Long long1);
    //�õ�����ѯȫ��
    public abstract List<OrderTable> getDaoChuAll();
    //�����˷�Ϊ��
    public abstract List<OrderTable> getDaoChuYunFei();
    //�������䵥��Ϊ��
    public abstract List<OrderTable> getDanHaoYunShuNull();
    //���Ҳ�ѯ
    public abstract List<YunFeiTable> getGuoJia(String guojia);
    //����˾�����Ų�ѯ
    public abstract List<OrderTable> getChaKanOrder(String orderId);
  //����˾�����Ų�ѯ
    public abstract List<OrderTable> getChaKanKeHu(String orderId);
    //����Ա�鿴����
    public  List<OrderTable> getChaKanZero(String orderId,String time,String time1,String dhgatezhanghao,String danhao);
    //ҵ�񵼳������ͻ����˷�
    public List<OrderTable> getDaoChuKeHuYunFei(Long id,String time,String time1);
    //����Ա ���������ͻ����˷�
    public List<OrderTable> getAdminYunFei(Long id,String time);
    //�ܵ�δ����
    public List<OrderTable> getWeiRuZhang(String orderId, String time, String time1, String zhanghao,String danhao,String sumaitong,String bianma,String leimu);
    //��ѯȫ��δ����
    public String getAllWeiRuZhang(String orderId, String time, String time1, String zhanghao,String danhao);
    //���Ų鿴ȫ��
    public List<OrderTable> getAllDanHao(String danhao,String orderId);
    //�ɹ�ɸѡ��ѯ
    public String getFilter(Long caigouyuan, String orderId, String time, String time1);
}
