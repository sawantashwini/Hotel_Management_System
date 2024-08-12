
<%@include file="include/head.jsp"%>
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

		<!-- ======= Breadcrumbs ======= -->
		<%@include file="include/breadcrumbs.jsp"%>
		<!--  Breadcrumbs End-->

		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Spend Report</h1>
		</div>
		
		

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">


						<div class="row " id="table_search">
							<form class="row g-3 d-flex justify-content-center" novalidate>


								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d1" name="D1"
											value="<%=d1%>"> <label for="floatingName">From
											Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d2" name="D2"
											value="<%=d2%>"> <label for="floatingPhone">To
											Date</label>

									</div>
								</div>
								
								<div class="col-md-3 refresh-container refresh-block-none mb-3" data-list="list_spend_head">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="name" name="Name"
											value="<%=name%>" placeholder="Name" autocomplete="off"> 
											<label for="name">Spend Head</label>
										<div class="invalid-feedback">Please, enter Spend Head!</div>
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
										<button type="submit" formnovalidate class="submit-btn">Submit</button>
									</div>
								</div>
							</form>
						</div>
						<br>

						<table id="example" class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 10px; text-align: center;">S.No.</th>
									<!-- <th style="width: 20%; text-align: center;">Image</th> -->
									<th style="width: 20%; text-align: center;">Download</th>
									<th style="width: 60px; text-align: center;">Date</th>
									<th style="width: 150px; text-align: center;">Spend on</th>
									<th style="width: 200px; text-align: center;">Remark</th>
									<th style="width: 70px; text-align: center;">Paid</th>
									<th style="width: 60px; text-align: center;">Pay Mode</th>
									<th style="width: 100px; text-align: center;">Cash</th>
									<th style="width: 100px; text-align: center;">Online</th>
									<th style="width: 100px; text-align: center;">Online Way</th>
									<th style="width: 150px; text-align: center;">Online
										Remark</th>
									<th style="width: 60px; text-align: center;">Online Date</th>
									<th style="width: 100px; text-align: center;">Bank</th>
									<!-- <th style="width: 20px; text-align: center;">Pay Date</th> -->
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<th style="width: 10px; text-align: center;">Edit</th>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<th style="width: 10px; text-align: center;">Del.</th>
									<%
									}
									%>
								</tr>
							</thead>
							<tbody id="tbody" class="text-center">


								<%
								SpendService service = new SpendService();
								ArrayList<SpendDto> list = service.getSpendInfo(d1, d2, name, config, request);
								float total_cash = 0, total_online = 0;
								for (SpendDto dto : list) {
								%>


								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<%-- <td>
										<div class="align-item-center justify-content-center">
											<img src="SpendImage/<%=dto.getId()%>.jpg"
												width="50px" alt="No Photo" />
										</div>
									</td> --%>
									<td><a style="font-size: 16px;" class="main-color"
										title="download" href="SpendImage/<%=dto.getId()%>.jpg"
										download><i class="fa-solid fa-download"></i> </a></td>
									<td><%=LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>



									<td><%=dto.getSpend_name()%></td>
									<td><%=dto.getRemark() == null ? "-" : dto.getRemark()%></td>
									<td><%=dto.getAmount()%></td>
									<td><%=dto.getPayment_mode()%></td>

									<td><%=dto.getDebit_cash_amount()%></td>
									<td><%=dto.getDebit_online_amount()%></td>

									<td><%=dto.getOnline_way() == null ? "-" : dto.getOnline_way()%></td>
									<td><%=dto.getOnline_remark() == null ? "-" : dto.getOnline_remark()%></td>
									<td><%=dto.getOnline_date() == null ? "-"
		: LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getBank_name() == null ? "-" : dto.getBank_name()%></td>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<td><a class="main-color"
										href="edit_spend.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>

									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxSpendIncomeDelete.jsp?spend_id=<%=dto.getId()%>">
											<i class="bi bi-trash main-color"></i>
									</a></td>
									<%
									}
									%>
								</tr>

								<%
								total_cash = total_cash + dto.getDebit_cash_amount();
								total_online = total_online + dto.getDebit_online_amount();
								}
								%>
							</tbody>

							<tfoot id="tfoot">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td>Total</td>
									<td><%=total_cash + total_online%></td>
									<td></td>
									<td><%=total_cash%></td>
									<td><%=total_online%></td>
									<!-- <td></td> -->
									<td></td>
									<td></td>
									<td></td>
									<td></td>

									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<td></td>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<td></td>
									<%
									}
									%>

								</tr>

							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</section>
		<div id="info"></div>

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