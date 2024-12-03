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

		try {
			List list = model.search(bean);
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

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		String[] ids = request.getParameterValues("ids");
		System.out.println(ids);
		try {
			if (op.equals("delete")) {

				if (ids != null && ids.length > 0) {

					for (String id : ids) {
						
						System.out.println(id);

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
			
			List list = model.search(bean);
			request.setAttribute("list", list);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("MarksheetListView.jsp");
		rd.forward(request, response);
		
	}

}
