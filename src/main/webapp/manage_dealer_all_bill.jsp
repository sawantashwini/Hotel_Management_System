
<%@include file="include/head.jsp"%>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  main start-->
	<main id="main" class="main">

		<%@include file="include/breadcrumbs.jsp"%>

		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Dealer All Bill Report</h1>
		</div>

		
		<section class="section">
			<div class="row">
				<div class="card table-responsive">
					<div class="card-body">

						<%
						int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
						DealerService ser1 = new DealerService();
						DealerDto dto1 = ser1.getDealerInfoById(id, config, request);
						%>

						<h5 class="text-center card-title"
							style="margin-bottom: -15px; margin-top: 5px">Dealer
							Details</h5>

						<div class="table-responsive">
							<table class="table info-table table-borderless mt-4 table-color">
								<tr>
									<th width="75px">Name</th>
									<th width="2px">:</th>
									<td width="600px"><%=dto1.getName()%></td>
									<th width="100px">Gstin</th>
									<th width="2px">:</th>
									<td width="250px"><%=dto1.getGst_no()%></td>
									<th width="150px">Mobile no</th>
									<th width="2px">:</th>
									<td width="145px"><%=dto1.getMobile_no()%></td>
								</tr>

								<tr>
									<th>Address</th>
									<th>:</th>
									<td><%=dto1.getAddress()%></td>
									<th>Due</th>
									<th>:</th>
									<td><%=dto1.getOld_due()%></td>
									<td style="border-right: none;"></td>
									<td style="border-left: none; border-right: none"></td>
									<td style="border-left: none;"></td>

								</tr>
							</table>
						</div>

						<div class="row " id="table_search">

							<form class="row g-3 d-flex justify-content-center"
								autocomplete="off">
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
								%>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d1" name="D1"
											value="<%=d1%>"> <label for="d1">From Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d2" name="D2"
											value="<%=d2%>"> <label for="d2">To Date</label>
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

						<!--  Table start-->
						<table id="examples-small" class="table hover nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th width="10%">S.No</th>
									<th width="25%">Remark</th>
									<th width="20%">Total Amount</th>

									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<th width="5%">Edit</th>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<th width="5%">Del.</th>
									<%
									}
									%>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								LiquorPurchaseBillService ser = new LiquorPurchaseBillService();
								ArrayList<LiquorPurchaseBillDto> list1 = ser.getPurchaseBillInfoByDealer(id, d1, d2, config, request);
								float total = 0;
								for (LiquorPurchaseBillDto dto : list1) {
									
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getRemark() == null ? "N/A" : dto.getRemark()%></td>
									<td><%=dto.getTotal_amount()%></td>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<td><a href="edit_liquor_purchase.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square main-color"></i></a></td>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?id=<%=dto.getId()%>&type=Liquor_Purchase">
											<i class="bi bi-trash main-color"></i>
									</a></td>
									<%
									}
									%>
								</tr>
								<%
								 total = total  + dto.getTotal_amount();
								}
								%>
							</tbody>
							<tfoot id="tfoot">
  
								<tr>
									<td></td>
									<td></td>
									<td>Total :- <%=total%></td>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<td class="main-color"></td>
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
						<!--  Table End-->
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