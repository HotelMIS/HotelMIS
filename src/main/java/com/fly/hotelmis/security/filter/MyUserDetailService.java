package com.fly.hotelmis.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fly.hotelmis.login.entity.Users;
import com.fly.hotelmis.security.dao.OriginalDao;
import com.fly.hotelmis.security.dao.UsersDao;
import com.fly.hotelmis.security.dao.impl.UsersDaoImpl;
import com.fly.hotelmis.security.entity.UsersRoles;

/**
 * 根据用户登录标识 获取和用户关联的所有的信息
 */
public class MyUserDetailService implements UserDetailsService {

	private UsersDao usersDao ;

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		// security 的内置的用户
		User user = null;
		// 根据用户名获取用户信息
		Users myUser = getUsersByUsername(username);
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		if (myUser != null) {
			// 0 代表管理员，1代表普通用户
			int enable = myUser.getEnable();
			// 管理员
			if (enable == 0) {
				GrantedAuthorityImpl adminRole = new GrantedAuthorityImpl(
						"ROLE_ADMIN");
				auths.add(adminRole);
			} else {
				List<UsersRoles> urlist = getRolesByuser(username);
				if (urlist != null && urlist.size() > 0) {
					for (int i = 0; i < urlist.size(); i++) {
						UsersRoles ur = urlist.get(i);
						// 获取
						GrantedAuthorityImpl roleid = new GrantedAuthorityImpl(
								ur.getRid());
						auths.add(roleid);
					}
				}
			}
			// 根据数据库加载的用户信息
			user = new User(username, myUser.getPassword(), true, true, true,
					true, auths);
		} else {
			System.out.println("【loadUserByUsername根据用户名获取用户失败！】");
		}
		return user;
	}

	/**
	 * 根据用户名获取用户权限关系信息
	 * 
	 * @param username
	 * @return
	 */
	public List<UsersRoles> getRolesByuser(String username) {
		return usersDao.getUserSRolesByUserNew(username);
	}

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	public Users getUsersByUsername(String username) {
		return usersDao.getUsersByUsernameNew(username);
	}

}