
	<%@include file="include/head.jsp"%>
	</head>
<!-- body Start-->
<body>

	<div id="ser"></div>

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
			<h1 id="page_title"> Liquor Low Stock</h1>
		</div>
		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
						<br>
						<!-- table Start-->
						<table id="examples-small" class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="5%">S.No.</th>
									<th style="text-align: center;" width="10">Name</th>
									<th style="text-align: center;" width="20%">Quantity</th>
									<th style="text-align: center;" width="10%">Min. Qty.</th>
									<th style="text-align: center;" width="10%">Capacity</th>
									
									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
										<th style="text-align: center;" width="10%">Edit</th>
									<%} %>
									
									<%-- <%if(user_head_dto.getDelete_module().equalsIgnoreCase("Yes")){ %>
										<th style="text-align: center;" width="10%">Delete</th>
									<%} %> --%>
									
									
									
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								LiquorCategoryService ser = new LiquorCategoryService();
								ArrayList<LiquorCategoryDto> list1 = ser.getLiquorCategoryRequiredInfo(config, request);
								for (LiquorCategoryDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getQuantity()%> (<%=dto.getMeasure_name()== null ? "N/A": dto.getMeasure_name()%>)</td>
									<td><%=dto.getMin_qty()%></td>
									<td><%=dto.getCapacity()%></td>
									
									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
										<td><a
										href="edit_liquor_category.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%} %>
									<%-- <%if(user_head_dto.getDelete_module().equalsIgnoreCase("Yes")){ %>
										<td><a  onclick="return confirm('Are you sure? You want to delete')" 
										href="AjaxFolder/AjaxDelete.jsp?id=<%=dto.getId()%>&type=Liquor_category">
									<i class="bi bi-trash main-color"></i></a></td>
									<%} %> --%>
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