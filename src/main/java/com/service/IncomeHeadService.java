package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.IncomeHeadDto;

public class IncomeHeadService {
	// Insert Method for IncomeHead Item
	public boolean insertIncomeHead(IncomeHeadDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection
					.prepareStatement("INSERT INTO income_head_tb (name,user_id_fk)VALUES(?,?);");

			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getUser_id_fk());

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
	public ArrayList<IncomeHeadDto> getIncomeHeadInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IncomeHeadDto> list = new ArrayList<IncomeHeadDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(
					"SELECT mt.id, mt.name, mt.status, mt.current_in_date, mt.user_id_fk, ut.name user_name  FROM income_head_tb mt INNER JOIN user_personal_info_tb ut ON mt.user_id_fk = ut.id;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IncomeHeadDto dto = new IncomeHeadDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setUser_name(resultSet.getString(6));

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
	public IncomeHeadDto getIncomeHeadInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		IncomeHeadDto dto = new IncomeHeadDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(
					"SELECT mt.id, mt.name, mt.status, mt.current_in_date, mt.user_id_fk, ut.name user_name  FROM income_head_tb mt INNER JOIN user_personal_info_tb ut ON mt.user_id_fk = ut.id WHERE mt.id=?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setUser_name(resultSet.getString(6));

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
	public boolean updateIncomeHead(IncomeHeadDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {
			
			
			// Update Query for update data
			ps = db.connection
					.prepareStatement("UPDATE income_head_tb SET name = ?,status = ?, user_id_fk = ? WHERE id = ? ;");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getStatus());
			ps.setInt(3, dto.getUser_id_fk());
			ps.setInt(4, dto.getId());

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

	public ArrayList<IncomeHeadDto> getActiveIncomeHeadInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<IncomeHeadDto> list = new ArrayList<IncomeHeadDto>();

		try {

			preparedStatement = db.connection.prepareStatement("SELECT it.id, it.name FROM income_head_tb it WHERE it.status='Active' GROUP BY it.name;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				IncomeHeadDto dto = new IncomeHeadDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));

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

	public IncomeHeadDto getIncomeHeadInfobyName(String name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		IncomeHeadDto dto = new IncomeHeadDto();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT mt.id, mt.name, mt.status, mt.current_in_date, mt.user_id_fk, ut.name user_name  FROM income_head_tb mt INNER JOIN user_personal_info_tb ut ON mt.user_id_fk = ut.id WHERE mt.name=?;");

			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setUser_name(resultSet.getString(6));

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
