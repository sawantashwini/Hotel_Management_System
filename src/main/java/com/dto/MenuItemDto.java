package com.dto;

public class MenuItemDto {
	int id, user_id_fk, liqour_cat_id_fk;
	 String item_code, item_name,current_in_date, status, liquor_cat_name;
    public String getLiquor_cat_name() {
		return liquor_cat_name;
	}
	public void setLiquor_cat_name(String liquor_cat_name) {
		this.liquor_cat_name = liquor_cat_name;
	}
	public int getLiqour_cat_id_fk() {
		return liqour_cat_id_fk;
	}
	public void setLiqour_cat_id_fk(int liqour_cat_id_fk) {
		this.liqour_cat_id_fk = liqour_cat_id_fk;
	}
	float  item_price, liqour_ind_qty;
	public float getLiqour_ind_qty() {
		return liqour_ind_qty;
	}
	public void setLiqour_ind_qty(float liqour_ind_qty) {
		this.liqour_ind_qty = liqour_ind_qty;
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
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
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
	public float getItem_price() {
		return item_price;
	}
	public void setItem_price(float item_price) {
		this.item_price = item_price;
	}
}
