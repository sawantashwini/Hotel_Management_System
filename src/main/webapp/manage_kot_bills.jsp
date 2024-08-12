
<!-- ======= Head ======= -->
<%@include file="include/head.jsp"%>
<!-- ======= Head end======= -->


</head>


<body onload="myResultLoadEvent()">
	<div id="ser"></div>
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
	String first_date = LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy") == null ? ""
			: LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
	String second_date = LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy") == null ? ""
			: LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
	%>

	<%
	menu_table = 2;
	%>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->
	

	<main id="main" class="main" style="margin-left: 0px;">

		<%@include file="include/breadcrumbs.jsp"%>

		<div class="pagetitle text-center">
			<h1 id=page_title>Kot Bill Report</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card">
					<div class="card-body table-responsive">

						<div class="row " id="table_search">
							<form class="row g-3 d-flex justify-content-center">
								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="d1"
											name="D1" value="<%=d1%>"> <label for="floatingName">From
											Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="d2"
											name="D2" value="<%=d2%>"> <label for="floatingPhone">To
											Date</label>
									</div>
								</div>
								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<select class="form-control" id="periods" name="periods">
											<option value="0">- Select -</option>
											<%
											int[] num = { 1, 3, 5 , 6};
											String[] word = { "Today", "Weekly", "Monthly", "Yearly" };

											for (int u = 0; u < word.length; u++) {
												int nums = num[u];
												String words = word[u];
											%>
											<option value="<%=nums%>" <%=(i == nums) ? "selected" : ""%>><%=words%></option>
											<%
											}
											%>
										</select> <label for="floatingPhone">To Date</label>
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

						<table id="examples-small" class="table hover" style="min-width: 100%;">

							<thead id="thead">
								<tr>
									<th style="width: 5%; text-align: center;">S.N0.</th>
									<th style="width: 8%; text-align: center;">Bill Time</th>
									<th style="width: 5%; text-align: center;">Table </th>
									<th style="width: 15%; text-align: center;">Manager Name</th>
									<th style="width:2%;">Print</th>
									<th style="width:2%;">Delete</th>

								</tr>
							</thead>
							<tbody id="tbody" class="text-center">


								<%
								OrderService service = new OrderService();
								ArrayList<OrderBillDto> list = service.getAllKotBillInfo(config, request);

								float total_final_amt = 0;
								for (OrderBillDto dto : list) {
								%>


								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td class="main-color"><%=LogFileService.formatDateTime(dto.getCurrent_in_date())%></td>

									<td class="main-color"><%=dto.getTable_name()%></td>
									<td><%=dto.getManager_name()%></td>



									<td><a class="main-color"
										href="print_kot_bill.jsp?kot_id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>


									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?kot_bill_id=<%=dto.getId()%>">
											<i class="bi bi-trash main-color"></i>
									</a></td>


								</tr>

								<%
								total_final_amt = total_final_amt + dto.getWithout_gst_amount() + dto.getFinal_amount();
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