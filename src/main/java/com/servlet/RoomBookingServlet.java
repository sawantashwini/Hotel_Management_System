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
import com.dto.RoomBookingDto;
import com.service.LogFileService;
import com.service.RoomBookingService;

/**
 * Servlet implementation class RoomBookingServlet
 */
@WebServlet("/RoomBookingServlet")
public class RoomBookingServlet extends HttpServlet {
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
    public RoomBookingServlet() {
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
		PaymentDto pay_dto= new PaymentDto();
		RoomBookingDto dto = new RoomBookingDto();
		RoomBookingService ser = new RoomBookingService();

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
					
					//*****Integer Values******
					if (name.equals("Id"))
						dto.setId(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Cust_id_fk"))
						dto.setCust_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Room_id_fk"))
						dto.setRoom_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("User_id_fk"))
						dto.setUser_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));

					if (name.equals("Bank_id_fk"))
						dto.setAd_bank_id_fk(Integer.parseInt(item.getString() == null ? "0" : item.getString().trim()));

					//*****Float Values******
					if (name.equals("Room_rent"))
						dto.setRoom_rent(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Advanced_amt"))
						dto.setAdvanced_paid_amt(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Extra_bed_amt"))
						dto.setExtra_bed_amt(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Cash_amount"))
						dto.setAd_cash_amount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
					if (name.equals("Online_amount"))
						dto.setAd_online_amount(Float.parseFloat(item.getString() == null ? "0" : item.getString().trim()));
					
				
					//*****String Values******
					if (name.equals("Session_year"))
						dto.setSession_year(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Check_in_time"))
						dto.setCheck_in_time(LogFileService.formatDateTimeForSql(item.getString() == null ? "" : item.getString().trim()));

					if (name.equals("Extra_bed_days"))
						dto.setExtra_bed_days(LogFileService.formatDateTimeForSql(item.getString() == null ? "" : item.getString().trim()));

					if (name.equals("Check_out_time"))
						dto.setCheck_out_time(LogFileService.formatDateTimeForSql(item.getString() == null ? "" : item.getString().trim()));
					if (name.equals("Room_type"))
						dto.setRoom_type(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Cust_name"))
						dto.setCust_name(item.getString() == null ? "" : item.getString().trim());
					
					

					if (name.equals("Cust_mobile"))
						dto.setCust_mobile(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Cust_address"))
						dto.setCust_address(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Cust_gst_no"))
						dto.setCust_gst_no(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Company_name"))
						dto.setCompany_name(item.getString() == null ? "" : item.getString().trim());
					if (name.equals("Doa"))
						dto.setDoa(item.getString() == null ? "" : item.getString().trim());
					if (name.equals("Dob"))
						dto.setDob(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Source"))
						dto.setSource(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Destination"))
						dto.setDestination(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Extra_bed"))
						dto.setExtra_bed(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Room_no"))
						dto.setRoom_no(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Payment_mode"))  dto.setAd_payment_mode(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Regular")) dto.setRegular(item.getString() == null ? "" : item.getString().trim());
					
				
					if (name.equals("Online_date"))
						dto.setAd_online_date(item.getString() == null ? "" : item.getString().trim());

					if (name.equals("Online_remark"))
						dto.setAd_online_remark(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Online_way"))
						dto.setAd_online_way(item.getString() == null ? "" : item.getString().trim());
					
					if (name.equals("Book_remark"))
						dto.setBook_remark(item.getString() == null ? "" : item.getString().trim());
					
					
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
					if (name.equals("Check_in_time"))
						pay_dto.setIn_date(LogFileService.formatDateTimeForSql(item.getString() == null ? "" : item
								.getString().trim()));
					if (name.equals("Book_remark"))
						pay_dto.setRemark(item.getString() == null ? "" : item
								.getString().trim());
				

				} else {

					if (item.getSize() != 0) {
						if (item.getSize() < 300000000) {
							if (dto.getId() == 0) {

								int j = ser.maxId(request);
								savesFile = new File(path + "RoomBookingDocument/" + j + ".zip");
								/*
								 * System.out.println(path + "PropertyProjectDocument/" + j + ".zip");
								 * System.out.println("Check");
								 */
								try {
									item.write(savesFile);

									System.out.println(savesFile);
								} catch (Exception e2) {
								}

							} else {

								savesFile = new File(path + "RoomBookingDocument/" + dto.getId() + ".zip");
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
		//System.out.println("hiii");
		pay_dto.setType("Room Booking");
		pay_dto.setStatus("Active");
		

		if (dto.getId() == 0) {

			boolean b = ser.insertRoomBooking(pay_dto,dto, request, config);

			if (b) {

				response.sendRedirect("manage_room_booked.jsp?msg=Yes");
			} else {

				response.sendRedirect("manage_room_booked.jsp?msg=No");

			}
		} 
		else {

			boolean b = ser.updateRoomBooking(pay_dto,dto, request, config);

			if (b) {

				response.sendRedirect("manage_room_booked.jsp?msg=YesUp");
			} else {

				response.sendRedirect("manage_room_booked.jsp?msg=NoUp");

			}
		} 

	}
}


