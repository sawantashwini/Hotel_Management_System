package com.dto;

public class RoomDto {
	int id,  user_id_fk, book_id_fk;
	String room_no, room_name ,status, current_in_date;
	float rent, rent_double, rent_three, rent_fourth;
	public int getBook_id_fk() {
		return book_id_fk;
	}
	public void setBook_id_fk(int book_id_fk) {
		this.book_id_fk = book_id_fk;
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
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
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
	public float getRent() {
		return rent;
	}
	public void setRent(float rent) {
		this.rent = rent;
	}
	public float getRent_double() {
		return rent_double;
	}
	public void setRent_double(float rent_double) {
		this.rent_double = rent_double;
	}
	public float getRent_three() {
		return rent_three;
	}
	public void setRent_three(float rent_three) {
		this.rent_three = rent_three;
	}
	public float getRent_fourth() {
		return rent_fourth;
	}
	public void setRent_fourth(float rent_fourth) {
		this.rent_fourth = rent_fourth;
	}

}
