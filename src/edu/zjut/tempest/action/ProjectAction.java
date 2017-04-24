package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import edu.zjut.tempest.entity.Project;
import edu.zjut.tempest.entity.ProjectTagRelation;
import edu.zjut.tempest.entity.Tag;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.ProjectService;
import edu.zjut.tempest.service.PtrService;
import edu.zjut.tempest.service.TagService;
import edu.zjut.tempest.service.TeacherService;
import edu.zjut.tempest.service.UprService;

public class ProjectAction {

	private ProjectService projectService;
	private TeacherService teacherService;
	private LoginService loginService;
	private PtrService ptrService;
	private TagService tagService;
	private UprService uprService;
	
	private Integer id;
	private String name;
	private int num;
	private String period;
	private String keyword;
	private String description;
	private String requirement;
	private int teacherId;
	private int status;
	private int selectCount;
	private Date createtime;
	
	private int loginId;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private String tag5;
	private String tag6;
	
	private int stuLoginId;

	private static int currPage = 1;
	private static int rowsPage = 10;
	private static List<Project> recommendList;
	/**
	 * project_save 新建项目
	 */
	public void project_save() {
		
		try {
			Project project = new Project();
			
			project.setName(name);
			project.setNum(num);
			project.setPeriod(period);
			project.setKeyword(keyword);
			project.setTeacherId(teacherId);
			project.setDescription(description);
			project.setRequirement(requirement);
			project.setStatus(0);
			project.setSelectCount(0);
			project.setCreatetime(new Date());
			
			int id = projectService.saveProject(project);
			
			ProjectTagRelation ptr = new ProjectTagRelation();
			ptr.setProjectId(id);
			
			//添加相应标签，并且标签数量加1
			if(!tag1.equals("")) {
				Tag tag = tagService.getTagByName(tag1);
				ptr.setTagId1(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag2.equals("")) {
				Tag tag = tagService.getTagByName(tag2);
				ptr.setTagId2(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag3.equals("")) {
				Tag tag = tagService.getTagByName(tag3);
				ptr.setTagId3(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag4.equals("")) {
				Tag tag = tagService.getTagByName(tag4);
				ptr.setTagId4(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag5.equals("")) {
				Tag tag = tagService.getTagByName(tag5);
				ptr.setTagId5(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag6.equals("")) {
				Tag tag = tagService.getTagByName(tag6);
				ptr.setTagId6(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			
			ptrService.savePtr(ptr);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * project_getListByTeacherId 通过TeacherId分页获得项目列表
	 * @return
	 */
	public String project_getListByTeacherId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = projectService.getProjectCountByTeacherId(teacherId);   //获得项目发布者的人数
		int totalPage=totalRows/rowsPage;//总页数
		totalPage = (totalRows%rowsPage)>0?totalPage+1:totalPage;
		
		if(totalRows != 0) {
			if (request.getParameter("currPage")!=null) {
				try { 
					currPage=Integer.parseInt(request.getParameter("currPage"));//当前显示第几页
					if(currPage < 1) {
						currPage = 1;
					} else if(currPage > totalPage) {
						currPage = totalPage;
					}
				} catch(Exception e) { 
					currPage=1;//当前显示第几页
				}
			} else {
				currPage=1;//当前显示第几页
			}		
			int begin = (currPage - 1) * rowsPage;
			List<Project> projectList = projectService.getProjectListByTeacherId(begin, rowsPage, teacherId);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0; i<projectList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", projectList.get(i).getId());
				map.put("name", projectList.get(i).getName());
				map.put("num", projectList.get(i).getNum());
				map.put("period", projectList.get(i).getPeriod());
				map.put("createtime", sdf.format(projectList.get(i).getCreatetime()));
				map.put("status", projectList.get(i).getStatus());
				
				list.add(map);
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getListByTeacherId_success";
		}
		
		return "getListByTeacherId_success";
	}

	public String project_getListByStuLoginId() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = projectService.getProjectCountByStuLoginId(stuLoginId);   //获得项目发布者的人数
		int totalPage=totalRows/rowsPage;//总页数
		totalPage = (totalRows%rowsPage)>0?totalPage+1:totalPage;
		if(totalRows != 0) {
			if (request.getParameter("currPage")!=null) {
				try { 
					currPage=Integer.parseInt(request.getParameter("currPage"));//当前显示第几页
					if(currPage < 1) {
						currPage = 1;
					} else if(currPage > totalPage) {
						currPage = totalPage;
					}
				} catch(Exception e) { 
					currPage=1;//当前显示第几页
				}
			} else {
				currPage=1;//当前显示第几页
			}		
			int begin = (currPage - 1) * rowsPage;
			List<Project> projectList = projectService.getProjectListByStuLoginId(begin, rowsPage, stuLoginId);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0; i<projectList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", projectList.get(i).getId());
				map.put("name", projectList.get(i).getName());
				map.put("num", projectList.get(i).getNum());
				map.put("period", projectList.get(i).getPeriod());
				map.put("createtime", sdf.format(projectList.get(i).getCreatetime()));
				map.put("status", projectList.get(i).getStatus());
				
				list.add(map);
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getListByStuLoginId_success";
		}
		
		return "getListByStuLoginId_success";
	}
	/**
	 * project_getByProjectId 通过项目id得到该项目 
	 * @throws IOException 
	 */
	public void project_getByProjectId() throws IOException {
		
		Project project = projectService.getProjectById(id);
		
		if(project != null) {
			HttpServletResponse  response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			
			map.put("id", project.getId());
			map.put("teaLoginId", loginService.getLoginByUserId(project.getTeacherId(), 2).getId());
			map.put("name", project.getName());
			map.put("teaName", teacherService.getTeacherById(project.getTeacherId()).getName());
			map.put("num", project.getNum());
			map.put("period", project.getPeriod());
			map.put("selectCount", project.getSelectCount());
			map.put("createtime", project.getCreatetime());
			map.put("status", project.getStatus());
			map.put("description", project.getDescription());
			map.put("requirement", project.getRequirement());
			map.put("keyword", project.getKeyword());
			
			String jo = JSON.toJSONString(map);
			out.print(jo);
			out.flush();
			out.close();
		}
	}
	
	/**
	 * project_recommend 向项目开发者推荐项目
	 * @return
	 */
	public String project_recommend() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		recommendList = projectService.getProjectByRecommed(stuLoginId);
		if(recommendList != null && recommendList.size() > 0) {
			int totalRows = recommendList.size(); //总行数
			
			int totalPage=totalRows/rowsPage;//总页数
			totalPage=(totalRows%rowsPage)>0?totalPage+1:totalPage;
			
//			System.out.println(totalRows);
			
			if (request.getParameter("currPage")!=null) {
				try { 
					currPage=Integer.parseInt(request.getParameter("currPage"));//当前显示第几页
					if(currPage < 1) {
						currPage = 1;
					} else if(currPage > totalPage) {
						currPage = totalPage;
					}
				} catch(Exception e) { 
					currPage=1;//当前显示第几页
				}
			} else {
				currPage=1;//当前显示第几页
			}		
			
			int begin = (currPage-1)*rowsPage;//计算分页开始行
			int end = currPage*rowsPage;
			if(end > totalRows) {
				end = totalRows;
			}
			
			List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=begin; i<end; i++) {
				Map<String, Object> map = new LinkedHashMap <String, Object>();
				map.put("id", recommendList.get(i).getId());
				map.put("name", recommendList.get(i).getName());
				map.put("teaName", teacherService.getTeacherById(recommendList.get(i).getTeacherId()).getName());
				map.put("num", recommendList.get(i).getNum());
				map.put("period", recommendList.get(i).getPeriod());
				map.put("createtime", sdf.format(recommendList.get(i).getCreatetime()));
				if(uprService.getUPR(recommendList.get(i).getId(), stuLoginId) != null) {
					if(uprService.getUPR(recommendList.get(i).getId(), stuLoginId).getStatus() == 0) {
						map.put("isApply", 2);
					} else {
						map.put("isApply", 1);
					}
					
				} else {
					map.put("isApply", 0);
				}
				
				newList.add(map);				
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", newList);
			
			return "recommend_success";
		}
		
		return "recommend_success";
	}
	
	/**
	 * project_start 开始项目
	 */
	public void project_start() {
		if(id != null) {
			Project project = projectService.getProjectById(id);
			
			if(project != null) {
				project.setStatus(1);   //项目状态设置为进行中
				projectService.updateProject(project);
			}
		}
	}
	
	/**
	 * project_remove 删除项目
	 */
	public void project_remove() {
		if(id != null) {
			Project project  = projectService.getProjectById(id);
			
			if(project != null) {
				
				//删除项目和标签的中间表
				ProjectTagRelation ptr = ptrService.getProjectByProjectId(id);
				
				if(ptr.getTagId1() != null) {
					Tag tag = tagService.getTagById(ptr.getTagId1());
					tag.setNum(tag.getNum()-1);
					tagService.updateTag(tag);
				}
				if(ptr.getTagId2() != null) {
					Tag tag = tagService.getTagById(ptr.getTagId2());
					tag.setNum(tag.getNum()-1);
					tagService.updateTag(tag);
				}
				if(ptr.getTagId3() != null) {
					Tag tag = tagService.getTagById(ptr.getTagId3());
					tag.setNum(tag.getNum()-1);
					tagService.updateTag(tag);
				}
				if(ptr.getTagId4() != null) {
					Tag tag = tagService.getTagById(ptr.getTagId4());
					tag.setNum(tag.getNum()-1);
					tagService.updateTag(tag);
				}
				if(ptr.getTagId5() != null) {
					Tag tag = tagService.getTagById(ptr.getTagId5());
					tag.setNum(tag.getNum()-1);
					tagService.updateTag(tag);
				}
				if(ptr.getTagId6() != null) {
					Tag tag = tagService.getTagById(ptr.getTagId6());
					tag.setNum(tag.getNum()-1);
					tagService.updateTag(tag);
				}
				
				ptrService.deletePtr(ptr);
				
				//删除项目和用户的中间表
				List<UserProjectRelation> list = uprService.getUPRList(id);
				
				for(UserProjectRelation upr : list) {
					uprService.deleteUPR(upr);
				}
				projectService.deleteProject(project);
			}
		}
	}
	
	/**
	 * project_finish 完成项目
	 */
	public void project_finish() {
		if(id != null) {
			Project project = projectService.getProjectById(id);
			
			if(project != null) {
				project.setStatus(2);   //项目状态设置为已完成
				projectService.updateProject(project);
			}
		}
	}
	
	public String project_getAllList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = projectService.getProjectCount();   //获得项目发布者的人数
		int totalPage=totalRows/rowsPage;//总页数
		totalPage = (totalRows%rowsPage)>0?totalPage+1:totalPage;
		if(totalRows != 0) {
			if (request.getParameter("currPage")!=null) {
				try { 
					currPage=Integer.parseInt(request.getParameter("currPage"));//当前显示第几页
					if(currPage < 1) {
						currPage = 1;
					} else if(currPage > totalPage) {
						currPage = totalPage;
					}
				} catch(Exception e) { 
					currPage=1;//当前显示第几页
				}
			} else {
				currPage=1;//当前显示第几页
			}		
			int begin = (currPage - 1) * rowsPage;
			List<Project> projectList = projectService.getProjectAllList(begin, rowsPage);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0; i<projectList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", projectList.get(i).getId());
				map.put("name", projectList.get(i).getName());
				map.put("num", projectList.get(i).getNum());
				map.put("period", projectList.get(i).getPeriod());
				map.put("createtime", sdf.format(projectList.get(i).getCreatetime()));
				map.put("status", projectList.get(i).getStatus());
				
				list.add(map);
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getAllList_success";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTag3() {
		return tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}

	public String getTag4() {
		return tag4;
	}

	public void setTag4(String tag4) {
		this.tag4 = tag4;
	}

	public String getTag5() {
		return tag5;
	}

	public void setTag5(String tag5) {
		this.tag5 = tag5;
	}

	public String getTag6() {
		return tag6;
	}

	public void setTag6(String tag6) {
		this.tag6 = tag6;
	}
	
	public int getStuLoginId() {
		return stuLoginId;
	}

	public void setStuLoginId(int stuLoginId) {
		this.stuLoginId = stuLoginId;
	}
	
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public PtrService getPtrService() {
		return ptrService;
	}

	public void setPtrService(PtrService ptrService) {
		this.ptrService = ptrService;
	}


	public TagService getTagService() {
		return tagService;
	}


	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
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

}
