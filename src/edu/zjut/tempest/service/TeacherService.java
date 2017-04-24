package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.Teacher;

public interface TeacherService {

	public Teacher getTeacherById(int id);
	
	public int saveTeacher(Teacher teacher);
	public void updateTeacher(Teacher teacher);
	public void deleteTeacher(Teacher teacher);
	
	/**
	 * getListByName   通过姓名获得项目发布者(教师)的信息列表
	 * @param name
	 * @return
	 */
	public List<Teacher> getListByName(String name);
}
