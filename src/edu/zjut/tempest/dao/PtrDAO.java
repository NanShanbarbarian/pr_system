package edu.zjut.tempest.dao;

import edu.zjut.tempest.entity.ProjectTagRelation;

public interface PtrDAO {

	public void savePtr(ProjectTagRelation ptr);
	
	public ProjectTagRelation getProjectByProjectId(int projectId);
	
	public void deletePtr(ProjectTagRelation ptr);
}
