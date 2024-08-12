package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.UserDto;

public class UserService {

	// ---- insert User -----//

	public boolean insertUser(UserDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("\r\n"
					+ "INSERT INTO user_personal_info_tb ( NAME, mobile_no, PASSWORD, address ) VALUES( ?, ?, ?, ?);\r\n"
					+ "");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile_no());

			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getAddress());

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

	// ---- End Inser User ----- //

	// ----- Select User Array list -------//

	public ArrayList<UserDto> getuserInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<UserDto> list = new ArrayList<UserDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT 	id, name, mobile_no, password, address, status, current_in_date FROM user_personal_info_tb ;");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				UserDto dto = new UserDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setPassword(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setStatus(resultSet.getString(6));
				dto.setCurrent_in_date(resultSet.getString(7));

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

	// ----- End User Array list -------//

	// ----- Select User by Id for Feature -------//

	public UserDto getuserInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		UserDto dto = new UserDto();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT id, name, mobile_no, password, address, status, current_in_date,\r\n"
					+ " user_module, delete_module, update_module, master_module, payment_module, bank_module, spend_module, employee_module, spend_report, "
					+ " employee_report, income_module, income_report, order_module, room_module, ingredients_module, reciepe_relation_module, dealer_module,"
					+ " customer_module, liquor_module, liquor_report, customer_report, dealer_report, ingredients_in_Report, ingredients_out_Report,"
					+ " complete_bills_report, pending_bills_report, kot_bills_report, room_available_report, room_booked_report,"
					+ " room_bill_report, spend_head_module, income_head_module "
					+ " FROM user_personal_info_tb ut WHERE id = ?; ");

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setPassword(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setStatus(resultSet.getString(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setUser_module(resultSet.getString(8));
				dto.setDelete_module(resultSet.getString(9));
				dto.setUpdate_module(resultSet.getString(10));
				dto.setMaster_module(resultSet.getString(11));
				
				dto.setPayment_module(resultSet.getString(12));
				dto.setBank_module(resultSet.getString(13));
				dto.setSpend_module(resultSet.getString(14));
				dto.setEmployee_module(resultSet.getString(15));
				
				dto.setSpend_report(resultSet.getString(16));
				dto.setEmployee_report(resultSet.getString(17));
				dto.setIncome_module(resultSet.getString(18));
				dto.setIncome_report(resultSet.getString(19));
				
				dto.setOrder_module(resultSet.getString(20));
				dto.setRoom_module(resultSet.getString(21));
				dto.setIngredients_module(resultSet.getString(22));
				dto.setReciepe_relation_module(resultSet.getString(23));
				dto.setDealer_module(resultSet.getString(24));
				dto.setCustomer_module(resultSet.getString(25));
				dto.setLiquor_module(resultSet.getString(26));
				dto.setLiquor_report(resultSet.getString(27));
				dto.setCustomer_report(resultSet.getString(28));
				dto.setDealer_report(resultSet.getString(29));
				dto.setIngredients_in_Report(resultSet.getString(30));
				dto.setIngredients_out_Report(resultSet.getString(31));
				dto.setComplete_bills_report(resultSet.getString(32));
				dto.setPending_bills_report(resultSet.getString(33));	
				dto.setKot_bills_report(resultSet.getString(34));
				dto.setRoom_available_report(resultSet.getString(35));
				dto.setRoom_booked_report(resultSet.getString(36));
				dto.setRoom_bill_report(resultSet.getString(37));
				dto.setSpend_head_module(resultSet.getString(38));
				dto.setIncome_head_module(resultSet.getString(39));
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

	// ----- End User by Id for Feature -------//
	
	public UserDto getuserInfoByMob(String mob, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		UserDto dto = new UserDto();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT 	id FROM user_personal_info_tb ut WHERE mobile_no = ?;");

			preparedStatement.setString(1, mob);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
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

	// ----- Update User -------//

	public boolean UpdateUser(UserDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement("UPDATE user_personal_info_tb SET name = ? , mobile_no = ? , password = ? , address = ? , status = ?   WHERE id = ? ;");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile_no());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getStatus());
			ps.setInt(6, dto.getId());
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

	// ----- End Update User -------//

	// ----- Update User by Id for Feature -------//

	public boolean UpdateUserById(UserDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement("UPDATE user_personal_info_tb\r\n"
					+ "SET  name = ?, mobile_no = ?, password = ?, address = ?, status = ?, user_module = ?, delete_module = ?, update_module = ?,\r\n"
					+ "  master_module = ?, payment_module =?, bank_module = ?, spend_module = ?, employee_module = ?, income_module = ?, income_report = ?,"
					+ " spend_report = ?, employee_report = ?, order_module = ?, room_module = ?, ingredients_module = ?, reciepe_relation_module = ?,"
					+ " dealer_module  = ?, customer_module  = ?, liquor_module = ?, liquor_report = ?, customer_report = ?, dealer_report = ?, "
					+ " ingredients_in_Report  = ?, ingredients_out_Report = ?, complete_bills_report = ?, pending_bills_report = ?, kot_bills_report = ?,\r\n"
					+ "room_available_report = ?, room_booked_report = ?, room_bill_report = ?, spend_head_module = ?, income_head_module = ? WHERE id = ?;");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile_no());
			ps.setString(3, dto.getPassword());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getStatus());
			ps.setString(6, dto.getUser_module());
			ps.setString(7, dto.getDelete_module());
			ps.setString(8, dto.getUpdate_module());
			ps.setString(9, dto.getMaster_module());
			ps.setString(10, dto.getPayment_module());
			ps.setString(11, dto.getBank_module());
			ps.setString(12, dto.getSpend_module());
			ps.setString(13, dto.getEmployee_module());
			ps.setString(14, dto.getIncome_module());
			ps.setString(15, dto.getIncome_report());
			ps.setString(16, dto.getSpend_report());
			ps.setString(17, dto.getEmployee_report());
			
			ps.setString(18, dto.getOrder_module());
			ps.setString(19, dto.getRoom_module());
			ps.setString(20, dto.getIngredients_module());
			ps.setString(21, dto.getReciepe_relation_module());
			ps.setString(22, dto.getDealer_module());
			ps.setString(23, dto.getCustomer_module());
			ps.setString(24, dto.getLiquor_module());
			ps.setString(25, dto.getLiquor_report());
			
			ps.setString(26, dto.getCustomer_report());
			ps.setString(27, dto.getDealer_report());
			ps.setString(28, dto.getIngredients_in_Report());
			ps.setString(29, dto.getIngredients_out_Report());
			ps.setString(30, dto.getComplete_bills_report());
			ps.setString(31, dto.getPending_bills_report());
			ps.setString(32, dto.getKot_bills_report());
			ps.setString(33, dto.getRoom_available_report());
			ps.setString(34, dto.getRoom_booked_report());
			ps.setString(35, dto.getRoom_bill_report());
			ps.setString(36, dto.getSpend_head_module());
			ps.setString(37, dto.getIncome_head_module());
			ps.setInt(38, dto.getId());

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

	// ----- End Update User by Id for Feature -------//

	// ------ Check login method ------//

	public int checkLogin(UserDto dto, ServletConfig config, HttpServletRequest request) throws IOException {
		try {

			DataDb db = new DataDb(request);
			String sql = "SELECT id FROM user_personal_info_tb WHERE mobile_no= ? && PASSWORD= ?";

			PreparedStatement preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setString(1, dto.getMobile_no());
			preparedStatement.setString(2, dto.getPassword());
			System.out.print(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}

		} catch (Exception e) {
			// LogFileService.catchLogFile(e, config);
			return 0;
		}
	}

	// ------ End Check login method ------//

}
