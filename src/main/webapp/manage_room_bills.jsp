
<%@page import="com.service.*"%>
<%@page import="com.dto.*"%><%@include file="include/head.jsp"%>
</head>
<body>


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<main id="main" class="main">

		<%@include file="include/breadcrumbs.jsp"%>

		<div class="pagetitle text-center">
			<h1 id="page_title">Room Bill Report</h1>
		</div>
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
	String first_date = LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy") == null ? ""
			: LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
	String second_date = LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy") == null ? ""
			: LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
	String cust_name = request.getParameter("Name") == null ? "" : request.getParameter("Name");
	%>
		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

	<div  class="row " id="table_search">
							<form autocomplete="off" class="row g-3 d-flex justify-content-center">
								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="date1"
											name="D1" value="<%=d1%>"> <label for="floatingName">From
											Date</label>
									</div>
								</div>
								
								<input type="hidden" id="d1_time" value="<%=first_date%>">
								<input type="hidden" id="d2_time" value="<%=second_date%>">

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="datetime-local" class="form-control " id="date2"
											name="D2" value="<%=d2%>"> <label for="floatingPhone">To
											Date</label>
									</div>
								</div>
								
								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<select class="form-control" id="periods" name="periods">
											<option value="0">- Select -</option>
											<%
											int[] num = { 1, 3, 5 , 6};
											String[] word = { "Today", "Weekly", "Monthly", "Yearly" };

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
								<div id="food_div" class="col-lg-2 col-md-3 col-6">
									<div class="control form-floating ">
										<input type="text" class="form-control" list="browsers"
											value="<%=cust_name == "" || cust_name == null ? "" : cust_name%>"
											placeholder="Search item by name" id="name_search"
											name="Name" style="padding: 7px; text-align: left;"
											autocomplete="off" />
										<datalist id="browsers">
												<%
												RoomBookingService service = new RoomBookingService();
												ArrayList<RoomBookingDto> list = service.getAllCustomer(config, request);
												for (RoomBookingDto dto : list){
											%>
											<option value="<%=dto.getCust_name()%>" />
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

						<table id="example" class="table hover table-responsive nowrap" style="min-width: 2500px !important;">
							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 1%;">S.No.</th>
									<th style="width: 1%;">Bill</th>
									<th style="width: 1%;">Food</th>
									<th style="width: 2%;">Room No.</th>
									<th style="width: 4%">Room Type</th>

									<th style="width: 8%">Name</th>
									<th style="width: 4%;">Mobile No.</th>
									<th style="width: 8%;">Address</th>

									<th style="width: 3%;">Check In</th>
									<th style="width: 3%;">Check Out</th>

									<th style="width: 2%;">Room Rent</th>
									<th style="width: 1%;">Extra Bad</th>
									<th style="width: 2%;">Bill Amt</th>
									<th style="width: 2%;">Advance Amt</th>

									<th style="width: 2%;">Bill Paid Amt</th>

									<th style="width: 5%;">Company Name</th>
									<th style="width: 3%;">Source</th>
									<th style="width: 3%;">Destination</th>
									<th style="width: 1%;">Edit</th>
									<th style="width: 1%;">Del.</th>
								</tr>

							</thead>
							<tbody class="text-center" id="tbody">
								<%
								RoomBookingService ser = new RoomBookingService();
								ArrayList<RoomBookingDto> list1 = ser.getAllRoomBills(d1, d2,cust_name,config, request);
								for (RoomBookingDto dto : list1) {
								%>
								<tr>
									<td><%=list1.indexOf(dto) + 1%></td>
									<td><a href="print_room_bill.jsp?bill_id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>
									<td><a href="print_food_bill.jsp?bill_id=<%=dto.getId()%>"><i
											class="bi bi-printer"></i></a></td>
									<td class="main-color"><%=dto.getRoom_no()%></td>
									<td class="main-color"><%=dto.getRoom_type()%></td>
									<td class="main-color"><%=dto.getCust_name()%></td>
									<td><%=dto.getCust_mobile()%></td>
									<td><%=dto.getCust_address()%></td>
									<td><%=LogFileService.formatDateTime(dto.getCheck_in_time())%></td>
									<td><%=LogFileService.formatDateTime(dto.getCheck_out_time())%></td>


									<td><%=dto.getRoom_rent()%></td>
									<td><%=dto.getExtra_bed()%></td>
									<td><%=Math.round(dto.getNet_amt())%></td>
									<td><%=Math.round(dto.getAdvanced_paid_amt())%></td>

									<td><%=Math.round(dto.getBill_paid_amt())%></td>


									<td><%=dto.getCompany_name()%></td>
									<td><%=dto.getSource()%></td>
									<td><%=dto.getDestination()%></td>


									

									<td><a href="room_billing.jsp?bill_id=<%=dto.getId()%>"><i
											class="bi bi-pencil-square"></i></a></td>

									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxFolder/AjaxDelete.jsp?room_bill_id=<%=dto.getId()%>&room_id=<%=dto.getRoom_id_fk()%>">
											<i class="bi bi-trash main-color"></i>
									</a></td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
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