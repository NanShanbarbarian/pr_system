package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import edu.zjut.tempest.dao.LoginDAO;
import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class LoginDAOImpl implements LoginDAO {
	
	private SessionFactory sessionFactory;

	/**
	 * findLogin 判断用户账号是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	public Login findLogin(String userName,String password) {
//		Session session = sessionFactory.openSession();
		//HibernateSessionFactory是个封装的工具类
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "from Login where number=?0 and password=?1 and status=1";
		Query<Login> query = session.createQuery(hql, Login.class);
		query.setParameter(0, userName);
		query.setParameter(1, password);
		
		List<Login> list = query.getResultList();
		HibernateSessionFactory.closeSession();
		if(list !=null && list.size() > 0) {
			Login login = (Login)list.get(0);
			return login;
		}
		
		return null;
	}
	
	public Login getLoginByUserId(int UserId, int role) {
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "from Login where user_id=?0 and role=?1 and status=1";
		Query<Login> query = session.createQuery(hql, Login.class);
		query.setParameter(0, UserId);
		query.setParameter(1, role);
		
		List<Login> list = query.getResultList();
		HibernateSessionFactory.closeSession();
		if(list !=null && list.size() > 0) {
			Login login = (Login)list.get(0);
			return login;
		}
		
		return null;
	}
	
	public int saveLogin(Login login) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int result = 0;
		try {
			result = (int) session.save(login);
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
	 * updateLogin 更新用户登录表
	 */
	public void updateLogin(Login login) {
		
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Login loginEx = session.get(Login.class, login.getId());
			loginEx.setNumber(login.getNumber());
			loginEx.setPassword(login.getPassword());
			loginEx.setUserId(login.getUserId());
			loginEx.setRole(login.getRole());
			loginEx.setStatus(login.getStatus());
			loginEx.setCreatetime(login.getCreatetime());
			loginEx.setLoginTime(login.getLoginTime());
			session.update(loginEx);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
			e.getMessage();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * deleteLogin 删除某登录账号
	 */
	public void deleteLogin(Login login) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(login);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.getMessage();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public Login getLoginById(int id) {
		Session session = HibernateSessionFactory.getSession();
		List<Login> list = session.createQuery("from Login where id=?0", Login.class).setParameter(0, id).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public Login getLoginByNumber(String number) {
		Session session = HibernateSessionFactory.getSession();
		List<Login> list = session.createQuery("from Login where number=?0", Login.class).setParameter(0, number).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * getLoginCountByRole 获得某角色的人数
	 * @param role
	 * @return
	 */
	public int getLoginCountByRole(int role) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Login where role=?0 and status=1";
		Query<Login> query = session.createQuery(hql, Login.class);
		query.setParameter(0, role);
		List<Login> list = query.getResultList();

		HibernateSessionFactory.closeSession();
		
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}
	
	/**
	 * getLoginByPageAndRole 获得某角色的登录列表
	 */
	public List<Login> getLoginByPageAndRole(int begin, int rowsPage, int role) {
		
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Login where role=?0 and status=1";
		Query<Login> query = session.createQuery(hql, Login.class);
		query.setParameter(0, role);
		query.setFirstResult(begin);
		query.setMaxResults(rowsPage);
		List<Login> list = query.getResultList();

		HibernateSessionFactory.closeSession();
		
		return list;
	}
	
	/**
	 * getAllList   获得所有已验证的某角色的登录账号列表
	 * @return
	 */
	public List<Login> getAllList(int role) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Login where role=?0 and status=1";
		Query<Login> query = session.createQuery(hql, Login.class);
		query.setParameter(0, role);
		List<Login> list = query.getResultList();

		HibernateSessionFactory.closeSession();
		
		return list;
	}
	
	/**
	 * getAllListOnApply   获得所有申请状态的账号列表
	 * @return
	 */
	public List<Login> getAllListOnApply() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Login where status=0";
		Query<Login> query = session.createQuery(hql, Login.class);
		List<Login> list = query.getResultList();

		HibernateSessionFactory.closeSession();
		
		return list;
	}
	
	/**
	 * getListOnApply   获得某类角色的申请状态的账号列表
	 * @param role
	 * @return
	 */
	public List<Login> getListOnApply(int role) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from Login where role=?0 and status=0";
		Query<Login> query = session.createQuery(hql, Login.class);
		query.setParameter(0, role);
		List<Login> list = query.getResultList();

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
