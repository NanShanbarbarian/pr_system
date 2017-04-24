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

import com.alibaba.fastjson.JSON;

import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.entity.Student;
import edu.zjut.tempest.entity.StudentTagRelation;
import edu.zjut.tempest.entity.Tag;
import edu.zjut.tempest.entity.UserProjectRelation;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.StrService;
import edu.zjut.tempest.service.StudentService;
import edu.zjut.tempest.service.TagService;
import edu.zjut.tempest.service.UprService;
import edu.zjut.tempest.util.ExcelUtil;
import edu.zjut.tempest.util.MD5Util;
import edu.zjut.tempest.util.UserSessionUtil;

public class StudentAction {

	private Integer id;
	private String number;
	private String name;
	private Integer sex;
	private Integer experience;
	private Integer level;
	private String academy;
	private String major;
	private String grade;
	private String className;
	private String nation;
	private String polity;
	private String telephone;
	private String cornet;
	private String qq;
	private String email;
	private String dormitory;
	private String description;
	private Integer loginId;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private String tag5;
	private String tag6;
	private StudentService studentService;
	private LoginService loginService;
	private StrService strService;
	private TagService tagService;
	private UprService uprService;
	
	private Integer frequency;
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
	 * student_getListByPage 分页获得项目开发者
	 * @return
	 */
	public String student_getListByPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = loginService.getLoginCountByRole(3);   //获得项目开发者的人数
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
			List<Login> loginList = loginService.getLoginByPageAndRole(begin, rowsPage, 3);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<loginList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				Student student = studentService.getStudentById(loginList.get(i).getUserId());
				map.put("loginId", loginList.get(i).getId());
				map.put("id", loginList.get(i).getUserId());
				map.put("name", student.getName());
				map.put("number", loginList.get(i).getNumber());
				map.put("sex", student.getSex());
				map.put("academy", student.getAcademy());
				map.put("major", student.getMajor());
				map.put("className", student.getClassName());
				map.put("level", student.getLevel());
				map.put("telephone", student.getTelephone());
				map.put("cornet", student.getCornet());
				map.put("qq", student.getQq());
				map.put("email", student.getEmail());
				map.put("description",student.getDescription());
				
				list.add(map);
			}
			
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getStudent_success";
		}
		
		return "getStudent_success";
	}
	
	/**
	 * student_getById 获得某项目开发者信息
	 * @return
	 */
	public String student_getById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if( loginId !=null) {
			Login login = loginService.getLoginById(Integer.parseInt(request.getParameter("loginId")));
			Student student = studentService.getStudentById(login.getUserId());
			if(login != null && student != null) {
				request.setAttribute("login", login);
				request.setAttribute("user", student);
				
				if(request.getParameter("frequency") !=null && Integer.parseInt(request.getParameter("frequency")) == 2) {
					return "getById2_success";
				} else if(request.getParameter("frequency") !=null && Integer.parseInt(request.getParameter("frequency")) == 3) {
					return "getById3_success";
				}
				return "getById_success";
			}
		}
		
		return "getById_error";
	}
	
	/**
	 * student_getByNumber 通过学号获得项目开发者的信息
	 * @throws IOException
	 */
	public void student_getByNumber() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(number != null && !number.equals("")) {
			
			Login login = loginService.getLoginByNumber(number);
			if(login != null) {
				Student student = studentService.getStudentById(login.getUserId());
				
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("name", student.getName());
				map.put("academy", student.getAcademy());
				map.put("major", student.getMajor());
				map.put("className", student.getClassName());
				
				String jo = JSON.toJSONString(map);
				
				out.print(jo);
				out.flush();
				out.close();
			} else {
				out.print(0);
				out.flush();
				out.close();
			}
		} else {
			out.print(0);
			out.flush();
			out.close();
		}
	}
	/**
	 * student_update 更新某项目开发者信息
	 */
	public void student_update() {
		
		try {
			Student student = studentService.getStudentById(id);
			student.setAcademy(academy);
			student.setLevel(level);
			student.setSex(sex);
			student.setMajor(major);
			student.setGrade(grade);
			student.setClassName(className);
			student.setNation(nation);
			student.setPolity(polity);
			student.setTelephone(telephone);
			student.setCornet(cornet);
			student.setQq(qq);
			student.setEmail(email);
			student.setDormitory(dormitory);
			student.setDescription(description);
			
			studentService.updateStudent(student);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * student_delete 删除某项目开发者及其登陆账号
	 */
	public void student_delete() {
		if(loginId != null) {
			try {
				Login login = loginService.getLoginById(loginId);
				Student student = studentService.getStudentById(login.getUserId());
				
				//删除开发人员和标签的中间表
				StudentTagRelation str = strService.getByStuLoginId(loginId);
				
				if(str != null) {
					if(str.getTagId1() != null) {
						Tag tag = tagService.getTagById(str.getTagId1());
						tag.setNum(tag.getNum()-1);
						tagService.updateTag(tag);
					}
					if(str.getTagId2() != null) {
						Tag tag = tagService.getTagById(str.getTagId2());
						tag.setNum(tag.getNum()-1);
						tagService.updateTag(tag);
					}
					if(str.getTagId3() != null) {
						Tag tag = tagService.getTagById(str.getTagId3());
						tag.setNum(tag.getNum()-1);
						tagService.updateTag(tag);
					}
					if(str.getTagId4() != null) {
						Tag tag = tagService.getTagById(str.getTagId4());
						tag.setNum(tag.getNum()-1);
						tagService.updateTag(tag);
					}
					if(str.getTagId5() != null) {
						Tag tag = tagService.getTagById(str.getTagId5());
						tag.setNum(tag.getNum()-1);
						tagService.updateTag(tag);
					}
					if(str.getTagId6() != null) {
						Tag tag = tagService.getTagById(str.getTagId6());
						tag.setNum(tag.getNum()-1);
						tagService.updateTag(tag);
					}
					strService.deleteStr(str);
				}
				
				studentService.deleteStudent(student);
				loginService.deleteLogin(login);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * student_deleteMore 删除多个项目开发者
	 */
	public void student_deleteMore() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getParameter("str");
		String[] values = str.split(",");
		try {
			for(int i=0; i<values.length; i++) {
				Login login = loginService.getLoginById(Integer.parseInt(values[i]));
				studentService.deleteStudent(studentService.getStudentById(login.getUserId()));
				loginService.deleteLogin(login);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * student_save 新增项目开发者
	 */
	public void student_save() {
		try {
			Student student = new Student();
			student.setName(name);
			student.setExperience(0);
			student.setAcademy(academy);
			student.setSex(sex);
			student.setLevel(level);
			student.setMajor(major);
			student.setGrade(grade);
			student.setNation(nation);
			student.setPolity(polity);
			student.setClassName(className);
			student.setTelephone(telephone);
			student.setCornet(cornet);
			student.setQq(qq);
			student.setEmail(email);
			student.setDormitory(dormitory);
			student.setDescription(description);
			
			int userId = studentService.saveStudent(student);
			
			Login login = new Login();
			login.setNumber(number);
			login.setPassword(MD5Util.getMD5("123456"));
			login.setRole(3);
			login.setUserId(userId);
			login.setStatus(1);   //账号正常
			login.setCreatetime(new Timestamp(System.currentTimeMillis()));
			
			loginService.saveLogin(login);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * student_alter 修改项目开发者信息及标签
	 */
	public void student_alter() {
		try {
			Student student = studentService.getStudentById(id);
//			student.setName(name);
			student.setAcademy(academy);
			student.setSex(sex);
			student.setLevel(level);
			student.setMajor(major);
			student.setGrade(grade);
			student.setNation(nation);
			student.setPolity(polity);
			student.setClassName(className);
			student.setTelephone(telephone);
			student.setCornet(cornet);
			student.setQq(qq);
			student.setEmail(email);
			student.setDormitory(dormitory);
			student.setDescription(description);
			
			studentService.updateStudent(student);
			UserSessionUtil.setUserSession(student);
			
			StudentTagRelation str = strService.getByStuLoginId(loginId);
			
			//将原标签数量减1
			if(str.getTagId1() != null) {
				Tag tag = tagService.getTagByName(tag1);
				tag.setNum(tag.getNum()-1);
				tagService.updateTag(tag);
			}
			if(str.getTagId2() != null) {
				Tag tag = tagService.getTagByName(tag2);
				tag.setNum(tag.getNum()-1);
				tagService.updateTag(tag);
			}
			if(str.getTagId3() != null) {
				Tag tag = tagService.getTagByName(tag3);
				tag.setNum(tag.getNum()-1);
				tagService.updateTag(tag);
			}
			if(str.getTagId4() != null) {
				Tag tag = tagService.getTagByName(tag4);
				tag.setNum(tag.getNum()-1);
				tagService.updateTag(tag);
			}
			if(str.getTagId5() != null) {
				Tag tag = tagService.getTagByName(tag5);
				tag.setNum(tag.getNum()-1);
				tagService.updateTag(tag);
			}
			if(str.getTagId6() != null) {
				Tag tag = tagService.getTagByName(tag6);
				tag.setNum(tag.getNum()-1);
				tagService.updateTag(tag);
			}
			
			//添加相应标签，并且标签数量加1
			if(!tag1.equals("")) {
				Tag tag = tagService.getTagByName(tag1);
				str.setTagId1(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag2.equals("")) {
				Tag tag = tagService.getTagByName(tag2);
				str.setTagId2(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag3.equals("")) {
				Tag tag = tagService.getTagByName(tag3);
				str.setTagId3(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag4.equals("")) {
				Tag tag = tagService.getTagByName(tag4);
				str.setTagId4(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag5.equals("")) {
				Tag tag = tagService.getTagByName(tag5);
				str.setTagId5(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			if(!tag6.equals("")) {
				Tag tag = tagService.getTagByName(tag6);
				str.setTagId6(tag.getId());
				tag.setNum(tag.getNum()+1);
				tagService.updateTag(tag);
			}
			
			strService.updateStr(str);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * student_getByProjectId 通过项目id和状态获得项目的人员列表
	 * @return
	 */
	public String student_getByProjectId() {
		List<UserProjectRelation> uprList = null;
		if(id != null) {
			if(frequency == 1) {
				uprList =  uprService.getUPRList(id, 1);
			} else if(frequency == 2) {
				uprList =  uprService.getUPRList(id, 0);
			} else if(frequency == 3) {
				uprList =  uprService.getUPRList(id, 0);
			}
		}
		
		
		if(uprList != null && uprList.size() > 0) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Map<String, Object>> list= new ArrayList<Map<String, Object>>();
			for(int i=0; i<uprList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("loginId", uprList.get(i).getStuLoginId());
				map.put("name", studentService.getStudentById(loginService.getLoginById(uprList.get(i).getStuLoginId()).getUserId()).getName());
				map.put("className", studentService.getStudentById(loginService.getLoginById(uprList.get(i).getStuLoginId()).getUserId()).getClassName());
				
				if(frequency == 3) {
					map.put("stuScore", uprList.get(i).getStuScore());
					map.put("stuRemark", uprList.get(i).getStuRemark());
				}
				list.add(map);
			}
			request.setAttribute("list", list);
		}
		
		if(frequency == 1) {
			return "getByProjectId1_success";
		} else if(frequency == 2) {
			return "getByProjectId2_success";
		} else {
			return "getByProjectId3_success";
		}
	}
	
	/**
	 * student_importExcel   项目开发者(学生)信息的Excel导入
	 * @throws Exception 
	 */
	public void student_importExcel() throws Exception {
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
								
								case "学号":
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
									
								case "层次":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										if(entry.getValue().equals("本科生")) {
											this.level = 0;
										} else if(entry.getValue().equals("研究生")) {
											this.level = 1;
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
									
								case "专业":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										this.major = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
								
								case "年级":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										this.grade = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
									
								case "班级":
									if(entry.getValue()!=null && !entry.getValue().equals("")) {
										this.className = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
									
								case "民族":
									this.nation = (String) entry.getValue();
									break;
								
								case "政治面貌":
									this.polity = (String) entry.getValue();
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
									
								case "宿舍":
									this.dormitory = (String) entry.getValue();
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
									Student student = new Student();
									student.setName(name);
									student.setExperience(0);
									
									if(academy!=null && !academy.equals("")) {
										student.setAcademy(academy);
									} else {
										student.setAcademy("信息工程学院");
									}
									student.setSex(sex);
									student.setLevel(level);
									student.setMajor(major);
									student.setGrade(grade);
									student.setNation(nation);
									student.setPolity(polity);
									student.setClassName(className);
									student.setTelephone(telephone);
									student.setCornet(cornet);
									student.setQq(qq);
									student.setEmail(email);
									student.setDormitory(dormitory);
									student.setDescription(description);
									
									int userId = studentService.saveStudent(student);
									
									Login login = new Login();
									login.setNumber(number);
									login.setPassword(MD5Util.getMD5("123456"));
									login.setRole(3);
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
	
	/**
	 * student_exportExcel   项目开发者(学生)基本信息Excel导出
	 * @return
	 * @throws IOException 
	 */
	public String student_exportExcel() throws IOException {
		List<Login> list = loginService.getAllList(3);    //3代表项目开发者(学生)
		
		if(list!=null && list.size()>0) {
			XSSFWorkbook wb = new XSSFWorkbook();   //创建Excel工作薄对象
			XSSFSheet sheet = wb.createSheet("学生基本信息表");   //创建Excel工作表对象
			XSSFRow row = sheet.createRow(0);   //创建Excel工作表的行，设置第一行为表头
			
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("学号");
			row.createCell(2).setCellValue("姓名");
			row.createCell(3).setCellValue("性别");
			row.createCell(4).setCellValue("层次");
			row.createCell(5).setCellValue("学院");
			row.createCell(6).setCellValue("专业");
			row.createCell(7).setCellValue("年级");
			row.createCell(8).setCellValue("班级");
			row.createCell(9).setCellValue("民族");
			row.createCell(10).setCellValue("政治面貌");
			row.createCell(11).setCellValue("手机");
			row.createCell(12).setCellValue("短号");
			row.createCell(13).setCellValue("QQ");
			row.createCell(14).setCellValue("宿舍");
			row.createCell(15).setCellValue("邮箱");
			
			for(int i=0; i<list.size(); i++) {
				Student student = studentService.getStudentById(list.get(i).getUserId());
				
				try {
					row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(list.get(i).getNumber());
					row.createCell(2).setCellValue(student.getName());
					if(student.getSex() == 0) {
						row.createCell(3).setCellValue("男");
					} else if(student.getSex() == 1) {
						row.createCell(3).setCellValue("女");
					}
					if(student.getLevel() == 0) {
						row.createCell(4).setCellValue("本科生");
					} else if(student.getLevel() == 1) {
						row.createCell(4).setCellValue("研究生");
					}
					row.createCell(5).setCellValue(student.getAcademy());
					row.createCell(6).setCellValue(student.getMajor());
					row.createCell(7).setCellValue(student.getGrade());
					row.createCell(8).setCellValue(student.getClassName());
					row.createCell(9).setCellValue(student.getNation());
					row.createCell(10).setCellValue(student.getPolity());
					row.createCell(11).setCellValue(student.getTelephone());
					row.createCell(12).setCellValue(student.getCornet());
					row.createCell(13).setCellValue(student.getQq());
					row.createCell(14).setCellValue(student.getDormitory());
					row.createCell(15).setCellValue(student.getEmail());
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
			
			this.fileName = "学生基本信息表.xlsx";
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
	 * student_getByQuery   项目开发者(学生)的查询
	 * @return
	 */
	public String student_getByQuery() {
		if(searchType!=null && searchWord!=null) {
			List<Login> loginList = new ArrayList<Login>();
			if(searchType == 1) {   //学生名称
				List<Student> studentList = studentService.getListByName(searchWord);
				if(studentList!=null && studentList.size()>0) {
					for(int i=0; i<studentList.size(); i++) {
						loginList.add(loginService.getLoginByUserId(studentList.get(i).getId(), 3));
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
						Student student = studentService.getStudentById(list.get(i).getUserId());
						map.put("loginId", list.get(i).getId());
						map.put("id", list.get(i).getUserId());
						map.put("name", student.getName());
						map.put("number", list.get(i).getNumber());
						map.put("sex", student.getSex());
						map.put("academy", student.getAcademy());
						map.put("major", student.getMajor());
						map.put("className", student.getClassName());
						map.put("level", student.getLevel());
						map.put("telephone", student.getTelephone());
						map.put("cornet", student.getCornet());
						map.put("qq", student.getQq());
						map.put("email", student.getEmail());
						map.put("description",student.getDescription());
						
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
	
	/**
	 * student_register   项目开发者注册账号
	 * @throws IOException 
	 */
	public void student_register() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Login isLogin = loginService.getLoginByNumber(number);
		if(isLogin == null) {
			try {
				Student student = new Student();
				student.setName(name);
				student.setExperience(0);
				student.setAcademy(academy);
				student.setSex(sex);
				student.setLevel(level);
				student.setMajor(major);
				student.setGrade(grade);
				student.setNation(nation);
				student.setPolity(polity);
				student.setClassName(className);
				student.setTelephone(telephone);
				student.setCornet(cornet);
				student.setQq(qq);
				student.setEmail(email);
				student.setDormitory(dormitory);
				student.setDescription(description);
				
				int userId = studentService.saveStudent(student);
				
				Login login = new Login();
				login.setNumber(number);
				login.setPassword(MD5Util.getMD5("123456"));
				login.setRole(3);
				login.setUserId(userId);
				login.setStatus(0);   //申请状态
				login.setCreatetime(new Timestamp(System.currentTimeMillis()));
				
				loginService.saveLogin(login);
				
				out.print(1);   //注册成功
				out.flush();
				out.close();
			} catch(Exception e) {
//			e.printStackTrace();
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
	
	public StudentService getStudentService() {
		return studentService;
	}
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public UprService getUprService() {
		return uprService;
	}

	public void setUprService(UprService uprService) {
		this.uprService = uprService;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPolity() {
		return polity;
	}
	public void setPolity(String polity) {
		this.polity = polity;
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
	public String getDormitory() {
		return dormitory;
	}
	public void setDormitory(String dormitory) {
		this.dormitory = dormitory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public StrService getStrService() {
		return strService;
	}

	public void setStrService(StrService strService) {
		this.strService = strService;
	}
	
	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
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

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
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
