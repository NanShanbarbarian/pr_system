package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.UprDAO;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class UprDAOImpl implements UprDAO{

	private SessionFactory sessionFactory;

	public UserProjectRelation getUPR(int projectId, int stuLoginId) {
		Session session = HibernateSessionFactory.getSession();
		List<UserProjectRelation> list = session.createQuery("from UserProjectRelation where project_id=?0 and stu_login_id=?1", UserProjectRelation.class).setParameter(0, projectId).setParameter(1, stuLoginId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public void saveUPR(UserProjectRelation upr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(upr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void updateUPR(UserProjectRelation upr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(upr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deleteUPR(UserProjectRelation upr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(upr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public List<UserProjectRelation> getUPRList(int projectId, int status) {
		Session session = HibernateSessionFactory.getSession();
		List<UserProjectRelation> list = session.createQuery("from UserProjectRelation where project_id=?0 and status=?1", UserProjectRelation.class).setParameter(0, projectId).setParameter(1, status).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public List<UserProjectRelation> getUPRList(int projectId) {
		Session session = HibernateSessionFactory.getSession();
		List<UserProjectRelation> list = session.createQuery("from UserProjectRelation where project_id=?0", UserProjectRelation.class).setParameter(0, projectId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<UserProjectRelation> getListByStuLoginId(int stuLoginId) {
		Session session = HibernateSessionFactory.getSession();
		List<UserProjectRelation> list = session.createQuery("from UserProjectRelation where stu_login_id=?0 and status=0", UserProjectRelation.class).setParameter(0, stuLoginId).getResultList();
		
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
