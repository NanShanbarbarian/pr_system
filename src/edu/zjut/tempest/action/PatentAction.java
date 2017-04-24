package edu.zjut.tempest.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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

import edu.zjut.tempest.entity.Patent;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.PatentService;
import edu.zjut.tempest.service.StudentService;
import edu.zjut.tempest.util.ExcelUtil;

public class PatentAction {

	private PatentService patentService;
	private LoginService loginService;
	private StudentService studentService;
	
	private Integer id;
	private String college;
	private String name;
	private String type;
	private String number;
	private Integer status;
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
	 * patent_applyForm   专利资料申请
	 */
	public void patent_applyForm() {
		try {
			Patent patent = new Patent();
			
			patent.setCollege(college);
			patent.setName(name);
			patent.setNumber(number);
			patent.setType(type);
			patent.setStatus(0);   //必须为授权
			
			patent.setTime(time);
			patent.setMember1(member1);
			patent.setMember2(member2);
			patent.setMember3(member3);
			patent.setMember4(member4);
			patent.setMember5(member5);
			patent.setMember6(member6);
			patent.setMember7(member7);
			patent.setMember8(member8);
			patent.setMember9(member9);
			patent.setMember10(member10);
			
			patent.setTeacher(teacher);
			patent.setAuthorName(authorName);
			patent.setAuthorNumber(authorNumber);
			patent.setAuthorTelephone(authorTelephone);
			patent.setIsPass(1);   //申请状态
			
			patentService.savePatent(patent);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * patent_applyExcel   专利Excel文件上传申请
	 * @throws Exception 
	 */
	public void patent_applyExcel() throws Exception {
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
							case "专利名称":
								this.name = (String) entry.getValue();
								break;
							case "专利类别":
								this.type = (String) entry.getValue();
								break;
							case "专利号":
								this.number = (String) entry.getValue();
								break;
							case "专利状态（必须为授权）":
								if(entry.getValue().equals("授权")) {
									this.status = 0;   //默认为0值
								} else {
									out.print(3);
									out.flush();
									out.close();
									return;
								}
								break;
							case "授权时间":
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									this.time = sdf.parse((String) entry.getValue());
								} catch (Exception e) {
									ExcelUtil.freeIO();
									out.print(4);   //授权时间填写不规范！
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
					Patent patent = new Patent();
					
					patent.setCollege(college);
					patent.setName(name);
					patent.setType(type);
					patent.setNumber(number);
					patent.setTime(time);
					patent.setStatus(status);
					
					if(member1 != null && !member1.equals("")) {
						patent.setMember1(member1);
					}
					if(member2 != null && !member2.equals("")) {
						patent.setMember2(member2);
					}
					if(member3 != null && !member3.equals("")) {
						patent.setMember3(member3);
					}
					if(member4 != null && !member4.equals("")) {
						patent.setMember4(member4);
					}
					if(member5 != null && !member5.equals("")) {
						patent.setMember5(member5);
					}
					if(member6 != null && !member6.equals("")) {
						patent.setMember6(member6);
					}
					if(member7 != null && !member7.equals("")) {
						patent.setMember7(member7);
					}
					if(member8 != null && !member8.equals("")) {
						patent.setMember8(member8);
					}
					if(member9 != null && !member9.equals("")) {
						patent.setMember9(member9);
					}
					if(member10 != null && !member10.equals("")) {
						patent.setMember10(member10);
					}
					
					patent.setTeacher(teacher);
					patent.setAuthorName(authorName);
					patent.setAuthorNumber(authorNumber);
					patent.setAuthorTelephone(authorTelephone);
					patent.setIsPass(1);   //申请状态
					
					int result = 0;
					try {
						result = patentService.savePatent(patent);
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
						out.print(5);
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
	 * patent_getListByNumber   通过学号获得专利记录
	 * @return
	 */
	public String patent_getListByNumber() {
		if(number != null && !number.equals("")) {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			List<Patent> pList = patentService.getListByNumber(number);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			if(pList != null) {
				for(Patent patent : pList) {
					Map<String, Object> map = new HashMap<String, Object>();
					
					map.put("id", patent.getId());
					map.put("name", patent.getName());
					map.put("type", patent.getType());
					map.put("status", patent.getIsPass());
					
					list.add(map);
				}
			}
			
			request.setAttribute("list", list);
		}
		
		return "getListByNumber_success";
	}

	/**
	 * patent_getById   通过id获得该专利记录
	 * @return
	 */
	public String patent_getById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(id != null) {
			
			Patent patent = patentService.getPatentById(id);
			
			request.setAttribute("patent", patent);
			
		}
		
		if(request.getParameter("frequency") !=null && Integer.parseInt(request.getParameter("frequency")) == 2) {
			return "getById2_success";
		}
		return "getById_success";
	}
	
	/**
	 * patent_getApplyList   分页获得专利的申请记录
	 * @return
	 */
	public String patent_getApplyList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = patentService.getCountByIsPass(1);   //获得申请状态的竞赛获奖记录数
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
			
			List<Patent> list = patentService.getListByIsPass(begin, rowsPage, 1);
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getApplyList_success";
	}
	
	/**
	 * patent_getPassedList   分页获得已验证通过的专利记录
	 * @return
	 */
	public String patent_getPassedList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = patentService.getCountByIsPass(0);   //获得申请状态的竞赛获奖记录数
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
			
			List<Patent> list = patentService.getListByIsPass(begin, rowsPage, 0);
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getPassedList_success";
	}
	
	public void patent_accept() {
		if(id != null) {
			Patent patent = patentService.getPatentById(id);
			
			if(patent != null) {
				if(college != null) {
					patent.setCollege(college);
					patent.setName(name);
					patent.setType(type);
					patent.setNumber(number);
					patent.setTime(time);
					
					patent.setMember1(member1);
					patent.setMember2(member2);
					patent.setMember3(member3);
					patent.setMember4(member4);
					patent.setMember5(member5);
					patent.setMember6(member6);
					patent.setMember7(member7);
					patent.setMember8(member8);
					patent.setMember9(member9);
					patent.setMember10(member10);
					
					patent.setTeacher(teacher);
					patent.setAuthorName(authorName);
					patent.setAuthorNumber(authorNumber);
					patent.setAuthorTelephone(authorTelephone);
				}
				
				patent.setIsPass(0);
				patentService.updatePatent(patent);
			}
		}
	}
	
	public void patent_refuse() {
		if(id != null) {
			Patent patent = patentService.getPatentById(id);
			
			if(patent != null) {
				patent.setIsPass(2);   //拒绝
				
				patentService.updatePatent(patent);
			}
		}
	}
	
	public void patent_remove() {
		if(id != null) {
			Patent patent = patentService.getPatentById(id);
			
			if(patent != null) {
				patentService.deletePatent(patent);
			}
		}
	}
	
	/**
	 * patent_importExcel   专利的Excel导入
	 * @throws Exception 
	 */
	public void patent_importExcel() throws Exception {
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
									if(entry.getValue() != null && !entry.getValue().equals("")) {
										this.college = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
								case "专利名称":
									if(entry.getValue() != null && !entry.getValue().equals("")) {
										this.name = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
								case "专利类别":
									if(entry.getValue() != null && !entry.getValue().equals("")) {
										this.type = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
								case "专利号":
									if(entry.getValue() != null && !entry.getValue().equals("")) {
										this.number = (String) entry.getValue();
									} else {
										flag = false;
									}
									break;
								case "专利状态（必须为授权）":
									if(entry.getValue().equals("授权")) {
										this.status = 0;   //默认为0值
									} else {
										flag = false;
										return;
									}
									break;
								case "授权时间":
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
									break;
							}   //switch
							
						}//while
						if(flag) {
							//保存到数据库中
							Patent patent = new Patent();
							
							patent.setCollege(college);
							patent.setName(name);
							patent.setType(type);
							patent.setNumber(number);
							patent.setTime(time);
							patent.setStatus(status);
							
							if(member1 != null && !member1.equals("")) {
								patent.setMember1(member1);
							}
							if(member2 != null && !member2.equals("")) {
								patent.setMember2(member2);
							}
							if(member3 != null && !member3.equals("")) {
								patent.setMember3(member3);
							}
							if(member4 != null && !member4.equals("")) {
								patent.setMember4(member4);
							}
							if(member5 != null && !member5.equals("")) {
								patent.setMember5(member5);
							}
							if(member6 != null && !member6.equals("")) {
								patent.setMember6(member6);
							}
							if(member7 != null && !member7.equals("")) {
								patent.setMember7(member7);
							}
							if(member8 != null && !member8.equals("")) {
								patent.setMember8(member8);
							}
							if(member9 != null && !member9.equals("")) {
								patent.setMember9(member9);
							}
							if(member10 != null && !member10.equals("")) {
								patent.setMember10(member10);
							}
							
							patent.setTeacher(teacher);
							patent.setAuthorName(authorName);
							patent.setAuthorNumber(authorNumber);
							patent.setAuthorTelephone(authorTelephone);
							patent.setIsPass(0);   //验证状态
							
							int result = 0;
							try {
								result = patentService.savePatent(patent);
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
	 * patent_exportExcel   专利记录的Excel导出
	 * @return
	 * @throws IOException 
	 */
	public String patent_exportExcel() throws IOException {
		List<Patent> list = patentService.getAllList();
		
		if(list!=null && list.size()>0) {
			XSSFWorkbook wb = new XSSFWorkbook();   //创建Excel工作薄对象
			XSSFSheet sheet = wb.createSheet("专利表");   //创建Excel工作表对象
			XSSFRow row = sheet.createRow(0);   //创建Excel工作表的行，设置第一行为表头
			
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("学院");
			row.createCell(2).setCellValue("专利名称");
			row.createCell(3).setCellValue("专利类别");
			row.createCell(4).setCellValue("专利号");
			row.createCell(5).setCellValue("专利状态");
			row.createCell(6).setCellValue("授权时间");
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
				row.createCell(3).setCellValue(list.get(i).getType());
				row.createCell(4).setCellValue(list.get(i).getNumber());
				row.createCell(5).setCellValue("授权");
				row.createCell(6).setCellValue(list.get(i).getTime().toString());
				
				try {
					if(list.get(i).getMember1()!=null && !list.get(i).getMember1().equals("")) {
						row.createCell(7).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember1()).getUserId()).getName() + "("+list.get(i).getMember1()+")");
					}
				} catch(Exception e) {
					row.createCell(7).setCellValue("");
				}
				try {
					if(list.get(i).getMember2()!=null && !list.get(i).getMember2().equals("")) {
						row.createCell(8).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember2()).getUserId()).getName() + "("+list.get(i).getMember2()+")");
					}
				} catch(Exception e) {
					row.createCell(8).setCellValue("");
				}
				try {
					if(list.get(i).getMember3()!=null && !list.get(i).getMember3().equals("")) {
						row.createCell(9).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember3()).getUserId()).getName() + "("+list.get(i).getMember3()+")");
					}
				} catch(Exception e) {
					row.createCell(9).setCellValue("");
				}
				try {
					if(list.get(i).getMember4()!=null && !list.get(i).getMember4().equals("")) {
						row.createCell(10).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember4()).getUserId()).getName() + "("+list.get(i).getMember4()+")");
					}
				} catch(Exception e) {
					row.createCell(10).setCellValue("");
				}
				try {
					if(list.get(i).getMember5()!=null && !list.get(i).getMember5().equals("")) {
						row.createCell(11).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember5()).getUserId()).getName() + "("+list.get(i).getMember5()+")");
					}
				} catch(Exception e) {
					row.createCell(11).setCellValue("");
				}
				try {
					if(list.get(i).getMember6()!=null && !list.get(i).getMember6().equals("")) {
						row.createCell(12).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember6()).getUserId()).getName() + "("+list.get(i).getMember6()+")");
					}
				} catch(Exception e) {
					row.createCell(12).setCellValue("");
				}
				try {
					if(list.get(i).getMember7()!=null && !list.get(i).getMember7().equals("")) {
						row.createCell(13).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember7()).getUserId()).getName() + "("+list.get(i).getMember7()+")");
					}
				} catch(Exception e) {
					row.createCell(13).setCellValue("");
				}
				try {
					if(list.get(i).getMember8()!=null && !list.get(i).getMember8().equals("")) {
						row.createCell(14).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember8()).getUserId()).getName() + "("+list.get(i).getMember8()+")");
					}
				} catch(Exception e) {
					row.createCell(14).setCellValue("");
				}
				try {
					if(list.get(i).getMember9()!=null && !list.get(i).getMember9().equals("")) {
						row.createCell(15).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember9()).getUserId()).getName() + "("+list.get(i).getMember9()+")");
					}
				} catch(Exception e) {
					row.createCell(15).setCellValue("");
				}
				try {
					if(list.get(i).getMember10()!=null && !list.get(i).getMember10().equals("")) {
						row.createCell(16).setCellValue(getStudentService().getStudentById(getLoginService().getLoginByNumber(list.get(i).getMember10()).getUserId()).getName() + "("+list.get(i).getMember10()+")");
					}
				} catch(Exception e) {
					row.createCell(16).setCellValue("");
				}
				
				row.createCell(17).setCellValue(list.get(i).getTeacher());
				row.createCell(18).setCellValue(list.get(i).getAuthorName());
				row.createCell(19).setCellValue(list.get(i).getAuthorNumber());
				row.createCell(20).setCellValue(list.get(i).getAuthorTelephone());
				
			}
			
			this.fileName = "专利表.xlsx";
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
	
	public String patent_getByQuery() {
		if(searchType!=null && searchWord!=null) {
			List<Patent> patentList = new ArrayList<Patent>();
			if(searchType == 1) {   //作品名称
				patentList = patentService.getListByName(searchWord);
			} else if(searchType == 2) {   //学生学号
				patentList = patentService.getListByNumber(searchWord);
			} else {
				patentList = null;
			}
			
			//清除列表中的申请状态的元素
			if(patentList!=null && patentList.size()>0) {
				for(int i=0; i<patentList.size(); i++) {
					if(patentList.get(i).getIsPass() == 1) {
						patentList.remove(i);
					}
				}
			}
			
			if(patentList!=null && patentList.size()>0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int totalRows = patentList.size();   //查询结果的长度
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
					
					List<Patent> list = new ArrayList<Patent>();
					
					for(int i=begin; i <= end; i++)
					{
						if(i < patentList.size()) {
							list.add(patentList.get(i));
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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


	public PatentService getPatentService() {
		return patentService;
	}

	public void setPatentService(PatentService patentService) {
		this.patentService = patentService;
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
