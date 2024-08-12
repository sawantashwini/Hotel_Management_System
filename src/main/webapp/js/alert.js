$(document).ready(function() {
	var currentURL = window.location.href;
	var title = getTitleFromURL(currentURL);
	//document.getElementById("title_link").innerHTML = title;
	var msgValue = new URLSearchParams(window.location.search).get("msg");
	loadAlert(title, msgValue);
});


function getTitleFromURL(url) {
	var title = '';

	var alertValue = new URLSearchParams(window.location.search).get("alert_title");
	if (alertValue) {
		title = alertValue;
	} else {
		// Split the URL by '/' and get the last part (assumes the title is at the end)
		var urlParts = url.split('/');
		var lastPart = urlParts[urlParts.length - 1];
		title = lastPart.replace(/\.[^/.]+$/, "");  // Remove file extension (assuming '.jsp' or similar)
		/* if (!title.endsWith(" Report") && title.includes("manage_")) { // Check if " Report" is already present at the end, and if "manage_" is present in the title
		  title = title + ' Report';  // If both conditions are met, add " Report" to the end of the title
		} */
		title = title.replace(/_report/g, ''); // Replace "_report" with an empty string
		title = title.replace(/manage_/g, ''); // Replace "manage_" with an empty string
		title = title.replace(/add_/g, '');   // Replace "add_" with an empty string
		title = title.replace(/edit_/g, '');   // Replace "add_" with an empty string
		title = title.replace(/msg/g, 'Message');   // Replace "add_" with an empty string
		title = title.replace(/_/g, ' '); // Replace underscores with spaces and capitalize the first letter
		title = title.replace(/index/g, ' '); // Replace underscores with spaces and capitalize the first letter
		title = title.charAt(0).toUpperCase() + title.slice(1);
	}

	return title;
}


/**
 * 
 */
function successAlert(message) {
	setAlert("Success", message, "alert-success", "bi-check-circle");
}

function failureAlert(message) {
	setAlert("Failure", message, "alert-danger", "bi-x-circle");
}

function dangerAlert(message) {
	setAlert("Danger", message, "alert-warning", "bi-exclamation-triangle");
}

function warningAlert(message) {
	setAlert("Warning", message, "alert-warning", "bi-question-circle");
}

function setAlert(title, message, alertType, alertIcon) {
	const alertHTML = `
    <div class="msg_alert row col-12">
      <div class="d-flex justify-content-center ">
        <div class="alert ${alertType} alert-dismissible" role="alert" style="display:none;">
          <i class="bi ${alertIcon} me-1"></i><strong>${title}!</strong> ${message}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
      </div>
    </div>
  `;
	$(".msg_alert").remove();
	$("body").prepend(alertHTML);
	//$(".alert").slideDown();
	$(".alert").fadeIn();
	//$(".alert").show();
	//$(".alert").slideToggle();
	//$(".alert").fadeToggle();

	setTimeout(function() {
		$(".alert").fadeOut();
		$(".msg_alert").remove();

	}, 3000); // Fade out after 3 seconds (3000 milliseconds)
}

function loadAlert(title, msg) {
	const actionMessage = {
		'Yes': 'added Successfully',
		'YesUp': 'Updated Successfully',
		'YesDel': 'Deleted Successfully',
		'YesLogin': 'Login Successfully'
	}[msg];

	const actionMessageNot = {
		'No': 'Not added Successfully',
		'NoUp': 'Not Updated Successfully',
		'NoDel': 'Not Deleted Successfully'
	}[msg];

	actionMessage ? successAlert(`${title} ${actionMessage}.`) : actionMessageNot ? failureAlert(`${title} ${actionMessageNot}.`) : null;
}


