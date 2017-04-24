package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.ScDAO;
import edu.zjut.tempest.entity.SoftwareCopyright;
import edu.zjut.tempest.service.ScService;

public class ScServiceImpl implements ScService{
	
	private ScDAO scDAO;

	@Override
	public int saveSc(SoftwareCopyright sc) {
		return scDAO.saveSc(sc);
	}
	@Override
	public void updateSc(SoftwareCopyright sc) {
		scDAO.updateSc(sc);
	}

	@Override
	public void deleteSc(SoftwareCopyright sc) {
		scDAO.deleteSc(sc);
	}

	@Override
	public SoftwareCopyright getScById(int id) {
		return scDAO.getScById(id);
	}

	@Override
	public List<SoftwareCopyright> getListByNumber(String number) {
		return scDAO.getListByNumber(number);
	}

	/**
	 * getListByName   根据软件著作名称获得软件著作记录列表
	 * @param name
	 * @return
	 */
	public List<SoftwareCopyright> getListByName(String name) {
		return scDAO.getListByName(name);
	}
	
	@Override
	public int getCountByIsPass(int isPass) {
		return scDAO.getCountByIsPass(isPass);
	}

	@Override
	public List<SoftwareCopyright> getListByIsPass(int begin, int rowsPage, int isPass) {
		return scDAO.getListByIsPass(begin, rowsPage, isPass);
	}

	/**
	 * getAllList   获得所有的已验证软件著作记录
	 * @return
	 */
	public List<SoftwareCopyright> getAllList() {
		return scDAO.getAllList();
	}
	
	public ScDAO getScDAO() {
		return scDAO;
	}

	public void setScDAO(ScDAO scDAO) {
		this.scDAO = scDAO;
	}

}
