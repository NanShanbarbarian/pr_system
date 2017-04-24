package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.PaperDAO;
import edu.zjut.tempest.entity.Paper;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class PaperDAOImpl implements PaperDAO{

	private SessionFactory sessionFactory;

	public int savePaper(Paper paper) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try {
			result = (Integer)session.save(paper);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return result;
	}
	
	public void updatePaper(Paper paper) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(paper);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deletePaper(Paper paper) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(paper);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public Paper getPaperById(int id) {
		Session session = HibernateSessionFactory.getSession();
		List<Paper> list = session.createQuery("from Paper where id=?0", Paper.class).setParameter(0, id).getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public List<Paper> getListByNumber(String number) {
		Session session = HibernateSessionFactory.getSession();
		List<Paper> list = session.createQuery("from Paper where member1=?0 or member2=?0 or member3=?0 or member4=?0 or member5=?0 or member6=?0 or member7=?0 or member8=?0 or member9=?0 or member10=?0", Paper.class).setParameter(0, number).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * getListByName   根据论文名称获得期刊论文记录
	 * @param name
	 * @return
	 */
	public List<Paper> getListByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		List<Paper> list = session.createQuery("from Paper where name like '%"+ name +"%'", Paper.class).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Override
	public int getCountByIsPass(int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<Paper> list = session.createQuery("from Paper where isPass=?0", Paper.class).setParameter(0, isPass).getResultList();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public List<Paper> getListByIsPass(int begin, int rowsPage, int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<Paper> list = session.createQuery("from Paper where isPass=?0", Paper.class).setParameter(0, isPass).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * getAllList   获得所有已验证的期刊论文记录
	 * @return
	 */
	public List<Paper> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		List<Paper> list = session.createQuery("from Paper where isPass=0", Paper.class).getResultList();
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
