package com.dto;

public class EmployeeAccountDto {
	
	private int id,employee_id_fk,user_id_fk,cash_payment_id_fk,online_payment_id_fk,bank_id_fk;
	private float debit_amount,credit_amount,online_amount,cash_amount,amount, Salary_per_month;
	private String payment_mode,online_remark,online_date,online_way, 
			status,current_in_date, bank_name;
	
	private String employee_name,mobile_no,user_name, qualification,address, in_date, remark;

	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getSalary_per_month() {
		return Salary_per_month;
	}
	public void setSalary_per_month(float salary_per_month) {
		Salary_per_month = salary_per_month;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployee_id_fk() {
		return employee_id_fk;
	}
	
	
	public float getOnline_amount() {
		return online_amount;
	}
	public void setOnline_amount(float online_amount) {
		this.online_amount = online_amount;
	}
	public float getCash_amount() {
		return cash_amount;
	}
	public void setCash_amount(float cash_amount) {
		this.cash_amount = cash_amount;
	}
	public String getOnline_way() {
		return online_way;
	}
	public void setOnline_way(String online_way) {
		this.online_way = online_way;
	}
	public void setEmployee_id_fk(int employee_id_fk) {
		this.employee_id_fk = employee_id_fk;
	}
	public int getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public float getDebit_amount() {
		return debit_amount;
	}
	public void setDebit_amount(float debit_amount) {
		this.debit_amount = debit_amount;
	}
	
	public int getCash_payment_id_fk() {
		return cash_payment_id_fk;
	}
	public void setCash_payment_id_fk(int cash_payment_id_fk) {
		this.cash_payment_id_fk = cash_payment_id_fk;
	}
	public int getOnline_payment_id_fk() {
		return online_payment_id_fk;
	}
	public void setOnline_payment_id_fk(int online_payment_id_fk) {
		this.online_payment_id_fk = online_payment_id_fk;
	}
	public float getCredit_amount() {
		return credit_amount;
	}
	public void setCredit_amount(float credit_amount) {
		this.credit_amount = credit_amount;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	
	
	public int getBank_id_fk() {
		return bank_id_fk;
	}
	public void setBank_id_fk(int bank_id_fk) {
		this.bank_id_fk = bank_id_fk;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getOnline_remark() {
		return online_remark;
	}
	public void setOnline_remark(String online_remark) {
		this.online_remark = online_remark;
	}
	public String getOnline_date() {
		return online_date;
	}
	public void setOnline_date(String online_date) {
		this.online_date = online_date;
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
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	

}
