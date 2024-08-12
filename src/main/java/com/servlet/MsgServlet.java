package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MsgDto;
import com.service.MsgService;

/**
 * Servlet implementation class MsgServlet
 */
@WebServlet("/MsgServlet")
public class MsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MsgServlet() {
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
		

		MsgDto dto = new MsgDto();
		MsgService ser = new MsgService();

		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

		dto.setUser_id_fk(Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));

		dto.setName(request.getParameter("Name") == null ? "" : request.getParameter("Name"));

		dto.setStatus(request.getParameter("Status") == null ? "active" : request.getParameter("Status"));

		/*
		 * dto.setCurrent_in_date( request.getParameter("Current_in_date") == null ? ""
		 * : request.getParameter("Current_in_date"));
		 */
		if (dto.getId() == 0) {

			boolean b = ser.insertMsg(dto, request, config);

			if (b) {

				response.sendRedirect("add_msg.jsp?msg=Yes");
			} else {

				response.sendRedirect("manage_msg.jsp?msg=No");

			}
		}

		else {
			boolean b = ser.UpdateMsg(dto, request, config);

			if (b) {

				response.sendRedirect("index.jsp?msg=YesUp&alert_title=Message");
			} else {

				response.sendRedirect("edit_msg.jsp?msg=NoUp&id="+ dto.getId());

			}

		}

		
	}

}
