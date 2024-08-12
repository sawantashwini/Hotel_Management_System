

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

		<div class="pagetitle text-center">
			<%@include file="include/add_pages.jsp"%>
			<h1 id=page_title>Customer Report</h1>
		</div>



		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
						<br>
						<table id="example" class="table hover text-center nowrap">

							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center" width="4%">S.NO.</th>
									<th style="text-align: center;" width="14%">Name</th>
									<th style="text-align: center;" width="10%">Mobile No.</th>
									<th style="text-align: center;" width="14%">Address</th>
									<th style="text-align: center;" width="10%">GST No.</th>
									<th style="text-align: center;" width="5%">Company</th>
									<th style="text-align: center;" width="5%">Date of Birth</th>
									<th style="text-align: center;" width="5%">Date of
										Anniversary</th>
									<th style="text-align: center;" width="10%">Due</th>

									<th style="text-align: center;" width="6%">Room Bills</th>
									<th style="text-align: center;" width="6%">Order Bills</th>

									<th style="text-align: center;" width="6%">Account</th>

									<th style="text-align: center;" width="10%">Op. Due</th>
									<!-- <th style="text-align:center;"width="10%">Upcoming Date</th>
											<th style="text-align:center;"width="10%">Upcoming remark</th> -->

									<%-- <% if(user_sidebar_dto.getUpdate_module().equals("Yes")){%>	 --%>
									<th style="text-align: center;" width="4%">Edit</th>
									<%-- 	<%} 
									if(user_sidebar_dto.getDelete_module().equals("Yes")){
									%> --%>
									<!-- <th style="width: 4%; text-align: center;">Del.</th> -->
									<%-- <%} %> --%>

								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								CustomerService ser = new CustomerService();
								ArrayList<CustomerDto> list1 = ser.getCustomerInfo(config, request);
								for (CustomerDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getAddress()%></td>
									<td><%=dto.getGst_no()%></td>
									<td><%=dto.getCompany_name()%></td>
									<td><%=LogFileService.changeFormate(dto.getDob(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=LogFileService.changeFormate(dto.getDoa(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getOld_due()%> <%
 if (dto.getOld_due() != 0) {
 %>
										/&nbsp;<a class="main-color"
										href="pay_customer_due.jsp?id=<%=dto.getId()%>"> <i
											class="bi bi-pencil-square"></i></a> /&nbsp;<a class="main-color"
										href="manage_customer_due.jsp?id=<%=dto.getId()%>"> <i
											class="bi bi-image"></i></a></td>
									<%
									}
									%>

									<td><a class="main-color"
										href="manage_customer_all_bills.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-image"></i></a></td>
									<td><a class="main-color"
										href="manage_customer_all_orders_bills.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-image"></i></a></td>
									<td><a class="main-color"
										href="manage_customer_account.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-image"></i></a></td>

									<td><%=dto.getOpening_due()%> <%
 if (dto.getOpening_due() == 0) {
 %>
										/ <a class="main-color"
										href="edit_customer_opening_due.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a> <%
 }
 %></td>

									<%-- <td><%=LogFileService.changeFormate(dto.getUpcoming_date(), "yyyy-MM-dd", "dd-MM-yyyy")%>
								<td> <%=dto.getUpcoming_remark()%> --%>
									<%-- <% if(user_sidebar_dto.getUpdate_module().equals("Yes")){%>	 --%>
									<td><a class="main-color"
										href="edit_customer.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%--  <%} if(user_sidebar_dto.getDelete_module().equals("Yes")){
									%> --%>

									<%-- <td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?customer_id=<%=dto.getId()%>">
											<i class="bi bi-trash main-color"></i>
									</a></td> --%>
									<%-- <%} %> --%>

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