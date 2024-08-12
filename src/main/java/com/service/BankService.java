package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.BankDto;

public class BankService {
	// Insert Method for Bank
	public boolean insertBank(BankDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			// Insert Query of Bank

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO bank_tb (name,account_no,branch,ifsc_code,opening_bal,opening_date,user_id_fk) VALUES(?,?,?,?,?,?,?);");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getAccount_no());
			ps.setString(3, dto.getBranch());
			ps.setString(4, dto.getIfsc_code());
			ps.setFloat(5, dto.getOpening_bal());
			ps.setString(6, dto.getOpening_date());
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

	// Method for Show data on Manage page
	public ArrayList<BankDto> getBankInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<BankDto> list = new ArrayList<BankDto>();

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement("SELECT bt.id, bt.name, bt.account_no, bt.branch, bt.ifsc_code, bt.opening_bal, bt.current_in_date, bt.opening_date, bt.status, bt.user_id_fk,  ut.name FROM bank_tb bt  INNER JOIN user_personal_info_tb ut ON bt.user_id_fk = ut.id ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				BankDto dto = new BankDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAccount_no(resultSet.getString(3));
				dto.setBranch(resultSet.getString(4));
				dto.setIfsc_code(resultSet.getString(5));
				dto.setOpening_bal(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setOpening_date(resultSet.getString(8));
				dto.setStatus(resultSet.getString(9));
				dto.setUser_id_fk(resultSet.getInt(10));
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
	public BankDto getBankInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		BankDto dto = new BankDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement("SELECT bt.id, bt.name, bt.account_no, bt.branch, bt.ifsc_code, bt.opening_bal, bt.current_in_date, bt.opening_date, bt.status, bt.user_id_fk,  ut.name FROM bank_tb bt  INNER JOIN user_personal_info_tb ut ON bt.user_id_fk = ut.id WHERE bt.id = ?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAccount_no(resultSet.getString(3));
				dto.setBranch(resultSet.getString(4));
				dto.setIfsc_code(resultSet.getString(5));
				dto.setOpening_bal(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setOpening_date(resultSet.getString(8));
				dto.setStatus(resultSet.getString(9));
				dto.setUser_id_fk(resultSet.getInt(10));
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
	public boolean updateBank(BankDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for update data
			ps = db.connection.prepareStatement(
					"UPDATE bank_tb SET name = ? ,account_no = ? ,branch = ? ,ifsc_code = ? ,opening_bal = ? , opening_date = ? , status = ? , user_id_fk = ? WHERE id = ? ;");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getAccount_no());
			ps.setString(3, dto.getBranch());
			ps.setString(4, dto.getIfsc_code());
			ps.setFloat(5, dto.getOpening_bal());
			ps.setString(6, dto.getOpening_date());
			ps.setString(7, dto.getStatus());
			ps.setInt(8, dto.getUser_id_fk());
			ps.setInt(9, dto.getId());

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

	public ArrayList<BankDto>getActiveBankInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<BankDto> list = new ArrayList<BankDto>();

		try {

			preparedStatement = db.connection.prepareStatement("SELECT bt.id, bt.name, bt.account_no, bt.branch, bt.ifsc_code, bt.opening_bal, bt.current_in_date, bt.opening_date, bt.status, bt.user_id_fk,  ut.name FROM bank_tb bt  INNER JOIN user_personal_info_tb ut ON bt.user_id_fk = ut.id WHERE bt.status = 'Active' ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				BankDto dto = new BankDto();
				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setAccount_no(resultSet.getString(3));
				dto.setBranch(resultSet.getString(4));
				dto.setIfsc_code(resultSet.getString(5));
				dto.setOpening_bal(resultSet.getFloat(6));
				dto.setCurrent_in_date(resultSet.getString(7));
				dto.setOpening_date(resultSet.getString(8));
				dto.setStatus(resultSet.getString(9));
				dto.setUser_id_fk(resultSet.getInt(10));
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
}
