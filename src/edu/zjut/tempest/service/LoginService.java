package edu.zjut.tempest.service;

import java.util.List;

import edu.zjut.tempest.entity.Login;

public interface LoginService {
	
	/**
	 * findLogin 判断用户账号是否存在
	 * @param userName
	 * @param password
	 * @return
	 */
	public Login findLogin(String userName,String password);
	
	public int saveLogin(Login login);
	public void updateLogin(Login login);
	public void deleteLogin(Login login);
	public Login getLoginById(int id);
	public Login getLoginByNumber(String number);
	public Login getLoginByUserId(int UserId, int role);
	
	public int getLoginCountByRole(int role);
	public List<Login> getLoginByPageAndRole(int begin, int rowsPage, int role);
	
	/**
	 * getAllList   获得所有已验证的某角色的登录账号列表
	 * @return
	 */
	public List<Login> getAllList(int role);
	
	/**
	 * getAllListOnApply   获得所有申请状态的账号列表
	 * @return
	 */
	public List<Login> getAllListOnApply();
	
	/**
	 * getListOnApply   获得某类角色的申请状态的账号列表
	 * @param role
	 * @return
	 */
	public List<Login> getListOnApply(int role);
}
