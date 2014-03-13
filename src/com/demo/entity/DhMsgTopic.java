package com.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * �ػ���վ����topic
 *
 */
@Entity
@Table(name="dh_msg_topic")
public class DhMsgTopic implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long topicId;
	private String title;
	private Long lastReplyTime; // ���ظ�ʱ��
	private Integer msgReplyCount; // �ظ���
	private Integer msgType; // ��Ϣ����
	private Integer mcLock; // ��Ϣ����, ��;��ʱδ֪
	private String parm; // ���Ӳ���, ������Ϣ���ͷ��ز�ͬ����ı��
	private String senderId; // ������id
	private String senderNickname; // �������ǳ�
	private Integer senderReaded; // �������Ѷ�״̬: 0-δ��; 1-�Ѷ�
	private String recieverId;
	private String recieverNickname;
	private Integer recieverReaded;
	private Long bdUserId; // �����¡�ش�ϵͳ�û���Id
	private String bdUserName;
	private String dhAccount; // �����Ķػ��˺�
	private Integer readStatus; // 0.δ��; 1.�Ѷ�
	public Integer getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "topic_id")
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "last_reply_time")
	public Long getLastReplyTime() {
		return lastReplyTime;
	}
	public void setLastReplyTime(Long lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}
	@Column(name = "msg_reply_count")
	public Integer getMsgReplyCount() {
		return msgReplyCount;
	}
	public void setMsgReplyCount(Integer msgReplyCount) {
		this.msgReplyCount = msgReplyCount;
	}
	@Column(name = "msg_type")
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	@Column(name = "mc_lock")
	public Integer getMcLock() {
		return mcLock;
	}
	public void setMcLock(Integer mcLock) {
		this.mcLock = mcLock;
	}
	@Column(name = "parm")
	public String getParm() {
		return parm;
	}
	public void setParm(String parm) {
		this.parm = parm;
	}
	@Column(name = "sender_id")
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	@Column(name = "sender_nickname")
	public String getSenderNickname() {
		return senderNickname;
	}
	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}
	@Column(name = "sender_readed")
	public Integer getSenderReaded() {
		return senderReaded;
	}
	public void setSenderReaded(Integer senderReaded) {
		this.senderReaded = senderReaded;
	}
	@Column(name = "reciever_id")
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	@Column(name = "reciever_nickname")
	public String getRecieverNickname() {
		return recieverNickname;
	}
	public void setRecieverNickname(String recieverNickname) {
		this.recieverNickname = recieverNickname;
	}
	@Column(name = "reciever_readed")
	public Integer getRecieverReaded() {
		return recieverReaded;
	}
	public void setRecieverReaded(Integer recieverReaded) {
		this.recieverReaded = recieverReaded;
	}
	@Column(name = "bd_user_id")
	public Long getBdUserId() {
		return bdUserId;
	}
	public void setBdUserId(Long bdUserId) {
		this.bdUserId = bdUserId;
	}
	@Column(name = "bd_user_name")
	public String getBdUserName() {
		return bdUserName;
	}
	public void setBdUserName(String bdUserName) {
		this.bdUserName = bdUserName;
	}
	@Column(name = "dh_account")
	public String getDhAccount() {
		return dhAccount;
	}
	public void setDhAccount(String dhAccount) {
		this.dhAccount = dhAccount;
	}
	
}
