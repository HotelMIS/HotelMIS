package com.fly.hotelmis.test.junit;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fly.hotelmis.test.service.PersonService;
import com.fly.hotelmis.test.vo.PersonVo;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : Junit.java    
 Package      : com.fly.project.test.junit                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-20 
 */
public class Junit {
	/**
	 * @throws java.lang.Exception
	 * @author HP
	 * @since  2011-5-20 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@Test
	public void save(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonService personService = (PersonService)ctx.getBean("personService");	
		List<PersonVo> personVoList = new ArrayList<PersonVo>();

		//1
//		for(int i = 0 ; i < 50 ; i++){
//			PersonVo person = new PersonVo();
//			person.setName("lzc"+i);
//			person.setAge(30+i+"");
//			personVoList.add(person);
//		}
//		personService.saveAll(personVoList);
		//personService.save(person);
		//2.
//		Map<String ,Object> params = new HashMap<String , Object>();
//		params.put("name", "lzc");
//		params.put("age", "30");
//		personVoList = personService.queryByParams(params, "select person from Person person where person.name =:name and person.age=:age");
		
		//3.
		Map<String ,Object> params = new HashMap<String , Object>();
		//params.put("name", "lzc");
		//params.put("age", "30");
		String name = "lzc";
		String hql = "select name from Person person where person.name like '%"+name+"%' " ;
		String hql_count = "select count(person.id) from Person person where person.name like '%"+name+"%'" ;
		personVoList = personService.queryPageByQuery(2 , 10 , params , hql , hql_count );
		
		//4
//		Map<String ,Object> params = new HashMap<String , Object>();
//		params.put("name", "lzc");
//		//params.put("age", "30");
//		String name = "lzc";
//		String hql = "select name from Person person where person.name like '%"+name+"%' " ;
//		String hql_count = "select count(person.id) from Person person where person.name like '%"+name+"%'" ;
//		personVoList = personService.findPageByCriteria(2 , 10 , params);
		
		//5
//		Map<String ,Object> params = new HashMap<String , Object>();
//		params.put("name", "lzc");
//		params.put("age", "30");
//		personVoList = personService.queryByParamsSql(params, "select id,name,age from person where person.name =:name and person.age=:age");
		
		//6
//		Map<String ,Object> params = new HashMap<String , Object>();
//		//params.put("name", "lzc");
//		//params.put("age", "30");
//		String name = "lzc";
//		String hql = "select id , name from person where name like '%"+name+"%' " ;
//		String hql_count = "select count(id) from person where name like '%"+name+"%'" ;
//		personVoList = personService.queryPageByQuery(2 , 10 , params , hql , hql_count );
//		
//		for(PersonVo personVo : personVoList){
//			System.out.println(personVo.getName());
//		}
		
		//7
//		Map<String ,Object> params = new HashMap<String , Object>();
//		params.put("name", "lzc");
//		params.put("age", "30");
//		List<PersonVo> xml = personService.queryByParams(params, "select person.id,person.name,person.age from Person person where person.name =:name and person.age=:age");
//		
	}

	

}
