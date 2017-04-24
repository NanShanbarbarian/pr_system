package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.SetupProjectDAO;
import edu.zjut.tempest.entity.SetupProject;
import edu.zjut.tempest.service.SetupProjectService;

public class SetupProjectServiceImpl implements SetupProjectService{

	private SetupProjectDAO setupProjectDAO;

	@Override
	public int saveSetupPro(SetupProject setupPro) {
		return setupProjectDAO.saveSetupPro(setupPro);
	}

	public int getCountOnAll() {
		return setupProjectDAO.getCountOnAll();
	}
	@Override
	public List<SetupProject> getAllList(int begin, int rowsPage) {
		return setupProjectDAO.getAllList(begin, rowsPage);
	}

	public List<SetupProject> getAllList() {
		return setupProjectDAO.getAllList();
	}
	
	@Override
	public List<SetupProject> getListByOfficalNumber(String officalNumber) {
		return setupProjectDAO.getListByOfficalNumber(officalNumber);
	}

	@Override
	public List<SetupProject> getListByCategory(String category) {
		return setupProjectDAO.getListByCategory(category);
	}

	@Override
	public List<SetupProject> getListByName(String name) {
		return setupProjectDAO.getListByName(name);
	}
	
	public SetupProject getSetupProById(int id) {
		return setupProjectDAO.getSetupProById(id);
	}
	
	public void deleteSetupPro(SetupProject setupPro) {
		setupProjectDAO.deleteSetupPro(setupPro);
	}
	
	public SetupProjectDAO getSetupProjectDAO() {
		return setupProjectDAO;
	}

	public void setSetupProjectDAO(SetupProjectDAO setupProjectDAO) {
		this.setupProjectDAO = setupProjectDAO;
	}

}
