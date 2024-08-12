<!DOCTYPE html>

<%@page import="com.service.RoomService"%>
<%@page import="com.dto.OrderItemDto"%>
<%@page import="com.dto.RoomBookingDto"%>
<%@page import="com.service.RoomBookingService"%>
<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@page import="javax.transaction.xa.XAResource"%>
<%@page import="com.service.LogFileService"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>

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
	width: 21%;
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
	font-size: 9px;
	color: #000;
	padding: 5px 5px 0;
	color: #000;
	text-align: right;
	border: none;
	padding-left: 1%;
}

.logo-img {
	width: 50px;
	height: 50px;
	border: none;
	padding-left: 5%;
	margin-top: 7%;
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
	<%
	NumberFormat format = new DecimalFormat("##.##");

		int bill_id = Integer.parseInt(request.getParameter("bill_id") == null ? "0" : request.getParameter("bill_id"));
		RoomBookingService service = new RoomBookingService();
		RoomBookingDto dto = service.getBookedRoomInfoById(bill_id, config, request);
		%>



	<div class="centered-container">

<button class="print-button" id="print-button" onclick="printBill()">Print</button>
		<a class="home-button" id="home-button" href="manage_room_bills.jsp">Home</a>
		<br><br>
		<div style="float: left; width: 100%;">
			<div style="float: left; width: 100%;">

				<!-- Project Container -->
				<table class="project-table">
					<thead>
						<tr class="border-top">
						<%if (dto.getFood_gst_per() > 0) {%>
							<!-- Logo Container -->
							<td class="logo-container" colspan="1"><b>GST IN:</b> <%=project_head_dto.getGst_in()%>
							</td>
	<%}%>
							<!-- Logo Image -->

						</tr>
						<tr>
							<!-- Project Details -->
							<td class="project-details">
					<div style="display: flex;">
					<div class="logo_th" style="width: 10%">
							<img class="logo-img"
								src="<%=new java.io.File(application.getRealPath("ProjectImage" + project_head_dto.getId() + ".jpg")).exists()
		? "ProjectImage" + project_head_dto.getId() + ".jpg"
		: "assets/img/logo.jpg"%>"
								alt="Logo" />
						</div> <div style="width: 80%"><span class="project_name"><b><%=project_head_dto.getName()%></b></span> <span
						class="address">Address : <%=project_head_dto.getAddress()%></span> <span
						class="mobile-no">Mob. : <%=project_head_dto.getMobile_no()%></span> </div>
					</div>
					<span
						class="invoice-heading"><b>Food Invoice </b></span>
					</td>
							
						</tr>
					</thead>
				</table>

				<table>
					<tr>
					<th class="align-right" style="width: 11%">Name :</th>
						<td class="input-width  align-left"><%=dto.getCust_name()%></td>
						<th class="align-right" style="width: 14%">Invoice :</th>
						<td class="align-left">F<%=dto.getInvoice_no()%></td>
						
					</tr>

					<tr>
					<%if (dto.getFood_gst_per() > 0) {%>
						<th class="align-right">Add. :</th>
						<td  class="input-width align-left"><%=dto.getCust_address()%></td>
						<%}else{%><th class="align-right">Mobile :</th>
						<td class="input-width align-left"><%=dto.getCust_mobile()%></td>
					
						<%}%>
						<th class="align-right">Room No. :</th>
						<td class="input-width align-left"><%=dto.getRoom_no()%></td>
					</tr>

					<tr> 	<%if (dto.getFood_gst_per() > 0) {%>
					<th class="align-right">GST :</th>
						<td class="input-width align-left"><%=dto.getCust_gst_no()%></td>
						<th class="align-right">Mobile :</th>
						<td class="input-width align-left"><%=dto.getCust_mobile()%></td>
						<%}else{%>
						<th class="align-right">Add. :</th>
						<td colspan="3"  class="input-width align-left"><%=dto.getCust_address()%></td><%}%>
					</tr>

				</table>
			</div>







			<table>

				<tr>
					<td colspan="5" style="border: none;"><b>Food order
							Details</b></td>
				</tr>

				<tr>
					<th>S.No</th>
					<th style="width: 40%;">Particular</th>
					<th style="width: 20%;">Date</th>
					<th>Price</th>
					<th>Qty</th>
					<th>Total </th>
				</tr>





				<%
				RoomService ser_room = new RoomService();
				ArrayList<OrderItemDto> list_order_item = ser_room.getRoomOrderItemInfoByRoomId(bill_id, config, request);
				for (OrderItemDto dto_item : list_order_item) {
				%>
				<tr class="item_row">
					<td align="center" class="border-bottom-none"><%=list_order_item.indexOf(dto_item) + 1%></td>

					<td class="align-left border-bottom-none"><%=dto_item.getItem_name()%></td>
					<td class="align-left border-bottom-none"><%=LogFileService.changeFormate(dto_item.getOrder_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
					<td align="center" class="border-bottom-none"><%=dto_item.getItem_rate()%></td>

					<td align="center" class="border-bottom-none"><%=dto_item.getItem_qty()%></td>
					<%
					float total_price = dto_item.getItem_rate() * dto_item.getItem_qty();
					%>
					<td align="center" class="border-bottom-none"><%=total_price%></td>
				</tr>

				<%
				}
				%>
				<tr class="item_row border-none border-bottom" style="height: 30vh;">
					<td class="border-none border-left"></td>
					<td class="border-none border-left"></td>
					<td class="border-none border-left"></td>
					<td class="border-none border-left"></td>
					<td class="border-none border-left"></td>
					<td class="border-none  border-left border-right" ></td>
				</tr>
				
				<tr class="total-row" >
					<td colspan="4"><span class="right height">Total Food Amount </span></td>
					
					<td colspan="2" class="align-left"><span class="rupee-symbol"></span> <b><%=format.format(dto.getFood_amt())%></b></td>
				</tr>
				
				<%if(dto.getFood_gst_per()!=0){ %>
				
				<tr class="total-row">
					<td colspan="4"><span class="right height">CGST  (<%=format.format(dto.getFood_gst_per()/ 2)%>%)</span></td>
					
					<td colspan="2" class="align-left" ><span class="rupee-symbol"></span> <b><%=format.format((dto.getFood_amt()*dto.getFood_gst_per()/100)/2)%></b></td>
				</tr>
				<tr class="total-row">
					<td colspan="4"><span class="right height">SGST  (<%=format.format(dto.getFood_gst_per()/ 2)%>%)</span></td>
					
					<td colspan="2" class="align-left" ><span class="rupee-symbol"></span> <b><%=format.format((dto.getFood_amt()*dto.getFood_gst_per()/100)/2)%></b></td>
				</tr>
			
				<tr class="total-row">
					<td colspan="4"><span class="right height"> Total Food Amount (With GST) </span></td>
					
					<td colspan="2" class="align-left"><span class="rupee-symbol"></span> <b><%=format.format((dto.getFood_amt()*dto.getFood_gst_per()/100)+dto.getFood_amt())%></b></td>
				</tr>
				<%} %>
	<%
			if (dto.getDis_amt_food() > 0) {
			%>
			<tr class="total-row">
				<td colspan="4"><span class="right height">Discount :</span></td>
				<td colspan="2" class="align-left"><span class="rupee-symbol"></span><%=dto.getDis_amt_food()%></td>
			</tr>
			
			<tr class="total-row">
					<td colspan="4"><span class="right height"> Total  Amount  </span></td>
					
					<td colspan="2" class="align-left"><span class="rupee-symbol"></span> <b><%=format.format(((dto.getFood_amt()*dto.getFood_gst_per()/100)+dto.getFood_amt())-dto.getDis_amt_food())%></b></td>
				</tr>
				<%
			}
			%>
			</table>
			
			
			
			

			<table>
				<tr class="total-row">
					<th class="amt-width border-top-none align-right" >Amount In Words :
					</th>
					<td class="border-top-none align-left"><%=LogFileService.convertToWords((dto.getFood_amt()*dto.getFood_gst_per()/100)+dto.getFood_amt())%> Ruppees Only</span></td>
				</tr>
			</table>
		</div>

		
		<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
		<!--
		<script src="js/toword.js"></script> <script type="text/javascript">
			
			$().ready(
					function() {
						NumToWord(
		
			,
								'WordsAmount1');
					});
		</script> -->

		<!-- <!-- <p class="cust-massage">Thank you for choosing our hotel. We hope
			you had a pleasant stay and look forward to serving you again in the
			future.</p> -->

		
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
			prebutton.style.display = "";
		}
	</script>


</body>
</html>
