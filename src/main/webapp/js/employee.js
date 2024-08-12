
//paidamount
function paidAmountEvent() {

	let cash_amount = parseFloat(document.getElementById("cash_amount").value);
	let online_amount = parseFloat(document
		.getElementById("online_amount").value);

	paid_amount = cash_amount + online_amount;
	document.getElementById("paid_amount").value = paid_amount
		.toFixed(2);
}

//payment Mode while payment
function checkPaymentMode() {

	//alert("hello");

	if (document.getElementById('cash_mode').checked) {

		document.getElementById("payment_mode").value = "Cash";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_block").className = "d-none";
		document.getElementById("online_amt_block").className = "d-none";
		document.getElementById("paid_block").className = "d-none";

		document.getElementById("online_amount").value = "0";
		document.getElementById("online_date").value = "";
		document.getElementById("online_remark").value = "N/A";
		document.getElementById("bank_id_fk").value = "0";
		document.getElementById("online_way").value = "N/A";

	} else if (document.getElementById('online_mode').checked) {
		document.getElementById("payment_mode").value = "Online";
		document.getElementById("online_amt_block").className = "col-md-3";

		document.getElementById("cash_block").style.display = "none";
		document.getElementById("online_block").className = "row g-3";
		document.getElementById("paid_block").className = "d-none";

		document.getElementById("cash_amount").value = "0.0";
		document.getElementById("online_way").value = "N/A";
	} else if (document.getElementById('both_mode').checked) {
		document.getElementById("payment_mode").value = "Both";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_amt_block").className = "col-md-3";
		document.getElementById("paid_block").className = "col-md-3";

		document.getElementById("online_block").className = "row g-3";
		document.getElementById("cash_amount").value = "0.0";
		document.getElementById("online_amount").value = "0.0";
		document.getElementById("online_way").value = "N/A";
		document.getElementById("paid_amount").value = "0.0";

	}
}

//checked payment mode on load
function loadPaymentMode() {

	//alert("hello");

	if (document.getElementById('cash_mode').checked) {

		document.getElementById("payment_mode").value = "Cash";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_block").className = "d-none";
		document.getElementById("online_amt_block").className = "d-none";
		document.getElementById("paid_block").className = "d-none";

	} else if (document.getElementById('online_mode').checked) {
		document.getElementById("payment_mode").value = "Online";
		document.getElementById("online_amt_block").className = "col-md-3";

		document.getElementById("cash_block").style.display = "none";
		document.getElementById("online_block").className = "row g-3";
		document.getElementById("paid_block").className = "d-none";

	} else if (document.getElementById('both_mode').checked) {
		document.getElementById("payment_mode").value = "Both";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_amt_block").className = "col-md-3";
		document.getElementById("paid_block").className = "col-md-3";

		document.getElementById("online_block").className = "row g-3";

	}
}


//Preview Image
function previewImage(event) {
	let reader = new FileReader();
	reader.onload = function() {
		let element = document.getElementById('preview-selected-image');
		element.src = reader.result;
	}
	reader.readAsDataURL(event.target.files[0]);
}
function refreshBank() {

	//alert("Start");
	var flag = 'Bank';


	$.ajax({

		url: 'AjaxDesign/AjaxRefreshAvailItem.jsp',
		data: 'Flag=' + flag,
		type: 'post',
		success: function(msg) {

			//alert(msg);

			$('#bank_id_fk').html(msg);




		}
	});
}



	// function is used for class create select box with search
/*$(document).ready(function () {
	initializeSelectizeCity();
	});
function refreshCity() {
	var flag = 'City';
	
	if (flag) {
		
		$.ajax({
			url : 'AjaxDesign/AjaxRefreshAvailItem.jsp',
			data : 'Flag='+ flag,
			type : 'post',
			success : function(msg) {
	
				//alert(msg);
				$("#city_id_fk_refresh").html(msg);
				initializeSelectizeCity();
			}
		});
	}
	
}*/