package com.demo.bean;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.demo.list.PageData;


public class MyHibernateTemplate extends HibernateTemplate {
	/**
	 * ���ز�ѯ����ĵ�һ�����󣬲�����ʱ���ؿ�
	 * @param hql
	 * @param params
	 * @return
	 */
	public <M> M findFirst(String hql, Object[] params){
		List<M> ls = find(hql, params);
		if(ls.isEmpty())return null;
		return (M)ls.get(0);
	}
	/**
	 * ���ز�ѯ���еĶ��󣬲�����ʱ���ؿ�
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <M> M findAll(String hql, Object[] params) {
		List<M> ls = this.find(hql, params);
		if (ls.isEmpty())
			return null;
		return (M) ls;
	}
	/**
	 * ��ҳ��ѯ
	 */
	@SuppressWarnings("unchecked")
	public static PageData findSimplePage(final String hql,
			final Object[] params, int page, int pagesize, Session s) {
		int start = (page - 1) * pagesize;
		List currentPageData = find(hql, params, start, pagesize, s);
		// �ܼ�¼��:
		// hql: from xxxx -> totalhql: ֱ��ƴ��select count(*)
		// hql: select xxxx from xxx -> ��ȡ��fromλ�õ��Ӵ���ǰ�����select count(*)
		// select distinct a from Order a left join fetch a.items b where b.name
		// = ?
		String thql = "";
		if (hql.trim().toLowerCase().startsWith("from")) {
			thql = "select count(*) " + hql;
		} else {
			int fromindex = hql.toLowerCase().indexOf("from");
			String str = hql.substring(fromindex);
			thql = "select count(*) " + str;
		}
		Object obj = findFirst(thql, params, s);
		int total = Integer.parseInt(obj + "");
		return new PageData(currentPageData, total, pagesize, page);
	}
	/**
	 * ��ѯ��Χ
	 */
	@SuppressWarnings("unchecked")
	public static List find(String hql, Object[] params, int start, int limit,
			Session s) {
		Query query = s.createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	/**
	 * ��ѯ���ص�һ������
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findFirst(String hql, Object[] params, Session s) {
		List ls = find(hql, params, s);
		if (ls.isEmpty())
			return null;
		return (T) ls.get(0);
	}
	/**
	 * ��ѯ
	 */
	@SuppressWarnings("unchecked")
	public static List find(String hql, Object[] params, Session s) {
		Query query = s.createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();

	}
}
