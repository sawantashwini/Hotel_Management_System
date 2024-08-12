<%@include file="include/head.jsp"%>
<body>

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>


	<main id="main" class="main">
		<div class="row justify-content-center">

			<div class="pagetitle col-lg-10 text-center">
				<h1>User Profile</h1>
			
			</div>
			<!-- End Page Title -->
		</div>



		<section class="section profile">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card" style="border-radius: 15px;">
						<div class="card-body profile-card pt-4 d-flex flex-column align-items-center" style="border-radius: 13px;">
							<h2><%=user_head_dto.getName()%></h2>
						</div>
					</div>

				</div>

				<div class="col-lg-10">

					<div class="card" style="border-radius: 20px;">
						<div class="card-body pt-3">
							<!-- Bordered Tabs -->
							<ul class="nav nav-tabs nav-tabs-bordered">

								<li class="nav-item">
									<button class="nav-link active" data-bs-toggle="tab"
										data-bs-target="#profile-edit">Edit Profile</button>
								</li>
								
							</ul>
							<div class="tab-content pt-2">

								<div class="tab-pane fade show active profile-edit pt-3" id="profile-edit">
								
									<form action="UserServlet" method="post">

										<div class="row mb-3">
											<label for="fullName"
												class="col-md-4 col-lg-3 col-form-label">Name</label>
											<div class="col-md-8 col-lg-9">
												<input name="Name" type="text" class="form-control"
													id="name" value="<%=user_head_dto.getName() %>">
													<input type="hidden" value="<%=user_id%>" name="Id">
													<input type="hidden" value="<%=user_head_dto.getMobile_no()%>" name="Mobile_no">
													<input type="hidden" value="<%=user_head_dto.getStatus()%>" name="Status">
											</div>
										</div>

										<div class="row mb-3">
											<label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
											<div class="col-md-8 col-lg-9">
												<input name="Address" type="text" class="form-control"
													id="address" value="<%=user_head_dto.getAddress()%>">
											</div>
										</div>
										<div class="row mb-3">
											<label for="newPassword"
												class="col-md-4 col-lg-3 col-form-label">New
												Password</label>
											<div class="col-md-8 col-lg-9">
												<input name="Password" type="password" value="<%=user_head_dto.getPassword()%>"
													class="form-control" id="password">
											</div>
										</div>

										<div class="text-center">
											<button type="submit" class="submit-btn">Save
												Changes</button>
										</div>
									</form>
									<!-- End Profile Edit Form -->

								</div>



							</div>


						</div>
						<!-- End Bordered Tabs -->

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