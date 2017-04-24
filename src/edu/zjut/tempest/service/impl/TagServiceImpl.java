package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.TagDAO;
import edu.zjut.tempest.entity.Tag;
import edu.zjut.tempest.service.TagService;

public class TagServiceImpl implements TagService{

	private TagDAO tagDAO;

	public Tag getTagById(int id) {
		return tagDAO.getTagById(id);
	}
	
	public void saveTag(Tag tag) {
		tagDAO.saveTag(tag);
	}
	
	public void deleteTag(Tag tag) {
		tagDAO.deleteTag(tag);
	}
	
	public void updateTag(Tag tag) {
		tagDAO.updateTag(tag);
	}
	
	public Tag getTagByName(String name) {
		return tagDAO.getTagByName(name);
	}
	
	public int getTagCount() {
		return tagDAO.getTagCount();
	}

	public List<Tag> getTagList(int begin, int rowsPage) {
		return tagDAO.getTagList(begin, rowsPage);
	}
	
	public int getTagCountOnSearch(String search) {
		return tagDAO.getTagCountOnSearch(search);
	}

	public List<Tag> getTagListOnSearch(int begin, int rowsPage, String search) {
		return tagDAO.getTagListOnSearch(begin, rowsPage, search);
	}

	/**
	 * getTagAllListOnsearch 获得模糊查询的所有标签列表
	 * @param search
	 * @return
	 */
	public List<Tag> getTagAllListOnsearch(String search) {
		return tagDAO.getTagAllListOnsearch(search);
	}
	
	public TagDAO getTagDAO() {
		return tagDAO;
	}

	public void setTagDAO(TagDAO tagDAO) {
		this.tagDAO = tagDAO;
	}

}
