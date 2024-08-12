
<!-- ======= Head ======= -->
<%@include file="include/head.jsp"%>
<!-- ======= Head end======= -->

</head>


<body>

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
			<h1 id=page_title>Dealer Report</h1>
		</div>

		

		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">

						<br>

						<table id="examples" class="table hover table-responsive">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center" width="4%">S.NO.</th>
									<th style="text-align: center;" width="14%">Name</th>
									<th style="text-align: center;" width="10%">Mobile No.</th>
									<th style="text-align: center;" width="14%">Address</th>
									<th style="text-align: center;" width="10%">GST In</th>
									<th style="text-align: center;" width="10%">Due</th>
									<th style="text-align: center;" width="10%">Bill</th>
									<th style="text-align: center;" width="10%">Account</th>
									<th style="text-align: center;" width="10%">Op. Due</th>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<th style="text-align: center;" width="4%">Edit</th>
									<%
									}
									%>
									<%--  if(user_sidebar_dto.getDelete_module().equals("Yes")){
									
									<th style="width:4%; text-align: center;">Del.</th>
									 <%} %>
								 --%>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								DealerService ser = new DealerService();
								ArrayList<DealerDto> list1 = ser.getDealerInfo(config, request);
								for (DealerDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getAddress()%></td>
									<td><%=dto.getGst_no()%></td>

									<td><%=dto.getOld_due() + " "%> <%
 if (dto.getOld_due() != 0) {
 %>
										/&nbsp;<a class="main-color"
										href="pay_dealer_due.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a> &nbsp;/<a class="main-color"
										href="manage_dealer_due.jsp?id=<%=dto.getId()%>"> <i
											class="bi bi-image"></i></a></td>
									<%
									}
									%>

									<td><a class="main-color"
										href="manage_dealer_all_bill.jsp?id=<%=dto.getId()%>"> <i
											class="bi bi-image"></i>
									</a></td>

									<td><a class="main-color"
										href="manage_dealer_account.jsp?id=<%=dto.getId()%>"> <i
											class="bi bi-image"></i></a></td>
									<td><%=dto.getOpening_due()%> <% if (dto.getOpening_due() == 0) { %>
										/ <a class="edit-icon"
										href="edit_dealer_opening_due.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a>
										<%
										}
										%></td>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<td><a class="main-color"
										href="edit_dealer.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
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