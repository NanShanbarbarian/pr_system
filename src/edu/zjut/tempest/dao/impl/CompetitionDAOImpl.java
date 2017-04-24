package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.CompetitionDAO;
import edu.zjut.tempest.entity.Competition;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class CompetitionDAOImpl implements CompetitionDAO{
	
	private SessionFactory sessionFactory;

	@Override
	public int saveCompetition(Competition competition) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try {
			result = (Integer)session.save(competition);
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
	public void updateCompetition(Competition competition) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(competition);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deleteCompetition(Competition competition) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(competition);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public Competition getCompetitionById(int id) {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where id=?0", Competition.class).setParameter(0, id).getResultList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<Competition> getListByNumber(String number) {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where member1=?0 or member2=?0 or member3=?0 or member4=?0 or member5=?0 or member6=?0 or member7=?0 or member8=?0 or member9=?0 or member10=?0", Competition.class).setParameter(0, number).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public List<Competition> getListByName(String workName) {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where workName like '%"+ workName +"%'", Competition.class).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public List<Competition> getListByCtId(int ctId) {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where ct_id=?0", Competition.class).setParameter(0, ctId).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int getCountByIsPass(int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where isPass=?0", Competition.class).setParameter(0, isPass).getResultList();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}
	
	public List<Competition> getListByIsPass(int begin, int rowsPage, int isPass) {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where isPass=?0", Competition.class).setParameter(0, isPass).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	/**
	 * getAllList   获得所有的竞赛验证记录
	 * @return
	 */
	public List<Competition> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		List<Competition> list = session.createQuery("from Competition where isPass=0", Competition.class).getResultList();
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
