package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.GiftedMentorDAO;
import edu.zjut.tempest.entity.GiftedMentor;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class GiftedMentorDAOImpl implements GiftedMentorDAO {

	public int saveGM(GiftedMentor gm) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try {
			result = (int)session.save(gm);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
		return result;
	}
	
	public void deleteGM(GiftedMentor gm) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(gm);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
	}
	
	public void updateGM(GiftedMentor gm) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(gm);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
	}
	
	public GiftedMentor getById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor where id=?0", GiftedMentor.class).setParameter(0, id).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<GiftedMentor> getByPublisherId(int publisherId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor where publisher_id=?0", GiftedMentor.class).setParameter(0, publisherId).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public int getAllCount() {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor", GiftedMentor.class).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list.size();
		}
		return 0;
	}
	
	public List<GiftedMentor> getListByPages(int begin, int rowsPage) {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor order by publish_time desc", GiftedMentor.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public List<GiftedMentor> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor", GiftedMentor.class).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public int getSelectCount() {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor where status=0 order by publish_time desc", GiftedMentor.class).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list.size();
		}
		return 0;
	}
	
	public List<GiftedMentor> getSelectListByPages(int begin, int rowsPage) {
		Session session = HibernateSessionFactory.getSession();
		
		List<GiftedMentor> list = session.createQuery("from GiftedMentor where status=0 order by publish_time desc", GiftedMentor.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public int deleteAllGm() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try{
			result = session.createQuery("delete from GiftedMentor").executeUpdate();
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
		
		return result;
	}
}
