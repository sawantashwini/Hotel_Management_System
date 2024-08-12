var preWorkExpIndex = 1;

function autoFocus() {
	var item_code = document.getElementById("item_code").value;
	var item_name = document.getElementById('item_name').value;
	var item_id = document.getElementById("item_id").value;
	var item_rate = parseFloat(document.getElementById("item_rate").value);


	//var warranty = document.getElementById("warranty").value;

	//alert(dis_amt);

	if (item_code == "") {
		//alert("Select any Name or Insert  value");

		clearTableFormValues();
	} else {

		var applicationTable = document.getElementById("tableScroll");

		var newRow = applicationTable.insertRow(preWorkExpIndex - 1);

		// alert(preWorkExpIndex);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_code.toUpperCase() + '<input id="item_code'
			+ preWorkExpIndex + '" type="hidden" name="Item_code" value="'
			+ item_code + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);
		newCell.innerHTML = item_name + '<input id="item_name'
			+ preWorkExpIndex + '" type="hidden" name="Item_name" value="'
			+ item_name + '"><input id="item_id' + preWorkExpIndex
			+ '" type="hidden" name="Item_id" value="' + item_id
			+ '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);

		newCell.innerHTML = item_rate + '<input id="item_rate'
			+ preWorkExpIndex
			+ '" type="hidden" name="Item_rate" value="'
			+ item_rate + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(3);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		clearTableFormValues();
		document.getElementById("item_code").focus();

		preWorkExpIndex++;

		//findTotal();

		//findTotalQty();

	}
}

function deleteRow(tableObjId, i) {


	var tblObj = document.getElementById(tableObjId);
	preWorkExpIndex--;
	tblObj.deleteRow(i - 1);
	manageRow(i);
}



function manageRow(rowNo) {

	var applicationTable = document.getElementById("tableScroll");
	var tblObj = document.getElementById(applicationTable.id);

	for (var index = rowNo; index <= tblObj.rows.length; index++) {
		j = index + 1;

		var item_code = document.getElementById("item_code" + j).value;
		var item_name = document.getElementById("item_name" + j).value;
		var item_id = document.getElementById("item_id" + j).value;
		var item_rate = document.getElementById("item_rate" + j).value;

		var newRow = applicationTable.insertRow(index - 1);
		// alert(index);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_code.toUpperCase() + '<input id="item_code'
			+ index + '" type="hidden" name="Item_code" value="'
			+ item_code + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);
		newCell.innerHTML = item_name + '<input id="item_name'
			+ index + '" type="hidden" name="Item_name" value="'
			+ item_name + '"><input id="item_id' + index
			+ '" type="hidden" name="Item_id" value="' + item_id
			+ '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);

		newCell.innerHTML = item_rate + '<input id="item_rate'
			+ index
			+ '" type="hidden" name="Item_rate" value="'
			+ item_rate + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(3);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		tblObj.deleteRow(index);
	}
}

function checkItemAvail(item_code) {
	//alert("Hello");
	if (item_code != "") {

		$
			.ajax({

				url: 'AjaxFolder/ItemCodeAvailAjax.jsp',
				data: 'item_code=' + item_code,
				type: 'post',
				success: function(msg) {

					//alert(msg);

					$('#rev').html(msg);

					var check = document.getElementById("check").value;
					//alert(check);

					if (check == "done") {

						//var duplicate_value = findDuplicateCode();

						//if (duplicate_value == 0) {

						document.getElementById('item_id').value = document
							.getElementById('item_id_val').value;



						document.getElementById('item_name').value = document
							.getElementById('item_name_val').value;
						document.getElementById('item_rate').value = document
							.getElementById('item_price_val').value;


						document.getElementById('item_qty').focus();



						//}	
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

	var table_id = Number(document.getElementById("table_id").value);
	var item_id = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
	var item_code = document.getElementById("item_code").value;
	var item_qty = parseFloat(document.getElementById("item_qty").value);
	var item_rate = parseFloat(document.getElementById("item_rate").value);

	//alert("Hello");
	//return ;
	if (item_code == "") {

		document.getElementById("item_code").focus();

	} else if (item_name == "") {
		document.getElementById("item_name").focus();
	}
	else if (item_rate == "" || item_rate < 0 || isNaN(item_rate)) {
		//alert("Insert Discount Amount");
		document.getElementById("item_rate").value = "0";
		document.getElementById("item_rate").focus();
	}

	else if (item_qty == "" || item_qty <= 0 || isNaN(item_qty)) {
		//alert("Insert qty value");
		document.getElementById("item_qty").value = "1";
		document.getElementById("item_qty").focus();
	}
	else if (table_id === 0) {
		alert("Please Open Any Table!");
	}
	else {

		$.ajax({
			url: 'AjaxFolder/AjaxOneOrderItem.jsp',
			data: 'table_id=' + table_id +
				'&item_id=' + item_id +
				'&item_name=' + item_name +
				'&item_code=' + item_code +
				'&item_qty=' + item_qty +
				'&item_rate=' + item_rate,
			type: 'post',
			success: function(msg) {
				$('#ajax_response_design').html(msg);
				var check = document.getElementById("item_result").value;
				if (check == "true") {
					openTable(table_id);
					document.getElementById("item_code").focus();
				} else {
					alert("Item Not Inserted");
				}
				clearTableFormValues();
				$('#ajax_response_design').html("");
				changeClass("table-btn-" + table_id, "book-table", "free-table");

			}
		});

	}

}

function deleteItem(order_id) {
	var table_id = document.getElementById("table_id").value;

	$.ajax({
		url: 'AjaxFolder/AjaxOneOrderItem.jsp',
		data: 'id=' + order_id,
		type: 'post',
		success: function(msg) {
			//alert(msg);
			$('#ajax_response_design').html(msg);
			var table_status = document.getElementById("free_table_val").value;
			if (table_status == "Yes" || table_status == "Free") {
				//alert("change table design");
				openTable(table_id);
			} else {
				alert("Item Not Deleted");
			}


			$('#ajax_response_design').html("");
			//alert(table_status);
			if (table_status == "Free") {
				//alert("change table btn class");
				changeClass("table-btn-" + table_id, "free-table", "book-table");

			}


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

function checkCheckbox() {
	var checkbox = document.getElementById("table-switch");

	if (checkbox.checked) {
		console.log("Checkbox is checked");
	} else {
		console.log("Checkbox is not checked");
	}
}

function findTotal() {
	//alert("Start");
	var totAmtArr = document.getElementsByName('Total_price_with_qty');
	var totQtyArr = document.getElementsByName('Item_qty');
	var total_basic_amt = 0.0;
	var total_qty = 0.0;
	//alert("Start 2");

	for (var i = 0; i < totAmtArr.length; i++) {
		var amt = parseFloat(totAmtArr[i].value);
		if (!isNaN(amt))
			total_basic_amt += amt;

		var qty = parseFloat(totQtyArr[i].value);
		if (!isNaN(qty))
			total_qty += qty;
	}

	document.getElementById("total_tab_price").innerHTML = total_basic_amt.toFixed(2);
	//document.getElementById("total_tab_qty").innerHTML = total_qty.toFixed(2);
	$('#without_gst_amount').val(total_basic_amt.toFixed(2));
	$('#without_gst_amount1').val(total_basic_amt.toFixed(2));
	checkGstStatus();
}





function insertOrderWithoutBilling() {
	//alert("Hello");
	var id = parseInt(document.getElementById("order_id").value);
	//alert("id: " + id);
	var table_id = document.getElementById("table_id").value;
	//alert("table_id: " + table_id);
	var without_gst_amount = parseFloat(document.getElementById("without_gst_amount").value);
	//alert("without_gst_amount: " + without_gst_amount);
	var bill_date = document.getElementById("bill_date").value;
	//alert("bill_date: " + bill_date);
	var session_year = document.getElementById("session_year").value;
	//alert("session_year: " + session_year);
	var user_id_fk = parseInt(document.getElementById("user_id_fk").value);
	//alert("user_id_fk: " + user_id_fk);
	var manager_name = document.getElementById("manager_name").value;
	//alert("manager_name: " + manager_name);

	//alert("id: " + id);

	$.ajax({
		url: 'AjaxFolder/AjaxOrderBill.jsp',
		data: 'id=' + id +
			'&table_id=' + table_id +
			'&without_gst_amount=' + without_gst_amount +
			'&bill_date=' + bill_date +
			'&session_year=' + session_year +
			'&user_id_fk=' + user_id_fk +
			'&manager_name=' + manager_name,
		type: 'post',
		success: function(msg) {
			//alert(msg);
			$('#ajax_response_design').html(msg);
			var check = document.getElementById("item_result").value;
			var order_id_val = document.getElementById("order_id_val").value;
			if (check == "true") {
				changeClass("table-btn-" + table_id, "free-table", "book-table");
				openTable(table_id);

				window.location.href = "print_order_bill.jsp?order_id=" + order_id_val;
			} else {
				alert("Item Not Inserted");
			}
			clearTableFormValues();
			$('#ajax_response_design').html("");
		}
	});
}



function changeClass(div_id, add_class, remove_class) {
	var div = document.getElementById(div_id);

	if (div) {


		if (add_class) {
			div.classList.add(add_class);
		}

		if (remove_class) {
			div.classList.remove(remove_class);
		}


	}
}




function SubmitBtmVisibility() {



	var items = document.getElementsByName("Item_id");
	//alert(items.length);

	var billingCheckbox = document.getElementById("billing");
	var directCheckbox = document.getElementById("direct_submit");

	var billingDesing = document.getElementById("billing-design");
	var directDesign = document.getElementById("direct-design");

	if (items.length != 0) {


		if (billingCheckbox.checked) {
			billingDesing.classList.remove("d-none");
			directDesign.classList.add("d-none");
		} else if (directCheckbox.checked) {
			billingDesing.classList.add("d-none");
			directDesign.classList.remove("d-none");

			document.getElementById("gst_status").checked = true;
			checkGstStatus();
		}
	}

	else {
		alert("No items found. Please add items before Billing.");
		clearBillingDesign();
	}
}


function clearBillingDesign() {

	var billingCheckbox = document.getElementById("billing");
	var directCheckbox = document.getElementById("direct_submit");
	var billingDesing = document.getElementById("billing-design");
	var directDesign = document.getElementById("direct-design");

	billingDesing.classList.add("d-none");
	directDesign.classList.add("d-none");
	billingCheckbox.checked = false;
	directCheckbox.checked = false;
	//alert("done");
}
function changeTable(new_table_id) {



	var old_table_id = document.getElementById("table_id").value;
	var checkbox = document.getElementById("table-switch");
	var old_button = document.getElementById("table-btn-" + old_table_id);
	var new_button = document.getElementById("table-btn-" + new_table_id);

	if (old_table_id != new_table_id) {
		if (checkbox.checked) {
			// Checkbox is checked 


			if (new_button.classList.contains("free-table") && old_button.classList.contains("book-table")) {
				//The new button has the class "free-table"  and old button has the class "book-table"
				var manager_name = document.getElementById("manager_name").value;
				$.ajax({
					url: 'AjaxFolder/AjaxSwitchTable.jsp',
					data: 'old_table_id=' + old_table_id
						+ '&new_table_id=' + new_table_id
						+ '&manager_name=' + manager_name,
					type: 'post',
					success: function(msg) {
						$('#ajax_response_design').html(msg);

						var result = document.getElementById("switch_status").value;
						$('#ajax_response_design').html("");

						//alert(result);

						if (result == 'true') {
							//alert("hello");
							checkbox.checked = false;
							changeClass(new_button.id, "book-table", "free-table");
							changeClass(old_button.id, "free-table", "book-table");
							openTable(new_table_id);
						}
					}
				});


			}
			else if (!old_button.classList.contains("free-table")) {
				alert("This Table is Already Booked");
			}
		}

		else {

			openTable(new_table_id);

		}
	}
}


function openTable(new_table_id) {
	var old_table_id = document.getElementById("table_id").value;
	var old_button = document.getElementById("table-btn-" + old_table_id);
	var new_button = document.getElementById("table-btn-" + new_table_id);

	$.ajax({
		url: 'AjaxDesign/GetMenuTableItemDesign.jsp',
		data: 'table_id=' + new_table_id,
		type: 'post',
		success: function(msg) {
			$('#tbody_design').html(msg);
			$('#table_id').val(new_table_id);
			var table_name = document.getElementById("table_name_val").value;
			var manager_name = document.getElementById("manager_name_val").value;
			$('#dv_tb_name').html(table_name);
			$('#table_name').val(table_name);
			$('#manager_name').val(manager_name);
			findTotal();
			clearBillingDesign();
		}
	});

	if (old_table_id != 0) {
		changeClass(old_button.id, "", "active");
	}
	changeClass(new_button.id, "active", "");


	document.getElementById('item_id').value = 0;
	document.getElementById('item_code').value = "";
	document.getElementById('item_rate').value = 0;
	document.getElementById('item_qty').value = 1;
}


function UpdateTableManager() {
	var table_id = parseInt(document.getElementById("table_id").value);
	var manager_name = document.getElementById("manager_name").value;
	//alert(table_id+" "+manager_name);
	$.ajax({
		url: 'AjaxFolder/AjaxUpdateTableManager.jsp',
		data: 'table_id=' + table_id
			+ '&manager_name=' + manager_name,
		type: 'post',
		type: 'post',
		success: function(msg) {

			//alert(msg);

			if (msg === "true") {
				//Success
				//alert("Table manager updated successfully.");
			} else {
				// Error
				//alert("Failed to update table manager.");
			}
		},
		error: function(xhr, status, error) {
			console.log(error);
		}
	});
}




function checkGstStatus() {
	var gst_status = document.getElementById("gst_status");
	var without_gst_amount = parseFloat(document.getElementById("without_gst_amount").value);
	var gst_per = 0;

	if (gst_status.checked == true) {
		gst_per = 5;
	}
	var gst_amount = (without_gst_amount * gst_per) / 100;
	var with_gst_amount = without_gst_amount + gst_amount;
	document.getElementById("gst_amount").value = gst_amount.toFixed(2);
	document.getElementById("with_gst_amount").value = Math.round(with_gst_amount.toFixed(2));
	findFinalAmount();
}

function findFinalAmount() {
	// Get input values and convert them to floating-point numbers
	var with_gst_amount = parseFloat(document.getElementById("with_gst_amount").value);
	var discount_amount = parseFloat(document.getElementById("discount_amount").value);
	//var old_due_amount = parseFloat(document.getElementById("old_due_amount").value);

	var finalAmt = with_gst_amount - discount_amount;


	if (discount_amount > with_gst_amount) {
		alert("Discount is greater thann Net amount!");
		discount_amount = 0; // Set disAmt to 0 if it's greater than netAmt
		finalAmt = with_gst_amount - discount_amount;
		document.getElementById("discount_amount").value = 0;
	}
	document.getElementById("final_amount").value = Math.round(finalAmt);
	document.getElementById("cash_amount").value = Math.round(finalAmt);
	document.getElementById('remaining_amount').value = 0;
	checkPaidAmountEvent();
}

function checkPaidAmountEvent() {
	var final_amount = parseFloat(document.getElementById("final_amount").value);
	var cust_id_fk = Number(document.getElementById("cust_id_fk").value);
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

	let cash_amount = parseFloat(document.getElementById("cash_amount").value);
	let online_amount = parseFloat(document.getElementById("online_amount").value);
	let final_amount = parseFloat(document.getElementById("final_amount").value);
	//alert(online_amount);
	remaining_amount = parseFloat(final_amount - cash_amount - online_amount);

	document.getElementById('remaining_amount').value = remaining_amount;
}

function findCustInfoByName(name) {

	if (name != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxCustomerInfoByNameForBIll.jsp',
			data: 'name=' + name,
			type: 'post',
			success: function(msg) {

				// alert(msg);

				$('#ajax_response_design').html(msg);

				var check = document.getElementById("check_cust").value;
				//alert(check);

				if (check == "done") {
					var remainingBlock = $('#remaining_block');
					//alert(remainingBlock);
					remainingBlock.show();
					findRemainingAmount();

					document.getElementById('cust_name').value = document.getElementById('cust_name_val').value;

					document.getElementById('cust_mob_no').value = document.getElementById('cust_mob_no_val').value;

					document.getElementById('cust_address').value = document.getElementById('cust_address_val').value;

					document.getElementById('cust_id_fk').value = document.getElementById('cust_id_val').value;

					document.getElementById('old_due_amount').value = document.getElementById('cust_old_due_val').value;

					document.getElementById('company_name').value = document.getElementById('cust_company_val').value;
					document.getElementById('gst_no').value = document.getElementById('cust_gst_val').value;
					//alert("hello");
					document.getElementById('cust_name').setAttribute('readonly', true);
					document.getElementById('cust_mob_no').setAttribute('readonly', true);
					document.getElementById('cust_address').setAttribute('readonly', true);
					document.getElementById('cust_id_fk').setAttribute('readonly', true);
					document.getElementById('company_name').setAttribute('readonly', true);
					document.getElementById('gst_no').setAttribute('readonly', true);

					//alert("hello");
				} else {

					alert("Please Insert Right Name");
				}
				checkDue();
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
					var remainingBlock = $('#remaining_block');
					//alert(remainingBlock);
					remainingBlock.show();
					findRemainingAmount();

					document.getElementById('cust_name').value = document.getElementById('cust_name_val').value;

					document.getElementById('cust_mob_no').value = document.getElementById('cust_mob_no_val').value;

					document.getElementById('cust_address').value = document.getElementById('cust_address_val').value;

					document.getElementById('cust_id_fk').value = document.getElementById('cust_id_val').value;

					document.getElementById('old_due_amount').value = document.getElementById('cust_old_due_val').value;

					document.getElementById('company_name').value = document.getElementById('cust_company_val').value;
					document.getElementById('gst_no').value = document.getElementById('cust_gst_val').value;
					document.getElementById('dob').value = document.getElementById('cust_dob').value;
					document.getElementById('doa').value = document.getElementById('cust_doa').value;
					//alert("hello");
					document.getElementById('cust_name').setAttribute('readonly', true);
					document.getElementById('cust_mob_no').setAttribute('readonly', true);
					document.getElementById('cust_address').setAttribute('readonly', true);
					document.getElementById('cust_id_fk').setAttribute('readonly', true);
					document.getElementById('company_name').setAttribute('readonly', true);
					document.getElementById('gst_no').setAttribute('readonly', true);
					document.getElementById('dob').setAttribute('readonly', true);
					document.getElementById('doa').setAttribute('readonly', true);

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

function checkDue() {

	let old_due_amount = parseFloat(document.getElementById("old_due_amount").value);
	if (old_due_amount > 0) {

		document.getElementById("due_block").style.display = "block";
	}
}
function removeCustDet() {

	document.getElementById('cust_name').removeAttribute('readonly');
	document.getElementById('cust_mob_no').removeAttribute('readonly');

	document.getElementById('cust_address').removeAttribute('readonly');
	document.getElementById('cust_id_fk').removeAttribute('readonly');
	document.getElementById('company_name').removeAttribute('readonly');
	document.getElementById('gst_no').removeAttribute('readonly');
	document.getElementById('dob').removeAttribute('readonly');
	document.getElementById('doa').removeAttribute('readonly');

	document.getElementById('search_name').value = "";
	document.getElementById('cust_name').value = "";
	document.getElementById('cust_mob_no').value = "";
	document.getElementById('cust_address').value = "";
	document.getElementById('cust_id_fk').value = "0";
	document.getElementById('company_name').value = "";
	document.getElementById('gst_no').value = "";
	document.getElementById('dob').value = "";
	document.getElementById('doa').value = "";
	document.getElementById('old_due_amount').value = "0";
	document.getElementById("due_block").style.display = "none";

	findFinalAmount();
}

function CustomerDesignVisibility() {
	var tableCheckbox = document.getElementById("table_bill");
	var custDiv = document.getElementById("cust_block");

	const customer_id_fk = document.getElementById("cust_id_fk").value;
	const nameInput = document.getElementById("cust_name");
	const mobileNoInput = document.getElementById("cust_mob_no");
	const addressInput = document.getElementById("cust_address");
	const oldDueInput = document.getElementById("old_due_amount");

	var regularChecked = $(this).is(':checked');
	var remainingBlock = $('#remaining_block');

	if (tableCheckbox.checked) {
		custDiv.classList.add("d-none");

		nameInput.readOnly = true;
		mobileNoInput.readOnly = true;
		addressInput.readOnly = true;
		remainingBlock.hide();

		nameInput.setAttribute("required", "false");
		mobileNoInput.setAttribute("required", "false");
		addressInput.setAttribute("required", "false");

	} else {
		custDiv.classList.remove("d-none");

		nameInput.setAttribute("required", "true");
		mobileNoInput.setAttribute("required", "true");
		addressInput.setAttribute("required", "true");

		if (customer_id_fk > 0 || regularChecked) {
			remainingBlock.show();
			document.getElementById("remaining_amount").value = 0.0;
		} else {
			remainingBlock.hide();
		}
		removeCustDet();


	}
}

function submitFormAjax() {
	var id = parseInt(document.getElementById("order_id").value);
	var table_id = document.getElementById("table_id").value;
	//alert("table_id:" + table_id);
	var without_gst_amount = parseFloat(document.getElementById("without_gst_amount").value);
	//alert("without_gst_amount:" + without_gst_amount);
	var manager_name = document.getElementById("manager_name").value;
	//alert("manager_name:" + manager_name);

	var cust_id_fk = parseInt(document.getElementById("cust_id_fk").value);
	//alert("cust_id_fk:" + cust_id_fk);
	var regular = document.getElementById('regular').checked ? "Yes" : "No";
	//alert("regular:" + regular);
	var cust_name = document.getElementById("cust_name").value;
	//alert("cust_name:" + cust_name);
	var cust_mob_no = document.getElementById("cust_mob_no").value;
	//alert("cust_mob_no:" + cust_mob_no);
	var cust_address = document.getElementById("cust_address").value;
	//alert("cust_address:" + cust_address);
	var cust_company_name = document.getElementById("company_name").value;
	//alert(company_name);
	var cust_gst_no = document.getElementById("gst_no").value;
	var cust_dob = document.getElementById("dob").value;
	var cust_doa = document.getElementById("doa").value;
	var bank_id_fk = parseInt(document.getElementById("bank_id_fk").value);
	//alert("bank_id_fk:" + bank_id_fk);
	var gst_amount = parseFloat(document.getElementById("gst_amount").value);
	//alert("gst_amount:" + gst_amount);
	var with_gst_amount = parseFloat(document.getElementById("with_gst_amount").value);
	//alert("with_gst_amount:" + with_gst_amount);
	var old_due_amount = parseFloat(document.getElementById("old_due_amount").value);
	//alert("old_due_amount:" + old_due_amount);
	var final_amount = parseFloat(document.getElementById("final_amount").value);
	//alert("final_amount:" + final_amount);
	var paid_amount = parseFloat(document.getElementById("paid_amount").value);
	//alert("paid_amount:" + paid_amount);
	var discount_amount = parseFloat(document.getElementById("discount_amount").value);
	//alert("discount_amount:" + discount_amount);

	var payment_mode = document.getElementById("payment_mode").value;
	//alert("payment_mode:" + payment_mode);
	var cash_amount = parseFloat(document.getElementById("cash_amount").value);
	//alert("cash_amount:" + cash_amount);
	var online_amount = parseFloat(document.getElementById("online_amount").value);
	//alert("online_amount:" + online_amount);
	var online_date = document.getElementById("online_date").value;
	//alert("online_date:" + online_date);
	var online_way = document.getElementById("online_way").value;
	//alert("online_way:" + online_way);
	var online_remark = document.getElementById("online_remark").value;
	//alert("online_remark:" + online_remark);
	var remark = document.getElementById("remark").value;
	//alert("remark:" + remark);
	var bill_date = document.getElementById("bill_date").value;
	//alert("bill_date:" + bill_date);
	var user_id_fk = document.getElementById("user_id_fk").value;
	//alert("user_id_fk:" + user_id_fk);
	var session_year = document.getElementById("session_year").value;
	//alert("id:" + id);

	var gst_status = document.getElementById('gst_status').checked ? 'Yes' : 'No';
	//alert("gst_status:" + gst_status);

	var flag = "direct bill";


	if (cust_id_fk == 0 && !document.getElementById('regular').checked && paid_amount != final_amount) {
		alert("Please Do Full Payment!");
		document.getElementById("cash_amount").value = 0;
		document.getElementById("online_amount").value = 0;
		document.getElementById("paid_amount").value = 0;
		return;
	}


	$.ajax({
		url: 'AjaxFolder/AjaxOrderBill.jsp',
		data: {
			id: id,
			cust_id_fk: cust_id_fk,
			table_id: table_id,
			without_gst_amount: without_gst_amount,
			manager_name: manager_name,
			regular: regular,
			cust_name: cust_name,
			cust_mob_no: cust_mob_no,
			cust_address: cust_address,
			cust_company_name: cust_company_name,
			cust_gst_no: cust_gst_no,
			cust_dob: cust_dob,
			cust_doa: cust_doa,
			bank_id_fk: bank_id_fk,
			gst_amount: gst_amount,
			with_gst_amount: with_gst_amount,
			old_due_amount: old_due_amount,
			final_amount: final_amount,
			paid_amount: paid_amount,
			discount_amount: discount_amount,
			payment_mode: payment_mode,
			cash_amount: cash_amount,
			online_amount: online_amount,
			online_date: online_date,
			online_way: online_way,
			online_remark: online_remark,
			gst_status: gst_status,
			bill_date: bill_date,
			user_id_fk: user_id_fk,
			session_year: session_year,
			remark: remark,
			flag: flag
		},
		type: 'post',
		success: function(msg) {
			//alert(msg);
			$('#ajax_response_design').html(msg);
			var check = document.getElementById("item_result").value;
			var order_id_val = document.getElementById("order_id_val").value;
			if (check === "true") {
				window.location.href = "print_order_bill.jsp?order_id=" + order_id_val;
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
			data: 'name=' + cust_name
				+ '&flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#name_avail_resposnse').html(msg);

				var check = document.getElementById("check_name").value;
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

		//alert("Please Insert Name");

	}
}
function checkCustMobileAvail() {
	var cust_mobile_no = $('#cust_mobile_no').val();
	var customer_id_fk = $('#cust_id_fk').val();
	var flag = "Customer";

	if (document.getElementById('regular').checked && customer_id_fk == 0 && cust_mobile_no != "") {


		$.ajax({

			url: 'AjaxFolder/AjaxCheckNameAvail.jsp',
			data: 'mobile_no=' + cust_mobile_no
				+ '&flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#name_avail_resposnse').html(msg);

				var check = document.getElementById("check_name").value;
				// alert(check);
				if (check == 'true') {
					document.getElementById('cust_mobile_no').value = "";
					//alert("done");
					$('#alert-container').html("");
					//alert("done");

				}

			}
		});
	} else {

		//alert("Please Insert Name");

	}
}
$(document).ready(function() {
	$('#regular').on('change', function() {
		var customer_id_fk = parseInt($('#cust_id_fk').val());
		var regularChecked = $(this).is(':checked');
		var remainingBlock = $('#remaining_block');

		if (customer_id_fk > 0 || regularChecked) {
			remainingBlock.show();
			findRemainingAmount();
		} else {
			remainingBlock.hide();
		}
	});
	// Initialize the visibility of the remaining block
	$('#regular').trigger('change');
});

function findDuplicateItem() {
	return 0;
}




function checkCodeAvail(item_name) {

	var item_name = document.getElementById("item_name").value;

	if (item_name != "") {
		//alert(item_name);
		$.ajax({
			url: 'AjaxFolder/AjaxItemCodeAvail.jsp',
			data: 'item_name=' + item_name,
			type: 'post',
			success: function(msg) {
				//alert(msg);

				$('#ingredients_item').html(msg);

				var check_item = document.getElementById("check_item").value;
				//alert(check_item);

				if (check_item == "done") {
					document.getElementById('item_id').value = document
						.getElementById('item_id_val').value;
					document.getElementById('sale_qty').value = document
						.getElementById('sale_qty_val').value;
					document.getElementById('item_quantity').focus();


				} else {

					alert("Please Insert Right Item");
					document.getElementById('item_name').value = "";
					document.getElementById('item_name').focus();
				}

			}
		});
	} else {
		//alert("Please Insert Item");

	}
}

document.querySelector("#item_code").addEventListener("change", (e) => {
	document.querySelector("#item_qty").select();
});

