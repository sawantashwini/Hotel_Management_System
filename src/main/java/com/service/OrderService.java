package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.ArrayOrderItemDto;
import com.dto.OrderBillDto;
import com.dto.OrderItemDto;
import com.dto.PaymentDto;
import com.mysql.jdbc.Statement;

public class OrderService {

	PaymentService pay_ser = new PaymentService();
	ReciepeRelationService rec_ser = new ReciepeRelationService();

	public boolean insertOneOrderItem(OrderItemDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {
		DataDb comm = new DataDb(request);

		try {

			// Insert a new row
			String insertQuery = "INSERT INTO order_item_tb (table_id, item_id_fk, item_name, item_code, item_qty, item_rate, order_id_fk) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement insertPs = comm.connection.prepareStatement(insertQuery);
			insertPs.setInt(1, dto.getTable_id());
			insertPs.setInt(2, dto.getItem_id_fk());
			insertPs.setString(3, dto.getItem_name());
			insertPs.setString(4, dto.getItem_code());
			insertPs.setFloat(5, dto.getItem_qty());
			insertPs.setFloat(6, dto.getItem_rate());
			insertPs.setInt(7, dto.getBill_id_fk());
			System.out.println("Insert Query: " + insertPs.toString());
			int insertedRows = insertPs.executeUpdate();

			if (insertedRows > 0) {
				rec_ser.managestockByMenu(dto.getItem_id_fk(), dto.getItem_qty(), "", request, config);
				changeTableStatus(dto.getTable_id(), "Active", request, config);
				return true;
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

	public ArrayList<OrderItemDto> getOrderItemInfoByTableId(int table_id, int order_id, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;
		ArrayList<OrderItemDto> list = new ArrayList<OrderItemDto>();

		try {

			String select_sql = "SELECT id, table_id, item_id_fk, item_code, item_name, item_qty, item_rate, order_id_fk, current_in_date"
					+ " FROM order_item_tb ";

			if (order_id == 0) {
				preparedStatement = db.connection
						.prepareStatement(select_sql + "WHERE table_id = ? AND order_id_fk = 0;");
				preparedStatement.setInt(1, table_id);
			} else {
				preparedStatement = db.connection.prepareStatement(select_sql + "WHERE  order_id_fk = ?;");
				preparedStatement.setInt(1, order_id);
			}

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				OrderItemDto dto = new OrderItemDto();
				dto.setId(resultSet.getInt(1));
				dto.setTable_id(resultSet.getInt(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_code(resultSet.getString(4));
				dto.setItem_name(resultSet.getString(5));
				dto.setItem_qty(resultSet.getFloat(6));
				dto.setItem_rate(resultSet.getFloat(7));
				dto.setOrder_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));

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
	public ArrayList<OrderItemDto> geItemWiseInfo(String time1, String time2,String food_item,String liquor_item, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;
		ArrayList<OrderItemDto> list = new ArrayList<OrderItemDto>();

		try {

			
			if (!time1.equalsIgnoreCase("")&&!time2.equalsIgnoreCase("")&&food_item.equalsIgnoreCase("")&&liquor_item.equalsIgnoreCase("")) {
				preparedStatement = db.connection.prepareStatement("SELECT ot.id, ot.table_id, ot.item_id_fk, ot.item_code, ot.item_name, ot.item_qty, ot.item_rate, ot.order_id_fk, ob.current_in_date,ob.bill_date \r\n"
						+ "FROM order_item_tb ot INNER JOIN order_bill_tb ob ON ob.id=ot.order_id_fk WHERE ob.current_in_date  BETWEEN ? AND ? ;");
				preparedStatement.setString(1, time1);
				preparedStatement.setString(2, time2);
			
			}
		
			else if (!time1.equalsIgnoreCase("")&&!time2.equalsIgnoreCase("")&&!food_item.equalsIgnoreCase("")&&liquor_item.equalsIgnoreCase("")) {
				preparedStatement = db.connection.prepareStatement("SELECT ot.id, ot.table_id, ot.item_id_fk, ot.item_code, ot.item_name, ot.item_qty, ot.item_rate, ot.order_id_fk, ob.current_in_date,ob.bill_date \r\n"
						+ "FROM order_item_tb ot INNER JOIN order_bill_tb ob ON ob.id=ot.order_id_fk WHERE ob.current_in_date  BETWEEN ? AND ? AND ot.item_name=?;");
				preparedStatement.setString(1, time1);
				preparedStatement.setString(2, time2);
				preparedStatement.setString(3, food_item);
				}
				else if (!time1.equalsIgnoreCase("")&&!time2.equalsIgnoreCase("")&&!liquor_item.equalsIgnoreCase("")&&food_item.equalsIgnoreCase("")) {
					preparedStatement = db.connection.prepareStatement("SELECT ot.id, ot.table_id, ot.item_id_fk, ot.item_code, ot.item_name, ot.item_qty, ot.item_rate, ot.order_id_fk, ob.current_in_date,ob.bill_date \r\n"
							+ "FROM order_item_tb ot INNER JOIN order_bill_tb ob ON ob.id=ot.order_id_fk WHERE ob.current_in_date  BETWEEN ? AND ? AND ot.item_name=?;");
					preparedStatement.setString(1, time1);
					preparedStatement.setString(2, time2);
					preparedStatement.setString(3, liquor_item);
					}
			
				else if (time1.equalsIgnoreCase("")&&time2.equalsIgnoreCase("")&&!liquor_item.equalsIgnoreCase("")&&food_item.equalsIgnoreCase("")) {
					preparedStatement = db.connection.prepareStatement("SELECT ot.id, ot.table_id, ot.item_id_fk, ot.item_code, ot.item_name, ot.item_qty, ot.item_rate, ot.order_id_fk, ob.current_in_date,ob.bill_date \r\n"
							+ "FROM order_item_tb ot INNER JOIN order_bill_tb ob ON ob.id=ot.order_id_fk WHERE  ot.item_name=?;");
					
					preparedStatement.setString(1, liquor_item);
				}
				else if (time1.equalsIgnoreCase("")&&time2.equalsIgnoreCase("")&&!food_item.equalsIgnoreCase("")&&liquor_item.equalsIgnoreCase("")) {
					preparedStatement = db.connection.prepareStatement("SELECT ot.id, ot.table_id, ot.item_id_fk, ot.item_code, ot.item_name, ot.item_qty, ot.item_rate, ot.order_id_fk, ob.current_in_date,ob.bill_date \r\n"
							+ "FROM order_item_tb ot INNER JOIN order_bill_tb ob ON ob.id=ot.order_id_fk WHERE  ot.item_name=?;");
					
					preparedStatement.setString(1, food_item);
				}
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				OrderItemDto dto = new OrderItemDto();
				dto.setId(resultSet.getInt(1));
				dto.setTable_id(resultSet.getInt(2));
				dto.setItem_id_fk(resultSet.getInt(3));
				dto.setItem_code(resultSet.getString(4));
				dto.setItem_name(resultSet.getString(5));
				dto.setItem_qty(resultSet.getFloat(6));
				dto.setItem_rate(resultSet.getFloat(7));
				dto.setOrder_id_fk(resultSet.getInt(8));
				dto.setCurrent_in_date(resultSet.getString(9));

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
	public String deleteOneOrderItem(int order_item_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb comm = new DataDb(request);

		try {

			int table_status = 0, menu_id = 0, table_id = 0;
			float qty = 0;

			String select_sql = "SELECT item_id_fk,item_qty,table_id	 \r\n" + "FROM order_item_tb WHERE id =?;";

			PreparedStatement select_ps = comm.connection.prepareStatement(select_sql);

			select_ps.setInt(1, order_item_id);

			ResultSet rs_order = select_ps.executeQuery();

			while (rs_order.next()) {

				menu_id = rs_order.getInt(1);
				qty = rs_order.getFloat(2);
				table_id = rs_order.getInt(3);

				rec_ser.managestockByMenu(menu_id, qty, "increase", request, config);

			}

			String deleteQuery = "DELETE FROM order_item_tb WHERE id = ?;";
			PreparedStatement deletePs = comm.connection.prepareStatement(deleteQuery);
			deletePs.setInt(1, order_item_id);
			System.out.println(deletePs);
			int deletedRows = deletePs.executeUpdate();

			if (deletedRows != 0) {

				String selectQuery = "SELECT id,item_qty FROM order_item_tb WHERE table_id = ? AND order_id_fk = 0;";
				PreparedStatement selectPs = comm.connection.prepareStatement(selectQuery);
				selectPs.setInt(1, table_id);
				System.out.println(selectPs);

				ResultSet resultSet = selectPs.executeQuery();

				while (resultSet.next()) {
					table_status = resultSet.getInt(1);
				}
				if (table_status != 0) {
					System.out.println("Yes");
					return "Yes";

				} else {
					boolean b = changeTableStatus(table_id, "Available", request, config);
					if (b) {
						System.out.println("Free");
						return "Free";
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "No";
	}

	public boolean switchTable(int old_table_id, int new_table_id, String manager_name, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb comm = new DataDb(request);

		try {
			// Update order_item_tb table with new table_id
			String updateQuery = "UPDATE order_item_tb SET table_id = ? WHERE table_id = ? AND order_id_fk = 0;";
			PreparedStatement updatePs = comm.connection.prepareStatement(updateQuery);
			updatePs.setInt(1, new_table_id);
			updatePs.setInt(2, old_table_id);
			int updatedRows = updatePs.executeUpdate();

			// Update manager of new_table_id
			UpdateTableManager(new_table_id, manager_name, request, config);

			// Update status of old_table_id to 'Available'
			changeTableStatus(old_table_id, "Available", request, config);

			// Update status of new_table_id to 'Active'
			changeTableStatus(new_table_id, "Active", request, config);

			if (updatedRows > 0) {
				return true;
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

	public int insertOrderWithoutBilling(OrderBillDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {
		DataDb comm = new DataDb(request);

		try {
			String insertQuery = "INSERT INTO order_bill_tb (table_id, without_gst_amount,final_amount, bill_date, session_year, user_id_fk, manager_name) VALUES (?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement insertPs = comm.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			insertPs.setInt(1, dto.getTable_id());
			insertPs.setFloat(2, dto.getWithout_gst_amount());
			insertPs.setFloat(3, dto.getWithout_gst_amount());
			insertPs.setString(4, dto.getBill_date());
			insertPs.setString(5, dto.getSession_year());
			insertPs.setInt(6, dto.getUser_id_fk());
			insertPs.setString(7, dto.getManager_name());
			System.out.println("Insert Query: " + insertPs.toString());

			int insertedRows = insertPs.executeUpdate();

			ResultSet rs = insertPs.getGeneratedKeys();
			rs.next();
			int order_id = rs.getInt(1);

			if (insertedRows > 0) {

				String updateQuery = "UPDATE order_item_tb SET order_id_fk = ? WHERE table_id = ? AND order_id_fk = 0;";
				PreparedStatement updatePs = comm.connection.prepareStatement(updateQuery);
				updatePs.setInt(1, order_id);
				updatePs.setInt(2, dto.getTable_id());
				System.out.println("Update Query: " + updatePs.toString());
				int updatedRows = updatePs.executeUpdate();

				if (updatedRows > 0) {
					// Update status of new_table_id to 'Active'
					changeTableStatus(dto.getTable_id(), "Available", request, config);
					return order_id;
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

		return 0;
	}

	public int insertKotBill(OrderBillDto dto, HttpServletRequest request, ServletConfig config) throws IOException {
		DataDb comm = new DataDb(request);

		try {
			String insertQuery = "INSERT INTO order_kot_bill_tb \r\n"
					+ "(table_id,bill_amount,manager_name,bill_date,session_year,user_id_fk) \r\n"
					+ "VALUES (?,?,?,?,?,?);";
			PreparedStatement insertPs = comm.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			insertPs.setInt(1, dto.getTable_id());
			insertPs.setFloat(2, dto.getWithout_gst_amount());
			insertPs.setString(3, dto.getManager_name());
			insertPs.setString(4, dto.getBill_date());
			insertPs.setString(5, dto.getSession_year());
			insertPs.setInt(6, dto.getUser_id_fk());

			System.out.println("Insert Query: " + insertPs.toString());

			int insertedRows = insertPs.executeUpdate();

			ResultSet rs = insertPs.getGeneratedKeys();
			rs.next();
			int kot_id = rs.getInt(1);

			if (insertedRows > 0) {

				// Update status of new_table_id to 'Active'
				changeTableStatus(dto.getTable_id(), "Active", request, config);
				return kot_id;

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

		return 0;
	}

	public boolean insertKotOrderBillItem(ArrayOrderItemDto arrdto, int bill_id_fk, int table_id,
			HttpServletRequest request, ServletConfig config) throws IOException {
		DataDb comm = new DataDb(request);
		boolean allInsertionsSuccessful = true;

		try {
			for (int i = 0; i < arrdto.getItem_id_fk().length; i++) {
				String insertQuery = "INSERT INTO order_kot_item_tb "
						+ "(table_id, item_id_fk, item_name, item_code, item_qty, item_rate, bill_id_fk)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?);";

				PreparedStatement insertPs = comm.connection.prepareStatement(insertQuery);
				insertPs.setInt(1, table_id);
				insertPs.setString(2, arrdto.getItem_id_fk()[i]);
				insertPs.setString(3, arrdto.getItem_name()[i]);
				insertPs.setString(4, arrdto.getItem_code()[i]);
				insertPs.setString(5, arrdto.getItem_qty()[i]);
				insertPs.setString(6, arrdto.getItem_rate()[i]);
				insertPs.setInt(7, bill_id_fk);
				System.out.println("Insert Query: " + insertPs.toString());
				int insertedRows = insertPs.executeUpdate();

				if (insertedRows <= 0) {
					allInsertionsSuccessful = false;
					break;
				}

				String insertOrderItem = "INSERT INTO order_item_tb (table_id, item_id_fk, item_name, item_code, item_qty, item_rate) VALUES (?, ?, ?, ?, ?, ?);";
				PreparedStatement insertPs2 = comm.connection.prepareStatement(insertOrderItem);
				insertPs2.setInt(1, table_id);
				insertPs2.setString(2, arrdto.getItem_id_fk()[i]);
				insertPs2.setString(3, arrdto.getItem_name()[i]);
				insertPs2.setString(4, arrdto.getItem_code()[i]);
				insertPs2.setString(5, arrdto.getItem_qty()[i]);
				insertPs2.setString(6, arrdto.getItem_rate()[i]);
				System.out.println("Insert Query: " + insertPs2.toString());
				int inserted2 = insertPs2.executeUpdate();

				rec_ser.managestockByMenu(Integer.parseInt(arrdto.getItem_id_fk()[i]),
						Float.parseFloat(arrdto.getItem_qty()[i]), "", request, config);

				allInsertionsSuccessful = inserted2 > 0; // Set the flag based on the second insertion.

			}

		} catch (Exception e) {
			e.printStackTrace();
			allInsertionsSuccessful = false; // Set the flag to false in case of any exception.
		}

		return allInsertionsSuccessful;
	}

	public boolean UpdateTableManager(int table_id, String manager_name, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb comm = new DataDb(request);

		try {
			String updateQuery = "UPDATE table_tb SET manager_name = ? WHERE id = ?;";
			PreparedStatement updatePs = comm.connection.prepareStatement(updateQuery);
			updatePs.setString(1, manager_name);
			updatePs.setInt(2, table_id);
			System.out.println("Update Query: " + updatePs.toString());
			int updatedRows = updatePs.executeUpdate();

			if (updatedRows > 0) {
				return true;
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

	public boolean changeTableStatus(int table_id, String status, HttpServletRequest request, ServletConfig config)
			throws IOException {
		DataDb comm = new DataDb(request);

		try {

			String sql = "UPDATE table_tb SET STATUS = ? WHERE id = ?;";
			PreparedStatement updatedps = comm.connection.prepareStatement(sql);

			updatedps.setString(1, status);
			updatedps.setInt(2, table_id);
			int updatedRows = updatedps.executeUpdate();

			if (updatedRows > 0) {
				return true;
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

	

	// Get All Kot bill Info
	public ArrayList<OrderBillDto> getAllKotBillInfo(ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;
		ArrayList<OrderBillDto> list = new ArrayList<OrderBillDto>();

		try {
			String selectquery = "SELECT ob.id, ob.table_id, ob.bill_amount, \r\n"
					+ "  ob.bill_date, ob.session_year, ob.current_in_date, ob.STATUS, ob.user_id_fk,\r\n"
					+ "  ob.manager_name, tb.name AS table_name FROM order_kot_bill_tb ob\r\n"
					+ "  INNER JOIN table_tb tb ON ob.table_id = tb.id\r\n" + "  WHERE ob.status = 'pending'";

			preparedStatement = db.connection.prepareStatement(selectquery);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {
				OrderBillDto dto = new OrderBillDto();
				dto.setId(resultSet.getInt("id"));
				dto.setTable_id(resultSet.getInt("table_id"));
				dto.setWithout_gst_amount(resultSet.getFloat("bill_amount"));
				dto.setBill_date(resultSet.getString("bill_date"));
				dto.setSession_year(resultSet.getString("session_year"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setManager_name(resultSet.getString("manager_name"));
				dto.setTable_name(resultSet.getString("table_name"));

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

	// Get All Order Item Info by Id
	public ArrayList<OrderItemDto> getAllOrderItemByOrderId(int order_id, ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement select_ps = null;
		ArrayList<OrderItemDto> list = new ArrayList<OrderItemDto>();

		try {
			String selectquery = "SELECT 	id, table_id, item_id_fk, item_code, item_name, item_qty,\r\n"
					+ " item_rate, order_id_fk, current_in_date FROM \r\n" + "	order_item_tb WHERE order_id_fk=?;";

			select_ps = db.connection.prepareStatement(selectquery);
			select_ps.setInt(1, order_id);
			System.out.println(select_ps);
			ResultSet resultSet = select_ps.executeQuery();

			while (resultSet.next()) {
				OrderItemDto dto = new OrderItemDto();
				dto.setId(resultSet.getInt("id"));
				dto.setTable_id(resultSet.getInt("table_id"));
				dto.setItem_id_fk(resultSet.getInt("item_id_fk"));
				dto.setItem_code(resultSet.getString("item_code"));
				dto.setItem_name(resultSet.getString("item_name"));
				dto.setItem_qty(resultSet.getFloat("item_qty"));
				dto.setItem_rate(resultSet.getFloat("item_rate"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));

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

	// Get All Kot Item Info by Id
	public ArrayList<OrderItemDto> getKotItemByKotId(int order_id, ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement select_ps = null;
		ArrayList<OrderItemDto> list = new ArrayList<OrderItemDto>();

		try {
			String selectquery = "SELECT 	id, table_id, item_id_fk, item_code, item_name, item_qty,\r\n"
					+ " item_rate, bill_id_fk, current_in_date FROM \r\n" + "	order_kot_item_tb WHERE bill_id_fk=?;";

			select_ps = db.connection.prepareStatement(selectquery);
			select_ps.setInt(1, order_id);
			System.out.println(select_ps);
			ResultSet resultSet = select_ps.executeQuery();

			while (resultSet.next()) {
				OrderItemDto dto = new OrderItemDto();
				dto.setId(resultSet.getInt("id"));
				dto.setTable_id(resultSet.getInt("table_id"));
				dto.setItem_id_fk(resultSet.getInt("item_id_fk"));
				dto.setItem_code(resultSet.getString("item_code"));
				dto.setItem_name(resultSet.getString("item_name"));
				dto.setItem_qty(resultSet.getFloat("item_qty"));
				dto.setItem_rate(resultSet.getFloat("item_rate"));
				dto.setOrder_id_fk(resultSet.getInt("bill_id_fk"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));

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

	// Get All Order Info by Id
	public OrderBillDto getOrderInfoById(int id, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;
		OrderBillDto dto = new OrderBillDto();

		try {

			String sql = "SELECT ob.id, ob.cust_id_fk, ob.cust_acc_id_fk, ob.table_id, ob.gst_amount, \r\n"
					+ "ob.without_gst_amount, ob.with_gst_amount, ob.old_due_amount, ob.final_amount, \r\n"
					+ "ob.paid_amount,ob.return_amount, ob.discount_amount, ob.bill_date, ob.cust_type, \r\n"
					+ "ob.payment_mode, ob.cash_amount, ob.online_amount,ob.online_date, ob.online_remark, \r\n"
					+ "ob.online_way, ob.bank_id_fk, ob.remark, ob.session_year, ob.current_in_date, ob.STATUS, ob.gst_status, \r\n"
					+ "ob.user_id_fk, ob.manager_name,tb.name table_name,ob.cust_name,ob.cust_mob_no,ob.cust_address,ob.cust_company_name,ob.cust_gst_no,ob.cust_dob,ob.cust_doa\r\n"
					+ "FROM order_bill_tb ob \r\n" + "INNER JOIN table_tb tb ON ob.table_id = tb.id \r\n"
					+ "WHERE ob.id=?;";

			preparedStatement = db.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setCust_id_fk(resultSet.getInt(2));
				dto.setCust_acc_id_fk(resultSet.getInt(3));
				dto.setTable_id(resultSet.getInt(4));
				dto.setGst_amount(resultSet.getFloat(5));
				dto.setWithout_gst_amount(resultSet.getFloat(6));
				dto.setWith_gst_amount(resultSet.getFloat(7));
				dto.setOld_due_amount(resultSet.getFloat(8));
				dto.setFinal_amount(resultSet.getFloat(9));
				dto.setPaid_amount(resultSet.getFloat(10));
				dto.setReturn_amount(resultSet.getFloat(11));
				dto.setDiscount_amount(resultSet.getFloat(12));
				dto.setBill_date(resultSet.getString(13));
				dto.setCust_type(resultSet.getString(14));
				dto.setPayment_mode(resultSet.getString(15));
				dto.setCash_amount(resultSet.getFloat(16));
				dto.setOnline_amount(resultSet.getFloat(17));
				dto.setOnline_date(resultSet.getString(18));
				dto.setOnline_remark(resultSet.getString(19));
				dto.setOnline_way(resultSet.getString(20));
				dto.setBank_id_fk(resultSet.getInt(21));
				dto.setRemark(resultSet.getString(22));
				dto.setSession_year(resultSet.getString(23));
				dto.setCurrent_in_date(resultSet.getString(24));
				dto.setStatus(resultSet.getString(25));
				dto.setGst_status(resultSet.getString(26));
				dto.setUser_id_fk(resultSet.getInt(27));
				dto.setManager_name(resultSet.getString(28));
				dto.setTable_name(resultSet.getString(29));
				dto.setCust_name(resultSet.getString(30));
				dto.setCust_mob_no(resultSet.getString(31));
				dto.setCust_address(resultSet.getString(32));
				dto.setCompany_name(resultSet.getString(33));
				dto.setGst_no(resultSet.getString(34));
				dto.setDob(resultSet.getString(35));
				dto.setDoa(resultSet.getString(36));

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

		return dto;
	}

	// Get Kot Order Info By Id
	public OrderBillDto getKotOrderInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;
		OrderBillDto dto = new OrderBillDto();

		try {

			String sql = "SELECT okt.id,okt.table_id,okt.bill_date,okt.bill_amount,okt.bill_id_fk,\r\n"
					+ "okt.session_year,okt.current_in_date,okt.status,okt.user_id_fk,okt.manager_name,\r\n"
					+ "tb.name table_name\r\n" + "FROM order_kot_bill_tb okt\r\n"
					+ "INNER JOIN table_tb tb ON tb.id=okt.table_id\r\n" + "WHERE okt.id=?;";

			preparedStatement = db.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(preparedStatement);

			while (resultSet.next()) {

				dto.setId(resultSet.getInt("id"));
				dto.setTable_id(resultSet.getInt("table_id"));
				dto.setBill_date(resultSet.getString("bill_date"));
				dto.setWithout_gst_amount(resultSet.getFloat("bill_amount"));
				dto.setKot_id(resultSet.getInt("bill_id_fk"));
				dto.setSession_year(resultSet.getString("session_year"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setManager_name(resultSet.getString("manager_name"));
				dto.setTable_name(resultSet.getString("table_name"));

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

		return dto;
	}

	// Get All Complete Order Info
	public ArrayList<OrderBillDto> getAllOrderInfo(String time1, String time2, String status,  ServletConfig config,
			HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);
		PreparedStatement selectPS = null;

		ArrayList<OrderBillDto> list = new ArrayList<OrderBillDto>();

		try {

			String sql = "SELECT ob.id, ob.cust_id_fk, ob.cust_acc_id_fk, ob.table_id, "
					+ "ob.gst_amount, ob.without_gst_amount, ob.with_gst_amount, "
					+ "ob.old_due_amount, ob.final_amount, ob.paid_amount,ob.return_amount, ob.discount_amount, "
					+ "ob.bill_date, ob.cust_type, ob.payment_mode, ob.cash_amount, ob.online_amount,"
					+ "ob.online_date, ob.online_remark, ob.online_way, ob.remark, ob.session_year, ob.current_in_date, "
					+ "ob.STATUS, ob.gst_status, ob.user_id_fk, ob.manager_name,tb.name table_name,ob.cust_name,ob.cust_mob_no,ob.cust_address "
					+ "FROM order_bill_tb ob INNER JOIN table_tb tb ON ob.table_id = tb.id "
					+ "WHERE ob.status = '"+ status +"' ";

			if (!time1.equals("") && !time2.equals("")) {
				selectPS = db.connection.prepareStatement(
						sql + " AND ob.current_in_date BETWEEN ? AND ? ORDER BY ob.current_in_date DESC;");
				selectPS.setString(1, time1);
				selectPS.setString(2, time2);
			} else {
				selectPS = db.connection.prepareStatement(sql + "AND ob.bill_date=CURDATE() ORDER BY ob.current_in_date DESC;");
			}

			System.out.println(selectPS);

			ResultSet resultSet = selectPS.executeQuery();

			while (resultSet.next()) {

				OrderBillDto dto = new OrderBillDto();

				dto.setId(resultSet.getInt("id"));
				dto.setCust_id_fk(resultSet.getInt("cust_id_fk"));
				dto.setCust_acc_id_fk(resultSet.getInt("cust_acc_id_fk"));
				dto.setTable_id(resultSet.getInt("table_id"));
				dto.setGst_amount(resultSet.getFloat("gst_amount"));
				dto.setWithout_gst_amount(resultSet.getFloat("without_gst_amount"));
				dto.setWith_gst_amount(resultSet.getFloat("with_gst_amount"));
				dto.setOld_due_amount(resultSet.getFloat("old_due_amount"));
				dto.setFinal_amount(resultSet.getFloat("final_amount"));
				dto.setPaid_amount(resultSet.getFloat("paid_amount"));
				dto.setReturn_amount(resultSet.getFloat("return_amount"));
				dto.setDiscount_amount(resultSet.getFloat("discount_amount"));
				dto.setBill_date(resultSet.getString("bill_date"));
				dto.setCust_type(resultSet.getString("cust_type"));
				dto.setPayment_mode(resultSet.getString("payment_mode"));
				dto.setCash_amount(resultSet.getFloat("cash_amount"));
				dto.setOnline_amount(resultSet.getFloat("online_amount"));
				dto.setOnline_date(resultSet.getString("online_date"));
				dto.setOnline_remark(resultSet.getString("online_remark"));
				dto.setOnline_way(resultSet.getString("online_way"));
				dto.setRemark(resultSet.getString("remark"));
				dto.setSession_year(resultSet.getString("session_year"));
				dto.setCurrent_in_date(resultSet.getString("current_in_date"));
				dto.setStatus(resultSet.getString("STATUS"));
				dto.setGst_status(resultSet.getString("gst_status"));
				dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
				dto.setManager_name(resultSet.getString("manager_name"));
				dto.setTable_name(resultSet.getString("table_name"));
				dto.setCust_name(resultSet.getString("cust_name"));
				dto.setCust_mob_no(resultSet.getString("cust_mob_no"));
				dto.setCust_address(resultSet.getString("cust_address"));

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
	
	
	// Get All Complete Order Info
		public ArrayList<OrderBillDto> getAllCustOrderInfo(String time1, String time2, int cust_id,  ServletConfig config,
				HttpServletRequest request) throws IOException {
			DataDb db = new DataDb(request);
			PreparedStatement selectPS = null;

			ArrayList<OrderBillDto> list = new ArrayList<OrderBillDto>();

			try {

				String sql = "SELECT ob.id, ob.cust_id_fk, ob.cust_acc_id_fk, ob.table_id, "
						+ "ob.gst_amount, ob.without_gst_amount, ob.with_gst_amount, "
						+ "ob.old_due_amount, ob.final_amount, ob.paid_amount,ob.return_amount, ob.discount_amount, "
						+ "ob.bill_date, ob.cust_type, ob.payment_mode, ob.cash_amount, ob.online_amount,"
						+ "ob.online_date, ob.online_remark, ob.online_way, ob.remark, ob.session_year, ob.current_in_date, "
						+ "ob.STATUS, ob.gst_status, ob.user_id_fk, ob.manager_name,tb.name table_name,ob.cust_name,ob.cust_mob_no,ob.cust_address "
						+ "FROM order_bill_tb ob INNER JOIN table_tb tb ON ob.table_id = tb.id "
						+ "WHERE ob.cust_id_fk = '"+ cust_id +"' ";

				if (!time1.equals("") && !time2.equals("")) {
					selectPS = db.connection.prepareStatement(
							sql + " AND ob.current_in_date BETWEEN ? AND ? ORDER BY ob.current_in_date DESC;");
					selectPS.setString(1, time1);
					selectPS.setString(2, time2);
				} else {
					selectPS = db.connection.prepareStatement(sql + " ORDER BY ob.current_in_date DESC;");
				}

				System.out.println(selectPS);

				ResultSet resultSet = selectPS.executeQuery();

				while (resultSet.next()) {

					OrderBillDto dto = new OrderBillDto();

					dto.setId(resultSet.getInt("id"));
					dto.setCust_id_fk(resultSet.getInt("cust_id_fk"));
					dto.setCust_acc_id_fk(resultSet.getInt("cust_acc_id_fk"));
					dto.setTable_id(resultSet.getInt("table_id"));
					dto.setGst_amount(resultSet.getFloat("gst_amount"));
					dto.setWithout_gst_amount(resultSet.getFloat("without_gst_amount"));
					dto.setWith_gst_amount(resultSet.getFloat("with_gst_amount"));
					dto.setOld_due_amount(resultSet.getFloat("old_due_amount"));
					dto.setFinal_amount(resultSet.getFloat("final_amount"));
					dto.setPaid_amount(resultSet.getFloat("paid_amount"));
					dto.setReturn_amount(resultSet.getFloat("return_amount"));
					dto.setDiscount_amount(resultSet.getFloat("discount_amount"));
					dto.setBill_date(resultSet.getString("bill_date"));
					dto.setCust_type(resultSet.getString("cust_type"));
					dto.setPayment_mode(resultSet.getString("payment_mode"));
					dto.setCash_amount(resultSet.getFloat("cash_amount"));
					dto.setOnline_amount(resultSet.getFloat("online_amount"));
					dto.setOnline_date(resultSet.getString("online_date"));
					dto.setOnline_remark(resultSet.getString("online_remark"));
					dto.setOnline_way(resultSet.getString("online_way"));
					dto.setRemark(resultSet.getString("remark"));
					dto.setSession_year(resultSet.getString("session_year"));
					dto.setCurrent_in_date(resultSet.getString("current_in_date"));
					dto.setStatus(resultSet.getString("STATUS"));
					dto.setGst_status(resultSet.getString("gst_status"));
					dto.setUser_id_fk(resultSet.getInt("user_id_fk"));
					dto.setManager_name(resultSet.getString("manager_name"));
					dto.setTable_name(resultSet.getString("table_name"));
					dto.setCust_name(resultSet.getString("cust_name"));
					dto.setCust_mob_no(resultSet.getString("cust_mob_no"));
					dto.setCust_address(resultSet.getString("cust_address"));

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

	// Get Order Billing
	public int completeOrderBilling(OrderBillDto dto, PaymentDto pay_dto, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb db = new DataDb(request);

		try {

			int new_cash_id = 0, new_online_id = 0;

			// bill for table ....
			if (dto.getCust_name() == null || dto.getCust_name().equals("")) {

				dto.setCust_id_fk(0);
				dto.setCust_acc_id_fk(0);
				dto.setCust_mob_no("");
				dto.setCust_address("");
				dto.setGst_no("");
				dto.setCompany_name("");
				dto.setDob("");
				dto.setDoa("");
			}

			// bill for customer ....
			else {

				// insert customer if customer is new and regular....
				if (dto.getCust_id_fk() == 0 && dto.getRegular().equalsIgnoreCase("Yes")) {
					dto.setCust_id_fk(insertCustomerFromOrderBill(dto, request, config));
				}

				// manage customer account....
				if (dto.getCust_id_fk() > 0) {

					// update customer due info
					PreparedStatement cust_due = db.connection.prepareStatement(
							"UPDATE customer_info_tb SET \n" + "	old_due = old_due + ? WHERE id = ?;");

					cust_due.setFloat(1, dto.getFinal_amount() - (dto.getCash_amount() + dto.getOnline_amount()));
					cust_due.setInt(2, dto.getCust_id_fk());

					System.out.println(cust_due);

					int i2 = cust_due.executeUpdate();

					if (i2 != 0) {

						// insert customer acc table info
						PreparedStatement cust_acc = db.connection.prepareStatement(
								"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
										+ "c_y_session,debit_amount,credit_amount, \r\n"
										+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
										+ "online_date,payment_mode,pay_date \r\n"
										+ ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
								Statement.RETURN_GENERATED_KEYS);

						cust_acc.setInt(1, dto.getCust_id_fk());
						cust_acc.setInt(2, dto.getUser_id_fk());
						cust_acc.setInt(3, dto.getId());
						cust_acc.setString(4, dto.getSession_year());
						cust_acc.setFloat(5, dto.getFinal_amount());// debit amount
						cust_acc.setFloat(6, dto.getCash_amount() + dto.getOnline_amount()); // credit
						cust_acc.setString(7, "Menu Order Bill");
						cust_acc.setFloat(8, dto.getCash_amount());
						cust_acc.setFloat(9, dto.getOnline_amount());
						cust_acc.setString(10, dto.getOnline_way());
						cust_acc.setString(11, dto.getOnline_remark());
						cust_acc.setString(12, dto.getOnline_date());
						cust_acc.setString(13, dto.getPayment_mode());
						cust_acc.setString(14, dto.getBill_date());

						System.out.println(cust_acc);

						cust_acc.executeUpdate();

						ResultSet rs_cust_acc = cust_acc.getGeneratedKeys();
						rs_cust_acc.next();
						dto.setCust_acc_id_fk(rs_cust_acc.getInt(1));

					}

				}
			}

			// ****** when Payment mode is both ********
			if (dto.getPayment_mode().equalsIgnoreCase("both")) {
				new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

			}
			// ****** when Payment mode is online ********
			else if (dto.getPayment_mode().equalsIgnoreCase("online")) {

				new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

			}
			// ****** when Payment mode is cash ********
			else {
				new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
			}

			dto.setCash_payment_id_fk(new_cash_id);
			dto.setOnline_payment_id_fk(new_online_id);

			String updateQuery = "UPDATE order_bill_tb SET cust_id_fk = ?, cust_name = ?, cust_mob_no = ?,"
					+ " cust_address = ?, without_gst_amount = ?, gst_amount = ?, with_gst_amount = ?, "
					+ "old_due_amount = ?, final_amount = ?, paid_amount = ?,return_amount = ?, discount_amount = ?, "
					+ "cust_type = ?, payment_mode = ?,  cash_amount = ?, online_amount = ?, online_date = ?, "
					+ "online_way = ?, online_remark = ?, remark = ?,cash_payment_id_fk = ?, online_payment_id_fk = ?,"
					+ "cust_acc_id_fk = ?, gst_status = ?,bank_id_fk =?,cust_company_name=?,cust_gst_no=?,cust_dob=?,cust_doa=?, STATUS = 'complete' WHERE id = ?";
			PreparedStatement updateps = db.connection.prepareStatement(updateQuery);
			updateps.setInt(1, dto.getCust_id_fk());
			updateps.setString(2, dto.getCust_name());
			updateps.setString(3, dto.getCust_mob_no());
			updateps.setString(4, dto.getCust_address());
			updateps.setFloat(5, dto.getWithout_gst_amount());
			updateps.setFloat(6, dto.getGst_amount());
			updateps.setFloat(7, dto.getWith_gst_amount());
			updateps.setFloat(8, dto.getOld_due_amount());
			updateps.setFloat(9, dto.getFinal_amount());
			updateps.setFloat(10, dto.getPaid_amount());
			updateps.setFloat(11, dto.getReturn_amount());
			updateps.setFloat(12, dto.getDiscount_amount());
			updateps.setString(13, dto.getCust_type());
			updateps.setString(14, dto.getPayment_mode());
			updateps.setFloat(15, dto.getCash_amount());
			updateps.setFloat(16, dto.getOnline_amount());
			updateps.setString(17, dto.getOnline_date());
			updateps.setString(18, dto.getOnline_way());
			updateps.setString(19, dto.getOnline_remark());
			updateps.setString(20, dto.getRemark());
			updateps.setInt(21, dto.getCash_payment_id_fk());
			updateps.setInt(22, dto.getOnline_payment_id_fk());
			updateps.setInt(23, dto.getCust_acc_id_fk());
			updateps.setString(24, dto.getGst_status());
			updateps.setInt(25, dto.getBank_id_fk());
			updateps.setString(26, dto.getCompany_name());
			updateps.setString(27, dto.getGst_no());
			updateps.setString(28, dto.getDob());
			updateps.setString(29, dto.getDoa());
			updateps.setInt(30, dto.getId());
			System.out.println("Update Query: " + updateps.toString());

			int updatedRows = updateps.executeUpdate();

			if (updatedRows > 0) {

				return dto.getId();

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

		return 0;
	}

	// Get Order Billing
	public int updateOrderBilling(OrderBillDto dto, PaymentDto pay_dto, HttpServletRequest request,
			ServletConfig config) throws IOException {
		DataDb db = new DataDb(request);

		try {

			float old_final_amount = 0, old_paid_amount = 0;
			String old_payment_mode = "";

			int old_customer_id_fk = 0, new_cash_id = 0, new_online_id = 0,old_customer_account_id_fk=0;

			PreparedStatement select = db.connection
					.prepareStatement("SELECT 	cust_id_fk,cust_acc_id_fk,payment_mode,final_amount,\r\n"
							+ "paid_amount,cash_payment_id_fk,online_payment_id_fk\r\n"
							+ "FROM order_bill_tb WHERE id = ?;");

			select.setInt(1, dto.getId());
			System.out.println(select);
			
			ResultSet rs1 = select.executeQuery();

			while (rs1.next()) {

				old_customer_id_fk = rs1.getInt(1);
				old_customer_account_id_fk=rs1.getInt(2);
				old_payment_mode = rs1.getString(3);
				old_final_amount = rs1.getFloat(4);
				old_paid_amount = rs1.getFloat(5);
				dto.setCash_payment_id_fk(rs1.getInt(6));
				dto.setOnline_payment_id_fk(rs1.getInt(7));

			}

			if (old_customer_id_fk != 0) {
				System.out.println("id yes");
				// update customer due info
				PreparedStatement del_acc_del = db.connection
						.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?;");

				del_acc_del.setInt(1, old_customer_account_id_fk);

				System.out.println(del_acc_del);
				del_acc_del.executeUpdate();
				// update customer due info
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET \n" + "	old_due = old_due - ? WHERE id = ?;");

				cust_due.setFloat(1, old_final_amount - old_paid_amount);
				cust_due.setInt(2, old_customer_id_fk);

				System.out.println(cust_due);
				cust_due.executeUpdate();
			}

			// bill for table ....
			if (dto.getCust_name() == null || dto.getCust_name().equals("")) {
				System.out.println("name null");
				dto.setCust_id_fk(0);
				dto.setCust_acc_id_fk(0);
				dto.setCust_mob_no("");
				dto.setCust_address("");
				dto.setCompany_name("");
				dto.setGst_no("");
				dto.setDob("");
				dto.setDoa("");
				
			}

			// bill for customer ....
			

				// insert customer if customer is new and regular....
				if (dto.getCust_id_fk() == 0 && dto.getRegular().equalsIgnoreCase("Yes")) {
					System.out.println("id null");
					dto.setCust_id_fk(insertCustomerFromOrderBill(dto, request, config));
					
				}

				// manage customer account....
				if (dto.getCust_id_fk() > 0) {
					System.out.println("correct");
					// update customer due info
					PreparedStatement cust_due = db.connection.prepareStatement(
							"UPDATE customer_info_tb SET \n" + "	old_due = old_due + ? WHERE id = ?;");

					cust_due.setFloat(1, dto.getFinal_amount() - (dto.getCash_amount() + dto.getOnline_amount()));
					cust_due.setInt(2, dto.getCust_id_fk());

					System.out.println(cust_due);

					int i2 = cust_due.executeUpdate();

					if (i2 != 0) {

						// insert customer acc table info
						PreparedStatement cust_acc = db.connection.prepareStatement(
								"INSERT INTO customer_account_tb \r\n" + "(customer_id_fk,user_id_fk,bill_id_fk, \r\n"
										+ "c_y_session,debit_amount,credit_amount, \r\n"
										+ "TYPE,cash_amount,online_amount,online_way,online_remark, \r\n"
										+ "online_date,payment_mode,pay_date \r\n"
										+ ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
								Statement.RETURN_GENERATED_KEYS);

						cust_acc.setInt(1, dto.getCust_id_fk());
						cust_acc.setInt(2, dto.getUser_id_fk());
						cust_acc.setInt(3, dto.getId());
						cust_acc.setString(4, dto.getSession_year());
						cust_acc.setFloat(5, dto.getFinal_amount());// debit amount
						cust_acc.setFloat(6, dto.getCash_amount() + dto.getOnline_amount()); // credit
						cust_acc.setString(7, "Menu Order Bill");
						cust_acc.setFloat(8, dto.getCash_amount());
						cust_acc.setFloat(9, dto.getOnline_amount());
						cust_acc.setString(10, dto.getOnline_way());
						cust_acc.setString(11, dto.getOnline_remark());
						cust_acc.setString(12, dto.getOnline_date());
						cust_acc.setString(13, dto.getPayment_mode());
						cust_acc.setString(14, dto.getBill_date());

						System.out.println(cust_acc);

						cust_acc.executeUpdate();

						ResultSet rs_cust_acc = cust_acc.getGeneratedKeys();
						rs_cust_acc.next();
						dto.setCust_acc_id_fk(rs_cust_acc.getInt(1));

					}

				}
			

			// ****** when old Payment mode & New mode is same ********
			if (old_payment_mode.equalsIgnoreCase(dto.getPayment_mode())) {

				pay_ser.updateCashPayment(pay_dto, dto.getCash_payment_id_fk(), request, config);
				pay_ser.updateOnlinePayment(pay_dto, dto.getOnline_payment_id_fk(), request, config);

			}

			// ****** when old Payment mode & New mode is Different ********

			else {

				pay_ser.deleteCashPayment(dto.getCash_payment_id_fk(), request, config);
				pay_ser.deleteOnlinePayment(dto.getOnline_payment_id_fk(), request, config);

				// ****** when new Payment mode is both ********
				if (dto.getPayment_mode().equalsIgnoreCase("both")) {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is online ********
				else if (dto.getPayment_mode().equalsIgnoreCase("online")) {

					new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

				}
				// ****** when new Payment mode is cash ********
				else {
					new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
				}

				dto.setCash_payment_id_fk(new_cash_id);
				dto.setOnline_payment_id_fk(new_online_id);

			}

			String updateQuery = "UPDATE order_bill_tb SET cust_id_fk = ?, cust_name = ?, "
					+ "cust_mob_no = ?, cust_address = ?, without_gst_amount = ?,  gst_amount = ?,"
					+ " with_gst_amount = ?, old_due_amount = ?, final_amount = ?, paid_amount = ?,"
					+ "return_amount = ?, discount_amount = ?, cust_type = ?, payment_mode = ?,  "
					+ "cash_amount = ?, online_amount = ?, online_date = ?, online_way = ?, "
					+ "online_remark = ?, remark = ?,cash_payment_id_fk = ?, online_payment_id_fk = ?,"
					+ "cust_acc_id_fk = ?, gst_status = ?,bank_id_fk =?,cust_company_name=?,cust_gst_no=?,cust_dob=?,cust_doa=?, STATUS = 'complete' WHERE id = ?";
			PreparedStatement updateps = db.connection.prepareStatement(updateQuery);
			updateps.setInt(1, dto.getCust_id_fk());
			updateps.setString(2, dto.getCust_name());
			updateps.setString(3, dto.getCust_mob_no());
			updateps.setString(4, dto.getCust_address());
			updateps.setFloat(5, dto.getWithout_gst_amount());
			updateps.setFloat(6, dto.getGst_amount());
			updateps.setFloat(7, dto.getWith_gst_amount());
			updateps.setFloat(8, dto.getOld_due_amount());
			updateps.setFloat(9, dto.getFinal_amount());
			updateps.setFloat(10, dto.getPaid_amount());
			updateps.setFloat(11, dto.getReturn_amount());
			updateps.setFloat(12, dto.getDiscount_amount());
			updateps.setString(13, dto.getCust_type());
			updateps.setString(14, dto.getPayment_mode());
			updateps.setFloat(15, dto.getCash_amount());
			updateps.setFloat(16, dto.getOnline_amount());
			updateps.setString(17, dto.getOnline_date());
			updateps.setString(18, dto.getOnline_way());
			updateps.setString(19, dto.getOnline_remark());
			updateps.setString(20, dto.getRemark());
			updateps.setInt(21, dto.getCash_payment_id_fk());
			updateps.setInt(22, dto.getOnline_payment_id_fk());
			updateps.setInt(23, dto.getCust_acc_id_fk());
			updateps.setString(24, dto.getGst_status());
			updateps.setInt(25, dto.getBank_id_fk());
			updateps.setString(26, dto.getCompany_name());
			updateps.setString(27, dto.getGst_no());
			updateps.setString(28, dto.getDob());
			updateps.setString(29, dto.getDoa());
			updateps.setInt(30, dto.getId());
			System.out.println("Update Query: " + updateps.toString());

			int updatedRows = updateps.executeUpdate();

			if (updatedRows > 0) {

				return dto.getId();

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

		return 0;
	}

	// Insert Method for Customer From order Bill
	public int insertCustomerFromOrderBill(OrderBillDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		String status = "Block";

		if (dto.getRegular().equalsIgnoreCase("Yes")) {
			status = "Active";
		}

		try {

			// Insert Query of Customer

			String sql = "INSERT INTO customer_info_tb (Name, address, mobile_no,  user_id_fk, status,company_name,gst_no,dob,doa) VALUES(?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = db.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getCust_name());
			ps.setString(2, dto.getCust_address());
			ps.setString(3, dto.getCust_mob_no());
			ps.setInt(4, dto.getUser_id_fk());
			ps.setString(5, status);
			ps.setString(6, dto.getCompany_name());
			ps.setString(7, dto.getGst_no());
			ps.setString(8, dto.getDob());
			ps.setString(9, dto.getDoa());

			System.out.println(ps);
			int i = ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int customer_id_fk = rs.getInt(1);

			if (i != 0) {

				// insert customer acc table info
				PreparedStatement cust_acc = db.connection.prepareStatement("INSERT INTO customer_account_tb \r\n"
						+ "(customer_id_fk,user_id_fk,c_y_session,debit_amount,credit_amount, \r\n"
						+ "TYPE,pay_date)VALUES(?,?,?,?,?,?,?);");

				cust_acc.setInt(1, customer_id_fk);
				cust_acc.setInt(2, dto.getUser_id_fk());
				cust_acc.setString(3, dto.getSession_year());
				cust_acc.setFloat(4, 0);
				cust_acc.setFloat(5, 0);
				cust_acc.setString(6, "Opening Due");
				cust_acc.setString(7, dto.getBill_date());
				System.out.println(cust_acc);

				int i2 = cust_acc.executeUpdate();

				if (i2 != 0) {

					return customer_id_fk;

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}

	// Get Customer Info By Name
	public OrderBillDto getCustomerInfoByName(String Name, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		OrderBillDto dto = new OrderBillDto();

		try {

			String sql = "SELECT id,Name, address, mobile_no, old_due,company_name,gst_no FROM customer_info_tb WHERE  Name=? AND status='active';";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setString(1, Name);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setCust_name(resultSet.getString(2));
				dto.setCust_address(resultSet.getString(3));
				dto.setCust_mob_no(resultSet.getString(4));
				dto.setOld_due_amount(resultSet.getFloat(5));
				dto.setCompany_name(resultSet.getString(6));
				dto.setGst_no(resultSet.getString(7));

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
	// Get Customer Info By Name
	public OrderBillDto getCustomerInfoByMobileNo(String Mobile_no, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		OrderBillDto dto = new OrderBillDto();

		try {

			String sql = "SELECT id,NAME, address, mobile_no, old_due,company_name,gst_no,dob,doa FROM customer_info_tb WHERE  mobile_no=? AND STATUS='active' GROUP BY mobile_no;";
			// Select query for showing data on Edit page
			preparedStatement = db.connection.prepareStatement(sql);

			preparedStatement.setString(1, Mobile_no);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dto.setId(resultSet.getInt(1));
				dto.setCust_name(resultSet.getString(2));
				dto.setCust_address(resultSet.getString(3));
				dto.setCust_mob_no(resultSet.getString(4));
				dto.setOld_due_amount(resultSet.getFloat(5));
				dto.setCompany_name(resultSet.getString(6));
				dto.setGst_no(resultSet.getString(7));
				dto.setDob(resultSet.getString(8));
				dto.setDoa(resultSet.getString(9));

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
