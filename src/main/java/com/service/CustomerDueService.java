package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;

import com.dto.CustomerDto;

import com.dto.PaymentDto;

import com.mysql.jdbc.Statement;

public class CustomerDueService {
	
	//*****Start pay Customer due ****
	
	PaymentService pay_ser = new PaymentService();

	public int insertCustomerDue(PaymentDto pay_dto, CustomerDto dto, HttpServletRequest request, ServletConfig config)
						throws IOException {

					DataDb db = new DataDb(request);
					
					int bill_id_fk=0,new_cash_id=0,new_online_id=0,customer_acc_id = 0;	

					try {
						
						//***** Insert new record in dealer due table.
						
						PreparedStatement ps_due = db.connection.prepareStatement("INSERT INTO customer_due_tb (customer_id_fk,c_y_session,pay_amount,pay_date,remark,online_way,online_remark,online_date,payment_mode,online_amount, \r\n"
								+ "cash_amount,bank_id_fk)\r\n"
								+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

						ps_due.setInt(1, dto.getId());
						ps_due.setString(2, dto.getC_y_session());
						ps_due.setFloat(3, dto.getCash_amount()+dto.getOnline_amount());
						ps_due.setString(4, dto.getPay_date());
						ps_due.setString(5, dto.getRemark());
						
						ps_due.setString(6, dto.getOnline_way());
						ps_due.setString(7, dto.getOnline_remark());
						ps_due.setString(8, dto.getOnline_date());
						ps_due.setString(9, dto.getPayment_mode());
						ps_due.setFloat(10, dto.getOnline_amount());
						ps_due.setFloat(11, dto.getCash_amount());
						
						ps_due.setInt(12, dto.getBank_id_fk());

						System.out.println(ps_due);
						int i= ps_due.executeUpdate();
						
						ResultSet rs_due = ps_due.getGeneratedKeys();
						rs_due.next();
						bill_id_fk= rs_due.getInt(1);
						
						pay_dto.setBill_id_fk(bill_id_fk);
						
						//**** this statement is used to reduce due amount from dealer table
						if (i != 0) {
							
							String sql = "UPDATE customer_info_tb SET old_due=old_due-?,upcoming_date=?,upcoming_remark=?  WHERE id=?;";
							// Update Query for update data
							PreparedStatement ps_reduce = db.connection.prepareStatement(sql);

							ps_reduce.setFloat(1, dto.getCash_amount()+dto.getOnline_amount());
							ps_reduce.setString(2, dto.getUpcoming_date());
							ps_reduce.setString(3, dto.getUpcoming_remark());
							ps_reduce.setInt(4, dto.getCustomer_id_fk());

							System.out.println(ps_reduce);

							ps_reduce.executeUpdate();
							
							
							//**** insert payment record (Customer Due Amount) in dealer account table.
							PreparedStatement ps_acc = db.connection.prepareStatement("INSERT INTO customer_account_tb (customer_id_fk,user_id_fk,c_y_session,credit_amount,debit_amount,TYPE,cash_amount,\r\n"
									+ "online_amount,online_way,online_remark,online_date,payment_mode,pay_date,bill_id_fk,remark)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

							ps_acc.setInt(1, dto.getCustomer_id_fk());
							ps_acc.setInt(2, dto.getUser_id_fk());
							ps_acc.setString(3, dto.getC_y_session());
							ps_acc.setFloat(4, dto.getCash_amount()+dto.getOnline_amount());
							ps_acc.setFloat(5, 0);
							ps_acc.setString(6, "Customer Due");
							ps_acc.setFloat(7, dto.getCash_amount());
							ps_acc.setFloat(8, dto.getOnline_amount());
							
							ps_acc.setString(9, dto.getOnline_way());
							ps_acc.setString(10, dto.getOnline_remark());
							ps_acc.setString(11, dto.getOnline_date());
							ps_acc.setString(12, dto.getPayment_mode());
							
							ps_acc.setString(13, dto.getPay_date());
							ps_acc.setInt(14, bill_id_fk);
							ps_acc.setString(15, dto.getRemark());
							

							System.out.println(ps_acc);
							ps_acc.executeUpdate();
							
							ResultSet rs_acc = ps_acc.getGeneratedKeys();
							rs_acc.next();
							customer_acc_id = rs_acc.getInt(1);
							dto.setCustomer_account_id_fk(customer_acc_id);
							
							// *************** insert cash and payment tb*****************

							// ****** when Payment mode is both ********
							if (dto.getPayment_mode().equalsIgnoreCase("Both")) {
								new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
								new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);
							}
							// ****** when Payment mode is online ********
							else if (dto.getPayment_mode().equalsIgnoreCase("Online")) {

								new_online_id = pay_ser.insertOnlinePayment(pay_dto, request, config);

							}
							// ****** when Payment mode is cash ********
							else {
								new_cash_id = pay_ser.insertCashPayment(pay_dto, request, config);
							}

							dto.setCash_payment_id_fk(new_cash_id);
							dto.setOnline_payment_id_fk(new_online_id);
							

							
							
							//*************** update cash and payment id in customer_due_tb *****************
							PreparedStatement update_due_tb = db.connection
									.prepareStatement(
											"UPDATE customer_due_tb SET\n"
											+ "	cash_payment_id_fk = ? , \n"
											+ "	online_payment_id_fk = ?,"
											+ " customer_account_id_fk = ? \n"
											+ "	WHERE id = ?;");

							update_due_tb.setInt(1, dto.getCash_payment_id_fk());
							update_due_tb.setInt(2, dto.getOnline_payment_id_fk());
							update_due_tb.setInt(3, dto.getCustomer_account_id_fk());
							update_due_tb.setInt(4, bill_id_fk);
							
							

							System.out.println(update_due_tb);

							int i2 = update_due_tb.executeUpdate();

				
							if (i2 != 0) {
								return bill_id_fk;
							}	
						}	

					} catch (Exception e) {
						
						e.printStackTrace();
					}
					return 0;
				}
	
	//****** End pay Customer Due 

	// Method for Show data on Manage page
	public ArrayList<CustomerDto> getCustomerAccountInfo(int id ,String date1, String date2,ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();

		try {
			
			if ("".equalsIgnoreCase(date1) && "".equalsIgnoreCase(date2)) {

				preparedStatement = db.connection.prepareStatement("SELECT 	ca.id, ca.customer_id_fk,ca.user_id_fk,ca.bill_id_fk,ca.c_y_session,ca.debit_amount,ca.credit_amount,ca.TYPE,ca.online_way,ca.online_remark,ca.online_date,ca.online_amount, \r\n"
						+ "ca.payment_mode,ca.pay_date,ca.cash_amount,ca.STATUS,ca.remark FROM customer_account_tb ca \r\n"
						+ "INNER JOIN user_personal_info_tb up ON  ca.user_id_fk = up.id\r\n"
						+ "INNER JOIN customer_info_tb c ON c.id=ca.customer_id_fk\r\n"
						+ "WHERE ca.customer_id_fk=?;");

				
				preparedStatement.setInt(1, id);

			}

			else if (!"".equalsIgnoreCase(date1) && !"".equalsIgnoreCase(date2)) {

				preparedStatement = db.connection.prepareStatement("SELECT 	ca.id, ca.customer_id_fk,ca.user_id_fk,ca.bill_id_fk,ca.c_y_session,ca.debit_amount,ca.credit_amount,ca.TYPE,ca.online_way,ca.online_remark,ca.online_date,ca.online_amount, \r\n"
						+ "ca.payment_mode,ca.pay_date,ca.cash_amount,ca.STATUS,ca.remark FROM customer_account_tb ca \r\n"
						+ "INNER JOIN user_personal_info_tb up ON  ca.user_id_fk = up.id\r\n"
						+ "INNER JOIN customer_info_tb c ON c.id=ca.customer_id_fk\r\n"
						+ "WHERE ca.customer_id_fk=? AND ca.pay_date BETWEEN ? AND ? ORDER BY ca.pay_date;");
				
				
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, date1);
				preparedStatement.setString(3, date2);

			}

			
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				CustomerDto dto = new CustomerDto();

				dto.setId(resultSet.getInt(1));
				
				dto.setCustomer_id_fk(resultSet.getInt(2));
				dto.setUser_id_fk(resultSet.getInt(3));
				dto.setBill_id_fk(resultSet.getInt(4));
				dto.setC_y_session(resultSet.getString(5));
				dto.setDebit_amount(resultSet.getFloat(6));
				dto.setCredit_amount(resultSet.getFloat(7));
				dto.setType(resultSet.getString(8));
				dto.setOnline_way(resultSet.getString(9));
				dto.setOnline_remark(resultSet.getString(10));
				dto.setOnline_date(resultSet.getString(11));
				dto.setOnline_amount(resultSet.getFloat(12));
				dto.setPayment_mode(resultSet.getString(13));
				dto.setPay_date(resultSet.getString(14));
				dto.setCash_amount(resultSet.getFloat(15));
				dto.setStatus(resultSet.getString(16));
				dto.setRemark(resultSet.getString(17));
				
				
				
				


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
		public ArrayList<CustomerDto> getCustomerDueInfoById(int id ,ServletConfig config, HttpServletRequest request)
				throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			ArrayList<CustomerDto> list = new ArrayList<CustomerDto>();

			try {

				// Select query for showing data on manage table
				preparedStatement = db.connection.prepareStatement("\r\n"
						+ "SELECT 	id, customer_id_fk,c_y_session,pay_amount,pay_date,remark,online_way,online_remark,online_date,payment_mode,online_amount,cash_amount, \r\n"
						+ "customer_account_id_fk,bank_id_fk,online_payment_id_fk,cash_payment_id_fk, STATUS\r\n"
						+ "	 FROM customer_due_tb WHERE customer_id_fk=?;");

				
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					CustomerDto dto = new CustomerDto();

					dto.setId(resultSet.getInt(1));
					
					dto.setCustomer_id_fk(resultSet.getInt(2));
					
					
					dto.setC_y_session(resultSet.getString(3));
					dto.setPay_amount(resultSet.getFloat(4));
					dto.setPay_date(resultSet.getString(5));
					dto.setRemark(resultSet.getString(6));
					
					dto.setOnline_way(resultSet.getString(7));
					dto.setOnline_remark(resultSet.getString(8));
					dto.setOnline_date(resultSet.getString(9));
					dto.setPayment_mode(resultSet.getString(10));
					dto.setOnline_amount(resultSet.getFloat(11));
					dto.setCash_amount(resultSet.getFloat(12));
					dto.setCustomer_account_id_fk(resultSet.getInt(13));
					dto.setBank_id_fk(resultSet.getInt(14));
					dto.setOnline_payment_id_fk(resultSet.getInt(15));
					dto.setCash_payment_id_fk(resultSet.getInt(16));
					
					
					
					
					
					
					
					
					dto.setStatus(resultSet.getString(17));
					
					
					


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
		public CustomerDto getCustomerDueInfoByPaidId(int id, ServletConfig config,
				HttpServletRequest request) throws IOException {

			DataDb db = new DataDb(request);
			PreparedStatement preparedStatement = null;

			CustomerDto dto = new CustomerDto();

			try {

				// Select query for showing data on Edit page
				preparedStatement = db.connection
						.prepareStatement("SELECT dt.id, it.user_id_fk, it.name, it.mobile_no, it.address, it.gst_no, it.old_due, it.opening_due, dt.status, \r\n"
								+ " dt.pay_date, dt.remark, dt.payment_mode, dt.online_way, dt.online_remark, dt.online_amount, dt.cash_amount, dt.online_date,\r\n"
								+ " dt.bank_id_fk FROM customer_due_tb dt INNER JOIN customer_info_tb it ON dt.customer_id_fk = it.id\r\n"
								+ " WHERE  dt.id= ?");

				preparedStatement.setInt(1, id);

				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					dto.setId(resultSet.getInt(1));
					dto.setUser_id_fk(resultSet.getInt(2));
					dto.setName(resultSet.getString(3));
					dto.setMobile_no(resultSet.getString(4));
					dto.setAddress(resultSet.getString(5));
					dto.setGst_no(resultSet.getString(6));
					dto.setOld_due(resultSet.getFloat(7));
					dto.setOpening_due(resultSet.getFloat(8));
					dto.setStatus(resultSet.getString(9));
					dto.setPay_date(resultSet.getString(10));
					dto.setRemark(resultSet.getString(11));
					dto.setPayment_mode(resultSet.getString(12));
					dto.setOnline_way(resultSet.getString(13));
					dto.setOnline_remark(resultSet.getString(14));
					dto.setOnline_amount(resultSet.getFloat(15));
					dto.setCash_amount(resultSet.getFloat(16));
					dto.setOnline_date(resultSet.getString(17));
					dto.setBank_id_fk(resultSet.getInt(18));

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


		
		 
	

		//****** start Update Customer Due 
		
		public int UpdateCustomerDue(PaymentDto pay_dto, CustomerDto dto, HttpServletRequest request, ServletConfig config)
				throws IOException {

			DataDb db = new DataDb(request);
			
			
			PreparedStatement ps_acc = null;
			PreparedStatement ps_due = null;
			PreparedStatement ps_increase = null;
			
			
			String old_payment_mode="";
			int  new_cash_id = 0,new_online_id = 0;
			float old_paid_amount = 0;
			

			try {
				
				
				PreparedStatement select_det = db.connection
						.prepareStatement("SELECT 	customer_account_id_fk,customer_id_fk, pay_amount, payment_mode,cash_payment_id_fk,online_payment_id_fk \r\n"
								+ "FROM customer_due_tb\r\n"
								+ "WHERE id= ?;");
				
				select_det.setInt(1, dto.getId());
				System.out.println(select_det);
				ResultSet resultSet = select_det.executeQuery();
				
				
				
				while (resultSet.next()) {
					
					dto.setCustomer_account_id_fk(resultSet.getInt(1));
					dto.setCustomer_id_fk(resultSet.getInt(2));
					old_paid_amount=resultSet.getFloat(3);
					old_payment_mode = (resultSet.getString(4));
					dto.setCash_payment_id_fk(resultSet.getInt(5));
					dto.setOnline_payment_id_fk(resultSet.getInt(6));
					
				}
				
				
				// clear customer old due info
				String sql = "UPDATE customer_info_tb SET old_due = old_due+?,upcoming_date=?,upcoming_remark=?  WHERE id=?;";
				// Update Query for update data
				ps_increase = db.connection.prepareStatement(sql);

				ps_increase.setFloat(1, old_paid_amount-(dto.getCash_amount()+dto.getOnline_amount()));
				ps_increase.setString(2, dto.getUpcoming_date());
				ps_increase.setString(3, dto.getUpcoming_remark());
				ps_increase.setInt(4, dto.getCustomer_id_fk());

				System.out.println(ps_increase);
				ps_increase.executeUpdate();
				
				
				
				String sql4="UPDATE customer_account_tb SET customer_id_fk = ? ,user_id_fk = ? ,c_y_session = ? ,debit_amount = ? , \r\n"
						+ "credit_amount = ? ,TYPE = ? ,online_way = ? ,online_remark = ? ,online_date = ? ,online_amount = ? ,payment_mode = ? ,pay_date = ? , \r\n"
						+ " cash_amount = ? ,STATUS = ?,remark = ? WHERE id = ? ;";
				
				//**** Update payment record (Dealer Due Amount) in dealer account table.
				ps_acc = db.connection.prepareStatement(sql4);
				
				ps_acc.setInt(1, dto.getCustomer_id_fk());
                ps_acc.setInt(2, dto.getUser_id_fk());
				
				ps_acc.setString(3, dto.getC_y_session());
				
				ps_acc.setFloat(4, 0);
				ps_acc.setFloat(5, dto.getCash_amount()+dto.getOnline_amount());
				ps_acc.setString(6, "Customer Due");
				
				ps_acc.setString(7, dto.getOnline_way());
				ps_acc.setString(8, dto.getOnline_remark());
				ps_acc.setString(9, dto.getOnline_date());
				ps_acc.setFloat(10, dto.getOnline_amount());
				ps_acc.setString(11, dto.getPayment_mode());
				ps_acc.setString(12, dto.getPay_date());
				ps_acc.setFloat(13, dto.getCash_amount());
				
				ps_acc.setString(14, dto.getStatus());
				ps_acc.setString(15, dto.getRemark());
				ps_acc.setInt(16, dto.getCustomer_account_id_fk());
				
				
				System.out.println(ps_acc);
				ps_acc.executeUpdate();
				
				
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
				
				//***** Insert new record in dealer due table.
				String sql3="UPDATE customer_due_tb SET c_y_session = ? ,pay_amount = ? , \r\n"
						+ "pay_date = ? ,remark = ? ,online_way = ? ,online_remark = ? ,online_date = ? ,payment_mode = ? ,\r\n"
						+ "online_amount = ? ,cash_amount = ? ,\r\n"
						+ "bank_id_fk = ? ,online_payment_id_fk = ? ,cash_payment_id_fk = ? ,STATUS = ?\r\n"
						+ "WHERE id = ? ;";
				
				ps_due = db.connection.prepareStatement(sql3);

				
				ps_due.setString(1, dto.getC_y_session());
				ps_due.setFloat(2, dto.getCash_amount()+dto.getOnline_amount());
				ps_due.setString(3, dto.getPay_date());
				ps_due.setString(4, dto.getRemark());
				
				ps_due.setString(5, dto.getOnline_way());
				ps_due.setString(6, dto.getOnline_remark());
				
				ps_due.setString(7, dto.getOnline_date());
				ps_due.setString(8, dto.getPayment_mode());
				ps_due.setFloat(9, dto.getOnline_amount());
				ps_due.setFloat(10, dto.getCash_amount());
				ps_due.setInt(11, dto.getBank_id_fk());
				ps_due.setFloat(12, dto.getOnline_payment_id_fk());
				ps_due.setFloat(13, dto.getCash_payment_id_fk());
				ps_due.setString(14, dto.getStatus());
				ps_due.setInt(15, dto.getId());
				
				System.out.println(ps_due);
				ps_due.executeUpdate();
				
				
				return dto.getCustomer_id_fk();
				
				

			} catch (Exception e) {

				e.printStackTrace();

			}

			return 0;
		}

		//****** End Update Customer Due 
		


}
