package org.sg.sgg.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class GetClientHandle {
	
	private static final Logger logger = Logger.getLogger(GetClientHandle.class);
	
	/**
	 * 关闭数据库连接
	 * @param pst
	 * @param rs
	 */
	public static void getCloseInf(PreparedStatement pst,ResultSet rs){
		try {
			if (pst != null) {
				pst.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("关闭数据库出现异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 根据参数   添加  删除 修改
	 * @param con
	 * @param pst
	 * @param sql
	 * @param params
	 */
	public void getClientModifyRemoveBean(Connection con,
			PreparedStatement pst, String sql, String[] params) {
		try {
			pst = con.prepareStatement(sql);
			if (params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setString(i + 1, params[i]);
				}
			}
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getCloseInf(pst, null);
		}
	}

	
	/**
	 * 根据参数  查询总记录数
	 * @param con
	 * @param pst
	 * @param res
	 * @param sql
	 * @param params
	 * @return
	 */
	public String getClientString(Connection con, PreparedStatement pst,
			ResultSet res, String sql, String[] params) {
		String text = null;
		try {
			pst = con.prepareStatement(sql);
			if (params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setString(i + 1, params[i]);
				}
			}
			res = pst.executeQuery();
			if (res.next()) {
				text = res.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getCloseInf(pst, res);
		}
		return text;
	}
	/**
	 * 根据传入参数  查询单个对象
	 * @param con
	 * @param pst
	 * @param res
	 * @param sql
	 * @param cla
	 * @param params
	 * @return
	 */
	public Object getClientBean(Connection con, PreparedStatement pst,
			ResultSet res, String sql, Class<?> cla, String[] params) {
		Object bean = null;
		try {
			pst = con.prepareStatement(sql);
			if (params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setString(i + 1, params[i]);
				}
			}
			res = pst.executeQuery();
			if (res.next()) {
				bean = cla.newInstance();
				getClientBean(bean, res.getMetaData(), res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getCloseInf(pst, res);
		}
		return bean;
	}
	/**
	 * 根据参数   获取List对象
	 * @param con
	 * @param pst
	 * @param res
	 * @param sql
	 * @param cla
	 * @param params
	 * @return
	 */
	public List<?> getClientListBean(Connection con, PreparedStatement pst,
			ResultSet res, String sql, Class<?> cla, String[] params) {
		List list = new ArrayList();
		try {
			Object bean = null;
			pst = con.prepareStatement(sql);
			if (params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					pst.setString(i + 1, params[i]);
				}
			}
			res = pst.executeQuery();
			while (res.next()) {
				bean = cla.newInstance();
				getClientBean(bean, res.getMetaData(), res);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getCloseInf(pst, res);
		}
		return list;
	}
	/**
	 * 
	 * @param bean
	 * @param mt
	 * @param res
	 */
	public static void getClientBean(Object bean, ResultSetMetaData mt,
			ResultSet res) {
		try {
			for (int i = 1; i < mt.getColumnCount() + 1; i++)
				BeanUtils.copyProperty(bean, getUpperCase(mt.getColumnName(i)),
						res.getString(mt.getColumnName(i)));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static String getUpperCase(String table_filed) {
		String name = table_filed.toLowerCase();
		StringBuffer bf = new StringBuffer();
		for (int j = 0; j < name.length(); j++) {
			char c = name.charAt(j);
			if (c == '_') {
				j++;
				bf.append(name.substring(j, j + 1).toUpperCase());
			} else if (j == 0) {
				bf.append(String.valueOf(c).toLowerCase());
			} else {
				bf.append(c);
			}
		}

		return bf.toString();
	}
}
