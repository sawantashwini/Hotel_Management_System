package com.dto;

public class PaymentDto {
	int  bank_id_fk,bill_id_fk,ipd_id_fk, user_id_fk;
	float online_amount,cash_amount,debit_online_amount,debit_cash_amount;
	String remark,online_remark,online_date,online_way,in_date,status,type, user_name;
	public int getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getBank_id_fk() {
		return bank_id_fk;
	}
	public void setBank_id_fk(int bank_id_fk) {
		this.bank_id_fk = bank_id_fk;
	}
	public int getBill_id_fk() {
		return bill_id_fk;
	}
	public void setBill_id_fk(int bill_id_fk) {
		this.bill_id_fk = bill_id_fk;
	}
	
	public int getIpd_id_fk() {
		return ipd_id_fk;
	}
	public void setIpd_id_fk(int ipd_id_fk) {
		this.ipd_id_fk = ipd_id_fk;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getOnline_way() {
		return online_way;
	}
	public void setOnline_way(String online_way) {
		this.online_way = online_way;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getDebit_online_amount() {
		return debit_online_amount;
	}
	public void setDebit_online_amount(float debit_online_amount) {
		this.debit_online_amount = debit_online_amount;
	}
	public float getDebit_cash_amount() {
		return debit_cash_amount;
	}
	public void setDebit_cash_amount(float debit_cash_amount) {
		this.debit_cash_amount = debit_cash_amount;
	}
}
