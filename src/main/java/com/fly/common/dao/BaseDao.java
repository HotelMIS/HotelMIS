package com.fly.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Criterion;

import com.fly.common.page.Page;

/**
 * ---------------------------------------------------------------------------------
 Confidential and Proprietary                                                                
 Copyright 2010 By                                                                                     
 SGAI & Hewlett-Packard Development Company, L.P. 	              
 All Rights Reserved                                                                                  

 Project Name : SGAI  MES                                                                                                                                       
 Class Name   : BaseDao.java    
 Package      : com.fly.hibernate.dao.impl                                                                   
 @version     $Id$                                                          
 @author 刘志才
 @since  2011-5-21 
 */
public interface BaseDao<T> {

	/**
	 * 得到当前Session
	 * @return
	 * @author HP
	 * @since  2011-5-21
	 */
	public abstract Session getSession();
	/**
	 * 打开一个无状态的连接getStatelessSession
	 * @return
	 * @author HP
	 * @since  2011-5-21
	 */
	public StatelessSession getStatelessSession();
	
	/**
	 * 分页查询
	 * @param params 放置参数的Map对象，key=参数名，value=参数值
	 * @param pageNo 查询页码 
	 * @param limit 每页限定记录数
	 * @return 组装好的Page对象 @see com.hp.common.pagination.Page
	 */
	//public Page<T> findByPage(Map<String, Object> params, int pageNo, int limit);

	/**
	 * 创建实体对象
	 * @param entity
	 * @return 
	 */
	public void insert(T entity);

	/**
	 * 批量创建实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	public void insertAll(final Collection<T> collection);

	/**
	 * 更新实体对象
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 执行hql语句进行更新
	 * @param entity
	 */
	public void updateByHql(String hql) ;
	/**
	 * 执行sql语句进行更新
	 * @param entity
	 */
	public void updateBySql(String sql) ;

	/**
	 * 批量更新实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	public void updateAll(final Collection<T> collection);

	/**
	 * 查询全部记录(HQL)
	 * @return 记录集合
	 */
	public List<T> findAll(String Hql);
	/**
	 * 查询全部记录(SQL)
	 * @return 记录集合
	 */
	@SuppressWarnings("rawtypes")
	public List findAllSql(String sql);
	
	/**
	 * 查询全部记录(HQL)
	 * @param hql
	 * @return 记录集合
	 */
	public Object findUniqueResult(String hql);
	
	/**
	 * 查询全部记录(HQL)
	 * @param sql
	 * @return 记录集合
	 */
	public Object findUniqueResultSql(String sql);
	/**
	 * 查询全部记录(命名查询方式)
	 * @param hql
	 * @return 记录集合
	 */
	public Object findUniqueResultByHqlId(String name);

	/**
	 * 删除实体对象
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 按照Map参数进行条件删除(HQL)
	 * @param params
	 */
	public void deleteByParams(Map<String,Object> params,String hql);
	/**
	 * 按照Map参数进行条件删除(SQL)
	 * @param params
	 */
	public void deleteByParamsSql(Map<String, Object> params , String sql);
	
	/**
	 * 根据主键删除实体对象(HQL)
	 * @param entity
	 */
	public void deleteByPrimaryKey(String id , String hql);
	/**
	 * 根据主键删除实体对象(SQL)
	 * @param entity
	 */
	public void deleteByPrimaryKeySql(String id,String sql);

	/**
	 * 批量删除实体对象，采用Btach批量处理机制
	 * @param collection
	 */
	public void deleteAll(final Collection<T> collection);
	
	/**
	 * 根据主键批量删除实体对象，采用Btach批量处理机制(HQL)
	 * @param collection
	 */
	public void deleteAllByPrimaryKey(final Collection<String> ids,String hql);
	/**
	 * 根据主键批量删除实体对象，采用Btach批量处理机制（SQL）
	 * @param collection
	 */
	public void deleteAllByPrimaryKeySql(Collection<String> ids , String sql);

	/**
	 * 根据主键返回唯一对象
	 * @param sid 序列主键
	 * @return 实体对象
	 */
	public T findByPrimaryKey(String id,String hql);
	/**
	 * 根据实体中的ID返回唯一对象
	 * @param sid 序列主键
	 * @return 实体对象
	 */
	public T find(T entity);


	/**
	 * 根据参数返回一个实体对象，一般用于查询具有唯一约束条件的记录
	 * @param params 参数Map对象
	 * @return 实体对象 
	 */
	public T findUniqueByParams(Map<String, Object> params , String hql);
	
	/**
	 * 根据参数返回实体对象集合(SQL)
	 * 
	 * @param params
	 *            参数Map对象
	 * @return 实体对象链表
	 */
	@SuppressWarnings("rawtypes")
	public List<T> findByParamsSql(Map<String, Object> params, String sql,Class clazz);
	
	/**
	 * 根据参数返回实体对象集合(SQL)
	 * @param params 参数Map对象
	 * @return 实体对象链表
	 */
	public List<T> findByParamsSql(Map<String, Object> params , String sql);
	
	/**
	 * 根据参数返回实体对象集合(HQL)
	 * @param params 参数Map对象
	 * @return 实体对象链表
	 */
	public List<T> findByParams(Map<String, Object> params , String hql);
	
	/**
	 * 命名查询方式
	 * @param name为配置文件中的id
	 * @param params 参数Map对象
	 * @return
	 */
	public List<T> findByHqlId(Map<String, Object> params , String name);
	
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
	@SuppressWarnings("rawtypes")
	public List<T> findBySqlId(Map<String, Object> params, String name , Class clazz) ;
	
	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo  当前页数
	 * @param pageSize 每页显示的条数
	 * @param params 查询条件map
	 * @param hql语句
	 * @param 用户计算总记录数的hql
	 * @return 查询结果Page
	 */

	public Page<T> findPageByQuery(int pageNo, int pageSize,Map<String, Object> params, String hql, String hql_count);
	
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
	public Page<T> findPageByNamedQuery(int pageNo, int pageSize,Map<String, Object> params, String hql_name, String hql_count_name);
	
	/**
	 * @function 分页显示符合所有的记录数，将查询结果封装为Page
	 * @param pageNo  当前页数
	 * @param pageSize 每页显示的条数
	 * @param params 查询条件map
	 * @param sql语句
	 * @param 用户计算总记录数的sql
	 * @param 定义返回类型
	 * @return 查询结果Page
	 */

	@SuppressWarnings("rawtypes")
	public Page<T> findPageByQuerySql(int pageNo, int pageSize,Map<String, Object> params, String sql, String sql_count , Class clazz);

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
	public Page<T> findPageByQuerySql(int pageNo, int pageSize,Map<String, Object> params, String sql, String sql_count);
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
	@SuppressWarnings( "rawtypes" )
	public Page<T> findPageByNamedQuerySql(int pageNo, int pageSize,Map<String, Object> params, String sql_name, String sql_count_name , Class clazz);
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
	@SuppressWarnings("rawtypes")
	public Page<T> findPageByCriteria(int pageNo, int pageSize,Map<String, Object> params, Class clazz);
	
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

	@SuppressWarnings("rawtypes")
	public Page<T> findPageByCriteria(int pageNo, int pageSize, Class clazz , Criterion... criterions);
	
	/**
	 * 将一级缓存中的数据同步到数据库中 
	 * @author HP
	 * @since  2011-5-21
	 */
	public void flush();
	
	/**
	 * 清空一级缓存中的数据
	 * @author HP
	 * @since  2011-5-21
	 */
	public void clear();
	


}