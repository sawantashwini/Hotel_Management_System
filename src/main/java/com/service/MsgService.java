package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.MsgDto;

public class MsgService {

	public boolean insertMsg(MsgDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection
.prepareStatement("INSERT INTO msg (Name,user_id_fk)	VALUES (?,?);");

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

	public ArrayList<MsgDto> getMsgInfo(ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<MsgDto> list = new ArrayList<MsgDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT 	id, name,Status FROM	msg;");
			
		 
		 
			
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MsgDto dto = new MsgDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2)); 
				
				dto.setStatus(resultSet.getString(3));
				

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
	public MsgDto  getMsgInfoById(int id, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		MsgDto dto = new MsgDto();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name,status FROM msg  where id=?");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

 
				
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2)); 
				
				dto.setStatus(resultSet.getString(3));
		 

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

	public boolean UpdateMsg(MsgDto dto, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection
					.prepareStatement("UPDATE msg SET name = ?, status = ?,user_id_fk = ? WHERE  id = ?;");

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
	
	public ArrayList<MsgDto> getActiveMsgInfo(ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<MsgDto> list = new ArrayList<MsgDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name,status FROM msg WHERE STATUS='active';");
			
			 
					
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				MsgDto dto = new MsgDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setStatus(resultSet.getString(3));

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
