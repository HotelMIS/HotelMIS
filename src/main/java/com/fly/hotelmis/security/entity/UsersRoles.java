package com.fly.hotelmis.security.entity;

import com.fly.common.entity.BaseEntity;

public class UsersRoles extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String urid;

	private String uid;

	private String rid;

	public String getUrid() {
		return urid;
	}

	public void setUrid(String urid) {
		this.urid = urid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

}
