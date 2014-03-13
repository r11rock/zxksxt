package com.demo.dao;

import java.util.List;

import com.demo.entity.ZhangHao;
import com.demo.entity.order.OrderTable;
import com.demo.page.PageBean;

public interface ZhangHaoDao extends BaseDao<ZhangHao, Long> {
	public abstract List<ZhangHao> getAllZhangHao();

    public abstract ZhangHao getZhangHao(String s);

    public abstract ZhangHao getZhangHaoId(Long long1);
    //�ػ��˺�
    public abstract String getdhgate(String s);
    //�˺Ų�ѯȫ��
    public abstract List<ZhangHao> getZhangHaoName(String zhanghaoname);
    //�˺ű�Ų�ѯȫ��
    public abstract List<ZhangHao> getZhangHaoIdAll(Long id);
    //�˺����Ʋ�ѯȫ��
    public ZhangHao findUniqueByAccount(String account);
    // ��ȡһ���˺���Ϣ
    public abstract ZhangHao findUnique(String account, String accountType);
    // ��ѯ�˺��б�
    public abstract List<ZhangHao> getAll(String accountType, Long bdUserId);
    // ��ҳ��ѯ�˺��б�
    public abstract PageBean getAllByPage(int pageSize, int page, String accountType);
}
