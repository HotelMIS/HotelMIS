package com.fly.hotelmis.security.dao;

import java.util.List;

import com.fly.hotelmis.login.entity.Users;
import com.fly.hotelmis.security.entity.UsersRoles;

public interface UsersDao {
	public List<UsersRoles> getUserSRolesByUser(String username);

	public Users getUsersByUsername(String username);

	public List<UsersRoles> getUserSRolesByUserNew(String username);

	public Users getUsersByUsernameNew(String username);
}
