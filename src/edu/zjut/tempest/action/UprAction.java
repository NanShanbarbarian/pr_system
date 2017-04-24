package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import edu.zjut.tempest.entity.Project;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.ProjectService;
import edu.zjut.tempest.service.UprService;

public class UprAction {

	private UprService uprService;
	private LoginService loginService;
	private ProjectService projectService;

	private Integer projectId;
	private Integer stuLoginId;
	private Integer teaLoginId;
	
	private Integer status;
	private Integer stuScore;
	private Integer teaScore;
	private String stuRemark;
	private String teaRemark;
	
	/**
	 * upr_save 新增用户和项目的关联记录
	 */
	public void upr_save() {
		if(projectId !=null && stuLoginId != null) {
			try {
				UserProjectRelation upr = new UserProjectRelation();
				upr.setProjectId(projectId);
				upr.setStuLoginId(stuLoginId);
				upr.setTeaLoginId(loginService.getLoginByUserId(projectService.getProjectById(projectId).getTeacherId(), 2).getId());
				upr.setStatus(1);   //1代表申请
				upr.setStuScore(0);
				upr.setTeaScore(0);
				
				uprService.saveUPR(upr);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * upr_accept 同意开发人员的申请
	 */
	public void upr_accept() {
		if(projectId != null && stuLoginId != null) {
			UserProjectRelation upr = uprService.getUPR(projectId, stuLoginId);
			
			if(upr != null) {
				upr.setStatus(0);
				uprService.updateUPR(upr);
				
				Project project = projectService.getProjectById(projectId);
				project.setSelectCount(project.getSelectCount()+1);
				projectService.updateProject(project);
			}
		}
	}
	
	/**
	 *upr_refuse 拒绝或删除开发人员 
	 */
	public void upr_refuse() {
		if(projectId != null && stuLoginId != null) {
			UserProjectRelation upr = uprService.getUPR(projectId, stuLoginId);
			
			if(upr != null) {
				if(status == 0) {   //若是已选人员删除，则项目表更新
					Project project = projectService.getProjectById(projectId);
					project.setSelectCount(project.getSelectCount()-1);
					projectService.updateProject(project);
				}
				uprService.deleteUPR(upr);
			}
		}
	}
	
	/**
	 * upr_remarkByStudent 开发人员给项目发布者的评论
	 */
	public void upr_remarkByStudent() {
		if(projectId != null && stuLoginId != null) {
			UserProjectRelation upr = uprService.getUPR(projectId, stuLoginId);
			
			if(upr != null) {
				upr.setTeaRemark(teaRemark);
				upr.setTeaScore(teaScore);
				uprService.updateUPR(upr);
			}
		}
	}
	
	/**
	 * upr_remarkByTeacher 项目发布者给开发人员的评论
	 */
	public void upr_remarkByTeacher() {
		if(projectId != null && stuLoginId != null) {
			UserProjectRelation upr = uprService.getUPR(projectId, stuLoginId);
			
			if(upr != null) {
				upr.setStuRemark(stuRemark);
				upr.setStuScore(stuScore);
				uprService.updateUPR(upr);
			}
		}
	}
	
	public void upr_get() throws IOException {
		if(projectId != null && stuLoginId != null) {
			UserProjectRelation upr = uprService.getUPR(projectId, stuLoginId);
			
			if(upr != null) {
				HttpServletResponse  response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				
				map.put("teaScore", upr.getTeaScore());
				if(upr.getTeaRemark() != null) {
					map.put("isEvaluate", 1);
				} else {
					map.put("isEvaluate", 0);
				}
				map.put("teaRemark", upr.getTeaRemark());
				
				String jo = JSON.toJSONString(map);
				out.print(jo);
				out.flush();
				out.close();
			}
		}
	}
	
	public UprService getUprService() {
		return uprService;
	}

	public void setUprService(UprService uprService) {
		this.uprService = uprService;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}


	//参数设置
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getStuLoginId() {
		return stuLoginId;
	}

	public void setStuLoginId(Integer stuLoginId) {
		this.stuLoginId = stuLoginId;
	}

	public Integer getTeaLoginId() {
		return teaLoginId;
	}

	public void setTeaLoginId(Integer teaLoginId) {
		this.teaLoginId = teaLoginId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStuScore() {
		return stuScore;
	}

	public void setStuScore(Integer stuScore) {
		this.stuScore = stuScore;
	}

	public Integer getTeaScore() {
		return teaScore;
	}

	public void setTeaScore(Integer teaScore) {
		this.teaScore = teaScore;
	}
	
	public String getStuRemark() {
		return stuRemark;
	}

	public void setStuRemark(String stuRemark) {
		this.stuRemark = stuRemark;
	}

	public String getTeaRemark() {
		return teaRemark;
	}

	public void setTeaRemark(String teaRemark) {
		this.teaRemark = teaRemark;
	}
}
