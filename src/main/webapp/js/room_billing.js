function findDuplicateItem() {
	return 0;
}


function checkExtraBedAmt() {
	/*alert("hi");*/
	var extraBed = document.getElementById("extra_bed").value;
	document.getElementById("extra_bad_amt_block").style.display = extraBed === 'Yes' ? "block" : "none";
	document.getElementById("extra_bed_amt").value = "0";
}




function validateCheckOutDate() {
	// Get the selected check-in and check-out date values
	var checkInDate = new Date(document.getElementById("check_in_time").value);
	var checkOutDate = new Date(document.getElementById("check_out_time").value);

	// Compare the check-in and check-out dates
	if (checkOutDate < checkInDate) {
		// Display an error message or take appropriate action
		alert("Check-out date cannot be earlier than check-in date.");
		// Reset the check-out date input to the check-in date
		document.getElementById("check_out_time").value = document
			.getElementById("check_in_time").value;
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
function getDays(onPageLoad) {
	//alert(onPageLoad);
	var checkInTime = new Date(document.getElementById("check_in_time").value).getTime();
	var checkOutTime = new Date(document.getElementById("check_out_time").value).getTime();
	var oneDay = 1000 * 60 * 60 * 24;
	let days = (checkOutTime - checkInTime) / oneDay;
	if (days.toString().includes(".")) {
		days = parseFloat(days).toFixed(0);
	}
	document.getElementById("no_of_days").value = days;
	if (document.getElementById("no_of_days").value ==0) {
		document.getElementById("no_of_days").value="1"
	}
	findTotalRoomRent(onPageLoad);
	checkExtraBed(onPageLoad)
	
}

function checkExtraBed(onPageLoad) {
	//alert("hi");
	let extraBed = document.getElementById("extra_bed").value;
		//alert(extraBed);
	if(extraBed =="No"){

	document.getElementById("extra_bed_amt").value = "0";
	document.getElementById("no_of_bed_days").value = "0";
	findTotalRoomRent(onPageLoad);
	}
	else{
		getBedDays(onPageLoad);
	}
}
function getBedDays(onPageLoad) {
	//alert(onPageLoad);
	var checkInTime = new Date(document.getElementById("check_in_time").value).getTime();
	var extraBedDays = new Date(document.getElementById("extra_bed_days").value).getTime();
	var oneDay = 1000 * 60 * 60 * 24;
	let days = (extraBedDays - checkInTime) / oneDay;
	if (days.toString().includes(".")) {
		days = parseFloat(days).toFixed(0);
	}
	document.getElementById("no_of_bed_days").value = days;
	
	findTotalRoomRent(onPageLoad);
	
}
function findTotalRoomRent(onPageLoad) {
	var roomRent = parseFloat(document.getElementById("room_rent").value);
	var extra_bed_amt = parseFloat(document.getElementById("extra_bed_amt").value);
    var days = parseFloat(document.getElementById("no_of_days").value);
    var bed_days = parseFloat(document.getElementById("no_of_bed_days").value);
	var totalRent = roomRent * days;
	var totalBedRent = extra_bed_amt * bed_days;
	document.getElementById("total_room_rent").value = totalRent.toFixed(0);
	document.getElementById("total_extra_bed_amt").value = totalBedRent.toFixed(0);
	findRoomGst(onPageLoad);
}

function findRoomGst(onPageLoad) {
	var gstPer = parseFloat(document.getElementById("room_gst_per").value);
	var extraBedAmt = parseFloat(document.getElementById("total_extra_bed_amt").value);
	var roomAmount = parseFloat(document.getElementById("total_room_rent").value);
	var totalAmount = extraBedAmt + roomAmount;
	var gstAmount = (totalAmount * gstPer) / 100;
	var withGstAmount = totalAmount + gstAmount;
	document.getElementById("room_gst_amt").value = gstAmount.toFixed(2);
	document.getElementById("total_room_amt_with_gst").value = Math.round(withGstAmount);
	findFoodGst(onPageLoad);
	findNetAmt(onPageLoad);
}

function findFoodGst(onPageLoad) {
	var gstPer = parseFloat(document.getElementById("food_gst_per").value);
	var amount = parseFloat(document.getElementById("food_amt").value);
	var gstAmount = (amount * gstPer) / 100;
	var withGstAmount = amount + gstAmount;
	document.getElementById("food_gst_amt").value = gstAmount.toFixed(2);
	document.getElementById("total_food_amt").value = Math.round(withGstAmount);
	findNetAmt(onPageLoad);
}

function findNetAmt(onPageLoad) {
	var totalRoomAmtWithGst = parseFloat(document.getElementById("total_room_amt_with_gst").value);
	var totalFoodAmt = parseFloat(document.getElementById("total_food_amt").value);
	var netAmt = totalRoomAmtWithGst + totalFoodAmt;
	document.getElementById("net_amt").value = Math.round(netAmt);
	//alert(netAmt);
	
	findFinalAmt(onPageLoad);
	
}



function findFinalAmt(onPageLoad) {
	var totalRoomAmtWithGst = parseFloat(document.getElementById("total_room_amt_with_gst").value);
	var totalFoodAmt = parseFloat(document.getElementById("total_food_amt").value);
	var netAmt = parseFloat(document.getElementById("net_amt").value);
	
	var advanceAmt = parseFloat(document.getElementById("advance_amt").value);
	var dis_amt_room = parseFloat(document.getElementById("dis_amt_room").value);
	 var dis_amt_food = parseFloat(document.getElementById("dis_amt_food").value);
	
	  var status = document.getElementById("page_status").value;


	var finalAmt = 0;
if (dis_amt_room > totalRoomAmtWithGst) {
	alert("Room Discount is greater than Total Room amount!");
	document.getElementById("dis_amt_room").value = 0;
	
}
else if (dis_amt_food > totalFoodAmt) {
	alert("Food Discount is greater than Total Food amount!");
	document.getElementById("dis_amt_food").value = 0;
	
}
	else if ((dis_amt_room+dis_amt_food) > netAmt) {
		alert("Food Discount is greater than Total Food amount!");
		disAmt = 0;
	}else{
	finalAmt = (netAmt - advanceAmt) - (dis_amt_room+dis_amt_food);
	document.getElementById("final_amt").value = Math.round(finalAmt);
	}
	if (status != "Complete") {
		//alert("hii");
		
		document.getElementById("cash_amount").value = Math.round(finalAmt);
	}
	
}



function paidAmountEvent() {
	var finalAmt = parseFloat(document.getElementById("final_amt").value);
	var cust_id_fk = Number(document.getElementById("customer_id_fk").value);
	var cash_amount = parseFloat(document.getElementById("cash_amount").value);
	var online_amount = parseFloat(document.getElementById("online_amount").value);

	//alert(cash_amount);
	var paid_amount = cash_amount + online_amount;


		if (paid_amount > finalAmt) {
		alert("Please put correct amount!");
		document.getElementById("cash_amount").value = 0;
		document.getElementById("online_amount").value = 0;
		document.getElementById("paid_amount").value = 0;
	}
	else {
		document.getElementById("paid_amount").value = paid_amount.toFixed(2);
		findRemainingAmount();

	}
	/*else {
		if (cust_id_fk > 0 || document.getElementById('regular').checked) {
			document.getElementById("paid_amount").value = paid_amount.toFixed(2);
			findRemainingAmount();
		} else {
			if (paid_amount == finalAmt) {
				document.getElementById("paid_amount").value = paid_amount.toFixed(2);
			} else {
				alert("Please Do Full Payment!");
				document.getElementById("cash_amount").value = Math.round(finalAmt);
				document.getElementById("online_amount").value = 0;
				document.getElementById("paid_amount").value = 0;
			}
		}
	}*/
}

function findRemainingAmount() {
	//alert("Hello");
	var cashAmount = parseFloat(document.getElementById("cash_amount").value);
	var onlineAmount = parseFloat(document.getElementById("online_amount").value);
	var finalAmt = parseFloat(document.getElementById("final_amt").value);
	var remainingAmount = finalAmt - cashAmount - onlineAmount;
	document.getElementById('remaining_amount').value = remainingAmount;
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




function checkCustAvail() {
	var custName = $('#name').val();
	var customer_id_fk = $('#customer_id_fk').val();
	var flag = "Customer";


	if (document.getElementById('regular').checked && customer_id_fk === 0 && custName !== "") {
		$.ajax({
			url: 'AjaxFolder/AjaxCheckNameAvail.jsp',
			data: 'name=' + custName + '&flag=' + flag,
			type: 'post',
			success: function(msg) {
				$('#name_avail_resposnse').html(msg);
				if (document.getElementById("check_name").value === 'true') {
					document.getElementById('name').value = "";
					$('#alert-container').html("");
					hideAlert();
				}
			}
		});
	}
}


function submitFormAjax() {
	var id = parseInt(document.getElementById("bill_id_fk").value);
	var cust_id_fk = parseInt(document.getElementById("customer_id_fk").value);
	var room_id_fk = parseInt(document.getElementById("room_id_fk").value);
	var user_id_fk = parseInt(document.getElementById("user_id_fk").value);
	var session_year = document.getElementById("session_year").value;
	var check_out_time = document.getElementById("check_out_time").value;
	var check_in_time = document.getElementById("check_in_time").value;
	var extra_bed_days = document.getElementById("extra_bed_days").value;
	var invoice_no = document.getElementById("invoice_no").value;
	var no_of_days = parseFloat(document.getElementById("no_of_days").value);
	var no_of_bed_days = parseFloat(document.getElementById("no_of_bed_days").value);
	var total_room_rent = parseFloat(document.getElementById("total_room_rent").value);
	var extra_bed_amt = parseFloat(document.getElementById("extra_bed_amt").value);
	var total_extra_bed_amt = parseFloat(document.getElementById("total_extra_bed_amt").value);
	var room_gst_per = parseFloat(document.getElementById("room_gst_per").value);
	var total_room_amt_with_gst = parseFloat(document.getElementById("total_room_amt_with_gst").value);
	var food_gst_per = parseFloat(document.getElementById("food_gst_per").value);
	var net_amt = parseFloat(document.getElementById("net_amt").value);
	var dis_amt_room = parseFloat(document.getElementById("dis_amt_room").value);
		var dis_amt_food = parseFloat(document.getElementById("dis_amt_food").value);
	var cust_old_due = parseFloat(document.getElementById("cust_old_due").value);
	var final_amt = parseFloat(document.getElementById("final_amt").value);
	var remark = document.getElementById("remark").value;
	var payment_mode = document.getElementById("payment_mode").value;
	var cash_amount = parseFloat(document.getElementById("cash_amount").value);
	var online_amount = parseFloat(document.getElementById("online_amount").value);
	var online_date = document.getElementById("online_date").value;
	var online_remark = document.getElementById("online_remark").value;
	var online_way = document.getElementById("online_way").value;
	var bank_id_fk = parseInt(document.getElementById("bank_id_fk").value);
	var regular = document.getElementById('regular').checked ? "Yes" : "No";
	var name = document.getElementById("name").value;
	var mobile_no = document.getElementById("mobile_no").value;
	var address = document.getElementById("address").value;
	var gst_no = document.getElementById("gst_no").value;
	var company_name = document.getElementById("company_name").value;
	var cust_dob = document.getElementById("dob").value;
	var cust_doa = document.getElementById("doa").value;
	var flag = document.getElementById("flag").value;
	if (cust_id_fk == 0 && !document.getElementById('regular').checked && (cash_amount+online_amount) != final_amt) {
		alert("Please Do Full Payment!");
		document.getElementById("cash_amount").value = 0;
		document.getElementById("online_amount").value = 0;
		document.getElementById("paid_amount").value = 0;
		return;
	}

	$.ajax({
		url: 'AjaxFolder/AjaxRoomBill.jsp',
		data: 'id=' + id +
			'&cust_id_fk=' + cust_id_fk +
			'&room_id_fk=' + room_id_fk +
			'&user_id_fk=' + user_id_fk +
			'&session_year=' + session_year +
			'&check_in_time=' + check_in_time +
			'&check_out_time=' + check_out_time +
			'&extra_bed_days=' + extra_bed_days +
			'&invoice_no=' + invoice_no +
			'&no_of_days=' + no_of_days +
			'&no_of_bed_days=' + no_of_bed_days +
			'&total_room_rent=' + total_room_rent +
			'&room_gst_per=' + room_gst_per +
			'&total_room_amt_with_gst=' + total_room_amt_with_gst +
			'&food_gst_per=' + food_gst_per +
			'&dis_amt_room=' + dis_amt_room +
			'&dis_amt_food=' + dis_amt_food +
			'&net_amt=' + net_amt +
			'&cust_old_due=' + cust_old_due +
			'&final_amt=' + final_amt +
			'&remark=' + remark +
			'&payment_mode=' + payment_mode +
			'&cash_amount=' + cash_amount +
			'&online_amount=' + online_amount +
			'&online_date=' + online_date +
			'&online_remark=' + online_remark +
			'&online_way=' + online_way +
			'&bank_id_fk=' + bank_id_fk +
			'&regular=' + regular +
			'&name=' + name +
			'&mobile_no=' + mobile_no +
			'&address=' + address +
			'&gst_no=' + gst_no +
			'&company_name=' + company_name +
			'&cust_dob=' + cust_dob +
			'&cust_doa=' + cust_doa +
			'&total_extra_bed_amt=' + total_extra_bed_amt +
			'&flag=' + flag + '&extra_bed_amt=' + extra_bed_amt,
		type: 'post',
		success: function(msg) {
			$('#ajax_response_design').html(msg);
			if ($("#form_sub_rs").val() === "true") {
				window.location.href = "print_room_bill.jsp?bill_id=" + id;
			} else {
				alert("Bill Not Updated");
			}
			$('#ajax_response_design').html("");
		}
	});
}

function checkItemAvail(item_code) {
	if (item_code !== "") {
		$.ajax({
			url: 'AjaxFolder/ItemCodeAvailAjax.jsp',
			data: 'item_code=' + item_code,
			type: 'post',
			success: function(msg) {
				$('#rev').html(msg);
				if (document.getElementById("check").value === "done") {
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
	var bill_id_fk = document.getElementById("bill_id_fk").value;
	var room_id = document.getElementById("room_id_fk").value;
	var item_id = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
	var item_code = document.getElementById("item_code").value;
	var item_qty = parseFloat(document.getElementById("item_qty").value);
	var item_rate = parseFloat(document.getElementById("item_rate").value);
	 var order_date = document.getElementById("order_date").value;

	if (item_code === "") {
		document.getElementById("item_code").focus();
	} else if (item_name === "") {
		document.getElementById("item_name").focus();
	} else if (item_rate === "" || item_rate < 0 || isNaN(item_rate)) {
		document.getElementById("item_rate").value = "0";
		document.getElementById("item_rate").focus();
	} else if (item_qty === "" || item_qty <= 0 || isNaN(item_qty)) {
		document.getElementById("item_qty").value = "1";
		document.getElementById("item_qty").focus();
	} else {
		$.ajax({
			url: 'AjaxFolder/AjaxOneRoomOrderItem.jsp',
			data: 'bill_id_fk=' + bill_id_fk +
				'&room_id=' + room_id +
				'&item_id=' + item_id +
				'&item_name=' + item_name +
				'&item_code=' + item_code +
				'&item_qty=' + item_qty +
				'&item_rate=' + item_rate +
                '&order_date=' + order_date,
			type: 'post',
			success: function(msg) {
				$('#ajax_response_design').html(msg);
				if (document.getElementById("item_result").value === "true") {
					$('#table_div_design').load(location.href + ' #table_div_design', function() {
						findTotal();
					});
					document.getElementById("item_code").focus();
				} else {
					alert("Item Not Inserted");
				}
				clearTableFormValues();
				$('#ajax_response_design').html("");
			}
		});
	}
}

function deleteItem(order_id) {
	$.ajax({
		url: 'AjaxFolder/AjaxOneRoomOrderItem.jsp',
		data: 'id=' + order_id,
		type: 'post',
		success: function(msg) {
			$('#ajax_response_design').html(msg);
			if (document.getElementById("item_result").value === "true") {
				
				$('#table_div_design').load(location.href + ' #table_div_design', function() {
					findTotal();
				});
				
			} else {
				alert("Item Not Deleted");
			}
		}
	});																																																																																																
}

function findTotal() {
	var totAmtArr = document.getElementsByName('Total_price_with_qty');
	var totQtyArr = document.getElementsByName('Item_qty');
	let total_basic_amt = 0.0;
	let total_qty = 0.0;

	for (let i = 0; i < totAmtArr.length; i++) {
		var amt = parseFloat(totAmtArr[i].value);
		if (!isNaN(amt))
			total_basic_amt += amt;

		var qty = parseFloat(totQtyArr[i].value);
		if (!isNaN(qty))
			total_qty += qty;
	}

	document.getElementById("total_tab_price").innerHTML = total_basic_amt.toFixed(2);
	document.getElementById("food_amt").value = total_basic_amt.toFixed(2);

	findFoodGst();
}

function clearTableFormValues() {
	document.getElementById("item_code").value = "";
	document.getElementById('item_name').value = "";
	document.getElementById("item_id").value = "";
	document.getElementById("item_rate").value = "";
	document.getElementById("item_qty").value = "1";
}

document.querySelector("#item_code").addEventListener("change",(e)=>{
	//document.querySelector("#item_qty").focus();
	document.querySelector("#item_qty").select();
});
