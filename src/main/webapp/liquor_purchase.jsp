
<%@include file="include/head.jsp"%>
<style>
option {
	background-color: #fff;
	cursor: pointer;
}

option:hover {
	background-color: #d12727;
	cursor: pointer;
}
</style>

</head>
<body>

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
				<h1>Liquor Purchase</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card">
						<div class="card-body">
							<br /> <br>
							<!-- Floating Labels Form -->
							<form action="LiquorPurchaseBillServlet" method="post"
								autocomplete="off" class="row g-3 needs-validation" novalidate>



								<!--  Date start-->
								<div class="col-md-3">
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

								<div class="col-md-4 refresh-container mb-3"
									data-add="add_dealer.jsp" data-list="list_dealer">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="name"
											placeholder="Name" required> <input type="hidden"
											id="dealer_id_fk" name="Dealer_id_fk" value="0"> <label
											for="name">Dealer Name</label>
										<div class="invalid-feedback">Please, enter Dealer Name!</div>
									</div>
								</div>


								<h5 class="text-center card-title">Purchase Item Details</h5>

								<!--  Item Table start-->
								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Name</th>
												<th>Quantity<br> <b style="font-size: 10px;">(Enter
														in ml)</b></th>
												<th>Amount</th>
												<th>Rate</th>
												<th width="10%">Add</th>
											</tr>
										</thead>
										<tbody class="text-center" id="tbody">
											<tr>
												<td>
													<div class="col-md-12 refresh-container mb-3"
														data-add="add_liquor_item.jsp"
														data-list="list_liquor_item">
														<div class="control form-floating refresh-input">
															<input type="text" class="form-control" id="item_code"
																value="" placeholder="item_code" autocomplete="off">
															<input type="hidden" id="item_id"><label
																for="item_code">Item Code</label>
															<div class="invalid-feedback">Please, enter Item
																Code!</div>
														</div>
													</div>
												</td>

												<td>
													<div class="control form-floating">
														<input type="hidden" id="item_name"> <input
															type="number" min="0" step="0.01" class="form-control"
															id="quantity" value="0" placeholder="Quantity"
															onblur="validQty();" /> <label for="quantity">Quantity</label>
													</div>
												</td>

												<td>
													<div class="control form-floating">
														<input type="number" min="0" step="0.01"
															onblur="findRate();" class="form-control" id="amt"
															value="0" placeholder="Amount" /> <label for="amt">Amount</label>
													</div>
												</td>



												<td>
													<div class="control form-floating">
														<input type="number" min="0" step="0.01" onblur=""
															class="form-control" id="rate" value="0"
															placeholder="Rate" /> <label for="rate">Rate</label>
													</div>
												</td>
												<td class="text-center">
													<div class="control col-12">
														<input type="button" id="auto_focus_button"
															class="form-control add-icon">
													</div>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
								<!--  Item Table End-->
								<hr>

								<!--  Item Table start-->
								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Code</th>

												<th>Quantity</th>
												<th>Amount</th>
												<th>Rate</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody id="tableScroll" class="text-center">
										</tbody>

									</table>
								</div>
								<!--  Item Table End-->

								<!-- <div class="d-flex justify-content-center mt-1"
									style="padding-left: 15%;"> -->
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="number" min="0" step="0.01" class="form-control"
											id="total_amount" name="Total_amount"
											placeholder="Total_amount" value="" required> <label
											for="total_amount">Total Amount</label>
										<div class="invalid-feedback">Please, enter
											total_amount!</div>
									</div>
								</div>
								<!-- </div> -->
								<!--  Total Amount End-->

								<!--  Remark start-->
								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>
								<!--  End start-->

								<!--  Submit Button Start-->
								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
								</div>
								<!--  Submit Button End-->



							</form>
							<!-- End floating Labels Form -->

						</div>

					</div>
				</div>
			</div>
			<div id="result_info"></div>
			<div id="get_item_info"></div>

		</section>

	</main>
	<!-- End main -->
	<br />
	<br />
	<!-- <script src="js/master.js" type="text/javascript"></script> -->
	<script src="js/liquor_purchase.js" type="text/javascript"></script>
	<script type="text/javascript">
		var myButton = document.getElementById("auto_focus_button");
		myButton.addEventListener("blur", autoFocus);
	</script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->



</body>
<!--  Body End-->
</html>