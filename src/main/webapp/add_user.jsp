<%@include file="include/head.jsp"%>
</head>
<body>

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<main id="main" class="main">

		<%@include file="include/breadcrumbs.jsp"%>
		
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>User</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-7">

					<div class="card" style="border-radius: 37px;">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->

							<form action="UserServlet" method="post" autocomplete="off"
								class="row mb-4 mt-2 needs-validation" novalidate
								style="margin-top: -20px;">
								<div class="col-sm-12 mt-3">
									<label for="yourName" class="form-label" style="color: #f00;">
										Name</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="yourName"><i
											class="ri-user-fill " style="color: #f00;"></i></span> <input
											type="text" name="Name" class="form-control" id="yourName"
											required>

										<div class="invalid-feedback">Please, enter your name!</div>
									</div>
								</div>
								<br> <br>
								<div class="col-sm-12 mt-3">
									<label for="name" class="form-label" style="color: #f00;">
										Mobile No.</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="PhoneNo."><i
											class="bi bi-phone-fill " style="color: #f00;"></i></span> <input
											class="form-control" type="text" id="name" name="Mobile_no"
											onblur="checkNameAvail(this.value, '0', 'Mobile');">
										<div class="invalid-feedback">Please, enter your mobile
											No.!</div>
									</div>
								</div>
								<br> <br>

								<div class="col-12 mt-3">
									<label for="password" class="form-label" style="color: #f00;">Password</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="Password"><i
											class="ri-lock-2-fill " style="color: #f00;"></i> </span> <input
											type="password" name="Password" class="form-control"
											id="password" required>
										<div class="invalid-feedback">Please enter your
											password!</div>
									</div>
								</div>

								<br> <br>

								<div class="col-12 mt-3">
									<label for="yourUsername" class="form-label"
										style="color: #f00;">Address</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="Address"><i
											class="bi bi-geo-alt-fill " style="color: #f00;"></i></span> <input
											type="text" name="Address" class="form-control" id="address"
											required>
										<div class="invalid-feedback">Please choose a Address.</div>
									</div>
								</div>


								<br> <br>

								<div class="row mt-3">
									<div class="col-sm-12">
										<button type="submit" id="submit" class="submit-btn"
											style="margin-left: 40%;">
											<b>Submit </b>
										</button>
									</div>
								</div>



							</form>
							<!-- End General Form Elements -->


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