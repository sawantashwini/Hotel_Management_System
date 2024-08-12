

<!-- head -->
<%@include file="include/head.jsp"%>
<!-- head end -->
</head>


<body>


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<main id="main" class="main">

		<%@include file="include/breadcrumbs.jsp"%>

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1 id=page_title>Manage Customer Due</h1>
			</div>
		</div>



		<section class="section profile">
			<div class="row justify-content-center">


				<div class="card">
					<div class="card-body">
						<%
						int cust_id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
						CustomerService service = new CustomerService();
						CustomerDto dto1 = service.getCustomerInfoById(cust_id, config, request);
						%>

						<h5 class="text-center card-title"
							style="margin-bottom: 5px; margin-top: 5px">Customer Details</h5>

						<div class="tab-content pt-2 nowrap">
							<table class="table info-table">
								<tr>
									<th width="10%">Name :</th>
									<td width="30%"><%=dto1.getName()%></td>
									<th width="10%">Mobile :</th>
									<td width="20%"><%=dto1.getMobile_no()%></td>
									<th width="10%">Due :</th>
									<td width="10%"><%=dto1.getOld_due()%></td>

								</tr>
								<tr>
									<th>GST In :</th>
									<td><%=dto1.getGst_no()%></td>
									<th>Address :</th>
									<td colspan="5"><%=dto1.getAddress()%></td>
								</tr>
							</table>

						</div>

						<h5 class="text-center card-title">Paid Amount Details</h5>

						<table id="examples" class="table hover table-responsive nowrap"
							style="min-width: 1450px;">


							<thead id="thead">
								<tr>
									<th width="7%">S.No.</th>
									<th width="10%">Paid amount</th>
									<th width="5%">Pay Mode</th>
									<th width="6%">Cash</th>
									<th width="6%">Online</th>
									<th width="10%">Pay date</th>
									<th width="10%">Online remark</th>
									<th width="10%">Online date</th>
									<th width="15%">Remark</th>
									<th width="7%">Print</th>
									<%-- <% if(user_sidebar_dto.getUpdate_module().equals("Yes")){%>	 --%>
									<th style="text-align: center;" width="7%">Edit</th>
									<%-- <%} %>
								<% if(user_sidebar_dto.getDelete_module().equals("Yes")){%> --%>
									<th style="text-align: center;" width="7%">Delete</th>
									<%-- <%} %> --%>
								</tr>
							</thead>
							<tbody id="tbody">


								<%
								int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
								CustomerDueService service1 = new CustomerDueService();
								ArrayList<CustomerDto> list1 = service1.getCustomerDueInfoById(id, config, request);
								float total_pay = 0, total_cash = 0, total_online = 0, total_due = 0;

								for (CustomerDto dto : list1) {
									total_pay = total_pay + dto.getPay_amount();
									total_cash = total_cash + dto.getCash_amount();
									total_online = total_online + dto.getOnline_amount();
									total_due = total_due + dto1.getOld_due();
								%>


								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>

									<td class="main-color"><%=dto.getPay_amount()%></td>
									<td><%=dto.getPayment_mode()%></td>
									<td><%=dto.getCash_amount()%></td>
									<td><%=dto.getOnline_amount()%></td>

									<td><%=dto.getPay_date() == null ? "-" : LogFileService.changeFormate(dto.getPay_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getOnline_remark() == null ? "-" : dto.getOnline_remark()%></td>
									<td><%=dto.getOnline_date() == null ? "-" : LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getRemark() == null ? "-" : dto.getRemark()%></td>

									<td><a class="main-color"
										href="print_customer_due_receipt.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>
									<%-- <% if(user_sidebar_dto.getUpdate_module().equals("Yes")){%>	 --%>
									<td><a class="main-color"
										href="edit_pay_customer_due.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%-- <%}%>
									<% if(user_sidebar_dto.getDelete_module().equals("Yes")){%> --%>
									<td><a class="main-color"
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxService/AjaxDelete.jsp?customer_due_id=<%=dto.getId()%>&return_id=<%=id%>">
											<i class="bi bi-trash"></i>
									</a></td>
									<%-- <%
								}
								%> --%>
								</tr>

								<%
								}
								%>



							</tbody>
							<tfoot id="tfoot">
								<tr>
									<td class="text-center">Total</td>
									<td id="name-td" class="text-center"><%=total_pay%></td>
									<td></td>
									<td class="text-center"><%=total_cash%></td>
									<td class="text-center"><%=total_online%></td>
									<td colspan="7"></td>
								</tr>
							</tfoot>
						</table>

					</div>
				</div>
			</div>



		</section>
	</main>
	<!-- End main -->
</body>


<!-- ======= Footer ======= -->
<%@include file="include/footer.jsp"%>
<!-- End Footer -->
<!-- ======= datatable_script ======= -->
<%@include file="include/datatable_script_footer.jsp"%>
<!-- End datatable_script -->


</body>

</html>