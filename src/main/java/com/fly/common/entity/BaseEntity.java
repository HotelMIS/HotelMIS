package com.fly.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : BaseEntity.java    
 Package      : com.fly.hibernate.entity                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-21 
 */
@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable {
	
	/** 序列主键 */
	private String id;
	
	/** 乐观锁版本 */
	private Integer version=new Integer(1);
	
	/** 创建者(登录帐号) */
	private String createdBy;

	/** 创建时间 */
	private Date createdDt=new Date();

	/** 最后更新者(登录帐号) */
	private String updatedBy;

	/** 最后更新时间 */
	private Date updatedDt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}

}
