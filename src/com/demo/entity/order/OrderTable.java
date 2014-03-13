/*jadclipse*/// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) radix(10) lradix(10) 
// Source File Name:   OrderTable.java

package com.demo.entity.order;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import oracle.sql.CHAR;
@Entity
@Table(name="orderinfo")
public class OrderTable implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;//���
    private java.util.Date time;//�ϴ�ʱ��

	private String orderId;//������ 
    private Double money;//��Ҹ�����
    private String yunshu;//��Ŀ
    private String danhao;//�������䵥��
    private Double yunfei;//�˷�
    private Double huokuan;//�ɹ����
    private String guojia;//����ͨ��ַ
    private Long ArtistsGetOrders;//�����õ����� 
    private String remark;//��ע
    private java.util.Date caigoutime;//�ɹ�ʱ��
    private Long guoneiwangzhanId;//������վ���
    private String wuping;//��Ʒ
    private Long daifahuo;//������ 0,δ����,1������2.������,3.��������񸶿�,4.��������
    private String guowaidizhi;//�����ַ
    private String guoneidanhao;//�������䵥��
    private Long sumaitong;//����ͨ 0.δ¼��,1.���뵥
    private Long denghuixin;
    private Long wancheng;//0.δ��ɣ�1.�Ѿ����
    private Long jiufen;//0.δ����,1.�Ѿ�����
    private Long xiugai;//0.δ�޸�,1.�Ѿ��޸�
    private Long fenpei;//0.δ����,1.�Ѿ�����,2.���ⶩ��,3�ͻ��ϴ��Ķ���
    private Long shangwang;//0.δ����,1.�Ѿ�����
    private Long qianshou;//0.δǩ��,1.�Ѿ�ǩ��
    private Long ruzhang;//0.δ����,1.�Ѿ�����
    private Long kuaidifangshiId;//���ʿ�ݷ�ʽ
    private Long caigouyuan;//�ɹ�Ա���
    private String gongyunshang;//��������Ϣ
    private Double tuikuan;//�˿�
    private Double huilv;//����
    private Long dengluId;//ҵ����
    private Long zhanghaoId;//�ػ��˺ű��
    private Long guoneikuaidiId;//���ڿ�ݱ��
    private Long tuihuo;//�Ƿ��˻�0.�� 1.��,2.���ص��ֿ��˼��˵� 
    private String name;//�˿�����
    private Long daochu;//0.δ���� 1.���� 2.����ҵ��,3���ظ��ֿ�
    private Long jingji;//��������0.������,1.����
    private Long kehuId;//�ͻ����
    private Date wanchengtime;//���ʱ��
    private Long kucun;//�Ƿ��ǿ��,0.���ǿ�棬1.�ǿ��
    private Double zhongliang;//����
    private String dizhi;//��ַ
    private String bianma;//���� 
    private Long yunfeidaochu;//0.δ���� 1.�Ѿ�����
    private Long guojiaId;//���� 
    private Date jiufentime;//����ʱ��
    private Long leimuid;//��Ŀ���
    private Long ArtistsGetOrdersId;//�������
    private Long fawan;//�Ƿ���  0.û���� 1.����
    private Long num;//����
    private Long chuli;//�Ƿ��� 0.�� 1.��
    private java.util.Date ruzhangtime;//����ʱ��
    private String timecha;//ʱ���
    private Long yjsc;//�Ѿ��ϴ��Ķ���
    private Long yjcx;//�Ѿ���ѯ
    private Long sjc;//ʱ���
    private String dggjdh;//������ʵ���
    private String dcsj;//��������ʱ��
    private Long cgjf;//�ɹ������Ƿ񵼳�
    private Long order_type;//����
    private String ordersn;
    private String address1;//��ַ
    private String city;
    private String province;
    private String postcode;
    private String telephone;
    private String receiver;
    private Long paydate;
    private String shippingtype; 
    private String country;
    private Long getordersId;//1.�õ�����0.û�еõ�����,2��� 
    private Long ArtProcessing;//�����Ƿ���
    
    public Long getArtistsGetOrdersId() {
		return ArtistsGetOrdersId;
	}

	public void setArtistsGetOrdersId(Long artistsGetOrdersId) {
		ArtistsGetOrdersId = artistsGetOrdersId;
	}

	public Long getArtistsGetOrders() {
		return ArtistsGetOrders;
	}

	public void setArtistsGetOrders(Long artistsGetOrders) {
		ArtistsGetOrders = artistsGetOrders;
	}

	public Long getArtProcessing() {
		return ArtProcessing;
	}

	public void setArtProcessing(Long artProcessing) {
		ArtProcessing = artProcessing;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public Long getGetordersId() {
		return getordersId;
	}

	public void setGetordersId(Long getordersId) {
		this.getordersId = getordersId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Id
    @GeneratedValue
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
	public Long getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Long orderType) {
		order_type = orderType;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public Long getPaydate() {
		return paydate;
	}

	public void setPaydate(Long paydate) {
		this.paydate = paydate;
	}

	public String getShippingtype() {
		return shippingtype;
	}

	public void setShippingtype(String shippingtype) {
		this.shippingtype = shippingtype;
	}

	public Long getCgjf() {
		return cgjf;
	}

	public void setCgjf(Long cgjf) {
		this.cgjf = cgjf;
	}

	public String getDcsj() {
		return dcsj;
	}

	public void setDcsj(String dcsj) {
		this.dcsj = dcsj;
	}

	public String getDggjdh() {
		return dggjdh;
	}

	public void setDggjdh(String dggjdh) {
		this.dggjdh = dggjdh;
	}

	public Long getSjc() {
		return sjc;
	}

	public void setSjc(Long sjc) {
		this.sjc = sjc;
	}

	public Long getYjcx() {
		return yjcx;
	}

	public void setYjcx(Long yjcx) {
		this.yjcx = yjcx;
	}

	public Long getYjsc() {
		return yjsc;
	}

	public void setYjsc(Long yjsc) {
		this.yjsc = yjsc;
	}

	public String getTimecha() {
		return timecha;
	}

	public void setTimecha(String timecha) {
		this.timecha = timecha;
	}

	public java.util.Date getRuzhangtime() {
		return ruzhangtime;
	}

	public void setRuzhangtime(java.util.Date ruzhangtime) {
		this.ruzhangtime = ruzhangtime;
	}

	public Long getChuli() {
		return chuli;
	}

	public void setChuli(Long chuli) {
		this.chuli = chuli;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	public Long getFawan() {
		return fawan;
	}

	public void setFawan(Long fawan) {
		this.fawan = fawan;
	}

	public Long getLeimuid() {
		return leimuid;
	}

	public void setLeimuid(Long leimuid) {
		this.leimuid = leimuid;
	}

	public Date getJiufentime() {
		return jiufentime;
	}

	public void setJiufentime(Date jiufentime) {
		this.jiufentime = jiufentime;
	}

	public Long getGuojiaId() {
		return guojiaId;
	}

	public void setGuojiaId(Long guojiaId) {
		this.guojiaId = guojiaId;
	}

	public Long getYunfeidaochu() {
		return yunfeidaochu;
	}

	public void setYunfeidaochu(Long yunfeidaochu) {
		this.yunfeidaochu = yunfeidaochu;
	}

	public String getBianma() {
		return bianma;
	}

	public void setBianma(String bianma) {
		this.bianma = bianma;
	}

	public String getDizhi() {
		return dizhi;
	}

	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}

	public Double getZhongliang() {
		return zhongliang;
	}

	public void setZhongliang(Double zhongliang) {
		this.zhongliang = zhongliang;
	}

	public Long getKucun() {
		return kucun;
	}

	public void setKucun(Long kucun) {
		this.kucun = kucun;
	}

	public Date getWanchengtime() {
		return wanchengtime;
	}

	public void setWanchengtime(Date wanchengtime) {
		this.wanchengtime = wanchengtime;
	}

	public Long getKehuId() {
		return kehuId;
	}

	public void setKehuId(Long kehuId) {
		this.kehuId = kehuId;
	}

	public Long getJingji() {
		return jingji;
	}

	public void setJingji(Long jingji) {
		this.jingji = jingji;
	}


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getGuoneikuaidiId()
    {
        return guoneikuaidiId;
    }

    public void setGuoneikuaidiId(Long guoneikuaidiId)
    {
        this.guoneikuaidiId = guoneikuaidiId;
    }

    public Long getKuaidifangshiId()
    {
        return kuaidifangshiId;
    }

    public void setKuaidifangshiId(Long kuaidifangshiId)
    {
        this.kuaidifangshiId = kuaidifangshiId;
    }
  
    public java.util.Date getCaigoutime() {
		return caigoutime;
	}

	public void setCaigoutime(java.util.Date caigoutime) {
		this.caigoutime = caigoutime;
	}

	public Long getGuoneiwangzhanId()
    {
        return guoneiwangzhanId;
    }

    public void setGuoneiwangzhanId(Long guoneiwangzhanId)
    {
        this.guoneiwangzhanId = guoneiwangzhanId;
    }

    public String getWuping()
    {
        return wuping;
    }

    public void setWuping(String wuping)
    {
        this.wuping = wuping;
    }

    public String getGuowaidizhi()
    {
        return guowaidizhi;
    }

    public void setGuowaidizhi(String guowaidizhi)
    {
        this.guowaidizhi = guowaidizhi;
    }

    public String getGuoneidanhao()
    {
        return guoneidanhao;
    }

    public void setGuoneidanhao(String guoneidanhao)
    {
        this.guoneidanhao = guoneidanhao;
    }
    public Long getDengluId()
    {
        return dengluId;
    }

    public void setDengluId(Long dengluId)
    {
        this.dengluId = dengluId;
    }

 
    public Long getZhanghaoId() {
		return zhanghaoId;
	}

	public void setZhanghaoId(Long zhanghaoId) {
		this.zhanghaoId = zhanghaoId;
	}
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getYunshu()
    {
        return yunshu;
    }

    public void setYunshu(String yunshu)
    {
        this.yunshu = yunshu;
    }

    public String getDanhao()
    {
        return danhao;
    }

    public void setDanhao(String danhao)
    {
        this.danhao = danhao;
    }

    public Double getYunfei()
    {
        return yunfei;
    }

    public void setYunfei(Double yunfei)
    {
        this.yunfei = yunfei;
    }

    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    public Double getHuokuan()
    {
        return huokuan;
    }

    public void setHuokuan(Double huokuan)
    {
        this.huokuan = huokuan;
    }

    public String getGuojia()
    {
        return guojia;
    }

    public void setGuojia(String guojia)
    {
        this.guojia = guojia;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public Long getDaifahuo() {
		return daifahuo;
	}

	public void setDaifahuo(Long daifahuo) {
		this.daifahuo = daifahuo;
	}

	public Long getSumaitong() {
		return sumaitong;
	}

	public void setSumaitong(Long sumaitong) {
		this.sumaitong = sumaitong;
	}

	public Long getDenghuixin() {
		return denghuixin;
	}

	public void setDenghuixin(Long denghuixin) {
		this.denghuixin = denghuixin;
	}

	public Long getWancheng() {
		return wancheng;
	}

	public void setWancheng(Long wancheng) {
		this.wancheng = wancheng;
	}

	public Long getJiufen() {
		return jiufen;
	}

	public void setJiufen(Long jiufen) {
		this.jiufen = jiufen;
	}

	public Long getXiugai() {
		return xiugai;
	}

	public void setXiugai(Long xiugai) {
		this.xiugai = xiugai;
	}

	public Long getFenpei() {
		return fenpei;
	}

	public void setFenpei(Long fenpei) {
		this.fenpei = fenpei;
	}

	public Long getShangwang() {
		return shangwang;
	}

	public void setShangwang(Long shangwang) {
		this.shangwang = shangwang;
	}

	public Long getQianshou() {
		return qianshou;
	}

	public void setQianshou(Long qianshou) {
		this.qianshou = qianshou;
	}

	public Long getRuzhang() {
		return ruzhang;
	}

	public void setRuzhang(Long ruzhang) {
		this.ruzhang = ruzhang;
	}

	public Long getTuihuo() {
		return tuihuo;
	}

	public void setTuihuo(Long tuihuo) {
		this.tuihuo = tuihuo;
	}

	public Long getDaochu() {
		return daochu;
	}

	public void setDaochu(Long daochu) {
		this.daochu = daochu;
	}

	public Long getCaigouyuan()
    {
        return caigouyuan;
    }

    public void setCaigouyuan(Long caigouyuan)
    {
        this.caigouyuan = caigouyuan;
    }

    public String getGongyunshang()
    {
        return gongyunshang;
    }

    public void setGongyunshang(String gongyunshang)
    {
        this.gongyunshang = gongyunshang;
    }

    public Double getTuikuan()
    {
        return tuikuan;
    }

    public void setTuikuan(Double tuikuan)
    {
        this.tuikuan = tuikuan;
    }

    public Double getHuilv()
    {
        return huilv;
    }

    public void setHuilv(Double huilv)
    {
        this.huilv = huilv;
    }


}
