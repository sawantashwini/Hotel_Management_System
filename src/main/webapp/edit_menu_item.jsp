<%@include file="include/head.jsp"%>
</head>

<!--  body start-->
<body>

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>

	<!--  main start-->
	<main id="main" class="main">
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Menu Item</h1>
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
							MenuItemService service = new MenuItemService();
							MenuItemDto dto = service.getMenuItemInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form action="MenuItemServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>
								<!-- Item code Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="hidden" name="Id" value="<%=id%>"> <input
											type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="text"
											class="form-control" id="name" name="Item_code"
											placeholder="Item code"
											onblur="checkNameAvail(this.value,<%=id%>, 'Menu');"
											value="<%=dto.getItem_code()%>" required> <label
											for="name">Item code</label>
										<div class="invalid-feedback">Please, Enter Item code!</div>
									</div>
								</div>
								<!-- Item code Block End -->

								<input type="hidden" value="Menu" name="Items">

								<!-- Item name Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="item_name"
											name="Item_name" placeholder="Item name"
											value="<%=dto.getItem_name()%>" required> <label
											for="item_name">Item name</label>
										<div class="invalid-feedback">Please, Enter Item name!</div>
									</div>
								</div>
								<!-- Item name Block End -->

								<!-- price Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="item_price"
											min="0" step="0.01" name="Item_price"
											placeholder="Item price" value="<%=dto.getItem_price()%>"
											required> <label for="item_price">Item price</label>
										<div class="invalid-feedback">Please, Enter Item price!</div>
									</div>
								</div>

								<div class="col-md-6">

									<div class="form-floating control">
										<select class="form-select" id="status" name="Status"
											aria-label="working post">
											<option value="Active"
												<%=dto.getStatus().equals("Active") ? "selected" : ""%>>Active</option>
											<option value="Block"
												<%=dto.getStatus().equals("Block") ? "selected" : ""%>>Block</option>
										</select> <label for="Status">Status</label>
										<div class="invalid-feedback">Please, Select Bank
											Status!</div>
									</div>
								</div>

								<!-- Submit Button Start -->
								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>
								</div>
								<!-- Submit Button End -->

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
<!--  body End-->

</html>