<%@include file="include/head.jsp"%>
</head>

<!--  body start-->
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
				<h1>Add Liquor Item</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form action="MenuItemServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>
								
								
								
								<div class="col-md-4 refresh-container mb-3"
									data-add="add_liquor_category.jsp" data-list="list_liquor_category">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="liqour_cat_name"
											placeholder="Select Liquor Category" autocomplete="off" required>
										<input type="hidden" id="liqour_cat_id_fk" name="Liqour_cat_id_fk" value="0"> 
										<label for="liqour_cat_name">Select Liquor Category</label>
										<div class="invalid-feedback">Please, Select Liquor Category!</div>
									</div>
								</div>


								<!-- Item code Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="text"
											class="form-control" id="name" name="Item_code"
											placeholder="Item code"
											onblur="checkNameAvail(this.value, '0', 'Menu');" required>
										<label for="name">Item code</label>
										<div class="invalid-feedback">Please, Enter Item code!</div>
									</div>
								</div>
								<!-- Item code Block End -->
								
								<input type="hidden" name="Items" value="Liquor">

								<!-- Item name Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="item_name"
											name="Item_name" placeholder="Item name" required> <label
											for="item_name">Item name</label>
										<div class="invalid-feedback">Please, Enter Item name!</div>
									</div>
								</div>
								<!-- Item name Block End -->

								<!-- price Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" class="form-control" id="item_price"
											min="0" step="0.01" value="0" name="Item_price"
											placeholder="Item price" required> <label
											for="item_price">Item price</label>
										<div class="invalid-feedback">Please, Enter Item price!</div>
									</div>
								</div>
								<!-- price Block End -->

						
								

								<!-- Liqour Quantity Block Start -->
								<div class="col-md-4" id="ind_qty">
									<div class="control form-floating">
										<input type="number" class="form-control" id="liqour_ind_qty"
											min="0" step="0.01" value="0" name="Liqour_ind_qty"
											placeholder="Deduct capacity" required> <label
											for="liqour_ind_qty">Deduct capacity</label>
										<div class="invalid-feedback">Please, Enter Deduct capacity!</div>
									</div>
								</div>
								<!-- Liqour Quantity Block End -->



								<!-- Submit Button Start -->
								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
								</div>
								<!-- Submit Button End -->

							</form>
							<!-- End floating Labels Form -->

						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- End main -->

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

</body>
<!--  body End-->

</html>