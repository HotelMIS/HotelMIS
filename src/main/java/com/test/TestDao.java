package com.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.fly.hotelmis.security.entity.RoleidUrl;

public class TestDao
{
    private SessionFactory sessionFactory;
    public List<RoleidUrl> getAllRolesResources()
    {
	List list = new ArrayList();
	
	return list;
    }
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
}
