<%@include file="include/head.jsp"%>
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
			<h1 id="page_title">Cash Payment Report</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">



					<%
					NumberFormat format = new DecimalFormat("##.##");

					String type = request.getParameter("Type") == null ? "" : request.getParameter("Type");
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

					CashPaymentService service = new CashPaymentService();

					ArrayList<CashPaymentDto> old_list = null;

					String get_last_date = "";

					if (!"".equals(d1) && !"".equals(d2)) {
						get_last_date = LogFileService.previousDateString(d1);
					}

					old_list = service.getCashPaymentInfoByDate("2023-01-01", get_last_date, type, request, config);

					float tot_Credit = 0, tot_Debit = 0, Balance_Amt = 0;

					if (!"".equals(d1) && !"".equals(d2)) {
						for (CashPaymentDto acc_old_dto : old_list) {
							tot_Credit = tot_Credit + acc_old_dto.getCredit();
							tot_Debit = tot_Debit + acc_old_dto.getDebit();
						}
					}

					Balance_Amt = tot_Credit - tot_Debit;
					%>

					<%

					%>
					<div class="card-body table-responsive">

						<div class="row " id="table_search">
							<form class="row g-3 d-flex justify-content-center">

								<div class="col-md-2 col-4"></div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d1" name="D1"
											value="<%=d1 == null ? "" : d1%>"> <label
											for="floatingName">From Date</label>
									</div>
								</div>

								<div class="col-md-2 col-4">
									<div class="form-floating control">
										<input type="date" class="form-control " id="d2" name="D2"
											value="<%=d2 == null ? "" : d2%>"> <label
											for="floatingPhone">To Date</label>
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

								<div class="col-md-2">
									<label class="cow" id="pre_bal" style="color: #ff0202;"> Prev. Bal: <%=format.format(Balance_Amt)%></label>
								</div>
								<div class="col-md-2 col-4">
									<div class="form-floating">
										<button type="submit" class="submit-btn">Submit</button>
									</div>
								</div>
							</form>
						</div>
						<br>



						<table id="example-small" class="table hover table-responsive nowrap" style="width: 100%;">


							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 5%; text-align: center;">S.N0.</th>
									<th style="width: 10%; text-align: center;">Credit</th>
									<th style="width: 10%; text-align: center;">Debit</th>
									
									<th style="width: 20%; text-align: center;">Balance</th>
									<th style="width: 15%; text-align: center;">Pay Date</th>
									<th style="width: 20%; text-align: center;">Remark</th>
									<th style="width: 15%; text-align: center;">Type</th>
									<th style="width: 15%; text-align: center;">User Name</th>
									
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								ArrayList<CashPaymentDto> list = service.getCashPaymentInfoByDate(d1, d2, type, request, config);
								float total_debit=0,total_credit=0;
								for (CashPaymentDto dto : list) {

									if (Balance_Amt < 0) {
										Balance_Amt = (Balance_Amt - dto.getDebit()) + dto.getCredit();
									} else {
										Balance_Amt = (Balance_Amt - dto.getDebit()) + dto.getCredit();

									}
								%>
								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td><%=dto.getCredit()%></td>
									<td><%=dto.getDebit()%></td>
									<td style="color: #ff0202;"> <%if(list.size()==(list.indexOf(dto)+1)){%><span style="color: #000;"> Cash In Hand :- </span><%}%> <%=format.format(Balance_Amt)%></td>
									<td><%=dto.getIn_date() == "" ? "-": LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>

									<td><%=dto.getRemark() == null ? "-": dto.getRemark()%></td>
									
									<td><%=dto.getType() == null ? "-": dto.getType()%></td>
									<td><%=dto.getUser_name() == null ? "-": dto.getUser_name()%></td>

								</tr>
								<%
								total_credit=total_credit+dto.getCredit();
								total_debit=total_debit+dto.getDebit();
								}
								%>
					
							</tbody>
							
							<tfoot id="tfoot">
								<tr>
									<td></td>
									<td><%=total_credit %></td>
									<td><%=total_debit %></td>
									<td><%=total_credit-total_debit %></td>
									<td></td>
									<td></td>
									<td></td>
									
								</tr>
							
							</tfoot>
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