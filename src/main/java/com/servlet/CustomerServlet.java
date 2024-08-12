package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.CustomerDto;
import com.service.CustomerService;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		CustomerDto dto = new CustomerDto();
		CustomerService ser = new CustomerService();
		
		
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		dto.setMobile_no(request.getParameter("Mobile_no") == null ? "" : request.getParameter("Mobile_no"));
		dto.setAddress(request.getParameter("Address") == null ? "" : request.getParameter("Address"));
		dto.setGst_no(request.getParameter("Gst_no") == null ? "" : request.getParameter("Gst_no"));
		dto.setCompany_name(request.getParameter("Company_name") == null ? "" : request.getParameter("Company_name"));
		dto.setIn_date(request.getParameter("In_date") == null ? "" : request.getParameter("In_date"));
		dto.setDob(request.getParameter("Dob") == null ? "" : request.getParameter("Dob"));
		dto.setDoa(request.getParameter("Doa") == null ? "" : request.getParameter("Doa"));
		
		dto.setOpening_due(Float
				.parseFloat(request.getParameter("Opening_due") == null ? "0" : request.getParameter("Opening_due")));
		
		dto.setC_y_session(request.getParameter("C_y_session") == null ? "" : request.getParameter("C_y_session"));

		dto.setStatus(request.getParameter("Status") == null ? "active" : request.getParameter("Status"));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		
		String flag =request.getParameter("Flag") == null ? "" : request.getParameter("Flag");
		
		/*
		 * dto.setUser_name(request.getParameter("User_name") == null ? "" :
		 * request.getParameter("User_name"));
		 */
		
		boolean b = false;
		
		
		// condition for insert
		if(dto.getId()==0) {
			
			int c = ser.insertCustomer(dto, request, config);
			
			
			if(c!=0) {
				response.sendRedirect("add_customer.jsp?msg=Yes");
			}
			else {
				response.sendRedirect("add_customer.jsp?msg=No");
			}
		}
		//condition for updating
		else {
			
			if(flag.equalsIgnoreCase("Opening Due")) {	
				b = ser.updateCustomerOpeningDue(dto, request, config);
			}
			else {
			
				b = ser.updateCustomer(dto, request, config);
			}
			if(b) {
				response.sendRedirect("manage_customer.jsp?msg=YesUp");
			}
			else {
				response.sendRedirect("manage_customer.jsp?msg=NoUp");
			}
		}
	}

}
