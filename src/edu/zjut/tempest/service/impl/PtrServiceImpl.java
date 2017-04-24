package edu.zjut.tempest.service.impl;

import edu.zjut.tempest.dao.PtrDAO;
import edu.zjut.tempest.entity.ProjectTagRelation;
import edu.zjut.tempest.service.PtrService;

public class PtrServiceImpl implements PtrService{

	private PtrDAO ptrDAO;

	public void savePtr(ProjectTagRelation ptr) {
		ptrDAO.savePtr(ptr);
	}
	
	public ProjectTagRelation getProjectByProjectId(int projectId) {
		return ptrDAO.getProjectByProjectId(projectId);
	}
	
	public void deletePtr(ProjectTagRelation ptr) {
		ptrDAO.deletePtr(ptr);
	}
	
	public PtrDAO getPtrDAO() {
		return ptrDAO;
	}

	public void setPtrDAO(PtrDAO ptrDAO) {
		this.ptrDAO = ptrDAO;
	}
}
