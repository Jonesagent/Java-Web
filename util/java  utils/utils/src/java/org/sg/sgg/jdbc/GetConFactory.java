package org.sg.sgg.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.sg.sgg.property.propertyUtils;

public class GetConFactory {
	
	private static final Logger logger = Logger.getLogger(GetConFactory.class);
	
	public static PreparedStatement pst = null;
	public static Statement st = null;
	public static ResultSet rs = null;
	
	/**
	 * 数据库连接实例
	 */
	public static Connection  conn =  null; 
	/**
	 * 数据库的辅助类
	 */
	GetClientHandle getClientHandle = new GetClientHandle();
	/**
	 *  返回数据库连接
	 * @return
	 */
	public Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName(propertyUtils.getConfigParam("jdbc.driverClassName"));
			connection = DriverManager.getConnection(
					propertyUtils.getConfigParam("jdbc.url"),
					propertyUtils.getConfigParam("jdbc.username"),
					propertyUtils.getConfigParam("jdbc.password")
			);
		} catch (Exception e) {
			logger.error("获取数据库连接出错:" + e.getMessage());
		}
		return connection;
	}

	/**
	 * 当数据连接为空,或数据源关闭时,在创建连接实例
	 */
	public static Connection getConInstance(){
		try {
			if (conn == null || conn.isClosed() == true) {
				conn = new GetConFactory().getConnection();
			}
		} catch (SQLException e) {
			logger.error("获取数据库连接出错:" + e.getMessage());
		}
		return conn;
	}
}
