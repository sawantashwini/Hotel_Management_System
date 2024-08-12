
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
	String year = request.getParameter("Paid_year") == null ? "" : request.getParameter("Paid_year");
	int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
	EmployeeService ser = new EmployeeService();

	EmployeeDto dto1 = ser.getEmployeeInfoById(id, config, request);
	%>

	<main id="main" class="main">



		<div class="pagetitle text-center">
			<h1 id="page_title"><%=dto1.getName()%>
				Salary
			</h1>
		</div>

		<section class="section profile">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">
						<br>

						<div class="tab-content pt-2 nowrap">
							<table class="table info-table">
								<tr style="">
									<th width="15%">Name :</th>
									<td width="50%"><%=dto1.getName()%></td>
									<th width="10%">Mobile :</th>
									<td><%=dto1.getMobile_no()%></td>
									

								</tr>

								<tr>
								<th >Salary /
											Month :</th>
									<td colspan="3"><%=dto1.getSalary_per_month()%></td>
									


								</tr>
							</table>

						</div>


						<div class="row col-12 justify-content-center" id="table_search">
							<form class="row g-3 d-flex justify-content-center">
								<input type="hidden" name="id" value="<%=id%>">

								<!-- Paid Year start -->
								<div class="col-md-3">
									<div class="control form-floating mb-2">
										<select class="form-select" id="paid_year" name="Paid_year"
											required aria-label="working post">
											<option value="<%=year%>" selected><%=year == null || year.equals("") ? "Select year" : year%></option>
											<option value="All">All</option>
											<option value="2023">2023</option>
											<option value="2024">2024</option>
											<option value="2025">2025</option>
											<option value="2026">2026</option>
											<option value="2027">2027</option>
										</select> <label for="paid_year">Select Paid Year</label>
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
						<br>

						<table id="large_simple_table"
							class="table hover table-responsive nowrap">


							<thead class="text-center" id="thead">
								<tr>
									<th>S.No.</th>
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
									<th>online Remark</th>
									<th>Online Date</th>
									<th>Remark</th>
									<th>Print</th>
									<th>Edit</th>
									<th>Delete</th>

								</tr>
							</thead>
							<tbody class="text-center" id="tbody">


								<%
								EmployeeSalaryService service = new EmployeeSalaryService();
								ArrayList<EmployeeSalaryDto> list = service.getEmployeeSalaryInfoMethodById(year, id, config, request);
								float paid_amount = 0;
								for (EmployeeSalaryDto dto : list) {
								%>

								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
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
									<td><%=dto.getOnline_date() == "" ? "-" : LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getRemark() == null ? "N/A" : dto.getRemark()%></td>
									<td><a
										href="print_employee_salary_receipt.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-printer main-color"></i></a></td>
									<td><a
										href="edit_pay_employee_salary.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square main-color"></i></a></td>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxEmployeeDelete.jsp?employee_salary_id=<%=dto.getId()%>&id=<%=dto.getEmployee_id_fk()%>">
											<i class="bi bi-trash main-color"></i>
									</a></td>
								</tr>

								<%
								paid_amount = paid_amount + dto.getAmount();
								}
								%>


							</tbody>
							<tfoot id="tfoot">
								<tr>
									<td></td>
									<td class="text-center">Total</td>
									<td class="text-center"><%=paid_amount%></td>
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
	
	<!-- Customize Script Start  -->
	<%-- <script>
	var page_title=document.getElementById('page_title').innerHTML;
	function printfunction(){
		return '<h1 style="text-align:center;margin-bottom:10px;">'+page_title+'</h1><br><h5 style="text-align:center;margin-bottom:2px;">Year: <%=year%></h5>';
	}
	function printOtherfunction(){
		return 'Year:<%=year%>';
	}
	function printTitle() {
		return page_title;
	}
	</script> --%>
	<!-- Customize Script End -->


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->



</body>

</html>