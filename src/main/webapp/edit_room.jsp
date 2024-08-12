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
				<h1>Edit Room</h1>
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
							RoomService service = new RoomService();
							RoomDto dto = service.getRoomInfoById(id, config, request);
							%>

							<!-- Floating Labels Form -->
							<form action="RoomServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation" novalidate>



								<!-- Room No. Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="hidden" name="Id" value="<%=id%>"> <input
											type="hidden" name="User_id_fk" id="user_id_fk"
											value="<%=user_id%>"> <input type="text"
											class="form-control" id="name" name="Room_no"
											placeholder="Room No."
											onblur="checkNameAvail(this.value, <%=id%>, 'Room');"
											value="<%=dto.getRoom_no()%>" required> <label
											for="name">Room No.</label>
										<div class="invalid-feedback">Please, Enter Room No.!</div>
									</div>
								</div>
								<!-- Room No. Block End -->

								<!-- Room name Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="text" class="form-control" id="room_name"
											name="Room_name" placeholder="Room name"
											value="<%=dto.getRoom_name()%>" required> <label
											for="room_name">Room name</label>
										<div class="invalid-feedback">Please, Enter Room name!</div>
									</div>
								</div>
								<!-- Room name Block End -->

								<!-- rent Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" class="form-control" id="rent" min="0"
											step="0.01" value="<%=dto.getRent()%>" name="Rent"
											placeholder="Rent" required> <label for="rent">Rent
										</label>
										<div class="invalid-feedback">Please, Enter Rent !</div>
									</div>
								</div>
								<!-- Rent Block End -->

								<!-- Rent Double Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" class="form-control" id="rent_double"
											min="0" step="0.01" value="<%=dto.getRent_double()%>"
											name="Rent_double" placeholder="Rent Double" required>
										<label for="rent_double">Rent Double </label>
										<div class="invalid-feedback">Please, Enter Rent Double
											!</div>
									</div>
								</div>
								<!-- Rent Double Block End -->

								<!-- rent_three Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" class="form-control" id="rent_three"
											min="0" step="0.01" value="<%=dto.getRent_three()%>"
											name="Rent_three" placeholder="Rent Three" required>
										<label for="rent_three">Rent Three </label>
										<div class="invalid-feedback">Please, Enter Rent Three !</div>
									</div>
								</div>
								<!-- rent_three Block End -->

								<!-- rent_fourth Block Start -->
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="number" class="form-control" id="rent_fourth"
											min="0" step="0.01" value="<%=dto.getRent_fourth()%>"
											name="Rent_fourth" placeholder="Rent" required> <label
											for="rent_fourth">Rent Fourth </label>
										<div class="invalid-feedback">Please, Enter Rent Fourth
											!</div>
									</div>
								</div>
								<!-- rent_fourth Block End -->
								<div class="col-md-6"></div>
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