
<%@include file="include/head.jsp"%>

</head>
<%
int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
String month = request.getParameter("m") == null ? "" : request.getParameter("m");
String year = request.getParameter("y") == null ? "" : request.getParameter("y");

EmployeeService service = new EmployeeService();
EmployeeDto att_dto = service.getAttendenceInfoById(id, request);
EmployeeDto emp_dto = service.getEmployeeInfoById(att_dto.getEmployee_id_fk(), config, request);
%>


<body onload="attendanceEvent();">
	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->

	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->

	<!--  Main Start -->

	<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>

	<main id="main" class="main">

		<div class="row justify-content-center">
			<div class="pagetitle col-lg-8 text-center">
				<h1>Edit Employee Attendance</h1>
			</div>
		</div>

		<section class="section dashboard">
			<div class="row justify-content-center">
				<div class="col-lg-8">
					<div class="card">
						<div class="card-body">
							<br />
							<!-- Floating Labels Form -->
							<form autocomplete="off" method="post"
								class="row g-3 needs-validation d-flex justify-content-center"
								novalidate>

								<input type="hidden" name="User_id_fk" id="user_id_fk"
									value="<%=user_id%>"> <input type="hidden" name="Id"
									id="id" value="<%=id%>">

								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="name"
											value="<%=emp_dto.getName()%>" placeholder="Name"
											onblur="checkNameAvail();" readonly> <label
											for="Name">Name</label>
										<div class="invalid-feedback">Please, Enter City Name!</div>
									</div>
								</div>

								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="text" class="form-control" id="mobile"
											value="<%=emp_dto.getMobile_no()%>" placeholder="Mobile no"
											onblur="checkNameAvail();" readonly> <label
											for="Name">Mobile no</label>
										<div class="invalid-feedback">Please, Enter Mobile no!</div>
									</div>
								</div>

								<div class="col-md-4 col-12">
									<div class="control form-floating">
										<input type="date" class="form-control" id="in_date"
											value="<%=att_dto.getIn_date()%>" placeholder="Date"
											onblur="checkNameAvail();" readonly> <label
											for="in_date">Date</label>
										<div class="invalid-feedback">Please, Enter Date!</div>
									</div>
								</div>


								<h5 class="text-center card-title">Attendance Details</h5>
								<div class="container d-flex justify-content-center">
									<div class="col-md-8 ">
										<div class="d-flex justify-content-around">

											<div class="form-check" style="margin-left: 5px;">
												<input class="form-check-input" type="radio"
													name="Attendance1" id="full_day" value="1"
													onclick="attendanceEvent();"
													<%if(att_dto.getAttendance_status()==1){%> checked <%} %>>
												<label class="form-check-label w-100" for="attendance1">
													Full Day</label>
											</div>
											<div class="form-check" style="margin-left: 5px;">
												<input class="form-check-input" type="radio"
													name="Attendance1" id="half_day" value="2"
													onclick="attendanceEvent();"
													<%if(att_dto.getAttendance_status()==2){%> checked <%} %>>
												<label class="form-check-label w-100" for="attendance1">
													Half Day</label>
											</div>

											<div class="form-check" style="margin-left: 5px;">
												<input class="form-check-input" type="radio"
													name="Attendance1" id="leave_day" value="3"
													onclick="attendanceEvent();"
													<%if(att_dto.getAttendance_status()==3){%> checked <%} %>>
												<label class="form-check-label w-100" for="attendance1">
													Leave Day</label>
											</div>

											<div class="form-check" style="margin-left: 5px;">
												<input class="form-check-input" type="radio"
													name="Attendance1" id="absent_day" value="0"
													onclick="attendanceEvent();"
													<%if(att_dto.getAttendance_status()==0){%> checked <%} %>>
												<label class="form-check-label w-100" for="attendance1">
													Absent Day</label>
											</div>

										</div>
									</div>
								</div>

								<input type="hidden" id="attendance" name="Attendance" value="" />

								<div class="text-center">
									<button type="button" class="submit-btn"
										onclick="updateAttendanceEntry();">Submit</button>
								</div>

							</form>
							<!-- End floating Labels Form -->

						</div>
					</div>
				</div>
				<div id="rev"></div>
			</div>
		</section>
	</main>
	<!-- End main -->

	<br />

	<script>
/*  For check correct bus route */

function updateAttendanceEntry() {
	
	var id = document.getElementById("id").value;
	var user_id_fk = document.getElementById("user_id_fk").value;
	var attendance_status = document.getElementById("attendance").value;
	var name = document.getElementById("name").value;
	// alert(id);
	// alert(user_id_fk);
	// alert(attendance_status);
	// alert(name);

if (id >0) {
	
	$.ajax({
		url : 'Ajax_update_attendance.jsp',
		data : 'Id=' + id +'&User_id_fk='+ user_id_fk+'&Attendance_status='+attendance_status,
		type : 'post',
		success : function(msg) {

	//	 alert(msg);
		 
		 $('#rev').html(msg);
			
			var status = document.getElementById("status").value;
		//	alert(status);
			
			if (status =='true') {
				window.location.href = "employee_attendence_report.jsp?msg=YesUp&m=<%=month%>&y=<%=year%>&n="+ name +"&id=<%=att_dto.getEmployee_id_fk()%>";
			} else {
				window.location.href = "employee_attendence_report.jsp?msg=YesUp&m=<%=month%>&y=<%=year%>&n="+ name +"&id=<%=att_dto.getEmployee_id_fk()%>";
			}
		}
	});
	}
}
</script>

	<script>
function attendanceEvent() {

	if(document.getElementById('full_day').checked){
		
		document.getElementById("attendance").value="1";
		
		
	}else if(document.getElementById('half_day').checked){
		
		document.getElementById("attendance").value="2";
		
	}
	else if(document.getElementById('leave_day').checked) {
		
		
		document.getElementById("attendance").value="3";
	}
else if(document.getElementById('absent_day').checked) {
		
		
		document.getElementById("attendance").value="0";
		
	}

}
</script>

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->

</body>
</html>