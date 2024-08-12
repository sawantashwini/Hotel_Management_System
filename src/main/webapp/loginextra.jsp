<!DOCTYPE html>
<%@page import="com.dto.ProjectDto"%>
<%@page import="com.service.ProjectService"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<%
	
	int user_id = (Integer) (session.getAttribute("user_id") == null ? 0 : session.getAttribute("user_id"));
	if (user_id != 0) {
			response.sendRedirect("index.jsp");
	}
		int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
		
	%>

<%
ProjectService project_head_ser = new ProjectService();
ProjectDto project_head_dto = project_head_ser.getProjectInfoById(1, config, request);
%>



<title><%=project_head_dto.getName()%></title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- logos -->
<link href="ProjectImage/<%=project_head_dto.getId()%>.jpg" rel="icon">
<link href="ProjectImage/<%=project_head_dto.getId()%>.jpg"
	rel="apple-touch-icon">
<link href="assets/vendor/fontawesome-free/css/all.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<style type="text/css">
.control input {
	height: 100% !important;
	font-size: 13px !important;
	padding: 18px 8px 3px 11px !important;
}

.control input:focus {
	box-shadow: 2px 2px 10px 1px #fbb5b5;
}

.control select {
	height: 100% !important;
	font-size: 13px !important;
	padding: 18px 8px 3px 11px !important;
	box-shadow: 2px 2px 10px 1px #fbb5b5;
}

.card-title {
	letter-spacing: 5px;
	text-align: center;
	font-size: 30px;
	font-weight: 1000;
	padding: 2%;
	color: #f00;
}

.control label, .input-group-text i {
	color: #f00;
}

.admin b {
	font-family: 'Odor Mean Chey', sans-serif;
	font-weight: 800;
	font-size: 25px;
	color: #f03606;
}

.login-logo {
	width: 100px;
	height: 100px;
}
</style>



<style>
body {
	background: #ffffff;
	margin: 0;
}

/* .custom-container {
	width: 100%;
	padding: 0;
	margin: 0;
	min-height: 500px;
	display: flex;
	align-items: stretch;
	background: url('assets/img/bg-image.jpg') no-repeat center/cover;
} */

.custom-container {
    position: relative; /* Add position: relative */
    width: 100%;
    padding: 0;
    margin: 0;
    min-height: 500px;
    display: flex;
    align-items: stretch;
    
}

.custom-container::before {
	z-index:-1;
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: url('assets/img/bg-img.jpg') no-repeat center/cover;
    /* Opacity */
    background-color: rgba(255, 255, 255, 0.5); /* Adjust the alpha value for opacity */
    /* Contrast */
    filter: contrast(150%); /* Adjust contrast percentage */
}



.first-container {
	margin: 0;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	border-radius: 0 0 50% 0;
	background: url('assets/img/hostel.jpg') no-repeat center/cover;
	background-size: cover;
	background-color: #d2acac36;
	border-right: 5px solid red;
}

.second-container {
	padding: 5%;
	padding-top: 0%
}

.third-container {
	border-radius: 50% 0 0 0;
	background: #fffff;
	display: flex;
	padding-top: 5%;
	flex-wrap: wrap;
	align-content: flex-end;
	justify-content: center;
	align-items: flex-end;
	flex-direction: column;
}

.ad-image img {
	max-width: 100%;
	/* height: 290px; */
	border: 3px solid red;
}

@media ( max-width : 991px) {
	.custom-container {
		width: 100%;
		padding-left: 0;
		padding-right: 0;
		flex-direction: column;
	}
	.row {
		margin-left: 0;
		margin-right: 0;
	}
	.first-container {
		height: 300px;
		border-radius: 0 0 0 50%;
		width: 100vw;
		border-right: none !important;
		border-bottom: 5px solid red !important;
		background: url('assets/img/hostel.jpg') no-repeat center/cover;
		background-size: cover;
	}
	.second-container {
		height: auto;
		margin-top: 20px;
	}
	.third-container {
		/* height: 300px; */
		border-radius: 0 50% 0 0;
		/* background: #fff; */
		width: 100vw;
		/* margin-top: 20px; */
		display: flex;
		padding-top: 5%;
		flex-wrap: wrap;
		align-content: center;
		justify-content: center;
		align-items: flex-end;
		flex-direction: column;
	}
}

@media ( min-width : 992px) {
	.custom-container {
		width: 100%;
		height: 100vh;
		min-height: 600px;
		padding-left: 0;
		padding-right: 0;
	}
	.first-container, .second-container, .third-container {
		height: 100%;
		flex: 1;
	}
}

/* CSS for the card */
.card {
	border: 1px solid #ccc;
	border-radius: 10px;
	width: 100%;
	padding: 2%;
	box-shadow: 2px 2px 10px 1px #fbb5b5;
	background-color: #0000003d !important;
}

.card-body {
	padding: 25px;
	/* Adjust the padding as needed */
}

.logo {
	display: flex;
	justify-content: center;
}

@font-face {
	font-family: 'ChunkFive';
	src: url('assets/css/ChunkFive-Regular.otf') format('otf');
	/* Add additional formats if available */
	font-style: normal;
}

.login-title {
	font-family: 'ChunkFive';
}

.submit-btn {
	color: #fff;
	background-color: #f03606;
	border-color: #f03606;
	box-shadow: rgb(49, 132, 253);
}

.submit-btn:hover {
	background-color: #ff3400;
}
</style>


</head>

<body>
	
	<% String imageFileName = "ProjectImage/" + project_head_dto.getId() + ".jpg";
	%>
	<div class="container-fluid custom-container">
		<div class="first-container col-lg-3 col-md-12 ripple-right"></div>

		<div class="second-container col-lg-6 col-md-12 d-flex flex-column">
		
			<div style="margin:auto 0;">

			<div class="d-flex flex-column align-items-center mt-3">
				<a href="index.jsp" class="d-flex align-items-center w-auto"> <img
					<%-- Check if the image file exists for the given ID --%>
           
					<%if (new java.io.File(application.getRealPath(imageFileName)).exists()) {%>
					src="<%=imageFileName%>" <%} else {%> src="assets/img/logo.png"
					<%}%> alt="" class="login-logo">
				</a> <a href="index.jsp" class=" d-flex align-items-center w-auto">
					<span class="admin d-lg-block" style="color: #f00;"><b><%=project_head_dto.getName()%></b></span>
				</a>
			</div>


			<div class="card mt-2">
				<div class="card-body">
					<h2 class="card-title login-title">
						<b>Login</b>
					</h2>
					<form action="Loginservlet" method="Post">


						<div class="col-12">
							<div class="input-group has-validation m-2">
								<div class="input-group-text" id="inputGroupPrepend">
									<i class="fa fa-user"></i>
								</div>
								<div class="form-floating control">
									<input type="text" class="control form-control" id="mobile_no"
										name="Mobile_no" placeholder="Mobile No" required> <label
										for="mobile_no">Enter your Mobile No.</label>

								</div>
								<div class="invalid-feedback">Please enter your Mobile
									No.!</div>

							</div>
						</div>
						<div class="col-12">
							<div class="input-group has-validation m-2">
								<div class="input-group-text" id="inputGroupPrepend">
									<i class="fa-solid fa-lock"></i>
								</div>
								<div class="form-floating control">
									<input type="Password" class="control form-control"
										id="password" name="Password" placeholder="Mobile No" required>
									<label for="password">Enter your Password</label>

								</div>
								<div class="invalid-feedback">Please enter your Mobile
									No.!</div>

							</div>
						</div>
						<div class="text-center">
							<button type="submit" class="submit-btn">Login</button>
						</div>

					</form>
				</div>
			</div>
			</div>
		</div>

		<div class="third-container col-lg-3 col-md-12">
			<div class="ad-image">
				<img src="assets/img/ad-image.jpg" alt="Ad Image" />
			</div>
		</div>
	</div>
	
	

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>
	<script type="text/javascript"
		src="assets/vendor/jquery/jquery-3.6.0.min.js"></script>
	<!-- Template Main JS File -->
	<script src="assets/js/alert.js"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		//alert("hello");
	    if (window.history.pushState) {
	        var newUrl = "index.jsp";
	        window.history.pushState({ path: newUrl }, '', newUrl);
	    }
	    
	    var allVisibleInputs = $('input:visible');

		// Set focus on the first visible input
		allVisibleInputs.first().focus();
	});
	
	/* $(document).ready(function() {
		// Select all visible input elements
		
		
		 allVisibleInputs.click(function() {
	            $(this).select();
	       });
	}); */

	</script>
	
	
</body>

</html>
