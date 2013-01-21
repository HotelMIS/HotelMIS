package com.fly.hotelmis.security.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.fly.hotelmis.login.entity.Users;
import com.fly.hotelmis.security.dao.OriginalDao;
import com.fly.hotelmis.security.dao.UsersDao;
import com.fly.hotelmis.security.entity.UsersRoles;

@Repository("usersDao")
public class UsersDaoImpl implements UsersDao {
	@Resource
	private SessionFactory sessionFactory;

	private OriginalDao dao = new OriginalDao();

	public UsersDaoImpl() {
		
	}

	 public List<UsersRoles> getUserSRolesByUser(String username){
		 // 查询
		 String hql =
		 "select ur from UsersRoles ur ,Users users where ur.uid = users.id and users.username = :username";
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("username", username);
		 Query query = sessionFactory.getCurrentSession().createQuery(hql);
		 for (String param : params.keySet()) {
			 query.setParameter(param, params.get(param));
		 }
		 List <UsersRoles>list = query.list();
		 if(list != null && list.size() > 0){
			 for (int i = 0; i < list.size(); i++){
				 UsersRoles myur = list.get(i);
			 }
		 }
		 return list;
	 }

	public Users getUsersByUsernameNew(String username) {
		String sql = "select * from users where username = '" + username + "'";
		Connection conn = dao.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Users users = new Users();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			rs.next();

			users.setId(rs.getString("id"));
			users.setUsername(rs.getString("username"));
			users.setPassword(rs.getString("password"));
			users.setEnable(rs.getInt("enable"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public List<UsersRoles> getUserSRolesByUserNew(String username) {
		// select ur from UsersRoles ur ,Users users where ur.uid = users.id and
		// users.username
		String sql = "select ur.rid as rid from users_roles ur,users u  where ur.uid = u.id and username = '"
				+ username + "'";
		Connection conn = dao.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<UsersRoles> list = new ArrayList<UsersRoles>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			UsersRoles ur = null;
			while (rs.next()) {
				ur = new UsersRoles();
				ur.setRid(rs.getString("rid"));
				list.add(ur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public Users getUsersByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	// public Users getUsersByUsername(String username)
	// {
	// String hql =
	// "select users from Users users where users.username = :username";
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("username", username);
	//
	// Query query = sessionFactory.getCurrentSession().createQuery(hql);
	// for (String param : params.keySet()) {
	// query.setParameter(param, params.get(param));
	// }
	//
	// List list = query.list();
	// if (list != null && list.size() > 0)
	// {
	// System.out.println("【getUsersByUsername用户的信息不为空。】");
	// return (Users) list.get(0);
	// }
	// else
	// {
	// System.out.println("【getUsersByUsername用户的信息为空。】");
	// return null;
	// }
	// }
}
