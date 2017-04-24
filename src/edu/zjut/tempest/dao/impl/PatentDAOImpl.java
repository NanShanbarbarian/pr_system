package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.PatentDAO;
import edu.zjut.tempest.entity.Patent;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class PatentDAOImpl implements PatentDAO{

	private SessionFactory sessionFactory;

	@Override
	public int savePatent(Patent patent) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		int result = 0;
		try {
			result = (Integer)session.save(patent);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return result;
	}

	@Override
	public void updatePatent(Patent patent) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(patent);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void deletePatent(Patent patent) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(patent);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public Patent getPatentById(int id) {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where id=?0", Patent.class).setParameter(0, id).getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Patent> getListByNumber(String number) {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where member1=?0 or member2=?0 or member3=?0 or member4=?0 or member5=?0 or member6=?0 or member7=?0 or member8=?0 or member9=?0 or member10=?0", Patent.class).setParameter(0, number).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * getListByName   通过专利名称获得专利记录列表
	 * @param name
	 * @return
	 */
	public List<Patent> getListByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where name like '%"+ name +"%'", Patent.class).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * getListByPaperNumber   通过专利号获得专利记录列表
	 * @param number
	 * @return
	 */
	public List<Patent> getListByPaperNumber(String number) {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where number=?0", Patent.class).setParameter(0, number).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Override
	public int getCountByIsPass(int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where isPass=?0", Patent.class).setParameter(0, isPass).getResultList();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public List<Patent> getListByIsPass(int begin, int rowsPage, int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where isPass=?0", Patent.class).setParameter(0, isPass).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * getAllList   获得所有已验证的专利记录
	 * @return
	 */
	public List<Patent> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		List<Patent> list = session.createQuery("from Patent where isPass=0", Patent.class).getResultList();
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
