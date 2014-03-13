package com.demo.dao.Impl;

import com.demo.dao.OrderDao;
import com.demo.entity.Express.YunFeiTable;
import com.demo.entity.order.OrderTable;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<OrderTable,Long> implements OrderDao
{

    public OrderDaoImpl()
    {
    	super(OrderTable.class);
    }
    //����Ա���䶩��
    public String getAllFenPei(String orderId)
    {
    	String stu = null;
        if(orderId != null && !"".equals(orderId))
            stu = "from OrderTable where  (fenpei = 0 or fenpei is null) and orderId='"+orderId+"' and sjc is null order by zhanghaoId desc";
        else
            stu = "from OrderTable where  (fenpei = 0 or fenpei is null) and sjc is null order by zhanghaoId desc";
        return stu;
    }
    //����Ա�鿴ȫ������
    public String getAllOrder(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu,Long chuli)
    {
    	
        String stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == null || "".equals(chuli)))
            {	
                stu = "from OrderTable where fenpei != 3 and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by zhanghaoId desc";
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == null || "".equals(chuli))){
            
                stu = "from OrderTable where fenpei != 3  and sjc is null and orderId = '"+orderId+"'";
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == null || "".equals(chuli))){
            
                stu = "from OrderTable a where fenpei != 3  and sjc is null and a.zhanghaoId = "+dhgatezhanghao+" order by zhanghaoId desc";
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&&(chuli == null || "".equals(chuli))){
            	
                stu = "from OrderTable a where fenpei != 3  and sjc is null and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+" order by zhanghaoId desc";
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == null || "".equals(chuli))){
            
                stu = "from OrderTable a where fenpei != 3  and sjc is null  and a.zhanghaoId ="+dhgatezhanghao+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  order by zhanghaoId desc";
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == null || "".equals(chuli))){
            
            	stu = "from OrderTable where fenpei != 3  and sjc is null and danhao='"+danhao+"' order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == null || "".equals(chuli))){
                stu = "from OrderTable where fenpei != 3  and sjc is null order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	stu="from OrderTable where fenpei != 3  and sjc is null and bianma = '"+bianma+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli)))
            {
            	
                stu = "from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and leimuid="+leimu+" order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and danhao = '"+danhao+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and bianma = '"+bianma+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null  and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null  and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"' order by zhanghaoId desc";
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and danhao='"+danhao+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and bianma='"+bianma+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3  and sjc is null and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3 and sjc is null and zhanghaoId = '"+dhgatezhanghao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }        
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	
            	stu="from OrderTable where fenpei != 3 and sjc is null and zhanghaoId = '"+dhgatezhanghao+"' and danhao='"+danhao+"' and leimuid = '"+leimu+"' order by zhanghaoId desc";
            }
        	else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == 0l)){
            	
        		stu = "from OrderTable where jiufen = 1 and sjc is null and (chuli = 0 or chuli is null)";
        	}
        	else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == 1l)){
        	
        		stu = "from OrderTable where jiufen = 1 and sjc is null and chuli = 1";
        	}
        	else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == 0l)){
            	
        		stu = "from OrderTable where (convert(varchar(10),time,120) between '"+time+"' and '"+time1+"')  and  jiufen = 1 and sjc is null and (chuli = 0 or chuli is null)";
        	}
        	else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&&(chuli == 1l)){
        	
        		stu = "from OrderTable where (convert(varchar(10),time,120) between '"+time+"' and '"+time1+"')  and  jiufen = 1 and sjc is null and chuli = 1";
        	}
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	stu="from OrderTable where fenpei != 3  and sjc is null and sumaitong=0 order by zhanghaoId desc";
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))&& (chuli == null || "".equals(chuli))){
            	stu="from OrderTable where fenpei != 3  and sjc is null and sumaitong=1 order by zhanghaoId desc";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴�ɹ���ɶ���
    public String getCaiGouAll(Long caigouyuan, String orderId, String time, String time1)
    {
    	String stu = null;
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = "from OrderTable where caigouyuan = "+caigouyuan+"  and wancheng = 1 and (guojia is null or guojia = '') and fenpei = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')";
            if(!"".equals(orderId) && orderId != null)
                stu = "from OrderTable where caigouyuan = "+caigouyuan+"  and wancheng = 1 and (guojia is null or guojia = '') and fenpei = 1 and orderId = '"+orderId+"'";         
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = "from OrderTable where caigouyuan = "+caigouyuan+"  and (guojia is null or guojia = '') and wancheng = 1 and fenpei = 1";
                  
        return stu;
    }
    //�ɹ��õ�����
    public String getDeOrder(Long userid, String orderId, String time, String time1,String zhanghaoId,Long leimu)
    {
    	String stu = null;
         if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)) && (zhanghaoId == null || "".equals(zhanghaoId)) && (leimu == null || "".equals(leimu)))
         {
             stu = "from OrderTable a where getordersId = 1 and a.caigouyuan = "+userid+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  order by jingji desc,id desc";
         }
         if(!"".equals(orderId) && orderId != null)
             stu = "from OrderTable a where getordersId = 1 and caigouyuan = "+userid+" and orderId = '"+orderId+"'  order by jingji desc,id desc";
         if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (zhanghaoId != null && !"".equals(zhanghaoId)) && (leimu == null || "".equals(leimu))){
        	 stu = "from OrderTable a where getordersId = 1 and caigouyuan = "+userid+" and a.zhanghaoId = "+zhanghaoId+"  order by jingji desc,id desc";
         }
         if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (zhanghaoId == null || "".equals(zhanghaoId)) && (leimu == null || "".equals(leimu)))
         {
             stu = "from OrderTable a where getordersId = 1 and a.caigouyuan = "+userid+" and datediff(day,time,'"+time+"')=0 order by jingji desc,id desc";
         }
         else
             if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (zhanghaoId != null && !"".equals(zhanghaoId)) && (leimu != null && !"".equals(leimu))){
            	 stu = "from OrderTable a where getordersId = 1 and caigouyuan = "+userid+" and a.zhanghaoId = "+zhanghaoId+" and  leimuid = "+leimu+"  order by jingji desc,id desc"; 
             }
         else
          if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (zhanghaoId == null || "".equals(zhanghaoId)) && (leimu != null && !"".equals(leimu)))
                 stu = "from OrderTable a where leimuid = "+leimu+" and getordersId=1 and caigouyuan = "+userid+" order by jingji desc,id desc";
         else
         if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (zhanghaoId == null || "".equals(zhanghaoId)) && (leimu == null || "".equals(leimu)))
             stu = "from OrderTable a where getordersId=1 and caigouyuan = "+userid+" order by jingji desc,id desc";
         return stu;
    }

    public OrderTable getDingDao(String dingdan)
    {
        return (OrderTable)ht.findAll("from OrderTable a where a.orderId = ? and sjc is null", new Object[] {
            dingdan
        });
    }

    public String getOrder(String orderId, String danhao, String xujia)
    {
    	String stu = null;
        if(!"".equals(orderId) && orderId != null && ("".equals(danhao) || danhao == null) && ("".equals(xujia) || xujia == null))
            stu = "from OrderTable where (fenpei != 3 or fenpei is null) and orderId = '"+orderId+"' and sjc is null order by time desc";
        else
        if(!"".equals(danhao) && danhao != null && ("".equals(orderId) || orderId == null) && ("".equals(xujia) || xujia == null))
            stu = "from OrderTable where (fenpei != 3 or fenpei is null) and danhao = '"+danhao+"' and sjc is null order by time desc";
               
        else
        if(!"".equals(xujia) && xujia != null && ("".equals(orderId) || orderId == null) && ("".equals(orderId) || orderId == null))
            stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and danhao like '%"+xujia+"%'";
        else
        if(("".equals(danhao) || danhao == null) && ("".equals(orderId) || orderId == null) && ("".equals(xujia) || xujia == null))
            stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null";
        return stu;
    }

    public List<OrderTable> getWanChengOrder(String orderId)
    {
    	List<OrderTable> stu = null;
        if(!"".equals(orderId) && orderId != null)
            stu = ht.find("from OrderTable a where a.wancheng = 1 and sjc is null and a.fenpei = 0 and orderId = ? order by time desc", new Object[] {
                orderId
            });
        else
            stu = ht.find("from OrderTable a where a.wancheng = 1 and sjc is null and a.fenpei = 0 order by time desc");
        return stu;
    }
    //ҵ��õ�ȫ���޸ĵĶ���
    public String getYeWuOrder(String orderId, String gongyunshang, Long selzhanghao, String danhao)
    {
        String stu = null;
        if(!"".equals(gongyunshang) && gongyunshang != null && ("".equals(orderId) || orderId == null) && ("".equals(selzhanghao) || selzhanghao == null) && ("".equals(danhao) || danhao == null))
        {
            stu = "from OrderTable a where fenpei != 3 and gongyunshang like '%"+gongyunshang+"%' order by time desc";
        } else
        if(!"".equals(orderId) && orderId != null && ("".equals(gongyunshang) || gongyunshang == null) && ("".equals(selzhanghao) || selzhanghao == null) && ("".equals(danhao) || danhao == null))
        {
            stu = "from OrderTable a where fenpei != 3 and orderId = '"+orderId+"' order by time desc";
        } else
        if(!"".equals(selzhanghao) && selzhanghao != null && ("".equals(orderId) || orderId == null) && ("".equals(gongyunshang) || gongyunshang == null) && ("".equals(danhao) || danhao == null))
        {
            stu = "from OrderTable a where fenpei != 3 and a.zhanghaoId = "+selzhanghao+" order by time desc";
        } else
        if(!"".equals(danhao) && danhao != null && ("".equals(selzhanghao) || selzhanghao == null) && ("".equals(orderId) || orderId == null) && ("".equals(gongyunshang) || gongyunshang == null))
        {
            stu = "from OrderTable a where fenpei != 3 and danhao = '"+danhao+"' order by time desc";
        } else
        if(("".equals(orderId) || orderId == null) && ("".equals(gongyunshang) || gongyunshang == null) && ("".equals(selzhanghao) || selzhanghao == null) && ("".equals(danhao) || danhao == null))
        {
            stu = "from OrderTable a where fenpei != 3 order by time desc";
        }
        return stu;
    }

    public List<OrderTable> getCaiGouDaoChuOrder(Long userid)
    {
        return ht.find("from OrderTable a where (denghuixin = 0 or denghuixin is null) and (sumaitong = 0 or sumaitong is null) and sjc is null and a.fenpei = 1 and (wancheng = 0 or wancheng is null) and (daifahuo = 0 or daifahuo is null) and caigouyuan = "+userid+" order by zhanghaoId");
    }
    //����Ա�鿴�ܽ��
    public List<OrderTable> getAllMoney(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
          try
          {
              if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
              {
              
                  stu = ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
              }
              if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
                  stu = ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3  and orderId = '"+orderId+"'");
              }
              if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                  stu = ht.find("select round(sum(money),3) from OrderTable a where sjc is null and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+"");
              }
              if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                  stu = ht.find("select round(sum(money),3) from OrderTable a where sjc is null and fenpei != 3 and a.zhanghaoId ="+dhgatezhanghao+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
              }
              if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
              	stu = ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and danhao='"+danhao+"'");
              }
              if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	  stu = ht.find("select round(sum(money),3) from OrderTable a where sjc is null and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                  stu = ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and bianma = '"+bianma+"'");
              }
              else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
              {
              	
                  stu = ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and leimuid="+leimu+"");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3  and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where  sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3  and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3  and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')  and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
              }
              else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
              	
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and sumaitong=0");
              }
              else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
              	stu=ht.find("select round(sum(money),3) from OrderTable where sjc is null and fenpei != 3 and sumaitong=1");
              }
              
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          return stu;
    }
    //����Ա�鿴���˷�
    public List<OrderTable> getAllYunFei(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(yunfei),3) from OrderTable a where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3  and a.zhanghaoId = "+dhgatezhanghao+"");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(yunfei),3) from OrderTable a where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3  and a.zhanghaoId = "+dhgatezhanghao+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	stu = ht.find("select round(sum(yunfei),3) from OrderTable a where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and fenpei != 3 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3  and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null  and fenpei != 3  and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3 and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and  fenpei != 3 and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3 and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3  and zhanghaoId = "+dhgatezhanghao+" and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and  fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(yunfei),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴�ܻ���
    public List<OrderTable> getAllHuoKuan(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and fenpei != 3 and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(huokuan),3) from OrderTable a where (jiufen=0 or jiufen is null) and sjc is null  and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+"");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(huokuan),3) from OrderTable a where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3  and a.zhanghaoId = "+dhgatezhanghao+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3  and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu = ht.find("select round(sum(huokuan),3) from OrderTable a where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {	
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null)  and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null)  and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3 and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and  fenpei != 3  and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and  fenpei != 3 and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null  and  fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where (jiufen=0 or jiufen is null) and sjc is null and fenpei != 3 and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(huokuan),3) from OrderTable where  (jiufen=0 or jiufen is null) and sjc is null  and fenpei != 3 and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴���˿�
    public List<OrderTable> getAllTuiKuan(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable a where sjc is null and  fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+"");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable a where sjc is null and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable a where sjc is null and  fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and  fenpei != 3 and danhao='"+danhao+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3  and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3  and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and  sjc is null and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = "+dhgatezhanghao+" and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3 and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and fenpei != 3  and zhanghaoId = "+dhgatezhanghao+" and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where sjc is null and  fenpei != 3  and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(tuikuan),3) from OrderTable where  sjc is null and  fenpei != 3 and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴���׸���
    public List<OrderTable> getJiuFenNum(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') ");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where jiufen = 1 and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+"");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where jiufen = 1 and fenpei != 3 and a.zhanghaoId="+dhgatezhanghao+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where  jiufen = 1 and fenpei != 3 and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1  and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  jiufen = 1 and fenpei != 3 and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and  fenpei != 3 and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3  and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1 and fenpei != 3 and zhanghaoId = "+dhgatezhanghao+" and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1  and fenpei != 3 and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where jiufen = 1  and fenpei != 3 and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getJiuFen(String orderId, String time, String time1,Long selcaigouyuan,String leimus)
    {
    
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)) && (selcaigouyuan==0 || "".equals(selcaigouyuan))&&(leimus == null || "".equals(leimus))){
            	
                stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and sjc is null and jiufen = 1 and (convert(varchar(10),jiufentime,120) between '"+time+"'and '"+time1+"')  order by zhanghaoid desc,jiufentime");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (selcaigouyuan==null || selcaigouyuan == 0l)&&(leimus != null && !"".equals(leimus))){
            	  stu = ht.find("from OrderTable  where leimuid='"+leimus+"' and fenpei = 1 and wancheng = 1 and sjc is null and jiufen = 1 order by zhanghaoid desc,jiufentime");
            }
            	else
                if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (selcaigouyuan==null || selcaigouyuan == 0l)&&(leimus == null || "".equals(leimus))){
                    stu = ht.find("from OrderTable  where fenpei = 1 and wancheng = 1 and sjc is null and jiufen = 1 order by zhanghaoid desc,jiufentime");
                }
                else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&(leimus == null || "".equals(leimus)) && (selcaigouyuan!=0)){
            	
            	stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and sjc is null and jiufen = 1 and caigouyuan = "+selcaigouyuan+" order by zhanghaoid desc,jiufentime");
                }
                else if((time != null && !"".equals(time)) && (time1 != null || !"".equals(time1)) && (orderId == null || "".equals(orderId))&&(leimus == null || "".equals(leimus)) && (selcaigouyuan!=0)){
                	
                	stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1  and sjc is null and jiufen = 1 and caigouyuan = "+selcaigouyuan+" and (convert(varchar(10),jiufentime,120) between '"+time+"'and '"+time1+"')  order by zhanghaoid desc,jiufentime");
                 }
                else if(!"".equals(orderId) && orderId != null){
            	
                stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and sjc is null and jiufen = 1 and orderId = ? order by zhanghaoid desc,jiufentime", new Object[] {
                    orderId
                });
            }
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�鿴���䵥��
    public String getDanHao(String orderId, String time, String time1)
    {
    	 String stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and id not in (select id from OrderTable where  danhao != '' and danhao is not null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')";
            if(!"".equals(orderId) && orderId != null)
                stu = "from OrderTable where (fenpei != 3 or fenpei is null)  and sjc is null and id not in (select id from OrderTable where  danhao != '' and danhao is not null) and orderId = '"+orderId+"'";
                    
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and id not in (select id from OrderTable where  danhao != '' and danhao is not null)";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //���䵥��Ϊ��
    public List<OrderTable> getDanHaoYunShuNull(){
    	return ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and id not in (select id from OrderTable where  danhao != '' and danhao is not null)");
    }
    public List<OrderTable> getAllOrderId(String orderId)
    {
        return ht.find("from OrderTable where orderId = ? and sjc is null", new Object[] {
            orderId
        });
    }
    //����Ա�鿴����Ϊ��
    public List<OrderTable> getHuoKuanNullNum(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select count(*) from OrderTable where huokuan is null  and sjc is null and (fenpei != 3 or fenpei is null) and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"')");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu =  ht.find("select count(*) from OrderTable a where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null)");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where huokuan is null  and sjc is null and (fenpei != 3 or fenpei is null) and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null  and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where huokuan is null  and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where (fenpei != 3 or fenpei is null)  and sjc is null and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and zhanghaoId = '"+dhgatezhanghao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and zhanghaoId = '"+dhgatezhanghao+"' and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where huokuan is null and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where huokuan is null  and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴�˷�Ϊ��
    public List<OrderTable> getYunFeiNullNum(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"')");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable a where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null)");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null) and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and  (fenpei != 3 or fenpei is null) and time (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null  and (fenpei != 3 or fenpei is null) and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null) and zhanghaoId = '"+dhgatezhanghao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select count(*) from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and zhanghaoId = '"+dhgatezhanghao+"' and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where yunfei is null and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select count(*) from OrderTable where yunfei is null  and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getHuoKuanNull(String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and wancheng = 1  and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and huokaun is null");
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and wancheng = 1  and sjc is null and orderId = ? and huokuan is null", new Object[] {
                    orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and wancheng = 1 and sjc is null and huokuan is null");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�˷�Ϊ��
    public String getYunFeiNull(String orderId, String time, String time1)
    {
    	String stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and yunfei is null order by zhanghaoid ,time";
            if(!"".equals(orderId) && orderId != null)
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and orderId = '"+orderId+"' and yunfei is null order by zhanghaoid ,time";
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and yunfei is null order by zhanghaoid ,time";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�����˷�Ϊ�� 
    public List<OrderTable> getDaoChuYunFei(){
    	
        return ht.find("from OrderTable where  yunfei is null and sjc is null and (fenpei != 3 or fenpei is null)");
    }
    public List<OrderTable> getSelId(Long id)
    {
        return ht.find("from OrderTable a where a.id = ? and sjc is null order by guoneikuaidiId,guoneidanhao desc", new Object[] {
            id
        });
    }
    //�ɹ���ɶ���
    public String getDeDaoOrder(Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma)
    {
    	
        String stu = null;
        if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)) && ("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null)&&("".equals(bianma) || bianma == null)){
        	
            stu = "from OrderTable a where a.fenpei = 1 and sjc is null and wancheng = 1 and a.caigouyuan = "+userid+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by zhanghaoId";
        }
        if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (caigoutime != null && !"".equals(caigoutime)) && (caigoutime1 != null && !"".equals(caigoutime1)) && ("".equals(bianma) || bianma == null)){
        	
        	stu = "from OrderTable a where  fenpei = 1 and sjc is null and wancheng = 1 and a.caigouyuan = "+userid+" and caigoutime between '"+caigoutime+"'and '"+caigoutime1+"' order by zhanghaoId";
        }
        if(("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && orderId != null && !"".equals(orderId)&&("".equals(bianma) || bianma == null)){
        	
            stu = "from OrderTable a where a.fenpei = 1 and sjc is null and wancheng = 1 and  caigouyuan = "+userid+" and orderId = '"+orderId+"' order by zhanghaoId";

            
        }
        if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&(caigoutime == null || "".equals(caigoutime)) && (caigoutime1 == null || "".equals(caigoutime1)) && (bianma != null && !"".equals(bianma))){
        	
        	 stu = "from OrderTable a where a.fenpei = 1 and sjc is null and wancheng = 1 and caigouyuan = "+userid+" and bianma = '"+bianma+"' order by zhanghaoId"; 
        }
        if(("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null) && (time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(bianma) || bianma == null)){
        	
            stu = "from OrderTable a where a.fenpei = 1 and sjc is null and wancheng = 1  and  caigouyuan = "+userid+" and datediff(day,time,'"+time+"')=0 order by zhanghaoId";

        }
        else
        if((time == null || "".equals(time)) && (time1 == null  || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null)&&("".equals(bianma) || bianma == null)){
 
            stu = "from OrderTable a where a.fenpei = 1  and sjc is null and wancheng = 1 and caigouyuan = "+userid+" order by zhanghaoId";
        }
        return stu;
    }

    public List<OrderTable> getDengHuiXin(Long userid, String orderId, String time)
    {
    	List<OrderTable> stu = null;
        if((orderId == null || "".equals(orderId)) && !"".equals(time) && time != null)
            stu = ht.find("from OrderTable a where a.caigouyuan = ? and sjc is null and denghuixin = 1 and fenpei = 1 and time = '"+time+"' order by zhanghaoId", new Object[] {
                userid
            });
        if(!"".equals(orderId) && orderId != null)
            stu = ht.find("from OrderTable a where a.caigouyuan = ?  and sjc is null and denghuixin = 1 and fenpei = 1 and orderId = ? order by zhanghaoId", new Object[] {
                userid, orderId
            });
        else
        if((time == null || "".equals(time)) && (orderId == null || "".equals(orderId)))
            stu = ht.find("from OrderTable a where a.caigouyuan = ? and sjc is null and denghuixin = 1 and fenpei = 1 order by zhanghaoId", new Object[] {
                userid
            });
        return stu;
    }

    public List<OrderTable> getSuMaiTong(Long userid, String orderId, String time)
    {
    	List<OrderTable> stu = null;
        if((orderId == null || "".equals(orderId)) && !"".equals(time) && time != null)
            stu = ht.find("from OrderTable a where a.caigouyuan = ? and sjc is null and sumaitong = 1 and fenpei = 1 and time = '"+time+"' order by zhanghaoId", new Object[] {
                userid
            });
        if(!"".equals(orderId) && orderId != null)
            stu = ht.find("from OrderTable a where a.caigouyuan = ?  and sjc is null and sumaitong = 1 and fenpei = 1 and orderId = ? order by zhanghaoId", new Object[] {
                userid, orderId
            });
        else
        if((time == null || "".equals(time)) && (orderId == null || "".equals(orderId)))
            stu = ht.find("from OrderTable a where a.caigouyuan = ? and sjc is null and sumaitong = 1 and fenpei = 1 order by zhanghaoId", new Object[] {
                userid
            });
        return stu;
    }

    public List<OrderTable> getSelTiaoJiao(String shangwang, String qianshou, String ruzhang)
    {
    	List<OrderTable> stu = null;
        if(("0".equals(shangwang) || "1".equals(shangwang)) && qianshou == null && ruzhang == null)
            stu = ht.find("from OrderTable a where (fenpei != 3 or fenpei is null) and sjc is null and shangwang = ?", new Object[] {
               Long.parseLong(shangwang)
            });
        else
        if(("0".equals(qianshou) || "1".equals(qianshou)) && ruzhang == null && shangwang == null)
            stu = ht.find("from OrderTable a where (fenpei != 3 or fenpei is null) and sjc is null and qianshou = ?", new Object[] {
               Long.parseLong(qianshou)
            });
        else
        if(("0".equals(ruzhang) || "1".equals(ruzhang)) && shangwang == null && qianshou == null)
            stu = ht.find("from OrderTable a where (fenpei != 3 or fenpei is null) and sjc is null and ruzhang = ?", new Object[] {
            		Long.parseLong(ruzhang)
            });
        else
        if(("0".equals(shangwang) || "1".equals(shangwang)) && ("0".equals(qianshou) || "1".equals(qianshou)) && ruzhang == null)
            stu = ht.find("from OrderTable a where (fenpei != 3 or fenpei is null) and sjc is null and shangwang = ? and qianshou = ?", new Object[] {
            		Long.parseLong(shangwang), Long.parseLong(qianshou)
            });
        else
        if("0".equals(shangwang) || "1".equals(shangwang) && ("0".equals(qianshou) || "1".equals(qianshou)) && ("0".equals(ruzhang) || "1".equals(ruzhang)))
            stu = ht.find("from OrderTable a where (fenpei != 3 or fenpei is null) and sjc is null and shangwang = ? and qianshou =? and ruzhang = ? ", new Object[] {
            		Long.parseLong(shangwang), Long.parseLong(qianshou), Long.parseLong(ruzhang)
            });
        else
        if(("0".equals(qianshou) || "1".equals(qianshou)) && ("0".equals(ruzhang) || "1".equals(ruzhang)) && shangwang == null)
            stu = ht.find("from OrderTable  where (fenpei != 3 or fenpei is null) and sjc is null and qianshou =? and ruzhang = ?", new Object[] {
            		Long.parseLong(qianshou), Long.parseLong(ruzhang)
            });
        else
        if(("0".equals(shangwang) || "1".equals(shangwang)) && ("0".equals(ruzhang) || "1".equals(ruzhang)) && qianshou == null)
            stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and shangwang = ? and ruzhang =?", new Object[] {
            		Long.parseLong(shangwang), Long.parseLong(ruzhang)
            });
        return stu;
    }
    //�鿴�˻�����
    public String getTuiKuanAll(String orderId, String danhao,Long chuli)
    {
    	String stu = null;
        try
        {
            if((danhao != null && !"".equals(danhao)) && (orderId == null || "".equals(orderId)) && (chuli == null || "".equals(chuli))){
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and  tuihuo = 1 and sjc is null and danhao='"+danhao+"' order by time desc";
            }
            else if(!"".equals(orderId) && orderId != null){
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and  tuihuo = 1 and sjc is null and orderId = '"+orderId+"' order by time desc";
            }
            else if((danhao == null || "".equals(danhao)) && (orderId == null || "".equals(orderId)) && (chuli == null || "".equals(chuli))){
                stu = "from OrderTable where (fenpei != 3 or fenpei is null) and tuihuo = 1 and sjc is null order by time desc";
            }
            else if(danhao == null || "".equals(danhao) && (orderId == null || "".equals(orderId)) && (chuli == 0l)){
            	stu = "from OrderTable where (fenpei != 3 or fenpei is null) and tuihuo = 1 and sjc is null and (chuli = 0 or chuli is null)";
            }
            else if(danhao == null || "".equals(danhao) && (orderId == null || "".equals(orderId)) && (chuli == 1l)){
            	stu = "from OrderTable where (fenpei != 3 or fenpei is null) and tuihuo = 1 and sjc is null and chuli = 1";;
            }
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�鿴���ⶩ��
    public String getWenTiOrder(String orderId)
    {
        String stu = null;
        try
        {
            if(!"".equals(orderId) && orderId != null)
            {
              
                stu = "from OrderTable where fenpei = 2 and (wancheng = 0 or wancheng is null) and orderId = '"+orderId+"' order by zhanghaoId,time";
            } else
            {
                stu = "from OrderTable where fenpei = 2 and (wancheng = 0 or wancheng is null) order by zhanghaoId,time";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouHuoKuanNull(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and id not in (select id from OrderTable where  huokuan != '' and huokuan is not null) and caigouyuan = ?   and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by zhanghaoId desc", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and id not in (select id from OrderTable where  huokuan != '' and huokuan is not null) and caigouyuan = ?  and orderId = ? order by zhanghaoId desc", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("from OrderTable where (fenpei != 3 or fenpei is null) and sjc is null and id not in (select id from OrderTable where  huokuan != '' and huokuan is not null) and caigouyuan = ?  order by zhanghaoId desc", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�鿴�ɹ�δ��ɶ���
    public String getCaiGouWeiWanCheng(String orderId)
    {
    	String stu = null;
        if(orderId != null && !"".equals(orderId))
            stu = "from OrderTable a where (a.denghuixin = 0 or denghuixin is null) and sjc is null and (a.sumaitong = 0 or sumaitong is null) and a.fenpei = 1 and (wancheng = 0 or wancheng is null) and (daifahuo = 0 or daifahuo is null) and orderId = '"+orderId+"'";              
        else
            stu = "from OrderTable a where (a.denghuixin = 0 or denghuixin is null) and sjc is null and (a.sumaitong = 0 or sumaitong is null) and a.fenpei = 1 and (wancheng = 0 or wancheng is null) and (daifahuo = 0 or daifahuo is null)";
        return stu;
    }
    //�ɹ�����Ա��ɶ���
    public String getCaiGouAdminWanChengOrder(Long userid, String orderId, String time, String time1,String caigoutime,String caigoutime1,String bianma,String gongyunshang,String wuping)
    {
    	
        String stu = null;
        if((time == null || "".equals(time)) && (time1 == null  || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null)&&("".equals(bianma) || bianma == null)&&(gongyunshang != null && !"".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
        	stu = "from OrderTable a where wancheng = 1  and a.caigouyuan = "+userid+" and gongyunshang = '"+gongyunshang+"' order by zhanghaoId";
        }
        if((time == null || "".equals(time)) && (time1 == null  || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null)&&("".equals(bianma) || bianma == null)&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping != null && !"".equals(wuping))){
        	stu = "from OrderTable a where  wancheng = 1  and a.caigouyuan = "+userid+" and wuping like '%"+wuping+"%' order by zhanghaoId";
        }
        if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null)&&("".equals(bianma) || bianma == null)&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
        
            stu = "from OrderTable a where  wancheng = 1  and a.caigouyuan = "+userid+" and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by zhanghaoId";
        }
        if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (caigoutime != null && !"".equals(caigoutime)) && (caigoutime1 != null && !"".equals(caigoutime1)) && ("".equals(bianma) || bianma == null)&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
        	
        	stu = "from OrderTable a where wancheng = 1  and a.caigouyuan = "+userid+" and (convert(varchar(10),caigoutime,120) between '"+caigoutime+"'and '"+caigoutime1+"') order by zhanghaoId";
        }
        if(("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && orderId != null && !"".equals(orderId)&&("".equals(bianma) || bianma == null)&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
            stu = "from OrderTable a where wancheng = 1  and  caigouyuan = "+userid+" and orderId = '"+orderId+"' order by zhanghaoId";
        }
        if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&(caigoutime == null || "".equals(caigoutime)) && (caigoutime1 == null || "".equals(caigoutime1)) && (bianma != null && !"".equals(bianma))&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
        
        	 stu = "from OrderTable a where wancheng = 1 and caigouyuan = "+userid+" and bianma = '"+bianma+"' order by zhanghaoId"; 
        }
        else if((time != null && !"".equals(time))&&("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(bianma) || bianma == null)&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
        	
            stu = "from OrderTable a where wancheng = 1  and  caigouyuan = "+userid+" and datediff(day,time,'"+time+"')=0 order by zhanghaoId";
        }
        else if((time == null || "".equals(time)) && (time1 == null  || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(caigoutime) || caigoutime == null)&&("".equals(caigoutime1) || caigoutime1 == null)&&("".equals(bianma) || bianma == null)&&(gongyunshang == null || "".equals(gongyunshang))&&(wuping == null || "".equals(wuping))){
        	
            stu = "from OrderTable a where wancheng = 1  and caigouyuan = "+userid+" order by zhanghaoId";
        }
        return stu;
    }
    //�ܲɹ�����
    public List<OrderTable> getCaiGouAllHuoKuan(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(huokuan),3)  from OrderTable where caigouyuan = ? and sjc is null and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where caigouyuan = ? and sjc is null and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAllJiuFen(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select count(*) from OrderTable where caigouyuan = ? and  wancheng = 1 and sjc is null and fenpei = 1 and jiufen = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select count(*) from OrderTable where caigouyuan = ? and wancheng = 1 and sjc is null and fenpei = 1 and jiufen = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select count(*) from OrderTable where caigouyuan = ? and wancheng = 1 and sjc is null and fenpei = 1 and jiufen = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAllMoney(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3)  from OrderTable where caigouyuan = ? and sjc is null and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ? and sjc is null  and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //�ɹ��˿����
    public List<OrderTable> getCaiGouAllTuiKuan(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select count(*) from OrderTable where caigouyuan = ? and tuikuan = 1  and sjc is null and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select count(*) from OrderTable where caigouyuan = ? and tuikuan = 1  and sjc is null and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select count(*) from OrderTable where caigouyuan = ? and tuikuan = 1 and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //���˷�
    public List<OrderTable> getCaiGouAllYunFei(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(yunfei),3)  from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAdminAllHuoKuan(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(huokuan),3)  from OrderTable where caigouyuan = ?  and sjc is null  and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where caigouyuan = ?  and sjc is null  and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(huokuan),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAdminAllJiuFen(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select count(*)  from OrderTable where jiufen = 1 and wancheng = 1 and sjc is null and fenpei = 1 and caigouyuan = ? and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select count(*) from OrderTable where jiufen = 1 and wancheng = 1 and sjc is null and fenpei = 1 and caigouyuan = ? and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select count(*) from OrderTable where jiufen = 1 and wancheng = 1 and sjc is null and fenpei = 1 and caigouyuan = ?", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAdminAllMoney(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3)  from OrderTable where caigouyuan = ?  and sjc is null and (guojia is null or guojia = '') and (jiufen = 0 or jiufen is null) and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ?  and sjc is null and (guojia is null or guojia = '') and (jiufen = 0 or jiufen is null) and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3) from OrderTable where  caigouyuan = ?  and sjc is null and (guojia is null or guojia = '') and (jiufen = 0 or jiufen is null) and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getDaiFaHuoMoney(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3)  from OrderTable where caigouyuan = ? and sjc is null and daifahuo = 1 and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ? and sjc is null and daifahuo = 1 and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ? and sjc is null and daifahuo = 1 and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAdminAllTuiKuan(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(tuikuan),3)  from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouAdminAllYunFei(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(yunfei),3)  from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(yunfei),3) from OrderTable where caigouyuan = ?  and sjc is null and fenpei = 1 and wancheng = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴�����ܶ� 
    public List<OrderTable> getCaiGouAllJiuFenMoney(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select round(sum(money),3) from OrderTable where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select round(sum(money),3) from OrderTable where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable a where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"')");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable a where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select round(sum(money),3) from OrderTable where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable a where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null)");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1  and sjc is null and (fenpei != 3 or fenpei is null) and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is nulland (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1  and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and  (fenpei != 3 or fenpei is null) and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1  and sjc is null and (fenpei != 3 or fenpei is null) and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and zhanghaoId = '"+dhgatezhanghao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and zhanghaoId = '"+dhgatezhanghao+"' and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money),3) from OrderTable where  jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money),3) from OrderTable where   jiufen = 1 and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getCaiGouJiuFenMoney(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3)  from OrderTable where caigouyuan = ? and sjc is null and wancheng = 1  and jiufen = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ? and sjc is null and wancheng = 1  and jiufen = 1 and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(money),3) from OrderTable where caigouyuan = ? and sjc is null and wancheng = 1  and jiufen = 1", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //����Ա�鿴�˿��ܽ�� 
    public List<OrderTable> getCaiGouTuiKuan(Long userid, String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where tuikuan = 1 and sjc is null and fenpei = 1 and wancheng = 1 and caigouyuan = ? and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')", new Object[] {
                    userid
                });
            if(!"".equals(orderId) && orderId != null)
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where tuikuan = 1 and sjc is null and fenpei = 1 and wancheng = 1 and caigouyuan = ? and orderId = ?", new Object[] {
                    userid, orderId
                });
            else
            if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
                stu = ht.find("select round(sum(tuikuan),3) from OrderTable where tuikuan = 1 and sjc is null and fenpei = 1 and wancheng = 1 and caigouyuan = ?", new Object[] {
                    userid
                });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getOldDaoRu(Long userid)
    {
        return ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and caigouyuan = ? and sjc is null", new Object[] {
            userid
        });
    }

    public List<OrderTable> getOldUnDaoRu(Long userid)
    {
        return ht.find("from OrderTable where fenpei = 1 and (wancheng = 0 or wancheng is null) and caigouyuan = ? and sjc is null", new Object[] {
            userid
        });
    }
    //����С��0
    public List<OrderTable> getLiRun(String orderId, String time, String time1,Double tuikuan
    ,Double huokuan,Double yunfei,Double money,Double huilv)
    {
    	List<OrderTable> stu = null;
        if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
            stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and sjc is null and (("+money+"*"+huilv+")-("+huokuan+"+"+yunfei+"+"+tuikuan+"*"+huilv+"))<0 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by time desc");
        if(!"".equals(orderId) && orderId != null)
            stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and sjc is null and (("+money+"*"+huilv+")-("+huokuan+"+"+yunfei+"+"+tuikuan+"*"+huilv+"))<0 and orderId = ?", new Object[] {
                orderId
            });
        else
        if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
            stu = ht.find("from OrderTable where fenpei = 1 and wancheng = 1 and sjc is null and (("+money+"*"+huilv+")-("+huokuan+"+"+yunfei+"+"+tuikuan+"*"+huilv+"))<0 order by time desc");
        return stu;
    }

    public List<OrderTable> getLiRunInterval(String orderId, String time, String time1)
    {
    	List<OrderTable> stu = null;
        if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)))
            stu = ht.find("from OrderTable where (money*huilv-(huokuan+yunfei+tuikuan))>0 and (money*huilv-(huokuan+yunfei+tuikuan))<30 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and sjc is null order by time desc");
        if(!"".equals(orderId) && orderId != null)
            stu = ht.find("from OrderTable where (money*huilv-(huokuan+yunfei+tuikuan))>0 and (money*huilv-(huokuan+yunfei+tuikuan))<30 and orderId = ?  and sjc is null order by time desc", new Object[] {
                orderId
            });
        else
        if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)))
            stu = ht.find("from OrderTable where (money*huilv-(huokuan+yunfei+tuikuan))>0 and (money*huilv-(huokuan+yunfei+tuikuan))<30 and sjc is null order by time desc");
        return stu;
    }
    //����Ա�鿴������
    public List<OrderTable> getZongLiRun(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu)
    {
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable a where a.fenpei != 3 and sjc is null and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"')");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable a where a.fenpei != 3 and sjc is null and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where  fenpei != 3  and sjc is null and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable a where a.fenpei != 3 and sjc is null and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei !=3 and sjc is null and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and zhanghaoId = '"+dhgatezhanghao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }        
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and zhanghaoId = '"+dhgatezhanghao+"' and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where fenpei != 3 and sjc is null and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select  round(sum(money*huilv-(huokuan+yunfei+tuikuan)),3) from OrderTable where  fenpei != 3 and sjc is null and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }

    public List<OrderTable> getSelAllId(Long id)
    {
        return ht.find("from OrderTable  where id = ? and (wancheng = 0 or wancheng is null) and (daochu = 0 or daochu is null) and sjc is null order by guoneikuaidiId,guoneidanhao desc", new Object[] {
            id
        });
    }
//�õ�����ѯȫ��
    public List<OrderTable> getDaoChuAll(){
    	return ht.find("from OrderTable where daochu = 1 and sjc is null");
    }
    //���Ҳ�ѯ
    public List<YunFeiTable> getGuoJia(String guojia){
    	return ht.find("from YunFeiTable where guojia = ?",new Object[]{guojia}); 
    }
    //�����Ų�ѯ
    public List<OrderTable> getChaKanOrder(String orderId){
    	return ht.find("from OrderTable where orderId = ? and (fenpei != 3 or fenpei is null) and sjc is null",new Object[]{orderId});
    }
  //����˾�����Ų�ѯ
    public List<OrderTable> getChaKanKeHu(String orderId){
    	return ht.find("from OrderTable where orderId = ? and fenpei = 3 and sjc is null",new Object[]{orderId});
    }
    //����Ա�鿴����
    public List<OrderTable> getChaKanZero(String orderId,String time,String time1,String dhgatezhanghao,String danhao){
    	List<OrderTable> stu = null;
        try
        {
            if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null))
            {
                stu = ht.find("from OrderTable where fenpei = 1 and wancheng=1 and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by time desc");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && orderId != null && !"".equals(orderId)&&("".equals(danhao) || danhao == null))
                stu = ht.find("from OrderTable where fenpei = 1 and wancheng=1 and sjc is null and orderId = '"+orderId+"' order by time desc");
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null))
                stu = ht.find("from OrderTable a where fenpei = 1 and wancheng=1 and sjc is null and a.zhanghaoId in(select b.id from ZhangHao b where b.name = '"+dhgatezhanghao+"') order by time desc");
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null))
                stu = ht.find("from OrderTable a where fenpei = 1 and wancheng=1 and sjc is null and a.zhanghaoId in(select b.id from ZhangHao b where b.name = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by time desc");
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))){
            	
            	stu = ht.find("from OrderTable where fenpei = 1 and wancheng=1 and sjc is null and danhao='"+danhao+"' order by time desc");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null))
                stu = ht.find("from OrderTable where fenpei = 1 and wancheng=1 and sjc is null order by time desc");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
    //ҵ�񵼳������ͻ����˷�
    public List<OrderTable> getDaoChuKeHuYunFei(Long id,String time,String time1){
    	
    	List<OrderTable> stu = null;
    	if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1))){
    		
    		stu = ht.find("from OrderTable where wanchengtime='"+time+"' and sjc is null and (ruzhang = 0 or ruzhang is null) and wancheng = 1 and yunfeidaochu is null and fenpei = 3 and dengluId = ?",new Object[]{id});
    	}else if((time != null && !"".equals(time))&&(time1 != null && !"".equals(time1))){
    		
    		stu = ht.find("from OrderTable where (convert(varchar(10),wanchengtime,120) between '"+time+"'and '"+time1+"') and sjc is null and (ruzhang = 0 or ruzhang is null) and wancheng = 1 and yunfeidaochu is null and fenpei = 3 and dengluId = ?",new Object[]{id});
    	}
    	return stu;
    }
  //����Ա ���������ͻ����˷�
    public List<OrderTable> getAdminYunFei(Long id,String time){
    	return ht.find("from OrderTable where wanchengtime = '"+time+"' and (ruzhang = 0 or ruzhang is null)  and sjc is null and wancheng = 1 and fenpei = 3 and dengluId = ?",new Object[]{id});
    }
    //�ܵ�δ����
    public List<OrderTable> getWeiRuZhang(String orderId, String time, String time1, String dhgatezhanghao,String danhao,String sumaitong,String bianma,String leimu){
    	List<OrderTable> stu = null;
        try
        {
            if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            
                stu = ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId != null && !"".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
                stu = ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and orderId = '"+orderId+"'");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable a where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"')");
            }
            if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable a where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')");
            }
            if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu = ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and danhao='"+danhao+"'");
            }
            if((!"".equals(dhgatezhanghao) && dhgatezhanghao != null)&& (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
                stu =  ht.find("select round(sum(money),3) from OrderTable a where  (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and a.zhanghaoId = "+dhgatezhanghao+" and leimuid="+leimu+"");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong)||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
                stu = ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null  and (fenpei != 3 or fenpei is null)");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time))&&((time1==null) || "".equals(time1)) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null)&&(sumaitong==null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu)))
            {
            	
                stu = ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null  and (fenpei != 3 or fenpei is null) and leimuid="+leimu+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = "+dhgatezhanghao+"");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and  (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and  (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and leimuid = '"+leimu+"'");
            }
           
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where  (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and datediff(day,time,'"+time+"')=0 and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and danhao = '"+danhao+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu == null || "".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and bianma = '"+bianma+"'");
            }
            else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null||"".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') and zhanghaoId = '"+dhgatezhanghao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and  (fenpei != 3 or fenpei is null) and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and  (fenpei != 3 or fenpei is null) and danhao = '"+danhao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(sumaitong == null || "".equals(sumaitong))&&(!"".equals(bianma) && bianma != null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null)  and sjc is null and  (fenpei != 3 or fenpei is null) and zhanghaoId = '"+dhgatezhanghao+"' and bianma='"+bianma+"' and leimuid = '"+leimu+"'");
            }        
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao != null && !"".equals(dhgatezhanghao))&&(!"".equals(danhao) && danhao != null)&&(sumaitong == null || "".equals(sumaitong))&&("".equals(bianma) || bianma == null)&&(leimu != null && !"".equals(leimu))){
            	
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and  (fenpei != 3 or fenpei is null) and zhanghaoId = '"+dhgatezhanghao+"' and danhao='"+danhao+"' and leimuid = '"+leimu+"'");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 0)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=0");
            }
            else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null)&&(Long.parseLong(sumaitong) == 1)&&("".equals(bianma) || bianma == null)&&(leimu == null || "".equals(leimu))){
            	stu=ht.find("select round(sum(money),3) from OrderTable where (ruzhang = 0 or ruzhang is null) and sjc is null and (fenpei != 3 or fenpei is null) and sumaitong=1");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return stu;
    }
  //��ѯȫ��δ����
    public String getAllWeiRuZhang(String orderId, String time, String time1, String dhgatezhanghao,String danhao){
    	 String stu = null;
         try
         {
             if((time != null || !"".equals(time)) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId)) && ("".equals(dhgatezhanghao) || dhgatezhanghao == null)&&("".equals(danhao) || danhao == null))
             {
                 stu = "from OrderTable where (fenpei != 3 or fenpei is null) and (ruzhang = 0 or ruzhang is null) and sjc is null and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by zhanghaoId desc";
             }
             if(("".equals(dhgatezhanghao) || dhgatezhanghao == null) && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && orderId != null && !"".equals(orderId)&&("".equals(danhao) || danhao == null))
                 stu = "from OrderTable where (fenpei != 3 or fenpei is null) and (ruzhang = 0 or ruzhang is null) and sjc is null and orderId = '"+orderId+"' order by zhanghaoId desc";
             if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && (time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null))
                 stu = "from OrderTable a where (fenpei != 3 or fenpei is null) and (ruzhang = 0 or ruzhang is null) and sjc is null and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') order by zhanghaoId desc";
             if(!"".equals(dhgatezhanghao) && dhgatezhanghao != null && time != null && !"".equals(time) && time1 != null && !"".equals(time1) && (orderId == null || "".equals(orderId))&&("".equals(danhao) || danhao == null))
                 stu = "from OrderTable a where (fenpei != 3 or fenpei is null) and (ruzhang = 0 or ruzhang is null) and sjc is null and a.zhanghaoId in(select b.id from ZhangHao b where b.id = '"+dhgatezhanghao+"') and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"') order by zhanghaoId desc";
             if((danhao != null && !"".equals(danhao)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&(time == null || "".equals(time)) && (time1 == null || "".equals(time1))){
             	
             	stu = "from OrderTable where (fenpei != 3 or fenpei is null) and (ruzhang = 0 or ruzhang is null) and sjc is null and danhao='"+danhao+"' order by zhanghaoId desc";
             }
             else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId)) && (dhgatezhanghao == null || "".equals(dhgatezhanghao))&&("".equals(danhao) || danhao == null))
                 stu = "from OrderTable where (fenpei != 3 or fenpei is null) and (ruzhang = 0 or ruzhang is null) and sjc is null order by zhanghaoId desc";
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
         return stu;
    }
    //���Ų鿴ȫ��
    public List<OrderTable> getAllDanHao(String danhao,String orderId){
    	
    	List<OrderTable> stu = ht.find("from OrderTable where orderId = ? and sjc is null and danhao = ?",new Object[]{danhao,orderId});

    	return stu;
    }
    //�ɹ�ɸѡ��ѯ
    public String getFilter(Long caigouyuan, String orderId, String time, String time1){
     	String stu = null;
        if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId))&&(caigouyuan == null || "".equals(caigouyuan)))
            stu = "from OrderTable where wancheng = 1 and (guojia is null or guojia = '') and fenpei = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')";
        if(!"".equals(orderId) && orderId != null)
            stu = "from OrderTable where  wancheng = 1 and (guojia is null or guojia = '') and fenpei = 1 and orderId = '"+orderId+"'";
        else if((time != null && !"".equals(time)) && (time1 != null && !"".equals(time1)) && (orderId == null || "".equals(orderId))&&(caigouyuan != null && !"".equals(caigouyuan))){
        	stu = "from OrderTable where caigouyuan = "+caigouyuan+"  and wancheng = 1 and (guojia is null or guojia = '') and fenpei = 1 and (convert(varchar(10),time,120) between '"+time+"'and '"+time1+"')";
        }
        else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&(caigouyuan != null && !"".equals(caigouyuan))){
        	stu = "from OrderTable where caigouyuan = "+caigouyuan+"  and wancheng = 1 and (guojia is null or guojia = '') and fenpei = 1";
        }
        else if((time == null || "".equals(time)) && (time1 == null || "".equals(time1)) && (orderId == null || "".equals(orderId))&&(caigouyuan == null || "".equals(caigouyuan)))
            stu = "from OrderTable where (guojia is null or guojia = '') and wancheng = 1 and fenpei = 1";
              
        return stu;
    }
}
