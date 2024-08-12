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
				<h1>Add Liquor Brand</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form action="LiquorCategoryServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>

								<!-- Name Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="text" 
											 onblur="checkNameAvail(this.value, '0', 'Category');"
											class="form-control" id="name" name="Name" placeholder="Name"
											required> <label for="name">Name</label>
										<div class="invalid-feedback">Please, Enter Name!</div>
									</div>
								</div>
								<!-- Name Block End -->

								<!-- min_limit Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" class="form-control" id="min_qty" min="0"
											step="0.01" value="0" name="Min_qty" placeholder="Min qty"
											required> <label for="min_qty">Minimum
											Quantity</label>
										<div class="invalid-feedback">Please, Enter Minimum
											Quantity!</div>
									</div>
								</div>
								<!-- min_limit Block End -->

								<!-- Capacity Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" class="form-control" id="capacity" min="0"
											step="0.01" value="0" name="Capacity" placeholder="Capacity"
											required> <label for="capacity">Capacity</label>
										<div class="invalid-feedback">Please, Enter Capacity!</div>
									</div>
								</div>
								<!-- Capacity Block End -->
								
								<div class="col-md-3 refresh-container mb-3"
									data-add="add_measurement.jsp" data-list="list_measure">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="measurement_name"
											placeholder="Select Measurement" autocomplete="off" required>
										<input type="hidden" id="measurement_id_fk"
											name="Measurement_id_fk" value="0"> <label
											for="measurement_name">Select Measurement</label>
										<div class="invalid-feedback">Please, Select
											Measurement!</div>
									</div>
								</div>

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
	<script src="js/master.js" type="text/javascript"></script>

	<br />
	<br />
	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

</body>
<!--  body End-->

</html>