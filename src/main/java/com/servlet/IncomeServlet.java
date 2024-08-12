package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dto.PaymentDto;
import com.dto.ProjectDto;
import com.dto.IncomeDto;
import com.service.ProjectService;
import com.service.IncomeService;

/**
 * Servlet implementation class IncomeServlet
 */
@WebServlet("/IncomeServlet")
public class IncomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletConfig config;
	String Name = "";
	String path = "";

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;

		path = config.getServletContext().getRealPath("/");
		System.out.println(path);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IncomeServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		IncomeDto dto = new IncomeDto();
		PaymentDto pay_dto = new PaymentDto();
		IncomeService ser = new IncomeService();
		File savesFile = null;
		FileItem item = null;

		if (ServletFileUpload.isMultipartContent(request)) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = null;
			try {

				items = upload.parseRequest(request);

			} catch (Exception e) {
			}

			Iterator<FileItem> itr = items.iterator();

			while (itr.hasNext()) {
				item = itr.next();

				if (item.isFormField()) {

					String name = item.getFieldName();
					//Integer
					if (name.equals("Id"))dto.setId(Integer.parseInt(item.getString() == null ? "0": item.getString().trim()));

					if (name.equals("Head_id_fk"))dto.setHead_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Bank_id_fk"))dto.setBank_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("User_id_fk"))dto.setUser_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));
					
					//float
					if (name.equals("Amount"))dto.setAmount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Cash_amount"))dto.setCash_amount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Online_amount"))dto.setOnline_amount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					//String
					if (name.equals("Current_in_date"))
						dto.setCurrent_in_date(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("In_date"))
						dto.setIn_date(item.getString() == null ? "0"
								: item.getString().trim());
					if (name.equals("Remark"))
						dto.setRemark(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Payment_mode"))
						dto.setPayment_mode(item.getString() == null ? "0"
								: item.getString().trim());
					
					if (name.equals("Status"))
						dto.setStatus(item.getString() == null ? "Active" : item
								.getString().trim());

					if (name.equals("Online_remark"))
						dto.setOnline_remark(item.getString() == null ? "N/A" : item
								.getString().trim());
					
					if (name.equals("Online_date"))
						dto.setOnline_date(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Income_name"))
						dto.setIncome_name(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Online_way"))
						dto.setOnline_way(item.getString() == null ? "" : item
								.getString().trim());
					//************PAYMENT DTO VALUES***********
					// Integer
					if (name.equals("Bank_id_fk"))pay_dto.setBank_id_fk(Integer.parseInt(item.getString() == null ? "0": item.getString().trim()));
					
					if (name.equals("Id"))pay_dto.setBill_id_fk(Integer.parseInt(item.getString() == null ? "0": item.getString().trim()));
					
					//Float
					if (name.equals("Online_amount"))pay_dto.setOnline_amount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Cash_amount"))pay_dto.setCash_amount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
						
					// String
					if (name.equals("Online_way"))
						pay_dto.setOnline_way(item.getString() == null ? "" : item
								.getString().trim());
					if (name.equals("Online_date"))
						pay_dto.setOnline_date(item.getString() == null ? "" : item
								.getString().trim());
					if (name.equals("Online_remark"))
						pay_dto.setOnline_remark(item.getString() == null ? "" : item
								.getString().trim());
					if (name.equals("Status"))
						pay_dto.setStatus(item.getString() == null ? "Active" : item
								.getString().trim());
					if (name.equals("In_date"))
						pay_dto.setIn_date(item.getString() == null ? "" : item
								.getString().trim());
					if (name.equals("Remark"))
						pay_dto.setRemark(item.getString() == null ? "" : item
								.getString().trim());
					if (name.equals("Type"))pay_dto.setType("Income");
				} else {

					if (item.getSize() != 0) {
						if (item.getSize() < 3000000) {
							if (dto.getId() == 0) {

								int j = ser.maxId(request);
								savesFile = new File(path + "IncomeImage/" + j
										+ ".jpg");

								try {
									item.write(savesFile);

									System.out.println(savesFile);
								} catch (Exception e2) {
								}

							} else {

								savesFile = new File(path + "IncomeImage/"
										+ dto.getId() + ".jpg");
								try {
									item.write(savesFile);

								} catch (Exception e2) {
								}
							}

						}

					}

				}
			}
		}
		
		int b = 0;

		boolean c = false;

		// condition for insert
		if (dto.getId() == 0) {

			b = ser.insertIncomeInfo(dto,pay_dto, request, config);

			if (b > 0) {
				response.sendRedirect("add_income.jsp?msg=Yes");
			} else {
				response.sendRedirect("add_Income.jsp?msg=No");
			}
		}
		// condition for updating
		else {
			
			

			c = ser.updateIncomeInfo(dto,pay_dto, request, config);

			if (c) {
				response.sendRedirect("manage_income.jsp?msg=YesUp");
			} else {
				response.sendRedirect("manage_income.jsp?msg=NoUp");
			}
		}
	
	

	}

}
