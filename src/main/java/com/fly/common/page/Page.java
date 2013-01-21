package com.fly.common.page;

import java.util.List;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : Page.java    
 Package      : com.fly.hibernate.util                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-22 
 */
public class Page<T> {
	
	// 一页显示的记录数
	private int pageSize = 10;
	// 记录总数
	private int totalRows;
	// 当前页码
	private int pageNo;
	// 总页数
	@SuppressWarnings("unused")
	private int totalPages;
	
	// 结果集存放List
	private List<T> resultList;
	
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	public List<T> getResultList() {
		return resultList;
	}
	/**
	 * 初始化每页显示的记录数
	 * @param pageSize
	 * @return
	 * @author HP
	 * @since  2011-5-22
	 */
	public int getPageSize(int pageSize){
		if(pageSize == 0){
			return this.pageSize;
		}else{
			this.pageSize = pageSize;
			return this.pageSize;
		}
	}
	/**
	 * 计算当前页码
	 * @param pageNo
	 * @return
	 * @author HP
	 * @since  2011-5-22
	 */
	public int getPageNo(int pageNo) {
		return pageNo == 0 ? 1:pageNo;
	}
	
	/**
	 * 
	 * @return
	 * @author HP
	 * @since  2011-5-22
	 */
	public int getTotalPages() {
		int totalPages = 0;
		if (totalRows % pageSize == 0) {
			totalPages = totalRows / pageSize;
		} else {
			totalPages = (totalRows / pageSize) + 1;
		}
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	

}
