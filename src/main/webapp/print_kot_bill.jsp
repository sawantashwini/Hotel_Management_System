<%@page import="com.dto.MsgDto"%>
<%@page import="com.service.MsgService"%>
<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.OrderItemDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.OrderBillDto"%>
<%@page import="com.service.OrderService"%>
<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>Hotel Bill</title>
<style>
a {
	text-decoration: none;
}

body {
	font-family: Arial, sans-serif;
	text-align: center;
}

.centered-container {
	max-width: 450px;
	margin: 0 auto;
}

table {
	border-collapse: collapse;
	margin: 0 auto;
	width: 85%;
}

th, td {
	border: 1px solid black;
	padding: 4px 8px;
	/* text-align: left; */
	font-size: 12px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

h3 {
	font-size: 14px;
	margin: 6px;
}

.right {
	float: right;
	padding-right: 3px;
}

.left {
	float: left;
	padding-left: 3px;
}

.print-button {
	background: #ff3f00;
	color: #fff;
	border: none;
	padding: 6px 15px;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
	margin-top: 20px;
}

.print-button:hover {
	background: #f03c02;
}

.home-button {
	background: #4c4846;
	color: #fff;
	border: none;
	padding: 6px 15px;
	border-radius: 5px;
	font-size: 18px;
	cursor: pointer;
	margin-top: 20px;
}

.home-button:hover {
	background: #373534;
}

.rupee-symbol::before {
	content: "\20B9"; /* Unicode character for the Rupee symbol (₹) */
}

.align-left {
	text-align: left;
}

.align-center {
	text-align: center;
}

.align-right {
	text-align: right;
}

.height {
	height: 15px
}

.max-width {
	max-width: 10%;
}

.fit-width {
	width: fit-content;
}

.input-width {
	width: 50%;
}

.border-all {
	border-right: 1px solid black;
	border-left: 1px solid black;
	border-top: 1px solid black;
	border-bottom: 1px solid black;
}

.border-none {
	border: none;
}

.border-right-none {
	border-right: none;
}

.border-left-none {
	border-left: none;
}

.border-top-none {
	border-top: none;
}

.border-bottom-none {
	border-bottom: none;
}

.border-right {
	border-right: 1px solid black;
}

.border-left {
	border-left: 1px solid black;
}

.border-top {
	border-top: 1px solid black;
}

.border-bottom {
	border-bottom: 1px solid black;
}

.total-row {
	font-weight: bold;
}

.remark-width {
	width: 15%
}

.amt-width {
	width: 27%;
}

.mb {
	margin-bottom: 10px;
	margin-top: 10px;
	height: 38px;
}

.align-left-none {
	text-align: left !important;
}

.align-left-width {
	width: 5%;
}

.td-width {
	width: 20%;
}

.align {
	text-align: right ! important;
}
</style>
</head>
<body>
	<div class="centered-container">

<button class="print-button" id="print-button" onclick="printBill()">Print</button>
		<a class="home-button" id="home-button" href="manage_kot_bills.jsp">Home</a>
		<br><br>

		<%
		int kot_id = Integer.parseInt(request.getParameter("kot_id") == null ? "0" : request.getParameter("kot_id"));
		OrderService service = new OrderService();
		OrderBillDto dto = service.getKotOrderInfoById(kot_id, config, request);
		%>


		<!-- <h3>Customer Details</h3> -->
		<table>
			<tr>
				<th class="align-right" width="20%">Bill Time:</th>
				<td class="align-left"><%=LogFileService.formatDateTime(dto.getCurrent_in_date())%></td>
				<th class="align-right" width="20%">Table:</th>
				<td class="align-left" width="20%" ><%=dto.getTable_name()%></td>
			</tr>

		</table>


		<h3>Item Details</h3>
		<table>

			<tr>
				<th style="width: 70%">Particular</th>
				<th style="width: 10%">Qty</th>
			</tr>



			<%
			ArrayList<OrderItemDto> item_list = service.getKotItemByKotId(kot_id, config, request);
			float total_amt = 0, final_amt = 0;
			for (OrderItemDto item_dto : item_list) {
				total_amt = item_dto.getItem_rate() * item_dto.getItem_qty();
				final_amt = final_amt + total_amt;
			%>

			<tr class="item_row" style="border-bottom: 1px solid black;">
				<td><%=item_dto.getItem_name()%></td>
				<td><%=item_dto.getItem_qty()%></td>
			</tr>
			<%
			}
			%>



		</table>
		
		<p class="cust-massage">
			<%
			MsgService ser_msg = new MsgService();
			MsgDto dto_msg = ser_msg.getMsgInfoById(1, config, request);
			%>
			<%
			if (dto_msg.getName() != null) {
			%>
			<%=dto_msg.getName()%>
			<%
			} else {
			%>
			<%
			}
			%>
		</p>
		
	</div>

	<script>
		function printBill() {
			// Hide the print button
			var button = document.getElementById("print-button");
			button.style.display = "none";
			var homebutton = document.getElementById("home-button");
			homebutton.style.display = "none";

			// Print the document
			window.print();

			// Show the print button again after printing
			button.style.display = "";
			homebutton.style.display = "";
		}
	</script>
</body>
</html>

