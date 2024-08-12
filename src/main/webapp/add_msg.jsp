
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

		
		<!-- ======= Breadcrumbs ======= -->
		<%@include file="include/breadcrumbs.jsp"%>
		<!--  Breadcrumbs End-->

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Add Msg</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-6">

					<div class="card">
						<div class="card-body">
							<br>

							<!-- Floating Labels Form -->
							<form autocomplete="off" action="MsgServlet" method="post"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>

								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>">

								<!-- Msg Start -->

								<div class="col-md-8">
									<div class="form-floating control">
										<input type="text" class="form-control" id="name" name="Name"
											placeholder="Name"> <label for="name">Msg</label>
										<div class="invalid-feedback">Please, Enter Msg!</div>
									</div>
								</div>

								<!-- Msg End -->


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
	<br>
	<br>


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->
</body>

</html>