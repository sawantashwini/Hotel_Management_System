package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.IngredientsDto;
import com.mysql.jdbc.Statement;

public class IngredientsPurchaseService {

	// ---- insert Purchase bill Info -----//

	public int insertIngredientsPurchaseBillInfo(IngredientsDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO ingredients_purchase_bill_tb ( remark, in_date, user_id_fk,total_amount) VALUES( ?, ?, ?,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getRemark());
			ps.setString(2, dto.getIn_date());
			ps.setInt(3, dto.getUser_id_fk());
			ps.setFloat(4, dto.getTotal_amount());

			System.out.println(ps);
			int i = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			dto.setId(rs.getInt(1));

			if (i != 0) {
				return rs.getInt(1);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}

	// ---- End insert Purchase bill info----- //

	// Show data on edit page according to id
	public IngredientsDto getIngredientsPurchaseBillInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		IngredientsDto dto = new IngredientsDto();

		try {

			// Select query for showing data on edit page
			ps = db.connection.prepareStatement(
					"SELECT ipt.id, ipt.remark, ipt.in_date, ipt.status, ipt.current_in_date,ipt.total_amount, ipt.user_id_fk, ut.name FROM ingredients_purchase_bill_tb ipt INNER JOIN user_personal_info_tb ut ON ipt.user_id_fk = ut.id WHERE ipt.id=?;");

			ps.setInt(1, id);
			System.out.println(ps);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setRemark(resultSet.getString(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setStatus(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setUser_name(resultSet.getString(8));
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
	public boolean updateIngredientsPurchaseBill(IngredientsDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for x-ray bill
			ps = db.connection.prepareStatement("UPDATE ingredients_purchase_bill_tb "
					+ " SET remark = ? , in_date = ? , status = ? , user_id_fk = ?,total_amount = ? WHERE id = ? ;");

			ps.setString(1, dto.getRemark());
			ps.setString(2, dto.getIn_date());
			ps.setString(3, dto.getStatus());
			ps.setInt(4, dto.getUser_id_fk());
			ps.setFloat(5, dto.getTotal_amount());
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

	// Method For insert x-ray bill item
	public boolean insertIngredientsBillItem(String[] Item_id, String[] Item_name, String[] Item_quantity,
			String[] Item_amt, String[] Item_rate, IngredientsDto pur_dto, int bill_id_fk, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb comm = new DataDb(request);

		try {

			for (int i = 0; i < Item_name.length; i++) {

				String sql = "INSERT INTO ingredients_purchase_bill_item_tb (bill_id_fk, item_id_fk, name, quantity, amount ,rate) "
						+ "	VALUES(?, ?, ?, ?, ?, ?);";

				PreparedStatement ps = comm.connection.prepareStatement(sql);
				ps.setInt(1, bill_id_fk);
				ps.setString(2, Item_id[i]);
				ps.setString(3, Item_name[i]);
				ps.setString(4, Item_quantity[i]);
				ps.setString(5, Item_amt[i]);
				ps.setString(6, Item_rate[i]);

				System.out.print(ps);

				int b = ps.executeUpdate();

				if (b != 0) {
					PreparedStatement ps2 = comm.connection
							.prepareStatement("UPDATE ingredients_item_tb SET qty = qty + ? WHERE id = ?;");

					ps2.setString(1, Item_quantity[i]);
					ps2.setString(2, Item_id[i]);
					System.out.println(ps2);
					ps2.executeUpdate();
				}

			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean insertOneIngredientsPurchaseBillItem(IngredientsDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			String sql = "INSERT INTO ingredients_purchase_bill_item_tb (bill_id_fk, item_id_fk, name, quantity, amount, rate) VALUES (?, ?, ?, ? , ?, ?);";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, dto.getBill_id_fk());
			ps.setInt(2, dto.getItem_id_fk());
			ps.setString(3, dto.getItem_name());
			ps.setFloat(4, dto.getItem_quantity());
			ps.setFloat(5, dto.getItem_amt());
			ps.setFloat(6, dto.getItem_rate());

			System.out.print(ps);

			int b = ps.executeUpdate();

			PreparedStatement ps2 = comm.connection
					.prepareStatement("UPDATE ingredients_item_tb SET qty = qty + ? WHERE id = ?;");

			ps2.setFloat(1, dto.getItem_quantity());
			ps2.setInt(2, dto.getItem_id_fk());
			System.out.println(ps2);
			ps2.executeUpdate();

			if (b != 0) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteOneIngredientsPurchaseBillItem(int id, int item_id_fk,  float item_quantity, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			String sql = "DELETE FROM ingredients_purchase_bill_item_tb WHERE id = ?;";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, id);
			System.out.print(ps);

			int b = ps.executeUpdate();

			PreparedStatement ps2 = comm.connection
					.prepareStatement("UPDATE ingredients_item_tb SET qty = qty - ? WHERE id = ?;");

			ps2.setFloat(1, item_quantity);
			ps2.setInt(2, item_id_fk);
			System.out.println(ps2);
			ps2.executeUpdate();

			if (b != 0) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<IngredientsDto> getIngredientsPurchaseBillItemInfoById(int bill_id, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT id, name, quantity, amount, rate, item_id_fk  FROM ingredients_purchase_bill_item_tb WHERE bill_id_fk = ?; ");

			preparedStatement.setInt(1, bill_id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_name(resultSet.getString(2));
				dto.setItem_quantity(resultSet.getFloat(3));
				dto.setItem_amt(resultSet.getFloat(4));
				dto.setItem_rate(resultSet.getFloat(5));
				dto.setItem_id_fk(resultSet.getInt(6));

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

	// Method for get name of all item on datalist in search box
	public ArrayList<IngredientsDto> getItemName(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement("SELECT name FROM ingredients_item_tb WHERE status = 'Active' GROUP BY name;");
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setItem_name(resultSet.getString(1));

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
	public ArrayList<IngredientsDto> getIngredientsPurchaseBillInfo(String d1, String d2, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			String sql_select = "SELECT ipt.id, ipt.remark, ipt.in_date, ipt.status, ipt.current_in_date,ipt.total_amount, ipt.user_id_fk, ut.name FROM ingredients_purchase_bill_tb ipt"
					+ " INNER JOIN user_personal_info_tb ut ON ipt.user_id_fk = ut.id ";

			if ((d1.equalsIgnoreCase("") && d2.equalsIgnoreCase(""))) {
				preparedStatement = db.connection.prepareStatement(sql_select + " ORDER BY ipt.in_date, ipt.id DESC;");
			}
			
			else if ((!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase(""))) {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement(sql_select + " WHERE ipt.in_date BETWEEN ? AND ? ORDER BY ipt.in_date, ipt.id DESC;");
				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);

			}

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setRemark(resultSet.getString(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setStatus(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setUser_name(resultSet.getString(8));

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
	public ArrayList<IngredientsDto> getIngredientsPurchaseBillItemInfo(String d1, String d2, String item_name,
			ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			String sql_select = "SELECT iit.id, iit.bill_id_fk, iit.item_id_fk, iit.name, iit.quantity, iit.amount, iit.rate, iit.status, iit.current_in_date,\r\n"
					+ "ibt.in_date, ibt.remark FROM ingredients_purchase_bill_item_tb iit \r\n"
					+ "INNER JOIN ingredients_purchase_bill_tb ibt ON iit.bill_id_fk = ibt.id ";

			if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(sql_select + " WHERE ibt.in_date BETWEEN ? AND ? ;");

				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
			}

			else if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection
						.prepareStatement(sql_select + " WHERE ibt.in_date BETWEEN ? AND ? AND iit.name = ?;");

				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
				preparedStatement.setString(3, item_name);
			}

			else if (!item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(sql_select + " WHERE iit.name = ?;");

				preparedStatement.setString(1, item_name);
			} else {
				preparedStatement = db.connection.prepareStatement(sql_select + "WHERE ibt.in_date=Curdate() ORDER BY ibt.in_date, iit.id DESC ;");

			}

			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setBill_id_fk(resultSet.getInt(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_name(resultSet.getString(4));
				dto.setItem_quantity(resultSet.getFloat(5));
				dto.setItem_amt(resultSet.getFloat(6));
				dto.setItem_rate(resultSet.getFloat(7));
				dto.setStatus(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setIn_date(resultSet.getString(10));
				dto.setRemark(resultSet.getString(11));

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
	public ArrayList<IngredientsDto> getIngredientsPurchaseBillInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT ipt.id, ipt.remark, ipt.in_date, ipt.status, ipt.current_in_date,ipt.total_amount, ipt.user_id_fk, ut.name FROM ingredients_purchase_bill_tb ipt INNER JOIN user_personal_info_tb ut ON ipt.user_id_fk = ut.id;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setRemark(resultSet.getString(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setStatus(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setTotal_amount(resultSet.getFloat(6));
				dto.setUser_id_fk(resultSet.getInt(7));
				dto.setUser_name(resultSet.getString(8));

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

}
