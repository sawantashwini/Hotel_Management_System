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
				<h1>Bank To Bank Transaction</h1>
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
							BankTransactionService ser = new BankTransactionService();
							BankDto dto = ser.getTransactionInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form autocomplete="off" action="BankTransactionServlet"
								method="post" class="row g-3 needs-validation" novalidate>

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="date" class="form-control" id="in_date"
											name="In_date" value="<%=dto.getIn_date()%>" placeholder="In Date" required> <label
											for="in_date">In Date</label>
										<div class="invalid-feedback">Please, enter In Date!</div>
									</div>
								</div>

								<div class="col-md-6 refresh-container mb-3"
									data-add="add_bank.jsp" data-list="list_bank">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control"
											id="credit_bank_name" placeholder="From Bank" value="<%=dto.getCredit_bank_name()%>"> <input type="hidden"
											id="credit_bank_id_fk" name="Credit_bank_id_fk" value="<%=dto.getCredit_bank_id_fk()%>"> <label
											for="credit_bank_name">From Bank</label>
										<div class="invalid-feedback">Please, Enter From Bank!</div>
									</div>
								</div>
								
								<div class="col-md-6 refresh-container mb-3" data-list="list_bank">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" value="<%=dto.getDebit_bank_name()%>"
											id="debit_bank_name" placeholder="To Bank"> <input type="hidden"
											id="debit_bank_id_fk" name="Debit_bank_id_fk" value="<%=dto.getDebit_bank_id_fk()%>"> <label
											for="debit_bank_name">To Bank</label>
										<div class="invalid-feedback">Please, Enter To Bank!</div>
									</div>
								</div>
								
								<input type="hidden" name="Id" id="transaction_id"
									value="<%=id%>">	
								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>">
								<input type="hidden" name="Credit_online_id_fk" id="credit_online_id_fk"
									value="<%=dto.getCredit_online_id_fk()%>">		
							    <input type="hidden" name="Debit_online_id_fk" id="debit_online_id_fk"
									value="<%=dto.getDebit_online_id_fk()%>">			

								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" step="0.01" min="0" class="form-control"
											id="transaction_amount" name="Transaction_amount" value="<%=dto.getTransaction_amount()%>"
											placeholder="Amount" required> <label
											for="transaction_amount">Amount</label>
										<div class="invalid-feedback">Please, enter Transaction
											Amount!</div>
									</div>
								</div>

								<!-- Start Remark -->
								<div class="col-md-12">
									<div class="form-floating control">
										<textarea  class="form-control" id="remark"
											name="Remark" placeholder="Remark"><%=dto.getRemark()%></textarea> <label
											for="remark">Remark</label>
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


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>