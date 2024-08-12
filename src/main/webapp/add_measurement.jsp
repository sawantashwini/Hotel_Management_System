
<!-- head -->
<%@include file="include/head.jsp"%>
<!-- head end -->
</head>

<body>



	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->


	<main id="main" class="main">
		
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Add Measurement</h1>
			</div>
		</div>
		
		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-8">

					<div class="card">


						<div class="card-body">
							<br>

							<!-- Floating Labels Form -->
							<form action="MeasurementServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>

								<div class="col-md-6 mb-8">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" id="user_id_fk"
											value="1"> <input type="text" class="form-control"
											id="name" onblur="checkNameAvail(this.value,'0','Measurement');"
											name="Name" placeholder="Name" required> <label
											for="name">Name</label>
										<div class="invalid-feedback">Please, enter name!</div>
									</div>
								</div>


								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>

								</div>


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

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>