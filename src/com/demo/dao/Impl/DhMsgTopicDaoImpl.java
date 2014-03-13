package com.demo.dao.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.demo.dao.DhMsgTopicDao;
import com.demo.dao.PageDao;
import com.demo.entity.DhMsgTopic;
import com.demo.page.PageBean;

@Repository
public class DhMsgTopicDaoImpl extends BaseDaoImpl<DhMsgTopic, Long> implements DhMsgTopicDao{

	@Resource
	private PageDao pageDao;
	
	public DhMsgTopicDaoImpl() {
		super(DhMsgTopic.class);
	}
	
	public DhMsgTopic getByTopicId(Long topicId) {
		return ht.findFirst("from DhMsgTopic where topicId=?", new Object[]{topicId});
	}
	
	/**
	 * ��ҳ��ѯվ����
	 * @param pageSize
	 * @param pageNum
	 * @param userId
	 * @return
	 */
	public PageBean getAllByPage(int pageSize, int page, Long userId,
			String dhAccount, Integer msgType, Integer readStatus) {
		PageBean pageBean = new PageBean(pageDao);
        String queryPart = "";
        if (userId != null) {
        	queryPart += "bdUserId = " + userId + " ";
        }
        if (dhAccount != null) {
        	if (!queryPart.equals("")) {
        		queryPart += "and ";
        	}
        	queryPart += " dhAccount = '" + dhAccount + "' ";
        }
        if (msgType != null) {
        	if (!queryPart.equals("")) {
        		queryPart += "and ";
        	}
        	queryPart += " msgType = '" + msgType + "' ";
        }
        if (readStatus != null) {
        	if (!queryPart.equals("")) {
        		queryPart += "and ";
        	}
        	queryPart += " readStatus = '" + readStatus + "' ";
        	
        }
        if (!queryPart.equals("")) {
        	queryPart = "where " + queryPart;
        }
        String hql = "from DhMsgTopic " + queryPart + " order by lastReplyTime desc";
        return pageBean.getFenYe(hql, pageSize, page);
    }
	
	/**
	 * ����վ����
	 */
	public void fenpeiMsg(String dhAccount, Long bdUserId, String bdUserName) {
		ht.bulkUpdate("update DhMsgTopic set bdUserId = ?, bdUserName = ? where bdUserId is null and dhAccount = ?", 
				 new Object[]{bdUserId, bdUserName, dhAccount});
	}
	/**
	 * ��ȡδ�����(��������)վ�����б�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DhMsgTopic> getNotFenpeiMsgList(String dhAccount) {
		return ht.find("from DhMsgTopic where bdUserId is null and (msgType = 2 or msgType = 4) and dhAccount = ?", new Object[]{dhAccount});
	}

}
