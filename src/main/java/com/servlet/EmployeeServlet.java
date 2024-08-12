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

import com.dto.EmployeeDto;
import com.service.EmployeeService;

/**
 * Servlet implementation class SliderServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
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
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		EmployeeDto dto = new EmployeeDto();
		EmployeeService ser = new EmployeeService();
		
		
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

					if (name.equals("Id"))
						dto.setId(Integer.parseInt(item.getString() == null ? "0"
								: item.getString().trim()));
					
					if (name.equals("City_id_fk"))
						dto.setCity_id_fk(Integer.parseInt(item.getString() == null ? "0"
								: item.getString().trim()));
					

					if (name.equals("User_id_fk"))
						dto.setUser_id_fk(Integer.parseInt(item.getString() == null ? "0"
								: item.getString().trim()));
					
					/* int end */
					
					if (name.equals("Salary_per_month"))
						dto.setSalary_per_month(Float.parseFloat(item.getString() == null ? "0"
								: item.getString().trim()));
					
					
					
					
					/* float end */
					
					if (name.equals("Name"))
						dto.setName(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Mobile_no"))
						dto.setMobile_no(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Other_no"))
						dto.setOther_no(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Email_id"))
						dto.setEmail_id(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Address"))
						dto.setAddress(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Bank"))
						dto.setBank(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Account_no"))
						dto.setAccount_no(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Ifsc_code"))
						dto.setIfsc_code(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Experience"))
						dto.setExperience(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Qualification"))
						dto.setQualification(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Dob"))
						dto.setDob(item.getString() == null ? "0" : item
								.getString().trim());
					
					if (name.equals("Id_card_status"))
						dto.setId_card_status(item.getString() == null ? "" : item
								.getString().trim());
					
					
					if (name.equals("Photo_status"))
						dto.setPhoto_status(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Status"))
						dto.setStatus(item.getString() == null ? "Active" : item
								.getString().trim());
					
					if (name.equals("Guardian_name"))
						dto.setGuardian_name(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Join_date"))
						dto.setJoin_date(item.getString() == null ? "" : item
								.getString().trim());
					
					if (name.equals("Resign_date"))
						dto.setResign_date(item.getString() == null ? "" : item
								.getString().trim());
					
				} else {

					if (item.getSize() != 0) {
						if (item.getSize() < 3000000) {
							if (dto.getId() == 0) {

								int j = ser.maxId(request);
								savesFile = new File(path + "EmployeeImage/" + j
										+ ".jpg");

								try {
									item.write(savesFile);

									System.out.println(savesFile);
								} catch (Exception e2) {
								}

							} else {

								savesFile = new File(path + "EmployeeImage/"
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

		if (dto.getId() == 0) {

			int b = ser.insertEmployee(dto, request, config);

			if (b>0) {

				response.sendRedirect("add_employee.jsp?msg=Yes&id="+b);
			} else {

				response.sendRedirect("add_employee.jsp?msg=No");

			}
		}

		
		  else { boolean b = ser.UpdateEmployee(dto, request, config);
		  
		 if (b) {
		 
		 response.sendRedirect("manage_employee.jsp?msg=YesUp&alert_title=Employee"); 
		 } 
		 
		 else {
		 
		 response.sendRedirect("manage_employee.jsp?msg=NoUp&alert_title=Employee");
		 
		 } 
		 
		}
		

	}

}
