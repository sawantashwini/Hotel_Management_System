
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

	<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>

	<main id="main" class="main">
		<div class="row justify-content-center" style="margin-top: 5%;">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Customer</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card">
						<div class="card-body">
							<br />

							<!-- Floating Labels Form -->

							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							CustomerService service = new CustomerService();
							CustomerDto dto = service.getCustomerInfoById(id, config, request);
							%>


							<!-- Floating Labels Form -->
							<form action="CustomerServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>
								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>"> <input type="hidden" name="Id"
									id="id" value="<%=id%>">
								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name" name="Name"
											onblur="checkNameAvail(this.value,<%=id%>,'Customer');"
											placeholder="Name" value="<%=dto.getName()%>"> <label
											for="Name"> Name</label>
										<div class="invalid-feedback">Please, Enter Customer
											Name !</div>
									</div>
								</div>

								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="mobile_no"
											name="Mobile_no" placeholder="Mobile_no"
											value="<%=dto.getMobile_no()%>"> <label
											for="mobile_no">Mobile No.</label>
										<div class="invalid-feedback">Please, Enter Mobile
											Number!</div>
									</div>
								</div>



								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="gst_no"
											name="Gst_no" placeholder="gst_no"
											value="<%=dto.getGst_no()%>"> <label for="gst_no">GST
											In</label>
										<div class="invalid-feedback">Please, Enter GST Number!</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="company_name"
											name="Company_name" placeholder="Company_name"
											value="<%=dto.getCompany_name()%>" required> <label
											for="company_name"> Company Name</label>
										<div class="invalid-feedback">Please, Enter Company Name
											!</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="dob"
											name="Dob" value="<%=dto.getDob() %>" placeholder="Date of Birth">
										<label for="dob"> Date of Birth</label>
										<div class="invalid-feedback">Please, Enter Date of Birth
											!</div>
									</div>
								</div>
									<div class="col-md-6 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="doa"
											name="Doa" value="<%=dto.getDoa() %>" placeholder="Date of Anniversary">
										<label for="doa"> Date of Anniversary</label>
										<div class="invalid-feedback">Please, Enter Date of Anniversary
											!</div>
									</div>
								</div>
								<div class="col-md-12 col-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Address"
											id="address" Name="Address" required style="height: 100px"><%=dto.getAddress()%></textarea>
										<label for="Customer Address">Address</label>
										<div class="invalid-feedback">Please, Enter Customer
											Address !</div>
									</div>
								</div>

								<div class="col-md-4 col-12"></div>

								<input type="hidden" id="c_y_session" name="C_y_session"
									value="2023-2024">


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