package edu.zjut.tempest.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.SetupProject;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.SetupProjectService;
import edu.zjut.tempest.service.StudentService;
import edu.zjut.tempest.util.ExcelUtil;

public class SetupProjectAction {

	private SetupProjectService setupProjectService;
	private LoginService loginService;
	private StudentService studentService;
	private Integer id;
	private String officalNumber;
	private String category;
	private String subcategory;
	private String name;
	private String proLevel;
	private Integer status;
	private Integer stuLevel;
	private String member1;
	private String member2;
	private String member3;
	private String member4;
	private String member5;
	private String member6;
	private String member7;
	private String member8;
	private String member9;
	private String member10;
	private String teacher;
	
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    //提交过来的file的名字
    private String fileName;
    //文件流
    private InputStream fileInputStream;
    
    private Integer searchType;
    private String searchWord;
    
	private static int currPage = 1;
	private static int rowsPage = 10;
	
	public String setuppro_getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = setupProjectService.getCountOnAll();   //获得申请状态的竞赛获奖记录数
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
			
			List<SetupProject> list = setupProjectService.getAllList(begin, rowsPage);
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getList_success";
	}
	
	/**
	 * setuppro_importExcel   项目立项结题Excel导入
	 * @throws Exception 
	 */
	public void setuppro_importExcel() throws Exception {
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
							case "项目官方编号":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.officalNumber = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "项目类别":
								this.category = (String) entry.getValue();
								break;
							case "项目子类别":
								this.subcategory = (String) entry.getValue();
								break;
							case "项目名称":
								this.name = (String) entry.getValue();
								break;
							case "项目层次":
								this.proLevel = (String) entry.getValue();
								break;
							case "项目状态":
								try {
									if(entry.getValue().equals("立项")) {
										this.status = 0;
									} else if(entry.getValue().equals("结题")) {
										this.status = 1;
									} else {
										flag = false;
									}
								} catch (Exception e) {
									flag = false;
								}
								break;
							case "学生层次":
								try {
									if(entry.getValue().equals("本科生")) {
										this.stuLevel = 0;
									} else if(entry.getValue().equals("研究生")) {
										this.stuLevel = 1;
									} else {
										flag = false;
									}
								} catch (Exception e) {
									flag = false;
									return;
								}
								break;
							case "学生1学号":
								this.member1 = (String) entry.getValue();
								break;
								
							case "学生2学号":
								this.member2 = (String) entry.getValue();
								break;
								
							case "学生3学号":
								this.member3 = (String) entry.getValue();
								break;
								
							case "学生4学号":
								this.member4 = (String) entry.getValue();
								break;
								
							case "学生5学号":
								this.member5 = (String) entry.getValue();
								break;
								
							case "学生6学号":
								this.member6 = (String) entry.getValue();
								break;
								
							case "学生7学号":
								this.member7 = (String) entry.getValue();
								break;
								
							case "学生8学号":
								this.member8 = (String) entry.getValue();
								break;
								
							case "学生9学号":
								this.member9 = (String) entry.getValue();
								break;
								
							case "学生10学号":
								this.member10 = (String) entry.getValue();
								break;
								
							case "指导老师":
								this.teacher = (String) entry.getValue();
								break;
							default : 
								System.out.println(key);
//								ExcelUtil.freeIO();
//								out.print(0);   //文件格式规范错误或只能允许申请一次
//								out.flush();
//								out.close();
								break;
							}   //switch
							
						}//while
						if(flag) {
							SetupProject setuppro = new SetupProject();
							
							setuppro.setOfficalNumber(officalNumber);
							setuppro.setCategory(category);
							setuppro.setSubcategory(subcategory);
							setuppro.setName(name);
							setuppro.setStatus(status);
							setuppro.setProLevel(proLevel);
							setuppro.setStuLevel(stuLevel);
							
							if(member1 != null && !member1.equals("")) {
								setuppro.setMember1(member1);
							}
							if(member2 != null && !member2.equals("")) {
								setuppro.setMember2(member2);
							}
							if(member3 != null && !member3.equals("")) {
								setuppro.setMember3(member3);
							}
							if(member4 != null && !member4.equals("")) {
								setuppro.setMember4(member4);
							}
							if(member5 != null && !member5.equals("")) {
								setuppro.setMember5(member5);
							}
							if(member6 != null && !member6.equals("")) {
								setuppro.setMember6(member6);
							}
							if(member7 != null && !member7.equals("")) {
								setuppro.setMember7(member7);
							}
							if(member8 != null && !member8.equals("")) {
								setuppro.setMember8(member8);
							}
							if(member9 != null && !member9.equals("")) {
								setuppro.setMember9(member9);
							}
							if(member10 != null && !member10.equals("")) {
								setuppro.setMember10(member10);
							}
							setuppro.setTeacher(teacher);
							
							int result = 0;
							try {
								result = setupProjectService.saveSetupPro(setuppro);
								if(result > 0) {
									count++;
								}
							} catch(Exception e) {
								System.out.println(e.getMessage());
							}
							
						}//flag
						
					}   //for(int i=0; i<list.size(); i++)
					if(count > 0) {
						out.print(count);   //成功导入多少条记录
						out.flush();
						out.close();
					} else {
						out.print(0);   
						out.flush();
						out.close();
					}
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
	
	public String setuppro_exportExcel() throws IOException {
		List<SetupProject> list = setupProjectService.getAllList();
		
		if(list!=null && list.size()>0) {
			//创建Excel工作簿对象
			XSSFWorkbook workbook = new XSSFWorkbook();
			//创建Excel工作表对象
			XSSFSheet sheet = workbook.createSheet("项目立项结题表");
			//创建Excel工作表的行,设置Excel表的第一行即表头
			XSSFRow row = sheet.createRow(0);
			//创建单元格
			XSSFCell cell = null;
			cell = row.createCell(0);
			cell.setCellValue("序号");
			cell = row.createCell(1);
			cell.setCellValue("项目官方编号");
			cell = row.createCell(2);
			cell.setCellValue("项目类别");
			cell = row.createCell(3);
			cell.setCellValue("项目子类别");
			cell = row.createCell(4);
			cell.setCellValue("项目名称");
			cell = row.createCell(5);
			cell.setCellValue("项目层次");
			cell = row.createCell(6);
			cell.setCellValue("立项状态（立项/结题）");
			cell = row.createCell(7);
			cell.setCellValue("学生层次");
			cell = row.createCell(8);
			cell.setCellValue("学员1");
			cell = row.createCell(9);
			cell.setCellValue("学员2");
			cell = row.createCell(10);
			cell.setCellValue("学员3");
			cell = row.createCell(11);
			cell.setCellValue("学员4");
			cell = row.createCell(12);
			cell.setCellValue("学员5");
			cell = row.createCell(13);
			cell.setCellValue("学员6");
			cell = row.createCell(14);
			cell.setCellValue("学员7");
			cell = row.createCell(15);
			cell.setCellValue("学员8");
			cell = row.createCell(16);
			cell.setCellValue("学员9");
			cell = row.createCell(17);
			cell.setCellValue("学员10");
			cell = row.createCell(18);
			cell.setCellValue("指导老师");
			
			for(int i=0; i<list.size(); i++) {
				row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(i+1);
				row.createCell(1).setCellValue(list.get(i).getOfficalNumber());
				row.createCell(2).setCellValue(list.get(i).getCategory());
				row.createCell(3).setCellValue(list.get(i).getSubcategory());
				row.createCell(4).setCellValue(list.get(i).getName());
				row.createCell(5).setCellValue(list.get(i).getProLevel());
				if(list.get(i).getStatus() == 0) {
					row.createCell(6).setCellValue("立项");
				} else if(list.get(i).getStatus() == 1){
					row.createCell(6).setCellValue("结题");
				}
				if(list.get(i).getStuLevel() == 0) {
					row.createCell(7).setCellValue("本科生");
				} else if(list.get(i).getStuLevel() == 1) {
					row.createCell(7).setCellValue("研究生");
				}
				
				try {
					if(list.get(i).getMember1()!=null && !list.get(i).getMember1().equals("")) {
						row.createCell(8).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember1()).getUserId()).getName() + "("+list.get(i).getMember1()+")");
					}
				} catch(Exception e) {
					row.createCell(8).setCellValue("");
				}
				try {
					if(list.get(i).getMember2()!=null && !list.get(i).getMember2().equals("")) {
						row.createCell(9).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember2()).getUserId()).getName() + "("+list.get(i).getMember2()+")");
					}
				} catch(Exception e) {
					row.createCell(9).setCellValue("");
				}
				try {
					if(list.get(i).getMember3()!=null && !list.get(i).getMember3().equals("")) {
						row.createCell(10).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember3()).getUserId()).getName() + "("+list.get(i).getMember3()+")");
					}
				} catch(Exception e) {
					row.createCell(10).setCellValue("");
				}
				try {
					if(list.get(i).getMember4()!=null && !list.get(i).getMember4().equals("")) {
						row.createCell(11).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember4()).getUserId()).getName() + "("+list.get(i).getMember4()+")");
					}
				} catch(Exception e) {
					row.createCell(11).setCellValue("");
				}
				try {
					if(list.get(i).getMember5()!=null && !list.get(i).getMember5().equals("")) {
						row.createCell(12).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember5()).getUserId()).getName() + "("+list.get(i).getMember5()+")");
					}
				} catch(Exception e) {
					row.createCell(12).setCellValue("");
				}
				try {
					if(list.get(i).getMember6()!=null && !list.get(i).getMember6().equals("")) {
						row.createCell(13).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember6()).getUserId()).getName() + "("+list.get(i).getMember6()+")");
					}
				} catch(Exception e) {
					row.createCell(13).setCellValue("");
				}
				try {
					if(list.get(i).getMember7()!=null && !list.get(i).getMember7().equals("")) {
						row.createCell(14).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember7()).getUserId()).getName() + "("+list.get(i).getMember7()+")");
					}
				} catch(Exception e) {
					row.createCell(14).setCellValue("");
				}
				try {
					if(list.get(i).getMember8()!=null && !list.get(i).getMember8().equals("")) {
						row.createCell(15).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember8()).getUserId()).getName() + "("+list.get(i).getMember8()+")");
					}
				} catch(Exception e) {
					row.createCell(15).setCellValue("");
				}
				try {
					if(list.get(i).getMember9()!=null && !list.get(i).getMember9().equals("")) {
						row.createCell(16).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember9()).getUserId()).getName() + "("+list.get(i).getMember9()+")");
					}
				} catch(Exception e) {
					row.createCell(16).setCellValue("");
				}
				try {
					if(list.get(i).getMember10()!=null && !list.get(i).getMember10().equals("")) {
						row.createCell(17).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember10()).getUserId()).getName() + "("+list.get(i).getMember10()+")");
					}
				} catch(Exception e) {
					row.createCell(17).setCellValue("");
				}
				
				row.createCell(18).setCellValue(list.get(i).getTeacher());
			}
			
			this.fileName = "项目立项结题表.xlsx";
			this.fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");
			
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			workbook.write(os);
			os.flush();
			byte[] fileContent = os.toByteArray();
			fileInputStream = new ByteArrayInputStream(fileContent);
			os.close();
			workbook.close();
 		}
		return "exportExcel_success";
	}
	
	/**
	 * setuppro_getById   通过id获得项目立项结题记录
	 * @return
	 */
	public String setuppro_getById() {
		if(id != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			SetupProject setupPro = setupProjectService.getSetupProById(id);
			
			request.setAttribute("setuppro", setupPro);
		}
		return "getById_success";
	}
	
	/**
	 * setuppro_getByQuery   根据查询返回搜索结果
	 * @return
	 */
	public String setuppro_getByQuery() {
		if(searchType!=null && searchWord!=null) {
			List<SetupProject> setupProList = new ArrayList<SetupProject>();
			if(searchType == 1) {   //项目名称查询
				setupProList = setupProjectService.getListByName(searchWord);
			} else if(searchType == 2) {   //项目类别查询
				setupProList = setupProjectService.getListByCategory(searchWord);
			} else if(searchType == 3) {   //项目官方编号查询
				setupProList = setupProjectService.getListByOfficalNumber(searchWord);
			} else {
				setupProList = null;
			}
			
			if(setupProList!=null && setupProList.size()>0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int totalRows = setupProList.size();   //查询结果的长度
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
					
					List<SetupProject> list = new ArrayList<SetupProject>();
					
					for(int i=begin; i <= end; i++)
					{
						if(i < setupProList.size()) {
							list.add(setupProList.get(i));
						}
					}
					request.setAttribute("currPage", currPage);
					request.setAttribute("rowsPage", rowsPage);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("list", list);
				}
			}
		}
		return "getByQuery_success";
	}
	
	/**
	 * setuppro_remove   删除项目立项结题记录
	 */
	public void setuppro_remove() {
		if(id != null) {
			SetupProject setupPro = setupProjectService.getSetupProById(id);
			
			if(setupPro != null) {
				setupProjectService.deleteSetupPro(setupPro);
			}
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOfficalNumber() {
		return officalNumber;
	}

	public void setOfficalNumber(String officalNumber) {
		this.officalNumber = officalNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProLevel() {
		return proLevel;
	}

	public void setProLevel(String proLevel) {
		this.proLevel = proLevel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStuLevel() {
		return stuLevel;
	}

	public void setStuLevel(Integer stuLevel) {
		this.stuLevel = stuLevel;
	}
	
	public String getMember1() {
		return member1;
	}

	public void setMember1(String member1) {
		this.member1 = member1;
	}

	public String getMember2() {
		return member2;
	}

	public void setMember2(String member2) {
		this.member2 = member2;
	}

	public String getMember3() {
		return member3;
	}

	public void setMember3(String member3) {
		this.member3 = member3;
	}

	public String getMember4() {
		return member4;
	}

	public void setMember4(String member4) {
		this.member4 = member4;
	}

	public String getMember5() {
		return member5;
	}

	public void setMember5(String member5) {
		this.member5 = member5;
	}

	public String getMember6() {
		return member6;
	}

	public void setMember6(String member6) {
		this.member6 = member6;
	}

	public String getMember7() {
		return member7;
	}

	public void setMember7(String member7) {
		this.member7 = member7;
	}

	public String getMember8() {
		return member8;
	}

	public void setMember8(String member8) {
		this.member8 = member8;
	}

	public String getMember9() {
		return member9;
	}

	public void setMember9(String member9) {
		this.member9 = member9;
	}

	public String getMember10() {
		return member10;
	}

	public void setMember10(String member10) {
		this.member10 = member10;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
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

	public SetupProjectService getSetupProjectService() {
		return setupProjectService;
	}

	public void setSetupProjectService(SetupProjectService setupProjectService) {
		this.setupProjectService = setupProjectService;
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
