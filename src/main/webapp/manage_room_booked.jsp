
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
			<h1 id="page_title">Room Booked</h1>
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
									<th  width="30px">S.No.</th>
									<th  width="50px">Room No.</th>
									<th  width="70px">Room Name</th>
									<th  width="50px">Booking</th>
									<th  width="30px">Order</th>
									<th  width="30px">Billing</th>
									<th  width="200px">Name</th>
									<th  width="70px">Mobile</th>
									<th  width="50px">Advanced</th>
									<th  width="50px">Room Rent</th>
									<th  width="50px">Food Amt</th>

									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
									<th  width="30px">Edit</th>
									<%} %>
									
									<%if(user_head_dto.getDelete_module().equalsIgnoreCase("Yes")){ %>
									<th  width="10%">Del.</th>
									<%} %>


								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								RoomBookingService ser = new RoomBookingService();
								ArrayList<RoomBookingDto> list1 = ser.getAllBookedRoomInfo(config, request);
								for (RoomBookingDto dto : list1) {
							
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td class="main-color"><%=dto.getRoom_no()%></td>
									<td class="main-color"><%=dto.getRoom_no()%></td>
									<td>
										<%
										if (dto.getStatus().equalsIgnoreCase("Available")) {
										%> <a href="room_booking.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a> <%
										} else{
										%> <span>Booked</span> <%
										} 
										%>

									</td>
									<td>
										<%
										if (dto.getStatus().equalsIgnoreCase("Booked")) {
										%> <a
										href="room_order.jsp?room_id=<%=dto.getRoom_id_fk()%>&bill_id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a> <%
										}
										%>

									</td>
									<td>
										<%
										if (dto.getStatus().equalsIgnoreCase("Booked")) {
										%> <a href="room_billing.jsp?bill_id=<%=dto.getId()%>"><i
											class="bi bi-receipt"></i></a> <%
										}
										%>

									</td>


									<td><%=dto.getCust_name()%></td>
									<td><%=dto.getCust_mobile()%></td>
									<td><%=dto.getAdvanced_paid_amt()%></td>
									<td><%=dto.getRoom_rent()%></td>
									<td><%=dto.getFood_amt()%></td>


									<%if(user_head_dto.getUpdate_module().equalsIgnoreCase("Yes")){ %>
									<td><a
										href="edit_room_booking.jsp?bill_id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>
									<%} %>

									<%
									if(user_head_dto.getDelete_module().equalsIgnoreCase("Yes")) {
									%>
									<td><a
										href="AjaxFolder/AjaxDelete.jsp?room_book_id=<%=dto.getId()%>&cust_id=<%=dto.getCust_id_fk()%>"><i
											class="bi bi-trash"></i></a></td>
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