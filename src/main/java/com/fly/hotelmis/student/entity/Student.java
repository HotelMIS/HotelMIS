package com.fly.hotelmis.student.entity;

/**
 * ----------------------------------------------------------------------------
 * ----- Confidential and Proprietary Copyright 2010 By SGAI & Hewlett-Packard
 * Development Company, L.P. All Rights Reserved
 * 
 * Project Name : SGAI MES Class Name : Person.java Package :
 * com.fly.project.test.vo
 * 
 * @version $Id$
 * @author 刘志才
 * @since 2011-5-20
 */
public class Student extends com.fly.common.entity.BaseEntity {

	private String id;
	private String name;
	private String age;
	private String cardid;
	private String cardname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

}
