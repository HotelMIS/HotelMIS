package com.fly.hotelmis.security.dao;

import java.util.List;

import org.hibernate.SessionFactory;


public interface ResourcesDao {
	public void setSessionFactory(SessionFactory sessionFactory);
	public List getAllRolesResources();
}
