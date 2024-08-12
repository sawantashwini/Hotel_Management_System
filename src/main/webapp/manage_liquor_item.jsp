
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
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title"> Liquor Item</h1>
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
									<th style="text-align: center;" width="10">Item Code</th>
									<th style="text-align: center;" width="25%">Item Name</th>
									<th style="text-align: center;" width="10%">Item Price</th>
								    <th style="text-align: center;" width="10%">Deduct capacity</th>
									
									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
										<th style="text-align: center;" width="10%">Edit</th>
									<%} %>
									
									
									
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								MenuItemService ser = new MenuItemService();
								ArrayList<MenuItemDto> list1 = ser.getLiquorItemInfo(config, request);
								for (MenuItemDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=dto.getItem_code()%></td>
									<td><%=dto.getItem_name()%></td>
									<td><%=dto.getItem_price()%></td>
									<td><%=dto.getLiqour_ind_qty()%></td>
									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
									<td><a href="edit_liquor_item.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%} %>
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