package com.rays.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {

	// step = 2: make default constructor private
	private JDBCDataSource() {

	}

	// step = 3: make self type of static variable
	private static JDBCDataSource datasource = null;

	private ComboPooledDataSource cpds = null;

	private static ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.system");

	// step = 4: make getInstance method
	public static JDBCDataSource getInstance() {

		if (datasource == null) {

			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();

			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
				datasource.cpds.setJdbcUrl(rb.getString("url"));
				datasource.cpds.setUser(rb.getString("username"));
				datasource.cpds.setPassword(rb.getString("password"));
				datasource.cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
				datasource.cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
				datasource.cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));

			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

		}

		return datasource;

	}

	public static Connection getConnection() throws Exception {

		return getInstance().cpds.getConnection();

	}

	public static void closeConnection(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	public static void trnRollback(Connection connection) throws Exception {
		if (connection != null) {

			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new Exception(ex.toString());
			}
		}
	}

}
