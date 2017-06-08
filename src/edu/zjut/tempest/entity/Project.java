package edu.zjut.tempest.entity;
// Generated 2017-3-9 20:00:46 by Hibernate Tools 5.2.0.CR1

import java.util.Date;

/**
 * Project generated by hbm2java
 */
public class Project implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4312301145865912751L;
	private Integer id;
	private String name;
	private String num;
	private String period;
	private String keyword;
	private String description;
	private String requirement;
	private Integer teacherId;
	private Integer status;
	private Integer selectCount;
	private String state;
	private Date createtime;

	public Project() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return this.requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSelectCount() {
		return this.selectCount;
	}

	public void setSelectCount(Integer selectCount) {
		this.selectCount = selectCount;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
