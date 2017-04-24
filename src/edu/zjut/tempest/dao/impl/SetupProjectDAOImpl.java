package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.SetupProjectDAO;
import edu.zjut.tempest.entity.SetupProject;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class SetupProjectDAOImpl implements SetupProjectDAO{

	private SessionFactory sessionFactory;

	public int saveSetupPro(SetupProject setupPro) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		int result = 0;
		try {
			result = (Integer)session.save(setupPro);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return result;
	}
	
	public int getCountOnAll() {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject", SetupProject.class).getResultList();
		
		if(list != null && list.size()>0) {
			return list.size();
		}
		return 0;
	}
	
	@Override
	public List<SetupProject> getAllList(int begin, int rowsPage) {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject", SetupProject.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		if(list != null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public List<SetupProject> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject", SetupProject.class).getResultList();
		
		if(list != null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public List<SetupProject> getListByOfficalNumber(String officalNumber) {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject where offical_number=?0", SetupProject.class).setParameter(0, officalNumber).getResultList();
		
		if(list != null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public List<SetupProject> getListByCategory(String category) {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject where category like '%"+category+"%'", SetupProject.class).getResultList();
		
		if(list != null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public List<SetupProject> getListByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject where name like '%"+name+"%'", SetupProject.class).getResultList();
		
		if(list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	public SetupProject getSetupProById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<SetupProject> list = session.createQuery("from SetupProject where id=?0", SetupProject.class).setParameter(0, id).getResultList();
		
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * deleteSetupPro   删除项目立项结题记录
	 */
	public void deleteSetupPro(SetupProject setupPro) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(setupPro);
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
