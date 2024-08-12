<!DOCTYPE html>

<%-- <%@page import="com.dto.PurchaseBillItemDto"%> --%>
<%@page import="com.dto.MsgDto"%>
<%@page import="com.service.MsgService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.RoomBookingDto"%>
<%@page import="com.service.RoomBookingService"%>
<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="javax.transaction.xa.XAResource"%>
<html>
<head>
<%
ProjectService project_head_ser = new ProjectService();
ProjectDto project_head_dto = project_head_ser.getProjectInfoById(1, config, request);
%>



<title><%=project_head_dto.getName()%></title>

<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<style>
a {
	text-decoration: none;
}

body {
	font-family: Arial, sans-serif;
	text-align: center;
}

.centered-container {
	width: 620px;
	margin: 0 auto;
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

.item_row_side {
	border-left: 1px solid black;
	border-right: 1px solid black;
}

.item_row_side td {
	border-top: none;
}

.item_row td {
	border-bottom: none;
}

.item_row {
	border-bottom: none;
	/* border: 1px solid black; */
	border-left: 1px solid black;
	border-right: 1px solid black;
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
	width: 47%;
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

/* Common styles */
.project-table {
	width: 100%;
	border-collapse: collapse;
}

.logo-container {
	width: 100%;
	font-size: 13px;
	color: #000;
	padding: 5px 5px 0;
	color: #000;
	text-align: right;
	border: none;
	padding-left: 6%;
}

.logo-img {
	width: 75px;
	height: 75px;
	border: none;
	padding-left: 50%;
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
	position: absolute;
	float: left;
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
</style>

</head>
<body>
	<div class="centered-container">
		<div>
			<button class="print-button" id="print-button" onclick="printBill()">Print</button>

			<a target="_blank" class="home-button" id="home-button"
				href="manage_room_bills.jsp">Home</a>
		</div>
		<br>
		<%
		NumberFormat format = new DecimalFormat("##.##");

		String Gst = request.getParameter("sadsafsaffdfsafdsadfsa") == null
				? ""
				: request.getParameter("sadsafsaffdfsafdsadfsa");

		int bill_id = Integer.parseInt(request.getParameter("bill_id") == null ? "0" : request.getParameter("bill_id"));
		RoomBookingService service = new RoomBookingService();
		RoomBookingDto dto = service.getBookedRoomInfoById(bill_id, config, request);
		float room_gst_amt = 0, grand_total = 0;
		room_gst_amt = ((dto.getTotal_room_rent() + dto.getExtra_bed_amt()) * dto.getRoom_gst_per()) / 100;
		grand_total = (dto.getTotal_room_amt_with_gst()) - (dto.getDis_amt_room());
		%>

		<!-- Project Container -->
		<table class="project-table">
			<thead>
				<tr class="border-top">
					<!-- Logo Container -->
					<%
					if (dto.getRoom_gst_per() > 0) {
					%>
					<td class="logo-container" colspan="1"><b>GST No.:</b> <%=project_head_dto.getGst_in()%>
					</td>
					<%
					}
					%>
					<!-- Logo Image -->

				</tr>
				<tr>
					<!-- Project Details -->
					<td class="project-details"><div class="logo_th">
							<img class="logo-img"
								<%String imageFileName = "ProjectImage/" + project_head_dto.getId() + ".jpg";
if (new java.io.File(application.getRealPath(imageFileName)).exists()) {%>
								src="<%=imageFileName%>" <%} else {%> src="assets/img/logo.jpg"
								<%}%> alt="Logo" />
						</div> <span class="project_name"><b><%=project_head_dto.getName()%></b></span>
						<span class="address">Address : <%=project_head_dto.getAddress()%></span>
						<span class="mobile-no">Mob. : <%=project_head_dto.getMobile_no()%></span>
						<span class="invoice-heading"><b> Invoice</b></span></td>
				</tr>
			</thead>
		</table>

		<!-- <h3>Customer Details</h3> -->
		<table>
			<tbody>
				<tr>
					<th class="align-right" style="width: 10%;">Name:</th>
					<td class="input-width align-left" width="60%"><%=dto.getCust_name()%></td>
					<th class="align-right " style="width: 19%;">Invoice:</th>
					<td class="align-left">R<%=dto.getInvoice_no()%></td>

				</tr>

				<tr>
					<%
					if (dto.getRoom_gst_per() > 0) {
					%>
					<th class="align-right">GstIn:</th>
					<td class="input-width align-left"><%=dto.getCust_gst_no()%></td>
					<%
					} else {
					%>
					<th class="align-right">Room No.</th>
					<td class="input-width align-left"><b><%=dto.getRoom_no()%></b></td>
					<%
					}
					%>
					<th class="align-right">No. of Persons:</th>
					<%
					String[] room_type = {"Single Person", "Two Person", "Three Person", "Fourth Person"};
					int[] no_of_persons = {1, 2, 3, 4};
					int persons = 0;
					int i = 0; // Move the initialization outside the loop

					for (String type : room_type) {
						if (dto.getRoom_type().equalsIgnoreCase(type)) {
							persons = no_of_persons[i];
							break; // Exit the loop once a match is found
						}
						i++; // Increment i for the next iteration
					}
					%>
					<td class="align-left"><%=persons%></td>

				</tr>

				<tr>
					<th class="align-right">Company:</th>
					<td class="input-width align-left"><%=dto.getCompany_name()%></td>
					<th class="align-right">Check In:</th>
					<td class="align-left"><%=LogFileService.formatDateTime(dto.getCheck_in_time())%></td>


				</tr>

				<tr>

					<th class="align-right">Mob.:</th>
					<td class="input-width align-left"><%=dto.getCust_mobile()%></td>
					<th class="align-right">Check Out:</th>
					<td class="align-left"><%=LogFileService.formatDateTime(dto.getCheck_out_time())%></td>
				</tr>

				<tr>
					<th class="align-right">Source:</th>
					<td class="input-width align-left"><%=dto.getSource()%></td>
					<th class="align-right">Destination.:</th>
					<td class="align-left"><%=dto.getDestination()%></td>

				</tr>
				<tr>
					<%
					if (dto.getRoom_gst_per() > 0) {
					%>
					<th class="align-right">Add.:</th>
					<td class="input-width align-left"><%=dto.getCust_address()%></td>

					<th class="align-right">Room No.</th>
					<td class="align-left"><b><%=dto.getRoom_no()%></b></td>
					<%
					} else {
					%>
					<th class="align-right">Add.:</th>
					<td colspan="3" class="align-left"><%=dto.getCust_address()%></td>
					<%
					}
					%>
				</tr>

			</tbody>

		</table>


		<h3>Room Details</h3>

		<table style="min-height: 650px;">
			<tbody>
				<tr style="height: 30px">
					<th style="width: 25%;">Particular</th>

					<th>Rent</th>
					<!-- <th>Extra Bed Amt (Per day)</th> -->
					<th>No. of Days</th>
					<th>Amount</th>
				</tr>



				<tr class="item_row" style="height: 30px">
					<td align="left"><%=dto.getRoom_name()%></td>


					<td align="center"><%=dto.getRoom_rent()%></td>
					<%-- 	<td align="center"><%=dto.getExtra_bed_amt()%></td> --%>
					<td align="center"><%=dto.getNo_of_days()%></td>
					<td align="center"><%=dto.getTotal_room_rent()%></td>
				</tr>
				<%if (dto.getExtra_bed_amt()>0){ %>
				<tr class="item_row" style="height: 30px">
					<td align="left">Extra Bed Amt</td>
					<td align="center"><%=dto.getExtra_bed_amt()%></td>
					<td align="center"><%=dto.getNo_of_bed_days()%></td>
					<td align="center"><%=dto.getTotal_extra_bed_amt()%></td>
				</tr>
				<%} %>
				<tr class="item_row_side">
					<td align="center"></td>
					<td align="center"></td>
					<td align="center"></td>
					<!-- <td align="center"></td> -->
					<td align="center"></td>
				</tr>


				<tr class="total-row" style="height: 30px">
					<td colspan="3"><span class="right height">Total Amount
					</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <%=format.format(dto.getTotal_room_rent() + dto.getTotal_extra_bed_amt())%></td>
				</tr>


				<%
				//	if(!bill_dto.getIgst_status().equalsIgnoreCase("yes")){

				if (dto.getRoom_gst_per() != 0) {
				%>

				<tr class="total-row" style="height: 30px">
					<td colspan="3"><span class="right">CGST (<%=format.format(dto.getRoom_gst_per() / 2)%>%)
					</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <%=format.format(room_gst_amt / 2)%></td>

				</tr>
				<tr class="total-row" style="height: 30px">
					<td colspan="3"><span class="right">SGST (<%=format.format(dto.getRoom_gst_per() / 2)%>%)
					</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <%=format.format(room_gst_amt / 2)%></td>

				</tr>


				<tr class="total-row" style="height: 30px">
					<td colspan="3"><span class="right height">Grand Total
							Amount</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <%=format.format(dto.getTotal_room_amt_with_gst())%></td>

				</tr>
				<%
				}
				%>
				<%-- <%
				if (dto.getCust_old_due() != 0) {
				%>
				<tr class="total-row" style="height: 30px">
					<td colspan="4"><span class="right height">Old Due</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <%=format.format(dto.getCust_old_due())%></td>
				</tr>
				<%
				}
				%> --%>



				<%
				if (dto.getDis_amt_room() > 0) {
				%>
				<tr class="total-row" style="height: 30px">
					<td colspan="3"><span class="right height"> Discount
							Amount</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <b><%=format.format(dto.getDis_amt_room())%></b></td>
				</tr>
				<%
				}
				%>

				<tr class="total-row" style="height: 30px">
					<td colspan="3"><span class="right height"> Grand Total</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <b><%=format.format(grand_total)%></b></td>
				</tr>
				<%-- <tr class="total-row" style="height: 30px">
					<td colspan="4"><span class="right height">Advance
							Amount</span></td>
					<td class="align-left"><span class="rupee-symbol"></span> <%=format.format(dto.getAdvanced_paid_amt())%></td>
				</tr> --%>
			</tbody>
		</table>
		<table style="height: 12vh;">
			<tr>
				<th class="align-right" style="width: 14%; border-top: none">Guest
					Sign:</th>
				<td class="align-left" style="border-top: none; width: 38%;"></td>
				<th class=" align-right" style="width: 17%; border-top: none">Manager
					Sign:</th>
				<td class="align-left" style="border-top: none"></td>
			</tr>
		</table>



		<%-- 	<h3>Payment Details</h3>

		<table>
		<tbody>
			<tr class="total-row">
				<th>Mode</th>
				<%
				if (dto.getBill_payment_mode().equalsIgnoreCase("Cash") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				%>
				<th>Cash</th>

				<%
				}
				if (dto.getBill_return_amt() > 0) {
				%>
				<th>Return</th>

				<%
				}
				if (dto.getBill_payment_mode().equalsIgnoreCase("Online") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				%>
				<th>Online</th>
				<%
				}
				%>
				<th>Paid</th>
				<%
				if (dto.getBill_payment_mode().equalsIgnoreCase("Online") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				%>
				<th>On. Way</th>
				<th>On Date</th>
				<%
				}
				%>
			</tr>
			<tr class="total-row">
				<td><%=dto.getBill_payment_mode()%></td>
				<%
				if (dto.getBill_payment_mode().equalsIgnoreCase("Cash") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				%>
				<td><span class="rupee-symbol"></span><%=dto.getBill_cash_amount()%></td>

				<%
				}
				if (dto.getBill_payment_mode().equalsIgnoreCase("Online") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				%>
				<td><span class="rupee-symbol"></span><%=dto.getBill_online_amount()%></td>

				<%
				}
				%>
				<td><span class="rupee-symbol"></span><%=dto.getBill_paid_amt()%></td>
				<%
				if (dto.getBill_payment_mode().equalsIgnoreCase("Online") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
				%>
				<td><%=dto.getBill_online_way()%></td>
				<td><%=LogFileService.changeFormate(dto.getBill_online_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
				<%
				}
				%>
			</tr>
			</tbody>
			
			<tfoot>

			<%
			if (dto.getBill_payment_mode().equalsIgnoreCase("Online") || dto.getBill_payment_mode().equalsIgnoreCase("Both")) {
			%>
			<%
			if (!dto.getBill_online_remark().equalsIgnoreCase("N/A") && dto.getBill_online_remark() != null) {
			%>
			<tr class="total-row">
				<th class="amt-width align-right">On. Remark :</th>
				<td colspan="5"><span class="left"><%=dto.getBill_online_remark()%></span></td>
			</tr>
			
			<%
			}
			%>
			<%
			}
			%>
			<tr class="total-row">
				<th class="amt-width align-right" class="border-top-none" >Amount In Words :</th>
				<td class="align-left" class="border-top-none" colspan="5"><%=LogFileService.convertToWords(grand_total)%>
					Rupees Only</td>
			</tr> --%>
		<!-- </tfoot>
			
		</table> -->
		<script src="js/jquery-3.6.0.min.js"></script>

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
