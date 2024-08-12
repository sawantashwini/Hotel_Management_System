<div id="ajax_response_design"></div>

<!-- <div id="title_link"></div> -->

<!-- ======= Footer ======= -->
<footer id="footer" class="row footer">
	<div class="copyright col-lg-8 col-sm-12 ">
		&copy; Copyright: <span><a href="https://mykhandwa.com/"><%=project_head_dto.getName()%></a></span>
		All Rights Reserved.
	</div>
	<div class="copyright-1 col-lg-4 col-sm-12 ">
		Developed By : <a href="#"> Madss Soft. Sol. Pvt. Ltd.</a>
	</div>
</footer>
<!-- End Footer -->

<a href="#"
	class="back-to-top d-flex align-items-center justify-content-center"><i
	class="fa fa-angle-double-up"></i></a>
<!-- Vendor JS Files -->
<!-- <script type="text/javascript" src="/media/js/site.js?_=87a9e5380bcd291d5eea77cf210da433" data-domain="datatables.net" data-api="https://plausible.sprymedia.co.uk/api/event"></script>
<script type="text/javascript" src="/media/js/dynamic.php?comments-page=extensions%2Fbuttons%2Fexamples%2Finitialisation%2Fexport.html"></script>
 -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<!-- <script src="assets/vendor/chart.js/chart.min.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script> -->
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/js/ethicalads.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/jquery-3.6.0.min.js"></script>


<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
<script src="js/refresh_add.js"></script>
<script src="js/alert.js"></script>
<script src="js/PaymentJavascript.js"></script>
<script src="js/datalist-refresh.js"></script>
<script src="js/popup.js"></script>

<script>
	function checkNameAvail(name, id, flag) {
		if (name != '') {
			$
					.ajax({
						url : 'AjaxFolder/AjaxCheckNameAvail.jsp',
						data : 'name=' + name + '&id=' + id + '&flag=' + flag,
						type : 'post',
						success : function(msg) {
							//alert(msg);
							$('#name_avail_resposnse').html(msg);
							var check_name = document
									.getElementById("check_name").value;
							if (check_name == 'true') {
								document.getElementById('name').value = "";
							}
						}
					});
		} else {
			//alert("Please Insert Name");
			return false;
		}
	}
</script>

<script>
	function backup() {
		//openDirectoryDialog();
		//alert("Start");
		showDownload();
		//showLoader()
		// Send an AJAX request to trigger the backup process
		$.ajax({
			url : "backup.jsp",
			type : "POST",
			success : function(response) {
				//alert(response);
				//hideDownload();
				//hideLoader();
				console.log(response); // Print the backup status to the browser console
				// window.location.href = "download.jsp"; // Redirect to the download page
				//hideDownload();
			},
			error : function(xhr, status, error) {
				alert("error");
				console.error(error); // Print any errors to the browser console
			}
		});
	}
	var progressBar = document.querySelector('.custom-progress-bar');
	var progressText = document.querySelector('.progress-text');
	var interval;
	function startDownload() {
		clearInterval(interval); // Clear any existing interval
		progressBar.style.width = '0%'; // Reset the progress bar width to 0%
		progressText.textContent = '0%'; // Reset the progress text to 0%
		interval = setInterval(progress, 100);
	}
	function progress() {
		var width = parseInt(progressBar.style.width); // Get the current progress bar width
		if (isNaN(width) || width >= 100) {
			width = 0; // Reset width to 0 if it's not a number or already at 100%
		}
		width += 10;
		progressBar.style.width = width + '%';
		progressText.textContent = width + '%';
		if (width >= 100) {
			clearInterval(interval);
			hideDownload();
		}
	}
	function hideDownload() {
		var downloadContainer = document.getElementById("download-container");
		downloadContainer.style.display = "none";
	}
	function showDownload() {
		var downloadContainer = document.getElementById("download-container");
		downloadContainer.style.display = "flex";
		//alert("Start download");
		startDownload();
	}
</script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var inputFields = document.querySelectorAll('input');
        inputFields.forEach(function(input) {
            input.setAttribute('autocomplete', 'off');
        });
    });
</script>

