
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

		<div class="pagetitle text-center">
			<h1 id="page_title">Online Payment Report</h1>
		</div>

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

						<%
						NumberFormat format = new DecimalFormat("##.##");

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
						String second_date = LogFileService.changeFormate(d1, "yyyy-MM-dd", "dd-MM-yyyy");
						
						int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));

						int bank_id_fk = Integer.parseInt(request.getParameter("bank_id_fk") == null ? "0" : request.getParameter("bank_id_fk"));
						if(id>0){
							bank_id_fk = id;
						}
						String name = request.getParameter("Name") == null ? "" : request.getParameter("Name");
						OnlinePaymentService service = new OnlinePaymentService();

						ArrayList<OnlinePaymentDto> old_list = null;

						String get_last_date = "";

						if (!"".equals(d1) && !"".equals(d2)) {
							get_last_date = LogFileService.previousDateString(d1);
						}

						old_list = service.getOnlinePaymentInfo("2023-01-01", get_last_date,bank_id_fk, config, request);

						float tot_Credit = 0, tot_Debit = 0, Balance_Amt = 0;

						if (!"".equals(d1) && !"".equals(d2)) {
							for (OnlinePaymentDto acc_old_dto : old_list) {
								tot_Credit = tot_Credit + acc_old_dto.getCredit();
								tot_Debit = tot_Debit + acc_old_dto.getDebit();
							}
						}

						Balance_Amt = tot_Credit - tot_Debit;
						%>

						<%

						%>

						<div class="row " id="table_search">
							<form class="row g-3">

								<div class="col-md-1"></div>

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
										<input type="hidden" class="form-control " id="name" name="Name"
											value="<%=name%>">	
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
								
								<% if(id==0){%>
								<div class="col-md-3 refresh-container refresh-block-none mb-3"
										data-list="list_bank">
										<div class="control form-floating refresh-input">
											<input type="text" class="form-control" id="bank_name"
												placeholder="Select Bank">
											<input type="hidden" id="bank_id_fk" name="Bank_id_fk"
												value="<%=bank_id_fk%>"> <label
												for="bank_name">Select Bank</label>
											<div class="invalid-feedback">Please, Select Bank!</div>
										</div>
									</div>
								
								<%} %>
								
								

								<div class="col-md-1">
									<label class="row" style="color: #ff0202;">Prev. Bal: <b id="total"></b></label>
								</div>
								<div class="col-md-2 col-4">
									<div class="form-floating">
										<button type="submit" class="submit-btn">Submit</button>
									</div>
								</div>


							</form>
						</div>

						<br>

						<table id="example" class="table hover table-responsive nowrap"
							>


							<thead class="text-center" id="thead">

								<tr>
									<th style="width: 5%; text-align: center;">S.N0.</th>
									<th style="width: 8%; text-align: center;">Credit</th>
									<th style="width: 8%; text-align: center;">Debit</th>

									<th style="width: 9%; text-align: center;">Balance</th>
									<th style="width: 8%; text-align: center;">Pay Date</th>
									<th style="width: 14%; text-align: center;">Remark</th>
									<th style="width: 8%; text-align: center;">Type</th>
									<th style="width: 8%; text-align: center;">Online Date</th>

									<th style="width: 5%; text-align: center;">Online Way</th>
									<th style="width: 8%; text-align: center;">Bank Name</th>
									<th style="width: 8%; text-align: center;">Acc No</th>
									<th style="width: 8%; text-align: center;">Branch</th>
									<th style="width: 8%; text-align: center;">IFSC</th>
								</tr>
							</thead>
							<tbody class="text-center" id="tbody">
								<%
								OnlinePaymentService ser = new OnlinePaymentService();
								ArrayList<OnlinePaymentDto> list = ser.getOnlinePaymentInfo(d1,d2,bank_id_fk,config, request);
								float total_debit=0,total_credit=0;
								for (OnlinePaymentDto dto : list) {
									
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
									<td style="color: #ff0202;">
										<%if(list.size()==(list.indexOf(dto)+1)){%><span
										style="color: #000;">Online Amount In Hand:-</span>
										<%}%> <%=format.format(Balance_Amt)%></td>

									<td><%=dto.getIn_date() == "" ? "-": LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getRemark() == null ? "-": dto.getRemark()%></td>
									<td><%=dto.getType() == null ? "-": dto.getType()%></td>
									<td><%=dto.getOnline_date() == "" ? "-": LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getOnline_way() == null ? "-": dto.getOnline_way()%></td>
									<td><%=dto.getBank_name() == null ? "-": dto.getBank_name()%></td>
									<td><%=dto.getAccount_no() == null ? "-": dto.getAccount_no()%></td>
									<td><%=dto.getBranch() == null ? "-": dto.getBranch()%></td>
									<td><%=dto.getIfsc() == null ? "-": dto.getIfsc()%></td>




								</tr>
								<%
								total_credit=total_credit+dto.getCredit();
								total_debit=total_debit+dto.getDebit();
								}
								%>


							</tbody>
							<tfoot id="tfoot">
								<tr>
									<td>Total</td>
									<td id="cred" onchange="balance_amt();"><%=total_credit %></td>
									<td id="deb" onchange="balance_amt();"><%=total_debit %></td>
									<td><%=total_credit-total_debit %></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
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
	<div id="info"></div>
	
	<script type="text/javascript">
	function balance_amt(){
		
		var cred = document.getElementById("cred").value;
		var deb = document.getElementById("deb").value;
		var total = cred + deb;
		document.getElementById("total").write = total;
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