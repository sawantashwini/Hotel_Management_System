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

		<%@include file="include/breadcrumbs.jsp"%>



		<div class="pagetitle text-center">
			<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Manage Table</h1>
		</div>



		<section class="section">
			<div class="row">
				<div class="card table-responsive">
					<div class="card-body">
						<br>

						<table id="examples-small"
							class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="5%">S.No.</th>
									<th style="text-align: center;" width="20">Name</th>
									<th style="text-align: center;" width="15%">Status</th>
									<th style="text-align: center;" width="5%">Edit</th>
									<th style="text-align: center;" width="5%">Delete</th>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								TableService ser = new TableService();
								ArrayList<TableDto> list1 = ser.getTableInfo(config, request);
								for (TableDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td id="name-td"><%=dto.getName()%></td>
									<td><%=dto.getStatus()%></td>
									<td><a href="edit_table.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square main-color"></i></a></td>

									<td><a
										onclick="return confirm('Are you sure? You want to delete');"
										href="AjaxFolder/AjaxDelete.jsp?id=<%=dto.getId()%>&type=Table">
											<i class="bi bi-trash main-color"></i>
									</a></td>
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