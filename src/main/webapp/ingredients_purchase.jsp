
<%@include file="include/head.jsp"%>
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
				<h1>Ingredients In</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card">
						<div class="card-body">
							<br /> <br>


							<!-- Floating Labels Form -->
							<form action="IngredientsPurchaseServlet" method="post"
								autocomplete="off" class="row g-3 needs-validation" novalidate>



								<!--  Date start-->
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" value="<%=user_id%>">
										<input type="Date" class="form-control" id="in_date"
											name="In_date" placeholder="In Date"
											value="<%=current_date%>" required> <label
											for="in_date">Date</label>
										<div class="invalid-feedback">Please, enter in_date!</div>
									</div>
								</div>
								<!--  Date End-->

								<!--  Remark start-->
								<div class="col-md-9">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>
								<!--  End start-->

								<h5 class="text-center card-title">Purchase Item Details</h5>

								<!--  Item Table start-->
								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Name</th>
												<th>Quantity<br>
												<b style="font-size: 10px;">(Enter in mg)</b></th>
												<th>Amount</th>
												<th>Rate</th>
												<th width="10%">Add</th>
											</tr>
										</thead>
										<tbody class="text-center" id="tbody">
											<tr>
												<td>
													<div class="col-md-12 refresh-container mb-3"
														data-add="add_ingredients_item.jsp" data-list="list_ingredients">
														<div class="control form-floating refresh-input">
															<input class="form-control" list="browsers1"
																id="item_name" value="" placeholder="Item Name"
																autocomplete="off">
																<input type="hidden" id="item_id">
																<label for="item_name">Item Name</label>
															<div class="invalid-feedback">Please, enter Item Name!</div>
														</div>
													</div>
												</td>

												<td>
													<div class="control form-floating">
														 <input
															type="number" min="0" step="0.01" class="form-control"
															id="item_quantity" value="0" placeholder="Quantity"
															onblur="validQty();" /> <label for="item_quantity">Quantity</label>
													</div>
												</td>
												<td>
													<div class="control form-floating">
														<input type="number" min="0" step="0.01"
															class="form-control" id="item_amt" value="0"
															onblur="findRate();" placeholder="Amount" /> <label
															for="item_amt">Amount</label>
													</div>
												</td>
												<td>
													<div class="control form-floating">
														<input type="number" min="0" step="0.01"
															readonly="readonly" class="form-control" id="item_rate"
															value="0" placeholder="Rate" /> <label for="item_rate">Rate</label>
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
												<th>Item Name</th>
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

								<div class="d-flex justify-content-center mt-1"
									style="padding-left: 15%;">
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" min="0" step="0.01" class="form-control"
												id="total_amount" name="Total_amount"
												placeholder="Total_amount" value="0" required> <label
												for="total_amount">Total Amount</label>
											<div class="invalid-feedback">Please, enter Total
												Amount!</div>
										</div>
									</div>
								</div>
								<!--  Total Amount End-->



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
			<div id="ingredients_item"></div>

		</section>

	</main>
	<!-- End main -->
	<br />
	<br />
	<script src="js/master.js" type="text/javascript"></script>
	<script src="js/ingredients_purchase.js" type="text/javascript"></script>
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