function checkPaymentMode() {

	//alert("hello");

	if (document.getElementById('cash_mode').checked) {

		document.getElementById("payment_mode").value = "Cash";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_block").className = "d-none";
		document.getElementById("online_amt_block").className = "d-none";
		document.getElementById("paid_block").className = "d-none";

		document.getElementById("online_amount").value = "0";
		document.getElementById("bank_id_fk").value = "0";
		document.getElementById("bank_name").value = "";
		document.getElementById("online_date").value = "";
		document.getElementById("online_remark").value = "N/A";
		document.getElementById("online_way").value = "N/A";


	} else if (document.getElementById('online_mode').checked) {
		document.getElementById("payment_mode").value = "Online";
		document.getElementById("online_amt_block").className = "col-md-3";

		document.getElementById("cash_block").style.display = "none";
		document.getElementById("online_block").className = "row g-3";
		document.getElementById("paid_block").className = "d-none";
		document.getElementById("cash_amount").value = "0";
		document.getElementById("bank_id_fk").value = "0";
		document.getElementById("bank_name").value = "";
		document.getElementById("online_date").value = "";
		document.getElementById("online_remark").value = "N/A";
		document.getElementById("online_way").value = "N/A";
	} else if (document.getElementById('both_mode').checked) {
		document.getElementById("payment_mode").value = "Both";
		document.getElementById("cash_block").style.display = "block";
		document.getElementById("online_amt_block").className = "col-md-3";
		document.getElementById("paid_block").className = "col-md-2";

		document.getElementById("online_block").className = "row g-3";
		document.getElementById("cash_amount").value = "0";
		document.getElementById("online_amount").value = "0";
		document.getElementById("online_way").value = "N/A";
		document.getElementById("bank_id_fk").value = "0";
		document.getElementById("bank_name").value = "";
		document.getElementById("online_date").value = "";
		document.getElementById("online_remark").value = "N/A";
		document.getElementById("online_way").value = "N/A";
	}
}

function loadPaymentMode() {

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