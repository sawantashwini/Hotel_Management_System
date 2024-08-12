package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MeasurementDto;
import com.service.MeasurementService;

/**
 * Servlet implementation class MeasurementServlet
 */
@WebServlet("/MeasurementServlet")
public class MeasurementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private ServletConfig config;
	   public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			this.config = config;
		}
	
    public MeasurementServlet() {
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
		

		MeasurementDto dto = new MeasurementDto();
		MeasurementService ser = new MeasurementService();
		
		
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));
		
		
		dto.setStatus(request.getParameter("Status") == null ? "active" : request.getParameter("Status"));
		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		
		/*
		 * dto.setUser_name(request.getParameter("User_name") == null ? "" :
		 * request.getParameter("User_name"));
		 */
		
		boolean b = false;
		
		// condition for insert
		if(dto.getId()==0) {
			
			b = ser.insertMeasurement(dto, request, config);
			
			if(b) {
				response.sendRedirect("add_measurement.jsp?msg=Yes");
			}
			else {
				response.sendRedirect("add_measurement.jsp?msg=No");
			}
		}
		//condition for updating
		else {
			
			b = ser.updateMeasurement(dto, request, config);
			
			if(b) {
				response.sendRedirect("manage_measurement.jsp?msg=YesUp");
			}
			else {
				response.sendRedirect("manage_measurement.jsp?msg=NoUp");
			}
		}
		
		
	}

}
