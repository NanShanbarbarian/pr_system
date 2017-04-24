package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.PaperDAO;
import edu.zjut.tempest.entity.Paper;
import edu.zjut.tempest.service.PaperService;

public class PaperServiceImpl implements PaperService{

	private PaperDAO paperDAO;

	@Override
	public int savePaper(Paper paper) {
		return paperDAO.savePaper(paper);
	}

	@Override
	public void updatePaper(Paper paper) {
		paperDAO.updatePaper(paper);
	}

	@Override
	public void deletePaper(Paper paper) {
		paperDAO.deletePaper(paper);
	}

	@Override
	public Paper getPaperById(int id) {
		return paperDAO.getPaperById(id);
	}
	
	@Override
	public List<Paper> getListByNumber(String number) {
		return paperDAO.getListByNumber(number);
	}

	/**
	 * getListByName   根据论文名称获得期刊论文记录
	 * @param name
	 * @return
	 */
	public List<Paper> getListByName(String name) {
		return paperDAO.getListByName(name);
	}
	
	@Override
	public int getCountByIsPass(int isPass) {
		return paperDAO.getCountByIsPass(isPass);
	}

	@Override
	public List<Paper> getListByIsPass(int begin, int rowsPage, int isPass) {
		return paperDAO.getListByIsPass(begin, rowsPage, isPass);
	}


	/**
	 * getAllList   获得所有已验证的期刊论文记录
	 * @return
	 */
	public List<Paper> getAllList() {
		return paperDAO.getAllList();
	}
	
	public PaperDAO getPaperDAO() {
		return paperDAO;
	}

	public void setPaperDAO(PaperDAO paperDAO) {
		this.paperDAO = paperDAO;
	}

}
