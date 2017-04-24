package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.MsrDAO;
import edu.zjut.tempest.entity.MentorStudentRelation;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class MsrDAOImpl implements MsrDAO {

	public int saveMsr(MentorStudentRelation msr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		int result = 0;
		try {
			result = (int) session.save(msr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		}
		HibernateSessionFactory.closeSession();
		return result;
	}
	
	public void delteMsr(MentorStudentRelation msr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(msr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		}
		HibernateSessionFactory.closeSession();
	}
	
	public void updateMsr(MentorStudentRelation msr) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(msr);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
		}
		HibernateSessionFactory.closeSession();
	}
	
	public List<MentorStudentRelation> getByGmId(int gmId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<MentorStudentRelation> list = session.createQuery("from MentorStudentRelation where gm_id=?0", MentorStudentRelation.class).setParameter(0, gmId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public MentorStudentRelation getByGmIdAndLoginId(int gmId, int stuLoginId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<MentorStudentRelation> list = session.createQuery("from MentorStudentRelation where gm_id=?0 and stu_login_id=?1", MentorStudentRelation.class).setParameter(0, gmId).setParameter(1, stuLoginId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<MentorStudentRelation> getByStuLoginId(int stuLoginId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<MentorStudentRelation> list = session.createQuery("from MentorStudentRelation where stu_login_id=?0", MentorStudentRelation.class).setParameter(0, stuLoginId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	public MentorStudentRelation getById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<MentorStudentRelation> list = session.createQuery("from MentorStudentRelation where id=?0", MentorStudentRelation.class).setParameter(0, id).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<MentorStudentRelation> getByGmIdAndTypeAndIsPass(int gmId, int type, int isPass) {
		Session session = HibernateSessionFactory.getSession();
		
		List<MentorStudentRelation> list = session.createQuery("from MentorStudentRelation where gm_id=?0 and type=?1 and isPass=?2", MentorStudentRelation.class).setParameter(0, gmId).setParameter(1, type).setParameter(2, isPass).getResultList();
		HibernateSessionFactory.closeSession();
		if(list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	
	/**
	 * deleteAllMsr   删除优才导师中间表的所有记录
	 * @return
	 */
	public void deleteAllMsr() {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try{
			session.createQuery("delete from MentorStudentRelation").executeUpdate();
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
	}
}
