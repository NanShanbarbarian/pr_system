package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.Competition;

public interface CompetitionService {

	public int saveCompetition(Competition competition);
	
	public void updateCompetition(Competition competition);
	
	public void deleteCompetition(Competition competition);
	
	public Competition getCompetitionById(int id);
	
	public List<Competition> getListByNumber(String number);
	
	/**
	 * getCountByIsPass 获得该状态的竞赛获奖记录数
	 * @param isPass
	 * @return
	 */
	public int getCountByIsPass(int isPass);
	
	/**
	 * getListByIsPass 分页获得该状态的竞赛获奖记录列表
	 * @param begin
	 * @param rowsPage
	 * @param isPass
	 * @return
	 */
	public List<Competition> getListByIsPass(int begin, int rowsPage, int isPass);
	
	/**
	 * getAllList   获得所有的竞赛验证记录
	 * @return
	 */
	public List<Competition> getAllList();
	
	public List<Competition> getListByName(String workName);
	public List<Competition> getListByCtId(int ctId);
}
