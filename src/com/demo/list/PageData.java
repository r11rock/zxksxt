package com.demo.list;

import java.io.Serializable;
import java.util.List;

public class PageData implements Serializable {
	@SuppressWarnings("unchecked")
	public PageData(List data) {
		this.data = data;
		this.pageSize = data.size();
		this.total = data.size();
	}
	@SuppressWarnings("unchecked")
	public PageData(List data, int total, int pagesize, int page) {
		this.data = data;
		this.pageSize = pagesize;
		this.total = total;
		this.page = page;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����
	@SuppressWarnings("unchecked")
	private List data;
	//ÿҳ��С
	private int pageSize;
	//�ܼ�¼��
	private int total;
	//��ǰҳ
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@SuppressWarnings("unchecked")
	public List getData() {
		return data;
	}
	@SuppressWarnings("unchecked")
	public void setData(List data) {
		this.data = data;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage(){
		int num = total/pageSize;
		if(total%pageSize != 0){
			num++;
		}
		return num;
	}
}
