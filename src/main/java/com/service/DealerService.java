package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;

import com.dto.DealerDto;
import com.dto.PaymentDto;
import com.mysql.jdbc.Statement;

public class DealerService {

	// Insert Method for Dealer Item
	public boolean insertDealer(DealerDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps_acc = null;
		int dealer_acc_id = 0;
		int dealer_id = 0;

		try {

			// Insert Query of PathologyItem

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO dealer_info_tb (user_id_fk, Name, mobile_no, address, gst_no, old_due, opening_due) \r\n"
							+ "VALUES (?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, dto.getUser_id_fk());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getMobile_no());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getGst_no());
			ps.setFloat(6, dto.getOld_due());
			ps.setFloat(7, dto.getOld_due());

			System.out.println(ps);
			ps.executeUpdate();

			ResultSet rs_dealer = ps.getGeneratedKeys();
			rs_dealer.next();
			dealer_id = rs_dealer.getInt(1);

			// **** insert payment record (Dealer Due Amount) in dealer account table.
			ps_acc = db.connection.prepareStatement("INSERT INTO dealer_account_tb "
					+ "( user_id_fk, dealer_id_fk, c_y_session, credit_amt,debit_amt, type, in_date )\r\n"
					+ "VALUES(?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

			ps_acc.setInt(1, dto.getUser_id_fk());
			ps_acc.setInt(2, dealer_id);
			ps_acc.setString(3, dto.getC_y_session());
			ps_acc.setFloat(4, dto.getOld_due());
			ps_acc.setFloat(5, 0);
			ps_acc.setString(6, "Opening Due");
			ps_acc.setString(7, dto.getIn_date());

			System.out.println(ps_acc);
			int i = ps_acc.executeUpdate();

			ResultSet rs_acc = ps_acc.getGeneratedKeys();
			rs_acc.next();
			dealer_acc_id = rs_acc.getInt(1);

			if (i != 0) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	// Method for Show data on Manage page
	public ArrayList<DealerDto> getDealerInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<DealerDto> list = new ArrayList<DealerDto>();

		try {

			String sql = "SELECT id,user_id_fk, NAME, mobile_no, address, gst_no, old_due, opening_due, current_in_date, STATUS FROM dealer_info_tb;";
			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				DealerDto dto = new DealerDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setName(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setGst_no(resultSet.getString(6));
				dto.setOld_due(resultSet.getFloat(7));
				dto.setOpening_due(resultSet.getFloat(8));

				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));

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

	public DealerDto getDealerInfoByName(String name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		DealerDto dto = new DealerDto();

		try {

			String sql = "SELECT id,user_id_fk, Name, mobile_no, address, gst_no, old_due, opening_due, current_in_date, Status FROM dealer_info_tb WHERE  name=?;";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setName(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setGst_no(resultSet.getString(6));
				dto.setOld_due(resultSet.getFloat(7));
				dto.setOpening_due(resultSet.getFloat(8));

				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));

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

	// Show data on edit page according to id
	public DealerDto getDealerInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		DealerDto dto = new DealerDto();

		try {

			String sql = "SELECT id,user_id_fk, Name, mobile_no, address, gst_no, old_due, opening_due, current_in_date, Status FROM dealer_info_tb WHERE  id=?;";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setName(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setGst_no(resultSet.getString(6));
				dto.setOld_due(resultSet.getFloat(7));
				dto.setOpening_due(resultSet.getFloat(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));

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

	// Show data on edit page according to id
	public DealerDto getDealerInfoByDueId(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		DealerDto dto = new DealerDto();

		try {

			String sql = "SELECT dt.id, it.user_id_fk, it.name, it.mobile_no, it.address, it.gst_no, it.old_due, it.opening_due, dt.status, \r\n"
					+ " dt.pay_date, dt.remark, dt.payment_mode, dt.online_way, dt.online_remark, \r\n"
					+ " dt.online_amount, dt.cash_amount, dt.online_date, dt.bank_id_fk, bt.name\r\n"
					+ " FROM dealer_due_tb dt INNER JOIN  dealer_info_tb it ON dt.dealer_id_fk = it.id \r\n"
					+ " INNER JOIN  bank_tb bt ON dt.bank_id_fk = bt.id WHERE  dt.id= ?;";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setName(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setGst_no(resultSet.getString(6));
				dto.setOld_due(resultSet.getFloat(7));
				dto.setOpening_due(resultSet.getFloat(8));
				dto.setStatus(resultSet.getString(9));
				dto.setPay_date(resultSet.getString(10));
				dto.setRemark(resultSet.getString(11));
				dto.setPayment_mode(resultSet.getString(12));
				dto.setOnline_way(resultSet.getString(13));
				dto.setOnline_remark(resultSet.getString(14));
				dto.setOnline_amount(resultSet.getFloat(15));
				dto.setCash_amount(resultSet.getFloat(16));
				dto.setOnline_date(resultSet.getString(17));
				dto.setBank_id_fk(resultSet.getInt(18));
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

	// Method For Updating data on edit page
	public boolean updateDealer(DealerDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			String sql = "UPDATE dealer_info_tb SET user_id_fk=?, Name=?, mobile_no=?, address=?, gst_no=?, Status=? WHERE id=?;";
			// Update Query for update data
			ps = db.connection.prepareStatement(sql);

			ps.setInt(1, dto.getUser_id_fk());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getMobile_no());
			ps.setString(4, dto.getAddress());
			ps.setString(5, dto.getGst_no());
			ps.setString(6, dto.getStatus());
			ps.setInt(7, dto.getId());

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

	public ArrayList<DealerDto> getActiveDealerInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<DealerDto> list = new ArrayList<DealerDto>();

		try {

			String sql = "SELECT id,user_id_fk, Name, mobile_no, address, gst_no, old_due, opening_due, in_date, current_in_date, Status FROM dealer_info_tb WHERE Status='Active';";
			preparedStatement = db.connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				DealerDto dto = new DealerDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setName(resultSet.getString(3));
				dto.setMobile_no(resultSet.getString(4));
				dto.setAddress(resultSet.getString(5));
				dto.setGst_no(resultSet.getString(6));
				dto.setOld_due(resultSet.getFloat(7));
				dto.setOpening_due(resultSet.getFloat(8));
				dto.setIn_date(resultSet.getString(9));
				dto.setCurrent_in_date(resultSet.getString(10));
				dto.setStatus(resultSet.getString(11));

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

	// *****Start pay dealer due ****

	PaymentService pay_ser = new PaymentService();

	// *****Start pay dealer due ****

	public int payDealerDue(PaymentDto pay_dto, DealerDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		int dealer_due_id = 0, new_cash_id = 0, new_online_id = 0, dealer_acc_id = 0;

		try {

			// ***** Insert new record in dealer due table.

			PreparedStatement ps_due = db.connection.prepareStatement(
					"INSERT INTO dealer_due_tb ( dealer_id_fk, online_payment_id_fk, cash_payment_id_fk, c_y_session, pay_amount, pay_date, \r\n"
							+ "remark, payment_mode, online_way, online_remark, online_amount, cash_amount, online_date, bank_id_fk )\r\n"
							+ "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps_due.setInt(1, dto.getDealer_id_fk());
			ps_due.setFloat(2, dto.getOnline_payment_id_fk());
			ps_due.setFloat(3, dto.getCash_payment_id_fk());
			ps_due.setString(4, dto.getC_y_session());
			ps_due.setFloat(5, dto.getCash_amount() + dto.getOnline_amount());
			ps_due.setString(6, dto.getPay_date());
			ps_due.setString(7, dto.getRemark());
			ps_due.setString(8, dto.getPayment_mode());
			ps_due.setString(9, dto.getOnline_way());
			ps_due.setString(10, dto.getOnline_remark());
			ps_due.setFloat(11, dto.getOnline_amount());
			ps_due.setFloat(12, dto.getCash_amount());
			ps_due.setString(13, dto.getOnline_date());
			ps_due.setInt(14, dto.getBank_id_fk());

			System.out.println(ps_due);
			int i = ps_due.executeUpdate();

			ResultSet rs_due = ps_due.getGeneratedKeys();
			rs_due.next();
			dealer_due_id = rs_due.getInt(1);

			pay_dto.setBill_id_fk(dto.getDealer_id_fk());

			// **** this statement is used to reduce due amount from dealer table
			if (i != 0) {

				String sql = "UPDATE dealer_info_tb SET old_due=old_due-?  WHERE id=?;";
				// Update Query for update data
				PreparedStatement ps_reduce = db.connection.prepareStatement(sql);

				ps_reduce.setFloat(1, dto.getCash_amount() + dto.getOnline_amount());
				ps_reduce.setInt(2, dto.getDealer_id_fk());

				System.out.println(ps_reduce);

				ps_reduce.executeUpdate();

				// **** insert payment record (Dealer Due Amount) in dealer account table.
				PreparedStatement ps_acc = db.connection.prepareStatement(
						"INSERT INTO dealer_account_tb ( user_id_fk, dealer_id_fk, c_y_session, credit_amt, debit_amt, type, payment_mode, \r\n"
								+ "online_way, online_remark, online_date, online_amount, cash_amount, in_date,remark,bill_id_fk )\r\n"
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
						Statement.RETURN_GENERATED_KEYS);

				ps_acc.setInt(1, dto.getUser_id_fk());
				ps_acc.setInt(2, dto.getDealer_id_fk());
				ps_acc.setString(3, dto.getC_y_session());
				ps_acc.setFloat(4, 0);
				ps_acc.setFloat(5, dto.getCash_amount() + dto.getOnline_amount());
				ps_acc.setString(6, dto.getType());
				ps_acc.setString(7, dto.getPayment_mode());
				ps_acc.setString(8, dto.getOnline_way());
				ps_acc.setString(9, dto.getOnline_remark());
				ps_acc.setString(10, dto.getOnline_date());
				ps_acc.setFloat(11, dto.getOnline_amount());
				ps_acc.setFloat(12, dto.getCash_amount());
				ps_acc.setString(13, dto.getPay_date());
				ps_acc.setString(14, dto.getRemark());
				ps_acc.setInt(15, dealer_due_id);

				System.out.println(ps_acc);
				ps_acc.executeUpdate();

				ResultSet rs_acc = ps_acc.getGeneratedKeys();
				rs_acc.next();
				dealer_acc_id = rs_acc.getInt(1);

				// *************** insert cash and payment tb*****************

				// ****** when Payment mode is both ********
				if (dto.getPayment_mode().equalsIgnoreCase("Both")) {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);
				}
				// ****** when Payment mode is online ********
				else if (dto.getPayment_mode().equalsIgnoreCase("Online")) {

					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when Payment mode is cash ********
				else {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				}

				dto.setCash_payment_id_fk(new_cash_id);
				dto.setOnline_payment_id_fk(new_online_id);

				// *************** update cash and payment id in dealer_due_tb *****************
				PreparedStatement update_due_tb = db.connection
						.prepareStatement("UPDATE dealer_due_tb SET\n" + "	cash_payment_id_fk = ? , \n"
								+ "	online_payment_id_fk = ?," + " dealer_account_id_fk = ? \n" + "	WHERE id = ?;");

				update_due_tb.setInt(1, dto.getCash_payment_id_fk());
				update_due_tb.setInt(2, dto.getOnline_payment_id_fk());
				update_due_tb.setInt(3, dealer_acc_id);
				update_due_tb.setInt(4, dealer_due_id);

				System.out.println(update_due_tb);

				int i2 = update_due_tb.executeUpdate();

				if (i2 != 0) {
					return dealer_due_id;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return 0;
	}

	// **** start: Method for Show data on Manage dealer due
	public DealerDto getDealerDueInfoById(Integer id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		DealerDto dto = new DealerDto();

		try {

			String sql = "SELECT 	ddt.id, ddt.dealer_id_fk, ddt.dealer_account_id_fk, ddt.online_payment_id_fk, ddt.cash_payment_id_fk, ddt.c_y_session, ddt.pay_amount, \r\n"
					+ "	ddt.pay_date, ddt.remark, ddt.payment_mode, ddt.online_way, ddt.online_remark, ddt.online_amount, ddt.cash_amount, ddt.online_date, ddt.bank_id_fk,\r\n"
					+ "	ddt.current_in_date,\r\n"
					+ "	dit.NAME, dit.mobile_no, dit.address, dit.gst_no, dit.old_due, dit.opening_due\r\n"
					+ "	FROM dealer_due_tb ddt LEFT JOIN dealer_info_tb dit ON ddt.dealer_id_fk = dit.id\r\n"
					+ "	LEFT JOIN dealer_account_tb dat ON ddt.dealer_account_id_fk = dat.id  WHERE ddt.id = ?;";
			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setDealer_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setOnline_payment_id_fk(resultSet.getInt(4));
				dto.setCash_payment_id_fk(resultSet.getInt(5));
				dto.setC_y_session(resultSet.getString(6));
				dto.setPay_amount(resultSet.getFloat(7));
				dto.setPay_date(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));
				dto.setPayment_mode(resultSet.getString(10));
				dto.setOnline_way(resultSet.getString(11));
				dto.setOnline_remark(resultSet.getString(12));
				dto.setOnline_amount(resultSet.getFloat(13));
				dto.setCash_amount(resultSet.getFloat(14));
				dto.setOnline_date(resultSet.getString(15));
				dto.setBank_id_fk(resultSet.getInt(16));
				dto.setCurrent_in_date(resultSet.getString(17));
				dto.setName(resultSet.getString(18));
				dto.setMobile_no(resultSet.getString(19));
				dto.setAddress(resultSet.getString(20));
				dto.setGst_no(resultSet.getString(21));
				dto.setOld_due(resultSet.getFloat(22));
				dto.setOpening_due(resultSet.getFloat(23));
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

//****** start Update Dealer Due 

	public int UpdateDealerDue(PaymentDto pay_dto, DealerDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		PreparedStatement ps_acc = null;
		PreparedStatement ps_due = null;
		PreparedStatement ps_increase = null;

		String old_payment_mode = "";
		int new_cash_id = 0, new_online_id = 0;
		float old_paid_amount = 0;

		try {

			PreparedStatement select_det = db.connection.prepareStatement(
					"SELECT 	dealer_account_id_fk,dealer_id_fk, pay_amount, payment_mode,cash_payment_id_fk,online_payment_id_fk \r\n"
							+ "FROM dealer_due_tb\r\n" + "WHERE id= ?;");

			select_det.setInt(1, dto.getId());
			System.out.println(select_det);
			ResultSet resultSet = select_det.executeQuery();

			while (resultSet.next()) {

				dto.setDealer_account_id_fk(resultSet.getInt(1));
				dto.setDealer_id_fk(resultSet.getInt(2));
				old_paid_amount = resultSet.getFloat(3);
				old_payment_mode = (resultSet.getString(4));
				dto.setCash_payment_id_fk(resultSet.getInt(5));
				dto.setOnline_payment_id_fk(resultSet.getInt(6));

			}

			// clear customer old due info
			String sql = "UPDATE dealer_info_tb SET old_due = old_due+?  WHERE id=?;";
			// Update Query for update data
			ps_increase = db.connection.prepareStatement(sql);

			ps_increase.setFloat(1, old_paid_amount - (dto.getCash_amount() + dto.getOnline_amount()));
			ps_increase.setInt(2, dto.getDealer_id_fk());

			System.out.println(ps_increase);
			ps_increase.executeUpdate();

			String sql4 = "UPDATE dealer_account_tb SET dealer_id_fk = ? ,user_id_fk = ? ,c_y_session = ? ,credit_amt = ? , \r\n"
					+ "debit_amt = ? ,TYPE = ? ,online_way = ? ,online_remark = ? ,online_date = ? ,online_amount = ? ,payment_mode = ? ,in_date = ? , \r\n"
					+ " cash_amount = ? ,STATUS = ?, remark=? WHERE id = ? ;";

			// **** Update payment record (Dealer Due Amount) in dealer account table.
			ps_acc = db.connection.prepareStatement(sql4);

			ps_acc.setInt(1, dto.getDealer_id_fk());
			ps_acc.setInt(2, dto.getUser_id_fk());

			ps_acc.setString(3, dto.getC_y_session());

			ps_acc.setFloat(4, 0);
			ps_acc.setFloat(5, dto.getCash_amount() + dto.getOnline_amount());
			ps_acc.setString(6, dto.getType());

			ps_acc.setString(7, dto.getOnline_way());
			ps_acc.setString(8, dto.getOnline_remark());
			ps_acc.setString(9, dto.getOnline_date());
			ps_acc.setFloat(10, dto.getOnline_amount());
			ps_acc.setString(11, dto.getPayment_mode());
			ps_acc.setString(12, dto.getPay_date());
			ps_acc.setFloat(13, dto.getCash_amount());

			ps_acc.setString(14, dto.getStatus());
			ps_acc.setString(15, dto.getRemark());
			ps_acc.setInt(16, dto.getDealer_account_id_fk());

			System.out.println(ps_acc);
			ps_acc.executeUpdate();

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

			// ***** Update in dealer due table.
			String sql3 = "UPDATE dealer_due_tb SET c_y_session = ? ,pay_amount = ? , \r\n"
					+ "pay_date = ? ,remark = ? ,online_way = ? ,online_remark = ? ,online_date = ? ,payment_mode = ? ,\r\n"
					+ "online_amount = ? ,cash_amount = ? ,\r\n"
					+ "bank_id_fk = ? ,online_payment_id_fk = ? ,cash_payment_id_fk = ? ,STATUS = ?\r\n"
					+ "WHERE id = ? ;";

			ps_due = db.connection.prepareStatement(sql3);

			ps_due.setString(1, dto.getC_y_session());
			ps_due.setFloat(2, dto.getCash_amount() + dto.getOnline_amount());
			ps_due.setString(3, dto.getPay_date());
			ps_due.setString(4, dto.getRemark());

			ps_due.setString(5, dto.getOnline_way());
			ps_due.setString(6, dto.getOnline_remark());

			ps_due.setString(7, dto.getOnline_date());
			ps_due.setString(8, dto.getPayment_mode());
			ps_due.setFloat(9, dto.getOnline_amount());
			ps_due.setFloat(10, dto.getCash_amount());
			ps_due.setInt(11, dto.getBank_id_fk());
			ps_due.setFloat(12, dto.getOnline_payment_id_fk());
			ps_due.setFloat(13, dto.getCash_payment_id_fk());
			ps_due.setString(14, dto.getStatus());
			ps_due.setInt(15, dto.getId());

			System.out.println(ps_due);
			ps_due.executeUpdate();

			return dto.getDealer_id_fk();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// ****** End Update Dealer Due

	// **** start: Method for Show data on Manage dealer due
	public ArrayList<DealerDto> getDealerDueInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<DealerDto> list = new ArrayList<DealerDto>();

		try {

			String sql = "SELECT 	ddt.id, ddt.dealer_id_fk, ddt.dealer_account_id_fk, ddt.online_payment_id_fk, ddt.cash_payment_id_fk, ddt.c_y_session, ddt.pay_amount, \r\n"
					+ "	ddt.pay_date, ddt.remark, ddt.payment_mode, ddt.online_way, ddt.online_remark, ddt.online_amount, ddt.cash_amount, ddt.online_date, ddt.bank_id_fk,\r\n"
					+ "	ddt.current_in_date,\r\n"
					+ "	dit.NAME, dit.mobile_no, dit.address, dit.gst_no, dit.old_due, dit.opening_due\r\n"
					+ "	FROM dealer_due_tb ddt LEFT JOIN dealer_info_tb dit ON ddt.dealer_id_fk = dit.id\r\n"
					+ "	LEFT JOIN dealer_account_tb dat ON ddt.dealer_account_id_fk = dat.id;";
			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				DealerDto dto = new DealerDto();

				dto.setId(resultSet.getInt(1));
				dto.setDealer_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setOnline_payment_id_fk(resultSet.getInt(4));
				dto.setCash_payment_id_fk(resultSet.getInt(5));
				dto.setC_y_session(resultSet.getString(6));
				dto.setPay_amount(resultSet.getFloat(7));
				dto.setPay_date(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));
				dto.setPayment_mode(resultSet.getString(10));
				dto.setOnline_way(resultSet.getString(11));
				dto.setOnline_remark(resultSet.getString(12));
				dto.setOnline_amount(resultSet.getFloat(13));
				dto.setCash_amount(resultSet.getFloat(14));
				dto.setOnline_date(resultSet.getString(15));
				dto.setBank_id_fk(resultSet.getInt(16));
				dto.setCurrent_in_date(resultSet.getString(17));
				dto.setName(resultSet.getString(18));
				dto.setMobile_no(resultSet.getString(19));
				dto.setAddress(resultSet.getString(20));
				dto.setGst_no(resultSet.getString(21));
				dto.setOld_due(resultSet.getFloat(22));
				dto.setOpening_due(resultSet.getFloat(23));
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

	// **** start: Method for Show data on Manage dealer due
	public ArrayList<DealerDto> getDealerDueInfoByDealerId(Integer id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<DealerDto> list = new ArrayList<DealerDto>();

		try {

			String sql = "SELECT 	ddt.id, ddt.dealer_id_fk, ddt.dealer_account_id_fk, ddt.online_payment_id_fk, ddt.cash_payment_id_fk, ddt.c_y_session, ddt.pay_amount, \r\n"
					+ "	ddt.pay_date, ddt.remark, ddt.payment_mode, ddt.online_way, ddt.online_remark, ddt.online_amount, ddt.cash_amount, ddt.online_date, ddt.bank_id_fk,\r\n"
					+ "	ddt.current_in_date,\r\n"
					+ "	dit.NAME, dit.mobile_no, dit.address, dit.gst_no, dit.old_due, dit.opening_due\r\n"
					+ "	FROM dealer_due_tb ddt LEFT JOIN dealer_info_tb dit ON ddt.dealer_id_fk = dit.id\r\n"
					+ "	LEFT JOIN dealer_account_tb dat ON ddt.dealer_account_id_fk = dat.id "
					+ " WHERE ddt.dealer_id_fk = ?;";
			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				DealerDto dto = new DealerDto();

				dto.setId(resultSet.getInt(1));
				dto.setDealer_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setOnline_payment_id_fk(resultSet.getInt(4));
				dto.setCash_payment_id_fk(resultSet.getInt(5));
				dto.setC_y_session(resultSet.getString(6));
				dto.setPay_amount(resultSet.getFloat(7));
				dto.setPay_date(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));
				dto.setPayment_mode(resultSet.getString(10));
				dto.setOnline_way(resultSet.getString(11));
				dto.setOnline_remark(resultSet.getString(12));
				dto.setOnline_amount(resultSet.getFloat(13));
				dto.setCash_amount(resultSet.getFloat(14));
				dto.setOnline_date(resultSet.getString(15));
				dto.setBank_id_fk(resultSet.getInt(16));
				dto.setCurrent_in_date(resultSet.getString(17));
				dto.setName(resultSet.getString(18));
				dto.setMobile_no(resultSet.getString(19));
				dto.setAddress(resultSet.getString(20));
				dto.setGst_no(resultSet.getString(21));
				dto.setOld_due(resultSet.getFloat(22));
				dto.setOpening_due(resultSet.getFloat(23));
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

	// **** Start select dealer account info

	public ArrayList<DealerDto> getDealerAccountInfo(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<DealerDto> list = new ArrayList<DealerDto>();

		try {

			String sql = "SELECT da.id, da.user_id_fk, da.dealer_id_fk, da.bill_id_fk, da.debit_amt,\r\n"
					+ "da.credit_amt, da.type ,da.payment_mode, da.online_way, da.online_remark, da.online_date,\r\n"
					+ "da.online_amount, da.cash_amount, da.in_date, da.current_in_date, da.status,da.remark, dt.name\r\n"
					+ "FROM dealer_account_tb da INNER JOIN dealer_info_tb dt ON da.dealer_id_fk = dt.id\r\n"
					+ "WHERE da.dealer_id_fk= ?;";
			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				DealerDto dto = new DealerDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setDealer_id_fk(resultSet.getInt(3));
				dto.setBill_id_fk(resultSet.getInt(4));
				dto.setDebit_amt(resultSet.getFloat(5));
				dto.setCredit_amt(resultSet.getFloat(6));
				dto.setType(resultSet.getString(7));
				dto.setPayment_mode(resultSet.getString(8));
				dto.setOnline_way(resultSet.getString(9));
				dto.setOnline_remark(resultSet.getString(10));
				dto.setOnline_date(resultSet.getString(11));
				dto.setOnline_amount(resultSet.getFloat(12));
				dto.setCash_amount(resultSet.getFloat(13));
				dto.setIn_date(resultSet.getString(14));
				dto.setCurrent_in_date(resultSet.getString(15));
				dto.setStatus(resultSet.getString(16));
				dto.setRemark(resultSet.getString(17));
				dto.setName(resultSet.getString(18));
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

	// **** End select dealer account info

	// **** Start select dealer account info By date

	public ArrayList<DealerDto> getDealerAccountInfoByDate(int id, String date1, String date2,
			HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		ArrayList<DealerDto> list = new ArrayList<DealerDto>();
		PreparedStatement ps = null;

		try {

			if ("".equalsIgnoreCase(date1) && "".equalsIgnoreCase(date2)) {

				ps = db.connection.prepareStatement(
						"SELECT da.id, da.user_id_fk, da.dealer_id_fk, da.bill_id_fk, da.debit_amt,\r\n"
								+ "da.credit_amt, da.type ,da.payment_mode, da.online_way, da.online_remark, da.online_date,\r\n"
								+ "da.online_amount, da.cash_amount, da.in_date, da.current_in_date, da.status,da.remark, dt.name\r\n"
								+ "FROM dealer_account_tb da INNER JOIN dealer_info_tb dt ON da.dealer_id_fk = dt.id\r\n"
								+ "WHERE da.dealer_id_fk= ? ORDER BY da.in_date;");

				ps.setInt(1, id);

			}

			else if (!"".equalsIgnoreCase(date1) && !"".equalsIgnoreCase(date2)) {

				ps = db.connection.prepareStatement(
						"SELECT da.id, da.user_id_fk, da.dealer_id_fk, da.bill_id_fk, da.debit_amt,\r\n"
								+ "da.credit_amt, da.type ,da.payment_mode, da.online_way, da.online_remark, da.online_date,\r\n"
								+ "da.online_amount, da.cash_amount, da.in_date, da.current_in_date, da.status,da.remark, dt.name\r\n"
								+ "FROM dealer_account_tb da INNER JOIN dealer_info_tb dt ON da.dealer_id_fk = dt.id\r\n"
								+ "WHERE da.dealer_id_fk= ? AND da.in_date BETWEEN ? AND ? ORDER BY da.in_date;");

				ps.setInt(1, id);
				ps.setString(2, date1);
				ps.setString(3, date2);

			}

			System.out.println(ps);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				DealerDto dto = new DealerDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setDealer_id_fk(resultSet.getInt(3));
				dto.setBill_id_fk(resultSet.getInt(4));
				dto.setDebit_amt(resultSet.getFloat(5));
				dto.setCredit_amt(resultSet.getFloat(6));
				dto.setType(resultSet.getString(7));
				dto.setPayment_mode(resultSet.getString(8));
				dto.setOnline_way(resultSet.getString(9));
				dto.setOnline_remark(resultSet.getString(10));
				dto.setOnline_date(resultSet.getString(11));
				dto.setOnline_amount(resultSet.getFloat(12));
				dto.setCash_amount(resultSet.getFloat(13));
				dto.setIn_date(resultSet.getString(14));
				dto.setCurrent_in_date(resultSet.getString(15));
				dto.setStatus(resultSet.getString(16));
				dto.setRemark(resultSet.getString(17));
				dto.setName(resultSet.getString(18));

				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// LogFileService.catchLogFile(e, config);
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

	// **** End select dealer account info by date

	// Method For Updating data on edit page
	public boolean updateDealerOpeningDue(DealerDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		float old_opening_due = 0;

		try {
			PreparedStatement select_cust_det = db.connection
					.prepareStatement("SELECT opening_due FROM dealer_info_tb" + "	WHERE id = ?;");

			select_cust_det.setInt(1, dto.getId());
			System.out.println(select_cust_det);
			ResultSet rs_item = select_cust_det.executeQuery();

			while (rs_item.next()) {
				old_opening_due = rs_item.getFloat(1);
			}

			String sql = "UPDATE dealer_info_tb SET  old_due= old_due + ?, opening_due = ? WHERE id=?;";
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
						"UPDATE dealer_account_tb SET credit_amt=?  WHERE dealer_id_fk=? AND TYPE = ?;");

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

	/*
	 * public boolean deletepurchaseBill(int bill_id_fk, HttpServletRequest request,
	 * ServletConfig config) throws IOException {
	 * 
	 * DataDb db = new DataDb(request);
	 * 
	 * int dealer_id_fk=0,dealer_account_id_fk=0; float total_amount_with_gst=0;
	 * 
	 * try {
	 * 
	 * 
	 * PreparedStatement ps_select = db.connection.
	 * prepareStatement("SELECT 	dealer_id_fk,total_amount_with_gst,dealer_account_id_fk FROM purchase_bill_tb WHERE id=?;"
	 * );
	 * 
	 * ps_select.setInt(1, bill_id_fk); System.out.println(ps_select); ResultSet
	 * resultSet = ps_select.executeQuery();
	 * 
	 * while (resultSet.next()) {
	 * 
	 * dealer_id_fk = (resultSet.getInt(1)); total_amount_with_gst =
	 * (resultSet.getFloat(2)); dealer_account_id_fk = (resultSet.getInt(3));
	 * 
	 * 
	 * }
	 * 
	 * // ***********Delete for sale bill item*********** PreparedStatement
	 * item_select = db.connection.
	 * prepareStatement("SELECT 	id FROM purchase_bill_item_tb WHERE pur_bill_id_fk = ?;"
	 * );
	 * 
	 * item_select.setInt(1, bill_id_fk); System.out.println(item_select); ResultSet
	 * resultSet_item = item_select.executeQuery();
	 * 
	 * while (resultSet_item.next()) {
	 * 
	 * int item_id_fk = (resultSet_item.getInt(1));
	 * 
	 * PurchaseBillService ser = new PurchaseBillService();
	 * ser.deleteOnePurchaseBillItem(item_id_fk, request, config);
	 * 
	 * }
	 * 
	 * // Delete for sell Bill PreparedStatement pur_bill =
	 * db.connection.prepareStatement("DELETE FROM purchase_bill_tb WHERE id = ?;");
	 * pur_bill.setInt(1, bill_id_fk);
	 * 
	 * System.out.println(pur_bill); int i = pur_bill.executeUpdate();
	 * 
	 * if (i != 0) { // clear customer old due info PreparedStatement deal_due =
	 * db.connection .prepareStatement(
	 * "UPDATE dealer_info_tb SET old_due = old_due - ? WHERE id = ?;");
	 * 
	 * deal_due.setFloat(1, total_amount_with_gst); deal_due.setInt(2,
	 * dealer_id_fk); deal_due.executeUpdate(); System.out.println(deal_due);
	 * 
	 * // Delete for cash payment PreparedStatement deal_acc = db.connection
	 * .prepareStatement("DELETE FROM dealer_account_tb WHERE id = ?;");
	 * deal_acc.setInt(1, dealer_account_id_fk); deal_acc.executeUpdate();
	 * System.out.println(deal_acc);
	 * 
	 * 
	 * 
	 * return true;
	 * 
	 * } } catch (Exception e) {
	 * 
	 * e.printStackTrace(); } return false; }
	 */

}
