
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
		String month = request.getParameter("Paid_month") == null ? "" : request.getParameter("Paid_month");
		String year = request.getParameter("Paid_year") == null ? "" : request.getParameter("Paid_year");
		%>
		<div class="pagetitle text-center">
			<h1 id="page_title">Manage Salary Record</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">

						<div class="container">


							<div class="row col-12 justify-content-center" id="table_search">
								<form autocomplete="off" class="row g-3 d-flex justify-content-center" novalidate>


									<!-- Paid Month start -->
									<div class="col-md-2 col-sm-4">
										<div class="control form-floating mb-3">
											<select class="form-select" id="month" name="Paid_month"
												aria-label="working post">

												<option value="">Select month</option>

												<%
												// Define the list of breadcrumb items and their corresponding href values
												String[] month_values = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
												String[] month_opts = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec" };

												// Iterate through the breadcrumb items and conditionally hide them
												for (int i = 0; i < month_values.length; i++) {
													String month_value = month_values[i];
													String month_opt = month_opts[i];
												%>
												<option value="<%=month_value%>" <%if (month.equals(month_value)) { out.println("selected='selected'"); }%>><%=month_opt%></option>
												<%
												}
												%>
											</select> <label for="paid_month">Select Paid Month</label>
										</div>
									</div>
									<!-- Paid Month end -->

									<!-- Paid Year start -->
									<div class="col-md-2">
										<div class="control form-floating mb-3">
											<select class="form-select" id="paid_year" name="Paid_year"
												required aria-label="working post">

												<option value="">Select Year</option>
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
											<div class="invalid-feedback">Please, Student session
												year!</div>
										</div>
									</div>
									<!-- Paid Year End -->

									<div class="col-md-2 col-4">
										<div class="form-floating">
											<button type="submit" class="submit-btn">Submit</button>
										</div>
									</div>
								</form>

							</div>

						</div>
						<br>

						<table id="large_simple_table"
							class="table hover table-responsive nowrap"
							style="min-width: 1750px;">


							<thead class="text-center" id="thead">
								<tr>
									<th>S.No.</th>
									<th>Name</th>
									<th>Mobile No.</th>
									<th>Total Salary</th>
									<th>Paid Salary</th>
									<th>Paid Date (MM/YYYY)</th>
									<th>Leave Days</th>
									<th>Half Days</th>
									<th>Absent Days</th>
									<th>Present Days</th>
									<th>Total Days</th>
									<th>Payment Mode</th>
									<th>Cash Amt</th>
									<th>Online Amt</th>
									<th>Online Way</th>
									<th>Online Remark</th>
									<th>Online Date</th>
									<th>Print</th>

								</tr>
							</thead>
							<tbody class="text-center" id="tbody">


								<%
								EmployeeSalaryService service = new EmployeeSalaryService();
								ArrayList<EmployeeSalaryDto> list = service.getAllEmployeeSalaryInfoMethod(month, year, config, request);
								float total_paid = 0;
								for (EmployeeSalaryDto dto : list) {

									total_paid = total_paid + dto.getAmount();
								%>


								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td><%=dto.getEmployee_name()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getFinal_salary()%></td>
									<td style="color: red; font-weight: 500;"><%=dto.getAmount()%></td>
									<td><%=dto.getPay_date() == "" ? "-" : LogFileService.changeFormate(dto.getPay_date(), "yyyy-MM-dd", "dd-MM-yyyy")%>&nbsp;&nbsp;/&nbsp;&nbsp;<span
										style="color: blue;">(<%=dto.getPaid_month()%>/<%=dto.getPaid_year()%>)
									</span></td>
									<td><%=dto.getLeave_days()%></td>
									<td><%=dto.getHalf_days()%></td>
									<td><%=dto.getAbsent_days()%></td>
									<td><%=dto.getPresent_days()%></td>
									<td><%=dto.getPresent_days() + dto.getAbsent_days() + (dto.getHalf_days() / 2) + dto.getLeave_days()%></td>
									<td><%=dto.getPayment_mode()%></td>
									<td><%=dto.getCash_amount()%></td>
									<td><%=dto.getOnline_amount()%></td>
									<td><%=dto.getOnline_way()%></td>
									<td><%=dto.getOnline_remark()%></td>
									<td><%=dto.getOnline_date() == "" ? "-"
		: LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><a id="view-icon" style="color: #f00;"
										href="print_employee_salary_receipt.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>
								</tr>

								<%
								}
								%>

							</tbody>
							<tfoot id="tfoot">
								<tr>
									<td></td>
									<td>Total</td>
									<td><%=total_paid%></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tfoot>

						</table>
					</div>
				</div>
			</div>
		</section>

	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->



</body>

</html>