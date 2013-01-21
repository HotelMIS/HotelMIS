package com.fly.hotelmis.login.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fly.common.dao.impl.BaseDaoImpl;
import com.fly.hotelmis.login.dao.LoginDao;
import com.fly.hotelmis.login.entity.Users;
import com.fly.hotelmis.student.entity.Student;

@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl implements LoginDao
{

	@Override
	public boolean checkUser(Users users)
	{
		boolean flag = false;
		String username = users.getUsername();
		String password = users.getPassword();
		String hql = "select users from Users users where users.username = :username and users.password = :password";
		Map <String,String>params = new HashMap<String,String>();
		params.put("username", username);
		params.put("password", password);
		List<Users> list = this.findByParams(params, hql);
		if (list != null && list.size() > 0)
		{
			flag = true;
		}
		System.out.println("------------------------"+flag);
		return flag;
	}

}
