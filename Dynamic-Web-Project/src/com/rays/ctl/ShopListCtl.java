package com.rays.ctl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.ShopBean;
import com.rays.model.ShopModel;

@WebServlet("/ShopListCtl.do")
public class ShopListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ShopBean bean = new ShopBean();
		ShopModel model = new ShopModel();

		try {
			List list = model.search(bean, 1, 5);
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ShopListView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		System.out.println("op ==>> " + op);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ShopBean bean = new ShopBean();
		ShopModel model = new ShopModel();
		int pageNo = 1;
		int pageSize = 5;

		String[] ids = request.getParameterValues("ids");

		try {

			if (op.equals("delete")) {

				if (ids != null && ids.length > 0) {

					for (String id : ids) {

						model.delete(Integer.parseInt(id));

					}

				} else {
					System.out.println("select at least one record");
				}

			}

			if (op.equals("search")) {

				System.out.println("in search condition");

				bean.setProductName(request.getParameter("productName"));
				bean.setProductCategory(request.getParameter("productCategory"));

				if (request.getParameter("productPrice") != null && request.getParameter("productPrice") != "") {

					bean.setProductPrice(Integer.parseInt(request.getParameter("productPrice")));

				}

				if (request.getParameter("purchaseDate") != null && request.getParameter("purchaseDate") != "") {

					bean.setPurchaseDate(sdf.parse(request.getParameter("purchaseDate")));

				}

			}

			if (op.equals("next")) {

				pageNo = Integer.parseInt(request.getParameter("pageNo"));

				pageNo++;

			}

			if (op.equals("previous")) {

				pageNo = Integer.parseInt(request.getParameter("pageNo"));

				pageNo--;

			}

			List list = model.search(bean, pageNo, pageSize);
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("list", list);

		} catch (Exception e) {

			e.printStackTrace();

		}

		RequestDispatcher rd = request.getRequestDispatcher("ShopListView.jsp");
		rd.forward(request, response);

	}

}
