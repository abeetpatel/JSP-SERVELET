package com.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.rays.bean.ShopBean;
import com.rays.util.JDBCDataSource;

public class ShopModel {

	ResourceBundle rb = ResourceBundle.getBundle("com.rays.bundle.system");

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from shop");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id => " + pk);

		}

		return pk + 1;

	}

	public void add(ShopBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into shop values(?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getProductName());
			pstmt.setInt(3, bean.getProductPrice());
			pstmt.setDate(4, new java.sql.Date(bean.getPurchaseDate().getTime()));
			pstmt.setString(5, bean.getProductCategory());

			int i = pstmt.executeUpdate();

			conn.commit();

			System.out.println("data added successfully => " + i);

		} catch (Exception e) {

			conn.rollback();

		}

	}

	public void delete(int id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from shop where id = ?");

		pstmt.setInt(1, id);

		int i = pstmt.executeUpdate();

		System.out.println("data deleted successfully => " + i);

	}

	public void update(ShopBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update shop set productName = ?, productPrice = ?, purchaseDate = ?, productCategory = ? where id = ?");

		pstmt.setString(1, bean.getProductName());
		pstmt.setInt(2, bean.getProductPrice());
		pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setString(4, bean.getProductCategory());
		pstmt.setInt(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("data updated successfully => " + i);

	}

	public ShopBean finedById(int id) throws Exception {

		ShopBean bean = null;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from shop where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new ShopBean();

			bean.setId(rs.getInt(1));
			bean.setProductName(rs.getString(2));
			bean.setProductPrice(rs.getInt(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setProductCategory(rs.getString(5));

		}

		return bean;

	}

	public List search(ShopBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from shop where 1 = 1");

		if (bean != null) {

			if (bean.getProductName() != null && bean.getProductName().length() > 0) {

				sql.append(" and productName like '" + bean.getProductName() + "%'");

			}

			if (bean.getProductPrice() != 0 && bean.getProductPrice() > 0) {

				sql.append(" and productPrice like '" + bean.getProductPrice() + "%'");

			}

			if (bean.getPurchaseDate() != null && bean.getPurchaseDate().getTime() > 0) {

				Date d = new Date(bean.getPurchaseDate().getTime());

				sql.append(" and purchaseDate like '" + d + "%'");

			}

			if (bean.getProductCategory() != null && bean.getProductCategory().length() > 0) {

				sql.append(" and productCategory like '" + bean.getProductCategory() + "%'");

			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" limit " + pageNo + ", " + pageSize);

		}

		System.out.println("sql ===>> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {

			bean = new ShopBean();

			bean.setId(rs.getInt(1));
			bean.setProductName(rs.getString(2));
			bean.setProductPrice(rs.getInt(3));
			bean.setPurchaseDate(rs.getDate(4));
			bean.setProductCategory(rs.getString(5));
			list.add(bean);

		}

		return list;

	}

}
