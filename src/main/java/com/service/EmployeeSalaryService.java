package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.EmployeeSalaryDto;
import com.dto.PaymentDto;
import com.mysql.jdbc.Statement;

public class EmployeeSalaryService {

	PaymentService pay_ser = new PaymentService();

	// ******** Employee salary insert method Start
	public int payEmployeeSalary(EmployeeSalaryDto dto, PaymentDto pay_dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		int new_cash_id = 0;
		int new_online_id = 0;

		try {

			PreparedStatement insert_ps = db.connection.prepareStatement("INSERT INTO employee_salary_tb(employee_id_fk,pay_date,paid_year,paid_month,salary_per_month,total_days, present_days,"
					+ "absent_days,leave_days,half_days,final_salary,payment_mode,amount,cash_amount,online_amount,online_date,online_way,online_remark,"
					+ "bank_id_fk,user_id_fk,in_date,remark)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			insert_ps.setInt(1, dto.getEmployee_id_fk());
			insert_ps.setString(2, dto.getPay_date());
			insert_ps.setString(3, dto.getPaid_year());
			insert_ps.setString(4, dto.getPaid_month());
			insert_ps.setFloat(5, dto.getSalary_per_month());
			insert_ps.setFloat(6, dto.getTotal_days());
			insert_ps.setFloat(7, dto.getPresent_days());
			insert_ps.setFloat(8, dto.getAbsent_days());
			insert_ps.setFloat(9, dto.getLeave_days());
			insert_ps.setFloat(10, dto.getHalf_days());
			insert_ps.setFloat(11, dto.getFinal_salary());
			insert_ps.setString(12, dto.getPayment_mode());
			insert_ps.setFloat(13, dto.getCash_amount() + dto.getOnline_amount());
			insert_ps.setFloat(14, dto.getCash_amount());
			insert_ps.setFloat(15, dto.getOnline_amount());
			insert_ps.setString(16, dto.getOnline_date());
			insert_ps.setString(17, dto.getOnline_way());
			insert_ps.setString(18, dto.getOnline_remark());
			insert_ps.setInt(19, dto.getBank_id_fk());
			insert_ps.setInt(20, dto.getUser_id_fk());
			insert_ps.setString(21, dto.getIn_date());
			insert_ps.setString(22, dto.getRemark());
			System.out.println(insert_ps);

			insert_ps.executeUpdate();
			ResultSet rs = insert_ps.getGeneratedKeys();
			rs.next();
			dto.setId(rs.getInt(1));
			pay_dto.setBill_id_fk(rs.getInt(1));

			// *************** insert cash and payment tb*****************

			// ****** when Payment mode is both ********
			if (dto.getPayment_mode().equalsIgnoreCase("both")) {
				new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

			}
			// ****** when Payment mode is online ********
			else if (dto.getPayment_mode().equalsIgnoreCase("online")) {

				new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

			}
			// ****** when Payment mode is cash ********
			else {
				new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
			}

			dto.setCash_payment_id_fk(new_cash_id);
			dto.setOnline_payment_id_fk(new_online_id);

			// *************** update cash and payment id in spend tb*****************
			PreparedStatement update_emp = db.connection.prepareStatement(
					"UPDATE employee_salary_tb SET\n" + "	cash_payment_id_fk = ? , \n"
							+ "	online_payment_id_fk = ?\n" + "	WHERE\n" + "	id = ?;",
					Statement.RETURN_GENERATED_KEYS);

			update_emp.setInt(1, dto.getCash_payment_id_fk());
			update_emp.setInt(2, dto.getOnline_payment_id_fk());
			update_emp.setInt(3, dto.getId());

			System.out.println(update_emp);

			int i = update_emp.executeUpdate();

			if (i != 0) {

				return rs.getInt(1);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}// ******** Employee salary insert method End

	// ******** Get All Employee salary Record Start
	public ArrayList<EmployeeSalaryDto> getAllEmployeeSalaryInfoMethod(String month, String year,
			ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		ArrayList<EmployeeSalaryDto> list = new ArrayList<EmployeeSalaryDto>();

		try {
			
				
			String sql = "SELECT es.id, es.employee_id_fk, es.pay_date, es.paid_year, es.paid_month, es.salary_per_month,\n"
					+ "es.total_days, es.present_days, es.absent_days, es.leave_days, es.half_days, es.final_salary, \n"
					+ "es.payment_mode, es.amount, es.cash_amount, es.online_amount, es.online_date, es.online_way,\n"
					+ "es.online_remark, es.status, es.bank_id_fk, es.cash_payment_id_fk, es.online_payment_id_fk,"
					+ "es.user_id_fk, es.current_in_date, ep.name, ep.mobile_no, up.name,es.in_date,es.remark "
					+ "FROM employee_salary_tb es INNER JOIN employee_personal_info_tb ep ON es.employee_id_fk = ep.id \n"
					+ "INNER JOIN user_personal_info_tb up ON es.user_id_fk = up.id ";

			if (month.equalsIgnoreCase("") && year.equalsIgnoreCase("")) {
				
			}
				
			else if (!month.equalsIgnoreCase("") && !year.equalsIgnoreCase("")) {

				ps = db.connection.prepareStatement(sql + " WHERE es.paid_month=? AND es.paid_year=?;");

				ps.setString(1, month);
				ps.setString(2, year);

			}else {

				ps = db.connection.prepareStatement(sql + " WHERE es.paid_year=?;");
				ps.setString(1, year);

			}
			
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				EmployeeSalaryDto dto = new EmployeeSalaryDto();

				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setPay_date(resultSet.getString(3));
				dto.setPaid_year(resultSet.getString(4));
				dto.setPaid_month(resultSet.getString(5));
				dto.setSalary_per_month(resultSet.getFloat(6));

				dto.setTotal_days(resultSet.getFloat(7));
				dto.setPresent_days(resultSet.getFloat(8));
				dto.setAbsent_days(resultSet.getFloat(9));
				dto.setLeave_days(resultSet.getFloat(10));
				dto.setHalf_days(resultSet.getFloat(11));
				dto.setFinal_salary(resultSet.getFloat(12));

				dto.setPayment_mode(resultSet.getString(13));
				dto.setAmount(resultSet.getFloat(14));
				dto.setCash_amount(resultSet.getFloat(15));
				dto.setOnline_amount(resultSet.getFloat(16));
				dto.setOnline_date(resultSet.getString(17));
				dto.setOnline_way(resultSet.getString(18));
				dto.setOnline_remark(resultSet.getString(19));

				dto.setStatus(resultSet.getString(20));
				dto.setBank_id_fk(resultSet.getInt(21));
				dto.setUser_id_fk(resultSet.getInt(22));
				dto.setCurrent_in_date(resultSet.getString(23));
				dto.setCash_payment_id_fk(resultSet.getInt(24));
				dto.setOnline_payment_id_fk(resultSet.getInt(25));
				dto.setEmployee_name(resultSet.getString(26));
				dto.setMobile_no(resultSet.getString(27));
				dto.setUser_name(resultSet.getString(28));
				dto.setIn_date(resultSet.getString(29));
				dto.setRemark(resultSet.getString(30));
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
	} // ******** Get All Employee salary Record End

	// ******** Get One Employee salary Record Start
	public ArrayList<EmployeeSalaryDto> getEmployeeSalaryInfoMethodById(String year, int id, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		ArrayList<EmployeeSalaryDto> list = new ArrayList<EmployeeSalaryDto>();

		try {
			
			String sql = "SELECT es.id,es.employee_id_fk,es.pay_date,es.paid_year,es.paid_month,es.salary_per_month,"
					+ "es.total_days,es.present_days,es.absent_days,es.leave_days,es.half_days,es.final_salary,"
					+ "es.payment_mode,es.amount,es.cash_amount,es.online_amount,es.online_date,es.online_way, "
					+ "es.online_remark,es.status,es.bank_id_fk,es.cash_payment_id_fk,es.online_payment_id_fk,"
					+ "es.user_id_fk,es.current_in_date,ep.name,ep.mobile_no,up.name ,es.remark FROM employee_salary_tb es "
					+ "INNER JOIN employee_personal_info_tb ep ON es.employee_id_fk=ep.id "
					+ "INNER JOIN user_personal_info_tb up ON es.user_id_fk=up.id WHERE ep.id=? ";
			
			if (year.equalsIgnoreCase("All")) {
				ps = db.connection.prepareStatement(sql +";");
				ps.setInt(1, id);
			}
			else {
				ps = db.connection.prepareStatement(sql +" AND es.paid_year = ?;");
				ps.setInt(1, id);
				ps.setString(2, year);
			}
			 
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				EmployeeSalaryDto dto = new EmployeeSalaryDto();

				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setPay_date(resultSet.getString(3));
				dto.setPaid_year(resultSet.getString(4));
				dto.setPaid_month(resultSet.getString(5));
				dto.setSalary_per_month(resultSet.getFloat(6));
				dto.setTotal_days(resultSet.getFloat(7));
				dto.setPresent_days(resultSet.getFloat(8));
				dto.setAbsent_days(resultSet.getFloat(9));
				dto.setLeave_days(resultSet.getFloat(10));
				dto.setHalf_days(resultSet.getFloat(11));
				dto.setFinal_salary(resultSet.getFloat(12));
				dto.setPayment_mode(resultSet.getString(13));
				dto.setAmount(resultSet.getFloat(14));
				dto.setCash_amount(resultSet.getFloat(15));
				dto.setOnline_amount(resultSet.getFloat(16));
				dto.setOnline_date(resultSet.getString(17));
				dto.setOnline_way(resultSet.getString(18));
				dto.setOnline_remark(resultSet.getString(19));
				dto.setStatus(resultSet.getString(20));
				dto.setBank_id_fk(resultSet.getInt(21));
				dto.setCash_payment_id_fk(resultSet.getInt(22));
				dto.setOnline_payment_id_fk(resultSet.getInt(23));
				dto.setUser_id_fk(resultSet.getInt(24));
				dto.setCurrent_in_date(resultSet.getString(25));
				dto.setEmployee_name(resultSet.getString(26));
				dto.setMobile_no(resultSet.getString(27));
				dto.setUser_name(resultSet.getString(28));
				dto.setRemark(resultSet.getString(29));

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
	} // ******** Get One Employee salary Record End

	// ******** Get One salary Record Start
	public EmployeeSalaryDto getSalaryInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		EmployeeSalaryDto dto = new EmployeeSalaryDto();

		try {
			ps = db.connection.prepareStatement(
					"SELECT 	es.id,es.employee_id_fk,es.pay_date,es.paid_year,es.paid_month,es.salary_per_month,es.total_days,es.present_days,es.absent_days, \n"
							+ "es.leave_days,es.half_days,es.final_salary,es.payment_mode,es.amount,es.cash_amount,es.online_amount,es.online_date,es.online_way, \n"
							+ "es.online_remark,es.status,es.bank_id_fk,es.cash_payment_id_fk,es.online_payment_id_fk,es.user_id_fk,es.current_in_date,\n"
							+ "ep.name,ep.mobile_no,up.name, ep.qualification,ep.address, es.in_date, es.remark FROM employee_salary_tb es\n"
							+ "INNER JOIN employee_personal_info_tb ep ON es.employee_id_fk=ep.id INNER JOIN user_personal_info_tb up ON es.user_id_fk=up.id\n"
							+ "WHERE es.id=?;");

			ps.setInt(1, id);

			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setPay_date(resultSet.getString(3));
				dto.setPaid_year(resultSet.getString(4));
				dto.setPaid_month(resultSet.getString(5));
				dto.setSalary_per_month(resultSet.getFloat(6));
				dto.setTotal_days(resultSet.getFloat(7));
				dto.setPresent_days(resultSet.getFloat(8));
				dto.setAbsent_days(resultSet.getFloat(9));
				dto.setLeave_days(resultSet.getFloat(10));
				dto.setHalf_days(resultSet.getFloat(11));
				dto.setFinal_salary(resultSet.getFloat(12));
				dto.setPayment_mode(resultSet.getString(13));
				dto.setAmount(resultSet.getFloat(14));
				dto.setCash_amount(resultSet.getFloat(15));
				dto.setOnline_amount(resultSet.getFloat(16));
				dto.setOnline_date(resultSet.getString(17));
				dto.setOnline_way(resultSet.getString(18));
				dto.setOnline_remark(resultSet.getString(19));
				dto.setStatus(resultSet.getString(20));
				dto.setBank_id_fk(resultSet.getInt(21));
				dto.setCash_payment_id_fk(resultSet.getInt(22));
				dto.setOnline_payment_id_fk(resultSet.getInt(23));
				dto.setUser_id_fk(resultSet.getInt(24));
				dto.setCurrent_in_date(resultSet.getString(25));
				dto.setEmployee_name(resultSet.getString(26));
				dto.setMobile_no(resultSet.getString(27));
				dto.setUser_name(resultSet.getString(28));
				dto.setQualification(resultSet.getString(29));
				dto.setAddress(resultSet.getString(30));
				dto.setIn_date(resultSet.getString(31));
				dto.setRemark(resultSet.getString(32));
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
	}// ******** Get One salary Record End

	// ******** Employee salary insert method Start
	public boolean updatepayEmployeeSalary(EmployeeSalaryDto dto, PaymentDto pay_dto, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb db = new DataDb(request);
		float old_amount = 0;
		String old_payment_mode = "";

		int new_cash_id = 0;
		int new_online_id = 0;

		try {
			// Select query for old Paid amount
			PreparedStatement ps1 = db.connection
					.prepareStatement("SELECT 	amount,payment_mode,cash_payment_id_fk,online_payment_id_fk\n"
							+ "	FROM employee_salary_tb WHERE id=?;");

			ps1.setInt(1, dto.getId());
			System.out.println(ps1);

			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {

				old_amount = rs1.getFloat(1);
				old_payment_mode = rs1.getString(2);
				dto.setCash_payment_id_fk(rs1.getInt(3));
				dto.setOnline_payment_id_fk(rs1.getInt(4));

			}

			// ****** when old Payment mode & New mode is same ********
			if (old_payment_mode.equalsIgnoreCase(dto.getPayment_mode())) {

				pay_ser.updateCashPayment(pay_dto, dto.getCash_payment_id_fk(), request, config);
				pay_ser.updateOnlinePayment(pay_dto, dto.getOnline_payment_id_fk(), request, config);

			}

			// ****** when old Payment mode & New mode is Different ********

			else {

				pay_ser.deleteCashPayment(dto.getCash_payment_id_fk(), request, config);
				pay_ser.deleteOnlinePayment(dto.getOnline_payment_id_fk(), request, config);

				// ****** when new Payment mode is both ********
				if (dto.getPayment_mode().equalsIgnoreCase("both")) {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is online ********
				else if (dto.getPayment_mode().equalsIgnoreCase("online")) {

					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is cash ********
				else {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				}

				dto.setCash_payment_id_fk(new_cash_id);
				dto.setOnline_payment_id_fk(new_online_id);

			}

			PreparedStatement update_emp = db.connection.prepareStatement(
					"UPDATE employee_salary_tb SET employee_id_fk =?,pay_date =?,paid_year=?,paid_month=?,salary_per_month=?,"
							+ "total_days=?, present_days=?, absent_days=?,leave_days=?,half_days=?,final_salary=?,payment_mode=?,"
							+ "amount=?,cash_amount=?,online_amount=?,online_date=?,online_way=?,online_remark=?, bank_id_fk=?,"
							+ "user_id_fk=?, cash_payment_id_fk = ?,online_payment_id_fk = ? , in_date=?, remark = ? WHERE id = ?;");

			update_emp.setInt(1, dto.getEmployee_id_fk());
			update_emp.setString(2, dto.getPay_date());
			update_emp.setString(3, dto.getPaid_year());
			update_emp.setString(4, dto.getPaid_month());
			update_emp.setFloat(5, dto.getSalary_per_month());
			update_emp.setFloat(6, dto.getTotal_days());
			update_emp.setFloat(7, dto.getPresent_days());
			update_emp.setFloat(8, dto.getAbsent_days());
			update_emp.setFloat(9, dto.getLeave_days());
			update_emp.setFloat(10, dto.getHalf_days());
			update_emp.setFloat(11, dto.getFinal_salary());
			update_emp.setString(12, dto.getPayment_mode());
			update_emp.setFloat(13, dto.getCash_amount() + dto.getOnline_amount());
			update_emp.setFloat(14, dto.getCash_amount());
			update_emp.setFloat(15, dto.getOnline_amount());
			update_emp.setString(16, dto.getOnline_date());
			update_emp.setString(17, dto.getOnline_way());
			update_emp.setString(18, dto.getOnline_remark());
			update_emp.setInt(19, dto.getBank_id_fk());
			update_emp.setInt(20, dto.getUser_id_fk());
			update_emp.setInt(21, dto.getCash_payment_id_fk());
			update_emp.setInt(22, dto.getOnline_payment_id_fk());
			update_emp.setString(23, dto.getIn_date());
			update_emp.setString(24, dto.getRemark());
			update_emp.setInt(25, dto.getId());

			int i = update_emp.executeUpdate();

			if (i != 0) {

				return true;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}
}
