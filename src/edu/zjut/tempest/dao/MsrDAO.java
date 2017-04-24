package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.MentorStudentRelation;

public interface MsrDAO {
	
	public int saveMsr(MentorStudentRelation msr);
	
	public void delteMsr(MentorStudentRelation msr);
	
	public void updateMsr(MentorStudentRelation msr);
	
	public MentorStudentRelation getById(int id);
	
	public List<MentorStudentRelation> getByGmId(int gmId);

	public MentorStudentRelation getByGmIdAndLoginId(int gmId, int stuLoginId);
	
	public List<MentorStudentRelation> getByStuLoginId(int stuLoginId);
	
	public List<MentorStudentRelation> getByGmIdAndTypeAndIsPass(int gmId, int type, int isPass);
	
	/**
	 * deleteAllMsr   删除优才导师中间表的所有记录
	 * @return
	 */
	public void deleteAllMsr();
}
