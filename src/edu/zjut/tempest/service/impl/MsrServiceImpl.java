package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.MsrDAO;
import edu.zjut.tempest.entity.MentorStudentRelation;
import edu.zjut.tempest.service.MsrService;

public class MsrServiceImpl implements MsrService {

	private MsrDAO msrDAO;
	
	@Override
	public int saveMsr(MentorStudentRelation msr) {
		return msrDAO.saveMsr(msr);
	}

	@Override
	public void delteMsr(MentorStudentRelation msr) {
		msrDAO.delteMsr(msr);
	}

	@Override
	public void updateMsr(MentorStudentRelation msr) {
		msrDAO.updateMsr(msr);
	}

	@Override
	public List<MentorStudentRelation> getByGmId(int gmId) {
		return msrDAO.getByGmId(gmId);
	}

	public MentorStudentRelation getByGmIdAndLoginId(int gmId, int stuLoginId) {
		return msrDAO.getByGmIdAndLoginId(gmId, stuLoginId);
	}
	
	public List<MentorStudentRelation> getByStuLoginId(int stuLoginId) {
		return msrDAO.getByStuLoginId(stuLoginId);
	}
	
	public MentorStudentRelation getById(int id) {
		return msrDAO.getById(id);
	}
	
	public List<MentorStudentRelation> getByGmIdAndTypeAndIsPass(int gmId, int type, int isPass) {
		return msrDAO.getByGmIdAndTypeAndIsPass(gmId, type, isPass);
	}
	
	/**
	 * deleteAllMsr   删除优才导师中间表的所有记录
	 * @return
	 */
	public void deleteAllMsr() {
		msrDAO.deleteAllMsr();
	}
	
	public MsrDAO getMsrDAO() {
		return msrDAO;
	}

	public void setMsrDAO(MsrDAO msrDAO) {
		this.msrDAO = msrDAO;
	}

}
