package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.entity.Student;
import edu.zjut.tempest.entity.Teacher;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.StudentService;
import edu.zjut.tempest.service.TeacherService;
import edu.zjut.tempest.util.MD5Util;

public class LoginAction {
	
	private LoginService loginService;
	private StudentService studentService;
	private TeacherService teacherService;
	
	private Integer id;
	private String number;
	private String password;
	private String isRemeber;
	private Integer searchType;
    
	private static int currPage = 1;
	private static int rowsPage = 10;

	/**
	 * login 登录判断方法
	 * @return
	 */
	public String login() {
		Login login = loginService.findLogin(number , MD5Util.getMD5(password));
		
		//当用户存在，并且账号状态正常的时候才能登录成功
		if(login !=null && login.getStatus() == 1) {
			HttpServletResponse response = ServletActionContext.getResponse();
			HttpSession session = ServletActionContext.getRequest().getSession();
			
			login.setLoginTime(new Timestamp(System.currentTimeMillis()));
			loginService.updateLogin(login);
			//添加session
			session.setAttribute("login", login);
			//添加cookie
			if( isRemeber != null && isRemeber.equals("on")) {
				Cookie name = new Cookie("number",number);
				Cookie pwd = new Cookie("password",password);
				Cookie check = new Cookie("isRemeber","checked");
				response.addCookie(name);
				response.addCookie(pwd);
				response.addCookie(check);
				name.setMaxAge(1000 *60 *60 *24);
				pwd.setMaxAge(1000 *60 *60 *24);
				check.setMaxAge(1000 *60 *60 *24);			
			} else {
				Cookie name = new Cookie("number","");
				Cookie pwd = new Cookie("password","");
				Cookie check = new Cookie("isRemeber","");
				response.addCookie(name);
				response.addCookie(pwd);
				response.addCookie(check);		
			}
			
			if(login.getRole() == 0) {   //role=0代表超级管理员
				Teacher tea = teacherService.getTeacherById(login.getUserId());
				session.setAttribute("user", tea);
				return "login_success";
			} else if(login.getRole() == 1) {   //role=1代表管理员
				Teacher tea = teacherService.getTeacherById(login.getUserId());
				session.setAttribute("user", tea);
				return "login_success";
			} else if(login.getRole() == 2) {   //role=2代表老师
				Teacher tea = teacherService.getTeacherById(login.getUserId());
				session.setAttribute("user", tea);
				return "login_success";
			} else if(login.getRole() == 3) {   //role=3代表学生
				Student stu = studentService.getStudentById(login.getUserId());
				session.setAttribute("user", stu);
				return "login_success";
			}
		}
		
		return "login_error";
	}
	
	/**
	 * login_out 注销方法
	 * @return
	 */
	public String login_out() {
		HttpSession session =  ServletActionContext.getRequest().getSession();
		if(session != null) {
			session.invalidate();	//注销session
			return "login_out_success";
		}
		else {
			return "login_out_error";
		}
	}
	
	/**
	 * login_isPassword 判断是否为当前密码
	 * @throws IOException
	 */
	public void login_isPassword() throws IOException {
		HttpServletResponse  response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Login login = loginService.getLoginById(id);
		
		int result = 0;
		if(login.getPassword().equals(MD5Util.getMD5(password))) {
			result = 1;
		}
		
		out.print(result);
		out.flush();
		out.close();
	}
	
	/**
	 * login_alterPassword 修改登录密码
	 */
	public void login_alterPassword() {
		Login login = loginService.getLoginById(id);
		
		login.setPassword(MD5Util.getMD5(password));
		loginService.updateLogin(login);
		
	}
	
	/**
	 * login_getListOnApply   获得所有申请人员列表
	 * @return
	 */
	public String login_getListOnApply() {
		if(searchType!=null) {
			List<Login> loginList = new ArrayList<Login>();
			if(searchType == 0) {   //所有申请人员
				loginList = loginService.getAllListOnApply();
				
			} else if(searchType == 1) {   //项目开发者
				loginList = loginService.getListOnApply(3);
			} else if(searchType == 2) {   //项目发布者
				loginList = loginService.getListOnApply(2);
			} else {
				loginList = null;
			}
			
			if(loginList!=null && loginList.size()>0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int totalRows = loginList.size();   //查询结果的长度
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
					int end = begin + rowsPage - 1;
					
					List<Login> list = new ArrayList<Login>();
					
					for(int i=begin; i <= end; i++)
					{
						if(i < loginList.size()) {
							list.add(loginList.get(i));
						}
					}
					
					List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
					for(int i=0; i<list.size(); i++) {
						Map<String, Object> map = new LinkedHashMap<String, Object>();
						
						if(list.get(i).getRole() == 3) {
							Student student = studentService.getStudentById(loginList.get(i).getUserId());
							map.put("loginId", list.get(i).getId());
							map.put("role", list.get(i).getRole());
							map.put("id", list.get(i).getUserId());
							map.put("name", student.getName());
							map.put("number", list.get(i).getNumber());
							map.put("sex", student.getSex());
							map.put("academy", student.getAcademy());
							map.put("level", student.getLevel());
							map.put("telephone", student.getTelephone());
							map.put("cornet", student.getCornet());
							map.put("qq", student.getQq());
							map.put("email", student.getEmail());
						} else if(list.get(i).getRole() == 2) {
							Teacher teacher = teacherService.getTeacherById(loginList.get(i).getUserId());
							map.put("loginId", list.get(i).getId());
							map.put("role", list.get(i).getRole());
							map.put("id", list.get(i).getUserId());
							map.put("name", teacher.getName());
							map.put("number", list.get(i).getNumber());
							map.put("sex", teacher.getSex());
							map.put("academy", teacher.getAcademy());
							map.put("level", teacher.getLevel());
							map.put("telephone", teacher.getTelephone());
							map.put("cornet", teacher.getCornet());
							map.put("qq", teacher.getQq());
							map.put("email", teacher.getEmail());
						}
						
						newList.add(map);
					}
					
					request.setAttribute("currPage", currPage);
					request.setAttribute("rowsPage", rowsPage);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("list", newList);
				}
			}
		}
		
		return "getListOnApply_success";
	}
	
	/*
	 * login_getById   查询某个账号的基本信息
	 */
	public String login_getById() {
		if(id != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			Login login = loginService.getLoginById(id);
			
			if(login != null) {
				if(login.getRole() == 2) {
					Teacher teacher = teacherService.getTeacherById(login.getUserId());
					request.setAttribute("user", teacher);
				} else if(login.getRole() == 3) {
					Student student = studentService.getStudentById(login.getUserId());
					request.setAttribute("user", student);
				}
				request.setAttribute("login", login);
			}
		}
		
		return "getById_success";
	}
	
	/*
	 * login_accept   验证账号申请
	 */
	public void login_accept() {
		try {
			Login login = loginService.getLoginById(id);
			
			login.setStatus(1);   //验证状态
			
			loginService.updateLogin(login);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * login_remove   拒绝账号申请(删除账号)
	 */
	public void login_remove() {
		try {
			Login login = loginService.getLoginById(id);
			if(login.getRole() == 2) {
				Teacher teacher = teacherService.getTeacherById(login.getUserId());
				teacherService.deleteTeacher(teacher);
			} else if(login.getRole() == 3) {
				Student student = studentService.getStudentById(login.getUserId());
				studentService.deleteStudent(student);
			}
			
			loginService.deleteLogin(login);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getIsRemeber() {
		return isRemeber;
	}

	public void setIsRemeber(String isRemeber) {
		this.isRemeber = isRemeber;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	
}
