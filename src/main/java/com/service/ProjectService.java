package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.ProjectDto;

public class ProjectService {
	public int maxId(HttpServletRequest request) {
		try {
			Connection connection = (Connection) new DataDb(request).connection;

			String dbname = connection.getCatalog();

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables "
							+ "WHERE table_name='project_info_tb' AND TABLE_SCHEMA=?");
			preparedStatement.setString(1, dbname);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return 0;
	}

	public boolean insertProject(ProjectDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO project_info_tb(name,mobile_no,address,gst_in,term_and_conditions)VALUES(?,?,?,?,?);");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile_no());
			ps.setString(3, dto.getAddress());
			ps.setString(4, dto.getGst_in());
			ps.setString(5, dto.getTerm_and_conditions());
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

	public ArrayList<ProjectDto> getProjectInfo(ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProjectDto> list = new ArrayList<ProjectDto>();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT id, name,mobile_no,address,gst_in,STATUS,current_in_date,term_and_conditions FROM project_info_tb;");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProjectDto dto = new ProjectDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setAddress(resultSet.getString(4));
				dto.setGst_in(resultSet.getString(5));
				dto.setStatus(resultSet.getString(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setTerm_and_conditions(resultSet.getString(8));

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

	public ProjectDto getProjectInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ProjectDto dto = new ProjectDto();

		try {
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT id, name,mobile_no,address,gst_in,status,current_in_date,term_and_conditions FROM project_info_tb WHERE id=?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setAddress(resultSet.getString(4));
				dto.setGst_in(resultSet.getString(5));
				dto.setStatus(resultSet.getString(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setTerm_and_conditions(resultSet.getString(8));
				dto.setActivation_date("2024-12-31");

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

	public boolean UpdateProject(ProjectDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"UPDATE project_info_tb SET mobile_no = ? ,address = ? ,gst_in = ? , term_and_conditions = ?	WHERE id = ? ;");

		
			ps.setString(1, dto.getMobile_no());
			ps.setString(2, dto.getAddress());
			ps.setString(3, dto.getGst_in());
			ps.setString(4, dto.getTerm_and_conditions());
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

	public ArrayList<ProjectDto> getActiveProjectInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<ProjectDto> list = new ArrayList<ProjectDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name FROM project_info_tb WHERE status='Active';");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ProjectDto dto = new ProjectDto();

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

}
