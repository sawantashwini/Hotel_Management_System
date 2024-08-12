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

		<div class="pagetitle text-center">
			<h1 id="page_title">Project Info</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">
						<br>
						<table id="examples-small" class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 5%; text-align: center;">S.No.</th>
									<th style="width: 20%; text-align: center;">Image</th>
									<th style="width: 20%; text-align: center;">Name</th>
									<th style="width: 20%; text-align: center;">Mobile No.</th>
									<th style="width: 30%; text-align: center;">Address</th>
									<th style="width: 15%; text-align: center;">GST in</th>
									<th style="width: 15%; text-align: center;">Date</th>
									<th style="width: 25%; text-align: center;">Terms and
										Conditions</th>
									<th style="width: 5%; text-align: center;">Edit</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<%
								ProjectService service = new ProjectService();

								ArrayList<ProjectDto> list = service.getProjectInfo(config, request);

								for (ProjectDto dto : list) {
								%>

								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td>
										<div class="align-item-center justify-content-center">
											<img src="ProjectImage/<%=dto.getId()%>.jpg" width="50px"
												alt="No Photo" />
										</div>
									</td>
									<td><%=dto.getName()%></td>
									<td><%=dto.getMobile_no()%></td>

									<td><%=dto.getAddress()%></td>
									<td><%=dto.getGst_in()%></td>

									<td><%=LogFileService.changeFormate(dto.getCurrent_in_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td>
										<%
										if (dto.getTerm_and_conditions() == null || dto.getTerm_and_conditions().equalsIgnoreCase("")) {
										%>N/A<%
										} else {
										%> <%=dto.getTerm_and_conditions()%> <%
 }
 %>
									</td>
									<td><a class="main-color"
										href="edit_project.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
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