package com.dto;

public class EmployeeDto {
	
	int id, city_id_fk,user_id_fk, employee_id_fk, attendance_status;
	
	String name,mobile_no,other_no,email_id,address,qualification;  
	String dob,status,current_in_date,id_card_status,photo_status,
	city_name,user_name,in_date,join_date,resign_date,guardian_name;
	
	float salary_per_month;
	
	String bank,account_no,ifsc_code,experience;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCity_id_fk() {
		return city_id_fk;
	}
	public void setCity_id_fk(int city_id_fk) {
		this.city_id_fk = city_id_fk;
	}

	public int getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getOther_no() {
		return other_no;
	}
	public void setOther_no(String other_no) {
		this.other_no = other_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrent_in_date() {
		return current_in_date;
	}
	public void setCurrent_in_date(String current_in_date) {
		this.current_in_date = current_in_date;
	}
	public String getId_card_status() {
		return id_card_status;
	}
	public void setId_card_status(String id_card_status) {
		this.id_card_status = id_card_status;
	}
	public String getPhoto_status() {
		return photo_status;
	}
	public void setPhoto_status(String photo_status) {
		this.photo_status = photo_status;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public float getSalary_per_month() {
		return salary_per_month;
	}
	public void setSalary_per_month(float salary_per_month) {
		this.salary_per_month = salary_per_month;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public int getEmployee_id_fk() {
		return employee_id_fk;
	}
	public void setEmployee_id_fk(int employee_id_fk) {
		this.employee_id_fk = employee_id_fk;
	}
	public int getAttendance_status() {
		return attendance_status;
	}
	public void setAttendance_status(int attendance_status) {
		this.attendance_status = attendance_status;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getJoin_date() {
		return join_date;
	}
	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	public String getGuardian_name() {
		return guardian_name;
	}
	public void setGuardian_name(String guardian_name) {
		this.guardian_name = guardian_name;
	}
	public String getResign_date() {
		return resign_date;
	}
	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
	}
	
	
}
