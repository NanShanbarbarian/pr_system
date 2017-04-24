package edu.zjut.tempest.dao;

import edu.zjut.tempest.entity.StudentTagRelation;

public interface StrDAO {

	/**
	 * getByStuLoginId 通过项目开发者登录id获得该用户的标签关联
	 * @param loginId
	 * @return
	 */
	public StudentTagRelation getByStuLoginId(int loginId);
	
	public void updateStr(StudentTagRelation str);
	
	public void saveStr(StudentTagRelation str);
	
	public void deleteStr(StudentTagRelation str);
}
