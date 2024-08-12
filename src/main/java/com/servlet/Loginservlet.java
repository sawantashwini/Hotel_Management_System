package com.servlet;

import java.io.IOException;




import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.UserDto;
import com.service.UserService;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loginservlet() {
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
		//HttpSession session = request.getSession();
		//HttpSession session = request.getSession(true);
		HttpSession session = request.getSession();
		try {
			UserDto dto = new UserDto();
			UserService service = new UserService();

			dto.setMobile_no(request.getParameter("Mobile_no"));
			dto.setPassword(request.getParameter("Password"));
			
			int id = service.checkLogin(dto, config, request);

			if (id>0) {
				
				session.setAttribute("user_id", id); 


				response.sendRedirect("customer_birthday_anneversary.jsp?msg=YesLogin");
			} else {
				response.sendRedirect("login.jsp?msg=NoLogin");
			}

		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
		}

	}
}
