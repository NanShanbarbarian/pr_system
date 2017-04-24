package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.Competition;

public interface CompetitionDAO {

	public int saveCompetition(Competition competition);
	
	public void updateCompetition(Competition competition);
	
	public void deleteCompetition(Competition competition);
	
	public Competition getCompetitionById(int id);
	
	public List<Competition> getListByNumber(String number);
	
	/**
	 * getAllList   获得所有的竞赛验证记录
	 * @return
	 */
	public List<Competition> getAllList();
	
	public int getCountByIsPass(int isPass);
	
	public List<Competition> getListByIsPass(int begin, int rowsPage, int isPass);
	
	public List<Competition> getListByName(String workName);
	public List<Competition> getListByCtId(int ctId);
	
}
