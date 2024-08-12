
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
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Employee</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card" style="border-radius: 20px;">
						<div class="card-body">
							<br />

							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							EmployeeService service = new EmployeeService();

							EmployeeDto dto = service.getEmployeeInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form action="EmployeeServlet" enctype="multipart/form-data"
								autocomplete="off" method="post"
								class="row g-3 needs-validation" novalidate>

								<input type="hidden" name="User_id_fk" value="1"> <input
									type="hidden" name="Id" value="<%=id%>">

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											placeholder="Name" value="<%=dto.getName()%>" required>
										<label for="name">Name</label>
										<div class="invalid-feedback">Please, enter Employee
											Name!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="guardian_name"
											name="Guardian_name" value="<%=dto.getGuardian_name()%>"
											placeholder="Guardian Name" required> <label
											for="name">Guardian Name</label>
										<div class="invalid-feedback">Please, Enter Guardian
											Name!</div>
									</div>
								</div>


								<div class="col-md-6">
									<div class="control control form-floating">
										<input type="email" class="form-control" id="email_id"
											name="Email_id" placeholder="Email Id"
											value="<%=dto.getEmail_id()%>" required> <label
											for="email_id">Email Id</label>
										<div class="invalid-feedback">Please, enter Email
											Address!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control control form-floating">
										<input type="number" class="form-control" id="mobile_no"
											name="Mobile_no" placeholder="Mobile Number"
											value="<%=dto.getMobile_no()%>" required> <label
											for="mobile_no">Mobile Number</label>
										<div class="invalid-feedback">Please, enter Mobile
											Number!</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="control control form-floating">
										<input type="number" class="form-control" id="other_no"
											name="Other_no" placeholder="Other Mobile Number"
											value="<%=dto.getOther_no()%>"> <label for="other_no">Other
											Mobile Number</label>
									</div>
								</div>
								
								
								<div class="col-md-6 refresh-container mb-3"
									data-add="add_city.jsp" data-list="list_city">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" value="<%=dto.getCity_name()%>"
											id="city_name" placeholder="Select City"> <input type="hidden"
											id="city_id_fk" name="City_id_fk" value="<%=dto.getCity_id_fk()%>"> <label
											for="city_name">Select City</label>
										<div class="invalid-feedback">Please, Select City!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="date" class="form-control"
											placeholder="Date Of Birth" id="dob" name="Dob"
											value="<%=dto.getDob()%>" required> <label for="dob">Date
											of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of
											Birth!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="date" class="form-control"
											placeholder="Date of Join" id="join_date" name="Join_date"
											value="<%=dto.getJoin_date()%>" required> <label
											for="join_date">Date of Join</label>
										<div class="invalid-feedback">Please, Enter Date of
											Join!</div>
									</div>
								</div>


								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" class="form-control"
											placeholder="Salary Per Month" id="salary_per_month"
											name="Salary_per_month"
											value="<%=dto.getSalary_per_month()%>" required> <label
											for="salary_per_month">Salary Per Month</label>
										<div class="invalid-feedback">Please, Enter Salary Per
											Month!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="bank" name="Bank"
											placeholder="Bank Name" value="<%=dto.getBank()%>" required>
										<label for="bank">Bank Name</label>
										<div class="invalid-feedback">Please, Enter Bank Name!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="account_no"
											name="Account_no" placeholder="Account Number"
											value="<%=dto.getAccount_no()%>" required> <label
											for="account_no">Account Number</label>
										<div class="invalid-feedback">Please, Enter Employee
											Account No!</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="text" class="form-control" id="ifsc_code"
											name="Ifsc_code" placeholder="Ifsc Code"
											value="<%=dto.getIfsc_code()%>" required> <label
											for="ifsc_code">IFSC Code</label>
										<div class="invalid-feedback">Please, Enter Employee
											IFSC Code!</div>
									</div>
								</div>


								<div class="col-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Address"
											id="address" Name="Address" required style="height: 100px"><%=dto.getAddress()%></textarea>
										<label for="address"> Address</label>
										<div class="invalid-feedback">Please, Enter Address!</div>
									</div>
								</div>


								<div class="col-md-12">
									<div class="control form-floating">
										<input type="text" class="form-control"
											placeholder="Qualification" id="qualification"
											name="Qualification" value="<%=dto.getQualification()%>"
											required> <label for="qualification">Qualification</label>
										<div class="invalid-feedback">Please, Enter
											Qualification!</div>
									</div>
								</div>


								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control"
											placeholder="Experience" id="exp" name="Experience"
											value="<%=dto.getExperience()%>" required> <label
											for="experience">Experience</label>
										<div class="invalid-feedback">Please, Enter Experience!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="control form-floating">
										<input type="date" class="form-control"
											placeholder="Date of Resign" id="resign_date"
											name="Resign_date" value="<%=dto.getResign_date()%>">
										<label for="resign_date">Date of Resign</label>
										<div class="invalid-feedback">Please, Enter Date of
											Resign!</div>
									</div>
								</div>

								<div class="col-md-3">
									<div class="form-floating control mb-3">
										<select class="form-select" id="status" name="Status"
											aria-label="working post">
											<option value="Active" <%=dto.getStatus().equals("Active") ? "selected" : ""%>>Active</option>

											<option value="Block" <%=dto.getStatus().equals("Block") ? "selected" : ""%>>Block</option>

										</select> <label for="status">Status</label>
										<div class="invalid-feedback">Please, select Employee
											Status!</div>
									</div>
								</div>

								<div class="d-flex col-md-6 ">
									<div class="col-md-4 ">
										<div class="form-floating">
											<div class="" style="padding: 0.5%;">
												<div class="d-flex justify-content-center pt-1">
													<img src="EmployeeImage/<%=dto.getId()%>.jpg"
														id="preview-selected-image"
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
									<div class="form-control" style="height: 65px;">

										<div class="form-check">

											<input class="form-check-input" type="checkbox" <%=dto.getPhoto_status().equals("Yes") ? "checked" : ""%>
												id="photo_status" name="Photo_status" value="Yes" >
											<label class="form-check-label" for="photo"
												style="font-size: 13px;"> Passport photo submitted </label>
										</div>
										<div class="form-check" style="height: 65px;">
										 <input <%=dto.getId_card_status().equals("Yes") ? "checked" : ""%>
												class="form-check-input" type="checkbox" id="id_card_status"
												name="Id_card_status" value="Yes" >
											<label class="form-check-label" for="Aadhar"
												style="font-size: 13px;"> Aadhar Card copy submitted
											</label>
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