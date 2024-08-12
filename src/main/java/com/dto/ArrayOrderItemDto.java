package com.dto;

public class ArrayOrderItemDto {
	
	String[] id,table_id,order_id_fk,item_id_fk,
	item_qty,item_rate,
	item_name,item_code,current_in_date,order_status;

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getTable_id() {
		return table_id;
	}

	public void setTable_id(String[] table_id) {
		this.table_id = table_id;
	}

	public String[] getOrder_id_fk() {
		return order_id_fk;
	}

	public void setOrder_id_fk(String[] order_id_fk) {
		this.order_id_fk = order_id_fk;
	}

	public String[] getItem_id_fk() {
		return item_id_fk;
	}

	public void setItem_id_fk(String[] item_id_fk) {
		this.item_id_fk = item_id_fk;
	}

	public String[] getItem_qty() {
		return item_qty;
	}

	public void setItem_qty(String[] item_qty) {
		this.item_qty = item_qty;
	}

	public String[] getItem_rate() {
		return item_rate;
	}

	public void setItem_rate(String[] item_rate) {
		this.item_rate = item_rate;
	}

	public String[] getItem_name() {
		return item_name;
	}

	public void setItem_name(String[] item_name) {
		this.item_name = item_name;
	}

	public String[] getItem_code() {
		return item_code;
	}

	public void setItem_code(String[] item_code) {
		this.item_code = item_code;
	}

	public String[] getCurrent_in_date() {
		return current_in_date;
	}

	public void setCurrent_in_date(String[] current_in_date) {
		this.current_in_date = current_in_date;
	}

	public String[] getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String[] order_status) {
		this.order_status = order_status;
	}
	
}
