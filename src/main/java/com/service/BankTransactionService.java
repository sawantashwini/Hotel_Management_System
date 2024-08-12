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

public class BankTransactionService {
	// Insert Method for Transaction
	public int insertTransaction(BankDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			// Insert Query of Transaction

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO bank_transaction_tb\r\n"
					+ "            (in_date, transaction_amount, debit_bank_id_fk, credit_bank_id_fk, STATUS, user_id_fk, Remark)\r\n"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getIn_date());
			ps.setFloat(2, dto.getTransaction_amount());
			ps.setInt(3, dto.getDebit_bank_id_fk());
			ps.setInt(4, dto.getCredit_bank_id_fk());
			ps.setString(5, dto.getStatus());
			ps.setInt(6, dto.getUser_id_fk());
			ps.setString(7, dto.getRemark());

			System.out.println(ps);

			int i = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();

			if (i != 0) {
				// insert query in debit online Payment table
				PreparedStatement ps_online_debit = db.connection.prepareStatement("INSERT INTO online_payment_tb"
						+ " (bank_id_fk, debit, remark, in_date, bill_id_fk, status, type, user_id_fk)\n"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

				ps_online_debit.setInt(1, dto.getDebit_bank_id_fk());
				ps_online_debit.setFloat(2, dto.getTransaction_amount());
				ps_online_debit.setString(3, dto.getRemark());
				ps_online_debit.setString(4, dto.getIn_date());
				ps_online_debit.setInt(5, rs.getInt(1));
				ps_online_debit.setString(6, dto.getStatus());
				ps_online_debit.setString(7, "Bank To Transfer");
				ps_online_debit.setInt(8, dto.getUser_id_fk());

				System.out.println(ps_online_debit);

				int y = ps_online_debit.executeUpdate();

				ResultSet rs_on_debit = ps_online_debit.getGeneratedKeys();
				rs_on_debit.next();
				
				
				// insert query in Credit online Payment table
				PreparedStatement ps_online_credit = db.connection.prepareStatement("INSERT INTO online_payment_tb"
						+ " (bank_id_fk, credit, remark, in_date, bill_id_fk, status, type, user_id_fk)\n"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

				ps_online_credit.setInt(1, dto.getCredit_bank_id_fk());
				ps_online_credit.setFloat(2, dto.getTransaction_amount());
				ps_online_credit.setString(3, dto.getRemark());
				ps_online_credit.setString(4, dto.getIn_date());
				ps_online_credit.setInt(5, rs.getInt(1));
				ps_online_credit.setString(6, dto.getStatus());
				ps_online_credit.setString(7, "Bank From Transaction");
				ps_online_credit.setInt(8, dto.getUser_id_fk());

				System.out.println(ps_online_credit);

				int u = ps_online_credit.executeUpdate();

				ResultSet rs_on_credit = ps_online_credit.getGeneratedKeys();
				rs_on_credit.next();
				if (y!=0&&u != 0) {

					PreparedStatement ps_up = db.connection
							.prepareStatement("UPDATE bank_transaction_tb SET  debit_online_id_fk = ?,  credit_online_id_fk = ? WHERE id = ? ;");

					ps_up.setInt(1, rs_on_debit.getInt(1));
					ps_up.setInt(2, rs_on_credit.getInt(1));
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
	public ArrayList<BankDto> getTransactionInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<BankDto> list = new ArrayList<BankDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(
					"SELECT tt.id, tt.in_date, tt.transaction_amount, tt.debit_bank_id_fk, tt.credit_bank_id_fk, tt.current_in_date, tt.status, tt.user_id_fk,  tt.remark, tt.credit_online_id_fk, tt.debit_online_id_fk, bt.name, bts.name\r\n"
							+ "FROM bank_transaction_tb tt INNER JOIN bank_tb bt ON tt.debit_bank_id_fk = bt.id INNER JOIN bank_tb bts ON tt.credit_bank_id_fk = bts.id;\r\n"
							+ "");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				BankDto dto = new BankDto();

				dto.setId(resultSet.getInt(1));
				dto.setIn_date(resultSet.getString(2));
				dto.setTransaction_amount(resultSet.getFloat(3));
				dto.setDebit_bank_id_fk(resultSet.getInt(4));
				dto.setCredit_bank_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setUser_id_fk(resultSet.getInt(8));
				dto.setRemark(resultSet.getString(9));
				dto.setCredit_online_id_fk(resultSet.getInt(10));
				dto.setDebit_online_id_fk(resultSet.getInt(11));
				dto.setDebit_bank_name(resultSet.getString(12));
				dto.setCredit_bank_name(resultSet.getString(13));

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
	public BankDto getTransactionInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		BankDto dto = new BankDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(
					"SELECT tt.id, tt.in_date, tt.transaction_amount, tt.debit_bank_id_fk, tt.credit_bank_id_fk, tt.current_in_date, tt.status, tt.user_id_fk,  tt.remark, tt.credit_online_id_fk, tt.debit_online_id_fk,  bt.name, bts.name "
							+ " FROM bank_transaction_tb tt INNER JOIN bank_tb bt ON tt.debit_bank_id_fk = bt.id INNER JOIN bank_tb bts ON tt.credit_bank_id_fk = bts.id Where tt.id = ?; ");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setIn_date(resultSet.getString(2));
				dto.setTransaction_amount(resultSet.getFloat(3));
				dto.setDebit_bank_id_fk(resultSet.getInt(4));
				dto.setCredit_bank_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setUser_id_fk(resultSet.getInt(8));
				dto.setRemark(resultSet.getString(9));
				dto.setCredit_online_id_fk(resultSet.getInt(10));
				dto.setDebit_online_id_fk(resultSet.getInt(11));
				dto.setDebit_bank_name(resultSet.getString(12));
				dto.setCredit_bank_name(resultSet.getString(13));

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
	public boolean updateTransaction(BankDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			// Insert Query of Transaction

			PreparedStatement ps = db.connection.prepareStatement("UPDATE bank_transaction_tb\r\n"
					+ "SET in_date = ?, transaction_amount = ?, debit_bank_id_fk = ?, credit_bank_id_fk = ?, STATUS = ?, user_id_fk = ?, remark = ?\r\n"
					+ "WHERE id = ?;");

			ps.setString(1, dto.getIn_date());
			ps.setFloat(2, dto.getTransaction_amount());
			ps.setInt(3, dto.getDebit_bank_id_fk());
			ps.setInt(4, dto.getCredit_bank_id_fk());
			ps.setString(5, dto.getStatus());
			ps.setInt(6, dto.getUser_id_fk());
			ps.setString(7, dto.getRemark());
			ps.setInt(8, dto.getId());

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) { 
				
				// update query in online Payment table
				PreparedStatement ps_online = db.connection.prepareStatement(
						"UPDATE online_payment_tb SET bank_id_fk = ?, debit = ?,remark =  ?, "
						+ " in_date = ?,status = ?, user_id_fk = ? WHERE id = ?;");

				ps_online.setInt(1, dto.getDebit_bank_id_fk());
				ps_online.setFloat(2, dto.getTransaction_amount());
				ps_online.setString(3, dto.getRemark());
				ps_online.setString(4, dto.getIn_date());
				ps_online.setString(5, dto.getStatus());
				ps_online.setInt(6, dto.getUser_id_fk());
				ps_online.setInt(7, dto.getDebit_online_id_fk());

				System.out.println(ps_online);

				ps_online.executeUpdate();
				
				
				// update query in Credit online Payment table
				PreparedStatement ps_online_credit = db.connection.prepareStatement("UPDATE online_payment_tb SET bank_id_fk = ?, credit = ?,remark =  ?, "
						+ " in_date = ?, status = ?, user_id_fk = ? WHERE id = ?;");

				ps_online_credit.setInt(1, dto.getCredit_bank_id_fk());
				ps_online_credit.setFloat(2, dto.getTransaction_amount());
				ps_online_credit.setString(3, dto.getRemark());
				ps_online_credit.setString(4, dto.getIn_date());
				ps_online_credit.setString(5, dto.getStatus());
				ps_online_credit.setInt(6, dto.getUser_id_fk());
				ps_online_credit.setInt(7, dto.getCredit_online_id_fk());

				System.out.println(ps_online_credit);

				ps_online_credit.executeUpdate();

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}
}
