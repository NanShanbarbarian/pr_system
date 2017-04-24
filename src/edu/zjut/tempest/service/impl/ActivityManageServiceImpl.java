package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.ActivityManageDAO;
import edu.zjut.tempest.entity.ActivityManage;
import edu.zjut.tempest.service.ActivityManageService;

public class ActivityManageServiceImpl implements ActivityManageService {

	private ActivityManageDAO amDAO;
	
	/**
	 * getById   通过id获得系统活动管理记录表
	 * @param id
	 * @return
	 */
	public ActivityManage getById(int id) {
		return amDAO.getById(id);
	}
	
	/**
	 * getByName   通过name获得系统活动管理记录表
	 * @param name
	 * @return
	 */
	public ActivityManage getByName(String name) {
		return amDAO.getByName(name);
	}
	
	/**
	 * getAllList   获得所有系统活动管理记录列表
	 * @return
	 */
	public List<ActivityManage> getAllList() {
		return amDAO.getAllList();
	}
	
	public void updateAM(ActivityManage am) {
		amDAO.updateAM(am);
	}

	public ActivityManageDAO getAmDAO() {
		return amDAO;
	}

	public void setAmDAO(ActivityManageDAO amDAO) {
		this.amDAO = amDAO;
	}
}
