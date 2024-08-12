
<%@include file="include/head.jsp"%>
</head>
<!--  body Start-->
<body onload="findTotal();">

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  main Start-->
	<main id="main" class="main">
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Ingredients In</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br /> <br>

							<%
							int bill_id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							IngredientsPurchaseService service = new IngredientsPurchaseService();
							IngredientsDto dto = service.getIngredientsPurchaseBillInfoById(bill_id, config, request);
							%>


							<!-- Floating Labels Form -->
							<form action="IngredientsPurchaseServlet" method="post"  autocomplete="off" 
								class="row g-3 needs-validation" novalidate>

								<!--  Date Start-->
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" id="user_id_fk" name="User_id_fk"
											value="<%=user_id%>"> <input type="hidden" id="bill_id"
											name="Id" value="<%=bill_id%>"><input
											type="Date" class="form-control" id="in_date" name="In_date"
											value="<%=dto.getIn_date()%>" placeholder="In Date" required>
										<label for="in_date"> date</label>
										<div class="invalid-feedback">Please, enter in_date!</div>
									</div>
								</div>
								<!--  Date End-->

								<!--  Remark Start-->
								<div class="col-md-9">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"><%=dto.getRemark()%></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>
								<!--  Remark End-->
								<h5 class="text-center card-title">Ingredients Item Details</h5>

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
														<input type="number" min="0" step="0.01" readonly="readonly"
															class="form-control" id="item_rate" value="0"
															placeholder="item_rate" /> <label for="Rate">Rate</label>
													</div>
												</td>
												<td class="text-center">
													<div class="control col-12">
														<input type="button" id="auto_focus_button" onclick="insertItem();"
															class="form-control add-icon">
													</div>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
								<!--  Item Table End-->
								<hr>

								<!--  Item Table Start-->
								<div class="table-responsive" id="purchase_item_show" >
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Name</th>
												<th>Quantity <br><b style="font-size: 10px;">(Enter in mg)</b></th>
												<th>Amount</th>
												<th>Rate</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody id="tbody" class="text-center">

											<%
											ArrayList<IngredientsDto> list = service.getIngredientsPurchaseBillItemInfoById(bill_id, config, request);

											int i = 1;

											for (IngredientsDto item_dto : list) {
											%>

											<tr>
												<td><%=item_dto.getItem_name()%> <input type="hidden"
													id="item_name<%=i%>" name="Item_name"
													value="<%=item_dto.getItem_name()%>"> <input
													type="hidden" id="item_id_fk<%=i%>" name="Item_id_fk"
													value="<%=item_dto.getItem_id_fk()%>"></td>
												<td><%=item_dto.getItem_quantity()%> <input
													type="hidden" id="item_quantity<%=i%>" name="Item_quantity"
													value="<%=item_dto.getItem_quantity()%>"></td>
												<td><%=item_dto.getItem_amt()%> <input type="hidden"
													id="item_amt<%=i%>" name="Item_amt"
													value="<%=item_dto.getItem_amt()%>"></td>
												<td><%=item_dto.getItem_rate()%> <input type="hidden"
													id="item_rate<%=i%>" name="Item_rate"
													value="<%=item_dto.getItem_rate()%>"></td>
												<td><i class="bi bi-trash main-color"
													onclick="deleteItem('<%=item_dto.getId()%>','<%=item_dto.getItem_id_fk()%>', '<%=item_dto.getItem_quantity()%>');">
												</i></td>
											</tr>

											<%
											i = i + 1;
											}
											%>

										</tbody>
									</table>
								</div>
								<!--  Item Table End-->
								
								<div class="d-flex justify-content-center mt-1" style="padding-left: 15%;">
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="number" min="0" step="0.01" class="form-control"
												id="total_amount" name="Total_amount"
												placeholder="Total_amount" value="" required> <label
												for="total_amount">Total Amount</label>
											<div class="invalid-feedback">Please, Enter Total Amount!</div>
										</div>
									</div>
								</div>
								<!--  Total Amount End-->

								<!--  Submit Button Start-->
								<div class="text-center">
									<button type="submit" id="st_btn" class="submit-btn">Submit</button>
								</div>
								<!--  Submit Button End-->

							</form>

							<div id="result_insert_item_info"></div>
							<div id="ingredients_item"></div>

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
	<script src="js/ingredients_purchase.js" type="text/javascript"></script>
	<script src="js/master.js" type="text/javascript"></script>
	<script type="text/javascript">
		var myButton = document.getElementById("auto_focus_button");

		myButton.addEventListener("blur", insertItem);
	
	</script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

</body>
<!--  body Start-->

</html>