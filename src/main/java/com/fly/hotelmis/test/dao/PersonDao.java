package com.fly.hotelmis.test.dao;

import java.util.List;
import java.util.Map;

import com.fly.common.dao.BaseDao;
import com.fly.hotelmis.test.entity.Person;
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
public interface PersonDao extends BaseDao {
	public void save(Person person);
	public void saveAll(List<Person> personList);
	public List<Person> query();
	public List<Person> queryByParams(Map<String,Object> params , String hql);
	public List<Person> queryPageByQuery(int pageNo , int pageSize , Map<String,Object> params , String hql , String hql_count);
	public List<Person> queryPageByCriteria(int pageNo , int pageSize , Map<String,Object> params );
	public List<PersonVo> queryByParamsSql(Map<String,Object> params , String sql);
	public String queryDocumentByParamsSql(Map<String,Object> params , String sql);
}
