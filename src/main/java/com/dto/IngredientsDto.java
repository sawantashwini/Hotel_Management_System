package com.dto;

public class IngredientsDto {
	int id, user_id_fk, measurement_id_fk, bill_id_fk, item_id_fk;
	String name, status, current_in_date, user_name, measurement_name, remark, in_date, item_name;
	float qty, min_limit, item_quantity, item_amt, item_rate,total_amount;

	public float getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}

	public float getItem_amt() {
		return item_amt;
	}

	public void setItem_amt(float item_amt) {
		this.item_amt = item_amt;
	}

	public float getItem_rate() {
		return item_rate;
	}

	public void setItem_rate(float item_rate) {
		this.item_rate = item_rate;
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

	public int getMeasurement_id_fk() {
		return measurement_id_fk;
	}

	public void setMeasurement_id_fk(int measurement_id_fk) {
		this.measurement_id_fk = measurement_id_fk;
	}

	public int getBill_id_fk() {
		return bill_id_fk;
	}

	public void setBill_id_fk(int bill_id_fk) {
		this.bill_id_fk = bill_id_fk;
	}

	public int getItem_id_fk() {
		return item_id_fk;
	}

	public void setItem_id_fk(int item_id_fk) {
		this.item_id_fk = item_id_fk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMeasurement_name() {
		return measurement_name;
	}

	public void setMeasurement_name(String measurement_name) {
		this.measurement_name = measurement_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIn_date() {
		return in_date;
	}

	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public float getMin_limit() {
		return min_limit;
	}

	public void setMin_limit(float min_limit) {
		this.min_limit = min_limit;
	}

	public float getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(float item_quantity) {
		this.item_quantity = item_quantity;
	}

}
