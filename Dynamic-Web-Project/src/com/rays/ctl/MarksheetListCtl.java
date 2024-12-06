package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.MarksheetBean;
import com.rays.model.MarksheetModel;

@WebServlet("/MarksheetListCtl.do")
public class MarksheetListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		int pageNo = 1;
		int pageSize = 5;

		try {
			List list = model.search(bean, pageNo, pageSize);
			request.setAttribute("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("MarksheetListView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		
		System.out.println("op ==> " +op);

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
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

				bean.setName(request.getParameter("name"));
				bean.setRoll_no(request.getParameter("roll_no"));

			}

			if (op.equals("previous")) {

				pageNo = Integer.parseInt(request.getParameter("pageNo"));

				pageNo--;

				System.out.println("pageNo =>" + pageNo);

			}

			if (op.equals("next")) {

				pageNo = Integer.parseInt(request.getParameter("pageNo"));

				pageNo++;

				System.out.println("pageNo =>" + pageNo);

			}

			List list = model.search(bean, pageNo, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("pageNo", pageNo);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("MarksheetListView.jsp");
		rd.forward(request, response);

	}

}
