package com.fly.hotelmis.security.entity;

import com.fly.common.entity.BaseEntity;

public class Roles extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String rolename;

	private int enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

}
