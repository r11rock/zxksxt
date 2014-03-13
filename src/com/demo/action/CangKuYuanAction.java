
package com.demo.action;

import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.entity.order.OrderTable;
import com.demo.list.PageModel;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
@Controller("cangkuYuanAction")
@Scope("prototype")
public class CangKuYuanAction extends BaseAction implements ServletRequestAware
{
 	private static final long serialVersionUID = 1L;
 	@Resource
    private OrderTableDao orderTableDao;
    public int pageindex;
    private OrderTable ordertable;
    @Resource
    private OrderDao orderDao;
    @Resource
    private PageBiz pageBiz;
    public String msg;
    public String danhao;
    public String guoneidanhao;
    public List<OrderTable> orders;
    public String orderId;
    public String wuping;
    public int pageNumber;
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
    //�ֿ�Ա�Ѿ�����
    public String getDaiFaHuo()
    {    
    	int pageSize = 10;
    	pageBean = pageBiz.selSingleNumberNotEmpty(pageSize, pageNumber, guoneidanhao, orderId, wuping);
        return "daifahuo";
    }
    public String upguoneiyunshu()
    {
        return "upyunshudanhao";
    }

    public OrderTable getCangKuYuanAll()
    {
        OrderTable order = orderDao.get(ordertable.getId());
        return order;
    }

    public String upguoneidanhao()
    {
        try
        {
            Long guoneikuaidiid = ordertable.getGuoneikuaidiId();
            Long kuaidifangshi = ordertable.getKuaidifangshiId();
            String remark = ordertable.getRemark();
            String yundaohao = ordertable.getGuoneidanhao();
            ordertable = (OrderTable)orderDao.get(ordertable.getId());
            ordertable.setGuoneidanhao(yundaohao);
            ordertable.setRemark(remark);
            ordertable.setGuoneikuaidiId(guoneikuaidiid);
            ordertable.setKuaidifangshiId(kuaidifangshi);
            orderDao.merge(ordertable);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getDaiFaHuo();
    }
    //�ֿ�Ա���ص�������
    public String wanchengorder()
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
                    ls.get(0).setDaifahuo(2l);
                    ls.get(0).setDaochu(0l);
                    orderDao.merge(ls.get(0));
                    str[i] = i +".�����ɹ�";
                }
            }
            ActionContext.getContext().put("stra", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getDaiFaHuo();
    }

    public String fanhui()
    {
        ordertable = orderDao.get(ordertable.getId());
        ordertable.setDaifahuo(0l);
        ordertable.setSumaitong(0l);
        ordertable.setFenpei(1l);
        ordertable.setWancheng(0l);
        ordertable.setDaochu(0l);
        ordertable.setGetordersId(1l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return getDaiFaHuo();
    }

    public String daifangqu()
    {
    	int pageSize = 10;
    	pageBean = pageBiz.selDaiFangQuOrder(pageSize, pageNumber, guoneidanhao, orderId);
        return "daifangqu";
    }

    public String daifangquwanchengorder()
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
                    ls.get(0).setDaifahuo(0l);
                    ls.get(0).setWancheng(1l);
                    orderDao.merge(ls.get(0));
                    str[i] = i + ".�����ɹ�";
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return daifangqu();
    }
    //���ش�����
    public String fanhuidaifahuo()
    {
        String[] id = request.getParameter("bulletinId").split("-");
        for(int i = 0; i < id.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.parseLong(id[i]));
            if(ls.size() != 0)
            {
                ls.get(0).setId(Long.parseLong(id[i]));
                ls.get(0).setDaifahuo(1l);
                
                orderDao.merge(ls.get(0));
            }
        }
        msg = "�����ɹ�";
        return daifangqu();
    }

    public String exportAllOrder()
    {
        orders = orderTableDao.getDaiFangQu(guoneidanhao, orderId);
        return "exportOrder";
    }

    public InputStream getExcelFile()
    {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        sheet.setColumnWidth(0, 1500);
        sheet.setColumnWidth(1, 2500);
        sheet.setColumnWidth(2, 1500);
        sheet.setColumnWidth(3, 1500);
        sheet.setColumnWidth(4, 3500);
        sheet.setColumnWidth(5, 8500);
        sheet.setColumnWidth(6, 3000);
 
        try
        {
        	orders = new ArrayList(); 
            String[] id = request.getParameter("bulletinId").split("-");
            for(int j = 0; j < id.length; j++)
            {
	        	 List<OrderTable> os = orderDao.getSelAllId(Long.valueOf(Long.parseLong(id[j])));
	        	
	        	 if(os.size() !=0){
	        		 orders.add(os.get(0));
	        	 
            	row = sheet.createRow(j);
                row.setHeight((short)2750);
                if(orders.get(j).getZhanghaoId() != null && orders.get(j).getZhanghaoId() == 15l){
                	cteateCell(workbook, row, 0, orders.get(j).getName());
                }else if(orders.get(j).getZhanghaoId() != null && orders.get(j).getZhanghaoId() != 15l){
                	cteateCell(workbook, row, 0, getZhangHaoId(orders.get(j).getZhanghaoId().toString()));
                }
                cteateCell(workbook, row, 1, orders.get(j).getWuping());
                cteateCell(workbook, row, 2, orders.get(j).getDanhao());
                if(orders.get(j).getKuaidifangshiId() == null){
                	cteateCell(workbook, row, 3, orders.get(j).getShippingtype());
                }else{
                	cteateCell(workbook, row, 3, getKuaiDiFangShi(orders.get(j).getKuaidifangshiId().toString()));
                }
                if((orders.get(j).getGuoneikuaidiId() == null || "".equals(orders.get(j).getGuoneikuaidiId()))&&(orders.get(j).getGuoneidanhao()==null || "".equals(orders.get(j).getGuoneidanhao()))){
                	cteateCell(workbook, row, 4, "");
                }else{
                    cteateCell(workbook, row, 4, getGuoNeiKuaiDiFangShi(orders.get(j).getGuoneikuaidiId().toString())+orders.get(j).getGuoneidanhao().toString());
                }
             
                if((orders.get(j).getGuowaidizhi()!=null && !"".equals(orders.get(j).getGuowaidizhi()))){
                	cteateCell(workbook, row, 5, orders.get(j).getGuowaidizhi());
                }if(orders.get(j).getCountry() != null){
                
                	cteateCell(workbook, row, 5, "Contact Name:"+orders.get(j).getReceiver()+"Address Line 1:"+orders.get(j).getAddress1()+"City:"+orders.get(j).getCity()+"State:"+orders.get(j).getProvince()+"Country:"+getCorresponding(orders.get(j).getCountry())+"Postal Code:"+orders.get(j).getPostcode()+"Phone Number:"+orders.get(j).getTelephone());
                }
                cteateCell(workbook, row, 6, orders.get(j).getOrderId());
            }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            workbook.write(baos);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        byte ba[] = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        if(orders.size() != 0)
        {
        	  for(int i = 0; i < orders.size(); i++)
              {
                  orders.get(i).setId(orders.get(i).getId());
                  orders.get(i).setDaochu(1l);
                  orderTableDao.merge(orders.get(i));
              }

        }
        return bais;
    }

    public String execute()
        throws Exception
    {
        return super.execute();
    }

    private void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, String val)
    {
    	  //����һ��celll��Ԫ��  
        HSSFCell cell=row.createCell(col);  
        cell.setCellValue(val);  
        
        //������ʽ  
        HSSFCellStyle cellstyle=wb.createCellStyle();    
        cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //�±߿�
        cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�
        cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�
        cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�
        cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//��ֱ������ж���  
       // cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//���߿�  
        cellstyle.setWrapText(true);//�����Զ�����  
        cell.setCellStyle(cellstyle);//����Ԫ��������ʽ
    }
    //����Ϊ��
    public String getWeiKong() 
    {
    	int pageSize = 10;
    	pageBean = pageBiz.selSingleNumberEmpty(pageSize, pageNumber, guoneidanhao, orderId, wuping);
        return "guoneiweikong";
    }

    public String upyundanhao()
    {
        return "upweikong";
    }
    public String updateWeiKong(){
    	Long guoneikuaidi = ordertable.getGuoneikuaidiId();
    	String guoneidanhao = ordertable.getGuoneidanhao();
    	String remark = ordertable.getRemark();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setGuoneikuaidiId(guoneikuaidi);
    	ordertable.setGuoneidanhao(guoneidanhao);
    	ordertable.setRemark(remark);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ�";
    	return getWeiKong();
    }
    //�ֿ��޸ĵ���Ϊ�� 
    public String upweikong()
    {
        try
        {
        	request.setCharacterEncoding("UTF-8");
            String[] ch = request.getParameter("bulletinId").split("-");
            String[] guoneiyunshu = request.getParameter("guoneiyunshu").split("-");
            String[] kuaidi = request.getParameter("userid").split("-");
            String[] str = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i].toString()));
                if(ls.size() != 0)
                {               	
                	 if(kuaidi.length != ch.length){      	
                    	str[i] = i +".���δѡ��,����ʧ��";
                    }
                	 
                	 else if(ch.length != guoneiyunshu.length){
                		 str[i] = i +".�������䵥����δ��Ĳ���ʧ��";
                	 }
                	 else{
                    	ls.get(0).setId(Long.parseLong(ch[i]));
                        ls.get(0).setGuoneidanhao(new String(guoneiyunshu[i].getBytes("ISO8859-1"), "utf-8"));
	                    ls.get(0).setGuoneikuaidiId(Long.parseLong(kuaidi[i]));	               
	                    orderDao.merge(ls.get(0));
	                    str[i] = i + ".�����ɹ�!";
                    }
                }
            }

            ActionContext.getContext().put("str", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getWeiKong();
    }

    public String guoneifanhui()
    {
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setDaifahuo(0l);
        ordertable.setSumaitong(0l);
        ordertable.setFenpei(1l);
        ordertable.setWancheng(0l);
        ordertable.setDaochu(0l);
        ordertable.setGetordersId(1l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return getWeiKong();
    }
    public String guoneifanhuis()
    {
        ordertable = (OrderTable)orderDao.get(ordertable.getId());
        ordertable.setDaifahuo(0l);
        ordertable.setSumaitong(0l);
        ordertable.setFenpei(1l);
        ordertable.setWancheng(0l);
        ordertable.setDaochu(0l);
        orderDao.merge(ordertable);
        msg = "�����ɹ�";
        return getYeWuOrder();
    }
    //�ֿ������
    public String zhijiefangru()
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
                    ls.get(0).setDaifahuo(2l);
                    orderDao.merge(ls.get(0));
                    str[i] = i+".�����ɹ���";
                }
            }

            ActionContext.getContext().put("wenti", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getWeiKong();
    }
    //�ֿ������
    public String seldaifangqu()
    {
        try
        {
            List<OrderTable> ls = orderTableDao.getChaXunDaiFaHuo1(guoneidanhao, orderId);
            for(int i = 0; i < ls.size(); i++)
            {
                ls.get(i).setId(ls.get(i).getId());
                ls.get(i).setDaifahuo(2l);
                msg = "�����ɹ� ";
                orderTableDao.merge(ls.get(i));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "daifahuo";
    }


    public String xiugaidaochu()
    {
        return "updaochu";
    }

    public String updatedaochu()
    {
        Long daochu = ordertable.getDaochu();
        ordertable = orderTableDao.get(ordertable.getId());
        ordertable.setDaochu(daochu);
        orderDao.merge(ordertable);
        return daifangqu();
    }
    //�ֿ��޸ĵ���״̬ 
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
                    
                    if(ls.get(0).getDaochu() == 1l){
                    
                    	ls.get(0).setDaochu(0l);
                    }
                    else if(ls.get(0).getDanhao()==null || ls.get(0).getDaochu()==0l){
                   
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
        return daifangqu();
    }
    //�ѵ�����������ҵ��
    public String daochuyewu(){
    	try
        {
            String[] ch = request.getParameter("bulletinId").split("-");
            String[] str = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(ls.get(0).getDaochu()==0){
                	str[i] = i +".����"+ls.get(0).getOrderId()+".��û����������ʧ�ܣ�";
                }
                else{
	                if(ls.size() != 0)
	                {
	                    ls.get(0).setId(Long.parseLong(ch[i]));
	                    ls.get(0).setDaochu(2l);
	                    ls.get(0).setWancheng(0l);
	                    orderDao.merge(ls.get(0));
	                    str[i] = i + ".�����ɹ���";
	                }
                }
            }

            ActionContext.getContext().put("strsd", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    	return daifangqu();
    }
    //���Ѿ������Ķ���ȫ������ҵ��Ϳͻ�
    public String daochuall(){
    	try {
        	List<OrderTable> dao = orderDao.getDaoChuAll();
        	  for(int i = 0; i < dao.size(); i++)
              {
        		  dao.get(i).setId(dao.get(i).getId());
        		  dao.get(i).setDaochu(2l);
                  orderDao.merge(dao.get(i));
              }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
    	return daifangqu();
    }
    //�鿴ҵ�񷵻ض���
    public String getYeWuOrder(){
    	int pageSize = 10;
    	pageBean = pageBiz.selYeWuFanHui(pageSize, pageNumber, guoneidanhao, orderId);
    	return "getYeWuTuiHui";
    }
    //���ش�����
    public String fanhuidaifangqu(){
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
                     ls.get(0).setDaochu(0l);
                     orderDao.merge(ls.get(0));
                     str[i] = i+".�����ɹ���";
                 }
             }
             ActionContext.getContext().put("wenti", str);
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return getYeWuOrder();
    }
    //���ظ�ҵ��
    public String fanhuiyewu(){
    	String[] id = request.getParameter("bulletinId").split("-");
        for(int i = 0; i < id.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.parseLong(id[i]));
            if(ls.size() != 0)
            {
                ls.get(0).setId(Long.parseLong(id[i]));
                ls.get(0).setDaochu(2l);
                orderDao.merge(ls.get(0));
            }
        }

        msg = "�����ɹ�";
    	return getYeWuOrder(); 
    }
   
    //�õ��˿��˻�����
    public String getFaHuoOrder(){
        int pageSize = 10;
        pageBean = pageBiz.selWanChengOrder(pageSize, pageNumber, danhao);
        return "getyijing";
    }
    //�ֿ��޸Ĺ˿��˻�����
    public String updateTuiHuo(){
    	 try
         {
    		
             String[] ch = request.getParameter("bulletinId").split("-");
            
             String[] str = new String[ch.length];
             for(int i = 0; i < ch.length; i++)
             {
           
                 List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                 if(ls.size() != 0)
                 {
                	
                 	ls.get(i).setId(ls.get(i).getId());
                	ls.get(i).setGetordersId(1l);
                	ls.get(i).setWancheng(0l);
                	ls.get(i).setDaochu(0l);
                 	ls.get(i).setTuihuo(1l);
                 
                    orderTableDao.merge(ls.get(i));
                    str[i] = i + ".�����ɹ���";
                 }
             }

             ActionContext.getContext().put("strsd", str);
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
    	return getFaHuoOrder();
    }
    //�޸��˻���ע
    public String upremark(){
    	return "upremark";
    }
    //�޸��˻���ע
    public String updateremark(){
    	String remark = ordertable.getRemark();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setRemark(remark);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ�";
    	return getFaHuoOrder();
    }
   
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }
}



