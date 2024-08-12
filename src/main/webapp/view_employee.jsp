<%@page import="com.service.LogFileService"%>
<%@page import="com.dto.EmployeeDto"%>
<%@page import="com.service.EmployeeService"%>
<%@page import="java.io.File"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@include file="UserTrack.jsp"%>
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

th {
	background-color: #f2f2f2;
}

.total-row {
	font-weight: bold;
}

h3 {
	font-size: 16px;
	margin: 6px;
}

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
	content: "\20B9"; /* Unicode character for the Rupee symbol (₹) */
}

.cust-massage {
	font-size: 12px;
}

.align-left {
	text-align: left;
}

.input-width {
	width: 20%;
}

.height {
	height: 15px
}

.width {
	width: fit-content;
}

.widths {
	width: 30%
}

.amt-width {
	width: 30%
}

.remark-width {
	width: 15%
}

.border-right {
	border-right: none;
}

.border-bottom {
	border-bottom: 1px solid black;
}

.align-right {
	text-align: right;
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

		String msg = request.getParameter("msg");
		int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
		EmployeeService service = new EmployeeService();

		EmployeeDto dto = service.getEmployeeInfoById(id, config, request);
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
							Details </b> </span>


				</div>



			</div>


		</div>

		<table>
			<tr>
				<th class="align-right" width="20%">Name:</th>
				<td class="align-left" width="50%"><%=dto.getName()%></td>
				<th class="align-right" width="15%">Emp Id.:</th>
				<td class="input-min-width align-left">E-<%=id%></td>



			</tr>

			<tr>
				<th class="align-right">Salary/Month.:</th>
				<td class="align-left"><%=dto.getSalary_per_month()%></td>
				<th class="align-right">Dob.:</th>
				<td class="input-width align-left"><%=LogFileService.changeFormate(dto.getDob(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>



			</tr>


		</table>

		<h3>Contact Details</h3>

		<table>

			<tr>
				<th>Mobile No</th>
				<th>Other Mobile No</th>
				<th>City</th>
				<th>Email</th>

			</tr>



			<tr class="item_row">
				<td align="center"><%=dto.getMobile_no()%></td>
				<td align="center"><%=dto.getOther_no()%></td>
				<td align="center"><%=dto.getCity_name()%></td>
				<td align="center"><%=dto.getEmail_id()%></td>

			</tr>

			<tr>

				<th class="align-right">Address.:</th>
				<td class="align-left width" colspan="3"><%=dto.getAddress()%></td>

			</tr>

		</table>

		<h3>Academic Details</h3>

		<table>
			<tr>
				<th class="align-right" width="20%">Qualification:</th>
				<td class="align-left" width="40%"><%=dto.getQualification()%></td>
				<th class="align-right" width="15%">Exp.:</th>
				<td class="input-min-width align-left"><%=dto.getExperience()%></td>



			</tr>


		</table>


		<h3>Bank Details</h3>

		<table>
			<tr class="total-row">
				<th>Bank</th>
				<th>Account No</th>
				<th>IFSC Code</th>
			</tr>
			<tr class="total-row">
				<td><%=dto.getBank()%></td>
				<td><%=dto.getAccount_no()%></td>
				<td><%=dto.getIfsc_code()%></td>
			</tr>
		</table>





		<p class="cust-massage">This is computer generated receipt , no
			signature required..</p>

		<button class="print-button" id="print-button" onclick="printBill()">Print</button>
		<a class="home-button" id="home-button" href="manage_employee.jsp">Home</a>

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