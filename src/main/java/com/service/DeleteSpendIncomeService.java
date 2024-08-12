package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;

public class DeleteSpendIncomeService {

	// Method For Delete Spend data
	public boolean deleteSpend(int spend_id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		PreparedStatement ps_select = null;

		int cash_payment_id = 0;
		int online_payment_id = 0;

		try {
			// Delete for opd Bill

			ps_select = db.connection
					.prepareStatement("SELECT 	cash_payment_id_fk, online_payment_id_fk FROM spend_tb WHERE id= ?;");

			ps_select.setInt(1, spend_id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				cash_payment_id = (resultSet.getInt(1));
				online_payment_id = (resultSet.getInt(2));

			}

			ps = db.connection.prepareStatement("DELETE FROM spend_tb WHERE id = ?;");
			ps.setInt(1, spend_id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {

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

	// Method For Delete Income data
	public boolean deleteIncome(int spend_id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement ps = null;
		PreparedStatement ps_select = null;

		int cash_payment_id = 0;
		int online_payment_id = 0;

		try {
			// Delete for opd Bill

			ps_select = db.connection.prepareStatement(
					"SELECT 	cash_payment_id_fk, online_payment_id_fk \n" + "FROM income_tb WHERE id= ?;");

			ps_select.setInt(1, spend_id);
			System.out.println(ps_select);
			ResultSet resultSet = ps_select.executeQuery();

			while (resultSet.next()) {

				cash_payment_id = (resultSet.getInt(1));
				online_payment_id = (resultSet.getInt(2));

			}

			ps = db.connection.prepareStatement("DELETE FROM income_tb WHERE id = ?;");
			ps.setInt(1, spend_id);

			System.out.println(ps);
			int i = ps.executeUpdate();

			if (i != 0) {

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

}
