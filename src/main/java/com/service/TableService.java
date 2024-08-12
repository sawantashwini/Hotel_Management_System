package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.TableDto;

public class TableService {

	// Insert Method for Table
		public boolean insertTable(TableDto dto, HttpServletRequest request, ServletConfig config) throws IOException {
			
			DataDb db = new DataDb(request);
			
			try {
				
				//Insert Query of table_tb
				
				PreparedStatement ps = db.connection.prepareStatement("INSERT INTO table_tb (NAME, user_id_fk) VALUES(?,?);");

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
	public ArrayList<TableDto> getTableInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<TableDto> list = new ArrayList<TableDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement(
					"SELECT 	id, NAME, STATUS, current_in_date,manager_name FROM table_tb;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TableDto dto = new TableDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				dto.setManager_name(resultSet.getString(5));
				
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
	public TableDto getTableName(
			/* String name, */ServletConfig config, HttpServletRequest request) throws IOException {
 
			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;
			TableDto dto = new TableDto();

			try {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement(
						"SELECT ct.id, ct.name, ct.status, ct.current_in_date, ut.name FROM table_tb ct INNER JOIN user_personal_info_tb ut ON ct.user_id_fk = ut.id;");

				//preparedStatement.setString(1, name);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					dto.setId(resultSet.getInt(1));
					dto.setName(resultSet.getString(2));
					dto.setStatus(resultSet.getString(3));
					dto.setCurrent_in_date(resultSet.getString(4));
					dto.setUser_name(resultSet.getString(5));

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
	public TableDto getTableInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		TableDto dto = new TableDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(
					"SELECT 	id, NAME, STATUS, current_in_date, user_id_fk,manager_name FROM table_tb WHERE id = ?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				dto.setUser_id_fk(resultSet.getInt(5));
				dto.setManager_name(resultSet.getString(6));
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
		public boolean updateTable(TableDto dto, HttpServletRequest request,
				ServletConfig config) throws IOException {
			
			DataDb db = new DataDb(request);
			PreparedStatement ps=null;
			
			try {
				
				//Update Query for update data 
				ps = db.connection.prepareStatement("UPDATE table_tb SET NAME = ?, STATUS = ?, user_id_fk = ? WHERE id = ? ;");

				ps.setString(1, dto.getName());
				ps.setString(2, dto.getStatus());
				ps.setInt(3, dto.getUser_id_fk());
				ps.setInt(4, dto.getId());

				System.out.println(ps);
				
				int i = ps.executeUpdate();
				
				if (i != 0) {
					return true;
				}
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
			return false;
		}

	// Fetch all Table_names from table_tb in option of select button
	public ArrayList<TableDto> getActiveTableInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<TableDto> list = new ArrayList<TableDto>();

		try {

			// Select query for show all Table_names in option
			preparedStatement = db.connection.prepareStatement(
					"SELECT 	id, name, status, current_in_date, ut.name FROM table_tb tt INNER JOIN user_personal_info_tb ut ON tt.user_id_fk = ut.id WHERE tt.status = 'Active' ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				TableDto dto = new TableDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				dto.setUser_name(resultSet.getString(5));

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
