package com.demo.page;

public interface PageBiz
{

    public abstract PageBean selForPage(int pageSize, int page, String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu,Long chuli);
   
    //�ɹ�����Ա�õ�����
    public abstract PageBean selCaiGouAdminWanChengOrder(int pageSize, int page, Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma,String gongyunshang,String wuping);
    //�鿴������
    public abstract PageBean selCaiGouAdminDaiFaHuo(int pageSize, int page, Long userid, String orderId,String time, String time1,String caigoutime,String caigoutime1,String wuping);
    //�鿴���ⶩ��
    public abstract PageBean selAllWenTiOrder(int pageSize, int page, String orderId);
   
    //�ɹ���ɶ���
    public abstract PageBean selCaiGouWanChengOrder(int pageSize, int page, Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma);
 
    //ҵ��õ��޸Ķ���
    public abstract PageBean selYeWuDeDaoOrder(int pageSize, int page, String orderId, String gongyunshang, Long selzhanghao, String danhao);
    //����ͨ�Ѿ��뵥
    public abstract PageBean selSuMaiTongRuDan(int pageSize, int page, Long userid, String orderId, String time, String time1);
    //ҵ��鿴δ�뵥����
    public abstract PageBean selSuMaiTongWeiRuDan(int pageSize, int page, String orderId, String time, String time1);
    //ҵ������ͨ����
    public abstract PageBean selSuMaiTongDaiFa(int pageSize, int page, String orderId, String time, String time1);

    public abstract PageBean selGuKeDaiFaHuo(int pageSize, int page,Long userid, String orderId);

    public abstract PageBean selYiJingRuDan(int pageSize, int page, String orderId,Long userId);
   
    //�ֿⵥ��Ϊ��
    public abstract PageBean selCangKuDanHaoWeiKong(int pageSize, int page, String orderId, String guoneidanhao,String wuping);
    //����Ա���䶩��
    public abstract PageBean selAdminFenPeiOrder(int pageSize, int page, String orderId);
    
    public abstract PageBean selAdminDeDaoOrder(int pageSize, int page, String orderId);
    //����Ա�޸Ķ���
    public abstract PageBean selAdminUpOrder(int pageSize, int page,String orderId, String danhao, String xujia);
    //����Ա�鿴ȫ������
    public abstract PageBean selAdminDanHao(int pageSize, int page,String orderId,String time,String time1);
    //����Ա�鿴�˻�����
    public abstract PageBean selAdminTuiHuo(int pageSize, int page,String orderId,String danhao,Long chuli);
    //�鿴�ɹ�δ��ɶ���
    public abstract PageBean selCaiGouWeiWanCheng(int pageSize, int page,String orderId);
    //����Ա�鿴����ͨ����
    public abstract PageBean selAdminSuMaiTongOrder(int pageSize, int page,String orderId,String time,String time1);
    //����Ա�鿴ȫ��Ա���˺�
    public abstract PageBean selAdminZhangHao(int pageSize, int page,String username);
    //����Ա�鿴�ػ��˺�
    public abstract PageBean selDhgate(int pageSize, int page,String username);
    //����Ա�鿴�ɹ�ȫ����ɶ���
    public abstract PageBean selAdminCaiGouWanChengOrder(int pageSize, int page,Long caigouyuan,String orderId,String time,String time1);
    //�鿴�˷�Ϊ��
    public abstract PageBean selYeWuYunFeiNull(int pageSize, int page,String orderId,String time,String time1);
    //�鿴ȫ�������˷�
    public abstract PageBean selGuoJiaYunFei(int pageSize, int page, Long quyu, String guojia);
    //�ͻ��鿴�����еĶ���
    public PageBean selChuHuo(int pageSize, int page, Long denglu, String orderId,String guoneidanhao);
  //����Ա�鿴����ͨ�Ѿ���ɶ���
    public PageBean selSuMaiTong(int pageSize, int page, String orderId,String time,String time1);
    //ҵ��鿴�޸Ĺ˿�������� 
    public PageBean selRuZhang(int pageSize, int page, String orderId,String time,String time1);
    //ҵ��õ��˿�ȫ����ɶ���
    public PageBean selWanChengOrder(int pageSize, int page, String orderId);
    //�ɹ�����Ա�õ�����
    public PageBean selCaiGouDeDaoOrder(int pageSize, int page, Long userid, String orderId,String time,String time1,String zhanghaoId);
    //�ɹ��õ�����
    public PageBean selDeDaoOrder(int pageSize, int page, Long userid, String orderId,String time,String time1,String zhanghaoId,Long leimu);
  //�˿͵õ��˻�����
    public PageBean selTuiHuo(int pageSize, int page, Long userid, String orderId,String danhao,String chuli);
    //�˿͵õ��˻�����
    public PageBean selChaKanAll(int pageSize, int page, String danhao,String chuli);
  //�õ�ҵ�񷵻ض���
    public PageBean selYeWuFanHui(int pageSize, int page, String danhao,String orderId);
    //ȫ��δ����
    public PageBean selAllWeiRuZhang(int pageSize, int page, String orderId, String time, String time1, String dhgatezhanghao,String danhao);
    //�鿴��
    public PageBean selAllKanXin(int pageSize, int page, Long userid);
  //�鿴δ����
    public PageBean selAllWeiDu(int pageSize, int page, Long userid);
    //�鿴��涩��
    public PageBean selKuCunOrder(int pageSize, int page,Long userid,String orderId);
    //�˷�Ϊ��
    public PageBean selYunFeiNull(int pageSize, int page,Long userid,String orderId,String time,String time1);
    //����Ϊ�� 
    public PageBean selHuoKuanNull(int pageSize, int page,Long userid,String orderId,String time,String time1);
  //�ɹ��鿴�˻�
    public PageBean selKanTuiHuo(int pageSize, int page,Long userid,String orderId,String danhao,String chuli);
  //���׶���
    public PageBean selChaKanJiuFenOrder(int pageSize, int page,Long userid,String orderId,String time,String time1);
    //��ѯȫ���ͻ�
    public PageBean selGuKeName(int pageSize, int page,String name);
    //��ѯ�������
    public PageBean selYcDetail(int pageSize, int page,String time,String time1);
    //�鿴
    public PageBean selXqYuCun(int pageSize, int page,Long userid);
    //�鿴���� 
    public PageBean selAllFenPei(int pageSize, int page,Long userid,String orderId);
    //�鿴���ⶩ��
    public PageBean selWenTiOrder(int pageSize, int page,Long userid,String orderId);
    //ҵ��鿴ȫ������
    public PageBean selYeAllOrder(int pageSize, int page,String orderId,Long userid);
    //�����˲�ѯȫ��
    public PageBean selFjjl(int pageSize, int page,String fjr);
    //����鿴�����
    public PageBean selgetPayment(int pageSize, int page);
    //�ɹ��鿴���׶���
    public PageBean selDisputeOrder(int pageSize, int page,Long userid,String orderId,String time,String time1,Long leimus);
    //��ѯ����Ϊ��
    public PageBean selPayEmpty(int pageSize, int page,Long userid,String orderId,String time,String time1);
    //�ֿ�õ��������䵥��Ϊ��
    public PageBean selSingleNumberEmpty(int pageSize, int page,String guoneidanhao,String orderId,String wuping);
    //�ֿ�õ��������䵥�Ų�Ϊ��
    public PageBean selSingleNumberNotEmpty(int pageSize, int page,String guoneidanhao,String orderId,String wuping);
    //�ֿ����������
    public PageBean selDaiFangQuOrder(int pageSize, int page,String guoneidanhao,String orderId);
    //������뵥
    public PageBean selDaiRudan(int pageSize, int page,String guoneidanhao,String orderId);
    //��ѯȫ����Ŀ
    public PageBean selAllCgs(int pageSize, int page);
    //��ѯȫ����Ŀ
    public PageBean selComplete(int pageSize, int page,String orderId,String guoneidanhao);
    //�鿴������Ʒ
    public PageBean selOnbehalf(int pageSize, int page,String orderId);
  //�鿴ȫ�����׶���
    public PageBean selDispute(int pageSize, int page,String orderId,Long userid,Long chuli,String time,String time1,Long zhanghaoId);
    //�鿴ȫ�����׶���
    public PageBean selFilter(int pageSize, int page,Long caigouyuan, String orderId, String time, String time1);
}
