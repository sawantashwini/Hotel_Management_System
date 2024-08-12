function findCustInfoByName(name) {
	if (name != "") {
		$.ajax({
			url: 'AjaxFolder/AjaxCustomerInfoByNameForBIll.jsp',
			data: 'name=' + name,
			type: 'post',
			success: function(msg) {
				$('#ajax_response_design').html(msg);
				var check = document.getElementById("check_cust").value;
				if (check == "done") {
					setCustomerDetails();
				} else {
					alert("Please Insert Right Name");
				}
				findFinalAmount();
				$('#ajax_response_design').html("");
			}
		});
	}
}
function findCustInfoByMobileNo(mobile_no) {

	if (mobile_no != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxCustomerInfoByMobileNoForBIll.jsp',
			data: 'mobile_no=' + mobile_no,
			type: 'post',
			success: function(msg) {

				// alert(msg);

				$('#ajax_response_design').html(msg);

				var check = document.getElementById("check_cust").value;
				//alert(check);

				if (check == "done") {
						setCustomerDetails();

					//alert("hello");
				} else {

					alert("Please Insert Right Mobile No.");
				}
				checkDue();
				findFinalAmount();
				$('#ajax_response_design').html("");

			}
		});
	}
}
function findDuplicateItem() {
	return 0;
}
function checkDue() {

	let old_due_amount = parseFloat(document.getElementById("old_due_amount").value);
	if (old_due_amount > 0) {

		document.getElementById("due_block").style.display = "block";
	}
}

function setCustomerDetails() {
	document.getElementById('cust_name').value = document.getElementById('cust_name_val').value;

	document.getElementById('cust_mob_no').value = document.getElementById('cust_mob_no_val').value;

	document.getElementById('cust_address').value = document.getElementById('cust_address_val').value;

	document.getElementById('cust_id_fk').value = document.getElementById('cust_id_val').value;

	document.getElementById('old_due_amount').value = document.getElementById('cust_old_due_val').value;

	document.getElementById('company_name').value = document.getElementById('cust_company_val').value;
	document.getElementById('gst_no').value = document.getElementById('cust_gst_val').value;
	document.getElementById('dob').value = document.getElementById('cust_dob').value;
	document.getElementById('doa').value = document.getElementById('cust_doa').value;
	document.getElementById('cust_name').setAttribute('readonly', true);
	document.getElementById('cust_mob_no').setAttribute('readonly', true);
	document.getElementById('cust_address').setAttribute('readonly', true);
	document.getElementById('cust_id_fk').setAttribute('readonly', true);
	document.getElementById('company_name').setAttribute('readonly', true);
	document.getElementById('gst_no').setAttribute('readonly', true);
	document.getElementById('dob').setAttribute('readonly', true);
	document.getElementById('doa').setAttribute('readonly', true);

}

function findTotal() {
	var totAmtArr = document.getElementsByName('Total_price_with_qty');
	var total_basic_amt = 0.0;

	for (var i = 0; i < totAmtArr.length; i++) {
		var amt = parseFloat(totAmtArr[i].value);
		if (!isNaN(amt)) total_basic_amt += amt;
	}

	document.getElementById("total_tab_price").innerHTML = total_basic_amt.toFixed(2);
	$('#without_gst_amount').val(total_basic_amt.toFixed(2));
	checkGstStatusBlur();
}

function checkGstStatus() {
	var gst_status = document.getElementById("gst_status");
	var without_gst_amount = parseFloat(document.getElementById("without_gst_amount").value);
	var total_tab_price = parseFloat(document.getElementById("total_tab_price").innerHTML);
	var gst_per = gst_status.checked ? 5 : 0;
	var gst_amount = (without_gst_amount * gst_per) / 100;
	var with_gst_amount = total_tab_price + gst_amount;

	document.getElementById("gst_amount").value = gst_amount.toFixed(2);
	document.getElementById("with_gst_amount").value = with_gst_amount.toFixed(2);
	var with_gst_amount = parseFloat(document.getElementById("with_gst_amount").value);
	var discount_amount = parseFloat(document.getElementById("discount_amount").value);
	//var old_due_amount = parseFloat(document.getElementById("old_due_amount").value);
	var finalAmt = with_gst_amount - discount_amount;
	document.getElementById("final_amount").value = Math.round(finalAmt);
	if (document.getElementById("paid_amount").value == 0) {
		document.getElementById("cash_amount").value = Math.round(finalAmt);
	}
}

function checkGstStatusBlur() {
	var gst_status = document.getElementById("gst_status");
	var without_gst_amount = parseFloat(document.getElementById("without_gst_amount").value);
	var gst_per = gst_status.checked ? 5 : 0;
	var gst_amount = (without_gst_amount * gst_per) / 100;
	var with_gst_amount = without_gst_amount + gst_amount;

	document.getElementById("gst_amount").value = gst_amount.toFixed(2);
	document.getElementById("with_gst_amount").value = Math.round(with_gst_amount.toFixed(2));
	findFinalAmount();
}


function findFinalAmount() {
	var with_gst_amount = parseFloat(document.getElementById("with_gst_amount").value);
	var discount_amount = parseFloat(document.getElementById("discount_amount").value);
	//var old_due_amount = parseFloat(document.getElementById("old_due_amount").value);
	var status = document.getElementById("page_status").value;

	var finalAmt = with_gst_amount - discount_amount;


	if (discount_amount > with_gst_amount) {
		alert("Discount is greater thann Net amount!");
		discount_amount = 0; // Set disAmt to 0 if it's greater than netAmt
		finalAmt = with_gst_amount - discount_amount;
		document.getElementById("discount_amount").value = 0;
	}
	document.getElementById("final_amount").value = Math.round(finalAmt);

	console.log(status);

	if (status != "complete") {
		console.log("hello");

		document.getElementById("cash_amount").value = Math.round(finalAmt);
	}
	//paidAmountEvent();
	//paidForRegular();
}


function paidAmountEvent() {
	var final_amount = parseFloat(document.getElementById("final_amount").value);
	//var cust_id_fk = Number(document.getElementById("cust_id_fk").value);
	var cash_amount = parseFloat(document.getElementById("cash_amount").value);
	var online_amount = parseFloat(document.getElementById("online_amount").value);
	var paid_amount = cash_amount + online_amount;

	if (paid_amount > final_amount) {
		alert("Please put correct amount!");
		document.getElementById("cash_amount").value = 0;
		document.getElementById("online_amount").value = 0;
		document.getElementById("paid_amount").value = 0;
	}
	else {
		document.getElementById("paid_amount").value = paid_amount.toFixed(2);
		findRemainingAmount();

	}
}



function findRemainingAmount() {
	var cash_amount = parseFloat(document.getElementById("cash_amount").value);
	var online_amount = parseFloat(document.getElementById("online_amount").value);
	var final_amount = parseFloat(document.getElementById("final_amount").value);
	var remaining_amount = final_amount - cash_amount - online_amount;
	document.getElementById('remaining_amount').value = remaining_amount;
}


$(document).ready(function() {
	$('#regular').on('change', function() {
		var customer_id_fk = parseInt($('#customer_id_fk').val());
		var regularChecked = $(this).is(':checked');
		var remainingBlock = $('#remaining_block');

		if (customer_id_fk > 0 || regularChecked) {
			remainingBlock.show();
		} else {
			remainingBlock.hide();
		}
	});

	// Initialize the visibility of the remaining block
	$('#regular').trigger('change');
});


function submitFormAjax() {
	//alert("Helloo");
	var id = parseInt(document.getElementById("order_id").value);
	var status = document.getElementById("status").value;

	var cust_id_fk = document.getElementById("cust_id_fk").value;
	var cust_name = document.getElementById("cust_name").value;
	var cust_mob_no = document.getElementById("cust_mob_no").value;
	var cust_address = document.getElementById("cust_address").value;
	var cust_company_name = document.getElementById("company_name").value;
	var cust_gst_no = document.getElementById("gst_no").value;
	var cust_dob = document.getElementById("dob").value;
	var cust_doa = document.getElementById("doa").value;

	var bank_id_fk = parseInt(document.getElementById("bank_id_fk").value);
	var without_gst_amount = parseFloat(document
		.getElementById("without_gst_amount").value);
	var gst_amount = parseFloat(document.getElementById("gst_amount").value);
	var with_gst_amount = parseFloat(document
		.getElementById("with_gst_amount").value);
	var old_due_amount = parseFloat(document
		.getElementById("old_due_amount").value);
	var final_amount = parseFloat(document
		.getElementById("final_amount").value);
	var paid_amount = parseFloat(document.getElementById("paid_amount").value);
	var discount_amount = parseFloat(document
		.getElementById("discount_amount").value);
	// alert("1");
	var payment_mode = document.getElementById("payment_mode").value;
	//alert("2");
	var cash_amount = parseFloat(document.getElementById("cash_amount").value);
	var online_amount = parseFloat(document
		.getElementById("online_amount").value);
	var online_date = document.getElementById("online_date").value;
	var online_way = document.getElementById("online_way").value;
	var online_remark = document.getElementById("online_remark").value;
	var remark = document.getElementById("remark").value;
	var bill_date = document.getElementById("bill_date").value;
	var user_id_fk = document.getElementById("user_id_fk").value;
	var session_year = document.getElementById("session_year").value;
	var gst_status = document.getElementById('gst_status').checked ? "Yes" : "No";
	var regular = document.getElementById('regular').checked ? "Yes" : "No";

	var flag = document.getElementById("flag").value;

	if (cust_id_fk == 0 && !document.getElementById('regular').checked && (cash_amount + online_amount) != final_amount) {
		alert("Please Do Full Payment!");
		document.getElementById("cash_amount").value = 0;
		document.getElementById("online_amount").value = 0;
		document.getElementById("paid_amount").value = 0;
		return;
	}

	$.ajax({
		url: 'AjaxFolder/AjaxOrderBill.jsp',
		data: 'id=' + id + '&status=' + status + '&cust_id_fk='
			+ cust_id_fk + '&regular=' + regular + '&cust_name='
			+ cust_name + '&cust_mob_no=' + cust_mob_no
			+ '&cust_address=' + cust_address +'&cust_company_name=' + cust_company_name  
			+ '&cust_gst_no=' + cust_gst_no + '&cust_dob=' + cust_dob + '&cust_doa=' + cust_doa +'&bank_id_fk='
			+ bank_id_fk + '&without_gst_amount='
			+ without_gst_amount + '&gst_amount=' + gst_amount
			+ '&with_gst_amount=' + with_gst_amount
			+ '&old_due_amount=' + old_due_amount
			+ '&final_amount=' + final_amount + '&paid_amount='
			+ paid_amount + '&discount_amount=' + discount_amount
			+ '&payment_mode=' + payment_mode + '&cash_amount='
			+ cash_amount + '&online_amount=' + online_amount
			+ '&online_date=' + online_date + '&online_way='
			+ online_way + '&online_remark=' + online_remark
			+ '&gst_status=' + gst_status + '&bill_date='
			+ bill_date + '&user_id_fk=' + user_id_fk
			+ '&session_year=' + session_year + '&remark=' + remark
			+ '&flag=' + flag,
		type: 'post',
		success: function(msg) {

			$('#ajax_response_design').html(msg);
			var check = document.getElementById("item_result").value;
			if (check == "true") {
				window.location.href = "print_order_bill.jsp?order_id=" + id;
			} else {
				alert("Bill Not Updated");
			}
			clearTableFormValues();
			$('#ajax_response_design').html("");
		}
	});
}

function checkCustAvail() {
	var cust_name = $('#cust_name').val();
	var customer_id_fk = $('#cust_id_fk').val();
	var flag = "Customer";

	if (document.getElementById('regular').checked && customer_id_fk == 0 && cust_name != "") {
		$.ajax({
			url: 'AjaxFolder/AjaxCheckNameAvail.jsp',
			data: 'name=' + cust_name + '&flag=' + flag,
			type: 'post',
			success: function(msg) {
				$('#name_avail_resposnse').html(msg);
				var check = document.getElementById("check_name").value;
				if (check == 'true') {
					document.getElementById('cust_name').value = "";
					$('#alert-container').html("");
				}
			}
		});
	} else {
		//alert("Please Insert Name");
	}
}

function CustomerDesignVisibility() {
	var tableCheckbox = document.getElementById("table_bill");
	var custDiv = $('#cust_block');

	const customer_id_fk = document.getElementById("cust_id_fk").value;
	const nameInput = document.getElementById("cust_name");
	const mobileNoInput = document.getElementById("cust_mob_no");
	const addressInput = document.getElementById("cust_address");
	const oldDueInput = document.getElementById("old_due_amount");

	if (tableCheckbox.checked) {

		custDiv.hide();
		nameInput.readOnly = true;
		mobileNoInput.readOnly = true;
		addressInput.readOnly = true;
		oldDueInput.readOnly = true;
		nameInput.removeAttribute("required");
		mobileNoInput.removeAttribute("required");
		//addressInput.removeAttribute("required");

	} else {
		custDiv.show();
		nameInput.setAttribute("required", "true");
		mobileNoInput.setAttribute("required", "true");
		//addressInput.setAttribute("required", "true");
		nameInput.readOnly = false;
		mobileNoInput.readOnly = false;
		addressInput.readOnly = false;
	}
}

function removeCustDet() {
	document.getElementById('cust_name').value = "";
	document.getElementById('cust_mob_no').value = "";
	document.getElementById('search_name').value = "";
	document.getElementById('cust_address').value = "";
	document.getElementById('cust_id_fk').value = "0";
	document.getElementById('old_due_amount').value = "0";
		document.getElementById('company_name').value = "";
	document.getElementById('gst_no').value = "";
	document.getElementById('dob').value = "";
	document.getElementById('doa').value = "";

	
	document.getElementById('cust_name').removeAttribute('readonly');
	document.getElementById('cust_mob_no').removeAttribute('readonly');
	document.getElementById('cust_address').removeAttribute('readonly');
	document.getElementById('cust_id_fk').removeAttribute('readonly');
	document.getElementById('company_name').removeAttribute('readonly');
	document.getElementById('gst_no').removeAttribute('readonly');
	document.getElementById('dob').removeAttribute('readonly');
	document.getElementById('doa').removeAttribute('readonly');
	findFinalAmount();
}

function checkItemAvail(item_code) {
	if (item_code != "") {
		$.ajax({
			url: 'AjaxFolder/ItemCodeAvailAjax.jsp',
			data: 'item_code=' + item_code,
			type: 'post',
			success: function(msg) {
				$('#rev').html(msg);
				var check = document.getElementById("check").value;
				if (check == "done") {
					document.getElementById('item_id').value = document.getElementById('item_id_val').value;
					document.getElementById('item_name').value = document.getElementById('item_name_val').value;
					document.getElementById('item_rate').value = document.getElementById('item_price_val').value;
					document.getElementById('item_qty').focus();
				} else {
					clearTableFormValues();
					document.getElementById('icode' + index).focus();
				}
				$('#rev').html("");
			}
		});
	}
}

function insertItem() {
	var order_id = document.getElementById("order_id").value;
	var table_id = document.getElementById("table_id").value;
	var item_id = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
	var item_code = document.getElementById("item_code").value;
	var item_qty = parseFloat(document.getElementById("item_qty").value);
	var item_rate = parseFloat(document.getElementById("item_rate").value);

	if (item_code == "") {
		document.getElementById("item_code").focus();
	} else if (item_name == "") {
		document.getElementById("item_name").focus();
	} else if (item_rate == "" || item_rate < 0 || isNaN(item_rate)) {
		document.getElementById("item_rate").value = "0";
		document.getElementById("item_rate").focus();
	} else if (item_qty == "" || item_qty <= 0 || isNaN(item_qty)) {
		document.getElementById("item_qty").value = "1";
		document.getElementById("item_qty").focus();
	} else {
		$.ajax({
			url: 'AjaxFolder/AjaxOneOrderItem.jsp',
			data: 'table_id=' + table_id + '&order_id='
				+ order_id + '&item_id=' + item_id
				+ '&item_name=' + item_name + '&item_code='
				+ item_code + '&item_qty=' + item_qty
				+ '&item_rate=' + item_rate,
			type: 'post',
			success: function(msg) {
				$('#ajax_response_design').html(msg);
				var check = document.getElementById("item_result").value;
				if (check == "true") {
					document.getElementById("item_code").focus();
				} else {
					alert("Item Not Inserted");
				}
				clearTableFormValues();
				$('#ajax_response_design').html("");
				$('#table_design').load(location.href + " #table_design", function() { findTotal(); });
			}
		});
	}
}

function deleteItem(order_item_id) {
	$.ajax({
		url: 'AjaxFolder/AjaxOneOrderItem.jsp',
		data: 'id=' + order_item_id,
		type: 'post',
		success: function(msg) {
			$('#ajax_response_design').html(msg);
			var table_status = document.getElementById("free_table_val").value;
			$('#ajax_response_design').html("");
			if (table_status == "Yes" || table_status == "Free") {
			} else {
				alert("Item Not Deleted");
			}
			$('#ajax_response_design').html("");
			$('#table_design').load(location.href + " #table_design", function() { findTotal(); });
		}
	});
}

function clearTableFormValues() {
	document.getElementById("item_code").value = "";
	document.getElementById('item_name').value = "";
	document.getElementById("item_id").value = "";
	document.getElementById("item_rate").value = "";
	document.getElementById("item_qty").value = "1";
}
document.querySelector("#item_code").addEventListener("change",(e)=>{
	document.querySelector("#item_qty").select();
});
