package edu.zjut.tempest.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.ActivityManage;
import edu.zjut.tempest.service.ActivityManageService;

public class ActivityManageAction {

	private ActivityManageService amService;

	private Integer id;
	/**
	 * am_getList   获得系统活动管理记录列表
	 * @return
	 */
	public String am_getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<ActivityManage> list = amService.getAllList();
		
		request.setAttribute("list", list);
		
		return "getList_success";
	}
	
	/**
	 * am_change   修改系统活动的开启或关闭
	 */
	public void am_change() {
		if(id != null) {
			ActivityManage am = amService.getById(id);
			
			if(am != null) {
				if(am.getIsOpen() == 0) {
					am.setIsOpen(1);
				} else if(am.getIsOpen() == 1) {
					am.setIsOpen(0);
				}
				
				amService.updateAM(am);
			}
		}
	}
	
	public ActivityManageService getAmService() {
		return amService;
	}

	public void setAmService(ActivityManageService amService) {
		this.amService = amService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
