package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.IngredientsDto;

public class IngredientsItemService {
	String sql_select = "SELECT it.id, it.user_id_fk, it.measurement_id_fk, it.name, it.qty, it.min_limit, it.status, it.current_in_date, ut.name, mt.name\r\n"
			+ "FROM ingredients_item_tb it LEFT JOIN measurement_tb mt ON it.measurement_id_fk = mt.id\r\n"
			+ "INNER JOIN user_personal_info_tb ut ON it.user_id_fk = ut.id ";
	
	// Insert Method for IngredientsItem Item
	public boolean insertIngredientsItem(IngredientsDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			// Insert Query of IngredientsItem

			PreparedStatement ps = db.connection
					.prepareStatement("INSERT INTO ingredients_item_tb  (user_id_fk, measurement_id_fk, name, qty, min_limit, status) VALUES (?,?,?,?,?,?);");

			ps.setInt(1, dto.getUser_id_fk());
			ps.setInt(2, dto.getMeasurement_id_fk());
			ps.setString(3, dto.getName());
			ps.setFloat(4, dto.getQty());
			ps.setFloat(5, dto.getMin_limit());
			ps.setString(6, dto.getStatus());

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

	// Method for Show data on Manage page
	public ArrayList<IngredientsDto> getIngredientsItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql_select +" ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQty(resultSet.getFloat(5));
				dto.setMin_limit(resultSet.getFloat(6));
				dto.setStatus(resultSet.getString(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setUser_name(resultSet.getString(9));
				dto.setMeasurement_name(resultSet.getString(10));

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
	
	public ArrayList<IngredientsDto> getIngredientsRequiredItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql_select +" WHERE it.qty<it.min_limit");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQty(resultSet.getFloat(5));
				dto.setMin_limit(resultSet.getFloat(6));
				dto.setStatus(resultSet.getString(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setUser_name(resultSet.getString(9));
				dto.setMeasurement_name(resultSet.getString(10));

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
	public IngredientsDto getIngredientsItemInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		IngredientsDto dto = new IngredientsDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement( sql_select+ " WHERE it.id=?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQty(resultSet.getFloat(5));
				dto.setMin_limit(resultSet.getFloat(6));
				dto.setStatus(resultSet.getString(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setUser_name(resultSet.getString(9));
				dto.setMeasurement_name(resultSet.getString(10));

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
	public boolean updateIngredientsItem(IngredientsDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for update data
			ps = db.connection.prepareStatement(
					"UPDATE ingredients_item_tb\r\n"
					+ " SET user_id_fk = ?, measurement_id_fk = ?, NAME = ?, min_limit = ?, STATUS = ?\r\n"
					+ " WHERE id = ?;");

			ps.setInt(1, dto.getUser_id_fk());
			ps.setInt(2, dto.getMeasurement_id_fk());
			ps.setString(3, dto.getName());
			ps.setFloat(4, dto.getMin_limit());
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

	public ArrayList<IngredientsDto> getActiveIngredientsItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IngredientsDto> list = new ArrayList<IngredientsDto>();

		try {

			preparedStatement = db.connection.prepareStatement(sql_select+ " WHERE it.status = 'Active' ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IngredientsDto dto = new IngredientsDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQty(resultSet.getFloat(5));
				dto.setMin_limit(resultSet.getFloat(6));
				dto.setStatus(resultSet.getString(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setUser_name(resultSet.getString(9));
				dto.setMeasurement_name(resultSet.getString(10));

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

	public IngredientsDto getIngredientsItemInfoByName(String name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		IngredientsDto dto = new IngredientsDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql_select +" WHERE it.name = ?;");

			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQty(resultSet.getFloat(5));
				dto.setMin_limit(resultSet.getFloat(6));
				dto.setStatus(resultSet.getString(7));
				dto.setCurrent_in_date(resultSet.getString(8));
				dto.setUser_name(resultSet.getString(9));
				dto.setMeasurement_name(resultSet.getString(10));

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
	
}
