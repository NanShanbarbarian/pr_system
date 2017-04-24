package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.CompetitionTypeDAO;
import edu.zjut.tempest.entity.CompetitionType;
import edu.zjut.tempest.service.CompetitionTypeService;

public class CompetitionTypeServiceImpl implements CompetitionTypeService {
	
	private CompetitionTypeDAO competitionTypeDAO;
	
	public void saveCT(CompetitionType ct) {
		competitionTypeDAO.saveCT(ct);
	}

	public CompetitionType getByNameAndCategory(String name, int category) {
		return competitionTypeDAO.getByNameAndCategory(name, category);
	}
	
	public CompetitionType getById(int id) {
		return competitionTypeDAO.getById(id);
	}
	/**
	 * getListByCategory 获得某种类别的赛事列表
	 * @param category
	 * @return
	 */
	public List<CompetitionType> getListByCategory(int category) {
		return competitionTypeDAO.getListByCategory(category);
	}
	
	public CompetitionTypeDAO getCompetitionTypeDAO() {
		return competitionTypeDAO;
	}

	public void setCompetitionTypeDAO(CompetitionTypeDAO competitionTypeDAO) {
		this.competitionTypeDAO = competitionTypeDAO;
	}

}
