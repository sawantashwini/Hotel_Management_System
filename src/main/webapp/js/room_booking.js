function checkValueRegular() {
	if (document.getElementById('regular_check').checked) {
		document.getElementById('regular').value = "Yes";
	}
	else {
		document.getElementById('regular').value = "No";
	}
}


function validateCheckOutDate() {
	// Get the selected check-in and check-out date values
	const checkInDate = new Date(document.getElementById("check_in_time").value);
	const checkOutDate = new Date(document.getElementById("check_out_time").value);

	// Compare the check-in and check-out dates
	if (checkOutDate < checkInDate) {
		// Display an error message or take appropriate action
		alert("Check-out date cannot be earlier than check-in date.");
		// Reset the check-out date input to the check-in date
		document.getElementById("check_out_time").value = document.getElementById("check_in_time").value;
	}
}
function validateExtraBedDays() {
	// Get the selected check-in and check-out date values
	const checkOutDate = new Date(document.getElementById("check_out_time").value);
	const extraBedDays = new Date(document.getElementById("extra_bed_days").value);

	// Compare the check-in and check-out dates
	if (extraBedDays > checkOutDate) {
		// Display an error message or take appropriate action
		alert("Extra Bed Days cannot be more than check-out date.");
		// Reset the check-out date input to the check-in date
		document.getElementById("extra_bed_days").value = document.getElementById("check_out_time").value;
	}
}
function checkCustAvail() {
	var cust_name = $('#cust_name').val();
	var customer_id_fk = $('#cust_id_fk').val();
	var flag = "Customer";


	if (document.getElementById('regular').checked
		&& customer_id_fk == 0 && cust_name != "") {


		$.ajax({

			url: 'AjaxFolder/AjaxCheckNameAvail.jsp',
			data: 'name=' + cust_name + '&flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#name_avail_resposnse').html(msg);

				var check = document
					.getElementById("check_name").value;
				// alert(check);
				if (check == 'true') {
					document.getElementById('cust_name').value = "";
					//alert("done");
					$('#alert-container').html("");
					//alert("done");

				}

			}
		});
	} else {
	}
}

function findRoomRent(room_type) {
	//alert("hii");
	var room_type = document.getElementById("room_type").value;
	var room_no = document.getElementById("room_no").value;

	if (room_type != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxFindRoomRent.jsp',
			data: 'Room_type=' + room_type + '&Room_no='
				+ room_no,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#find_room_rent').html(msg);

				var check_rent = document
					.getElementById("check_rent").value;
				//alert(check_item);

				if (check_rent == "done") {
					document.getElementById('room_rent').value = document
						.getElementById('room_rent_val').value;

				}

			}
		});

	} else {
		//alert("Please Insert Item");

	}

}

function findCustInfoByName(name) {

	if (name != "") {

		$
			.ajax({

				url: 'AjaxFolder/AjaxCustomerInfoByNameForRoom.jsp',
				data: 'name=' + name,
				type: 'post',
				success: function(msg) {

					// alert(msg);

					$('#result_info').html(msg);

					var check = document
						.getElementById("check_cust").value;
					//alert(check);

					if (check == "done") {

						document.getElementById('cust_name').value = document
							.getElementById('cust_name_val').value;

						document.getElementById('cust_mobile').value = document
							.getElementById('cust_mobile_val').value;

						document.getElementById('cust_address').value = document
							.getElementById('cust_address_val').value;

						document.getElementById('cust_gst_no').value = document
							.getElementById('cust_gst_no_val').value;

						document.getElementById('company_name').value = document
							.getElementById('company_name_val').value;

						document.getElementById('cust_id_fk').value = document
							.getElementById('cust_id_val').value;
						document.getElementById('dob').value = document.getElementById('cust_dob').value;
						document.getElementById('doa').value = document.getElementById('cust_doa').value;

						document.getElementById('cust_name')
							.setAttribute('readonly', true);
						document.getElementById('cust_mobile')
							.setAttribute('readonly', true);
						document.getElementById('cust_address')
							.setAttribute('readonly', true);
						document.getElementById('cust_gst_no')
							.setAttribute('readonly', true);
						document.getElementById('company_name')
							.setAttribute('readonly', true);
						document.getElementById('cust_id_fk')
							.setAttribute('readonly', true);
						document.getElementById('dob').setAttribute('readonly', true);
						document.getElementById('doa').setAttribute('readonly', true);


					} else {

						alert("Please Insert Right Name");
					}

				}
			});
	} else {

		//alert("Please Insert Name");

	}
}

function removeCustDet() {

	//alert("hii");

	document.getElementById('cust_name').value = "";
	document.getElementById('cust_mobile').value = "";
	document.getElementById('search_name').value = "";
	document.getElementById('cust_address').value = "";
	document.getElementById('cust_gst_no').value = "";
	document.getElementById('company_name').value = "";
	document.getElementById('cust_id_fk').value = "0";
	document.getElementById('dob').value = "";
	document.getElementById('doa').value = "";

	document.getElementById('cust_name').removeAttribute('readonly');
	document.getElementById('cust_mobile').removeAttribute('readonly');
	document.getElementById('cust_address').removeAttribute('readonly');
	document.getElementById('cust_id_fk').removeAttribute('readonly');
	document.getElementById('company_name').removeAttribute('readonly');
	document.getElementById('cust_gst_no').removeAttribute('readonly');
	document.getElementById('dob').removeAttribute('readonly');
	document.getElementById('doa').removeAttribute('readonly');
}


function changeExtraBed() {
	var extra_bed = document.getElementById("extra_bed").value;
	var extra_bed_amt_block = document.getElementById("extra_bed_amt_block");
	var extra_bed_days_block = document.getElementById("extra_bed_days_block");

	var extra_bed_amt = document.getElementById("extra_bed_amt");
	var extra_bed_days = document.getElementById("extra_bed_days");
	//alert(extra_bed);

	if (extra_bed == "Yes") {
		extra_bed_amt_block.classList.remove("d-none");
		extra_bed_days_block.classList.remove("d-none");
	} else {
		//alert("Hello");
		extra_bed_amt_block.classList.add("d-none");
		extra_bed_amt.value = "0";
		extra_bed_days_block.classList.add("d-none");
		extra_bed_days.value = "0";
	}

}


function paidAmountEvent() {
	var cash_amount = Number(document.getElementById("cash_amount").value);
	var online_amount = Number(document.getElementById("online_amount").value);

	var paid_amount = Number(cash_amount + online_amount);
	//alert(paid_amount);
	document.getElementById("paid_amount").value = paid_amount.toFixed(2);

}


function FindAdvancedAmount() {
	//alert("hii");
	if (document.getElementById('both_mode').checked) {

		var cash_amount = parseFloat(document.getElementById("cash_amount").value);
		var online_amount = parseFloat(document.getElementById("online_amount").value);

		Advanced_amt = cash_amount + online_amount;
		document.getElementById("advanced_amt").value = Advanced_amt;
		document.getElementById('advanced_amt').setAttribute('readonly', true);
	}
}


function changeExtraBed(extra_bed) {
	var extra_bed_amt_block = document.getElementById("extra_bed_amt_block");
	var extra_bed_days_block = document.getElementById("extra_bed_days_block");

	var extra_bed_amt = document.getElementById("extra_bed_amt");
	var extra_bed_days = document.getElementById("extra_bed_days");
	//alert(extra_bed);


	if (extra_bed == "Yes") {
		extra_bed_amt_block.classList.remove("d-none");
		extra_bed_days_block.classList.remove("d-none");
	} else {
		//alert("Hello");
		extra_bed_amt_block.classList.add("d-none");
		extra_bed_amt.value = "0";
		extra_bed_days_block.classList.add("d-none");
		extra_bed_days.value = "0";
	}

}

