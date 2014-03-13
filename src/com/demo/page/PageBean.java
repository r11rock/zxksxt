package com.demo.page;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.demo.dao.PageDao;
import com.demo.entity.order.OrderTable;

public class PageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Ҫ���ص�ĳһҳ�ļ�¼�б�
	@SuppressWarnings("unchecked")
	private List list;
//	�ܼ�¼��
	private int allRow;
	//��ҳ��
	private int allPage;
	//��ǰҳ
	private int currentPage;
	//ÿҳ��¼��
	private int pageSize;
//	�Ƿ�Ϊ��һҳ
	@SuppressWarnings("unused")
	private boolean isFirstPage;
	//�Ƿ�Ϊ���һҳ
	@SuppressWarnings("unused")
	private boolean isLastPage;
	//�Ƿ���ǰһҳ
	@SuppressWarnings("unused")
	private boolean hasUpPage;
	//�Ƿ�����һҳ
	 @Resource
	 private PageDao pageDao;
	@SuppressWarnings("unused")
	private boolean hasNextPage;

	public PageBean() {
		
	}
	
	public PageBean(PageDao pageDao) {
		this.pageDao = pageDao;
	}
	
	/*
	 * ��ʼ����ҳ��Ϣ
	 */
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasUpPage = isHasUpPage();
		this.hasNextPage = isHasNextPage();
	}

	////��һҳ
	public boolean isHasNextPage() {
		return currentPage != allPage;
	}

	//��һҳ
	public boolean isHasUpPage() {
		return currentPage != 1;
	}

	//��һҳ
	public boolean isFirstPage() {
		return (currentPage == 1);
	}

	//���һҳ
	public boolean isLastPage() {
		return currentPage == allPage;
	}

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getAllRow() {
		return allRow;
	}

	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * ������ҳ������̬�������ⲿֱ��ͨ����������
	 * @param pageSize ÿҳ��¼��
	 * @param allRow �ܼ�¼��
	 * @return ��ҳ��
	 */
	public static int countPage(final int pageSize, final int allRow) {
		int allPage = allRow % pageSize == 0 ? allRow / pageSize : allRow/ pageSize + 1;
		return allPage;
	}
	/**
	 * ���㵱ǰҳ��ʼ��¼
	 * @param pageSize ÿҳ��¼��
	 * @param currentPage ��ǰ�ڼ�ҳ
	 * @return ��ǰҳ��ʼ��¼��
	 */
	public static int countStart(final int pageSize, final int currentPage) {
		final int countstart = pageSize * (currentPage - 1);
		return countstart;
	}


	/**
	 * ���㵱ǰҳ����Ϊ0���������URL��û�С�?page=��,����1����
	 * @param page ����Ĳ�����Ϊ0�򷵻�1��
	 * @return ��ǰҳ
	 */
	public static int countCurrentPage(int page) {
		int startPage = (page == 0 ? 1 : page);
		return startPage;
	}
	//��ҳ����
	public PageBean getFenYe(String hql,int pageSize,int page){
//		�ܼ�¼��
		int allRow=pageDao.getAllCount(hql);
//		��ҳ��
		int allPage=PageBean.countPage(pageSize, allRow);
		 int currentPage=PageBean.countCurrentPage(page);
		////��ǰ��ʼ��¼
		final int countstart=PageBean.countStart(pageSize, currentPage);
		
		////ÿҳ��¼��
		final int length=pageSize;
		
		//��һҳ���ļ�¼
		List list=pageDao.selForPage(hql, countstart, length);
		
		//�ѷ�ҳ��Ϣ���浽Bean��
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setAllPage(allPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
}