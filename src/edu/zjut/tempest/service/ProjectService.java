package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.Project;

public interface ProjectService {

	public int saveProject(Project project);
	
	public void updateProject(Project project);
	
	public void deleteProject(Project project);
	
	public Project getProjectById(int id);
	
	public List<Project> getProjectByRecommed(int loginId);
	
	/**
	 * getProjectCountByTeaLoginId 获得某项目发布者的项目数
	 * @param loginId
	 * @return
	 */
	public int getProjectCountByTeacherId(int teacherId);
	
	/**
	 * getProjectListByTeaLoginId 分页获得某项目发布者的项目列表
	 * @param begin
	 * @param rowsPage
	 * @param loginId
	 * @return
	 */
	public List<Project> getProjectListByTeacherId(int begin, int rowsPage, int teacherId);
	
	/**
	 * getProjectCountByStuLoginId 获得项目开发者的项目数
	 * @param stuLoginId
	 * @return
	 */
	public int getProjectCountByStuLoginId(int stuLoginId);
	
	/**
	 * getProjectListByStuLoginId 分页获得某项目开发者的项目列表
	 * @param begin
	 * @param rowsPage
	 * @param stuLoginId
	 * @return
	 */
	public List<Project> getProjectListByStuLoginId(int begin, int rowsPage, int stuLoginId);
	
	public int getProjectCount();
	
	public List<Project> getProjectAllList(int begin, int rowsPage);
}
