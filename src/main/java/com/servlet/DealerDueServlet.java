package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.DealerDto;
import com.dto.PaymentDto;
import com.service.DealerService;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/DealerDueServlet")

public class DealerDueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DealerDueServlet() {
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

		DealerDto dto = new DealerDto();
		PaymentDto pay_dto = new PaymentDto();
		DealerService ser = new DealerService();
		int flag;

		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

		dto.setDealer_id_fk(Integer
				.parseInt(request.getParameter("Dealer_id_fk") == null ? "0" : request.getParameter("Dealer_id_fk")));

		dto.setBank_id_fk(Integer
				.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));

		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));

		dto.setType(request.getParameter("Type") == null ? "" : request.getParameter("Type"));

		dto.setPay_date(request.getParameter("Pay_date") == null ? "" : request.getParameter("Pay_date"));

		dto.setC_y_session(request.getParameter("C_y_session") == null ? "" : request.getParameter("C_y_session"));

		dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));

		dto.setPayment_mode(request.getParameter("Payment_mode") == null ? "" : request.getParameter("Payment_mode"));

		dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));

		dto.setOnline_remark(
				request.getParameter("Online_remark") == null ? "N/A" : request.getParameter("Online_remark"));

		dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));

		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));

		dto.setCurrent_in_date(
				request.getParameter("Current_in_date") == null ? "" : request.getParameter("Current_in_date"));

		dto.setCash_amount(Float.parseFloat(
				request.getParameter("Cash_amount") == null || request.getParameter("Cash_amount") == "" ? "0.0"
						: request.getParameter("Cash_amount")));

		dto.setOnline_amount(Float.parseFloat(
				request.getParameter("Online_amount") == null || request.getParameter("Online_amount") == "" ? "0.0"
						: request.getParameter("Online_amount")));

		// ************PAYMENT DTO VALUES***********
		// Integer
		pay_dto.setBill_id_fk(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		pay_dto.setBank_id_fk(Integer
				.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));
		// Float
		pay_dto.setDebit_online_amount(Float.parseFloat(
				request.getParameter("Online_amount") == null || request.getParameter("Online_amount") == "" ? "0.0"
						: request.getParameter("Online_amount")));
		pay_dto.setDebit_cash_amount(Float.parseFloat(
				request.getParameter("Cash_amount") == null || request.getParameter("Cash_amount") == "" ? "0.0"
						: request.getParameter("Cash_amount")));
		// String
		pay_dto.setRemark(request.getParameter("Remark") == null ? "0" : request.getParameter("Remark"));
		pay_dto.setOnline_remark(
				request.getParameter("Online_remark") == null ? "N/A" : request.getParameter("Online_remark"));
		pay_dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));
		pay_dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));
		pay_dto.setIn_date(request.getParameter("Pay_date") == null ? "" : request.getParameter("Pay_date"));
		pay_dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		pay_dto.setType("Dealer due");

		flag = (Integer.parseInt(request.getParameter("Flag") == null ? "0" : request.getParameter("Flag")));

		if (flag == 1) {

			int b = ser.payDealerDue(pay_dto, dto, request, config);

			if (b > 0) {

				response.sendRedirect("print_dealer_due_receipt.jsp?id=" + b);
			} else {

				response.sendRedirect("manage_dealer.jsp?msg=No");

			}
		}

		else if (flag == 2) {

			int b = ser.UpdateDealerDue(pay_dto, dto, request, config);

			if (b != 0) {

				response.sendRedirect("print_dealer_due_receipt.jsp?msg=YesUp&id=" + dto.getId());
			} else {

				response.sendRedirect("manage_dealer_due_by_id.jsp?msg=NoUp&id=" + b);

			}

		}

	}

}
