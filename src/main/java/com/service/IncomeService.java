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
import com.dto.IncomeDto;
import com.mysql.jdbc.Statement;

public class IncomeService {
	public int maxId(HttpServletRequest request) {
		try {
			Connection connection = (Connection) new DataDb(request).connection;

			String dbname = connection.getCatalog();

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables "
							+ "WHERE table_name='income_tb' AND TABLE_SCHEMA=?");
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

	// ******** Income insert method Start
	public int insertIncomeInfo(IncomeDto dto, PaymentDto pay_dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		int new_cash_id = 0;
		int new_online_id = 0;

		try {
			// *************** insert query in income table*****************
			PreparedStatement insert_Income = db.connection.prepareStatement("INSERT INTO income_tb (head_id_fk,bank_id_fk,amount,cash_amount,online_amount,remark,online_remark,online_date,online_way,in_date,"
					+ "user_id_fk,payment_mode)VALUES(?,?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

			insert_Income.setInt(1, dto.getHead_id_fk());
			insert_Income.setInt(2, dto.getBank_id_fk());
			insert_Income.setFloat(3, dto.getCash_amount() + dto.getOnline_amount());

			insert_Income.setFloat(4, dto.getCash_amount());
			insert_Income.setFloat(5, dto.getOnline_amount());

			insert_Income.setString(6, dto.getRemark());
			insert_Income.setString(7, dto.getOnline_remark());

			insert_Income.setString(8, dto.getOnline_date());
			insert_Income.setString(9, dto.getOnline_way());
			insert_Income.setString(10, dto.getIn_date());

			insert_Income.setInt(11, dto.getUser_id_fk());

			insert_Income.setString(12, dto.getPayment_mode());

			System.out.println(insert_Income);

			insert_Income.executeUpdate();
			ResultSet rs = insert_Income.getGeneratedKeys();
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

			// *************** update cash and payment id in income tb*****************
			PreparedStatement update_Income = db.connection.prepareStatement("UPDATE income_tb SET cash_payment_id_fk = ? , online_payment_id_fk = ? WHERE id = ?;",
					Statement.RETURN_GENERATED_KEYS);

			update_Income.setInt(1, dto.getCash_payment_id_fk());
			update_Income.setInt(2, dto.getOnline_payment_id_fk());
			update_Income.setInt(3, dto.getId());

			System.out.println(update_Income);

			int i = update_Income.executeUpdate();

			if (i != 0) {

				return rs.getInt(1);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}// ******** income insert method End

	// Method for Show data on Manage page
	public ArrayList<IncomeDto> getIncomeInfo(String d1, String d2, String name, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		ArrayList<IncomeDto> list = new ArrayList<IncomeDto>();

		try {
			// Select query for showing data on manage table
			String sql="SELECT 	s.id,s.head_id_fk,s.bank_id_fk,s.amount,s.cash_amount,s.online_amount,s.remark,s.online_remark,s.online_date,s.online_way,s.current_in_date,s.in_date, "
					+ "s.status,s.online_payment_id_fk,s.cash_payment_id_fk,s.user_id_fk,s.payment_mode,st.name, bt.name FROM income_tb s INNER JOIN user_personal_info_tb up ON up.id=s.user_id_fk "
					+ "INNER JOIN income_head_tb st ON st.id=s.head_id_fk LEFT JOIN bank_tb bt ON bt.id=s.bank_id_fk";

			if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && !name.equalsIgnoreCase("")) {
				ps = db.connection.prepareStatement( sql + " WHERE s.in_date BETWEEN ? AND ? AND st.name=?;");
				ps.setString(1, d1);
				ps.setString(2, d2);
				ps.setString(3, name);
			} 
			else if (!name.equalsIgnoreCase("")) {
				ps = db.connection.prepareStatement(sql+ " WHERE st.name=?;");
				ps.setString(1, name);
			} 
			else if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && name.equalsIgnoreCase("")) {
				ps = db.connection.prepareStatement(sql + "	WHERE s.in_date BETWEEN ? AND ?;");
				ps.setString(1, d1);
				ps.setString(2, d2);
			} else {
				ps = db.connection.prepareStatement( sql+ ";");
			}

			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				IncomeDto dto = new IncomeDto();

				dto.setId(resultSet.getInt(1));
				dto.setHead_id_fk(resultSet.getInt(2));
				dto.setBank_id_fk(resultSet.getInt(3));
				dto.setAmount(resultSet.getFloat(4));

				dto.setCash_amount(resultSet.getFloat(5));
				dto.setOnline_amount(resultSet.getFloat(6));

				dto.setRemark(resultSet.getString(7));
				dto.setOnline_remark(resultSet.getString(8));

				dto.setOnline_date(resultSet.getString(9));
				dto.setOnline_way(resultSet.getString(10));
				dto.setCurrent_in_date(resultSet.getString(11));
				dto.setIn_date(resultSet.getString(12));
				dto.setStatus(resultSet.getString(13));
				dto.setOnline_payment_id_fk(resultSet.getInt(14));
				dto.setCash_payment_id_fk(resultSet.getInt(15));

				dto.setUser_id_fk(resultSet.getInt(16));

				dto.setPayment_mode(resultSet.getString(17));
				dto.setIncome_name(resultSet.getString(18));
				dto.setBank_name(resultSet.getString(19));

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
	public IncomeDto getIncomeInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		IncomeDto dto = new IncomeDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement("SELECT 	s.id,s.head_id_fk,s.bank_id_fk,s.amount,s.cash_amount,s.online_amount,s.remark,s.online_remark,s.online_date,s.online_way,s.current_in_date,s.in_date, s.status,s.online_payment_id_fk,s.cash_payment_id_fk,s.user_id_fk,s.payment_mode,st.name, bt.name FROM income_tb s INNER JOIN user_personal_info_tb up ON up.id=s.user_id_fk INNER JOIN income_head_tb st ON st.id=s.head_id_fk LEFT JOIN bank_tb bt ON bt.id=s.bank_id_fk WHERE s.id=?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setHead_id_fk(resultSet.getInt(2));
				dto.setBank_id_fk(resultSet.getInt(3));
				dto.setAmount(resultSet.getFloat(4));

				dto.setCash_amount(resultSet.getFloat(5));
				dto.setOnline_amount(resultSet.getFloat(6));

				dto.setRemark(resultSet.getString(7));
				dto.setOnline_remark(resultSet.getString(8));

				dto.setOnline_date(resultSet.getString(9));
				dto.setOnline_way(resultSet.getString(10));
				dto.setCurrent_in_date(resultSet.getString(11));
				dto.setIn_date(resultSet.getString(12));
				dto.setStatus(resultSet.getString(13));
				dto.setOnline_payment_id_fk(resultSet.getInt(14));
				dto.setCash_payment_id_fk(resultSet.getInt(15));

				dto.setUser_id_fk(resultSet.getInt(16));

				dto.setPayment_mode(resultSet.getString(17));
				dto.setIncome_name(resultSet.getString(18));
				dto.setBank_name(resultSet.getString(19));

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

	// ******** income Update method Start
	public boolean updateIncomeInfo(IncomeDto dto, PaymentDto pay_dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		float old_amount = 0;
		String old_payment_mode = "";

		int new_cash_id = 0;
		int new_online_id = 0;

		try {
			// Select query for old Paid amount
			PreparedStatement ps1 = db.connection
					.prepareStatement("SELECT 	amount,payment_mode,cash_payment_id_fk,online_payment_id_fk\n"
							+ "	FROM income_tb WHERE id=?;");

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

			// ********* Update query in income table **********
			PreparedStatement ps = db.connection.prepareStatement("UPDATE income_tb SET amount = ?,cash_amount = ?,online_amount = ?,remark = ?,payment_mode = ?,online_date = ?,online_way = ?,online_remark = ?, bank_id_fk = ?,user_id_fk = ?,cash_payment_id_fk = ?,online_payment_id_fk = ?,head_id_fk=? WHERE id = ?;");

			ps.setFloat(1, dto.getCash_amount() + dto.getOnline_amount());
			ps.setFloat(2, dto.getCash_amount());
			ps.setFloat(3, dto.getOnline_amount());

			ps.setString(4, dto.getRemark());
			ps.setString(5, dto.getPayment_mode());
			ps.setString(6, dto.getOnline_date());
			ps.setString(7, dto.getOnline_way());
			ps.setString(8, dto.getOnline_remark());
			ps.setInt(9, dto.getBank_id_fk());
			ps.setInt(10, dto.getUser_id_fk());
			ps.setInt(11, dto.getCash_payment_id_fk());
			ps.setInt(12, dto.getOnline_payment_id_fk());

			ps.setInt(13, dto.getHead_id_fk());

			ps.setInt(14, dto.getId());

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				return true;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}// ******** income Update method End

}
