package com.demo.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="xiexin")
public class XieXinTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//���
	private Long userid;//�ռ���
	private String title;//����
	private String neirong;//����
	private String time;//����ʱ��
	private Long chakan;//�Ƿ�鿴 1.�Ѿ��鿴��null��δ�鿴
	private String fajianren;//������
	public String getFajianren() {
		return fajianren;
	}
	public void setFajianren(String fajianren) {
		this.fajianren = fajianren;
	}
	public Long getChakan() {
		return chakan;
	}
	public void setChakan(Long chakan) {
		this.chakan = chakan;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNeirong() {
		return neirong;
	}
	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}
	
}
