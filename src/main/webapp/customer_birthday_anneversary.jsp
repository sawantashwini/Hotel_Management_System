

<!-- head -->
<%@include file="include/head.jsp"%>
<!-- head end -->
</head>


<body>

<%
	String d1 = request.getParameter("D1") == null ? "" : request.getParameter("D1");
	String d2 = request.getParameter("D2") == null ? "" : request.getParameter("D2");
	int i = Integer.parseInt(request.getParameter("periods") == null ? "0" : request.getParameter("periods"));

	if (i != 0) {
		String temp = LogFileService.getEndDates("yyyy-MM-dd", i);
		String dates[] = temp.split(",");
		d1 = dates[0];
		d2 = dates[1];
		System.out.println(temp);
	}
	String first_date = LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
	String second_date = LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
	String name = request.getParameter("Name") == null ? "" : request.getParameter("Name");
	%>

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->


	<main id="main" class="main">
	<%-- 	<%@include file="include/breadcrumbs.jsp"%> --%>

		<div class="pagetitle text-center">
			<%@include file="include/add_pages.jsp"%>
			<h1 id=page_title>Guest BirthDay Report</h1>
		</div>



		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
						<div class="row " id="table_search">
							<form class="row g-3 d-flex justify-content-center" novalidate>


								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d1" name="D1"
											value="<%=d1%>"> <label for="floatingName">
											Date</label>
									</div>
								</div>

							

								<div class="col-md-2 col-4">
									<div class="form-floating">
										<button type="submit" formnovalidate class="submit-btn">Submit</button>
									</div>
								</div>
							</form>
						</div>
						<br>

						<table id="small_simple_table" class="table hover text-center nowrap">

							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center" width="4%">S.NO.</th>
										<th style="text-align: center;" width="5%">Date of Birth</th>
									<th style="text-align: center;" width="14%">Name</th>
									<th style="text-align: center;" width="10%">Mobile No.</th>
									<th style="text-align: center;" width="14%">Address</th>
									<!-- <th style="text-align: center;" width="10%">GST No.</th>
									<th style="text-align: center;" width="5%">Company</th> -->
								
									
									<!-- <th style="text-align: center;" width="4%">Edit</th> -->
									

								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								CustomerService ser = new CustomerService();
								ArrayList<CustomerDto> list1 = ser.getCustomerBirthdayInfo(d1,config, request);
								for (CustomerDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
	<td><%=LogFileService.changeFormate(dto.getDob(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>									<td><%=dto.getName()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getAddress()%></td>
									<%-- <td><%=dto.getGst_no()%></td>
									<td><%=dto.getCompany_name()%></td> --%>
								
									
								<%-- 	<td><a class="main-color"
										href="edit_customer.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td> --%>
									

								</tr>
								<%
								}
								%>
							</tbody>
						</table>
						<br>
						
					</div>
				</div>
			</div>
		 		<div class="pagetitle text-center">
			
			<h1 id=page_titles>Guest Anniversary Report</h1>
		</div>
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
					
						<div class="row " id="table_search">
							<form class="row g-3 d-flex justify-content-center" novalidate>


							

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d2" name="D2"
											value="<%=d2%>"> <label for="floatingPhone">
											Date</label>

									</div>
								</div>
								
								

								<div class="col-md-2 col-4">
									<div class="form-floating">
										<button type="submit" formnovalidate class="submit-btn">Submit</button>
									</div>
								</div>
							</form>
						</div>
					<br>
						<table id="small_simple_table" class="table hover text-center nowrap">

							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center" width="4%">S.NO.</th>
									<th style="text-align: center;" width="5%">Date of
										Anniversary</th>
									<th style="text-align: center;" width="14%">Name</th>
									<th style="text-align: center;" width="10%">Mobile No.</th>
									<th style="text-align: center;" width="14%">Address</th>
									<!-- <th style="text-align: center;" width="10%">GST No.</th>
									<th style="text-align: center;" width="5%">Company</th> -->
									
									
									<!-- <th style="text-align: center;" width="4%">Edit</th> -->
								
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								CustomerService service = new CustomerService();
								ArrayList<CustomerDto> list = service.getCustomerAnniversaryInfo(d2,config, request);
								for (CustomerDto dto : list) {
								%>
								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
										<td><%=LogFileService.changeFormate(dto.getDoa(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getAddress()%></td>
							<%-- 		<td><%=dto.getGst_no()%></td>
									<td><%=dto.getCompany_name()%></td>
									
								
									
									<td><a class="main-color"
										href="edit_customer.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td> --%>
								

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