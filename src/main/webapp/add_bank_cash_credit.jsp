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
				<h1>Bank Cash Credit</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-8">

					<div class="card" style="border-radius: 25px;">


						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form autocomplete="off" action="BankCashCreditServlet"
								method="post" class="row g-3 needs-validation" novalidate>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="date" class="form-control" id="in_date"
											name="In_date" value="<%=current_date%>"
											placeholder="In_date" required> <label for="in_date">In
											Date</label>
										<div class="invalid-feedback">Please, enter In Date!</div>
									</div>
								</div>
								
								<div class="col-md-4 refresh-container mb-3"
									data-add="add_bank.jsp" data-list="list_bank">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control"
											id="bank_name" placeholder="Bank Name"> <input type="hidden"
											id="credit_bank_id_fk" name="Credit_bank_id_fk" value="0"> <label
											for="bank_name">Bank Name</label>
										<div class="invalid-feedback">Please, enter Bank Name!</div>
									</div>
								</div>

								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>">

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control"
											id="credit_amount" name="Credit_amount" placeholder="Amount"
											required> <label for="credit_amount">Amount</label>
										<div class="invalid-feedback">Please, enter Cash Credit
											Amount!</div>
									</div>
								</div>

								<!-- Start Remark -->
								<div class="col-md-12">
									<div class="form-floating control">
										<textarea class="form-control" id="remark" name="Remark"
											placeholder="Remark"></textarea>
										<label for="remark">Remark</label>
										<div class="invalid-feedback">Please, enter Remark!</div>
									</div>
								</div>
								<!-- end Remark -->

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