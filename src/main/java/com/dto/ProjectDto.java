package com.dto;

public class ProjectDto {
	
	String name, mobile_no, address, gst_in, status, current_in_date, term_and_conditions, activation_date;

	int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGst_in() {
		return gst_in;
	}

	public void setGst_in(String gst_in) {
		this.gst_in = gst_in;
	}

	public String getCurrent_in_date() {
		return current_in_date;
	}

	public void setCurrent_in_date(String current_in_date) {
		this.current_in_date = current_in_date;
	}

	public String getTerm_and_conditions() {
		return term_and_conditions;
	}

	public void setTerm_and_conditions(String term_and_conditions) {
		this.term_and_conditions = term_and_conditions;
	}

	public String getActivation_date() {
		return activation_date;
	}

	public void setActivation_date(String activation_date) {
		this.activation_date = activation_date;
	}

}
