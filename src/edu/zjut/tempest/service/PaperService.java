package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.Paper;

public interface PaperService {

	public int savePaper(Paper paper);
	
	public void updatePaper(Paper paper);
	
	public void deletePaper(Paper paper);
	
	public Paper getPaperById(int id);
	
	public List<Paper> getListByNumber(String number);
	
	/**
	 * getListByName   根据论文名称获得期刊论文记录
	 * @param name
	 * @return
	 */
	public List<Paper> getListByName(String name);
	
	public int getCountByIsPass(int isPass);
	
	public List<Paper> getListByIsPass(int begin, int rowsPage, int isPass);
	
	/**
	 * getAllList   获得所有已验证的期刊论文记录
	 * @return
	 */
	public List<Paper> getAllList();
}
