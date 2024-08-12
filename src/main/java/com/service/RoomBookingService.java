package com.service;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.PaymentDto;
import com.dto.RoomBookingDto;
import com.mysql.jdbc.Statement;

public class RoomBookingService {

	public String getMaxInvoiceNo(String s, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			PreparedStatement preparedStatement = null;

			preparedStatement = comm.connection.prepareStatement(
					"SELECT MAX(CAST(SUBSTRING(invoice_no, 1, LENGTH(invoice_no)-0) AS UNSIGNED)) FROM room_booked_tb");

			// LogFileService.PreparestatementLogFile(preparedStatement, config);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				return (resultSet.getString(1));

			}
		} catch (Exception e) {
			// LogFileService.catchLogFile(e, config); //
			// LogFileService.PreparestatementLogFile(PreparedStatement,
			e.printStackTrace(); // config);
		} finally {
			if (comm.connection != null)
				try {
					comm.connection.close();
				} catch (Exception e) {
					// LogFileService.catchLogFile(e, config); //
					// LogFileService.PreparestatementLogFile(PreparedStatement,
					// config);
				}
		}
		return "0";
	}

	public int maxId(HttpServletRequest request) {
		try {
			Connection connection = (Connection) new DataDb(request).connection;

			String dbname = connection.getCatalog();

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables "
							+ "WHERE table_name='room_booked_tb' AND TABLE_SCHEMA=?");
			preparedStatement.setString(1, dbname);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return 0;
	}

	PaymentService pay_ser = new PaymentService();

	// Insert Method for Room Booking
	public boolean insertRoomBooking(PaymentDto pay_dto, RoomBookingDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		int bill_id_fk = 0, new_cash_id = 0, new_online_id = 0;
		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO room_booked_tb ( room_type, extra_bed, cust_id_fk, cust_name, cust_mobile, cust_address, cust_gst_no, \r\n"
							+ "company_name, source, destination, room_id_fk, room_no, room_rent, advanced_paid_amt, check_in_time, \r\n"
							+ "check_out_time,  extra_bed_amt, user_id_fk, book_remark, ad_payment_mode,  \r\n"
							+ "ad_cash_amount, ad_online_amount, ad_online_date, ad_online_remark, ad_online_way,  \r\n"
							+ "ad_bank_id_fk, STATUS,extra_bed_days,dob,doa) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getRoom_type());
			ps.setString(2, dto.getExtra_bed());
			ps.setInt(3, dto.getCust_id_fk());
			ps.setString(4, dto.getCust_name());
			ps.setString(5, dto.getCust_mobile());
			ps.setString(6, dto.getCust_address());
			ps.setString(7, dto.getCust_gst_no());
			ps.setString(8, dto.getCompany_name());
			ps.setString(9, dto.getSource());
			ps.setString(10, dto.getDestination());
			ps.setInt(11, dto.getRoom_id_fk());
			ps.setString(12, dto.getRoom_no());
			ps.setFloat(13, dto.getRoom_rent());
			ps.setFloat(14, dto.getAd_cash_amount() + dto.getAd_online_amount());
			ps.setString(15, dto.getCheck_in_time());
			ps.setString(16, dto.getCheck_out_time());
			ps.setFloat(17, dto.getExtra_bed_amt());
			ps.setInt(18, dto.getUser_id_fk());
			ps.setString(19, dto.getBook_remark());
			ps.setString(20, dto.getAd_payment_mode());
			ps.setFloat(21, dto.getAd_cash_amount());
			ps.setFloat(22, dto.getAd_online_amount());
			ps.setString(23, dto.getAd_online_date());
			ps.setString(24, dto.getAd_online_remark());
			ps.setString(25, dto.getAd_online_way());
			ps.setInt(26, dto.getAd_bank_id_fk());
			ps.setString(27, "Booked");
			ps.setString(28, dto.getExtra_bed_days());
			ps.setString(29, dto.getDob());
			ps.setString(30, dto.getDoa());

			System.out.println(ps);

			int i = ps.executeUpdate();
			ResultSet rs_up = ps.getGeneratedKeys();
			rs_up.next();
			bill_id_fk = rs_up.getInt(1);

			dto.setId(bill_id_fk);
			pay_dto.setBill_id_fk(bill_id_fk);

			if (i != 0) {

				changeRoomStatus(dto.getRoom_id_fk(), "Booked", request, config);

				// *************** insert customer if new customer is new and
				// regular*****************
				if (dto.getRegular().equalsIgnoreCase("Yes") && dto.getCust_id_fk() == 0) {

					dto.setCust_id_fk(insertCustomerFromRoomBill(dto, request, config));
				}

				if (dto.getCust_id_fk() != 0) {

					// *************** insert billing acc info for new cust *****************
					PreparedStatement cust_acc = db.connection.prepareStatement(
							"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
									+ "c_y_session,debit_amount,credit_amount, \r\n"
									+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
									+ "online_date,payment_mode,pay_date \r\n" + ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
							Statement.RETURN_GENERATED_KEYS);

					cust_acc.setInt(1, dto.getCust_id_fk());
					cust_acc.setInt(2, dto.getUser_id_fk());
					cust_acc.setInt(3, dto.getId());
					cust_acc.setString(4, dto.getSession_year());
					cust_acc.setFloat(5, 0);// debit amount
					cust_acc.setFloat(6, dto.getAd_cash_amount() + dto.getAd_online_amount()); // credit
					cust_acc.setString(7, "Room Advance");
					cust_acc.setFloat(8, dto.getAd_cash_amount());
					cust_acc.setFloat(9, dto.getAd_online_amount());
					cust_acc.setString(10, dto.getAd_online_way());
					cust_acc.setString(11, dto.getAd_online_remark());
					cust_acc.setString(12, dto.getAd_online_date());
					cust_acc.setString(13, dto.getAd_payment_mode());
					cust_acc.setString(14, dto.getCheck_in_time());

					System.out.println(cust_acc);
					cust_acc.executeUpdate();
					ResultSet rs_cust_acc = cust_acc.getGeneratedKeys();
					rs_cust_acc.next();
					dto.setAd_customer_acc_id_fk(rs_cust_acc.getInt(1));
				}

				// *************** insert cash and payment tb*****************

				// ****** when Payment mode is both ********
				if (dto.getAd_payment_mode().equalsIgnoreCase("Both")) {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);
				}
				// ****** when Payment mode is online ********
				else if (dto.getAd_payment_mode().equalsIgnoreCase("Online")) {

					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when Payment mode is cash ********
				else {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				}

				dto.setAd_cash_payment_id_fk(new_cash_id);
				dto.setAd_online_payment_id_fk(new_online_id);

				// *************** update cash and payment id in customer_due_tb
				// *****************
				PreparedStatement update_bill = db.connection.prepareStatement("UPDATE room_booked_tb SET\n"
						+ "	ad_customer_acc_id_fk = ? ,\n" + "	ad_cash_payment_id_fk = ? ,"
						+ "	ad_online_payment_id_fk = ?" + "	WHERE id = ?;");
				update_bill.setInt(1, dto.getAd_customer_acc_id_fk());
				update_bill.setInt(2, dto.getAd_cash_payment_id_fk());
				update_bill.setInt(3, dto.getAd_online_payment_id_fk());
				update_bill.setInt(4, bill_id_fk);

				update_bill.executeUpdate();
				System.out.println(update_bill);

			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	// Insert Method for Room Booking
	public boolean updateRoomBooking(PaymentDto pay_dto, RoomBookingDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		int new_cash_id = 0, new_online_id = 0, old_customer_id_fk = 0,old_customer_account_id_fk=0;
		float old_final_amount = 0, old_ad_paid_amount = 0;
		String old_payment_mode = "";
		try {// *************** insert into payment tb*****************

			PreparedStatement select = db.connection.prepareStatement(
					"SELECT cust_id_fk, ad_customer_acc_id_fk, Ad_payment_mode, final_amt, advanced_paid_amt, "
							+ " Ad_cash_payment_id_fk , Ad_online_payment_id_fk FROM room_booked_tb WHERE id = ?;");

			select.setInt(1, dto.getId());
			System.out.println(select);

			ResultSet rs1 = select.executeQuery();

			while (rs1.next()) {

				old_customer_id_fk = rs1.getInt(1);
				old_customer_account_id_fk = rs1.getInt(2);
				old_payment_mode = rs1.getString(3);
				old_final_amount = rs1.getFloat(4);
				old_ad_paid_amount = rs1.getFloat(5);
				dto.setAd_cash_payment_id_fk(rs1.getInt(6));
				dto.setAd_online_payment_id_fk(rs1.getInt(7));

			}

			if (old_customer_id_fk > 0) {
				// clear customer old acc info
				PreparedStatement del_cust_acc_del = db.connection
						.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?;");

				del_cust_acc_del.setInt(1, old_customer_account_id_fk);
				System.out.println(del_cust_acc_del);
				del_cust_acc_del.executeUpdate();
				// update customer due info
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET old_due = old_due - ? WHERE id = ?;");

				cust_due.setFloat(1, old_final_amount - old_ad_paid_amount);
				cust_due.setInt(2, dto.getCust_id_fk());

				System.out.println(cust_due);
				cust_due.executeUpdate();

			}

			// *************** insert customer if new customer is new and
			// regular*****************
			if (dto.getCust_id_fk() == 0 && dto.getRegular().equalsIgnoreCase("Yes") ) {

				dto.setCust_id_fk(insertCustomerFromRoomBill(dto, request, config));
			}

			if (dto.getCust_id_fk() > 0) {

				// *************** insert billing acc info for new cust *****************
				PreparedStatement cust_acc = db.connection.prepareStatement(
						"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
								+ "c_y_session,debit_amount,credit_amount, \r\n"
								+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
								+ "online_date,payment_mode,pay_date \r\n" + ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				cust_acc.setInt(1, dto.getCust_id_fk());
				cust_acc.setInt(2, dto.getUser_id_fk());
				cust_acc.setInt(3, dto.getId());
				cust_acc.setString(4, dto.getSession_year());
				cust_acc.setFloat(5, 0); // debit amount
				cust_acc.setFloat(6, dto.getAd_cash_amount() + dto.getAd_online_amount()); // credit
				cust_acc.setString(7, "Booking");
				cust_acc.setFloat(8, dto.getAd_cash_amount());
				cust_acc.setFloat(9, dto.getAd_online_amount());
				cust_acc.setString(10, dto.getAd_online_way());
				cust_acc.setString(11, dto.getAd_online_remark());
				cust_acc.setString(12, dto.getAd_online_date());
				cust_acc.setString(13, dto.getAd_payment_mode());
				cust_acc.setString(14, dto.getCheck_in_time());

				System.out.println(cust_acc);
				cust_acc.executeUpdate();
				ResultSet rs_cust_acc = cust_acc.getGeneratedKeys();
				rs_cust_acc.next();
				dto.setAd_customer_acc_id_fk(rs_cust_acc.getInt(1));
			}

			// *************** insert into payment tb Start *****************

			// ****** when old Payment mode & New mode is same ********
			if (old_payment_mode.equalsIgnoreCase(dto.getAd_payment_mode())) {

				pay_ser.updateCashPayment(pay_dto, dto.getAd_cash_payment_id_fk(), request, config);
				pay_ser.updateOnlinePayment(pay_dto, dto.getAd_online_payment_id_fk(), request, config);

			}

			// ****** when old Payment mode & New mode is Different ********

			else {

				pay_ser.deleteCashPayment(dto.getAd_cash_payment_id_fk(), request, config);
				pay_ser.deleteOnlinePayment(dto.getAd_online_payment_id_fk(), request, config);

				// ****** when new Payment mode is both ********
				if (dto.getAd_payment_mode().equalsIgnoreCase("both")) {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is online ********
				else if (dto.getAd_payment_mode().equalsIgnoreCase("online")) {

					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is cash ********
				else {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				}

				dto.setAd_cash_payment_id_fk(new_cash_id);
				dto.setAd_online_payment_id_fk(new_online_id);

			}

			PreparedStatement ps = db.connection.prepareStatement(
					" UPDATE room_booked_tb SET room_type = ?, extra_bed  = ? , cust_id_fk = ?, cust_name = ?, cust_mobile = ?, cust_address = ?, cust_gst_no = ?,\r\n"
							+ " company_name = ?, source = ?, destination = ?, room_id_fk = ?, room_no = ?, room_rent = ?, advanced_paid_amt = ?, check_in_time = ?, \r\n"
							+ " check_out_time = ?,  extra_bed_amt = ?, user_id_fk = ?, book_remark = ?, ad_payment_mode = ?,  \r\n"
							+ " ad_cash_amount = ? , ad_online_amount = ? , ad_online_date = ? , ad_online_remark  = ?, ad_online_way  =? ,  \r\n"
							+ " ad_bank_id_fk  = ?  , ad_cash_payment_id_fk = ? , ad_online_payment_id_fk = ?, cust_id_fk = ?, cust_name = ?, cust_mobile = ?, "
							+ "	cust_address = ?, cust_gst_no = ?, company_name = ?, ad_customer_acc_id_fk = ?,extra_bed_days=?,dob=?,doa=?  WHERE id = ?;");

			ps.setString(1, dto.getRoom_type());
			ps.setString(2, dto.getExtra_bed());
			ps.setInt(3, dto.getCust_id_fk());
			ps.setString(4, dto.getCust_name());
			ps.setString(5, dto.getCust_mobile());
			ps.setString(6, dto.getCust_address());
			ps.setString(7, dto.getCust_gst_no());
			ps.setString(8, dto.getCompany_name());
			ps.setString(9, dto.getSource());
			ps.setString(10, dto.getDestination());
			ps.setInt(11, dto.getRoom_id_fk());
			ps.setString(12, dto.getRoom_no());
			ps.setFloat(13, dto.getRoom_rent());
			ps.setFloat(14, dto.getAd_cash_amount() + dto.getAd_online_amount());
			ps.setString(15, dto.getCheck_in_time());
			ps.setString(16, dto.getCheck_out_time());
			ps.setFloat(17, dto.getExtra_bed_amt());
			ps.setInt(18, dto.getUser_id_fk());
			ps.setString(19, dto.getBook_remark());
			ps.setString(20, dto.getAd_payment_mode());
			ps.setFloat(21, dto.getAd_cash_amount());
			ps.setFloat(22, dto.getAd_online_amount());
			ps.setString(23, dto.getAd_online_date());
			ps.setString(24, dto.getAd_online_remark());
			ps.setString(25, dto.getAd_online_way());
			ps.setInt(26, dto.getAd_bank_id_fk());
			ps.setInt(27, dto.getAd_cash_payment_id_fk());
			ps.setInt(28, dto.getAd_online_payment_id_fk());
			ps.setInt(29, dto.getCust_id_fk());
			ps.setString(30, dto.getCust_name());
			ps.setString(31, dto.getCust_mobile());
			ps.setString(32, dto.getCust_address());
			ps.setString(33, dto.getCust_gst_no());
			ps.setString(34, dto.getCompany_name());
			ps.setInt(35, dto.getAd_customer_acc_id_fk());
			ps.setString(36, dto.getExtra_bed_days());
			ps.setString(37, dto.getDob());
			ps.setString(38, dto.getDoa());
			ps.setInt(39, dto.getId());

			int rowsAffected = ps.executeUpdate();
			System.out.println(ps);
			if (rowsAffected != 0) {

				return true;
			}

			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	

	// Method for Show data on Manage page
	public ArrayList<RoomBookingDto> getAllBookedRoomInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomBookingDto> list = new ArrayList<RoomBookingDto>();

		try {

			preparedStatement = db.connection
					.prepareStatement("SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
							+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
							+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
							+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
							+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
							+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
							+ "ad_customer_acc_id_fk, extra_bed_amt, food_amt\r\n" + "FROM room_booked_tb\r\n"
							+ "WHERE STATUS = 'booked';");

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomBookingDto dto = new RoomBookingDto();

				dto.setId(resultSet.getInt("id"));
				dto.setRoom_type(resultSet.getString("room_type"));
				dto.setExtra_bed(resultSet.getString("extra_bed"));
				dto.setCust_id_fk(resultSet.getInt("cust_id_fk"));
				dto.setCust_name(resultSet.getString("cust_name"));
				dto.setCust_mobile(resultSet.getString("cust_mobile"));
				dto.setCust_address(resultSet.getString("cust_address"));
				dto.setCust_gst_no(resultSet.getString("cust_gst_no"));
				dto.setCompany_name(resultSet.getString("company_name"));
				dto.setSource(resultSet.getString("source"));
				dto.setDestination(resultSet.getString("destination"));
				dto.setRoom_id_fk(resultSet.getInt("room_id_fk"));
				dto.setRoom_no(resultSet.getString("room_no"));
				dto.setRoom_rent(resultSet.getFloat("room_rent"));
				dto.setAdvanced_paid_amt(resultSet.getFloat("advanced_paid_amt"));
				dto.setCheck_in_time(resultSet.getString("check_in_time"));
				dto.setCheck_out_time(resultSet.getString("check_out_time"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setBook_remark(resultSet.getString("book_remark"));
				dto.setAd_payment_mode(resultSet.getString("ad_payment_mode"));
				dto.setAd_cash_amount(resultSet.getFloat("ad_cash_amount"));
				dto.setAd_online_amount(resultSet.getFloat("ad_online_amount"));
				dto.setAd_online_date(resultSet.getString("ad_online_date"));
				dto.setAd_online_remark(resultSet.getString("ad_online_remark"));
				dto.setAd_online_way(resultSet.getString("ad_online_way"));
				dto.setAd_bank_id_fk(resultSet.getInt("ad_bank_id_fk"));
				dto.setAd_cash_payment_id_fk(resultSet.getInt("ad_cash_payment_id_fk"));
				dto.setAd_online_payment_id_fk(resultSet.getInt("ad_online_payment_id_fk"));
				dto.setAd_customer_acc_id_fk(resultSet.getInt("ad_customer_acc_id_fk"));
				dto.setExtra_bed_amt(resultSet.getFloat("extra_bed_amt"));
				dto.setFood_amt(resultSet.getFloat("food_amt"));

				list.add(dto);
			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// Method for Show data on Manage page
	public ArrayList<RoomBookingDto> getAllCustomer(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomBookingDto> list = new ArrayList<RoomBookingDto>();

		try {
		
				preparedStatement = db.connection
						.prepareStatement(" SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
								+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
								+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
								+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
								+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
								+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
								+ "ad_customer_acc_id_fk, \r\n"
								+ "invoice_no,no_of_days,total_room_rent,extra_bed_amt,total_extra_bed_amt,room_gst_per,\r\n"
								+ "total_room_amt_with_gst,food_amt,food_gst_per,net_amt,dis_amt_room,\r\n"
								+ "cust_old_due,final_amt,bill_paid_amt,bill_return_amt,bill_remark,\r\n"
								+ "bill_payment_mode,bill_cash_amount,bill_online_amount,bill_online_date,bill_online_remark,\r\n"
								+ "bill_online_way,bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
								+ "FROM room_booked_tb\r\n" + "WHERE STATUS = 'complete';");
			
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomBookingDto dto = new RoomBookingDto();

				dto.setId(resultSet.getInt("id"));
				dto.setRoom_type(resultSet.getString("room_type"));
				dto.setExtra_bed(resultSet.getString("extra_bed"));
				dto.setCust_id_fk(resultSet.getInt("cust_id_fk"));
				dto.setCust_name(resultSet.getString("cust_name"));
				dto.setCust_mobile(resultSet.getString("cust_mobile"));
				dto.setCust_address(resultSet.getString("cust_address"));
				dto.setCust_gst_no(resultSet.getString("cust_gst_no"));
				dto.setCompany_name(resultSet.getString("company_name"));
				dto.setSource(resultSet.getString("source"));
				dto.setDestination(resultSet.getString("destination"));
				dto.setRoom_id_fk(resultSet.getInt("room_id_fk"));
				dto.setRoom_no(resultSet.getString("room_no"));
				dto.setRoom_rent(resultSet.getFloat("room_rent"));
				dto.setAdvanced_paid_amt(resultSet.getFloat("advanced_paid_amt"));
				dto.setCheck_in_time(resultSet.getString("check_in_time"));
				dto.setCheck_out_time(resultSet.getString("check_out_time"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setBook_remark(resultSet.getString("book_remark"));
				dto.setAd_payment_mode(resultSet.getString("ad_payment_mode"));
				dto.setAd_cash_amount(resultSet.getFloat("ad_cash_amount"));
				dto.setAd_online_amount(resultSet.getFloat("ad_online_amount"));
				dto.setAd_online_date(resultSet.getString("ad_online_date"));
				dto.setAd_online_remark(resultSet.getString("ad_online_remark"));
				dto.setAd_online_way(resultSet.getString("ad_online_way"));
				dto.setAd_bank_id_fk(resultSet.getInt("ad_bank_id_fk"));
				dto.setAd_cash_payment_id_fk(resultSet.getInt("ad_cash_payment_id_fk"));
				dto.setAd_online_payment_id_fk(resultSet.getInt("ad_online_payment_id_fk"));
				dto.setAd_customer_acc_id_fk(resultSet.getInt("ad_customer_acc_id_fk"));
				dto.setInvoice_no(resultSet.getString("invoice_no"));
				dto.setNo_of_days(resultSet.getInt("no_of_days"));
				dto.setTotal_room_rent(resultSet.getFloat("total_room_rent"));
				dto.setExtra_bed_amt(resultSet.getFloat("extra_bed_amt"));
				dto.setTotal_extra_bed_amt(resultSet.getFloat("total_extra_bed_amt"));
				dto.setRoom_gst_per(resultSet.getFloat("room_gst_per"));
				dto.setTotal_room_amt_with_gst(resultSet.getFloat("total_room_amt_with_gst"));
				dto.setFood_amt(resultSet.getFloat("food_amt"));
				dto.setFood_gst_per(resultSet.getFloat("food_gst_per"));
				dto.setNet_amt(resultSet.getFloat("net_amt"));
				dto.setDis_amt_room(resultSet.getFloat("dis_amt_room"));
				dto.setCust_old_due(resultSet.getFloat("cust_old_due"));
				dto.setFinal_amt(resultSet.getFloat("final_amt"));
				dto.setBill_paid_amt(resultSet.getFloat("bill_paid_amt"));
				dto.setBill_return_amt(resultSet.getFloat("bill_return_amt"));
				dto.setBill_remark(resultSet.getString("bill_remark"));
				dto.setBill_payment_mode(resultSet.getString("bill_payment_mode"));
				dto.setBill_cash_amount(resultSet.getFloat("bill_cash_amount"));
				dto.setBill_online_amount(resultSet.getFloat("bill_online_amount"));
				dto.setBill_online_date(resultSet.getString("bill_online_date"));
				dto.setBill_online_remark(resultSet.getString("bill_online_remark"));
				dto.setBill_online_way(resultSet.getString("bill_online_way"));
				dto.setBill_bank_id_fk(resultSet.getInt("bill_bank_id_fk"));
				dto.setBill_customer_acc_id_fk(resultSet.getInt("bill_customer_acc_id_fk"));
				dto.setBill_cash_payment_id_fk(resultSet.getInt("bill_cash_payment_id_fk"));
				dto.setBill_online_payment_id_fk(resultSet.getInt("bill_online_payment_id_fk"));

				list.add(dto);
			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}
	// Method for Show data on Manage page
	public ArrayList<RoomBookingDto> getAllRoomBills(String time1, String time2,String cust_name,ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomBookingDto> list = new ArrayList<RoomBookingDto>();

		try {
			if (!time1.equals("") && !time2.equals("") && !cust_name.equals("")) {
			preparedStatement = db.connection
					.prepareStatement(" SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
							+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
							+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
							+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
							+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
							+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
							+ "ad_customer_acc_id_fk, \r\n"
							+ "invoice_no,no_of_days,total_room_rent,extra_bed_amt,total_extra_bed_amt,room_gst_per,\r\n"
							+ "total_room_amt_with_gst,food_amt,food_gst_per,net_amt,dis_amt_room,\r\n"
							+ "cust_old_due,final_amt,bill_paid_amt,bill_return_amt,bill_remark,\r\n"
							+ "bill_payment_mode,bill_cash_amount,bill_online_amount,bill_online_date,bill_online_remark,\r\n"
							+ "bill_online_way,bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
							+ "FROM room_booked_tb\r\n" + "WHERE STATUS = 'complete' AND check_out_time BETWEEN ? AND ? AND cust_name=?;");
			preparedStatement.setString(1, time1);
			preparedStatement.setString(2, time2);
			preparedStatement.setString(3, cust_name);
			}else if (time1.equals("") && time2.equals("") && !cust_name.equals("")) {
				preparedStatement = db.connection
						.prepareStatement(" SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
								+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
								+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
								+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
								+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
								+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
								+ "ad_customer_acc_id_fk, \r\n"
								+ "invoice_no,no_of_days,total_room_rent,extra_bed_amt,total_extra_bed_amt,room_gst_per,\r\n"
								+ "total_room_amt_with_gst,food_amt,food_gst_per,net_amt,dis_amt_room,\r\n"
								+ "cust_old_due,final_amt,bill_paid_amt,bill_return_amt,bill_remark,\r\n"
								+ "bill_payment_mode,bill_cash_amount,bill_online_amount,bill_online_date,bill_online_remark,\r\n"
								+ "bill_online_way,bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
								+ "FROM room_booked_tb\r\n" + "WHERE STATUS = 'complete'  AND cust_name=?;");
			
				preparedStatement.setString(1, cust_name);
			}else if (!time1.equals("") && !time2.equals("") && cust_name.equals("")) {
					preparedStatement = db.connection
							.prepareStatement(" SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
									+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
									+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
									+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
									+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
									+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
									+ "ad_customer_acc_id_fk, \r\n"
									+ "invoice_no,no_of_days,total_room_rent,extra_bed_amt,total_extra_bed_amt,room_gst_per,\r\n"
									+ "total_room_amt_with_gst,food_amt,food_gst_per,net_amt,dis_amt_room,\r\n"
									+ "cust_old_due,final_amt,bill_paid_amt,bill_return_amt,bill_remark,\r\n"
									+ "bill_payment_mode,bill_cash_amount,bill_online_amount,bill_online_date,bill_online_remark,\r\n"
									+ "bill_online_way,bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
									+ "FROM room_booked_tb\r\n" + "WHERE STATUS = 'complete' AND check_out_time BETWEEN ? AND ?");
					preparedStatement.setString(1, time1);
					preparedStatement.setString(2, time2);
					
					}else {
				preparedStatement = db.connection
						.prepareStatement(" SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
								+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
								+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
								+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
								+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
								+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
								+ "ad_customer_acc_id_fk, \r\n"
								+ "invoice_no,no_of_days,total_room_rent,extra_bed_amt,total_extra_bed_amt,room_gst_per,\r\n"
								+ "total_room_amt_with_gst,food_amt,food_gst_per,net_amt,dis_amt_room,\r\n"
								+ "cust_old_due,final_amt,bill_paid_amt,bill_return_amt,bill_remark,\r\n"
								+ "bill_payment_mode,bill_cash_amount,bill_online_amount,bill_online_date,bill_online_remark,\r\n"
								+ "bill_online_way,bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
								+ "FROM room_booked_tb\r\n" + "WHERE STATUS = 'complete';");
			}
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomBookingDto dto = new RoomBookingDto();

				dto.setId(resultSet.getInt("id"));
				dto.setRoom_type(resultSet.getString("room_type"));
				dto.setExtra_bed(resultSet.getString("extra_bed"));
				dto.setCust_id_fk(resultSet.getInt("cust_id_fk"));
				dto.setCust_name(resultSet.getString("cust_name"));
				dto.setCust_mobile(resultSet.getString("cust_mobile"));
				dto.setCust_address(resultSet.getString("cust_address"));
				dto.setCust_gst_no(resultSet.getString("cust_gst_no"));
				dto.setCompany_name(resultSet.getString("company_name"));
				dto.setSource(resultSet.getString("source"));
				dto.setDestination(resultSet.getString("destination"));
				dto.setRoom_id_fk(resultSet.getInt("room_id_fk"));
				dto.setRoom_no(resultSet.getString("room_no"));
				dto.setRoom_rent(resultSet.getFloat("room_rent"));
				dto.setAdvanced_paid_amt(resultSet.getFloat("advanced_paid_amt"));
				dto.setCheck_in_time(resultSet.getString("check_in_time"));
				dto.setCheck_out_time(resultSet.getString("check_out_time"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setBook_remark(resultSet.getString("book_remark"));
				dto.setAd_payment_mode(resultSet.getString("ad_payment_mode"));
				dto.setAd_cash_amount(resultSet.getFloat("ad_cash_amount"));
				dto.setAd_online_amount(resultSet.getFloat("ad_online_amount"));
				dto.setAd_online_date(resultSet.getString("ad_online_date"));
				dto.setAd_online_remark(resultSet.getString("ad_online_remark"));
				dto.setAd_online_way(resultSet.getString("ad_online_way"));
				dto.setAd_bank_id_fk(resultSet.getInt("ad_bank_id_fk"));
				dto.setAd_cash_payment_id_fk(resultSet.getInt("ad_cash_payment_id_fk"));
				dto.setAd_online_payment_id_fk(resultSet.getInt("ad_online_payment_id_fk"));
				dto.setAd_customer_acc_id_fk(resultSet.getInt("ad_customer_acc_id_fk"));
				dto.setInvoice_no(resultSet.getString("invoice_no"));
				dto.setNo_of_days(resultSet.getInt("no_of_days"));
				dto.setTotal_room_rent(resultSet.getFloat("total_room_rent"));
				dto.setExtra_bed_amt(resultSet.getFloat("extra_bed_amt"));
				dto.setTotal_extra_bed_amt(resultSet.getFloat("total_extra_bed_amt"));
				dto.setRoom_gst_per(resultSet.getFloat("room_gst_per"));
				dto.setTotal_room_amt_with_gst(resultSet.getFloat("total_room_amt_with_gst"));
				dto.setFood_amt(resultSet.getFloat("food_amt"));
				dto.setFood_gst_per(resultSet.getFloat("food_gst_per"));
				dto.setNet_amt(resultSet.getFloat("net_amt"));
				dto.setDis_amt_room(resultSet.getFloat("dis_amt_room"));
				dto.setCust_old_due(resultSet.getFloat("cust_old_due"));
				dto.setFinal_amt(resultSet.getFloat("final_amt"));
				dto.setBill_paid_amt(resultSet.getFloat("bill_paid_amt"));
				dto.setBill_return_amt(resultSet.getFloat("bill_return_amt"));
				dto.setBill_remark(resultSet.getString("bill_remark"));
				dto.setBill_payment_mode(resultSet.getString("bill_payment_mode"));
				dto.setBill_cash_amount(resultSet.getFloat("bill_cash_amount"));
				dto.setBill_online_amount(resultSet.getFloat("bill_online_amount"));
				dto.setBill_online_date(resultSet.getString("bill_online_date"));
				dto.setBill_online_remark(resultSet.getString("bill_online_remark"));
				dto.setBill_online_way(resultSet.getString("bill_online_way"));
				dto.setBill_bank_id_fk(resultSet.getInt("bill_bank_id_fk"));
				dto.setBill_customer_acc_id_fk(resultSet.getInt("bill_customer_acc_id_fk"));
				dto.setBill_cash_payment_id_fk(resultSet.getInt("bill_cash_payment_id_fk"));
				dto.setBill_online_payment_id_fk(resultSet.getInt("bill_online_payment_id_fk"));

				list.add(dto);
			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// Method for Show data on Manage page
	public ArrayList<RoomBookingDto> getRoomBillInfoById(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomBookingDto> list = new ArrayList<RoomBookingDto>();

		try {

			preparedStatement = db.connection
					.prepareStatement(" SELECT id, room_type, extra_bed, cust_id_fk, cust_name,\r\n"
							+ "cust_mobile, cust_address, cust_gst_no, company_name, source,\r\n"
							+ "destination, room_id_fk, room_no, room_rent, advanced_paid_amt,\r\n"
							+ "check_in_time, check_out_time, current_in_date, STATUS, user_id_fk,\r\n"
							+ "book_remark, ad_payment_mode, ad_cash_amount, ad_online_amount, ad_online_date,\r\n"
							+ "ad_online_remark, ad_online_way, ad_bank_id_fk, ad_cash_payment_id_fk, ad_online_payment_id_fk,\r\n"
							+ "ad_customer_acc_id_fk, \r\n"
							+ "invoice_no,no_of_days,total_room_rent,extra_bed_amt,room_gst_per,\r\n"
							+ "total_room_amt_with_gst,food_amt,food_gst_per,net_amt,dis_amt,\r\n"
							+ "cust_old_due,final_amt,bill_paid_amt,bill_return_amt,bill_remark,\r\n"
							+ "bill_payment_mode,bill_cash_amount,bill_online_amount,bill_online_date,bill_online_remark,\r\n"
							+ "bill_online_way,bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
							+ "FROM room_booked_tb\r\n" + "WHERE id = ?;");

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomBookingDto dto = new RoomBookingDto();

				dto.setId(resultSet.getInt("id"));
				dto.setRoom_type(resultSet.getString("room_type"));
				dto.setExtra_bed(resultSet.getString("extra_bed"));
				dto.setCust_id_fk(resultSet.getInt("cust_id_fk"));
				dto.setCust_name(resultSet.getString("cust_name"));
				dto.setCust_mobile(resultSet.getString("cust_mobile"));
				dto.setCust_address(resultSet.getString("cust_address"));
				dto.setCust_gst_no(resultSet.getString("cust_gst_no"));
				dto.setCompany_name(resultSet.getString("company_name"));
				dto.setSource(resultSet.getString("source"));
				dto.setDestination(resultSet.getString("destination"));
				dto.setRoom_id_fk(resultSet.getInt("room_id_fk"));
				dto.setRoom_no(resultSet.getString("room_no"));
				dto.setRoom_rent(resultSet.getFloat("room_rent"));
				dto.setAdvanced_paid_amt(resultSet.getFloat("advanced_paid_amt"));
				dto.setCheck_in_time(resultSet.getString("check_in_time"));
				dto.setCheck_out_time(resultSet.getString("check_out_time"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setBook_remark(resultSet.getString("book_remark"));
				dto.setAd_payment_mode(resultSet.getString("ad_payment_mode"));
				dto.setAd_cash_amount(resultSet.getFloat("ad_cash_amount"));
				dto.setAd_online_amount(resultSet.getFloat("ad_online_amount"));
				dto.setAd_online_date(resultSet.getString("ad_online_date"));
				dto.setAd_online_remark(resultSet.getString("ad_online_remark"));
				dto.setAd_online_way(resultSet.getString("ad_online_way"));
				dto.setAd_bank_id_fk(resultSet.getInt("ad_bank_id_fk"));
				dto.setAd_cash_payment_id_fk(resultSet.getInt("ad_cash_payment_id_fk"));
				dto.setAd_online_payment_id_fk(resultSet.getInt("ad_online_payment_id_fk"));
				dto.setAd_customer_acc_id_fk(resultSet.getInt("ad_customer_acc_id_fk"));

				dto.setInvoice_no(resultSet.getString("invoice_no"));
				dto.setNo_of_days(resultSet.getInt("no_of_days"));
				dto.setTotal_room_rent(resultSet.getFloat("total_room_rent"));
				dto.setExtra_bed_amt(resultSet.getFloat("extra_bed_amt"));
				dto.setRoom_gst_per(resultSet.getFloat("room_gst_per"));
				dto.setTotal_room_amt_with_gst(resultSet.getFloat("total_room_amt_with_gst"));
				dto.setFood_amt(resultSet.getFloat("food_amt"));
				dto.setFood_gst_per(resultSet.getFloat("food_gst_per"));
				dto.setNet_amt(resultSet.getFloat("net_amt"));
				dto.setDis_amt_room(resultSet.getFloat("dis_amt_room"));
				dto.setCust_old_due(resultSet.getFloat("cust_old_due"));
				dto.setFinal_amt(resultSet.getFloat("final_amt"));
				dto.setBill_paid_amt(resultSet.getFloat("bill_paid_amt"));
				dto.setBill_return_amt(resultSet.getFloat("bill_return_amt"));
				dto.setBill_remark(resultSet.getString("bill_remark"));
				dto.setBill_payment_mode(resultSet.getString("bill_payment_mode"));
				dto.setBill_cash_amount(resultSet.getFloat("bill_cash_amount"));
				dto.setBill_online_amount(resultSet.getFloat("bill_online_amount"));
				dto.setBill_online_date(resultSet.getString("bill_online_date"));
				dto.setBill_online_remark(resultSet.getString("bill_online_remark"));
				dto.setBill_online_way(resultSet.getString("bill_online_way"));
				dto.setBill_bank_id_fk(resultSet.getInt("bill_bank_id_fk"));
				dto.setBill_customer_acc_id_fk(resultSet.getInt("bill_customer_acc_id_fk"));
				dto.setBill_cash_payment_id_fk(resultSet.getInt("bill_cash_payment_id_fk"));
				dto.setBill_online_payment_id_fk(resultSet.getInt("bill_online_payment_id_fk"));

				list.add(dto);
			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}

	// Method for Show data on Room Billing page
	public RoomBookingDto getBookedRoomInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement selectPS = null;

		RoomBookingDto dto = new RoomBookingDto();

		try {

			selectPS = db.connection.prepareStatement(
					" SELECT rb.id, rb.room_type, rb.extra_bed,  rb.cust_id_fk, rb.cust_name,rb.cust_mobile, rb.cust_address, rb.cust_gst_no, rb.company_name, rb.source, rb.destination, \r\n"
					+ "rb.room_id_fk, rb.room_no, rb.room_rent, rb.advanced_paid_amt, rb.check_in_time, rb.check_out_time, rb.current_in_date, rb.STATUS, rb.user_id_fk,\r\n"
					+ "rb.book_remark, rb.ad_payment_mode, rb.ad_cash_amount, rb.ad_online_amount, rb.ad_online_date, rb.ad_online_remark, rb.ad_online_way, rb.ad_bank_id_fk, rb.ad_cash_payment_id_fk, rb.ad_online_payment_id_fk,\r\n"
					+ "rb.ad_customer_acc_id_fk, rb.invoice_no, rb.no_of_days, rb.total_room_rent, rb.extra_bed_amt,rb.total_extra_bed_amt, rb.room_gst_per, rb.total_room_amt_with_gst, rb.food_amt, rb.food_gst_per, rb.net_amt,rb.dis_amt_room,rb.dis_amt_food, \r\n"
					+ "rb.cust_old_due, rb.final_amt, rb.bill_paid_amt, rb.bill_return_amt,  rb.bill_remark, rb.bill_payment_mode, rb.bill_cash_amount, rb.bill_online_amount, rb.bill_online_date, \r\n"
					+ "rb.bill_online_remark, rb.bill_online_way, rb.bill_bank_id_fk, rb.bill_customer_acc_id_fk, rb.bill_cash_payment_id_fk,\r\n"
					+ "rb.bill_online_payment_id_fk, ci.old_due, ci.id, abt.name, bbt.name,rt.room_name,rb.extra_bed_days,rb.no_of_bed_days,rb.dob,rb.doa  FROM room_booked_tb rb \r\n"
					+ "LEFT JOIN customer_info_tb ci ON ci.id = rb.cust_id_fk\r\n"
					+ " LEFT JOIN bank_tb abt ON abt.id = rb.ad_bank_id_fk\r\n"
					+ " LEFT JOIN room_tb rt ON rt.id = rb.room_id_fk    \r\n"
					+ " LEFT JOIN bank_tb bbt ON bbt.id = rb.bill_bank_id_fk WHERE rb.id = ?;\r\n"
					+ "");
			selectPS.setInt(1, id);
			System.out.println(selectPS);

			ResultSet resultSet = selectPS.executeQuery();

			if (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setRoom_type(resultSet.getString(2));
				dto.setExtra_bed(resultSet.getString(3));
				dto.setCust_id_fk(resultSet.getInt(4));
				dto.setCust_name(resultSet.getString(5));
				dto.setCust_mobile(resultSet.getString(6));
				dto.setCust_address(resultSet.getString(7));
				dto.setCust_gst_no(resultSet.getString(8));
				dto.setCompany_name(resultSet.getString(9));
				dto.setSource(resultSet.getString(10));
				dto.setDestination(resultSet.getString(11));
				dto.setRoom_id_fk(resultSet.getInt(12));
				dto.setRoom_no(resultSet.getString(13));
				dto.setRoom_rent(resultSet.getFloat(14));
				dto.setAdvanced_paid_amt(resultSet.getFloat(15));
				dto.setCheck_in_time(resultSet.getString(16));
				dto.setCheck_out_time(resultSet.getString(17));
				dto.setCurrent_in_date(resultSet.getString(18));
				dto.setStatus(resultSet.getString(19));
				dto.setUser_id_fk(resultSet.getInt(20));
				dto.setBook_remark(resultSet.getString(21));
				dto.setAd_payment_mode(resultSet.getString(22));
				dto.setAd_cash_amount(resultSet.getFloat(23));
				dto.setAd_online_amount(resultSet.getFloat(24));
				dto.setAd_online_date(resultSet.getString(25));
				dto.setAd_online_remark(resultSet.getString(26));
				dto.setAd_online_way(resultSet.getString(27));
				dto.setAd_bank_id_fk(resultSet.getInt(28));
				dto.setAd_cash_payment_id_fk(resultSet.getInt(29));
				dto.setAd_online_payment_id_fk(resultSet.getInt(30));
				dto.setAd_customer_acc_id_fk(resultSet.getInt(31));
				dto.setInvoice_no(resultSet.getString(32));
				dto.setNo_of_days(resultSet.getInt(33));
				dto.setTotal_room_rent(resultSet.getFloat(34));
				dto.setExtra_bed_amt(resultSet.getFloat(35));
				dto.setTotal_extra_bed_amt(resultSet.getFloat(36));
				dto.setRoom_gst_per(resultSet.getFloat(37));
				dto.setTotal_room_amt_with_gst(resultSet.getFloat(38));
				dto.setFood_amt(resultSet.getFloat(39));
				dto.setFood_gst_per(resultSet.getFloat(40));
				dto.setNet_amt(resultSet.getFloat(41));
				dto.setDis_amt_room(resultSet.getFloat(42));
				dto.setDis_amt_food(resultSet.getFloat(43));
				dto.setBill_time_old_due(resultSet.getFloat(44));
				dto.setFinal_amt(resultSet.getFloat(45));
				dto.setBill_paid_amt(resultSet.getFloat(46));
				dto.setBill_return_amt(resultSet.getFloat(47));
				dto.setBill_remark(resultSet.getString(48));
				dto.setBill_payment_mode(resultSet.getString(49));
				dto.setBill_cash_amount(resultSet.getFloat(50));
				dto.setBill_online_amount(resultSet.getFloat(51));
				dto.setBill_online_date(resultSet.getString(52));
				dto.setBill_online_remark(resultSet.getString(53));
				dto.setBill_online_way(resultSet.getString(54));
				dto.setBill_bank_id_fk(resultSet.getInt(55));
				dto.setBill_customer_acc_id_fk(resultSet.getInt(56));
				dto.setBill_cash_payment_id_fk(resultSet.getInt(57));
				dto.setBill_online_payment_id_fk(resultSet.getInt(58));
				dto.setCust_old_due(resultSet.getFloat(59));
				dto.setCust_id_fk(resultSet.getInt(60));
				dto.setAd_bank_name(resultSet.getString(61));
				dto.setBill_bank_name(resultSet.getString(62));
				dto.setRoom_name(resultSet.getString(63));
				dto.setExtra_bed_days(resultSet.getString(64));
				dto.setNo_of_bed_days(resultSet.getFloat(65));
				dto.setDob(resultSet.getString(66));
				dto.setDoa(resultSet.getString(67));
			}

		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}

		// System.out.println(dto.getFood_amt());
		return dto;
	}

	// Insert Method for Customer From order Bill
	public int insertCustomerFromRoomBill(RoomBookingDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		String status = "Block";

		if (dto.getRegular().equalsIgnoreCase("Yes")) {
			status = "Active";
		}

		try {

			// Insert Query of Customer

			String sql = "INSERT INTO customer_info_tb (Name, address, mobile_no, user_id_fk, gst_no,company_name,status,dob,doa) VALUES(?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getCust_name());
			ps.setString(2, dto.getCust_address());
			ps.setString(3, dto.getCust_mobile());
			ps.setInt(4, dto.getUser_id_fk());
			ps.setString(5, dto.getCust_gst_no());
			ps.setString(6, dto.getCompany_name());
			ps.setString(7, status);
			ps.setString(8, dto.getDob());
			ps.setString(9, dto.getDoa());

			
			int i = ps.executeUpdate();
			System.out.println(ps);
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int customer_id_fk = rs.getInt(1);

			if (i != 0) {

				// insert customer acc table info
				PreparedStatement cust_acc = db.connection.prepareStatement("INSERT INTO customer_account_tb \r\n"
						+ "(customer_id_fk, user_id_fk, c_y_session,debit_amount,credit_amount, \r\n"
						+ "TYPE, pay_date)VALUES(?,?,?,?,?,?,?);");

				cust_acc.setInt(1, customer_id_fk);
				cust_acc.setInt(2, dto.getUser_id_fk());
				cust_acc.setString(3, dto.getSession_year());
				cust_acc.setFloat(4, 0);
				cust_acc.setFloat(5, 0);
				cust_acc.setString(6, "Opening Due");
				cust_acc.setString(7, dto.getCheck_in_time());
				System.out.println(cust_acc);

				cust_acc.executeUpdate();

				return customer_id_fk;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	public boolean changeRoomStatus(int room_id, String status, HttpServletRequest request, ServletConfig config)
			throws IOException {
		DataDb comm = new DataDb(request);

		try {

			String sql = "UPDATE room_tb SET STATUS = ? WHERE id = ?;";
			PreparedStatement updatedps = comm.connection.prepareStatement(sql);

			updatedps.setString(1, status);
			updatedps.setInt(2, room_id);
			System.out.println(updatedps);
			int updatedRows = updatedps.executeUpdate();

			if (updatedRows > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (comm.connection != null) {
				try {
					comm.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	// Insert Method for Room Booking
	public boolean completeRoomBilling(PaymentDto pay_dto, RoomBookingDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb db = new DataDb(request);
		int new_cash_id = 0, new_online_id = 0;
		try {

			// *************** insert customer if new customer is new and
			// regular*****************
			if (dto.getRegular().equalsIgnoreCase("Yes") && dto.getCust_id_fk() == 0) {

				dto.setCust_id_fk(insertCustomerFromRoomBill(dto, request, config));

				// *************** select advanced acc payment info *****************

				PreparedStatement selectPS = db.connection.prepareStatement(""
						+ "SELECT 	ad_payment_mode,ad_cash_amount,ad_online_amount, \r\n"
						+ "ad_online_date,ad_online_remark,ad_online_way\r\n" + "FROM room_booked_tb WHERE id = ?;");
				selectPS.setInt(1, dto.getId());
				System.out.println(selectPS);

				ResultSet resultSet = selectPS.executeQuery();
				if (resultSet.next()) {
					dto.setAd_payment_mode(resultSet.getString("ad_payment_mode"));
					dto.setAd_cash_amount(resultSet.getFloat("ad_cash_amount"));
					dto.setAd_online_amount(resultSet.getFloat("ad_online_amount"));
					dto.setAd_online_date(resultSet.getString("ad_online_date"));
					dto.setAd_online_remark(resultSet.getString("ad_online_remark"));
					dto.setAd_online_way(resultSet.getString("ad_online_way"));
				}

				// *************** insert advanced acc payment info for new cust
				// *****************
				PreparedStatement cust_ad_acc = db.connection.prepareStatement(
						"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
								+ "c_y_session,debit_amount,credit_amount, \r\n"
								+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
								+ "online_date,payment_mode,pay_date \r\n" + ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				cust_ad_acc.setInt(1, dto.getCust_id_fk());
				cust_ad_acc.setInt(2, dto.getUser_id_fk());
				cust_ad_acc.setInt(3, dto.getId());
				cust_ad_acc.setString(4, dto.getSession_year());
				cust_ad_acc.setFloat(5, 0);// debit amount
				cust_ad_acc.setFloat(6, dto.getAd_cash_amount() + dto.getAd_online_amount()); // credit
				cust_ad_acc.setString(7, "Room Advance");
				cust_ad_acc.setFloat(8, dto.getAd_cash_amount());
				cust_ad_acc.setFloat(9, dto.getAd_online_amount());
				cust_ad_acc.setString(10, dto.getAd_online_way());
				cust_ad_acc.setString(11, dto.getAd_online_remark());
				cust_ad_acc.setString(12, dto.getAd_online_date());
				cust_ad_acc.setString(13, dto.getAd_payment_mode());
				cust_ad_acc.setString(14, dto.getCheck_in_time());

				System.out.println(cust_ad_acc);
				cust_ad_acc.executeUpdate();

				ResultSet rs_cust_ad_acc = cust_ad_acc.getGeneratedKeys();
				rs_cust_ad_acc.next();
				dto.setAd_customer_acc_id_fk(rs_cust_ad_acc.getInt(1));
			}

			// *************** insert into payment tb*****************

			// ****** when Payment mode is both ********
			if (dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);
			}
			// ****** when Payment mode is online ********
			else if (dto.getBill_payment_mode().equalsIgnoreCase("Online")) {

				new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

			}
			// ****** when Payment mode is cash ********
			else {
				new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
			}

			dto.setBill_cash_payment_id_fk(new_cash_id);
			dto.setBill_online_payment_id_fk(new_online_id);
			// *************** insert into payment tb END *****************

			if (dto.getCust_id_fk() != 0) {

				// *************** insert billing acc info for new cust *****************
				PreparedStatement cust_acc = db.connection.prepareStatement(
						"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
								+ "c_y_session,debit_amount,credit_amount, \r\n"
								+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
								+ "online_date,payment_mode,pay_date \r\n" + ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				cust_acc.setInt(1, dto.getCust_id_fk());
				cust_acc.setInt(2, dto.getUser_id_fk());
				cust_acc.setInt(3, dto.getId());
				cust_acc.setString(4, dto.getSession_year());
				cust_acc.setFloat(5, 0);// debit amount
				cust_acc.setFloat(6, dto.getBill_cash_amount() + dto.getBill_online_amount()); // credit
				cust_acc.setString(7, "Room Billing");
				cust_acc.setFloat(8, dto.getBill_cash_amount());
				cust_acc.setFloat(9, dto.getBill_online_amount());
				cust_acc.setString(10, dto.getBill_online_way());
				cust_acc.setString(11, dto.getBill_online_remark());
				cust_acc.setString(12, dto.getBill_online_date());
				cust_acc.setString(13, dto.getBill_payment_mode());
				cust_acc.setString(14, dto.getCheck_out_time());

				System.out.println(cust_acc);
				cust_acc.executeUpdate();
				ResultSet rs_cust_acc = cust_acc.getGeneratedKeys();
				rs_cust_acc.next();
				dto.setBill_customer_acc_id_fk(rs_cust_acc.getInt(1));

				// *************** update customer due info *****************
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET \n" + "	old_due = old_due + ? WHERE id = ?;");
				cust_due.setFloat(1, dto.getFinal_amt() - (dto.getBill_cash_amount() + dto.getBill_online_amount()));
				cust_due.setInt(2, dto.getCust_id_fk());
				System.out.println(cust_due);
				cust_due.executeUpdate();
			}

			// *************** complete customer billing *****************
			PreparedStatement updatePs = db.connection.prepareStatement("UPDATE room_booked_tb SET\r\n"
					+ "    check_out_time = ?, STATUS = ?, invoice_no = ?, no_of_days = ?,\r\n"
					+ "    total_room_rent = ?, extra_bed_amt = ?,total_extra_bed_amt = ?, room_gst_per = ?, total_room_amt_with_gst = ?,\r\n"
					+ "    bill_return_amt = ?, food_gst_per = ?, net_amt = ?, dis_amt_room = ?,dis_amt_food = ?,\r\n"
					+ "    cust_old_due = ?, final_amt = ?, bill_paid_amt = ?, bill_remark = ?,\r\n"
					+ "    bill_payment_mode = ?, bill_cash_amount = ?, bill_online_amount = ?, bill_online_date = ?,\r\n"
					+ "    bill_online_remark = ?, bill_online_way = ?, bill_bank_id_fk = ? , bill_customer_acc_id_fk = ?,\r\n"
					+ "    bill_cash_payment_id_fk = ?, bill_online_payment_id_fk = ?,cust_id_fk = ?, cust_name = ?, cust_mobile = ?,\r\n"
					+ "	   cust_address = ?, cust_gst_no = ?, company_name = ?, ad_customer_acc_id_fk = ?,extra_bed_days=?,no_of_bed_days=?,dob=?,doa=? \r\n"
					+ "    WHERE id = ?;");

			updatePs.setString(1, dto.getCheck_out_time());
			updatePs.setString(2, "Complete");
			updatePs.setString(3, dto.getInvoice_no());
			updatePs.setFloat(4, dto.getNo_of_days());
			updatePs.setFloat(5, dto.getTotal_room_rent());
			updatePs.setFloat(6, dto.getExtra_bed_amt());
			updatePs.setFloat(7, dto.getTotal_extra_bed_amt());
			updatePs.setFloat(8, dto.getRoom_gst_per());
			updatePs.setFloat(9, dto.getTotal_room_amt_with_gst());
			updatePs.setFloat(10, dto.getBill_return_amt());
			updatePs.setFloat(11, dto.getFood_gst_per());
			updatePs.setFloat(12, dto.getNet_amt());
			updatePs.setFloat(13, dto.getDis_amt_room());
			updatePs.setFloat(14, dto.getDis_amt_food());
			updatePs.setFloat(15, dto.getCust_old_due());
			updatePs.setFloat(16, dto.getFinal_amt());
			updatePs.setFloat(17, dto.getBill_cash_amount() + dto.getBill_online_amount());
			updatePs.setString(18, dto.getBill_remark());
			updatePs.setString(19, dto.getBill_payment_mode());
			updatePs.setFloat(20, dto.getBill_cash_amount());
			updatePs.setFloat(21, dto.getBill_online_amount());
			updatePs.setString(22, dto.getBill_online_date());
			updatePs.setString(23, dto.getBill_online_remark());
			updatePs.setString(24, dto.getBill_online_way());
			updatePs.setInt(25, dto.getBill_bank_id_fk());
			updatePs.setInt(26, dto.getBill_customer_acc_id_fk());
			updatePs.setInt(27, dto.getBill_cash_payment_id_fk());
			updatePs.setInt(28, dto.getBill_online_payment_id_fk());
			updatePs.setInt(29, dto.getCust_id_fk());
			updatePs.setString(30, dto.getCust_name());
			updatePs.setString(31, dto.getCust_mobile());
			updatePs.setString(32, dto.getCust_address());
			updatePs.setString(33, dto.getCust_gst_no());
			updatePs.setString(34, dto.getCompany_name());
			updatePs.setInt(35, dto.getAd_customer_acc_id_fk());
			updatePs.setString(36, dto.getExtra_bed_days());
			updatePs.setFloat(37, dto.getNo_of_bed_days());
			updatePs.setString(38, dto.getDob());
			updatePs.setString(39, dto.getDoa());
			updatePs.setInt(40, dto.getId());

			// Execute the update statement
			int rowsAffected = updatePs.executeUpdate();
			System.out.println(updatePs);
			if (rowsAffected != 0) {

				// *************** change room status *****************
				changeRoomStatus(dto.getRoom_id_fk(), "Available", request, config);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Insert Method for Room Booking
	public boolean updateRoomBilling(PaymentDto pay_dto, RoomBookingDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb db = new DataDb(request);
		int new_cash_id = 0, new_online_id = 0, old_customer_id_fk = 0;
		float old_final_amount = 0, old_paid_amount = 0;
		String old_payment_mode = "";
		try {
			// *************** insert into payment tb*****************

			PreparedStatement select = db.connection.prepareStatement(
					"SELECT cust_id_fk,ad_customer_acc_id_fk,bill_customer_acc_id_fk,bill_payment_mode,final_amt,\r\n"
							+ "bill_paid_amt,bill_cash_payment_id_fk,bill_online_payment_id_fk\r\n"
							+ "FROM room_booked_tb WHERE id = ?;");

			select.setInt(1, dto.getId());
			System.out.println(select);

			ResultSet rs1 = select.executeQuery();

			while (rs1.next()) {

				old_customer_id_fk = rs1.getInt(1);
				dto.setAd_customer_acc_id_fk(rs1.getInt(2));
				dto.setBill_customer_acc_id_fk(rs1.getInt(3));
				old_payment_mode = rs1.getString(4);
				old_final_amount = rs1.getFloat(5);
				old_paid_amount = rs1.getFloat(6);
				dto.setBill_cash_payment_id_fk(rs1.getInt(7));
				dto.setBill_online_payment_id_fk(rs1.getInt(8));

			}

			if (old_customer_id_fk != 0) {

				// update customer due info
				PreparedStatement del_bill_acc_del = db.connection
						.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?;");

				del_bill_acc_del.setInt(1, dto.getBill_customer_acc_id_fk());
				System.out.println(del_bill_acc_del);
				del_bill_acc_del.executeUpdate();

				// update customer due info
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET \n" + "	old_due = old_due - ? WHERE id = ?;");

				cust_due.setFloat(1, old_final_amount - old_paid_amount);
				cust_due.setInt(2, dto.getCust_id_fk());

				System.out.println(cust_due);
				cust_due.executeUpdate();

			}

			// *************** insert customer if new customer is new and
			// regular*****************
			if (dto.getRegular().equalsIgnoreCase("Yes") && dto.getCust_id_fk() == 0) {

				dto.setCust_id_fk(insertCustomerFromRoomBill(dto, request, config));

				// *************** select advanced acc payment info *****************

				PreparedStatement selectPS = db.connection
						.prepareStatement("SELECT 	ad_payment_mode,ad_cash_amount,ad_online_amount, \r\n"
								+ "ad_online_date,ad_online_remark,ad_online_way\r\n"
								+ "FROM room_booked_tb WHERE id = ?;");
				selectPS.setInt(1, dto.getId());
				System.out.println(selectPS);

				ResultSet resultSet = selectPS.executeQuery();
				if (resultSet.next()) {
					dto.setAd_payment_mode(resultSet.getString("ad_payment_mode"));
					dto.setAd_cash_amount(resultSet.getFloat("ad_cash_amount"));
					dto.setAd_online_amount(resultSet.getFloat("ad_online_amount"));
					dto.setAd_online_date(resultSet.getString("ad_online_date"));
					dto.setAd_online_remark(resultSet.getString("ad_online_remark"));
					dto.setAd_online_way(resultSet.getString("ad_online_way"));
				}

				// *************** insert advanced acc payment info for new cust
				// *****************
				PreparedStatement cust_ad_acc = db.connection.prepareStatement(
						"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
								+ "c_y_session,debit_amount,credit_amount, \r\n"
								+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
								+ "online_date,payment_mode,pay_date \r\n" + ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				cust_ad_acc.setInt(1, dto.getCust_id_fk());
				cust_ad_acc.setInt(2, dto.getUser_id_fk());
				cust_ad_acc.setInt(3, dto.getId());
				cust_ad_acc.setString(4, dto.getSession_year());
				cust_ad_acc.setFloat(5, 0);// debit amount
				cust_ad_acc.setFloat(6, dto.getAd_cash_amount() + dto.getAd_online_amount()); // credit
				cust_ad_acc.setString(7, "Room Advance");
				cust_ad_acc.setFloat(8, dto.getAd_cash_amount());
				cust_ad_acc.setFloat(9, dto.getAd_online_amount());
				cust_ad_acc.setString(10, dto.getAd_online_way());
				cust_ad_acc.setString(11, dto.getAd_online_remark());
				cust_ad_acc.setString(12, dto.getAd_online_date());
				cust_ad_acc.setString(13, dto.getAd_payment_mode());
				cust_ad_acc.setString(14, dto.getCheck_in_time());

				System.out.println(cust_ad_acc);
				cust_ad_acc.executeUpdate();

				ResultSet rs_cust_ad_acc = cust_ad_acc.getGeneratedKeys();
				rs_cust_ad_acc.next();
				dto.setAd_customer_acc_id_fk(rs_cust_ad_acc.getInt(1));

			}

			if (dto.getCust_id_fk() != 0) {
				// *************** update customer due info *****************
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET \n" + "	old_due = old_due + ? WHERE id = ?;");
				cust_due.setFloat(1, dto.getFinal_amt() - (dto.getBill_cash_amount() + dto.getBill_online_amount()));
				cust_due.setInt(2, dto.getCust_id_fk());
				System.out.println(cust_due);
				cust_due.executeUpdate();

				// *************** insert billing acc info for new cust *****************
				PreparedStatement cust_acc = db.connection.prepareStatement(
						"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
								+ "c_y_session,debit_amount,credit_amount, \r\n"
								+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
								+ "online_date,payment_mode,pay_date \r\n" + ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				cust_acc.setInt(1, dto.getCust_id_fk());
				cust_acc.setInt(2, dto.getUser_id_fk());
				cust_acc.setInt(3, dto.getId());
				cust_acc.setString(4, dto.getSession_year());
				cust_acc.setFloat(5, 0); // debit amount
				cust_acc.setFloat(6, dto.getBill_cash_amount() + dto.getBill_online_amount()); // credit
				cust_acc.setString(7, "Room Billing");
				cust_acc.setFloat(8, dto.getBill_cash_amount());
				cust_acc.setFloat(9, dto.getBill_online_amount());
				cust_acc.setString(10, dto.getBill_online_way());
				cust_acc.setString(11, dto.getBill_online_remark());
				cust_acc.setString(12, dto.getBill_online_date());
				cust_acc.setString(13, dto.getBill_payment_mode());
				cust_acc.setString(14, dto.getCheck_out_time());

				System.out.println(cust_acc);
				cust_acc.executeUpdate();
				ResultSet rs_cust_acc = cust_acc.getGeneratedKeys();
				rs_cust_acc.next();
				dto.setBill_customer_acc_id_fk(rs_cust_acc.getInt(1));
			}

			// *************** insert into payment tb Start *****************

			// ****** when old Payment mode & New mode is same ********
			if (old_payment_mode.equalsIgnoreCase(dto.getBill_payment_mode())) {

				pay_ser.updateCashPayment(pay_dto, dto.getBill_cash_payment_id_fk(), request, config);
				pay_ser.updateOnlinePayment(pay_dto, dto.getBill_online_payment_id_fk(), request, config);

			}

			// ****** when old Payment mode & New mode is Different ********

			else {

				pay_ser.deleteCashPayment(dto.getBill_cash_payment_id_fk(), request, config);
				pay_ser.deleteOnlinePayment(dto.getBill_online_payment_id_fk(), request, config);

				// ****** when new Payment mode is both ********
				if (dto.getBill_payment_mode().equalsIgnoreCase("both")) {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is online ********
				else if (dto.getBill_payment_mode().equalsIgnoreCase("online")) {

					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is cash ********
				else {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				}

				dto.setBill_cash_payment_id_fk(new_cash_id);
				dto.setBill_online_payment_id_fk(new_online_id);

			}

			// *************** insert into payment tb END *****************

			// *************** complete customer billing *****************
			PreparedStatement updatePs = db.connection.prepareStatement("UPDATE room_booked_tb SET\r\n"
					+ "    check_out_time = ?, STATUS = ?, invoice_no = ?, no_of_days = ?,\r\n"
					+ "    total_room_rent = ?, extra_bed_amt = ?,total_extra_bed_amt = ?, room_gst_per = ?, total_room_amt_with_gst = ?,\r\n"
					+ "    bill_return_amt = ?, food_gst_per = ?, net_amt = ?, dis_amt_room = ?,dis_amt_food = ?,\r\n"
					+ "    cust_old_due = ?, final_amt = ?, bill_paid_amt = ?, bill_remark = ?,\r\n"
					+ "    bill_payment_mode = ?, bill_cash_amount = ?, bill_online_amount = ?, bill_online_date = ?,\r\n"
					+ "    bill_online_remark = ?, bill_online_way = ?, bill_bank_id_fk = ? , bill_customer_acc_id_fk = ?,\r\n"
					+ "    bill_cash_payment_id_fk = ?, bill_online_payment_id_fk = ?,cust_id_fk = ?, cust_name = ?, cust_mobile = ?,\r\n"
					+ "	   cust_address = ?, cust_gst_no = ?, company_name = ?, ad_customer_acc_id_fk = ?,extra_bed_days=?,no_of_bed_days=?,dob=?,doa=? \r\n"
					+ "    WHERE id = ?;");

		
			
			updatePs.setString(1, dto.getCheck_out_time());
			updatePs.setString(2, "Complete");
			updatePs.setString(3, dto.getInvoice_no());
			updatePs.setFloat(4, dto.getNo_of_days());
			updatePs.setFloat(5, dto.getTotal_room_rent());
			updatePs.setFloat(6, dto.getExtra_bed_amt());
			updatePs.setFloat(7, dto.getTotal_extra_bed_amt());
			updatePs.setFloat(8, dto.getRoom_gst_per());
			updatePs.setFloat(9, dto.getTotal_room_amt_with_gst());
			updatePs.setFloat(10, dto.getBill_return_amt());
			updatePs.setFloat(11, dto.getFood_gst_per());
			updatePs.setFloat(12, dto.getNet_amt());
			updatePs.setFloat(13, dto.getDis_amt_room());
			updatePs.setFloat(14, dto.getDis_amt_food());
			updatePs.setFloat(15, dto.getCust_old_due());
			updatePs.setFloat(16, dto.getFinal_amt());
			updatePs.setFloat(17, dto.getBill_cash_amount() + dto.getBill_online_amount());
			updatePs.setString(18, dto.getBill_remark());
			updatePs.setString(19, dto.getBill_payment_mode());
			updatePs.setFloat(20, dto.getBill_cash_amount());
			updatePs.setFloat(21, dto.getBill_online_amount());
			updatePs.setString(22, dto.getBill_online_date());
			updatePs.setString(23, dto.getBill_online_remark());
			updatePs.setString(24, dto.getBill_online_way());
			updatePs.setInt(25, dto.getBill_bank_id_fk());
			updatePs.setInt(26, dto.getBill_customer_acc_id_fk());
			updatePs.setInt(27, dto.getBill_cash_payment_id_fk());
			updatePs.setInt(28, dto.getBill_online_payment_id_fk());
			updatePs.setInt(29, dto.getCust_id_fk());
			updatePs.setString(30, dto.getCust_name());
			updatePs.setString(31, dto.getCust_mobile());
			updatePs.setString(32, dto.getCust_address());
			updatePs.setString(33, dto.getCust_gst_no());
			updatePs.setString(34, dto.getCompany_name());
			updatePs.setInt(35, dto.getAd_customer_acc_id_fk());
			updatePs.setString(36, dto.getExtra_bed_days());
			updatePs.setFloat(37, dto.getNo_of_bed_days());
			updatePs.setString(38, dto.getDob());
			updatePs.setString(39, dto.getDoa());

			updatePs.setInt(40, dto.getId());


			// Execute the update statement
			int rowsAffected = updatePs.executeUpdate();
			System.out.println(updatePs);
			if (rowsAffected != 0) {

				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
