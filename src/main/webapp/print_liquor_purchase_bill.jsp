<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.dto.MsgDto"%>
<%@page import="com.service.MsgService"%>
<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.OrderItemDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.LiquorPurchaseBillDto"%>
<%@page import="com.service.LiquorPurchaseBillService"%>
<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>Liquor Bill</title>
<style>
a {
	text-decoration: none;
}

body {
	font-family: Arial, sans-serif;
	text-align: center;
}

.centered-container {
	width: 350px;
	margin: 0 auto;
}

.hotel-logo {
	max-width: 80px; /* Decreased logo width */
	float: left; /* Move logo to the left */
	margin-right: 20px; /* Add some space between logo and hotel info */
}

.hotel-logo img {
	width: 100%;
}

.hotel-info {
	display: flex;
	align-items: center;
	justify-content: center; /* Center-align hotel info */
	margin-bottom: 10px;
	/* Decreased margin between hotel info and customer details */
}

.hotel-details {
	text-align: left;
}

.hotel-name, .hotel-address, .hotel-mob {
	margin: 0; /* Remove default margins */
}

table {
	border-collapse: collapse;
	margin: 0 auto;
	width: 100%;
}

th, td {
	border: 1px solid black;
	padding: 4px 8px;
	/* text-align: left; */
	font-size: 12px;
	text-align: center;
}

.border-left {
	border-left: 1px solid black;
	border-right: none;
	border-top: none;
	border-bottom: none;
}

.border-top-none {
	border-top: none;
}

th {
	background-color: #f2f2f2;
}

.total-row {
	font-weight: bold;
}

h3 {
	font-size: 14px;
	margin: 6px;
}

.item_row td {
	border: none;
}

.item_row {
	/* border: 1px solid black; */
	border-left: 1px solid black;
	border-right: 1px solid black;
}

td, th {
	
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
	content: "\20B9"; /* Unicode character for the Rupee symbol (â¹) */
}

.cust-massage {
	font-size: 12px;
}

.align-left {
	text-align: left;
}

.input-width {
	width: 40%;
}

.height {
	height: 17px
}

.width {
	width: 45%
}

.amt-width {
	width: 18%;
}

.remark-width {
	width: 15%
}

.border-right {
	border-right: none;
}

/* Common styles */
.project-table {
	width: 100%;
	border-collapse: collapse;
}

.logo-container {
	width: 100%;
	font-size: 9px;
	color: #000;
	padding: 5px 5px 0;
	color: #000;
	text-align: right;
	border: none;
	padding-left: 3%;
}

.logo-img {
	width: 50px;
	height: 50px;
	border: none;
	padding-left: 5%;
}

.project-details {
	width: 100%;
	padding: 0;
	border: none;
}

.project-details span {
	margin-top: 5px;
}

.logo_th {
	border: none;
	/* position: absolute; */
	float: left;
	margin-right: 10px;
}
.address, .mobile-no {
	font-size: 12px;
	width: 100%;
	float: left;
}

.invoice-heading {
	font-size: 15px;
	width: 100%;
	float: left;
}

.project_name {
	font-size: 21px;
	width: 100%;
	float: left;
	color: #f00;
}

.project-table tr {
	border-left: 1px solid #000;
	border-right: 1px solid #000;
}
.align-right{
text-align: right;
}
</style>
</head>
<body>
	<div class="centered-container">
	<button class="print-button" id="print-button" onclick="printBill()">Print</button>
		<a class="home-button" id="home-button" href="liquor_purchase_report.jsp">Home</a>
<br><br>
		<%
		ProjectService project_ser = new ProjectService();
		ProjectDto project = project_ser.getProjectInfoById(1, config, request);
		%>
		<!-- Project Container -->
		<table class="project-table">
			<thead>
				<tr class="border-top">
					<!-- Logo Container -->
					<td class="logo-container" style="border-top: 1px solid #000"
						colspan="1"><b>GST-No.:</b> <%=project.getGst_in()%></td>

					<!-- Logo Image -->

				</tr>
				<tr>

					<!-- Project Details -->
					<td class="project-details">
					<div style="display: flex;">
					<div class="logo_th" style="width: 10%">
							<img class="logo-img"
								src="<%=new java.io.File(application.getRealPath("ProjectImage" + project.getId() + ".jpg")).exists()
		? "ProjectImage" + project.getId() + ".jpg"
		: "assets/img/logo.jpg"%>"
								alt="Logo" />
						</div> <div style="width: 80%"><span class="project_name"><b><%=project.getName()%></b></span> <span
						class="address">Address : <%=project.getAddress()%></span> <span
						class="mobile-no">Mob. : <%=project.getMobile_no()%></span> </div>
					</div>
					<span
						class="invoice-heading"><b> Liquor Invoice</b></span>
					</td>
				</tr>
			</thead>
		</table>




		<%
		NumberFormat format = new DecimalFormat("##.##");
		int bill_id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
		LiquorPurchaseBillService service = new LiquorPurchaseBillService();
		LiquorPurchaseBillDto dto = service.getPurchaseBillInfoById(bill_id, config, request);
		%>

		<!-- <h3>Customer Details</h3> -->
		<table>
			<tr>
				<th class="align-right"  style=" width: 10%;">Name:</th>
				<td class="align-left input-width"><%=dto.getDealer_name()%></td>
				<th class="align-right">Date:</th>
				<td class="align-left"><%=LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>

			</tr>

			<tr>

				<th class="align-right ">Mob.:</th>
				<td class="align-left input-width"><%=dto.getDealer_mobile_no()%></td>
				<th class="align-right">GST-In.:</th>
				<td class="align-left"><%=dto.getDealer_gst_in()%></td>

			</tr>
			<tr>

				<th class="align-right ">Add.:</th>
				<td colspan="3" class="align-left input-width"><%=dto.getDealer_address()%></td>
				
			</tr>

		</table>

		
		<%-- <!-- <h3>Customer Details</h3> -->
		<table style="min-width: 100%;">
			<tr>
				<th>Time:</th>
				<td class="align-left"><%=LogFileService.formatDateTime(dto.getCurrent_in_date())%></td>
				<th>Table:</th>
				<td class="align-left"><%=dto.getTable_name()%></td>
			</tr>
			<%
			if (!dto.getCust_name().equals("")) {
			%>
			<tr>
				<th>Name:</th>
				<td class="align-left"><%=dto.getCust_name()%></td>
				<th>Mob.:</th>
				<td class="align-left"><%=dto.getCust_mob_no()%></td>
			</tr>
			<tr>
				<th>Add.:</th>
				<td colspan="3" class="align-left"><%=dto.getCust_address()%></td>
			</tr>
			<%
			}
			%>
		</table>
 --%>

		<h3>Item Details</h3>
		<table>

			<tr>
				<th width="2%">S.No.</th>
				<th>Particular</th>
				<th width="10%">Rate</th>
				<th width="2%">Qty</th>
				<th width="10%">Amt</th>
			</tr>


<%
											ArrayList<LiquorPurchaseBillDto> list = service.getPurchaseBillItemInfoById(bill_id, config, request);

											int i = 1;
											float total_amt = 0, final_amt = 0;
											for (LiquorPurchaseBillDto item_dto : list) {
												total_amt = item_dto.getTotal_price() * item_dto.getQuantity();
										
											%>
			<%-- <%
			ArrayList<OrderItemDto> item_list = service.getAllOrderItemByOrderId(order_id, config, request);
			float total_amt = 0, final_amt = 0;
			for (OrderItemDto item_dto : item_list) {
				total_amt = item_dto.getItem_rate() * item_dto.getItem_qty();
				final_amt = final_amt + total_amt;
			%> --%>

			<tr class="item_row" style="height: 15px;">
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"><%=list.indexOf(item_dto) + 1%></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"><%=item_dto.getItem_name()%></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"><%=item_dto.getTotal_price()%></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"><%=item_dto.getQuantity()%></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"><%=total_amt%></td>
			</tr>
			<%
			}
			%>

			<tr class="item_row" height="20px;">
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"
					align="center"></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"
					align="center"></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"
					align="center"></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"
					align="center"></td>
				<td
					style="border-left: 1px solid #000; border-right: 1px solid #000;"
					align="center"></td>
			</tr>
			<%-- <%
			if (dto.getStatus().equalsIgnoreCase("complete")) {
			%>
			<tr class="total-row">
				<td colspan="4"><span class="right height">Total :</span></td>
				<td colspan="1"
					<%if (!dto.getStatus().equalsIgnoreCase("complete")) {%>
					style="border-bottom: none;" <%}%> class="align-left"><span
					class="rupee-symbol"></span><%=final_amt%></td>
			</tr>
			<%
			}
			if (dto.getOld_due_amount() > 0) {
			%>
			<tr class="total-row">
				<td colspan="4"><span class="right height">Old Due :</span></td>
				<td class="align-left"><span class="rupee-symbol"></span><%=dto.getOld_due_amount()%></td>
			</tr>
			<%
			}
			%>
			<%
			if (dto.getStatus().equalsIgnoreCase("complete")) {
				if (dto.getGst_status().equalsIgnoreCase("Yes")) {
			%>
			<tr class="total-row">
				<td colspan="4"><span class="right height">GST Amount
						(5%) :</span></td>
				<td colspan="1" class="align-left"><span class="rupee-symbol"></span><%=dto.getGst_amount()%></td>
			</tr>
			<%
			}
			}
			%>
			<%
			if (dto.getDiscount_amount() > 0) {
			%>
			<tr class="total-row">
				<td colspan="4"><span class="right height">Discount :</span></td>
				<td class="align-left"><span class="rupee-symbol"></span><%=dto.getDiscount_amount()%></td>
			</tr>
			<%
			}
			%> --%>
			<tr class="total-row">
				<td colspan="4"><span class="right height">Grand Total :</span></td>
				<td class="align-left"><span class="rupee-symbol"></span><%=dto.getTotal_amount()%></td>
			</tr>
		</table>



		<%-- <%
		if (dto.getStatus().equalsIgnoreCase("Complete")) {
		%>

		<h3>Payment Details</h3>

		<table>
			<tr class="total-row">
				<th>Mode</th>
				<th>Cash</th>
				<th>Online</th>
				<th>Paid</th>
			</tr>
			<tr class="total-row">
				<td style="border-bottom: none;"><%=dto.getPayment_mode()%></td>
				<td style="border-bottom: none;"><span class="rupee-symbol"></span>
					<%=dto.getCash_amount()%></td>
				<td style="border-bottom: none;"><span class="rupee-symbol"></span>
					<%=dto.getOnline_amount()%></td>
				<td style="border-bottom: none;"><span class="rupee-symbol"></span>
					<%=dto.getPaid_amount()%></td>
			</tr>

			<tr>
				<%
				if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
				%>
				<th colspan="2">On. Way</th>
				<th colspan="2">Date</th>
				<%
				}
				%>
			</tr>
			<tr>
				<%
				if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
				%>
				<td colspan="2"><%=dto.getOnline_way()%></td>
				<td colspan="2"><%=LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
				<%
				}
				%>
			</tr>
		</table>
		<%
		}
		%>

		<table>

			<%
			if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
			%>
			<%
			if (!dto.getOnline_remark().equalsIgnoreCase("N/A") && dto.getOnline_remark() != null) {
			%>
			<tr class="total-row">
				<th class="amt-width" style="border-top: none;" colspan="1">On.
					Remark:</th>
				<td colspan="6" style="border-top: none;"><span class="left"><%=dto.getOnline_remark()%></span></td>
			</tr>
			<%
			}
			%>
			<%
			}
			%> --%>
<table>
			<%
			if ( dto.getRemark() != null) {
			%>
			<tr class="total-row" style="border-top: none">
				<th class="amt-width align-right" colspan="1">Remark:</th>
				<td colspan="6"><span class="left"><%=dto.getRemark()%></span></td>
			</tr>
			<%
			}
			%>

		</table>
		<%-- <table>
			<tr class="total-row">
				<th class="amt-width align-left border-top-none">Amount In
					Words :</th>
				<td class="align-left border-top-none">
					<%
					if (dto.getPaid_amount() > 0) {
					%> <%=LogFileService.convertToWords(dto.getPaid_amount())%> <% } else {%> <%=LogFileService.convertToWords(final_amt)%> <% } %> Rupees Only
				</td>
			</tr>
		</table> --%>
		<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

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

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     