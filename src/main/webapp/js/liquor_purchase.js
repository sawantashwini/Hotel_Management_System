var preWorkExpIndex = 1;

function autoFocus() {

	var item_id = document.getElementById("item_id").value;
	var item_code = document.getElementById("item_code").value;
	var item_name = document.getElementById("item_name").value;
	var amt = document.getElementById("amt").value;
	var quantity = document.getElementById("quantity").value;
	var rate = document.getElementById("rate").value;


	if (item_code == "") {
		alert("Select any Item ");
		document.getElementById("item_code").value = "";
		document.getElementById("amt").value = "0.0";
		document.getElementById("quantity").value = "0";
		document.getElementById("rate").value = "0.0";

	} else if (amt < 0 || isNaN(amt)) {
		//alert("Insert item_fees ");
		document.getElementById("amt").value = "0";
		document.getElementById("amt").focus();
	} else {

		//setAmount();

		var applicationTable = document.getElementById("tableScroll");

		var newRow = applicationTable.insertRow(preWorkExpIndex - 1);

		//alert(preWorkExpIndex);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_code.toUpperCase() + '<input id="item_code'
			+ preWorkExpIndex + '" type="hidden" name="Item_code" value="'
			+ item_code + '">';
		newCell.setAttribute("align", "center");
		
		var newCell = newRow.insertCell(1);
		newCell.innerHTML = quantity + '<input id="quantity'
			+ preWorkExpIndex + '" type="hidden" name="Quantity" value="'
			+ quantity + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);
		newCell.innerHTML = amt + '<input id="amt'
			+ preWorkExpIndex + '" type="hidden" name="Amt" value="'
			+ amt + '">' + '<input  type="hidden" id="item_id'
			+ preWorkExpIndex + '" name="Item_id" value="'
			+ item_id + '">'+ '<input  type="hidden" id="item_name'
			+ preWorkExpIndex + '" name="Item_name" value="'
			+ item_name + '">';
		newCell.setAttribute("align", "center");
		
		var newCell = newRow.insertCell(3);
		newCell.innerHTML = rate + '<input id="rate'
			+ preWorkExpIndex + '" type="hidden" name="Rate" value="'
			+ rate + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(4);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		document.getElementById("item_code").value = "";
		document.getElementById("item_name").value = "";
		document.getElementById("amt").value = 0;
		document.getElementById("quantity").value = 0;
		document.getElementById("rate").value = 0;
		document.getElementById('item_code').focus();

		preWorkExpIndex++;

		findTotal()
	}
}

function deleteRow(tableObjId, i) {

	var tblObj = document.getElementById(tableObjId);
	preWorkExpIndex--;

	tblObj.deleteRow(i - 1);
	findTotal()
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
		var amt = document.getElementById("amt" + j).value;
		var quantity = document.getElementById("quantity" + j).value;
		var rate = document.getElementById("rate" + j).value;

		var newRow = applicationTable.insertRow(index - 1);
		// alert(index);

		var newCell = newRow.insertCell(0);
		newCell.innerHTML = item_code + '<input id="item_code'
			+ index + '" type="hidden" name="Item_code" value="'
			+ item_code + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);

		newCell.innerHTML = amt + '<input id="amt'
			+ index + '" type="hidden" name="Amt" value="'
			+ amt + '">' + '<input  type="hidden" id="item_id'
			+ index + '" name="Item_id" value="'
			+ item_id + '">'+ '<input  type="hidden" id="item_name'
			+ index + '" name="Item_name" value="'
			+ item_name + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);
		newCell.innerHTML = quantity + '<input id="quantity'
			+ index + '" type="hidden" name="Quantity" value="'
			+ quantity + '">';
		newCell.setAttribute("align", "center");
		
		var newCell = newRow.insertCell(3);
		newCell.innerHTML = rate + '<input id="rate'
			+ index + '" type="hidden" name="Rate" value="'
			+ rate + '">';
		newCell.setAttribute("align", "center");
		

		newCell = newRow.insertCell(4);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		tblObj.deleteRow(index);
	}
}

function validQty() {

	var quantity = document.getElementById("quantity").value;

	if (quantity < 1) {

		alert("please insert valid Quantity");
		document.getElementById('quantity').value = "1";
		document.getElementById('quantity').focus();

	}
}

function findAmt() {

	var quantity = document.getElementById("quantity").value;
	var amt = document.getElementById("amt").value;
	if (amt > 0) {

		var rate = amt * quantity;
		document.getElementById('rate').value = rate.toFixed(2);
	}


}

function findRate() {

	var item_quantity = document.getElementById("quantity").value;
	var item_amt = document.getElementById("amt").value;
	if (item_amt > 0) {

		var item_rate = item_amt / item_quantity;
		document.getElementById('rate').value = item_rate.toFixed(2);
	}


}
function findTotal() {
	var amtarr = document.getElementsByName('Amt');
	var total_amount = 0.0;
	for (var i = 0; i < amtarr.length; i++) {
		if (parseFloat(amtarr[i].value))
			total_amount = total_amount + parseFloat(amtarr[i].value);
	}
	document.getElementById("total_amount").value = total_amount.toFixed(2);
}

function findDuplicateItem() {

	var arr = document.getElementsByName("Item_code");
	var ret = 0;

	for (var i = 1; i <= arr.length; i++) {

		if (document.getElementById("item_code").value.toUpperCase() == document
			.getElementById("item_code" + i).value.toUpperCase()) {

			// alert(document.getElementById("itemCode" + i).value);

			ret = 1;
			alert("Same Code");

			document.getElementById('item_code').value = "";
			document.getElementById('item_code').focus();
		}
	}
	return ret;
}



function checkItemAvail(item_code) {
	//alert("Hello");
	if (item_code != "") {

		$.ajax({

				url: 'AjaxFolder/ItemCodeAvailAjax.jsp',
				data: 'item_code=' + item_code,
				type: 'post',
				success: function(msg) {

					//alert(msg);

					$('#get_item_info').html(msg);
					
					var check = document.getElementById("check").value;
					//alert(check);

					if (check == "done") {

						var duplicate_value = findDuplicateCode();

						if (duplicate_value == 0) {

						document.getElementById('item_id').value = document
							.getElementById('item_id_val').value;
						document.getElementById('item_name').value = document
							.getElementById('item_name_val').value;


						document.getElementById('quantity').focus();

						}	
					}
				}
			});
	}
}




function clearTableFormValues() {

	document.getElementById("item_code").value = "";
	document.getElementById('price').value = "0";
	document.getElementById("item_id").value = "";
	document.getElementById("quantity").value = "0";
	document.getElementById("total_price").value = "";

}


function findDealerInfoByName(name) {

	if (name != "") {

		$.ajax({

			url: 'AjaxDealerInfoByName.jsp',
			data: 'name=' + name,
			type: 'post',
			success: function(msg) {

				// alert(msg);

				$('#ajax_response_design').html(msg);

				var check = document.getElementById("check_dealer").value;
				//alert(check);

				if (check == "done") {

					document.getElementById('name').value = document
						.getElementById('name_val').value;


					document.getElementById('dealer_id_fk').value = document
						.getElementById('dealer_id_val').value;

					//document.getElementById('name').setAttribute('readonly', true);



				} else {

					alert("Please Insert Right Name");
				}
				$('#ajax_response_design').html("");

			}
		});
	} else {

		//alert("Please Insert Name");

	}
}

function removeDealerDet() {
	//alert("hiii");
	document.getElementById('name').removeAttribute('readonly')

	document.getElementById('name').value = "";
}





function insertItem() {
	
	var item_id_fk = document.getElementById("item_id").value;
	var item_code = document.getElementById("item_code").value;
	var item_name = document.getElementById("item_name").value;
	var quantity = document.getElementById("quantity").value;
	var amt = document.getElementById("amt").value;
	var rate = document.getElementById("rate").value;
	var bill_id_fk = document.getElementById("bill_id").value;
	//alert("hello");
	if (item_code != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneLiquorItem.jsp',
			data: 'Item_id_fk=' + item_id_fk + '&Item_code=' + item_code + '&Item_name=' + item_name
				+ '&Quantity=' + quantity + '&Price=' + amt +
				'&Total_price=' + rate + '&Bill_id_fk='
				+ bill_id_fk ,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#result_insert_item_info').html(msg);

				var check_pur = document.getElementById("item_result").value;
				//alert(check_pur);

				if (check_pur == "true") {


					//alert("Item Inserted Successfully");
					//location.reload();
					$('#purchase_item_show').load(location.href + " #purchase_item_show", function() {
						findTotal();
					}
					);
					document.getElementById("item_code").value = "";
					document.getElementById("item_name").value = "";
					document.getElementById("quantity").value = 0;
					document.getElementById("amt").value = 0;
					document.getElementById("rate").value = 0;


				} else {

					alert("Item Not Inserted");
				}

			}
		});
	} else {

		alert("Please Insert Item Name");

	}

}

function deleteItem(id) {
	//alert("hello");

	var msg = "Are You Sure! you want to delete this Item";
	if (confirm(msg) == true) {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneLiquorItem.jsp',
			data: 'Id=' + id,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#result_insert_item_info').html(msg);

				var check_del = document.getElementById("item_result").value;
				//alert(check_del);

				if (check_del == "true") {

					//alert("Item Deleted Successfully");
					//location.reload();
					$('#purchase_item_show').load(location.href + " #purchase_item_show", function() {
						findTotal();
					}
					);

				}
			}
		});

	} else {

		alert("Item Not Deleted");
	}

}
