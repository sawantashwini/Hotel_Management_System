package com.service;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.CustomerDto;
import com.dto.RoomBookingDto;
import com.mysql.jdbc.Statement;

public class CustomerService {
	
	
	// Method for Show data on Manage page
		public ArrayList<RoomBookingDto> getBillInfoByCust(int cust_id, String time1, String time2, ServletConfig config,
				HttpServletRequest request) throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<RoomBookingDto> list = new ArrayList<RoomBookingDto>();

			try {
				
				String select_sql =" SELECT rt.id, rt.cust_id_fk, rt.current_in_date, rt.status, rt.user_id_fk,"
						+ " rt.room_type , rt.extra_bed, rt.source, rt.destination, rt.room_id_fk, rt.room_no, rt.room_rent, "
						+ " rt.advanced_paid_amt, rt.check_in_time, rt.check_out_time, rt.invoice_no, rt.no_of_days, rt.total_room_rent, "
						+ " rt.extra_bed_amt, rt.room_gst_per, rt.total_room_amt_with_gst, rt.food_amt, rt.food_gst_per, rt.net_amt, rt.dis_amt_room, \r\n"
						+ " rt.cust_old_due, rt.final_amt, rt.bill_paid_amt, rt.bill_remark, rt.bill_payment_mode, rt.bill_cash_amount, \r\n"
						+ " rt.bill_online_amount, rt.bill_online_date, rt.bill_online_remark, rt.bill_online_way, rt.bill_bank_id_fk, \r\n"
						+ " rt.bill_cash_payment_id_fk, rt.bill_online_payment_id_fk, rt.bill_customer_acc_id_fk, c.name, c.mobile_no,\r\n"
						+ " c.address, c.gst_no , bt.name FROM room_booked_tb rt INNER JOIN user_personal_info_tb up ON up.id=rt.user_id_fk \r\n"
						+ " INNER JOIN customer_info_tb c ON c.id=rt.cust_id_fk LEFT JOIN bank_tb bt ON bt.id= rt.bill_bank_id_fk \r\n";

				
				
				if(!time1.equalsIgnoreCase("")&& !time2.equalsIgnoreCase("")) {
					preparedStatement = db.connection.prepareStatement(select_sql
							+ " WHERE rt.cust_id_fk = ? AND rt.current_in_date BETWEEN ? AND ?;");

					preparedStatement.setInt(1, cust_id);
					preparedStatement.setString(2, time1);
					preparedStatement.setString(3, time2);
				}else {
					
					preparedStatement = db.connection.prepareStatement(select_sql
							+ " WHERE rt.cust_id_fk = ?;");

					preparedStatement.setInt(1, cust_id);
					
				}
				System.out.println(preparedStatement);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					RoomBookingDto dto = new RoomBookingDto();

					dto.setId(resultSet.getInt(1));
					dto.setCust_id_fk(resultSet.getInt(2));
					dto.setCurrent_in_date(resultSet.getString(3));
					dto.setStatus(resultSet.getString(4));
					dto.setUser_id_fk(resultSet.getInt(5));
					dto.setRoom_type(resultSet.getString(6));
					dto.setExtra_bed(resultSet.getString(7));
					dto.setSource(resultSet.getString(8));
					dto.setDestination(resultSet.getString(9));
					dto.setRoom_id_fk(resultSet.getInt(10));
					dto.setRoom_no(resultSet.getString(11));
					dto.setRoom_rent(resultSet.getFloat(12));
					dto.setAdvanced_paid_amt(resultSet.getFloat(13));
					dto.setCheck_in_time(resultSet.getString(14));
					dto.setCheck_out_time(resultSet.getString(15));
					dto.setInvoice_no(resultSet.getString(16));
					dto.setNo_of_days(resultSet.getInt(17));
					dto.setTotal_room_rent(resultSet.getFloat(18));
					dto.setExtra_bed_amt(resultSet.getFloat(19));
					dto.setRoom_gst_per(resultSet.getFloat(20));
					dto.setTotal_room_amt_with_gst(resultSet.getFloat(21));
					dto.setFood_amt(resultSet.getFloat(22));
					dto.setFood_gst_per(resultSet.getFloat(23));
					dto.setNet_amt(resultSet.getFloat(24));
					dto.setDis_amt_room(resultSet.getFloat(25));
					dto.setCust_old_due(resultSet.getFloat(26));
					dto.setFinal_amt(resultSet.getFloat(27));
					dto.setBill_paid_amt(resultSet.getFloat(28));
					dto.setBill_remark(resultSet.getString(29));
					dto.setBill_payment_mode(resultSet.getString(30));
					dto.setBill_cash_amount(resultSet.getFloat(31));
					dto.setBill_online_amount(resultSet.getFloat(32));
					dto.setBill_online_date(resultSet.getString(33));
					dto.setBill_online_remark(resultSet.getString(34));
					dto.setBill_online_way(resultSet.getString(35));
					dto.setBill_bank_id_fk(resultSet.getInt(36));
					dto.setBill_cash_payment_id_fk(resultSet.getInt(37));
					dto.setBill_online_payment_id_fk(resultSet.getInt(38));
					dto.setBill_customer_acc_id_fk(resultSet.getInt(39));
					
					dto.setCust_name(resultSet.getString(40));
					dto.setCust_mobile(resultSet.getString(41));
					dto.setCust_address(resultSet.getString(42));
					dto.setCust_gst_no(resultSet.getString(43));
					dto.setBill_bank_name(resultSet.getString(44));

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

	// Insert Method for Customer Item
	public int insertCustomer(CustomerDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		int customer_id_fk = 0;

		try {

			// Insert Query of PathologyItem

			String sql = "INSERT INTO customer_info_tb (Name, address, mobile_no, opening_due,old_due, user_id_fk, gst_no,company_name,dob,doa) VALUES(?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getAddress());
			ps.setString(3, dto.getMobile_no());
			ps.setFloat(4, dto.getOpening_due());
			ps.setFloat(5, dto.getOpening_due());
			ps.setInt(6, dto.getUser_id_fk());
			ps.setString(7, dto.getGst_no());
			ps.setString(8, dto.getCompany_name());
			ps.setString(9, dto.getDob());
			ps.setString(10, dto.getDoa());
			System.out.println(ps);
			int i = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			customer_id_fk = rs.getInt(1);

			if (i != 0) {

				// insert customer acc table info
				PreparedStatement cust_acc = db.connection.prepareStatement("INSERT INTO customer_account_tb \r\n"
						+ "(customer_id_fk,user_id_fk, \r\n" + "c_y_session,debit_amount,credit_amount, \r\n"
						+ "TYPE,pay_date,STATUS \r\n" + ")VALUES(?,?,?,?,?,?,?,?);");

				cust_acc.setInt(1, customer_id_fk);
				cust_acc.setInt(2, dto.getUser_id_fk());
				cust_acc.setString(3, dto.getC_y_session());
				cust_acc.setFloat(4, dto.getOpening_due());
				cust_acc.setFloat(5, 0);
				cust_acc.setString(6, "Opening Due");
				cust_acc.setString(7, dto.getIn_date());
				cust_acc.setString(8, dto.getStatus());

				System.out.println(cust_acc);

				int i2 = cust_acc.executeUpdate();

				if (i2 != 0) {

					return customer_id_fk;

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}

	// Insert Method for Customer From sell Bill
	

	// Method for Show data on Manage page
	public ArrayList<CustomerDto> getCustomerInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();

		try {

			String sql = "SELECT id,Name, address, mobile_no, old_due, opening_due, user_id_fk, current_in_date, gst_no,Status,upcoming_date,upcoming_remark,company_name,dob,doa FROM customer_info_tb GROUP BY mobile_no;";
			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {

				CustomerDto dto = new CustomerDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAddress(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setOld_due(resultSet.getFloat(5));
				dto.setOpening_due(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setGst_no(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));
				dto.setUpcoming_date(resultSet.getString(11));
				dto.setUpcoming_remark(resultSet.getString(12));
				dto.setCompany_name(resultSet.getString(13));
				dto.setDob(resultSet.getString(14));
				dto.setDoa(resultSet.getString(15));
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
	public ArrayList<CustomerDto> getCustomerBirthdayInfo(String d1, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();

		try {

			String sql = "SELECT id,Name, address, mobile_no, old_due, opening_due, user_id_fk, current_in_date, gst_no,Status,upcoming_date,upcoming_remark,company_name,dob,doa FROM customer_info_tb ";
			// Select query for showing data on manage table

			if (!d1.equalsIgnoreCase("")  ) {

				preparedStatement = db.connection.prepareStatement(sql + " WHERE dob = ? ORDER BY dob ASC;");

				preparedStatement.setString(1, d1);
				

			} else {

				preparedStatement = db.connection.prepareStatement(sql + " WHERE DATEDIFF(`dob`,DATE(NOW()))<= 4 ORDER BY dob ASC;");
			}
			
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {

				CustomerDto dto = new CustomerDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAddress(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setOld_due(resultSet.getFloat(5));
				dto.setOpening_due(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setGst_no(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));
				dto.setUpcoming_date(resultSet.getString(11));
				dto.setUpcoming_remark(resultSet.getString(12));
				dto.setCompany_name(resultSet.getString(13));
				dto.setDob(resultSet.getString(14));
				dto.setDoa(resultSet.getString(15));
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
		public ArrayList<CustomerDto> getCustomerAnniversaryInfo(String d2, ServletConfig config, HttpServletRequest request) throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();

			try {

				String sql = "SELECT id,Name, address, mobile_no, old_due, opening_due, user_id_fk, current_in_date, gst_no,Status,upcoming_date,upcoming_remark,company_name,dob,doa FROM customer_info_tb ";
				// Select query for showing data on manage table

				if (!d2.equalsIgnoreCase("")  ) {

					preparedStatement = db.connection.prepareStatement(sql + " WHERE doa = ? ORDER BY doa ASC;");

					preparedStatement.setString(1, d2);
					

				} else {

					preparedStatement = db.connection.prepareStatement(sql + " WHERE DATEDIFF(`doa`,DATE(NOW()))<= 4 ORDER BY doa ASC;");
				}
				
				System.out.println(preparedStatement);
				ResultSet resultSet = preparedStatement.executeQuery();
				System.out.println(preparedStatement);

				while (resultSet.next()) {

					CustomerDto dto = new CustomerDto();

					dto.setId(resultSet.getInt(1));
					dto.setName(resultSet.getString(2));
					dto.setAddress(resultSet.getString(3));
					dto.setMobile_no(resultSet.getString(4));
					dto.setOld_due(resultSet.getFloat(5));
					dto.setOpening_due(resultSet.getFloat(6));
					dto.setUser_id_fk(resultSet.getInt(7));
					dto.setCurrent_in_date(resultSet.getString(8));
					dto.setGst_no(resultSet.getString(9));
					dto.setStatus(resultSet.getString(10));
					dto.setUpcoming_date(resultSet.getString(11));
					dto.setUpcoming_remark(resultSet.getString(12));
					dto.setCompany_name(resultSet.getString(13));
					dto.setDob(resultSet.getString(14));
					dto.setDoa(resultSet.getString(15));
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

	// Show data on edit page according to id
	public CustomerDto getCustomerInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		CustomerDto dto = new CustomerDto();

		try {

			String sql = "SELECT id,Name, address, mobile_no, old_due, opening_due, user_id_fk, current_in_date, gst_no,Status,company_name,dob,doa FROM customer_info_tb WHERE  id=?;";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAddress(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setOld_due(resultSet.getFloat(5));
				dto.setOpening_due(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setGst_no(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));
				dto.setCompany_name(resultSet.getString(11));
				dto.setDob(resultSet.getString(12));
				dto.setDoa(resultSet.getString(13));

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return dto;
	}

	// Method For Updating data on edit page
	public boolean updateCustomer(CustomerDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			String sql = "UPDATE Customer_info_tb SET  Name=?, address=?, mobile_no=?, gst_no=?, user_id_fk=?, Status=?,company_name=?,dob=?,doa=? WHERE id=?;";
			// Update Query for update data
			ps = db.connection.prepareStatement(sql);

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getAddress());
			ps.setString(3, dto.getMobile_no());
			ps.setString(4, dto.getGst_no());
			ps.setInt(5, dto.getUser_id_fk());

			ps.setString(6, dto.getStatus());
			ps.setString(7, dto.getCompany_name());
			ps.setString(8, dto.getDob());
			ps.setString(9, dto.getDoa());
			ps.setInt(10, dto.getId());

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				return true;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	// Method For Updating data on edit page
	public boolean updateCustomerOpeningDue(CustomerDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		float old_opening_due = 0;

		try {
			PreparedStatement select_cust_det = db.connection
					.prepareStatement("SELECT opening_due FROM Customer_info_tb" + "	WHERE id = ?;");

			select_cust_det.setInt(1, dto.getId());
			System.out.println(select_cust_det);
			ResultSet rs_item = select_cust_det.executeQuery();

			while (rs_item.next()) {
				old_opening_due = rs_item.getFloat(1);
			}

			String sql = "UPDATE Customer_info_tb SET  old_due= old_due + ?, opening_due = ? WHERE id=?;";
			// Update Query for update data
			ps = db.connection.prepareStatement(sql);

			ps.setFloat(1, dto.getOpening_due() - old_opening_due);
			ps.setFloat(2, dto.getOpening_due());

			ps.setInt(3, dto.getId());

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				// insert customer acc table info
				PreparedStatement cust_acc = db.connection.prepareStatement(
						"UPDATE customer_account_tb SET  debit_amount=?   WHERE customer_id_fk=? AND TYPE = ?;");

				cust_acc.setFloat(1, dto.getOpening_due());
				cust_acc.setInt(2, dto.getId());
				cust_acc.setString(3, "Opening Due");
				System.out.println(cust_acc);
				int i2 = cust_acc.executeUpdate();

				if (i2 != 0) {
					return true;

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<CustomerDto> getActiveCustomerInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();

		try {

			String sql = "SELECT id,Name, address, mobile_no, old_due, opening_due, user_id_fk, current_in_date, gst_no,Status,company_name FROM customer_info_tb WHERE Status='active';";
			preparedStatement = db.connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				CustomerDto dto = new CustomerDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAddress(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setOld_due(resultSet.getFloat(5));
				dto.setOpening_due(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setGst_no(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));
				dto.setCompany_name(resultSet.getString(11));

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

	public CustomerDto getCustomerInfoByName(String Name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		CustomerDto dto = new CustomerDto();

		try {

			String sql = "SELECT id,Name, address, mobile_no, old_due, opening_due, user_id_fk, current_in_date, gst_no,Status,company_name,dob,doa FROM customer_info_tb WHERE  Name=?;";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setString(1, Name);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAddress(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setOld_due(resultSet.getFloat(5));
				dto.setOpening_due(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setGst_no(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));
				dto.setCompany_name(resultSet.getString(11));
				dto.setDob(resultSet.getString(12));
				dto.setDoa(resultSet.getString(13));
			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return dto;
	}
}