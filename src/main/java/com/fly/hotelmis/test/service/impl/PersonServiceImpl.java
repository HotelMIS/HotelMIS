package com.fly.hotelmis.test.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fly.hotelmis.test.dao.PersonDao;
import com.fly.hotelmis.test.entity.Person;
import com.fly.hotelmis.test.service.PersonService;
import com.fly.hotelmis.test.vo.PersonVo;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : PersonServiceImpl.java    
 Package      : com.fly.project.test.service.impl                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-20 
 */
@Service("personService")@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Resource private PersonDao personDao;
	

	/* 保存用户
	 * @see com.fly.project.test.service.PersonService#save(com.fly.project.test.vo.Person)
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void save(PersonVo personVo) {
		Person person = new Person();
		try {
			BeanUtils.copyProperties(person, personVo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		personDao.save(person);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveAll(List<PersonVo> personVoList){
		List<Person> personList = new ArrayList<Person>();
		for(PersonVo personVo : personVoList){
			Person person = new Person();
			try {
				BeanUtils.copyProperties(person, personVo);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			personList.add(person);
		}
		personDao.saveAll(personList);
	}
	/* 查询所有用户
	 * @see com.fly.project.test.service.PersonService#query()
	 */
	public void query() {
		
	}
	
	public List<PersonVo> queryByParams(Map<String,Object> params , String hql){
		List<PersonVo> personVoList = new ArrayList<PersonVo>();
		List<Person> personList = personDao.queryByParams(params, hql);
		System.out.println("测试用Bean值："+personList);
		for(Person person : personList){
			PersonVo personVo = new PersonVo();
			try {
				BeanUtils.copyProperties(personVo, person);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			personVoList.add(personVo);
		}
		return personVoList ; 
	}

	public List<PersonVo> queryPageByQuery(int pageNo , int pageSize , Map<String,Object> params , String hql , String hql_count){
		List<PersonVo> personVoList = new ArrayList<PersonVo>();
		List<Person> personList = personDao.queryPageByQuery(pageNo,pageSize , params, hql,hql_count);
//		for(Person person : personList){
//			PersonVo personVo = new PersonVo();
//			try {
//				BeanUtils.copyProperties(personVo, person);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			personVoList.add(personVo);
//		}
//		for(int i = 0 ; i <  personList.size() ; i++){
//			System.out.println(personList.get(i));
//		}
		return personVoList ; 
	}
	public List<PersonVo> findPageByCriteria(int pageNo , int pageSize , Map<String,Object> params){
		List<PersonVo> personVoList = new ArrayList<PersonVo>();
		
		List<Person> personList = personDao.queryPageByCriteria(pageNo,pageSize , params);
		for(Person person : personList){
			PersonVo personVo = new PersonVo();
			try {
				BeanUtils.copyProperties(personVo, person);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			personVoList.add(personVo);
		}
		return personVoList ; 
	}
	
	public List<PersonVo> queryByParamsSql(Map<String,Object> params , String sql){
		List<PersonVo> personVoList = personDao.queryByParamsSql(params, sql);
		
		return personVoList ; 
	}
	public String queryDocumentByParamsSql(Map<String,Object> params , String sql){
		String xml = personDao.queryDocumentByParamsSql(params, sql);
		return xml ; 
	}
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

}
