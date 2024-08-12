
<%@include file="include/head.jsp"%>
</head>

<body>

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
				<h1>Edit Ingredients Out</h1>
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
							IngredientsSaleService service = new IngredientsSaleService();
							IngredientsDto dto = service.getIngredientsSaleBillInfoById(bill_id, config, request);
							%>


							<!-- Floating Labels Form -->
							<form action="IngredientsSaleServlet" method="post"
								autocomplete="off" class="row g-3 needs-validation" novalidate>



								<!--  Date Start-->
								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" id="user_id_fk" name="User_id_fk"
											value="<%=user_id%>"> <input type="hidden" id="bill_id"
											name="Id" value="<%=bill_id%>"> <input
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
												<th>Quantity<br>
												<b style="font-size: 10px;">(Enter in mg)</b></th>
												<th>Add</th>
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
																<input type="hidden" id="item_id" value="0"> 
																<label for="item_name">Item Name</label>
															<div class="invalid-feedback">Please, enter Item Name!</div>
														</div>
													</div>
												</td>

												<td>
													<div class="control form-floating">
														<input
															type="number" class="form-control" id="item_quantity"
															onblur="validQty();" value="0" placeholder="Quantity" />
														<label for="item_quantity">Quantity</label>
													</div>
												</td>

												<td class="text-center">
													<div class="control col-12">
														<input type="button" id="auto_focus_button"
															onclick="insertItem();" class="form-control add-icon">
													</div>
												</td>

											</tr>
										</tbody>
									</table>
								</div>
								<!--  Item Table End-->
								<hr>

								<!--  Item Table Start-->
								<div class="table-responsive" id="sale_item_show">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Name</th>
												<th>Quantity</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody class="text-center" id="tbody">

											<%
											ArrayList<IngredientsDto> list = service.getIngredientsSaleBillItemInfoById(bill_id, config, request);

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
												<td><i class="bi bi-trash main-color"
													onclick="deleteItem('<%=item_dto.getId()%>','<%=item_dto.getItem_id_fk()%>','<%=item_dto.getItem_quantity()%>');">
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

								<!--  Submit Button Start-->
								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
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
	<script src="js/ingredients_sale.js" type="text/javascript"></script>
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