package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.Patent;

public interface PatentService {

	public int savePatent(Patent patent);
	
	public void updatePatent(Patent patent);
	
	public void deletePatent(Patent patent);
	
	public Patent getPatentById(int id);
	
	public List<Patent> getListByNumber(String number);
	
	/**
	 * getListByName   通过专利名称获得专利记录列表
	 * @param name
	 * @return
	 */
	public List<Patent> getListByName(String name);
	
	/**
	 * getListByPaperNumber   通过专利号获得专利记录列表
	 * @param number
	 * @return
	 */
	public List<Patent> getListByPaperNumber(String number);
	
	public int getCountByIsPass(int isPass);
	
	public List<Patent> getListByIsPass(int begin, int rowsPage, int isPass);
	
	/**
	 * getAllList   获得所有已验证的专利记录
	 * @return
	 */
	public List<Patent> getAllList();
}
