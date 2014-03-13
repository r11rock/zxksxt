package com.demo.action;

import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.user.GuKeDao;
import com.demo.dao.user.UserDao;
import com.demo.entity.order.OrderTable;
import com.demo.entity.user.GuKeTable;
import com.demo.entity.user.UserInfo;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import java.io.PrintStream;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller("guKeAction")
@Scope("prototype")
public class GuKeAction extends BaseAction implements ServletRequestAware
{

    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    @Resource
    private OrderDao orderDao;
    @Resource
    private GuKeDao guKeDao;
    @Resource
    private UserDao userDao;
    @Resource
    private OrderTableDao orderTableDao;
    @Resource
    private PageBiz pageBiz;
    public String chuli;
    public String danhao;
    private OrderTable orderTable;
    private GuKeTable guKeTable;
    private UserInfo user;
    public int pageNumber;
    public String guoneidanhao;
    private PageBean pageBean;
    public int pageindex;
    public String orderId;
    public String msg;
    
    public GuKeTable getGuKeTable() {
		return guKeTable;
	}

	public void setGuKeTable(GuKeTable guKeTable) {
		this.guKeTable = guKeTable;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public OrderTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(OrderTable orderTable) {
		this.orderTable = orderTable;
	}

	public PageBean getPageBean()
    {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean)
    {
        this.pageBean = pageBean;
    }

    public String addorder()
    {
        return "addorder";
    }
    //�˿��ϴ�����
    public String addAllOrder()
    {
        String[] name = request.getParameterValues("name");
        String[] dingdan = request.getParameterValues("orderId");
        String[] guoneikuaidi = request.getParameterValues("guoneikuaidi");
        String[] guoneidanhao = request.getParameterValues("guoneidanhao");
        String[] wuping = request.getParameterValues("wuping");
        String[] guojiaId=request.getParameterValues("guojiaId");
        String[] guowaidizhi = request.getParameterValues("guowaidizhi");
        String[] kuaidifangshiId = request.getParameterValues("kuaidifangshiId");
        String[] remark = request.getParameterValues("remark");
        String[] str = new String[dingdan.length];
        for(int i = 0; i < dingdan.length; i++)
        {
            List<OrderTable> ls = orderDao.getAllOrderId(dingdan[i]);
            if("".equals(dingdan[i]) || dingdan[i] == null)
                str[i] = i+".�����Ų���Ϊ��!";
            else
            if(ls.size() != 0)
                str[i] = i+".�������Ѿ�����";
            else
            if(guoneidanhao[i] == null || "".equals(guoneidanhao[i]))
                str[i] = i+".�˺�δѡ��";
            else
            if("".equals(guoneikuaidi[i]) || guoneikuaidi[i] == null)
            {
                str[i] = i + 1+".���δѡ��";
            }
            else
                if("".equals(guojiaId[i]) || guojiaId[i] == null)
                {
                    str[i] = i + 1+".����δѡ��";
                }
            else
            {
                OrderTable order = new OrderTable();
                order.setTime(new Date(System.currentTimeMillis()));
                order.setCaigouyuan(0L);
                order.setName(name[i]);
                order.setOrderId(dingdan[i]);
                order.setGuoneikuaidiId(Long.parseLong(guoneikuaidi[i]));
                order.setGuoneidanhao(guoneidanhao[i]);
                order.setWuping(wuping[i]);
                order.setGuojiaId(Long.parseLong(guojiaId[i]));
                order.setGuowaidizhi(guowaidizhi[i]);
                order.setKuaidifangshiId(Long.parseLong(kuaidifangshiId[i]));
                order.setZhanghaoId(15l);
                order.setGuoneiwangzhanId(6l);
                order.setRemark(remark[i]);
                LoginInfo login = (LoginInfo)getFromSession("logininfo");
                order.setDengluId(login.getUserId());
                order.setFenpei(3l);
                order.setDaifahuo(1l);
                orderDao.merge(order);
                str[i] = i+".�����ɹ�";
            }
        }

        ActionContext.getContext().put("insert", str);
        return "addorder";
    }
    //�˿ʹ�����
    public String getDaiFaHuo()
    {
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
        int pageSize = 10;
        pageBean = pageBiz.selGuKeDaiFaHuo(pageSize, pageNumber,us.getUserId(),orderId);
        return "daifahuo";
    }
    //�˿��Ѿ�����
    public String getYiJingFaHuo()
    {
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
        int pageSize = 10;
        pageBean = pageBiz.selYiJingRuDan(pageSize, pageNumber, orderId,us.getUserId());
        return "yijingfahuo";
    }
    //�޸Ķ���
    public String updaifahuo(){
    	return "updaifahuo";
    }
    //��Ų�ѯȫ��
    public OrderTable getUpdateId()
    {
        OrderTable stu = (OrderTable)orderDao.get(orderTable.getId());
        return stu;
    }
    //�˿��޸Ĵ�����
    public String updatedaifahuo(){
   
    	try {
    		String orderid = orderTable.getOrderId();
        	Long guoneikuaidi = orderTable.getGuoneikuaidiId();
        	String wuping = orderTable.getWuping();
        	String guoneidanhao = orderTable.getGuoneidanhao();
        	String dizhi = orderTable.getGuowaidizhi();
        	String remark = orderTable.getRemark();
        	Long guojia = orderTable.getGuojiaId();
        	orderTable = orderTableDao.get(orderTable.getId());
        	orderTable.setOrderId(orderid);
        	orderTable.setGuoneikuaidiId(guoneikuaidi);
        	orderTable.setWuping(wuping);
        	orderTable.setGuoneidanhao(guoneidanhao);
        	orderTable.setGuowaidizhi(dizhi);
        	orderTable.setRemark(remark);
        	orderTable.setGuojiaId(guojia);
        	orderTableDao.merge(orderTable);
        	msg = "�޸ĳɹ�";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return getChuHuo();
    }
    //�����޸��Ѿ�����ҳ��
    public String upyijingfahuo(){
    	return "upyijingfahuo";
    }
    //�޸��Ѿ�����
    public String updateyijingfahuo(){
    	String orderid = orderTable.getOrderId();
    	Long guoneikuaidi = orderTable.getGuoneikuaidiId();
    	String wuping = orderTable.getWuping();
    	String guoneidanhao = orderTable.getGuoneidanhao();
    	String dizhi = orderTable.getGuowaidizhi();
    	String remark = orderTable.getRemark();
    	orderTable = orderTableDao.get(orderTable.getId());
    	orderTable.setOrderId(orderid);
    	orderTable.setGuoneikuaidiId(guoneikuaidi);
    	orderTable.setWuping(wuping);
    	orderTable.setGuoneidanhao(guoneidanhao);
    	orderTable.setGuowaidizhi(dizhi);
    	orderTable.setRemark(remark);
    	orderTableDao.merge(orderTable);
    	msg = "�޸ĳɹ�";
    	return getYiJingFaHuo();
    }
    //ɾ����������Ʒ
    public String deleteorder(){
    	try {
    		orderDao.delete(orderTable);
        	msg = "ɾ���ɹ�";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return getDaiFaHuo();
    }
    //�ͻ����ظ��ֿ�
    public String cangkudaifangqu(){
    	String[] id = request.getParameter("bulletinId").split("-");
        for(int i = 0; i < id.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.parseLong(id[i]));
            if(ls.size() != 0)
            {
                ls.get(0).setId(Long.parseLong(id[i]));
                ls.get(0).setDaochu(1l);
                orderDao.merge(ls.get(0));
            }
        }

        msg = "�����ɹ�";
        return getYiJingFaHuo();
    }
    //�鿴�������� 
    public String getChuHuo(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
        int pageSize = 10;
        pageBean = pageBiz.selChuHuo(pageSize, pageNumber, us.getUserId(), orderId, guoneidanhao);
    	return "chuhuo";
    }
    //�˿��޸ĸ�������
    public String upZiLiao(){
    	return "UpdateZiLiao";
    }
    //�˿��޸ĸ�������
    public String updateZiLiao(){

    	String username = user.getUsername();
    	String pwd = user.getPwd();
    	String name = guKeTable.getName();
    	String gukeQQ = guKeTable.getGukeQQ();
    	String phone = guKeTable.getPhone();
    	guKeTable = guKeDao.get(guKeTable.getId());
    	user = userDao.get(user.getId());
    	user.setUsername(username);
    	user.setPwd(pwd);
    	guKeTable.setGukeQQ(gukeQQ);
    	guKeTable.setPhone(phone);
    	guKeTable.setName(name);
    	guKeDao.merge(guKeTable);
		userDao.merge(user);
		msg = "�޸ĳɹ�";
    	return "UpdateZiLiao";
    }
    //�˿Ͳ鿴�˻�
    public String getTuiHuoAll(){
    	
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    	int pageSize = 10;
    	pageBean = pageBiz.selTuiHuo(pageSize, pageNumber, us.getUserId(), orderId,danhao,chuli);
    	return "tuihuo";
    }
    //�˿Ͳ鿴�˷�����
    public String getXqYunFei(){
    	LoginInfo us = (LoginInfo)getFromSession("logininfo");
    
    	GuKeTable gg = guKeDao.getByUserId(us.getUserId());
    	int pageSize = 10;
    	pageBean = pageBiz.selXqYuCun(pageSize, pageNumber, gg.getId());
    	return "getxqYuCun";
    }
    //������ѯ
    public String zxzx(){
    	return "zxzx";
    }
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }

}
