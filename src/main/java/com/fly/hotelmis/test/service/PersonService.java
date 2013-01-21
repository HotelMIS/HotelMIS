package com.fly.hotelmis.test.service;

import java.util.List;
import java.util.Map;

import com.fly.hotelmis.test.vo.PersonVo;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : PersonService.java    
 Package      : com.fly.project.test.service                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-20 
 */
public interface PersonService {
	public void save(PersonVo person);
	public void saveAll(List<PersonVo> personVoList);
	public void query();
	public List<PersonVo> queryByParams(Map<String,Object> params , String hql);
	public List<PersonVo> queryPageByQuery(int pageNo , int pageSize , Map<String,Object> params , String hql , String hql_count);
	public List<PersonVo> findPageByCriteria(int pageNo , int pageSize , Map<String,Object> params);
	public List<PersonVo> queryByParamsSql(Map<String,Object> params , String sql);
	public String queryDocumentByParamsSql(Map<String,Object> params , String sql);
}
