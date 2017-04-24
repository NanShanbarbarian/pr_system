package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import edu.zjut.tempest.entity.CompetitionType;
import edu.zjut.tempest.service.CompetitionTypeService;

public class CompetitionTypeAction {

	private CompetitionTypeService competitionTypeService;
	private Integer id;
	private String name;
	private Integer category;
	
	/**
	 * ct_getListByCategory 获得某类别的赛事列表
	 * @throws IOException 
	 */
	public void ct_getListByCategory() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<CompetitionType> list = competitionTypeService.getListByCategory(category);
		
		String ja = JSON.toJSONString(list);
		out.print(ja);
		out.flush();
		out.close();
		
	}
	
	public CompetitionTypeService getCompetitionTypeService() {
		return competitionTypeService;
	}

	public void setCompetitionTypeService(CompetitionTypeService competitionTypeService) {
		this.competitionTypeService = competitionTypeService;
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}
