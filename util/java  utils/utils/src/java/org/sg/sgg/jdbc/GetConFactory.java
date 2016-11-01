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
	 * ���ݿ�����ʵ��
	 */
	public static Connection  conn =  null; 
	/**
	 * ���ݿ�ĸ�����
	 */
	GetClientHandle getClientHandle = new GetClientHandle();
	/**
	 *  �������ݿ�����
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
			logger.error("��ȡ���ݿ����ӳ���:" + e.getMessage());
		}
		return connection;
	}

	/**
	 * ����������Ϊ��,������Դ�ر�ʱ,�ڴ�������ʵ��
	 */
	public static Connection getConInstance(){
		try {
			if (conn == null || conn.isClosed() == true) {
				conn = new GetConFactory().getConnection();
			}
		} catch (SQLException e) {
			logger.error("��ȡ���ݿ����ӳ���:" + e.getMessage());
		}
		return conn;
	}
}
