package com.fly.hotelmis.security.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.fly.hotelmis.security.dao.OriginalDao;
import com.fly.hotelmis.security.dao.ResourcesDao;
import com.fly.hotelmis.security.entity.RoleidUrl;

@Repository("resourcesDao")
public class ResourcesDaoImpl implements ResourcesDao  {
	//@Resource
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/**
	 * 获取所有的角色以及权限信息
	 */

	public List<RoleidUrl> getAllRolesResources() {
		 String sql =
			 "select rr.rid as roleid,r.url as url from roles_resources rr , resources r where rr.reid = r.id";
		 Query query = sessionFactory.openSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(RoleidUrl.class));;
		 List<RoleidUrl> list = (List<RoleidUrl>)query.list();
		 if (list != null && list.size() > 0){
			 for (int i = 0; i < list.size(); i++){
				 RoleidUrl ru = (RoleidUrl) list.get(i);
				 System.out.println(ru.getRoleid() + "    :    " + ru.getUrl());
			 }
		 }
		 return list;
	}
}
