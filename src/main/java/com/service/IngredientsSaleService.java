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

public class IngredientsSaleService {

	// ---- insert Sale bill Info -----//

	public int insertIngredientsSaleBillInfo(IngredientsDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO ingredients_sale_bill_tb\r\n"
							+ " ( remark, in_date,user_id_fk) VALUES( ?, ?,?);\r\n" + "",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getRemark());
			ps.setString(2, dto.getIn_date());
			ps.setInt(3, dto.getUser_id_fk());

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

	// ---- End insert Sale bill info----- //

	// Method for Show data on Manage page
	public ArrayList<IngredientsDto> getIngredientsSaleBillInfo(String d1, String d2, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			if ((d1.equalsIgnoreCase("") && d2.equalsIgnoreCase(""))) {
				preparedStatement = db.connection.prepareStatement(
						"SELECT st.id, st.remark, st.in_date, st.status, st.current_in_date, st.user_id_fk,ut.name FROM ingredients_sale_bill_tb st\r\n"
								+ "INNER JOIN user_personal_info_tb ut ON st.user_id_fk = ut.id;");
			}

			else if ((!d1.equalsIgnoreCase("") && d2.equalsIgnoreCase(""))) {
				preparedStatement = db.connection.prepareStatement(
						"SELECT st.id, st.remark, st.in_date, st.status, st.current_in_date, st.user_id_fk,ut.name FROM ingredients_sale_bill_tb st\r\n"
								+ "INNER JOIN user_personal_info_tb ut ON st.user_id_fk = ut.id WHERE in_date BETWEEN ? AND NOW();");
				preparedStatement.setString(1, d1);
			} else if ((!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase(""))) {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement(
						"SELECT st.id, st.remark, st.in_date, st.status, st.current_in_date, st.user_id_fk,ut.name FROM ingredients_sale_bill_tb st\r\n"
								+ "INNER JOIN user_personal_info_tb ut ON st.user_id_fk = ut.id WHERE in_date BETWEEN ? AND ?;");
				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);

			}

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setRemark(resultSet.getString(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setStatus(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setUser_id_fk(resultSet.getInt(6));
				dto.setUser_name(resultSet.getString(7));

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
	public IngredientsDto getIngredientsSaleBillInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		IngredientsDto dto = new IngredientsDto();

		try {

			// Select query for showing data on edit page
			ps = db.connection.prepareStatement(
					"SELECT 	st.id, st.remark, st.in_date, st.status, st.current_in_date, st.user_id_fk,ut.name FROM ingredients_sale_bill_tb st\r\n"
							+ "INNER JOIN user_personal_info_tb ut ON st.user_id_fk = ut.id WHERE st.id=?;");

			ps.setInt(1, id);
			System.out.println(ps);

			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setRemark(resultSet.getString(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setStatus(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setUser_id_fk(resultSet.getInt(6));
				dto.setUser_name(resultSet.getString(7));
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
	public boolean updateIngredientsSaleBill(IngredientsDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for x-ray bill
			ps = db.connection.prepareStatement("UPDATE ingredients_sale_bill_tb SET remark = ? , in_date = ? , STATUS = ? , user_id_fk = ? WHERE id = ? ;");

			ps.setString(1, dto.getRemark());
			ps.setString(2, dto.getIn_date());
			ps.setString(3, dto.getStatus());
			ps.setInt(4, dto.getUser_id_fk());
			ps.setInt(5, dto.getId());

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
				 IngredientsDto pur_dto, int bill_id_fk, HttpServletRequest request,
				ServletConfig config) throws IOException {
			DataDb comm = new DataDb(request);

			try {

				for (int i = 0; i < Item_name.length; i++) {

					String sql = "INSERT INTO ingredients_sale_bill_item_tb (bill_id_fk, item_id_fk, name, quantity) "
							+ "	VALUES(?, ?, ?, ?);";

					PreparedStatement ps = comm.connection.prepareStatement(sql);
					ps.setInt(1, bill_id_fk);
					ps.setString(2, Item_id[i]);
					ps.setString(3, Item_name[i]);
					ps.setString(4, Item_quantity[i]);
					

					System.out.print(ps);

					int b = ps.executeUpdate();

					if (b != 0) {
						PreparedStatement ps2 = comm.connection
								.prepareStatement("UPDATE ingredients_item_tb SET qty = qty - ? WHERE id = ?;");

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

	public boolean insertOneIngredientsSaleBillItem(IngredientsDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			String sql = "INSERT INTO ingredients_sale_bill_item_tb (bill_id_fk, item_id_fk, NAME, quantity) VALUES	(?, ?, ?,?);";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, dto.getBill_id_fk());
			ps.setInt(2, dto.getItem_id_fk());
			ps.setString(3, dto.getItem_name());
			ps.setFloat(4, dto.getItem_quantity());

			System.out.print(ps);

			int b = ps.executeUpdate();

			PreparedStatement ps2 = comm.connection.prepareStatement(
					"UPDATE ingredients_item_tb \n" + "	SET qty = qty - ?\n" + "	WHERE id = ?;");

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

	// Method for Show data on Manage page
	public ArrayList<IngredientsDto> getIngredientsSaleBillItemInfo(String d1, String d2, String item_name,
			ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			String sql_select = "SELECT ist.id, ist.bill_id_fk, ist.item_id_fk, ist.name, ist.quantity, ist.status, ist.current_in_date, isb.in_date, isb.remark\r\n"
					+ "FROM ingredients_sale_bill_item_tb ist INNER JOIN ingredients_sale_bill_tb isb ON ist.bill_id_fk = isb.id ";
			
			if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(sql_select+ " WHERE isb.in_date BETWEEN ? AND ? ;");

				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
			}

			else if (!d1.equalsIgnoreCase("") && !d2.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(sql_select+ " WHERE isb.in_date  BETWEEN ? AND ? AND ist.name = ?;");

				preparedStatement.setString(1, d1);
				preparedStatement.setString(2, d2);
				preparedStatement.setString(3, item_name);
			}

			else if (d1.equalsIgnoreCase("") && d2.equalsIgnoreCase("") && !item_name.equalsIgnoreCase("")) {

				preparedStatement = db.connection.prepareStatement(sql_select+ " WHERE ist.name = ?;");

				preparedStatement.setString(1, item_name);
			}
			
			else{
				preparedStatement = db.connection.prepareStatement(sql_select+ "WHERE isb.in_date=Curdate();");

			} 

			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setBill_id_fk(resultSet.getInt(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_name(resultSet.getString(4));
				dto.setItem_quantity(resultSet.getFloat(5));
				dto.setStatus(resultSet.getString(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setIn_date(resultSet.getString(8));
				dto.setRemark(resultSet.getString(9));

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

	public boolean deleteOneIngredientsSaleBillItem(int id, int item_id_fk, float item_quantity, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			String sql = "DELETE FROM ingredients_sale_bill_item_tb WHERE id = ?;";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, id);

			System.out.print(ps);

			int b = ps.executeUpdate();

			PreparedStatement ps2 = comm.connection.prepareStatement(
					"UPDATE ingredients_item_tb SET qty = qty + ? WHERE id = ?;");

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

	public ArrayList<IngredientsDto> getIngredientsSaleBillItemInfoById(int bill_id, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT 	id, NAME, quantity, item_id_fk FROM ingredients_sale_bill_item_tb WHERE bill_id_fk = ?; ");
			preparedStatement.setInt(1, bill_id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_name(resultSet.getString(2));
				dto.setItem_quantity(resultSet.getFloat(3));
				dto.setItem_id_fk(resultSet.getInt(4));

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
			preparedStatement = db.connection.prepareStatement("SELECT NAME FROM ingredients_item_tb GROUP BY NAME;");
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
}
