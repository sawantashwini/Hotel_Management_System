package com.dto;

public class ReciepeRelationDto {
	int id, user_id_fk, menu_id_fk, ingredient_id_fk;
	String menu_name , current_in_date, status, ingredient_name;
    public String getIngredient_name() {
		return ingredient_name;
	}
	public void setIngredient_name(String ingredient_name) {
		this.ingredient_name = ingredient_name;
	}
	float  reciepe_ratio;
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
	public int getMenu_id_fk() {
		return menu_id_fk;
	}
	public void setMenu_id_fk(int menu_id_fk) {
		this.menu_id_fk = menu_id_fk;
	}
	public int getIngredient_id_fk() {
		return ingredient_id_fk;
	}
	public void setIngredient_id_fk(int ingredient_id_fk) {
		this.ingredient_id_fk = ingredient_id_fk;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public float getReciepe_ratio() {
		return reciepe_ratio;
	}
	public void setReciepe_ratio(float reciepe_ratio) {
		this.reciepe_ratio = reciepe_ratio;
	}
}
