
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



		<!-- ======= Breadcrumbs ======= -->
		<%@include file="include/breadcrumbs.jsp"%>
		<!--  Breadcrumbs End-->

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Add Employee</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card" style="border-radius: 20px;">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->
							<form action="EmployeeServlet" enctype="multipart/form-data" autocomplete="off"
								method="post" class="row g-3 needs-validation" novalidate>

								<input type="hidden" value="<%=user_id%>" name="User_id_fk">
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											placeholder="Name" required> <label for="name">Name</label>
										<div class="invalid-feedback">Please, Enter Employee
											Name!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="guardian_name"
											name="Guardian_name" placeholder="Guardian Name" required>
										<label for="guardian_name">Guardian Name</label>
										<div class="invalid-feedback">Please, Enter Guardian
											Name!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="email" class="form-control" id="email_id"
											name="Email_id" placeholder="Email Id" required> <label
											for="email_id">Email Id</label>
										<div class="invalid-feedback">Please, enter Email
											Address!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" class="form-control" id="mobile_no"
											name="Mobile_no" placeholder="Mobile No" required> <label
											for="mobile_no">Mobile Number</label>
										<div class="invalid-feedback">Please, enter Mobile
											Number!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" class="form-control" id="other_no"
											name="Other_no" placeholder="Other_no"> <label
											for="other_no">Other Mobile Number</label>
									</div>
								</div>
								
								<div class="col-md-6 refresh-container mb-3"
									data-add="add_city.jsp" data-list="list_city">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control"
											id="city_name" placeholder="Select City"> <input type="hidden"
											id="city_id_fk" name="City_id_fk" value="0"> <label
											for="city_name">Select City</label>
										<div class="invalid-feedback">Please, Select City!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="date" class="form-control" placeholder="Date Of Birth"
											id="dob" name="Dob" required> <label for="dob">Date
											of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of
											Birth!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="date" class="form-control"
											placeholder="Date of Join" id="join_date" name="Join_date"
											required> <label for="join_date">Date of Join</label>
										<div class="invalid-feedback">Please, Enter Date of
											Join!</div>
									</div>
								</div>


								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" class="form-control"
											placeholder="Salary_per_month" id="salary_per_month"
											value="0" name="Salary_per_month" required> <label
											for="salary_per_month">Salary Per Month</label>
										<div class="invalid-feedback">Please, Enter Employee
											Salary Per Month!</div>
									</div>
								</div>


								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="bank" name="Bank"
											placeholder="Bank" required> <label for="bank">Bank
											Name</label>
										<div class="invalid-feedback">Please, Enter Employee
											Bank Name!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="account_no"
											name="Account_no" placeholder="Account No" required>
										<label for="account_no">Account Number</label>
										<div class="invalid-feedback">Please, Enter Employee
											Account No!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="ifsc_code"
											name="Ifsc_code" placeholder="Ifsc Code" required> <label
											for="ifsc_code">IFSC Code</label>
										<div class="invalid-feedback">Please, Enter Employee
											Ifsc Code!</div>
									</div>
								</div>


								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Address"
											id="address" Name="Address" required style="height: 70px"></textarea>
										<label for="address"> Address</label>
										<div class="invalid-feedback">Please, Enter Address!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control"
											placeholder="Qualification" id="qualification"
											name="Qualification" required> <label
											for="qualification">Qualification</label>
										<div class="invalid-feedback">Please, Enter
											Qualification!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control"
											placeholder="Experience" id="exp" name="Experience" required>
										<label for="exp">Experience</label>
										<div class="invalid-feedback">Please, Enter Experience!</div>
									</div>
								</div>

								<div class="d-flex col-md-6 ">
									<div class="col-md-4 ">
										<div class="form-floating">
											<div class="" style="padding: 0.5%;">
												<div class="d-flex justify-content-center pt-1">
													<img src="" id="preview-selected-image"
														onerror="this.src='assets/img/dummy.png'" class="preview"
														alt="" style="height: 60px; width: 60px;">
												</div>
												<div class="d-flex justify-content-center">
													<div class="pt-1 pb-1">
														<div style="display: none;">
															<input name="File" onchange="previewImage(event);"
																type="file" id="file">
														</div>

													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-7 mt-4" style="margin-left: 20px;">
										<label for="file" id="file-label"> <a
											class="btn btn-primary btn-sm"
											title="Choose employee profile Picture"><i
												class="bi bi-upload"></i></a> Profile Picture
										</label>
									</div>

								</div>


								<div class="col-md-6">
									<div class="form-control" style="height: 75px;">

										<div class="form-check">
											<input class="form-check-input" type="checkbox"
												id="photo_status" name="Photo_status" value="Yes"
												value="N/A"> <label class="form-check-label"
												for="photo" style="font-size: 13px;"> Passport photo
												submitted </label>
										</div>

										<div class="form-check">
											<input class="form-check-input" type="checkbox"
												id="id_card_status" name="Id_card_status" value="Yes"
												value="N/A"> <label class="form-check-label"
												for="Aadhar" style="font-size: 13px;"> Aadhar Card
												copy submitted </label>
										</div>

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

	<script>
		function previewImage(event) {
			let reader = new FileReader();
			reader.onload = function() {
				let element = document.getElementById('preview-selected-image');
				element.src = reader.result;
			}
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


	<script type="text/javascript" src="js/employee.js"></script>

</body>

</html>