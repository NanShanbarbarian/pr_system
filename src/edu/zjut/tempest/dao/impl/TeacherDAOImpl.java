package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import edu.zjut.tempest.dao.TeacherDAO;
import edu.zjut.tempest.entity.Teacher;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class TeacherDAOImpl implements TeacherDAO{

	private SessionFactory sessionFactory;

	public Teacher getTeacherById(int id) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Teacher where id=?0";
		Query<Teacher> query = session.createQuery(hql, Teacher.class);
		query.setParameter(0, id);
		List<Teacher> list = query.getResultList();
		HibernateSessionFactory.closeSession();
		
		if( list.size() > 0) {
			Teacher tea = list.get(0);
			return tea;
		}
		return null;
	}
	
	public int saveTeacher(Teacher teacher) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int id = 0;
		try {
			id = (Integer)session.save(teacher);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return id;
	}
	
	public void updateTeacher(Teacher teacher) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(teacher);
			tx.commit();
//			System.out.println("ok");
		} catch(Exception e) {
			tx.rollback();
//			System.out.println("erorr");
			e.printStackTrace();
			e.getMessage();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deleteTeacher(Teacher teacher) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(teacher);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.getMessage();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * getListByName   通过姓名获得项目发布者(教师)的信息列表
	 * @param name
	 * @return
	 */
	public List<Teacher> getListByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Teacher> list  = session.createQuery("from Teacher where name=?0", Teacher.class).setParameter(0, name).getResultList();
		
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
