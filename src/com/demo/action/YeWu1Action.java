package com.demo.action;

import com.demo.dao.HuiLvDao;
import com.demo.dao.LeiMuDao;
import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.XieXinDao;
import com.demo.dao.user.UserDao;
import com.demo.entity.HuiLvTable;
import com.demo.entity.LeiMuTable;
import com.demo.entity.XieXinTable;
import com.demo.entity.order.OrderTable;
import com.demo.entity.user.GuKeTable;
import com.demo.entity.user.UserInfo;
import com.demo.list.PageModel;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("yewu1Action")
@Scope("prototype")
public class YeWu1Action extends BaseAction implements ServletRequestAware
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
    public String usertype;
    private OrderTable ordertable;
    private XieXinTable xiexin;
    public String msg;
    public int pageindex;
    public String orderId;
    public String time;
    public String time1;
    public Long zhanghaoId;
    public Long caigouyuan;
    public int pageNumber;
    public Long chuli;
    private PageBean pageBean;
    private HttpServletRequest request;
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
    //ҵ��鿴ȫ�����䶩��
    public String getFenPeiAll(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selAllFenPei(pageSize, pageNumber, us.getUserId(), orderId);
    	return "fenpei";
    }
    //ҵ�� ���䶩��
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
                    ((OrderTable)ls.get(0)).setId(Long.parseLong(ch[i]));
                    ((OrderTable)ls.get(0)).setFenpei(1l);
                    ((OrderTable)ls.get(0)).setCaigouyuan(Long.parseLong(sel[i]));
                    ls.get(0).setGetordersId(1l);
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
        return getFenPeiAll();
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
        return getFenPeiAll();
    }
  //�޸ķ��䱸ע
    public String updateRemark(){
   	 return "upadminremark";
    }
    public OrderTable getUpdateId()
    {
        OrderTable stu = (OrderTable)orderDao.get(ordertable.getId());
        return stu;
    }
    //�޸ı�ע
    public String upAdminRemark(){
		String remark = ordertable.getRemark();
	   	ordertable = orderDao.get(ordertable.getId());
	   	ordertable.setRemark(remark);
	   	orderDao.merge(ordertable);
	   	msg = "�޸ĳɹ�"; 
	   	return getFenPeiAll();
    }
    //�鿴���ⶩ��
    public String getWenTiOrder(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selWenTiOrder(pageSize, pageNumber, us.getUserId(), orderId);
    	return "wenti";
    }
    //�������ⶩ��
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
                    ls.get(0).setGetordersId(1l);
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
    //�޸ı�ע
    public String upRemark(){
    	return "upremark";
    }
    //�޸ı�ע
    public String updateWenTiRemark(){
    	String remark = ordertable.getRemark();
	   	ordertable = orderDao.get(ordertable.getId());
	   	ordertable.setRemark(remark);
	   	orderDao.merge(ordertable);
	   	msg = "�޸ĳɹ�"; 
	   	return getWenTiOrder();
    }
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }
    //ҵ��鿴���ж�����
    public String getYeAllOrder(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean=pageBiz.selYeAllOrder(pageSize, pageNumber, orderId,us.getUserId());
    	return "getallorder";
    }
    //�޸Ķ���
    public String upOrder(){
    	return "updateorder";
    }
    //�޸Ķ���
    public String updateOrder(){
    	String danhao = ordertable.getDanhao();
    	String remark = ordertable.getRemark();
	   	ordertable = orderDao.get(ordertable.getId());
	   	ordertable.setDanhao(danhao);
	   	ordertable.setRemark(remark);
	   	orderDao.merge(ordertable);
	   	msg = "�޸ĳɹ�"; 
	   	return getYeAllOrder();
    }
    //��עΪ��������
    public String upjinji(){
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setJingji(1l);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ� ��";
    	return getYeAllOrder();
    }
    //�鿴���׶���
    public String getDispute(){
    	try {
    		LoginInfo us = (LoginInfo)getFromSession("logininfo");
        	int pageSize = 10;
        	pageBean = pageBiz.selDispute(pageSize, pageNumber, orderId, us.getUserId(),chuli,time,time1,zhanghaoId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return "getDispute";
    }
}