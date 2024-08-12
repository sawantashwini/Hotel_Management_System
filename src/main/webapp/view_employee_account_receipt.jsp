

<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.EmployeeAccountDto"%>
<%@page import="com.service.EmployeeAccountService"%>
<%@page import="java.io.File"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@include file="UserTrack.jsp"%> 
<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.EmployeeSalaryDto"%>
<%@page import="com.service.EmployeeSalaryService"%>
<%@page import="java.io.File"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>
<html>
<head>
<%
ProjectService project_header_ser = new ProjectService();
ProjectDto project_dto = project_header_ser.getProjectInfoById(1, config, request);
%>
<title><%=project_dto.getName()%></title>
<link href="ProjectImage/<%=project_dto.getId()%>.jpg" rel="icon" />

<script>
	window.location.hash = "no-back-button";
	window.location.hash = "Again-no-back-button";//for google chrome
	window.onhashchange = function() {
		window.location.hash = "no-back-button";
	};
</script>
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

		
				<%
					NumberFormat format = new DecimalFormat("#");
					int value = Integer.parseInt(request.getParameter("value") == null ? "0" : request.getParameter("value"));

					if (value == 1) {
						String filename = "word.doc";
						File filepath = new File(filename);
						response.setContentType("application/vnd.ms-word");
						response.setHeader("Content-Disposition", "inline; filename=" + filepath);
					}

					String amountmode = request.getParameter("amountmode");
					String msg = request.getParameter("msg");
					int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
					EmployeeAccountService service = new EmployeeAccountService();

					EmployeeAccountDto dto = service.getEmployeeAccountInfoById(id, config, request);
					%>
		<div
			style="float: left; width: 448px; border: 1px solid; border-bottom: none;">




			<div
				style="width: 100%; float: left; font-size: 20px; color: #000; text-align: center;">

				<div style="width: 20%; float: left; font-size: 19px;">
					<img src="ProjectImage/<%=project_dto.getId()%>.jpg"
						onerror="this.src='assets/img/logo.png'"
						style="float: left; width: 80px;">
					<div style="float: left; width: 100%;">

						<div
							style="width: 100%; float: left; font-size: 11px; text-align: right; color: #000; padding: 0px 5px 0; display: none;">
							<b>Mobile -</b>9826607332
						</div>
					</div>
				</div>

				<div style="width: 60%; float: left; font-size: 21px;">




					<span
						style="font-size: 21px; width: 100%; float: left; margin-top: 5px;">
						<b style="color: #f00;"><%=project_dto.getName()%></b>
					</span> <span
						style="font-size: 13px; width: 100%; float: left; margin-top: 5px;">
						Address : <%=project_dto.getAddress()%></span> <span
						style="font-size: 13px; width: 100%; float: left; margin-top: 5px;">
						Mob. : <%=project_dto.getMobile_no()%></span> <span
						style="font-size: 15px; width: 100%; float: left; margin-top: 10px;"><b>Employee
							Account Reciept </b> </span>


				</div>



			</div>


		</div>



		<!-- <h3>Customer Details</h3> -->
		<table>
			<tr>
				<th class="align-left">Name:</th>
				<td class="align-left width"><%=dto.getEmployee_name()%></td>
				<th class="align-left width">Reciept No.:</th>
				<td class="input-width align-left">E-<%=id%></td>

			</tr>

			<tr>

				<th class="align-right">Qualification.:</th>
				<td class="align-left width"><%=dto.getQualification()%></td>
				<th class="align-left width">Mobile No.:</th>
				<td class="input-width align-left"><%=dto.getMobile_no()%></td>

			</tr>
			<tr>

				<th class="align-left ">Address.:</th>
				<td class="align-left width" colspan="3"><%=dto.getAddress()%></td>

			</tr>

		</table>



		<h3>Employee Salary Details</h3>

		<table>

			<tr>
				<th>Amount Mode</th>
				<%
				if (dto.getDebit_amount() > 0) {
				%>
				<th>Debit Amount</th>
				<%
				}
				if (dto.getCredit_amount() > 0) {
				%>
				<th>Credit Amount</th>
				<%
				}
				%>

			</tr>



			<tr class="item_row">
				<td align="center"><%=amountmode%></td>
				<%
				if (dto.getDebit_amount() > 0) {
				%>
				<td align="center"><%=dto.getDebit_amount()%></td>
				<%
				}
				if (dto.getCredit_amount() > 0) {
				%>
				<td align="center"><%=dto.getCredit_amount()%></td>
				<%
				}
				%>
			</tr>

		</table>
		
		<h3>Paid Amount Details</h3>


		<table>
			<tr class="total-row" style="height: 35px;">
				<th class="amt-width">Paid Amount</th>
				<td><span class="left"><%=dto.getCash_amount() + dto.getOnline_amount()%></span></td>
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
				<td><%=dto.getPayment_mode()%></td>
				<td><span class="rupee-symbol"></span><%=dto.getCash_amount()%></td>
				<td><span class="rupee-symbol"></span><%=dto.getOnline_amount()%></td>
				<td><span class="rupee-symbol"></span><%=dto.getCash_amount() + dto.getOnline_amount()%></td>


				<%
				if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
				%>

				<td><%=dto.getOnline_way()%></td>
				<td><%=LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
				<%
				}
				%>
			</tr>
		</table>
		
		
		<table>
		
		<%
			if (dto.getPayment_mode().equalsIgnoreCase("Online") || dto.getPayment_mode().equalsIgnoreCase("Both")) {
			%>
			<%
			if (!dto.getOnline_remark().equalsIgnoreCase("N/A") && dto.getOnline_remark() != null) {
			%>
			<tr class="total-row">
				<th class="amt-width align-left">On. Remark:</th>
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
				<th class="amt-width align-left">Remark:</th>
				<td colspan="6"><span class="left"><%=dto.getRemark()%></span></td>

			</tr>
			<%
			}
			%>
			<tr class="total-row">
				<th class="amt-width align-left" style="border-top: none;">Amount in
					Words:</th>
				<td style="border-top: none;"><span class="left"
					id="WordsAmount1"></span><span class="left">Rupees Only</span></td>
			</tr>
		</table>
		<script type="text/javascript">
			$().ready(function() {
				NumToWord(
		<%=format.format(dto.getAmount())%>
			, 'WordsAmount1');
			});
		</script>


		<p class="cust-massage">This is computer
							generated receipt , no signature required..</p>

		<button class="print-button" id="print-button" onclick="printBill()">Print</button>
		<a class="home-button" id="home-button" href="index.jsp">Home</a>

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