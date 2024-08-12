
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
			<h1 id="page_title">Available Room</h1>
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
									<th style="text-align: center;" width="10">Room No.</th>
									<th style="text-align: center;" width="25%">Room Name</th>
									<th style="text-align: center;" width="10">Booking</th>
									<th style="text-align: center;" width="10%">Rent</th>
									<th style="text-align: center;" width="10%">Rent Double</th>
									<th style="text-align: center;" width="10%">Rent Three</th> 
									<th style="text-align: center;" width="10%">Rent Fourth</th> 
									
									
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								RoomService ser = new RoomService();
								ArrayList<RoomDto> list1 = ser.getRoomAvailableInfo(config, request);
								for (RoomDto dto : list1) {
							
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td class="main-color"><%=dto.getRoom_no()%></td>
									<td class="main-color"><%=dto.getRoom_name()%></td>
									<td><a href="room_booking.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									
									
									<td><%=dto.getRent()%></td>
									<td><%=dto.getRent_double()%></td>
									<td><%=dto.getRent_three()%></td>
									<td><%=dto.getRent_fourth()%></td>
									
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