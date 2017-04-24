package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.ActivityManageDAO;
import edu.zjut.tempest.entity.ActivityManage;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class ActivityManageDAOImpl implements ActivityManageDAO {

	/**
	 * getById   通过id获得系统活动管理记录
	 * @param id
	 * @return
	 */
	public ActivityManage getById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<ActivityManage> list = session.createQuery("from ActivityManage where id=?0", ActivityManage.class).setParameter(0, id).getResultList();
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * getByName   通过name获得系统活动管理记录
	 * @param name
	 * @return
	 */
	public ActivityManage getByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		
		List<ActivityManage> list = session.createQuery("from ActivityManage where name=?0", ActivityManage.class).setParameter(0, name).getResultList();
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * getAllList   获得所有系统活动管理记录列表
	 * @return
	 */
	public List<ActivityManage> getAllList() {
		Session session = HibernateSessionFactory.getSession();
		
		List<ActivityManage> list = session.createQuery("from ActivityManage", ActivityManage.class).getResultList();
		HibernateSessionFactory.closeSession();
		
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public void updateAM(ActivityManage am) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(am);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
	}
}
