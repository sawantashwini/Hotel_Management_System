
<!-- ======= Head ======= -->
<%@include file="include/head.jsp"%>
<!-- ======= Head end======= -->


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
		String first_date= LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy")== null ? "" :LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
		String second_date= LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy")== null ? "" :LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
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
			<h1 id=page_title>Customer All Order Bill</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card">
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

						<div  class="row " id="table_search">
							<form autocomplete="off" class="row g-3 d-flex justify-content-center">
								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="date1"
											name="D1" value="<%=d1%>"> <label for="floatingName">From
											Date</label>
									</div>
								</div>
								
								<input type="hidden" id="d1_time" value="<%=first_date%>">
								<input type="hidden" id="d2_time" value="<%=second_date%>">

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="date2"
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
						<table id="example" class="table hover nowrap" style="min-width: 100%;">

							<thead id="thead">
								<tr>
									<th style="width: 5%; text-align: center;">S.No.</th>
									<th style="width: 10%; text-align: center;">Bill Date</th>
									<th style="width: 10%; text-align: center;">Table Name</th>
									<th style="width: 20%; text-align: center;">Manager Name</th>
									<th style="width: 5%; text-align: center;">Final Amount</th>
									<th style="width: 5%; text-align: center;">Paid Amount</th>
									<th style="text-align: center;" width="5%">Edit</th>
									<th style="text-align: center;" width="5%">Print</th>
									<th style="text-align: center;" width="5%">Delete</th>

								</tr>
							</thead>
							<tbody id="tbody" class="text-center">


								<%
								OrderService o_service = new OrderService();
								ArrayList<OrderBillDto> o_list = o_service.getAllCustOrderInfo(d1, d2, cust_id, config, request);

								float total_final_amt = 0, total_paid_amt = 0;
								for (OrderBillDto dto : o_list) {
								%>


								<tr>
									<td><%=o_list.indexOf(dto) + 1%></td>
									<td class="main-color"><%=LogFileService.formatDateTime(dto.getCurrent_in_date())%></td>

									<td class="main-color"><%=dto.getTable_name()%></td>
									<td><%=dto.getManager_name()%></td>

									<td class="main-color"><%=Math.round(dto.getFinal_amount())%></td>
									<td class="main-color"><%=Math.round(dto.getPaid_amount())%></td>

									<td><a class="main-color"
										href="order_bill.jsp?complete_order_id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<td><a class="main-color"
										href="print_order_bill.jsp?order_id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>


									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?order_bill_id=<%=dto.getId()%>"> <i class="bi bi-trash main-color"></i></a></td>


								</tr>

								<%
								
								total_final_amt =total_final_amt+dto.getFinal_amount();
								total_paid_amt =total_paid_amt+dto.getPaid_amount();
								}
								%>
							</tbody>

							<tfoot id="tfoot">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td>Total</td>
									<td><%=Math.round(total_final_amt)%></td>
									<td><%=Math.round(total_paid_amt) %></td>
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