
<%@include file="include/head.jsp"%>

<body>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->

	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  Main Start -->
	<main id="main" class="main">

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Room Booking</h1>
			</div>
		</div>
		<div id="name_avail_resposnse"></div>
		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card" style="border-radius: 20px;">
						<div class="card-body">
							<br />
							<%
							int bill_id = Integer.parseInt(request.getParameter("bill_id") == null ? "0" : request.getParameter("bill_id"));
							RoomBookingService service = new RoomBookingService();
							RoomBookingDto dto = service.getBookedRoomInfoById(bill_id, config, request);
							%>
							<h5
								style="text-align: center; color: #f03c02; margin-bottom: 15px;">
								Room Details</h5>



							<!-- Form Start Second-->
							<form action="RoomBookingServlet" method="post"
								autocomplete="off" enctype="multipart/form-data"
								class="row g-3 needs-validation" novalidate>
								<!-- Room Detail Start -->

								<input type="hidden" name="Id" id="room_booking_id"
									value="<%=bill_id%>"> <input type="hidden"
									name="User_id_fk" id="user_id_fk" value="<%=user_id%>">
								<input type="hidden" name="Room_id_fk" id="room_id_fk"
									value="<%=dto.getRoom_id_fk()%>">


								<div class="col-md-2">
									<div class="control form-floating">
										<input type="text" class="form-control" id="room_no"
											name="Room_no" value="<%=dto.getRoom_no()%>"
											placeholder="Room No." readonly="readonly"> <label
											for="room_no">Room No.</label>
										<div class="invalid-feedback">Please, enter Room No.!</div>
									</div>
								</div>

								<div class="col-md-2">
									<div class="control form-floating">

										<select class="form-select" id="room_type"
											onchange="findRoomRent(this.value);" name="Room_type"
											aria-label="working post" required>
											<option selected disabled value="">Select Room Type</option>
											<option value="Single Person"
												<%if (dto.getRoom_type().equalsIgnoreCase("Single Person")) {%>
												selected <%}%>>Single Person</option>
											<option value="Two Person"
												<%if (dto.getRoom_type().equalsIgnoreCase("Two Person")) {%>
												selected <%}%>>Two Person</option>
											<option value="Three Person"
												<%if (dto.getRoom_type().equalsIgnoreCase("Three Person")) {%>
												selected <%}%>>Three Person</option>
											<option value="Fourth Person"
												<%if (dto.getRoom_type().equalsIgnoreCase("Fourth Person")) {%>
												selected <%}%>>Fourth Person</option>

										</select> <label for="room_type">Select Room Type</label>
										<div class="invalid-feedback">Please, select Room Type</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="room_rent"
											name="Room_rent" required value="<%=dto.getRoom_rent()%>"
											placeholder="Room Rent"> <label for="room_rent">Room
											Rent</label>
										<div class="invalid-feedback">Please, enter Room Rent !</div>
									</div>
								</div>

								<div class="col-md-2">
									<div class="control form-floating">

										<select class="form-select" id="extra_bed"
											onchange="changeExtraBed();" name="Extra_bed"
											aria-label="working post" required>

											<option value="No"
												<%if (dto.getExtra_bed().equalsIgnoreCase("No")) {%>
												selected <%}%>>No</option>
											<option value="Yes"
												<%if (dto.getExtra_bed().equalsIgnoreCase("Yes")) {%>
												selected <%}%>>Yes</option>


										</select> <label for="extra_bad">Select Extra Bed </label>
										<div class="invalid-feedback">Please, Select Extra Bed</div>
									</div>
								</div>

								<%
								if (dto.getExtra_bed().equalsIgnoreCase("Yes")) {
								%>

								<div class="col-md-3" id="extra_bed_amt_block">
									<div class="control form-floating">
										<input type="number" class="form-control" id="extra_bed_amt"
											name="Extra_bed_amt" value="<%=dto.getExtra_bed_amt()%>"
											placeholder="Extra Bed Amt"> <label
											for="extra_bed_amt">Extra Bed Amt</label>
										<div class="invalid-feedback">Please, select Extra Bed
											Amt</div>
									</div>
								</div>
								<%
								}
								%>
								<div class="col-md-3">

									<div class="control form-floating">
										<input required type="datetime-local" class="form-control"
											id="check_in_time" name="Check_in_time"
											value="<%=dto.getCheck_in_time()%>"
											placeholder="check_in_time"> <label
											for="check_in_time">Check In</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>

								<div class="col-md-3">

									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="check_out_time" name="Check_out_time"
											onblur="validateCheckOutDate();"
											value="<%=dto.getCheck_out_time()%>"
											placeholder="Check Out Time"> <label
											for="check_out_time">Check Out</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>
<%
								if (dto.getExtra_bed().equalsIgnoreCase("Yes")) {
								%>

								<div class="col-md-3 " id="extra_bed_days_block">

									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="extra_bed_days" onblur="validateExtraBedDays();"
											name="Extra_bed_days" value="<%=dto.getExtra_bed_days()%>" placeholder="Extra Bed Days"> <label
											for="extra_bed_days">Extra Bed Days</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>
								<%
								}
								%>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="source"
											name="Source" placeholder="Source"
											value="<%=dto.getSource()%>"> <label for="source">
											Source</label>
										<div class="invalid-feedback">Please, enter Source!</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="destination"
											name="Destination" placeholder="Destination"
											value="<%=dto.getDestination()%>"> <label
											for="destination"> Destination</label>
										<div class="invalid-feedback">Please, enter Destination!</div>
									</div>
								</div>
								<div class="col-md-9"></div>

								<!-- Room Detail End -->



								<h5
									style="text-align: center; color: #f03c02; margin-bottom: 15px;">
									Customer Details</h5>

								<div class="col-md-2 col-lg-2">
									<div class="row d-flex d-flex justify-content-center ">
										<div class="form-check col-4 mt-2">
											<input class="form-check-input" type="checkbox" id="regular_check"
											name="Regular" value="Yes" 	onclick="checkCustAvail();checkValueRegular();"
												<%if (dto.getCust_id_fk() > 0) {%> checked <%}%>
												> <label
												class="form-check-label mt-1" for="regular"
												style="font-size: 13px;"> Regular </label>
										</div>
										
										<!-- <input type="hidden"  id="regular" name="Regular"> -->

									</div>
								</div>
								
								
								




								<div class="col-md-3">

									<div
										class="control form-floating d-flex justify-content-center">

										<input type="text" class="form-control" list="datalist_name"
											id="search_name" placeholder="Name"
											onblur="findCustInfoByName(this.value);" autocomplete="off" />
										<input type="hidden" value="0">
										<datalist id="datalist_name">

											<%
											CustomerService service1 = new CustomerService();
											ArrayList<CustomerDto> list3 = service1.getCustomerInfo(config, request);
											for (CustomerDto dto_path : list3) {
											%>
											<option value="<%=dto_path.getName()%>" />
											<%
											}
											%>
										</datalist>
										<label for="search_name">Search By Name</label>

										<div style="width: 30px; margin-left: 8px;">
											<div class="mt-2" title="Reset Customer information">
												<i class="bi bi-x-square main-color"
													onclick="removeCustDet();"></i>
											</div>

										</div>

									</div>



								</div>
								<!-- Name -->


								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="cust_name"
											name="Cust_name" value="<%=dto.getCust_name()%>"
											placeholder="Customer Name"> <label for="cust_name">Customer
											Name</label>
										<div class="invalid-feedback">Please, enter Customer
											Name!</div>
									</div>
								</div>

								<input type="hidden" id="cust_id_fk" name="Cust_id_fk" value="<%=dto.getCust_id_fk()%>">


								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="cust_mobile"
											value="<%=dto.getCust_mobile()%>" name="Cust_mobile"
											placeholder="Customer Mobile"> <label
											for="cust_mobile">Customer Mobile</label>
										<div class="invalid-feedback">Please, enter Customer
											Mobile!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="cust_address"
											value="<%=dto.getCust_address()%>" name="Cust_address"
											placeholder="Customer Address"> <label
											for="cust_address">Customer Address</label>
										<div class="invalid-feedback">Please, enter Customer
											Address!</div>
									</div>
								</div>



								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="cust_gst_no"
											value="<%=dto.getCust_gst_no()%>" name="Cust_gst_no"
											placeholder="Customer GST No."> <label
											for="cust_gst_no">Customer GST No.</label>
										<div class="invalid-feedback">Please, enter Customer GST
											No.!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="company_name"
											value="<%=dto.getCompany_name()%>" name="Company_name"
											placeholder="Company Name"> <label for="company_name">Company
											Name</label>
									</div>
								</div>

 <div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="dob"
											name="Dob" value="<%=dto.getDob() %>" placeholder="Date of Birth">
										<label for="dob"> Date of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of Birth
											!</div>
									</div>
								</div>
									<div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa"
											name="Doa" value="<%=dto.getDoa() %>" placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of Anniversary
											!</div>
									</div>
								</div>

								<input type="hidden" class="form-control" id="session_year"
									name="Session_year" value="<%=dto.getSession_year()%>">


								<!-- Cash Amount Start -->
								<div class="col-md-3" id="cash_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent()" id="cash_amount"
											name="Cash_amount" placeholder="Cash Amount"
											value="<%=dto.getAd_cash_amount()%>" required> <label
											for="cash_amount">Cash Amount</label>
									</div>
								</div>
								<!-- Cash Amount end -->

								<!-- Online Amount Start -->
								<div class="d-none" id="online_amt_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent()" id="online_amount"
											name="Online_amount" placeholder="Online Amount"
											value="<%=dto.getAd_online_amount()%>" required> <label
											for="online_amount">Online Amount</label>
									</div>
								</div>
								<!-- Online Amount end -->

								<!-- Paid Amount Start -->
								<div class="d-none" id="paid_block">
									<div class="control form-floating">
										<input readonly type="number" step="0.01"
											style="background-color: #e7ededad;" class="form-control"
											id="paid_amount" name="Paid_amount" placeholder="Paid Amount"
											value="0" required> <label for="paid_amount">Advanced
											Paid</label>
									</div>
								</div>
								<!-- Paid Amount end -->

								<div class="d-none" id="online_block">



									<!-- Online Date Start-->
									<div class="col-md-2 col-6">
										<div class="control form-floating">
											<input type="date" class="form-control"
												placeholder="Online Date" id="online_date"
												value="<%=dto.getAd_online_date()%>" name="Online_date">
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
												for (String online_way : Arrays.asList("Online", "Phonepay", "Paytm", "Gpay", "Cheque")) {
												%>

												<option value="<%=online_way%>"
													<%=online_way.equalsIgnoreCase(dto.getAd_online_way()) ? "selected" : ""%>><%=online_way%></option>

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
												placeholder="Select Bank"> <input type="hidden"
												id="bank_id_fk" name="Bank_id_fk"
												value="<%=dto.getAd_bank_id_fk()%>"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>


									<!-- Online Remark Start -->
									<div class="col-md-6 col-6">
										<div class="control form-floating">
											<input class="form-control" id="online_remark"
												value="<%=dto.getAd_online_remark() == null ? "" : dto.getAd_online_remark()%>"
												name="Online_remark" placeholder="Online Remark"> <label
												for="online_remark">Online Remark</label>
										</div>
									</div>
									<!-- Online Remark End -->

								</div>
								<!-- *** Online Block End *** -->


								<h6 class="text-center mt-2 card-title">Payment Mode</h6>

								<div class="row g-3 d-flex justify-content-center">
									<div class="col-lg-3 col-md-4 mt-0">
										<div class="d-flex justify-content-around">
											<%
											String paymentMode = dto.getAd_payment_mode();
											%>

											<%
											for (String mode : Arrays.asList("Cash", "Online", "Both")) {
											%>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
													<%=mode.equals(paymentMode) ? "checked" : ""%>
													value="<%=mode%>" onclick="checkPaymentMode();"> <label
													class="form-check-label" for="<%=mode.toLowerCase()%>_mode">
													<%=mode%>
												</label>
											</div>
											<%
											}
											%>
										</div>
									</div>
								</div>


								<input type="hidden" id="payment_mode" name="Payment_mode"
									value="Cash" />

								<!-- Document Upload -->
								<div style="text-align: center;">
									<div class="col-md-12">
										<div class="pt-1 pb-1">
											<div style="display: none;">
												<input name="File" type="file" id="zip_file">
											</div>

										</div>
									</div>
									<div class="col-md-12 mt-2 ">
										<label for="zip_file" id="zip_file-label"> <a
											class="btn btn-primary btn-sm"
											title="Choose document Zip file"><i
												class="text-center bi bi-upload"></i></a> <br>Document Zip
										</label>
									</div>
								</div>
								<!-- Document Upload End -->

								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Book Remark"
											id="book_remark" Name="Book_remark" style="height: 70px"><%=dto.getBook_remark() == null ? "N/A" : dto.getBook_remark()%></textarea>
										<label for="book_remark">Booking Remark</label>
										<div class="invalid-feedback">Please, Enter Booking
											Remark!</div>
									</div>
								</div>


								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
								</div>

							</form>
							<!-- Form end Second-->

						</div>
					</div>
				</div>
				<div id="find_room_rent"></div>
				<div id="result_info"></div>

			</div>
		</section>
	</main>

	<br />
	<br />
	<script type="text/javascript">
		window.addEventListener(
						"load",
						function() {

							//******** Handle cutomer inputs*******
							const customer_id_fk = document
									.getElementById("cust_id_fk").value;
							const nameInput = document
									.getElementById("cust_name");
							const mobileNoInput = document
									.getElementById("cust_mobile");
							const addressInput = document
									.getElementById("cust_address");
							const gstNoInput = document
									.getElementById("cust_gst_no");
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
								gstNoInput.readOnly = true;
								companyNameInput.readOnly = true;
								dobNameInput.readOnly = true;
								doaNameInput.readOnly = true;
							} else {
								// Customer is not selected, set inputs as required
								nameInput.setAttribute("required", "true");
								mobileNoInput.setAttribute("required", "true");
								addressInput.setAttribute("required", "true");
							/* 	gstNoInput.setAttribute("required", "true");
								companyNameInput.setAttribute("required", "true");
								dobNameInput.setAttribute("required", "true");
								doaNameInput.setAttribute("required", "true"); */
							}

							//******** Handle check out time if empty *******
							//******** Handle check in time if empty *******
							if ($("#check_in_time").val() === "") {
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
								document.getElementById("check_in_time").value = formattedDateTime
										.replace(/,/g, '');
							}

							// check payment mode
							loadPaymentMode();
							//alert("done");
						});
	</script>


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<script type="text/javascript" src="js/room_booking.js"></script>

</body>
</html>