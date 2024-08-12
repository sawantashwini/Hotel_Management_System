
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
	
		<%-- <%@include file="include/breadcrumbs.jsp"%> --%>

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Add Dealer</h1>
			</div>
		</div>


		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form action="DealerServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>
								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>"> <input type="hidden" id="in_date"
									name="In_date" value="<%=current_date%>">
								<%-- <input
									type="hidden" name="C_y_session" id="c_y_session"
									value="<%=session_year%>" >--%>

								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											onblur="checkNameAvail(this.value,'0','Dealer');"
											placeholder="Name" required> <label for="name">
											Name</label>
										<div class="invalid-feedback">Please, Enter Dealer Name
											!</div>
									</div>
								</div>

								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="number" class="form-control" id="mobile_no"
											name="Mobile_no" placeholder="Mobile No"> <label
											for="mobile_no">Mobile No.</label>
										<div class="invalid-feedback">Please, Enter Mobile
											Number!</div>
									</div>
								</div>

								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="gst_no"
											name="Gst_no" placeholder="Gst No"> <label
											for="gst_no">GST In</label>
										<div class="invalid-feedback">Please, Enter GST Number!</div>
									</div>
								</div>


								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="number" class="form-control" id="old_due"
											name="Old_due" placeholder="Old Due"  step="0.01"
											value="0" min="0"> <label for="Old_due">Opening
											due</label>
										<div class="invalid-feedback">Please, Enter Opening due
											!</div>
									</div>
								</div>


								<div class="col-md-12 col-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Address"
											id="address" name="Address" style="height: 70px"></textarea>
										<label for="address">Address</label>
										<div class="invalid-feedback">Please, Enter Dealer
											Address !</div>
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

	<script src="js/jquery-1.9.1.js" type="text/javascript"></script>

	<!-- Footer -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>