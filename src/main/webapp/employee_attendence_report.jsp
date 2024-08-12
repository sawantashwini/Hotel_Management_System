<%@include file="include/head.jsp"%>
</head>
<%
String month = request.getParameter("m") == null ? "" : request.getParameter("m");
String year = request.getParameter("y") == null ? "" : request.getParameter("y");
String name = request.getParameter("n") == null ? "" : request.getParameter("n");
int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
%>

<body onload="checkEmployeeNameIsCorrect('<%=name%>');">


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	
	<main id="main" class="main">



		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Attendance Report</h1>
		</div>
		
		
		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

						<div class="container">

							<div class="row col-12 justify-content-center" id="table_search">
								<form autocomplete="off"
									class="row g-3 d-flex justify-content-center" novalidate>

									<!-- Paid Month start -->
									<div class="col-md-2 col-sm-4">
										<div class="control form-floating mb-3">
											<select class="form-select" id="month" name="m"
												aria-label="working post">

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
											</select> <label for="month">Select Month</label>
										</div>
									</div>
									<!-- Paid Month end -->

									<!-- Paid Year start -->
									<div class="col-md-2">
										<div class="control form-floating mb-3">
											<select class="form-select" id="paid_year" name="y" required
												aria-label="working post">
												<option value="">Select Year</option>
												<%
												String[] options = {"2023", "2024", "2025", "2026", "2027"};
												%>
												<%
												for (String option : options) {
												%>
												<option value="<%=option%>"
													<%=option.equals(year) ? "selected" : ""%>><%=option%></option>
												<%
												}
												%>
											</select> <label for="year">Select Year</label>
										</div>
									</div>
									<!-- Paid Year End -->

									<div class="col-md-3">

										<div class="control form-floating">
											<input type="text" class="form-control" id="employee_name"
												name="n" onblur="checkEmployeeNameIsCorrect(this.value);"
												placeholder="Name" list="browsers" value="<%=name%>"
												readonly="readonly" autocomplete="off" required>

											<datalist id="browsers">
												<%
												EmployeeService ser2 = new EmployeeService();
												ArrayList<EmployeeDto> list2 = ser2.getActiveEmployeeName(config, request);
												for (EmployeeDto employee_dto : list2) {
												%>
												<option value="<%=employee_dto.getName()%>" />
												<%
												}
												%>
											</datalist>
											<label for="name">Employee</label>
											<div class="invalid-feedback">Please, Enter Employee
												Name!</div>
										</div>
									</div>
									<input type="hidden" id="employee_id_fk" name="id"
										value="<%=id%>">

									<div class="col-md-2 col-4">
										<div class="form-floating">
											<button type="submit" class="submit-btn">Submit</button>
										</div>
									</div>
								</form>

							</div>
							<div id="rev"></div>

						</div>

						<br>

						<table id="small_table" class="table hover table-responsive"
							style="width: 100%">

							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="5%">S.No.</th>
									<th style="text-align: center;" width="10%">Date</th>
									<th style="text-align: center;" width="10%">Type</th>
									<th style="text-align: center;" width="5%">Edit</th>
									<th style="text-align: center;" width="5%">Delete</th>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">

								<%
								EmployeeService service = new EmployeeService();
								ArrayList<EmployeeDto> list = service.getAttendenceInfoByEmp(id, month, year, request);
								for (EmployeeDto dto : list) {
								%>
								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td><%=LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td>
										<%
										if (dto.getAttendance_status() == 0){
										%>Absent<%}
										else if (dto.getAttendance_status() == 1){
										%>Full Day<%}
										else if (dto.getAttendance_status() == 2){
										%>Half Day<%}
										else{
										%>Leave<%} %>
									</td>
									<%
									if (user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")) {
									%>
									<td><a
										href="edit_attendance_entry.jsp?id=<%=dto.getId()%>&y=<%=year%>&m=<%=month%>"><i
											class="main-color bi bi-pencil-square"></i></a></td>
									<%
									}
									%>
									<%
									if (user_head_dto.getDelete_module().equalsIgnoreCase("Yes")) {
									%>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxEmployeeDelete.jsp?attendance_id=<%=dto.getId()%>&id=<%=dto.getEmployee_id_fk()%>&y=<%=year%>&m=<%=month%>&n=<%=name%>"><i
											class="main-color bi bi-trash"></i></a></td>
									<%
									}
									%>
								</tr>

								<%
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

	<script>
		function checkEmployeeNameIsCorrect(name) {
			//alert(name);
			if (name != '') {

				$
						.ajax({
							url : 'Ajax_check_employee_name_corret.jsp',
							data : 'Name=' + name,
							type : 'post',
							success : function(msg) {

								//alert(msg);

								$('#rev').html(msg);

								var employee_id = document
										.getElementById('employee_id').value;
								if (employee_id == 0) {
									document.getElementById('employee_name').value = "";
									document.getElementById('employee_id_fk').value = 0;
									//alert(document.getElementById('employee_id_fk').value);
									alert('Insert corret employee name');
								} else {
									document.getElementById('employee_id_fk').value = employee_id;
									//alert('Employee Id ='+ employee_id);
								}
							}
						});
			} else {

				document.getElementById('employee_id_fk').value = 0;
				document.getElementById('employee_name').value = "";
				//alert(document.getElementById('employee_id_fk').value);
				//alert(document.getElementById('employee_name').value);
			}
		}
	</script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->



</body>

</html>