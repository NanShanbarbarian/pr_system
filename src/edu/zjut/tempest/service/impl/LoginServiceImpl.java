package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.LoginDAO;
import edu.zjut.tempest.entity.Login;
import edu.zjut.tempest.service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	private LoginDAO loginDAO;

	/**
	 * findLogin 判断用户账号是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	public Login findLogin(String userName,String password) {
		return loginDAO.findLogin(userName, password);
	}
	
	public int saveLogin(Login login) {
		return loginDAO.saveLogin(login);
	}
	
	public void updateLogin(Login login) {
		loginDAO.updateLogin(login);
	}
	
	public void deleteLogin(Login login) {
		loginDAO.deleteLogin(login);
	}
	
	public Login getLoginById(int id) {
		return loginDAO.getLoginById(id);
	}
	
	public Login getLoginByNumber(String number) {
		return loginDAO.getLoginByNumber(number);
	}
	
	public Login getLoginByUserId(int UserId, int role) {
		return loginDAO.getLoginByUserId(UserId, role);
	}
	
	public int getLoginCountByRole(int role) {
		return loginDAO.getLoginCountByRole(role);
	}
	
	public List<Login> getLoginByPageAndRole(int begin, int rowsPage, int role) {
		return loginDAO.getLoginByPageAndRole(begin, rowsPage, role);
	}
	
	/**
	 * getAllList   获得所有已验证的某角色的登录账号列表
	 * @return
	 */
	public List<Login> getAllList(int role) {
		return loginDAO.getAllList(role);
	}
	
	/**
	 * getAllListOnApply   获得所有申请状态的账号列表
	 * @return
	 */
	public List<Login> getAllListOnApply() {
		return loginDAO.getAllListOnApply();
	}
	
	/**
	 * getListOnApply   获得某类角色的申请状态的账号列表
	 * @param role
	 * @return
	 */
	public List<Login> getListOnApply(int role) {
		return loginDAO.getListOnApply(role);
	}
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
}
