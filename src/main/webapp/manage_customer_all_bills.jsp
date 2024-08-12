

<!-- head -->
<%@include file="include/head.jsp"%>
<!-- head end -->
</head>


<body>
	<div id="ser"></div>
	<%
	String d1 = request.getParameter("D1") == null ? "" : request.getParameter("D1");
	String d2 = request.getParameter("D2") == null ? "" : request.getParameter("D2");
	String first_date = LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy") == null ? ""
			: LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
	String second_date = LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy") == null ? ""
			: LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
	int i = Integer.parseInt(request.getParameter("periods") == null ? "0" : request.getParameter("periods"));

	if (i != 0) {
		String temp = LogFileService.getEndDates("yyyy-MM-dd", i);
		String dates[] = temp.split(",");
		d1 = dates[0];
		d2 = dates[1];
		System.out.println(temp);
	}
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
			<h1 id=page_title>Customer Bill Report</h1>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-12">
					<div class="card ">
						<div class="card-body table-responsive">

							<%
							int cust_id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							CustomerService service = new CustomerService();
							CustomerDto dto1 = service.getCustomerInfoById(cust_id, config, request);
							%>

							<h5 class="text-center card-title"
								style="margin-bottom: -15px; margin-top: 5px">Customer
								Details</h5>
							<div class="table-responsive">
								<table
									class="table info-table table-borderless mt-4 table-color">
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


							<div class="row" style="margin-top: -30px;" id="table_search">
								<form autocomplete="off"
									class="row g-3 d-flex justify-content-center">
									<div class="col-md-2 col-4">
										<div class="form-floating control">
											<input type="hidden" name="id" value="<%=cust_id%>">
											<input type="date" class="form-control " id="d1" name="D1"
												value="<%=d2 == null ? "" : d2%>"> <label
												for="floatingName">From Date</label>
										</div>
									</div>

									<div class="col-md-2 col-4">
										<div class="form-floating control">
											<input type="date" class="form-control " id="d2" name="D2"
												value="<%=d2 == null ? "" : d2%>"> <label
												for="floatingPhone">To Date</label>
										</div>
									</div>
									<div class="col-md-2 col-4">
										<div class="form-floating control">
											<select class="form-control" id="periods" name="periods">
												<option value="0">- Select -</option>
												<%
												int[] num = { 1, 3, 5, 6 };
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

							<table id="example" class="table hover table-responsive nowrap"
								style="width: 1400px;">


								<thead id="thead" class="text-center">
									<tr>
										<th style="width: 2%;">S.No.</th>
										<th style="width: 3%;">Invoice</th>

										<th style="width: 5%;">Final Amt.</th>

										<th style="width: 3%;">Mode</th>
										<th style="width: 5%;">Cash</th>
										<th style="width: 5%;">Online</th>
										<th style="width: 5%;">Paid Amt.</th>


										<th style="width: 5%;">On. Way</th>
										<th style="width: 5%;">On. Remark</th>

										<th style="width: 5%;">On. Date</th>

										<!-- <th style="width: 20px;">Bank Name</th> -->
										<th style="width: 5%;">Remark</th>
										<%
										if (user_head_dto.getUpdate_module().equals("Yes")) {
										%>
										<th style="width: 2%;">Edit</th>
										<%
										}
										%>
										<th style="width: 2%;">Print</th>
										<th style="width: 2%;">Food</th>
										<%
										if (user_head_dto.getUpdate_module().equals("Yes")) {
										%>
										<th style="width: 2%;">Delete</th>
										<%
										}
										%>

									</tr>
								</thead>
								<tbody id="tbody" class="text-center">


									<%
									CustomerService service_bill = new CustomerService();
									ArrayList<RoomBookingDto> list = service_bill.getBillInfoByCust(cust_id, d1, d2, config, request);

									float total_cash = 0, total_online = 0, total_final_amt = 0;
									for (RoomBookingDto dto : list) {
									%>


									<tr>
										<td><%=list.indexOf(dto) + 1%></td>

										<td id="name-td"><%=dto.getInvoice_no()%></td>

										<td id="name-td"><b><%=dto.getFinal_amt()%></b></td>



										<td><%=dto.getBill_payment_mode()%></td>
										<td><%=dto.getBill_cash_amount()%></td>
										<td><%=dto.getBill_online_amount()%></td>

										<td class="main-color"><b><%=dto.getBill_paid_amt()%></b></td>

										<td><%=dto.getBill_online_way() == null ? "-" : dto.getBill_online_way()%></td>
										<td><%=dto.getBill_online_remark() == null ? "-" : dto.getBill_online_remark()%></td>
										<td><%=dto.getBill_online_date() == null ? "-"
		: LogFileService.changeFormate(dto.getBill_online_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
										<td><%=dto.getBill_remark() == null ? "-" : dto.getBill_remark()%></td>


										<%
										if (user_head_dto.getUpdate_module().equals("Yes")) {
										%>
										<td><a href="room_billing.jsp?bill_id=<%=dto.getId()%>"><i
												class="bi bi-pencil-square"></i></a></td>
										<%}%>
										<td><a
											href="print_room_bill.jsp?bill_id=<%=dto.getId()%>"><i
												class="bi bi-printer"></i></a></td>
										<td><a
											href="print_food_bill.jsp?bill_id=<%=dto.getId()%>"><i
												class="bi bi-printer"></i></a></td>

										<%
										if (user_head_dto.getDelete_module().equals("Yes")) {
										%>
										<td><a
											onclick="return confirm('Are you sure? You want to delete')"
											href="AjaxFolder/AjaxDelete.jsp?room_bill_id=<%=dto.getId()%>&room_id=<%=dto.getRoom_id_fk()%>&return_id=<%=cust_id%>">
												<i class="bi bi-trash main-color"></i>
										</a></td>
										<%}%>




									</tr>

									<%
									total_final_amt = total_final_amt + dto.getFinal_amt();

									total_cash = total_cash + dto.getBill_cash_amount();
									total_online = total_online + dto.getBill_online_amount();

									}
									%>




								</tbody>

								<tfoot id="tfoot">
									<tr>

										<td></td>
										<td></td>
										<td>Total</td>

										<td><%=total_final_amt%></td>

										<td></td>
										<td><%=total_cash%></td>
										<td><%=total_online%></td>
										<td><%=total_cash + total_online%></td>
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