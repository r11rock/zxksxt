package com.demo.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.dao.YuCunDao;
import com.demo.entity.YunCun;
import com.demo.entity.order.OrderTable;

@Repository
public class YuCunDaoImpl extends BaseDaoImpl<YunCun, Long> implements YuCunDao{
	public YuCunDaoImpl() {
		super(YunCun.class);
	}
	//�˿ͱ�Ų�ѯ
	public List<YunCun> getGuKeId(Long gukeid){
		return ht.find("from YunCun where gukeId = ?",new Object[]{gukeid});
	}
	//��ѯȫ��Ԥ��
	public String getYuCun(String time,String time1){
		String stu = null;
		if((time != null && !"".equals(time))&&(time1 != null && !"".equals(time1))){
            stu = "from YunCun a where  time between '"+time+"'and '"+time1+"'";
		}else{
			stu = "from YunCun a";
		}
		return stu;
	}
	//�˿ͱ�Ų�ѯ���һ������
	public List<YunCun> getLastNum(Long gukeid){
		return ht.find("from YunCun where gukeId = ? order by id desc",new Object[]{gukeid});
	}
	//����ѯ �ÿͻ�userid��ѯ���һ������
	public List<YunCun> getUserLastNum(Long gukeid){
		return ht.find("from YunCun where gukeId in(select id from GuKeTable where userid = "+gukeid+") order by id desc");
	}
	//�鿴�˷�����
	public String getXqYuCun(Long userid){
		String hql = "from YunCun where gukeId = "+userid+"";
		return hql;
	}
}
