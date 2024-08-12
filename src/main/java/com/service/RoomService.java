package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.OrderItemDto;
import com.dto.RoomDto;

public class RoomService {

	ReciepeRelationService rec_ser = new ReciepeRelationService();

	// Insert Method for Room
	public boolean insertRoom(RoomDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO room_tb (room_no, room_name, rent, rent_double, rent_three, rent_fourth,user_id_fk)\r\n"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?);");

			ps.setString(1, dto.getRoom_no());
			ps.setString(2, dto.getRoom_name());
			ps.setFloat(3, dto.getRent());
			ps.setFloat(4, dto.getRent_double());
			ps.setFloat(5, dto.getRent_three());
			ps.setFloat(6, dto.getRent_fourth());
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
	public ArrayList<RoomDto> getRoomInfo(ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomDto> list = new ArrayList<RoomDto>();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT rt.id, rt.room_no, rt.room_name, rt.rent, rt.rent_double, rt.rent_three, rt.rent_fourth,rt.user_id_fk , rt.current_in_date, rt.status , rbt.id FROM room_tb rt LEFT JOIN room_booked_tb rbt ON  rt.id = rbt.room_id_fk AND rbt.status = 'Booked' ;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomDto dto = new RoomDto();

				dto.setId(resultSet.getInt(1));
				dto.setRoom_no(resultSet.getString(2));
				dto.setRoom_name(resultSet.getString(3));
				dto.setRent(resultSet.getFloat(4));
				dto.setRent_double(resultSet.getFloat(5));
				dto.setRent_three(resultSet.getFloat(6));
				dto.setRent_fourth(resultSet.getFloat(7));
				dto.setUser_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));
				dto.setBook_id_fk(resultSet.getInt(11));

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
	public ArrayList<RoomDto> getRoomAvailableInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomDto> list = new ArrayList<RoomDto>();

		try {

			preparedStatement = db.connection.prepareStatement(
					"SELECT id, room_no, room_name, rent, rent_double, rent_three, rent_fourth,user_id_fk , current_in_date,STATUS  FROM room_tb\r\n"
							+ "WHERE STATUS = 'Available';");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomDto dto = new RoomDto();

				dto.setId(resultSet.getInt(1));
				dto.setRoom_no(resultSet.getString(2));
				dto.setRoom_name(resultSet.getString(3));
				dto.setRent(resultSet.getFloat(4));
				dto.setRent_double(resultSet.getFloat(5));
				dto.setRent_three(resultSet.getFloat(6));
				dto.setRent_fourth(resultSet.getFloat(7));
				dto.setUser_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));

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
	public RoomDto getRoomInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		RoomDto dto = new RoomDto();

		try {

			preparedStatement = db.connection
					.prepareStatement("SELECT id, room_no, room_name, rent, rent_double, rent_three, rent_fourth,\"\r\n"
							+ "						+ \"user_id_fk , current_in_date, STATUS FROM room_tb WHERE  id=?;");

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setRoom_no(resultSet.getString(2));
				dto.setRoom_name(resultSet.getString(3));
				dto.setRent(resultSet.getFloat(4));
				dto.setRent_double(resultSet.getFloat(5));
				dto.setRent_three(resultSet.getFloat(6));
				dto.setRent_fourth(resultSet.getFloat(7));
				dto.setUser_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));

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

	// Show data on Room Rent page according to room type
	public float getRoomInfoByRoomType(String room_type, String room_no, ServletConfig config,
			HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		try {
			
			String Sql_select  ="";

			if (room_type.equals("Single Person")) {
				Sql_select="SELECT rent FROM room_tb where room_no=?;";

			}
			if (room_type.equals("Two Person")) {
				Sql_select= "SELECT rent_double FROM room_tb where room_no=?;";
			}
			if (room_type.equals("Three Person")) {
				Sql_select = "SELECT rent_three FROM room_tb where room_no=?;";
			}
			if (room_type.equals("Fourth Person")) {
				Sql_select = "SELECT rent_fourth FROM room_tb where room_no=?;";
			}
			
			preparedStatement = db.connection.prepareStatement(Sql_select);

			preparedStatement.setString(1, room_no);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				return resultSet.getFloat(1);

			}
		} catch (Exception e) {

		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {

				}
		}
		return 0;
	}

	// Method For Updating data on edit page
	public boolean updateRoom(RoomDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {

			// Update Query for update data
			ps = db.connection
					.prepareStatement("UPDATE room_tb SET room_no = ?, room_name = ?, rent = ?, rent_double = ?, \r\n"
							+ "rent_three = ?, rent_fourth = ?,  user_id_fk = ? WHERE id = ?;");

			ps.setString(1, dto.getRoom_no());
			ps.setString(2, dto.getRoom_name());
			ps.setFloat(3, dto.getRent());
			ps.setFloat(4, dto.getRent_double());
			ps.setFloat(5, dto.getRent_three());
			ps.setFloat(6, dto.getRent_fourth());
			ps.setInt(7, dto.getUser_id_fk());
			ps.setInt(8, dto.getId());

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

	public ArrayList<RoomDto> getActiveMenuItemInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<RoomDto> list = new ArrayList<RoomDto>();

		try {

			preparedStatement = db.connection
					.prepareStatement("SELECT id, room_no, room_name, rent, rent_double, rent_three, rent_fourth,"
							+ "user_id_fk , current_in_date,STATUS  FROM room_tb WHERE  Status='Active';");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				RoomDto dto = new RoomDto();

				dto.setId(resultSet.getInt(1));
				dto.setRoom_no(resultSet.getString(2));
				dto.setRoom_name(resultSet.getString(3));
				dto.setRent(resultSet.getFloat(4));
				dto.setRent_double(resultSet.getFloat(5));
				dto.setRent_three(resultSet.getFloat(6));
				dto.setRent_fourth(resultSet.getFloat(7));
				dto.setUser_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setStatus(resultSet.getString(10));

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

	public boolean insertOneRoomOrderItem(OrderItemDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {
		DataDb comm = new DataDb(request);

		try {

			// Insert a new row
			String insertQuery = "INSERT INTO room_order_item_tb (room_id, item_id_fk, item_name, item_code, item_qty, item_rate, bill_id_fk,order_date) VALUES (?, ?, ?, ?, ?, ?, ?,?);";
			PreparedStatement insertPs = comm.connection.prepareStatement(insertQuery);
			insertPs.setInt(1, dto.getRoom_id());
			insertPs.setInt(2, dto.getItem_id_fk());
			insertPs.setString(3, dto.getItem_name());
			insertPs.setString(4, dto.getItem_code());
			insertPs.setFloat(5, dto.getItem_qty());
			insertPs.setFloat(6, dto.getItem_rate());
			insertPs.setInt(7, dto.getBill_id_fk());
			insertPs.setString(8, dto.getOrder_date());

			System.out.println("Insert Query: " + insertPs.toString());
			int insertedRows = insertPs.executeUpdate();

			if (insertedRows > 0) {

				rec_ser.managestockByMenu(dto.getItem_id_fk(), dto.getItem_qty(), "", request, config);

				String updateQuery = "UPDATE room_booked_tb SET food_amt = food_amt + ?\r\n" + "WHERE id = ?;";

				PreparedStatement updatePs = comm.connection.prepareStatement(updateQuery);
				updatePs.setFloat(1, dto.getItem_rate() * dto.getItem_qty());
				updatePs.setInt(2, dto.getBill_id_fk());
				System.out.println("Update Query: " + updatePs.toString());
				int updatedRows = updatePs.executeUpdate();

				if (updatedRows > 0) {
					return true;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (comm.connection != null) {
				try {
					comm.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	public boolean deleteOneRoomOrderItem(int order_item_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb comm = new DataDb(request);

		try {

			int room_id = 0, item_id_fk = 0, bill_id_fk= 0;
			float item_rate = 0, item_qty = 0;

			String sql = "SELECT item_id_fk,room_id,item_qty,item_rate, bill_id_fk \r\n" + "FROM room_order_item_tb WHERE id = ?;";
			// Select query for showing data on Edit page
			PreparedStatement select = comm.connection.prepareStatement(sql);

			select.setInt(1, order_item_id);
			
			ResultSet resultSet = select.executeQuery();
			System.out.println(select);
			while (resultSet.next()) {
				item_id_fk = resultSet.getInt("item_id_fk");
				room_id = resultSet.getInt("room_id");
				item_qty = resultSet.getFloat("item_qty");
				item_rate = resultSet.getFloat("item_rate");
				bill_id_fk =  resultSet.getInt("bill_id_fk");
				
				String deleteQuery = "DELETE FROM room_order_item_tb WHERE id = ?;";
				PreparedStatement deletePs = comm.connection.prepareStatement(deleteQuery);
				deletePs.setInt(1, order_item_id);
				
				int deletedRows = deletePs.executeUpdate();
				System.out.println(deletePs);
				if (deletedRows != 0) {

					rec_ser.managestockByMenu(item_id_fk, item_qty, "increase", request, config);

					String updateQuery = "UPDATE room_booked_tb SET food_amt = food_amt - ? \r\n"
							+ "WHERE id = ?;";

					PreparedStatement updatePs = comm.connection.prepareStatement(updateQuery);
					updatePs.setFloat(1, item_rate * item_qty);
					updatePs.setInt(2, bill_id_fk);
					System.out.println("Update Query: " + updatePs.toString());
					int updatedRows = updatePs.executeUpdate();

					if (updatedRows > 0) {
						return true;
					}
				}

			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<OrderItemDto> getRoomOrderItemInfoByRoomId(int bill_id_fk, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;
		ArrayList<OrderItemDto> list = new ArrayList<OrderItemDto>();

		try {
			preparedStatement = db.connection.prepareStatement(
					"SELECT id, room_id, item_id_fk, item_code, item_name, item_qty, item_rate, bill_id_fk, current_in_date,order_date FROM room_order_item_tb WHERE  bill_id_fk = ?;");
			preparedStatement.setInt(1, bill_id_fk);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				OrderItemDto dto = new OrderItemDto();
				dto.setId(resultSet.getInt(1));
				dto.setRoom_id(resultSet.getInt(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_code(resultSet.getString(4));
				dto.setItem_name(resultSet.getString(5));
				dto.setItem_qty(resultSet.getFloat(6));
				dto.setItem_rate(resultSet.getFloat(7));
				dto.setBill_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));
				dto.setOrder_date(resultSet.getString(10));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.connection != null) {
				try {
					db.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

}
