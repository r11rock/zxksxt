package com.demo.dao;

import org.springframework.transaction.annotation.Transactional;

public interface BaseDao<T, K> { 
	/**
	 * �¼ӻ���±���
	 * @param t
	 * @return
	 */
	@Transactional
	public T merge(T t);
	@Transactional
	public void delete(T id);
	/**
	 * ������������
	 * @param id
	 * @return
	 */
	public T get(K id);
}
