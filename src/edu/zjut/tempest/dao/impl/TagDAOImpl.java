package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.TagDAO;
import edu.zjut.tempest.entity.Tag;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class TagDAOImpl implements TagDAO{

	private SessionFactory sessionFactory;

	public Tag getTagById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Tag> list = session.createQuery("from Tag where id=?0",Tag.class).setParameter(0, id).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public void saveTag(Tag tag) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(tag);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deleteTag(Tag tag) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(tag);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void updateTag(Tag tag) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(tag);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public Tag getTagByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		List<Tag> list = session.createQuery("from Tag where name=?0", Tag.class).setParameter(0, name).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * getTagCountOnSearch 模糊查询的标签总数
	 * @param search
	 * @return
	 */
	public int getTagCountOnSearch(String search) {
		Session session = HibernateSessionFactory.getSession();
		
		int count = session.createQuery("from Tag where name like '%"+search+"%' ",Tag.class).getResultList().size();
		
		HibernateSessionFactory.closeSession();
		
		if(count > 0) {
			return count;
		}
		return 0;
	}
	
	/**
	 * getTagListOnSearch 分页获得模糊查询的标签列表
	 * @param begin
	 * @param rowsPage
	 * @param search
	 * @return
	 */
	public List<Tag> getTagListOnSearch(int begin, int rowsPage,String search) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Tag> list = session.createQuery("from Tag where name like '%"+search+"%' ",Tag.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	/**
	 * getTagAllListOnsearch 获得模糊查询的所有标签列表
	 * @param search
	 * @return
	 */
	public List<Tag> getTagAllListOnsearch(String search) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Tag> list = session.createQuery("from Tag where name like '%"+search+"%' ",Tag.class).getResultList();
		
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	/**
	 * getTagCount 获得标签的总数
	 */
	public int getTagCount() {
		Session session = HibernateSessionFactory.getSession();
		int count = session.createQuery("from Tag",Tag.class).getResultList().size();
		
		HibernateSessionFactory.closeSession();
		if(count > 0) {
			return count;
		}
		return 0;
	}

	/**
	 * getTagList 分页查询标签列表
	 */
	public List<Tag> getTagList(int begin, int rowsPage) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Tag> list = session.createQuery("from Tag",Tag.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
