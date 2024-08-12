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
				<h1>Bank</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-8">

					<div class="card" style="border-radius: 25px;">


						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form autocomplete="off" action="BankServlet" method="post"
								class="row g-3 needs-validation" novalidate>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="text"
											onblur="checkNameAvail(this.value, '0', 'Bank');"
											class="form-control" id="name" name="Name" placeholder="Name"
											required> <label for="name">Name</label>
										<div class="invalid-feedback">Please, enter name!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="branch"
											name="Branch" placeholder="Branch" required> <label
											for="branch">Branch</label>
										<div class="invalid-feedback">Please, enter Branch!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control"
											id="account_no" name="Account_no" placeholder="Account No"
											required> <label for="account_no">Account No</label>
										<div class="invalid-feedback">Please, enter "Account
											No"!</div>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="ifsc_code"
											name="Ifsc_code" placeholder="Ifsc code" required> <label
											for="ifsc_code">Ifsc Code</label>
										<div class="invalid-feedback">Please, enter Ifsc Code!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control"
											id="opening_bal" name="Opening_bal"
											placeholder="Opening Balance" required> <label
											for="opening_bal">Opening Balance</label>
										<div class="invalid-feedback">Please, enter Opening
											Balance!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="date" class="form-control" id="opening_date"
											name="Opening_date" placeholder="Opening Date" required>
										<label for="opening_date">Opening Date</label>
										<div class="invalid-feedback">Please, enter Opening
											Date!</div>
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