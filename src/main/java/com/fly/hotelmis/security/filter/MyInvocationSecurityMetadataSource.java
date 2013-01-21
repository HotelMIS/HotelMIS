package com.fly.hotelmis.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Component;

import com.fly.hotelmis.security.dao.ResourcesDao;
import com.fly.hotelmis.security.dao.impl.ResourcesDaoImpl;
import com.fly.hotelmis.security.entity.RoleidUrl;
/**
 *  最核心的地方，就是提供某个资源对应的权限定义， 即getAttributes方法返回的结果。
 *  此类在初始化时，应该取到所有资源及其对应角色的定义。
 * @author yuhao
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	// 资源信息dao
	//@Resource
	//private ResourcesDao resourcesDao ; ;
	//由spring调用
	public MyInvocationSecurityMetadataSource(ResourcesDao resourcesDao,SessionFactory sessionFactory) {
		this.resourcesDao = resourcesDao;
		this.sessionFactory = sessionFactory;
		System.out.println("this.sessionFactory======================"+this.sessionFactory);
		loadResourceDefine();
	}

	private ResourcesDao resourcesDao;

	public ResourcesDao getResourcesDao() {
		return resourcesDao;
	}

	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}
	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//	public MyInvocationSecurityMetadataSource() {
//		loadResourceDefine();
//	}

	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<RoleidUrl> list = getAllRoleIDAndUrl();
		if (list != null && list.size() > 0) {
			for (RoleidUrl ur : list) {
				String url = ur.getUrl();
				String roleID = ur.getRoleid();
				String realUrl = "";
				// 对于jsp 只要求匹配最后一段
				if (url.contains("/")) {
					realUrl = url.substring(url.lastIndexOf("/"));
				} else {// 对于action 要求全匹配
					realUrl = url;
				}
				ConfigAttribute ca = new SecurityConfig(roleID);
				if (resourceMap.containsKey(realUrl)) {
					Collection<ConfigAttribute> value = resourceMap
							.get(realUrl);
					value.add(ca);
					resourceMap.put(realUrl, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(realUrl, atts);
				}
			}
		}
	}

	/**
	 * 获取所有的角色id和url信息
	 * 
	 * @return
	 */
	public List getAllRoleIDAndUrl() {
		resourcesDao.setSessionFactory(sessionFactory);
		List list = resourcesDao.getAllRolesResources();
		return list;
	}

	// 根据访问的url地址查找此地址是哪些角色能够访问的
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// object is a URL.
		String url = ((FilterInvocation) object).getRequestUrl();
		// 提出掉？后面的参数以免对校验产生影响
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(url, resURL)) {
				return resourceMap.get(resURL);
			}
		}
		System.out
				.println("MyInvocationSecurityMetadataSource ：getAttributes() 产生了无角色资源页面。"
						+ ",url====" + url);
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}