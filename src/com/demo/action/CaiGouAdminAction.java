package com.demo.action;

import com.demo.dao.KuCunDao;
import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.ZhangHaoDao;
import com.demo.dao.Express.YunFeiTableDao;
import com.demo.dao.user.UserDao;
import com.demo.entity.KuCunTable;
import com.demo.entity.ZhangHao;
import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.order.OrderTable;
import com.demo.entity.user.UserInfo;
import com.demo.list.PageData;
import com.demo.list.PageModel;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller("caiGouAdminAction")
@Scope("prototype")
public class CaiGouAdminAction extends BaseAction implements ServletRequestAware
{
    private static final long serialVersionUID = 1L;
    @Resource
    private PageBiz pageBiz;
    @Resource
    private OrderDao orderDao;
    @Resource
    private UserDao userDao;
    @Resource
    private YunFeiTableDao yunFeiTableDao;
    @Resource
    private OrderTableDao orderTableDao;
    @Resource
    private KuCunDao kuCunDao;
    @Resource
    private ZhangHaoDao zhangHaoDao;
    public Long leimu;
    private OrderTable ordertable;
    public String zhanghaoIds;
    public String orderId;
    public String time;
    public String msg;
    public String wuping;
    public PageData pd;
    public int pageindex;
    public String wancheng;
    public String time1;
    public String caigoutime;
    public String caigoutime1;
    public int pageNumber;
    private PageBean pageBean;
    public List<OrderTable> orders;
    public String zhanghaoId;
    private int page;
    public String key;
    public String bianma;
    private Long cid;
    public String bianhao;
    private ZhangHao myzhangHao;
    public Long leimus;
    public Long category;
    public String gongyunshang;

    private HttpServletRequest request;
    
    public ZhangHao getMyzhangHao() {
		return myzhangHao;
	}

	public void setMyzhangHao(ZhangHao myzhangHao) {
		this.myzhangHao = myzhangHao;
	}

	public PageBean getPageBean()
    {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean)
    {
        this.pageBean = pageBean;
    }

    public Long getCid()
    {
        return cid;
    }

    public void setCid(Long cid)
    {
        this.cid = cid;
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

    public String getAdminDeDaoOrder()
    {
       int pageSize = 10;
       pageBean = pageBiz.selAdminDeDaoOrder(pageSize, pageNumber, orderId);
        return "cgadmindedaoorder";
    }


    public String updedaos()
    {
        LoginInfo us = (LoginInfo)getFromSession("logininfo");
        String ch[] = request.getParameter("bulletinId").split("-");
        String str[] = new String[ch.length];
        for(int i = 0; i < ch.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
            if(ls.size() != 0)
                if(((OrderTable)ls.get(0)).getWuping() == null || "".equals(((OrderTable)ls.get(0)).getWuping()) || ((OrderTable)ls.get(0)).getGongyunshang() == null || "".equals(((OrderTable)ls.get(0)).getGongyunshang()))
                {
                    str[i] = i + ".����"+ls.get(0).getOrderId()+"����Ϣû��д����";
                } else
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setCaigouyuan(us.getUserId());
                    ls.get(0).setDaifahuo(1l);
                    ls.get(0).setGetordersId(0l);
                    orderDao.merge(ls.get(0));
                    str[i] = i +".�����ɹ���";
                }
        }

        ActionContext.getContext().put("str", str);
        return getCaiGouAdminDeDaoOrder();
    }

    public String upcgadminorder()
    {
        return "cgadminorder";
    }
    //�ɹ�����Ա�޸Ķ���
    public String cgadminorder()
        throws Exception
    {
    	try {
    		String orderId = ordertable.getOrderId();
            Long cai = ordertable.getCaigouyuan();
            String danhao = ordertable.getDanhao();
            Double huokuan = ordertable.getHuokuan();
            Double yunfei = ordertable.getYunfei();
            String remark = ordertable.getRemark();
            String gongyunshang = ordertable.getGongyunshang();
            String dizhi = ordertable.getGuowaidizhi();
            String wuping = ordertable.getWuping();
            Date shijian = ordertable.getCaigoutime();
            Long caigou = ordertable.getGuoneiwangzhanId();
            Long kuaidi = ordertable.getKuaidifangshiId();
            String guojia = ordertable.getGuojia();
            String country = ordertable.getCountry();
            Long kucun = ordertable.getKucun();
            String shippingtype = ordertable.getShippingtype();
            String num = request.getParameter("num");
            String bianhao = request.getParameter("bianhao");
            String biaojihao = request.getParameter("biaojihao");
            ordertable = (OrderTable)orderDao.get(ordertable.getId());
            ordertable.setCaigouyuan(cai);
            ordertable.setDanhao(danhao);
            ordertable.setHuokuan(huokuan);
            ordertable.setYunfei(yunfei);
            ordertable.setRemark(remark);
            ordertable.setGongyunshang(gongyunshang);
            if((country == null || "".equals(country))&&(dizhi != null && !"".equals(dizhi))){
            	ordertable.setGuowaidizhi(dizhi);
            }else if((country != null && !"".equals(country))&&(dizhi == null || "".equals(dizhi))){
            	ordertable.setCountry(country);
            }else if((country != null && !"".equals(country))&&(dizhi != null && !"".equals(dizhi))){
            	msg = "���Һ͹����ַ����ͬʱ��д";
            	return "cgadminorder";
            }
            else if((country == null || "".equals(country)) && (shippingtype != null && !"".equals(shippingtype))){
            	msg = "����δ��д,����д����";
            	return "cgadminorder";
            }
            ordertable.setWuping(wuping);
            if(huokuan!=null && !"".equals(huokuan)){
            	ordertable.setCaigoutime(shijian);
            }
            ordertable.setGuoneiwangzhanId(caigou);
            ordertable.setKuaidifangshiId(kuaidi);
            ordertable.setGuojia(guojia);
            ordertable.setXiugai(1l);
            ordertable.setKucun(kucun);
            LoginInfo us = (LoginInfo)getFromSession("logininfo");
            KuCunTable tt = kuCunDao.getKuCunAll(biaojihao,orderId);
            KuCunTable ss = kuCunDao.getOrderAll(orderId);
            KuCunTable aa = kuCunDao.getBiaoHao(biaojihao);
            if(bianhao == null){
            	orderDao.merge(ordertable);
                msg = "�����ɹ�";
                return getCaiGouAdminDeDaoOrder();
            }
            if(Integer.parseInt(bianhao) == 0){
             if(ss == null){
            	if(aa == null){
            		
            	KuCunTable kk = new KuCunTable();
            	kk.setOrderId(orderId);
            	kk.setBiaoji(biaojihao);
            	kk.setNum(Long.parseLong(num));
            	kk.setUserid(us.getUserId());
            	msg = "�����ɹ�";
            	kuCunDao.merge(kk);
            	}else{
            		msg = "����Ѿ����ڡ�����ʧ��";
            		return "cgadminorder";
            	}
             }else{
            	 msg = "�˶����Ѿ��б��";
            	 return "cgadminorder";
             }
            }
            if(Integer.parseInt(bianhao)==1){
            	if(aa == null){
            		msg = "δ�ҵ��˱�š�����ʧ��";
            		return "cgadminorder";
            	}else{
            		
            		
            		if(aa.getNum()-(Long.parseLong(num))>0){
            			aa.setId(aa.getId());
            			aa.setNum(aa.getNum()-(Long.parseLong(num)));
            			kuCunDao.merge(aa);
            		}else{
            			msg = "���"+aa.getNum()+"���㡢����ʧ��";
            			return "cgadminorder";
            		}
            	}
            }
            
            
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return getCaiGouAdminDeDaoOrder();
       
    }

    public String fanhui()
    {
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setFenpei(0l);
        ordertable.setWancheng(0l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return getWanChengOrder();
    }

    public String fanhuiorder()
    {
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setFenpei(0l);
        ordertable.setWancheng(0l);
        ordertable.setGetordersId(0l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return getCaiGouAdminDeDaoOrder();
    }
    //�ѿ�涩��ȫ�����������
    public String daifangqu(){
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
                    ls.get(0).setGetordersId(0l);
                    orderDao.merge(ls.get(0));
                    str[i] = i +".�����ɹ�";
                }
            }
            ActionContext.getContext().put("strsd", str);
           }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return getCaiGouAdminDeDaoOrder();
    }
    //�ɹ�����Ա���ص�������
    public String upsumaitong(){
    	 try
         {
             String ch[] = request.getParameter("bulletinId").split("-");
             for(int i = 0; i < ch.length; i++)
             {
                 List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
                 if(ls.size() != 0)
                 {
                     ls.get(0).setId(Long.parseLong(ch[i]));
                     ls.get(0).setSumaitong(1l);
                     orderDao.merge(ls.get(0));
                 }
             }

             msg = "�����ɹ�";
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return getDaiFaHuo();
    }
    public String upcgadminwenchengorder()
    {
        return "upcgadminorder";
    }

    public OrderTable getCgUpOrderId()
    {
        OrderTable stu = (OrderTable)orderDao.get(ordertable.getId());
        return stu;
    }
   
    public String getcgadminorder()
    {
        return "cguporder";
    }
    //�ɹ�����Ա��ɶ���
    public String getWanChengOrder()
    {
    	try {
    	
    		 LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	        int pageSize = 10;
    	        pageBean = pageBiz.selCaiGouAdminWanChengOrder(pageSize, pageNumber, us.getUserId(), orderId, time, time1,caigoutime,caigoutime1,bianma,gongyunshang,wuping);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
        return "cguporder";
    }

    public String caigouadminuporder()
    {
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String ff = f.format(d);
        request.setAttribute("date", ff);
        return "cgadminorder";
    }

    public String cgupdateorder()
    {
        Long cai = ordertable.getCaigouyuan();
        String danhao = ordertable.getDanhao();
        Double huokuan = ordertable.getHuokuan();
        Double yunfei = ordertable.getYunfei();
        String gongyunshang = ordertable.getGongyunshang();
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setDanhao(danhao);
        ordertable.setYunfei(yunfei);
        ordertable.setHuokuan(huokuan);
        ordertable.setCaigouyuan(cai);
        ordertable.setGongyunshang(gongyunshang);
        ordertable.setXiugai(1l);
        orderDao.merge(ordertable);
        return getWanChengOrder();
    }

    public String addproduct()
    {
        return "addcporder";
    }

    public String addorder()
    {
        String dingdan[] = request.getParameterValues("dingdan");
        String jine[] = request.getParameterValues("jine");
        String yunshu[] = request.getParameterValues("yunshu");
        String zhanghao[] = request.getParameterValues("zhanghaoId");
        String beizhu[] = request.getParameterValues("remark");
        String str[] = new String[dingdan.length];
        for(int i = 0; i < dingdan.length; i++)
        {
            List<OrderTable> ls = orderDao.getAllOrderId(dingdan[i]);
            if("".equals(dingdan[i]) || dingdan[i] == null)
                str[i] = i+".��������Ϊ��";
            else
            if(ls.size() != 0)
                str[i] = i+".�����Ѿ�����";
            else
            if("".equals(jine[i]) || jine[i] == null)
                str[i] = i+"����Ϊ��";
            else
            if("0".equals(zhanghao[i]) || Integer.parseInt(zhanghao[i]) == 0)
            {
                str[i] = i+"�˺�Ϊѡ��";
            } else
            {
                OrderTable order = new OrderTable();
                order.setTime(new java.sql.Date(System.currentTimeMillis()));
                order.setCaigouyuan(0L);
                order.setOrderId(dingdan[i]);
                order.setMoney(Double.parseDouble(jine[i]));
                order.setYunshu(yunshu[i]);
                order.setZhanghaoId(Long.parseLong(zhanghao[i]));
                order.setRemark(beizhu[i]);
                LoginInfo login = (LoginInfo)getFromSession("logininfo");
                order.setDengluId(login.getUserId());
                orderDao.merge(order);
                str[i] = i+".�����ɹ�";
            }
        }

        ActionContext.getContext().put("insert", str);
        return "addcporder";
    }

    public String ceshi()
    {
        return getCaiGouAdminDeDaoOrder();
    }

    public String oldOrder()
    {
        LoginInfo us = (LoginInfo)getFromSession("logininfo");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        UserInfo user = userDao.getByUserNameOnPwd(username, pwd);
        try
        {
            if(user != null)
            {
                List<OrderTable> order = orderDao.getOldDaoRu(user.getId());
                for(int i = 0; i < order.size(); i++)
                {
                    order.get(i).setId(((OrderTable)order.get(i)).getId());
                    order.get(i).setCaigouyuan(us.getUserId());
                    orderDao.merge(order.get(i));
                    msg = "�����ɹ�";
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getWanChengOrder();
    }

    public String oldUnFinishedOrder()
    {
        LoginInfo us = (LoginInfo)getFromSession("logininfo");
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        UserInfo user = userDao.getByUserNameOnPwd(username, pwd);
        System.out.println((new StringBuilder("user")).append(user).toString());
        try
        {
            if(user != null)
            {
                List<OrderTable> order = orderDao.getOldUnDaoRu(user.getId());
                for(int i = 0; i < order.size(); i++)
                {
                     order.get(i).setId(order.get(i).getId());
                    order.get(i).setCaigouyuan(us.getUserId());
                    orderDao.merge(order.get(i));
                    msg = "�����ɹ�";
                }

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getCaiGouAdminDeDaoOrder();
    }
    //�ɹ�����Ա�õ�����
    public String getCaiGouAdminDeDaoOrder()
    {
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
 	   List<OrderTable> aa = orderTableDao.getXiuGaiOrder(us.getUserId());
 	   if(aa != null){
 		   for(int i = 0; i < aa.size(); i++)
             {
 			    aa.get(i).setId(aa.get(i).getId());
 			    aa.get(i).setJingji(1l);
                 orderTableDao.merge(aa.get(i));
             }
 	   }
 	   int pageSize = 10;
 	   pageBean = pageBiz.selDeDaoOrder(pageSize, pageNumber, us.getUserId(), orderId, time, time1, zhanghaoId,category);
        return "caigouadmindedaoorder";
    }
    //�ɹ�����Ա������
    public String getDaiFaHuo()
    {
        int pageSize = 10;
        LoginInfo us = (LoginInfo)getFromSession("logininfo");
        pageBean = pageBiz.selCaiGouAdminDaiFaHuo(pageSize, pageNumber, us.getUserId(), orderId, time, time1,caigoutime,caigoutime1,wuping);
        return "caigouadmindaifahuo";
    }

    public String adminweiwancheng()
    {
        try
        {
            LoginInfo us = (LoginInfo)getFromSession("logininfo");
            orders = orderTableDao.getCaiGouAdminDeDaoOrder1(us.getUserId(), orderId, time);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "exportOrder";
    }

    public String xuanzhe()
    {
        orders = new ArrayList();
        String id[] = request.getParameter("bulletinId").split("-");
        for(int i = 0; i < id.length; i++)
        {
            List<OrderTable> os = orderDao.getSelId(Long.parseLong(id[i]));
            orders.add(os.get(0));
        }

        return "exportOrder";
    }

    public String upadmindaifahuo()
    {
        return "cgadminupdaifahuo";
    }
    //�ɹ�����Ա�޸Ĵ�����
    public String cgadminupdaifahuo()
    {
        String gongyunshang = ordertable.getGongyunshang();
        String beizhu = ordertable.getRemark();
        Long cai = ordertable.getCaigouyuan();
        String danhao = ordertable.getDanhao();
        Double huokuan = ordertable.getHuokuan();
        String dizhi = ordertable.getGuowaidizhi();
        String wuping = ordertable.getWuping();
        Date shijian = ordertable.getCaigoutime();
        Long caigou = ordertable.getGuoneiwangzhanId();
        Long kuaidi = ordertable.getKuaidifangshiId();
        String guojia = ordertable.getGuojia();
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        
        ordertable.setGongyunshang(gongyunshang);
        ordertable.setRemark(beizhu);
        ordertable.setCaigouyuan(cai);
        ordertable.setDanhao(danhao);
        ordertable.setHuokuan(huokuan);
        ordertable.setGuowaidizhi(dizhi);
        ordertable.setGuojia(guojia);
        ordertable.setWuping(wuping);
        ordertable.setCaigoutime(shijian);
        ordertable.setGuoneiwangzhanId(caigou);
        ordertable.setKuaidifangshiId(kuaidi);
        ordertable.setXiugai(1l);
        orderDao.merge(ordertable);
        return getDaiFaHuo();
    }
    //�ɹ�����Ա��������������ͨ������Ա
    public String adminsumaitong()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setSumaitong(1l);
                    ls.get(0).setGetordersId(0l);
                    orderDao.merge(ls.get(0));
                }
            }

            msg = "�����ɹ�";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getCaiGouAdminDeDaoOrder();
    }
    //����ҵ�����
    public String daifa()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setDenghuixin(1l);
                    ls.get(0).setGetordersId(0l);
                    orderDao.merge(ls.get(0));
                }
            }

            msg = "�����ɹ�";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getCaiGouAdminDeDaoOrder();
    }
    //��ɶ�������
    public String fanhuidaifahuo()
    {
        try
        {
            String ch[] = request.getParameter("bulletinId").split("-");
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
                if(ls.size() != 0)
                {
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setWancheng(0l);
                    ls.get(0).setDaifahuo(0l);
                    ls.get(0).setDaochu(0l);
                    ls.get(0).setGetordersId(1l);
                    orderDao.merge(ls.get(0));
                }
            }

            msg = "�����ɹ� ";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getWanChengOrder();
    }
    //�鿴���ⶩ��
    public String getWenTiOrder()
    {
        int pageSize = 10;
        pageBean = pageBiz.selAllWenTiOrder(pageSize, pageNumber, orderId);
        return "wentiorder";
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
                if(sel.length != ch.length)
                    str[i] = i+"���˺�δѡ�����ʧ�ܣ�";
                else
                if("".equals(sel[i]) || sel[i] == null)
                    str[i] = i+"����ʧ��";
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
    //�ɹ�����Ա�鿴����
    public String jiufen(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selDisputeOrder(pageSize, pageNumber, us.getUserId(), orderId, caigoutime, caigoutime1, leimus);
    	return "caigouadminjiufen";
    }
    //�ɹ�����Ա�鿴����С��0�Ķ���
    public String liRunAdmin(){
    
    	return "lirunadminzero";
    }
    //�鿴��涩��
    public String getKuCun(){
   	 LoginInfo us = (LoginInfo)getFromSession("logininfo");
   	 int pageSize = 10;
   	 pageBean = pageBiz.selKuCunOrder(pageSize, pageNumber,us.getUserId(), orderId);
   	 return "kucun";
    }
    //�ɹ�����Ա�޸Ŀ��
    public String upkucun(){
    	return "upnumber";
    }
    //�ɹ�����Ա�޸Ŀ�� 
    public String updatekucun(){
    	Long num = ordertable.getNum();
    	String remark = ordertable.getRemark();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setNum(num);
    	ordertable.setRemark(remark);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ� ";
    	return getKuCun();
    }
    //�޸��˺�
    public String updatezhanghao(){
    	return "upzhanghao";
    }
    //�޸��˺�
    public String myupzhanghao(){
    	 try
         {
    		 	Long zhanghao = ordertable.getZhanghaoId();
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	ordertable.setZhanghaoId(zhanghao);
    	    	orderDao.merge(ordertable);
    	    	msg = "�޸ĳɹ� ";
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
    	return getDaiFaHuo();
    }
    //����ȫ�����׶���
    public String jforder(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	orders=orderTableDao.getJfOrder(us.getUserId());
    	if(orders.size()!=0){
    		  if(orders.size() != 0)
    	        {
	        	  for(int i = 0; i < orders.size(); i++)
	              {
	                  orders.get(i).setId(orders.get(i).getId());
	                  orders.get(i).setCgjf(1l);
	                  orderTableDao.merge(orders.get(i));
	              }
    	        }
    	}
    	return "cgadminexportOrder";
    }
    //�ɹ�����Ա�޸ĵ���״̬
    public String updateAllDaoChu()
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
                    if(ls.get(0).getDaochu() == 1){     	
                    	ls.get(0).setDaochu(0l);
                    }
                    else if(ls.get(0).getDaochu()==0){  	
                    	ls.get(0).setDaochu(1l);
                    }
                    orderDao.merge(ls.get(0));
                    str[i] = i + ".�����ɹ���";
                }
            }
            ActionContext.getContext().put("strsd", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "caigouadminjiufen";
    }
    //�����񸶿��
    public String getPayment(){
    	  LoginInfo us = (LoginInfo)getFromSession("logininfo");
          String ch[] = request.getParameter("bulletinId").split("-");
          String str[] = new String[ch.length];
          for(int i = 0; i < ch.length; i++)
          {
              List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
              if(ls.size() != 0)
            
                      ls.get(0).setId(Long.parseLong(ch[i]));
                      ls.get(0).setCaigouyuan(us.getUserId());
                      ls.get(0).setDaifahuo(3l);
                      ls.get(0).setGetordersId(0l);
                      orderDao.merge(ls.get(0));
                      str[i] = i +".�����ɹ���";
                
          }
          return getCaiGouAdminDeDaoOrder();
    }
    //��ѯ����
    public YunFeiTable getCountry(){
    	YunFeiTable tt = null;
    	try {
    		 OrderTable stu = (OrderTable)orderDao.get(ordertable.getId());
    	  	 tt =  yunFeiTableDao.getCorresponding(stu.getCountry());
    	  	  System.out.println("++tt++"+tt.getGuojia());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	 
  	  return tt;
    }
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }
}
