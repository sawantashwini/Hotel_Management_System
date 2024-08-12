<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.EmployeeDto"%>
<%@page import="com.service.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="include/head.jsp"%>
</head>

<body>

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->


	<main id="main" class="main">
	
		<!-- ======= Breadcrumbs ======= -->
		<%@include file="include/breadcrumbs.jsp"%>
		<!--  Breadcrumbs End-->

		<%
		String month = request.getParameter("m") == null ? "" : request.getParameter("m");
		String year = request.getParameter("y") == null ? "" : request.getParameter("y");
		%>
		<div class="pagetitle text-center">
			<h1 id="page_title">Pay Salary</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

						<div class="container">


							<div class="row col-12 justify-content-center" id="table_search">
								<form class="row g-3 d-flex justify-content-center" novalidate>


									<!-- Paid Month start -->
									<div class="col-md-2 col-sm-4">
										<div class="control form-floating mb-3">
											<select class="form-select" id="month" name="m"
												aria-label="working post">

												<option value="">Select month</option>
												<%
												 // Replace this with your actual selected month value
												for (int i = 1; i <= 12; i++) {
													String monthValue = String.format("%02d", i);
													String monthName = new java.text.DateFormatSymbols().getShortMonths()[i - 1];
													String selectedAttribute = (month.equals(monthValue)) ? "selected='selected' id='selected_month'" : "";
												%>
												<option value="<%=monthValue%>" <%=selectedAttribute%>><%=monthName%></option>
												<%
												}
												%>


											</select> <label for="paid_month">Select Month</label>
										</div>
									</div>
									<!-- Paid Month end -->

									<div class="col-md-2">
										<div class="control form-floating mb-3">
											<select class="form-select" id="year" name="y" required
												aria-label="working post">

												<option value="">Select Session</option>
												<%
												String[] options = { "2023", "2024", "2025", "2026", "2027" };
												%>
												<%
												for (String option : options) {
												%>
												<option value="<%=option%>"
													<%=option.equals(year) ? "selected" : ""%>><%=option%></option>
												<%
												}
												%>
											</select> <label for="session_search">Session year</label>
											<div class="invalid-feedback">Please, Employee] session
												year!</div>
										</div>
									</div>

									<div class="col-md-2 col-4">
										<div class="form-floating">
											<button type="submit" class="submit-btn">Submit</button>
										</div>
									</div>
								</form>

							</div>

						</div>
						<br> <br>

						<table id="large_simple_table"
							class="table hover table-responsive nowrap"
							style="min-width: 1700px;">

							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="2%">S.No.</th>
									<th style="text-align: center;" width="2%">Pay Salary</th>
									<th style="text-align: center;" width="12%">Name</th>
									
									<th style="text-align: center;" width="10%">Email</th>
									<th style="text-align: center;" width="6%">Mobile No.</th>
									<th style="text-align: center;" width="">Address</th>
									<th style="text-align: center;" width="8%">City Name</th>
									<th style="text-align: center;" width="4%">Salary</th>
									<th style="text-align: center;" width="4%">Leave Days</th>
									<th style="text-align: center;" width="4%">Half Days</th>
									<th style="text-align: center;" width="4%">Absent Days</th>
									<th style="text-align: center;" width="4%">Full Days</th>
									<th style="text-align: center;" width="4%">Final Salary</th>

								</tr>
							</thead>
							<tbody class="text-center" id="tbody">


								<%
								EmployeeService ser = new EmployeeService();

								if (!month.equalsIgnoreCase("") && !year.equalsIgnoreCase("")) {

									ArrayList<EmployeeDto> emp_list = ser.getEmployeeInfoAfterMonthYear(month, year, config, request);

									ArrayList<EmployeeDto> pay_emp_list = ser.getSalaryPaidOrNot(month, year, request);
									int avil_id = 0;
									int index = 0;

									for (EmployeeDto dto : emp_list) {

										for (EmployeeDto pay_emp_dto : pay_emp_list) {

									if (dto.getId() == pay_emp_dto.getEmployee_id_fk()) {
										avil_id = dto.getId();
									}
										}

										if (avil_id == dto.getId()) {

										} else {
									index++;

									int full = 0, half = 0, leave = 0, absent = 0;

									ArrayList<EmployeeDto> list_days = ser.getAttendenceInfoByEmp(dto.getId(), month, year, request);
									for (EmployeeDto dto_days : list_days) {
										if (dto_days.getAttendance_status() == 0) {
											absent++;
										}
										if (dto_days.getAttendance_status() == 1) {
											full++;
										}
										if (dto_days.getAttendance_status() == 2) {
											half++;
										}
										if (dto_days.getAttendance_status() == 3) {
											leave++;
										}

									}

									float per_day = dto.getSalary_per_month() / 30;

									float due_absent = absent * per_day;
									float due_half = half * per_day / 2;

									float final_salary = (dto.getSalary_per_month() - due_absent - due_half);
								%>

								<tr class="class_row">
									<td><%=index%></td>
									<td><a id="edit-icon" style="color: #f00;"
										href="pay_employee_salary.jsp?id=<%=dto.getId()%>&m=<%=month%>&y=<%=year%>&n=<%=dto.getName()%>"><i
											class="bi bi-pencil-square"></i></a></td>

									<td id="name-td"><%=dto.getName()%></td>

									<td><%=dto.getEmail_id()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getAddress()%></td>
									<td><%=dto.getCity_name()%></td>
									<td><%=dto.getSalary_per_month()%></td>
									<th><%=leave%></th>
									<th><%=half%></th>
									<th><%=absent%></th>
									<th><%=full%></th>
									<th><%=final_salary%></th>

								</tr>
								<%
								}
								}
								}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</section>

	</main>
	<!-- End #main -->
	
	<!-- Customize Script Start  -->
	<!-- <script>
	var page_title=document.getElementById('page_title').innerHTML;
	function printfunction(){
		return '<h1 style="text-align:center;margin-bottom:10px;">'+page_title+'</h1>';
	}
	function printTitle(){
		return page_title;
	}
	</script> -->
	<!-- Customize Script End -->

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->
 


</body>

</html>