package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.EmployeeSalaryDto;
import com.dto.PaymentDto;
import com.service.EmployeeSalaryService;

/**
 * Servlet implementation class EmployeeSalaryServlet
 */
@WebServlet("/EmployeeSalaryServlet")
public class EmployeeSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeSalaryServlet() {
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
		EmployeeSalaryDto dto = new EmployeeSalaryDto();
		PaymentDto pay_dto = new PaymentDto();
		EmployeeSalaryService ser = new EmployeeSalaryService();

		//Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setEmployee_id_fk(Integer.parseInt(request.getParameter("Employee_id_fk") == null ? "0" : request.getParameter("Employee_id_fk")));
		dto.setBank_id_fk(Integer.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));
		dto.setCash_payment_id_fk(Integer.parseInt(request.getParameter("Cash_payment_id_fk") == null ? "0" : request.getParameter("Cash_payment_id_fk")));
		dto.setOnline_payment_id_fk(Integer.parseInt(request.getParameter("Online_payment_id_fk") == null ? "0" : request.getParameter("Online_payment_id_fk")));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		
		//Days
		dto.setTotal_days(Float.parseFloat(request.getParameter("Total_days") == null ? "0" : request.getParameter("Total_days")));
		dto.setPresent_days(Float.parseFloat(request.getParameter("Present_days") == null ? "0" : request.getParameter("Present_days")));
		dto.setAbsent_days(Float.parseFloat(request.getParameter("Absent_days") == null ? "0.0" : request.getParameter("Absent_days")));
		dto.setLeave_days(Float.parseFloat(request.getParameter("Leave_days") == null ? "0.0" : request.getParameter("Leave_days")));
		dto.setHalf_days(Float.parseFloat(request.getParameter("Half_days") == null ? "0.0" : request.getParameter("Half_days")));

		//Salary
		dto.setSalary_per_month(Float.parseFloat(request.getParameter("Salary_per_month") == null ? "0.0" : request.getParameter("Salary_per_month")));
		dto.setFinal_salary(Float.parseFloat(request.getParameter("Final_salary") == null ? "0.0" : request.getParameter("Final_salary")));
		dto.setCash_amount(Float.parseFloat(request.getParameter("Cash_amount") == null ? "0.0" : request.getParameter("Cash_amount")));
		dto.setOnline_amount(Float.parseFloat(request.getParameter("Online_amount") == null ? "0.0" : request.getParameter("Online_amount")));
		dto.setAmount(Float.parseFloat(request.getParameter("Amount") == null ? "0.0" : request.getParameter("Amount")));

		//String
		dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));
		dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));
		dto.setPay_date(request.getParameter("Pay_date") == null ? "" : request.getParameter("Pay_date"));
		dto.setPaid_year(request.getParameter("Paid_year") == null ? "" : request.getParameter("Paid_year"));
		dto.setPaid_month(request.getParameter("Paid_month") == null ? "" : request.getParameter("Paid_month"));
		dto.setPayment_mode(request.getParameter("Payment_mode") == null ? "" : request.getParameter("Payment_mode"));
		dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));
		dto.setOnline_remark(request.getParameter("Online_remark") == null ? "0" : request.getParameter("Online_remark"));
		dto.setOnline_date(request.getParameter("Online_date") == null ? "0" : request.getParameter("Online_date"));
		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		dto.setCurrent_in_date(request.getParameter("Current_in_date") == null ? "0" : request.getParameter("Current_in_date"));

		// payment_dto
		pay_dto.setBank_id_fk(Integer.parseInt(request.getParameter("Bank_id_fk") == null ? "0" : request.getParameter("Bank_id_fk")));
		pay_dto.setBill_id_fk(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		pay_dto.setCash_amount(Float.parseFloat(request.getParameter("Cash_amount") == null ? "0.0" : request.getParameter("Cash_amount")));
		pay_dto.setOnline_amount(Float.parseFloat(request.getParameter("Online_amount") == null ? "0.0" : request.getParameter("Online_amount")));
		pay_dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));
		pay_dto.setOnline_date(request.getParameter("Online_date") == null ? "" : request.getParameter("Online_date"));
		pay_dto.setOnline_remark(request.getParameter("Online_remark") == null ? "" : request.getParameter("Online_remark"));
		pay_dto.setOnline_way(request.getParameter("Online_way") == null ? "" : request.getParameter("Online_way"));
		pay_dto.setRemark(request.getParameter("Remark") == null ? "" : request.getParameter("Remark"));
		pay_dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		pay_dto.setType("Salary");

		int employee_id = dto.getEmployee_id_fk();
		if (dto.getId() == 0) {

			int b = ser.payEmployeeSalary(dto, pay_dto, request, config);

			if (b > 0) {
				response.sendRedirect("print_employee_salary_receipt.jsp?msg=Yes&id=" + b);

			} else {
				response.sendRedirect("manage_employee.jsp?msg=No&id=" + employee_id);

			}
		}

		else {
			boolean b = ser.updatepayEmployeeSalary(dto, pay_dto, request, config);

			if (b) {

				response.sendRedirect("manage_employee_salary.jsp?msg=YesUp&id=" + employee_id + "&alert_title=Employee Salary");
			} else {

				response.sendRedirect("manage_employee_salary.jsp?msg=NoUp&id=" + employee_id + "&alert_title=Employee Salary");

			}
		}

	}

}