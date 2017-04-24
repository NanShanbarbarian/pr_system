package edu.zjut.tempest.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import edu.zjut.tempest.dao.ProjectDAO;
import edu.zjut.tempest.entity.Project;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.util.HibernateSessionFactory;

public class ProjectDAOImpl implements ProjectDAO{

	private SessionFactory sessionFactory;
	
	public int saveProject(Project project) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		int count = 0;
		try {
			count = (Integer)session.save(project);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return count;
	}
	
	public void updateProject(Project project) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(project);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void deleteProject(Project project) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.delete(project);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public Project getProjectById(int id) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Project> list = session.createQuery("from Project where id=?0", Project.class).setParameter(0, id).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * getListByStatus 通过项目状态获得相应的项目列表
	 * @param status
	 * @return
	 */
	public List<Project> getListByStatus(int status) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Project> list = session.createQuery("from Project where status=?0", Project.class).setParameter(0, status).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int getProjectCountByTeacherId(int teacherId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Project> list = session.createQuery("from Project where teacher_id=?0", Project.class).setParameter(0, teacherId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}

	public List<Project> getProjectListByTeacherId(int begin, int rowsPage, int teacherId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Project> list = session.createQuery("from Project where teacher_id=?0 order by createtime desc", Project.class).setParameter(0, teacherId).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		return list;
	}
	
	/**
	 * getProjectCountByStuLoginId 获得项目开发者的项目数
	 * @param stuLoginId
	 * @return
	 */
	public int getProjectCountByStuLoginId(int stuLoginId) {
		Session session = HibernateSessionFactory.getSession();
		List<UserProjectRelation> list = session.createQuery("from UserProjectRelation where stu_login_id=?0", UserProjectRelation.class).setParameter(0, stuLoginId).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}
	
	/**
	 * getProjectListByStuLoginId 分页获得某项目开发者的项目列表
	 * @param begin
	 * @param rowsPage
	 * @param stuLoginId
	 * @return
	 */
	public List<Project> getProjectListByStuLoginId(int begin, int rowsPage, int stuLoginId) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Project> list = session.createQuery("select p from Project as p,UserProjectRelation as upr where p.id=upr.projectId and upr.status=0 and upr.stuLoginId=?0 order by p.createtime desc", Project.class).setParameter(0, stuLoginId).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int getProjectCount() {
		Session session = HibernateSessionFactory.getSession();
		List<UserProjectRelation> list = session.createQuery("from UserProjectRelation", UserProjectRelation.class).getResultList();
		
		HibernateSessionFactory.closeSession();
		if(list != null && list.size() > 0) {
			return list.size();
		}
		return 0;
	}
	
	public List<Project> getProjectAllList(int begin, int rowsPage) {
		Session session = HibernateSessionFactory.getSession();
		
		List<Project> list = session.createQuery("select p from Project as p,UserProjectRelation as upr where p.id=upr.projectId order by p.createtime desc", Project.class).setFirstResult(begin).setMaxResults(rowsPage).getResultList();
		
		HibernateSessionFactory.closeSession();
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
