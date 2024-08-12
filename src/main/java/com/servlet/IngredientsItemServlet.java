package com.servlet;

import java.io.IOException;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.IngredientsDto;
import com.service.IngredientsItemService;

/**
 * Servlet implementation class IngredientsItemServlet
 */
@WebServlet("/IngredientsItemServlet")
public class IngredientsItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IngredientsItemServlet() {
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
		IngredientsDto dto = new IngredientsDto();
		IngredientsItemService ser = new IngredientsItemService();

		//Int
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		dto.setMeasurement_id_fk(Integer.parseInt(request.getParameter("Measurement_id_fk") == null ? "0" : request.getParameter("Measurement_id_fk")));
		
		//String 
		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		
		
		//Float
		dto.setQty(Float.parseFloat(request.getParameter("Qty") == null || request.getParameter("Qty") == "" ? "0.0" : request.getParameter("Qty")));
		dto.setMin_limit(Float.parseFloat(request.getParameter("Min_limit") == null || request.getParameter("Min_limit") == "" ? "0.0" : request.getParameter("Min_limit")));

		boolean b = false;

		// condition for insert
		if (dto.getId() == 0) {

			b = ser.insertIngredientsItem(dto, request, config);

			if (b) {
				response.sendRedirect("add_ingredients_item.jsp?msg=Yes");
			} else {
				response.sendRedirect("add_ingredients_item.jsp?msg=No");
			}
		}
		// condition for updating
		else {

			b = ser.updateIngredientsItem(dto, request, config);

			if (b) {
				response.sendRedirect("manage_ingredients_item.jsp?msg=YesUp");
			} else {
				response.sendRedirect("manage_ingredients_item.jsp?msg=NoUp");
			}
		}
	}

}
