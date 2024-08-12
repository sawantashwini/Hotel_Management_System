package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.CashPaymentDto;

public class CashPaymentService {

	
	public ArrayList<CashPaymentDto> getCashPaymentInfo(ServletConfig config, HttpServletRequest request) throws IOException{
		
		DataDb db = new DataDb(request);
		
		ArrayList<CashPaymentDto> list = new ArrayList<CashPaymentDto>();
		
		try {
			
			// Select query for showing data on manage table
			PreparedStatement preparedStatement = db.connection.prepareStatement("SELECT	ct.id, ct.credit, ct.debit, ct.remark, ct.current_in_date, ct.in_date, ct.bill_id_fk, ct.status, ct.type , ct.user_id_fk, up.name\n"
					+ " FROM cash_payment_tb ct Left JOIN user_personal_info_tb up ON up.id = ct.user_id_fk;");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);
			
			while(resultSet.next()) {
				
				CashPaymentDto dto =new CashPaymentDto();
				
				dto.setId(resultSet.getInt(1));
				dto.setCredit(resultSet.getFloat(2));
				dto.setDebit(resultSet.getFloat(3));
				dto.setRemark(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setIn_date(resultSet.getString(6));
				dto.setBill_id_fk(resultSet.getInt(7));
				dto.setStatus(resultSet.getString(8));
				dto.setType(resultSet.getString(9));
				dto.setUser_id_fk(resultSet.getInt(10));
				dto.setUser_name(resultSet.getString(11));
				list.add(dto);
				System.out.println(list);
			}
		}
		catch (Exception e) {
			
		}
		finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return list;
	}
	
	
	public ArrayList<CashPaymentDto> getCashPaymentInfoByDate(String date1, String date2, String type,  HttpServletRequest request,
			ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		ArrayList<CashPaymentDto> list = new ArrayList<CashPaymentDto>();
		CashPaymentDto dto = null;
		PreparedStatement ps = null;
		try {
			String sql  ="SELECT	ct.id, ct.credit, ct.debit, ct.remark, ct.current_in_date, ct.in_date, ct.bill_id_fk, ct.status, ct.type , ct.user_id_fk, up.name\n"
					+ " FROM cash_payment_tb ct Left JOIN user_personal_info_tb up ON up.id = ct.user_id_fk ";

			
			
			 if (!"".equalsIgnoreCase(date1) && !"".equalsIgnoreCase(date2)&&!"".equalsIgnoreCase(type)) {

				ps = db.connection.prepareStatement(sql + " WHERE ct.in_date BETWEEN ? AND ? AND ct.type = ? ORDER BY ct.in_date;");

				ps.setString(1, date1);
				ps.setString(2, date2);
				ps.setString(3, type);

			}

			else if (!"".equalsIgnoreCase(date1) && !"".equalsIgnoreCase(date2)&& "".equalsIgnoreCase(type)) {

				ps = db.connection.prepareStatement(sql + " WHERE ct.in_date BETWEEN ? AND ? ORDER BY ct.in_date;");

				ps.setString(1, date1);
				ps.setString(2, date2);

			}
			else if ("".equalsIgnoreCase(date1) && "".equalsIgnoreCase(date2) && !"".equalsIgnoreCase(type)) {

				ps = db.connection.prepareStatement(sql + " WHERE ct.type = ? ORDER BY ct.in_date;");

				ps.setString(1, type);

			}
			else if ("".equalsIgnoreCase(date1) && "".equalsIgnoreCase(date2) && "".equalsIgnoreCase(type)) {

				ps = db.connection.prepareStatement(sql +" ORDER BY ct.in_date ; ");

			}

			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				dto = new CashPaymentDto();
				dto.setId(resultSet.getInt(1));
				dto.setCredit(resultSet.getFloat(2));
				dto.setDebit(resultSet.getFloat(3));
				dto.setRemark(resultSet.getString(4));
				dto.setCurrent_in_date(resultSet.getString(5));
				dto.setIn_date(resultSet.getString(6));
				dto.setBill_id_fk(resultSet.getInt(7));
				dto.setStatus(resultSet.getString(8));
				dto.setType(resultSet.getString(9));
				dto.setUser_id_fk(resultSet.getInt(10));
				dto.setUser_name(resultSet.getString(11));
				
				if(dto.getDebit()!=0 || dto.getCredit()!=0) {
					list.add(dto);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		//LogFileService.catchLogFile(e, config);
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) { /* ignored */
				}
			}

			if (db.connection != null) {
				try {
					db.connection.close();
				} catch (SQLException e) { /* ignored */
				}
			}

		}
		return list;
	}
	
	
	
}