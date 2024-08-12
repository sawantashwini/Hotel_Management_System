package com.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import com.db.DataDb;
import com.dto.EmployeeDto;
import com.mysql.jdbc.Statement;

public class EmployeeService {

	public int maxId(HttpServletRequest request) {
		try {
			Connection connection = (Connection) new DataDb(request).connection;

			String dbname = connection.getCatalog();

			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables "
							+ "WHERE table_name='employee_personal_info_tb' AND TABLE_SCHEMA=?");
			preparedStatement.setString(1, dbname);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return 0;
	}

	public int insertEmployee(EmployeeDto dto, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);

		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"INSERT INTO employee_personal_info_tb (NAME,mobile_no,other_no,email_id,address,city_id_fk,qualification,salary_per_month,EXP,dob,bank,account_no,ifsc_code,user_id_fk,id_card_status,photo_status,guardian_name,join_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile_no());
			ps.setString(3, dto.getOther_no());
			ps.setString(4, dto.getEmail_id());
			ps.setString(5, dto.getAddress());
			ps.setInt(6, dto.getCity_id_fk());
			ps.setString(7, dto.getQualification());
			ps.setFloat(8, dto.getSalary_per_month());
			ps.setString(9, dto.getExperience());
			ps.setString(10, dto.getDob());
			ps.setString(11, dto.getBank());
			ps.setString(12, dto.getAccount_no());
			ps.setString(13, dto.getIfsc_code());
			ps.setInt(14, dto.getUser_id_fk());
			ps.setString(15, dto.getId_card_status());
			ps.setString(16, dto.getPhoto_status());
			ps.setString(17, dto.getGuardian_name());
			ps.setString(18, dto.getJoin_date());

			System.out.println(ps);

			int i = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			dto.setId(rs.getInt(1));
			if (i != 0) {

				return rs.getInt(1);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return 0;

	}

	public ArrayList<EmployeeDto> getEmployeeInfoAfterMonthYear(String month, String year, ServletConfig config, HttpServletRequest request) throws IOException {
		
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT ep.id, ep.name, ep.mobile_no, ep.other_no, ep.email_id, ep.address,\n"
							+ "ep.city_id_fk, ep.qualification, ep.salary_per_month, ep.exp,	ep.dob, ep.bank, ep.account_no, ep.ifsc_code, ep.status,\n"
							+ "ep.current_in_date, ep.user_id_fk, ep.id_card_status,	ep.photo_status, ct.name, us.name , ep.join_date, ep.resign_date, \n"
							+ "ep.guardian_name \n"
							+ "FROM employee_personal_info_tb ep\n"
							+ "INNER JOIN city_tb ct ON ep.city_id_fk = ct.id\n"
							+ "INNER JOIN user_personal_info_tb us ON ep.user_id_fk = us.id\n"
							+ "WHERE (DATE_FORMAT(ep.join_date, '%Y-%m') <= ? AND ep.resign_date = '') \n"
							+ "OR (DATE_FORMAT(ep.join_date, '%Y-%m') <= ? AND DATE_FORMAT(ep.resign_date, '%Y-%m') >= ?)");
			
			preparedStatement.setString(1, year+"-"+month);
			preparedStatement.setString(2, year+"-"+month);
			preparedStatement.setString(3, year+"-"+month);
			
			System.out.println(preparedStatement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setOther_no(resultSet.getString(4));

				dto.setEmail_id(resultSet.getString(5));
				dto.setAddress(resultSet.getString(6));
				dto.setCity_id_fk(resultSet.getInt(7));
				dto.setQualification(resultSet.getString(8));

				dto.setSalary_per_month(resultSet.getFloat(9));
				dto.setExperience(resultSet.getString(10));
				dto.setDob(resultSet.getString(11));
				dto.setBank(resultSet.getString(12));
				dto.setAccount_no(resultSet.getString(13));
				dto.setIfsc_code(resultSet.getString(14));
				dto.setStatus(resultSet.getString(15));
				dto.setCurrent_in_date(resultSet.getString(16));
				dto.setUser_id_fk(resultSet.getInt(17));

				dto.setId_card_status(resultSet.getString(18));
				dto.setPhoto_status(resultSet.getString(19));
				dto.setCity_name(resultSet.getString(20));
				dto.setUser_name(resultSet.getString(21));
				dto.setJoin_date(resultSet.getString(22));
				dto.setResign_date(resultSet.getString(23));
				dto.setGuardian_name(resultSet.getString(24));

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
	
	
	public ArrayList<EmployeeDto> getEmployeeInfoAfterJoinDate(String date, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT ep.id, ep.name, ep.mobile_no, ep.other_no, ep.email_id, ep.address,"
							+ "	ep.city_id_fk, ep.qualification, ep.salary_per_month, ep.exp,"
							+ "	ep.dob, ep.bank, ep.account_no, ep.ifsc_code, ep.status, "
							+ "	ep.current_in_date, ep.user_id_fk, ep.id_card_status,"
							+ "	ep.photo_status, ct.name, us.name , ep.join_date, ep.resign_date, ep.guardian_name " 
							+ "	FROM employee_personal_info_tb ep"
							+ "	INNER JOIN city_tb ct ON ep.city_id_fk=ct.id"
							+ "	INNER JOIN user_personal_info_tb us ON ep.user_id_fk=us.id"
							+ " WHERE (DATE_FORMAT(ep.join_date, '%Y-%m-%d') <= ? AND ep.resign_date = '') \n"
							+ "OR (DATE_FORMAT(ep.join_date, '%Y-%m-%d') <= ? AND DATE_FORMAT(ep.resign_date, '%Y-%m-%d') >= ?) ;");
			
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, date);
			
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setOther_no(resultSet.getString(4));

				dto.setEmail_id(resultSet.getString(5));
				dto.setAddress(resultSet.getString(6));
				dto.setCity_id_fk(resultSet.getInt(7));
				dto.setQualification(resultSet.getString(8));

				dto.setSalary_per_month(resultSet.getFloat(9));
				dto.setExperience(resultSet.getString(10));
				dto.setDob(resultSet.getString(11));
				dto.setBank(resultSet.getString(12));
				dto.setAccount_no(resultSet.getString(13));
				dto.setIfsc_code(resultSet.getString(14));
				dto.setStatus(resultSet.getString(15));
				dto.setCurrent_in_date(resultSet.getString(16));
				dto.setUser_id_fk(resultSet.getInt(17));

				dto.setId_card_status(resultSet.getString(18));
				dto.setPhoto_status(resultSet.getString(19));
				dto.setCity_name(resultSet.getString(20));
				dto.setUser_name(resultSet.getString(21));
				dto.setJoin_date(resultSet.getString(22));
				dto.setResign_date(resultSet.getString(23));
				dto.setGuardian_name(resultSet.getString(24));

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
	
	
	public ArrayList<EmployeeDto> getEmployeeInfoByName(String name, ServletConfig config, HttpServletRequest request) throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();
		PreparedStatement preparedStatement = null;

		try {
			
			if(name.equalsIgnoreCase("All")) {
				
				preparedStatement = db.connection
						.prepareStatement("SELECT ep.id, ep.name, ep.mobile_no, ep.other_no, ep.email_id, ep.address,"
								+ "	ep.city_id_fk, ep.qualification, ep.salary_per_month, ep.exp,"
								+ "	ep.dob, ep.bank, ep.account_no, ep.ifsc_code, ep.status, "
								+ "	ep.current_in_date, ep.user_id_fk, ep.id_card_status,"
								+ "	ep.photo_status, ct.name, us.name , ep.join_date, ep.resign_date, ep.guardian_name " 
								+ "	FROM employee_personal_info_tb ep"
								+ "	INNER JOIN city_tb ct ON ep.city_id_fk=ct.id"
								+ "	INNER JOIN user_personal_info_tb us ON ep.user_id_fk=us.id;");
				
			}else if(!name.equalsIgnoreCase("")){
			 preparedStatement = db.connection
					.prepareStatement("SELECT ep.id, ep.name, ep.mobile_no, ep.other_no, ep.email_id, ep.address,"
							+ "	ep.city_id_fk, ep.qualification, ep.salary_per_month, ep.exp,"
							+ "	ep.dob, ep.bank, ep.account_no, ep.ifsc_code, ep.status, "
							+ "	ep.current_in_date, ep.user_id_fk, ep.id_card_status,"
							+ "	ep.photo_status, ct.name, us.name , ep.join_date, ep.resign_date, ep.guardian_name " 
							+ "	FROM employee_personal_info_tb ep"
							+ "	INNER JOIN city_tb ct ON ep.city_id_fk=ct.id"
							+ "	INNER JOIN user_personal_info_tb us ON ep.user_id_fk=us.id WHERE ep.name LIKE ?;");
			
			preparedStatement.setString(1, '%'+name+'%');
			}
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setOther_no(resultSet.getString(4));

				dto.setEmail_id(resultSet.getString(5));
				dto.setAddress(resultSet.getString(6));
				dto.setCity_id_fk(resultSet.getInt(7));
				dto.setQualification(resultSet.getString(8));

				dto.setSalary_per_month(resultSet.getFloat(9));
				dto.setExperience(resultSet.getString(10));
				dto.setDob(resultSet.getString(11));
				dto.setBank(resultSet.getString(12));
				dto.setAccount_no(resultSet.getString(13));
				dto.setIfsc_code(resultSet.getString(14));
				dto.setStatus(resultSet.getString(15));
				dto.setCurrent_in_date(resultSet.getString(16));
				dto.setUser_id_fk(resultSet.getInt(17));

				dto.setId_card_status(resultSet.getString(18));
				dto.setPhoto_status(resultSet.getString(19));
				dto.setCity_name(resultSet.getString(20));
				dto.setUser_name(resultSet.getString(21));
				dto.setJoin_date(resultSet.getString(22));
				dto.setResign_date(resultSet.getString(23));
				dto.setGuardian_name(resultSet.getString(24));

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


	public EmployeeDto getEmployeeInfoById(int id, ServletConfig config, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		EmployeeDto dto = new EmployeeDto();

		try {

			// Select query for showing data on Edit page
			preparedStatement = db.connection
					.prepareStatement("SELECT 	ep.id,ep.name,ep.mobile_no,ep.other_no,ep.email_id,ep.address, \n"
							+ "	ep.city_id_fk,ep.qualification,ep.salary_per_month,ep.exp, \n"
							+ "	ep.dob,ep.bank,ep.account_no,ep.ifsc_code,ep.status, \n"
							+ "	ep.current_in_date,ep.user_id_fk,ep.id_card_status, \n"
							+ "	ep.photo_status,ct.name,us.name, ep.join_date, ep.resign_date, ep.guardian_name  \n" 
							+ "	FROM employee_personal_info_tb ep\n"
							+ "	INNER JOIN city_tb ct ON ep.city_id_fk=ct.id\n"
							+ "	INNER JOIN user_personal_info_tb us ON ep.user_id_fk=us.id" + "	WHERE ep.id=?;");

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));
				dto.setMobile_no(resultSet.getString(3));
				dto.setOther_no(resultSet.getString(4));

				dto.setEmail_id(resultSet.getString(5));
				dto.setAddress(resultSet.getString(6));
				dto.setCity_id_fk(resultSet.getInt(7));
				dto.setQualification(resultSet.getString(8));

				dto.setSalary_per_month(resultSet.getFloat(9));
				dto.setExperience(resultSet.getString(10));
				dto.setDob(resultSet.getString(11));
				dto.setBank(resultSet.getString(12));
				dto.setAccount_no(resultSet.getString(13));
				dto.setIfsc_code(resultSet.getString(14));
				dto.setStatus(resultSet.getString(15));
				dto.setCurrent_in_date(resultSet.getString(16));
				dto.setUser_id_fk(resultSet.getInt(17));

				dto.setId_card_status(resultSet.getString(18));
				dto.setPhoto_status(resultSet.getString(19));
				dto.setCity_name(resultSet.getString(20));
				dto.setUser_name(resultSet.getString(21));
				dto.setJoin_date(resultSet.getString(22));
				dto.setResign_date(resultSet.getString(23));
				dto.setGuardian_name(resultSet.getString(24));
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

	public boolean UpdateEmployee(EmployeeDto dto, HttpServletRequest request, ServletConfig config)
			throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"UPDATE employee_personal_info_tb SET NAME = ?,mobile_no = ?,other_no = ?,email_id = ?,address = ?,city_id_fk = ?,qualification = ?,salary_per_month = ?,EXP = ?, "
					+ "dob = ?,bank = ?,account_no = ?,ifsc_code = ?,id_card_status = ?,photo_status = ?,STATUS = ?, join_date=?, resign_date = ?, guardian_name = ? WHERE id = ?;");

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getMobile_no());
			ps.setString(3, dto.getOther_no());
			ps.setString(4, dto.getEmail_id());

			ps.setString(5, dto.getAddress());
			ps.setInt(6, dto.getCity_id_fk());
			ps.setString(7, dto.getQualification());
			ps.setFloat(8, dto.getSalary_per_month());

			ps.setString(9, dto.getExperience());
			ps.setString(10, dto.getDob());
			ps.setString(11, dto.getBank());
			ps.setString(12, dto.getAccount_no());
			ps.setString(13, dto.getIfsc_code());
			ps.setString(14, dto.getId_card_status());
			ps.setString(15, dto.getPhoto_status());

			ps.setString(16, dto.getStatus());
			ps.setString(17, dto.getJoin_date());
			ps.setString(18, dto.getResign_date());
			ps.setString(19, dto.getGuardian_name());
			ps.setInt(20, dto.getId());

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

	public ArrayList<EmployeeDto> getEmployeeName(ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name FROM employee_personal_info_tb;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));

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
	
	public ArrayList<EmployeeDto> getActiveEmployeeName(ServletConfig config, HttpServletRequest request)
			throws IOException {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		try {
			PreparedStatement preparedStatement = db.connection
					.prepareStatement("SELECT id, name FROM employee_personal_info_tb WHERE status = 'Active' GROUP BY name;");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				EmployeeDto dto = new EmployeeDto();

				dto.setId(resultSet.getInt(1));
				dto.setName(resultSet.getString(2));

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

	public boolean deleteEmployee(int id, HttpServletRequest request, ServletConfig config) throws IOException {

		DataDb db = new DataDb(request);
		try {

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

	public boolean employeeAttendanceInsert(String check, int user_id, String date, HttpServletRequest request) {
		DataDb db = new DataDb(request);

		PreparedStatement preparedStatement = null;
		try {
			String str[] = check.split("-");
			preparedStatement = db.connection.prepareStatement(
					"INSERT INTO employee_attendance (employee_id_fk, in_date, attendance_status, user_id_fk ) VALUES(?,?,?,?);");

			preparedStatement.setString(1, str[0].trim());
			preparedStatement.setString(2, date);
			preparedStatement.setString(3, str[1]);
			preparedStatement.setInt(4, user_id);
			System.out.println(str[0]+ "check string");
			
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		    return true;
		    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (db != null && db.connection != null) {
	            try {
	                db.connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    return false;
	}

	public ArrayList<EmployeeDto> getAttendenceInfoByEmp(int id, String month, String year,
			HttpServletRequest request) {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		// String[] sess = session.split("-");
		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = db.connection
					.prepareStatement("SELECT id,employee_id_fk,DATE_FORMAT(in_date,'%d-%b-%Y'),attendance_status \n"
							+ "FROM employee_attendance WHERE employee_id_fk=? AND MONTH(in_date)=? AND YEAR(in_date)=? ; ");
			// preparedStatement.setString(1, sess[0]);
			// preparedStatement.setString(2, sess[1]);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, month);
			preparedStatement.setString(3, year);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setAttendance_status(resultSet.getInt(4));
				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	
	public EmployeeDto getAttendenceInfoById(int id, HttpServletRequest request) {
		DataDb db = new DataDb(request);
		EmployeeDto dto = new EmployeeDto();

		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = db.connection
					.prepareStatement("SELECT id,employee_id_fk,in_date,attendance_status \n"
							+ "FROM employee_attendance WHERE id =?");

			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				dto.setIn_date(resultSet.getString(3));
				dto.setAttendance_status(resultSet.getInt(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return dto;
	}
	
	public boolean updateEmployeeAttendance(int id, int att_status, int user_id_fk, HttpServletRequest request)
			throws IOException {

		DataDb db = new DataDb(request);
		try {

			PreparedStatement ps = db.connection.prepareStatement(
					"UPDATE employee_attendance SET	attendance_status = ?, user_id_fk = ? WHERE id = ? ;");

			ps.setInt(1, att_status);
			ps.setInt(2, user_id_fk);
			ps.setInt(3, id);

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
	
	
	public ArrayList<EmployeeDto> getUnfillAttendanceInfo(String date,
			HttpServletRequest request) {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		// String[] sess = session.split("-");
		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = db.connection
					.prepareStatement("SELECT 	id, employee_id_fk FROM employee_attendance WHERE in_date = ?;");
			// preparedStatement.setString(1, sess[0]);
			// preparedStatement.setString(2, sess[1]);
			preparedStatement.setString(1, date);
			System.out.println(preparedStatement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	
	public ArrayList<EmployeeDto> getSalaryPaidOrNot(String month, String year,
			HttpServletRequest request) {
		DataDb db = new DataDb(request);

		ArrayList<EmployeeDto> list = new ArrayList<EmployeeDto>();

		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = db.connection
					.prepareStatement("SELECT 	id, employee_id_fk FROM employee_salary_tb \n"
							+ "WHERE paid_month = ? && paid_year = ?;");
			preparedStatement.setString(1, month);
			preparedStatement.setString(2, year);
			
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				EmployeeDto dto = new EmployeeDto();
				dto.setId(resultSet.getInt(1));
				dto.setEmployee_id_fk(resultSet.getInt(2));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db.connection != null)
				try {
					db.connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	
	// Method for Show data on Manage page
	public int checkEmployeeNameIsCorret(String name, ServletConfig config, HttpServletRequest request) throws IOException {

		DataDb db = new DataDb(request);
		PreparedStatement preparedStatement = null;

		try {

			// Select query for showing data on manage table
			preparedStatement = db.connection.prepareStatement("SELECT NAME, id FROM employee_personal_info_tb WHERE NAME= ?;");
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
		//	System.out.println("Hello1");
			while (resultSet.next()) {
				 if (name.equalsIgnoreCase(resultSet.getString(1))) {
					 System.out.println("id="+resultSet.getInt(2));
					// System.out.println("Hello2");
					 return resultSet.getInt(2); 
				 }
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

}
