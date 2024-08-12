package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.LiquorCategoryDto;
import com.dto.LiquorPurchaseBillDto;
import com.service.LiquorCategoryService;
import com.service.LiquorPurchaseBillService;

/**
 * Servlet implementation class LiquorPurchaseBillServlet
 */
@WebServlet("/LiquorPurchaseBillServlet")
public class LiquorPurchaseBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LiquorPurchaseBillServlet() {
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
		LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();
		LiquorPurchaseBillService ser = new LiquorPurchaseBillService();

		// Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));
		dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));

		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		dto.setDealer_id_fk(Integer
				.parseInt(request.getParameter("Dealer_id_fk") == null ? "0" : request.getParameter("Dealer_id_fk")));
		dto.setDealer_account_id_fk(Integer.parseInt(request.getParameter("Dealer_account_id_fk") == null ? "0"
				: request.getParameter("Dealer_account_id_fk")));

		dto.setTotal_amount(Float.parseFloat(
				request.getParameter("Total_amount") == null || request.getParameter("Total_amount") == "" ? "0.0"
						: request.getParameter("Total_amount")));

		String Item_id[] = request.getParameterValues("Item_id");
		String Item_code[] = request.getParameterValues("Item_code");
		String Item_name[] = request.getParameterValues("Item_name");
		String Quantity[] = request.getParameterValues("Quantity");
		String Amt[] = request.getParameterValues("Amt");
		String Rate[] = request.getParameterValues("Rate");

		int i = 0;
		boolean b = false;
		// condition for insert
		if (dto.getId() == 0) {

			i = ser.insertLiquorPurchase(dto, request, config);

			b = ser.insertLiquorBillItem(Item_id, Item_name, Item_code, Quantity, Amt, Rate, dto, i, request, config);
			if (b) {
				response.sendRedirect("liquor_purchase.jsp?msg=Yes");
			} else {
				response.sendRedirect("liquor_purchase.jsp?msg=No");
			}
		}
		// condition for updating

		else {

			b = ser.updateLiquorPurchaseInfoById(dto, request, config);

			if (b) {
				response.sendRedirect("liquor_purchase_report.jsp?msg=YesUp");
			} else {
				response.sendRedirect("liquor_purchase_report.jsp?msg=NoUp");
			}
		}

	}

}
