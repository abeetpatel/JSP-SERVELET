package com.rays.ctl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.ShopBean;
import com.rays.model.ShopModel;

@WebServlet("/ShopCtl.do")
public class ShopCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		System.out.println("id => " + id);

		ShopBean bean = new ShopBean();
		ShopModel model = new ShopModel();

		if (id != null && id.length() > 0) {

			try {
				bean = model.finedById(Integer.parseInt(id));
				request.setAttribute("bean", bean);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("ShopView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		System.out.println("operation => " + op);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		String purchaseDate = request.getParameter("purchaseDate");
		String productCategory = request.getParameter("productCategory");
		String id = request.getParameter("id");

		ShopBean bean = new ShopBean();
		ShopModel model = new ShopModel();

		try {

			bean.setProductName(productName);
			bean.setProductPrice(Integer.parseInt(productPrice));
			bean.setPurchaseDate(sdf.parse(purchaseDate));
			bean.setProductCategory(productCategory);

			if (op.equals("update")) {

				System.out.println("id => " + id);

				bean.setId(Integer.parseInt(id));
				model.update(bean);
				request.setAttribute("msg", "User Updated Successfully");

			}

			if (op.equals("save")) {

				model.add(bean);
				request.setAttribute("msg", "User Added Successfully");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ShopView.jsp");
		rd.forward(request, response);

	}

}
