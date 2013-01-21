package com.fly.hotelmis.test.entity;


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
public class Person extends com.fly.common.entity.BaseEntity {
	private String name ; 
	private String age ;
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
	
}
