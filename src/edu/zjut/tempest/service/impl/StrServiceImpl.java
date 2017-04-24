package edu.zjut.tempest.service.impl;

import edu.zjut.tempest.dao.StrDAO;
import edu.zjut.tempest.entity.StudentTagRelation;
import edu.zjut.tempest.service.StrService;

public class StrServiceImpl implements StrService{

	private StrDAO strDAO;

	public StudentTagRelation getByStuLoginId(int loginId) {
		return strDAO.getByStuLoginId(loginId);
	}
	
	public void updateStr(StudentTagRelation str) {
		strDAO.updateStr(str);
	}
	
	public void saveStr(StudentTagRelation str) {
		strDAO.saveStr(str);
	}
	
	public void deleteStr(StudentTagRelation str) {
		strDAO.deleteStr(str);
	}
	
	public StrDAO getStrDAO() {
		return strDAO;
	}

	public void setStrDAO(StrDAO strDAO) {
		this.strDAO = strDAO;
	}
}
