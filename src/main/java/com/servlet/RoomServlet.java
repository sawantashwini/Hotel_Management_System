package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.RoomDto;
import com.service.RoomService;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
		RoomDto dto = new RoomDto();
		RoomService ser = new RoomService();

		// Integer
		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
		// String
		dto.setRoom_no(request.getParameter("Room_no") == null ? "" : request.getParameter("Room_no"));
		
		dto.setRoom_name(request.getParameter("Room_name") == null ? "" : request.getParameter("Room_name"));
		
		dto.setStatus(request.getParameter("Status") == null ? "Active" : request.getParameter("Status"));
		
		//Float
		dto.setRent(Float.parseFloat(request.getParameter("Rent") == null || request.getParameter("Rent") == "" ? "0.0" : request.getParameter("Rent")));

		dto.setRent_double(Float.parseFloat(request.getParameter("Rent_double") == null || request.getParameter("Rent_double") == "" ? "0.0" : request.getParameter("Rent_double")));

		dto.setRent_three(Float.parseFloat(request.getParameter("Rent_three") == null || request.getParameter("Rent_three") == "" ? "0.0" : request.getParameter("Rent_three")));

		dto.setRent_fourth(Float.parseFloat(request.getParameter("Rent_fourth") == null || request.getParameter("Rent_fourth") == "" ? "0.0" : request.getParameter("Rent_fourth")));


	boolean b = false;
		
		// condition for insert
		if(dto.getId()==0) {
			
			b = ser.insertRoom(dto, request, config);
			
			if(b) {
				response.sendRedirect("add_room.jsp?msg=Yes");
			}
			else {
				response.sendRedirect("add_room.jsp?msg=No");
			}
		}
		//condition for updating
		else {
			
			b = ser.updateRoom(dto, request, config);
			
			if(b) {
				response.sendRedirect("manage_room.jsp?msg=YesUp");
			}
			else {
				response.sendRedirect("manage_room.jsp?msg=NoUp");
			}
		}
	}
}
