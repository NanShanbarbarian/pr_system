package edu.zjut.tempest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import edu.zjut.tempest.algorithm.ProjectData;
import edu.zjut.tempest.algorithm.ProjectRecommend;
import edu.zjut.tempest.dao.ProjectDAO;
import edu.zjut.tempest.dao.PtrDAO;
import edu.zjut.tempest.dao.StrDAO;
import edu.zjut.tempest.dao.TagDAO;
import edu.zjut.tempest.dao.UprDAO;
import edu.zjut.tempest.entity.Project;
import edu.zjut.tempest.entity.ProjectTagRelation;
import edu.zjut.tempest.entity.StudentTagRelation;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.service.ProjectService;

public class ProjectServiceImpl implements ProjectService{

	private ProjectDAO projectDAO;
	private StrDAO strDAO;
	private TagDAO tagDAO;
	private PtrDAO ptrDAO;
	private UprDAO uprDAO;
	
	static float α1 = 0.4f;
	static float α2 = 0.6f;
	
	private List<Project> npList;
	private List<Project> jpList;
	
	private static int N = 20;  //推荐数
	
	public int saveProject(Project project) {
		return projectDAO.saveProject(project);
	}
	
	public void updateProject(Project project) {
		projectDAO.updateProject(project);
	}
	
	public void deleteProject(Project project) {
		projectDAO.deleteProject(project);
	}
	
	public Project getProjectById(int id) {
		return projectDAO.getProjectById(id);
	}
	
	public List<Project> getProjectByRecommed(int loginId) {
		npList = projectDAO.getListByStatus(0);
		List<UserProjectRelation> uprList = uprDAO.getListByStuLoginId(loginId);
		
		if(uprList != null && uprList.size() > 0) {
			jpList =  new ArrayList<Project>();
			for(UserProjectRelation upr : uprList) {
				jpList.add(projectDAO.getProjectById(upr.getProjectId()));
			}
		}
		
		//推荐算法
		List<ProjectData> cfList = new ArrayList<ProjectData>();
		List<ProjectData> crList = new ArrayList<ProjectData>();
		
		ProjectRecommend pr = new ProjectRecommend();
		if(npList.size() > 0 && jpList.size() > 0) {
			cfList = pr.projectCF(jpList, npList);
		}
		
		crList = this.projectCR(npList, loginId);
		
		List<ProjectData> pdList = new ArrayList<ProjectData>();
		for(int i=0; i<cfList.size(); i++) {
			ProjectData pd = new ProjectData();
			pd.projectId = cfList.get(i).projectId;
			for(int j=0; j<crList.size(); j++) {
				if(cfList.get(i).projectId == crList.get(j).projectId) {
					pd.score = cfList.get(i).score + crList.get(j).score;
				}
			}
			pdList.add(pd);
		}
		
		Collections.sort(pdList,new Comparator<ProjectData>() {
			//降序排列
			public int compare(ProjectData o1,
					ProjectData o2) {
				if(o1.score > o2.score) {
					return -1;
				} else if(o1.score < o2.score) {
					return 1;
				}	
				return 0;
			}
			
		});
		
		//排序后的项目集合
		List<Project> pList = new ArrayList<Project>();
		for(int i=0; i<N; i++) {
			if(i < pdList.size()) {
				pList.add(projectDAO.getProjectById(pdList.get(i).projectId));
			}
		}
		
		return pList;
	}
	
	/**
	 * getProjectCountByTeaLoginId 获得某项目发布者的项目数
	 * @param loginId
	 * @return
	 */
	public int getProjectCountByTeacherId(int teacherId) {
		return projectDAO.getProjectCountByTeacherId(teacherId);
	}
	
	/**
	 * getProjectListByTeaLoginId 分页获得某项目发布者的项目列表
	 * @param begin
	 * @param rowsPage
	 * @param loginId
	 * @return
	 */
	public List<Project> getProjectListByTeacherId(int begin, int rowsPage, int teacherId) {
		return projectDAO.getProjectListByTeacherId(begin, rowsPage, teacherId);
	}
	
	/**
	 * getProjectCountByStuLoginId 获得项目开发者的项目数
	 * @param stuLoginId
	 * @return
	 */
	public int getProjectCountByStuLoginId(int stuLoginId) {
		return projectDAO.getProjectCountByStuLoginId(stuLoginId);
	}
	
	/**
	 * getProjectListByStuLoginId 分页获得某项目开发者的项目列表
	 * @param begin
	 * @param rowsPage
	 * @param stuLoginId
	 * @return
	 */
	public List<Project> getProjectListByStuLoginId(int begin, int rowsPage, int stuLoginId) {
		return projectDAO.getProjectListByStuLoginId(begin, rowsPage, stuLoginId);
	}
	
	public int getProjectCount() {
		return projectDAO.getProjectCount();
	}
	
	public List<Project> getProjectAllList(int begin, int rowsPage) {
		return projectDAO.getProjectAllList(begin, rowsPage);
	}
	
	//基于内容(标签)的推荐算法
	public List<ProjectData> projectCR(List<Project> npList, int loginId) {
		float W1 = 0.6f;
		float W2 = 0.4f;
		
		StudentTagRelation str = strDAO.getByStuLoginId(loginId);
		List<String> stuTagList = new ArrayList<String>();
		if(str.getTagId1() != null) {
			stuTagList.add(tagDAO.getTagById(str.getTagId1()).getName());
		}
		if(str.getTagId2() != null) {
			stuTagList.add(tagDAO.getTagById(str.getTagId2()).getName());
		}
		if(str.getTagId3() != null) {
			stuTagList.add(tagDAO.getTagById(str.getTagId3()).getName());
		}
		if(str.getTagId4() != null) {
			stuTagList.add(tagDAO.getTagById(str.getTagId4()).getName());
		}
		if(str.getTagId5() != null) {
			stuTagList.add(tagDAO.getTagById(str.getTagId5()).getName());
		}
		if(str.getTagId6() != null) {
			stuTagList.add(tagDAO.getTagById(str.getTagId6()).getName());
		}
		
		List<ProjectData> crList = new ArrayList<ProjectData>();
		
		//给每个项目进行推荐评分
		for(int i=0; i<npList.size(); i++) {
			float score = 0;
			int num = 0;
			
			//1.标签
			//获得项目的标签列表
			ProjectTagRelation ptr = ptrDAO.getProjectByProjectId(npList.get(i).getId());
			List<String> proTagList = new ArrayList<String>(); 
			if(ptr.getTagId1() != null) {
				proTagList.add(tagDAO.getTagById(ptr.getTagId1()).getName());
			}
			if(ptr.getTagId2() != null) {
				proTagList.add(tagDAO.getTagById(ptr.getTagId2()).getName());
			}
			if(ptr.getTagId3() != null) {
				proTagList.add(tagDAO.getTagById(ptr.getTagId3()).getName());
			}
			if(ptr.getTagId4() != null) {
				proTagList.add(tagDAO.getTagById(ptr.getTagId4()).getName());
			}
			if(ptr.getTagId5() != null) {
				proTagList.add(tagDAO.getTagById(ptr.getTagId5()).getName());
			}
			if(ptr.getTagId6() != null) {
				proTagList.add(tagDAO.getTagById(ptr.getTagId6()).getName());
			}
			String keyword = npList.get(i).getKeyword();	//获得备注关键词
			for(int j=0; j<stuTagList.size(); j++) {
				if(proTagList != null && proTagList.size() > 0) {
					for(int k=0; k<proTagList.size(); k++) {
						if(stuTagList.get(j).equals(proTagList.get(k)))
							num++;
					}
				}
				if(keyword != null && keyword != "") {
					if(keyword.indexOf(stuTagList.get(j)) > -1)
						num++;
				}
			}
			
			score = (float)(W1 * num / stuTagList.size());
			
			//2.创建时间				
			Date createtime = npList.get(i).getCreatetime();
			Date nowtime = new Date();
			long between = (nowtime.getTime()-createtime.getTime())/1000;//除以1000是为了转换成秒
			long day = between/(24*3600);
			if(day <= 3) {
				score += (float) (W2 * 1.0);
			} else if( day>7 && day<=14) {
				score += (float)(W2 * 0.8);
			} else if( day>14 && day<=30) {
				score += (float)(W2 * 0.5);
			} else if( day>30 ) {
				score +=(float)(W2 * 0.3);
			}
			
			ProjectData pd = new ProjectData();
			pd.projectId = npList.get(i).getId();
			pd.score = score;
			
			crList.add(pd);
		}
		return crList;
	}
	
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}
	
	public StrDAO getStrDAO() {
		return strDAO;
	}

	public void setStrDAO(StrDAO strDAO) {
		this.strDAO = strDAO;
	}

	public PtrDAO getPtrDAO() {
		return ptrDAO;
	}

	public void setPtrDAO(PtrDAO ptrDAO) {
		this.ptrDAO = ptrDAO;
	}
	
	public UprDAO getUprDAO() {
		return uprDAO;
	}

	public void setUprDAO(UprDAO uprDAO) {
		this.uprDAO = uprDAO;
	}

}
