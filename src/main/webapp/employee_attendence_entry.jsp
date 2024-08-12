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


		<!-- ======= Breadcrumbs ======= -->
		<%@include file="include/breadcrumbs.jsp"%>
		<!--  Breadcrumbs End-->
		<script>
			function checkedAll() {
				var f_list = document.getElementsByClassName("class_full_day");
				var h_clist = document.getElementsByClassName("class_half_day");
				var l_clist = document
						.getElementsByClassName("class_leave_day");
				var a_clist = document
						.getElementsByClassName("class_absent_day");

				if (document.getElementById('chek1').checked == true) {
					for (var i = 0; i < f_list.length; ++i) {
						f_list[i].checked = true;
						h_clist[i].checked = false;
						l_clist[i].checked = false;
						a_clist[i].checked = false;
					}
				} else {
					for (var i = 0; i < f_list.length; ++i) {
						f_list[i].checked = false;
						h_clist[i].checked = false;
						l_clist[i].checked = false;
						a_clist[i].checked = false;
					}
				}
			}
		</script>

		<script>
			function checkAttendance() {
				var y = confirm("Do You Want to Submit?");
				if (y == true) {
					var check = document.getElementsByName('chek');
					//alert(check);
					var attenddate = document.getElementById('attenddate').value;
					var user_id = document.getElementById('user_id_fk').value;
					//	alert(attenddate);
					for (var i = 0; i < check.length; i++) {
						if (check[i].checked) {
							//	 alert(check[i].value);
							$
									.ajax({
										url : 'Ajax_employee_attendence_entry.jsp',
										data : 'chek=' + check[i].value
												+ '&attenddate=' + attenddate
												+ '&user_id=' + user_id,
										type : 'post',
										success : function(msg) {
											window.location.href = 'employee_attendence_entry.jsp?msg=Yes';
										}
									});
						}
					}
				}
			}
		</script>

		<script>
			/*  For check employee attendance entry or not */

			function checkAttendenceStatus(date) {
				//alert(date);
				if (date != '') {

					$.ajax({
						url : 'Ajax_check_employee_attendance_avail.jsp',
						data : 'date=' + date,
						type : 'post',
						success : function(msg) {

							// alert(msg);

							$('#table_body').html(msg);

						}
					});
				} else {
					//alert(date);
					var class_row = document
							.getElementsByClassName('class_row');
					for (i = 0; i < class_row.length; i++) {
						class_row[i].style.display = "none";
					}
				}
			}
		</script>

		<div class="pagetitle text-center">
			<h1 id="page_title">Employee Attendance Entry</h1>
		</div>

		<div id="rev"></div>

		<section class="section">
			<div class="row">
				<div class="card" style="border-radius: 25px;">
					<div class="card-body table-responsive">

						<div class="container">

							<div class="row col-12 justify-content-center" id="table_search">
								<form autocomplete="off"
									class="row g-3 d-flex justify-content-center needs-validation">


									<div class="col-md-3">
										<div class="control form-floating">
											<input type="date" class="form-control" id="attenddate"
												onchange="checkAttendenceStatus(this.value);"
												name="attenddate" placeholder="Name" required> <label
												for="name">Select Date</label>
											<div class="invalid-feedback">Please, Enter Date !</div>
										</div>
									</div>

								</form>

							</div>

						</div>

						<br>

						<form autocomplete="off" id="registration" name="registration"
							onsubmit="" method="post">

							<input type="hidden" name="User_id_fk" id="user_id_fk"
								value="<%=user_id%>">

							<table  class="table hover table-responsive nowrap">

								<thead class="text-center" id="thead">
									<tr>
										<th style="text-align: center; font-size: 15px" width="2%">S.No.</th>

										<th style="text-align: left; font-size: 15px" width="6%"><input
											type="checkbox" id="chek1" onclick="checkedAll();" />Attendence</th>
										<th style="text-align: center; font-size: 15px" width="8%">Name</th>
										<th style="text-align: center; font-size: 15px" width="7%">Email</th>
										<th style="text-align: center; font-size: 15px" width="5%">Mobile
											No.</th>
										<th style="text-align: center; font-size: 15px" width="5%">Other
											No.</th>
										<th style="text-align: center; font-size: 15px" width="10%">Address</th>
										<th style="text-align: center; font-size: 15px" width="5%">City
										</th>
										<th style="text-align: center; font-size: 15px" width="3%">Salary</th>

									</tr>
								</thead>
								<tbody class="text-center" id="table_body">

								</tbody>
							</table>
						</form>

						<table style="border: none;">
							<tr style="border: none;">
								<td colspan="7" style="border: none;"><input type="button"
									value="Submit" class="submit-btn" style="margin-left: 10px;"
									onclick="checkAttendance()" /></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</section>

	</main>
	<!-- End #main -->

	<script>
		function checkOrNot(id, att_status) {
			//alert(att_status);

			if (document.getElementById("a" + id + "_" + att_status).checked == false) {
				//	document.getElementById("a" +id+ "_"+ att_status).checked = false;
				document.getElementById("a" + id + "_1").checked = false;
				document.getElementById("a" + id + "_2").checked = false;
				document.getElementById("a" + id + "_3").checked = false;
				document.getElementById("a" + id + "_0").checked = false;
				document.getElementById('chek1').checked = false;
			} else {
				document.getElementById("a" + id + "_1").checked = false;
				document.getElementById("a" + id + "_2").checked = false;
				document.getElementById("a" + id + "_3").checked = false;
				document.getElementById("a" + id + "_0").checked = false;
				document.getElementById("a" + id + "_" + att_status).checked = true;
			}

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