package com.fly.hotelmis.security.entity;

import com.fly.common.entity.BaseEntity;

public class RoleidUrl extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleid;

	private String url;
	
	public RoleidUrl(){
		
	}
			
	
	public RoleidUrl(String rid,String url){
		this.roleid = rid;
		this.url = url;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
