package edu.zjut.tempest.entity;

import java.io.Serializable;
import java.util.Date;

public class GiftedMentor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3236379035425223637L;
	private Integer id;
	private String  leader;
	private String title;
	private String member;
	private Integer teachingAsistant;
	private Integer labAsistant;
	private Integer competitionNum;
	private Integer researchAsistant;
	private Integer publisherId;
	private Date publishTime;
	private Integer status;
	
	public GiftedMentor() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public Integer getTeachingAsistant() {
		return teachingAsistant;
	}

	public void setTeachingAsistant(Integer teachingAsistant) {
		this.teachingAsistant = teachingAsistant;
	}

	public Integer getLabAsistant() {
		return labAsistant;
	}

	public void setLabAsistant(Integer labAsistant) {
		this.labAsistant = labAsistant;
	}

	public Integer getCompetitionNum() {
		return competitionNum;
	}

	public void setCompetitionNum(Integer competitionNum) {
		this.competitionNum = competitionNum;
	}

	public Integer getResearchAsistant() {
		return researchAsistant;
	}

	public void setResearchAsistant(Integer researchAsistant) {
		this.researchAsistant = researchAsistant;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
