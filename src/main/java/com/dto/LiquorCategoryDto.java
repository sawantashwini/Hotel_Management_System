package com.dto;

public class LiquorCategoryDto {
	int id, user_id_fk, measurement_id_fk;
	String name, status, current_in_date, measure_name, user_name;
	public String getMeasure_name() {
		return measure_name;
	}
	public void setMeasure_name(String measure_name) {
		this.measure_name = measure_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	float capacity, min_qty, quantity;
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
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
	public float getCapacity() {
		return capacity;
	}
	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}
	public float getMin_qty() {
		return min_qty;
	}
	public void setMin_qty(float min_qty) {
		this.min_qty = min_qty;
	}
}
