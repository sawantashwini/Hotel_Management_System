
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

	<main id="main" class="main">
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Msg</h1>
			</div>
		</div>



		<section class="section dashboard">
			<div class="row justify-content-center">

				<div class="col-lg-8">

					<div class="card">
						<div class="card-body">
							<br />


							<%
							int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							MsgService service = new MsgService();
							MsgDto dto = service.getMsgInfoById(id, config, request);
							%>


							<!-- Floating Labels Form -->
							<form autocomplete="off" action="MsgServlet" method="post"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>
								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>">
								<div class="col-md-6">
									<div class="control form-floating">
										<input type="hidden" name="Id" value="<%=id%>"> <input
											type="text" class="form-control" id="name" name="Name"
											value="<%=dto.getName()%>" placeholder="name" required>
										<label for="name">Msg</label>
										<div class="invalid-feedback">Please, Enter Msg!</div>
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
	<br />
	<br />


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>