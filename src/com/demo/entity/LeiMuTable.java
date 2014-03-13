package com.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="leimu")
public class LeiMuTable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;//���
	private String leimu;//��Ŀ
	private Long userid;//�ɹ�Ա���
	private String cateId; // �ػ�������ĿId
	private Long msgFenpeiUserId; // վ���ŷ���ģ�ҵ���û�id 
	private String msgFenpeiUserName; // վ���ŷ���ģ�ҵ���û�����
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLeimu() {
		return leimu;
	}
	public void setLeimu(String leimu) {
		this.leimu = leimu;
	}
	@Column(name = "cate_id")
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	@Column(name = "msg_fenpei_user_id")
	public Long getMsgFenpeiUserId() {
		return msgFenpeiUserId;
	}
	public void setMsgFenpeiUserId(Long msgFenpeiUserId) {
		this.msgFenpeiUserId = msgFenpeiUserId;
	}
	@Column(name = "msg_fenpei_user_name")
	public String getMsgFenpeiUserName() {
		return msgFenpeiUserName;
	}
	public void setMsgFenpeiUserName(String msgFenpeiUserName) {
		this.msgFenpeiUserName = msgFenpeiUserName;
	}
	
}
