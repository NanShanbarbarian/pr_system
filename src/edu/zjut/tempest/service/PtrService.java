package edu.zjut.tempest.service;

import edu.zjut.tempest.entity.ProjectTagRelation;

public interface PtrService {

	public void savePtr(ProjectTagRelation ptr);
	
	public ProjectTagRelation getProjectByProjectId(int projectId);
	
	public void deletePtr(ProjectTagRelation ptr);
	
	public void updatePtr(ProjectTagRelation ptr);
}
