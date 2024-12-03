package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.MarksheetBean;
import com.rays.model.MarksheetModel;

@WebServlet("/MarksheetCtl.do")
public class MarksheetCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		System.out.println("id => " + id);

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		if (id != null && id.length() > 0) {

			try {
				bean = model.finedById(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("MarksheetView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		System.out.println("Operation => " + op);

		String name = request.getParameter("name");
		String roll_no = request.getParameter("roll_no");
		String physics = request.getParameter("physics");
		String chemistry = request.getParameter("chemistry");
		String maths = request.getParameter("maths");
		String id = request.getParameter("id");

		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		try {

			bean.setName(name);
			bean.setRoll_no(roll_no);
			bean.setPhysics(Integer.parseInt(physics));
			bean.setChemistry(Integer.parseInt(chemistry));
			bean.setMaths(Integer.parseInt(maths));

			if (op.equals("save")) {
				model.add(bean);
				request.setAttribute("msg", "Marksheet Added Successfully");
			}

			if (op.equals("update")) {
				bean.setId(Integer.parseInt(id));
				model.update(bean);
				request.setAttribute("msg", "Marksheet Updated Successfully");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		RequestDispatcher rd = request.getRequestDispatcher("MarksheetView.jsp");
		rd.forward(request, response);

	}

}
