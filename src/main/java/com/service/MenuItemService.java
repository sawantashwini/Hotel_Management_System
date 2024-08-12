package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.MenuItemDto;

public class MenuItemService {

	// Insert Method for Liquor Item
	public boolean insertLiquorItem(MenuItemDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {
		DataDb db = new DataDb(request);

		try {
			PreparedStatement ps_liq = db.connection.prepareStatement("INSERT INTO menu_item_detail_tb "
					+ " (item_code, item_name, item_price, user_id_fk, liqour_cat_id_fk, liqour_ind_qty)VALUES (?, ?,  ?, ?,?,?);");

			ps_liq.setString(1, dto.getItem_code());
			ps_liq.setString(2, dto.getItem_name());
			ps_liq.setFloat(3, dto.getItem_price());
			ps_liq.setInt(4, dto.getUser_id_fk());
			ps_liq.setInt(5, dto.getLiqour_cat_id_fk());
			ps_liq.setFloat(6, dto.getLiqour_ind_qty());

			System.out.println(ps_liq);

			int i = ps_liq.executeUpdate();

			if (i != 0) {
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;
	}

	// Method for Show data on Manage page
	public ArrayList<MenuItemDto> getMenuItemInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement("SELECT id, item_code, item_name, "
					+ "item_price, user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb Where liqour_cat_id_fk =0;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MenuItemDto dto = new MenuItemDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
		public ArrayList<MenuItemDto> geItemWiseInfo( String food_name,String liquor_name,ServletConfig config, HttpServletRequest request) throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

			try {
				String sql_select ="SELECT id, item_code, item_name,item_price, user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb Where item_name =?;";
				// Select query for showing data on manage table
				if (!food_name.equalsIgnoreCase("")) {
				preparedStatement = db.connection.prepareStatement(sql_select);
				preparedStatement.setString(1, food_name);
				}
				else if (!liquor_name.equalsIgnoreCase("")) {
					preparedStatement = db.connection.prepareStatement(sql_select);
					preparedStatement.setString(1, liquor_name);
					}
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					MenuItemDto dto = new MenuItemDto();

					dto.setId(resultSet.getInt(1));
					dto.setItem_code(resultSet.getString(2));
					dto.setItem_name(resultSet.getString(3));
					dto.setItem_price(resultSet.getFloat(4));
					dto.setUser_id_fk(resultSet.getInt(5));
					dto.setCurrent_in_date(resultSet.getString(6));
					dto.setStatus(resultSet.getString(7));
					dto.setLiqour_cat_id_fk(resultSet.getInt(8));
					dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
		public ArrayList<MenuItemDto> getMenuItemName(ServletConfig config, HttpServletRequest request) throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

			try {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement("SELECT item_name, liqour_cat_id_fk  FROM menu_item_detail_tb Where liqour_cat_id_fk =0;");

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					MenuItemDto dto = new MenuItemDto();

				
					dto.setItem_name(resultSet.getString(1));
				    dto.setLiqour_cat_id_fk(resultSet.getInt(2));
					

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
				public ArrayList<MenuItemDto> getLiquorItemName(ServletConfig config, HttpServletRequest request) throws IOException {

					DataDb db = new DataDb(request);
					PreparedStatement preparedStatement = null;

					ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

					try {

						// Select query for showing data on manage table
						preparedStatement = db.connection.prepareStatement("SELECT item_name,liqour_cat_id_fk FROM menu_item_detail_tb Where liqour_cat_id_fk !=0;");

						ResultSet resultSet = preparedStatement.executeQuery();

						while (resultSet.next()) {

							MenuItemDto dto = new MenuItemDto();

							
							dto.setItem_name(resultSet.getString(1));
							
							dto.setLiqour_cat_id_fk(resultSet.getInt(2));
							

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
		public ArrayList<MenuItemDto> getLiquorItemInfo(ServletConfig config, HttpServletRequest request) throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

			try {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement("SELECT id, item_code, item_name, "
						+ "item_price, user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb Where liqour_cat_id_fk !=0;");

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					MenuItemDto dto = new MenuItemDto();

					dto.setId(resultSet.getInt(1));
					dto.setItem_code(resultSet.getString(2));
					dto.setItem_name(resultSet.getString(3));
					dto.setItem_price(resultSet.getFloat(4));
					dto.setUser_id_fk(resultSet.getInt(5));
					dto.setCurrent_in_date(resultSet.getString(6));
					dto.setStatus(resultSet.getString(7));
					dto.setLiqour_cat_id_fk(resultSet.getInt(8));
					dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
	public MenuItemDto getMenuItemInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		MenuItemDto dto = new MenuItemDto();

		try {

			// Select query for showing data on Edit page

			preparedStatement = db.connection.prepareStatement(
					"SELECT midt.id, midt.item_code, midt.item_name, midt.item_price, midt.user_id_fk, midt.current_in_date, midt.status, midt.liqour_cat_id_fk, midt.liqour_ind_qty, lc.name \r\n"
					+ "FROM menu_item_detail_tb midt LEFT JOIN liqour_cat_tb lc ON  midt.liqour_cat_id_fk = lc.id  WHERE  midt.id=? ;");

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));
				dto.setLiquor_cat_name(resultSet.getString(10));

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
	public MenuItemDto getMenuItemInfoByName(String name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		MenuItemDto dto = new MenuItemDto();

		try {

			// Select query for showing data on Edit page

			preparedStatement = db.connection.prepareStatement(
					"SELECT id, item_code, item_name, item_price, user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty\r\n"
							+ "FROM menu_item_detail_tb WHERE  item_name=?;");

			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
	public MenuItemDto getMenuItemInfoByCode(String item_code, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		MenuItemDto dto = new MenuItemDto();

		try {

			// Select query for showing data on Edit page

			preparedStatement = db.connection.prepareStatement(
					"SELECT id, item_code, item_name, item_price, user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty\r\n"
							+ "FROM menu_item_detail_tb WHERE  item_code=?;");

			preparedStatement.setString(1, item_code);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
	public boolean updateMenuItem(MenuItemDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for update data
			ps = db.connection.prepareStatement(
					"UPDATE menu_item_detail_tb SET  user_id_fk=?, item_code=?,  item_name=?, item_price=?, Status=?  WHERE id=?;");

			ps.setInt(1, dto.getUser_id_fk());
			ps.setString(2, dto.getItem_code());
			ps.setString(3, dto.getItem_name());
			ps.setFloat(4, dto.getItem_price());
			ps.setString(5, dto.getStatus());
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
			
			
			// Method For Updating data on edit page
			public boolean updateLiquorItem(MenuItemDto dto, HttpServletRequest request, ServletConfig config)
					throws IOException {

				DataDb db = new DataDb(request);
				PreparedStatement ps = null;

				try {

					// Update Query for update data
					ps = db.connection.prepareStatement(
							"UPDATE menu_item_detail_tb SET  user_id_fk=?, item_code=?,  item_name=?, item_price=?, Status=?, liqour_ind_qty= ? WHERE id=?;");

					ps.setInt(1, dto.getUser_id_fk());
					ps.setString(2, dto.getItem_code());
					ps.setString(3, dto.getItem_name());
					ps.setFloat(4, dto.getItem_price());
					ps.setString(5, dto.getStatus());
					ps.setString(5, dto.getStatus());
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

	public ArrayList<MenuItemDto> getActiveMenuItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

		try {

			preparedStatement = db.connection.prepareStatement("SELECT id, item_code, item_name, item_price,"
					+ " user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb WHERE  Status='Active';");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MenuItemDto dto = new MenuItemDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
	
	public ArrayList<MenuItemDto> getActiveMenuLiquorItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

		try {

			preparedStatement = db.connection.prepareStatement("SELECT id, item_code, item_name, item_price,"
					+ " user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb WHERE  Status='Active' && liqour_cat_id_fk>0;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MenuItemDto dto = new MenuItemDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
	
	public ArrayList<MenuItemDto> getActiveFoodItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<MenuItemDto> list = new ArrayList<MenuItemDto>();

		try {

			preparedStatement = db.connection.prepareStatement("SELECT id, item_code, item_name, item_price,"
					+ " user_id_fk, current_in_date, status, liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb WHERE  Status='Active' && liqour_cat_id_fk =0;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MenuItemDto dto = new MenuItemDto();

				dto.setId(resultSet.getInt(1));
				dto.setItem_code(resultSet.getString(2));
				dto.setItem_name(resultSet.getString(3));
				dto.setItem_price(resultSet.getFloat(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setCurrent_in_date(resultSet.getString(6));
				dto.setStatus(resultSet.getString(7));
				dto.setLiqour_cat_id_fk(resultSet.getInt(8));
				dto.setLiqour_ind_qty(resultSet.getFloat(9));

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
