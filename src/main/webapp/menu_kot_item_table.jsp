
<%@include file="include/head.jsp"%>

<style>
.table_submit {
	font-size: smaller;
	color: #fff;
	background: #ff3f00;
	border: 0;
	padding: 8px 10px;
}

.free-table {
	min-width: 40px;
	font-size: smaller;
	color: #000;
	background: #e6f3e3;
	/* background: linear-gradient(to bottom, #2fff37, #328737); */
	border: 2px solid #388E3C;
	padding: 8px 10px;
	position: relative;
	overflow: hidden;
	transition: background-color 0.3s;
}

.free-table:hover, .free-table.active {
	color: #fff;
	background: linear-gradient(to bottom, #2fff37, #328737);
	/* background: linear-gradient(to bottom, #388E3C, #1B5E20); */
}

.book-table {
	min-width: 40px;
	font-size: smaller;
	color: #000;
	border: 2px solid #C62828;
	background: #f5d3d3;
	/* background: linear-gradient(to bottom, #C62828, #B71C1C); */
	padding: 8px 10px;
	position: relative;
	overflow: hidden;
	transition: background-color 0.3s;
}

.book-table:hover, .book-table.active {
	color: #fff;
	background: linear-gradient(to bottom, #ff0000, #000);
}

.custom-table {
	min-width: 40px;
	font-size: smaller;
	color: #000;
	background: linear-gradient(to bottom, #FF9800, #FF5722);
	border: 2px solid #E64A19;
	padding: 8px 10px;
	position: relative;
	overflow: hidden;
	transition: background-color 0.3s;
}

.custom-table:hover {
	color: #fff;
	background: linear-gradient(to bottom, #E64A19, #BF360C);
}

.shine-table:before {
	content: "";
	position: absolute;
	top: -50%;
	left: -50%;
	width: 200%;
	height: 200%;
	background: linear-gradient(to top right, rgba(255, 255, 255, 0.3),
		rgba(255, 255, 255, 0));
	transform: rotate(30deg);
	z-index: 1;
	opacity: 0;
	transition: opacity 0.8s ease-in-out;
}

.shine-table:hover {
	background-color: rgba(0, 0, 0, 0.6);
}

.shine-table:hover:before {
	opacity: 1;
}
</style>

<!-- <style>
  .menu-table .table_submit {
    margin-right: 10px;
  } 
</style> -->

</head>


<body>

	<%
	menu_table = 2;
	%>
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<main id="main" class="main" style="margin-left: 0px;">

		<%
		String name = request.getParameter("name") == null ? "" : request.getParameter("name");
		%>

		<%@include file="include/breadcrumbs.jsp"%>


		<section class="section dashboard">
			<div class="row justify-content-center"
				style="display: flex; align-items: stretch; justify-content: space-around;">

				<div class="col-lg-3">
					<div class="card" style="background-color: black;">
						<div class="card-body">
							<div id="all-table"
								class="d-flex flex-wrap justify-content-center menu-table-wrapper mt-2">
								<%
								TableService tb_ser = new TableService();
								ArrayList<TableDto> list1 = tb_ser.getTableInfo(config, request);
								for (TableDto dto : list1) {
									String button_class = dto.getStatus().equalsIgnoreCase("Active") ? "book-table" : "free-table";
								%>

								<div class="col-lg-3 col-md-2 col-sm-2 col-4">
									<div class="text-center pt-2">
										<button class="<%=button_class%> shine-table"
											id="table-btn-<%=dto.getId()%>"
											onclick="openTable(<%=dto.getId()%>);">
											<%=dto.getName()%>
										</button>
									</div>
								</div>

								<%
								if (list1.indexOf(dto) == 0) {
								%>
								<script>
							        window.onload = function() {
							        	openTable(<%=dto.getId()%>);
							        };
							    </script>
								<%
								}
								%>

								<%
								}
								%>




							</div>
						</div>
					</div>




				</div>

				<div class="col-lg-9">

					<div class="card" style="border-radius: 25px; min-height: 532px;">


						<div class="card-body">



							<br />

							<div class="d-flex" style="margin-left: 10%;">
								<div class="pagetitle col-lg-8 text-center">
									<h1 id="get_name">
										<span id="dv_tb_name"></span> - <label
											style="padding-right: 10px; padding-top: 7px;"
											for="table-switch">Table</label>
									</h1>
									<input type="hidden" id="table_id" value="0" /> <input
										type="hidden" id="table_name" />


								</div>




								<div class="col-md-3">
									<div class=" control form-floating">
										<input type="text" class="form-control"
											list="manager_datalist" autocomplete="off" id="manager_name"
											onchange="UpdateTableManager();" />
										<datalist id="manager_datalist">
											<option value="Admin">


												<%
												EmployeeService ser_emp = new EmployeeService();

												ArrayList<EmployeeDto> list_emp = ser_emp.getActiveEmployeeName(config, request);

												for (EmployeeDto emp_dto : list_emp) {
												%>
											
											<option value="<%=emp_dto.getName()%>">
												<%
												}
												%>
										</datalist>
										<label for="manager_name">Manager</label>
									</div>
								</div>

								<script>
									    var selectBox = document.getElementById("manager_name");
									
									    // Add click event listener to the select box
									    selectBox.addEventListener("click", function() {
									        this.select(); // Execute this.select() when clicked
									        
									        // Perform other operations here
									        // ...
									    });
									</script>


							</div>

							<br />



							<!-- Floating Labels Form -->
							<form class="row g-3 needs-validation" novalidate>

								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>

												<th>Item code</th>
												<th>Item price</th>
												<th>Item Quantity</th>
												<th>Add</th>

											</tr>
										</thead>
										<tbody class="text-center" id="tbody">
											<tr>
												<td>
													<div class="col-md-12 refresh-container mb-3"
														data-add="add_menu_item.jsp" data-list="list_menu_item">
														<div class="control form-floating refresh-input">
															<input type="text" class="form-control" id="item_code"
																placeholder="Item Code"> <input type="hidden"
																id="item_id"> <label for="item_code">Select
																Item Code</label>
															<div class="invalid-feedback">Please, Select Item
																Code!</div>
														</div>

													</div>
												</td>


												<td>
													<div class="control form-floating">
														<input type="text" class="form-control" id="item_rate"
															placeholder="Item Rate" />  <input type="hidden"
																id="item_name"><label for="item_rate">Item
															Rate</label>
													</div>
												</td>
												<td>
													<div class="control form-floating">
														<input type="text" class="form-control" id="item_qty"
															placeholder="Item Quantity" onclick="this.select();"
															value="1" /> <label for="item_rate">Item
															Quantity</label>


													</div>
												</td>
												<td class="text-center">
													<div class="control col-12">

														<input type="button" id="ins_btn" onblur="autoFocus();"
															class="form-control add-icon">
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>

								<hr>

								<div class="table-responsive">
									<table class="table">
										<thead class="text-center" id="thead">
											<tr>
												<th>Item Code</th>
												<th>Item Price</th>
												<th>Item Quantity</th>
												<th>Total Price</th>
												<th>Delete</th>

											</tr>
										</thead>
										<tbody id="tableScroll" class="text-center">

										</tbody>

										<tfoot id="tfoot">
											<tr>
												<td></td>
												<td></td>
												<td><span id="total_tab_qty">Total</span></td>

												<td><span id="total_tab_price"></span></td>
												<td></td>
											</tr>
										</tfoot>
									</table>
								</div>
								<div id="result_item_info"></div>



								<input type="hidden" id="kot_id" value="0"> <input
									type="hidden" id="without_gst_amount" value="0"> <input
									type="hidden" id="bill_date" value="<%=current_date%>">
								<input type="hidden" id="session_year" value="2023-24">
								<input type="hidden" id="user_id_fk" value="<%=user_id%>">


							</form>
							<!-- End floating Labels Form -->
							<br />
							<div class="text-center">
								<button onclick="SubmitKot();" id="bill-submit"
									class="submit-btn ">Submit</button>
							</div>

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
	
	<script src="js/menu_kot_item.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
	    $("#ins_btn").on("click", autoFocus);
	    $("#ins_btn").on("blur", function() {
	        // Optionally, you can add a delay to prevent immediate execution on blur
	        setTimeout(autoFocus, 100);
	    });
	});
	</script>


</body>
</html>