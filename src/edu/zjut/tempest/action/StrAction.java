package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import edu.zjut.tempest.entity.StudentTagRelation;
import edu.zjut.tempest.service.StrService;
import edu.zjut.tempest.service.TagService;

public class StrAction {

	private StrService strService;
	private TagService tagService;
	private Integer id;
	private Integer loginId;

	/**
	 * str_getByStuLoginId 通过项目开发者登录id得到该用户的标签关联
	 * @throws IOException
	 */
	public void str_getByStuLoginId() throws IOException {
		StudentTagRelation str = strService.getByStuLoginId(loginId);
		
		if(str != null) {
			HttpServletResponse  response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			
			if(str.getTagId1() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(str.getTagId1()).getName());
				list.add(map);
			}
			if(str.getTagId2() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(str.getTagId2()).getName());
				list.add(map);
			}
			if(str.getTagId3() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(str.getTagId3()).getName());
				list.add(map);
			}
			if(str.getTagId4() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(str.getTagId4()).getName());
				list.add(map);
			}
			if(str.getTagId5() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(str.getTagId5()).getName());
				list.add(map);			}
			if(str.getTagId6() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(str.getTagId6()).getName());
				list.add(map);
			}
			
			String jo = JSON.toJSONString(list);
			
			out.print(jo);
			out.flush();
			out.close();
		}
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
}
