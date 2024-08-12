package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.IngredientsDto;
import com.service.IngredientsPurchaseService;

/**
 * Servlet implementation class IngredientsPurchaseServlet
 */
@WebServlet("/IngredientsPurchaseServlet")
public class IngredientsPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IngredientsPurchaseServlet() {
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
		IngredientsPurchaseService ser = new IngredientsPurchaseService();

		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));
		dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));

		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));

		dto.setUser_name(request.getParameter("User_name") == null ? "" : request.getParameter("User_name"));
		dto.setTotal_amount(Float.parseFloat(request.getParameter("Total_amount") == null || request.getParameter("Total_amount") == "" ? "0.0" : request.getParameter("Total_amount")));


		String Item_id[] = request.getParameterValues("Item_id");
		String Item_name[] = request.getParameterValues("Item_name");
		String Item_quantity[] = request.getParameterValues("Item_quantity");
		String Item_amt[] = request.getParameterValues("Item_amt");
		String Item_rate[] = request.getParameterValues("Item_rate");

		int id = 0;

		// condition for insert

		if (dto.getId() == 0) {

			id = ser.insertIngredientsPurchaseBillInfo(dto, request, config);

			boolean b = ser.insertIngredientsBillItem(Item_id, Item_name, Item_quantity,Item_amt, Item_rate, dto, id, request, config);
			if (b) {

				response.sendRedirect("ingredients_purchase.jsp?msg=Yes");

			} else {

				response.sendRedirect("ingredients_purchase.jsp?msg=No");
			}
		}

		// condition for updating

		else {
			boolean b = ser.updateIngredientsPurchaseBill(dto, request, config);

			if (b) {

				response.sendRedirect("ingredients_purchase_report.jsp?msg=YesUp");
			} else {

				response.sendRedirect("ingredients_purchase_report.jsp?msg=NoUp");

			}

		}
	}

}
