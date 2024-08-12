package com.service;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.CustomerDto;

public class DeleteService {
	PaymentService pay_ser = new PaymentService();

	// ***** start Master delete *********

	public boolean deleteCity(int id, HttpServletRequest request, ServletConfig config) throws IOException {

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

	// Method For Delete Room bill data
	public boolean deleteRoom_bill(int room_bill_id, int room_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		PreparedStatement ps_select = null;

		int customer_id_fk = 0, customer_account_id_fk = 0,cash_payment_id = 0;
		int online_payment_id = 0;
		float final_amount = 0, paid_amount = 0;

		try {
			// Delete for Income

			ps_select = db.connection.prepareStatement(
					"SELECT bill_cash_payment_id_fk,  bill_online_payment_id_fk,cust_id_fk,final_amt,bill_paid_amt,bill_customer_acc_id_fk FROM room_booked_tb WHERE id= ?;");

			ps_select.setInt(1, room_bill_id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				cash_payment_id = (resultSet.getInt(1));
				online_payment_id = (resultSet.getInt(2));
				customer_id_fk = (resultSet.getInt(3));
				final_amount = (resultSet.getFloat(4));
				paid_amount = (resultSet.getFloat(5));
				customer_account_id_fk = (resultSet.getInt(6));

			}

			ps = db.connection.prepareStatement("DELETE FROM room_booked_tb WHERE id = ?;");
			ps.setInt(1, room_bill_id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {
				// clear customer old due info
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET old_due = old_due - ? WHERE id = ?;");

				cust_due.setFloat(1, final_amount - paid_amount);
				cust_due.setInt(2, customer_id_fk);
				cust_due.executeUpdate();
				System.out.println(cust_due);

				// Delete for customer account
				PreparedStatement cust_acc = db.connection
						.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?;");
				cust_acc.setInt(1, customer_account_id_fk);
				cust_acc.executeUpdate();
				System.out.println(cust_acc);
				
				PreparedStatement ps_order = db.connection
						.prepareStatement("DELETE FROM room_order_item_tb WHERE bill_id_fk = ?;");
				ps_order.setInt(1, room_bill_id);

				System.out.println(ps_order);
				ps_order.executeUpdate();

				PreparedStatement ps_room = db.connection
						.prepareStatement("UPDATE room_tb SET status = 'Available' WHERE  id = ?;");
				ps_room.setInt(1, room_id);

				System.out.println(ps_room);
				ps_room.executeUpdate();

				// Delete for cash payment
				PreparedStatement ps2 = db.connection.prepareStatement("DELETE FROM cash_payment_tb WHERE id = ?;");
				ps2.setInt(1, cash_payment_id);
				ps2.executeUpdate();
				System.out.println(ps2);

				// Delete for online payment
				PreparedStatement ps3 = db.connection.prepareStatement("DELETE FROM online_payment_tb WHERE id = ?;");
				ps3.setInt(1, online_payment_id);
				ps3.executeUpdate();
				System.out.println(ps3);

				return true;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	// Method For Delete Order bill data
	public boolean deleteOrder_bill(int order_bill_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		PreparedStatement ps_select = null;

		int customer_id_fk = 0, customer_account_id_fk = 0,cash_payment_id = 0;
		int online_payment_id = 0;
		float final_amount = 0, paid_amount = 0;

		try {
			// Delete for Order

			ps_select = db.connection.prepareStatement(
					"SELECT cash_payment_id_fk,  online_payment_id_fk,cust_id_fk,cust_acc_id_fk,final_amount,paid_amount  FROM order_bill_tb WHERE id= ?;");

			ps_select.setInt(1, order_bill_id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				cash_payment_id = (resultSet.getInt(1));
				online_payment_id = (resultSet.getInt(2));
				customer_id_fk = (resultSet.getInt(3));
				customer_account_id_fk = (resultSet.getInt(4));
				final_amount = (resultSet.getFloat(5));
				paid_amount = (resultSet.getFloat(6));
				

			}

			ps = db.connection.prepareStatement("DELETE FROM order_bill_tb WHERE id = ?;");
			ps.setInt(1, order_bill_id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {
				
				// clear customer old due info
				PreparedStatement cust_due = db.connection
						.prepareStatement("UPDATE customer_info_tb SET old_due = old_due - ? WHERE id = ?;");

				cust_due.setFloat(1, final_amount - paid_amount);
				cust_due.setInt(2, customer_id_fk);
				cust_due.executeUpdate();
				System.out.println(cust_due);

				// Delete for customer account
				PreparedStatement cust_acc = db.connection
						.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?;");
				cust_acc.setInt(1, customer_account_id_fk);
				cust_acc.executeUpdate();
				System.out.println(cust_acc);

				PreparedStatement ps_order = db.connection
						.prepareStatement("DELETE FROM order_item_tb  WHERE order_id_fk = ?;");
				ps_order.setInt(1, order_bill_id);

				System.out.println(ps_order);
				ps_order.executeUpdate();

				// Delete for cash payment
				PreparedStatement ps2 = db.connection.prepareStatement("DELETE FROM cash_payment_tb WHERE id = ?;");
				ps2.setInt(1, cash_payment_id);
				ps2.executeUpdate();
				System.out.println(ps2);

				// Delete for online payment
				PreparedStatement ps3 = db.connection.prepareStatement("DELETE FROM online_payment_tb WHERE id = ?;");
				ps3.setInt(1, online_payment_id);
				ps3.executeUpdate();
				System.out.println(ps3);

				return true;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	// Method For Delete KOT order bill data
	public boolean deleteKot_order_bill(int kot_bill_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;

		try {
			// Delete for KOT order bill data

			ps = db.connection.prepareStatement("DELETE FROM order_kot_bill_tb WHERE id = ?;");
			ps.setInt(1, kot_bill_id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {

				PreparedStatement ps_order = db.connection
						.prepareStatement("DELETE FROM  order_kot_item_tb WHERE bill_id_fk = ?;");
				ps_order.setInt(1, kot_bill_id);

				System.out.println(ps_order);
				ps_order.executeUpdate();

				return true;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	// Method For Delete Bank
	public boolean deleteAjax(int id, String table_name, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {
			// Delete for Bank

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM " + table_name + " WHERE id = ?;");
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

	// ***** start delete Ingredients purchase bill
	public boolean deleteIngredientsSale(int bill_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps_select = db.connection.prepareStatement(
					"SELECT quantity, item_id_fk FROM ingredients_sale_bill_item_tb WHERE bill_id_fk = ?;");
			ps_select.setInt(1, bill_id);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				float qunatity = (resultSet.getFloat(1));
				int item_id = (resultSet.getInt(2));

				PreparedStatement ps_item = db.connection
						.prepareStatement("UPDATE ingredients_item_tb SET qty = qty + ? WHERE id = ? ;");
				ps_item.setFloat(1, qunatity);
				ps_item.setInt(2, item_id);
				ps_item.executeUpdate();
			}

			PreparedStatement ps = db.connection
					.prepareStatement("DELETE FROM ingredients_sale_bill_item_tb WHERE bill_id_fk= ? ;");

			ps.setInt(1, bill_id);
			System.out.println(ps);
			ps.executeUpdate();

			PreparedStatement ps1 = db.connection
					.prepareStatement("DELETE FROM ingredients_sale_bill_tb WHERE id = ? ;");
			ps1.setInt(1, bill_id);
			System.out.println(ps1);
			int i = ps1.executeUpdate();

			if (i != 0) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	// ***** End delete Ingredients purchase bill

	// ***** start delete Ingredients purchase bill
	public boolean deleteIngredientsPurchase(int bill_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps_select = db.connection.prepareStatement(
					"SELECT quantity, item_id_fk FROM ingredients_purchase_bill_item_tb WHERE bill_id_fk = ?;");
			ps_select.setInt(1, bill_id);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				float qunatity = (resultSet.getFloat(1));
				int item_id = (resultSet.getInt(2));

				PreparedStatement ps_item = db.connection
						.prepareStatement("UPDATE ingredients_item_tb SET qty = qty - ? WHERE id = ? ;");
				ps_item.setFloat(1, qunatity);
				ps_item.setInt(2, item_id);
				ps_item.executeUpdate();
			}

			PreparedStatement ps1 = db.connection
					.prepareStatement("DELETE FROM ingredients_purchase_bill_tb WHERE id = ? ;");
			ps1.setInt(1, bill_id);
			System.out.println(ps1);

			int i = ps1.executeUpdate();

			if (i != 0) {

				PreparedStatement ps = db.connection
						.prepareStatement("DELETE FROM ingredients_purchase_bill_item_tb WHERE bill_id_fk= ? ;");

				ps.setInt(1, bill_id);
				System.out.println(ps);
				ps.executeUpdate();

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	// ***** start delete Liquor purchase bill
	public boolean deleteLiquorPurchase(int bill_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		int liqour_cat_id = 0;
		float liqour_ind_qty = 0;
		int dealer_id_fk = 0, dealer_account_id_fk = 0;
		float total_amount = 0;
		try {
			PreparedStatement ps_select = db.connection.prepareStatement(
					"SELECT 	dealer_id_fk,dealer_account_id_fk,total_amount FROM liquor_purchase_bill_tb WHERE id=?;");

			ps_select.setInt(1, bill_id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				dealer_id_fk = (resultSet.getInt(1));
				dealer_account_id_fk = (resultSet.getInt(2));
				total_amount = (resultSet.getFloat(3));
				

			}

			PreparedStatement pus_select = db.connection.prepareStatement(
					"SELECT quantity, item_id_fk FROM liquor_purchase_bill_item_tb WHERE bill_id_fk = ?;");
			pus_select.setInt(1, bill_id);
			ResultSet resultSet_item = pus_select.executeQuery();

			while (resultSet_item.next()) {

				float qunatity = (resultSet_item.getFloat(1));
				int item_id = (resultSet_item.getInt(2));

				String select_menu_sql = "SELECT liqour_cat_id_fk, liqour_ind_qty FROM menu_item_detail_tb WHERE id = ?;";

				PreparedStatement select_menu_ps = db.connection.prepareStatement(select_menu_sql);
				select_menu_ps.setInt(1, item_id);
				ResultSet rs_menu = select_menu_ps.executeQuery();
				while (rs_menu.next()) {

					liqour_cat_id = rs_menu.getInt(1);
					liqour_ind_qty = rs_menu.getFloat(2);

					PreparedStatement ps_item = db.connection
							.prepareStatement("UPDATE liqour_cat_tb SET quantity = quantity - ? WHERE id = ? ;");
					ps_item.setFloat(1, liqour_ind_qty * qunatity);
					ps_item.setInt(2, liqour_cat_id);
					ps_item.executeUpdate();
				}
			}

			PreparedStatement ps1 = db.connection
					.prepareStatement("DELETE FROM  liquor_purchase_bill_tb WHERE id = ? ;");
			ps1.setInt(1, bill_id);
			System.out.println(ps1);

			int i = ps1.executeUpdate();

			if (i != 0) {
				// clear customer old due info
				PreparedStatement deal_due = db.connection
						.prepareStatement("UPDATE dealer_info_tb SET old_due = old_due - ? WHERE id = ?;");

				deal_due.setFloat(1, total_amount);
				deal_due.setInt(2, dealer_id_fk);
				deal_due.executeUpdate();
				System.out.println(deal_due);

				// Delete for cash payment
				PreparedStatement deal_acc = db.connection
						.prepareStatement("DELETE FROM dealer_account_tb WHERE id = ?;");
				deal_acc.setInt(1, dealer_account_id_fk);
				deal_acc.executeUpdate();
				System.out.println(deal_acc);

				PreparedStatement ps = db.connection
						.prepareStatement("DELETE FROM liquor_purchase_bill_item_tb WHERE bill_id_fk= ? ;");

				ps.setInt(1, bill_id);
				System.out.println(ps);
				ps.executeUpdate();

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	// ***** End delete Ingredients purchase bill

	public boolean deleteCustomer(int id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		CustomerDto dto = new CustomerDto();

		try {
			PreparedStatement ps_select = db.connection
					.prepareStatement("SELECT dt.payment_mode FROM customer_due_tb dt WHERE dt.customer_id_fk = ?;");

			ps_select.setInt(1, id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {
				dto.setPayment_mode(resultSet.getString(1));
			}

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM customer_info_tb WHERE id = ?");
			ps.setInt(1, id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {

				PreparedStatement ps1 = db.connection
						.prepareStatement("DELETE FROM customer_account_tb WHERE customer_id_fk = ?");
				ps1.setInt(1, id);
				System.out.println(ps1);
				ps1.executeUpdate();

				PreparedStatement ps_due = db.connection
						.prepareStatement("DELETE FROM customer_due_tb WHERE customer_id_fk = ?");
				ps_due.setInt(1, id);

				System.out.println(ps_due);
				ps_due.executeUpdate();
				// ****** when Payment mode is both ********
				if (dto.getPayment_mode().equalsIgnoreCase("both")) {
					// delete query in Cash Payment table
					PreparedStatement ps_cash = db.connection
							.prepareStatement("DELETE FROM cash_payment_tb WHERE bill_id_fk = ? ;");
					ps_cash.setInt(1, id);
					System.out.println(ps_cash);
					ps_cash.executeUpdate();

					// delete query in online Payment table
					PreparedStatement ps_on = db.connection
							.prepareStatement("DELETE FROM online_payment_tb WHERE bill_id_fk = ? ;");

					ps_on.setInt(1, id);
					System.out.println(ps_on);
					ps_on.executeUpdate();

				}
				// ****** when Payment mode is online ********
				else if (dto.getPayment_mode().equalsIgnoreCase("online")) {

					// delete query in online Payment table
					PreparedStatement ps_on = db.connection
							.prepareStatement("DELETE FROM online_payment_tb WHERE bill_id_fk = ? ;");

					ps_on.setInt(1, id);
					System.out.println(ps_on);
					ps_on.executeUpdate();

				}
				// ****** when Payment mode is cash ********
				else {
					// delete query in Cash Payment table
					PreparedStatement ps_cash = db.connection
							.prepareStatement("DELETE FROM cash_payment_tb WHERE bill_id_fk = ? ;");
					ps_cash.setInt(1, id);
					System.out.println(ps_cash);
					ps_cash.executeUpdate();
				}
				// ****** when Payment mode is both ********
				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

// ***** start customer due delete

	public boolean deleteCustomerDue(int id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		boolean cash_del_status;
		boolean online_del_status;

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		CustomerDto dto = new CustomerDto();

		try {

			ps = db.connection.prepareStatement(
					"SELECT 	customer_account_id_fk, online_payment_id_fk, cash_payment_id_fk, pay_amount, payment_mode, online_amount, cash_amount , customer_id_fk \r\n"
							+ "FROM customer_due_tb\r\n" + "WHERE id= ?;");

			ps.setInt(1, id);
			System.out.println(ps);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {

				dto.setCustomer_account_id_fk(resultSet.getInt(1));
				dto.setOnline_payment_id_fk(resultSet.getInt(2));
				dto.setCash_payment_id_fk(resultSet.getInt(3));
				dto.setPay_amount(resultSet.getFloat(4));
				dto.setPayment_mode(resultSet.getString(5));
				dto.setOnline_amount(resultSet.getFloat(6));
				dto.setCash_amount(resultSet.getFloat(7));
				dto.setCustomer_id_fk(resultSet.getInt(8));
			}

			// Delete Query for stool routine
			ps1 = db.connection.prepareStatement("DELETE FROM customer_due_tb WHERE id = ?;");

			ps1.setInt(1, id);
			System.out.println(ps1);

			int i = ps1.executeUpdate();

			// ****** when Payment mode is both ********
			if (dto.getPayment_mode().equalsIgnoreCase("both")) {
				cash_del_status = pay_ser.deleteCashPayment(dto.getCash_payment_id_fk(), request, config);
				online_del_status = pay_ser.deleteOnlinePayment(dto.getOnline_payment_id_fk(), request, config);

			}
			// ****** when Payment mode is online ********
			else if (dto.getPayment_mode().equalsIgnoreCase("online")) {

				online_del_status = pay_ser.deleteOnlinePayment(dto.getOnline_payment_id_fk(), request, config);

			}
			// ****** when Payment mode is cash ********
			else {
				cash_del_status = pay_ser.deleteCashPayment(dto.getCash_payment_id_fk(), request, config);
			}

			if (i != 0) {

				ps2 = db.connection.prepareStatement("UPDATE customer_info_tb SET old_due = old_due+? WHERE id = ?;");

				ps2.setFloat(1, dto.getPay_amount());
				ps2.setInt(2, dto.getCustomer_id_fk());

				System.out.println(ps2);

				int i2 = ps2.executeUpdate();

				if (i2 != 0) {

					PreparedStatement ps3 = db.connection
							.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?");
					ps3.setInt(1, dto.getCustomer_account_id_fk());

					System.out.println(ps3);
					ps3.executeUpdate();
				}

				return true;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteRoomBooking(int id, int cust_id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps_select = db.connection.prepareStatement(
					"SELECT ad_cash_payment_id_fk, ad_online_payment_id_fk, ad_customer_acc_id_fk, room_id_fk FROM room_booked_tb WHERE id = ?;");

			ps_select.setInt(1, id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {
				resultSet.getInt(1);
				resultSet.getInt(2);
				resultSet.getInt(3);
				resultSet.getInt(4);

				if (cust_id > 0) {

					PreparedStatement ps1 = db.connection
							.prepareStatement("DELETE FROM customer_account_tb WHERE id = ?");
					ps1.setInt(1, resultSet.getInt(3));
					System.out.println(ps1);
					ps1.executeUpdate();
				}

				PreparedStatement ps_cash = db.connection
						.prepareStatement("DELETE FROM cash_payment_tb WHERE id = ? ;");
				ps_cash.setInt(1, resultSet.getInt(1));
				System.out.println(ps_cash);
				ps_cash.executeUpdate();

				// delete query in online Payment table
				PreparedStatement ps_on = db.connection
						.prepareStatement("DELETE FROM online_payment_tb WHERE id = ? ;");

				ps_on.setInt(1, resultSet.getInt(2));
				System.out.println(ps_on);
				ps_on.executeUpdate();
				
				PreparedStatement ps_up = db.connection.prepareStatement("UPDATE room_tb SET status = 'Available'   WHERE id = ?");
				ps_up.setInt(1, resultSet.getInt(4));

				System.out.println(ps_up);
				ps_up.executeUpdate();
				
			}
			
			
			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM room_booked_tb WHERE id = ?");
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