
<%@include file="include/head.jsp"%>
</head>
<!--  body Start-->
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
				<h1>Reciepe-Ingredients Relation</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br /> <br>

							<!-- Floating Labels Form -->
							<form class="row g-3 needs-validation" novalidate>
								<div class="d-flex justify-content-center">
									<div class="col-md-4 refresh-container mb-3"
										data-add="add_menu_item.jsp" data-list="list_menu_item_food">
										<div class="control form-floating refresh-input">
											<input type="text" class="form-control" id="menu_name"
												placeholder="Menu Name"> <input type="hidden"
												id="menu_id_fk" value="0"> <label for="menu_name">Menu
												Item</label>
											<div class="invalid-feedback">Please, Select Menu Item!</div>
										</div>
									</div>
								</div>
								<input type="hidden" id="user_id_fk" value="<%=user_id%>">



								<div id="table_design" class="d-none">
									<h5 class="text-center card-title">Ingredients Item
										Details</h5>

									<!--  Item Table start-->
									<div class="table-responsive">
										<table class="table">
											<thead class="text-center" id="thead">
												<tr>
													<th>Ingredients Name</th>
													<th>Quantity (mg/ml)</th>
													<th width="10%">Add</th>
												</tr>
											</thead>
											<tbody class="text-center" id="tbody">
												<tr>
													<td>
														<div class="col-md-12 refresh-container mb-3"
															data-add="add_ingredients_item.jsp"
															data-list="list_ingredients">
															<div class="control form-floating refresh-input">
																<input class="form-control" id="ingredient_name"
																	value="" placeholder="Ingredients Name"
																	autocomplete="off"> <input type="hidden"
																	id="ingredient_id_fk"> <label
																	for="ingredient_name">Ingredient Name</label>
																<div class="invalid-feedback">Please, enter
																	Ingredient Name!</div>
															</div>
														</div>
													</td>

													<td>
														<div class="control form-floating">
															<input type="number" min="0" step="0.01"
																class="form-control" id="reciepe_ratio" value="0"
																placeholder="Quantity" /> <label for="reciepe_ratio">Quantity
																(mg/ml)</label>
														</div>
													</td>
													<td class="text-center">
														<div class="control col-12">
															<input type="button" id="auto_focus_button"
																onclick="insertFull();" class="form-control add-icon">
														</div>
													</td>

												</tr>
											</tbody>
										</table>
									</div>
									<!--  Item Table End-->
									<hr>

									<!--  Item Table Start-->
									<div class="table-responsive">
										<table class="table">
											<thead class="text-center" id="thead">
												<tr>
													<th>Ingredients Name</th>
													<th>Quantity</th>
													<th>Delete</th>
												</tr>
											</thead>
											<tbody class="text-center tbody" id="tbody_design">


											</tbody>
										</table>
									</div>
									<!--  Item Table End-->

								</div>

							</form>

							<div id="result_insert_item_info"></div>
							<div id="ingredients_item"></div>
							<div id="info"></div>

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


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<script src="js/reciepe_relation.js" type="text/javascript"></script>
	<script type="text/javascript">
		var myButton = document.getElementById("auto_focus_button");
		myButton.addEventListener("blur", insertFull);
	</script>

</body>
<!--  body Start-->

</html>