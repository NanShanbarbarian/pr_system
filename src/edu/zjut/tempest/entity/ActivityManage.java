package edu.zjut.tempest.entity;

import java.io.Serializable;

public class ActivityManage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6679574649573608353L;
	private Integer id;
	private String name;
	private Integer isOpen;
	
	private ActivityManage() {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
