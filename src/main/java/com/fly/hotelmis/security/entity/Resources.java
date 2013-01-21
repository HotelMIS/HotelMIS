package com.fly.hotelmis.security.entity;

import com.fly.common.entity.BaseEntity;

public class Resources extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Resources() {
	}

	private String id;

	private String resoname;

	private int type;

	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResoname() {
		return resoname;
	}

	public void setResoname(String resoname) {
		this.resoname = resoname;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
