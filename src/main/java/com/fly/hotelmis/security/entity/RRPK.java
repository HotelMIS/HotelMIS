package com.fly.hotelmis.security.entity;

import java.io.Serializable;

public class RRPK implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String rrid;

	private String rid;

	private String reid;

	public String getRrid() {
		return rrid;
	}

	public void setRrid(String rrid) {
		this.rrid = rrid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getReid() {
		return reid;
	}

	public void setReid(String reid) {
		this.reid = reid;
	}

}
