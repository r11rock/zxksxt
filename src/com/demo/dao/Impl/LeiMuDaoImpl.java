
package com.demo.dao.Impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.dao.LeiMuDao;
import com.demo.entity.LeiMuTable;
import com.demo.entity.order.OrderTable;


@Repository
public class LeiMuDaoImpl extends BaseDaoImpl<LeiMuTable,Long>
    implements LeiMuDao
{

    public LeiMuDaoImpl()
    {
        super(LeiMuTable.class);
    }
    //��ѯȫ����Ŀ
    public List<LeiMuTable> getAllLeiMu(){
    	return ht.find("from LeiMuTable");
    }
  //�ñ�Ų�ѯȫ��
    public  List<LeiMuTable> getSelId(Long id){
    	return ht.find("from LeiMuTable a where a.id = ?", new Object[] {
                id
            });
    }
  //�òɹ���Ų�ѯ��Ŀ
    public LeiMuTable getLeiMuUser(Long userid){
    	return ht.findFirst("from LeiMuTable a where id = ?",new Object[]{userid});
    }
  //����Ŀ���Ʋ�ѯȫ��
    public List<LeiMuTable> getLmName(String name){
    
    	return ht.find("from LeiMuTable where leimu like '%"+name+"%'");
    }
    //����Ŀ���Ʋ�ѯȫ��
    public List<LeiMuTable> getAllName(String name){
    	return ht.find("from LeiMuTable where leimu like '%"+name+"%'");
    }
  //������ĿId��ѯ
	public LeiMuTable getByCateId(String cateId) {
		return ht.findFirst("from LeiMuTable where cateId = ?", new Object[]{cateId});
	}
}
