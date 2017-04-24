package edu.zjut.tempest.entity;

import java.io.Serializable;

public class MentorStudentRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6579755735027072801L;
	private Integer id;
	private Integer gmId;
	private Integer stuLoginId;
	private Integer type;
	private Integer isPass;
	
	public MentorStudentRelation() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGmId() {
		return gmId;
	}
	public void setGmId(Integer gmId) {
		this.gmId = gmId;
	}
	public Integer getStuLoginId() {
		return stuLoginId;
	}
	public void setStuLoginId(Integer stuLoginId) {
		this.stuLoginId = stuLoginId;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
