
package com.demo.page;

import java.util.List;

import javax.annotation.Resource;

import oracle.net.aso.f;

import org.springframework.stereotype.Service;

import com.demo.dao.GuoJiaDao;
import com.demo.dao.LeiMuDao;
import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.Order_DetailDao;
import com.demo.dao.XieXinDao;
import com.demo.dao.YuCunDao;
import com.demo.dao.ZhangHaoDao;
import com.demo.dao.user.GuKeDao;
import com.demo.dao.user.UserDao;
import com.demo.entity.YunCun;
import com.demo.entity.order.Order_Detail;

@Service
public class PageBizImpl extends PageBean implements PageBiz
{
	private static final long serialVersionUID = 1L;
	@Resource
    private OrderDao orderDao;
	@Resource
    private UserDao userDao;
	@Resource
    private ZhangHaoDao zhangHaoDao;
	@Resource
    private GuoJiaDao guoJiaDao;
	@Resource
    private OrderTableDao orderTableDao;
	@Resource
    private XieXinDao xieXinDao;
	@Resource
    private GuKeDao guKeDao;
	@Resource
    private YuCunDao yuCunDao;
	@Resource
    private Order_DetailDao order_DetailDao;
	@Resource
    private LeiMuDao leiMuDao;
	//����Ա�鿴ȫ������
	public PageBean selForPage(int pageSize, int page, String orderId,String time, String time1, String dhgatezhanghao, String danhao,String sumaitong, String bianma, String leimu,Long chuli)
    {
    	 String hql = orderDao.getAllOrder(orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu,chuli);
         return getFenYe(hql, pageSize, page);
    }

    //�ɹ�����Ա��ɶ���
    public PageBean selCaiGouAdminWanChengOrder(int pageSize, int page, Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma,String gongyunshang,String wuping)
    {
        String hql = orderDao.getCaiGouAdminWanChengOrder(userid, orderId, time, time1,caigoutime,caigoutime1,bianma,gongyunshang,wuping);
        return getFenYe(hql, pageSize, page);
    }
    //�ɹ�������
    public PageBean selCaiGouAdminDaiFaHuo(int pageSize, int page, Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String wuping)
    {
        String hql = orderTableDao.getDaiFaHuo(userid, orderId, time, time1,caigoutime,caigoutime1,wuping);
        return getFenYe(hql, pageSize, page);
    }
    //�鿴���ⶩ��
    public PageBean selAllWenTiOrder(int pageSize, int page, String orderId)
    {
        String hql = orderDao.getWenTiOrder(orderId);
        return getFenYe(hql, pageSize, page);
    }
    //�ɹ���ɶ���
    public PageBean selCaiGouWanChengOrder(int pageSize, int page, Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma)
    {
        String hql = orderDao.getDeDaoOrder(userid, orderId, time, time1,caigoutime,caigoutime1,bianma);
        return getFenYe(hql, pageSize, page);
    }

    //ҵ��õ��޸Ķ���
    public PageBean selYeWuDeDaoOrder(int pageSize, int page, String orderId, String gongyunshang, Long selzhanghao, String danhao)
    {
        String hql = orderDao.getYeWuOrder(orderId, gongyunshang, selzhanghao, danhao);
        return getFenYe(hql, pageSize, page);
    }
    //ҵ������ͨ�Ѿ��뵥
    public PageBean selSuMaiTongRuDan(int pageSize, int page, Long userid, String orderId, String time, String time1)
    {
        String hql = orderTableDao.getYeWuOrder(orderId, time, time1);
        return getFenYe(hql, pageSize, page);
    }
    //ҵ��鿴δ�뵥����
    public PageBean selSuMaiTongWeiRuDan(int pageSize, int page, String orderId, String time, String time1)
    {
        String hql = orderTableDao.getWeiRuOrder(orderId, time, time1);
        return getFenYe(hql, pageSize, page);
    }
    //ҵ������ͨ����
    public PageBean selSuMaiTongDaiFa(int pageSize, int page, String orderId, String time, String time1)
    {
        String hql = orderTableDao.getChaKanDaiFa(orderId, time, time1);
        return getFenYe(hql, pageSize, page);
    }
    //�˿ʹ�����
    public PageBean selGuKeDaiFaHuo(int pageSize, int page,Long userid, String orderId)
    {
        String hql = orderTableDao.getDaiFaHuo(orderId,userid);
        return getFenYe(hql, pageSize, page);
    }
    //�˿��Ѿ�����
    public PageBean selYiJingRuDan(int pageSize, int page, String orderId,Long userId)
    {
        String hql = orderTableDao.getYiJingFaHuo(orderId,userId);
        return getFenYe(hql, pageSize, page);
    }
    //����Ϊ��
    public PageBean selCangKuDanHaoWeiKong(int pageSize, int page, String orderId, String guoneidanhao,String wuping)
    {
        //String hql = orderTableDao.getWeiKong(guoneidanhao, orderId,wuping);
        return null;//getFenYe(hql, pageSize, page);
    }
    //����Ա���䶩��
    public PageBean selAdminFenPeiOrder(int pageSize, int page, String orderId){
    	String hql = orderDao.getAllFenPei(orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //�ɹ�����Ա�õ�����
    public  PageBean selAdminDeDaoOrder(int pageSize, int page, String orderId){
    	String hql = orderDao.getAllFenPei(orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�޸Ķ���
    public PageBean selAdminUpOrder(int pageSize, int page,String orderId, String danhao, String xujia){
    	String hql = orderDao.getOrder(orderId, danhao, xujia);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�鿴ȫ������
    public PageBean selAdminDanHao(int pageSize, int page,String orderId,String time,String time1){
    	String hql = orderDao.getDanHao(orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴����Ա�˻�����
    public PageBean selAdminTuiHuo(int pageSize, int page,String orderId,String danhao,Long chuli){
    	String hql = orderDao.getTuiKuanAll(orderId,danhao,chuli);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�鿴�ɹ�δ��ɶ���
    public PageBean selCaiGouWeiWanCheng(int pageSize, int page,String orderId){
    	String hql = orderDao.getCaiGouWeiWanCheng(orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�鿴����ͨ����
    public  PageBean selAdminSuMaiTongOrder(int pageSize, int page,String orderId,String time,String time1){
    	String hql = orderTableDao.getAdminSuMaiTong(orderId,time,time1);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�鿴ȫ��Ա���˺�
    public PageBean selAdminZhangHao(int pageSize, int page,String username){
    	String hql = userDao.getzhanghao(username);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴�ػ��˺�
    public PageBean selDhgate(int pageSize, int page,String name){
    	String hql = zhangHaoDao.getdhgate(name);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�鿴�ɹ���ɶ���
    public PageBean selAdminCaiGouWanChengOrder(int pageSize, int page,Long caigouyuan,String orderId,String time,String time1){
    	String hql = orderDao.getCaiGouAll(caigouyuan, orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //ҵ��鿴�˷�Ϊ��
    public PageBean selYeWuYunFeiNull(int pageSize, int page,String orderId,String time,String time1){
    	String hql = orderDao.getYunFeiNull(orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴ȫ�������˷�
    public  PageBean selGuoJiaYunFei(int pageSize, int page, Long quyu, String guojia){
    	String hql = guoJiaDao.getGuoJia(quyu, guojia);
    	return getFenYe(hql, pageSize, page);
    }
    //�ͻ��鿴�����еĶ���
    public PageBean selChuHuo(int pageSize, int page, Long denglu, String orderId,String guoneidanhao){
    	String hql = orderTableDao.getChuHuoOrder(denglu, orderId, guoneidanhao);
    	return getFenYe(hql, pageSize, page);
    }
  //����Ա�鿴����ͨ�Ѿ���ɶ���
    public PageBean selSuMaiTong(int pageSize, int page, String orderId,String time,String time1){
    	String hql = orderTableDao.getSuMaiTong(orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //ҵ��鿴�޸Ĺ˿�������� 
    public PageBean selRuZhang(int pageSize, int page, String orderId,String time,String time1){
    	String hql = orderTableDao.getRuZhang(orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
  //ҵ��õ��˿�ȫ����ɶ���
    public PageBean selWanChengOrder(int pageSize, int page, String danhao){
    	String hql = orderTableDao.getWanChengOrder(danhao);
    	return getFenYe(hql, pageSize, page);
    }
    //�ɹ�����Ա�õ�����
    public PageBean selCaiGouDeDaoOrder(int pageSize, int page, Long userid, String orderId,String time,String time1,String zhanghaoId){
    	String hql = orderTableDao.getCaiGouAdminDeDaoOrder(userid, orderId, time, time1,zhanghaoId);
    	return getFenYe(hql, pageSize, page);
    }
    //�ɹ��õ�����
    public PageBean selDeDaoOrder(int pageSize, int page, Long userid, String orderId,String time,String time1,String zhanghaoId, Long leimu){
    	
    	String hql = orderDao.getDeOrder(userid, orderId, time, time1,zhanghaoId,leimu);
    	return getFenYe(hql, pageSize, page);
    }
    //�˿͵õ��˻�����
    public PageBean selTuiHuo(int pageSize, int page, Long userid, String orderId,String danhao,String chuli){
    	String hql = orderTableDao.getTuiHuoAll(userid,orderId,danhao,chuli);
    	return getFenYe(hql, pageSize, page);
    }
    //�˿͵õ��˻�����
    public PageBean selChaKanAll(int pageSize, int page, String danhao,String chuli){
    	String hql = orderTableDao.getTuiHuoAll(danhao,chuli);
    	return getFenYe(hql, pageSize, page);
    }
  //�õ�ҵ�񷵻ض���
    public PageBean selYeWuFanHui(int pageSize, int page, String guoneidanhao,String orderId){
    	String hql = orderTableDao.getFanHuiOrder(guoneidanhao, orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //ȫ��δ����
    public PageBean selAllWeiRuZhang(int pageSize, int page, String orderId, String time, String time1, String dhgatezhanghao,String danhao){
    	String hql = orderDao.getAllWeiRuZhang(orderId, time, time1, dhgatezhanghao, danhao);
    	return getFenYe(hql, pageSize, page);
    }
  //�鿴��
    public PageBean selAllKanXin(int pageSize, int page, Long userid){
    	String hql = xieXinDao.getAllKanXin(userid);
    	return getFenYe(hql, pageSize, page);
    }
  //�鿴δ����
    public PageBean selAllWeiDu(int pageSize, int page, Long userid){
    	String hql = xieXinDao.getAllXinXi(userid);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴��涩��
    public PageBean selKuCunOrder(int pageSize, int page,Long userid, String orderId){
    	String hql = orderTableDao.getKuCunOrder(userid,orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //����Ա�鿴����Ϊ��
    public PageBean selHuoKuanNull(int pageSize, int page,Long userid,String orderId,String time,String time1){
    	String hql = orderTableDao.getHuoKuanAllNum(userid, orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //�˷�Ϊ��
    public PageBean selYunFeiNull(int pageSize, int page,Long userid,String orderId,String time,String time1){
    	String hql = orderTableDao.getYunFeiAllNum(userid, orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //�ɹ��鿴�˻�
    public PageBean selKanTuiHuo(int pageSize, int page,Long userid,String orderId,String danhao,String chuli){
    	String hql = orderTableDao.getTuiHuo(userid, orderId, danhao, chuli);
    	return getFenYe(hql, pageSize, page);
    }
    public PageBean selChaKanJiuFenOrder(int pageSize, int page,Long userid,String orderId,String time,String time1){
    	String hql = orderTableDao.getJiuFenNum(userid, orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //��ѯȫ���ͻ�
    public PageBean selGuKeName(int pageSize, int page,String name){
    	String hql = guKeDao.getGuKeName(name);
    	return getFenYe(hql, pageSize, page);
    }
    //��ѯ�������
    public PageBean selYcDetail(int pageSize, int page,String time,String time1){
    	String hql = yuCunDao.getYuCun(time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴�������
    public PageBean selXqYuCun(int pageSize, int page,Long userid){
    	String hql = yuCunDao.getXqYuCun(userid);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴�������
    public PageBean selAllFenPei(int pageSize, int page,Long userid,String orderId){
    	String hql = orderTableDao.getAllFenPei(userid, orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴���ⶩ��
    public PageBean selWenTiOrder(int pageSize, int page,Long userid,String orderId){
    	String hql = orderTableDao.getWenTiOrder(userid, orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //ҵ��鿴ȫ������
    public PageBean selYeAllOrder(int pageSize, int page,String orderId,Long userid){
    	String hql = orderTableDao.getYeAllOrder(orderId,userid);
    	return getFenYe(hql, pageSize, page);
    }
    //�����˲�ѯȫ��
    public PageBean selFjjl(int pageSize, int page,String fjr){
    	String hql = xieXinDao.getFjjl(fjr);
    	return getFenYe(hql, pageSize, page);
    }
    //����鿴�����
    public PageBean selgetPayment(int pageSize, int page){
    	String hql = orderTableDao.getPayment();
    	return getFenYe(hql, pageSize, page);
    }
  //�ɹ��鿴���׶���
    public PageBean selDisputeOrder(int pageSize, int page,Long userid,String orderId,String time,String time1,Long leimus){	
    	String hql= orderTableDao.getDisputeOrder(userid, orderId, time, time1, leimus);
    	return getFenYe(hql, pageSize, page);
    }
    //��ѯ����Ϊ��
    public PageBean selPayEmpty(int pageSize, int page,Long userid,String orderId,String time,String time1){
    	String hql = orderTableDao.PayEmpty(userid, orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }
    //�ֿ�õ��������䵥��Ϊ��
    public PageBean selSingleNumberEmpty(int pageSize, int page,String guoneidanhao,String orderId,String wuping){
    	String hql = orderTableDao.SingleNumberEmpty(guoneidanhao, orderId, wuping);
    	return getFenYe(hql, pageSize, page);
    }
    //�ֿ�õ��������䵥�Ų�Ϊ��
    public PageBean selSingleNumberNotEmpty(int pageSize, int page,String guoneidanhao,String orderId,String wuping){
    	String hql = orderTableDao.getSingleNumberNotEmpty(guoneidanhao, orderId, wuping);
    	return getFenYe(hql, pageSize, page);
    }
    //�ֿ����������
    public PageBean selDaiFangQuOrder(int pageSize, int page,String guoneidanhao,String orderId){
    	String hql = orderTableDao.getDaiFangQuOrder(guoneidanhao, orderId);
    	return getFenYe(hql, pageSize, page);
    }
    //������뵥
    public PageBean selDaiRudan(int pageSize, int page,String guoneidanhao,String orderId){
    	String hql = orderTableDao.getSingle(guoneidanhao, orderId);
    	return getFenYe(hql, pageSize, page);
    }
  //��ѯȫ����Ŀ
    public PageBean selAllCgs(int pageSize, int page){
    	String hql = order_DetailDao.getAllCategory();
    	return getFenYe(hql, pageSize, page);
    }
    //��ѯȫ����Ŀ
    public PageBean selComplete(int pageSize, int page,String orderId,String guoneidanhao){
    	String hql = orderTableDao.getComplete(orderId, guoneidanhao);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴������Ʒ
    public PageBean selOnbehalf(int pageSize, int page,String orderid){
    	String hql = orderTableDao.getOnOrder(orderid);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴ȫ�����׶���
    public PageBean selDispute(int pageSize, int page,String orderId,Long userid,Long chuli,String time,String time1,Long zhanghaoId){
    	String hql = orderTableDao.getOnDispute(orderId, userid,chuli,time,time1,zhanghaoId);
    	return getFenYe(hql, pageSize, page);
    }
    //�鿴ȫ�����׶���
    public PageBean selFilter(int pageSize, int page,Long caigouyuan, String orderId, String time, String time1){
    	String hql = orderDao.getFilter(caigouyuan, orderId, time, time1);
    	return getFenYe(hql, pageSize, page);
    }



}
