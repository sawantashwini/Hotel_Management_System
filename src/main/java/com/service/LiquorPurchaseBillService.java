package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.LiquorPurchaseBillDto;

import com.mysql.jdbc.Statement;

public class LiquorPurchaseBillService {

	ReciepeRelationService rec_ser = new ReciepeRelationService();

	// Insert Method for Liquor Purchase
	public int insertLiquorPurchase(LiquorPurchaseBillDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		int bill_id_fk = 0;
		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO liquor_purchase_bill_tb\r\n"
					+ "            (user_id_fk, dealer_account_id_fk, dealer_id_fk, in_date, total_amount, STATUS, remark)\r\n"
					+ "VALUES (?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, dto.getUser_id_fk());
			ps.setInt(2, dto.getDealer_account_id_fk());
			ps.setInt(3, dto.getDealer_id_fk());
			ps.setString(4, dto.getIn_date());
			ps.setFloat(5, dto.getTotal_amount());
			ps.setString(6, dto.getStatus());
			ps.setString(7, dto.getRemark());

			System.out.println(ps);

			int i = ps.executeUpdate();
			ResultSet rs_up = ps.getGeneratedKeys();
			rs_up.next();
			bill_id_fk = rs_up.getInt(1);

			if (i != 0) {

				// update dealer info due //
				PreparedStatement del_info = db.connection
						.prepareStatement("UPDATE dealer_info_tb SET	old_due = old_due + ? WHERE id = ?;");

				del_info.setFloat(1, dto.getTotal_amount());
				del_info.setInt(2, dto.getDealer_id_fk());

				System.out.println(del_info);

				int i2 = del_info.executeUpdate();

				if (i2 != 0) {

					// insert dealer acc table info //
					PreparedStatement deal_acc = db.connection.prepareStatement("INSERT INTO dealer_account_tb \r\n"
							+ "(dealer_id_fk,user_id_fk,bill_id_fk, \r\n" + "c_y_session,credit_amt, \r\n"
							+ "TYPE,in_date,STATUS \r\n" + ")VALUES(?,?,?,?,?,?,?,?);",
							Statement.RETURN_GENERATED_KEYS);

					deal_acc.setInt(1, dto.getDealer_id_fk());
					deal_acc.setInt(2, dto.getUser_id_fk());
					deal_acc.setInt(3, bill_id_fk);
					deal_acc.setString(4, "2023-2024");
					deal_acc.setFloat(5, dto.getTotal_amount());// Credit amount

					deal_acc.setString(6, "Purchase");

					deal_acc.setString(7, dto.getIn_date());
					deal_acc.setString(8, dto.getStatus());

					System.out.println(deal_acc);

					deal_acc.executeUpdate();

					ResultSet rs_deal_acc = deal_acc.getGeneratedKeys();
					rs_deal_acc.next();
					dto.setDealer_account_id_fk(rs_deal_acc.getInt(1));

				}

			}

			// update purchase bill in dealer acc id
			PreparedStatement update_purchase_bill = db.connection
					.prepareStatement("UPDATE liquor_purchase_bill_tb SET  dealer_account_id_fk = ? WHERE id = ?;");

			update_purchase_bill.setInt(1, dto.getDealer_account_id_fk());
			update_purchase_bill.setInt(2, bill_id_fk);

			System.out.println(update_purchase_bill);
			int i2 = update_purchase_bill.executeUpdate();

			if (i2 != 0) {
				return bill_id_fk;

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;
	}

	// Method For insert Liquor bill item
	public boolean insertLiquorBillItem(String[] Item_id, String[] Item_name, String[] Item_code, String[] quantity,
			String[] price, String[] total_price, LiquorPurchaseBillDto pur_dto, int bill_id_fk,
			HttpServletRequest request, ServletConfig config) throws IOException {
		DataDb comm = new DataDb(request);

		try {

			for (int i = 0; i < Item_code.length; i++) {

				String sql = "INSERT INTO liquor_purchase_bill_item_tb (bill_id_fk, item_id_fk, item_code, item_name, quantity, price ,total_price) "
						+ "	VALUES(?, ?, ?, ?, ?, ?,?);";

				PreparedStatement ps = comm.connection.prepareStatement(sql);
				ps.setInt(1, bill_id_fk);
				ps.setString(2, Item_id[i]);
				ps.setString(3, Item_code[i]);
				ps.setString(4, Item_name[i]);

				ps.setString(5, quantity[i]);
				ps.setString(6, price[i]);
				ps.setString(7, total_price[i]);

				System.out.print(ps);

				int b = ps.executeUpdate();

				rec_ser.managestockByMenu(Integer.parseInt(Item_id[i]), Float.parseFloat(quantity[i]), "increase",
						request, config);

			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// date vice search
	public ArrayList<LiquorPurchaseBillDto> getPurchaseBillInfo(String d1, String d2, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorPurchaseBillDto> list = new ArrayList<LiquorPurchaseBillDto>();

		try {
			
			if(!d1.equalsIgnoreCase("")&&!d2.equalsIgnoreCase("")) {

			preparedStatement = db.connection.prepareStatement(
					"SELECT lp.id, lp.user_id_fk, lp.dealer_account_id_fk, lp.dealer_id_fk, lp.in_date, lp.total_amount, lp.current_in_date, lp.status, lp.remark, dt.name\r\n"
							+ "FROM liquor_purchase_bill_tb lp INNER JOIN user_personal_info_tb ut ON ut.id = lp.user_id_fk INNER JOIN dealer_info_tb dt ON dt.id = lp.dealer_id_fk \r\n"
							+ "INNER JOIN dealer_account_tb da ON da.id = lp.dealer_account_id_fk  WHERE lp.in_date BETWEEN ? AND ?;");
			preparedStatement.setString(1, d1);
			preparedStatement.setString(2, d2);
			}
			if(d1.equalsIgnoreCase("")&&d2.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(
						"SELECT lp.id, lp.user_id_fk, lp.dealer_account_id_fk, lp.dealer_id_fk, lp.in_date, lp.total_amount, lp.current_in_date, lp.status, lp.remark, dt.name\r\n"
								+ "FROM liquor_purchase_bill_tb lp INNER JOIN user_personal_info_tb ut ON ut.id = lp.user_id_fk INNER JOIN dealer_info_tb dt ON dt.id = lp.dealer_id_fk \r\n"
								+ "INNER JOIN dealer_account_tb da ON da.id = lp.dealer_account_id_fk;");

				}
			// Select query for showing data on manage table

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setDealer_id_fk(resultSet.getInt(4));

				dto.setIn_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));

				dto.setStatus(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));

				dto.setDealer_name(resultSet.getString(10));

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
	
	public ArrayList<LiquorPurchaseBillDto> getPurchaseBillInfoByDealer(int id, String d1, String d2, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorPurchaseBillDto> list = new ArrayList<LiquorPurchaseBillDto>();

		try {
			
			if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(
						"SELECT lp.id, lp.user_id_fk, lp.dealer_account_id_fk, lp.dealer_id_fk, lp.in_date, lp.total_amount, lp.current_in_date, lp.status, lp.remark, dt.name\r\n"
								+ "FROM liquor_purchase_bill_tb lp INNER JOIN user_personal_info_tb ut ON ut.id = lp.user_id_fk INNER JOIN dealer_info_tb dt ON dt.id = lp.dealer_id_fk \r\n"
								+ "INNER JOIN dealer_account_tb da ON da.id = lp.dealer_account_id_fk  WHERE lp.dealer_id_fk =? AND lp.in_date BETWEEN ? AND ?;");
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, d1);
				preparedStatement.setString(3, d2);
			}
			if (d1.equalsIgnoreCase("") && d2.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(
						"SELECT lp.id, lp.user_id_fk, lp.dealer_account_id_fk, lp.dealer_id_fk, lp.in_date, lp.total_amount, lp.current_in_date, lp.status, lp.remark, dt.name\r\n"
								+ "FROM liquor_purchase_bill_tb lp INNER JOIN user_personal_info_tb ut ON ut.id = lp.user_id_fk INNER JOIN dealer_info_tb dt ON dt.id = lp.dealer_id_fk \r\n"
								+ "INNER JOIN dealer_account_tb da ON da.id = lp.dealer_account_id_fk where lp.dealer_id_fk =?;");
				preparedStatement.setInt(1, id);
			}
			// Select query for showing data on manage table

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setDealer_id_fk(resultSet.getInt(4));

				dto.setIn_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));

				dto.setStatus(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));

				dto.setDealer_name(resultSet.getString(10));

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

	// date vice search in purchase report item vice
	public ArrayList<LiquorPurchaseBillDto> getPurchaseBillItemInfo(String d1, String d2, String item_name,
			ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorPurchaseBillDto> list = new ArrayList<LiquorPurchaseBillDto>();

		try {

			String Select = "SELECT lt.id, lt.bill_id_fk, lt.item_id_fk, lt.item_code, lt.item_name, lt.quantity, lt.price, lt.total_price, lt.status, lt.current_in_date, lp.in_date \r\n"
					+ " FROM liquor_purchase_bill_item_tb lt INNER JOIN liquor_purchase_bill_tb lp ON lp.id= lt.bill_id_fk ";
			if (d1.equalsIgnoreCase("") && d2.equalsIgnoreCase("") && item_name.equalsIgnoreCase("")) {
				preparedStatement = db.connection.prepareStatement(Select + " WHERE lp.in_date = CURDATE();");

			} else if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(Select + " WHERE lp.in_date BETWEEN ? AND ?;");

				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
			}

			else if (d1.equalsIgnoreCase("") && d2.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(Select + " WHERE lt.item_name = ?;");

				preparedStatement.setString(1, item_name);
			} else if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection
						.prepareStatement(Select + " WHERE lp.in_date  BETWEEN ? AND ? AND lt.item_name = ?;");

				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
				preparedStatement.setString(3, item_name);
			}

			// Select query for showing data on manage table

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

				dto.setId(resultSet.getInt(1));
				dto.setBill_id_fk(resultSet.getInt(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_code(resultSet.getString(4));
				dto.setItem_name(resultSet.getString(5));
				dto.setQuantity(resultSet.getFloat(6));
				dto.setPrice(resultSet.getFloat(7));
				dto.setTotal_price(resultSet.getFloat(8));

				dto.setStatus(resultSet.getString(9));
				dto.setCurrent_in_date(resultSet.getString(10));
				dto.setIn_date(resultSet.getString(11));

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
	public ArrayList<LiquorPurchaseBillDto> getPurchaseBillInfoByDealerId(int dealer_id, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorPurchaseBillDto> list = new ArrayList<LiquorPurchaseBillDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(
					"SELECT lp.id, lp.user_id_fk, lp.dealer_account_id_fk, lp.dealer_id_fk, lp.in_date, lp.total_amount, lp.current_in_date, lp.status, lp.remark, dt.name\\r\\n\"\r\n"
							+ "					+ \"FROM liquor_purchase_bill_tb lp INNER JOIN user_personal_info_tb ut ON ut.id = lp.user_id_fk INNER JOIN dealer_info_tb dt ON dt.id = lp.dealer_id_fk \\r\\n\"\r\n"
							+ "					+ \"INNER JOIN dealer_account_tb da ON da.id = lp.dealer_account_id_fk  WHERE dt.dealer_id_fk=?;");

			preparedStatement.setInt(1, dealer_id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setDealer_id_fk(resultSet.getInt(4));

				dto.setIn_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));

				dto.setStatus(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));

				dto.setDealer_name(resultSet.getString(10));

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

	// Method for Show data on edit page
	public LiquorPurchaseBillDto getPurchaseBillInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

		try {

			// Select query for showing data on edit page
			preparedStatement = db.connection.prepareStatement(
					"SELECT lp.id, lp.user_id_fk, lp.dealer_account_id_fk, lp.dealer_id_fk, lp.in_date, lp.total_amount, lp.current_in_date, lp.status, lp.remark, dt.name, dt.mobile_no, dt.address, dt.gst_no\r\n"
							+ "FROM liquor_purchase_bill_tb lp INNER JOIN user_personal_info_tb ut ON ut.id = lp.user_id_fk INNER JOIN dealer_info_tb dt ON dt.id = lp.dealer_id_fk \r\n"
							+ "INNER JOIN dealer_account_tb da ON da.id = lp.dealer_account_id_fk  WHERE lp.id=?;");

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setDealer_account_id_fk(resultSet.getInt(3));
				dto.setDealer_id_fk(resultSet.getInt(4));

				dto.setIn_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));

				dto.setStatus(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));

				dto.setDealer_name(resultSet.getString(10));
				dto.setDealer_mobile_no(resultSet.getString(11));
				dto.setDealer_address(resultSet.getString(12));
				dto.setDealer_gst_in(resultSet.getString(13));
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

	public ArrayList<LiquorPurchaseBillDto> getPurchaseBillItemInfoById(int bill_id, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorPurchaseBillDto> list = new ArrayList<LiquorPurchaseBillDto>();

		try {

			preparedStatement = db.connection.prepareStatement("SELECT 	id, item_code, item_id_fk, item_name, \r\n"
					+ "	quantity, price, total_price, bill_id_fk, status, current_in_date FROM \r\n"
					+ "	liquor_purchase_bill_item_tb WHERE bill_id_fk = ?; ");
			preparedStatement.setInt(1, bill_id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_name(resultSet.getString(4));
				dto.setQuantity(resultSet.getFloat(5));
				dto.setPrice(resultSet.getFloat(6));
				dto.setTotal_price(resultSet.getFloat(7));
				dto.setBill_id_fk(resultSet.getInt(8));
				dto.setStatus(resultSet.getString(9));
				dto.setCurrent_in_date(resultSet.getString(9));

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

	// Method For insert in purchase bill item
	public boolean insertOnePurchaseBillItem(LiquorPurchaseBillDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			String sql = "INSERT INTO liquor_purchase_bill_item_tb (bill_id_fk, item_id_fk, item_code, item_name, quantity, price ,total_price) "
					+ "	VALUES(?, ?, ?, ?, ?, ?,?);";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, dto.getBill_id_fk());

			ps.setInt(2, dto.getItem_id_fk());
			ps.setString(3, dto.getItem_code());
			ps.setString(4, dto.getItem_name());
			ps.setFloat(5, dto.getQuantity());
			ps.setFloat(6, dto.getPrice());
			ps.setFloat(7, dto.getTotal_price());

			System.out.print(ps);

			int b = ps.executeUpdate();

			rec_ser.managestockByMenu(dto.getItem_id_fk(), dto.getQuantity(), "increase", request, config);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteOnePurchaseBillItem(int bill_item_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb comm = new DataDb(request);
		float item_qty = 0;
		int item_id_fk = 0;

		try {

			PreparedStatement select_item_det = comm.connection.prepareStatement(
					"SELECT quantity,item_id_fk FROM liquor_purchase_bill_item_tb" + "	WHERE id = ?;");

			select_item_det.setInt(1, bill_item_id);
			System.out.println(select_item_det);
			ResultSet rs_item = select_item_det.executeQuery();

			while (rs_item.next()) {
				item_qty = rs_item.getFloat(1);
				item_id_fk = rs_item.getInt(2);
			}

			String sql = "DELETE FROM liquor_purchase_bill_item_tb WHERE id = ?;";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, bill_item_id);

			System.out.print(ps);

			int b = ps.executeUpdate();

			if (b != 0) {
				rec_ser.managestockByMenu(item_id_fk, item_qty, "", request, config);

				return true;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateLiquorPurchaseInfoById(LiquorPurchaseBillDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		float old_total_amount = 0;
		int old_dealer_id_fk = 0;

		try {

			PreparedStatement ps1 = db.connection.prepareStatement(
					"SELECT  dealer_id_fk,total_amount,dealer_account_id_fk FROM liquor_purchase_bill_tb WHERE id=?;");

			ps1.setInt(1, dto.getId());
			System.out.println(ps1);

			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {

				old_dealer_id_fk = rs1.getInt(1);
				old_total_amount = rs1.getFloat(2);

				dto.setDealer_account_id_fk(rs1.getInt(3));

			}

			PreparedStatement old_dealer_due = db.connection
					.prepareStatement("UPDATE dealer_info_tb SET old_due = old_due - ? WHERE id = ?;");

			old_dealer_due.setFloat(1, old_total_amount);
			old_dealer_due.setInt(2, old_dealer_id_fk);

			System.out.println(old_dealer_due);
			old_dealer_due.executeUpdate();

			// update dealer due info
			PreparedStatement Deal_due = db.connection
					.prepareStatement("UPDATE dealer_info_tb SET old_due = old_due + ? WHERE id = ?;");

			Deal_due.setFloat(1, dto.getTotal_amount());
			Deal_due.setInt(2, dto.getDealer_id_fk());

			System.out.println(Deal_due);

			int i2 = Deal_due.executeUpdate();

			if (i2 != 0) {

				// UPDATE dealer acc table info
				PreparedStatement dealer_acc = db.connection.prepareStatement(
						"UPDATE dealer_account_tb SET user_id_fk = ? , dealer_id_fk = ? , c_y_session = ? ,\r\n"
								+ "credit_amt = ? , TYPE = ?,in_date = ? , STATUS = ? WHERE id = ? ;",
						Statement.RETURN_GENERATED_KEYS);

				dealer_acc.setInt(1, dto.getUser_id_fk());
				dealer_acc.setInt(2, dto.getDealer_id_fk());
				dealer_acc.setString(3, "2023-2024");
				dealer_acc.setFloat(4, dto.getTotal_amount());// credit amount
				dealer_acc.setString(5, "Liquor Purchase");
				dealer_acc.setString(6, dto.getIn_date());
				dealer_acc.setString(7, dto.getStatus());
				dealer_acc.setInt(8, dto.getDealer_account_id_fk());

				System.out.println(dealer_acc);

				dealer_acc.executeUpdate();

			}

			// Update Query for purchase Bill details
			ps = db.connection.prepareStatement("UPDATE liquor_purchase_bill_tb\r\n"
					+ "SET  user_id_fk = ?, dealer_account_id_fk = ?, dealer_id_fk = ?, in_date = ?, total_amount = ?, status = ?, remark = ?\r\n"
					+ "WHERE id = ?;");

			ps.setInt(1, dto.getUser_id_fk());
			ps.setInt(2, dto.getDealer_account_id_fk());
			ps.setInt(3, dto.getDealer_id_fk());
			ps.setString(4, dto.getIn_date());
			ps.setFloat(5, dto.getTotal_amount());
			ps.setString(6, dto.getStatus());
			ps.setString(7, dto.getRemark());
			ps.setInt(8, dto.getId());

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
