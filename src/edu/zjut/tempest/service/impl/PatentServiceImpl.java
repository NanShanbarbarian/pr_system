package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.PatentDAO;
import edu.zjut.tempest.entity.Patent;
import edu.zjut.tempest.service.PatentService;

public class PatentServiceImpl implements PatentService{
	
	private PatentDAO patentDAO;
	
	@Override
	public int savePatent(Patent patent) {
		return patentDAO.savePatent(patent);
	}

	@Override
	public void updatePatent(Patent patent) {
		patentDAO.updatePatent(patent);
	}

	@Override
	public void deletePatent(Patent patent) {
		patentDAO.deletePatent(patent);
	}

	@Override
	public Patent getPatentById(int id) {
		return patentDAO.getPatentById(id);
	}

	@Override
	public List<Patent> getListByNumber(String number) {
		return patentDAO.getListByNumber(number);
	}

	/**
	 * getListByName   通过专利名称获得专利记录列表
	 * @param name
	 * @return
	 */
	public List<Patent> getListByName(String name) {
		return patentDAO.getListByName(name);
	}
	
	/**
	 * getListByPaperNumber   通过专利号获得专利记录列表
	 * @param number
	 * @return
	 */
	public List<Patent> getListByPaperNumber(String number) {
		return patentDAO.getListByPaperNumber(number);
	}
	
	@Override
	public int getCountByIsPass(int isPass) {
		return patentDAO.getCountByIsPass(isPass);
	}

	@Override
	public List<Patent> getListByIsPass(int begin, int rowsPage, int isPass) {
		return patentDAO.getListByIsPass(begin, rowsPage, isPass);
	}
	
	/**
	 * getAllList   获得所有已验证的专利记录
	 * @return
	 */
	public List<Patent> getAllList() {
		return patentDAO.getAllList();
	}
	
	public PatentDAO getPatentDAO() {
		return patentDAO;
	}

	public void setPatentDAO(PatentDAO patentDAO) {
		this.patentDAO = patentDAO;
	}

}
