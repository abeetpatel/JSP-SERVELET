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

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/UserListCtl.do")
public class UserListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		try {
			List list = model.search(bean, 1, 5);
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		System.out.println("op === > " + op);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
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

				bean.setFirstName(request.getParameter("firstName"));
				bean.setLastName(request.getParameter("lastName"));
				bean.setLoginId(request.getParameter("loginId"));
				bean.setAddress(request.getParameter("address"));
				if (request.getParameter("dob") != null && request.getParameter("dob") != "") {

					bean.setDob(sdf.parse(request.getParameter("dob")));

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

			System.out.println(e.getMessage());

		}

		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		rd.forward(request, response);

	}

}
