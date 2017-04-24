package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.SoftwareCopyright;

public interface ScService {

	public int saveSc(SoftwareCopyright sc);
	public void updateSc(SoftwareCopyright sc);
	
	public void deleteSc(SoftwareCopyright sc);
	
	public SoftwareCopyright getScById(int id);
	
	public List<SoftwareCopyright> getListByNumber(String number);
	
	/**
	 * getListByName   根据软件著作名称获得软件著作记录列表
	 * @param name
	 * @return
	 */
	public List<SoftwareCopyright> getListByName(String name);
	
	public int getCountByIsPass(int isPass);
	
	public List<SoftwareCopyright> getListByIsPass(int begin, int rowsPage, int isPass);
	
	/**
	 * getAllList   获得所有的已验证软件著作记录
	 * @return
	 */
	public List<SoftwareCopyright> getAllList();
}
