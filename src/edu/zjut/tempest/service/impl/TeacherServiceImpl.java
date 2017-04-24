package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.TeacherDAO;
import edu.zjut.tempest.entity.Teacher;
import edu.zjut.tempest.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{
	
	private TeacherDAO teacherDAO;

	public Teacher getTeacherById(int id) {
		return teacherDAO.getTeacherById(id);
	}
	
	public int saveTeacher(Teacher teacher) {
		return teacherDAO.saveTeacher(teacher);
	}
	
	public void updateTeacher(Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
	}
	
	public void deleteTeacher(Teacher teacher) {
		teacherDAO.deleteTeacher(teacher);
	}
	
	/**
	 * getListByName   通过姓名获得项目发布者(教师)的信息列表
	 * @param name
	 * @return
	 */
	public List<Teacher> getListByName(String name) {
		return teacherDAO.getListByName(name);
	}
	
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
}
