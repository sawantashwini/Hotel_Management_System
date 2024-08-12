package com.dto;

public class BankDto {

	int id, user_id_fk;
	String name, account_no, branch, ifsc_code, current_in_date, opening_date, status, user_name, in_date;
	float opening_bal, credit;

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getCurrent_in_date() {
		return current_in_date;
	}

	public void setCurrent_in_date(String current_in_date) {
		this.current_in_date = current_in_date;
	}

	public String getOpening_date() {
		return opening_date;
	}

	public void setOpening_date(String opening_date) {
		this.opening_date = opening_date;
	}

	public float getOpening_bal() {
		return opening_bal;
	}

	public void setOpening_bal(float opening_bal) {
		this.opening_bal = opening_bal;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	int debit_bank_id_fk, credit_bank_id_fk, online_id_fk, cash_id_fk, debit_online_id_fk, credit_online_id_fk;
	float transaction_amount, credit_amount;
	public int getDebit_online_id_fk() {
		return debit_online_id_fk;
	}

	public void setDebit_online_id_fk(int debit_online_id_fk) {
		this.debit_online_id_fk = debit_online_id_fk;
	}

	public int getCredit_online_id_fk() {
		return credit_online_id_fk;
	}

	public void setCredit_online_id_fk(int credit_online_id_fk) {
		this.credit_online_id_fk = credit_online_id_fk;
	}

	public float getCredit_amount() {
		return credit_amount;
	}

	public void setCredit_amount(float credit_amount) {
		this.credit_amount = credit_amount;
	}

	String transaction_type, debit_bank_name, credit_bank_name, remark;

	public int getCash_id_fk() {
		return cash_id_fk;
	}

	public void setCash_id_fk(int cash_id_fk) {
		this.cash_id_fk = cash_id_fk;
	}

	public int getOnline_id_fk() {
		return online_id_fk;
	}

	public void setOnline_id_fk(int online_id_fk) {
		this.online_id_fk = online_id_fk;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDebit_bank_id_fk() {
		return debit_bank_id_fk;
	}

	public void setDebit_bank_id_fk(int debit_bank_id_fk) {
		this.debit_bank_id_fk = debit_bank_id_fk;
	}

	public int getCredit_bank_id_fk() {
		return credit_bank_id_fk;
	}

	public void setCredit_bank_id_fk(int credit_bank_id_fk) {
		this.credit_bank_id_fk = credit_bank_id_fk;
	}

	public float getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public String getDebit_bank_name() {
		return debit_bank_name;
	}

	public void setDebit_bank_name(String debit_bank_name) {
		this.debit_bank_name = debit_bank_name;
	}

	public String getCredit_bank_name() {
		return credit_bank_name;
	}

	public void setCredit_bank_name(String credit_bank_name) {
		this.credit_bank_name = credit_bank_name;
	}

}
