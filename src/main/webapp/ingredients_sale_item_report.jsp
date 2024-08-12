
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
			<h1 id="page_title">Ingredient Out Item Report</h1>
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

								String search_item = request.getParameter("Search_item") == null ? "" : request.getParameter("Search_item");
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

								<div class="col-lg-2 col-md-3 col-6">
									<div class="control form-floating ">
										<input type="text" class="form-control" list="browsers"
											value="<%=search_item == "" || search_item == null ? "" : search_item%>"
											placeholder="Search item by name" id="name_search"
											name="Search_item" style="padding: 7px; text-align: left;"
											autocomplete="off" />
										<datalist id="browsers">
											<%
											IngredientsSaleService ser = new IngredientsSaleService();
											ArrayList<IngredientsDto> i_list = ser.getItemName(config, request);
											for (IngredientsDto i_dto : i_list) {
											%>
											<option value="<%=i_dto.getItem_name()%>" />
											<%
											}
											%>
										</datalist>
										<label for="item_name">Item Name</label>
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
						<table id=examples-small class="table hover"
							style="min-width: 750px;">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="10%">S.No.</th>
									<th style="text-align: center;" width="10%">Date</th>
									<th style="text-align: center;" width="15%">Name</th>
									<th style="text-align: center;" width="10%">Quantity</th>
									<th style="text-align: center;" width="30%">Remark</th>
									<!-- <th style="text-align: center;" width="10%">Print</th> -->
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								ArrayList<IngredientsDto> list1 = ser.getIngredientsSaleBillItemInfo(d1, d2, search_item, config, request);
								for (IngredientsDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><%=LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getItem_name()%></td>
									<td><%=dto.getItem_quantity()%></td>
									<td><%=dto.getRemark() == null ? "N/A" : dto.getRemark()%></td>
									<%-- <td><a class="main-color"
										href="Print_purchase.jsp?id=<%=dto.getBill_id_fk()%>"><i
											class="bi bi-printer"></i></a></td> --%>
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


	<!-- Customize Script Start  -->
	<script>
	var page_title=document.getElementById('page_title').innerHTML;
	function printfunction(){
		return '<h1 style="text-align:center;margin-bottom:10px;">'+page_title+'</h1><br><h5 style="text-align:center;margin-bottom:2px;">From Date: <%=first_date%> To Date: <%=second_date%></h5>';
	}
	function printOtherfunction(){
		return 'From Date: <%=first_date%> To Date:<%=second_date%>';
	}
		function printTitle() {
			return page_title;
		}
	</script>


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->
	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->

</body>

</html>