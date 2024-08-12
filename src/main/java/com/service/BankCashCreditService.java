package com.service;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.BankDto;
import com.mysql.jdbc.Statement;

public class BankCashCreditService {
	

	// Insert Method for Transaction
	public int insertCashCredit(BankDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			// Insert Query of Transaction

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO bank_cash_credit_tb\r\n"
					+ " (in_date, credit_amount, credit_bank_id_fk, STATUS, user_id_fk, remark  )\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getIn_date());
			ps.setFloat(2, dto.getCredit_amount());
			ps.setInt(3, dto.getCredit_bank_id_fk());
			ps.setString(4, dto.getStatus());
			ps.setInt(5, dto.getUser_id_fk());
			ps.setString(6, dto.getRemark());

			System.out.println(ps);

			int i = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			if (i != 0) {

				// insert query in Cash Payment table
				PreparedStatement ps_cash = db.connection.prepareStatement(
						"INSERT INTO cash_payment_tb (debit, remark, in_date, bill_id_fk, status, type, user_id_fk)\n"
								+ "VALUES (?, ?, ?, ?, ?, ?, ?);",
						Statement.RETURN_GENERATED_KEYS);

				ps_cash.setFloat(1, dto.getCredit_amount());
				ps_cash.setString(2, dto.getRemark());
				ps_cash.setString(3, dto.getIn_date());
				ps_cash.setInt(4, rs.getInt(1));
				ps_cash.setString(5, dto.getStatus());
				ps_cash.setString(6, "Bank Cash Debit");
				ps_cash.setInt(7, dto.getUser_id_fk());

				System.out.println(ps_cash);

				int u = ps_cash.executeUpdate();

				ResultSet rs_cash = ps_cash.getGeneratedKeys();
				rs_cash.next();
				
				// insert query in Online Payment table
				PreparedStatement ps_on = db.connection.prepareStatement(
						"INSERT INTO online_payment_tb (bank_id_fk, credit, remark, in_date, bill_id_fk, status, type, user_id_fk)\n"
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
						Statement.RETURN_GENERATED_KEYS);

				ps_on.setInt(1, dto.getCredit_bank_id_fk());
				ps_on.setFloat(2, dto.getCredit_amount());
				ps_on.setString(3, dto.getRemark());
				ps_on.setString(4, dto.getIn_date());
				ps_on.setInt(5, rs.getInt(1));
				ps_on.setString(6, dto.getStatus());
				ps_on.setString(7, "Bank Cash credit");
				ps_on.setInt(8, dto.getUser_id_fk());

				System.out.println(ps_on);

				int y = ps_on.executeUpdate();

				ResultSet rs_on = ps_on.getGeneratedKeys();
				rs_on.next();
				if (u != 0 &&y!=0 ) {

					PreparedStatement ps_up = db.connection
							.prepareStatement("UPDATE bank_cash_credit_tb SET  cash_id_fk = ? ,  online_id_fk = ? WHERE id = ? ;");

					ps_up.setFloat(1, rs_cash.getInt(1));
					ps_up.setFloat(2, rs_on.getInt(1));
					ps_up.setInt(3, rs.getInt(1));

					System.out.println(ps_up);

					ps_up.executeUpdate();

				}

				return rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// Method for Show data on Manage page
	public ArrayList<BankDto> getCashCreditInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<BankDto> list = new ArrayList<BankDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(
					"SELECT cc.id, cc.in_date, cc.credit_amount, cc.credit_bank_id_fk, cc.status, cc.user_id_fk, cc.current_in_date, cc.remark, cc.cash_id_fk, bt.name, cc.online_id_fk\r\n"
					+ "FROM bank_cash_credit_tb cc INNER JOIN bank_tb bt ON  cc.credit_bank_id_fk = bt.id ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				BankDto dto = new BankDto();

				dto.setId(resultSet.getInt(1));
				dto.setIn_date(resultSet.getString(2));
				dto.setCredit_amount(resultSet.getFloat(3));
				dto.setCredit_bank_id_fk(resultSet.getInt(4));
				dto.setStatus(resultSet.getString(5));
				dto.setUser_id_fk(resultSet.getInt(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setRemark(resultSet.getString(8));
				dto.setCash_id_fk(resultSet.getInt(9));
				dto.setCredit_bank_name(resultSet.getString(10));
				dto.setOnline_id_fk(resultSet.getInt(11));

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
	public BankDto getCashCreditInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		BankDto dto = new BankDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(
					"SELECT cc.id, cc.in_date, cc.credit_amount, cc.credit_bank_id_fk, cc.status, cc.user_id_fk, cc.current_in_date, cc.remark, cc.cash_id_fk, bt.name, cc.online_id_fk\r\n"
					+ "FROM bank_cash_credit_tb cc INNER JOIN bank_tb bt ON  cc.credit_bank_id_fk = bt.id WHERE cc.id =?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				dto.setId(resultSet.getInt(1));
				dto.setIn_date(resultSet.getString(2));
				dto.setCredit_amount(resultSet.getFloat(3));
				dto.setCredit_bank_id_fk(resultSet.getInt(4));
				dto.setStatus(resultSet.getString(5));
				dto.setUser_id_fk(resultSet.getInt(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setRemark(resultSet.getString(8));
				dto.setCash_id_fk(resultSet.getInt(9));
				dto.setCredit_bank_name(resultSet.getString(10));
				dto.setOnline_id_fk(resultSet.getInt(11));
				
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

	// Update Method for Transaction
	public boolean updateCashCredit(BankDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("\r\n"
					+ "UPDATE bank_cash_credit_tb\r\n"
					+ "SET in_date = ?, credit_amount = ?, credit_bank_id_fk = ?, STATUS = ?, user_id_fk = ?, remark = ?"
					+ "WHERE id = ?;");

			ps.setString(1, dto.getIn_date());
			ps.setFloat(2, dto.getCredit_amount());
			ps.setInt(3, dto.getCredit_bank_id_fk());
			ps.setString(4, dto.getStatus());
			ps.setInt(5, dto.getUser_id_fk());
			ps.setString(6, dto.getRemark());
			ps.setInt(7, dto.getId());

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				// update query in Cash Payment table
				PreparedStatement ps_cash = db.connection.prepareStatement(
						"UPDATE cash_payment_tb SET debit = ?, remark = ?,in_date = ?,  STATUS = ?, user_id_fk = ?  WHERE id = ?;");

				ps_cash.setFloat(1, dto.getCredit_amount());
				ps_cash.setString(2, dto.getRemark());
				ps_cash.setString(3, dto.getIn_date());
				ps_cash.setString(4, dto.getStatus());
				ps_cash.setInt(5, dto.getUser_id_fk());

				ps_cash.setInt(6, dto.getCash_id_fk());

				System.out.println(ps_cash);

				ps_cash.executeUpdate();
				
				// insert query in Online Payment table
				PreparedStatement ps_on = db.connection.prepareStatement(
						"UPDATE online_payment_tb\r\n"
						+ "SET bank_id_fk = ?, credit = ?, remark = ?, in_date = ?, STATUS = ?, user_id_fk = ?\r\n"
						+ "WHERE id = ?;");

				ps_on.setInt(1, dto.getCredit_bank_id_fk());
				ps_on.setFloat(2, dto.getCredit_amount());
				ps_on.setString(3, dto.getRemark());
				ps_on.setString(4, dto.getIn_date());
				ps_on.setString(5, dto.getStatus());
				ps_on.setInt(6, dto.getUser_id_fk());
				ps_on.setInt(7, dto.getOnline_id_fk());

				System.out.println(ps_on);

				ps_on.executeUpdate();
				
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}
}
