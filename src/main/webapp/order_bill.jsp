
<%@include file="include/head.jsp"%>

<style>
.bi-x-square:hover {
	color: #fff;
	background-color: #f02c03;
}
</style>

</head>


<body>

	<%
	menu_table = 2;
	%>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->

	<main id="main" class="main" style="margin-left: 0px;">

		<%
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		int order_id = Integer.parseInt(request.getParameter("order_id") == null ? "0" : request.getParameter("order_id"));
		int complete_order_id = Integer
				.parseInt(request.getParameter("complete_order_id") == null ? "0" : request.getParameter("complete_order_id"));
		OrderService service = new OrderService();
		if (complete_order_id > 0) {

			order_id = complete_order_id;
			OrderBillDto dto = service.getOrderInfoById(order_id, config, request);
		}
		OrderBillDto dto = service.getOrderInfoById(order_id, config, request);
		%>

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1 id="page_name"><%=dto.getStatus().equalsIgnoreCase("complete") ? "Edit Bill" : "Start Bill"%></h1>
				<input id="page_status" type="hidden"  value="<%=dto.getStatus()%>">
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center"
				style="display: flex; align-items: stretch; justify-content: space-around;">

				<div class="col-lg-9">

					<div class="card" style="border-radius: 25px;">


						<div class="card-body">

						
<br>

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
															placeholder="Item Code">
															<input type="hidden" id="item_id">  
															<label for="item_code">Select
															Item Code</label>
														<div class="invalid-feedback">Please, Select Item
															Code!</div>
													</div>

												</div>
											</td>
											<td>
												<div class="control form-floating">
													<input type="hidden" id="item_name"> <input type="text"
														class="form-control" id="item_rate"
														placeholder="Item Rate" /> <label for="item_rate">Item
														Rate</label>
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

													<input type="button" id="ins_item"
														class="form-control add-icon">


												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="table-responsive" id="table_design">
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

										<%
										// Get the table_id parameter from the request
										int table_id = Integer.parseInt(request.getParameter("table_id") == null ? "0" : request.getParameter("table_id"));

										String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");
										float total_tab_price = 0;
										if (!flag.equals("kot")) {
											OrderService ser1 = new OrderService();

											int i = 0;
											ArrayList<OrderItemDto> list1 = ser1.getOrderItemInfoByTableId(dto.getTable_id(), order_id, config, request);
											for (OrderItemDto dto1 : list1) {
												i++;
										%>



										<tr>
											<td align="center"><%=dto1.getItem_code()%> <input
												id="item_code<%=i%>" type="hidden" name="Item_code"
												value="ItemCode1"> <input id="item_name<%=i%>"
												type="hidden" name="Item_name"
												value="<%=dto1.getItem_name()%>"> <input
												id="item_id<%=i%>" type="hidden" name="Item_id"
												value="<%=dto1.getItem_id_fk()%>"></td>



											<td align="center"><%=dto1.getItem_rate()%> <input
												id="item_price<%=i%>" type="hidden" name="Item_price"
												value="<%=dto1.getItem_rate()%>"></td>

											<td align="center"><%=dto1.getItem_qty()%> <input
												id="item_qty<%=i%>" type="hidden" name="Item_qty"
												value="<%=dto1.getItem_qty()%>"></td>
											<%
											float total_price = dto1.getItem_rate() * dto1.getItem_qty();
											%>
											<td align="center"><%=total_price%> <input
												id="total_price_with_qty" type="hidden"
												name="Total_price_with_qty" value="<%=total_price%>">
											</td>

											<td><i class="bi bi-trash main-color"
												onclick="deleteItem('<%=dto1.getId()%>');"> </i></td>
										</tr>



										<%
										total_tab_price = total_tab_price + total_price;
										}
										}
										TableService tb_ser = new TableService();
										TableDto tab_dto = tb_ser.getTableInfoById(table_id, config, request);
										%>

									</tbody>

									<tfoot id="tfoot">
										<tr>
											<td></td>
											<td></td>
											<td><span id="total_tab_qty">Total</span></td>

											<td><span id="total_tab_price"><%=total_tab_price%>
											</span></td>
											<td></td>
										</tr>
									</tfoot>
								</table>
							</div>

							<br> 



							<!-- Form Start-->
							<form action="" class="row g-3 needs-validation "
								autocomplete="off" novalidate>
								<input type="hidden" id="order_id" value="<%=order_id%>">
								<input type="hidden" id="table_id"
									value="<%=dto.getTable_id()%>" /> <input type="hidden"
									id="status" value="<%=dto.getStatus()%>"> <input
									type="hidden" id="session_year" value="2023-24"> <input
									type="hidden" id="user_id_fk" value="<%=user_id%>"> <input
									type="hidden" id="flag"
									value="<%=dto.getStatus().equalsIgnoreCase("complete") ? "update bill" : "direct bill"%>">

								<div class="container d-flex justify-content-center">
									<div class="col-md-4 col-sm-12 d-flex justify-content-around">

										<div class="form-check" style="margin-right: 5px;">
											<input class="form-check-input" type="radio" id="table_bill"
												name="cust_design"
												onclick="CustomerDesignVisibility();removeCustDet();"
												<%if (dto.getCust_name().equalsIgnoreCase("")) {%> checked
												<%}%>> <label class="form-check-label"
												for="table_bill"> Table </label>
										</div>
										<div class="form-check" style="margin-right: 5px;">
											<input class="form-check-input" type="radio" id="cust_bill"
												name="cust_design" onclick="CustomerDesignVisibility()"
												<%if (!dto.getCust_name().equalsIgnoreCase("")) {%> checked
												<%}%>> <label class="form-check-label"
												for="cust_bill"> Customer </label>
										</div>
									</div>
								</div>
								<div id="cust_block" class="row g-3">


									<h5 class="text-center card-title">Customer Info</h5>
									<div class="col-md-2 col-lg-2">
										<div class="row d-flex d-flex justify-content-center ">
											<div class="form-check col-4 mt-2">
												<input class="form-check-input" type="checkbox" id="regular"
													name="Regular" onclick="checkCustAvail();"
													<%if (dto.getCust_id_fk() > 0) {%> checked <%}%>> <label
													class="form-check-label mt-1" for="regular"
													style="font-size: 13px;"> Regular </label>
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



									</div>
									<!-- Name -->


									<div class="col-md-4">
										<div class="control form-floating">
											<input type="text" class="form-control" id="cust_name"
												value="<%=dto.getCust_name()%>" placeholder="Customer Name"
												onblur="checkCustAvail();"> <label for="cust_name">Name</label>
											<div class="invalid-feedback">Please, Enter Customer
												Name!</div>
										</div>
									</div>

									<input type="hidden" id="cust_id_fk" name="cust_id_fk"
										value="<%=dto.getCust_id_fk()%>">

									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" class="form-control" id="cust_mob_no"
												value="<%=dto.getCust_mob_no()%>" placeholder="Mobile No.">
											<label for="cust_mob_no">Mobile No.</label>
											<div class="invalid-feedback">Please, Enter Mobile no!</div>
										</div>
									</div>


									<div class="col-md-6">
										<div class="control form-floating">
											<input type="text" class="form-control" id="cust_address"
												value="<%=dto.getCust_address()%>" placeholder="Address">
											<label for="address">Address</label>
										</div>
									</div>
	<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="gst_no"
											value="<%=dto.getGst_no()%>" placeholder=" Gst_no">
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
										<div class="invalid-feedback">Please, Enter Date of Birth
											!</div>
									</div>
								</div>
									<div class="col-md-3 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa"
											value="<%=dto.getDoa()%>" placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of Anniversary
											!</div>
									</div>
								</div>
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="text" class="form-control" id="old_due_amount"
												value="<%=dto.getOld_due_amount()%>"
												onchange="findFinalAmount();" placeholder="Old Due Amount"
												readonly> <label for="old_due_amount">Old
												Due Amount</label>
										</div>
									</div>

								</div>
								<h5 class="text-center card-title">Bill Info</h5>


								<div class="col-md-2 col-lg-2">
									<div class="row d-flex d-flex justify-content-center ">

										<div class="form-check col-4 mt-2">

											<input class="form-check-input" type="checkbox"
												id="gst_status" onclick="checkGstStatusBlur();"
												<%if (dto.getGst_status().equalsIgnoreCase("Yes")) {%>
												checked <%}%>> <label class="form-check-label mt-1"
												for="gst_status" style="font-size: 13px;"> GST</label>
										</div>

									</div>

								</div>

								<div class="col-md-2">
									<div class="control form-floating">
										<input type="Date" class="form-control" id="bill_date"
											placeholder="bill_date" readonly
											value="<%=dto.getBill_date()%>"> <label
											for="bill_date">Date</label>
									</div>
								</div>

								<div class="col-md-2 ">
									<div class="control form-floating">
										<input type="text" class="form-control" id="table_name"
											readonly placeholder="Table" value="<%=dto.getTable_name()%>">
										<label for="table_name">Table</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="text" class="form-control" id="manager_name"
											readonly placeholder="Manager"
											value="<%=dto.getManager_name()%>"> <label
											for="manager_name">Manager</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control"
											id="without_gst_amount" step="0.01" placeholder="Bill Amount"
											value="<%=total_tab_price%>" readonly> <label
											for="without_gst_amount">Bill Amount</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="gst_amount"
											step="0.01" placeholder="Gst Amount"
											value="<%=dto.getGst_amount()%>" readonly> <label
											for="gst_amount">Gst Amount</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control" id="with_gst_amount"
											step="0.01" placeholder="Texable Amount"
											value="<%=dto.getWith_gst_amount()%>" readonly> <label
											for="with_gst_amount">Texable Amount</label>
									</div>
								</div>


								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" class="form-control" id="discount_amount"
											onchange="findFinalAmount();" placeholder="Discount Amount"
											value="<%=Math.round(dto.getDiscount_amount())%>" step="0">
										<label for="discount_amount">Discount Amount</label>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input readonly type="number" class="form-control" id="final_amount"
											placeholder="Final Amount"
											value="<%=Math.round(dto.getFinal_amount())%>" step="0.01">
										<label for="final_amount">Final Amount</label>
									</div>
								</div>

								<!-- Cash Amount Start -->
								<div class="col-md-3 col-6" id="cash_block">
									<div class="control form-floating">
										<input type="number" class="form-control"
											onblur="paidAmountEvent();" id="cash_amount"
											name="Cash_amount" placeholder="Cash Amount"
											value="<%=dto.getCash_amount()%>" step="0.01"> <label
											for="cash_amount">Cash Amount</label>
									</div>
								</div>
								<!-- Cash Amount end -->

								<!-- Online Amount Start -->
								<div class="d-none" id="online_amt_block">
									<div class="control form-floating">
										<input type="number" class="form-control"
											onblur="paidAmountEvent();" id="online_amount"
											name="Online_amount" placeholder="Online Amount"
											value="<%=dto.getOnline_amount()%>" step="0.01"> <label
											for="online_amount">Online Amount</label>
									</div>
								</div>
								<!-- Online Amount end -->

								<!-- Paid Amount Start -->
								<div class="d-none" id="paid_block">
									<div class="control form-floating">
										<input readonly type="text"
											style="background-color: #e7ededad;" class="form-control"
											id="paid_amount" name="Paid_amount" placeholder="Paid Amount"
											value="<%=dto.getCash_amount() + dto.getOnline_amount()%>"
											step="0.01"> <label for="paid_amount">Paid
											Amount</label>
									</div>
								</div>
								<!-- Paid Amount end -->

								<!-- Remaining Amount Start -->
								<div class="col-md-3 col-6" id="remaining_block">
									<div class="control form-floating">
										<input readonly type="text"
											style="background-color: #e7ededad;" class="form-control"
											id="remaining_amount" placeholder="Remaining Amount"
											<% if(dto.getCust_id_fk()>0){  %>value="<%=Math.round(dto.getFinal_amount() - (dto.getCash_amount() + dto.getOnline_amount()))%>"
											<%}else{ %> value="0.0"<%} %>
											step=0.01> <label for="remaining_amount">Remaining
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
												value="<%=dto.getOnline_date()%>" name="Online_date">
											<label for="online_date">Online Date</label>
											<div class="invalid-feedback">Please, Enter Online
												Date!</div>
										</div>
									</div>
									<!-- Online Date End -->

									<!-- Online way start -->
									<div class="col-md-3 col-6">
										<div class="control form-floating mb-3">
											<select class="form-select" id="online_way" name="Online_way"
												aria-label="working post">
												<option value="N/A" Selected='selected'>Select
													Online way</option>
												<%
												String[] online_way_arr = {"Online", "Phonepay", "Paytm", "Gpay", "Cheque", };
												for (String online_way : online_way_arr) {
												%>
												<option value="<%=online_way%>"
													<%if ((dto.getOnline_way() == null ? "" : dto.getOnline_way()).equalsIgnoreCase(online_way)) { out.print("selected"); }%>><%=online_way%></option>

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
												placeholder="Select Bank" value="">
											<input type="hidden" id="bank_id_fk" name="Bank_id_fk"
												value="<%=dto.getBank_id_fk()%>"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>


									<!-- Online Remark Start -->
									<div class="col-md-3 col-6">
										<div class="control form-floating">
											<input class="form-control" id="online_remark"
												value="<%=dto.getOnline_remark() == null ? "" : dto.getOnline_remark()%>"
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
											String paymentMode = dto.getPayment_mode();
											%>

											<%
											for (String mode : Arrays.asList("Cash", "Online", "Both")) {
											%>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
													<%=mode.equals(paymentMode) ? "checked" : ""%>
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
											id="remark" Name="Remark"><%=dto.getRemark() == null ? "N/A" : dto.getRemark()%></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>


								<div class="text-center">
									<button class="submit-btn">Submit</button>
								</div>


							</form>
							<!-- Form end -->
							<br />





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

	<script type="text/javascript" src="js/order_bill.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			loadPaymentMode();
			checkCustAvail();
			CustomerDesignVisibility();
			checkGstStatus();
		});
	</script>

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