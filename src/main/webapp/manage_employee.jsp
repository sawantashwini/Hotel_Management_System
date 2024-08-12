
<%@include file="include/head.jsp"%>
</head>


<body>


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<%
	String name_search = request.getParameter("name") == null ? "" : request.getParameter("name");
	%>

	<main id="main" class="main">

		<!-- ======= Breadcrumbs ======= -->
		<%@include file="include/breadcrumbs.jsp"%>
		<!--  Breadcrumbs End-->

		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Manage Employee</h1>
		</div>
		
		

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

						<div class="row" id="table_search">

							<form class="row g-3 d-flex justify-content-center">
								<div class="col-lg-3 col-md-3 col-6">

									<div class="control form-floating" style="height: 50px">

										<input type="hidden" name="session"
											value="2023"> <input type="text"
											class="form-control" list="name_list"
											 id="name" name="name"
											value="<%=name_search%>" placeholder="Search Name...."
											style="padding: 7px; text-align: left;" autofocus="autofocus"
											autocomplete="off" />
										<datalist id="name_list">
											<option value="All" />
											<%
											EmployeeService e_ser = new EmployeeService();
											ArrayList<EmployeeDto> e_list = e_ser.getEmployeeName(config, request);
											for (EmployeeDto p_dto : e_list) {
											%>
											<option value="<%=p_dto.getName()%>" />
											<%
											}
											%>
										</datalist>
										<label for="name">Search Name....</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating">
										<button type="submit" class="submit-btn">Submit</button>
									</div>
								</div>
							</form>
						</div>

						<br>

						<table id="large_simple_table"
							class="table hover table-responsive nowrap"
							style="min-width: 1750px;">

							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="2%">S.No.</th>
									<th style="text-align: center;" width="3%">Image</th>
									<th style="text-align: center;" width="8%">Name</th>
									<th style="text-align: center;" width="2%">Salary</th>
									<th style="text-align: center;" width="4%">Transaction</th>
									<th style="text-align: center;" width="4%">Email</th>
									<th style="text-align: center;" width="3%">Mobile No.</th>
									<th style="text-align: center;" width="3%">Other No.</th>
									<th style="text-align: center;" width="">Address</th>
									<th style="text-align: center;" width="4%">City Name</th>
									<th style="text-align: center;" width="5%">Qualification</th>
									<th style="text-align: center;" width="3%">Salary</th>
									<th style="text-align: center;" width="3%">Experience</th>
									<th style="text-align: center;" width="7%">DOB</th>
									<th style="text-align: center;" width="3%">Id Status</th>
									<th style="text-align: center;" width="3%">Photo Status</th>
									<th style="text-align: center;" width="2%">Status</th>
									<th style="text-align: center;" width="2%">Print</th>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<th style="text-align: center;" width="2%">Edit</th>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<th style="text-align: center;" width="2%">Delete</th>
									<%
									}
									%>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">

								<%
								EmployeeService service = new EmployeeService();
								EmployeeAccountService ser = new EmployeeAccountService();

								ArrayList<EmployeeDto> list = service.getEmployeeInfoByName(name_search, config, request);

								for (EmployeeDto dto : list) {

									float bal_amount = ser.getEmployeeTransactionAmountInfo(dto.getId(), config, request);
								%>


								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td>
										<div class="align-item-center justify-content-center">
											<img src="EmployeeImage/<%=dto.getId()%>.jpg" width="49px"
												height="49px" style="border: 1px solid gray;" alt="" />
										</div>
									</td>
									<td id="name-td"><%=dto.getName()%></td>
									<td>
										<%-- <a 
										href="pay_salary.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square main-color"></i></a>  /   --%> <a
										href="manage_employee_salary.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-image main-color"></i></a>
									</td>

									<td><%=bal_amount%>/<a id="edit-icon" style="color: #f00;"
										href="pay_amount_employee.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square main-color"></i></a> / <a
										href="manage_employee_account.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-image main-color"></i></a></td>

									<td><%=dto.getEmail_id()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getOther_no()%></td>
									<td><%=dto.getAddress()%></td>
									<td><%=dto.getCity_name()%></td>
									<td><%=dto.getQualification()%></td>
									<td><%=dto.getSalary_per_month()%></td>
									<td><%=dto.getExperience()%></td>
									<td><%=LogFileService.changeFormate(dto.getDob(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getId_card_status() == null ? "N/A" : dto.getId_card_status()%></td>
									<td><%=dto.getPhoto_status() == null ? "N/A" : dto.getPhoto_status()%></td>
									<td><%=dto.getStatus()%></td>
									<td><a id="view-icon" style="color: #f00;"
										href="view_employee.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<td><a id="edit-icon" style="color: #f00;"
										href="edit_employee.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxEmployeeDelete.jsp?employee_id=<%=dto.getId()%>">
											<i class="bi bi-trash main-color"></i>
									</a></td>
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

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->



</body>

</html>