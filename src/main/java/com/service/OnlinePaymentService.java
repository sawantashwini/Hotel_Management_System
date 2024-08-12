package com.service;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.OnlinePaymentDto;

public class OnlinePaymentService {

	public ArrayList<OnlinePaymentDto> getOnlinePaymentInfo(String d1, String d2, int bank_id,
			ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		ArrayList<OnlinePaymentDto> list = new ArrayList<OnlinePaymentDto>();

		try {

			String sql = "SELECT ot.id, bank_id_fk, credit, debit, remark, online_remark, online_date,\n"
					+ "online_way, ot.current_in_date, in_date, bill_id_fk, ot.status, ot.type, bt.name,\n"
					+ "bt.account_no, bt.branch, bt.ifsc_code, ot.user_id_fk, up.name FROM online_payment_tb ot \n"
					+ "LEFT JOIN bank_tb bt ON ot.bank_id_fk=bt.id INNER JOIN user_personal_info_tb up ON ot.user_id_fk = up.id";

			if ("".equalsIgnoreCase(d1) && "".equalsIgnoreCase(d2) && bank_id == 0) {

				// Select query for showing data on manage table
				ps = db.connection.prepareStatement(sql + " ;");
			}

			else if (!"".equalsIgnoreCase(d1) && !"".equalsIgnoreCase(d2)&& bank_id == 0) {

				// Select query for showing data on manage table
				ps = db.connection.prepareStatement(sql + "  WHERE in_date BETWEEN ? AND ? ORDER BY in_date;");

				ps.setString(1, d1);
				ps.setString(2, d2);

			}

			else if (bank_id != 0&& "".equalsIgnoreCase(d1) && "".equalsIgnoreCase(d2)) {

				// Select query for showing data on manage table
				ps = db.connection.prepareStatement(sql + " WHERE bank_id_fk = ? ORDER BY in_date;");

				ps.setInt(1, bank_id);

			}  else if (!"".equalsIgnoreCase(d1) && !"".equalsIgnoreCase(d2) && bank_id != 0) {

				// Select query for showing data on manage table
				ps = db.connection
						.prepareStatement(sql + " WHERE in_date BETWEEN ? AND ? AND bank_id_fk = ? ORDER BY in_date;");

				ps.setString(1, d1);
				ps.setString(2, d2);
				ps.setInt(3, bank_id);

			} 
			ResultSet resultSet = ps.executeQuery();
			System.out.println(ps);

			while (resultSet.next()) {

				OnlinePaymentDto dto = new OnlinePaymentDto();

				dto.setId(resultSet.getInt(1));
				dto.setBank_id_fk(resultSet.getInt(2));
				dto.setCredit(resultSet.getFloat(3));
				dto.setDebit(resultSet.getFloat(4));
				dto.setRemark(resultSet.getString(5));
				dto.setOnline_remark(resultSet.getString(6));
				dto.setOnline_date(resultSet.getString(7));
				dto.setOnline_way(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setIn_date(resultSet.getString(10));
				dto.setBill_id_fk(resultSet.getInt(11));
				dto.setStatus(resultSet.getString(12));
				dto.setType(resultSet.getString(13));
				dto.setBank_name(resultSet.getString(14));
				dto.setAccount_no(resultSet.getString(15));
				dto.setBranch(resultSet.getString(16));
				dto.setIfsc(resultSet.getString(17));
				dto.setUser_id_fk(resultSet.getInt(18));
				dto.setUser_name(resultSet.getString(19));
				
				if(dto.getDebit()!=0 || dto.getCredit()!=0) {
					list.add(dto);
				}
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

	public ArrayList<OnlinePaymentDto> getAllTransactioReport(String date1, String date2, int bank_id_fk,
			HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		ArrayList<OnlinePaymentDto> list = new ArrayList<OnlinePaymentDto>();
		OnlinePaymentDto dto = null;
		PreparedStatement ps = null;
		try {
			String sql = "SELECT ot.id, ot.bank_id_fk, ot.credit, ot.debit, remark, ot.online_remark, ot.online_date, ot.online_way, ot.current_in_date,\n"
					+ " ot.in_date, ot.bill_id_fk, ot.status, ot.type, bt.name, bt.account_no, bt.branch, bt.ifsc_code, ot.user_id_fk, up.name\n"
					+ "FROM online_payment_tb ot LEFT JOIN bank_tb bt ON ot.bank_id_fk=bt.id INNER JOIN user_personal_info_tb up ON ot.user_id_fk = up.id";

			if ("".equalsIgnoreCase(date1) && "".equalsIgnoreCase(date2) && bank_id_fk != 0) {

				ps = db.connection.prepareStatement(sql + " where bank_id_fk =? ORDER BY in_date ; ");
				ps.setInt(1, bank_id_fk);
			}

			else if (!"".equalsIgnoreCase(date1) && !"".equalsIgnoreCase(date2) && bank_id_fk == 0) {

				ps = db.connection.prepareStatement(sql + " WHERE in_date BETWEEN ? AND ? ORDER BY in_date;");

				ps.setString(1, date1);
				ps.setString(2, date2);

			}

			else if (!"".equalsIgnoreCase(date1) && !"".equalsIgnoreCase(date2) && bank_id_fk != 0) {

				ps = db.connection
						.prepareStatement(sql + "  WHERE in_date BETWEEN ? AND ? AND bank_id_fk =? ORDER BY in_date;");

				ps.setString(1, date1);
				ps.setString(2, date2);
				ps.setInt(3, bank_id_fk);

			} else {

				ps = db.connection.prepareStatement(sql + " ;");
			}

			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				dto = new OnlinePaymentDto();

				dto.setId(resultSet.getInt(1));
				dto.setBank_id_fk(resultSet.getInt(2));
				dto.setCredit(resultSet.getFloat(3));
				dto.setDebit(resultSet.getFloat(4));
				dto.setRemark(resultSet.getString(5));
				dto.setOnline_remark(resultSet.getString(6));
				dto.setOnline_date(resultSet.getString(7));
				dto.setOnline_way(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setIn_date(resultSet.getString(10));
				dto.setBill_id_fk(resultSet.getInt(11));
				dto.setStatus(resultSet.getString(12));
				dto.setType(resultSet.getString(13));
				dto.setBank_name(resultSet.getString(14));
				dto.setAccount_no(resultSet.getString(15));
				dto.setBranch(resultSet.getString(16));
				dto.setIfsc(resultSet.getString(17));
				dto.setUser_id_fk(resultSet.getInt(18));
				dto.setUser_name(resultSet.getString(19));

				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
			LogFileService.catchLogFile(e, config);
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* ignored */
				}
			}

			if (db.connection != null) {
				try {
					db.connection.close();
				} catch (SQLException e) { /* ignored */
				}
			}

		}
		return list;
	}

}