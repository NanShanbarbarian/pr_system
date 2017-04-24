package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.MessageDAO;
import edu.zjut.tempest.entity.Message;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class MessageDAOImpl implements MessageDAO{

	private SessionFactory sessionFactory;

	@Override
	public void saveMessage(Message message) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(message);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void updateMessage(Message message) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(message);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			
			HibernateSessionFactory.closeSession();
		}
	}

	public List<Message> getMessageBySenderIdAndReceiverId(int projectId, int senderId, int receiverId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Message> list = session.createQuery("from Message where project_id=?0 and sender_id=?1 and receiver_id=?2 order by createtime desc",Message.class).setParameter(0, projectId).setParameter(1, senderId).setParameter(2, receiverId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	 
	public List<Message> getMessageBySenderId(int projectId, int senderId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Message> list = session.createQuery("from Message where project_id=?0 and sender_id=?1 order by createtime desc",Message.class).setParameter(0, projectId).setParameter(1, senderId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public List<Message> getMessageByReceiverId(int projectId, int receiverId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Message> list = session.createQuery("from Message where project_id=?0 and receiver_id=?1 order by createtime desc",Message.class).setParameter(0, projectId).setParameter(1, receiverId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	@Override
	public Message getMessageById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Message> list = session.createQuery("from Message where id=?0",Message.class).setParameter(0, id).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
