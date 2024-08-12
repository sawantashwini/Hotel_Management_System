
<%@include file="include/head.jsp"%>
</head>
<body>
	<div id="ser"></div>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  main start-->
	<main id="main" class="main">

		<%@include file="include/breadcrumbs.jsp"%>



		<div class="pagetitle text-center">
		<%@include file="include/add_pages.jsp"%>
			<h1 id="page_title">Ingredients Out Report</h1>
		</div>
		
		

		<section class="section">
			<div class="row">
				<div class="card table-responsive">
					<div class="card-body">

						<div class="row " id="table_search">

							<form autocomplete="off" class="row g-3 d-flex justify-content-center">
								<%
								String d1 = request.getParameter("D1") == null ? "" : request.getParameter("D1");
								String d2 = request.getParameter("D2") == null ? "" : request.getParameter("D2");
								int i = Integer.parseInt(request.getParameter("periods") == null ? "0" : request.getParameter("periods"));

								if (i != 0) {
									String temp = LogFileService.getEndDates("yyyy-MM-dd", i);
									String dates[] = temp.split(",");
									d1 = dates[0];
									d2 = dates[1];
									System.out.println(temp);
								}
								String first_date = LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
								String second_date = LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
								%>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d1" name="D1"
											value="<%=d1%>"> <label for="d1">From Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d2" name="D2"
											value="<%=d2%>"> <label for="d2">To Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<select class="form-control" id="periods" name="periods">
											<option value="0">- Select -</option>
											<%
											int[] num = { 1, 3, 5, 6};
											String[] word = { "Today", "Weekly", "Monthly", "Yearly"};

											for (int u = 0; u < word.length; u++) {
												int nums = num[u];
												String words = word[u];
											%>
											<option value="<%=nums%>" <%=(i == nums) ? "selected" : ""%>><%=words%></option>
											<%
											}
											%>
										</select> <label for="floatingPhone">To Date</label>
									</div>
								</div>


								<div class="col-md-2 col-4">
									<div class="form-floating">
										<button type="submit" class="submit-btn">Submit</button>
									</div>
								</div>
							</form>
						</div>


						<br>

						<!--  Table start-->
						<table id="example-small" class="table hover nowrap">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="10%">S.no</th>
									<th style="text-align: center;" width="15%">Date</th>
									<th style="text-align: center;" width="">Remark</th>
									<th style="text-align: center;" width="15%">Print</th>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									<th style="text-align: center;" width="15%">Edit</th>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>
									<th style="text-align: center;" width="15%">Delete</th>
									<%
									}
									%>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								IngredientsSaleService ser = new IngredientsSaleService();
								ArrayList<IngredientsDto> list1 = ser.getIngredientsSaleBillInfo(d1, d2, config, request);
								for (IngredientsDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getRemark() == null ? "N/A" : dto.getRemark()%></td>
									<td><a
										href="print_ingredients_sale.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>
									<%
									if (user_head_dto.getUpdate_module().equals("Yes")) {
									%>
									
									<td><a
										href="edit_ingredients_sale.jsp?id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square main-color"></i></a></td>
									<%
									}
									if (user_head_dto.getDelete_module().equals("Yes")) {
									%>

									<%-- <td><a id="del"
											onclick="confirmDelete('<%=dto.getId()%>','Ingredients_Sale');"> <i
												class="bi bi-trash main-color"></i>
										</a></td> --%>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?id=<%=dto.getId()%>&type=Ingredients_Sale">
											<i class="bi bi-trash main-color"></i>
									</a></td>


									<%
									}
									%>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
						<!--  Table End-->
					</div>
				</div>
			</div>
		</section>

	</main>
	<!-- End #main -->



	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->

	

</body>

</html>