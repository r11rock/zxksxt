package com.demo.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.demo.dao.GuoJiaDao;
import com.demo.dao.OrderDao;
import com.demo.dao.OrderTableDao;
import com.demo.dao.YuCunDao;
import com.demo.dao.Express.YunFeieDao;
import com.demo.entity.YunCun;
import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.Express.YunFeiTableE;
import com.demo.entity.order.OrderTable;
import com.demo.list.PageModel;
import com.demo.page.PageBean;
import com.demo.page.PageBiz;
import com.demo.vo.LoginInfo;
import com.opensymphony.xwork2.ActionContext;

@Controller("caiWuAction")
@Scope("prototype")
public class CaiWuAction extends BaseAction implements ServletRequestAware
{
    private static final long serialVersionUID = 1L;
    @Resource
	private PageBiz pageBiz;
    @Resource
    private OrderDao orderDao;
	@Resource
    private OrderTableDao orderTableDao;
	@Resource
    private GuoJiaDao guoJiaDao;
	@Resource
	private YuCunDao yuCunDao;
	@Resource
	private YunFeieDao yunFeieDao;
    private PageBean pageBean;
    public int pageNumber;
    public String danhao;
    public String time;
    public String time1;
    public String orderId;
    public String gongyunshang;
    public Long selzhanghao;
    public List<OrderTable> orders;
    public String guoneidanhao;
    public int pageindex;
    public String msg;
    private OrderTable ordertable;
    public String chuli;
    private HttpServletRequest request;
    public OrderTable getOrdertable() {
		return ordertable;
	}
	public void setOrdertable(OrderTable ordertable) {
		this.ordertable = ordertable;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	//�鿴ȫ���˻�
    public String getTuiHuoAll(){
    	try {
    		
    		int pageSize = 10;
        	pageBean = pageBiz.selChaKanAll(pageSize, pageNumber, danhao,chuli);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "tuihuoAll";
    }
    //���񷵻ص����˷�ҳ��
    public String getdaochukehu(){
    	return "importkehu";
    }
    //���񵼳��ͻ����˷�
    public InputStream getExcelFile()
    {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        HSSFCellStyle cellstyle = workbook.createCellStyle();
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 2500);
        sheet.setColumnWidth(2, 2500);
        sheet.setColumnWidth(3, 3900);
        sheet.setColumnWidth(4, 2000);
     
        try
        {
        	String id = request.getParameter("guke");
        	String time = request.getParameter("time");
        	String time1 = request.getParameter("time1");
            orders = orderDao.getDaoChuKeHuYunFei(Long.parseLong(id),time,time1);
           
            for(int i = 0; i < orders.size(); i++)
            {
                row = sheet.createRow(i);
                row.setHeight((short)1000);
                cteateCell(workbook, row, 0, orders.get(i).getName());
                if(orders.get(i).getZhongliang() == null){
                	cteateCell(workbook, row, 1, "");
                }else{
                	cteateCell(workbook, row, 1, orders.get(i).getZhongliang().toString());	
                }
                if(orders.get(i).getGuojiaId() == null){
                	cteateCell(workbook, row, 2, "");	
                }else{
                	cteateCell(workbook, row, 2, getAllYunFei(orders.get(i).getGuojiaId().toString()));
                }
                cteateCell(workbook, row, 3, orders.get(i).getDanhao());
                if(orders.get(i).getYunfei()==null){
                	cteateCell(workbook, row, 4, "");
                }else{
                	Double  yunfei = orders.get(i).getYunfei();
                	
                	DecimalFormat df = new DecimalFormat("0.000");
        			String num = df.format(yunfei);
                	cteateCell(workbook, row, 4,num);
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
        try
        {
            for(int i = 0; i < orders.size(); i++)
            {
                orders.get(i).setId(orders.get(i).getId());
                orders.get(i).setYunfeidaochu(1l);
                orderTableDao.merge(orders.get(i));
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return bais;
    }
    public String execute() throws Exception
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
    //�����޸Ŀͻ�������� 
    public String getRuZhang(){
    	int pageSize = 10;
    	pageBean = pageBiz.selRuZhang(pageSize, pageNumber, orderId, time, time1);
    	return "getruzhang";
    }
    //�����޸�����
    public String upruzhang(){
    	 try
         {
             String[] ch = request.getParameter("bulletinId").split("-");
             String[] sel = request.getParameter("seluserid").split("-");
             String[] str = new String[ch.length];
             for(int i = 0; i < ch.length; i++)
             {
            	
                 List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                 if(ls.size() != 0)
                 {
                     ls.get(0).setId(Long.parseLong(ch[i]));
                     ls.get(0).setRuzhang(Long.parseLong(sel[i]));
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
    	return getRuZhang();
    }
 

    //���񵼳���˾�˷�
    public String getDaoChuYunFei(){
    	return "daochuyunfei";
    }
    
    
    //���񵼳���˾�˷�
    public String daochuyunfei(){
    	String time = request.getParameter("time");
    	String time1 = request.getParameter("time1");
    	orders = orderTableDao.getDaoChuYunFei(time,time1);
    	return "exportKeHuOrder";
    	
    }
    //����鿴���뵥
    public String getDaiRuDan()
    {
    	int pageSize = 10;
    	pageBean = pageBiz.selDaiRudan(pageSize, pageNumber, guoneidanhao, orderId);
        return "dairudan";
    }
    //�ñ�Ų�ѯȫ��
    public OrderTable getUpdateIds()
    {
        OrderTable stu = null;
        try
        {
            stu = orderDao.get(ordertable.getId());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�����޸Ĵ��뵥
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
                    ls.get(0).setSumaitong(0l);
                   
                    ls.get(0).setWancheng(1l);
                    ls.get(0).setDaochu(0l);
                    ls.get(0).setGetordersId(0l);
                    ls.get(0).setWanchengtime(new java.sql.Date(System.currentTimeMillis()));
     
                    orderDao.merge(ls.get(0));
                    str[i] = i+".�����ɹ�";
                }
            }
            ActionContext.getContext().put("strss", str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return getDaiRuDan();
    }
    //���񷵻ش�����
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
                ls.get(0).setDaochu(0l);
                orderDao.merge(ls.get(0));
            }
        }

        msg = "�����ɹ�";
        return getDaiRuDan();
    }
    //���ظ��ֿ��������
    public String cangkudaifangqu(){
    	String[] id = request.getParameter("bulletinId").split("-");
        for(int i = 0; i < id.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.parseLong(id[i]));
            if(ls.size() != 0)
            {
                ls.get(0).setId(Long.parseLong(id[i]));
                ls.get(0).setDaochu(3l);
                
                orderDao.merge(ls.get(0));
            }
        }
        msg = "�����ɹ�";
    	return getDaiRuDan();
    }
    //ҵ�񷵻ظ��˿��Ѿ�����
    public String gukeyifahuo(){
    	String[] id = request.getParameter("bulletinId").split("-");
        for(int i = 0; i < id.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.parseLong(id[i]));
            if(ls.size() != 0)
            {
                ls.get(0).setId(Long.parseLong(id[i]));
                ls.get(0).setWancheng(1l);
                ls.get(0).setWanchengtime(new java.sql.Date(System.currentTimeMillis()));
                orderDao.merge(ls.get(0));
            }
        }

        msg = "�����ɹ�";
    	return getDaiRuDan();
    }
    //���񷵻��޸Ĵ��뵥
    public String updairudan(){	
    	return "updairudan";
    }
    //���񷵻��޸Ĵ��뵥
    public String czupdatedairudan(){	
    	return "czupdairudan";
    }
    //����
    public String czupdatedanhao(){
    	
    	return "updatedanhao";
    }
    
    //�����޸Ĵ��뵥
    public String updatedairudan() throws Exception{
    	String typeuser = request.getParameter("usertype");
    	String[] orderId = request.getParameterValues("orderIds");
    	String[] danhao = request.getParameterValues("danhao");
    	String[] guojia = request.getParameterValues("guojiaId");
    	String[] zhongliang = request.getParameterValues("zhongliang");
    	String[] type = request.getParameterValues("type");
    	String[] yunfei = request.getParameterValues("yunfei");
    	String dengluId = request.getParameter("dengluId");
    	 String[] str = new String[danhao.length];
    	try {
    		for(int i = 0; i < danhao.length; i++) {
    		
    		if(Integer.parseInt(typeuser) == 1){	
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	ordertable.setDanhao(danhao[i]);
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
    	    	ordertable.setYunfei(Double.parseDouble(yunfei[i]));
    	    	
    	    	orderDao.merge(ordertable);
    	    	msg = "�޸ĳɹ����Ƿ���� ";
    			return "updairudan";
    		}
			List<OrderTable> dan = orderDao.getChaKanOrder(orderId[i]);
			List<OrderTable> dd = orderDao.getChaKanKeHu(orderId[i]);
			List<YunFeiTable> ss = null;
			
			if(Integer.parseInt(type[i]) != 2){
				ss = guoJiaDao.getAllGuoJia(guojia[i]);
				if(ss.size()==0){
				str[i] =i + ".δ�ҵ��˹��� ";
    			ActionContext.getContext().put("insert", str);
        		return "updairudan";
				}
			}
    		List<OrderTable> hh = orderDao.getAllDanHao(orderId[i],danhao[i]);
    		List<YunCun> yy = yuCunDao.getUserLastNum(Long.parseLong(dengluId));
    		List<YunFeiTableE> tt = null;
    		if(Integer.parseInt(type[i]) == 2){
    			tt = yunFeieDao.getGuoJia(guojia[i]);
    			if(tt.size()==0){
    			str[i] =i + ".E�ʱ�δ�ҵ��˹��� ";
    			ActionContext.getContext().put("insert", str);
        		return "updairudan";
    			}
    		}
    		
    		if(hh.size()!=0){
    			
    			str[i] =i + ".�����ظ� ";
    			ActionContext.getContext().put("insert", str);
    			return "updairudan";
    		}
    		if(danhao.length != orderId.length){
    		
    			str[i] =i + ".����δ��д ";
    			ActionContext.getContext().put("insert", str);
        		return "updairudan";
    		}
    		if(Double.parseDouble(zhongliang[i]) > 2 && Integer.parseInt(type[i]) == 0){
    			str[i] =i + ".С���������ܴ���2";
    			ActionContext.getContext().put("insert", str);
    			return "updairudan";
    		}
    		if(zhongliang[i] == null || "".equals(zhongliang[i])){
    			str[i] =i + ".����δ��д";
    			ActionContext.getContext().put("insert", str);
    			return "updairudan";
    		}   
    	
    		else if(Integer.parseInt(type[i]) == 0 && dan.size() != 0 && (dan.get(0).getDanhao() != null && !"".equals(dan.get(0).getDanhao()))){
    			ordertable = orderDao.get(ordertable.getId());
    			OrderTable ds = new OrderTable();
    			Long s=System.currentTimeMillis();
    			ds.setOrderId(s.toString());
    			ds.setDanhao(danhao[i]);
    			ds.setKuaidifangshiId(ordertable.getKuaidifangshiId());
    			ds.setSjc(1l);
    			orderTableDao.merge(ds);
    			Double yun = (ss.get(0).getMoney()*Double.parseDouble(zhongliang[i])*0.85+8);
        		ordertable.setDanhao(danhao[i]+","+dan.get(0).getDanhao());
        		String gg="";
        		if(ordertable.getDggjdh()==null){
        			gg = "";
        		}else{
        			gg=ordertable.getDggjdh();
        		}
        		String k = "����"+ds.getOrderId()+","+gg+"";
        		ordertable.setDggjdh(k);
        		Double aaa = null;
        		if(dan.get(0).getZhongliang() == null){
        			aaa = 0d;
        		}else{
        			aaa = dan.get(0).getZhongliang();
        		}
        		ordertable.setZhongliang(Double.parseDouble(zhongliang[i])+aaa);
        		ordertable.setYunfei(yun+dan.get(0).getYunfei());
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - ordertable.getYunfei();
        			YunCun bb = new YunCun();
        			bb.setMoney(u-Double.parseDouble(yunfei[i]));
        			bb.setSymoney(Double.parseDouble(yunfei[i]));
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
        		str[i] =i +".�����ɹ� ";
        		orderDao.merge(ordertable);
    		}
    		else if(Integer.parseInt(type[i]) == 0 && dan.size() != 0 && (dan.get(0).getDanhao() == null || "".equals(dan.get(0).getDanhao()))){
    	
        		Double yun = (ss.get(0).getMoney()*Double.parseDouble(zhongliang[i])*0.85+8);
        		ordertable = orderDao.get(ordertable.getId());
        		ordertable.setDanhao(danhao[i]);
        		ordertable.setDggjdh(ordertable.getOrderId());
        		ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
        		ordertable.setYunfei(yun);
        		
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
        		str[i] =i +".�����ɹ� ";
        		orderDao.merge(ordertable);
        	}
    		if(dd.size() != 0){
    			
        	if(Integer.parseInt(type[i]) == 0 && dd.size() != 0 && (dd.get(0).getDanhao()==null || "".equals(dd.get(0).getDanhao()))){
        		
        		Double yun = (ss.get(0).getMoney()*Double.parseDouble(zhongliang[i]))*0.85+8;
        		ordertable = orderDao.get(ordertable.getId());
        		ordertable.setDanhao(danhao[i]);
        		ordertable.setDggjdh(ordertable.getOrderId());
        		ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
        		ordertable.setYunfei(yun);
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
        		str[i] =i +".�����ɹ� ";
        		orderDao.merge(ordertable);
        	}
        	
        	else if(Integer.parseInt(type[i]) == 0 && (dd.get(0).getDanhao()!=null && !"".equals(dd.get(0).getDanhao())) && dd.size() != 0){
        	
        		Double yun = (ss.get(0).getMoney()*Double.parseDouble(zhongliang[i]))*0.85+8;
        
        		ordertable = orderDao.get(ordertable.getId());
        		OrderTable ds = new OrderTable();
    			Long s=System.currentTimeMillis();
    			ds.setOrderId(s.toString());
    			ds.setDanhao(danhao[i]);
    			ds.setKuaidifangshiId(ordertable.getKuaidifangshiId());
    			ds.setSjc(1l);
    			orderTableDao.merge(ds);
        		ordertable.setDanhao(danhao[i]+","+dd.get(0).getDanhao());
        		ordertable.setZhongliang(Double.parseDouble(zhongliang[i])+dd.get(0).getZhongliang());
        		ordertable.setYunfei(yun+dd.get(0).getYunfei());
        		String gg="";
        		if(ordertable.getDggjdh()==null){
        			gg = "";
        		}else{
        			gg=ordertable.getDggjdh();
        		}
        		String k = "����"+ds.getOrderId()+","+gg+"";
        		ordertable.setDggjdh(k);
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
        		str[i] =i +".�����ɹ� ";
        		orderDao.merge(ordertable);
    		}
        	else if(Integer.parseInt(type[i]) == 1 && (dd.get(0).getDanhao() != null && !"".equals(dd.get(0).getDanhao()))){
       
        		ordertable = orderDao.get(ordertable.getId());
        		OrderTable ds = new OrderTable();
    			Long s=System.currentTimeMillis();
    			ds.setOrderId(s.toString());
    			ds.setDanhao(danhao[i]);
    			ds.setKuaidifangshiId(ordertable.getKuaidifangshiId());
    			ds.setSjc(1l);
    			orderTableDao.merge(ds);
    	    	ordertable.setDanhao(danhao[i]+","+dd.get(0).getDanhao());
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i])+dd.get(0).getZhongliang());
    	    	ordertable.setYunfei(Double.parseDouble(yunfei[i])+dd.get(0).getYunfei());
    	    	String gg="";
        		if(ordertable.getDggjdh()==null){
        			gg = "";
        		}else{
        			gg=ordertable.getDggjdh();
        		}
        		String k = "����"+ds.getOrderId()+","+gg+"";
        		ordertable.setDggjdh(k);
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - ordertable.getYunfei();
        			YunCun bb = new YunCun();
        			bb.setMoney(u-Double.parseDouble(yunfei[i]));
        			bb.setSymoney(Double.parseDouble(yunfei[i]));
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	str[i] =i + ".�����ɹ� ";
    	    	orderDao.merge(ordertable);
        	}
        	else if(Integer.parseInt(type[i]) == 1 && (dd.get(0).getDanhao() == null || "".equals(dd.get(0).getDanhao()))){
       
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	ordertable.setDanhao(danhao[i]);
    	    	ordertable.setDggjdh(ordertable.getOrderId());
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
    	    	ordertable.setYunfei(Double.parseDouble(yunfei[i]));
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - ordertable.getYunfei();
        			YunCun bb = new YunCun();
        			bb.setMoney(u-Double.parseDouble(yunfei[i]));
        			bb.setSymoney(Double.parseDouble(yunfei[i]));
        			bb.setGukeId(yy.get(0).getGukeId());
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	str[i] =i + ".�����ɹ� ";
    	    	orderDao.merge(ordertable);
        	}
        	else if(Integer.parseInt(type[i]) == 2 && (dd.get(0).getDanhao() == null || "".equals(dd.get(0).getDanhao()))){
        		
        		Double yun = 0d;
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	ordertable.setDanhao(danhao[i]);
    	    	ordertable.setDggjdh(ordertable.getOrderId());
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
    	    	if("����".equals(guojia[i]) && 0<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<500){
    	    		 yun = 30d;	
    	    		 ordertable.setYunfei(yun);
    	    	}
    	    	if("����".equals(guojia[i]) && (500<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<2000)){
    	    		 yun = 35d;	
    	    		 ordertable.setYunfei(yun);
    	    	}else if(!"����".equals(guojia[i])){
    	    		 yun = (0.08*Double.parseDouble(zhongliang[i]))+7+5;
    	    		 ordertable.setYunfei(yun);
    	    	}
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	str[i] =i + ".�����ɹ� ";
    	    	orderDao.merge(ordertable);
        	}
        	else if(Integer.parseInt(type[i]) == 2 && ((dd.get(0).getDanhao() != null || !"".equals(dd.get(0).getDanhao())))){
        		Double yun = 0d;
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	OrderTable ds = new OrderTable();
    			Long s=System.currentTimeMillis();
    			ds.setOrderId(s.toString());
    			ds.setDanhao(danhao[i]);
    			ds.setKuaidifangshiId(ordertable.getKuaidifangshiId());
    			ds.setSjc(1l);
    			orderTableDao.merge(ds);
    	    	ordertable.setDanhao(danhao[i]+","+dd.get(0).getDanhao());
        		ordertable.setZhongliang(Double.parseDouble(zhongliang[i])+dd.get(0).getZhongliang());
    	    	if("����".equals(guojia[i]) && 0<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<500){
    	    		if(dd.get(0).getDanhao() != null || !"".equals(dd.get(0).getDanhao())){
    	    		 yun = dd.get(0).getYunfei()+30d;	
    	    		 ordertable.setYunfei(yun);
    	    		}
    	    	}
    	    	if("����".equals(guojia[i]) && (500<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<2000)){
    	    		if(dd.get(0).getDanhao() != null || !"".equals(dd.get(0).getDanhao())){
       	    		 yun = dd.get(0).getYunfei()+35d;	
       	    		 ordertable.setYunfei(yun);
       	    		}
    	    	}else  if(!"����".equals(guojia[i])){
    	    		if(dd.get(0).getDanhao() != null || !"".equals(dd.get(0).getDanhao())){
    	    			yun =dd.get(0).getYunfei()+((0.08*Double.parseDouble(zhongliang[i])))+7+5;
    	    			 ordertable.setYunfei(yun);
    	    		}
    	    	}
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	str[i] =i + ".�����ɹ� ";
    	    	String gg="";
        		if(ordertable.getDggjdh()==null){
        			gg = "";
        		}else{
        			gg=ordertable.getDggjdh();
        		}
        		String k = "����"+ds.getOrderId()+","+gg+"";
        		ordertable.setDggjdh(k);
    	    	orderDao.merge(ordertable);
        	}
    		}
        	else if(Integer.parseInt(type[i]) == 1 && (dan.get(0).getDanhao() != null && !"".equals(dan.get(0).getDanhao()))){
        	
        		ordertable = orderDao.get(ordertable.getId());
        		OrderTable ds = new OrderTable();
    			Long s=System.currentTimeMillis();
    			ds.setOrderId(s.toString());
    			ds.setDanhao(danhao[i]);
    			ds.setKuaidifangshiId(ordertable.getKuaidifangshiId());
    			ds.setSjc(1l);
    			orderTableDao.merge(ds);
    	    	ordertable.setDanhao(danhao[i]+","+dan.get(0).getDanhao());
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i])+dan.get(0).getZhongliang());
    	    	ordertable.setYunfei(Double.parseDouble(yunfei[i])+dan.get(0).getYunfei());
    	    	if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - ordertable.getYunfei();
        			YunCun bb = new YunCun();
        			bb.setMoney(u-Double.parseDouble(yunfei[i]));
        			bb.setSymoney(Double.parseDouble(yunfei[i]));
        			bb.setGukeId(yy.get(0).getGukeId());
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	String gg="";
        		if(ordertable.getDggjdh()==null){
        			gg = "";
        		}else{
        			gg=ordertable.getDggjdh();
        		}
        		String k = "����"+ds.getOrderId()+","+gg+"";
        		ordertable.setDggjdh(k);
    	    	str[i] =i + ".�����ɹ� ";
    	    	orderDao.merge(ordertable);
        	}
        	else if(Integer.parseInt(type[i]) == 1 && (dan.get(0).getDanhao() == null || "".equals(dan.get(0).getDanhao()))){
     
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	ordertable.setDanhao(danhao[i]);
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
    	    	ordertable.setYunfei(Double.parseDouble(yunfei[i]));
    	    	ordertable.setDggjdh(ordertable.getOrderId());
    	    	if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - ordertable.getYunfei();
        			YunCun bb = new YunCun();
        			bb.setMoney(u-Double.parseDouble(yunfei[i]));
        			bb.setSymoney(Double.parseDouble(yunfei[i]));
        			bb.setGukeId(yy.get(0).getGukeId());
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	str[i] =i + ".�����ɹ� ";
    	    	orderDao.merge(ordertable);
        	}
        	else if(Integer.parseInt(type[i]) == 2 && ((dan.get(0).getDanhao() == null || "".equals(dan.get(0).getDanhao())))){
        	
        		Double yun = 0d;
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	ordertable.setDanhao(danhao[i]);
    	    	ordertable.setZhongliang(Double.parseDouble(zhongliang[i]));
    	    	ordertable.setDggjdh(ordertable.getOrderId());
    	    	if("����".equals(guojia[i]) && 0<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<500){
    	    		 yun = 30d;	
    	    		
    	    		 ordertable.setYunfei(yun);
    	    	}
    	    	if("����".equals(guojia[i]) && (500<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<2000)){
    	    		 yun = 35d;
    	    	
    	    		 ordertable.setYunfei(yun);
    	    	}else if(!"����".equals(guojia[i])){
    	    		 yun = (0.08*Double.parseDouble(zhongliang[i]))+7+5;
    	    		
    	    		 ordertable.setYunfei(yun);
    	    	}
        		if(yy.size() !=0){
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
        		
    	    	str[i] =i + ".�����ɹ� ";
    	    	orderDao.merge(ordertable);
        	}
        	else if(Integer.parseInt(type[i]) == 2 && ((dan.get(0).getDanhao() != null || !"".equals(dan.get(0).getDanhao())))){
        		Double yun = 0d;
    	    	ordertable = orderDao.get(ordertable.getId());
    	    	OrderTable ds = new OrderTable();
    			Long s=System.currentTimeMillis();
    			ds.setOrderId(s.toString());
    			ds.setDanhao(danhao[i]);
    			ds.setKuaidifangshiId(ordertable.getKuaidifangshiId());
    			ds.setSjc(1l);
    			orderTableDao.merge(ds);
    	    	ordertable.setDanhao(danhao[i]+","+dan.get(0).getDanhao());
        		ordertable.setZhongliang(Double.parseDouble(zhongliang[i])+dan.get(0).getZhongliang());
    	    	if("����".equals(guojia[i]) && 0<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<500){
    	    	if(dan.get(0).getDanhao() != null || !"".equals(dan.get(0).getDanhao())){
    	    		
    	    			 yun = dan.get(0).getYunfei()+30d;
    	    		
    	    			 ordertable.setYunfei(yun);
    	    		}
    	    	}
    	    	if("����".equals(guojia[i]) && (500<Double.parseDouble(zhongliang[i])&& Double.parseDouble(zhongliang[i])<2000)){
    	    		if(dan.get(0).getDanhao() != null || !"".equals(dan.get(0).getDanhao())){
       	    		 yun = dan.get(0).getYunfei()+35d;
       	    		
       	    		 ordertable.setYunfei(yun);
       	    		}	
    	    	}else if(!"����".equals(guojia[i])){
    	    		if(dan.get(0).getDanhao() != null || !"".equals(dan.get(0).getDanhao())){
    	    			yun =dan.get(0).getYunfei()+((0.08*Double.parseDouble(zhongliang[i])))+7+5;
    	    		
    	    			 ordertable.setYunfei(yun);
    	    		}
    	    	}
        		if(yy.size() !=0){
        			
        			Double u = yy.get(0).getMoney();
            		Double aa = u - yun;
        			YunCun bb = new YunCun();
        			bb.setMoney(u-yun);
        			bb.setSymoney(yun);
        			bb.setGukeId(yy.get(0).getGukeId());
        			
        			 java.util.Date d = new java.util.Date();
    	             SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	             String ff = f.format(d);
        			bb.setSytime(ff);
        			if(aa<0){
        				msg ="���㡢����"+(-aa)+"�������ɹ�";
        			}else{
        			 msg = "�����ɹ�";
        			}
                     yuCunDao.merge(bb);
        			
        		}else{
        			msg ="���ѹ˿͸��˷ѡ������ɹ� ";
        		}
    	    	str[i] =i + ".�����ɹ� ";
    	    	String gg="";
        		if(ordertable.getDggjdh()==null){
        			gg = "";
        		}else{
        			gg=ordertable.getDggjdh();
        		}
        		String k = "����"+ds.getOrderId()+","+gg+"";
        		ordertable.setDggjdh(k);
    	    	orderDao.merge(ordertable);
        	}
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ActionContext.getContext().put("insert", str);
    	return getDaiRuDan();
    }
    //�����޸����䵥�� 
    public String updanhao(){
    	return "updanhao";
    }
  //�����޸����䵥��
    public String updatedanhao(){
    	String danhao = ordertable.getDanhao();
    	ordertable = orderDao.get(ordertable.getId());
    	ordertable.setDanhao(danhao);
    	orderDao.merge(ordertable);
    	msg = "�޸ĳɹ� ";
    	return getDaiRuDan();
    }

    //�޸Ĵ������
    public String updateAllChuLi(){
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
                     if(ls.get(0).getChuli()==null || ls.get(0).getChuli()==0l){	 
                    	 ls.get(0).setChuli(1l);
                     }else{
                    	 ls.get(0).setChuli(0l);
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
         return getTuiHuoAll();
    }
    //����õ��޸Ķ���
    public String getDeDaoOrderAll()
    {
        int pageSize = 10;
        pageBean = pageBiz.selYeWuDeDaoOrder(pageSize, pageNumber, orderId, gongyunshang, selzhanghao, danhao);
        return "yewuallorder";
    }
    //�����޸Ķ���
    public String getorderIdAll()
    {
        return "updateorders";
    }
    //ҵ���޸Ķ���
    public String updatedingdanAll()
    {
    
         String order = ordertable.getOrderId();
         String yunshu = ordertable.getYunshu();
         Double yunfei = ordertable.getYunfei();
         String guojia = ordertable.getGuojia();
         String remark = ordertable.getRemark();
         Double huilv = ordertable.getHuilv();
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
         String gongyunshang = ordertable.getGongyunshang();
         java.sql.Date time = ordertable.getJiufentime();
         ordertable = (OrderTable)orderDao.get(ordertable.getId());
      
         ordertable.setOrderId(order);
         ordertable.setYunshu(yunshu);
         ordertable.setYunfei(yunfei);
         ordertable.setGuojia(guojia);
         ordertable.setRemark(remark);
         ordertable.setHuilv(huilv);
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
         ordertable.setGongyunshang(gongyunshang);
         if(jiufen ==1 && (time == null || "".equals(time))){
        	 ordertable.setJiufentime(new java.sql.Date(System.currentTimeMillis()));
         }else if(jiufen ==1 && (time != null && !"".equals(time))){
        	    ordertable.setJiufentime(time);
         }
     
         ordertable.setXiugai(1l);
         orderDao.merge(ordertable);
         msg = "�޸ĳɹ�";
        return getDeDaoOrderAll();
    }
    //�鿴ȫ��Ҫ�����
    public String getPayMent(){
    	int pageSize = 10;
    	pageBean = pageBiz.selgetPayment(pageSize, pageNumber);
    	return "getPayMent";
    }
    public String updedaos()
    {
     
        String ch[] = request.getParameter("bulletinId").split("-");
        String str[] = new String[ch.length];
        for(int i = 0; i < ch.length; i++)
        {
            List<OrderTable> ls = orderDao.getSelId(Long.valueOf(Long.parseLong(ch[i])));
            if(ls.size() != 0)
              
                    ls.get(0).setId(Long.parseLong(ch[i]));
               
                    ls.get(0).setDaifahuo(1l);
                    ls.get(0).setWancheng(0l);
                    ls.get(0).setFenpei(1l);
                    orderDao.merge(ls.get(0));
                    str[i] = i +".�����ɹ���";
        }

        ActionContext.getContext().put("str", str);
        return getPayMent();
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
        return getPayMent();
    }
    //��������
    public String daifa(){
    	try
        {
            String[] ch = request.getParameter("bulletinId").split("-");
            String[] str = new String[ch.length];
            for(int i = 0; i < ch.length; i++)
            {
                List<OrderTable> ls = orderDao.getSelId(Long.parseLong(ch[i]));
                if(ls.size() != 0)
                {
                	System.out.println("+��������+");
                    ls.get(0).setId(Long.parseLong(ch[i]));
                    ls.get(0).setDaifahuo(4l);
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
    	return getPayMent();
    }
    public void setServletRequest(HttpServletRequest arg0)
    {
        request = arg0;
    }


}