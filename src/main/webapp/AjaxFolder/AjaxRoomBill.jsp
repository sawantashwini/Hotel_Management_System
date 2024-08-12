<%@page import="com.service.LogFileService"%>
<%@page import="com.service.RoomBookingService"%>
<%@page import="com.dto.RoomBookingDto"%>
<%@page import="com.dto.PaymentDto"%>
<%@page import="com.dto.OrderBillDto"%>
<%@page import="com.service.OrderService"%>
<%


RoomBookingDto dto = new RoomBookingDto();

//Integer Values
dto.setId(Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
dto.setUser_id_fk(
		Integer.parseInt(request.getParameter("user_id_fk") == null ? "0" : request.getParameter("user_id_fk")));
dto.setCust_id_fk(
		Integer.parseInt(request.getParameter("cust_id_fk") == null ? "0" : request.getParameter("cust_id_fk")));
dto.setBill_bank_id_fk(
		Integer.parseInt(request.getParameter("bank_id_fk") == null ? "0" : request.getParameter("bank_id_fk")));
dto.setRoom_id_fk(
		Integer.parseInt(request.getParameter("room_id_fk") == null ? "0" : request.getParameter("room_id_fk")));

//String Values
dto.setSession_year(request.getParameter("session_year") == null ? "" : request.getParameter("session_year"));
dto.setCheck_in_time(LogFileService.formatDateTimeForSql(
		request.getParameter("check_in_time") == null ? "" : request.getParameter("check_in_time")));
dto.setCheck_out_time(LogFileService.formatDateTimeForSql(
		request.getParameter("check_out_time") == null ? "" : request.getParameter("check_out_time")));
dto.setExtra_bed_days(LogFileService.formatDateTimeForSql(
		request.getParameter("extra_bed_days") == null ? "" : request.getParameter("extra_bed_days")));
dto.setInvoice_no(request.getParameter("invoice_no") == null ? "" : request.getParameter("invoice_no"));
dto.setBill_remark(request.getParameter("remark") == null ? "" : request.getParameter("remark"));
dto.setBill_payment_mode(request.getParameter("payment_mode") == null ? "" : request.getParameter("payment_mode"));
dto.setBill_online_date(request.getParameter("online_date") == null ? "" : request.getParameter("online_date"));
dto.setBill_online_remark(request.getParameter("online_remark") == null ? "" : request.getParameter("online_remark"));
dto.setBill_online_way(request.getParameter("online_way") == null ? "" : request.getParameter("online_way"));
dto.setRegular(request.getParameter("regular") == null ? "" : request.getParameter("regular"));
dto.setCust_name(request.getParameter("name") == null ? "" : request.getParameter("name"));
dto.setCust_mobile(request.getParameter("mobile_no") == null ? "" : request.getParameter("mobile_no"));
dto.setCust_address(request.getParameter("address") == null ? "" : request.getParameter("address"));
dto.setCust_gst_no(request.getParameter("gst_no") == null ? "" : request.getParameter("gst_no"));
dto.setCompany_name(request.getParameter("company_name") == null ? "" : request.getParameter("company_name"));
dto.setDob(request.getParameter("cust_dob") == null ? "" : request.getParameter("cust_dob"));
dto.setDoa(request.getParameter("cust_doa") == null ? "" : request.getParameter("cust_doa"));
//Float Values
dto.setBill_cash_amount(
		Float.parseFloat(request.getParameter("cash_amount") == null ? "0.00" : request.getParameter("cash_amount")));
dto.setBill_online_amount(Float
		.parseFloat(request.getParameter("online_amount") == null ? "0.00" : request.getParameter("online_amount")));
dto.setNo_of_days(
		Float.parseFloat(request.getParameter("no_of_days") == null ? "0.00" : request.getParameter("no_of_days")));
dto.setNo_of_bed_days(
		Float.parseFloat(request.getParameter("no_of_bed_days") == null ? "0.00" : request.getParameter("no_of_bed_days")));
dto.setTotal_room_rent(Float.parseFloat(
		request.getParameter("total_room_rent") == null ? "0.00" : request.getParameter("total_room_rent")));
dto.setRoom_gst_per(
		Float.parseFloat(request.getParameter("room_gst_per") == null ? "0.00" : request.getParameter("room_gst_per")));
dto.setTotal_room_amt_with_gst(Float.parseFloat(request.getParameter("total_room_amt_with_gst") == null ? "0.00"
		: request.getParameter("total_room_amt_with_gst")));
dto.setFood_gst_per(
		Float.parseFloat(request.getParameter("food_gst_per") == null ? "0.00" : request.getParameter("food_gst_per")));
dto.setNet_amt(Float.parseFloat(request.getParameter("net_amt") == null ? "0.00" : request.getParameter("net_amt")));
dto.setDis_amt_room(Float.parseFloat(request.getParameter("dis_amt_room") == null ? "0.00" : request.getParameter("dis_amt_room")));
dto.setDis_amt_food(Float.parseFloat(request.getParameter("dis_amt_food") == null ? "0.00" : request.getParameter("dis_amt_food")));
dto.setCust_old_due(
		Float.parseFloat(request.getParameter("cust_old_due") == null ? "0.00" : request.getParameter("cust_old_due")));
dto.setExtra_bed_amt(Float
		.parseFloat(request.getParameter("extra_bed_amt") == null ? "0.00" : request.getParameter("extra_bed_amt")));
dto.setTotal_extra_bed_amt(Float
		.parseFloat(request.getParameter("total_extra_bed_amt") == null ? "0.00" : request.getParameter("total_extra_bed_amt")));
dto.setFinal_amt(
		Float.parseFloat(request.getParameter("final_amt") == null ? "0.00" : request.getParameter("final_amt")));

// ************PAYMENT DTO VALUES***********
PaymentDto pay_dto = new PaymentDto();

// Integer
pay_dto.setBill_id_fk(Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
pay_dto.setBank_id_fk(
		Integer.parseInt(request.getParameter("bank_id_fk") == null ? "0" : request.getParameter("bank_id_fk")));
// Float
pay_dto.setOnline_amount(Float
		.parseFloat(request.getParameter("online_amount") == null || request.getParameter("online_amount") == "" ? "0.0"
		: request.getParameter("online_amount")));
pay_dto.setCash_amount(Float
		.parseFloat(request.getParameter("cash_amount") == null || request.getParameter("cash_amount") == "" ? "0.0"
		: request.getParameter("cash_amount")));
// String
pay_dto.setRemark(request.getParameter("remark") == null ? "0" : request.getParameter("remark"));
pay_dto.setOnline_remark(request.getParameter("online_remark") == null ? "0" : request.getParameter("online_remark"));
pay_dto.setOnline_date(request.getParameter("online_date") == null ? "" : request.getParameter("online_date"));
pay_dto.setOnline_way(request.getParameter("online_way") == null ? "" : request.getParameter("online_way"));
pay_dto.setIn_date(LogFileService.formatDateTimeForSql(
		request.getParameter("check_out_time") == null ? "" : request.getParameter("check_out_time")));
pay_dto.setStatus("Active");
pay_dto.setType("Room Billing");

// return amount logic
float return_amt = (dto.getBill_cash_amount() + dto.getBill_online_amount()) - dto.getFinal_amt();
if (return_amt > 0) {

	dto.setBill_return_amt(return_amt);

	// *** Cash
	if (dto.getBill_payment_mode().equalsIgnoreCase("cash")) {
		pay_dto.setCash_amount(pay_dto.getCash_amount() - return_amt);
		dto.setBill_cash_amount(dto.getBill_cash_amount() - return_amt);
	}
	// *** Online
	else if (dto.getBill_payment_mode().equalsIgnoreCase("online")) {
		pay_dto.setOnline_amount(pay_dto.getOnline_amount() - return_amt);
		dto.setBill_online_amount(dto.getBill_online_amount() - return_amt);
	}
	// *** Both
	else {
		pay_dto.setCash_amount(pay_dto.getCash_amount() - (return_amt / 2));
		dto.setBill_cash_amount(dto.getBill_cash_amount() - (return_amt / 2));

		pay_dto.setOnline_amount(pay_dto.getOnline_amount() - (return_amt / 2));
		dto.setBill_online_amount(dto.getBill_online_amount() - (return_amt / 2));
	}

}

String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");
RoomBookingService ser = new RoomBookingService();
boolean b = false;

if (dto.getId() != 0 && flag.equalsIgnoreCase("complete bill")) {
	b = ser.completeRoomBilling(pay_dto, dto, request, config);
} else if (dto.getId() != 0 && flag.equalsIgnoreCase("update bill")) {
	b = ser.updateRoomBilling(pay_dto, dto, request, config);

}
System.out.println(b);
%>
<input type="hidden" id="form_sub_rs" value="<%=b%>">