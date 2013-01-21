package com.fly.hotelmis.test.vo;


/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : Person.java    
 Package      : com.fly.project.test.vo                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-20 
 */
public class PersonVo{
	private String id;
	private String name ; 
	private String age ;
	private String cardId ; 
	private String cardName ;
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
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}
