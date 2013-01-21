package com.fly.hotelmis.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fly.hotelmis.login.dao.LoginDao;
import com.fly.hotelmis.login.entity.Users;
import com.fly.hotelmis.login.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService
{

	//private BeanCopier copy = BeanCopier.create(Users.class, target, useConverter)
	@Resource
	private LoginDao loginDao;

	@Override
	public boolean checkUser(String username, String password) {
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);
		return loginDao.checkUser(user);
	}

}
