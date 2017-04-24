package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.PtrDAO;
import edu.zjut.tempest.entity.ProjectTagRelation;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class PtrDAOImpl implements PtrDAO{
	
	private SessionFactory sessionFactory;

	public void savePtr(ProjectTagRelation ptr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(ptr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public ProjectTagRelation getProjectByProjectId(int projectId) {
		Session session = HibernateSessionFactory.getSession();
		List<ProjectTagRelation> list = session.createQuery("from ProjectTagRelation where project_id=?0", ProjectTagRelation.class).setParameter(0, projectId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list !=null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public void deletePtr(ProjectTagRelation ptr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(ptr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
