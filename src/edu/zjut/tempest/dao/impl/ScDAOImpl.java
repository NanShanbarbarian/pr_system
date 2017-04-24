package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.ScDAO;
import edu.zjut.tempest.entity.SoftwareCopyright;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class ScDAOImpl implements ScDAO{

	private SessionFactory sessionFactory;

	@Override
	public int saveSc(SoftwareCopyright sc) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try {
			result = (Integer)session.save(sc);
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
	public void updateSc(SoftwareCopyright sc) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(sc);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public void deleteSc(SoftwareCopyright sc) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(sc);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@Override
	public SoftwareCopyright getScById(int id) {
		Session session = HibernateSessionFactory.getSession();
		List<SoftwareCopyright> list = session.createQuery("from SoftwareCopyright where id=?0", SoftwareCopyright.class).setParameter(0, id).getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SoftwareCopyright> getListByNumber(String number) {
		Session session = HibernateSessionFactory.getSession();
		List<SoftwareCopyright> list = session.createQuery("from SoftwareCopyright where member1=?0 or member2=?0 or member3=?0 or member4=?0 or member5=?0 or member6=?0 or member7=?0 or member8=?0 or member9=?0 or member10=?0", SoftwareCopyright.class).setParameter(0, number).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * getListByName   根据软件著作名称获得软件著作记录列表
	 * @param name
	 * @return
	 */
	public List<SoftwareCopyright> getListByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		List<SoftwareCopyright> list = session.createQuery("from SoftwareCopyright where name like '%"+ name +"%'", SoftwareCopyright.class).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public int getCountByIsPass(int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<SoftwareCopyright> list = session.createQuery("from SoftwareCopyright where isPass=?0", SoftwareCopyright.class).setParameter(0, isPass).getResultList();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	@Override
	public List<SoftwareCopyright> getListByIsPass(int begin, int rowsPage, int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<SoftwareCopyright> list = session.createQuery("from SoftwareCopyright where isPass=?0", SoftwareCopyright.class).setParameter(0, isPass).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * getAllList   获得所有的已验证软件著作记录
	 * @return
	 */
	public List<SoftwareCopyright> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		List<SoftwareCopyright> list = session.createQuery("from SoftwareCopyright where isPass=0", SoftwareCopyright.class).getResultList();
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
