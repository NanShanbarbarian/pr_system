package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.ActivityManage;

public interface ActivityManageService {

	/**
	 * getById   通过id获得系统活动管理记录表
	 * @param id
	 * @return
	 */
	public ActivityManage getById(int id);
	
	/**
	 * getByName   通过name获得系统活动管理记录表
	 * @param name
	 * @return
	 */
	public ActivityManage getByName(String name);
	
	/**
	 * getAllList   获得所有系统活动管理记录列表
	 * @return
	 */
	public List<ActivityManage> getAllList();
	public void updateAM(ActivityManage am);
}
