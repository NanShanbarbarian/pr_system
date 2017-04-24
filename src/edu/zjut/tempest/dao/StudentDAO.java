package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.Student;

public interface StudentDAO {

	public Student getStudentById(int id);
	
	public void updateStudent(Student student);
	
	public void deleteStudent(Student student);
	
	public int saveStudent(Student student);
	
	/**
	 * getListByName   通过姓名获得项目开发者(学生)的基本信息列表
	 * @param name
	 * @return
	 */
	public List<Student> getListByName(String name);
	
}
