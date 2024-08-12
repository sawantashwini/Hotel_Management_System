
<%@include file="include/head.jsp"%>

</head>


<body onload="myResultLoadEvent();">


	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<main id="main" class="main">

		<div class="pagetitle text-center">
			<h1 id="page_title">Manage Salary Transaction</h1>
		</div>

		<section class="section profile">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">
						<br>


						<%
						int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
						EmployeeService ser = new EmployeeService();

						EmployeeDto dto1 = ser.getEmployeeInfoById(id, config, request);
						%>


						<div class="tab-content pt-2 nowrap" >
							<table class="table info-table">
								<tr>
									<th style="width: 15%;">Name :</th>
									<td width="35%"><%=dto1.getName()%></td>
									<th width="10%">Mobile :</th>
									<td width="30%"><%=dto1.getMobile_no()%></td>
								</tr>

								<tr>
									<th>Salary / Month :</th>
									<td><%=dto1.getSalary_per_month()%></td>
									<th>Due :</th>
									<td id="due_account"></td>
								</tr>
							</table>

						</div>





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
						String second_date = LogFileService.changeFormate(d2, "yyyy-MM-dd", "dd-MM-yyyy");
						float tot_Credit = 0, tot_Debit = 0, Balance_Amt = 0;

						Balance_Amt = tot_Credit - tot_Debit;
						%>

						<div class="row " id="table_search">
							<form class="row g-3">
								<input type="hidden" name="id" value="<%=id%>">
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
									<div class="row pt-2" style="color: #ff0202; font-size: 20px;">
										<pre>Prev. Bal:<a id="account"></a> </pre>
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
						<table id="large_simple_table"
							class="table hover table-responsive nowrap">


							<thead class="text-center" id="thead">
								<tr>
									<th style="width: 5%; text-align: center;">S.No.</th>
									<th style="width: 10%; text-align: center;">Pay Date</th>
									<th style="width: 10%; text-align: center;">Payment Mode</th>
									<th style="width: 15%; text-align: center;">Debit Amount</th>
									<th style="width: 15%; text-align: center;">Credit Amount</th>
									<th style="width: 9%; text-align: center;">Balance</th>

									<th style="width: 15%; text-align: center;">Payment Way</th>
									<th style="width: 30%; text-align: center;">Online Remark</th>
									<th style="width: 15%; text-align: center;">Online Date</th>

									<th style="width: 5%; text-align: center;">Remark</th>
									<th style="width: 5%; text-align: center;">Edit</th>
									<th style="width: 5%; text-align: center;">Delete</th>

								</tr>
							</thead>
							<tbody class="text-center" id="tbody">


								<%
								EmployeeAccountService service = new EmployeeAccountService();
								ArrayList<EmployeeAccountDto> list = service.getEmployeeAccountInfo(d1, d2, id, config, request);

								for (EmployeeAccountDto dto : list) {

									if (Balance_Amt < 0) {
										Balance_Amt = (Balance_Amt - dto.getDebit_amount()) + dto.getCredit_amount();
									} else {
										Balance_Amt = (Balance_Amt - dto.getDebit_amount()) + dto.getCredit_amount();

									}
								%>


								<tr>
									<td><%=list.indexOf(dto) + 1%></td>
									<td><%=LogFileService.changeFormate(dto.getIn_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getPayment_mode()%></td>
									<td style="color: red; font-weight: 500;"><%=dto.getDebit_amount()%></td>
									<td style="color: red; font-weight: 500;"><%=dto.getCredit_amount()%></td>
									<td style="color: #ff0202;">
										<%
										if (list.size() == (list.indexOf(dto) + 1)) {
										%><span style="color: #000;">In Hand:-</span> <% } %> <%=format.format(Balance_Amt)%></td>


									<td><%=dto.getOnline_way()%></td>
									<td><%=dto.getOnline_remark()%></td>
									<td><%=LogFileService.changeFormate(dto.getOnline_date(), "yyyy-MM-dd", "dd-MM-yyyy")%></td>
									<td><%=dto.getRemark() == null ? "N/A" : dto.getRemark()%></td>
									<%-- <td>
										<%if(dto.getDebit_amount()>0){ %> <a
										href="view_employee_account_receipt.jsp?id=<%=dto.getId()%>&amountmode=Debit"><i
											class="bi bi-printer main-color"></i></a> <%}if(dto.getCredit_amount()>0){ %>
										<a
										href="view_employee_account_receipt.jsp?id=<%=dto.getId()%>&amountmode=Credit"><i
											class="bi bi-printer main-color"></i></a> <%} %>
									</td>	 --%>

									<td>
										<%
										if (dto.getDebit_amount() > 0) {
										%> <a
										href="edit_pay_amount_employee.jsp?id=<%=dto.getId()%>&amountmode=Debit"><i
											class="bi bi-pencil-square main-color"></i></a> <% } 
										if (dto.getCredit_amount() > 0) { %> <a
										href="edit_pay_amount_employee.jsp?id=<%=dto.getId()%>&amountmode=Credit"><i
											class="bi bi-pencil-square main-color"></i></a> <%} %>
									</td>
									<td><a
										onclick="return confirm('Are you sure? You want to delete')"
										href="AjaxEmployeeDelete.jsp?employee_transaction_id=<%=dto.getId()%>&id=<%=dto.getEmployee_id_fk()%>"><i
											class="bi bi-trash main-color member"></i> </a></td>
								</tr>


								<%
								tot_Debit = tot_Debit + dto.getDebit_amount();
								tot_Credit = tot_Credit + dto.getCredit_amount();
								}
								%>



							</tbody>
							<tfoot class="text-center" id="tfoot">
								<tr>
									<td></td>
									<td>Total</td>
									<td></td>
									<td id="deb" onchange="balance_amt();"><%=tot_Debit%></td>
									<td id="cred" onchange="balance_amt();"><%=tot_Credit%></td>
									<td><span style="color: #000;">In Hand:-</span><span
										id="due"><%=tot_Credit - tot_Debit%></span></td>
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
<!-- Customize Script Start  -->
	<!-- <script>
	var page_title=document.getElementById('page_title').innerHTML;
	function printfunction(){
		return '<h1 style="text-align:center;margin-bottom:10px;">'+page_title+'</h1>';
	}
	function printTitle(){
		return page_title;
	}
	</script> -->
	<!-- Customize Script End -->
	<script>
		function myResultLoadEvent() {
			let due = document.getElementById("due").innerHTML;
			document.getElementById("due_account").innerHTML = due;
			document.getElementById("account").innerHTML = due;
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