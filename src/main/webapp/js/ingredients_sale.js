var preWorkExpIndex = 1;

function autoFocus() {

	var item_id = document.getElementById("item_id").value;
	//alert("hii");
	var item_name = document.getElementById("item_name").value;
	var item_quantity = document.getElementById("item_quantity").value;

	if (item_name == "") {
		alert("Select any Item ");
		document.getElementById("item_name").value = "";
		document.getElementById("item_quantity").value = "0";

	} else if (item_quantity == "" || item_quantity < 0 || isNaN(item_quantity)) {
		alert("Insert item_quantity ");
		document.getElementById("item_quantity").value = "0";
		document.getElementById("item_quantity").focus();
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


		newCell = newRow.insertCell(2);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		document.getElementById("item_name").value = "";
		document.getElementById("item_quantity").value = 0;
		document.getElementById('item_name').focus();

		preWorkExpIndex++;


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

		var item_name = document.getElementById("item_name" + j).value;

		var item_id = document.getElementById("item_id" + j).value;
		var item_quantity = document.getElementById("item_quantity" + j).value;



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

		newCell = newRow.insertCell(2);
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
function insertItem() {

	var item_id_fk = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
	var item_quantity = document.getElementById("item_quantity").value;
	var bill_id_fk = document.getElementById("bill_id").value;
	var flag = "Sale";

	if (item_name != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneIngredientsItem.jsp',
			data: 'Item_id_fk=' + item_id_fk + '&Item_name=' + item_name
				+ '&Item_quantity=' + item_quantity + '&Bill_id_fk='
				+ bill_id_fk + '&Flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#ajax_response_design').html(msg);

				var check = document.getElementById("item_result").value;
				//alert(check);

				if (check == "true") {

					$('#sale_item_show').load(location.href + " #sale_item_show");

					document.getElementById("item_name").value = "";
					document.getElementById("item_quantity").value = 0;

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
	//alert(item_id_fk);
	var msg = "Are You Sure! you want to delete this Item";
	var flag = "Sale";
	if (confirm(msg) == true) {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneIngredientsItem.jsp',
			data: 'Id=' + id + '&Item_id_fk=' + item_id_fk + '&Item_quantity=' + item_quantity + '&Flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#ajax_response_design').html(msg);

				var check = document.getElementById("item_result").value;
				//alert(check);

				if (check == "true") {

					//alert("Item Deleted Successfully");
					$('#sale_item_show').load(location.href + " #sale_item_show");

				}
				$('#ajax_response_design').html("");
			}
		});

	} else {

		alert("Item Not Deleted");
	}

}

