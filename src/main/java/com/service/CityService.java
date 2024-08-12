package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.CityDto;

public class CityService {

	public boolean insertCity(CityDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {
			


			PreparedStatement ps = db.connection
.prepareStatement("INSERT INTO city_tb (Name,user_id_fk)	VALUES (?,?);");

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

	public ArrayList<CityDto> getCityInfo(ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<CityDto> list = new ArrayList<CityDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT 	id, name,status, current_in_date FROM	city_tb;");
			
		 
		 
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				CityDto dto = new CityDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2)); 
				
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
				

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
	public CityDto  getCityInfoById(int id, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		CityDto dto = new CityDto();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name,status, current_in_date FROM city_tb  where id=?");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

 
				
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2)); 
				
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));
		 

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

	public boolean UpdateCity(CityDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection
					.prepareStatement("UPDATE city_tb SET name = ?, status = ?, user_id_fk =? WHERE  id = ?;");

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
	
	public ArrayList<CityDto> getActiveCityInfo(ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<CityDto> list = new ArrayList<CityDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name,status, current_in_date FROM city_tb WHERE STATUS='Active';");
			
			 
					
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				CityDto dto = new CityDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));
				dto.setCurrent_in_date(resultSet.getString(4));

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
	
	
	public boolean deleteCity(int id, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM city_tb WHERE id = ?;");

			ps.setInt(1, id);
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
