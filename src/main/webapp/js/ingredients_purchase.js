var preWorkExpIndex = 1;

function autoFocus() {

	var item_id = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
	var item_quantity = document.getElementById("item_quantity").value;
	var item_amt = document.getElementById("item_amt").value;
	var item_rate = document.getElementById("item_rate").value;
	
	//alert(item_id);

	if (item_name == "") {
		alert("Select any Item ");
		document.getElementById("item_name").value = "";
		document.getElementById("item_quantity").value = "0";
		document.getElementById("item_amt").value = "0.0";
		document.getElementById("item_rate").value = "0.0";

	} else if (item_amt < 0 || isNaN(item_amt)) {
		//alert("Insert item_fees ");
		document.getElementById("item_amt").value = "0";
		document.getElementById("item_amt").focus();
	} else {

		//setAmount();

		var applicationTable = document.getElementById("tableScroll");

		var newRow = applicationTable.insertRow(preWorkExpIndex - 1);

		//alert(preWorkExpIndex);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_name.toUpperCase() + '<input id="item_name'
			+ preWorkExpIndex + '" type="hidden" name="Item_name" value="'
			+ item_name + '">';
		newCell.setAttribute("align", "center");


		var newCell = newRow.insertCell(1);
		newCell.innerHTML = item_quantity + '<input id="item_quantity'
			+ preWorkExpIndex + '" type="hidden" name="Item_quantity" value="'
			+ item_quantity + '">' + '<input  type="hidden" id="item_id'
			+ preWorkExpIndex + '" name="Item_id" value="'
			+ item_id + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);
		newCell.innerHTML = item_amt + '<input id="item_amt'
			+ preWorkExpIndex + '" type="hidden" name="Item_amt" value="'
			+ item_amt + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(3);
		newCell.innerHTML = item_rate + '<input id="item_rate'
			+ preWorkExpIndex + '" type="hidden" name="Item_rate" value="'
			+ item_rate + '">';
		newCell.setAttribute("align", "center");

		newCell = newRow.insertCell(4);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		document.getElementById("item_name").value = "";
		document.getElementById("item_quantity").value = 0;
		document.getElementById("item_amt").value = 0;
		document.getElementById("item_rate").value = 0;
		document.getElementById('item_name').focus();

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

		var item_name = document.getElementById("item_name" + j).value;

		var item_id = document.getElementById("item_id" + j).value;
		var item_quantity = document.getElementById("item_quantity" + j).value;
		var item_amt = document.getElementById("item_amt" + j).value;
		var item_rate = document.getElementById("item_rate" + j).value;



		var newRow = applicationTable.insertRow(index - 1);
		// alert(index);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_name.toUpperCase() + '<input id="item_name'
			+ index + '" type="hidden" name="Item_name" value="'
			+ item_name + '">' + '<input  type="hidden" id="item_id'
			+ index + '" name="Item_id" value="'
			+ item_id + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);
		newCell.innerHTML = item_quantity + '<input id="item_quantity'
			+ index + '" type="hidden" name="Item_quantity" value="'
			+ item_quantity + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);
		newCell.innerHTML = item_amt + '<input id="item_amt'
			+ index + '" type="hidden" name="Item_amt" value="'
			+ item_amt + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(3);
		newCell.innerHTML = item_rate + '<input id="item_rate'
			+ index + '" type="hidden" name="Item_rate" value="'
			+ item_rate + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(4);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		tblObj.deleteRow(index);
	}
}

function validQty() {

	var item_quantity = document.getElementById("item_quantity").value;

	if (item_quantity < 1) {

		alert("please insert valid Quantity");
		document.getElementById('item_quantity').value = "1";
		document.getElementById('item_quantity').focus();

	}
}

function findRate() {

	var item_quantity = document.getElementById("item_quantity").value;
	var item_amt = document.getElementById("item_amt").value;
	if (item_amt > 0) {

		var item_rate = item_amt / item_quantity;
		document.getElementById('item_rate').value = item_rate.toFixed(2);
	}


}
function findTotal() {
	var amtarr = document.getElementsByName('Item_amt');
	var total_amount = 0.0;
	for (var i = 0; i < amtarr.length; i++) {
		if (parseFloat(amtarr[i].value))
			total_amount = total_amount + parseFloat(amtarr[i].value);
	}
	document.getElementById("total_amount").value = total_amount.toFixed(2);
}




function insertItem() {
	//alert("hello");
	var item_id_fk = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
	var item_quantity = document.getElementById("item_quantity").value;
	var item_amt = document.getElementById("item_amt").value;
	var item_rate = document.getElementById("item_rate").value;
	var bill_id_fk = document.getElementById("bill_id").value;
	var flag = "Purchase";
	//alert("hello1");
	if (item_name != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneIngredientsItem.jsp',
			data: 'Item_id_fk=' + item_id_fk + '&Item_name=' + item_name
				+ '&Item_quantity=' + item_quantity + '&Item_amt=' + item_amt +
				'&Item_rate=' + item_rate + '&Bill_id_fk='
				+ bill_id_fk + '&Flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#ajax_response_design').html(msg);

				var check_pur = document.getElementById("item_result").value;
				//alert(check_pur);

				if (check_pur == "true") {


					//alert("Item Inserted Successfully");
					//location.reload();
					$('#purchase_item_show').load(location.href + " #purchase_item_show", function() {
						findTotal();
					}
					);
					document.getElementById("item_name").value = "";
					document.getElementById("item_quantity").value = 0;
					document.getElementById("item_amt").value = 0;
					document.getElementById("item_rate").value = 0;


				} else {

					alert("Item Not Inserted");
				}
				$('#ajax_response_design').html("");

			}
		});
	} else {

		alert("Please Insert Item Name");

	}

}

function deleteItem(id, item_id_fk, item_quantity) {
	//alert("hello");

	var msg = "Are You Sure! you want to delete this Item";
	var flag = "Purchase";
	if (confirm(msg) == true) {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneIngredientsItem.jsp',
			data: 'Id=' + id + '&Item_id_fk=' + item_id_fk + '&Item_quantity=' + item_quantity + '&Flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#ajax_response_design').html(msg);

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
				$('#ajax_response_design').html("");
			}
		});

	} else {

		alert("Item Not Deleted");
	}

}
