
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

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Liquor Purchase</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card">
						<div class="card-body">
							<br /> <br>
							<%
							int bill_id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							LiquorPurchaseBillService service = new LiquorPurchaseBillService();
							LiquorPurchaseBillDto dto = service.getPurchaseBillInfoById(bill_id, config, request);
							%>


							<!-- Floating Labels Form -->
							<form action="LiquorPurchaseBillServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>



								<!--  Date start-->
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" value="<%=user_id%>">
										<input type="hidden" name="Id" id="bill_id" value="<%=bill_id%>">
										<input type="Date" class="form-control" id="in_date"
											name="In_date" placeholder="in_date"
											value="<%=dto.getIn_date()%>" required> <label
											for="in_date">Date</label>
										<div class="invalid-feedback">Please, enter in_date!</div>
									</div>
								</div>
								<!--  Date End-->

		
								
								<div class="col-md-4 refresh-container mb-3"
									data-add="add_dealer.jsp" data-list="list_dealer">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control"
											id="name" placeholder="Name" value="<%=dto.getDealer_name()%>"> <input type="hidden"
											id="dealer_id_fk" name="Dealer_id_fk" value="<%=dto.getDealer_id_fk()%>"> <label
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
												<th>Quantity</th>
												<th>Amount</th>
												<th>Rate</th>
												<th width="10%">Add</th>
											</tr>
										</thead>
										<tbody class="text-center" id="tbody">
											<tr>
												<td>
													<div class="col-md-12 refresh-container mb-3"
														data-add="add_liquor_item.jsp" data-list="list_liquor_item">
														<div class="control form-floating refresh-input">
															<input type="text" class="form-control"
															id="item_code" value=""
																placeholder="item_code" autocomplete="off">
															<input type="hidden" id="item_id" ><label for="item_code">Item Code</label>
															<div class="invalid-feedback">Please, enter Item Code!</div>
														</div>
													</div>
												</td>
												
												
												<td>
													<div class="control form-floating">
														<input type="hidden" id="item_id"> <input
															type="hidden" id="item_name"> <input
															type="number" min="0" step="0.01" class="form-control"
															id="quantity" value="0" placeholder="Quantity"
															onblur="validQty();" /> <label for="quantity">Quantity</label>
													</div>
												</td>
												

												<td>
													<div class="control form-floating">
														<input type="number" min="0" step="0.01"
															class="form-control" id="amt" value="0" onblur="findRate();"
															placeholder="Amount" /> <label for="amt">Amount</label>
													</div>
												</td>

												
												<td>
													<div class="control form-floating">
														<input type="number" min="0" step="0.01" onblur=""
															class="form-control"
															id="rate" value="0" placeholder="Rate" />
														<label for="rate">Rate</label>
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
								<div class="table-responsive" id="purchase_item_show">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Code</th>
												<th>Quantity <br>
												<b style="font-size: 10px;">(Enter in ml)</b></th>
												<th>Amount</th>
												<th>Rate</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody id="tbody" class="text-center">

											<%
											ArrayList<LiquorPurchaseBillDto> list = service.getPurchaseBillItemInfoById(bill_id, config, request);

											int i = 1;

											for (LiquorPurchaseBillDto item_dto : list) {
											%>

											<tr>
												<td><%=item_dto.getItem_name()%> <input type="hidden"
													id="item_name<%=i%>" name="Item_name"
													value="<%=item_dto.getItem_name()%>"> <input
													type="hidden" id="item_id_fk<%=i%>" name="Item_id_fk"
													value="<%=item_dto.getItem_id_fk()%>"></td>
												<td><%=item_dto.getQuantity()%> <input type="hidden"
													id="quantity<%=i%>" name="Quantity"
													value="<%=item_dto.getQuantity()%>"></td>	
												<td><%=item_dto.getPrice()%> <input type="hidden"
													id="amt<%=i%>" name="Amt"
													value="<%=item_dto.getPrice()%>"></td>
												<td><%=item_dto.getTotal_price()%> <input type="hidden"
													id="rate<%=i%>" name="Rate"
													value="<%=item_dto.getTotal_price()%>"></td>
												<td><i class="bi bi-trash main-color"
													onclick="deleteItem('<%=item_dto.getId()%>');"> </i></td>
											</tr>

											<%
											i = i + 1;
											}
											%>
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
											placeholder="Total_amount" value="<%=dto.getTotal_amount()%>"
											required> <label for="total_amount">Total
											Amount</label>
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
											id="remark" Name="Remark"><%=dto.getRemark()%></textarea>
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
			<div id="result_insert_item_info"></div>

		</section>

	</main>
	<!-- End main -->
	<br />
	<br />
	<!-- <script src="js/master.js" type="text/javascript"></script> -->
	<script src="js/liquor_purchase.js" type="text/javascript"></script>
	<script type="text/javascript">
		var myButton = document.getElementById("auto_focus_button");
		myButton.addEventListener("blur", insertItem);
	</script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->



</body>
<!--  Body End-->
</html>