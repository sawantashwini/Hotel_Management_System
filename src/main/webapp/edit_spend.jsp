
<%@include file="include/head.jsp"%>
<script type="text/javascript" src="js/spend-income.js"></script>
</head>


<body onload="loadPaymentMode();">


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->




	<main id="main" class="main">


		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Spend</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-12">

					<div class="card" style="border-radius: 25px;">


						<div class="card-body">
							<br /> <br>
							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							SpendService service = new SpendService();
							SpendDto dto = service.getSpendInfoById(id, config, request);
							%>




							<!-- Floating Labels Form -->
							<form action="SpendServlet" enctype="multipart/form-data"
								autocomplete="off" method="post"
								class="row g-3 needs-validation" novalidate>


								<div class="col-md-3">
									<div class="control form-floating">
										<input type="hidden" id="user_id_fk" name="User_id_fk"
											value="<%=user_id%>"> <input type="hidden"
											id="spend_id" name="Id" value="<%=id%>"> <input
											type="Date" class="form-control" id="in_date" name="In_date"
											value="<%=dto.getIn_date()%>" placeholder="In date" required>
										<label for="in_date">In date</label>
										<div class="invalid-feedback">Please, enter in_date!</div>
									</div>
								</div>

								<div class="col-md-4 refresh-container mb-3"
									data-add="add_liquor_item.jsp" data-list="list_spend_head">
									<div class="control form-floating refresh-input">
										<input type="text" class="form-control" id="name" name="Name"
											placeholder="Name" autocomplete="off" required> <input
											type="hidden" id="head_id_fk" name="Head_id_fk"
											value="<%=dto.getHead_id_fk()%>"> <label for="name">Spend
											Head</label>
										<div class="invalid-feedback">Please, enter Spend Head!</div>
									</div>
								</div>


								<!-- Cash Amount Start -->
								<div class="col-md-3" id="cash_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent()" id="cash_amount"
											name="Cash_amount" placeholder="Cash Amount"
											value="<%=dto.getDebit_cash_amount()%>" required> <label
											for="cash_amount">Cash Amount</label>
									</div>
								</div>
								<!-- Cash Amount end -->

								<!-- Online Amount Start -->
								<div class="d-none" id="online_amt_block">
									<div class="control form-floating">
										<input type="number" step="0.01" class="form-control"
											onblur="paidAmountEvent()" id="online_amount"
											name="Online_amount" placeholder="Online Amount"
											value="<%=dto.getDebit_online_amount()%>" required> <label
											for="online_amount">Online Amount</label>
									</div>
								</div>
								<!-- Online Amount end -->

								<!-- Paid Amount Start -->
								<div class="d-none" id="paid_block">
									<div class="control form-floating">
										<input readonly type="number" step="0.01"
											style="background-color: #e7ededad;" class="form-control"
											id="paid_amount" name="Paid_amount" placeholder="Paid Amount"
											value="<%=dto.getDebit_cash_amount() + dto.getDebit_online_amount()%>"
											required> <label for="paid_amount">Paid
											Amount</label>
									</div>
								</div>
								<!-- Paid Amount end -->

								<div class="d-none" id="online_block">



									<!-- Online Date Start-->
									<div class="col-md-3">
										<div class="control form-floating">
											<input type="date" class="form-control"
												placeholder="Online Date" id="online_date"
												value="<%=dto.getOnline_date()%>" name="Online_date">
											<label for="online_date">Online Date</label>
											<div class="invalid-feedback">Please, Enter Online
												Date!</div>
										</div>
									</div>
									<!-- Online Date End -->

									<!-- Online way start -->
									<div class="col-md-3">
										<div class="control form-floating mb-3">
											<select class="form-select" id="online_way" name="Online_way"
												aria-label="working post">
												<option value="N/A" Selected='selected'>Select
													Online way</option>
												<%
												for (String online_way : Arrays.asList( "Online", "Phonepay",  "Paytm", "Gpay", "Cheque")) {
												%>

												<option value="<%=online_way%>"
													<%=online_way.equalsIgnoreCase(dto.getOnline_way()) ? "selected" : ""%>><%=online_way%></option>

												<%
												}
												%>


											</select> <label for="online_way">Select Online Way</label>
										</div>
									</div>
									<!-- Online way end -->

									<div class="col-md-3 refresh-container mb-3"
										data-add="add_bank.jsp" data-list="list_bank">
										<div class="control form-floating refresh-input">
											<input type="text" class="form-control" id="bank_name"
												placeholder="Select Bank"> <input type="hidden"
												id="bank_id_fk" name="Bank_id_fk"
												value="<%=dto.getBank_id_fk()%>"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>

									<!-- Online Remark Start -->
									<div class="col-md-3">
										<div class="control form-floating">
											<input class="form-control" id="online_remark"
												name="Online_remark" placeholder="Online Remark"
												value="<%=dto.getOnline_remark()%>"> <label
												for="online_remark">Online Remark</label>
										</div>
									</div>
									<!-- Online Remark End -->

								</div>
								<!-- *** Online Block End *** -->

								<h6 class="text-center mt-2 card-title">Payment Mode</h6>

								<div class="row g-3 d-flex justify-content-center">
									<div class="col-lg-3 col-md-4 mt-0">
										<div class="d-flex justify-content-around">
											<%
											String paymentMode = dto.getPayment_mode();
											%>

											<%
											for (String mode : Arrays.asList("Cash", "Online", "Both")) {
											%>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="Payment_mode1" id="<%=mode.toLowerCase()%>_mode"
													<%=mode.equals(paymentMode) ? "checked" : ""%>
													value="<%=mode%>" onclick="checkPaymentMode();"> <label
													class="form-check-label" for="<%=mode.toLowerCase()%>_mode">
													<%=mode%>
												</label>
											</div>
											<%
											}
											%>
										</div>
									</div>
								</div>


								<input type="hidden" id="payment_mode" name="Payment_mode"
									value="Cash" /> <input type="hidden" id="type" name="Type"
									value="Spend" />

								<!--  onchange="previewImage(event);" -->
								<div class="d-flex justify-content-center">
									<div class="col-md-3">
										<div class="control form-floating">
											<div class="file-form" style="padding: 2%;">
												<%-- <div class="d-flex justify-content-center">
													<img src="SpendImage/<%=dto.getId()%>.jpg"
														id="preview-selected-image" alt="No Photo"
														style="height: 60px; width: 60px;">
												</div> --%>
												<div class="d-flex justify-content-center">
													<div class="pt-1 pb-1">
														<div style="display: none;">
															<input name="File" type="file" id="file">
														</div>
														<label for="file" id="file-label"> <a
															class="btn btn-primary btn-sm"
															title="Upload new profile image"> <i
																class="bi bi-upload"></i>
														</a>
														</label>
													</div>
													<div class="pt-1 pb-1" style="margin-left: 2px;">
														<a class="btn btn-primary btn-sm" title="download"
															href="SpendImage/<%=dto.getId()%>.jpg" id="download"
															download><i class="fa-solid fa-download"></i> </a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>


								<div class="col-md-12">
									<div class="control form-floating">
										<textarea class="form-control" placeholder="Remark"
											id="remark" Name="Remark"><%=dto.getRemark()%></textarea>
										<label for="remark">Remark</label>
									</div>
								</div>

								<input type="hidden" id="id" name="Id" value="<%=dto.getId()%>">

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
		<div id="result_spend"></div>

	</main>
	<!-- End main -->
	<br />
	<br />

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>
</html>