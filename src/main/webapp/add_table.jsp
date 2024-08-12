<%@include file="include/head.jsp"%>
</head>
<body>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->

	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  Main Start -->
	<main id="main" class="main">


		<%@include file="include/breadcrumbs.jsp"%>
		
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Add Table</h1>
			</div>
		</div>
		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card" style="border-radius: 20px;">
						<div class="card-body">
							<br /> <br />

							<!-- Form Start-->
							<form action="TableServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>
								<input type="hidden" name="User_id_fk" value="<%=user_id%>">


								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											onblur="checkNameAvail(this.value, '0', 'Table');"
											placeholder="Name" required> <label for="name">Name</label>
										<div class="invalid-feedback">Please, enter Name!</div>
									</div>
								</div>

								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
								</div>

							</form>
							<!-- Form end -->

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