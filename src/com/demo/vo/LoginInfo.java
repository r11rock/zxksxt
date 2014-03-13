package com.demo.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.demo.entity.user.CaiGou;
import com.demo.entity.user.CaiGouAdmin;

/**
 * ��ǰ�û���¼����Ϣ
 * @author Administrator
 *
 */
public class LoginInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�ʺ�
	private String account;
	//����
	private String name;
	//�ʻ����
	private Long userId;
	//��ɫ:1.����Ա,2.ѧԱ,3.��Ա,4.�ɹ�����Ա
	private Integer role;
	//ҵ��������
	private Long YeWuId;
	//�ɹ����
	private Long CaiGouId;
	//�ɹ�����Ա
	private Long CaiGouAdminId;
    private Long gukeId;//�˿ͱ��
    private Long CangKuYuanId;//�ֿ�Ա���
    private Long caiwuId;//������
	//��¼ʱ��
	private Timestamp loginTime;
	private List<CaiGou> caigou;//�ɹ�
	private List<CaiGouAdmin> caiGouAdmin;//�ɹ�����Ա
	//��¼IP
	private String ip;
	private String pwd;//����
	private Long id;//�û����
	private Long yewu1Id;//ҵ����
	public Long getYewu1Id() {
		return yewu1Id;
	}
	public void setYewu1Id(Long yewu1Id) {
		this.yewu1Id = yewu1Id;
	}
	public Long getCaiwuId() {
		return caiwuId;
	}
	public void setCaiwuId(Long caiwuId) {
		this.caiwuId = caiwuId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CaiGouAdmin> getCaiGouAdmin() {
		return caiGouAdmin;
	}
	public void setCaiGouAdmin(List<CaiGouAdmin> caiGouAdmin) {
		this.caiGouAdmin = caiGouAdmin;
	}
	public List<CaiGou> getCaigou() {
		return caigou;
	}
	public void setCaigou(List<CaiGou> caigou) {
		this.caigou = caigou;
	}
	public Long getGukeId() {
		return gukeId;
	}
	public void setGukeId(Long gukeId) {
		this.gukeId = gukeId;
	}
	public Long getCangKuYuanId() {
		return CangKuYuanId;
	}
	public void setCangKuYuanId(Long cangKuYuanId) {
		CangKuYuanId = cangKuYuanId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Long getYeWuId() {
		return YeWuId;
	}
	public void setYeWuId(Long yeWuId) {
		YeWuId = yeWuId;
	}
	public Long getCaiGouId() {
		return CaiGouId;
	}
	public void setCaiGouId(Long caiGouId) {
		CaiGouId = caiGouId;
	}
	public Long getCaiGouAdminId() {
		return CaiGouAdminId;
	}
	public void setCaiGouAdminId(Long caiGouAdminId) {
		CaiGouAdminId = caiGouAdminId;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
}
