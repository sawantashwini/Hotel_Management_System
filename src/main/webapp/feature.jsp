
<%@include file="include/head.jsp"%>
<style>
.form-label {
	color: #f00;
	font-size: 15px;
	font-weight: bolder;
}

.div-width {
padding-left: 1.5em;
	width: fit-content;
	white-space: nowrap;
}

.form-check h6 {
	color: #787373;
	font-size: 13px;
	font-weight: 400;
}

.form-check h6 i {
	font-size: 13px;
	padding-right:2px ;
}

.form-check-label {
	color: #000;
	font-size: 12px;
}

.form-check-input {
	margin-top: 4px;
}

.icon-color {

	color: #f00;
}
</style>
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
				<h1>Features</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-10">

					<div class="card" style="border-radius: 25px;">
						<div class="card-body">
							<br />

							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							UserService service = new UserService();
							UserDto dto = service.getuserInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form autocomplete="off" action="UserServlet" method="post"
								class="row g-3 needs-validation" novalidate>

								<div class="col-sm-6 mt-3">
									<label for="yourName" class="form-label"> Name</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="Name"><i
											class="ri-user-fill icon-color"></i></span> <input type="text"
											name="Name" class="form-control" id="name"
											value="<%=dto.getName()%>" required> <input
											type="hidden" value="<%=id%>" name="Id"> <input
											type="hidden" value="2" name="flag">


									</div>
								</div>



								<div class="col-sm-6 mt-3">
									<label for="yourName" class="form-label"> Mobile No.</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="PhoneNo."><i
											class="bi bi-phone-fill icon-color"></i></span> <input type="number"
											step="0.01" min="0"
											onblur="checkNameAvail(this.value, <%=id%>, 'Mobile');"
											name="Mobile_no" class="form-control" id="mobileno"
											value="<%=dto.getMobile_no()%>" required>
										<div class="invalid-feedback">Please, enter your Phone
											No.!</div>
									</div>
								</div>


								<div class="col-6 mt-3">
									<label for="yourPassword" class="form-label">Password</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="Password"><i
											class="ri-lock-2-fill icon-color"></i></span> <input type="PASSWORD"
											name="Password" class="form-control" id="PASSWORD"
											value="<%=dto.getPassword()%>" required>
										<div class="invalid-feedback">Please enter your
											password!</div>
									</div>
								</div>

								<div class="col-6 mt-3">
									<label for="yourUsername" class="form-label">Address</label>
									<div class="input-group has-validation">
										<span class="input-group-text" id="Address"><i
											class="bi bi-geo-alt-fill icon-color"></i></span> <input type="text"
											name="Address" class="form-control" id="local_address"
											value="<%=dto.getAddress()%>" required>
										<div class="invalid-feedback">Please choose a Address.</div>
									</div>
								</div>

								<div class="col-md-12 mt-2">
									<label for="Activationdate" class="form-label">Status</label>
									<div class="form-floating control mb-3">
										<select class="form-select" id="status" name="Status"
											aria-label="working post">
											<option value="Active"
												<%if (dto.getStatus().equalsIgnoreCase("Active")) {out.print("selected='selected'");}%>>Active</option>

											<option value="Block"
												<%if (dto.getStatus().equalsIgnoreCase("Block")) {out.print("selected='selected'");}%>>Block</option>

										</select> <label for="Status">Status</label>
										<div class="invalid-feedback">Please, select Employee
											Status!</div>
									</div>
								</div>

								<label for="Activationdate" class="form-label">Features</label>
								<div class="col-md-12"
									style="display: grid; grid-template-columns: repeat(1, 1fr); grid-gap: 20px;">

									<div class="form-control" style="padding-top: 3%;">

										<%
										String[] icon = { "ri-restaurant-line", "ri-hotel-line", "fa-solid fa-pizza-slice",
												"fas fa-cocktail", "fas fa-bread-slice", "fa fa-handshake", "fa-solid fa-microscope", 
												"fa-solid fa-sack-dollar", "fa-solid fa-hand-holding-dollar", "fa-solid fa-users", "fa-solid fa-indian-rupee-sign", "fa-solid fa-chalkboard", "fa-solid fa-sack-dollar", "fa-solid fa-hand-holding-dollar", "fa-solid fa-award" , "fa-solid fa-user", "fa-solid fa-pencil-square", "fa-solid fa-trash" };
										String[] name = { "Order", "Room", "Ingredients", "Liquor", "Reciepe Relation", "Dealer", "Customer", "Spend", "Income", "Employee" , "Payment Collection",  "Master" , "Spend Head", "Income Head", "Bank", "User" , "Update",  "Delete"}; // Updated the ID for Home
										String[][] sub_name = { { "Order Module ", "Pending Report", "Complete Report", "Kot Report" }, { "Room Module", "Room Booked Report", "Room Bill report" } ,  {"Ingredients Module", "Ingredients In Report", "Ingredients Out Report"} , {"Liquor Module", "Liquor Report"}, {"Reciepe Relation"}, {"Dealer Report", "Dealer Module"}, {"Customer Module", "Customer Report"}, { "Spend Module", "Spend Report"}, {"Income Module", "Income Report"}, {"Employee Module", "Employee Report"}, {"Payment Module"}, {"Master Module"}, {"Spend Head Module"}, {"Income Head Module"}, {"Bank Module"}, {"User Module"}, {"Update Module"} ,{"Delete Module"} };
										String[][] attr_name = { { "Order_module", "Pending_bills_report", "Complete_bills_report", "Kot_bills_report" },
												{"Room_module", "Room_booked_report", "Room_bill_report"} ,  {"Ingredients_module", "Ingredients_in_Report", "Ingredients_out_Report"} , {"Liquor_module", "Liquor_report"}, {"Reciepe_relation_module"}, {"Dealer_module", "Dealer_report"}, {"Customer_module", "Customer_report"}, { "Spend_module", "Spend_report"}, {"Income_module", "Income_report"},
												{"Employee_module", "Employee_report"}, {"Payment_module"}, {"Master_module"}, {"Spend_head_module"}, {"Income_head_module"}, {"Bank_module"}, {"User_module"}, {"Update_module"} ,{"Delete_module"}};
										String[][] attr_id = { { "order_module", "pending_bills_report", "complete_bills_report", "kot_bills_report" }, {"room_module", "room_booked_report", "room_bill_report"},  {"ingredients_module", "ingredients_in_Report", "ingredients_out_Report"} , {"liquor_module", "liquor_report"}, {"reciepe_relation_module"}, {"dealer_module", "dealer_report"}, {"customer_module", "customer_report"}, { "spend_module", "spend_report"}, {"income_module", "income_report"},
												{"employee_module", "employee_report"}, {"payment_module"}, {"master_module"}, {"spend_head_module"}, {"income_head_module"}, {"bank_module"}, {"user_module"}, {"update_module"} ,{"delete_module"} };
										String[][] ul_get = { { dto.getOrder_module(), dto.getPending_bills_report(), dto.getComplete_bills_report(), dto.getKot_bills_report()}, {dto.getRoom_module(), dto.getRoom_booked_report(), dto.getRoom_bill_report()}, {dto.getIngredients_module(), dto.getIngredients_in_Report(), dto.getIngredients_out_Report()} , {dto.getLiquor_module(), dto.getLiquor_report()},{dto.getReciepe_relation_module()}, 
												{dto.getDealer_module(), dto.getDealer_report()}, {dto.getCustomer_module(), dto.getCustomer_report()},{dto.getSpend_module(), dto.getSpend_report()}, {dto.getIncome_module(),dto.getIncome_report()},{dto.getEmployee_module(),dto.getEmployee_report()},{dto.getPayment_module()},
												{dto.getMaster_module()},{dto.getSpend_head_module()}, {dto.getIncome_head_module()},{dto.getBank_module()},{dto.getUser_module()},{dto.getUpdate_module()},{dto.getDelete_module()} };

										for (int p = 0; p < name.length; p++) {
										%>
										<div class="form-check">
											<h6 style="font-size: 13px;">
												<i class="<%=icon[p]%>"></i>&nbsp;&nbsp;&nbsp;<b><%=name[p]%></b>
											</h6>

											<div class="row mx-5">
												<%
												for (int i = 0; i < sub_name[p].length; i++) {
												%>
												<div class="form-check col-md-4 div-width">
													<input class="form-check-input" type="checkbox"
														id="<%=attr_id[p][i]%>" name="<%=attr_name[p][i]%>"
														value="Yes" style="margin-top: 3px;"
														<%if ("Yes".equalsIgnoreCase(ul_get[p][i])) {out.print("checked='checked'");}%>>
													<label class="form-check-label" for="<%=attr_id[p][i]%>"><%=sub_name[p][i]%></label>
												</div>
												<%
												}
												%>
												
											</div>
											<hr>
										</div>
										<%
										}
										%>

									</div>
								</div>
								
								
								

								<br> <br>
								<div class="row mt-3">
									<div class="col-sm-12">
										<button type="submit" class="submit-btn"
											style="margin-left: 40%;">
											<b>Submit </b>
										</button>
									</div>
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