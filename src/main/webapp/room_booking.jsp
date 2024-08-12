
<%@page import="com.service.*"%>
<%@page import="com.dto.*"%>
<%@page import="java.util.ArrayList"%>
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
		<%-- 
		<%@include file="include/breadcrumbs.jsp"%> --%>

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
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							RoomService service = new RoomService();
							RoomDto dto = service.getRoomInfoById(id, config, request);
							%>
							<h5
								style="text-align: center; color: #f03c02; margin-bottom: 15px;">
								Room Details</h5>



							<!-- Form Start Second-->
							<form action="RoomBookingServlet" method="post"
								autocomplete="off" enctype="multipart/form-data"
								class="row g-3 needs-validation" novalidate>
								<!-- Room Detail Start -->
								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>"> <input type="hidden"
									name="Room_id_fk" id="room_id_fk" value="<%=id%>">


								<div class="col-md-2">
									<div class="control form-floating">
										<input type="text" class="form-control" id="room_no"
											name="Room_no" value="<%=dto.getRoom_no()%>"
											placeholder="Room No." readonly="readonly" required>
										<label for="room_no">Room No.</label>
										<div class="invalid-feedback">Please, enter Room No.!</div>
									</div>
								</div>

								<div class="col-md-2">
									<div class="control form-floating">

										<select class="form-select" id="room_type"
											onchange="findRoomRent(this.value);" name="Room_type"
											aria-label="working post" required>
											<option selected disabled value="">Select Room Type</option>
											<option value="Single Person">Single Person</option>
											<option value="Two Person">Two Person</option>
											<option value="Three Person">Three Person</option>
											<option value="Fourth Person">Fourth Person</option>

										</select> <label for="room_type">Select Room Type</label>
										<div class="invalid-feedback">Please, select Room Type</div>
									</div>
								</div>


								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="room_rent"
											name="Room_rent" placeholder="Room Rent"> <label
											for="room_rent">Room Rent</label>
										<div class="invalid-feedback">Please, enter Room Rent !</div>
									</div>
								</div>

								<div class="col-md-2">
									<div class="control form-floating">

										<select class="form-select" id="extra_bed"
											onchange="changeExtraBed(this.value);" name="Extra_bed"
											aria-label="working post" required>

											<option value="No">No</option>
											<option value="Yes">Yes</option>


										</select> <label for="extra_bad">Select Extra Bed </label>
										<div class="invalid-feedback">Please, Select Extra Bed</div>
									</div>
								</div>

								<div class="col-md-3 d-none" id="extra_bed_amt_block">
									<div class="control form-floating">
										<input type="number" class="form-control" id="extra_bed_amt"
											name="Extra_bed_amt" value="0" placeholder="Extra Bed Amt"
											required> <label for="extra_bed_amt">Extra
											Bed Amt</label>
										<div class="invalid-feedback">Please, select Extra Bed
											Amt</div>
									</div>
								</div>

	
								<div class="col-md-3">

									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="check_in_time" name="Check_in_time" value="<%=current_date_time%>"
											placeholder="check_in_time" required> <label
											for="check_in_time">Check In</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>

								<div class="col-md-3">

									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="check_out_time" onblur="validateCheckOutDate();"
											name="Check_out_time" placeholder="Check Out Time"> <label
											for="check_out_time">Check Out</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>
	<div class="col-md-3 d-none" id="extra_bed_days_block">

									<div class="control form-floating">
										<input type="datetime-local" class="form-control"
											id="extra_bed_days" onblur="validateExtraBedDays();"
											name="Extra_bed_days" placeholder="Extra Bed Days"> <label
											for="extra_bed_days">Extra Bed Days</label>
										<div class="invalid-feedback"></div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="source"
											name="Source" placeholder="Source"> <label
											for="source"> Source</label>
										<div class="invalid-feedback">Please, enter Source!</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="destination"
											name="Destination" placeholder="Destination"> <label
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
												onclick="checkValueRegular();" name="Regular" value="Regular"> <label
												class="form-check-label mt-1" for="regular_check"
												style="font-size: 13px;"> Regular </label>
										</div>

									</div>

								</div>
								<input type="hidden"  id="regular" name="Regular">
								<!-- <div class="col-md-3 refresh-container refresh-block-none mb-3" data-list="list_customer">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="search_name"
											placeholder="Name" value=""> 
											 <label
											for="search_name">Search By Name</label>

									</div>
								</div> -->


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
											name="Cust_name" placeholder="Customer Name"> 
											<input type="hidden" id="cust_id_fk" name="Cust_id_fk" value="0"><label
											for="cust_name">Customer Name</label>
										<div class="invalid-feedback">Please, enter Customer
											Name!</div>
									</div>
								</div>
	



								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="cust_mobile"
											name="Cust_mobile" placeholder="Customer Mobile"> <label
											for="cust_mobile">Customer Mobile</label>
										<div class="invalid-feedback">Please, enter Customer
											Mobile!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="cust_address"
											name="Cust_address" placeholder="Customer Address"> <label
											for="cust_address">Customer Address</label>
										<div class="invalid-feedback">Please, enter Customer
											Address!</div>
									</div>
								</div>



								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="cust_gst_no"
											name="Cust_gst_no" placeholder="Customer GST No."> <label
											for="cust_gst_no">Customer GST No.</label>
										<div class="invalid-feedback">Please, enter Customer GST
											No.!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="company_name"
											name="Company_name" placeholder="Company Name"> <label
											for="company_name">Company Name</label>
									</div>
								</div>
         <div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="dob"
											name="Dob" placeholder="Date of Birth">
										<label for="dob"> Date of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of Birth
											!</div>
									</div>
								</div>
									<div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa"
											name="Doa" placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of Anniversary
											!</div>
									</div>
								</div>
								<input type="hidden" class="form-control" id="session_year"
									name="Session_year" value="<%="2023-24"%>">


								<!-- Cash Amount Start -->
								<div class="col-md-3 col-6" id="cash_block">
									<div class="control form-floating">
										<input type="number" class="form-control"
											onblur="paidAmountEvent();" id="cash_amount" value="0"
											name="Cash_amount" placeholder="Advanced" step="0.01">
										<label for="cash_amount">Advanced Amount</label>
									</div>
								</div>
								<!-- Cash Amount end -->

								<!-- Online Amount Start -->
								<div class="d-none" id="online_amt_block">
									<div class="control form-floating">
										<input type="number" class="form-control" value="0"
											onblur="paidAmountEvent();" id="online_amount"
											name="Online_amount" placeholder="Online Amount" step="0.01">
										<label for="online_amount">Online Amount</label>
									</div>
								</div>
								<!-- Online Amount end -->

								<!-- Paid Amount Start -->
								<div class="d-none" id="paid_block">
									<div class="control form-floating">
										<input readonly type="number"
											style="background-color: #e7ededad;" class="form-control"
											id="paid_amount" name="Paid_amount" value="0"
											placeholder="Advanced Paid" step="0.01"> <label
											for="paid_amount">Advanced Paid</label>
									</div>
								</div>
								<!-- Paid Amount end -->


								<div class="d-none" id="online_block">

									<!-- Online Date Start-->
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="date" class="form-control"
												placeholder="Online Date" id="online_date"
												name="Online_date"> <label for="online_date">Online
												Date</label>
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
												<option value="N/A">Select Online way</option>
												<option value="online">Online</option>
												<option value="phonepay">Phone Pay</option>
												<option value="paytm">Paytm</option>
												<option value="gpay">Gpay</option>
												<option value="cheque">Cheque</option>
											</select> <label for="online_way">Select Online Way</label>
										</div>
									</div>
									<!-- Online way end -->


									<div class="col-md-3 refresh-container mb-3"
										data-add="add_bank.jsp" data-list="list_bank">
										<div class="control form-floating refresh-input">
											<input type="text" class="form-control" id="bank_name"
												placeholder="Select Bank"> <input type="hidden"
												id="bank_id_fk" name="Bank_id_fk" value="0"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>


									<!-- Online Remark Start -->
									<div class="col-md-3">
										<div class="control form-floating">
											<input class="form-control" id="online_remark"
												name="Online_remark" placeholder="Online Remark"> <label
												for="online_remark">Online Remark</label>
										</div>
									</div>
									<!-- Online Remark End -->

								</div>
								<!-- *** Online Block End *** -->



								<h5 class="text-center card-title">Payment Details</h5>
								<div class="row g-3 d-flex justify-content-center">
									<div class="col-lg-3 col-md-4 mt-0">
										<div class="d-flex justify-content-around">


											<%
											for (String mode : Arrays.asList("Cash", "Online", "Both")) {
											%>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													<%=mode.equalsIgnoreCase("Cash") ? "checked" :"" %>
													name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
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
											id="book_remark" Name="Book_remark" style="height: 70px">N/A</textarea>
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


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<script type="text/javascript" src="js/room_booking.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		changeExtraBed();
	});
	</script>

</body>
</html>