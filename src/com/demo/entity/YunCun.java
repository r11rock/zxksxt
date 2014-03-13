package com.demo.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="yucun")
public class YunCun implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//���
	private Double money;//Ԥ���ʽ�
	private String time;//Ԥ��ʱ��
	private Long gukeId;//�˿ͱ��
	private String sytime;//ʹ��ʱ��
	private Double symoney;//ʹ�õ��˷�
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSytime() {
		return sytime;
	}
	public void setSytime(String sytime) {
		this.sytime = sytime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Long getGukeId() {
		return gukeId;
	}
	public void setGukeId(Long gukeId) {
		this.gukeId = gukeId;
	}

	public Double getSymoney() {
		return symoney;
	}
	public void setSymoney(Double symoney) {
		this.symoney = symoney;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
