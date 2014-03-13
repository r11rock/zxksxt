
package com.demo.dao.Impl.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.bean.MyHibernateTemplate;
import com.demo.dao.Impl.BaseDaoImpl;
import com.demo.dao.user.GuKeDao;
import com.demo.entity.user.GuKeTable;


@Repository
public class GuKeDaoImpl extends BaseDaoImpl<GuKeTable,Long>
    implements GuKeDao
{

    public GuKeDaoImpl()
    {
        super(GuKeTable.class);
    }

    public GuKeTable getByUserId(Long userId)
    {
        return (GuKeTable)ht.findFirst("from GuKeTable a where a.userid = ?", new Object[] {
            userId
        });
    }
    //��ѯȫ���˿�
    public List<GuKeTable> getGuKeAll(){
    	
    	return ht.find("from GuKeTable");
    	 
    }
  //��Ų�ѯȫ���˿� 
    public GuKeTable getSelAllGuKe(Long userid){
    	return ht.findFirst("from GuKeTable where userid = ?",new Object[]{userid});
    }
    //�������鿴ȫ��
    public  String getGuKeName(String name){
      String stu = null;
      if(name != null && !"".equals(name)){
    	  stu = "from GuKeTable where name = '"+name+"'";
      }else{
    	  stu = "from GuKeTable";
      }
      return stu;
    }
}
