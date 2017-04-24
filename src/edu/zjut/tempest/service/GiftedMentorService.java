package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.GiftedMentor;

public interface GiftedMentorService {

	public int saveGM(GiftedMentor gm);
	
	public void deleteGM(GiftedMentor gm);
	
	public void updateGM(GiftedMentor gm);
	
	public GiftedMentor getById(int id);
	
	public List<GiftedMentor> getByPublisherId(int publisherId);
	
	public int getAllCount();
	
	public List<GiftedMentor> getListByPages(int begin, int rowsPage);
	
	public List<GiftedMentor> getAllList();
	
	public int getSelectCount();
	public List<GiftedMentor> getSelectListByPages(int begin, int rowsPage);
	
	/**
	 * deleteAllGm   删除数据库中所有数据记录
	 * @return
	 */
	public int deleteAllGm();
}
