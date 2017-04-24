package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.GiftedMentorDAO;
import edu.zjut.tempest.entity.GiftedMentor;
import edu.zjut.tempest.service.GiftedMentorService;

public class GiftedMentorServiceImpl implements GiftedMentorService {
	
	private GiftedMentorDAO gmDAO;

	@Override
	public int saveGM(GiftedMentor gm) {
		return gmDAO.saveGM(gm);
	}

	@Override
	public void deleteGM(GiftedMentor gm) {
		gmDAO.deleteGM(gm);
	}

	@Override
	public void updateGM(GiftedMentor gm) {
		gmDAO.updateGM(gm);
	}

	@Override
	public GiftedMentor getById(int id) {
		return gmDAO.getById(id);
	}

	@Override
	public List<GiftedMentor> getByPublisherId(int publisherId) {
		return gmDAO.getByPublisherId(publisherId);
	}

	@Override
	public int getAllCount() {
		return gmDAO.getAllCount();
	}

	@Override
	public List<GiftedMentor> getListByPages(int begin, int rowsPage) {
		return gmDAO.getListByPages(begin, rowsPage);
	}

	@Override
	public List<GiftedMentor> getAllList() {
		return gmDAO.getAllList();
	}
	
	public int getSelectCount() {
		return gmDAO.getSelectCount();
	}
	public List<GiftedMentor> getSelectListByPages(int begin, int rowsPage) {
		return gmDAO.getSelectListByPages(begin, rowsPage);
	}
	
	/**
	 * deleteAllGm   删除数据库中所有数据记录
	 * @return
	 */
	public int deleteAllGm() {
		return gmDAO.deleteAllGm();
	}

	public GiftedMentorDAO getGmDAO() {
		return gmDAO;
	}

	public void setGmDAO(GiftedMentorDAO gmDAO) {
		this.gmDAO = gmDAO;
	}

}
