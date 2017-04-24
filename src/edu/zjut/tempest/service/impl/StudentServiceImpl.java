package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.StudentDAO;
import edu.zjut.tempest.entity.Student;
import edu.zjut.tempest.service.StudentService;

public class StudentServiceImpl implements StudentService {

	private StudentDAO studentDAO;

	public Student getStudentById(int id) {
		return studentDAO.getStudentById(id);
	}
	
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}
	
	public void deleteStudent(Student student) {
		studentDAO.deleteStudent(student);
	}
	
	public int saveStudent(Student student) {
		return studentDAO.saveStudent(student);
	}
	
	/**
	 * getListByName   通过姓名获得项目开发者(学生)的基本信息列表
	 * @param name
	 * @return
	 */
	public List<Student> getListByName(String name) {
		return studentDAO.getListByName(name);
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
}
