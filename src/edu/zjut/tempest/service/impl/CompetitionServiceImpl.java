package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.CompetitionDAO;
import edu.zjut.tempest.entity.Competition;
import edu.zjut.tempest.service.CompetitionService;

public class CompetitionServiceImpl implements CompetitionService{

	private CompetitionDAO competitionDAO;

	public int saveCompetition(Competition competition) {
		return competitionDAO.saveCompetition(competition);
	}

	@Override
	public void updateCompetition(Competition competition) {
		competitionDAO.updateCompetition(competition);
	}
	
	public void deleteCompetition(Competition competition) {
		competitionDAO.deleteCompetition(competition);
	}
	
	public Competition getCompetitionById(int id) {
		return competitionDAO.getCompetitionById(id);
	}
	
	public List<Competition> getListByNumber(String number) {
		return competitionDAO.getListByNumber(number);
	}
	
	public int getCountByIsPass(int isPass) {
		return competitionDAO.getCountByIsPass(isPass);
	}
	
	public List<Competition> getListByIsPass(int begin, int rowsPage, int isPass) {
		return competitionDAO.getListByIsPass(begin, rowsPage, isPass);
	}
	
	/**
	 * getAllList   获得所有的竞赛验证记录
	 * @return
	 */
	public List<Competition> getAllList() {
		return competitionDAO.getAllList();
	}
	
	public List<Competition> getListByName(String workName) {
		return competitionDAO.getListByName(workName);
	}
	
	public List<Competition> getListByCtId(int ctId) {
		return competitionDAO.getListByCtId(ctId);
	}
	
	
	
	public CompetitionDAO getCompetitionDAO() {
		return competitionDAO;
	}

	public void setCompetitionDAO(CompetitionDAO competitionDAO) {
		this.competitionDAO = competitionDAO;
	}

	
}
