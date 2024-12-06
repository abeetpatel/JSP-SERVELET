package com.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.rays.bean.UserBean;
import com.rays.util.JDBCDataSource;

public class UserModel {

	ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.system");

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from user");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}

		return pk + 1;

	}

	public void add(UserBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLoginId());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getAddress());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully = " + i);

		} catch (Exception e) {
			conn.rollback();
			// TODO: handle exception
		}

	}

	public void delete(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from user where id = ?");

		pstmt.setInt(1, id);

		int i = pstmt.executeUpdate();

		System.out.println("data deleted successfully = " + i);

	}

	public void update(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update user set firstName = ?, lastName = ?, loginId = ?, password = ?, dob = ?, address = ? where id = ?");

		pstmt.setString(1, bean.getFirstName());
		pstmt.setString(2, bean.getLastName());
		pstmt.setString(3, bean.getLoginId());
		pstmt.setString(4, bean.getPassword());
		pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(6, bean.getAddress());
		pstmt.setInt(7, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("data Updated successfully = " + i);

	}

	public UserBean findById(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("select * from user where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));

		}

		return bean;

	}

	public UserBean authenticate(String loginId, String password) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("select * from user where loginId = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));

		}

		return bean;

	}

	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rays", "root", "root");

		StringBuffer sql = new StringBuffer("select * from user where 1 = 1");

		if (bean != null) {

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {

				sql.append(" and firstName like '" + bean.getFirstName() + "%'");

			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {

				sql.append(" and lastName like '" + bean.getLastName() + "%'");

			}

			if (bean.getLoginId() != null && bean.getLoginId().length() > 0) {

				sql.append(" and loginId like '" + bean.getLoginId() + "%'");

			}

			if (bean.getPassword() != null && bean.getPassword().length() > 0) {

				sql.append(" and password like '" + bean.getPassword() + "%'");

			}

			if (bean.getDob() != null && bean.getDob().getTime() > 0) {

				Date d = new Date(bean.getDob().getTime());

				sql.append(" and dob like '" + d + "%'");

			}

			if (bean.getAddress() != null && bean.getAddress().length() > 0) {

				sql.append(" and address like '" + bean.getAddress() + "%'");

			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + ", " + pageSize);

		}

		System.out.println("sql ==> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
			list.add(bean);

		}

		return list;

	}

}
