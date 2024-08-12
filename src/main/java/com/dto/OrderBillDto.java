package com.dto;

public class OrderBillDto {
		
		private int id,kot_id, cust_id_fk, cust_acc_id_fk, table_id, user_id_fk,cash_payment_id_fk,online_payment_id_fk,bank_id_fk;
		private float gst_amount, without_gst_amount, with_gst_amount, old_due_amount, final_amount, paid_amount,return_amount, discount_amount,cash_amount, online_amount;
		private String bill_date, online_date, current_in_date,manager_name,table_name;
		private String online_way, payment_mode,  online_remark, remark, session_year, status, gst_status;
		private String cust_type,cust_name,regular,cust_address,cust_mob_no,flag,company_name,gst_no,dob,doa;
		public String getCompany_name() {
			return company_name;
		}
		public void setCompany_name(String company_name) {
			this.company_name = company_name;
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
		public String getGst_no() {
			return gst_no;
		}
		public void setGst_no(String gst_no) {
			this.gst_no = gst_no;
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
		
		public int getKot_id() {
			return kot_id;
		}
		public void setKot_id(int kot_id) {
			this.kot_id = kot_id;
		}
		public void setCust_id_fk(int cust_id_fk) {
			this.cust_id_fk = cust_id_fk;
		}
		public int getCust_acc_id_fk() {
			return cust_acc_id_fk;
		}
		public void setCust_acc_id_fk(int cust_acc_id_fk) {
			this.cust_acc_id_fk = cust_acc_id_fk;
		}
		public int getTable_id() {
			return table_id;
		}
		public void setTable_id(int table_id) {
			this.table_id = table_id;
		}
		public int getUser_id_fk() {
			return user_id_fk;
		}
		public void setUser_id_fk(int user_id_fk) {
			this.user_id_fk = user_id_fk;
		}
		public float getGst_amount() {
			return gst_amount;
		}
		public void setGst_amount(float gst_amount) {
			this.gst_amount = gst_amount;
		}
		public float getWithout_gst_amount() {
			return without_gst_amount;
		}
		public void setWithout_gst_amount(float without_gst_amount) {
			this.without_gst_amount = without_gst_amount;
		}
		public float getWith_gst_amount() {
			return with_gst_amount;
		}
		public void setWith_gst_amount(float with_gst_amount) {
			this.with_gst_amount = with_gst_amount;
		}
		public float getOld_due_amount() {
			return old_due_amount;
		}
		public void setOld_due_amount(float old_due_amount) {
			this.old_due_amount = old_due_amount;
		}
		public float getFinal_amount() {
			return final_amount;
		}
		public void setFinal_amount(float final_amount) {
			this.final_amount = final_amount;
		}
		public float getPaid_amount() {
			return paid_amount;
		}
		public void setPaid_amount(float paid_amount) {
			this.paid_amount = paid_amount;
		}
		public float getReturn_amount() {
			return return_amount;
		}
		public void setReturn_amount(float return_amount) {
			this.return_amount = return_amount;
		}
		public float getDiscount_amount() {
			return discount_amount;
		}
		public void setDiscount_amount(float discount_amount) {
			this.discount_amount = discount_amount;
		}
		public float getCash_amount() {
			return cash_amount;
		}
		public void setCash_amount(float cash_amount) {
			this.cash_amount = cash_amount;
		}
		public float getOnline_amount() {
			return online_amount;
		}
		public void setOnline_amount(float online_amount) {
			this.online_amount = online_amount;
		}
		public String getBill_date() {
			return bill_date;
		}
		public void setBill_date(String bill_date) {
			this.bill_date = bill_date;
		}
		public String getOnline_date() {
			return online_date;
		}
		public void setOnline_date(String online_date) {
			this.online_date = online_date;
		}
		public String getCurrent_in_date() {
			return current_in_date;
		}
		public void setCurrent_in_date(String current_in_date) {
			this.current_in_date = current_in_date;
		}
		public String getManager_name() {
			return manager_name;
		}
		public void setManager_name(String manager_name) {
			this.manager_name = manager_name;
		}
		public String getTable_name() {
			return table_name;
		}
		public void setTable_name(String table_name) {
			this.table_name = table_name;
		}
		public String getCust_type() {
			return cust_type;
		}
		public void setCust_type(String cust_type) {
			this.cust_type = cust_type;
		}
		public String getPayment_mode() {
			return payment_mode;
		}
		public void setPayment_mode(String payment_mode) {
			this.payment_mode = payment_mode;
		}
		public String getOnline_remark() {
			return online_remark;
		}
		public void setOnline_remark(String online_remark) {
			this.online_remark = online_remark;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getSession_year() {
			return session_year;
		}
		public void setSession_year(String session_year) {
			this.session_year = session_year;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getGst_status() {
			return gst_status;
		}
		public void setGst_status(String gst_status) {
			this.gst_status = gst_status;
		}
		public String getOnline_way() {
			return online_way;
		}
		public void setOnline_way(String online_way) {
			this.online_way = online_way;
		}
		public int getCash_payment_id_fk() {
			return cash_payment_id_fk;
		}
		public void setCash_payment_id_fk(int cash_payment_id_fk) {
			this.cash_payment_id_fk = cash_payment_id_fk;
		}
		public int getOnline_payment_id_fk() {
			return online_payment_id_fk;
		}
		public void setOnline_payment_id_fk(int online_payment_id_fk) {
			this.online_payment_id_fk = online_payment_id_fk;
		}
		public String getCust_name() {
			return cust_name;
		}
		public void setCust_name(String cust_name) {
			this.cust_name = cust_name;
		}
		public String getRegular() {
			return regular;
		}
		public void setRegular(String regular) {
			this.regular = regular;
		}
		public String getCust_address() {
			return cust_address;
		}
		public void setCust_address(String cust_address) {
			this.cust_address = cust_address;
		}
		public String getCust_mob_no() {
			return cust_mob_no;
		}
		public void setCust_mob_no(String cust_mob_no) {
			this.cust_mob_no = cust_mob_no;
		}
		public int getBank_id_fk() {
			return bank_id_fk;
		}
		public void setBank_id_fk(int bank_id_fk) {
			this.bank_id_fk = bank_id_fk;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		
		
		
}
