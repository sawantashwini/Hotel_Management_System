
<%@page
	import="javax.print.attribute.standard.PrinterMoreInfoManufacturer"%>
<%@include file="include/head.jsp"%>
</head>
<body onload="findTotal();">

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->


	<!--  main start-->
	<main id="main" class="main">

		<%@include file="include/breadcrumbs.jsp"%>

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Food Order</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card">
						<div class="card-body">
							<br /> <br>

							<%
							int bill_id = Integer.parseInt(request.getParameter("bill_id") == null ? "0" : request.getParameter("bill_id"));
							int room_id = Integer.parseInt(request.getParameter("room_id") == null ? "0" : request.getParameter("room_id"));
							RoomService service = new RoomService();
							RoomDto dto = service.getRoomInfoById(room_id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form action="" method="post" class="row g-3 needs-validation"
								novalidate>


								<!--  Date start-->
								<div class="col-md-3 d-none" >
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" value="<%=user_id%>">
										<input type="Date" class="form-control" id="in_date"
											name="In_date" placeholder="in_date"
											value="<%=current_date%>" required> <label
											for="in_date">Date</label>
										<div class="invalid-feedback">Please, enter in_date!</div>
									</div>
								</div>
								<!--  Date End-->

								<!--  Remark start-->
								<div class="col-md-3">
									<div class="control form-floating">

										<input type="text" class="form-control" id="room_no"
											name="Room_no" placeholder="Room No." readonly
											value="<%=dto.getRoom_no()%>" required> <label
											for="room_no">Room No.</label>
										<div class="invalid-feedback">Please, enter Room No.!</div>
									</div>
								</div>
								<!--  End start-->

								<input type="hidden" id="room_id" value="<%=dto.getId()%>" /> <input
									type="hidden" id="bill_id_fk" value="<%=bill_id%>" />

								<!--  Item Table start-->
								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>

												<th>Item code</th>
												<th>Item price</th>
												<th>Order Date</th>
												<th>Item Quantity</th>
												
												<th>Add</th>

											</tr>
										</thead>
										<tbody class="text-center" id="tbody">
											<tr>
												<td>
													<div class="col-md-12 refresh-container mb-3"
														data-add="add_menu_item.jsp" data-list="list_menu_item">
														<div class="control form-floating refresh-input">
															<input type="text" class="form-control" id="item_code"
																placeholder="Item Code"> <input type="hidden"
																id="item_id"> <label for="item_code">Select
																Item Code</label>
															<div class="invalid-feedback">Please, Select Item
																Code!</div>
														</div>

													</div>
												</td>


												<td>
													<div class="control form-floating">
														<input type="hidden" id="item_name"> <input
															type="text" class="form-control" id="item_rate" readonly
															placeholder="Item Rate" /> <label for="item_rate">Item
															Rate</label>
													</div>
												</td>
												<td>
													<div class="control form-floating">
														<input type="date" class="form-control" id="order_date"
															placeholder="Order Date" value="<%=current_date%>" /> <label
															for="order_date">Order Date</label>


													</div>
												</td>
												<td>
													<div class="control form-floating">
														<input type="text" class="form-control" id="item_qty"
															placeholder="Item Quantity" onclick="this.select();"
															value="1" /> <label for="item_rate">Item
															Quantity</label>


													</div>
												</td>
												
												<td class="text-center">
													<div class="control col-12">

														<input type="button" id="ins_btn"
															class="form-control add-icon">




													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!--  Item Table End-->
								<hr>

								<div class="table-responsive" id="table_div_design">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Code</th>
												<th>Item Price</th>
												<th>Order Date</th>
												<th>Item Quantity</th>
												<th>Total Price</th>
												
												<th>Delete</th>

											</tr>
										</thead>
										<tbody id="tbody_design" class="text-center">

											<%
											RoomService ser_room = new RoomService();
											int i = 0;
											ArrayList<OrderItemDto> list_order_item = ser_room.getRoomOrderItemInfoByRoomId(bill_id, config, request);
											for (OrderItemDto dto_item : list_order_item) {
												i++;
											%>



											<tr>
												<td align="center"><%=dto_item.getItem_code()%> <input
													id="item_code<%=i%>" type="hidden" name="Item_code"
													value="ItemCode1"> <input id="item_name<%=i%>"
													type="hidden" name="Item_name"
													value="<%=dto_item.getItem_name()%>"> <input
													id="item_id<%=i%>" type="hidden" name="Item_id"
													value="<%=dto_item.getItem_id_fk()%>"></td>



												<td align="center"><%=dto_item.getItem_rate()%> <input
													id="item_price<%=i%>" type="hidden" name="Item_price"
													value="<%=dto_item.getItem_rate()%>"></td>
<td align="center"><%=LogFileService.changeFormate(dto_item.getOrder_date(), "yyyy-MM-dd", "dd-MM-yyyy")%><input
													id="order_date" type="hidden" name="Order_date"
													value="<%=LogFileService.changeFormate(dto_item.getOrder_date(), "yyyy-MM-dd", "dd-MM-yyyy")%>">
												</td>
												<td align="center"><%=dto_item.getItem_qty()%> <input
													id="item_qty<%=i%>" type="hidden" name="Item_qty"
													value="<%=dto_item.getItem_qty()%>"></td>
												<%
												float total_price = dto_item.getItem_rate() * dto_item.getItem_qty();
												%>
												<td align="center"><%=total_price%> <input
													id="total_price_with_qty" type="hidden"
													name="Total_price_with_qty" value="<%=total_price%>">
												</td>
												
												<td><i class="bi bi-trash main-color"
													onclick="deleteItem('<%=dto_item.getId()%>');"> </i></td>
											</tr>



											<%
											}
											%>

										</tbody>

										<tfoot id="tfoot">
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td><span id="total_tab_qty">Total</span></td>

												<td><span id="total_tab_price"></span></td>
												
											</tr>
										</tfoot>
									</table>
								</div>

							</form>
							<!-- End floating Labels Form -->

						</div>

					</div>
				</div>
			</div>
			<div id="ingredients_item"></div>

		</section>

	</main>
	<!-- End main -->
	<br />
	<br />

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<script src="js/room_order.js" type="text/javascript"></script>
	<script type="text/javascript">
		function findDuplicateItem() {
			return 0;
		}
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#ins_btn").on("click", insertItem);
			$("#ins_btn").on("blur", function() {
				setTimeout(insertItem, 100);
			});
		});
	</script>


</body>
<!--  Body End-->
</html>