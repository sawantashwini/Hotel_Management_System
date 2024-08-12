package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.EmployeeAccountDto;
import com.dto.EmployeeSalaryDto;
import com.dto.PaymentDto;
import com.mysql.jdbc.Statement;

public class EmployeeAccountService {
	PaymentService pay_ser = new PaymentService();

	// ******** Employee Account info insert method Start
	public int insertEmployeeAccountInfo(EmployeeAccountDto dto, PaymentDto pay_dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		int new_cash_id = 0;
		int new_online_id = 0;

		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO employee_account_tb "
					+ "(employee_id_fk,debit_amount,credit_amount,payment_mode,amount,online_amount,cash_amount,online_remark,online_date,"
					+ "online_way,status,bank_id_fk,user_id_fk,remark,in_date)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, dto.getEmployee_id_fk());
			ps.setFloat(2, dto.getDebit_amount());
			ps.setFloat(3, dto.getCredit_amount());

			ps.setString(4, dto.getPayment_mode());
			ps.setFloat(5, dto.getCash_amount() + dto.getOnline_amount());
			ps.setFloat(6, dto.getOnline_amount());
			ps.setFloat(7, dto.getCash_amount());

			ps.setString(8, dto.getOnline_remark());
			ps.setString(9, dto.getOnline_date());
			ps.setString(10, dto.getOnline_way());
			ps.setString(11, dto.getStatus());
			ps.setInt(12, dto.getBank_id_fk());
			ps.setInt(13, dto.getUser_id_fk());

			ps.setString(14, dto.getRemark());
			ps.setString(15, dto.getIn_date());

			System.out.println(ps);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
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
			PreparedStatement update_EmpAcc = db.connection.prepareStatement(
					"UPDATE employee_account_tb SET cash_payment_id_fk = ? , online_payment_id_fk = ? WHERE id = ?;",
					Statement.RETURN_GENERATED_KEYS);

			update_EmpAcc.setInt(1, dto.getCash_payment_id_fk());
			update_EmpAcc.setInt(2, dto.getOnline_payment_id_fk());
			update_EmpAcc.setInt(3, dto.getId());

			System.out.println(update_EmpAcc);

			int i = update_EmpAcc.executeUpdate();

			if (i != 0) {

				return rs.getInt(1);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}// ******** Employee account info insert method End

	// Method for Show data on Manage page
	public ArrayList<EmployeeAccountDto> getEmployeeAccountInfo(String d1, String d2, int id, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		ArrayList<EmployeeAccountDto> list = new ArrayList<EmployeeAccountDto>();

		try {
			String sql = "SELECT ea.id,ea.employee_id_fk,ea.debit_amount, ea.credit_amount,ea.payment_mode,ea.cash_amount,ea.online_amount,\n"
					+ "ea.online_way,ea.online_remark,\n"
					+ "ea.online_date,ea.status,ea.user_id_fk,ea.cash_payment_id_fk,ea.online_payment_id_fk,\n"
					+ "ea.current_in_date,emp.name,emp.mobile_no, usp.name,ea.in_date,ea.remark FROM employee_account_tb ea \n"
					+ "INNER JOIN employee_personal_info_tb emp ON emp.id=ea.employee_id_fk \n"
					+ "INNER JOIN user_personal_info_tb usp ON usp.id=ea.user_id_fk  WHERE emp.id = ? ";

			if (!"".equalsIgnoreCase(d1) && !"".equalsIgnoreCase(d2)) {

				// Select query for showing data on manage table
				ps = db.connection.prepareStatement(sql + "AND ea.in_date BETWEEN ? AND ? ORDER BY ea.in_date;");

				ps.setInt(1, id);
				ps.setString(2, d1);
				ps.setString(3, d2);
				
			} else {
				
				ps = db.connection.prepareStatement(sql +";");

				ps.setInt(1, id);

			}
			System.out.println(ps);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				EmployeeAccountDto dto = new EmployeeAccountDto();

				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setDebit_amount(resultSet.getFloat(3));
				dto.setCredit_amount(resultSet.getFloat(4));
				dto.setPayment_mode(resultSet.getString(5));
				dto.setCash_amount(resultSet.getFloat(6));
				dto.setOnline_amount(resultSet.getFloat(7));
				dto.setOnline_way(resultSet.getString(8));
				dto.setOnline_remark(resultSet.getString(9));
				dto.setOnline_date(resultSet.getString(10));
				dto.setStatus(resultSet.getString(11));
				dto.setUser_id_fk(resultSet.getInt(12));
				dto.setCash_payment_id_fk(resultSet.getInt(13));
				dto.setOnline_payment_id_fk(resultSet.getInt(14));
				dto.setCurrent_in_date(resultSet.getString(15));
				dto.setEmployee_name(resultSet.getString(16));
				dto.setMobile_no(resultSet.getString(17));
				dto.setUser_name(resultSet.getString(18));
				dto.setIn_date(resultSet.getString(19));
				dto.setRemark(resultSet.getString(20));
				
				

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
	
	
	
	public float getEmployeeTransactionAmountInfo( int id, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		
		float tot_Credit = 0, tot_Debit = 0, Balance_Amt = 0, credit_amt = 0, debit_amt = 0;
		Balance_Amt = tot_Credit - tot_Debit;
		
		try {
			String sql = "SELECT debit_amount, credit_amount FROM employee_account_tb \n"
					+ "WHERE employee_id_fk = ?;";
				
			ps = db.connection.prepareStatement(sql +";");

			ps.setInt(1, id);

			//System.out.println(ps);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				debit_amt=(resultSet.getFloat(1));
				credit_amt=(resultSet.getFloat(2));
				
				tot_Debit = tot_Debit + debit_amt;
				tot_Credit = tot_Credit + credit_amt;

				//System.out.println(tot_Credit-tot_Debit +"check");
				Balance_Amt =  tot_Credit-tot_Debit;
				//System.out.println(Balance_Amt+"check bal amount");

			}
		}catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return Balance_Amt;
	}

	
	

	// Method for Show data on Manage page
	public EmployeeAccountDto getEmployeeAccountInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		EmployeeAccountDto dto = new EmployeeAccountDto();

		try {

			// Select query for showing data on manage table
			ps = db.connection.prepareStatement("SELECT ea.id,ea.employee_id_fk,ea.debit_amount, ea.credit_amount,"
					+ "ea.payment_mode,ea.cash_amount,ea.online_amount,ea.online_way,ea.online_remark, ea.online_date,"
					+ "ea.status,ea.user_id_fk,ea.cash_payment_id_fk,ea.online_payment_id_fk,ea.current_in_date,emp.name,"
					+ "emp.mobile_no,emp.salary_per_month, usp.name, emp.qualification,emp.address, ea.in_date,ea.remark,ea.bank_id_fk, bt.name FROM employee_account_tb ea INNER JOIN "
					+ "employee_personal_info_tb emp ON emp.id=ea.employee_id_fk  Left JOIN bank_tb bt ON bt.id=ea.bank_id_fk "
					+ " INNER JOIN user_personal_info_tb usp ON usp.id=ea.user_id_fk WHERE ea.id = ?;");

			ps.setInt(1, id);
			System.out.println(ps);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setDebit_amount(resultSet.getFloat(3));
				dto.setCredit_amount(resultSet.getFloat(4));
				dto.setPayment_mode(resultSet.getString(5));
				dto.setCash_amount(resultSet.getFloat(6));
				dto.setOnline_amount(resultSet.getFloat(7));
				dto.setOnline_way(resultSet.getString(8));
				dto.setOnline_remark(resultSet.getString(9));
				dto.setOnline_date(resultSet.getString(10));
				dto.setStatus(resultSet.getString(11));
				dto.setUser_id_fk(resultSet.getInt(12));
				dto.setCash_payment_id_fk(resultSet.getInt(13));
				dto.setOnline_payment_id_fk(resultSet.getInt(14));
				dto.setCurrent_in_date(resultSet.getString(15));
				dto.setEmployee_name(resultSet.getString(16));
				dto.setMobile_no(resultSet.getString(17));
				dto.setSalary_per_month(resultSet.getFloat(18));
				dto.setUser_name(resultSet.getString(19));
				dto.setQualification(resultSet.getString(20));
				dto.setAddress(resultSet.getString(21));
				dto.setIn_date(resultSet.getString(22));
				dto.setRemark(resultSet.getString(23));
				dto.setBank_id_fk(resultSet.getInt(24));
				dto.setBank_name(resultSet.getString(25));

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

	// ******** Employee salary insert method Start
	public boolean updateEmployeeAccountInfo(EmployeeAccountDto dto, PaymentDto pay_dto, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb db = new DataDb(request);
		float old_amount = 0;
		String old_payment_mode = "";

		int new_cash_id = 0;
		int new_online_id = 0;

		try {
			// Select query for old Paid amount
			PreparedStatement ps1 = db.connection
					.prepareStatement("SELECT 	amount,payment_mode,cash_payment_id_fk,online_payment_id_fk "
							+ "	FROM employee_account_tb WHERE id=?;");

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

			}

			PreparedStatement ps = db.connection.prepareStatement("UPDATE employee_account_tb SET employee_id_fk = ?,"
					+ "debit_amount=?,credit_amount=?,payment_mode=?,amount=?,online_amount=?,cash_amount=?,"
					+ "online_remark=?,online_date=?,online_way=?,status=?,bank_id_fk=?,user_id_fk=?,"
					+ "cash_payment_id_fk = ?,online_payment_id_fk = ?, in_date =?,remark = ? WHERE id = ?");

			ps.setInt(1, dto.getEmployee_id_fk());
			ps.setFloat(2, dto.getDebit_amount());
			ps.setFloat(3, dto.getCredit_amount());
			ps.setString(4, dto.getPayment_mode());

			ps.setFloat(5, dto.getCash_amount() + dto.getOnline_amount());
			ps.setFloat(6, dto.getOnline_amount());
			ps.setFloat(7, dto.getCash_amount());
			ps.setString(8, dto.getOnline_remark());
			ps.setString(9, dto.getOnline_date());
			ps.setString(10, dto.getOnline_way());
			ps.setString(11, dto.getStatus());

			ps.setInt(12, dto.getBank_id_fk());
			ps.setInt(13, dto.getUser_id_fk());

			ps.setInt(14, dto.getCash_payment_id_fk());
			ps.setInt(15, dto.getOnline_payment_id_fk());
			
			ps.setString(16, dto.getIn_date());
			ps.setString(17, dto.getRemark());

			ps.setInt(18, dto.getId());

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

}
