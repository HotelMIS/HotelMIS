package com.fly.hotelmis.login.service;

import com.fly.hotelmis.login.entity.Users;
import com.fly.hotelmis.login.vo.UsersVo;



public interface LoginService
{
	public boolean checkUser(String username ,String password); 
}
