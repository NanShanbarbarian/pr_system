package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import edu.zjut.tempest.dao.StudentDAO;
import edu.zjut.tempest.entity.Student;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class StudentDAOImpl implements StudentDAO{
	
	private SessionFactory sessionFactory;

	public Student getStudentById(int id) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Student where id=?0";
		Query<Student> query = session.createQuery(hql, Student.class);
		query.setParameter(0, id);
		List<Student> list = query.getResultList();
		HibernateSessionFactory.closeSession();
		
		if( list.size() > 0) {
			Student stu = list.get(0);
			return stu;
		}
		return null;
	}
	
	public void updateStudent(Student student) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(student);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deleteStudent(Student student) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(student);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		} 
	}
	
	public int saveStudent(Student student) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try {
			result = (Integer)session.save(student);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		} 
		return result;
	}
	
	/**
	 * getListByName   通过姓名获得项目开发者(学生)的基本信息列表
	 * @param name
	 * @return
	 */
	public List<Student> getListByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		List<Student> list = session.createQuery("from Student where name=?0", Student.class).setParameter(0, name).getResultList();
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
