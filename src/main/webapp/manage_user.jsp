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
			<h1 id="page_title">Manage User</h1>
		</div>
		
		
		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">


						<br>

						<table id="examples-small"
							class="table hover table-responsive nowrap" style="width: 100%;">


							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 5%;">S.No.</th>
									<th style="width: 20%;">Name</th>
									<th style="width: 10%;">Mobile No.</th>
									<th style="width: 10%;">Pass.</th>
									<th style="width: 10%;">Address</th>
									<th style="width: 10%;">Status</th>
									<th style="width: 6%;">Features</th>
									<th style="width: 6%;">Edit</th>
									<th style="width: 6%;">Del.</th>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">


								<%
								UserService service = new UserService();
								ArrayList<UserDto> list = service.getuserInfo(config, request);

								for (UserDto dto : list) {
								%>



								<tr>

									<td id="name-td"><%=list.indexOf(dto) + 1%></td>
									<td style="color: #f00;"><%=dto.getName()%></td>
									<td><%=dto.getMobile_no()%></td>
									<td><%=dto.getPassword()%></td>
									<td><%=dto.getAddress()%></td>
									<td><%=dto.getStatus()%></td>
									<td><a class="main-color"
										href="feature.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-display" style="margin-left: 30px;"></i></a></td>
									<td><a class="main-color"
										href="edit_user.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square" style="margin-left: 10px;"></i></a></td>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?id=<%=dto.getId()%>&type=User"><i
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