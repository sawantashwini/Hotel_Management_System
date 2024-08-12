package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;

public class CheckNameAvailService {

	public boolean checkAvail(String name, String table_name, String field_name, int id, HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			ps = db.connection.prepareStatement(
					"SELECT " + field_name + " FROM " + table_name + " WHERE " + field_name + " = ? AND id != ?;");
			ps.setString(1, name);
			ps.setInt(2, id);
			ResultSet resultSet = ps.executeQuery();
			System.out.println(ps);

			while (resultSet.next()) {
				if (name.trim().equalsIgnoreCase(resultSet.getString(1))) {
					return true;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

}