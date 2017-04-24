package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.CompetitionTypeDAO;
import edu.zjut.tempest.entity.CompetitionType;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class CompetitionTypeDAOImpl implements CompetitionTypeDAO{
	
	private SessionFactory sessionFactory;
	
	public void saveCT(CompetitionType ct) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(ct);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public CompetitionType getByNameAndCategory(String name, int category) {
		Session session = HibernateSessionFactory.getSession();
		
		List<CompetitionType> list = session.createQuery("from CompetitionType where name=?0 and category=?1", CompetitionType.class).setParameter(0, name).setParameter(1, category).getResultList();
		
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public CompetitionType getById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<CompetitionType> list = session.createQuery("from CompetitionType where id=?0", CompetitionType.class).setParameter(0, id).getResultList();
		
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<CompetitionType> getListByCategory(int category) {
		Session session = HibernateSessionFactory.getSession();
		
		List<CompetitionType> list = session.createQuery("from CompetitionType where category=?0", CompetitionType.class).setParameter(0, category).getResultList();
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
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
