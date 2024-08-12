

<!-- head -->
<%@include file="include/head.jsp"%>
<!-- head end -->
</head>

<body>



	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->




	<div id="result_info"></div>


	<main id="main" class="main">


		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Pay Customer Due</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-12">

					<div class="card" style="border-radius: 25px;">


						<div class="card-body">
							<br /> <br>

							<!-- Floating Labels Form -->
							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							CustomerService service = new CustomerService();
							CustomerDto dto = service.getCustomerInfoById(id, config, request);
							%>
							<form action="CustomerDueServlet" method="post"
								class="row g-3 needs-validation" novalidate>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" value="<%=user_id%>">
										<input type="hidden" id="type" name="Type"
											value="Customer due"> <input type="hidden" name="Id"
											value="<%=id%>"> <input type="hidden" name="Flag"
											value="1"> <input type="Date" class="form-control"
											id="pay_date" name="Pay_date" placeholder="pay_date"
											value="<%=current_date%>" required> <label
											for="bill_date">Date</label>
										<div class="invalid-feedback">Please, enter bill_date!</div>
									</div>
								</div>

								<input type="hidden" name="Customer_id_fk" id="customer_id_fk"
									value="<%=id%>">
								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											placeholder="Name" value="<%=dto.getName()%>"> <label
											for="Name">Customer Name</label>
										<div class="invalid-feedback">Please, Enter Customer
											Name !</div>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="mobile_no"
											name="Mobile_no" placeholder="Mobile_no"
											value="<%=dto.getMobile_no()%>"> <label
											for="mobile_no">Mobile No.</label>
										<div class="invalid-feedback">Please, Enter Mobile
											Number!</div>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="address"
											name="Address" placeholder="address"
											value="<%=dto.getAddress()%>"> <label
											for="Customer Address">Customer Address</label>
										<div class="invalid-feedback">Please, Enter Customer
											Address !</div>
									</div>
								</div>

								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="gst_no"
											name="Gst_no" placeholder="gst_no"
											value="<%=dto.getGst_no()%>"> <label for="gst_no">GST
											No.</label>
										<div class="invalid-feedback">Please, Enter GST Number!</div>
									</div>
								</div>
								<div class="col-sm-3" id="paid_amount_block">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="old_due" name="Old_due" placeholder="Old due"
											value="<%=dto.getOld_due()%>" required step=0.01> <label
											for="Old_due">Due</label>
									</div>
								</div>


								<input type="hidden" id="payment_mode" name="Payment_mode"
									value="Cash" />

								<!-- Cash Amount Start -->
								<div class="col-md-3" id="cash_block">
									<div class="control form-floating">
										<input type="number" class="form-control"
											onblur="paidAmountEvent()" id="cash_amount"
											name="Cash_amount" placeholder="Cash Amount" value="0"
											step=0.01> <label for="cash_amount">Cash
											Amount</label>
									</div>
								</div>
								<!-- Cash Amount end -->

								<!-- Online Amount Start -->
								<div class="d-none" id="online_amt_block">
									<div class="control form-floating">
										<input type="number" class="form-control"
											onblur="paidAmountEvent()" id="online_amount"
											name="Online_amount" placeholder="Online Amount" value="0"
											step=0.01> <label for="online_amount">Online
											Amount</label>
									</div>
								</div>
								<!-- Online Amount end -->

								<!-- <div class="col-md-3">
										<div class="control form-floating">
											<input type="date" class="form-control"
												placeholder="Upcoming_date" id="upcoming_date"
												name="Upcoming_date"> <label for="upcoming_date">Upcoming
												Date</label>
											<div class="invalid-feedback">Please, Enter Upcoming
												Date!</div>
										</div>
									</div>
								 -->








								<div class="d-none" id="paid_block">
									<div class="control form-floating">
										<input readonly type="number"
											style="background-color: #e7ededad;" class="form-control"
											id="pay_amount" name="Pay_amount" onblur="paidAmountEvent()"
											placeholder="pay_amount" value="0" required step=0.01>
										<label for="paid_amount">Paid Amount</label>
									</div>
								</div>
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
												<option value="N/A" Selected='selected'>Select
													Online way</option>
												<option value="Online">Online</option>
												<option value="Phonepay">Phone Pay</option>
												<option value="Paytm">Paytm</option>
												<option value="Gpay">Gpay</option>
												<option value="Cheque">Cheque</option>

											</select> <label for="online_way">Select Online Way</label>
										</div>
									</div>
									<!-- Online way end -->

									<div class="col-md-3 refresh-container mb-3"
										data-add="add_bank.jsp" data-list="list_bank">
										<div class="control form-floating refresh-input">
											<input type="text" class="form-control" id="bank_name"
												placeholder="Select Bank" value="">
											<input type="hidden" id="bank_id_fk" name="Bank_id_fk"
												value="0"> <label
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

								<input type="hidden" class="form-control" id="c_y_session"
									name="C_y_session" value="2023-2024">


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


								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>



								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>


								</div>


							</form>
							<!-- End floating Labels Form -->

						</div>
					</div>
				</div>


			</div>
		</section>

	</main>
	<!-- End main -->
	<br />
	<br />

	<script>
	
	
function myResultLoadEvent() {
	
	var msg="";
			if (msg == "Yes") {
				var div = document.getElementById("success");
				div.style.display = "block";
			} else if (msg == "No") {
				var div = document.getElementById("unsuccess");
				div.style.display = "block";
			} else if (msg == "YesUp") {
				var div = document.getElementById("update");
				div.style.display = "block";
			} else if (msg == "NoUp") {
				var div = document.getElementById("unupdate");
				div.style.display = "block";
			}
		}
		
		
		
		
		
function checkPaymentMode() {

	//alert("hello");

	if (document.getElementById('cash_mode').checked) {

		document.getElementById("payment_mode").value = "Cash";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_block").className = "d-none";
		document.getElementById("online_amt_block").className = "d-none";
		document.getElementById("paid_block").className = "d-none";

		document.getElementById("online_amount").value = "0";
		document.getElementById("online_date").value = "";
		document.getElementById("online_remark").value = "N/A";
		document.getElementById("bank_id_fk").value = "0";
		document.getElementById("online_way").value = "N/A";
	
	} else if (document.getElementById('online_mode').checked) {
		document.getElementById("payment_mode").value = "Online";
		document.getElementById("online_amt_block").className = "col-md-3";

		document.getElementById("cash_block").style.display = "none";
		document.getElementById("online_block").className = "row g-3";
		document.getElementById("paid_block").className = "d-none";


		document.getElementById("cash_amount").value = "0";
		document.getElementById("online_way").value = "N/A";
		
		
	
		
	} else if (document.getElementById('both_mode').checked) {
		document.getElementById("payment_mode").value = "Both";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_amt_block").className = "col-md-3";
		document.getElementById("paid_block").className = "col-md-3";

		document.getElementById("online_block").className = "row g-3";
		document.getElementById("cash_amount").value = "0";
		document.getElementById("online_amount").value = "0";
		document.getElementById("online_way").value = "N/A";
		document.getElementById("paid_fees").value = "0";
		
		
	
		
		
		

	}
}

//checked payment mode on load
function loadPaymentMode() {

	//alert("hello");

	if (document.getElementById('cash_mode').checked) {

		document.getElementById("payment_mode").value = "Cash";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_block").className = "d-none";
		document.getElementById("online_amt_block").className = "d-none";
		document.getElementById("paid_block").className = "d-none";
		
	

	} else if (document.getElementById('online_mode').checked) {
		document.getElementById("payment_mode").value = "Online";
		document.getElementById("online_amt_block").className = "col-md-3";

		document.getElementById("cash_block").style.display = "none";
		document.getElementById("online_block").className = "row g-3";
		document.getElementById("paid_block").className = "d-none";
		
		
		

	} else if (document.getElementById('both_mode').checked) {
		document.getElementById("payment_mode").value = "Both";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_amt_block").className = "col-md-3";
		document.getElementById("paid_block").className = "col-md-3";
		document.getElementById("online_block").className = "row g-3";
		
		

	}

	//alert("hello");
}
function paidAmountEvent() {
	 
	let cash_amount = parseFloat(document.getElementById("cash_amount").value);
	let online_amount = parseFloat(document.getElementById("online_amount").value);
	let remaining_fees = parseFloat(document.getElementById("old_due").value);
	
	paid_amount = cash_amount + online_amount;
	
	if(paid_amount>remaining_fees){
		alert("Paid amount is greater than remaining fees.");
		document.getElementById("cash_amount").value="";
		document.getElementById("online_amount").value="";
		
		
	}
	else{
	document.getElementById("pay_amount").value=paid_amount.toFixed(2);
	}
}
function refreshBank() {
	
	//alert("Start");
	var flag = 'Bank';
	
	
		$.ajax({

			url : 'AjaxFolder/Ajax_refresh.jsp',
			data : 'Flag='+ flag,
			type : 'post',
			success : function(msg) {

				//alert(msg);

				$('#bank_id_fk').html(msg);
				

				

			}
		});
		

}
	</script>
	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>
</html>