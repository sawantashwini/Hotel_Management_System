<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.MsgDto"%>
<%@page import="com.service.MsgService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@page import="com.dto.DealerDto"%>
<%@page import="com.service.DealerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>Dealer due receipt</title>
<style>
a {
	text-decoration: none;
}

body {
	font-family: Arial, sans-serif;
	text-align: center;
}

.centered-container {
	width: 420px;
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
	width: 29%;
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
}a
</style>

</head>
<body>
	<div class="centered-container">

		<button class="print-button" id="print-button" onclick="printBill()">Print</button>
		<a class="home-button" id="home-button" href="manage_dealer.jsp">Home</a>
		<br><br>
		<%
		ProjectService project_ser = new ProjectService();
		ProjectDto project = project_ser.getProjectInfoById(1, config, request);
		%>
		<%
			NumberFormat format = new DecimalFormat("##.##");

			String Gst = request.getParameter("sadsafsaffdfsafdsadfsa") == null
					? ""
					: request.getParameter("sadsafsaffdfsafdsadfsa");

			int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
			DealerService service = new DealerService();
			DealerDto dto = service.getDealerDueInfoById(id, config, request);

			
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
						class="invoice-heading"><b> Invoice</b></span>
					</td>
				</tr>
			</thead>
		</table>


		<!-- <h3>Customer Details</h3> -->
		<table>
			<tr>
				<th class="align-right">Name:</th>
				<td class="align-left input-width"><%=dto.getName()%></td>
				<th class="align-right">Date:</th>
				<td class="align-left"><%=LogFileService.changeFormate(dto.getPay_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>

			</tr>

			<tr>

				<th class="align-right ">Mob.:</th>
				<td class="align-left input-width"><%=dto.getMobile_no()%></td>
				<th class="align-right">Rec. no.:</th>
				<td class="align-left">R<%=id%></td>

			</tr>
			<tr>

				<th class="align-right ">Add.:</th>
				<td class="align-left input-width"><%=dto.getAddress()%></td>
				<th class="align-right">GST-In.:</th>
				<td class="align-left"><%=dto.getGst_no()%></td>
			</tr>

		</table>

		<table>
			<tr class="total-row" style="height: 40px;">
				<th class="amt-width">Paid Amount</th>
				<td><span class="left"><%=dto.getCash_amount()+dto.getOnline_amount()%></span></td>
			</tr>
		</table>


		<h3>Payment Details</h3>

		<table>
			<tr class="total-row">
				<th>Mode:</th>
				<th>Cash:</th>
				<th>Online:</th>
				<th>Total:</th>


				<%
				if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
				%>
				<th>On. Way :</th>
				<th>On. Date:</th>
				<%
				}
				%>
			</tr>
			<tr class="total-row">
				<td><%=dto.getPayment_mode() %></td>
				<td><span class="rupee-symbol"></span><%=dto.getCash_amount()%></td>
				<td><span class="rupee-symbol"></span><%=dto.getOnline_amount()%></td>
				<td><span class="rupee-symbol"></span><%=dto.getCash_amount()+dto.getOnline_amount()%></td>


				<%
				if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
				%>

				<td><%=dto.getOnline_way()%></td>
				<td><%=LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
				<%
				}
				%>
			</tr>

			<%
			if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
			%>
			<%
			if (!dto.getOnline_remark().equalsIgnoreCase("N/A") && dto.getOnline_remark() != null) {
			%>
			<tr class="total-row">
				<th>On. Remark:</th>
				<td colspan="6"><span class="left"><%=dto.getOnline_remark()%></span></td>
			</tr>
			<%
			}
			%>
			<%
			}
			%>

			<%
			if (!dto.getRemark().equalsIgnoreCase("N/A") && dto.getRemark() != null) {
			%>
			<tr class="total-row">
				<th class="remark-width">Remark:</th>
				<td colspan="6"><span class="left"><%=dto.getRemark()%></span></td>

			</tr>
			<%
			}
			%>

		</table>
		<table>
			<tr class="total-row">
				<th class="amt-width" style="border-top: none;">Amount in
					Words:</th>
				<td style="border-top: none;"><span class="left"
					id="WordsAmount1"></span><span class="left">Rupees Only</span></td>
			</tr>
		</table>
		<%-- Note:<%=project.getTerm_and_conditions()%> --%>
		<script src="js/toword.js"></script>
		<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

	
		<script>
			$().ready(function() {
				<%-- var aAmount = <%=Math.round(bill_dto.getTotal_amt_with_gst())%>;

				if (!isNaN(aAmount)) {
					aAmount = parseFloat(aAmount);
					var aVarText = "Rs. " + NumToWord(aAmount) + " Only.";
					document.getElementById("WordsAmount1").innerHTML = aVarText;
					//document.getElementById("WordsAmount2").innerHTML = aVarText;
				} --%>
				NumToWord(<%=Math.round(dto.getCash_amount()+dto.getOnline_amount())%>,'WordsAmount1');
				
			});
			</script>


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

