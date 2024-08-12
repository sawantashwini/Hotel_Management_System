
<%@include file="include/head.jsp"%>
</head>
<body onload="findItem();">

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
			<h1 id="page_title">Item Wise Report</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card table-responsive">
					<div class="card-body">

						<div class="row " id="table_search">

							<form autocomplete="off"
								class="row g-3 d-flex justify-content-center">
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

								String food_item = request.getParameter("Food_item") == null ? "" : request.getParameter("Food_item");
								String liquor_item = request.getParameter("Liquor_item") == null ? "" : request.getParameter("Liquor_item");
								String category = request.getParameter("Cg") == null ? "" : request.getParameter("Cg");
								%>
<input type="hidden" id="d1_time" value="<%=first_date%>">
								<input type="hidden" id="d2_time" value="<%=second_date%>">
								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="d1" name="D1"
											value="<%=d1%>"> <label for="d1">From Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="d2" name="D2"
											value="<%=d2%>"> <label for="d2">To Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<select class="form-control" id="periods" name="periods">
											<option value="0">- Select -</option>

											<%
											int[] num = {1, 3, 5, 6};
											String[] word = {"Today", "Weekly", "Monthly", "Yearly"};

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
									<div class="form-floating control">
										<select class="form-control" id="category" name="Cg" onchange="findItem();">
										
											<option value="food" <%if(category.equalsIgnoreCase("food")){ out.print("selected"); }%>>Food</option>
										
											<option value="liquor" <%if(category.equalsIgnoreCase("liquor")){out.print("selected"); }%>>Liquor</option>




										</select> <label for="floatingPhone">Category</label>
									</div>
								</div>
								<div id="food_div" class="col-lg-2 col-md-3 col-6" style="<%if(category.equalsIgnoreCase("liquor")){out.print("display: none"); }%>">
									<div class="control form-floating ">
										<input type="text" class="form-control" list="browsers"
											value="<%=food_item == "" || food_item == null ? "" : food_item%>"
											placeholder="Search item by name" id="food_search"
											name="Food_item" style="padding: 7px; text-align: left;"
											autocomplete="off" />
										<datalist id="browsers">
											<%
											MenuItemService ser_food = new MenuItemService();
											ArrayList<MenuItemDto> list_food = ser_food.getMenuItemName(config, request);
											for (MenuItemDto dto : list_food) {
											%>
											<option value="<%=dto.getItem_name()%>" />
											<%
											}
											%>
										</datalist>
										<label for="item_name">Item Name</label>
									</div>
								</div>
								<div id="liquor_div" class="col-lg-2 col-md-3 col-6" style="<%if(category.equalsIgnoreCase("food")){out.print("display: none"); }%>">
									<div class="control form-floating ">
										<input type="text" class="form-control" list="browser"
											value="<%=liquor_item == "" || liquor_item == null ? "" : liquor_item%>"
											placeholder="Search item by name" id="liquor_search"
											name="Liquor_item" style="padding: 7px; text-align: left;"
											autocomplete="off" />
										<datalist id="browser">
											<%
											MenuItemService ser_liquor = new MenuItemService();
											ArrayList<MenuItemDto> list_liquor = ser_liquor.getLiquorItemName(config, request);
											for (MenuItemDto dto : list_liquor) {
											%>
											<option value="<%=dto.getItem_name()%>" />
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
						<table id="examples-small" class="table hover"
							style="min-width: 750px;">
							<thead class="text-center" id="thead">
								<tr>
									<th style="text-align: center;" width="5%">S.No.</th>
									<th style="text-align: center;" width="10%">Item Code</th>
									<th style="text-align: center;" width="10%">Item Name</th>
									<th style="text-align: center;" width="10%">Item Qty</th>
									<th style="text-align: center;" width="10%">Item Price</th>
									
									<!-- <th style="text-align: center;" width="10%">Print</th> -->
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								OrderService ser_fo = new OrderService();
								ArrayList<OrderItemDto> list = ser_fo.geItemWiseInfo(d1, d2,food_item,liquor_item, config, request);
								float  total_amt = 0,total_qty = 0;
								for (OrderItemDto dto : list) {
								%>
								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td><%=dto.getItem_code()%></td>
									<td><%=dto.getItem_name()%></td>
									<td><%=dto.getItem_qty()%></td>
									<td><%=dto.getItem_rate()%></td>
									
								</tr>
								<%
								total_amt =total_amt+( dto.getItem_qty()*dto.getItem_rate());
								total_qty= total_qty+dto.getItem_qty();
								}
								%>
							</tbody>
							<tfoot  id="tfoot">
							<tr>
						            <td></td>
									<td></td>
									<td>Total</td>
									<td><%=total_qty%></td>
									<td><%=total_amt%></td>
									</tr>
							</tfoot>
						</table>
						<!--  Table End-->
					</div>
				</div>
			</div>
		</section>

	</main>
	<!-- End #main -->

	<script type="text/javascript">
		function findItem() {
			let category = document.getElementById("category").value;
//alert(category);
			if (category == "liquor") {
				
				document.getElementById("liquor_div").style.display = "block";
				
				document.getElementById("food_search").value = "";
				document.getElementById("food_div").style.display = "none";
				
			} 
			else if (category == "food") {
				
				document.getElementById("food_div").style.display = "block";
				
				document.getElementById("liquor_search").value = "";
				
				document.getElementById("liquor_div").style.display = "none";
				
			} 
		}
	</script>
	<!-- Customize Script End -->
	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->
	<!-- ======= datatable_script ======= -->
	<%@include file="include/datatable_script_footer.jsp"%>
	<!-- End datatable_script -->

</body>

</html>