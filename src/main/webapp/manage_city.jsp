
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
			<h1 id=page_title>City Report</h1>
		</div>





		<div class="section">
			<div class="row">
				<div class="card">
					<div class="card-body table-responsive">

						<br />

						<table id="example-small" class="table hover table-responsive">
							<thead class="text-center" id="thead">
								<tr>
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
							<tbody id="tbody">


								<%
									
									CityService service = new CityService();
									ArrayList<CityDto>list = service.getCityInfo(config, request);
									
									for(CityDto dto : list ){
									
									%>


								<tr>
									<td style="text-align: center;"><%=list.indexOf(dto) + 1%></td>
									<td style="text-align: center;" id="name"><%=dto.getName()%></td>
									<%-- <td style="text-align: center;"> <%=LogFileService.changeFormate(dto.getCurrent_in_date(), "yyyy-MM-dd", "dd-MM-yyyy")%> --%>
									<% if(user_head_dto.getUpdate_module().equals("Yes")){%>
									<td style="text-align: center;"><a class="main-color"
										href="edit_city.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>

									<%} %>
									<% if(user_head_dto.getDelete_module().equals("Yes")){%>
									<td><a class="main-color"
										onclick="confirm('<%=dto.getId()%>', '0', 'City')"> <i
											class="bi bi-trash"></i>
									</a></td>
									<%} %>
								</tr>

								<%} %>



							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->

	<script type="text/javascript">
	
	function confirm(id,return_id, title) {
	    createPopup(
	        'Confirm', // Heading
	        'Are You Sure! You Want to delete.', // Message
	        'OK', // Text for OK button
	        'Cancel', // Text for Cancel button
	        function() {$.ajax({
				url: 'AjaxFolder/AjaxDelete.jsp',
				data: 'id=' + id + '&return_id=' + return_id + '&type=' + title,
				type: 'post',
				success: function(msg) {
					var check = $("#check_name").val();
					if (check === "true") {
						successAlert('Success '+ title + ' Deleted Successfully.');
					} else {
						
					}
				}
			});
	        },
	        function() {
	            alert('ok');
	            // On OK function
	            console.log('OK button clicked');

	        },
	        function() {
	            alert('cancel');
	            // On Cancel function
	            console.log('Cancel button clicked');
	        },
	        function() {
	            alert('close');
	            // On Close function
	            console.log('Popup closed');
	        }
	    );
	}
	</script>



</body>

</html>