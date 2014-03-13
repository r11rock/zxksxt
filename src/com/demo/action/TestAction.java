package com.demo.action;

import com.demo.dao.GuoJiaDao;
import com.demo.dao.HuiLvDao;
import com.demo.dao.LeiMuDao;
import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.XieXinDao;
import com.demo.dao.Express.DhlZkDao;
import com.demo.dao.Express.DhlfqDao;
import com.demo.dao.Express.DhlgbjDao;
import com.demo.dao.Express.EmsZfDao;
import com.demo.dao.Express.FedExIeDao;
import com.demo.dao.Express.FedExIeGbjDao;
import com.demo.dao.Express.FedExIpDao;
import com.demo.dao.Express.FedExIpGbjDao;
import com.demo.dao.Express.FedexDao;
import com.demo.dao.Express.GjslDao;
import com.demo.dao.Express.HkdbDao;
import com.demo.dao.Express.HydbDao;
import com.demo.dao.Express.KyfqDao;
import com.demo.dao.Express.KyfqGbjDao;
import com.demo.dao.Express.SALdbDao;
import com.demo.dao.user.UserDao;
import com.demo.entity.HuiLvTable;
import com.demo.entity.LeiMuTable;
import com.demo.entity.XieXinTable;
import com.demo.entity.Express.DhlFq;
import com.demo.entity.Express.DhlGbj;
import com.demo.entity.Express.Dhlzk;
import com.demo.entity.Express.EmsZf;
import com.demo.entity.Express.FedExIe;
import com.demo.entity.Express.FedExIeGbj;
import com.demo.entity.Express.FedExIp;
import com.demo.entity.Express.FedExIpgbj;
import com.demo.entity.Express.Fedex;
import com.demo.entity.Express.Gjsl;
import com.demo.entity.Express.Hkdb;
import com.demo.entity.Express.Hydb;
import com.demo.entity.Express.Kygbj;
import com.demo.entity.Express.SALdb;
import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.Express.kyfq;
import com.demo.entity.order.OrderTable;
import com.demo.entity.user.GuKeTable;
import com.demo.entity.user.UserInfo;
import com.demo.list.PageModel;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.aspectj.weaver.ast.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends BaseAction implements ServletRequestAware
{
    private static final long serialVersionUID = 1L;
    @Resource
    private OrderDao orderDao;
    @Resource
    private LeiMuDao leiMuDao;
    @Resource
    private OrderTableDao orderTableDao;
    @Resource
    private UserDao userDao;
    @Resource
    private HuiLvDao huiLvDao;
    @Resource
    private PageBiz pageBiz;
    @Resource
    private XieXinDao xieXinDao;
    @Resource
    private DhlfqDao dhlfqDao;
    @Resource
    private GuoJiaDao guoJiaDao;
    @Resource
    private DhlgbjDao dhlgbjDao;
    @Resource
    private FedExIeDao fedExIeDao;
    @Resource
    private FedExIeGbjDao fedExIeGbjDao;
    @Resource
    private FedExIpDao fedExIpDao;
    @Resource
    private FedExIpGbjDao FedExIpGbjDao;
    @Resource
    private GjslDao gjslDao;
    @Resource
    private HkdbDao hkdbDao;
    @Resource
    private HydbDao hydbDao;
    @Resource
    private KyfqDao kyfqDao;
    @Resource
    private KyfqGbjDao kyfqGbjDao;
    @Resource
    private SALdbDao sALdbDao;
    @Resource
    private EmsZfDao emsZfDao;
    @Resource
    private DhlZkDao dhlZkDao;
    @Resource
    private FedexDao fedexDao;
    public String usertype;
    private OrderTable ordertable;
    private XieXinTable xiexin;
    public String msg;
    public int pageindex;
    public String orderId;
    public String time;
    public String time1;
    public Long caigouyuan;
    public int pageNumber;
    public String hydbAll;//���˴��
    public String hkdbAll;//���մ��
    public String salAll;//SAL���
    private PageBean pageBean;
    private HttpServletRequest request;
    public XieXinTable getXiexin() {
		return xiexin;
	}

	public void setXiexin(XieXinTable xiexin) {
		this.xiexin = xiexin;
	}

	public PageBean getPageBean()
    {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean)
    {
        this.pageBean = pageBean;
    }

    public OrderTable getOrdertable()
    {
        return ordertable;
    }

    public void setOrdertable(OrderTable ordertable)
    {
        this.ordertable = ordertable;
    }
    //����Ա��ҵ���ϴ����� 
    public String adddingdanorder() throws Exception
    {
    
        String[] dingdan = request.getParameterValues("dingdan");
        String[] jine = request.getParameterValues("jine");
        String[] yunshu = request.getParameterValues("yunshu");
        String[] zhanghao = request.getParameterValues("zhId");
        String[] beizhu = request.getParameterValues("remark");
        String[] bianma = request.getParameterValues("bianma"); 
        String[] str = new String[dingdan.length];
       
        try {
        	 java.util.Date d = new java.util.Date();
             SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
             String ff = f.format(d);
        	 List<HuiLvTable> hh = huiLvDao.getHuiTime(ff);
        for(int i = 0; i < dingdan.length; i++)
        {
            List<OrderTable> ls = orderDao.getAllOrderId(dingdan[i]);
            if(bianma[i] == null || "".equals(bianma[i])){
            	str[i] = i+".�������������д";
            }
            if("".equals(dingdan[i]) || dingdan[i] == null){
                str[i] = i+".�����Ų���Ϊ�գ�";
            }
            else
            if(ls.size() != 0){
                str[i] = i+".�������Ѿ�����!";
            }
            else
            if("".equals(jine[i]) || jine[i] == null){
                str[i] = i+".����Ϊ��!";
            }
            else
            if("0".equals(zhanghao[i]) || Integer.parseInt(zhanghao[i]) == 0)
            {
                str[i] = i+".�˺�δѡ��";
            } 
            else
            if("0".equals(yunshu[i]) || Integer.parseInt(yunshu[i]) == 0)
            {
                str[i] = i+".��Ŀδѡ��";
            } 
            else if((!"0".equals(zhanghao[i]) && Integer.parseInt(zhanghao[i]) != 0) && (!"".equals(jine[i]) && jine[i] != null)
             &&(ls.size() == 0) && (!"".equals(dingdan[i]) && dingdan[i] != null) && (bianma[i] != null&& !"".equals(bianma[i]))
             &&(!"0".equals(yunshu[i]) && Integer.parseInt(yunshu[i]) != 0)
            )
            {
            	List<LeiMuTable> ss = leiMuDao.getSelId(Long.parseLong(yunshu[i]));
                OrderTable order = new OrderTable();
                java.util.Date ds = new java.util.Date();
                SimpleDateFormat fs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String ffs = fs.format(ds);
                order.setTime(fs.parse(ffs));
                order.setCaigouyuan(Long.valueOf(0L));
                order.setOrderId(dingdan[i]);
                order.setMoney(Double.parseDouble(jine[i]));
                order.setZhanghaoId(Long.parseLong(zhanghao[i]));
                order.setRemark(beizhu[i]);
                order.setLeimuid(Long.parseLong(yunshu[i]));
                order.setBianma(bianma[i]);
                LoginInfo login = (LoginInfo)getFromSession("logininfo");
                order.setDengluId(login.getUserId());
                order.setFenpei(1l);
                order.setQianshou(0l);
                order.setShangwang(0l);
                order.setRuzhang(0l);
                order.setGetordersId(1l);
                if(hh.size() != 0){
                	for (int j = 0; j < hh.size(); j++) {
						order.setHuilv(hh.get(0).getHuilv());
					}
                }
                if(ss.size()!=0){
                	order.setCaigouyuan(ss.get(0).getUserid());
                }
                orderDao.merge(order);
                str[i] = i + ".��ӳɹ�!";
            }
        }
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
         ActionContext.getContext().put("insert", str);
        return "addorder";
    }

    public String caigou_fenpei()
    {
        try
        {
            String selid = request.getParameter("selid");
            String selcaigouyuan = request.getParameter("selcaigouyuan");
            ordertable = (OrderTable)orderDao.get(Long.parseLong(selid));
            ordertable.setFenpei(1l);
            ordertable.setCaigouyuan(Long.parseLong(selcaigouyuan));
            orderDao.merge(ordertable);
            msg = "�����ɹ�!";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "fenpeiorder";
    }
    //����
    public String getKanXin(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selAllKanXin(pageSize, pageNumber, us.getUserId());
    	return "kanxin";
    }
    //�鿴��
    public String biaoti(){
    	try {
    		
        	String id = request.getParameter("id");
        	String chakan = request.getParameter("chakan");
        	if("".equals(chakan)|| Long.parseLong(chakan) == 0){
        		xiexin = xieXinDao.get(Long.parseLong(id));
        		xiexin.setChakan(1l);
        		xieXinDao.merge(xiexin);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "getkanxin";
    }
    //�鿴��
    public String biaotis(){
    	try {
    		
        	String id = request.getParameter("id");
        	String chakan = request.getParameter("chakan");
        	if("".equals(chakan)|| Long.parseLong(chakan) == 0){
        		xiexin = xieXinDao.get(Long.parseLong(id));
        		xiexin.setChakan(1l);
        		xieXinDao.merge(xiexin);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "getfjkanxin";
    }
    //�鿴��Ϣ
    public XieXinTable getXieXinAll(){
    	
    	String id = request.getParameter("id");
    	XieXinTable stu = null;
        try
        {
            stu = xieXinDao.get(Long.parseLong(id));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�鿴δ����
    public String getWeiDuXin(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selAllWeiDu(pageSize, pageNumber, us.getUserId());
    	return "weidu";
    }
    //д��
    public String allXieXin(){
    	
    	return "allXieXin";
    }
    //д��
    public String addXieXin()throws Exception{
    	String usertype = request.getParameter("usertype");
    	String shoujian = request.getParameter("userid");
    	String title =new String(request.getParameter("title").getBytes("ISO8859-1"),"utf-8");
    	String neirong = new String(request.getParameter("neirong").getBytes("ISO8859-1"),"utf-8");
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	try {  	
    			if(Long.parseLong(usertype)==7){
    				List<UserInfo> admin = userDao.getAdmin();
    				for (int i = 0; i < admin.size(); i++) {
    					XieXinTable xiexin = new XieXinTable();
            			xiexin.setUserid(admin.get(i).getId());
            			xiexin.setTitle(title);
            			xiexin.setNeirong(neirong);
            			java.util.Date date = new java.util.Date();
                        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        xiexin.setTime(sim.format(date));
                        
                        xiexin.setFajianren(us.getName());
                        xiexin.setChakan(0l);
            			xieXinDao.merge(xiexin);
            			msg = "���ͳɹ� ";
					}
    			}else{
	        		XieXinTable xiexin = new XieXinTable();
	        		xiexin.setUserid(Long.parseLong(shoujian));
	            	xiexin.setTitle(title);
	            	xiexin.setNeirong(neirong);
	        	    java.util.Date date = new java.util.Date();
	                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                xiexin.setTime(sim.format(date));
	                xiexin.setFajianren(us.getName());
	                xiexin.setChakan(0l);
	                xieXinDao.merge(xiexin);
	        		msg = "���ͳɹ� ";
    			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "allXieXin";
    }
    //�˿�д��
    public String guKeXieXin(){
    	
    	return "kehuxiexin";
    }
    //�˿�д��
    public String addXin() throws Exception{
    	String zhanghao =request.getParameter("zhanghao");
    	String title =request.getParameter("title");
    	String neirong = request.getParameter("neirong");
    	UserInfo aa = userDao.getByAccount(zhanghao);
    	LoginInfo zz = (LoginInfo)getFromSession("logininfo");
    	if(aa == null){
    		msg = "�˺���������";
    	}else{
    		XieXinTable xiexin = new XieXinTable();
    		xiexin.setUserid(aa.getId());
        	xiexin.setTitle(title);
        	xiexin.setNeirong(neirong);
    	    java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            xiexin.setTime(sim.format(date));
            xiexin.setFajianren(zz.getName());
            xiexin.setChakan(0l);
            xieXinDao.merge(xiexin);
    		msg = "���ͳɹ� ";
    	}
    	return "kehuxiexin";
    }
    //�����˷�
    public String jsyf(){
    	return "jsyf";
    }
    //�����˷�
    public String cgjsyf(){
    	
    	return "jsyf";
    }
    //DHL������
    public List<DhlGbj> getDhlAll(){
    	List<DhlGbj> dd = null;
    	List<Dhlzk> z = null;
    
    	try {
    		String guojia = request.getParameter("guojia");
        	String zl = request.getParameter("zl");
        	String c = request.getParameter("c");
        	String k = request.getParameter("k");
        	String g = request.getParameter("g");
        	z = dhlZkDao.getDhlzk();
        	
        	Double tjz= 0d;
        	if((c != null && !"".equals(c))&&((k != null && !"".equals(k)))&&(g != null && !"".equals(g))){
        		tjz=(Double.parseDouble(c)*Double.parseDouble(k)*Double.parseDouble(g))/5000;	
        	}
             	
        	Double ss = 0d;
        	if(tjz>Double.parseDouble(zl)){
        		ss=tjz;
        	}else{
        		ss=Double.parseDouble(zl);
        	}
        	List<DhlFq> ff = dhlfqDao.getGjSel(guojia);
        	
        	if (ff.size()==0) {
        		
        		//request.setAttribute("gj", "�Ҳ����˹���");
        		if(ss>20){
        		//request.setAttribute("zl", "�������ܳ���20KG");
        		}
			}else{
				
        	dd = dhlgbjDao.getZlQy(ss, ff.get(0).getQuyu());
        	if(ss<0.5){
        		ss = 0.5;
        		dd = dhlgbjDao.getZlQy(ss, ff.get(0).getQuyu());
        	}else{
        	if(dd.size() ==0){
        		Double countsize = ss-0.5;
	    		Double zhi=(Double) (countsize)/0.5;
	    		Double ye = (Double) Math.ceil(zhi);
	    		
				ye=0.5+ye*0.5;
				
				dd = dhlgbjDao.getZlQy(ye, ff.get(0).getQuyu());
			}
        	}
			}
        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.setAttribute("dd", (dd.get(0).getMoney())*(z.get(0).getLy())*(z.get(0).getZk()));
		return dd;
    }
    //����IE������
    public List<FedExIeGbj> getFedExIeAllGbj(){
    	List<FedExIeGbj> dd = null;
    	List<Fedex> f = null;
    	try {
    		String guojia = request.getParameter("guojia");
        	String zl = request.getParameter("zl");
        	String c = request.getParameter("c");
        	String k = request.getParameter("k");
        	String g = request.getParameter("g");
        	f = fedexDao.getFedex();
        	Double tjz= 0d;
        	if((c != null && !"".equals(c))&&((k != null && !"".equals(k)))&&(g != null && !"".equals(g))){
        		tjz=(Double.parseDouble(c)*Double.parseDouble(k)*Double.parseDouble(g))/5000;	
        	}
             	
        	Double ss = 0d;
        	if(tjz>Double.parseDouble(zl)){
        		ss=tjz;
        	}else{
        		ss=Double.parseDouble(zl);
        	}
        	List<FedExIe> ff = fedExIeDao.getFedExIe(guojia);
        	if (ff.size()==0 || ss>20) {
        		//request.setAttribute("gj", "�Ҳ����˹���");
        		if(ss>20){
        			//request.setAttribute("zl", "�������ܳ���20KG");
        		}
			}else{
				
        	dd = fedExIeGbjDao.getFedExIeGbj(ss, ff.get(0).getQuyu());
        	if(ss<0.5){
        		ss = 0.5;
        		dd = fedExIeGbjDao.getFedExIeGbj(ss, ff.get(0).getQuyu());
        	}else{
        	if(dd.size() ==0){
        		Double countsize = ss-0.5;
	    		Double zhi=(Double) (countsize)/0.5;
	    		Double ye = (Double) Math.ceil(zhi);
	    		
				ye=0.5+ye*0.5;
				
	    		dd = fedExIeGbjDao.getFedExIeGbj(ye, ff.get(0).getQuyu());
			}
        	}
			}
        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.setAttribute("edd", (dd.get(0).getMoney())*(f.get(0).getLy()));
    	return dd;
    }
    //����IP������
    public List<FedExIpgbj> getFedExIpGbj(){
    	
    	List<FedExIpgbj> dd = null;
    	List<Fedex> f = null;
    	try {
    		String guojia = request.getParameter("guojia");
        	String zl = request.getParameter("zl");
        	String c = request.getParameter("c");
        	String k = request.getParameter("k");
        	String g = request.getParameter("g");
        	f = fedexDao.getFedex();
        	Double tjz= 0d;
        	if((c != null && !"".equals(c))&&((k != null && !"".equals(k)))&&(g != null && !"".equals(g))){
        		tjz=(Double.parseDouble(c)*Double.parseDouble(k)*Double.parseDouble(g))/5000;	
        	}
             	
        	Double ss = 0d;
        
        	if(tjz>Double.parseDouble(zl)){
        		ss=tjz;
        
        	}else{
        		ss=Double.parseDouble(zl);
        
        	}
        	List<FedExIp> ff = fedExIpDao.getFedExIp(guojia);
        	if (ff.size()==0 || ss>20) {
        		
        		if(ss>20){
        		//request.setAttribute("zl", "�������ܳ���20KG");
        		}
			}else{
				
        	dd = FedExIpGbjDao.getFedExIpGbj(ss, ff.get(0).getQuyu());
        	if(ss<0.5){
        		ss = 0.5;
        		dd = FedExIpGbjDao.getFedExIpGbj(ss, ff.get(0).getQuyu());
        	}else{
        	if(dd.size() ==0){
        		Double countsize = ss-0.5;
	    		Double zhi=(Double) (countsize)/0.5;
	    		Double ye = (Double) Math.ceil(zhi);
	    		
				ye=0.5+ye*0.5;
				
	    		dd = FedExIpGbjDao.getFedExIpGbj(ye, ff.get(0).getQuyu());
			}
        	}
			}
        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.setAttribute("pdd", (dd.get(0).getMoney())*(f.get(0).getLy()));
		return dd;
    }
    //EMS������
    public List<EmsZf> getEmsGbj(){
    	String guojia = request.getParameter("guojia");
    	String zl = request.getParameter("zl");
    
    	List<EmsZf> ss = emsZfDao.getEmszf(guojia);
    	try {
    		if(ss.size() ==0 || Double.parseDouble(zl)>30){
        		
        		if(Double.parseDouble(zl)>30){
        			//request.setAttribute("zl", "�������ܳ���20KG");
        		}
        	}else{
        	Double m = 0d;
        	for (int i = 0; i < ss.size(); i++) {
        		if(Double.parseDouble(zl)>=0d && Double.parseDouble(zl)<30){
    	    		if(Double.parseDouble(zl)>0.5){
    	    	    if(Double.parseDouble(zl)<=1){
    	    	    	Double countsize = Double.parseDouble(zl)-0.5;
        	    		Double zhi=(Double) (countsize/0.5);
        	    		int ye = (int) Math.ceil(zhi);
        	    	
        	    			for (int j = 0; j < ye; j++) {
        	    				
        	    				m=m+ss.get(0).getXuzhong();
        	    				
        					}
        	    			m=m+ss.get(i).getMoney();
    	    	    }else{
    	    	    	
    	    		Double countsize = Double.parseDouble(zl);
    	    		Double zhi=(Double) (countsize/0.5);
    	    		int ye = (int) Math.floor(zhi);
    	    		if(countsize%0.5==0){
    	    			ye=ye-1;
    	    		}
    	    			for (int j = 0; j < ye; j++) {		
    	    				m=m+ss.get(0).getXuzhong();   				
    					}
    	    			m=m+ss.get(i).getMoney();
    	    	    }
    	        	}else{		
    	        		m=ss.get(i).getMoney();
    	        	}
        		}
    		}
        	request.setAttribute("ss", m+4);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return ss;
    }
    //����ˮ½������
    public List<Gjsl> getGjsl(){
    	String zl = request.getParameter("zl");
    	Double a=0d;
    	List<Gjsl> s = null;
    	s= gjslDao.getZlYf(Double.parseDouble(zl));
    	try {
    		if(Double.parseDouble(zl)>2){
        		//request.setAttribute("zl", "����ˮ½�������ܴ���2kg");
        	}else{
        		if(s.size()==0){
        			Double countsize = Double.parseDouble(zl);
    	    		Double zhi=(Double) (countsize)*10;
    	    		Double ye = (Double) Math.ceil(zhi);
    	    		ye=(ye)/10;
    	    		s=gjslDao.getZlYf(ye);
        		}
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return s;
    }
    //���մ��������
    public List<Hkdb> getHkdb(){
    	String guojia=request.getParameter("guojia");
    	String zl = request.getParameter("zl");	
    	List<Hkdb> ss = hkdbDao.getHkdb(guojia);
    	try {	
    		if(ss.size()!=0 && ss.get(0).getXz()<Double.parseDouble(zl)){
    			//hkdbAll = "111";
    		}
        	else{
        	Double m = 0d;
        	for (int i = 0; i < ss.size(); i++) {
        		if((Double.parseDouble(zl)>1d) && (ss.get(i).getXz()>Double.parseDouble(zl))){
    	    		if(Double.parseDouble(zl)>1){
    	    			if(Double.parseDouble(zl)<=2){
        	    	    	Double countsize = Double.parseDouble(zl)-1;
            	    		Double zhi=(Double) (countsize/1);
            	    		int ye = (int) Math.ceil(zhi);
            	    			for (int j = 0; j < ye; j++) {	    				
            	    				m=m+ss.get(0).getXuz();    	    				
            					}
            	    			m=m+ss.get(i).getMoney();
        	    	    }else{	    	
        	    		Double countsize = Double.parseDouble(zl);
        	    		Double zhi=(Double) (countsize);
        	    		int ye = (int) Math.floor(zhi);
        	    		if(countsize%1==0){
        	    			ye=ye-1;
        	    		}	
        	    			for (int j = 0; j < ye; j++) { 				
        	    				m=m+ss.get(0).getXuz();
        					}
        	    			m=m+ss.get(i).getMoney();
        	    	    }
        	        	}
    	        	}else{
    	        		m=ss.get(i).getMoney();
    	        	}
        		}
        	request.setAttribute("hkdb", m+8);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return ss;
    }
    //���˴��
    public List<Hydb> gethydb(){
    	String guojia=request.getParameter("guojia");
    	String zl = request.getParameter("zl");
    	List<Hydb> ss = hydbDao.getHydb(guojia);
    	try {        		
    		if(ss.size() !=0 && ss.get(0).getXz()<Double.parseDouble(zl)){
    			//hydbAll = "222";
    		}
        	else{
        	Double m = 0d;
        	for (int i = 0; i < ss.size(); i++) {
        		if((Double.parseDouble(zl)>1d) && (ss.get(i).getXz()>Double.parseDouble(zl))){
    	    		if(Double.parseDouble(zl)>1){
    	    			if(Double.parseDouble(zl)<=2){
        	    	    	Double countsize = Double.parseDouble(zl)-1;
            	    		Double zhi=(Double) (countsize/1);
            	    		int ye = (int) Math.ceil(zhi);	    		
            	    			for (int j = 0; j < ye; j++) {
            	    				m=m+ss.get(0).getXuz();		
            					}
            	    			m=m+ss.get(i).getMoney();
        	    	    }else{	
        	    		Double countsize = Double.parseDouble(zl);
        	    		Double zhi=(Double) (countsize);
        	    		int ye = (int) Math.floor(zhi);
        	    		if(countsize%1==0){
        	    			ye=ye-1;
        	    		}
        	    			for (int j = 0; j < ye; j++) {
        	    				m=m+ss.get(0).getXuz();				
        					}
        	    			m=m+ss.get(i).getMoney();
        	    	    }
        	        	}
    	        	}else{
    	        		m=ss.get(i).getMoney();
    	        	}
        		}
        	request.setAttribute("hydb", m+8);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return ss;
    }
    //���˹�����
    public List<Kygbj> getKygbj(){
    	List<Kygbj> dd = null;
    	try {
    		String guojia = request.getParameter("guojia");
        	String zl = request.getParameter("zl");
        	List<kyfq> kk = kyfqDao.getkyfq(guojia);
        	if(kk.size()==0 || Double.parseDouble(zl)>20){
        		//request.setAttribute("gj", "�Ҳ����˹���");
        		if(Double.parseDouble(zl)>20){
        		//request.setAttribute("zl", "�������ܳ���20KG");
        		}
        	}else{
        	  dd = kyfqGbjDao.getKygbj(kk.get(0).getZu(), Double.parseDouble(zl));
        	  if(dd.size()==0){
        		  	Double countsize = Double.parseDouble(zl);
	  	    		Double zhi=(Double) (countsize)*10;
	  	    		Double ye = (Double) Math.ceil(zhi);
	  	    		ye=(ye)/10;
    				dd = kyfqGbjDao.getKygbj(kk.get(0).getZu(), ye);
        	  }
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return dd;
    }
    //SAL���
    public List<SALdb> getSALdbs(){
    	String guojia = request.getParameter("guojia");
    	String zl = request.getParameter("zl");
    	List<com.demo.entity.Express.SALdb> ss = sALdbDao.getSalDb(guojia);
    	try {
    		//request.setAttribute("gj", "�Ҳ����˹���");
    		if(ss.size()!=0 && ss.get(0).getXz()<Double.parseDouble(zl)){
    			msg="����"+ss.get(0).getXz()+"";
    		}
        	else{
        	Double m = 0d;
        	for (int i = 0; i < ss.size(); i++) {
        		if((Double.parseDouble(zl)>1d) && (ss.get(i).getXz()>Double.parseDouble(zl))){
    	    		if(Double.parseDouble(zl)>1){
    	    			if(Double.parseDouble(zl)<=2){
        	    	    	Double countsize = Double.parseDouble(zl)-1;
            	    		Double zhi=(Double) (countsize/1);
            	    		int ye = (int) Math.ceil(zhi);
            	    			for (int j = 0; j < ye; j++) {  				
            	    				m=m+ss.get(0).getXuz();            	    				
            					}
            	    			m=m+ss.get(i).getMoney();
        	    	    }else{  	
        	    		Double countsize = Double.parseDouble(zl);
        	    		Double zhi=(Double) (countsize);
        	    		int ye = (int) Math.floor(zhi);
        	    		if(countsize%1==0){
        	    			ye=ye-1;
        	    		}
        	    	
        	    			for (int j = 0; j < ye; j++) {			
        	    				m=m+ss.get(0).getXuz();    				
        					}
        	    			m=m+ss.get(i).getMoney();
        	    	    }
        	        	}
    	        	}else{
    	        		m=ss.get(i).getMoney();
    	        	}
        		}
    		
        	request.setAttribute("sal", m+8);
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return ss;
    }
    //�й�����С��
    public List<YunFeiTable> getYzxb(){
    	String guojia = request.getParameter("guojia");
    	String zl = request.getParameter("zl");
    	List<YunFeiTable> yy = guoJiaDao.getAllGuoJia(guojia);
    	Double aa = 0d;
    	aa = (Double.parseDouble(zl)*yy.get(0).getMoney())*0.9+8;
    	request.setAttribute("aa", aa);
    	return yy;
    }
    //������¼
    public String fjjl(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selFjjl(pageSize, pageNumber, us.getName());
    	return "fjjl";
    }
    //���ؿ���
    public String rekx(){
    	return getKanXin();
    }
    //���ؿ���
    public String refjkx(){
    	return fjjl();
    }
    //ɾ����Ϣ
    public String delxiexin(){
    	try {
    		String id = request.getParameter("ids");
    		
    		XieXinTable xx = new XieXinTable();
    		xx.setId(Long.parseLong(id));
        
        	msg="�����ɹ�";
        	xieXinDao.delete(xx);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}    	
    	return fjjl();
    }
    //ɾ������
    public String delxx(){
    	try {
    		String id = request.getParameter("ids");
    		
    		XieXinTable xx = new XieXinTable();
    		xx.setId(Long.parseLong(id));
        
        	msg="�����ɹ�";
        	xieXinDao.delete(xx);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}    	
    	return getKanXin();
    }
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }


}