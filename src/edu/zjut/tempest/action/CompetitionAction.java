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

import edu.zjut.tempest.entity.Competition;
import edu.zjut.tempest.entity.CompetitionType;
import edu.zjut.tempest.service.CompetitionService;
import edu.zjut.tempest.service.CompetitionTypeService;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.StudentService;
import edu.zjut.tempest.util.ExcelUtil;

public class CompetitionAction {

	private CompetitionService competitionService;
	private CompetitionTypeService competitionTypeService;
	private LoginService loginService;
	private StudentService studentService;

	private Integer id;
	private Integer ctId;
	private String workName;
	private Integer level;
	private Integer rank;
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
	private Date winningTime;
	private String period;
	private Integer term;
	private Integer isPass;
	private String name;
	private Integer category;
	
	private String number;
	private Integer frequency;
	
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
	/**
	 * competition_save 竞赛获奖申请
	 */
	public void competition_applyByForm() {
		
		try {
			
			if(ctId != null) {
				Competition competition = new Competition();
				
				competition.setCtId(ctId);
				competition.setWorkName(workName);
				competition.setLevel(level);
				competition.setRank(rank);
				
				competition.setMember1(member1.trim());
				competition.setMember2(member2.trim());
				competition.setMember3(member3.trim());
				competition.setMember4(member4.trim());
				competition.setMember5(member5.trim());
				competition.setMember6(member6.trim());
				competition.setMember7(member7.trim());
				competition.setMember8(member8.trim());
				competition.setMember9(member9.trim());
				competition.setMember10(member10.trim());
				
				competition.setTeacher(teacher);
				competition.setWinningTime(winningTime);
				competition.setPeriod(period);
				competition.setTerm(term);
				competition.setIsPass(1);   //申请状态
				
				competitionService.saveCompetition(competition);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * competition_applyExcel Excel文件上传申请
	 * @return 
	 * @throws Exception 
	 */
	public void competition_applyExcel() throws Exception {
		
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
					
					Iterator<Entry<String, Object>> ite = list.get(0).entrySet().iterator();
					
					while(ite.hasNext()) {
						Entry<String, Object> entry = (Entry<String, Object>)ite.next(); 
						String key = (String) entry.getKey();
						
						switch (key) {
							case "序号":
								break;
								
							case "竞赛名称":
								List<CompetitionType> ctList = competitionTypeService.getListByCategory(0); 
								if(ctList != null && ctList.size() > 0) {
									for(int i=0; i<ctList.size(); i++) {
										if(ctList.get(i).getName().equals(entry.getValue())) {
											this.name = (String) entry.getValue();
										}
									}
								}
								
								List<CompetitionType> ctList2 = competitionTypeService.getListByCategory(1);
								if(ctList2 != null && ctList2.size() > 0) {
									for(int i=0; i<ctList2.size(); i++) {
										if(ctList2.get(i).getName().equals(entry.getValue())) {
											this.name = (String) entry.getValue();
										}
									}
								}
								
								if(name != null) {
									break;
								} else {
									ExcelUtil.freeIO();
									out.print(3);   //竞赛名称填写不规范，请按照sheet2的规范填写！
									out.flush();
									out.close();
									return;
								}
								
							case "竞赛类别":
								if(entry.getValue().equals("专业赛事")) {
									this.setCategory(0);
								} else if(entry.getValue().equals("非专业赛事")) {
									this.setCategory(1);
									System.out.print(category);
								} else {
									ExcelUtil.freeIO();
									out.print(4);   //竞赛类别填写不规范，请按照sheet2的规范填写！
									out.flush();
									out.close();
									return;
								}
								break;
								
							case "作品名称":
								this.workName = (String) entry.getValue();
								break;
							
							case "获奖层次":
								if(entry.getValue().equals("国家级")) {
									this.level = 1;
								} else if(entry.getValue().equals("省级")) {
									this.level = 2;
								} else if(entry.getValue().equals("市级")) {
									this.level = 3;
								} else if(entry.getValue().equals("校级")) {
									this.level = 4;
								} else if(entry.getValue().equals("院级")) {
									this.level = 5;
								} else {
									ExcelUtil.freeIO();
									out.print(5);   //获奖层次填写不规范！
									out.flush();
									out.close();
									return;
								}
								break;
								
							case "获奖等级":
								if(entry.getValue().equals("特等奖")) {
									this.rank = 0;
								} else if(entry.getValue().equals("一等奖")) {
									this.rank = 1;
								} else if(entry.getValue().equals("二等奖")) {
									this.rank = 2;
								} else if(entry.getValue().equals("三等奖")) {
									this.rank = 3;
								} else if(entry.getValue().equals("成功参赛奖")) {
									this.rank = 4;
								} else {
									ExcelUtil.freeIO();
									out.print(6);   //获奖等级填写不规范！
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
								if(entry !=null && !entry.getValue().equals("")) {
									this.teacher = (String) entry.getValue();
								} else {
									ExcelUtil.freeIO();
									out.print(7);   //指导老师填写不规范！
									out.flush();
									out.close();
									return;
								}
								
								break;
								
							case "获奖时间":
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									this.winningTime = sdf.parse((String) entry.getValue());
								} catch (Exception e) {
									ExcelUtil.freeIO();
									out.print(8);   //获奖时间填写不规范！
									out.flush();
									out.close();
									return;
								}
								break;
								
							case "获奖学年":
								try {
									this.period = (String) entry.getValue();
								} catch(Exception e) {
									ExcelUtil.freeIO();
									out.print(9);   //获奖学年填写不规范！
									out.flush();
									out.close();
									return;
								}
								break;
								
							case "获奖学期":
								
								if(entry.getValue().equals("上学期")) {
									this.term = 1;
								} else if(entry.getValue().equals("下学期")) {
									this.term = 2;
								} else {
									ExcelUtil.freeIO();
									out.print(11);   //获奖学期填写不规范！
									out.flush();
									out.close();
									return;
								}
								break;
							
							default : 
//								System.out.println(key);
								ExcelUtil.freeIO();
								out.print(2);   //文件格式规范错误或只能允许申请一次
								out.flush();
								out.close();
								break;
						}   //switch
						
					}   //while
					
				} else {   //list
					ExcelUtil.freeIO();
					out.print(2);   //文件格式规范错误或只能允许申请一次
					out.flush();
					out.close();
					return;
				}
				
				//保存到数据库中
				CompetitionType ct = competitionTypeService.getByNameAndCategory(name, category);
				if(ct.getId() != null) {
					Competition competition = new Competition();
					
					competition.setCtId(ct.getId());
					competition.setWorkName(workName);
					competition.setLevel(level);
					competition.setRank(rank);

					if(member1 != null && !member1.equals("")) {
						competition.setMember1(member1);
					}
					if(member2 != null && !member2.equals("")) {
						competition.setMember1(member2);
					}
					if(member3 != null && !member3.equals("")) {
						competition.setMember2(member3);
					}
					if(member4 != null && !member4.equals("")) {
						competition.setMember2(member4);
					}
					if(member5 != null && !member5.equals("")) {
						competition.setMember2(member5);
					}
					if(member6 != null && !member6.equals("")) {
						competition.setMember2(member6);
					}
					if(member7 != null && !member7.equals("")) {
						competition.setMember2(member7);
					}
					if(member8 != null && !member8.equals("")) {
						competition.setMember2(member8);
					}
					if(member9 != null && !member9.equals("")) {
						competition.setMember2(member9);
					}
					if(member10 != null && !member10.equals("")) {
						competition.setMember2(member10);
					}
					
					competition.setTeacher(teacher);
					competition.setWinningTime(winningTime);
					competition.setPeriod(period);
					competition.setTerm(term);
					competition.setIsPass(1);   //申请状态
					
					competitionService.saveCompetition(competition);
					ExcelUtil.freeIO();
					out.print(10);   //导入成功
					out.flush();
					out.close();
					
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
	 * competition_getListByNumber 通过学号获得竞赛获奖记录
	 * @return
	 */
	public String competition_getListByNumber() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(number != null && !number.equals("")) {
			List<Competition> cList = competitionService.getListByNumber(number); 
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			if(cList != null) {
				for(Competition competition : cList) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					map.put("id", competition.getId());
					map.put("name", competitionTypeService.getById(competition.getCtId()).getName());
					map.put("level", competition.getLevel());
					map.put("rank", competition.getRank());
//				map.put("winning_time", competition.getWinningTime());
					map.put("status", competition.getIsPass());
					
					list.add(map);
				}
			}
			request.setAttribute("list", list);
		}
		return "getListByNumber_success";
	}
	
	public String competition_getById() {
		if(id != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			Competition competition = competitionService.getCompetitionById(id);
			
			CompetitionType ct = competitionTypeService.getById(competition.getCtId());
			
			request.setAttribute("competition", competition);
			request.setAttribute("ct", ct);
			
		}
		if(frequency == 2) {
			return "getById2_success";
		}
		return "getById_success";
	}
	/**
	 * competition_getApplyList 分页获得竞赛获奖申请列表
	 * @return
	 */
	public String competition_getApplyList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = competitionService.getCountByIsPass(1);   //获得申请状态的竞赛获奖记录数
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
			
			List<Competition> cList = competitionService.getListByIsPass(begin, rowsPage, 1);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			for(int i=0; i<cList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", cList.get(i).getId());
				map.put("name", competitionTypeService.getById(cList.get(i).getCtId()).getName());
				map.put("category", competitionTypeService.getById(cList.get(i).getCtId()).getCategory());
				map.put("workName", cList.get(i).getWorkName());
				map.put("level", cList.get(i).getLevel());
				map.put("rank", cList.get(i).getRank());
				map.put("teacher", cList.get(i).getTeacher());
				map.put("winningTime", cList.get(i).getWinningTime());
				
				list.add(map);
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getApplyList_success";
	}
	
	/**
	 * competition_getPassedList   获得已验证的竞赛获奖列表
	 * @return
	 */
	public String competition_getPassedList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = competitionService.getCountByIsPass(0);   //获得竞赛获奖记录数
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
			
			List<Competition> cList = competitionService.getListByIsPass(begin, rowsPage, 0);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			
			for(int i=0; i<cList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("id", cList.get(i).getId());
				map.put("name", competitionTypeService.getById(cList.get(i).getCtId()).getName());
				map.put("category", competitionTypeService.getById(cList.get(i).getCtId()).getCategory());
				map.put("workName", cList.get(i).getWorkName());
				map.put("level", cList.get(i).getLevel());
				map.put("rank", cList.get(i).getRank());
				map.put("teacher", cList.get(i).getTeacher());
				map.put("winningTime", cList.get(i).getWinningTime());
				
				list.add(map);
			}
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
		}
		
		return "getPassedList_success";
	}
	
	/**
	 *competition_accept 同意竞赛获奖申请 
	 */
	public void competition_accept() {
		if(id != null) {
			Competition competition = competitionService.getCompetitionById(id);
			
			if(competition != null) {
				
				if(workName != null) {
					competition.setWorkName(workName);
					competition.setLevel(level);
					competition.setRank(rank);
					
					competition.setMember1(member1.trim());
					competition.setMember2(member2.trim());
					competition.setMember3(member3.trim());
					competition.setMember4(member4.trim());
					competition.setMember5(member5.trim());
					competition.setMember6(member6.trim());
					competition.setMember7(member7.trim());
					competition.setMember8(member8.trim());
					competition.setMember9(member9.trim());
					competition.setMember10(member10.trim());
					
					competition.setTeacher(teacher);
					competition.setWinningTime(winningTime);
					competition.setPeriod(period);
					competition.setTerm(term);
				}
				
				competition.setIsPass(0);   //同意申请
				
				competitionService.updateCompetition(competition);
			}
		}
	}
	
	/**
	 * competition_refuse 拒绝竞赛获奖申请
	 */
	public void competition_refuse() {
		if(id != null) {
			Competition competition = competitionService.getCompetitionById(id);
			
			if(competition != null) {
				competition.setIsPass(2);   //拒绝申请
				
				competitionService.updateCompetition(competition);
			}
		}
	}
	
	/**
	 * competition_remove 删除竞赛获奖申请记录
	 */
	public void competition_remove() {
		if(id != null) {
			Competition competition = competitionService.getCompetitionById(id);
			
			if(competition != null) {
				competitionService.deleteCompetition(competition);
			}
		}
	}
	
	/**
	 * competition_importExcel   竞赛获奖Excel导入
	 * @throws Exception 
	 */
	public void competition_importExcel() throws Exception {
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
								
							case "竞赛名称":
								List<CompetitionType> ctList = competitionTypeService.getListByCategory(0); 
								if(ctList != null && ctList.size() > 0) {
									for(int i1=0; i1<ctList.size(); i1++) {
										if(ctList.get(i1).getName().equals(entry.getValue())) {
											this.name = (String) entry.getValue();
										}
									}
								}
								
								List<CompetitionType> ctList2 = competitionTypeService.getListByCategory(1);
								if(ctList2 != null && ctList2.size() > 0) {
									for(int i1=0; i1<ctList2.size(); i1++) {
										if(ctList2.get(i1).getName().equals(entry.getValue())) {
											this.name = (String) entry.getValue();
										}
									}
								}
								
								if(name != null) {
									break;
								} else {
									flag = false;
									return;
								}
								
							case "竞赛类别":
								if(entry.getValue().equals("专业赛事")) {
									this.setCategory(0);
								} else if(entry.getValue().equals("非专业赛事")) {
									this.setCategory(1);
									System.out.print(category);
								} else {
									flag = false;
									return;
								}
								break;
								
							case "作品名称":
								this.workName = (String) entry.getValue();
								break;
							
							case "获奖层次":
								if(entry.getValue().equals("国家级")) {
									this.level = 1;
								} else if(entry.getValue().equals("省级")) {
									this.level = 2;
								} else if(entry.getValue().equals("市级")) {
									this.level = 3;
								} else if(entry.getValue().equals("校级")) {
									this.level = 4;
								} else if(entry.getValue().equals("院级")) {
									this.level = 5;
								} else {
									flag = false;
									return;
								}
								break;
								
							case "获奖等级":
								if(entry.getValue().equals("特等奖")) {
									this.rank = 0;
								} else if(entry.getValue().equals("一等奖")) {
									this.rank = 1;
								} else if(entry.getValue().equals("二等奖")) {
									this.rank = 2;
								} else if(entry.getValue().equals("三等奖")) {
									this.rank = 3;
								} else if(entry.getValue().equals("成功参赛奖")) {
									this.rank = 4;
								} else {
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
								if(entry !=null && !entry.getValue().equals("")) {
									this.teacher = (String) entry.getValue();
								} else {
									flag = false;
									return;
								}
								
								break;
								
							case "获奖时间":
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									this.winningTime = sdf.parse((String) entry.getValue());
								} catch (Exception e) {
									flag = false;
									return;
								}
								break;
								
							case "获奖学年":
								try {
									this.period = (String) entry.getValue();
								} catch(Exception e) {
									flag = false;
									return;
								}
								break;
								
							case "获奖学期":
								
								if(entry.getValue().equals("上学期")) {
									this.term = 1;
								} else if(entry.getValue().equals("下学期")) {
									this.term = 2;
								} else {
									flag = false;
									return;
								}
								break;
							
							default : 
								System.out.println(key);
								break;
							}   //switch
							
						}//while
						if(flag) {
							//保存到数据库中
							CompetitionType ct = competitionTypeService.getByNameAndCategory(name, category);
							if(ct.getId() != null) {
								Competition competition = new Competition();
								
								competition.setCtId(ct.getId());
								competition.setWorkName(workName);
								competition.setLevel(level);
								competition.setRank(rank);

								if(member1 != null && !member1.equals("")) {
									competition.setMember1(member1);
								}
								if(member2 != null && !member2.equals("")) {
									competition.setMember1(member2);
								}
								if(member3 != null && !member3.equals("")) {
									competition.setMember2(member3);
								}
								if(member4 != null && !member4.equals("")) {
									competition.setMember2(member4);
								}
								if(member5 != null && !member5.equals("")) {
									competition.setMember2(member5);
								}
								if(member6 != null && !member6.equals("")) {
									competition.setMember2(member6);
								}
								if(member7 != null && !member7.equals("")) {
									competition.setMember2(member7);
								}
								if(member8 != null && !member8.equals("")) {
									competition.setMember2(member8);
								}
								if(member9 != null && !member9.equals("")) {
									competition.setMember2(member9);
								}
								if(member10 != null && !member10.equals("")) {
									competition.setMember2(member10);
								}
								
								competition.setTeacher(teacher);
								competition.setWinningTime(winningTime);
								competition.setPeriod(period);
								competition.setTerm(term);
								competition.setIsPass(0);   //正常状态
							
								int result = 0;
								try {
									result = competitionService.saveCompetition(competition);
									if(result > 0) {
										count++;
									}
								} catch(Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}//flag
						
					}   //for(int i=0; i<list.size(); i++)
					if(count > 0) {
						out.print(count);   //成功导入多少条记录
						out.flush();
						out.close();
					} else {
						out.print(0);   //成功导入多少条记录
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
	 * competition_exportExcel   竞赛获奖验证记录Excel导出
	 * @throws IOException 
	 */
	public String competition_exportExcel() throws IOException {
		List<Competition> list = competitionService.getAllList();
		
		if(list!=null && list.size()>0) {
			XSSFWorkbook wb = new XSSFWorkbook();   //创建Excel工作薄对象
			XSSFSheet sheet = wb.createSheet("竞赛获奖表");   //创建Excel工作表对象
			XSSFRow row = sheet.createRow(0);   //创建Excel工作表的行，设置第一行为表头
			
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("竞赛名称");
			row.createCell(2).setCellValue("竞赛类别");
			row.createCell(3).setCellValue("作品名称");
			row.createCell(4).setCellValue("获奖层次");
			row.createCell(5).setCellValue("获奖等级");
			row.createCell(6).setCellValue("队员1学号");
			row.createCell(7).setCellValue("队员2学号");
			row.createCell(8).setCellValue("队员3学号");
			row.createCell(9).setCellValue("队员4学号");
			row.createCell(10).setCellValue("队员5学号");
			row.createCell(11).setCellValue("队员6学号");
			row.createCell(12).setCellValue("队员7学号");
			row.createCell(13).setCellValue("队员8学号");
			row.createCell(14).setCellValue("队员9学号");
			row.createCell(15).setCellValue("队员10学号");
			row.createCell(16).setCellValue("指导老师");
			row.createCell(17).setCellValue("获奖时间");
			row.createCell(18).setCellValue("获奖学期");
			
			for(int i=0; i<list.size(); i++) {
				row = sheet.createRow(i+1);
				row.createCell(0).setCellValue(i+1);
				row.createCell(1).setCellValue(competitionTypeService.getById(list.get(i).getCtId()).getName());
				
				if(competitionTypeService.getById(list.get(i).getCtId()).getCategory() == 0) {
					row.createCell(2).setCellValue("专业赛事");
				} else if(competitionTypeService.getById(list.get(i).getCtId()).getCategory() == 1) {
					row.createCell(2).setCellValue("非专业赛事");
				}
				
				row.createCell(3).setCellValue(list.get(i).getWorkName());
				
				if(list.get(i).getLevel() == 1) {
					row.createCell(4).setCellValue("国家级");
				} else if(list.get(i).getLevel() == 2) {
					row.createCell(4).setCellValue("省级");
				} else if(list.get(i).getLevel() == 3) {
					row.createCell(4).setCellValue("市级");
				} else if(list.get(i).getLevel() == 4) {
					row.createCell(4).setCellValue("校级");
				} else if(list.get(i).getLevel() == 5) {
					row.createCell(4).setCellValue("院级");
				}
				
				if(list.get(i).getRank() == 0) {
					row.createCell(5).setCellValue("特等奖");
				} else if(list.get(i).getRank() == 1) {
					row.createCell(5).setCellValue("一等奖");
				} else if(list.get(i).getRank() == 2) {
					row.createCell(5).setCellValue("二等奖");
				} else if(list.get(i).getRank() == 3) {
					row.createCell(5).setCellValue("三等奖");
				} else if(list.get(i).getRank() == 4) {
					row.createCell(5).setCellValue("成功参赛奖");
				}
				
				try {
					if(list.get(i).getMember1()!=null && !list.get(i).getMember1().equals("")) {
						row.createCell(6).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember1()).getUserId()).getName() + "("+list.get(i).getMember1()+")");
					}
				} catch(Exception e) {
					row.createCell(6).setCellValue("");
				}
				try {
					if(list.get(i).getMember2()!=null && !list.get(i).getMember2().equals("")) {
						row.createCell(7).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember2()).getUserId()).getName() + "("+list.get(i).getMember2()+")");
					}
				} catch(Exception e) {
					row.createCell(7).setCellValue("");
				}
				try {
					if(list.get(i).getMember3()!=null && !list.get(i).getMember3().equals("")) {
						row.createCell(8).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember3()).getUserId()).getName() + "("+list.get(i).getMember3()+")");
					}
				} catch(Exception e) {
					row.createCell(8).setCellValue("");
				}
				try {
					if(list.get(i).getMember4()!=null && !list.get(i).getMember4().equals("")) {
						row.createCell(9).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember4()).getUserId()).getName() + "("+list.get(i).getMember4()+")");
					}
				} catch(Exception e) {
					row.createCell(9).setCellValue("");
				}
				try {
					if(list.get(i).getMember5()!=null && !list.get(i).getMember5().equals("")) {
						row.createCell(10).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember5()).getUserId()).getName() + "("+list.get(i).getMember5()+")");
					}
				} catch(Exception e) {
					row.createCell(10).setCellValue("");
				}
				try {
					if(list.get(i).getMember6()!=null && !list.get(i).getMember6().equals("")) {
						row.createCell(11).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember6()).getUserId()).getName() + "("+list.get(i).getMember6()+")");
					}
				} catch(Exception e) {
					row.createCell(11).setCellValue("");
				}
				try {
					if(list.get(i).getMember7()!=null && !list.get(i).getMember7().equals("")) {
						row.createCell(12).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember7()).getUserId()).getName() + "("+list.get(i).getMember7()+")");
					}
				} catch(Exception e) {
					row.createCell(12).setCellValue("");
				}
				try {
					if(list.get(i).getMember8()!=null && !list.get(i).getMember8().equals("")) {
						row.createCell(13).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember8()).getUserId()).getName() + "("+list.get(i).getMember8()+")");
					}
				} catch(Exception e) {
					row.createCell(13).setCellValue("");
				}
				try {
					if(list.get(i).getMember9()!=null && !list.get(i).getMember9().equals("")) {
						row.createCell(14).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember9()).getUserId()).getName() + "("+list.get(i).getMember9()+")");
					}
				} catch(Exception e) {
					row.createCell(14).setCellValue("");
				}
				try {
					if(list.get(i).getMember10()!=null && !list.get(i).getMember10().equals("")) {
						row.createCell(15).setCellValue(studentService.getStudentById(loginService.getLoginByNumber(list.get(i).getMember10()).getUserId()).getName() + "("+list.get(i).getMember10()+")");
					}
				} catch(Exception e) {
					row.createCell(15).setCellValue("");
				}
				
				row.createCell(16).setCellValue(list.get(i).getTeacher());
				row.createCell(17).setCellValue(list.get(i).getWinningTime().toString());
				
				if(list.get(i).getTerm() == 1) {
					row.createCell(18).setCellValue(list.get(i).getPeriod().toString() + "(上学期)");
				} else if(list.get(i).getTerm() == 2) {
					row.createCell(18).setCellValue(list.get(i).getPeriod().toString() + "(下学期)");
				}
				
			}
			
			this.fileName = "竞赛获奖表.xlsx";
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
	 *competition_getByQuery   竞赛获奖记录查询 
	 * @return
	 */
	public String competition_getByQuery() {
		
		if(searchType!=null && searchWord!=null) {
			List<Competition> cList = new ArrayList<Competition>();
			if(searchType == 1) {   //作品名称
				cList = competitionService.getListByName(searchWord);
				
			} else if(searchType == 2) {   //学生学号
				cList = competitionService.getListByNumber(searchWord);
			} else {
				cList = null;
			}
			
			//清楚列表中申请状态的元素
			if(cList!=null && cList.size()>0) {
				for(int i=0; i<cList.size(); i++) {
					if(cList.get(i).getIsPass() == 1) {
						cList.remove(i);
					}
				}
			}
			
			if(cList!=null && cList.size()>0) {
				HttpServletRequest request = ServletActionContext.getRequest();
				
				int totalRows = cList.size();   //查询结果的长度
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
					
					List<Competition> list = new ArrayList<Competition>();
					
					for(int i=begin; i <= end; i++)
					{
						if(i < cList.size()) {
							list.add(cList.get(i));
						}
					}
					
					List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
					
					for(int i=0; i<list.size(); i++) {
						Map<String, Object> map = new LinkedHashMap<String, Object>();
						map.put("id", cList.get(i).getId());
						map.put("name", competitionTypeService.getById(cList.get(i).getCtId()).getName());
						map.put("category", competitionTypeService.getById(cList.get(i).getCtId()).getCategory());
						map.put("workName", cList.get(i).getWorkName());
						map.put("level", cList.get(i).getLevel());
						map.put("rank", cList.get(i).getRank());
						map.put("teacher", cList.get(i).getTeacher());
						map.put("winningTime", cList.get(i).getWinningTime());
						
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
	
	public CompetitionService getCompetitionService() {
		return competitionService;
	}

	public void setCompetitionService(CompetitionService competitionService) {
		this.competitionService = competitionService;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCtId() {
		return ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
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

	public Date getWinningTime() {
		return winningTime;
	}

	public void setWinningTime(Date winningTime) {
		this.winningTime = winningTime;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public CompetitionTypeService getCompetitionTypeService() {
		return competitionTypeService;
	}

	public void setCompetitionTypeService(CompetitionTypeService competitionTypeService) {
		this.competitionTypeService = competitionTypeService;
	}


	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
