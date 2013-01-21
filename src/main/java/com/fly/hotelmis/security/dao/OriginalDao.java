package com.fly.hotelmis.security.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class OriginalDao {
	public Connection getConnection() {
		// 查询所有的权限信息
		Properties props = new Properties();
		InputStream in = null;
		Connection conn = null;
		try {
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("jdbc.properties");
			props.load(in);
			String driver = props.getProperty("jdbc.driverClassName");
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
