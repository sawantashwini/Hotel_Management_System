<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.service.* "%>



<%
int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
String type = request.getParameter("type") == null ? "" : request.getParameter("type");
int return_id = Integer.parseInt(request.getParameter("return_id") == null ? "0" : request.getParameter("return_id"));
String table_name = "";
String page_name = "";
//Common module

boolean delstatus = false;
DeleteService ser_main = new DeleteService();

if (type.equals("Bank")) {
	table_name = "bank_tb";
	page_name = "../manage_bank";
	delstatus = ser_main.deleteAjax(id, table_name, request, config);
	if (delstatus == true) {
		response.sendRedirect(page_name + ".jsp?msg=YesDel");
	} else {
		response.sendRedirect(page_name + ".jsp?msg=NoDel");
	}
}
if (type.equals("City")) {
	table_name = "city_tb";
	page_name = "../manage_city";
	delstatus = ser_main.deleteAjax(id, table_name, request, config);
	if (delstatus == true) {
		response.sendRedirect(page_name + ".jsp?msg=YesDel");
	} else {
		response.sendRedirect(page_name + ".jsp?msg=NoDel");
	}
}

if (type.equals("Table")) {
	table_name = "table_tb";
	page_name = "../manage_table";
	delstatus = ser_main.deleteAjax(id, table_name, request, config);
	if (delstatus == true) {
		response.sendRedirect(page_name + ".jsp?msg=YesDel");
	} else {
		response.sendRedirect(page_name + ".jsp?msg=NoDel");
	}

}

if (type.equals("Measurement")) {
	table_name = "measurement_tb";
	page_name = "../manage_measurement";
	delstatus = ser_main.deleteAjax(id, table_name, request, config);
	if (delstatus == true) {
		response.sendRedirect(page_name + ".jsp?msg=YesDel");
	} else {
		response.sendRedirect(page_name + ".jsp?msg=NoDel");
	}

}

if (type.equals("Ingredients_Purchase")) {
	delstatus = ser_main.deleteIngredientsPurchase(id, request, config);
	if (delstatus == true) {
		response.sendRedirect("../ingredients_purchase_report.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../ingredients_purchase_report.jsp?msg=NoDel");
	}
}

if (type.equals("Ingredients_Sale")) {
	delstatus = ser_main.deleteIngredientsSale(id, request, config);
	if (delstatus == true) {
	   response.sendRedirect("../ingredients_sale_report.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../ingredients_sale_report.jsp?msg=NoDel");
	} 
}

if (type.equals("Liquor_Purchase")) {
	delstatus = ser_main.deleteLiquorPurchase(id, request, config);
	if (delstatus == true) {
		response.sendRedirect("../liquor_purchase_report.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../liquor_purchase_report.jsp?msg=NoDel");
	}
}
//Delete customer_id
int customer_id = Integer.parseInt(request.getParameter("customer_id") == null ? "0" : request.getParameter("customer_id"));

if (customer_id > 0) {
	delstatus = ser_main.deleteCustomer(customer_id, request,config);
	if (delstatus == true) {
		response.sendRedirect("../manage_customer.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../manage_customer.jsp?msg=NoDel");
	}
}

//Delete customer_id
int room_bill_id = Integer.parseInt(request.getParameter("room_bill_id") == null ? "0" : request.getParameter("room_bill_id"));
int room_id = Integer.parseInt(request.getParameter("room_id") == null ? "0" : request.getParameter("room_id"));

if (room_bill_id > 0) {
	delstatus = ser_main.deleteRoom_bill(room_bill_id, room_id, request,config);
	if (delstatus == true) {
		if(return_id>0) response.sendRedirect("../manage_customer_all_bills.jsp?msg=YesDel&id="+return_id);
		else response.sendRedirect("../manage_room_bills.jsp?msg=YesDel");
	} else {
		if(return_id>0) response.sendRedirect("../manage_customer_all_bills.jsp?msg=YesDel&id="+return_id);
		else response.sendRedirect("../manage_room_bills.jsp?msg=YesDel");
	}
}

int order_bill_id = Integer.parseInt(request.getParameter("order_bill_id") == null ? "0" : request.getParameter("order_bill_id"));

if (order_bill_id > 0) {
	delstatus = ser_main.deleteOrder_bill(order_bill_id, request,config);
	if (delstatus == true) {
		response.sendRedirect("../manage_complete_bills.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../manage_room_bills.jsp?msg=NoDel");
	}
}


int kot_bill_id = Integer.parseInt(request.getParameter("kot_bill_id") == null ? "0" : request.getParameter("kot_bill_id"));

if (kot_bill_id > 0) {
	delstatus = ser_main.deleteKot_order_bill(kot_bill_id, request,config);
	if (delstatus == true) {
		response.sendRedirect("../manage_kot_bills.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../manage_kot_bills.jsp?msg=NoDel");
	}
}

//Delete customer_due_id

int customer_due_id = Integer.parseInt(request.getParameter("customer_due_id") == null ? "0" : request.getParameter("customer_due_id"));

if (customer_due_id > 0) {
	delstatus = ser_main.deleteCustomerDue(customer_due_id, request, config);
	if (delstatus == true) {
		response.sendRedirect("../manage_customer_due.jsp?msg=YesDel&id="+return_id);
	} else {
		response.sendRedirect("../manage_customer_due.jsp?msg=NoDel&id="+return_id);
	}
}

int room_book_id = Integer.parseInt(request.getParameter("room_book_id") == null ? "0" : request.getParameter("room_book_id"));
int cust_id = Integer.parseInt(request.getParameter("cust_id") == null ? "0" : request.getParameter("cust_id"));
if (room_book_id > 0) {
	delstatus = ser_main.deleteRoomBooking(room_book_id,cust_id, request, config);
	if (delstatus == true) {
		response.sendRedirect("../manage_room_booked.jsp?msg=YesDel");
	} else {
		response.sendRedirect("../manage_room_booked.jsp?msg=NoDel");
	}
}

System.out.println(delstatus);
%>
<input type="hidden" id="delstatus" value="<%=delstatus%>" />