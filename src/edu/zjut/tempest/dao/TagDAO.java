package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.Tag;

public interface TagDAO {
	
	public Tag getTagById(int id);
	
	public void saveTag(Tag tag);
	
	public void deleteTag(Tag tag);
	
	public void updateTag(Tag tag);
	
	public Tag getTagByName(String name);
	
	/**
	 * getTagCountOnSearch 模糊查询的标签总数
	 * @param search
	 * @return
	 */
	public int getTagCountOnSearch(String search);
	
	/**
	 * getTagListOnSearch 分页获得模糊查询的标签列表
	 * @param begin
	 * @param rowsPage
	 * @param search
	 * @return
	 */
	public List<Tag> getTagListOnSearch(int begin, int rowsPage,String search);
	
	/**
	 * getTagAllListOnsearch 获得模糊查询的所有标签列表
	 * @param search
	 * @return
	 */
	public List<Tag> getTagAllListOnsearch(String search);
	
	/**
	 * getTagCount 获得标签总数
	 * @return
	 */
	public int getTagCount();
	
	/**
	 * getTagList 分页获得tag列表
	 * @param begin
	 * @param rowsPage
	 * @return
	 */
	public List<Tag> getTagList(int begin, int rowsPage);
	
}
