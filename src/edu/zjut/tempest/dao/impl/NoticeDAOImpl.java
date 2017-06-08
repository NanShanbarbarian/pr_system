package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.NoticeDAO;
import edu.zjut.tempest.entity.Notice;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class NoticeDAOImpl implements NoticeDAO{

	private SessionFactory sessionFactory;
	
	@Override
	public void saveNotice(Notice notice) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(notice);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void updateNotice(Notice notice) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(notice);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public int getNoticeCount() {
		Session session = HibernateSessionFactory.getSession();
		
		List<Notice> list = session.createQuery("from Notice where state='U'", Notice.class).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}
	
	@Override
	public List<Notice> getNoticeList(int begin, int rowsPage) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Notice> list = session.createQuery("from Notice where state='U' order by createtime desc", Notice.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		return list;
	}

	@Override
	public Notice getNoticeById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Notice> list = session.createQuery("from Notice where id=?0", Notice.class).setParameter(0, id).getResultList();
		
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
