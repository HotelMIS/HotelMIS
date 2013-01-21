package com.fly.hotelmis.login.entity;

import com.fly.common.entity.BaseEntity;

public class Users extends BaseEntity
{
	private String id;

	private String username;

	private String password;

	private int enable;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public int getEnable()
	{
		return enable;
	}

	public void setEnable(int enable)
	{
		this.enable = enable;
	}

}
