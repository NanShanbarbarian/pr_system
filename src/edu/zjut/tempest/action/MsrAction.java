package edu.zjut.tempest.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.entity.MentorStudentRelation;
import edu.zjut.tempest.entity.Student;
import edu.zjut.tempest.service.LoginService;
import edu.zjut.tempest.service.MsrService;
import edu.zjut.tempest.service.StudentService;

public class MsrAction {

	private MsrService msrService;
	private LoginService loginService;
	private StudentService studentService;
	
	private Integer id;
	private Integer gmId;
	private Integer stuLoginId;
	private Integer type;
	private Integer isPass;
	
	public void msr_save() {
		if(gmId!=null && stuLoginId!=null && type!=null) {
			try {
				MentorStudentRelation msr = new MentorStudentRelation();
				msr.setGmId(gmId);
				msr.setStuLoginId(stuLoginId);
				msr.setType(type);
				msr.setIsPass(0);   //申请状态
				
				msrService.saveMsr(msr);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void msr_remove() {
		if(id != null) {
			try {
				MentorStudentRelation msr = msrService.getById(id);
				
				msrService.delteMsr(msr);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * msr_applyStudent   获得申请人员列表
	 * @return
	 */
	public String msr_applyStudent() {
		if(gmId!=null && type!=null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<MentorStudentRelation> msrList = msrService.getByGmIdAndTypeAndIsPass(gmId, type, 0);   
			
			if(msrList != null && msrList.size()>0) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				for(int i=0; i<msrList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					Login login = loginService.getLoginById(msrList.get(i).getStuLoginId());
					Student student = studentService.getStudentById(login.getUserId());
					
					map.put("loginId", login.getId());
					map.put("name", student.getName());
					map.put("className", student.getClassName());
					
					list.add(map);
				}
				
				request.setAttribute("list", list);
			}
		}
		return "applyStudent_success";
	}
	
	/**
	 * msr_selectedStudent   获得已选人员列表
	 * @return
	 */
	public String msr_selectedStudent() {
		if(gmId!=null && type!=null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<MentorStudentRelation> msrList = msrService.getByGmIdAndTypeAndIsPass(gmId, type, 1);
			
			if(msrList != null && msrList.size()>0) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				
				for(int i=0; i<msrList.size(); i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					Login login = loginService.getLoginById(msrList.get(i).getStuLoginId());
					Student student = studentService.getStudentById(login.getUserId());
					
					map.put("loginId", login.getId());
					map.put("name", student.getName());
					map.put("className", student.getClassName());
					
					list.add(map);
				}
				
				request.setAttribute("list", list);
			}
		}
		return "selectedStudent_success";
	}
	
	public void msr_accept() {
		if(gmId!=null && stuLoginId!=null && type!=null) {
			try {
				MentorStudentRelation msr = msrService.getByGmIdAndLoginId(gmId, stuLoginId);
				
				if(msr != null) {
					msr.setIsPass(1);   //已验证状态
					
					msrService.updateMsr(msr);
					
					//老师选中后，删除其它的
//					List<MentorStudentRelation> msrList = msrService.getByStuLoginId(stuLoginId);
//					if(msrList)
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void msr_refuse() {
		if(gmId!=null && stuLoginId!=null && type!=null) {
			try {
				MentorStudentRelation msr = msrService.getByGmIdAndLoginId(gmId, stuLoginId);
				
				if(msr != null) {
					msr.setIsPass(2);   //拒绝状态
					
					msrService.updateMsr(msr);
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public MsrService getMsrService() {
		return msrService;
	}

	public void setMsrService(MsrService msrService) {
		this.msrService = msrService;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGmId() {
		return gmId;
	}

	public void setGmId(Integer gmId) {
		this.gmId = gmId;
	}

	public Integer getStuLoginId() {
		return stuLoginId;
	}

	public void setStuLoginId(Integer stuLoginId) {
		this.stuLoginId = stuLoginId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
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
