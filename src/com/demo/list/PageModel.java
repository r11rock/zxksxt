package com.demo.list;

import java.util.List;

public class PageModel<T> {
	private int pagecount=0;//��ҳ��
	private int pageindex=1;//��ǰҳ
	private int count=0;//�ܼ�¼
	private int pagecenter=10;//ÿҳ��ʾ��
	private List<T> pagelist=null;//���м�¼
	private int start=0;//��ʼ
	public int getPageindex() {
		return pageindex;
	}
	public void setPageindex(int pageindex) {
		if(this.pagecount<pageindex)//��ҳ��С�ڵ�ǰҳ
		{
			pageindex=this.pagecount;
		}
		else if(pageindex<=0)//��ǰҳС�ڵ���0
		{
			pageindex=1;
		}
		this.pageindex = pageindex;
		start=(this.pageindex-1)*pagecenter;//��ʼ����
	}
	public int getPagecenter() {
		return pagecenter;
	}
	public void setPagecenter(int pagecenter) {
		this.pagecenter = pagecenter;
	}
	public List<T> getFsd(){
		return getPagelist();
	}
	public List<T> getPagelist() {
		if(this.count<(start+pagecenter))
		{
		return pagelist.subList(start, count);
		}else
		{
			return pagelist.subList(start, start+pagecenter);//��ʼ�������ռ�
		}
	}
	public void setPagelist(List<T> pagelist) {
		this.count=pagelist.size();
		this.pagecount=(this.count+this.pagecenter-1)/this.pagecenter;//��ҳ��
		this.pagelist = pagelist;
	}
	public int getPagecount() {
		return pagecount;
	}
	public int getCount() {
		return count;
	}
	public int getStart() {
		return start;
	}
	public void setPagecount(int pagecount) {
		this.pagecount=(this.count+this.pagecenter-1)/this.pagecenter;
		this.pagecount = pagecount;
	}
	public Integer getSize()
	{
		return pagelist.size();
	}
}
