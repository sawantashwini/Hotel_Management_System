package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.CustomerDto;

import com.dto.PaymentDto;
import com.service.CustomerDueService;

/**
 * Servlet implementation class CustomerDueServlet
 */
@WebServlet("/CustomerDueServlet")
public class CustomerDueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerDueServlet() {
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
		CustomerDto dto = new CustomerDto();

		CustomerDueService ser = new CustomerDueService();
		int flag;
		// Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

		dto.setBank_id_fk(Integer
				.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));

		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));

		dto.setCustomer_id_fk(Integer.parseInt(
				request.getParameter("Customer_id_fk") == null ? "0" : request.getParameter("Customer_id_fk")));

		dto.setOnline_payment_id_fk(Integer.parseInt(request.getParameter("Online_payment_id_fk") == null ? "0"
				: request.getParameter("Online_payment_id_fk")));

		dto.setCash_payment_id_fk(Integer.parseInt(
				request.getParameter("Cash_payment_id_fk") == null ? "0" : request.getParameter("Cash_payment_id_fk")));

		dto.setCustomer_account_id_fk(Integer.parseInt(request.getParameter("Customer_account_id_fk") == null ? "0"
				: request.getParameter("Customer_account_id_fk")));
		// string
		dto.setPay_date(request.getParameter("Pay_date") == null ? "" : request.getParameter("Pay_date"));

		dto.setC_y_session(request.getParameter("C_y_session") == null ? "" : request.getParameter("C_y_session"));
		dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));

		dto.setPayment_mode(request.getParameter("Payment_mode") == null ? "" : request.getParameter("Payment_mode"));

		dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));

		dto.setOnline_remark(
				request.getParameter("Online_remark") == null ? "N/A" : request.getParameter("Online_remark"));

		dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));
		dto.setUpcoming_remark(
				request.getParameter("Upcoming_remark") == null ? "N/A" : request.getParameter("Upcoming_remark"));

		dto.setUpcoming_date(request.getParameter("Upcoming_date") == null ? "" : request.getParameter("Upcoming_date"));

		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		// Float

		dto.setCash_amount(Float
				.parseFloat(request.getParameter("Cash_amount") == null || request.getParameter("Cash_amount") == "" ? "0.0" : request.getParameter("Cash_amount")));


		dto.setOnline_amount(Float.parseFloat(
				request.getParameter("Online_amount") == null || request.getParameter("Online_amount") == "" ? "0.0"
						: request.getParameter("Online_amount")));


		dto.setPay_amount(Float
				.parseFloat(request.getParameter("Pay_amount") == null ? "0.0" : request.getParameter("Pay_amount")));
		// ************PAYMENT DTO VALUES***********
		PaymentDto pay_dto = new PaymentDto();

		// Integer
		pay_dto.setBill_id_fk(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		pay_dto.setBank_id_fk(Integer
				.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));
		// Float
		pay_dto.setOnline_amount(Float.parseFloat(
				request.getParameter("Online_amount") == null ||request.getParameter("Online_amount") == "" ? "0.0"  : request.getParameter("Online_amount")));
		pay_dto.setCash_amount(Float
				.parseFloat(request.getParameter("Cash_amount") == null ||request.getParameter("Cash_amount") == "" ? "0.0"  : request.getParameter("Cash_amount")));
		// String
		pay_dto.setRemark(request.getParameter("Remark") == null ? "0" : request.getParameter("Remark"));
		pay_dto.setOnline_remark(
				request.getParameter("Online_remark") == null ? "" : request.getParameter("Online_remark"));
		pay_dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));
		pay_dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));
		pay_dto.setIn_date(request.getParameter("Pay_date") == null ? "" : request.getParameter("Pay_date"));
		pay_dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		pay_dto.setType("Customer Due");

		flag = (Integer.parseInt(request.getParameter("Flag") == null ? "0" : request.getParameter("Flag")));

		if (flag == 1) {

			int id = ser.insertCustomerDue(pay_dto, dto, request, config);

			if (id != 0) {

				response.sendRedirect("print_customer_due_receipt.jsp?id=" + id);
			} else {

				response.sendRedirect("pay_customer_due.jsp?msg=NoUp");

			}
		}

		else if (flag == 2) {

			int b = ser.UpdateCustomerDue(pay_dto, dto, request, config);

			if (b != 0) {

				response.sendRedirect("print_customer_due_receipt.jsp?msg=YesUp&id=" + dto.getId());
			} else {

				response.sendRedirect("pay_customer_due.jsp?msg=NoUp");

			}

		}

	}

}
