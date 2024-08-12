package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.MeasurementDto;

public class MeasurementService {




	// Insert Method for Measurement Item
				public boolean insertMeasurement(MeasurementDto dto, HttpServletRequest request, ServletConfig config) throws IOException {
					
					DataDb db = new DataDb(request);
					
					try {
						
						//Insert Query of PathologyItem
						
						PreparedStatement ps = db.connection.prepareStatement("INSERT INTO measurement_tb (user_id_fk,Name)VALUES(?,?);");

						ps.setInt(1, dto.getUser_id_fk());
						ps.setString(2, dto.getName());
						

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

				//Method for Show data on Manage page
					public ArrayList<MeasurementDto> getMeasurementInfo(ServletConfig config, HttpServletRequest request) throws IOException{
						
						DataDb db = new DataDb(request);
						PreparedStatement preparedStatement=null;
						
						ArrayList<MeasurementDto> list = new ArrayList<MeasurementDto>();
						
						try {
							
							String sql = "SELECT id,user_id_fk,Name, Status, current_in_date FROM measurement_tb;";
							// Select query for showing data on manage table
							preparedStatement = db.connection.prepareStatement(sql);
							
							ResultSet resultSet = preparedStatement.executeQuery();
							
							while(resultSet.next()) {
								
								MeasurementDto dto =new MeasurementDto();
								
								dto.setId(resultSet.getInt(1));
								dto.setUser_id_fk(resultSet.getInt(2));
								dto.setName(resultSet.getString(3));
								dto.setStatus(resultSet.getString(4));
								dto.setCurrent_in_date(resultSet.getString(5));
								 
								list.add(dto);
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
					//Show data on edit page according to id
						public MeasurementDto getMeasurementInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException{
							
							DataDb db = new DataDb(request);
							PreparedStatement preparedStatement=null;
							
							MeasurementDto dto = new MeasurementDto();
							
							try {
								
								String sql = "SELECT id,user_id_fk,Name, Status, current_in_date FROM measurement_tb WHERE  id=?;";
								// Select query for showing data on Edit page
								preparedStatement = db.connection.prepareStatement(sql);
								
								preparedStatement.setInt(1, id);
								
								ResultSet resultSet = preparedStatement.executeQuery();
								
								while(resultSet.next()) {
									dto.setId(resultSet.getInt(1));
									dto.setUser_id_fk(resultSet.getInt(2));
									dto.setName(resultSet.getString(3));
									dto.setStatus(resultSet.getString(4));
									dto.setCurrent_in_date(resultSet.getString(5));
									
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
							return dto;
						}
						
						// Method For Updating data on edit page
						public boolean updateMeasurement(MeasurementDto dto, HttpServletRequest request,
								ServletConfig config) throws IOException {
							
							DataDb db = new DataDb(request);
							PreparedStatement ps=null;
							
							try {
								
								String sql = "UPDATE measurement_tb SET  user_id_fk=?, Name=?, Status=?  WHERE id=?;";
								//Update Query for update data 
								ps = db.connection.prepareStatement(sql);

								ps.setInt(1, dto.getUser_id_fk());
								ps.setString(2, dto.getName());
								
								ps.setString(3, dto.getStatus());
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
						
						
						public ArrayList<MeasurementDto> getActiveMeasurementInfo(ServletConfig config, HttpServletRequest request) throws IOException{
							
							DataDb db = new DataDb(request);
							PreparedStatement preparedStatement=null;
							
							ArrayList<MeasurementDto> list = new ArrayList<MeasurementDto>();
							
							try {
								
								String sql = "SELECT id,user_id_fk, Name, Status, current_in_date FROM measurement_tb WHERE  Status='active';";
								preparedStatement = db.connection.prepareStatement(sql);
								
								ResultSet resultSet = preparedStatement.executeQuery();
								
								while(resultSet.next()) {
									
									MeasurementDto dto =new MeasurementDto();
									
									dto.setId(resultSet.getInt(1));
									dto.setUser_id_fk(resultSet.getInt(2));
									dto.setName(resultSet.getString(3));
									dto.setStatus(resultSet.getString(4));
									dto.setCurrent_in_date(resultSet.getString(5));
									
									
									list.add(dto);
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
	

}
