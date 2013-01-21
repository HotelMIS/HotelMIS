package com.fly.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.fly.common.dao.BaseDao;
import com.fly.common.entity.BaseEntity;
import com.fly.common.page.Page;

/**
 * ----------------------------------------------------------------------------
 * ----- Confidential and Proprietary Copyright 2010 By SGAI & Hewlett-Packard
 * Development Company, L.P. All Rights Reserved
 * 
 * Project Name : SGAI MES Class Name : BaseDao.java Package :
 * com.fly.hibernate.dao
 * 
 * @version V1.0
 * @author 刘志才
 * @since 2011-5-21
 */
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

	// 定义批量保存的个数，该处和<property name="jdbc.batch_size">20</property>保持一致
	private static final int BATCH_SIZE = 20;
	// model's Class
	protected Class<T> entityClass;

	// model's ClassName
	protected String entityClassName;

	// 通过注解方式得到sessionFactory
	@Resource
	private SessionFactory sessionFactory;
	private Session session;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		try {
			Object genericClz = getClass().getGenericSuperclass();
			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<T>) ((ParameterizedType) genericClz)
						.getActualTypeArguments()[0];
				entityClassName = entityClass.getName();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 得到当前Session
	 * 
	 * @return
	 * @author HP
	 * @since 2011-5-21
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 得到当前Session(未完成)
	 * 
	 * @return
	 * @author HP
	 * @since 2011-5-21
	 */
	public Session getDom4jSession() {
		return null;
	}
	
	/**
	 * 打开一个无状态的连接getStatelessSession
	 * 
	 * @return
	 * @author HP
	 * @since 2011-5-21
	 */
	public StatelessSession getStatelessSession() {
		return sessionFactory.openStatelessSession();
	}

	/**
	 * 创建实体对象(改方法调用persist方法，保证在transaction内部执行， 在transaction外部并不会触发Insert
	 * sql，并"不保证"标识符被立刻填入到持久化实例中， 标识符的填入可能被推迟到flush的时间)
	 * 
	 * @param entity
	 * @return
	 */
	public void insert(T entity) {
		this.getCurrentSession().persist(entity);
	}

	/**
	 * 批量创建实体对象，采用Btach批量处理机制
	 * 
	 * @param collection
	 */
	public void insertAll(Collection<T> collection) {
		int i = 0;
		if (collection == null || collection.isEmpty()) {
			return;
		} else {
			for (T entity : collection) {
				i++;
				this.getCurrentSession().save(entity);
				if (i == BATCH_SIZE) {
					i = 0;
					this.getCurrentSession().flush();
					this.getCurrentSession().clear();
				}
			}
		}

	}

	/**
	 * 更新实体对象(merge方法调用后对象仍为脱管状态)
	 * 
	 * @param entity
	 * 			
	 */
	public void update(T entity) {
		this.getCurrentSession().merge(entity.getId(), entity);
	}

	/**
	 * 执行hql语句进行更新
	 * 
	 * @param entity
	 */
	public void updateByHql(String hql) {
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 执行sql语句进行更新
	 * 
	 * @param entity
	 */
	public void updateBySql(String sql) {
		this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 执行sql语句进行更新(命名查询方式)
	 * 
	 * @param entity
	 */
	public void update(String name) {
		this.getCurrentSession().getNamedQuery(name).executeUpdate();
	}

	/**
	 * 批量更新实体对象，采用Btach批量处理机制(此处merge方法是否有一级缓存？)
	 * 
	 * @param collection
	 */
	public void updateAll(Collection<T> collection) {
		int i = 0;
		if (collection == null || collection.isEmpty()) {
			return;
		} else {
			for (T entity : collection) {
				i++;
				this.getCurrentSession().merge(entity.getId(), entity);
				if (i == BATCH_SIZE) {
					i = 0;
					this.getCurrentSession().flush();
					this.getCurrentSession().clear();
				}
			}
		}

	}

	/**
	 * 查询全部记录(HQL)
	 * 
	 * @param hql
	 * @return 记录集合
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return (List<T>) query.list();

	}

	/**
	 * 查询全部记录(SQL)
	 * 
	 * @param sql
	 * @return 记录集合
	 */
	@SuppressWarnings({ "rawtypes" })
	public List findAllSql(String sql) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		return query.list();

	}

	/**
	 * 查询全部记录(HQL)
	 * 
	 * @param hql
	 * @return 记录集合
	 */
	public Object findUniqueResult(String hql) {
		Query query = this.getCurrentSession().createSQLQuery(hql);
		return query.uniqueResult();

	}

	/**
	 * 查询全部记录(命名查询方式)
	 * 
	 * @param hql
	 * @return 记录集合
	 */
	public Object findUniqueResultByHqlId(String name) {
		Query query = this.getCurrentSession().getNamedQuery(name);
		return query.uniqueResult();

	}

	/**
	 * 查询全部记录(HQL)
	 * 
	 * @param sql
	 * @return 记录集合
	 */
	public Object findUniqueResultSql(String sql) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		return query.uniqueResult();

	}

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		this.getCurrentSession().delete(entity.getId(), entity);
	}

	/**
	 * 按照Map参数进行条件删除(SQL)
	 * @param params 
	 * @param hql
	 */
	public void deleteByParams(Map<String, Object> params, String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		query.executeUpdate();
	}

	/**
	 * 按照Map参数进行条件删除(SQL)
	 * @param params
	 * @param sql
	 */
	public void deleteByParamsSql(Map<String, Object> params, String sql) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		query.executeUpdate();
	}

	/**
	 * 根据主键删除实体对象(HQL)
	 * 
	 * @param entity
	 */
	public void deleteByPrimaryKey(String id, String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	/**
	 * 根据主键删除实体对象(SQL)
	 * 
	 * @param entity
	 */
	public void deleteByPrimaryKeySql(String id, String sql) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	/**
	 * 批量删除实体对象，采用Btach批量处理机制
	 * 
	 * @param collection
	 */
	public void deleteAll(Collection<T> collection) {
		int i = 0;
		if (collection == null || collection.isEmpty()) {
			return;
		} else {
			for (T entity : collection) {
				i++;
				this.getCurrentSession().delete(entity.getId(), entity);
				if (i == BATCH_SIZE) {
					i = 0;
					this.getCurrentSession().flush();
					this.getCurrentSession().clear();
				}
			}
		}
	}

	/**
	 * 根据主键批量删除实体对象，采用Btach批量处理机制（SQL）
	 * 
	 * @param collection
	 */
	public void deleteAllByPrimaryKey(Collection<String> ids, String hql) {
		if (ids == null || ids.isEmpty()) {
			return;
		} else {
			for (String id : ids) {
				Query query = this.getCurrentSession().createQuery(hql);
				query.setParameter("id", id);
				query.executeUpdate();
			}
		}
	}

	/**
	 * 根据主键批量删除实体对象，采用Btach批量处理机制（SQL）
	 * 
	 * @param collection
	 */
	public void deleteAllByPrimaryKeySql(Collection<String> ids, String sql) {
		if (ids == null || ids.isEmpty()) {
			return;
		} else {
			for (String id : ids) {
				Query query = this.getCurrentSession().createSQLQuery(sql);
				query.setParameter("id", id);
				query.executeUpdate();
			}
		}
	}

	/**
	 * 根据主键返回唯一对象(HQL)
	 * 
	 * @param sid
	 *            序列主键
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(String id, String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		return (T) query.uniqueResult();
	}

	/**
	 * 根据实体中的ID返回唯一对象
	 * 
	 * @param sid
	 *            序列主键
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public T find(T entity) {
		return (T) this.getCurrentSession().get(entity.getClass(),
				entity.getId());
	}

	/**
	 * 根据参数返回一个实体对象(HQL)
	 * 
	 * @param params
	 *            参数Map对象
	 * @return 实体对象
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueByParams(Map<String, Object> params, String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (T) query.uniqueResult();
	}

	/**
	 * 根据参数返回实体对象集合(SQL)
	 * 
	 * @param params
	 *            参数Map对象
	 * @param 定义返回类型
	 * @return 实体对象链表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByParamsSql(Map<String, Object> params, String sql,
			Class clazz) {
		Query query = this.getCurrentSession().createSQLQuery(sql)
				.setResultTransformer(Transformers.aliasToBean(clazz));
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (List<T>) query.list();
	}

	/**
	 * 根据参数返回实体对象集合(SQL)
	 * 
	 * @param params
	 *            参数Map对象
	 * @return 实体对象链表
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByParamsSql(Map<String, Object> params, String sql) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query.list();
	}
	/**
	 * 根据参数返回实体对象集合(SQL)未完成
	 * 
	 * @param params
	 *            参数Map对象
	 * @param 定义返回类型
	 * @return Document对象
	 */
	@SuppressWarnings({ "rawtypes",  "null" })
	public String findDocumentByParamsSql(Map<String, Object> params, String sql, Class clazz) {
		Document doc = null;
		Query query = this.getDom4jSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz));
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		List results = query.list();
		for ( int i=0; i<results.size(); i++ ) {
			Element entity = (Element) results.get(i);
			//System.out.println("*************"+entity.elementText("name"));
			doc.add(entity);
		}

		return doc.asXML();
	}
	
	/**
	 * 根据参数返回实体对象集合(HQL) 未完成
	 * 
	 * @param params
	 *            参数Map对象
	 * @param 定义返回类型
	 * @return Document对象
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public String findDocumentByParams(Map<String, Object> params, String hql) {
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("persons");

		Query query = this.getDom4jSession().createQuery(hql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		List results = query.list();
		for ( int i=0; i<results.size(); i++ ) {
			System.out.println("*************"+results.get(i).toString());
			Element entity = (Element) results.get(i);
			//System.out.println("*************"+entity.elementText("name"));
			document.add(entity);
		}

		return document.asXML();
	}
	/**
	 * 根据参数返回实体对象集合(HQL)
	 * 
	 * @param params
	 *            参数Map对象
	 * @return 实体对象链表
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByParams(Map<String, Object> params, String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (List<T>) query.list();
	}

	/**
	 * 命名查询方式(Hql)
	 * 
	 * @param name为配置文件中的id
	 * @param params
	 *            参数Map对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByHqlId(Map<String, Object> params, String name) {
		Query query = this.getCurrentSession().getNamedQuery(name);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (List<T>) query.list();
	}
	
	/**
	 * 命名查询方式(Sql)
	 * 
	 * @param name为配置文件中的id
	 * @param params
	 *            参数Map对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBySqlId(Map<String, Object> params, String name) {
		Query query = this.getCurrentSession().getNamedQuery(name);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (List<T>) query.list();
	}
	
	/**
	 * 命名查询方式(Sql)
	 * 
	 * @param name为配置文件中的id
	 * @param params
	 *            参数Map对象
	 * @param params
	 *            参数clazz对象
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findBySqlId(Map<String, Object> params, String name , Class clazz) {
		Query query = this.getCurrentSession().getNamedQuery(name).setResultTransformer(Transformers.aliasToBean(clazz));
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (List<T>) query.list();
	}

	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param params
	 *            查询条件map
	 * @param hql语句
	 * @param 用户计算总记录数的hql
	 * @return 查询结果Page
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPageByQuery(int pageNo, int pageSize,
			Map<String, Object> params, String hql, String hql_count) {
		Query query = this.getCurrentSession().createQuery(hql);
		Query query_count = this.getCurrentSession().createQuery(hql_count);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
			query_count.setParameter(param, params.get(param));
		}
		Page<T> page = new Page<T>();
		int initPageSize = page.getPageSize(pageSize);
		int initPageNo = page.getPageNo(pageNo);
		query.setFirstResult((initPageNo - 1) * initPageSize);
		query.setMaxResults(initPageSize);
		List<T> resultList = (List<T>) query.list();
		Long rowCount = (Long) query_count.uniqueResult();
		if (rowCount != null) {
			page.setTotalRows(rowCount.intValue());
		} else {
			page.setTotalRows(0);
		}
		page.setResultList(resultList);
		return page;

	}

	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page(命名查询方式)
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param params
	 *            查询条件map
	 * @param hql语句
	 * @param 用户计算总记录数的hql
	 * @return 查询结果Page
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPageByNamedQuery(int pageNo, int pageSize,
			Map<String, Object> params, String hql_name, String hql_count_name) {
		Query query = this.getCurrentSession().createQuery(hql_name);
		Query query_count = this.getCurrentSession().createQuery(hql_count_name);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
			query_count.setParameter(param, params.get(param));
		}
		Page<T> page = new Page<T>();
		int initPageSize = page.getPageSize(pageSize);
		int initPageNo = page.getPageNo(pageNo);
		query.setFirstResult((initPageNo - 1) * initPageSize);
		query.setMaxResults(initPageSize);
		List<T> resultList = (List<T>) query.list();
		Long rowCount = (Long) query_count.uniqueResult();
		if (rowCount != null) {
			page.setTotalRows(rowCount.intValue());
		} else {
			page.setTotalRows(0);
		}
		page.setResultList(resultList);
		return page;

	}
	
	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param params
	 *            查询条件map
	 * @param sql语句
	 * @param 用户计算总记录数的sql
	 * @param 定义返回类型
	 * @return 查询结果Page
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPageByQuerySql(int pageNo, int pageSize,
			Map<String, Object> params, String sql, String sql_count , Class clazz) {
		Query query = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(clazz));
		Query query_count = this.getCurrentSession().createSQLQuery(sql_count);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
			query_count.setParameter(param, params.get(param));
		}
		Page<T> page = new Page<T>();
		int initPageSize = page.getPageSize(pageSize);
		int initPageNo = page.getPageNo(pageNo);
		query.setFirstResult((initPageNo - 1) * initPageSize);
		query.setMaxResults(initPageSize);
		List<T> resultList = (List<T>) query.list();
		BigInteger rowCount = (BigInteger)query_count.uniqueResult();
		if (rowCount != null) {
			page.setTotalRows(rowCount.intValue());
		} else {
			page.setTotalRows(0);
		}
		page.setResultList(resultList);
		return page;

	}
	
	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page,命名查询方式
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param params
	 *            查询条件map
	 * @param sql语句
	 * @param 用户计算总记录数的sql
	 * @param 定义返回类型
	 * @return 查询结果Page
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPageByNamedQuerySql(int pageNo, int pageSize,
			Map<String, Object> params, String sql_name, String sql_count_name , Class clazz) {
		Query query = this.getCurrentSession().getNamedQuery(sql_name).setResultTransformer(Transformers.aliasToBean(clazz));
		Query query_count = this.getCurrentSession().getNamedQuery(sql_count_name);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
			query_count.setParameter(param, params.get(param));
		}
		Page<T> page = new Page<T>();
		int initPageSize = page.getPageSize(pageSize);
		int initPageNo = page.getPageNo(pageNo);
		query.setFirstResult((initPageNo - 1) * initPageSize);
		query.setMaxResults(initPageSize);
		List<T> resultList = (List<T>) query.list();
		BigInteger rowCount = (BigInteger)query_count.uniqueResult();
		if (rowCount != null) {
			page.setTotalRows(rowCount.intValue());
		} else {
			page.setTotalRows(0);
		}
		page.setResultList(resultList);
		return page;

	}
	
	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param params
	 *            查询条件map
	 * @param sql语句
	 * @param 用户计算总记录数的sql
	 * @return 查询结果Page
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findPageByQuerySql(int pageNo, int pageSize,
			Map<String, Object> params, String sql, String sql_count) {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		Query query_count = this.getCurrentSession().createSQLQuery(sql_count);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
			query_count.setParameter(param, params.get(param));
		}
		Page<T> page = new Page<T>();
		int initPageSize = page.getPageSize(pageSize);
		int initPageNo = page.getPageNo(pageNo);
		query.setFirstResult((initPageNo - 1) * initPageSize);
		query.setMaxResults(initPageSize);
		List<T> resultList = (List<T>) query.list();
		BigInteger rowCount = (BigInteger)query_count.uniqueResult();
		if (rowCount != null) {
			page.setTotalRows(rowCount.intValue());
		} else {
			page.setTotalRows(0);
		}
		page.setResultList(resultList);
		return page;

	}

	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param map
	 *            将查询条件封装为map
	 * @return 查询结果Page
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPageByCriteria(int pageNo, int pageSize,
			Map<String, Object> params, Class clazz) {
		Page<T> page = new Page<T>();
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(clazz);

			if (params != null) {
				for (String param : params.keySet()) {
					criteria.add(Restrictions.like(param, params.get(param)));
				}
			}

			// 获取根据条件分页查询的总行数
			Long rowCount = (Long) criteria.setProjection(
					Projections.rowCount()).uniqueResult();
			criteria.setProjection(null);

			int initPageSize = page.getPageSize(pageSize);
			int initPageNo = page.getPageNo(pageNo);
			criteria.setFirstResult((initPageNo - 1) * initPageSize);
			criteria.setMaxResults(initPageSize);
			List<T> resultList = (List<T>) criteria.list();
			if (rowCount != null) {
				page.setTotalRows(rowCount.intValue());
			} else {
				page.setTotalRows(0);
			}
			page.setResultList(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo
	 *            当前页数
	 * @param pageSize
	 *            每页显示的条数
	 * @param criterions
	 *            不定参数Criterion
	 * @return 查询结果Page
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPageByCriteria(int pageNo, int pageSize, Class clazz,
			Criterion... criterions) {
		Page<T> page = new Page<T>();
		try {
			Criteria criteria = this.getCurrentSession().createCriteria(clazz);

			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}

				}
			}

			// 获取根据条件分页查询的总行数
			Long rowCount = (Long) criteria.setProjection(
					Projections.rowCount()).uniqueResult();
			criteria.setProjection(null);

			int initPageSize = page.getPageSize(pageSize);
			int initPageNo = page.getPageNo(pageNo);
			criteria.setFirstResult((initPageNo - 1) * initPageSize);
			criteria.setMaxResults(initPageSize);
			List<T> resultList = (List<T>) criteria.list();
			if (rowCount != null) {
				page.setTotalRows(rowCount.intValue());
			} else {
				page.setTotalRows(0);
			}
			page.setResultList(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 将一级缓存中的数据同步到数据库中
	 * 
	 * @author HP
	 * @since 2011-5-21
	 */
	public void flush() {
		this.getCurrentSession().flush();
	}

	/**
	 * 清空一级缓存中的数据
	 * 
	 * @author HP
	 * @since 2011-5-21
	 */
	public void clear() {
		this.getCurrentSession().clear();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fly.hibernate.dao.impl.BaseDao#getSession()
	 */
	@Override
	public Session getSession() {
		return session;
	}

}
