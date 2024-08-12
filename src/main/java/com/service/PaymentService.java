package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.PaymentDto;
import com.mysql.jdbc.Statement;

public class PaymentService {

	// ******** Cash insert method Start
	public int insertCashPayment(PaymentDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {
				// insert query in Cash Payment table
				PreparedStatement ps = db.connection.prepareStatement(
						"INSERT INTO cash_payment_tb \n" + "	(credit,debit,remark,\n"
								+ "	in_date,bill_id_fk,STATUS,TYPE)\n" + "	VALUES (?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				ps.setFloat(1, dto.getCash_amount());
				ps.setFloat(2, dto.getDebit_cash_amount());
				ps.setString(3, dto.getRemark());
				ps.setString(4, dto.getIn_date());
				ps.setInt(5, dto.getBill_id_fk());
				ps.setString(6, "Active");
				ps.setString(7, dto.getType());

				System.out.println(ps);

				int i = ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				if (i != 0) {
					return rs.getInt(1);
				}
			

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}// ******** Cash insert method End

	// ******** Online Cash Update method Start
	public boolean updateCashPayment(PaymentDto dto, int cash_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

				// update query in Cash Payment table
				PreparedStatement ps = db.connection.prepareStatement("UPDATE cash_payment_tb SET \n"
						+ "	credit = ?,debit = ?,remark = ?,in_date = ?,  \n" + "	STATUS = ? WHERE id = ?;");

				ps.setFloat(1, dto.getCash_amount());
				ps.setFloat(2, dto.getDebit_cash_amount());
				ps.setString(3, dto.getRemark());
				ps.setString(4, dto.getIn_date());
				ps.setString(5, "Active");

				ps.setInt(6, cash_id);

				System.out.println(ps);

				int i = ps.executeUpdate();

				if (i != 0) {
					return true;
				}
			

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}// ******** Cash Payment Update method End

	// ******** Online Cash Delete method Start
	public boolean deleteCashPayment(int cash_id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {
			// delete query in Cash Payment table
			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM cash_payment_tb WHERE id = ? ;");

			ps.setInt(1, cash_id);

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}// ******** Cash Payment Delete method End

	// ******** Online Payment insert method Start
	public int insertOnlinePayment(PaymentDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

				// insert query in online Payment table
				PreparedStatement ps = db.connection.prepareStatement("INSERT INTO online_payment_tb \n"
						+ "	(bank_id_fk,credit,debit,remark,online_remark,online_date, \n"
						+ "	online_way,in_date,bill_id_fk,STATUS,TYPE)\n" + "	VALUES (?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				ps.setInt(1, dto.getBank_id_fk());
				ps.setFloat(2, dto.getOnline_amount());
				ps.setFloat(3, dto.getDebit_online_amount());
				ps.setString(4, dto.getRemark());
				ps.setString(5, dto.getOnline_remark());
				ps.setString(6, dto.getOnline_date());
				ps.setString(7, dto.getOnline_way());
				ps.setString(8, dto.getIn_date());
				ps.setInt(9, dto.getBill_id_fk());
				ps.setString(10, dto.getStatus());
				ps.setString(11, dto.getType());

				System.out.println(ps);

				int i = ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				if (i != 0) {
					return rs.getInt(1);
				}
			
		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}// ******** Online Payment insert method End

	// ******** Online Payment Update method Start
	public boolean updateOnlinePayment(PaymentDto dto, int online_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {
			// update query in online Payment table
				PreparedStatement ps = db.connection.prepareStatement(
						"UPDATE online_payment_tb SET  bank_id_fk = ?,credit = ?,debit = ?,remark =  ?,"
								+ "	online_remark = ?,online_date = ?,online_way =  ?,"
								+ "	in_date = ?,STATUS = ? WHERE id = ?;");

				ps.setInt(1, dto.getBank_id_fk());
				ps.setFloat(2, dto.getOnline_amount());
				ps.setFloat(3, dto.getDebit_online_amount());
				ps.setString(4, dto.getRemark());
				ps.setString(5, dto.getOnline_remark());
				ps.setString(6, dto.getOnline_date());
				ps.setString(7, dto.getOnline_way());
				ps.setString(8, dto.getIn_date());
				ps.setString(9, dto.getStatus());

				ps.setInt(10, online_id);

				System.out.println(ps);

				int i = ps.executeUpdate();

				if (i != 0) {
					return true;
				}
			
		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}// ******** Online Payment Update method End

	// ******** Online Payment Delete method Start
	public boolean deleteOnlinePayment(int online_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {
			// delete query in online Payment table
			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM online_payment_tb WHERE id = ? ;");

			ps.setInt(1, online_id);

			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}// ******** Online Payment Delete method End

}