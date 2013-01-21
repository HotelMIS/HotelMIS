package com.fly.hotelmis.test.dao.impl;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fly.common.dao.impl.BaseDaoImpl;
import com.fly.common.page.Page;
import com.fly.hotelmis.test.dao.PersonDao;
import com.fly.hotelmis.test.entity.Person;
import com.fly.hotelmis.test.vo.PersonVo;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : PersonDaoImpl.java    
 Package      : com.fly.project.test.dao.impl                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-20 
 */
@Repository("personDao")
public class PersonDaoImpl extends BaseDaoImpl implements PersonDao {
	
	/* 保存用户
	 * @see com.fly.project.test.service.PersonService#save(com.fly.project.test.vo.Person)
	 */
	public void save(Person person) {
		this.insert(person);
	}
	
	public void saveAll(List<Person> personList){
		this.insertAll(personList);
	}

	/* 查询所有用户
	 * @see com.fly.project.test.service.PersonService#query()
	 */
	public List<Person> query() {
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;	
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> queryByParams(Map<String,Object> params , String hql){
		//return this.findByParams(params, hql);
		List<Person> pList = (List<Person>)this.findByParams(params, hql);
		for(Person personVo : pList){
			System.out.println(personVo.getName()+"  "+personVo.getAge());
		}
		return pList;
	}
	
	public List<Person> queryPageByQuery(int pageNo , int pageSize , Map<String,Object> params , String sql , String sql_count){
		Page page = this.findPageByQuerySql(pageNo, pageSize, params, sql, sql_count , PersonVo.class);
		List<PersonVo> pList = (List<PersonVo>)page.getResultList();
		for(PersonVo personVo : pList){
			System.out.println(personVo.getName());
		}
		//System.out.println(page.getResultList().get(0));
		//return (List<Person>)this.findPageByQuery(pageNo, pageSize, params, hql, hql_count).getResultList();
		return null;
	}
	
	public List<Person> queryPageByCriteria(int pageNo , int pageSize , Map<String,Object> params){
		return (List<Person>)this.findPageByCriteria(pageNo, pageSize, params , Person.class).getResultList();
	}
	
	public List<PersonVo> queryByParamsSql(Map<String,Object> params , String sql){

		return this.findByParamsSql(params, sql);
	}
	
	public String queryDocumentByParamsSql(Map<String,Object> params , String sql){

		return this.findDocumentByParams(params, sql);
	}
	
	public static void main(String[] args){
		PersonDaoImpl impl = new PersonDaoImpl();
		impl.query();
	}

}
