
<%@include file="include/head.jsp"%>
</head>


<body>


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->


	<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>

	<main id="main" class="main">

		<div class="row justify-content-center" style="margin-top: 5%;">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Spend Head</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-8">

					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->

							<% 
						int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							SpendHeadService service = new SpendHeadService();
							SpendHeadDto dto = service.getSpendHeadInfoById(id, config, request);
						%>


							<!-- Floating Labels Form -->
							<form autocomplete="off" action="SpendHeadServlet" method="post"
								class="row g-3 needs-validation" novalidate>


								<div class="col-md-6">
									<div class="control form-floating">
										<input type="hidden" id="id" name="Id" value="<%=id%>">
										<input type="hidden" name="User_id_fk" value="<%=user_id%>">

										<input type="text" class="form-control" id="name" name="Name"
											value="<%=dto.getName()%>" placeholder="name"
											onblur="checkNameAvail(this.value, <%=id%>, 'Spend Head');"
											required> <label for="Name">Name</label>
										<div class="invalid-feedback">Please, enter name!</div>
									</div>
								</div>


								<div class="col-md-6 col-6">

									<div class="form-floating control mb-3">
										<select class="form-select" id="status" name="Status"
											aria-label="working post">
											<option value="Active" <%=dto.getStatus().equals("Active") ? "selected" : "" %>>Active</option>
											<option value="Block"  <%=dto.getStatus().equals("Block") ? "selected" : "" %> >Block</option>
										</select> <label for="Status">Status</label>
										<div class="invalid-feedback">Please, Select Spend
											Status!</div>
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

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>