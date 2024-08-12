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

	<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>

	<!--  main start-->
	<main id="main" class="main">
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Liquor Brand</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-10">
					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />
							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							LiquorCategoryService service = new LiquorCategoryService();
							LiquorCategoryDto dto = service.getLiquorCategoryInfoById(id, config, request);
							%>
							<!-- Floating Labels Form -->
							<form action="LiquorCategoryServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>

								<!-- Name Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="hidden" name="Id"
											id="liquor_brand_id" value="<%=id%>"> <input type="text"
											class="form-control" id="name" name="Name" placeholder="Name"
											value="<%=dto.getName()%>"
											onblur="checkNameAvail(this.value, <%=id%>, 'Category');"
											required> <label for="name">Name</label>
										<div class="invalid-feedback">Please, Enter name!</div>
									</div>
								</div>
								<!-- Name Block End -->

								<!-- Min_limit Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="min_qty" min="0"
											step="0.01" name="Min_qty" placeholder="Min qty"
											value="<%=dto.getMin_qty()%>" required> <label
											for="min_qty">Minimum Quantity</label>
										<div class="invalid-feedback">Please, Enter Minimum
											Quantity!</div>
									</div>
								</div>
								<!-- Min_limit Block End -->

								<!-- Capacity Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="capacity" min="0"
											step="0.01" name="Capacity" value="<%=dto.getCapacity()%>"
											placeholder="Capacity" required> <label
											for="capacity">Capacity</label>
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
											name="Measurement_id_fk" value="<%=dto.getMeasurement_id_fk()%>"> <label
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