	<!-- head -->
	<%@include file="include/head.jsp"%>
	<!-- head end -->
	</head>

<body >

  <!-- ======= Header ======= -->
  <%@include file="include/header.jsp" %>
  <!-- ======= Header end======= -->
  
  
  	<!-- ======= Sidebar ======= -->
  <%@include file="include/sidebar.jsp" %>
	 <!--  Sidebar End-->
  
  
    <main id="main" class="main">
      <div class="row justify-content-center">
        <div class="pagetitle col-lg-8 text-center">
            <h1>Edit Project</h1>

          </div>
        </div>

    <section class="section dashboard">
      <div class="row justify-content-center">

        <div class="col-lg-8">

          <div class="card">
            <div class="card-body">
             <!--  <h5 class="card-title">Edit Project</h5> -->
			<br>
              
            <!-- Floating Labels Form -->
            <form autocomplete="off" action="ProjectServlet" enctype="multipart/form-data"  method="post" class="row g-3 needs-validation" novalidate>

 
              <%
						int id = Integer.parseInt(request.getParameter("id") == null ? "0"
								: request.getParameter("id"));
						ProjectService service = new ProjectService();

						ProjectDto dto = service.getProjectInfoById(id, config, request);
					%>
					
              
                  
                  
                   <input type="hidden" value="<%=id%>" name="Id">
                 

                <div class="col-md-6">
                  <div class="control form-floating mb-3">
                    <input type="text" class="form-control" id="mobile_no."name="Mobile_no" placeholder=" Mobile no." value="<%=dto.getMobile_no() %>"required>
                    <label for="Mobile_no"> Mobile no.</label> 
                    <div class="invalid-feedback">Please enter a valid  Mobile no.!</div>
                   
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="control form-floating mb-3">
                    <input type="text" class="form-control" id="gst_in"name="Gst_in" placeholder=" Mobile no." value="<%=dto.getGst_in()%>" >
                    <label for="Gst_in"> GST_in No.</label> 
                    <div class="invalid-feedback">Please enter a valid  GST_in No.!</div>
                   
                  </div>
                </div>
                <div class="col-lg--12">
                  <div class="control form-floating">
                    <textarea class="form-control" name="Address" id="address" placeholder=" Local Address"  ><%=dto.getAddress()%></textarea>
                    <label for="address">Local Address</label>
                    <div class="invalid-feedback">Please enter a valid Local address!</div>
                  </div>
                </div>
                  
                <div class="col-lg--12">
                  <div class="form-floating">
                    <textarea   class="form-control" name="Term_and_conditions" id="term_and_Conditions" placeholder=" Term_and_Conditions"   ><%=dto.getTerm_and_conditions()%></textarea>
                    <label for="Term_and_conditions">Terms and Conditions</label>
                    <div class="invalid-feedback">Please enter a valid Term_and_Conditions!</div>
                  </div>
                </div>
                 
                              
                           <div class="d-flex justify-content-center">
									<div class="col-md-2">
										<div class="control form-floating">
											<div class="file-form" style="padding: 0.5%;">
												<div class="d-flex justify-content-center pt-1">
													<img src="ProjectImage/<%=dto.getId()%>.jpg" id="preview-selected-image" class="preview"
														alt="" style="height: 80px; width: 80px;">
												</div>
												<div class="d-flex justify-content-center">
													<div class="pt-1 pb-1">
														<div style="display: none;">
															<input name="File" onchange="previewImage(event);"
																type="file" id="file">
														</div>
														<label for="file" id="file-label"> <a
															class="btn btn-primary btn-sm"
															title="Upload new profile image"> <i
																class="bi bi-upload"></i>
														</a>
														</label>
													</div>
													<!-- <div class="pt-2" style="margin-left: 2px;">
														<a class="btn btn-primary btn-sm" title="download"
															id="download" download><i
															class="fa-solid fa-download"></i> </a>
													</div> -->
												</div>
											</div>
										</div>
									</div>
								</div>
            <div class="text-center">
              <button type="submit" class="submit-btn">Submit</button>
            </div>

            
          </form><!-- End floating Labels Form -->
 
          </div>
        </div>
      </div>


      </div>
    </section> 
		
    </main>
<script>
	 //Preview Image
function previewImage(event) {
	let reader = new FileReader();
	reader.onload = function() {
		let element = document.getElementById('preview-selected-image');
		element.src = reader.result;
	}
	reader.readAsDataURL(event.target.files[0]);
} 
</script>
	 <!-- ======= Footer ======= -->
  <%@include file="include/footer.jsp" %>
  <!-- End Footer --> 
  
</body>

</html>