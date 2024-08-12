
<%@include file="include/head.jsp"%>
	<style>
	.blink {
  animation: blink 3s infinite;
}</style>
</head>
<body>

	<%
	menu_table = 2;
	%>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>

	<!-- ======= Header end======= -->


	<main id="main" class="main" style="margin-left: 0px;">


		<%@include file="include/breadcrumbs.jsp"%>


		<section class="section dashboard">
			<div class="row justify-content-center"
				style="display: flex; align-items: stretch; justify-content: space-around;">

				<div class="col-lg-3">
					<div class="card" style="background-color: black;">
						<div class="card-body" >
							<div id="all-table"
								class="d-flex flex-wrap justify-content-center menu-table-wrapper mt-2">
								<%
								TableService tb_ser = new TableService();
								ArrayList<TableDto> list1 = tb_ser.getTableInfo(config, request);
								for (TableDto dto : list1) {
									String button_class = dto.getStatus().equalsIgnoreCase("Active") ? "book-table" : "free-table";
								%>

								<div class="col-lg-3 col-md-2 col-sm-2 col-4">
									<div class="text-center pt-2">
										<button class="<%=button_class%> shine-table blink"
											id="table-btn-<%=dto.getId()%>"
											onclick="changeTable(<%=dto.getId()%>);">
											<%=dto.getName()%>
										</button>
									</div>
								</div>

								<%
								if (list1.indexOf(dto) == 0) {
								%>
								<script>
							        window.onload = function() {
							        	changeTable(<%=dto.getId()%>);
							        };
							    </script>
								<%
								}
								%>

								<%
								}
								%>




							</div>
						</div>
					</div>




				</div>

				<div class="col-lg-9">

					<div class="card" style="border-radius: 25px; min-height: 532px;">


						<div class="card-body">



							<br />

							<div class="d-flex" style="margin-left: 10%;">
								<div class="pagetitle col-lg-8 text-center">
									<h1 id="get_name">
										<span id="dv_tb_name"></span> - <label
											style="padding-right: 10px; padding-top: 7px;"
											for="table-switch">Switch Table</label><span
											class="form-check form-switch" style="display: inline-flex;">
											<input class="form-check-input" type="checkbox" role="switch"
											style="width: 1.5em;" id="table-switch" />
										</span>
									</h1>
									<input type="hidden" id="table_id" value="0" /> <input
										type="hidden" id="table_name" />


								</div>




								<div class="col-md-3">
									<div class=" control form-floating">
										<input type="text" class="form-control"
											placeholder="Manager Name" list="manager_datalist"
											autocomplete="off" id="manager_name"
											onchange="UpdateTableManager();" />

										<datalist id="manager_datalist">

											<option value="Admin">


												<%
												EmployeeService ser_emp = new EmployeeService();

												ArrayList<EmployeeDto> list_emp = ser_emp.getActiveEmployeeName(config, request);

												for (EmployeeDto emp_dto : list_emp) {
												%>
											
											<option value="<%=emp_dto.getName()%>">
												<%
												}
												%>
											
										</datalist>




										<label for="manager_name">Manager</label>
									</div>
								</div>

								<script>
									    var selectBox = document.getElementById("manager_name");
									
									    // Add click event listener to the select box
									    selectBox.addEventListener("click", function() {
									        this.select(); // Execute this.select() when clicked
									        
									        // Perform other operations here
									        // ...
									    });
									</script>

							</div>

							<br />



							<!-- Floating Labels Form -->
							<form action="" class="row g-3 needs-validation" novalidate>


								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>

												<th>Item code</th>
												<th>Item price</th>
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
																id="item_id" value=""> <label for="item_code">Select
																Item Code</label>
															<div class="invalid-feedback">Please, Select Item
																Code!</div>
														</div>

													</div>
												</td>


												<td>
													<div class="control form-floating">
														<input type="hidden" id="item_name" value=""> <input
															type="number" class="form-control" id="item_rate"
															value="0" placeholder="Item Rate" /> <label
															for="item_rate">Item Rate</label>
													</div>
												</td>
												<td>
													<div class="control form-floating">
														<input type="text" class="form-control" id="item_qty"
															placeholder="Item Quantity" value="1"
															onclick="this.select();" /> <label
															for="item_qty">Item Quantity</label>


													</div>
												</td>
												<td class="text-center">
													<div class="control col-12">

														<input type="button" id="ins_item"
															class="form-control add-icon">

													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>

								<hr>

								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Code</th>
												<th>Item Price</th>
												<th>Item Quantity</th>
												<th>Total Price</th>
												<th>Delete</th>

											</tr>
										</thead>
										<tbody id="tbody_design" class="text-center">

										</tbody>

										<tfoot id="tfoot">
											<tr>
												<td></td>
												<td></td>
												<td><span id="total_tab_qty">Total</span></td>

												<td><span id="total_tab_price"></span></td>
												<td></td>
											</tr>
										</tfoot>
									</table>
								</div>
								<div id="result_item_info"></div>

								<div class="container d-flex justify-content-center">
									<div class="col-md-12 col-sm-12 d-flex justify-content-around">

										<div class="form-check">
											<input class="form-check-input" type="radio" id="billing"
												name="type_bill" onclick="SubmitBtmVisibility()"> <label
												class="form-check-label w-100" for="billing">Billing</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="type_bill"
												id="direct_submit" onclick="SubmitBtmVisibility()">
											<label class="form-check-label w-100" for="direct_submit">Direct</label>
										</div>

									</div>
								</div>

								<input type="hidden" id="order_id" value="0"> <input
									type="hidden" id="without_gst_amount" value="0"> <input
									type="hidden" id="bill_date" value="<%=current_date%>">
								<input type="hidden" id="session_year" value="2023-24">
								<input type="hidden" id="user_id_fk" value="<%=user_id%>">



								<div class="text-center d-none" id="billing-design">
									<button type="button" onclick="insertOrderWithoutBilling();"
										class="submit-btn ">Submit</button>
								</div>



								<!--  Room Direct billing Start -->
								<div class="row g-3 d-none" id="direct-design">




									<div class="container d-flex justify-content-center">
										<div class="col-md-4 col-sm-12 d-flex justify-content-around">

											<div class="form-check" style="margin-right: 5px;">
												<input class="form-check-input" type="radio" id="table_bill"
													name="cust_design" onclick="CustomerDesignVisibility()"
													checked> <label class="form-check-label"
													for="table_bill"> Table </label>
											</div>
											<div class="form-check" style="margin-right: 5px;">
												<input class="form-check-input" type="radio" id="cust_bill"
													name="cust_design" onclick="CustomerDesignVisibility()">
												<label class="form-check-label" for="cust_bill">
													Customer </label>
											</div>
										</div>
									</div>


									<div id="cust_block" class="row g-3 d-none">


										<h5 class="text-center card-title">Customer Info</h5>

										<div class="col-md-2 col-lg-2">
											<div class="row d-flex d-flex justify-content-center ">



												<div class="form-check col-4 mt-2">
													<input class="form-check-input" type="checkbox"
														id="regular" name="Regular" onclick="checkCustAvail();"
														value="Yes"> <label class="form-check-label mt-1"
														for="regular" style="font-size: 13px;"> Regular </label>
												</div>

											</div>

										</div>


										<div class="col-md-4">

											<div
												class="control form-floating d-flex justify-content-center">

												<input type="number" class="form-control" list="datalist_name"
													id="search_name" placeholder="Name"
													onblur="findCustInfoByMobileNo(this.value);" autocomplete="off" />
												<input type="hidden" value="0">
												<datalist id="datalist_name">

													<%
													CustomerService service1 = new CustomerService();
													ArrayList<CustomerDto> list3 = service1.getCustomerInfo(config, request);
													for (CustomerDto dto_path : list3) {
													%>
													<option value="<%=dto_path.getMobile_no()%>" />
													<%
													}
													%>
												</datalist>
												<label for="search_name">Search By Mobile no.</label>

												<div style="width: 30px; margin-left: 8px;">
													<div class="mt-2" title="Reset Customer information">
														<i class="bi bi-x-square main-color"
															onclick="removeCustDet();"></i>
													</div>

												</div>

											</div>

											<script>
									    var search_name = document.getElementById("search_name");
									
									    // Add click event listener to the select box
									    search_name.addEventListener("click", function() {
									        this.select(); // Execute this.select() when clicked
									        
									        // Perform other operations here
									        // ...
									    });
									</script>



										</div>


										<div class="col-md-4">
											<div class="control form-floating">
												<input type="text" autocomplete="off" class="form-control"
													id="cust_name" placeholder="Customer Name"
													onblur="checkCustAvail();"> <label for="cust_name">Name</label>
												<div class="invalid-feedback">Please, Enter Customer
													Name!</div>
											</div>
										</div>

										<input type="hidden" id="cust_id_fk" name="cust_id_fk"
											value="0">


										<div class="col-md-3">
											<div class="control form-floating">
												<input type="number" class="form-control" id="cust_mob_no"
													placeholder="Mobile No."> <label for="cust_mob_no">Mobile
													No.</label>
												<div class="invalid-feedback">Please, Enter Mobile no!</div>
											</div>
										</div>


										<div class="col-md-6">
											<div class="control form-floating">
												<input type="text" class="form-control" id="cust_address"
													placeholder="Address"> <label for="address">Address</label>
											</div>
										</div>

										<div class="col-md-3">
											<div class="control form-floating">
												<input type="text" class="form-control" id="gst_no"
													placeholder=" Gst_no"> <label for="gst_no">Gst
													no</label>
											</div>
										</div>

										<div class="col-md-3">
											<div class="control form-floating">
												<input type="text" class="form-control" id="company_name"
													placeholder="Company Name"> <label
													for="company_name">Company Name</label>
											</div>
										</div>
	              <div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="dob"
											 placeholder="Date of Birth">
										<label for="dob"> Date of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of Birth
											!</div>
									</div>
								</div>
									<div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa"
											 placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of Anniversary
											!</div>
									</div>
								</div>
										<div class="col-md-3 " style="display: none;" id="due_block">
											<div class="control form-floating">
												<input readonly type="number" class="form-control"
													id="old_due_amount" onchange="findFinalAmount();"
													placeholder="Old Due Amount" value="0" step="0.01">
												<label for="old_due_amount">Old Due Amount</label>
											</div>
										</div>


									</div>

									<h5 class="text-center card-title">Bill Info</h5>


									<div class="col-md-2 col-lg-2">
										<div class="row d-flex d-flex justify-content-center ">

											<div class="form-check col-4 mt-2">

												<input class="form-check-input" type="checkbox"
													id="gst_status" onclick="checkGstStatus();" > <label
													class="form-check-label mt-1" for="gst_status"
													style="font-size: 13px;"> GST</label>
											</div>

										</div>

									</div>


									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" class="form-control"
												id="without_gst_amount1" step="0.01"
												placeholder="Bill Amount" readonly> <label
												for="without_gst_amount">Bill Amount</label>
										</div>
									</div>

									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" class="form-control" id="gst_amount"
												step="0.01" placeholder="Gst Amount" readonly> <label
												for="gst_amount">Gst Amount</label>
										</div>
									</div>

									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" class="form-control"
												id="with_gst_amount" step="0.01"
												placeholder="Texable Amount" readonly> <label
												for="with_gst_amount">Texable Amount</label>
										</div>
									</div>


									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" class="form-control"
												id="discount_amount" value="0" onchange="findFinalAmount();"
												placeholder="Discount Amount" step="0.001"> <label
												for="discount_amount">Discount Amount</label>
										</div>
									</div>

									<div class="col-md-3">
										<div class="control form-floating">
											<input readonly type="number" class="form-control"
												id="final_amount" placeholder="Final Amount" value="0"
												step="0.01"> <label for="final_amount">Final
												Amount</label>
										</div>
									</div>





									<!-- Cash Amount Start -->
									<div class="col-md-3 col-6" id="cash_block">
										<div class="control form-floating">
											<input type="number" class="form-control"
												onblur="checkPaidAmountEvent();" id="cash_amount"
												name="Cash_amount" placeholder="Cash Amount" value="0"
												step="0.01"> <label for="cash_amount">Cash
												Amount</label>
										</div>
									</div>
									<!-- Cash Amount end -->

									<!-- Online Amount Start -->
									<div class="d-none" id="online_amt_block">
										<div class="control form-floating">
											<input type="number" class="form-control"
												onblur="checkPaidAmountEvent();" id="online_amount"
												name="Online_amount" placeholder="Online Amount" value="0"
												step="0.01"> <label for="online_amount">Online
												Amount</label>
										</div>
									</div>
									<!-- Online Amount end -->

									<!-- Paid Amount Start -->
									<div class="d-none" id="paid_block">
										<div class="control form-floating">
											<input readonly type="number"
												style="background-color: #e7ededad;" class="form-control"
												id="paid_amount" name="Paid_amount"
												placeholder="Paid Amount" value="0" step=0.01> <label
												for="paid_amount">Paid Amount</label>
										</div>
									</div>
									<!-- Paid Amount end -->

									<!-- Remaining Amount Start -->
									<div class="col-md-3 col-6" id="remaining_block">
										<div class="control form-floating">
											<input readonly type="number"
												style="background-color: #e7ededad;" class="form-control"
												id="remaining_amount" placeholder="Remaining Amount"
												value="0" step=0.01> <label for="remaining_amount">Remaining
												Amount</label>
										</div>
									</div>
									<!-- Remaining Amount end -->

									<div class="d-none" id="online_block">

										<!-- Online Date Start-->
										<div class="col-md-3 col-6">
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
										<div class="col-md-3 col-6">
											<div class="control form-floating mb-3">
												<select class="form-select" id="online_way"
													name="Online_way" aria-label="working post">
													<option value="N/A">Select Online way</option>
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
													placeholder="Select Bank" value=""> <input
													type="hidden" id="bank_id_fk" name="Bank_id_fk" value="0">
												<label for="bank_name">Select Bank</label>
												<div class="invalid-feedback">Please, Select Bank!</div>
											</div>
										</div>



										<!-- Online Remark Start -->
										<div class="col-md-3 col-6">
											<div class="control form-floating">
												<input class="form-control" id="online_remark"
													name="Online_remark" placeholder="Online Remark"> <label
													for="online_remark">Online Remark</label>
											</div>
										</div>
										<!-- Online Remark End -->

									</div>
									<!-- *** Online Block End *** -->


									<h5 class="text-center card-title form-heading">Payment
										Details</h5>
									<div class="container d-flex justify-content-center">
										<div class="col-md-3">
											<div class="d-flex justify-content-around">

												<%
												for (String mode : Arrays.asList("Cash", "Online", "Both")) {
												%>
												<div class="form-check">
													<input class="form-check-input" type="radio"
														name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
														<%=mode.equals("Cash") ? "checked" : ""%>
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
										value="Cash" />



									<div class="col-md-12">
										<div class="control form-floating">
											<textarea class="form-control" placeholder="Remark"
												id="remark" Name="Remark"></textarea>
											<label for="remark">Remark</label>
										</div>
									</div>



									<!-- Form end -->
									<br />

									<div class="text-center">
										<button class="submit-btn">Submit</button>
									</div>




								</div>


								<!--  Room Direct billing End -->


							</form>
							<!-- End floating Labels Form -->




							<br />




						</div>
					</div>
				</div>



			</div>
		</section>


	</main>


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<script src="js/menu_item.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(document).ready(function() {
		$("#ins_item").on("click", insertItem);
		$("#ins_item").on("blur", function() {
			setTimeout(insertItem, 100);
			});
		});
	</script>
</body>
</html>