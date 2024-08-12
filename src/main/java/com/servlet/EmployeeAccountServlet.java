package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.EmployeeAccountDto;
import com.dto.PaymentDto;
import com.service.EmployeeAccountService;

/**
 * Servlet implementation class EmployeeAccountServlet
 */
@WebServlet("/EmployeeAccountServlet")
public class EmployeeAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeAccountServlet() {
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
		EmployeeAccountDto dto = new EmployeeAccountDto();
		PaymentDto pay_dto = new PaymentDto();
		EmployeeAccountService ser = new EmployeeAccountService();
		
		//Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setEmployee_id_fk(Integer.parseInt(request.getParameter("Employee_id_fk") == null ? "0" : request.getParameter("Employee_id_fk")));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		dto.setBank_id_fk(Integer.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));
		dto.setCash_payment_id_fk(Integer.parseInt(request.getParameter("Cash_payment_id_fk") == null ? "0" : request.getParameter("Cash_payment_id_fk")));
		dto.setOnline_payment_id_fk(Integer.parseInt(request.getParameter("Online_payment_id_fk") == null ? "0" : request.getParameter("Online_payment_id_fk")));

		//String
		dto.setPayment_mode(request.getParameter("Payment_mode") == null ? "" : request.getParameter("Payment_mode"));
		dto.setOnline_remark(request.getParameter("Online_remark") == null ? "" : request.getParameter("Online_remark"));
		dto.setEmployee_name(request.getParameter("Employee_name") == null ? "" : request.getParameter("Employee_name"));
		dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));
		dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));
		dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));
		dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));
		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		
		dto.setCurrent_in_date(request.getParameter("Current_in_date") == null ? "" : request.getParameter("Current_in_date"));

		//Float
		dto.setSalary_per_month(Float.parseFloat(request.getParameter("Salary_per_month") == null ? "0.0" : request.getParameter("Salary_per_month")));
		dto.setCash_amount(Float.parseFloat(request.getParameter("Cash_amount") == null ? "0.0" : request.getParameter("Cash_amount")));
		dto.setOnline_amount(Float.parseFloat(request.getParameter("Online_amount") == null ? "0.0" : request.getParameter("Online_amount")));

		//Payment dto
		pay_dto.setBank_id_fk(Integer.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));
		pay_dto.setBill_id_fk(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		pay_dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));
		pay_dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));
		pay_dto.setOnline_remark(request.getParameter("Online_remark") == null ? "" : request.getParameter("Online_remark"));
		pay_dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));
		pay_dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));
		pay_dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		pay_dto.setType("Transaction");


		String amountmode = request.getParameter("Amountmode");

		if (amountmode.equals("Debit")) {
			dto.setDebit_amount(Float.parseFloat(request.getParameter("Paid_amount") == null ? "0.0" : request.getParameter("Paid_amount")));
			pay_dto.setDebit_cash_amount(Float.parseFloat(request.getParameter("Cash_amount") == null ? "0.0" : request.getParameter("Cash_amount")));
			pay_dto.setDebit_online_amount(Float.parseFloat(request.getParameter("Online_amount") == null ? "0.0" : request.getParameter("Online_amount")));
		}

		else {
			dto.setCredit_amount(Float.parseFloat(request.getParameter("Paid_amount") == null ? "0.0" : request.getParameter("Paid_amount")));
			pay_dto.setCash_amount(Float.parseFloat(request.getParameter("Cash_amount") == null ? "0.0" : request.getParameter("Cash_amount")));
			pay_dto.setOnline_amount(Float.parseFloat(request.getParameter("Online_amount") == null ? "0.0" : request.getParameter("Online_amount")));
		}

		int employee_id = dto.getEmployee_id_fk();
		if (dto.getId() == 0) {

			int b = ser.insertEmployeeAccountInfo(dto, pay_dto, request, config);

			if (b > 0) {
				response.sendRedirect("manage_employee.jsp?msg=YesUp&alert_title=Employee Transaction");

			} else {
				response.sendRedirect("pay_employee_account.jsp?msg=No&id=" + employee_id + "&alert_title=Employee Transaction");

			}
		}

		else if(dto.getId()>0){
			
			boolean b = ser.updateEmployeeAccountInfo(dto, pay_dto, request, config);

			if (b==true) {

				response.sendRedirect("manage_employee_account.jsp?msg=YesUp&id="+employee_id);
			} else {

				response.sendRedirect("manage_employee_account.jsp?msg=NoUp&id="+employee_id);

			}
		}

	}

}