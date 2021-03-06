package edu.zjut.tempest.entity;
// Generated 2017-3-9 20:00:46 by Hibernate Tools 5.2.0.CR1

/**
 * UserProjectRelation generated by hbm2java
 */
public class UserProjectRelation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398772531615924474L;
	private Integer id;
	private Integer projectId;
	private Integer stuLoginId;
	private Integer teaLoginId;
	private Integer status;
	private Integer stuScore;
	private Integer teaScore;
	private String stuRemark;
	private String teaRemark;

	public UserProjectRelation() {
	}

	public UserProjectRelation(int projectId, int stuLoginId, int teaLoginId, int status, int stuScore, int teaScore) {
		this.projectId = projectId;
		this.stuLoginId = stuLoginId;
		this.teaLoginId = teaLoginId;
		this.status = status;
		this.stuScore = stuScore;
		this.teaScore = teaScore;
	}

	public UserProjectRelation(int projectId, int stuLoginId, int teaLoginId, int status, int stuScore, int teaScore,
			String stuRemark, String teaRemark) {
		this.projectId = projectId;
		this.stuLoginId = stuLoginId;
		this.teaLoginId = teaLoginId;
		this.status = status;
		this.stuScore = stuScore;
		this.teaScore = teaScore;
		this.stuRemark = stuRemark;
		this.teaRemark = teaRemark;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getStuLoginId() {
		return this.stuLoginId;
	}

	public void setStuLoginId(Integer stuLoginId) {
		this.stuLoginId = stuLoginId;
	}

	public Integer getTeaLoginId() {
		return this.teaLoginId;
	}

	public void setTeaLoginId(Integer teaLoginId) {
		this.teaLoginId = teaLoginId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStuScore() {
		return this.stuScore;
	}

	public void setStuScore(Integer stuScore) {
		this.stuScore = stuScore;
	}

	public Integer getTeaScore() {
		return this.teaScore;
	}

	public void setTeaScore(Integer teaScore) {
		this.teaScore = teaScore;
	}

	public String getStuRemark() {
		return this.stuRemark;
	}

	public void setStuRemark(String stuRemark) {
		this.stuRemark = stuRemark;
	}

	public String getTeaRemark() {
		return this.teaRemark;
	}

	public void setTeaRemark(String teaRemark) {
		this.teaRemark = teaRemark;
	}

}
