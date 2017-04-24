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

import edu.zjut.tempest.entity.ProjectTagRelation;
import edu.zjut.tempest.service.PtrService;
import edu.zjut.tempest.service.TagService;

public class PtrAction {

	private PtrService ptrService;
	private TagService tagService;
	private Integer projectId;

	/**
	 * ptr_getByProjectId 通过projectId获得相应标签
	 * @throws IOException 
	 */
	public void ptr_getByProjectId() throws IOException {
		ProjectTagRelation ptr = ptrService.getProjectByProjectId(projectId);
		
		if(ptr != null) {
			HttpServletResponse  response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			
			if(ptr.getTagId1() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(ptr.getTagId1()).getName());
				list.add(map);
			}
			if(ptr.getTagId2() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(ptr.getTagId2()).getName());
				list.add(map);
			}
			if(ptr.getTagId3() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(ptr.getTagId3()).getName());
				list.add(map);
			}
			if(ptr.getTagId4() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(ptr.getTagId4()).getName());
				list.add(map);
			}
			if(ptr.getTagId5() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(ptr.getTagId5()).getName());
				list.add(map);			}
			if(ptr.getTagId6() != null) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("tag", tagService.getTagById(ptr.getTagId6()).getName());
				list.add(map);
			}
			
			String jo = JSON.toJSONString(list);
			
			out.print(jo);
			out.flush();
			out.close();
		}
	}
	
	public PtrService getPtrService() {
		return ptrService;
	}

	public void setPtrService(PtrService ptrService) {
		this.ptrService = ptrService;
	}
	
	public TagService getTagService() {
		return tagService;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	
}
