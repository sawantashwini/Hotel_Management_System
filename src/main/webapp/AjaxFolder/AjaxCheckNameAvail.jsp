<%@page import="com.service.CheckNameAvailService"%>
<%@page import="com.dto.* "%>

<%
int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
String name = request.getParameter("name") == null ? "" : request.getParameter("name");
String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");
String class_name = (request.getParameter("class_name") == null ? "" : request.getParameter("class_name"));
String last_line = (request.getParameter("last_line") == null ? "" : request.getParameter("last_line"));
String icon_name = (request.getParameter("icon_name") == null ? "" : request.getParameter("icon_name"));

String table_name = "";
String field_name = "";

if (flag.equals("Ingredients")) {
	table_name = "ingredients_item_tb";
	field_name = "name";
}
if (flag.equals("Measurement")) {
	table_name = "measurement_tb";
	field_name = "name";
}

if (flag.equals("City")) {
	table_name = "city_tb";
	field_name = "name";
}

if (flag.equals("Spend Head")) {
	table_name = "spend_head_tb";
	field_name = "name";
}
 
if (flag.equals("Income Head")) {
	table_name = "income_head_tb";
	field_name = "name";
}
if (flag.equals("Bank")) {
	table_name = "bank_tb";
	field_name = "name";
}
if (flag.equals("Table")) {
	table_name = "table_tb";
	field_name = "name";
}

if (flag.equals("Menu")) {
	table_name = "menu_item_detail_tb";
	field_name = "item_code";
}

if (flag.equals("Room")) {
	table_name = "room_tb";
	field_name = "room_no";
}

if (flag.equals("Mobile")) {
	table_name = "user_personal_info_tb";
	field_name = "mobile_no";
}

if (flag.equals("Customer")) {
	table_name = "customer_info_tb";
	field_name = "name";
}

if (flag.equals("Dealer")) {
	table_name = "dealer_info_tb";
	field_name = "name";
}

boolean check_status = false;

CheckNameAvailService ser = new CheckNameAvailService();
check_status = ser.checkAvail(name, table_name, field_name,  id, request, config);

if (check_status) {
%>
<script type="text/javascript">
warningAlert(`This <%=flag%> Already Exist.`);
</script>
<input id="check_name" type="hidden" value="<%=check_status%>" />
<%
}
%>
