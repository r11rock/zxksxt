package com.demo.action;

import com.demo.biz.AddBiz;
import com.demo.dao.*;
import com.demo.dao.Express.DhlZkDao;
import com.demo.dao.Express.DhlfqDao;
import com.demo.dao.Express.FedexDao;
import com.demo.dao.user.CaiGouAdminDao;
import com.demo.dao.user.CaiGouDao;
import com.demo.dao.user.CaiWuDao;
import com.demo.dao.user.CangKuYuanDao;
import com.demo.dao.user.GuKeDao;
import com.demo.dao.user.UserDao;
import com.demo.dao.user.YeWu1Dao;
import com.demo.dao.user.YeWuDao;
import com.demo.entity.*;
import com.demo.entity.Express.DhlFq;
import com.demo.entity.Express.Dhlzk;
import com.demo.entity.Express.Fedex;
import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.order.OrderTable;
import com.demo.entity.order.Order_Detail;
import com.demo.entity.user.CaiGou;
import com.demo.entity.user.CaiGouAdmin;
import com.demo.entity.user.CaiWuTable;
import com.demo.entity.user.CangKuYuan;
import com.demo.entity.user.GuKeTable;
import com.demo.entity.user.UserInfo;
import com.demo.entity.user.YeWu;
import com.demo.entity.user.YeWu1;
import com.demo.list.PageModel;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction implements ServletRequestAware
{
	 private static final long serialVersionUID = 1L;
	 @Resource
	private PageBiz pageBiz;
	@Resource
	private XieXinDao xieXinDao;
 	@Resource
    private ZhangHaoDao zhangHaoDao;
 	@Resource
    private GuoJiaDao guoJiaDao;
 	@Resource
    private LeiMuDao leiMuDao;
 	@Resource
    private OrderDao orderDao;
 	@Resource
    private YeWu1Dao yeWu1Dao;
 	@Resource
    private UserDao userDao;
 	@Resource
    private OrderTableDao orderTableDao;
 	@Resource
    private CaiGouDao caiGouDao;
 	@Resource
    private YeWuDao yeWuDao;
 	@Resource
    private CangKuYuanDao cangKuYuanDao;
 	@Resource
    private GuKeDao guKeDao;
 	@Resource
    private CaiGouAdminDao caiGouAdminDao;
 	@Resource
    private CaiWuDao caiWuDao;
 	@Resource
    private HuiLvDao huiLvDao;
 	@Resource
    private Order_DetailDao order_DetailDao;
 	@Resource
    private YuCunDao yuCunDao;
	@Resource
    private DhlfqDao dhlfqDao;
	@Resource
    private DhlZkDao dhlZkDao;
	@Resource
    private FedexDao fedexDao;
 	private YunCun yunCun;
 	public Long chuli;
 	public List<OrderTable> stu1;
    private OrderTable ordertable;
    private GuKeTable guketable;
    private YunFeiTable yunfei;
    private UserInfo user;
    private ZhangHao zhanghao;
    private HuiLvTable huitable;
    private CaiWuTable caiWuTable;
    public String ms;
    private YeWu yeWu;
    private CaiGouAdmin caiGouAdmin;
    private XieXinTable xiexin;
    public String sumaitong;
    public String guoneidanhao;
    public String msg;
    public String usertype;
    public int pageindex;
    public String name;
    public String oldpwd;
    public String username;
    public String orderId;
    public String orderIds;
    public String time;
    public String time1;
    public String zhname;
    public String name1;
    public Long zhanghaoId;
    public String zhanghaoIds;
    private PageBean pageBean;
    public String danhao;
    public String xujia;
    public String dhgatezhanghao;
    public int pageNumber;
    public Long caigouyuan;
    private int page;
    public List<OrderTable> orders;
    public Long quyu;
    public String guojia;
    public Long selcaigouyuan;
    public String bianma;
    public String leimu;
    public String leimus;
   
    private HttpServletRequest request;
    
	public YunCun getYunCun() {
		return yunCun;
	}

	public void setYunCun(YunCun yunCun) {
		this.yunCun = yunCun;
	}

	public GuKeTable getGuketable() {
		return guketable;
	}

	public void setGuketable(GuKeTable guketable) {
		this.guketable = guketable;
	}

	public HuiLvTable getHuitable() {
		return huitable;
	}

	public void setHuitable(HuiLvTable huitable) {
		this.huitable = huitable;
	}

	public XieXinTable getXiexin() {
		return xiexin;
	}

	public void setXiexin(XieXinTable xiexin) {
		this.xiexin = xiexin;
	}

	public CaiWuTable getCaiWuTable() {
		return caiWuTable;
	}

	public void setCaiWuTable(CaiWuTable caiWuTable) {
		this.caiWuTable = caiWuTable;
	}

	public YunFeiTable getYunfei() {
		return yunfei;
	}

	public void setYunfei(YunFeiTable yunfei) {
		this.yunfei = yunfei;
	}

	public PageBean getPageBean()
    {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean)
    {
        this.pageBean = pageBean;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public OrderTable getOrdertable()
    {
        return ordertable;
    }

    public void setOrdertable(OrderTable ordertable)
    {
        this.ordertable = ordertable;
    }

    public UserInfo getUser()
    {
        return user;
    }

    public YeWu getYeWu()
    {
        return yeWu;
    }

    public void setYeWu(YeWu yeWu)
    {
        this.yeWu = yeWu;
    }

    public void setUser(UserInfo user)
    {
        this.user = user;
    }

    public ZhangHao getZhanghao()
    {
        return zhanghao;
    }

    public void setZhanghao(ZhangHao zhanghao)
    {
        this.zhanghao = zhanghao;
    }

    public String addzh()
    {
        return "addzh";
    }
    //���ص��ϴ�����ҳ��
    public String returnorder()
    {
        return "addorder";
    }
    //����Ա�޸Ķ�����
    public String uporder(){
    	return "uporders";
    }
    //����Ա�޸Ķ�����
    public String updateorders(){
    	String orderId = ordertable.getOrderId();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setOrderId(orderId);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ�";
    	return getAllOrder();
    }
    //�ϴ�����
    public String addorder(){
    	 String dingdan[] = request.getParameterValues("dingdan");
         String jine[] = request.getParameterValues("jine");
         String yunshu[] = request.getParameterValues("yunshu");
         String zhanghao[] = request.getParameterValues("zhanghaoId");
         String beizhu[] = request.getParameterValues("remark");
         AddBiz aa = new AddBiz();
         aa.addOrder(dingdan, jine, yunshu, zhanghao, beizhu);
    	return "addorder";
    }
    //����Ա�鿴ȫ����ɶ���
    public String wcorder()
    {
    	int pageSize = 10;
    	pageBean = pageBiz.selComplete(pageSize, pageNumber, orderId, guoneidanhao);
    	
        return "wcorder";
    }
    //�ɹ�����Ա�õ�����
    public String upcaigouadmin()
    {
    	 LoginInfo us = (LoginInfo)getFromSession("logininfo");
         String ch[] = request.getParameter("bulletinId").split("-");
         for(int i = 0; i < ch.length; i++)
         {
             List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
             if(ls.size() != 0)
             {
                 ls.get(0).setId(Long.parseLong(ch[i]));
                 ls.get(0).setCaigouyuan(us.getUserId());
                 ls.get(0).setWancheng(0l);
                 ls.get(0).setFenpei(1l);
                 orderDao.merge(ls.get(0));
             }
         }
         msg = "�����ɹ� ";
        return getFpAll();
    }
    //�ɹ�����Ա�õ����ⶩ��
    public String cgadminwenti(){
    	 LoginInfo us = (LoginInfo)getFromSession("logininfo");
         String ch[] = request.getParameter("bulletinId").split("-");
         for(int i = 0; i < ch.length; i++)
         {
             List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
             if(ls.size() != 0)
             {
                 ls.get(0).setId(Long.parseLong(ch[i]));
                 ls.get(0).setCaigouyuan(us.getUserId());
                 ls.get(0).setWancheng(0l);
                 ls.get(0).setFenpei(1l);
                 
                 orderDao.merge(ls.get(0));
             }
         }
         msg = "�����ɹ� ";
    	return getWenTiOrder();
    }
    //����ɶ������ظ����� 
    public String fhdaifahuo()
    {
        try
        {
            String[] ch = request.getParameter("bulletinId").split("-");
            String[] str = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setFenpei(1l);
                    ls.get(0).setWancheng(0l);
                    ls.get(0).setDaifahuo(0l);
                    ls.get(0).setDaochu(0l);
                    ls.get(0).setSumaitong(0l);
                    ls.get(0).setGetordersId(1l);
                    orderDao.merge(ls.get(0));
                    str[i] = i + ".�����ɹ���";
                } 
            }

            ActionContext.getContext().put("wenti", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getAllOrder();
    }
    public String adduser()
    {
        return "addadminuser";
    }

    public String updateuser()
    {
        return "updateuser";
    }

    public String update_pwd()
    {
        UserInfo us = userDao.getByUserNameOnPwd(username, oldpwd);
        LoginInfo logininfo = (LoginInfo)ActionContext.getContext().getSession().get("logininfo");
        if(us != null)
        {
            String pwd = user.getPwd();
            UserInfo ui = (UserInfo)userDao.get(logininfo.getUserId());
            ui.setPwd(pwd);
            userDao.merge(ui);
            msg = "�����ɹ�";
        } else
        {
            msg = "����ʧ��";
        }
        return "upuser";
    }
   
    public String getuphuokuan()
    {
        return "uphuokuan";
    }

    public String getorderIds()
    {
        return "updatehuokuan";
    }

    public String getorderIdsd()
    {
        return "updatehuokuans";
    }

    public String getorderId()
    {
        return "updateorder";
    }

    public String jiufen()
    {
        return "jiufen";
    }

    public PageModel<OrderTable> getJiuFen()
    {
    	PageModel<OrderTable> stu = null;
	  try
      {
        List<OrderTable> ls = orderDao.getJiuFen(orderId, time, time1,selcaigouyuan,leimus);
        stu = new PageModel<OrderTable>();
       
            if(ls != null)
            {
                stu.setPagelist(ls);
                if(pageindex != 0)
                    stu.setPageindex(pageindex);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�ɹ�Ա�鿴�����ܽ��
    public List<OrderTable> getAllMoney(String orderId,String time,String time1,Long selcaigouyuan,Long leimus){
    	List<OrderTable> stu = null;
    	try {
    	
    		stu = orderTableDao.getAllMoney(orderId, time, time1, selcaigouyuan,leimus);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return stu;
    }
    //�ɹ�Ա�鿴�����ܻ���
    public List<OrderTable> getAllHuoKuan(String orderId,String time,String time1,Long selcaigouyuan,Long leimus){
    	
    	List<OrderTable> stu = orderTableDao.getAllHuoKuan(orderId, time, time1, selcaigouyuan,leimus);
    	return stu;
    }
    //�ɹ�Ա�鿴�������˷�
    public List<OrderTable> getAllYunFei(String orderId,String time,String time1,Long selcaigouyuan,Long leimus){
    	
    	List<OrderTable> stu = orderTableDao.getAllYunFei(orderId, time, time1, selcaigouyuan,leimus);
    	return stu;
    }
    public OrderTable getUpdateId()
    {
        OrderTable stu = (OrderTable)orderDao.get(ordertable.getId());
        return stu;
    }
    //����Ա�޸Ķ���
    public String updatedingdan()
    {
    	 Long cai = ordertable.getCaigouyuan();
         String order = ordertable.getOrderId();
         String yunshu = ordertable.getYunshu();
         Double yunfei = ordertable.getYunfei();
         String guojia = ordertable.getGuojia();
         String remark = ordertable.getRemark();
         Double huilv = ordertable.getHuilv();
         String gong = ordertable.getGongyunshang();
         Double tuikuan = ordertable.getTuikuan();
         Long shangwang = ordertable.getShangwang();
         Long qianshou = ordertable.getQianshou();
         Long ruzhang = ordertable.getRuzhang();
         String danhao = ordertable.getDanhao();
         Long jiufen = ordertable.getJiufen();
         Double huokuan = ordertable.getHuokuan();
         Double jine = ordertable.getMoney();
         Long tuihuo = ordertable.getTuihuo();
         String bianma = ordertable.getBianma(); 
         java.sql.Date time = ordertable.getJiufentime();
         ordertable = (OrderTable)orderDao.get(ordertable.getId());
         ordertable.setCaigouyuan(cai);
         ordertable.setOrderId(order);
         ordertable.setYunshu(yunshu);
         ordertable.setYunfei(yunfei);
         ordertable.setGuojia(guojia);
         ordertable.setRemark(remark);
         ordertable.setHuilv(huilv);
         ordertable.setGongyunshang(gong);
         ordertable.setTuikuan(tuikuan);
         ordertable.setShangwang(shangwang);
         ordertable.setQianshou(qianshou);
         ordertable.setRuzhang(ruzhang);
         ordertable.setDanhao(danhao);
         ordertable.setJiufen(jiufen);
         ordertable.setHuokuan(huokuan);
         ordertable.setTuihuo(tuihuo);
         ordertable.setMoney(jine);
         ordertable.setBianma(bianma);
         if(jiufen ==1 && (time == null || "".equals(time))){
        	 ordertable.setJiufentime(new java.sql.Date(System.currentTimeMillis()));
         }else if(jiufen ==1 && (time != null && !"".equals(time))){
        	    ordertable.setJiufentime(time);
         }
     
         ordertable.setXiugai(1l);
         orderDao.merge(ordertable);
         msg = "�޸ĳɹ�";
        return getAllOrder();
    }

    public String updatedingdans()
    {
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        Long cai = ordertable.getCaigouyuan();
        Double huokuan = ordertable.getHuokuan();
        ordertable.setHuokuan(huokuan);
        ordertable.setCaigouyuan(cai);
        ordertable.setXiugai(1l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return "uphuokuan";
    }

    public String updatedingdansd()
    {
        Long cai = ordertable.getCaigouyuan();
        Double huokuan = ordertable.getHuokuan();
        String dingdan = ordertable.getOrderId();
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setHuokuan(huokuan);
        ordertable.setOrderId(dingdan);
        ordertable.setCaigouyuan(cai);
        ordertable.setXiugai(1l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return "upadminhuokuan";
    }
    //���ع���Ա�޸Ķ���ҳ��
    public String getOrderAll()
    {
    	int pageSize = 10;
    	pageBean = pageBiz.selAdminUpOrder(pageSize, pageNumber, orderId, danhao, xujia);
       return "uporder";
    }

    public String getupadminhuokuan()
    {
        return "upadminhuokuan";
    }
    //����Ա�鿴ȫ������
    public String getAllOrder()
    {
    	try {
    		 int pagesize = 10;
    	        pageBean = pageBiz.selForPage(pagesize, pageNumber, orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu,chuli);
    	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
        return "getall";
    }
    //����������
    public String getZongLiRun(String orderId,String time,String time1,String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu){
    	
    	List<OrderTable> stu = orderTableDao.getChaKanOrder(orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu);
    	Double lirun = 0d;
    	Double tuikuan = 0d;
    	Double huokuan = 0d;
    	Double yunfei = 0d;
    	Double money = 0d;
    	Double huilv = 0d;
    	String num = "";
    	try {
    		for (int i = 0; i < stu.size(); i++) {
    			if(stu.get(i).getTuikuan() == null){
    				tuikuan = 0d;
    			}else{
    				tuikuan = stu.get(i).getTuikuan();
    			}
    			if(stu.get(i).getHuokuan() == null){
    				huokuan = 0d;
    			}else{
    				 huokuan = stu.get(i).getHuokuan();
    			}
    			if(stu.get(i).getYunfei()==null){
    				yunfei = 0d;
    			}else{
    				yunfei = stu.get(i).getYunfei();
    			}
    			 if(stu.get(i).getHuilv() == null){
    				 huilv = 0d;
    			 }else{
    				 huilv = stu.get(i).getHuilv();
    			 }
    			 if(stu.get(i).getMoney()==null){
    				 money = 0d;
    			 }else{
    				 money = stu.get(i).getMoney();
    			 }
    			
    			lirun = lirun +(money*huilv-(tuikuan*huilv+huokuan+yunfei));
    			DecimalFormat df = new DecimalFormat("0.000");
    			num = df.format(lirun);
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return num;
    }
  
    public String getAllFenPei()
    {
        return "fenpeiorder";
    }
    //����Ա���䶩��
    public String caigou_fenpei()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            String sel[] = request.getParameter("seluserid").split("-");
            String str[] = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(sel.length != ch.length)
                    str[i] = i+".����ʧ��";
                else
                if("".equals(sel[i]) || sel[i] == null)
                    str[i] = i+".����ʧ��";
                else
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setFenpei(1l);
                    ls.get(0).setGetordersId(1l);
                    ls.get(0).setCaigouyuan(Long.parseLong(sel[i]));
                    orderDao.merge((OrderTable)ls.get(0));
                    str[i] = i+".����ɹ���";
                }
            }

            ActionContext.getContext().put("strsd", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getFpAll();
    }
    //�������ⶩ��
    public String wentiorder()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            String str[] = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(ls.size() != 0)
                {
                    ((OrderTable)ls.get(0)).setId(Long.parseLong(ch[i]));
                    ((OrderTable)ls.get(0)).setFenpei(2l);
                    orderDao.merge((OrderTable)ls.get(0));
                    str[i] = "�����ɹ�";
                }
            }

            ActionContext.getContext().put("wenti", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getFpAll();
    }
    //����Ա���䶩��
    public String getFpAll()
    {
    	try {
    		int pageSize = 10;
        	pageBean = pageBiz.selAdminFenPeiOrder(pageSize, pageNumber, orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return "fenpeiorder";
    }

    public String add_user()
    {
    	
        UserInfo us = userDao.getByAccount(user.getUsername());
        if(us != null){
            msg = "�Ѿ�����";
        }
        else
        if(Integer.parseInt(usertype) == 0){
            try
            {
                user.setAdmin(Boolean.valueOf(false));
                user.setName(user.getUsername());
                java.util.Date date = new java.util.Date();
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                user.setTime(sim.format(date));
                user.setQuanxian(Long.valueOf(2L));
                user = (UserInfo)userDao.merge(user);
                YeWu ye = new YeWu();
                ye.setName(name);
                ye.setUserid(user.getId());
                yeWuDao.merge(ye);
                msg = "�����ɹ�";
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        if(Integer.parseInt(usertype) == 1)
        {
            user.setAdmin(Boolean.valueOf(false));
            user.setName(user.getUsername());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setTime(sim.format(date));
            user.setQuanxian(Long.valueOf(3L));
            user = (UserInfo)userDao.merge(user);
            CaiGou cai = new CaiGou();
            cai.setName(name);
            cai.setUserid(user.getId());
            caiGouDao.merge(cai);
            msg = "�����ɹ�";
        } else
        if(Integer.parseInt(usertype) == 2)
        {
            user.setAdmin(Boolean.valueOf(false));
            user.setName(user.getUsername());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setTime(sim.format(date));
            user.setQuanxian(Long.valueOf(4L));
            user = (UserInfo)userDao.merge(user);
            CaiGouAdmin ss = new CaiGouAdmin();
            ss.setName(name);
            ss.setUserid(user.getId());
            caiGouAdminDao.merge(ss);
            msg = "�����ɹ�";
        } else
        if(Integer.parseInt(usertype) == 3)
        {
            user.setAdmin(Boolean.valueOf(false));
            user.setName(user.getUsername());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setTime(sim.format(date));
            user.setQuanxian(Long.valueOf(5L));
            user = (UserInfo)userDao.merge(user);
            CangKuYuan ss = new CangKuYuan();
            ss.setName(name);
            ss.setUserid(user.getId());
            cangKuYuanDao.merge(ss);
            msg = "�����ɹ�";
        }else
        if(Integer.parseInt(usertype) == 4)
        {
            user.setAdmin(Boolean.valueOf(false));
            user.setName(user.getUsername());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setTime(sim.format(date));
            user.setQuanxian(Long.valueOf(6L));
            user = (UserInfo)userDao.merge(user);
            GuKeTable ss = new GuKeTable();
            ss.setName(name);
            ss.setUserid(user.getId());
            guKeDao.merge(ss);
            msg = "�����ɹ�";
        }else
        if(Integer.parseInt(usertype) == 5)
        {
            user.setAdmin(Boolean.valueOf(false));
            user.setName(user.getUsername());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setTime(sim.format(date));
            user.setQuanxian(Long.valueOf(7L));
            user = (UserInfo)userDao.merge(user);
            CaiWuTable ss = new CaiWuTable();
            ss.setName(name);
            ss.setUserid(user.getId());
            caiWuDao.merge(ss);
            msg = "�����ɹ�";
        }else
        if(Integer.parseInt(usertype) == 6)
        {
            user.setAdmin(Boolean.valueOf(false));
            user.setName(user.getUsername());
            java.util.Date date = new java.util.Date();
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setTime(sim.format(date));
            user.setQuanxian(Long.valueOf(8L));
            user = (UserInfo)userDao.merge(user);
            YeWu1 ss = new YeWu1();
            ss.setName(name);
            ss.setUserid(user.getId());
            yeWu1Dao.merge(ss);
            msg = "�����ɹ�";
        }
        return "addadminuser";
    }

    public CaiGouAdmin getCaiGouAdmin()
    {
        return caiGouAdmin;
    }

    public void setCaiGouAdmin(CaiGouAdmin caiGouAdmin)
    {
        this.caiGouAdmin = caiGouAdmin;
    }

    public String addzhanghao()
    {
        try
        {
            ZhangHao ss = zhangHaoDao.getZhangHao(zhname);
            if(ss != null)
            {
                msg = "�Ѿ�����";
            } else
            {
                ZhangHao s = new ZhangHao();
                s.setName(zhname);
                zhangHaoDao.merge(s);
                msg = "�����ɹ�";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "addzh";
    }
    //����Ա�鿴ȫ������
    public String getDanHao()
    {
       int pageSize = 10;
       pageBean = pageBiz.selAdminDanHao(pageSize, pageNumber, orderId, time, time1);
        return "danhao";
    }

    //����Ա�鿴Ա���˺�
    public String getAllZhangHao()
    {
        int pageSize = 10;
        pageBean = pageBiz.selAdminZhangHao(pageSize, pageNumber, username);
        return "zhanghao";
    }
    //ɾ���˺�
    public String delzhanghao()
    {
        try
        {
            CaiGou cai = userDao.getcaigou(user.getId());
            CaiGouAdmin caigou = userDao.getcaigouadmin(user.getId());
            YeWu yewu = userDao.getyewu(user.getId());
            CangKuYuan cangku = cangKuYuanDao.getByUserId(user.getId());
            CaiWuTable caiwu = caiWuDao.getByUserId(user.getId());
            GuKeTable guke = guKeDao.getByUserId(user.getId());
            if(cai != null)
            {
                userDao.delete(user);
                caiGouDao.delete(cai);
                msg = "�����ɹ�";
            } else
            if(caigou != null)
            {
                userDao.delete(user);
                caiGouAdminDao.delete(caigou);
                msg = "�����ɹ�";
            } else
            if(yewu != null)
            {
                userDao.delete(user);
                yeWuDao.delete(yewu);
                msg = "�����ɹ�";
            }
            else if(cangku != null){
            	 userDao.delete(user);
            	 cangKuYuanDao.delete(cangku);
                 msg = "�����ɹ�";
            }
            else if(caiwu != null){
            	userDao.delete(user);
           	 	caiWuDao.delete(caiwu);
                msg = "�����ɹ�";
            }else if(guke != null){
            	userDao.delete(user);
           	 	guKeDao.delete(guke);
                msg = "�����ɹ�";
            }
            if(cai != null || caigou != null){
            	
            	List<OrderTable> dd = orderTableDao.getCaiGouNull();
            	 for(int i = 0; i < dd.size(); i++)
                 {
            		 dd.get(i).setId(dd.get(i).getId());
            		 dd.get(i).setCaigouyuan(27l);
                     orderTableDao.merge(dd.get(i));
                     msg = "�����ɹ�";
                 }
            }
            else
            {
                userDao.delete(user);
                msg = "�����ɹ�";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getAllZhangHao();
    }
    //�鿴ȫ���ػ��˺�
    public String getDhgateAll()
    {
       int pageSize = 10;
       pageBean = pageBiz.selDhgate(pageSize, pageNumber, name);
        return "dhgate";
    }
    //�޸Ķػ��˺���Ϣ
    public String updatedhgate()
    {
        ZhangHao zz = new ZhangHao();
        zz.setId(zhanghaoId);
        zz.setName(name1);
        zhangHaoDao.merge(zz);
        msg = "�����ɹ�";
        return getDhgateAll();
    }

    public String updhgate()
    {
        return "updhgate";
    }

    public ZhangHao getZhangId()
    {
        ZhangHao ss = (ZhangHao)zhangHaoDao.get(zhanghaoId);
        return ss;
    }

    public String delorder()
    {
        orderDao.delete(ordertable);
        msg = "�����ɹ�";
        return getAllOrder();
    }

    //�鿴�˷�Ϊ��
    public String getYunFeiNull()
    {
    	int pageSize = 10;
        pageBean = pageBiz.selYeWuYunFeiNull(pageSize, pageNumber, orderId, time, time1);
         return "yunfeinull";
    }

    public PageModel<OrderTable> getHuoKuanNull()
    {
        List<OrderTable> ls = orderDao.getHuoKuanNull(orderId, time, time1);
        PageModel<OrderTable> arr = new PageModel<OrderTable>();
        try
        {
            if(ls != null)
            {
                arr.setPagelist(ls);
                if(pageindex != 0)
                    arr.setPageindex(pageindex);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return arr;
    }

    public String huokuannull()
    {
        return "huokuannull";
    }

    public String caigouweiwancheng()
    {
        return "";
    }

    public String adminlirun()
    {
    	
        return "lirun";
    }
    //����Ա�鿴����С��0
    public PageModel<OrderTable> getLiRunZeRo()
    {  
    	
    	PageModel<OrderTable> ls = null;
    	
    	try {
    		
    	List<OrderTable> stu = orderTableDao.getChaKanOrder(orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu);
    	Double tuikuan = 0d;
    	Double huokuan = 0d;
    	Double yunfei = 0d;
    	Double money = 0d;
    	Double huilv = 0d;
    	ls = new PageModel<OrderTable>();
    	 stu1 = new ArrayList();
    		for (int i = 0; i < stu.size(); i++) {
    			if(stu.get(i).getTuikuan() == null || "".equals(stu.get(i).getTuikuan())){
    				tuikuan = 0d;
    			}else{
    				tuikuan = stu.get(i).getTuikuan();
    			}
    			if(stu.get(i).getHuokuan() == null || "".equals(stu.get(i).getHuokuan())){
    				huokuan = 0d;
    			}else{
    				 huokuan = stu.get(i).getHuokuan();
    			}
    			if(stu.get(i).getYunfei()==null || "".equals(stu.get(i).getYunfei())){
    				yunfei = 0d;
    			}else{
    				yunfei = stu.get(i).getYunfei();
    			}
    			 if(stu.get(i).getHuilv() == null || "".equals(stu.get(i).getHuilv())){
    				 huilv = 0d;
    			 }else{
    				 huilv = stu.get(i).getHuilv();
    			 }
    			 if(stu.get(i).getMoney()==null || "".equals(stu.get(i).getMoney())){
    				 money = 0d;
    			 }else{
    				 money = stu.get(i).getMoney();
    			 }
    			 if(((money*huilv)-(huokuan+yunfei+tuikuan*huilv))<0){
    				 if(stu != null)
    		         {					
    					 stu1.add(stu.get(i));
    		             ls.setPagelist(stu1);
    		             if(pageindex != 0)
    		                 ls.setPageindex(pageindex);
    		         }
    			 }
    		}
    		
        
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return ls;
    }
    //����С��0������
    public String getChaKanZero(String orderId,String time,String time1,String dhgatezhanghao,String danhao)
    {
    	List<OrderTable> stu = orderTableDao.getChaKanOrder(orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu);
    	Double tuikuan = 0d;
    	Double huokuan = 0d;
    	Double yunfei = 0d;
    	Double money = 0d;
    	Double huilv = 0d;
    	Double lirun = 0d;
    	String num = "";
    		for (int i = 0; i < stu.size(); i++) {
    			if(stu.get(i).getTuikuan() == null){
    				tuikuan = 0d;
    			}else{
    				tuikuan = stu.get(i).getTuikuan();
    			}
    			if(stu.get(i).getHuokuan() == null){
    				huokuan = 0d;
    			}else{
    				 huokuan = stu.get(i).getHuokuan();
    			}
    			if(stu.get(i).getYunfei()==null){
    				yunfei = 0d;
    			}else{
    				yunfei = stu.get(i).getYunfei();
    			}
    			 if(stu.get(i).getHuilv() == null){
    				 huilv = 0d;
    			 }else{
    				 huilv = stu.get(i).getHuilv();
    			 }
    			 if(stu.get(i).getMoney()==null){
    				 money = 0d;
    			 }else{
    				 money = stu.get(i).getMoney();
    			 }
    			 if(((money*huilv)-(huokuan+yunfei+tuikuan*huilv))<0){
    				 	lirun = lirun +(money*huilv-(tuikuan*huilv+huokuan+yunfei));
    	    			DecimalFormat df = new DecimalFormat("0.000");
    	    			num = df.format(lirun);
    			 }
    		}
    		return num;
    }
    public String liruninterval()
    {
        return "liruninterval";
    }
    //����Ա�鿴����0С��30�Ķ���
    public PageModel<OrderTable> getLiRunInterval()
    {
    	List<OrderTable> stu = orderTableDao.getChaKanOrder(orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu);
    	Double tuikuan = 0d;
    	Double huokuan = 0d;
    	Double yunfei = 0d;
    	Double money = 0d;
    	Double huilv = 0d;
    	 PageModel<OrderTable> ls = new PageModel<OrderTable>();
    	 stu1 = new ArrayList();
    		for (int i = 0; i < stu.size(); i++) {
    			if(stu.get(i).getTuikuan() == null || "".equals(stu.get(i).getTuikuan())){
    				tuikuan = 0d;
    			}else{
    				tuikuan = stu.get(i).getTuikuan();
    			}
    			if(stu.get(i).getHuokuan() == null || "".equals(stu.get(i).getHuokuan())){
    				huokuan = 0d;
    			}else{
    				 huokuan = stu.get(i).getHuokuan();
    			}
    			if(stu.get(i).getYunfei()==null || "".equals(stu.get(i).getYunfei())){
    				yunfei = 0d;
    			}else{
    				yunfei = stu.get(i).getYunfei();
    			}
    			 if(stu.get(i).getHuilv() == null || "".equals(stu.get(i).getHuilv())){
    				 huilv = 0d;
    			 }else{
    				 huilv = stu.get(i).getHuilv();
    			 }
    			 if(stu.get(i).getMoney()==null || "".equals(stu.get(i).getMoney())){
    				 money = 0d;
    			 }else{
    				 money = stu.get(i).getMoney();
    			 }
    			 if(((money*huilv)-(huokuan+yunfei+tuikuan*huilv))>0 && ((money*huilv)-(huokuan+yunfei+tuikuan*huilv))<30){
    				 if(stu != null)
    		         {					
    					 stu1.add(stu.get(i));
    		             ls.setPagelist(stu1);
    		             if(pageindex != 0)
    		                 ls.setPageindex(pageindex);
    		         }
    			 }
    		}
    		
        return ls;
    }

    //����Ա�鿴����ͨ����
    public String getSuMaiTong()
    {
    	int pageSize = 10;
    	pageBean = pageBiz.selAdminSuMaiTongOrder(pageSize, pageNumber, orderId, time, time1);
    	 return "chakansumaitong";
    }
    //����ͨ�������ظ�ҵ��
    public String yewufanhui()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
                if(ls.size() != 0)
                {
                    ((OrderTable)ls.get(0)).setId(Long.valueOf(Long.parseLong(ch[i])));
                    ((OrderTable)ls.get(0)).setSumaitong(2l);
                    ls.get(0).setYunfei(0d);
                    orderDao.merge((OrderTable)ls.get(0));
                }
            }

            msg = "�����ɹ�";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getSuMaiTong();
    }

    public String fanhuicaigou()
    {
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setSumaitong(0l);
        orderDao.merge(ordertable);
        return getSuMaiTong();
    }
    //�鿴����Ա�˻�����
    public String getTuiKuanOrder()
    {
        int pageSize = 10;
        pageBean = pageBiz.selAdminTuiHuo(pageSize, pageNumber, orderId, danhao,chuli);
        return "tuikuan";
    }
    //�鿴���ⶩ��
    public String getWenTiOrder()
    {
        int pageSize = 10;
        pageBean = pageBiz.selAllWenTiOrder(pageSize, pageNumber, orderId);
        return "wentiorder";
    }
    //�鿴�ɹ�δ��ɶ���
    public String getCaiGouWeiOrder() throws Exception
    {
    	try {
    		int pageSize = 10;
            pageBean = pageBiz.selCaiGouWeiWanCheng(pageSize, pageNumber, orderId);
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return "weiwancheng";
    }
    //�鿴�ɹ�ȫ����ɶ���
    public String getCaiGouAll()
    {
    	try {
    	     request.getSession().setAttribute("caigouyuan", caigouyuan);
    	       int pageSize = 10;
    	       pageBean = pageBiz.selAdminCaiGouWanChengOrder(pageSize, pageNumber, caigouyuan, orderId, time, time1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
   
        return "getcaigou";
    }
   //��������ͨ�޸ı�עҳ��
    public String updateremark(){
    	return "upsumaitong";
    }
    //�޸�����ͨ��ע
    public String upremark(){
    	String remark = ordertable.getRemark();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setRemark(remark);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ�";
    	return getSuMaiTong();
    }
    //��עΪ��������
    public String upjinji(){
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setJingji(1l);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ� ��";
    	return getAllOrder();
    }
    public String yunfeitable(){
    	return "addguojia";
    }
    //����Զ�����˷��ֶ�
    public String adminguojia() throws Exception{
    	
    	String[] quyu = request.getParameterValues("guojiaquyu");
    	String[] guojia = request.getParameterValues("guojia");
    	String[] money = request.getParameterValues("money");
         String[] str = new String[quyu.length];
        try {
        for(int i = 0; i < quyu.length; i++)
        {
            List<YunFeiTable> ls = orderDao.getGuoJia(guojia[i]);
           
            if("".equals(quyu[i]) || quyu[i] == null)
                str[i] = i+".������Ϊ�գ�";
            else
            if(ls.size() != 0)
                str[i] = i+".�����Ѿ�����!";
            else
            if("".equals(money[i]) || money[i] == null)
                str[i] = i+".����Ϊ��!";
            else
            {
               YunFeiTable aa = new YunFeiTable();
               aa.setQuyu(Long.parseLong(quyu[i]));
               aa.setGuojia(guojia[i]);
               aa.setMoney(Double.parseDouble(money[i]));
               guoJiaDao.merge(aa);
               str[i] = i+".��ӳɹ�!";
            }
        }
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
         ActionContext.getContext().put("insert", str);
    	return "addguojia";
    }
    //��ѯȫ�������˷�
    public String guoJiaYunFei(){
    	int pageSize = 10;
    	pageBean = pageBiz.selGuoJiaYunFei(pageSize, pageNumber, quyu, guojia);
    	return "yunfei";
    }
    //�޸Ĺ����˷�
    public String updateGuoJia(){
    	return "updateyunfei";
    }
    //��Ų�ѯȫ�������˷�
    public YunFeiTable getYunFeiAll(){
    	YunFeiTable stu = guoJiaDao.get(yunfei.getId());
    	return stu;
    }
    //�޸��˷�
    public String upyunfei(){
    	Long quyu = yunfei.getQuyu();
    	String guojia = yunfei.getGuojia();
    	Double money = yunfei.getMoney();
    	yunfei = guoJiaDao.get(yunfei.getId());
    	yunfei.setQuyu(quyu);
    	yunfei.setGuojia(guojia);
    	yunfei.setMoney(money);
    	guoJiaDao.merge(yunfei);
    	msg = "�޸ĳɹ�";
    	return guoJiaYunFei();
    }
    //ɾ���˷�
    public String delGuoJia(){
    	guoJiaDao.delete(yunfei);
        msg = "ɾ���ɹ�";
    	return guoJiaYunFei();
    }
    //�鿴����ͨ�Ѿ���ɶ���
    public String getWanChengSuMaiTong(){
    	int pageSize = 10;
    	pageBean = pageBiz.selSuMaiTong(pageSize, pageNumber, orderId, time, time1);
    	return "sumaitong";
    }
    //���ص����ͻ���Ϣҳ��
    public String getKeHuYunFei(){
    	return "importkehu";
    }
    //�����ͻ��˷�
    public String importYunFei(){
    	String id = request.getParameter("guke");
    	String time = request.getParameter("time");
    	orders = orderDao.getAdminYunFei(Long.parseLong(id), time);
    	return "export";
    }
    //����
    public String ceshi(){
    	return "test";
    }
    //�鿴ȫ����Ŀ
    public String leimu()
    {
    	return "leimu";
    }
    //�鿴ȫ����Ŀ
    public PageModel<LeiMuTable> getLeiMuAll(){
    	 List<LeiMuTable> ls = leiMuDao.getAllLeiMu();
         PageModel<LeiMuTable> arr = new PageModel<LeiMuTable>();
         try
         {
             if(ls != null)
             {
                 arr.setPagelist(ls);
                 if(pageindex != 0)
                     arr.setPageindex(pageindex);
             }
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return arr;
    }
    //����Ա������Ŀ���ɹ�
    public String fenpei_leimu(){
	  try
      {
          String ch[] = request.getParameter("bulletinId").split("-");
          String sel[] = request.getParameter("seluserid").split("-");
          String str[] = new String[ch.length];
          for(int i = 0; i < ch.length; i++)
          {
              List<LeiMuTable> ls = leiMuDao.getSelId(Long.parseLong(ch[i]));
              if(sel.length != ch.length)
                  str[i] = i+".����ʧ��";
              else
              if("".equals(sel[i]) || sel[i] == null)
                  str[i] = i+".����ʧ��";
              else
              if(ls.size() != 0)
              {
                  ls.get(0).setId(Long.parseLong(ch[i]));
                  ls.get(0).setUserid((Long.parseLong(sel[i])));
                  leiMuDao.merge(ls.get(0));
                  str[i] = i+".����ɹ���";
              }
          }
          ActionContext.getContext().put("strsd", str);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
      return "leimu";
    }
    public String upfenpei()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            String sel[] = request.getParameter("seluserid").split("-");
            String str[] = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(sel.length != ch.length){
                    str[i] = i+"���˺�δѡ�����ʧ�ܣ�";
                }
                else
                if("".equals(sel[i]) || sel[i] == null)
                {
                    str[i] = i+"����ʧ��";
                }
                else
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setFenpei(1l);
                    ls.get(0).setCaigouyuan(Long.parseLong(sel[i]));
                    ls.get(0).setDenghuixin(0l);
                    ls.get(0).setSumaitong(0l);
                    ls.get(0).setDaifahuo(0l);
                    orderDao.merge(ls.get(0));
                    str[i] = i+".�����ɹ�";
                }
            }
            ActionContext.getContext().put("str", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getWenTiOrder();
    }
    //�ɹ�����Ա�õ�����
    public String caigouadmin(){
    	try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            String str[] = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<LeiMuTable> ls = leiMuDao.getSelId(Long.parseLong(ch[i]));
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setUserid(32l);
                    leiMuDao.merge(ls.get(0));
                    str[i] = i+".����ɹ���";
                }
            }
            ActionContext.getContext().put("strsd", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    	return "leimu";
    }
    //����Ա�趨����
    public String getHuiLv(){
    	
    	return "huilv";
    }
    //����Ա�趨����
    public String updateHuiLv(){
    	try {
    		String date = request.getParameter("time");
    		String huilvcaozuo = request.getParameter("huilvcaozuo");
        	Double huilv = huitable.getHuilv();
        	List<OrderTable> stu = orderTableDao.getMonths();
        	if(Long.parseLong(huilvcaozuo) == 0){
        		HuiLvTable ss = huiLvDao.getHuiLvTime(date);
            	if(ss != null){
            		msg = "������Ѿ��趨�����ʡ��������趨��.";
            	}else{
    	        	HuiLvTable hh = new HuiLvTable();
    	        	hh.setHuilv(huilv);
    	        	hh.setTime(date);
    	        	for (int i = 0; i < stu.size(); i++) {
    	        		
    	        		stu.get(i).setId(stu.get(i).getId());
    	        		stu.get(i).setHuilv(huilv);
                        orderTableDao.merge(stu.get(i));  

					}
    	        	msg = "�����ɹ�";
    	        	huiLvDao.merge(hh);
            	}
        	}else{
        		HuiLvTable ss = huiLvDao.getHuiLvTime(date);
        		if(ss != null){
        			huitable.setId(ss.getId());
        			huitable.setHuilv(huilv);
        			huitable.setTime(date);
        			msg = "�޸ĳɹ�";
        			huiLvDao.merge(huitable);
        		}else{
        			msg = "δ�ҵ��˼�¼�������趨 ";
        		}
        		
        	}        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "huilv";
    }

    //д��
    public String xiexin(){
    	return "xiexin";
    }
    //��ѯȫ��δ����
    public String weiruzhang(){
    	int pageSize = 10;
    	pageBean = pageBiz.selAllWeiRuZhang(pageSize, pageNumber, orderId, time, time1, dhgatezhanghao, danhao);
    	return "weiruzhang";
    }
    //д��
    public String addXieXin()throws Exception{
    	String usertype = request.getParameter("usertype");
    	String shoujian = request.getParameter("userid");
    	String title =new String(request.getParameter("title").getBytes("ISO8859-1"),"utf-8");
    	String neirong = new String(request.getParameter("neirong").getBytes("ISO8859-1"),"utf-8");
    	LoginInfo us = (LoginInfo)getFromSession("logininfo"); 
    	try {
    		if(Long.parseLong(usertype) == 7){
        		
        		List<GuKeTable> guke = guKeDao.getGuKeAll();
        		
        		for (int i = 0; i < guke.size(); i++) {
        			XieXinTable xiexin = new XieXinTable();
        			xiexin.setUserid(guke.get(i).getUserid());
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
        	}else
        	if(Long.parseLong(usertype) == 8){
        		
        		List<UserInfo> yuangong = userDao.getYuanGong();
        		for (int i = 0; i < yuangong.size(); i++) {
        			XieXinTable xiexin = new XieXinTable();
        			xiexin.setUserid(yuangong.get(i).getId());
        			xiexin.setTitle(title);
        			xiexin.setNeirong(neirong);
        			java.util.Date date = new java.util.Date();
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    xiexin.setTime(sim.format(date));
                    UserInfo aa = userDao.getUserId(us.getUserId());
                    xiexin.setFajianren(aa.getName());
                    xiexin.setChakan(0l);
        			xieXinDao.merge(xiexin);
        			msg = "���ͳɹ� ";
    			}
        	}else
        	if(Long.parseLong(usertype) == 9){
        		
        		List<UserInfo> all = userDao.getAllUser();
        		for (int i = 0; i < all.size(); i++) {
        			XieXinTable xiexin = new XieXinTable();
        			xiexin.setUserid(all.get(i).getId());
        			xiexin.setTitle(title);
        			xiexin.setNeirong(neirong);  			
        			java.util.Date date = new java.util.Date();
                    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    xiexin.setTime(sim.format(date));
                    UserInfo aa = userDao.getUserId(us.getUserId());
                    xiexin.setFajianren(aa.getName());
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
                UserInfo aa = userDao.getUserId(us.getUserId());
                xiexin.setFajianren(aa.getName());
                xiexin.setChakan(0l);
                xieXinDao.merge(xiexin);
        		msg = "���ͳɹ� ";
        	}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "xiexin";
    }
    //���ⶩ���޸ı�ע
    public String wenTiRemark(){
    	return "upremark";
    }
    //�޸ı�ע
    public String upWenTiRemark(){
    	String remark = ordertable.getRemark();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setRemark(remark);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ�";
    	return getWenTiOrder();
    }
   //�޸Ļ���
     public String getUpHuiLv(){
    	 return "uphuilv";
     }
     //�޸Ļ���
     public String upHuiLv(){
    	 String time = request.getParameter("time");
    	 String time1 = request.getParameter("time1");
    	 String huilv = request.getParameter("huilv");
    	 List<OrderTable> dd = orderTableDao.getHuiLv(time, time1);
    	  for(int i = 0; i < dd.size(); i++)
          {
    		  dd.get(i).setId(dd.get(i).getId());
    		  dd.get(i).setHuilv(Double.parseDouble(huilv));
    		  msg="�����ɹ�";
              orderTableDao.merge(dd.get(i));
          }
    	 return "uphuilv";
     }
     //�˷�Ϊ��
     public String getChaYunFeiNull(){
    	 String caigouyuan = request.getParameter("caigouyuan");
    	 int pageSize = 10;
    	 pageBean = pageBiz.selYunFeiNull(pageSize, pageNumber, Long.parseLong(caigouyuan), orderId, time, time1);
    	 return "getyunfeinull";
     }
     //����Ϊ��
     public String getChaHuoKuanNull(){
    	 String caigouyuan = request.getParameter("caigouyuan");
    	 int pageSize = 10;
    	 pageBean = pageBiz.selHuoKuanNull(pageSize, pageNumber, Long.parseLong(caigouyuan), orderId, time, time1);
    	 return "gethuokuannull";
     }
     //����Ա��δ��ɶ����޸�Ϊ�Ѿ���� 
     public String upWcOrder(){
    	   try
           {
               String[] ch = request.getParameter("bulletinId").split("-");
               String[] str = new String[ch.length];
               for(int i = 0; i < ch.length; i++)
               {
                   List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                   if(ls.size() != 0)
                   {
                	   if(ls.get(0).getWancheng() == 1){
                		   str[i]  = i+".�˶����Ѿ�����ɵĶ���";
                	   }else{
                       ls.get(0).setId(Long.parseLong(ch[i]));
                       ls.get(0).setDaifahuo(0l);
                       ls.get(0).setWancheng(1l);
                       ls.get(0).setGetordersId(0l);
                       ls.get(0).setSumaitong(0l);
                       
                       ls.get(0).setWanchengtime(new java.sql.Date(System.currentTimeMillis()));
        
                       orderDao.merge(ls.get(0));
                       str[i] = i+".�����ɹ�";
                	   }
                   }
               }
               ActionContext.getContext().put("str", str);
           }
           catch(Exception e)
           {
               e.printStackTrace();
           }
           
    	 return getAllOrder();
     }
     //�鿴�ɹ�������ʱ���
     public PageModel<OrderTable> getCaiRuTime()
     {
     	List<OrderTable> stu = orderTableDao.getChaKanOrder(orderId, time, time1, dhgatezhanghao,danhao,sumaitong,bianma,leimu);
     	
     	 PageModel<OrderTable> ls = new PageModel<OrderTable>();
     	java.util.Date caigoutime = null;
		java.util.Date ruzhangtime = null;
     	 stu1 = new ArrayList();
     		for (int i = 0; i < stu.size(); i++) {
     			
     			if(stu.get(i).getCaigoutime() == null || stu.get(i).getRuzhangtime()==null){
     				stu.get(i).setTimecha(null);
     			}else{
     				caigoutime = stu.get(i).getCaigoutime();
     				ruzhangtime =stu.get(i).getRuzhangtime(); 
     			}
     			 if(stu.get(i).getCaigoutime() != null && stu.get(i).getRuzhangtime() != null){
     				 if(stu != null)
     		         {					
     					 stu1.add(stu.get(i));
     		             ls.setPagelist(stu1);
     		             if(pageindex != 0)
     		                 ls.setPageindex(pageindex);
     		         }
     			 }
     		}
     		
         return ls;
     }
     //���ⶩ���Ѿ���� 
     public String upWtWcOrder(){
    	  try
          {
              String[] ch = request.getParameter("bulletinId").split("-");
              String[] str = new String[ch.length];
              for(int i = 0; i < ch.length; i++)
              {
                  List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                  if(ls.size() != 0)
                  {
               	   if(ls.get(0).getWancheng() == 1){
               		   str[i]  = i+".�˶����Ѿ�����ɵĶ���";
               	   }else{
                      ls.get(0).setId(Long.parseLong(ch[i]));
                      ls.get(0).setDaifahuo(0l);
                      ls.get(0).setWancheng(1l);
                      ls.get(0).setHuokuan(0d);
                      ls.get(0).setYunfei(0d);
                      ls.get(0).setFenpei(1l);
                      ls.get(0).setWanchengtime(new java.sql.Date(System.currentTimeMillis()));
       
                      orderDao.merge(ls.get(0));
                      str[i] = i+".�����ɹ�";
               	   }
                  }
              }
              ActionContext.getContext().put("str", str);
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          
    	 return getWenTiOrder();
     }
     //���׸���
     public String getJiuFenNum(){
    	
    	 String userid= request.getParameter("caigouyuan");
    	 int pageSize = 10;
    	 pageBean = pageBiz.selChaKanJiuFenOrder(pageSize, pageNumber, Long.parseLong(userid), orderId, time, time1);
    	 return "cgjiufen";
     }
     //�޸ķ��䱸ע
     public String updateAdminRemark(){
    	 return "upadminremark";
     }
     //�޸ı�ע
     public String upAdminRemark(){
		String remark = ordertable.getRemark();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setRemark(remark);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ�"; 
    	return getFpAll();
     }
     //����ԱԤ���ʽ�
     public String getYuCun(){
    	 int pageSize=10;
    	 pageBean = pageBiz.selGuKeName(pageSize, pageNumber, name);
    	 return "getyucun";
     }
     //��ֵ
     public String addmoney(){
    	 return "addmoney";
     }
     //��Ų�ѯȫ���ͻ�
     public GuKeTable getGuKeId()
     {
    	 GuKeTable stu = (GuKeTable)guKeDao.get(guketable.getId());
         return stu;
     }
     //��Ų�ѯԤ��
     public YunCun getYuCunId(){
    	 YunCun stu = (YunCun)yuCunDao.get(yunCun.getId());
         return stu;
     }
     //��ֵ���
     public String czMoney(){
    	 try {
    		 String gukeId = request.getParameter("gukeId");
        	
        	 String money = request.getParameter("money");
        	 List<YunCun> gg = yuCunDao.getLastNum(Long.parseLong(gukeId));
        	 if(gg.size() !=0){
	        	 YunCun yun = new YunCun();
	        	 yun.setGukeId(Long.parseLong(gukeId));
	        	 yun.setMoney(Double.parseDouble(money)+gg.get(0).getMoney());
	        	 java.util.Date d = new java.util.Date();
	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             String ff = f.format(d);
	             yun.setTime(ff);
	        	 msg = "��ֵ�ɹ�";
	        	 yuCunDao.merge(yun);
        	 }else{
        		 YunCun yun = new YunCun();
	        	 yun.setGukeId(Long.parseLong(gukeId));
	        	 yun.setMoney(Double.parseDouble(money));
	        	 java.util.Date d = new java.util.Date();
	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             String ff = f.format(d);
	             yun.setTime(ff);
	        	 msg = "��ֵ�ɹ�";
	        	 yuCunDao.merge(yun);
        	 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	 return getYuCun();
     }
     //�޸ĳ�ֵ���
     public String upmoney(){
    	 return "upmoney";
     }
     //�޸ĳ�ֵ���
     public String updateMoney(){
    	 try {
    		 String yuCunId = request.getParameter("yuCunId");
        	
        	 String money = request.getParameter("money"); 
        	 yunCun = yuCunDao.get(Long.parseLong(yuCunId));
	        	 yunCun.setId(Long.parseLong(yuCunId));
	        	 yunCun.setMoney(Double.parseDouble(money));
	        	 java.util.Date d = new java.util.Date();
	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	             String ff = f.format(d);
	             yunCun.setTime(ff);
	             msg = "�޸ĳɹ� ";
	             yuCunDao.merge(yunCun);
        	 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	 return getYcDetail();
     }
     //�������
     public String getYcDetail(){
    	 int pageSize = 10;
    	 pageBean = pageBiz.selYcDetail(pageSize, pageNumber, time, time1);
    	 return "getYcDetail";
     }
     //����Ա����ɶ������ص�������
     public String redaifangqu(){
    	  try
          {
              String[] ch = request.getParameter("bulletinId").split("-");
              String[] str = new String[ch.length];
              for(int i = 0; i < ch.length; i++)
              {
                  List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                  if(ls.size() != 0)
                  {
                      ls.get(0).setId(Long.parseLong(ch[i]));
                      ls.get(0).setDaifahuo(2l);
                      ls.get(0).setWancheng(0l);
//                      ls.get(0).setChaozhongqu(0l);
                      ls.get(0).setDaochu(0l);
                      orderDao.merge(ls.get(0));
                      str[i] = i +".�����ɹ�";
                  }
              }
              ActionContext.getContext().put("strb", str);
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          return getAllOrder();
     }
     //��ѯȫ���˺�
     public String getZhangHaoAll(){
    	 int pageSize = 10;
         pageBean = pageBiz.selDhgate(pageSize, pageNumber, name);
    	 return "getfpzhanghao";
     }
     //�����˺Ÿ�ҵ��
     public String yewu_fenpei(){
    	  try
          {
              String ch[] = request.getParameter("bulletinId").split("-");
              String sel[] = request.getParameter("seluserid").split("-");
              String str[] = new String[ch.length];
              for(int i = 0; i < ch.length; i++)
              {
                  List<ZhangHao> ls = zhangHaoDao.getZhangHaoIdAll(Long.parseLong(ch[i]));
                  if(sel.length != ch.length)
                      str[i] = i+".����ʧ��";
                  else
                  if("".equals(sel[i]) || sel[i] == null)
                      str[i] = i+".����ʧ��";
                  else
                  if(ls.size() != 0)
                  {
                      ((ZhangHao)ls.get(0)).setId(Long.parseLong(ch[i]));
                      ((ZhangHao)ls.get(0)).setYewuId(Long.parseLong(sel[i]));
                      zhangHaoDao.merge((ZhangHao)ls.get(0));
                      str[i] = i+".����ɹ���";
                  }
              }

              ActionContext.getContext().put("strsd", str);
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
    	 return getZhangHaoAll();
     }
     //�ϴ�����
     public String addDhlfq(){
    	 return "addfq";
     }
     //�ϴ�����
     public String addfq(){
    	 String[] guojiaquyu = request.getParameterValues("guojiaquyu");
         String[] guojia = request.getParameterValues("guojia");
         String[] str = new String[guojiaquyu.length];  
         try {
	         for(int i = 0; i < guojiaquyu.length; i++)
	         {
	            DhlFq dhl = new DhlFq();
	            dhl.setGuojia(guojia[i]);
	            dhl.setQuyu(Long.parseLong(guojiaquyu[i]));
	            dhlfqDao.merge(dhl);
	         }
     	} catch (Exception e) {
 			// TODO: handle exception
     		e.printStackTrace();
 		}
          ActionContext.getContext().put("insert", str);
    	 return "addfq";
     }
     public String addmysqlorder(){
    	 return "addmysqlorder";
     }
     //�趨ȼ�ͺ��ۿ�
     public String sdryhzk(){
    	 return "sdryhzk";
     }
     //�趨�ۿۺ�ȼ��
     public String addzkhry(){
    	 String fd = request.getParameter("fd");
    	 String ry = request.getParameter("ry");
    	 String zk = request.getParameter("zk");
    	 List<Dhlzk> dd = dhlZkDao.getDhlzk();
    	 List<Fedex> ff = fedexDao.getFedex();
    	 if(Double.parseDouble(fd) == 0){
    		 if(dd.size()==0){
    			 Dhlzk s = new Dhlzk();
    			 s.setZk(Double.parseDouble(zk));
    			 s.setLy(Double.parseDouble(ry));
    			 msg="�����ɹ� ";
    			 dhlZkDao.merge(s);
    		 }else{
    			 for (int i = 0; i < dd.size(); i++) {
    				 dd.get(i).setId(dd.get(i).getId());
    				 if(!"".equals(ry) && ry != null){
    					 dd.get(i).setLy(Double.parseDouble(ry));
    				 }
    				 if(!"".equals(zk) && zk != null){
    					 dd.get(i).setZk(Double.parseDouble(zk));
    				 }
    				 msg="�����ɹ� ";
    				 dhlZkDao.merge(dd.get(i));
				}
    		 }
    	 }else{
    		 if(ff.size()==0){
    			 Fedex f = new Fedex();
    			 f.setZk(Double.parseDouble(zk));
    			 f.setLy(Double.parseDouble(ry));
    			 msg="�����ɹ� ";
    			 fedexDao.merge(f);
    		 }else{
    			 for (int i = 0; i < ff.size(); i++) {
					ff.get(i).setId(ff.get(i).getId());
					 if(!"".equals(ry) && ry != null){
    					 ff.get(i).setLy(Double.parseDouble(ry));
    				 }
    				 if(!"".equals(zk) && zk != null){
    					 ff.get(i).setZk(Double.parseDouble(zk));
    				 }
					msg="�����ɹ� ";
					fedexDao.merge(ff.get(i));
				}
    		 }
    		 
    	 }
    	 return "sdryhzk";
     }
     //һ������
     public String yjfp(){
    	 List<Order_Detail> dd = orderTableDao.getAllCgs();
    	 try {
    		 for (int i = 0; i < dd.size(); i++) {
    				List<LeiMuTable> mm = leiMuDao.getLmName(dd.get(i).getCat_name());
    				List<OrderTable> ss = orderTableDao.getOrder(dd.get(i).getOrderId());
    				List<Order_Detail> dds = order_DetailDao.getAllOrder(dd.get(i).getOrderId());
    				
    				if(mm.size()==0){
    					msg="û�������Ŀ";
    				}else{
	                  ss.get(0).setId(ss.get(0).getId());
	              
	                  if(dds.size() != 0){
	                  ss.get(0).setBianma(dds.get(0).getItemcode());
	                  }
	                  ss.get(0).setLeimuid(mm.get(0).getId());
	                  ss.get(0).setQianshou(0l);
	                  ss.get(0).setShangwang(0l);
	                  ss.get(0).setRuzhang(0l);
	                  ss.get(0).setFenpei(1l);
	                  ss.get(0).setGetordersId(1l);
	                  ss.get(0).setCaigouyuan(mm.get(0).getUserid());
	                  orderTableDao.merge(ss.get(0));
    				}
    			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	 return getFpAll();
     }
     public String reAllOrders(){
    	 
    	 return "getallorders";
     }
     public OrderTable getReAllOrders(){
    	 OrderTable or = orderDao.get(ordertable.getId());
    	 return or;
     }
     public String reorder(){
    	 return getAllOrder();
     }
     //�鿴�ɹ�ȫ������
     public String getFilter(){
    	 int pageSize = 10;
    	 pageBean = pageBiz.selFilter(pageSize, pageNumber, caigouyuan, orderId, time, time1);
    	 return "getcaigous";
     }
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }
}

