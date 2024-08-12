package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;

public class DeleteBankService {
	// Method For Delete Bank
	public boolean deleteBank(int id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {
			// Delete for Bank

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM bank_tb WHERE id = ?;");
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

	// Method For Delete Bank Cash Credit
	public boolean deleteBankCashCredit(int id, int cash_id, int on_id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {
			// Delete for Bank Cash Credit

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM bank_cash_credit_tb WHERE id = ?;");
			ps.setInt(1, id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {
				
				PreparedStatement ps_cash = db.connection.prepareStatement("DELETE FROM cash_payment_tb WHERE id = ?;");
				ps_cash.setInt(1, cash_id);

				System.out.println(ps_cash);
				ps_cash.executeUpdate();
				
				PreparedStatement ps_on = db.connection.prepareStatement("DELETE FROM online_payment_tb WHERE id = ?;");
				ps_on.setInt(1, on_id);

				System.out.println(ps_on);
				ps_on.executeUpdate();

				return true;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	// Method For Delete Bank Transaction
	public boolean deleteBankTransaction(int id, int deb_online_id, int cred_online_id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {
			// Delete for Bank Transaction

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM bank_transaction_tb WHERE id = ?;");
			ps.setInt(1, id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {
				
				
				PreparedStatement ps_on = db.connection.prepareStatement("DELETE FROM online_payment_tb WHERE id = ?;");
				ps_on.setInt(1, deb_online_id);

				System.out.println(ps_on);
				ps_on.executeUpdate();
				
				PreparedStatement ps_on_cred = db.connection.prepareStatement("DELETE FROM online_payment_tb WHERE id = ?;");
				ps_on_cred.setInt(1, cred_online_id);

				System.out.println(ps_on_cred);
				ps_on_cred.executeUpdate();

				return true;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	
}
