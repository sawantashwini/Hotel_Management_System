package com.dto;

public class SpendDto {
	int id, head_id_fk, bank_id_fk, online_payment_id_fk, cash_payment_id_fk, user_id_fk;
	float amount, cash_amount, online_amount, debit_online_amount, debit_cash_amount;

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	String remark, online_remark, online_date, online_way, current_in_date, in_date, STATUS, payment_mode, spend_name,
			bank_name, name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpend_name() {
		return spend_name;
	}

	public void setSpend_name(String spend_name) {
		this.spend_name = spend_name;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getPayment_mode() {
		return payment_mode;
	}

	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHead_id_fk() {
		return head_id_fk;
	}

	public void setHead_id_fk(int head_id_fk) {
		this.head_id_fk = head_id_fk;
	}

	public int getBank_id_fk() {
		return bank_id_fk;
	}

	public void setBank_id_fk(int bank_id_fk) {
		this.bank_id_fk = bank_id_fk;
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

	public int getUser_id_fk() {
		return user_id_fk;
	}

	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}

	public float getCash_amount() {
		return cash_amount;
	}

	public void setCash_amount(float cash_amount) {
		this.cash_amount = cash_amount;
	}

	public float getOnline_amount() {
		return online_amount;
	}

	public void setOnline_amount(float online_amount) {
		this.online_amount = online_amount;
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

	public String getCurrent_in_date() {
		return current_in_date;
	}

	public void setCurrent_in_date(String current_in_date) {
		this.current_in_date = current_in_date;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

}
