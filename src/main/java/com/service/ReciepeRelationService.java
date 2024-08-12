package com.service;

import java.io.IOException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.ReciepeRelationDto;

public class ReciepeRelationService {

	// ---- insert Sale bill Info -----//

	public boolean insertInfo(ReciepeRelationDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement("INSERT INTO reciepe_relation_tb  (menu_id_fk, menu_name, ingredient_id_fk, ingredient_name, reciepe_ratio, status, user_id_fk) "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?);");

			ps.setInt(1, dto.getMenu_id_fk());
			ps.setString(2, dto.getMenu_name());
			ps.setInt(3, dto.getIngredient_id_fk());
			ps.setString(4, dto.getIngredient_name());
			ps.setFloat(5, dto.getReciepe_ratio());
			ps.setString(6, dto.getStatus());
			ps.setInt(7, dto.getUser_id_fk());

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

	public boolean deleteReciepeRelation(int id, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {

			String sql = "DELETE FROM reciepe_relation_tb WHERE id = ?;";

			PreparedStatement ps = comm.connection.prepareStatement(sql);

			ps.setInt(1, id);

			System.out.print(ps);

			int b = ps.executeUpdate();

			if (b != 0) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void managestockByMenu(int menu_id, float qty, String operation, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb comm = new DataDb(request);

		try {
			int liqour_cat_id = 0;
			float liqour_ind_qty = 0;

			String qtyflag = " qty - ? ";
			String qtyflag1 = " quantity - ? ";
			if (operation.equalsIgnoreCase("increase")) {
				qtyflag = " qty + ? ";
				qtyflag1 = " quantity + ? ";
			}

			String select_menu_sql = "SELECT liqour_cat_id_fk,liqour_ind_qty FROM menu_item_detail_tb WHERE id = ?;";

			PreparedStatement select_menu_ps = comm.connection.prepareStatement(select_menu_sql);

			select_menu_ps.setInt(1, menu_id);

			ResultSet rs_menu = select_menu_ps.executeQuery();
			 
			while (rs_menu.next()) {

				liqour_cat_id = rs_menu.getInt(1);
				liqour_ind_qty = rs_menu.getFloat(2);
			}
			/******* If menu item is liqour *******/
			if (liqour_cat_id != 0) {
				PreparedStatement update_liqour_stock = comm.connection
						.prepareStatement("UPDATE liqour_cat_tb SET \r\n" + "quantity = " + qtyflag1 + " WHERE id = ?;");

				update_liqour_stock.setFloat(1, liqour_ind_qty * qty);
				update_liqour_stock.setInt(2, liqour_cat_id);

				System.out.println(update_liqour_stock);

				update_liqour_stock.executeUpdate();
			} 
			
			/******* If menu item is Food *******/
			
			else {
				String select_ingredient_sql = "SELECT reciepe_ratio,ingredient_id_fk\r\n"
						+ "FROM reciepe_relation_tb WHERE menu_id_fk = ?;";

				PreparedStatement select_ingredients = comm.connection.prepareStatement(select_ingredient_sql);

				select_ingredients.setInt(1, menu_id);

				ResultSet rs_ingredients = select_ingredients.executeQuery();

				while (rs_ingredients.next()) {

					PreparedStatement update_ingredient_stock = comm.connection.prepareStatement(
							"UPDATE ingredients_item_tb SET \r\n" + "qty = " + qtyflag + " WHERE id = ?;");

					update_ingredient_stock.setFloat(1, rs_ingredients.getFloat(1) * qty);
					update_ingredient_stock.setInt(2, rs_ingredients.getInt(2));

					System.out.println(update_ingredient_stock);

					update_ingredient_stock.executeUpdate();

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<ReciepeRelationDto> getInfoByMenuName(String name, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<ReciepeRelationDto> list = new ArrayList<ReciepeRelationDto>();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT id, menu_id_fk, menu_name, ingredient_id_fk, ingredient_name, reciepe_ratio, status, current_in_date, user_id_fk\r\n"
					+ "FROM reciepe_relation_tb Where menu_name = ?;");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ReciepeRelationDto dto = new ReciepeRelationDto();

				dto.setId(resultSet.getInt(1));
				dto.setMenu_id_fk(resultSet.getInt(2));
				dto.setMenu_name(resultSet.getString(3));
				dto.setIngredient_id_fk(resultSet.getInt(4));
				dto.setIngredient_name(resultSet.getString(5));
				dto.setReciepe_ratio(resultSet.getFloat(6));
				dto.setStatus(resultSet.getString(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setUser_id_fk(resultSet.getInt(9));

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
