package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.IncomeHeadDto;
import com.service.IncomeHeadService;

/**
 * Servlet implementation class IncomeHeadServlet
 */
@WebServlet("/IncomeHeadServlet")
public class IncomeHeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncomeHeadServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		IncomeHeadDto dto = new IncomeHeadDto();
		IncomeHeadService ser = new IncomeHeadService();
		
		//Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		
		//String
		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));	
		dto.setUser_name(request.getParameter("User_name") == null ? "" : request.getParameter("User_name"));

		
		boolean b;
		
		// condition for insert
		if(dto.getId()==0) {
			
			Boolean rn = ser.insertIncomeHead(dto, request, config);
			
			if(rn) {
				response.sendRedirect("add_income_head.jsp?msg=Yes");
			}
			else {
				response.sendRedirect("add_income_head.jsp?msg=No");
			}
		}
		//condition for updating
		else {
			
			b = ser.updateIncomeHead(dto, request, config);
			
			if(b) {
				response.sendRedirect("manage_income_head.jsp?msg=YesUp");
			}
			else {
				response.sendRedirect("manage_income_head.jsp?msg=NoUp");
			}
		}
	}

}
