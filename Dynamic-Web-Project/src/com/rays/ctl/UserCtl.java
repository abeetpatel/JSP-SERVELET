package com.rays.ctl;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/UserCtl.do")
public class UserCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		System.out.println("id ===> " + id);

		UserModel model = new UserModel();
		UserBean bean = new UserBean();

		if (id != null && id.length() > 0) {

			try {
				bean = model.findById(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("UserView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		System.out.println("op ==== > " + op);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String id = request.getParameter("id");

		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		try {

			bean.setFirstName(firstName);
			bean.setLastName(lastName);
			bean.setLoginId(loginId);
			bean.setPassword(password);
			bean.setDob(sdf.parse(dob));
			bean.setAddress(address);

			if (op.equals("save") || op.equals("update")) {

				if (id != null && id.length() > 0) {

					System.out.println("id ==== > " + id);

					bean.setId(Integer.parseInt(id));
					model.update(bean);
					request.setAttribute("msg", "User Updated Successfully");

				} else {

					model.add(bean);
					request.setAttribute("msg", "User Added Successfully");

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UserView.jsp");
		rd.forward(request, response);

	}

}
