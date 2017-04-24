package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.UserProjectRelation;

public interface UprDAO {

	/**
	 * getUPR 通过项目id和项目开发者登录id获得相应的关联数据
	 * @param projectId
	 * @param stuLoginId
	 * @return
	 */
	public UserProjectRelation getUPR(int projectId, int stuLoginId);
	
	public void saveUPR(UserProjectRelation upr);
	
	public void updateUPR(UserProjectRelation upr);
	
	public void deleteUPR(UserProjectRelation upr);
	
	public List<UserProjectRelation> getUPRList(int projectId, int status);
	
	public List<UserProjectRelation> getUPRList(int projectId);
	
}
