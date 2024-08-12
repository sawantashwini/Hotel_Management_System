package com.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.UserDto;
import com.service.UserService;

/**
 * Servlet implementation class userservlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * Default constructor.
	 */
	public UserServlet() {
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

		UserDto dto = new UserDto();
		UserService ser = new UserService();

		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		dto.setMobile_no(request.getParameter("Mobile_no") == null ? "" : request.getParameter("Mobile_no"));

		dto.setPassword(request.getParameter("Password") == null ? "" : request.getParameter("Password"));
		dto.setAddress(request.getParameter("Address") == null ? "" : request.getParameter("Address"));

		dto.setActivation_date(
				request.getParameter("Activation_date") == null ? "" : request.getParameter("Activation_date"));
		dto.setStatus(request.getParameter("Status") == null ? "" : request.getParameter("Status"));

		dto.setDelete_module(
				request.getParameter("Delete_module") == null ? "" : request.getParameter("Delete_module"));
		dto.setUser_module(request.getParameter("User_module") == null ? "" : request.getParameter("User_module"));
		dto.setUpdate_module(
				request.getParameter("Update_module") == null ? "" : request.getParameter("Update_module"));

		dto.setMaster_module(
				request.getParameter("Master_module") == null ? "" : request.getParameter("Master_module"));
		
		
		dto.setPayment_module(request.getParameter("Payment_module") == null ? "" : request.getParameter("Payment_module"));
		dto.setBank_module(request.getParameter("Bank_module") == null ? "" : request.getParameter("Bank_module"));
		dto.setSpend_module(request.getParameter("Spend_module") == null ? "" : request.getParameter("Spend_module"));
		dto.setEmployee_module(request.getParameter("Employee_module") == null ? "" : request.getParameter("Employee_module"));
		dto.setIncome_module(request.getParameter("Income_module") == null ? "" : request.getParameter("Income_module"));
		
		dto.setOrder_module(request.getParameter("Order_module") == null ? "" : request.getParameter("Order_module"));
		dto.setRoom_module(request.getParameter("Room_module") == null ? "" : request.getParameter("Room_module"));
		dto.setIngredients_module(request.getParameter("Ingredients_module") == null ? "" : request.getParameter("Ingredients_module"));
		dto.setReciepe_relation_module(request.getParameter("Reciepe_relation_module") == null ? "" : request.getParameter("Reciepe_relation_module"));
		dto.setDealer_module(request.getParameter("Dealer_module") == null ? "" : request.getParameter("Dealer_module"));
		dto.setCustomer_module(request.getParameter("Customer_module") == null ? "" : request.getParameter("Customer_module"));
		dto.setLiquor_module(request.getParameter("Liquor_module") == null ? "" : request.getParameter("Liquor_module"));
		
		//Report
		dto.setIncome_report(request.getParameter("Income_report") == null ? "" : request.getParameter("Income_report"));
		dto.setSpend_report(request.getParameter("Spend_report") == null ? "" : request.getParameter("Spend_report"));
		dto.setEmployee_report(request.getParameter("Employee_report") == null ? "" : request.getParameter("Employee_report"));
		
		dto.setLiquor_report(request.getParameter("Liquor_report") == null ? "" : request.getParameter("Liquor_report"));
		dto.setCustomer_report(request.getParameter("Customer_report") == null ? "" : request.getParameter("Customer_report"));
		dto.setDealer_report(request.getParameter("Dealer_report") == null ? "" : request.getParameter("Dealer_report"));
		dto.setIngredients_in_Report(request.getParameter("Ingredients_in_Report") == null ? "" : request.getParameter("Ingredients_in_Report"));
		dto.setIngredients_out_Report(request.getParameter("Ingredients_out_Report") == null ? "" : request.getParameter("Ingredients_out_Report"));
		
		dto.setComplete_bills_report(request.getParameter("Complete_bills_report") == null ? "" : request.getParameter("Complete_bills_report"));
		dto.setPending_bills_report(request.getParameter("Pending_bills_report") == null ? "" : request.getParameter("Pending_bills_report"));
		dto.setKot_bills_report(request.getParameter("Kot_bills_report") == null ? "" : request.getParameter("Kot_bills_report"));
		dto.setRoom_available_report(request.getParameter("Room_available_report") == null ? "" : request.getParameter("Room_available_report"));
		dto.setRoom_booked_report(request.getParameter("Room_booked_report") == null ? "" : request.getParameter("Room_booked_report"));
		dto.setRoom_bill_report(request.getParameter("Room_bill_report") == null ? "" : request.getParameter("Room_bill_report"));
		
		dto.setSpend_head_module(request.getParameter("Spend_head_module") == null ? "" : request.getParameter("Spend_head_module"));
		dto.setIncome_head_module(request.getParameter("Income_head_module") == null ? "" : request.getParameter("Income_head_module"));
		
		int flag = (Integer.parseInt(request.getParameter("flag") == null ? "0" : request.getParameter("flag")));

		boolean b = false;

		if (dto.getId() == 0) {

			b = ser.insertUser(dto, request, config);

			if (b) {

				response.sendRedirect("add_user.jsp?msg=Yes");
			} else {

				response.sendRedirect("add_user.jsp?msg=No");

			}
		}

		else {

			if (flag == 2) {

				b = ser.UpdateUserById(dto, request, config);

			} else {
				b = ser.UpdateUser(dto, request, config);
			}

			if (b) {

				response.sendRedirect("manage_user.jsp?msg=YesUp");

			} else {

				response.sendRedirect("manage_user.jsp?msg=NoUp");

			}

		}

	}
}
