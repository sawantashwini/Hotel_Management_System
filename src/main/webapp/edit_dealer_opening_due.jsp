	<!-- ======= Head ======= -->
	<%@include file="include/head.jsp"%>
	<!-- ======= Head end======= -->
	
	<style type="text/css">
	.table-borderless th, .table-borderless td, .table-borderless tbody, .table-borderless tr ,.table-borderless tbody,.table{
	border: none;
	}
	
	.table-color th{
	color:red;
	}
	tr{
	text-align: center;
	}
	</style>

</head>


<body>
<div id="ser"></div>
	

	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->
	


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

 


	<main id="main" class="main">
	




		<div class="pagetitle text-center">
			<h1>Edit Opening Due</h1>
		</div>

		<section class="section">
			<div class="row justify-content-center">
			
			
			
						
				<div class="card ">
					<div class="card-body table-responsive">
					
						  <%
					NumberFormat format = new DecimalFormat("##.##");


					/* String d1 = request.getParameter("D1") == null ? "" : request.getParameter("D1");
					String d2 = request.getParameter("D2") == null ? "" : request.getParameter("D2");

					DealerService service = new DealerService();

					ArrayList<DealerDto> old_list = null;

					String get_last_date = "";

					if (!"".equals(d1) && !"".equals(d2)) {
						get_last_date = LogFileService.previousDateString(d1);
					}

					old_list = service.getDealerAccountInfoByDate(id,"2023-01-01", get_last_date, request, config);

					float tot_Credit = 0, tot_Debit = 0, Balance_Amt = 0;

					if (!"".equals(d1) && !"".equals(d2)) {
						for (DealerDto acc_old_dto : old_list) {
							tot_Credit = tot_Credit + acc_old_dto.getCredit_amt();
							tot_Debit = tot_Debit + acc_old_dto.getDebit_amt();
						}
					} */
					float tot_Credit = 0, tot_Debit = 0, Balance_Amt = 0;

					Balance_Amt = tot_Debit - tot_Credit;
					%>
							<br>

					<%
							int del_id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
							DealerService service = new DealerService();
							DealerDto dto = service.getDealerInfoById(del_id , config, request);
							%>
						<div class="table-responsive col-lg-12 col-md-12 text-nowrap d-flex justify-content-center mt-3" style="border: 1px solid #f02c03;">
                     <table class="table table-borderless mt-4 table-color" style="width: 1000px">
                    <tr>
                    	<th>Name :</th>
                    	
                    	<td><%=dto.getName()%></td>
                    	<th>Mobile :</th>
                    	
                    	<td><%=dto.getMobile_no()%></td>
                    	<th>Address :</th>
                    	
                    	<td><%=dto.getAddress()%></td>
                    </tr>
                    
                    <tr>
                    	<th>GST no :</th>
                    	
                    	<td><%=dto.getGst_no()%></td>
                    	<th>Due :</th>
                    	
                    	<td><%=dto.getOld_due()%></td>
                    	
                    </tr>
                    </table>
                    </div>

						
						<br>

						<!-- Floating Labels Form -->
							<form action="DealerServlet" method="post" autocomplete="off"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>
								
								<div class="col-sm-4">
									<div class="control form-floating">
										<input type="number" class="form-control" id="opening_due"
											name="Opening_due" placeholder="Opening_due" value="<%=dto.getOpening_due() %>" required step=0.01 min="0"> <label
											for="opening_due">Opening due</label>
										<div class="invalid-feedback">Please, Enter Opening due !</div>
									</div>
								</div>
								
								<input type="hidden" name="User_id_fk" id="user_id_fk" value="<%=user_id%>"> 	
								<input type="hidden" name="Id" id="id" value="<%=del_id %>">
								<input type="hidden" id="c_y_session" name="C_y_session" value="2023-2024"> 
								<input type="hidden" id="flag" name="Flag" value="Opening Due"> 
								
								
								<div class="text-center">
									<button type="submit" class="submit-btn">Submit</button>

								</div>

							</form>
							<!-- End floating Labels Form -->
					</div>
				</div>
			</div>
		</section>

	</main>
	<!-- End #main -->

	


	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->



</body>

</html>