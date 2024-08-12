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
	
		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id=page_title>Measurement Report</h1>
		</div>
		
		
		
		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
				
						<br>

						<table id="example-small" class="table hover table-responsive">
							<thead class="text-center"  id="thead">		
								<tr   >
									<th style="text-align: center;" width="10%">S.No.</th>
					  				<th style="text-align: center;" width="35%">Name</th>
									<!-- <th style="text-align: center;" width="35%">Date</th> -->
									
									<% if(user_head_dto.getUpdate_module().equals("Yes")){%>	
									<th style="text-align: center;" width="10%">Edit</th>
									<%} %>
								<% if(user_head_dto.getDelete_module().equals("Yes")){%>
									<th style="text-align: center;" width="10%">Delete</th>
									<%} %>
								</tr>
							</thead>
							<tbody class="text-center"  id="tbody">
								<%
								MeasurementService ser = new MeasurementService();
									ArrayList<MeasurementDto>list1 = ser.getMeasurementInfo(config, request);
									for(MeasurementDto dto : list1 ){
									
									%>	
								<tr>
								<td> <%=list1.indexOf(dto) + 1%></td>
								<td><%=dto.getName()%></td>
								<%-- <td><%=LogFileService.changeFormate(dto.getCurrent_in_date(), "yyyy-MM-dd", "dd-MM-yyyy")%> --%>
								<% if(user_head_dto.getUpdate_module().equals("Yes")){%>
								<td><a class="main-color"  href="edit_measurement.jsp?id=<%=dto.getId()%>"><i class="bi bi-pencil-square"></i></a></td>
								 <%} %>
								
									 <% if(user_head_dto.getDelete_module().equals("Yes")){%>
								<td><a class="main-color" onclick="return confirm('Are you sure? You want to delete')" 
								href="AjaxFolder/AjaxDelete.jsp?id=<%=dto.getId()%>&type=Measurement">
									<i class="bi bi-trash"></i></a></td>
							<%} %>
							</tr>
							 <%} %> 
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