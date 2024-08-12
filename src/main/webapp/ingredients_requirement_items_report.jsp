
<%@include file="include/head.jsp"%>
</head>
<!-- body Start-->
<body>

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->
	<!-- main Start-->
	<main id="main" class="main">


		<%@include file="include/breadcrumbs.jsp"%>

		<div class="pagetitle text-center">
			<h1 id="page_title">Ingredients Low Stock</h1>
		</div>
		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
						<br>
						<!-- table Start-->
						<table id="examples-small"
							class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="5%">S.No.</th>
									<th style="text-align: center;" width="10">Name</th>
									<th style="text-align: center;" width="20%">Quantity</th>
									<th style="text-align: center;" width="10%">Minimum limit</th>

									<%
									if (user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")) {
									%>
									<th style="text-align: center;" width="10%">Edit</th>
									<%
									}
									%>



								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								IngredientsItemService ser = new IngredientsItemService();
								ArrayList<IngredientsDto> list1 = ser.getIngredientsRequiredItemInfo(config, request);
								for (IngredientsDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getQty()%> (<%=dto.getMeasurement_name() == null ? "N/A" : dto.getMeasurement_name()%>)</td>
									<td><%=dto.getMin_limit()%></td>

									<%
									if (user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")) {
									%>
									<td><a
										href="edit_ingredients_item.jsp?id=<%=dto.getId()%>"><i
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
						<!-- table End-->
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
<!-- body End-->

</html>