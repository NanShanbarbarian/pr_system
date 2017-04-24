package edu.zjut.tempest.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.entity.Teacher;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.TeacherService;
import edu.zjut.tempest.util.ExcelUtil;
import edu.zjut.tempest.util.MD5Util;
import edu.zjut.tempest.util.UserSessionUtil;

public class TeacherAction {

	private Integer id;
	private String number;
	private String name;
	private Integer sex;
	private String academy;
	private Integer level;
	private String telephone;
	private String cornet;
	private String qq;
	private String email;
	private String description;
	private TeacherService teacherService;
	private LoginService loginService;
	
	private static int currPage = 1;
	private static int rowsPage = 10;
	
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    //提交过来的file的名字
    private String fileName;
    //文件流
    private InputStream fileInputStream;
    
    private Integer searchType;
    private String searchWord;
	
	/**
	 * teacher_alter 修改个人信息方法
	 * @return
	 */
	public void teacher_alter() {
		
		try {
			Teacher teacher = teacherService.getTeacherById(id);
			
			teacher.setAcademy(academy);
			teacher.setLevel(level);
			teacher.setSex(sex);
			teacher.setTelephone(telephone);
			teacher.setCornet(cornet);
			teacher.setQq(qq);
			teacher.setEmail(email);
			teacher.setDescription(description);
			
//		System.out.println(level);
//		System.out.println(level.getClass().getName());
			
			teacherService.updateTeacher(teacher);
			UserSessionUtil.setUserSession(teacher);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * teacher_getTeacher 获得项目开发者函数
	 * @return
	 */
	public String teacher_getTeacher() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		int totalRows = loginService.getLoginCountByRole(2);   //获得项目发布者的人数
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
			List<Login> loginList = loginService.getLoginByPageAndRole(begin, rowsPage, 2);
//			List<Teacher> teacherList = new ArrayList<Teacher>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<loginList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				Teacher teacher = teacherService.getTeacherById(loginList.get(i).getUserId());
				map.put("loginId", loginList.get(i).getId());
				map.put("id", loginList.get(i).getUserId());
				map.put("name", teacher.getName());
				map.put("number", loginList.get(i).getNumber());
				map.put("sex", teacher.getSex());
				map.put("academy", teacher.getAcademy());
				map.put("level", teacher.getLevel());
				map.put("telephone", teacher.getTelephone());
				map.put("cornet", teacher.getCornet());
				map.put("qq", teacher.getQq());
				map.put("email", teacher.getEmail());
				map.put("description",teacher.getDescription());
				
				list.add(map);
			}
			
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getTeacher_success";
		}
		
		return "getTeacher_success";
	}
	
	/**
	 * teacher_getAdmin 获得普通管理员
	 * @return
	 */
	public String teacher_getAdmin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = loginService.getLoginCountByRole(1);   //获得普通管理员的人数
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
			List<Login> loginList = loginService.getLoginByPageAndRole(begin, rowsPage, 1);
//			List<Teacher> teacherList = new ArrayList<Teacher>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<loginList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				Teacher teacher = teacherService.getTeacherById(loginList.get(i).getUserId());
				map.put("loginId", loginList.get(i).getId());
				map.put("id", loginList.get(i).getUserId());
				map.put("name", teacher.getName());
				map.put("number", loginList.get(i).getNumber());
				map.put("sex", teacher.getSex());
				map.put("academy", teacher.getAcademy());
				map.put("level", teacher.getLevel());
				map.put("telephone", teacher.getTelephone());
				map.put("cornet", teacher.getCornet());
				map.put("qq", teacher.getQq());
				map.put("email", teacher.getEmail());
				map.put("description",teacher.getDescription());
				
				list.add(map);
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getAdmin_success";
		}
		
		return "getAdmin_success";
	}
	
	/**
	 * teacher_getTeacherById 通过id获得某个teacher的信息
	 * @throws IOException
	 */
	public String teacher_getTeacherById() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		if( request.getParameter("loginId") !=null) {
			Login login = loginService.getLoginById(Integer.parseInt(request.getParameter("loginId")));
			Teacher teacher = teacherService.getTeacherById(login.getUserId());
			if(login != null && teacher != null) {
				request.setAttribute("login", login);
				request.setAttribute("user", teacher);
				return "getTeacherById_success";
			}
		}
		
		return "getTeacherById_error";
	}
	
	public String teacher_getAdminById() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		if( request.getParameter("loginId") !=null && id != null ) {
			Login login = loginService.getLoginById(Integer.parseInt(request.getParameter("loginId")));
			Teacher teacher = teacherService.getTeacherById(id);
			if(login != null && teacher != null) {
				request.setAttribute("login", login);
				request.setAttribute("user", teacher);
				return "getAdminById_success";
			}
		}
		
		return "getAdminById_error";
	}
	/**
	 * teacher_update 更新信息
	 */
	public void teacher_update() {
		
		if( id != null ) {
			Teacher teacher = teacherService.getTeacherById(id);
			teacher.setAcademy(academy);
			teacher.setLevel(level);
			teacher.setSex(sex);
			teacher.setTelephone(telephone);
			teacher.setCornet(cornet);
			teacher.setQq(qq);
			teacher.setEmail(email);
			teacher.setDescription(description);
			teacherService.updateTeacher(teacher);
		}
	}
	
	/**
	 * teacher_delete 删除账号
	 */
	public void teacher_delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if( request.getParameter("loginId") != null && request.getParameter("userId") != null ) {
			Login login = loginService.getLoginById(Integer.parseInt(request.getParameter("loginId")));
			Teacher teacher = teacherService.getTeacherById(Integer.parseInt(request.getParameter("userId")));
			
			loginService.deleteLogin(login);
			teacherService.deleteTeacher(teacher);
		}
	}
	
	/**
	 * teacher_deleteMore 删除多个账号
	 */
	public void teacher_deleteMore() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getParameter("str");
		String[] values = str.split(",");
		try {
			for(int i=0; i<values.length; i++) {
				Login login = loginService.getLoginById(Integer.parseInt(values[i]));
				teacherService.deleteTeacher(teacherService.getTeacherById(login.getUserId()));
				loginService.deleteLogin(login);
				
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * teacher_saveTeacher 新增项目开发者
	 */
	public void teacher_saveTeacher() {
		
		try {
			Teacher teacher = new Teacher();
			teacher.setName(name);
			teacher.setAcademy(academy);
			teacher.setLevel(level);
			teacher.setSex(sex);
			teacher.setTelephone(telephone);
			teacher.setCornet(cornet);
			teacher.setQq(qq);
			teacher.setEmail(email);
			teacher.setDescription(description);
			int userId = teacherService.saveTeacher(teacher);
			
			Login login = new Login();
			login.setNumber(number);
			login.setPassword(MD5Util.getMD5("123456"));
			login.setRole(2);
			login.setUserId(userId);
			login.setStatus(1);   //账号正常
			login.setCreatetime(new Timestamp(System.currentTimeMillis()));
			
			loginService.saveLogin(login);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * teacher_saveAdmin 新增普通管理员
	 */
	public void teacher_saveAdmin() {
		
		try {
			Teacher teacher = new Teacher();
			teacher.setName(name);
			teacher.setAcademy(academy);
			teacher.setLevel(level);
			teacher.setSex(sex);
			teacher.setTelephone(telephone);
			teacher.setCornet(cornet);
			teacher.setQq(qq);
			teacher.setEmail(email);
			teacher.setDescription(description);
			int userId = teacherService.saveTeacher(teacher);
			
			Login login = new Login();
			login.setNumber(number);
			login.setPassword(MD5Util.getMD5("123456"));
			login.setRole(1);
			login.setUserId(userId);
			login.setStatus(1);   //账号正常
			login.setCreatetime(new Timestamp(System.currentTimeMillis()));
			
			loginService.saveLogin(login);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * teacher_register   项目发布者注册账号
	 * @throws IOException
	 */
	public void teacher_register() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Login isLogin = loginService.getLoginByNumber(number);
		if(isLogin == null) {
			try {
				Teacher teacher = new Teacher();
				teacher.setName(name);
				teacher.setAcademy(academy);
				teacher.setLevel(level);
				teacher.setSex(sex);
				teacher.setTelephone(telephone);
				teacher.setCornet(cornet);
				teacher.setQq(qq);
				teacher.setEmail(email);
				teacher.setDescription(description);
				int userId = teacherService.saveTeacher(teacher);
				
				Login login = new Login();
				login.setNumber(number);
				login.setPassword(MD5Util.getMD5("123456"));
				login.setRole(2);
				login.setUserId(userId);
				login.setStatus(0);   //申请状态
				login.setCreatetime(new Timestamp(System.currentTimeMillis()));
				
				loginService.saveLogin(login);
				out.print(1);   //注册成功
				out.flush();
				out.close();
			} catch(Exception e) {
				System.out.println(e.getMessage());
				out.print(0);   //注册失败
				out.flush();
				out.close();
			}
		}
		out.print(0);   //注册失败
		out.flush();
		out.close();
	}
	
	/*
	 * techer_importExcel   项目发布者信息Excel导入
	 */
	public void teacher_importExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(file != null) {
			//获得工作薄
			Workbook wb = ExcelUtil.getWorkbook(file, fileName);
			if(wb != null) {
				List<Map<String, Object>> list = ExcelUtil.readExcel(wb);
				
				//获取申请的信息
				if(list!=null && list.size()>0) {
					int count = 0;
					for(int i=0; i<list.size(); i++) {
						boolean flag = true;
						Iterator<Entry<String, Object>> it = list.get(i).entrySet().iterator();
						while(it.hasNext()) {
							Entry<String, Object> entry = (Entry<String, Object>)it.next(); 
							String key = (String) entry.getKey();
							
							switch(key) {
								case "序号":
									break;
								
								case "工号":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										this.number = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
							
								case "姓名":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										this.name = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
								
								case "性别":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										if(entry.getValue().equals("男")) {
											this.sex = 0;
										} else if(entry.getValue().equals("女")) {
											this.sex = 1;
										} else {
											flag = false;
										}
									} else {
										flag = false;
									}
									break;
									
								case "学院":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										this.academy = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
									
								case "职称":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										if(entry.getValue().equals("教师")) {
											this.level = 0;
										} else if(entry.getValue().equals("副教授")) {
											this.level = 1;
										} else if(entry.getValue().equals("教授")) {
											this.level = 2;
										} else {
											flag = false;
										}
									} else {
										flag = false;
									}
									break;
									
								case "手机":
									this.telephone = (String) entry.getValue();
									break;
									
								case "短号":
									this.cornet = (String) entry.getValue();
									break;
								
								case "QQ":
									this.qq = (String) entry.getValue();
									break;
									
								case "邮箱":
									this.email = (String) entry.getValue();
									break;
								default : 
									System.out.println(key);
									break;
							}   //switch
							
						}//while
						if(flag) {
							//保存到数据库中
							Login isLogin = loginService.getLoginByNumber(number);
							if(isLogin == null) {
								
								int result = 0;
								
								try {
									Teacher teacher = new Teacher();
									
									teacher.setName(name);
									teacher.setSex(sex);
									teacher.setLevel(level);
									if(academy!=null && !academy.equals("")) {
										teacher.setAcademy(academy);
									} else {
										teacher.setAcademy("信息工程学院");
									}
									teacher.setTelephone(telephone);
									teacher.setCornet(cornet);
									teacher.setQq(qq);
									teacher.setEmail(email);
									teacher.setDescription(description);
									
									int userId = teacherService.saveTeacher(teacher);
									
									Login login = new Login();
									login.setNumber(number);
									login.setPassword(MD5Util.getMD5("123456"));
									login.setRole(2);
									login.setUserId(userId);
									login.setStatus(1);   //账号正常
									login.setCreatetime(new Timestamp(System.currentTimeMillis()));
									
									result = loginService.saveLogin(login);
									
									if(result > 0) {
										count++;
									}
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						}//flag
						
					}   //for(int i=0; i<list.size(); i++)
					out.print(count);   //成功导入多少条记录
					out.flush();
					out.close();
					ExcelUtil.freeIO();
					return;
				} else {
					ExcelUtil.freeIO();
					out.print(0);  //文件格式规范错误或只能允许申请一次
					out.flush();
					out.close();
				}
			} else {   //workbook
				ExcelUtil.freeIO();
				out.print(0);   //上传时文件出现问题
				out.flush();
				out.close();
			}
		} else {   //file
			ExcelUtil.freeIO();
			out.print(0);   //上传时文件出现问题
			out.flush();
			out.close();
		}
		return;
	}
	
	/*
	 * teacher_exportExcel   项目发布者信息Excel导出
	 */
	public String teacher_exportExcel() throws IOException {
		List<Login> list = loginService.getAllList(2);    //3代表项目开发者(学生)
		
		if(list!=null && list.size()>0) {
			XSSFWorkbook wb = new XSSFWorkbook();   //创建Excel工作薄对象
			XSSFSheet sheet = wb.createSheet("老师基本信息表");   //创建Excel工作表对象
			XSSFRow row = sheet.createRow(0);   //创建Excel工作表的行，设置第一行为表头
			
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("姓名");
			row.createCell(2).setCellValue("工号");
			row.createCell(3).setCellValue("性别");
			row.createCell(4).setCellValue("学院");
			row.createCell(5).setCellValue("职称");
			row.createCell(6).setCellValue("手机");
			row.createCell(7).setCellValue("短号");
			row.createCell(8).setCellValue("QQ");
			row.createCell(9).setCellValue("邮箱");
			
			for(int i=0; i<list.size(); i++) {
				Teacher teacher = teacherService.getTeacherById(list.get(i).getUserId());
				
				try {
					row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(teacher.getName());
					row.createCell(2).setCellValue(list.get(i).getNumber());
					if(teacher.getSex() == 0) {
						row.createCell(3).setCellValue("男");
					} else if(teacher.getSex() == 1) {
						row.createCell(3).setCellValue("女");
					}
					
					row.createCell(4).setCellValue(teacher.getAcademy());
					
					if(teacher.getLevel() == 0) {
						row.createCell(5).setCellValue("教师");
					} else if(teacher.getLevel() == 1) {
						row.createCell(5).setCellValue("副教授");
					} else if(teacher.getLevel() == 2) {
						row.createCell(5).setCellValue("教授");
					}
					
					row.createCell(6).setCellValue(teacher.getTelephone());
					row.createCell(7).setCellValue(teacher.getCornet());
					row.createCell(8).setCellValue(teacher.getQq());
					row.createCell(9).setCellValue(teacher.getEmail());
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
			
			this.fileName = "老师基本信息表.xlsx";
			this.fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");
			
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			wb.write(os);
			os.flush();
			byte[] fileContent = os.toByteArray();
			fileInputStream = new ByteArrayInputStream(fileContent);
			os.close();
			wb.close();
		}
		return "exportExcel_success";
	}
	
	
	/**
	 *teacher_getByQuery    通过查询获得相关项目发布者(老师)信息
	 * @return
	 */
	public String teacher_getByQuery() {
		if(searchType!=null && searchWord!=null) {
			List<Login> loginList = new ArrayList<Login>();
			if(searchType == 1) {   //学生名称
				List<Teacher> studentList = teacherService.getListByName(searchWord);
				if(studentList!=null && studentList.size()>0) {
					for(int i=0; i<studentList.size(); i++) {
						loginList.add(loginService.getLoginByUserId(studentList.get(i).getId(), 2));
					}
				}
				
			} else if(searchType == 2) {   //学生学号
				Login login = loginService.getLoginByNumber(searchWord);
				if(login != null)
					loginList.add(login);
			} else {
				loginList = null;
			}
			
			//清除列表中申请状态的元素
			if(loginList!=null && loginList.size()>0) {
				for(int i=0; i<loginList.size(); i++) {
					if(loginList.get(i).getStatus() == 0) {   //申请状态
						loginList.remove(i);
					}
				}
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
						Teacher teacher = teacherService.getTeacherById(list.get(i).getUserId());
						map.put("loginId", list.get(i).getId());
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
		
		return "getByQuery_success";
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
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCornet() {
		return cornet;
	}
	public void setCornet(String cornet) {
		this.cornet = cornet;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

}
