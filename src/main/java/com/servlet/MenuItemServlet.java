package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MenuItemDto;
import com.service.MenuItemService;

/**
 * Servlet implementation class MenuItemServlet
 */
@WebServlet("/MenuItemServlet")
public class MenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuItemServlet() {
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
		MenuItemDto dto = new MenuItemDto();
		MenuItemService ser = new MenuItemService();

		dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

		dto.setUser_id_fk(Integer
				.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));

		dto.setLiqour_cat_id_fk(Integer.parseInt(
				request.getParameter("Liqour_cat_id_fk") == null ? "0" : request.getParameter("Liqour_cat_id_fk")));

		// string
		dto.setItem_code(request.getParameter("Item_code") == null ? "" : request.getParameter("Item_code"));

		dto.setItem_name(request.getParameter("Item_name") == null ? "" : request.getParameter("Item_name"));

		dto.setStatus(request.getParameter("Status") == null ? "active" : request.getParameter("Status"));

		String type = request.getParameter("Items");

		// Float
		dto.setItem_price(Float.parseFloat(
				request.getParameter("Item_price") == null || request.getParameter("Item_price") == "" ? "0.0"
						: request.getParameter("Item_price")));

		dto.setLiqour_ind_qty(Float.parseFloat(
				request.getParameter("Liqour_ind_qty") == null || request.getParameter("Liqour_ind_qty") == "" ? "0.0"
						: request.getParameter("Liqour_ind_qty")));

		boolean b = false;

		// condition for insert
		if (dto.getId() == 0) {
			b = ser.insertLiquorItem(dto, request, config);

			if (type.equals("Liquor")) {

				if (b) {
					response.sendRedirect("add_liquor_item.jsp?msg=YesUp");
				} else {
					response.sendRedirect("add_liquor_item.jsp?msg=NoUp");
				}
			} else if (type.equals("Menu"))  {
				if (b) {
					response.sendRedirect("add_menu_item.jsp?msg=Yes");
				} else {
					response.sendRedirect("add_menu_item.jsp?msg=No");
				}
			}

		}
		// condition for updating
		else {

			

			if (type.equals("Liquor")) {
				b = ser.updateMenuItem(dto, request, config);

				if (b) {
					response.sendRedirect("manage_liquor_item.jsp?msg=YesUp");
				} else {
					response.sendRedirect("manage_liquor_item.jsp?msg=NoUp");
				}
			} else if (type.equals("Menu"))  {
				b = ser.updateLiquorItem(dto, request, config);
				if (b) {
					response.sendRedirect("manage_menu_item.jsp?msg=YesUp");
				} else {
					response.sendRedirect("manage_menu_item.jsp?msg=NoUp");
				}
			}
		}

	}

}
