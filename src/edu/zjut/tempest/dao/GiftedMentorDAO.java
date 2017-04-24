package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.GiftedMentor;

public interface GiftedMentorDAO {

	public int saveGM(GiftedMentor gm);
	
	public void deleteGM(GiftedMentor gm);
	
	public void updateGM(GiftedMentor gm);
	
	public GiftedMentor getById(int id);
	
	public List<GiftedMentor> getByPublisherId(int publisherId);
	
	public int getAllCount();
	
	public List<GiftedMentor> getListByPages(int begin, int rowsPage);
	
	public int getSelectCount();
	public List<GiftedMentor> getSelectListByPages(int begin, int rowsPage);
	
	/**
	 * getAllList   获得优才导师计划的所有记录
	 * @return
	 */
	public List<GiftedMentor> getAllList();
	
	/**
	 * deleteAllGm   删除数据库中所有数据记录
	 * @return
	 */
	public int deleteAllGm();
}
