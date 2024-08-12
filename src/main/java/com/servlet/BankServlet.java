package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.BankDto;
import com.service.BankService;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BankServlet() {
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
		BankDto dto = new BankDto();
		BankService ser = new BankService();

		// Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id").trim()));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk").trim()));

		// String
		dto.setName(request.getParameter("Name") == null || request.getParameter("Name").equalsIgnoreCase("") ? "" : request.getParameter("Name").trim());
		dto.setIn_date(request.getParameter("In_date") == null || request.getParameter("In_date").equalsIgnoreCase("") ? "" : request.getParameter("In_date").trim());
		dto.setAccount_no(request.getParameter("Account_no") == null || request.getParameter("Account_no").equalsIgnoreCase("") ? "" : request.getParameter("Account_no").trim());
		dto.setBranch(request.getParameter("Branch") == null || request.getParameter("Branch").equalsIgnoreCase("") ? "" : request.getParameter("Branch").trim());
		dto.setIfsc_code(request.getParameter("Ifsc_code") == null  || request.getParameter("Ifsc_code").equalsIgnoreCase("") ? "" : request.getParameter("Ifsc_code").trim());
		dto.setOpening_date(request.getParameter("Opening_date") == null || request.getParameter("Opening_date").equalsIgnoreCase("")  ? "" : request.getParameter("Opening_date").trim());
		dto.setStatus(request.getParameter("Status") == null || request.getParameter("Status").equalsIgnoreCase("") ? "Active" : request.getParameter("Status").trim());

		// float
		dto.setOpening_bal(Float.parseFloat(request.getParameter("Opening_bal") == null || request.getParameter("Opening_bal") == "" ? "0.00" : request.getParameter("Opening_bal").trim()));
		
		dto.setCredit(Float.parseFloat(request.getParameter("Credit") == null || request.getParameter("Credit") == "" ? "0.00" : request.getParameter("Credit").trim()));

		boolean b = false;

		// condition for insert
		if (dto.getId() == 0) {

			b = ser.insertBank(dto, request, config);

			if (b) {
				response.sendRedirect("add_bank.jsp?msg=Yes");
			} else {
				response.sendRedirect("add_bank.jsp?msg=No");
			}
		}
		// condition for updating
		else {

			b = ser.updateBank(dto, request, config);

			if (b) {
				response.sendRedirect("manage_bank.jsp?msg=YesUp");
			} else {
				response.sendRedirect("manage_bank.jsp?msg=NoUp");
			}
		}
	}
}
