
<!-- ======= Header ======= -->
<%@include file="include/head.jsp"%>
<!-- ======= Header end======= -->

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
				<h1>Add Customer</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card">


						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form action="CustomerServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation"
								novalidate>
								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>">
								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											
											placeholder="Name" required> <label for="Name">
											Name</label>
										<div class="invalid-feedback">Please, Enter Customer
											Name !</div>
									</div>
								</div>

								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="number" class="form-control" id="mobile_no"
											onblur="checkNameAvail(this.value,'0','Customer');" name="Mobile_no" placeholder="Mobile_no  " required>
										<label for="mobile_no">Mobile no.</label>
										<div class="invalid-feedback">Please, Enter Mobile
											Number!</div>
									</div>
								</div>


								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="gst_no"
											name="Gst_no" placeholder="Gst No"> <label
											for="gst_no">GST In</label>
										<div class="invalid-feedback">Please, Enter GST Number!</div>
									</div>
								</div>


								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="number" class="form-control" id="opening_due"
											name="Opening_due" placeholder="Opening Due " value="0"
											 step="0.01" min="0"> <label for="opening_due">Opening
											due</label>
										<div class="invalid-feedback">Please, Enter Opening due
											!</div>
									</div>
								</div>
								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="company_name"
											name="Company_name" placeholder="Company Name">
										<label for="company_name"> Company Name</label>
										<div class="invalid-feedback">Please, Enter Company Name
											!</div>
									</div>
								</div>
									<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="dob"
											name="Dob" placeholder="Date of Birth">
										<label for="dob"> Date of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of Birth
											!</div>
									</div>
								</div>
									<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa"
											name="Doa" placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of Anniversary
											!</div>
									</div>
								</div>
								<div class="col-md-12 col-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Address"
											id="address" name="Address" style="height: 70px"></textarea>
										<label for="Customer Address"> Address</label>
										<div class="invalid-feedback">Please, Enter Customer
											Address !</div>
									</div>
								</div>

								<input type="hidden" id="c_y_session" name="C_y_session"
									value="2023-2024"> <input type="hidden" id="in_date"
									name="In_date" value="<%=current_date%>">


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
	<script src="js/jquery-1.9.1.js" type="text/javascript"></script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>