var preWorkExpIndex = 1;


function autoFocus() {

	var item_id = document.getElementById("item_id").value;
	var item_code = document.getElementById("item_code").value;
	var item_name = document.getElementById("item_name").value;
	var item_quantity = document.getElementById("item_quantity").value;
	var rate = document.getElementById("rate").value;
	var item_amt = document.getElementById("item_amt").value;


	if (item_code == "") {
		alert("Select any Item ");
		document.getElementById("item_code").value = "";
		document.getElementById("item_name").value = "";
		document.getElementById("item_quantity").value = "0";
		document.getElementById("item_amt").value = "0.0";
		document.getElementById("rate").value = "0.0";

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

		newCell.innerHTML = item_code.toUpperCase() + '<input id="item_code'
			+ preWorkExpIndex + '" type="hidden" name="Item_code" value="'
			+ item_code + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);

		newCell.innerHTML = item_name.toUpperCase() + '<input id="item_name'
			+ preWorkExpIndex + '" type="hidden" name="Item_name" value="'
			+ item_name + '">';
		newCell.setAttribute("align", "center");


		var newCell = newRow.insertCell(2);
		newCell.innerHTML = item_quantity + '<input id="item_quantity'
			+ preWorkExpIndex + '" type="hidden" name="Item_quantity" value="'
			+ item_quantity + '">' + '<input  type="hidden" id="item_id'
			+ preWorkExpIndex + '" name="Item_id" value="'
			+ item_id + '">';
		newCell.setAttribute("align", "center");
		
			var newCell = newRow.insertCell(3);
		newCell.innerHTML = rate + '<input id="rate'
			+ preWorkExpIndex + '" type="hidden" name="Rate" value="'
			+ rate + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(4);
		newCell.innerHTML = item_amt + '<input id="item_amt'
			+ preWorkExpIndex + '" type="hidden" name="Item_amt" value="'
			+ item_amt + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(5);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		document.getElementById("item_code").value = "";
		document.getElementById("item_name").value = "";
		document.getElementById("item_quantity").value = 0;
		document.getElementById("item_amt").value = 0;
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
		var item_quantity = document.getElementById("item_quantity" + j).value;
		var rate = document.getElementById("rate" + j).value;
		var item_amt = document.getElementById("item_amt" + j).value;
		



		var newRow = applicationTable.insertRow(index - 1);
		// alert(index);

			var newCell = newRow.insertCell(0);
		newCell.innerHTML = item_code + '<input id="item_code'
			+ index + '" type="hidden" name="Item_code" value="'
			+ item_code + '">';
		newCell.setAttribute("align", "center");
		
		var newCell = newRow.insertCell(1);

		newCell.innerHTML = item_name.toUpperCase() + '<input id="item_name'
			+ index + '" type="hidden" name="Item_name" value="'
			+ item_name + '">' + '<input  type="hidden" id="item_id'
			+ index + '" name="Item_id" value="'
			+ item_id + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(2);
		newCell.innerHTML = item_quantity + '<input id="item_quantity'
			+ index + '" type="hidden" name="Item_quantity" value="'
			+ item_quantity + '">';
		newCell.setAttribute("align", "center");
		
			var newCell = newRow.insertCell(3);
		newCell.innerHTML = rate + '<input id="rate'
			+ index + '" type="hidden" name="Rate" value="'
			+ rate + '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(4);
		newCell.innerHTML = item_amt + '<input id="item_amt'
			+ index + '" type="hidden" name="Item_amt" value="'
			+ item_amt + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(5);
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

function findAmt() {

    var item_quantity = document.getElementById("item_quantity").value;
	var rate = document.getElementById("rate").value;
	if (rate > 0) {

		var item_amt = rate * item_quantity;
		document.getElementById('item_amt').value = item_amt.toFixed(2);
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

function refreshItem(flag) {
	
	
		$.ajax({

			url : 'AjaxFolder/Ajax_refresh.jsp',
			data : 'Flag='+ flag,
			type : 'post',
			success : function(msg) {

				//alert(msg);

				$('#browsers1').html(msg);
				

				

			}
		});
		

}

function deleteItem(item_id, item_quantity) {
	//alert("hello");

	var msg = "Are You Sure! you want to delete this Item";
	var flag = "Purchase";
	if (confirm(msg) == true) {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertOneIngredientsItem.jsp',
			data: 'Id=' + item_id + '&Item_quantity=' + item_quantity + '&Flag=' + flag,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#result_insert_item_info').html(msg);

				var check_del = document.getElementById("item_result").value;
				//alert(check_del);

				if (check_del == "true") {

					alert("Item Deleted Successfully");
					location.reload();
					findTotal();

				}
			}
		});

	} else {

		alert("Item Not Deleted");
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
    var bill_id_fk = document.getElementById("bill_id_fk").value;
    var room_id = document.getElementById("room_id").value;
    var item_id = document.getElementById("item_id").value;
    var item_name = document.getElementById("item_name").value;
    var item_code = document.getElementById("item_code").value;
    var item_qty = parseFloat(document.getElementById("item_qty").value);
    var item_rate = parseFloat(document.getElementById("item_rate").value);
     var order_date = document.getElementById("order_date").value;
    
    
    
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
		else {

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
					$('#table_div_design').load(location.href + ' #table_div_design');
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
				
				$('#table_div_design').load(location.href + ' #table_div_design');
				
			} else {
				alert("Item Not Deleted");
			}
		}
	});																																																																																																
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
      total_basic_amt = total_basic_amt + amt;

    var qty = parseFloat(totQtyArr[i].value);
    if (!isNaN(qty))
      total_qty += qty;
  }

  document.getElementById("total_tab_price").innerHTML = total_basic_amt.toFixed(2);
  
}



 function clearTableFormValues(){
	
	document.getElementById("item_code").value = "";
	document.getElementById('item_name').value = "";
	document.getElementById("item_id").value = "";
	document.getElementById("item_rate").value = "";
	document.getElementById("item_qty").value = "1";
	
	
}


function getRoomOrderItemDesign() {
	var bill_id_fk = document.getElementById("bill_id_fk").value;
	
	//alert(room_id);
	
	$.ajax({
		url: 'AjaxDesign/GetRoomOrderItemDesign.jsp',
		data: 'bill_id_fk=' + bill_id_fk,
		type: 'post',
		success: function(msg) {
			//alert(msg);
			$('#tbody_design').html(msg);
			findTotal();
		}
	});
	
	
}

document.querySelector("#item_code").addEventListener("change",(e)=>{
	document.querySelector("#item_qty").select();
});

