
<%@include file="include/head.jsp"%>
</head>
<!-- body Start -->
<body>
	

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->
	
	<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>

	<!-- main Start -->
	<main id="main" class="main">


		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Ingredients Item</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />

							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							IngredientsItemService service = new IngredientsItemService();
							IngredientsDto dto = service.getIngredientsItemInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form action="IngredientsItemServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>



								<!-- Name Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="hidden" name="Id" id="ingredient_item_id" value="<%=id%>">
										<input type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="text"
											class="form-control" id="name" name="Name" placeholder="Name"
											value="<%=dto.getName()%>"
											onblur="checkNameAvail(this.value,<%=id%>, 'Ingredients');"
											required> <label for="name">Name</label>
										<div class="invalid-feedback">Please, Enter name!</div>
									</div>
								</div>
								<!-- Name Block End -->

								<!-- min_limit Block Start -->
								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" class="form-control" id="min_limit" min="0"
											step="0.01" value="<%=dto.getMin_limit()%>" name="Min_limit"
											placeholder="Min Limit" required> <label
											for="min_limit">Minimum Limit</label>
										<div class="invalid-feedback">Please, Enter Minimum
											Limit!</div>
									</div>
								</div>
								<!-- min_limit Block End -->
								
								
								<div class="col-md-3 refresh-container mb-3"
									data-add="add_measurement.jsp" data-list="list_measure">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="measurement_name" value="<%=dto.getMeasurement_name()%>"
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
	<br />
	<br />
	<script src="js/master.js" type="text/javascript"></script>
	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

</body>
<!-- body End -->

</html>