package edu.zjut.tempest.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import edu.zjut.tempest.entity.Paper;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.PaperService;
import edu.zjut.tempest.service.StudentService;
import edu.zjut.tempest.util.ExcelUtil;

public class PaperAction {

	private PaperService paperService;
	private LoginService loginService;
	private StudentService studentService;
	
	private Integer id;
	private String college;
	private String name;
	private String periodical;
	private String number;
	private String level;
	private Date time;
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
	private String authorName;
	private String authorNumber;
	private String authorTelephone;
	private Integer isPass;
	
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
	//提交过来的file的名字
    private String fileName;
    private InputStream fileInputStream;
    
    private Integer searchType;
    private String searchWord;
    
	private static int currPage = 1;
	private static int rowsPage = 10;
	
	/**
	 * paper_applyForm 期刊论文资料申请
	 */
	public void paper_applyForm() {
		
		try {
			Paper paper = new Paper();
			
			paper.setCollege(college.trim());
			paper.setName(name.trim());
			paper.setPeriodical(periodical.trim());
			paper.setNumber(number.trim());
			paper.setLevel(level.trim());
			paper.setTime(time);
			paper.setMember1(member1.trim());  
			paper.setMember2(member2.trim());
			paper.setMember3(member3.trim());
			paper.setMember4(member4.trim());
			paper.setMember5(member5.trim());
			paper.setMember6(member6.trim());
			paper.setMember7(member7.trim());
			paper.setMember8(member8.trim());
			paper.setMember9(member9.trim());
			paper.setMember10(member10.trim());
			
			paper.setTeacher(teacher);
			paper.setAuthorName(authorName);
			paper.setAuthorNumber(authorNumber);
			paper.setAuthorTelephone(authorTelephone);
			
			paper.setIsPass(1);   //申请
			
			paperService.savePaper(paper);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * paper_applyExcel   期刊论文Excel文件上传申请
	 * @throws Exception 
	 */
	public void paper_applyExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(file != null) {
			//获得工作薄
			Workbook wb = ExcelUtil.getWorkbook(file, fileName);
			if(wb != null) {
				List<Map<String, Object>> list = ExcelUtil.readExcel(wb);
				
				//获取申请的信息
				if(list != null && list.size() == 1) {
					Iterator<Entry<String, Object>> it = list.get(0).entrySet().iterator();
					while(it.hasNext()) {
						Entry<String, Object> entry = (Entry<String, Object>)it.next(); 
						String key = (String) entry.getKey();
						
						switch(key) {
							case "序号":
								break;
							case "学院":
								this.college = (String) entry.getValue();
								break;
							case "论文名称":
								this.name = (String) entry.getValue();
								break;
							case "期刊名称":
								this.periodical = (String) entry.getValue();
								break;
							case "期刊号（ISSN）":
								this.number = (String) entry.getValue();
								break;
							case "期刊等级":
								this.level = (String) entry.getValue();
								break;
							case "论文发表时间（按样刊时间填写，年、卷、期、页）":
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									this.time = sdf.parse((String) entry.getValue());
								} catch (Exception e) {
									ExcelUtil.freeIO();
									out.print(3);   //发表时间填写不规范！
									out.flush();
									out.close();
									return;
								}
								break;
							case "队员1学号":
								this.member1 = (String) entry.getValue();
								break;
							
							case "队员2学号":
								this.member2 = (String) entry.getValue();
								break;
								
							case "队员3学号":
								this.member3 = (String) entry.getValue();
								break;
								
							case "队员4学号":
								this.member4 = (String) entry.getValue();
								break;
								
							case "队员5学号":
								this.member5 = (String) entry.getValue();
								break;
								
							case "队员6学号":
								this.member6 = (String) entry.getValue();
								break;
								
							case "队员7学号":
								this.member7 = (String) entry.getValue();
								break;
								
							case "队员8学号":
								this.member8 = (String) entry.getValue();
								break;
								
							case "队员9学号":
								this.member9 = (String) entry.getValue();
								break;
								
							case "队员10学号":
								this.member10 = (String) entry.getValue();
								break;
								
							case "指导老师":
								this.teacher = (String) entry.getValue();
								break;
							case "本科学生第一作者姓名":
								this.authorName = (String) entry.getValue();
								break;
							case "第一作者学号":
								this.authorNumber = (String) entry.getValue();
								break;
							case "第一作者电话":
								this.authorTelephone = (String) entry.getValue();
								break;
							default : 
								System.out.println(key);
								ExcelUtil.freeIO();
								out.print(2);   //文件格式规范错误或只能允许申请一次
								out.flush();
								out.close();
								break;
						}   //switch
					}   //while
					
					//保存到数据库中
					Paper paper = new Paper();
					
					paper.setCollege(college);
					paper.setName(name);
					paper.setPeriodical(periodical);
					paper.setLevel(level);
					paper.setNumber(number);
					paper.setTime(time);
					
					if(member1 != null && !member1.equals("")) {
						paper.setMember1(member1);
					}
					if(member2 != null && !member2.equals("")) {
						paper.setMember2(member2);
					}
					if(member3 != null && !member3.equals("")) {
						paper.setMember3(member3);
					}
					if(member4 != null && !member4.equals("")) {
						paper.setMember4(member4);
					}
					if(member5 != null && !member5.equals("")) {
						paper.setMember5(member5);
					}
					if(member6 != null && !member6.equals("")) {
						paper.setMember6(member6);
					}
					if(member7 != null && !member7.equals("")) {
						paper.setMember7(member7);
					}
					if(member8 != null && !member8.equals("")) {
						paper.setMember8(member8);
					}
					if(member9 != null && !member9.equals("")) {
						paper.setMember9(member9);
					}
					if(member10 != null && !member10.equals("")) {
						paper.setMember10(member10);
					}
					
					paper.setTeacher(teacher);
					paper.setAuthorName(authorName);
					paper.setAuthorNumber(authorNumber);
					paper.setAuthorTelephone(authorTelephone);
					paper.setIsPass(1);   //申请状态
					
					int result = 0;
					try {
						result = paperService.savePaper(paper);
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					if(result > 0) {
						ExcelUtil.freeIO();
						out.print(10);   //导入成功
						out.flush();
						out.close();
					} else {
						ExcelUtil.freeIO();   //导入失败
						out.print(4);
						out.flush();
						out.close();
					}
					
					return;
				} else {
					ExcelUtil.freeIO();
					out.print(2);  //文件格式规范错误或只能允许申请一次
					out.flush();
					out.close();
				}
			} else {   //workbook
				ExcelUtil.freeIO();
				out.print(1);   //上传时文件出现问题
				out.flush();
				out.close();
			}
		} else {   //file
			ExcelUtil.freeIO();
			out.print(1);   //上传时文件出现问题
			out.flush();
			out.close();
		}
		
		return;
	}
	/**
	 * paper_getListByNumber 通过该用户的学号获得期刊论文记录
	 */
	public String paper_getListByNumber() {
		if(number != null && !number.equals("")) {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			List<Paper> pList = paperService.getListByNumber(number);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			if(pList != null) {
				for(Paper paper : pList) {
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", paper.getId());
					map.put("name", paper.getName());
					map.put("periodical", paper.getPeriodical());
					map.put("status", paper.getIsPass());
					
					list.add(map);
				}
			}
			
			request.setAttribute("list", list);
		}
		
		return "getListByNumber_success";
	}
	
	/**
	 * paper_getById 通过id获取期刊论文信息
	 * @return
	 */
	public String paper_getById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(id != null) {
			
			Paper paper = paperService.getPaperById(id);
			
			request.setAttribute("paper", paper);
		}
		
		if(request.getParameter("frequency") !=null && Integer.parseInt(request.getParameter("frequency")) == 2) {
			return "getById2_success";
		}
		return "getById_success";
	}
	
	/**
	 * paper_getApplyList 分页获得期刊论文的申请记录
	 * @return
	 */
	public String paper_getApplyList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = paperService.getCountByIsPass(1);   //获得申请状态的竞赛获奖记录数
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
			
			List<Paper> list = paperService.getListByIsPass(begin, rowsPage, 1);
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getApplyList_success";
	}
	
	public String paper_getPassedList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = paperService.getCountByIsPass(0);   //获得申请状态的竞赛获奖记录数
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
			
			List<Paper> list = paperService.getListByIsPass(begin, rowsPage, 0);
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getPassedList_success";
	}
	/**
	 * paper_accept 接受期刊论文申请
	 */
	public void paper_accept() {
		if(id != null) {
			Paper paper = paperService.getPaperById(id);
			
			if(paper != null) {
				if(college != null) {
					paper.setCollege(college);
					paper.setName(name);
					paper.setPeriodical(periodical);
					paper.setNumber(number);
					paper.setLevel(level);
					paper.setTime(time);
					
					paper.setMember1(member1);
					paper.setMember2(member2);
					paper.setMember3(member3);
					paper.setMember4(member4);
					paper.setMember5(member5);
					paper.setMember6(member6);
					paper.setMember7(member7);
					paper.setMember8(member8);
					paper.setMember9(member9);
					paper.setMember10(member10);
					
					paper.setTeacher(teacher);
					paper.setAuthorName(authorName);
					paper.setAuthorNumber(authorNumber);
					paper.setAuthorTelephone(authorTelephone);
				}
				
				paper.setIsPass(0);
				paperService.updatePaper(paper);
			}
		}
	}
	
	/**
	 * paper_refuse 拒绝期刊论文申请
	 */
	public void paper_refuse() {
		if(id != null) {
			Paper paper = paperService.getPaperById(id);
			
			if(paper != null) {
				paper.setIsPass(2);
				
				paperService.updatePaper(paper);
			}
		}
	}
	
	/**
	 * paper_remove 删除期刊论文记录
	 */
	public void paper_remove() {
		if(id != null) {
			Paper paper = paperService.getPaperById(id);
			
			if(paper != null) {
				paperService.deletePaper(paper);
			}
		}
	}
	
	/**
	 * paper_importExcel   期刊论文Excel导入  
	 * @throws Exception 
	 */
	public void paper_importExcel() throws Exception {
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
							case "学院":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.college = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "论文名称":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.name = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "期刊名称":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.periodical = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "期刊号（ISSN）":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.number = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "期刊等级":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.level = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "论文发表时间（按样刊时间填写，年、卷、期、页）":
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									this.time = sdf.parse((String) entry.getValue());
								} catch (Exception e) {
									flag = false;
									return;
								}
								break;
							case "队员1学号":
								this.member1 = (String) entry.getValue();
								break;
							
							case "队员2学号":
								this.member2 = (String) entry.getValue();
								break;
								
							case "队员3学号":
								this.member3 = (String) entry.getValue();
								break;
								
							case "队员4学号":
								this.member4 = (String) entry.getValue();
								break;
								
							case "队员5学号":
								this.member5 = (String) entry.getValue();
								break;
								
							case "队员6学号":
								this.member6 = (String) entry.getValue();
								break;
								
							case "队员7学号":
								this.member7 = (String) entry.getValue();
								break;
								
							case "队员8学号":
								this.member8 = (String) entry.getValue();
								break;
								
							case "队员9学号":
								this.member9 = (String) entry.getValue();
								break;
								
							case "队员10学号":
								this.member10 = (String) entry.getValue();
								break;
								
							case "指导老师":
								this.teacher = (String) entry.getValue();
								break;
							case "本科学生第一作者姓名":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.authorName = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "第一作者学号":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.authorNumber = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							case "第一作者电话":
								if(entry.getValue()!=null && !entry.getValue().equals("")) {
									this.authorTelephone = (String) entry.getValue();
								} else {
									flag = false;
								}
								break;
							default : 
								System.out.println(key);
								break;
							}   //switch
							
						}//while
						if(flag) {
							Paper paper = new Paper();
							
							paper.setCollege(college);
							paper.setName(name);
							paper.setPeriodical(periodical);
							paper.setLevel(level);
							paper.setNumber(number);
							paper.setTime(time);
							
							if(member1 != null && !member1.equals("")) {
								paper.setMember1(member1);
							}
							if(member2 != null && !member2.equals("")) {
								paper.setMember2(member2);
							}
							if(member3 != null && !member3.equals("")) {
								paper.setMember3(member3);
							}
							if(member4 != null && !member4.equals("")) {
								paper.setMember4(member4);
							}
							if(member5 != null && !member5.equals("")) {
								paper.setMember5(member5);
							}
							if(member6 != null && !member6.equals("")) {
								paper.setMember6(member6);
							}
							if(member7 != null && !member7.equals("")) {
								paper.setMember7(member7);
							}
							if(member8 != null && !member8.equals("")) {
								paper.setMember8(member8);
							}
							if(member9 != null && !member9.equals("")) {
								paper.setMember9(member9);
							}
							if(member10 != null && !member10.equals("")) {
								paper.setMember10(member10);
							}
							
							paper.setTeacher(teacher);
							paper.setAuthorName(authorName);
							paper.setAuthorNumber(authorNumber);
							paper.setAuthorTelephone(authorTelephone);
							paper.setIsPass(0);   //验证状态
							
							int result = 0;
							try {
								result = paperService.savePaper(paper);
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
	
	/**
	 * paper_exportExcel   期刊论文Excel导出
	 * @return
	 * @throws Exception 
	 */
	public String paper_exportExcel() throws Exception {
		List<Paper> list = paperService.getAllList();
		
		if(list!=null && list.size()>0) {
			XSSFWorkbook wb = new XSSFWorkbook();   //创建Excel工作薄对象
			XSSFSheet sheet = wb.createSheet("期刊论文表");   //创建Excel工作表对象
			XSSFRow row = sheet.createRow(0);   //创建Excel工作表的行，设置第一行为表头
			
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("学院");
			row.createCell(2).setCellValue("论文名称");
			row.createCell(3).setCellValue("期刊名称");
			row.createCell(4).setCellValue("期刊号（ISSN）");
			row.createCell(5).setCellValue("期刊等级");
			row.createCell(6).setCellValue("论文发表时间");
			row.createCell(7).setCellValue("队员1学号");
			row.createCell(8).setCellValue("队员2学号");
			row.createCell(9).setCellValue("队员3学号");
			row.createCell(10).setCellValue("队员4学号");
			row.createCell(11).setCellValue("队员5学号");
			row.createCell(12).setCellValue("队员6学号");
			row.createCell(13).setCellValue("队员7学号");
			row.createCell(14).setCellValue("队员8学号");
			row.createCell(15).setCellValue("队员9学号");
			row.createCell(16).setCellValue("队员10学号");
			row.createCell(17).setCellValue("指导老师");
			row.createCell(18).setCellValue("第一作者姓名");
			row.createCell(19).setCellValue("第一作者学号");
			row.createCell(20).setCellValue("第一作者电话");
			
			for(int i=0; i<list.size(); i++) {
				row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(i+1);
				row.createCell(1).setCellValue(list.get(i).getCollege());
				row.createCell(2).setCellValue(list.get(i).getName());
				row.createCell(3).setCellValue(list.get(i).getPeriodical());
				row.createCell(4).setCellValue(list.get(i).getNumber());
				row.createCell(5).setCellValue(list.get(i).getLevel());
				row.createCell(6).setCellValue(list.get(i).getTime().toString());
				
				try {
					if(list.get(i).getMember1()!=null && !list.get(i).getMember1().equals("")) {
						row.createCell(7).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember1()).getUserId()).getName() + "("+list.get(i).getMember1()+")");
					}
				} catch(Exception e) {
					row.createCell(7).setCellValue("");
				}
				try {
					if(list.get(i).getMember2()!=null && !list.get(i).getMember2().equals("")) {
						row.createCell(8).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember2()).getUserId()).getName() + "("+list.get(i).getMember2()+")");
					}
				} catch(Exception e) {
					row.createCell(8).setCellValue("");
				}
				try {
					if(list.get(i).getMember3()!=null && !list.get(i).getMember3().equals("")) {
						row.createCell(9).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember3()).getUserId()).getName() + "("+list.get(i).getMember3()+")");
					}
				} catch(Exception e) {
					row.createCell(9).setCellValue("");
				}
				try {
					if(list.get(i).getMember4()!=null && !list.get(i).getMember4().equals("")) {
						row.createCell(10).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember4()).getUserId()).getName() + "("+list.get(i).getMember4()+")");
					}
				} catch(Exception e) {
					row.createCell(10).setCellValue("");
				}
				try {
					if(list.get(i).getMember5()!=null && !list.get(i).getMember5().equals("")) {
						row.createCell(11).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember5()).getUserId()).getName() + "("+list.get(i).getMember5()+")");
					}
				} catch(Exception e) {
					row.createCell(11).setCellValue("");
				}
				try {
					if(list.get(i).getMember6()!=null && !list.get(i).getMember6().equals("")) {
						row.createCell(12).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember6()).getUserId()).getName() + "("+list.get(i).getMember6()+")");
					}
				} catch(Exception e) {
					row.createCell(12).setCellValue("");
				}
				try {
					if(list.get(i).getMember7()!=null && !list.get(i).getMember7().equals("")) {
						row.createCell(13).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember7()).getUserId()).getName() + "("+list.get(i).getMember7()+")");
					}
				} catch(Exception e) {
					row.createCell(13).setCellValue("");
				}
				try {
					if(list.get(i).getMember8()!=null && !list.get(i).getMember8().equals("")) {
						row.createCell(14).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember8()).getUserId()).getName() + "("+list.get(i).getMember8()+")");
					}
				} catch(Exception e) {
					row.createCell(14).setCellValue("");
				}
				try {
					if(list.get(i).getMember9()!=null && !list.get(i).getMember9().equals("")) {
						row.createCell(15).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember9()).getUserId()).getName() + "("+list.get(i).getMember9()+")");
					}
				} catch(Exception e) {
					row.createCell(15).setCellValue("");
				}
				try {
					if(list.get(i).getMember10()!=null && !list.get(i).getMember10().equals("")) {
						row.createCell(16).setCellValue(studentService.getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember10()).getUserId()).getName() + "("+list.get(i).getMember10()+")");
					}
				} catch(Exception e) {
					row.createCell(16).setCellValue("");
				}
				
				row.createCell(17).setCellValue(list.get(i).getTeacher());
				row.createCell(18).setCellValue(list.get(i).getAuthorName());
				row.createCell(19).setCellValue(list.get(i).getAuthorNumber());
				row.createCell(20).setCellValue(list.get(i).getAuthorTelephone());
				
			}
			
			this.fileName = "期刊论文表.xlsx";
			this.fileName = new String(this.fileName.getBytes("GBK"),"ISO-8859-1");
			
			ByteArrayOutputStream os = new ByteArrayOutputStream(); 
			wb.write(os);
			os.flush();
			byte[] fileContent = os.toByteArray();
			setFileInputStream(new ByteArrayInputStream(fileContent));
			os.close();
			wb.close();
		}
		return "exportExcel_success";
	}
	
	public String paper_getByQuery() {
		if(searchType!=null && searchWord!=null) {
			List<Paper> paperList = new ArrayList<Paper>();
			if(searchType == 1) {   //论文名称查询
				paperList = paperService.getListByName(searchWord);
			} else if(searchType == 2) {   //学生学号查询
				paperList = paperService.getListByNumber(searchWord);
			} else {
				paperList = null;
			}
			
			//清除列表中的申请状态的元素
			if(paperList!=null && paperList.size()>0) {
				for(int i=0; i<paperList.size(); i++) {
					if(paperList.get(i).getIsPass() == 1) {
						paperList.remove(i);
					}
				}
			}
			
			if(paperList!=null && paperList.size()>0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int totalRows = paperList.size();   //查询结果的长度
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
					
					List<Paper> list = new ArrayList<Paper>();
					
					for(int i=begin; i <= end; i++)
					{
						if(i < paperList.size()) {
							list.add(paperList.get(i));
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriodical() {
		return periodical;
	}

	public void setPeriodical(String periodical) {
		this.periodical = periodical;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorNumber() {
		return authorNumber;
	}

	public void setAuthorNumber(String authorNumber) {
		this.authorNumber = authorNumber;
	}

	public String getAuthorTelephone() {
		return authorTelephone;
	}

	public void setAuthorTelephone(String authorTelephone) {
		this.authorTelephone = authorTelephone;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
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

	public PaperService getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
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
