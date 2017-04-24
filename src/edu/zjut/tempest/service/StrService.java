package edu.zjut.tempest.service;

import edu.zjut.tempest.entity.StudentTagRelation;

public interface StrService {

	public StudentTagRelation getByStuLoginId(int loginId);
	
	public void updateStr(StudentTagRelation str);
	
	public void saveStr(StudentTagRelation str);
	
	public void deleteStr(StudentTagRelation str);
}
