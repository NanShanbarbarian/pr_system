package edu.zjut.tempest.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.GiftedMentor;
import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.entity.MentorStudentRelation;
import edu.zjut.tempest.entity.Student;
import edu.zjut.tempest.service.GiftedMentorService;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.MsrService;
import edu.zjut.tempest.service.StudentService;

public class GiftedMentorAction {

	private GiftedMentorService gmService;
	private MsrService msrService;
	private LoginService loginService;
	private StudentService studentService;

	private Integer id;
	private String  leader;
	private String title;
	private String member;
	private Integer teachingAsistant;
	private Integer labAsistant;
	private Integer competitionNum;
	private Integer researchAsistant;
	private Integer publisherId;
	private Date publishTime;
	private Integer status;
	
	private Integer stuLoginId;
	
	private Integer frequency;
	
    private String fileName;
    //文件流
    private InputStream fileInputStream;
	
	private static int currPage = 1;
	private static int rowsPage = 10;
	/**
	 * gm_save   发布优才导师计划
	 */
	public void gm_save() {
		try {
			GiftedMentor gm = new GiftedMentor();
			
			gm.setLeader(leader);
			gm.setTitle(title);
			gm.setMember(member);
			gm.setTeachingAsistant(teachingAsistant);
			gm.setLabAsistant(labAsistant);
			gm.setCompetitionNum(competitionNum);
			gm.setResearchAsistant(researchAsistant);
			gm.setPublisherId(publisherId);
			
			gm.setPublishTime(new Date());
			gm.setStatus(0);   //选择中
			
			gmService.saveGM(gm);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * gm_getList   获得该用户发布的优才导师计划
	 */
	public String gm_getListByPublisherId() {
		if(id != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			List<GiftedMentor> gmList = gmService.getByPublisherId(id);
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<gmList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				map.put("id", gmList.get(i).getId());
				map.put("leader", gmList.get(i).getLeader());
				map.put("member", "(" + gmList.get(i).getMember() + ")");
				map.put("title", gmList.get(i).getTitle());
				map.put("publishTime", sdf.format(gmList.get(i).getPublishTime()));
				list.add(map);
			}
			request.setAttribute("list", list);
			
		}
		return "getListByPublisherId_success";
	}
	
	/**
	 * gm_getById   获取某个优才导师计划的信息记录
	 * @return
	 */
	public String gm_getById() {
		if(id!=null && frequency!=null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			GiftedMentor gm = gmService.getById(id);
			request.setAttribute("gm", gm);
			
			if(stuLoginId!=null) {   //活动申请时查看信息
				try {
					MentorStudentRelation msr = msrService.getByGmIdAndLoginId(gm.getId(), stuLoginId);
					request.setAttribute("msr", msr);
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		if(frequency!=null && frequency == 1) {
			return "getById1_success";
		} else if(frequency!=null && frequency == 2){
			return "getById2_success";
		} else if(frequency!=null && frequency == 3){
			return "getById3_success";
		} else {
			return "getById4_success";
		}
	}
	
	/**
	 * gm_getList   分页获得所有的发布中记录
	 * @return
	 */
	public String gm_getSelectList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currPage")!=null) {
		
			int totalRows = gmService.getSelectCount();   
			int totalPage=totalRows/rowsPage;//总页数
			totalPage = (totalRows%rowsPage)>0?totalPage+1:totalPage;
			if(totalRows != 0) {
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
				int begin = (currPage - 1) * rowsPage;
				
				List<GiftedMentor> gmList = gmService.getSelectListByPages(begin, rowsPage);
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for(int i=0; i<gmList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					map.put("id", gmList.get(i).getId());
					map.put("leader", gmList.get(i).getLeader());
					map.put("member", gmList.get(i).getMember());
					map.put("title", gmList.get(i).getTitle());
					map.put("publishTime", sdf.format(gmList.get(i).getPublishTime()));
					list.add(map);
				}
				request.setAttribute("currPage", currPage);
				request.setAttribute("rowsPage", rowsPage);
				request.setAttribute("totalRows", totalRows);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("list", list);
			}
		}
		
		return "getSelectList_success";
	}
	
	public String gm_getListByStuLoginId() {
		if(stuLoginId != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<MentorStudentRelation> msrList = msrService.getByStuLoginId(stuLoginId);
			
			List<GiftedMentor> gmList = new ArrayList<GiftedMentor>();
			if(msrList != null) {
				for(MentorStudentRelation msr : msrList) {
					gmList.add(gmService.getById(msr.getGmId()));
				}
			}
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if(gmList != null) {
				for(int i=0; i<gmList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					map.put("id", gmList.get(i).getId());
					map.put("leader", gmList.get(i).getLeader());
					map.put("member", "(" + gmList.get(i).getMember() + ")");
					map.put("title", gmList.get(i).getTitle());
					map.put("publishTime", sdf.format(gmList.get(i).getPublishTime()));
					list.add(map);
				}
			}
			request.setAttribute("list", list);
			
		}
		return "getListByStuLoginId_success";
	}
	
	/**
	 * gm_getAllList   分页获得优才导师的所有记录
	 * @return
	 */
	public String gm_getAllList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getParameter("currPage")!=null) {
		
			int totalRows = gmService.getAllCount();   
			int totalPage=totalRows/rowsPage;//总页数
			totalPage = (totalRows%rowsPage)>0?totalPage+1:totalPage;
			if(totalRows != 0) {
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
				int begin = (currPage - 1) * rowsPage;
				
				List<GiftedMentor> gmList = gmService.getListByPages(begin, rowsPage);
				
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				for(int i=0; i<gmList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					map.put("id", gmList.get(i).getId());
					map.put("leader", gmList.get(i).getLeader());
					map.put("member", gmList.get(i).getMember());
					map.put("title", gmList.get(i).getTitle());
					map.put("publishTime", sdf.format(gmList.get(i).getPublishTime()));
					map.put("status", gmList.get(i).getStatus());
					list.add(map);
				}
				request.setAttribute("currPage", currPage);
				request.setAttribute("rowsPage", rowsPage);
				request.setAttribute("totalRows", totalRows);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("list", list);
			}
		}
		
		return "getAllList_success";
	}
	
	/**
	 * gm_finish   完成某个优才导师的记录
	 */
	public void gm_finish() {
		if(id != null) {
			GiftedMentor gm = gmService.getById(id);
			
			if(gm != null) {
				gm.setStatus(1);   //已完成
				
				gmService.updateGM(gm);
			}
		}
	}
	
	/**
	 * gm_allFinish   一键结束所有活动
	 */
	public void gm_allFinish() {
		List<GiftedMentor> list = gmService.getAllList();
		
		if(list!=null && list.size()>0) {
			for(int i=0; i<list.size(); i++) {
				GiftedMentor gm = list.get(i);
				gm.setStatus(1);   //已完成状态
				
				gmService.updateGM(gm);
			}
		}
	}
	
	/**
	 * gm_allClear   一键清除所有记录
	 * @throws IOException
	 */
	public void gm_allClear() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int result = gmService.deleteAllGm();
		msrService.deleteAllMsr();
		
		out.print(result);
		out.flush(); 
		out.close();
	}
	
	/**
	 * gm_exportExcel   优才导师计划Excel导出
	 * @return
	 * @throws IOException 
	 */
	public String gm_exportExcel() throws IOException {
		//一键结束活动
		this.gm_allFinish();
		List<GiftedMentor> list = gmService.getAllList();
		
		if(list!=null && list.size()>0) {
			XSSFWorkbook wb = new XSSFWorkbook();   //创建Excel工作薄对象
			XSSFSheet sheet = wb.createSheet("优才导师计划匹配结果表");   //创建Excel工作表对象
			XSSFRow row = sheet.createRow(0);   //创建Excel工作表的行，设置第一行为表头
			
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("导师名称");
			row.createCell(2).setCellValue("职称");
			row.createCell(3).setCellValue("学生指标申请");
			row.createCell(4).setCellValue("指标数");
			row.createCell(5).setCellValue("录取学生名单");
			
			int count=0;
			for(int i=0; i<list.size(); i++) {
				for(int j=1; j<5; j++) {
					List<MentorStudentRelation> msrList = msrService.getByGmIdAndTypeAndIsPass(list.get(i).getId(), j, 1);
					if(msrList != null && msrList.size()>0) {
						row = sheet.createRow(++count);

						row.createCell(0).setCellValue(count);
						row.createCell(1).setCellValue(list.get(i).getLeader() + "("+ list.get(i).getMember() +")");
						row.createCell(2).setCellValue(list.get(i).getTitle());
						
						if(j == 1) {
							row.createCell(3).setCellValue("课业学生助教");
							row.createCell(4).setCellValue(list.get(i).getTeachingAsistant());
						} else if(j == 2) {
							row.createCell(3).setCellValue("实验室助教");
							row.createCell(4).setCellValue(list.get(i).getLabAsistant());
						} else if(j == 3) {
							row.createCell(3).setCellValue("课外科技竞赛");
							row.createCell(4).setCellValue(list.get(i).getCompetitionNum());
						} else if(j == 4) {
							row.createCell(3).setCellValue("科研研究助理");
							row.createCell(4).setCellValue(list.get(i).getResearchAsistant());
						}
						
						String str = "";
						//录取学生名单
						for(int k=0; k<msrList.size(); k++) {
							Login login = loginService.getLoginById(msrList.get(k).getStuLoginId());
							Student student = studentService.getStudentById(login.getUserId());
							
							if(k == msrList.size()-1) {
								str += student.getName();
							} else {
								str += student.getName() + ",";
							}
						}
						row.createCell(5).setCellValue(str);
					}
				}
				
			}
			
			this.fileName = "优才导师计划匹配结果表.xlsx";
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
	
	public GiftedMentorService getGmService() {
		return gmService;
	}

	public void setGmService(GiftedMentorService gmService) {
		this.gmService = gmService;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Integer getTeachingAsistant() {
		return teachingAsistant;
	}

	public void setTeachingAsistant(Integer teachingAsistant) {
		this.teachingAsistant = teachingAsistant;
	}

	public Integer getLabAsistant() {
		return labAsistant;
	}

	public void setLabAsistant(Integer labAsistant) {
		this.labAsistant = labAsistant;
	}

	public Integer getCompetitionNum() {
		return competitionNum;
	}

	public void setCompetitionNum(Integer competitionNum) {
		this.competitionNum = competitionNum;
	}

	public Integer getResearchAsistant() {
		return researchAsistant;
	}

	public void setResearchAsistant(Integer researchAsistant) {
		this.researchAsistant = researchAsistant;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public MsrService getMsrService() {
		return msrService;
	}

	public void setMsrService(MsrService msrService) {
		this.msrService = msrService;
	}

	public Integer getStuLoginId() {
		return stuLoginId;
	}

	public void setStuLoginId(Integer stuLoginId) {
		this.stuLoginId = stuLoginId;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
}
