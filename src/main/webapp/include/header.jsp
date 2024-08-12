
<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

<%
	String imageFileName = "ProjectImage/" + project_head_dto.getId() + ".jpg";
	%>
	<div class="d-flex align-items-center justify-content-between">
		<%
		if (menu_table == 1) {
		%><i class="bi bi-list toggle-sidebar-btn"
			onclick=""></i>
		<%
		} else {
		%><a href="index.jsp"><i class="fa fa-home toggle-home-btn"
			onclick=""></i></a>
		<%
		}
		%>
		<a href="index.jsp" class="logo d-flex align-items-center"> <img
			<%if (new java.io.File(application.getRealPath(imageFileName)).exists()) {%>
					src="<%=imageFileName%>" <%} else {%> src="assets/img/logo.jpg"
					<%}%>
			style="border-radius: 50%; border: 2px solid #fff;" alt=""> <span
			class="d-lg-block mx-1"><%=project_head_dto.getName()%></span>
		</a>

	</div>
	<!-- End Logo -->

	<nav class="header-nav ms-auto">
		<ul class="d-flex align-items-center">

			<li class="nav-item dropdown pe-3"><a
				class="nav-link nav-profile d-flex align-items-center pe-0" href="#"
				data-bs-toggle="dropdown"> <img src="assets/img/user.png"
					alt="Profile" class="rounded-circle"> <span
					class="d-none d-md-block dropdown-toggle ps-2"><%=user_head_dto.getName()%></span>
			</a> <!-- End Profile Iamge Icon -->

				<ul
					class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">

					<li class="dropdown-header">
						<h6><%=user_head_dto.getName()%></h6> <span>User</span>
					</li>

					<li>
						<hr class="dropdown-divider">
					</li>

					<li><a class="dropdown-item d-flex align-items-center"
						href="edit_project.jsp?id=<%=project_head_dto.getId()%>"><i
							class="fa-solid fa-eye"></i> <span>View Project</span> </a></li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li><a class="dropdown-item d-flex align-items-center"
						href="user_profile.jsp"> <i class="bi bi-person"></i> <span>My
								Profile</span>
					</a></li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li><a id="downloadButton" onclick="backup();"
						class="dropdown-item d-flex align-items-center"> <i
							class="fa-solid fa-file-arrow-down"></i> <span>Download
								Backup</span>
					</a></li>
					
					<li>
						<hr class="dropdown-divider">
					</li>
					<li><a id="downloadButton" href="edit_msg.jsp?id=1"
						class="dropdown-item d-flex align-items-center"> <i
							class="fa-solid fa-message"></i> <span>Message</span>
					</a></li>
					<li>
						<hr class="dropdown-divider">
					</li>


					<li><a class="dropdown-item d-flex align-items-center"
						href="logout.jsp"> <i class="bi bi-box-arrow-right"></i> <span>Sign
								Out</span>
					</a></li>

				</ul> <!-- End Profile Dropdown Items --></li>
			<!-- End Profile Nav -->

		</ul>
	</nav>
	<!-- End Icons Navigation -->

</header>
<!-- End Header -->

<div class="download-container" id="download-container">
	<!-- <span class="close-button" onclick="hideDownload()">
      <i class='bx bx-x'></i>
    </span> -->
	<div class="download-content">
		<h2>Wait, File is Downloading</h2>
		<div class="custom-progress-container">
			<div class="custom-progress-bar" role="progressbar" aria-valuenow="0"
				aria-valuemin="0" aria-valuemax="100"></div>
		</div>
		<div class="progress-text">0%</div>
	</div>
</div>