package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.LiquorCategoryDto;

public class LiquorCategoryService {
	String sql_select = "SELECT lc.id, lc.user_id_fk, lc.measurement_id_fk, lc.name, lc.quantity, lc.Min_qty, lc.capacity, lc.status, lc.current_in_date, mt.name, ut.name FROM liqour_cat_tb lc INNER JOIN measurement_tb mt ON mt.id =lc.measurement_id_fk INNER JOIN user_personal_info_tb ut ON ut.id = lc.user_id_fk ";
	
	// Insert Method for LiquorCategory Item
	public boolean insertLiquorCategory(LiquorCategoryDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			// Insert Query of LiquorCategory

			PreparedStatement ps = db.connection
					.prepareStatement("INSERT INTO liqour_cat_tb( user_id_fk, measurement_id_fk, NAME, quantity, Min_qty, capacity, status) VALUES (?, ?, ?, ?, ?, ?, ?);");

			ps.setInt(1, dto.getUser_id_fk());
			ps.setInt(2, dto.getMeasurement_id_fk());
			ps.setString(3, dto.getName());
			ps.setFloat(4, dto.getQuantity());
			ps.setFloat(5, dto.getMin_qty());
			ps.setFloat(6, dto.getCapacity());
			ps.setString(7, dto.getStatus());

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
	public ArrayList<LiquorCategoryDto> getLiquorCategoryInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorCategoryDto> list = new ArrayList<LiquorCategoryDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql_select +" ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorCategoryDto dto = new LiquorCategoryDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQuantity(resultSet.getFloat(5));
				dto.setMin_qty(resultSet.getFloat(6));
				dto.setCapacity(resultSet.getFloat(7));
				dto.setStatus(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setMeasure_name(resultSet.getString(10));
				dto.setUser_name(resultSet.getString(11));

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
	
	public ArrayList<LiquorCategoryDto> getLiquorCategoryRequiredInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorCategoryDto> list = new ArrayList<LiquorCategoryDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(sql_select +" WHERE lc.quantity<lc.Min_qty");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorCategoryDto dto = new LiquorCategoryDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQuantity(resultSet.getFloat(5));
				dto.setMin_qty(resultSet.getFloat(6));
				dto.setCapacity(resultSet.getFloat(7));
				dto.setStatus(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setMeasure_name(resultSet.getString(10));
				dto.setUser_name(resultSet.getString(11));


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
	public LiquorCategoryDto getLiquorCategoryInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		LiquorCategoryDto dto = new LiquorCategoryDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement( " SELECT lc.id, lc.user_id_fk, lc.measurement_id_fk, lc.name, lc.quantity, lc.Min_qty, lc.capacity, lc.status, lc.current_in_date, mt.name, ut.name FROM liqour_cat_tb lc "
					+ "INNER JOIN measurement_tb mt ON mt.id =lc.measurement_id_fk INNER JOIN user_personal_info_tb ut ON ut.id = lc.user_id_fk  WHERE lc.id = ?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQuantity(resultSet.getFloat(5));
				dto.setMin_qty(resultSet.getFloat(6));
				dto.setCapacity(resultSet.getFloat(7));
				dto.setStatus(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setMeasure_name(resultSet.getString(10));
				dto.setUser_name(resultSet.getString(11));


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
	public boolean updateLiquorCategory(LiquorCategoryDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for update data
			ps = db.connection.prepareStatement("UPDATE liqour_cat_tb SET  user_id_fk = ?, measurement_id_fk = ?, NAME = ?, Min_qty = ?, capacity = ?, STATUS = ?  WHERE id = ?;");

			ps.setInt(1, dto.getUser_id_fk());
			ps.setInt(2, dto.getMeasurement_id_fk());
			ps.setString(3, dto.getName());
			ps.setFloat(4, dto.getMin_qty());
			ps.setFloat(5, dto.getCapacity());
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

	public ArrayList<LiquorCategoryDto> getActiveLiquorCategoryInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<LiquorCategoryDto> list = new ArrayList<LiquorCategoryDto>();

		try {

			preparedStatement = db.connection.prepareStatement(sql_select+ " WHERE lc.status = 'Active' ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				LiquorCategoryDto dto = new LiquorCategoryDto();

				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQuantity(resultSet.getFloat(5));
				dto.setMin_qty(resultSet.getFloat(6));
				dto.setCapacity(resultSet.getFloat(7));
				dto.setStatus(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setUser_name(resultSet.getString(10));
				dto.setMeasure_name(resultSet.getString(11));


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

	public LiquorCategoryDto getLiquorCategoryInfoByName(String name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		LiquorCategoryDto dto = new LiquorCategoryDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql_select +" WHERE lc.name = ?;");

			preparedStatement.setString(1, name);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				dto.setId(resultSet.getInt(1));
				dto.setUser_id_fk(resultSet.getInt(2));
				dto.setMeasurement_id_fk(resultSet.getInt(3));
				dto.setName(resultSet.getString(4));
				dto.setQuantity(resultSet.getFloat(5));
				dto.setMin_qty(resultSet.getFloat(6));
				dto.setCapacity(resultSet.getFloat(7));
				dto.setStatus(resultSet.getString(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setMeasure_name(resultSet.getString(10));
				dto.setUser_name(resultSet.getString(11));


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
