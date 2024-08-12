
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
			<h1 id="page_title">Manage Room</h1>
		</div>

		

		<section class="section">
			<div class="row">
				<div class="card ">
					<div class="card-body table-responsive">
						<br>
						<!-- table Start-->
						<table id="examples" class="table hover table-responsive nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="5%">S.No.</th>
									<th style="text-align: center;" width="10">Room No.</th>
									<th style="text-align: center;" width="25%">Room Name</th>
									<th style="text-align: center;" width="10">Booking</th>
									<th style="text-align: center;" width="10">Order</th>
									<th style="text-align: center;" width="10">Billing</th>
									<th style="text-align: center;" width="10">Status</th>
									<th style="text-align: center;" width="10%">Rent</th>
									<th style="text-align: center;" width="10%">Rent Double</th>
									<th style="text-align: center;" width="10%">Rent Three</th> 
									<th style="text-align: center;" width="10%">Rent Fourth</th> 
									
									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
										<th style="text-align: center;" width="10%">Edit</th>
									<%} %>
									
									
									
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								RoomService ser = new RoomService();
								ArrayList<RoomDto> list1 = ser.getRoomInfo(config, request);
								for (RoomDto dto : list1) {
							
								%>
								<tr <%if(dto.getStatus().equalsIgnoreCase("Booked")){ %>
									style="background: #41e92285;"<%} 
								  else { %>
									style="background: #fff;" <% }%>>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td class="main-color"><%=dto.getRoom_no()%></td>
									<td class="main-color"><%=dto.getRoom_name()%></td>
									<td>
										<%
										if (dto.getStatus().equalsIgnoreCase("Available")) {
										%>
									<a href="room_booking.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a>
											<%
										} else{
										%>
										<span>Booked</span>
												<%
										} 
										%>		
									
											</td>
									<td>
										<%
										if (dto.getStatus().equalsIgnoreCase("Booked")) {
										%>
									<a href="room_order.jsp?room_id=<%=dto.getId()%>&bill_id=<%=dto.getBook_id_fk()%>"><i
											class="bi bi-pencil-square"></i></a>
										
											<%
										}
										%>	
										
											</td>
													<td>
										<%
										if (dto.getStatus().equalsIgnoreCase("Booked")) {
										%>
									
											<a href="room_billing.jsp?bill_id=<%=dto.getBook_id_fk()%>"><i
											class="bi bi-receipt"></i></a>
											<%
										}
										%>	
										
											</td>
									<td><%=dto.getStatus()%></td>
									
									<td><%=dto.getRent()%></td>
									<td><%=dto.getRent_double()%></td>
									<td><%=dto.getRent_three()%></td>
									<td><%=dto.getRent_fourth()%></td>
									
									
									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
										<td><a
										href="edit_room.jsp?id=<%=dto.getId()%>"><i
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