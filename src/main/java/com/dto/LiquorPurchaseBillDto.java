package com.dto;

public class LiquorPurchaseBillDto {
	int id, user_id_fk, dealer_id_fk, dealer_account_id_fk, bill_id_fk, item_id_fk;
	String in_date, current_in_date, item_code, item_name, status, remark, dealer_name,dealer_mobile_no,dealer_address,dealer_gst_in;
	public String getDealer_mobile_no() {
		return dealer_mobile_no;
	}
	public void setDealer_mobile_no(String dealer_mobile_no) {
		this.dealer_mobile_no = dealer_mobile_no;
	}
	public String getDealer_address() {
		return dealer_address;
	}
	public void setDealer_address(String dealer_address) {
		this.dealer_address = dealer_address;
	}
	public String getDealer_gst_in() {
		return dealer_gst_in;
	}
	public void setDealer_gst_in(String dealer_gst_in) {
		this.dealer_gst_in = dealer_gst_in;
	}
	public String getDealer_name() {
		return dealer_name;
	}
	public void setDealer_name(String dealer_name) {
		this.dealer_name = dealer_name;
	}
	public int getDealer_account_id_fk() {
		return dealer_account_id_fk;
	}
	public void setDealer_account_id_fk(int dealer_account_id_fk) {
		this.dealer_account_id_fk = dealer_account_id_fk;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	float total_amount, quantity, price,  total_price;
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
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
	public int getDealer_id_fk() {
		return dealer_id_fk;
	}
	public void setDealer_id_fk(int dealer_id_fk) {
		this.dealer_id_fk = dealer_id_fk;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
}
