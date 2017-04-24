package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import edu.zjut.tempest.entity.Tag;
import edu.zjut.tempest.service.TagService;

public class TagAction {

	private Integer id;
	private String name;
	private int num;
	
	private TagService tagService;
	private static int currPage = 1;
	private static int rowsPage = 10;

	public String tag_getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = tagService.getTagCount();   //获得项目发布者的人数
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
			List<Tag> tagList = tagService.getTagList(begin, rowsPage);
//			List<Teacher> teacherList = new ArrayList<Teacher>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<tagList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				Tag tag = tagService.getTagById(tagList.get(i).getId());
				map.put("id", tag.getId());
				map.put("name", tag.getName());
				map.put("num", tag.getNum());
				
				list.add(map);
			}
			
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getList_success";
		}
		
		return "getList_success";
	}
	
	/**
	 * tag_save 新增标签
	 */
	public void tag_save() {
		Tag tag = new Tag();
		
		tag.setName(name);
		tag.setNum(0);
		
		tagService.saveTag(tag);
	}
	
	/**
	 * tag_delete 删除标签
	 */
	public void tag_delete() {
		
		Tag tag = tagService.getTagById(id);
		tagService.deleteTag(tag);
	}
	
	/**
	 * tag_getListOnSearch 分页获得标签模糊查询列表
	 * @return
	 */
	public String tag_getListOnSearch() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = tagService.getTagCountOnSearch(name);   //获得项目发布者的人数
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
			List<Tag> tagList = tagService.getTagListOnSearch(begin, begin, name);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(int i=0; i<tagList.size(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				Tag tag = tagService.getTagById(tagList.get(i).getId());
				map.put("id", tag.getId());
				map.put("name", tag.getName());
				map.put("num", tag.getNum());
				
				list.add(map);
			}
			
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getListOnSearch_success";
		}
		
		return "getListOnSearch_success";
	}
	
	public void tag_search() throws IOException {
		
		List<Tag> tagList = tagService.getTagAllListOnsearch(name);
		if(!name.equals("")) {
			HttpServletResponse  response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if(tagList != null) {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				
				for(int i=0; i<tagList.size(); i++) {
					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put("name", tagList.get(i).getName());
					list.add(map);
				}
				
				String ja = JSON.toJSONString(list);
				out.print(ja);
				out.flush(); 
				out.close();
				
			} else {
				out.print(0);
				out.flush(); 
				out.close();
			}
		}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
