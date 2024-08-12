<%@include file="include/head.jsp"%>
</head>
<body>


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->
	
	
	<main id="main" class="main">

		
		
		<%@include file="include/breadcrumbs.jsp" %>

		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Manage Bank</h1>
		</div>
		
		

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

						<br>

						<table id="examples" class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 8%; text-align: center;">S.N0.</th>
									<th style="width: 18%; text-align: center;">Name</th>
									<th style="width: 10%; text-align: center;">Branch</th>
									<th style="width: 10%; text-align: center;">Acc No.</th>
									<th style="width: 10%; text-align: center;">IFSC</th>
									<th style="width: 10%; text-align: center;">Op Balance</th>
									<th style="width: 10%; text-align: center;">Op Date</th>

									<th style="width: 8%; text-align: center;">Status</th>
									<th style="width: 8%; text-align: center;">View</th>
									<th style="width: 8%; text-align: center;">Edit</th>

									<th style="width: 8%; text-align: center;">Del.</th>
								</tr>

							</thead>
							<tbody class="text-center" id="tbody">
								<%
								BankService ser = new BankService();
								ArrayList<BankDto> list1 = ser.getBankInfo(config, request);
								for (BankDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getBranch()%></td>
									<td><%=dto.getAccount_no()%></td>
									<td><%=dto.getIfsc_code()%></td>
									<td><%=dto.getOpening_bal()%></td>
									
									<td><%=LogFileService.changeFormate(dto.getOpening_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getStatus()%></td>
									
									<td><a href="manage_online_payment.jsp?id=<%=dto.getId()%>">
									<i class="fa fa-file" aria-hidden="true"></i></a></td>

									<td><a href="edit_bank.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>

									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxBankDelete.jsp?id=<%=dto.getId()%>&type=Bank"> <i
											class="bi bi-trash main-color"></i></a></td>
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