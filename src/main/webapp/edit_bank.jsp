<%@include file="include/head.jsp"%>
</head>
<body >
				

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
				<h1>Edit Bank</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />
				
							<!-- Floating Labels Form -->

							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							BankService service = new BankService();
							BankDto dto = service.getBankInfoById(id, config, request);
							%>


							<!-- Floating Labels Form -->
							<form autocomplete="off" action="BankServlet" 
								method="post" class="row g-3 needs-validation" novalidate>
							
							
							
							<div class="col-md-6">
									<div class="control form-floating">
									<input type="hidden" name="Id" id="id" value="<%=id%>">
									<input type="hidden" name="User_id_fk"  id="user_id_fk"value="<%=user_id%>">
									
										<input type="text" class="form-control" id="name" onblur="checkNameAvail(this.value, <%=id%>, 'Bank');"
											name="Name" value="<%=dto.getName()%>" placeholder="name" required> <label
											for="name">Name</label>
										<div class="invalid-feedback">Please, enter name!</div>
									</div>
								</div>


								<div class="col-md-6">
									<div class="control form-floating">
									
										<input type="text" class="form-control" id="branch"
											name="Branch" value="<%=dto.getBranch()%>" placeholder="Branch" required> <label
											for="name">Branch</label>
										<div class="invalid-feedback">Please, enter Branch!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
									
										<input type="number" step="0.01" min="0" class="form-control" id="account_no"
											name="Account_no"  value="<%=dto.getAccount_no()%>"placeholder="Account No" required> <label
											for="account_no">Account No</label>
										<div class="invalid-feedback">Please, enter "Account No"!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
									
										<input type="text" class="form-control" id="ifsc_code"
											name="Ifsc_code" value="<%=dto.getIfsc_code()%>" placeholder="IFSC Code" required> <label
											for="ifsc_code">IFSC Code</label>
										<div class="invalid-feedback">Please, enter Ifsc Code!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control" id="opening_bal"
											name="Opening_bal"  value="<%=dto.getOpening_bal()%>" placeholder="Opening_bal" required>
										<label for="opening_bal">Opening Balance</label>
										<div class="invalid-feedback">Please, enter Opening Balance!</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="control form-floating">
									
										<input type="date" class="form-control" id="opening_date"
											name="Opening_date" value="<%=dto.getOpening_date()%>" placeholder="Opening Date" required> <label
											for="opening_date">Opening Date</label>
										<div class="invalid-feedback">Please, enter Opening Date!</div>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="date" class="form-control" id="in_date"
											name="In_date" value="<%=dto.getIn_date()%>" placeholder="In Date" required>
										<label for="in_date">In Date</label>
										<div class="invalid-feedback">Please, enter In Date!</div>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control" id="credit"
											name="Credit" value="<%=dto.getCredit()%>" placeholder="Credit Amount" required>
										<label for="credit_amt">Credit Amount</label>
										<div class="invalid-feedback">Please, enter Credit Amount!</div>
									</div>
								</div>
								
								
								<div class="col-md-6 col-6 mt-3">
								
									<div class="form-floating control mb-3 mt-3">
										<select class="form-select" id="status" name="Status"
											aria-label="working post">
											<option value="Active"
												<%if (dto.getStatus().equals("Active")) {
										out.print("selected='selected'");
									}%>>Active</option>

											<option value="Block"
												<%if (dto.getStatus().equals("Block")) {
										out.print("selected='selected'");
									}%>>Block</option>

										</select> <label for="Status">Status</label>
										<div class="invalid-feedback">Please, Select Bank
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