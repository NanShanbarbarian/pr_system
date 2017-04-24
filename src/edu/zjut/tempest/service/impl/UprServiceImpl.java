package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.UprDAO;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.service.UprService;

public class UprServiceImpl implements UprService{

	private UprDAO uprDAO;

	/**
	 * getUPR 通过项目id和项目开发者登录id获得相应的关联数据
	 * @param projectId
	 * @param stuLoginId
	 * @return
	 */
	public UserProjectRelation getUPR(int projectId, int stuLoginId) {
		return uprDAO.getUPR(projectId, stuLoginId);
	}
	
	public void saveUPR(UserProjectRelation upr) {
		uprDAO.saveUPR(upr);
	}
	
	public void updateUPR(UserProjectRelation upr) {
		uprDAO.updateUPR(upr);
	}
	
	public void deleteUPR(UserProjectRelation upr) {
		uprDAO.deleteUPR(upr);
	}
	
	public List<UserProjectRelation> getUPRList(int projectId, int status) {
		return uprDAO.getUPRList(projectId, status);
	}
	
	public List<UserProjectRelation> getUPRList(int projectId) {
		return uprDAO.getUPRList(projectId);
	}
	
	public UprDAO getUprDAO() {
		return uprDAO;
	}

	public void setUprDAO(UprDAO uprDAO) {
		this.uprDAO = uprDAO;
	}
	
}
