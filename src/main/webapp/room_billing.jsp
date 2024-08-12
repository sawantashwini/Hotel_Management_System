<%@page import="com.service.*"%>
<%@page import="com.dto.*"%>
<%@page import="java.util.ArrayList"%>
<%@include file="include/head.jsp"%>

<body onload="getDays();">
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->

	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  Main Start -->
	<main id="main" class="main">
		<%@include file="include/breadcrumbs.jsp"%>

		<%
		int bill_id = Integer.parseInt(request.getParameter("bill_id") == null ? "0" : request.getParameter("bill_id"));
		RoomBookingService service = new RoomBookingService();
		RoomBookingDto dto = service.getBookedRoomInfoById(bill_id, config, request);

		String invoice = service.getMaxInvoiceNo(current_date, request, config);

		int max_invoice_id = 1;

		if (invoice != null) {
			max_invoice_id = Integer.parseInt(invoice);
			max_invoice_id = max_invoice_id + 1;
		}
		%>


		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1><%=dto.getStatus().equalsIgnoreCase("complete") ? "Edit Room Bill" : "Room Bill"%>
					<input id="page_status" type="hidden" value="<%=dto.getStatus()%>">

				</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card" style="border-radius: 20px;">
						<div class="card-body">
							<br />

							<h5
								style="text-align: center; color: #f03c02; margin-bottom: 15px;">
								Order Details</h5>


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
														value="1" /> <label for="item_rate">Item Quantity</label>


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
										float total = 0;
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
										total = total + total_price;
										}
										%>

									</tbody>

									<tfoot id="tfoot">
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td><span id="total_tab_qty">Total</span></td>

											<td><span id="total_tab_price"><%=total%></span></td>

										</tr>
									</tfoot>
								</table>
							</div>


							<h5
								style="text-align: center; color: #f03c02; margin-bottom: 15px;">
								Booking Details</h5>



							<!-- Form Start Second-->
							<form class="row g-3 needs-validation" autocomplete="off"
								novalidate>
								<!-- Room Detail Start -->


								<div class="col-md-2">
									<div class="control form-floating">
										<input type="text" class="form-control" id="invoice_no"
											<%if (dto.getInvoice_no().equals("")) {%>
											value="<%=max_invoice_id%>" <%} else {%>
											value="<%=dto.getInvoice_no()%>" <%}%>
											placeholder="Invoice No."> <label for="invoice_no">Invoice
											No.</label>
										<div class="invalid-feedback">Please, enter Invoice No.!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" id="extra_bed"
											value="<%=dto.getExtra_bed()%>"> <input type="text"
											class="form-control" id="room_no" name="Room_no"
											value="<%=dto.getRoom_no()%>" placeholder="Room No." readonly>
										<label for="room_no">Room No.</label>
										<div class="invalid-feedback">Please, enter Room No.!</div>
									</div>
								</div>

								<div class="col-md-1">
									<div class="row d-flex d-flex justify-content-center ">
										<div class="form-check mt-2">
											<input class="form-check-input" type="checkbox" id="regular"
												name="Regular" onclick="checkCustAvail();"
												<%if (dto.getCust_id_fk() > 0) {%> value="Yes"
												checked="checked" <%}%>> <label
												class="form-check-label mt-1" for="regular"
												style="font-size: 13px;">Regular</label>
										</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control"
											onchange="checkCustAvail();" id="name" placeholder="name"
											value="<%=dto.getCust_name()%>"> <label for="name">Name</label>
										<div class="invalid-feedback">Please, enter name!</div>
									</div>
								</div>

								<input type="hidden" id="customer_id_fk"
									value="<%=dto.getCust_id_fk()%>"> <input type="hidden"
									id="user_id_fk" value="<%=user_id%>"> <input
									type="hidden" id="bill_id_fk" value="<%=dto.getId()%>">
								<input type="hidden" id="room_id_fk"
									value="<%=dto.getRoom_id_fk()%>"> <input type="hidden"
									id="session_year" value="<%="2023-24"%>"> <input
									type="hidden" id="flag"
									value="<%=dto.getStatus().equalsIgnoreCase("complete") ? "update bill" : "complete bill"%>">

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="mobile_no"
											placeholder="mobile" value="<%=dto.getCust_mobile()%>">
										<label for="mobile">Mobile No.</label>
										<div class="invalid-feedback">Please, enter mobile no!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="address"
											value="<%=dto.getCust_address()%>" placeholder=" address">
										<label for="address">Address</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="gst_no"
											value="<%=dto.getCust_gst_no()%>" placeholder=" Gst_no">
										<label for="gst_no">Gst no</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="company_name"
											value="<%=dto.getCompany_name()%>" placeholder="Company Name">
										<label for="company_name">Company Name</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="dob"
											value="<%=dto.getDob()%>" placeholder="Date of Birth">
										<label for="dob"> Date of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of
											Birth !</div>
									</div>
								</div>
								<div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa" 
											value="<%=dto.getDoa()%>" placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of
											Anniversary !</div>
									</div>
								</div>
								<!-- Customer Detail End -->


								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="cust_old_due" placeholder="Due Amount" step="0.01"
											value="<%=dto.getCust_old_due()%>"> <label
											for="cust_old_due">Old Due</label>
									</div>
								</div>




								<div class="col-md-3">
									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="check_in_time" name="check_in_time"
											value="<%=dto.getCheck_in_time()%>"
											placeholder="check_in_time" readonly> <label
											for="check_in_time">Check In</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input required type="datetime-local" class="form-control"
											id="check_out_time" onblur="validateCheckOutDate();"
											value="<%=dto.getCheck_out_time()%>"
											placeholder="Check Out Time" onchange="getDays(false);">
										<label for="check_out_time">Check Out</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>
								<div <%if (!dto.getExtra_bed().equalsIgnoreCase("Yes")) {%>
									style="display: none;" <%} else {%> class="col-md-3" <%}%>>
									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="extra_bed_days" onblur="validateExtraBedDays();"
											name="Extra_bed_days" value="<%=dto.getExtra_bed_days()%>"
											onchange="getBedDays(false);" placeholder="Extra Bed Days">
										<label for="extra_bed_days">Extra Bed Days</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="no_of_days"
											placeholder="No.Of Days" required> <label
											for="no_of_days"> No.Of Days</label>
										<div class="invalid-feedback">Please, enter No.Of Days!</div>
									</div>
								</div>

								<div <%if (!dto.getExtra_bed().equalsIgnoreCase("Yes")) {%>
									style="display: none;" <%} else {%> class="col-md-3" <%}%>>
									<div class="control form-floating">
										<input type="text" class="form-control" id="no_of_bed_days"
											placeholder="No.Of Bed Days" required> <label
											for="no_of_days"> No.Of Bed Days</label>
										<div class="invalid-feedback">Please, enter No.Of Bed
											Days!</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="room_rent"
											name="Room_rent" value="<%=dto.getRoom_rent()%>"
											placeholder="Room Rent" readonly> <label
											for="room_rent">Room Rent</label>
										<div class="invalid-feedback">Please, enter Room Rent !</div>
									</div>
								</div>
								<div <%if (!dto.getExtra_bed().equalsIgnoreCase("Yes")) {%>
									style="display: none;" <%} else {%> class="col-md-3" <%}%>>
									<div class="control form-floating">
										<input readonly type="number" class="form-control" step="0.01"
											id="extra_bed_amt" value="<%=dto.getExtra_bed_amt()%>"
											readonly placeholder="Extra Bed Amount"> <label
											for="extra_bed_amt">Extra Bed Amount</label>
										<div class="invalid-feedback">Please, enter Extra Bed
											Amount!</div>
									</div>
								</div>


								<input type="hidden" class="form-control" id="session_year"
									name="session_year" value="0<%-- <%=current_session%> --%>">

								<!-- Food And Payment Detail Start -->
								<h5
									style="text-align: center; color: #f03c02; margin-bottom: 15px;">
									Billing Details</h5>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="total_room_rent"
											placeholder="Total Room Rent" readonly> <label
											for="total_room_rent"> Total Room Rent</label>
										<div class="invalid-feedback">Please, enter Total Room
											Rent!</div>
									</div>
								</div>

								<div <%if (!dto.getExtra_bed().equalsIgnoreCase("Yes")) {%>
									style="display: none;" <%} else {%> class="col-md-3" <%}%>>
									<div class="control form-floating">
										<input readonly type="number" class="form-control" step="0.01"
											id="total_extra_bed_amt" placeholder="Extra Bed Amount">
										<label for="extra_bed_amt">Total Extra Bed Amount</label>
										<div class="invalid-feedback">Please, enter Extra Bed
											Amount!</div>
									</div>
								</div>


								<%
								int[] Arrper = {0, 5, 12, 18, 28};
								int defaultRoomPer = 12;
								float selectedPer = !dto.getStatus().equalsIgnoreCase("complete") ? defaultRoomPer : dto.getRoom_gst_per();
								%>

								<div class="col-md-3">
									<div class="form-floating control">
										<select class="form-select" id="room_gst_per"
											onchange="findRoomGst(false);" aria-label="working post">
											<%
											for (int per : Arrper) {
											%>
											<option value="<%=per%>" <%if (per == selectedPer) {%>
												selected <%}%>><%=per%>%
											</option>
											<%
											}
											%>

										</select> <label for="room_gst_per">Room GST (%)</label>
										<div class="invalid-feedback">Please, enter Room GST
											(%)!</div>
									</div>
								</div>

								<input type="hidden" id="room_gst_amt" name="Room_gst_amt">




								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" step="0.01"
											id="total_room_amt_with_gst" placeholder="Room Gst Amount"
											readonly> <label for="total_room_amt_with_gst">Total
											Room Amount </label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="food_amt" name="Food_amt" value="<%=dto.getFood_amt()%>"
											placeholder="Food Amount" step="0.01"> <label
											for="food_amt">Food Amount</label>
										<div class="invalid-feedback">Please, enter Food Amount!</div>
									</div>
								</div>



								<%
								int defaultFoodPer = 5;
								float selectedFoodPer = !dto.getStatus().equalsIgnoreCase("complete") ? defaultFoodPer : dto.getFood_gst_per();
								%>
								<div class="col-md-3">
									<div class="form-floating control ">
										<select class="form-select" id="food_gst_per"
											onchange="findFoodGst(false);" aria-label="working post">

											<%
											for (int per : Arrper) {
											%>
											<option value="<%=per%>" <%if (per == selectedFoodPer) {%>
												selected <%}%>><%=per%>%
											</option>
											<%
											}
											%>
										</select> <label for="food_gst_per">Food GST (%)</label>
										<div class="invalid-feedback">Please, enter Food GST
											(%)!</div>
									</div>
								</div>

								<input type="hidden" id="food_gst_amt" name="Food_gst_amt">


								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" step="0.01" class="form-control"
											id="total_food_amt" name="Total_food_amt"
											placeholder="Total Food Amount"> <label
											for="total_food_amt">Total Food Amount</label>
										<div class="invalid-feedback">Please, enter Total Food
											Amount!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="net_amt" step="0.01" placeholder="Net Amount"> <label
											for="net_amt">Net Amount</label>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="advance_amt" name="Advance_amt" step="0.01"
											placeholder="Advance Amount"
											value="<%=dto.getAdvanced_paid_amt()%>"> <label
											for="advance_amt">Advance Amount</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="dis_amt_room"
											onchange="findFinalAmt(false);"
											value="<%=dto.getDis_amt_room()%>"
											placeholder="Discount Amount" step="0.01"> <label
											for="dis_amt"> Room Discount Amount </label>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="dis_amt_food"
											onchange="findFinalAmt(false);"
											value="<%=dto.getDis_amt_food()%>"
											placeholder="Discount Amount" step="0.01"> <label
											for="dis_amt">Food Discount Amount </label>
									</div>
								</div>



								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="final_amt" step="0.01" value="<%=dto.getFinal_amt()%>"
											placeholder="Final Amount" required> <label
											for="final_amt"> Final Amount</label>
										<div class="invalid-feedback">Please, enter Final Amount</div>
									</div>
								</div>

								<!-- Cash Amount Start -->
								<div class="col-md-3" id="cash_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent();" id="cash_amount"
											name="Cash_amount" placeholder="Cash Amount"
											value="<%=dto.getBill_cash_amount() == 0.0 ? dto.getFinal_amt() : dto.getBill_cash_amount()%>"
											required> <label for="cash_amount">Cash
											Amount</label>
									</div>
								</div>
								<!-- Cash Amount end -->

								<!-- Online Amount Start -->
								<div class="d-none" id="online_amt_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent()" id="online_amount"
											name="Online_amount" placeholder="Online Amount"
											value="<%=dto.getBill_online_amount()%>" required> <label
											for="online_amount">Online Amount</label>
									</div>
								</div>
								<!-- Online Amount end -->

								<!-- Paid Amount Start -->
								<div class="d-none" id="paid_block">
									<div class="control form-floating">
										<input readonly type="number" step="0.01"
											style="background-color: #e7ededad;" class="form-control"
											id="paid_amount" value="<%=dto.getBill_paid_amt()%>"
											name="Paid_amount" placeholder="Paid Amount" required>
										<label for="paid_amount">Paid Amount</label>
									</div>
								</div>
								<!-- Paid Amount end -->

								<!-- Remaining Amount Start -->
								<div class="col-md-3" id="remaining_block">
									<div class="control form-floating">
										<input readonly type="number"
											style="background-color: #e7ededad;" class="form-control"
											id="remaining_amount" placeholder="Remaining Amount"
											value="<%=dto.getFinal_amt() - dto.getBill_paid_amt()%>"
											step="0.01"> <label for="remaining_amount">Remaining
											Amount</label>
									</div>
								</div>
								<!-- Remaining Amount end -->

								<div class="d-none" id="online_block">
									<!-- Online Date Start-->
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="date" class="form-control"
												placeholder="Online Date" id="online_date"
												value="<%=dto.getBill_online_date()%>" name="Online_date">
											<label for="online_date">Online Date</label>
											<div class="invalid-feedback">Please, Enter Online
												Date!</div>
										</div>
									</div>
									<!-- Online Date End -->

									<!-- Online way start -->
									<div class="col-md-3">
										<div class="control form-floating mb-3">
											<select class="form-select" id="online_way" name="Online_way"
												aria-label="working post">
												<option value="N/A" Selected='selected'>Select
													Online way</option>
												<%
												for (String online_way : Arrays.asList("Online", "Phonepay", "Gpay", "Paytm", "Cheque")) {
												%>

												<option value="<%=online_way%>"
													<%=online_way.equalsIgnoreCase(dto.getBill_online_way()) ? "selected" : ""%>><%=online_way%></option>

												<%
												}
												%>


											</select> <label for="online_way">Select Online Way</label>
										</div>
									</div>
									<!-- Online way end -->

									<div class="col-md-3 refresh-container mb-3"
										data-add="add_bank.jsp" data-list="list_bank">
										<div class="control form-floating refresh-input">
											<input type="text" class="form-control" id="bank_name"
												placeholder="Select Bank"
												value="<%=dto.getBill_bank_name() == null ? "" : dto.getBill_bank_name()%>">
											<input type="hidden" id="bank_id_fk" name="Bank_id_fk"
												value="<%=dto.getBill_bank_id_fk()%>"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>


									<!-- Online Remark Start -->
									<div class="col-md-3">
										<div class="control form-floating">
											<input class="form-control" id="online_remark"
												value="<%=dto.getBill_online_remark() == null ? "" : dto.getBill_online_remark()%>"
												name="Online_remark" placeholder="Online Remark"> <label
												for="online_remark">Online Remark</label>
										</div>
									</div>
									<!-- Online Remark End -->

								</div>
								<!-- *** Online Block End *** -->


								<h6 class="text-center mt-2 card-title">Payment Mode</h6>

								<div class="container d-flex justify-content-center">
									<div class="col-md-3">
										<div class="d-flex justify-content-around">
											<%
											String paymentMode = dto.getBill_payment_mode();
											%>

											<%
											for (String mode : Arrays.asList("Cash", "Online", "Both")) {
											%>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
													<%=mode.equals(paymentMode) || mode.equals("Cash") ? "checked" : ""%>
													value="<%=mode%>" onclick="checkPaymentMode();"> <label
													class="form-check-label mt-1"
													for="<%=mode.toLowerCase()%>_mode"> <%=mode%>
												</label>
											</div>
											<%
											}
											%>
										</div>
									</div>
								</div>

								<input type="hidden" id="payment_mode" name="Payment_mode"
									value="<%=paymentMode == null || paymentMode.equals("") ? "Cash" : paymentMode%>" />



								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"><%=dto.getBill_remark() == null ? "" : dto.getBill_remark()%></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>


								<div class="text-center">
									<button class="submit-btn" onsubmit="paidAmountEvent()">Submit</button>
								</div>

							</form>
							<!-- Form end Second-->



						</div>
					</div>
				</div>

			</div>
		</section>
	</main>
	<!-- End main -->

	<br />
	<br />






	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->
	<script src="js/room_billing.js" type="text/javascript"></script>

	<script type="text/javascript">
		window
				.addEventListener(
						"load",
						function() {
							//******** Handle cutomer inputs*******
							const customer_id_fk = document
									.getElementById("customer_id_fk").value;
							const nameInput = document.getElementById("name");
							const mobileNoInput = document
									.getElementById("mobile_no");
							const addressInput = document
									.getElementById("address");
							const gstNoInput = document
									.getElementById("gst_no");
							const companyNameInput = document
									.getElementById("company_name");
							const dobNameInput = document
							.getElementById("dob");
							const doaNameInput = document
							.getElementById("doa");

							if (customer_id_fk > 0) {
								// Customer is selected, disable inputs
								nameInput.readOnly = true;
								mobileNoInput.readOnly = true;
								addressInput.readOnly = true;
								gstNoInput.readOnly = true;
								companyNameInput.readOnly = true;
								dobNameInput.readOnly = true;
								doaNameInput.readOnly = true;
							} else {
								// Customer is not selected, set inputs as required
								nameInput.setAttribute("required", "true");
								mobileNoInput.setAttribute("required", "true");
								addressInput.setAttribute("required", "true");
								/* gstNoInput.setAttribute("required", "true");
								companyNameInput.setAttribute("required", "true");
								dobNameInput.setAttribute("required", "true");
								doaNameInput.setAttribute("required", "true"); */

							}

							//******** Handle check out time if empty *******
							if ($("#check_out_time").val() === "") {
								var currentDate = new Date();
								var formattedDateTime = currentDate
										.toLocaleString('en-CA', {
											year : 'numeric',
											month : '2-digit',
											day : '2-digit',
											hour : '2-digit',
											minute : '2-digit',
											hour12 : false
										});
								document.getElementById("check_out_time").value = formattedDateTime
										.replace(/,/g, '');

							}

							// Call getDays function
							getDays(true);

							loadPaymentMode();
							$("#ins_btn").on("click", insertItem);
							$("#ins_btn").on("blur", function() {
								// Optionally, you can add a delay to prevent immediate execution on blur
								setTimeout(insertItem, 100);
							});
						});
	</script>




	<script>
		$('#check_out_time').blur(function() {
			getDays();
		});
	</script>

</body>
</html>