package com.service;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;

public class DeleteEmployeeService {
	
	public boolean deleteEmployee(int id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps_cash = db.connection.prepareStatement(
					"DELETE c FROM cash_payment_tb c INNER JOIN employee_account_tb eap ON eap.id = c.bill_id_fk WHERE eap.employee_id_fk = ? AND type = 'Transaction';");

			ps_cash.setInt(1, id);
			System.out.println(ps_cash);

			ps_cash.executeUpdate();

			PreparedStatement ps_online = db.connection.prepareStatement(
					"DELETE o FROM online_payment_tb o INNER JOIN employee_account_tb eap ON eap.id = o.bill_id_fk WHERE eap.employee_id_fk = ? AND type = 'Transaction';");

			ps_online.setInt(1, id);
			System.out.println(ps_online);

			ps_online.executeUpdate();

			PreparedStatement ps_cash_salary = db.connection.prepareStatement(
					"DELETE c FROM cash_payment_tb c INNER JOIN employee_salary_tb esp ON esp.id = c.bill_id_fk WHERE esp.employee_id_fk = ? AND type = 'Salary';");

			ps_cash_salary.setInt(1, id);
			System.out.println(ps_cash_salary);

			ps_cash_salary.executeUpdate();

			PreparedStatement ps_online_salary = db.connection.prepareStatement(
					"DELETE o FROM online_payment_tb o INNER JOIN employee_salary_tb esp ON esp.id = o.bill_id_fk WHERE esp.employee_id_fk = ? AND type = 'Salary';");

			ps_online_salary.setInt(1, id);
			System.out.println(ps_online_salary);

			ps_online_salary.executeUpdate();

			PreparedStatement ps_account = db.connection
					.prepareStatement("DELETE FROM employee_account_tb WHERE employee_id_fk = ?;");

			ps_account.setInt(1, id);
			System.out.println(ps_account);

			ps_account.executeUpdate();

			PreparedStatement ps_salary = db.connection
					.prepareStatement("DELETE FROM employee_salary_tb WHERE employee_id_fk = ?;");

			ps_salary.setInt(1, id);
			System.out.println(ps_salary);

			ps_salary.executeUpdate();
			PreparedStatement ps_attendance = db.connection
					.prepareStatement("DELETE FROM employee_attendance WHERE employee_id_fk = ?;");

			ps_attendance.setInt(1, id);
			System.out.println(ps_attendance);

			ps_attendance.executeUpdate();
			PreparedStatement ps = db.connection
					.prepareStatement("DELETE FROM employee_personal_info_tb WHERE id = ?;");

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

	public boolean deleteEmployeeAttendance(int id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps_attendance = db.connection
					.prepareStatement("DELETE FROM employee_attendance WHERE id = ?;");

			ps_attendance.setInt(1, id);
			System.out.println(ps_attendance);

			int i = ps_attendance.executeUpdate();

			if (i != 0) {

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteEmployeeSalary(int id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps_salary = db.connection
					.prepareStatement("DELETE FROM employee_salary_tb WHERE id = ?;");

			ps_salary.setInt(1, id);
			System.out.println(ps_salary);

			int i = ps_salary.executeUpdate();

			if (i != 0) {

				PreparedStatement ps_cash = db.connection
						.prepareStatement("DELETE FROM cash_payment_tb WHERE bill_id_fk = ? AND type = 'Salary';");

				ps_cash.setInt(1, id);
				System.out.println(ps_cash);

				ps_cash.executeUpdate();

				PreparedStatement ps_online = db.connection
						.prepareStatement("DELETE FROM online_payment_tb WHERE bill_id_fk = ? AND type = 'Salary';");

				ps_online.setInt(1, id);
				System.out.println(ps_online);

				ps_online.executeUpdate();

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	public boolean deleteEmployeeTransaction(int id, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement("DELETE FROM employee_account_tb WHERE id = ?;");

			ps.setInt(1, id);
			System.out.println(ps);

			int i = ps.executeUpdate();

			if (i != 0) {

				PreparedStatement ps_cash = db.connection
						.prepareStatement("DELETE FROM cash_payment_tb WHERE bill_id_fk = ? AND type = 'Transaction';");

				ps_cash.setInt(1, id);
				System.out.println(ps_cash);

				ps_cash.executeUpdate();

				PreparedStatement ps_online = db.connection.prepareStatement(
						"DELETE FROM online_payment_tb WHERE bill_id_fk = ? AND type = 'Transaction';;");

				ps_online.setInt(1, id);
				System.out.println(ps_online);

				ps_online.executeUpdate();

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}


}
