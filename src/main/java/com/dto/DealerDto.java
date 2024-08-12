package com.dto;

public class DealerDto {
	 
	
	int id,user_id_fk;
	String Name,mobile_no,address,Status,gst_no,in_date,current_in_date;
	float old_due,opening_due;
	
	int  dealer_id_fk;
	float total_amount; 
	String name, remark,  bill_date, status, bank_name;
	
	//Pay Due	
	int dealer_account_id_fk, online_payment_id_fk, cash_payment_id_fk, bill_id_fk, bank_id_fk;
	String c_y_session, type, pay_date, payment_mode, online_way, online_remark, online_date;
	float debit_amt, credit_amt, pay_amount, online_amount, cash_amount;
	
	
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getGst_no() {
		return gst_no;
	}
	public void setGst_no(String gst_no) {
		this.gst_no = gst_no;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getCurrent_in_date() {
		return current_in_date;
	}
	public void setCurrent_in_date(String current_in_date) {
		this.current_in_date = current_in_date;
	}
	public float getOld_due() {
		return old_due;
	}
	public void setOld_due(float old_due) {
		this.old_due = old_due;
	}
	public float getOpening_due() {
		return opening_due;
	}
	public void setOpening_due(float opening_due) {
		this.opening_due = opening_due;
	}
	public int getDealer_id_fk() {
		return dealer_id_fk;
	}
	public void setDealer_id_fk(int dealer_id_fk) {
		this.dealer_id_fk = dealer_id_fk;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public int getDealer_account_id_fk() {
		return dealer_account_id_fk;
	}
	public void setDealer_account_id_fk(int dealer_account_id_fk) {
		this.dealer_account_id_fk = dealer_account_id_fk;
	}
	public int getOnline_payment_id_fk() {
		return online_payment_id_fk;
	}
	public void setOnline_payment_id_fk(int online_payment_id_fk) {
		this.online_payment_id_fk = online_payment_id_fk;
	}
	public int getCash_payment_id_fk() {
		return cash_payment_id_fk;
	}
	public void setCash_payment_id_fk(int cash_payment_id_fk) {
		this.cash_payment_id_fk = cash_payment_id_fk;
	}
	public int getBill_id_fk() {
		return bill_id_fk;
	}
	public void setBill_id_fk(int bill_id_fk) {
		this.bill_id_fk = bill_id_fk;
	}
	public int getBank_id_fk() {
		return bank_id_fk;
	}
	public void setBank_id_fk(int bank_id_fk) {
		this.bank_id_fk = bank_id_fk;
	}
	public String getC_y_session() {
		return c_y_session;
	}
	public void setC_y_session(String c_y_session) {
		this.c_y_session = c_y_session;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	public String getOnline_way() {
		return online_way;
	}
	public void setOnline_way(String online_way) {
		this.online_way = online_way;
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
	public float getDebit_amt() {
		return debit_amt;
	}
	public void setDebit_amt(float debit_amt) {
		this.debit_amt = debit_amt;
	}
	public float getCredit_amt() {
		return credit_amt;
	}
	public void setCredit_amt(float credit_amt) {
		this.credit_amt = credit_amt;
	}
	public float getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(float pay_amount) {
		this.pay_amount = pay_amount;
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
	
}
