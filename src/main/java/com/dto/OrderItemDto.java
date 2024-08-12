package com.dto;

public class OrderItemDto {
	
	int id,table_id,room_id,order_id_fk,bill_id_fk,item_id_fk;
	float item_qty,item_rate;
	String item_name,item_code,current_in_date,order_date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getTable_id() {
		return table_id;
	}
	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}
	public int getOrder_id_fk() {
		return order_id_fk;
	}
	public void setOrder_id_fk(int order_id_fk) {
		this.order_id_fk = order_id_fk;
	}
	public int getItem_id_fk() {
		return item_id_fk;
	}
	public void setItem_id_fk(int item_id_fk) {
		this.item_id_fk = item_id_fk;
	}
	public float getItem_qty() {
		return item_qty;
	}
	public void setItem_qty(float item_qty) {
		this.item_qty = item_qty;
	}
	public float getItem_rate() {
		return item_rate;
	}
	public void setItem_rate(float item_rate) {
		this.item_rate = item_rate;
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
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public int getBill_id_fk() {
		return bill_id_fk;
	}
	public void setBill_id_fk(int bill_id_fk) {
		this.bill_id_fk = bill_id_fk;
	}
	
	
}
