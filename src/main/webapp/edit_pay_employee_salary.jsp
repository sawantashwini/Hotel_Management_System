
<%@include file="include/head.jsp"%>

</head>


<body onload="loadPaymentMode();" >

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->


	<main id="main" class="main">

	
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Pay Salary</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-12">

					<div class="card" style="border-radius: 20px;">
						<div class="card-body">
							<h5 class="text-center card-title pt-3">Employee Details</h5>

							<!-- <br /> -->

							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							EmployeeSalaryService service = new EmployeeSalaryService();

							EmployeeSalaryDto dto = service.getSalaryInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form action="EmployeeSalaryServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>

								<div class="col-sm-4">
									<div class="control form-floating">
										<input readonly type="text" class="form-control" id="name"
											name="Name" placeholder="Model Name"
											value="<%=dto.getEmployee_name()%>" required> <label
											for="name">Employee Name</label>
									</div>
								</div>

								<input type="hidden" name="Id" value="<%=id%>"> <input
									type="hidden" name="Employee_id_fk"
									value="<%=dto.getEmployee_id_fk()%>"> <input
									type="hidden" id="user_id_fk" value="<%=user_id%>"
									name="User_id_fk">





								<div class="col-md-4">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="mobile_no" name="Mobile_no" placeholder="Mobile Number"
											value="<%=dto.getMobile_no()%>" required> <label
											for="mo_nomber">Mobile Number</label>
									</div>
								</div>

								<!-- Salary Per Month Start -->
								<div class="col-sm-4">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="salary_per_month" name="Salary_per_month"
											placeholder="Base Price"
											value="<%=dto.getSalary_per_month()%>" required> <label
											for="salary_per_month">Salary Per Month</label>
									</div>
								</div>
								<!-- Salary Per Month end -->

								<!-- End Restrict Form -->

								<h5 class="text-center card-title">Pay Salary Details</h5>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="Date" class="form-control" id="in_date"
											name="In_date" placeholder="in_date"
											value="<%=dto.getIn_date()%>" required> <label
											for="in_date">Date</label>
										<div class="invalid-feedback">Please, enter in_date!</div>
									</div>
								</div>

								<!-- Pay Date start -->
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="date" class="form-control" max="31" min="1"
											value="<%=dto.getPay_date()%>" id="pay_date" name="Pay_date"
											placeholder="Pay Date" required> <label
											for="mo_nomber">Pay Date</label>
										<div class="invalid-feedback">Please, Enter Valid Date!</div>
									</div>
								</div>
								<!-- Pay Date End -->

								<!-- Paid Month start -->
								<div id="on_way" class="col-md-3">
									<div class="control form-floating mb-3">
										<select class="form-select" id="paid_month" name="Paid_month"
											aria-label="working post">
											<option value="<%=dto.getPaid_month() == null ? "Select Paid Month" : dto.getPaid_month()%>"
												selected><%=dto.getPaid_month() == null ? "Select Paid Month" : dto.getPaid_month()%></option>
											<option value="January">January</option>
											<option value="February">February</option>
											<option value="March">March</option>
											<option value="April">April</option>
											<option value="May">May</option>
											<option value="June">June</option>
											<option value="July">July</option>
											<option value="August">August</option>
											<option value="September">September</option>
											<option value="October">October</option>
											<option value="November">November</option>
											<option value="December">December</option>
										</select> <label for="paid_month">Select Paid Month</label>
									</div>
								</div>
								<!-- Paid Month end -->

								<!-- Paid Year start -->
								<div class="col-md-3">
									<div class="control form-floating mb-3">
										<select class="form-select" id="paid_year" name="Paid_year"  required
											aria-label="working post">
											<option value="<%=dto.getPaid_year() == null ? "Select Paid Year" : dto.getPaid_year()%>"
												selected><%=dto.getPaid_year() == null ? "Select Paid Year" : dto.getPaid_year()%></option>
											<option value="2023">2023</option>
											<option value="2024">2024</option>
											<option value="2025">2025</option>
											<option value="2026">2026</option>
											<option value="2027">2027</option>
										</select> <label for="paid_year">Select Paid Year</label>
									</div>
								</div>
								<!-- Paid Year End -->



								<!-- Leave Day Start -->
								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="leave_days"
											name="Leave_days" placeholder="Leave Days"
											value="<%=dto.getLeave_days()%>" required> <label
											for="leave_days">Leave Days</label>
									</div>
								</div>
								<!-- Leave Day end -->

								<!-- Half Day Start -->
								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="half_days"
											name="Half_days" placeholder="Half Days"
											value="<%=dto.getHalf_days()%>" required> <label
											for="half_days">Half Days</label>
									</div>
								</div>
								<!-- Half Day end -->

								<!-- Absent Day Start -->
								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="number" class="form-control"
										 id="absent_days"
											name="Absent_days" placeholder="Absent Days"
											value="<%=dto.getAbsent_days()%>" required> <label
											for="absent_days">Absent Days</label>
										<div class="invalid-feedback">Please, Enter Absent Days!</div>
									</div>
								</div>
								<!-- Absent Day end -->

								<!-- Present Day Start -->
								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="number" class="form-control" onblur="totalDayEvent();"
											 id="present_days"
											name="Present_days" placeholder="Present Days"
											value="<%=dto.getPresent_days()%>" required> <label
											for="present_days">Present Days</label>
										<div class="invalid-feedback">Please, Enter Present
											Days!</div>
									</div>
								</div>
								<!-- Present Day end -->

								<!-- Total Day Start -->
								<div class="col-sm-3">
									<div class="control form-floating">
										<input type="number" class="form-control" min="28" max="31" 
											value="<%=dto.getTotal_days()%>" id="total_days"
											name="Total_days" placeholder="Total Days" value="" required>
										<label for="total_days">Total Days</label>
									</div>
								</div>
								<!-- Total Day end -->



								<!-- Remaining Salary Start -->
								<div class="col-sm-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="final_salary" name="Final_salary"
											placeholder="Final Salary" value="<%=dto.getFinal_salary()%>"
											required> <label for="remaining_salary">Final
											Salary</label>
									</div>
								</div>
								<!-- Remaining Salary end -->

								<!-- Cash Amount Start -->
								<div class="col-md-3" id="cash_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent()" id="cash_amount"
											name="Cash_amount" placeholder="Cash Amount"
											value="<%=dto.getCash_amount()%>" required> <label
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
											value="<%=dto.getOnline_amount()%>" required> <label
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
											value="<%=dto.getAmount()%>" required> <label
											for="paid_amount">Paid Amount</label>
									</div>
								</div>
								<!-- Paid Amount end -->

								<div class="d-none" id="online_block">

									<!-- Online Date Start-->
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="date" class="form-control"
												placeholder="Online Date" id="online_date"
												name="Online_date" value="<%=dto.getOnline_date()%>">
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
												for (String online_way : Arrays.asList("Online", "Phonepay", "Paytm", "Gpay",  "Cheque")) {
												%>
												<option value="<%=online_way%>"
													<%=online_way.equalsIgnoreCase(dto.getOnline_way()) ? "selected" : ""%>><%=online_way%></option>
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
												placeholder="Select Bank" value="<%=dto.getBank_name()%>">
											<input type="hidden" id="bank_id_fk" name="Bank_id_fk"
												value="<%=dto.getBank_id_fk()%>"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>


									<!-- Online Remark Start -->
									<div class="col-md-3">
										<div class="control form-floating">
											<input class="form-control" id="online_remark"
												name="Online_remark" placeholder="Online Remark"
												value="<%=dto.getOnline_remark()%>"> <label
												for="online_remark">Online Remark</label>
										</div>
									</div>
									<!-- Online Remark End -->

								</div>
								<!-- *** Online Block End *** -->
								
								<h6 class="text-center mt-2 card-title">Payment
									Mode</h6>

								<div class="row g-3 d-flex justify-content-center">
									<div class="col-lg-3 col-md-4 mt-0">
										<div class="d-flex justify-content-around">
											<%
											String paymentMode = dto.getPayment_mode();
											%>

											<%
											for (String mode : Arrays.asList("Cash", "Online", "Both")) {
											%>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
													<%=mode.equals(paymentMode) ? "checked" : ""%>
													value="<%=mode%>" onclick="checkPaymentMode();">
												<label class="form-check-label"
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
									value="Cash" />
									
								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"><%=dto.getRemark() %></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>	

								<div id="sub" class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
								</div>


							</form>
							<!-- Fees Form start -->

						</div>
					</div>
				</div>


			</div>
		</section>

	</main>
	<!-- End main -->
	<br />
	<br />
	
	<script type="text/javascript" src="js/employee.js"></script>
	<script type="text/javascript">
		function totalDayEvent() {

			let absent_days = Number(document.getElementById("absent_days").value);
			let present_days = Number(document.getElementById("present_days").value);
			let half_days = Number(document.getElementById("half_days").value);
			let leave_days = Number(document.getElementById("leave_days").value);
			total_days = absent_days + present_days + (half_days / 2)
					+ leave_days;
			document.getElementById("total_days").value = total_days;
			salaryEvent();
		}


		function paidAmountEvent() {

			let cash_amount = parseFloat(document.getElementById("cash_amount").value);
			let online_amount = parseFloat(document
					.getElementById("online_amount").value);

			paid_amount = cash_amount + online_amount;

			document.getElementById("paid_amount").value = paid_amount
					.toFixed(2);
		}

		function salaryEvent() {

			let total_day = Number(document.getElementById("total_days").value);
			let salary = Number(document.getElementById("salary_per_month").value);
			let half = Number(document.getElementById("half_days").value);
			let absent_days = Number(document.getElementById("absent_days").value);
			let leave_days = Number(document.getElementById("leave_days").value);

			let per_day = salary / 31;

			let due_absent = absent_days * per_day;
			let due_half = half * per_day / 2;
			/* let present_day = total_day - (half/2 + absent_days + leave_days);
			document.getElementById("present_days").value = present_day; */
			document.getElementById("final_salary").value = (salary
					- due_absent - due_half).toFixed(2);

		}
	</script>


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>