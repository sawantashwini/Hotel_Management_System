package com.dto;

public class RoomBookingDto {
	
	int id,cust_id_fk,room_id_fk,user_id_fk,ad_bank_id_fk,
	ad_cash_payment_id_fk,ad_online_payment_id_fk,ad_customer_acc_id_fk;
	
	float room_rent,advanced_paid_amt,ad_cash_amount,ad_online_amount,extra_bed_amt,total_extra_bed_amt,food_amt,cust_old_due,bill_time_old_due,bill_return_amt;
	
	String session_year,regular,room_type,extra_bed,cust_name,cust_mobile,cust_address,cust_gst_no,dob,doa,
	company_name,source,destination,room_no,room_name,check_in_time,check_out_time,extra_bed_days,
	current_in_date,status,book_remark,ad_payment_mode,ad_online_date,ad_online_remark,ad_online_way;
	
	public String getRoom_name() {
		return room_name;
	}
	public String getExtra_bed_days() {
		return extra_bed_days;
	}
	public void setExtra_bed_days(String extra_bed_days) {
		this.extra_bed_days = extra_bed_days;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	int bill_bank_id_fk,bill_customer_acc_id_fk,bill_cash_payment_id_fk,bill_online_payment_id_fk;
	
	float no_of_days,no_of_bed_days,total_room_rent,room_gst_per,total_room_amt_with_gst,food_gst_per,net_amt, 
	dis_amt_room,dis_amt_food,final_amt,bill_paid_amt,bill_cash_amount,bill_online_amount;
	
	public float getNo_of_bed_days() {
		return no_of_bed_days;
	}
	public void setNo_of_bed_days(float no_of_bed_days) {
		this.no_of_bed_days = no_of_bed_days;
	}
	String invoice_no,bill_remark,bill_payment_mode,bill_online_date,bill_online_remark,bill_online_way, bill_bank_name, Ad_bank_name; 

	public String getBill_bank_name() {
		return bill_bank_name;
	}
	public void setBill_bank_name(String bill_bank_name) {
		this.bill_bank_name = bill_bank_name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoa() {
		return doa;
	}
	public void setDoa(String doa) {
		this.doa = doa;
	}
	public String getAd_bank_name() {
		return Ad_bank_name;
	}
	public void setAd_bank_name(String ad_bank_name) {
		Ad_bank_name = ad_bank_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCust_id_fk() {
		return cust_id_fk;
	}
	public void setCust_id_fk(int cust_id_fk) {
		this.cust_id_fk = cust_id_fk;
	}
	public int getRoom_id_fk() {
		return room_id_fk;
	}
	public void setRoom_id_fk(int room_id_fk) {
		this.room_id_fk = room_id_fk;
	}
	public int getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(int user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public int getAd_bank_id_fk() {
		return ad_bank_id_fk;
	}
	public void setAd_bank_id_fk(int ad_bank_id_fk) {
		this.ad_bank_id_fk = ad_bank_id_fk;
	}
	public int getAd_cash_payment_id_fk() {
		return ad_cash_payment_id_fk;
	}
	public void setAd_cash_payment_id_fk(int ad_cash_payment_id_fk) {
		this.ad_cash_payment_id_fk = ad_cash_payment_id_fk;
	}
	public int getAd_online_payment_id_fk() {
		return ad_online_payment_id_fk;
	}
	public void setAd_online_payment_id_fk(int ad_online_payment_id_fk) {
		this.ad_online_payment_id_fk = ad_online_payment_id_fk;
	}
	public int getAd_customer_acc_id_fk() {
		return ad_customer_acc_id_fk;
	}
	public void setAd_customer_acc_id_fk(int ad_customer_acc_id_fk) {
		this.ad_customer_acc_id_fk = ad_customer_acc_id_fk;
	}
	public float getRoom_rent() {
		return room_rent;
	}
	public void setRoom_rent(float room_rent) {
		this.room_rent = room_rent;
	}
	public float getAdvanced_paid_amt() {
		return advanced_paid_amt;
	}
	public void setAdvanced_paid_amt(float advanced_paid_amt) {
		this.advanced_paid_amt = advanced_paid_amt;
	}
	public float getAd_cash_amount() {
		return ad_cash_amount;
	}
	public void setAd_cash_amount(float ad_cash_amount) {
		this.ad_cash_amount = ad_cash_amount;
	}
	public float getAd_online_amount() {
		return ad_online_amount;
	}
	public void setAd_online_amount(float ad_online_amount) {
		this.ad_online_amount = ad_online_amount;
	}
	public float getExtra_bed_amt() {
		return extra_bed_amt;
	}
	public void setExtra_bed_amt(float extra_bed_amt) {
		this.extra_bed_amt = extra_bed_amt;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getExtra_bed() {
		return extra_bed;
	}
	public void setExtra_bed(String extra_bed) {
		this.extra_bed = extra_bed;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public String getCust_address() {
		return cust_address;
	}
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}
	public String getCust_gst_no() {
		return cust_gst_no;
	}
	public void setCust_gst_no(String cust_gst_no) {
		this.cust_gst_no = cust_gst_no;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getCheck_in_time() {
		return check_in_time;
	}
	public void setCheck_in_time(String check_in_time) {
		this.check_in_time = check_in_time;
	}
	public String getCheck_out_time() {
		return check_out_time;
	}
	public void setCheck_out_time(String check_out_time) {
		this.check_out_time = check_out_time;
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
	public String getBook_remark() {
		return book_remark;
	}
	public void setBook_remark(String book_remark) {
		this.book_remark = book_remark;
	}
	public String getAd_online_date() {
		return ad_online_date;
	}
	public void setAd_online_date(String ad_online_date) {
		this.ad_online_date = ad_online_date;
	}
	public String getAd_online_remark() {
		return ad_online_remark;
	}
	public void setAd_online_remark(String ad_online_remark) {
		this.ad_online_remark = ad_online_remark;
	}
	public String getAd_online_way() {
		return ad_online_way;
	}
	public void setAd_online_way(String ad_online_way) {
		this.ad_online_way = ad_online_way;
	}
	public String getAd_payment_mode() {
		return ad_payment_mode;
	}
	public void setAd_payment_mode(String ad_payment_mode) {
		this.ad_payment_mode = ad_payment_mode;
	}
	public String getRegular() {
		return regular;
	}
	public void setRegular(String regular) {
		this.regular = regular;
	}
	public String getSession_year() {
		return session_year;
	}
	public void setSession_year(String session_year) {
		this.session_year = session_year;
	}
	public float getFood_amt() {
		return food_amt;
	}
	public void setFood_amt(float food_amt) {
		this.food_amt = food_amt;
	}
	public float getCust_old_due() {
		return cust_old_due;
	}
	public void setCust_old_due(float cust_old_due) {
		this.cust_old_due = cust_old_due;
	}
	public int getBill_bank_id_fk() {
		return bill_bank_id_fk;
	}
	public void setBill_bank_id_fk(int bill_bank_id_fk) {
		this.bill_bank_id_fk = bill_bank_id_fk;
	}
	public int getBill_customer_acc_id_fk() {
		return bill_customer_acc_id_fk;
	}
	public void setBill_customer_acc_id_fk(int bill_customer_acc_id_fk) {
		this.bill_customer_acc_id_fk = bill_customer_acc_id_fk;
	}
	public int getBill_cash_payment_id_fk() {
		return bill_cash_payment_id_fk;
	}
	public void setBill_cash_payment_id_fk(int bill_cash_payment_id_fk) {
		this.bill_cash_payment_id_fk = bill_cash_payment_id_fk;
	}
	public int getBill_online_payment_id_fk() {
		return bill_online_payment_id_fk;
	}
	public void setBill_online_payment_id_fk(int bill_online_payment_id_fk) {
		this.bill_online_payment_id_fk = bill_online_payment_id_fk;
	}
	public float getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(float no_of_days) {
		this.no_of_days = no_of_days;
	}
	public float getTotal_room_rent() {
		return total_room_rent;
	}
	public void setTotal_room_rent(float total_room_rent) {
		this.total_room_rent = total_room_rent;
	}
	public float getRoom_gst_per() {
		return room_gst_per;
	}
	public void setRoom_gst_per(float room_gst_per) {
		this.room_gst_per = room_gst_per;
	}
	public float getTotal_room_amt_with_gst() {
		return total_room_amt_with_gst;
	}
	public void setTotal_room_amt_with_gst(float total_room_amt_with_gst) {
		this.total_room_amt_with_gst = total_room_amt_with_gst;
	}
	public float getFood_gst_per() {
		return food_gst_per;
	}
	public void setFood_gst_per(float food_gst_per) {
		this.food_gst_per = food_gst_per;
	}
	public float getNet_amt() {
		return net_amt;
	}
	public void setNet_amt(float net_amt) {
		this.net_amt = net_amt;
	}
	
	public float getTotal_extra_bed_amt() {
		return total_extra_bed_amt;
	}
	public void setTotal_extra_bed_amt(float total_extra_bed_amt) {
		this.total_extra_bed_amt = total_extra_bed_amt;
	}
	public float getDis_amt_room() {
		return dis_amt_room;
	}
	public void setDis_amt_room(float dis_amt_room) {
		this.dis_amt_room = dis_amt_room;
	}
	public float getDis_amt_food() {
		return dis_amt_food;
	}
	public void setDis_amt_food(float dis_amt_food) {
		this.dis_amt_food = dis_amt_food;
	}
	public float getFinal_amt() {
		return final_amt;
	}
	public void setFinal_amt(float final_amt) {
		this.final_amt = final_amt;
	}
	public float getBill_paid_amt() {
		return bill_paid_amt;
	}
	public void setBill_paid_amt(float bill_paid_amt) {
		this.bill_paid_amt = bill_paid_amt;
	}
	public float getBill_cash_amount() {
		return bill_cash_amount;
	}
	public void setBill_cash_amount(float bill_cash_amount) {
		this.bill_cash_amount = bill_cash_amount;
	}
	public float getBill_online_amount() {
		return bill_online_amount;
	}
	public void setBill_online_amount(float bill_online_amount) {
		this.bill_online_amount = bill_online_amount;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getBill_remark() {
		return bill_remark;
	}
	public void setBill_remark(String bill_remark) {
		this.bill_remark = bill_remark;
	}
	public String getBill_payment_mode() {
		return bill_payment_mode;
	}
	public void setBill_payment_mode(String bill_payment_mode) {
		this.bill_payment_mode = bill_payment_mode;
	}
	public String getBill_online_date() {
		return bill_online_date;
	}
	public void setBill_online_date(String bill_online_date) {
		this.bill_online_date = bill_online_date;
	}
	public String getBill_online_remark() {
		return bill_online_remark;
	}
	public void setBill_online_remark(String bill_online_remark) {
		this.bill_online_remark = bill_online_remark;
	}
	public String getBill_online_way() {
		return bill_online_way;
	}
	public void setBill_online_way(String bill_online_way) {
		this.bill_online_way = bill_online_way;
	}
	public float getBill_return_amt() {
		return bill_return_amt;
	}
	public void setBill_return_amt(float bill_return_amt) {
		this.bill_return_amt = bill_return_amt;
	}
	public float getBill_time_old_due() {
		return bill_time_old_due;
	}
	public void setBill_time_old_due(float bill_time_old_due) {
		this.bill_time_old_due = bill_time_old_due;
	}
		
}
