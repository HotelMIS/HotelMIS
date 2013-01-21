package com.fly.hotelmis.test.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.apache.struts2.components.Param;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.fly.common.action.BaseAction;
import com.fly.hotelmis.test.service.PersonService;
import com.fly.hotelmis.test.vo.PersonVo;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : PersonAction.java    
 Package      : com.fly.hotelmis.test.action                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2012-12-11 
 */
@ParentPackage("json-default")
@Namespace("/")
@Controller("personAction")
@Scope("prototype")
@Action(value="/personAction", results={@Result(name="succ", location="/personAction!queryByParams1.action" ,type="redirect"),
		@Result(name="success", type="json" ,params={"root","personVoList"})}
)
public class PersonAction extends BaseAction {
	@Resource private PersonService personService;
	List<PersonVo> personVoList ;
	public List<PersonVo> getPersonVoList() {
		return personVoList;
	}

	public void setPersonVoList(List<PersonVo> personVoList) {
		this.personVoList = personVoList;
	}

	Logger log = Logger.getLogger(this.getClass());
	
	public String queryByParams(){
		log.info("log测试的例子");
		Map<String ,Object> params = new HashMap<String , Object>();
		params.put("name", "lzc");
		params.put("age", "30");
		String hql = "select person from Person person where person.name =:name and person.age=:age";
		personVoList = personService.queryByParams(params, hql);
		log.debug("打印json内容："+personVoList);
		return "success";
	}
	
	public void queryByParams1(){
		Map<String ,Object> params = new HashMap<String , Object>();
		params.put("name", "lzc");
		params.put("age", "30");
		String hql = "select person from Person person where person.name =:name and person.age=:age";
		personVoList = personService.queryByParams(params, hql);
		
	}

}
