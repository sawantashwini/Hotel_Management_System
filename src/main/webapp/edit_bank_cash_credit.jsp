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
				<h1>Bank Cash Credit</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-8">

					<div class="card" style="border-radius: 25px;">


						<div class="card-body">
							<br />


							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							BankCashCreditService ser = new BankCashCreditService();
							BankDto dto = ser.getCashCreditInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form autocomplete="off" action="BankCashCreditServlet"
								method="post" class="row g-3 needs-validation" novalidate>

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="date" class="form-control" id="in_date"
											name="In_date" value="<%=dto.getIn_date()%>"
											placeholder="In Date" required> <label for="in_date">In
											Date</label>
										<div class="invalid-feedback">Please, enter In Date!</div>
									</div>
								</div>


								<div class="col-md-3 refresh-container mb-3"
									data-add="add_bank.jsp" data-list="list_bank">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="bank_name"
											placeholder="Select Bank"> <input type="hidden"
											id="credit_bank_id_fk" name="Credit_bank_id_fk"
											value="<%=dto.getCredit_bank_id_fk()%>"> <label
											for="bank_name">Select Bank</label>
										<div class="invalid-feedback">Please, Select Bank!</div>
									</div>
								</div>


								<input type="hidden" name="Id" id="bank_cash_id" value="<%=id%>">

								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>"> <input type="hidden"
									name="Cash_id_fk" id="cash_id_fk"
									value="<%=dto.getCash_id_fk()%>"> <input type="hidden"
									name="Online_id_fk" id="Online_id_fk"
									value="<%=dto.getOnline_id_fk()%>">

								<div class="col-md-4">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control"
											id="credit_amount" value="<%=dto.getCredit_amount()%>"
											name="Credit_amount" placeholder="Amount" required> <label
											for="credit_amount">Amount</label>
										<div class="invalid-feedback">Please, enter Credit
											Amount!</div>
									</div>
								</div>

								<!-- Start Remark -->
								<div class="col-md-12">
									<div class="form-floating control">
										<textarea class="form-control" id="remark" name="Remark"
											placeholder="Remark"><%=dto.getRemark()%> </textarea>
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