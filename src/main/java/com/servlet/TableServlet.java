package com.servlet;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.TableDto;
import com.service.TableService;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TableServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
		TableDto dto = new TableDto();
		TableService ser = new TableService();

		// Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		// String
		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));

	boolean b = false;
		
		// condition for insert
		if(dto.getId()==0) {
			
			b = ser.insertTable(dto, request, config);
			
			if(b) {
				response.sendRedirect("add_table.jsp?msg=Yes");
			}
			else {
				response.sendRedirect("add_table.jsp?msg=No");
			}
		}
		//condition for updating
		else {
			
			b = ser.updateTable(dto, request, config);
			
			if(b) {
				response.sendRedirect("manage_table.jsp?msg=YesUp");
			}
			else {
				response.sendRedirect("manage_table.jsp?msg=NoUp");
			}
		}
	}
}
