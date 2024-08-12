var preWorkExpIndex = 1;

function autoFocus() {
	

	var item_id = document.getElementById("item_id").value;
	var item_name = document.getElementById("item_name").value;
   	var item_code = document.getElementById("item_code").value;
    var item_qty = parseFloat(document.getElementById("item_qty").value);
    var item_rate = parseFloat(document.getElementById("item_rate").value);
    
    var total_price_with_qty = item_qty*item_rate;
    
    
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

		var applicationTable = document.getElementById("tableScroll");

		var newRow = applicationTable.insertRow(preWorkExpIndex - 1);

		// alert(preWorkExpIndex);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_code.toUpperCase() + '<input id="item_code'
			+ preWorkExpIndex + '" type="hidden" name="Item_code" value="'
			+ item_code + '"><input id="item_name'
			+ preWorkExpIndex + '" type="hidden" name="Item_name" value="'
			+ item_name + '"><input id="item_id' + preWorkExpIndex
			+ '" type="hidden" name="Item_id" value="' + item_id
			+ '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);

		newCell.innerHTML = item_rate + '<input id="item_rate'
			+ preWorkExpIndex
			+ '" type="hidden" name="Item_rate" value="'
			+ item_rate + '">';
		newCell.setAttribute("align", "center");
		
		
		var newCell = newRow.insertCell(2);

		newCell.innerHTML = item_qty + '<input id="item_qty'
			+ preWorkExpIndex
			+ '" type="hidden" name="Item_qty" value="'
			+ item_qty + '">';
		newCell.setAttribute("align", "center");
		
		
		var newCell = newRow.insertCell(3);

		newCell.innerHTML = total_price_with_qty + '<input id="total_price_with_qty'
			+ preWorkExpIndex
			+ '" type="hidden" name="Total_price_with_qty" value="'
			+ total_price_with_qty + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(4);
		newCell.innerHTML = "<i class='bi bi-trash main-color' onclick='deleteRow(\""
			+ applicationTable.id
			+ "\",this.parentNode.parentNode.rowIndex);'/> ";

		clearTableFormValues();
		document.getElementById("item_code").focus();

		preWorkExpIndex++;

		findTotal();

		//findTotalQty();

	}
}

function deleteRow(tableObjId, i) {


	var tblObj = document.getElementById(tableObjId);
	preWorkExpIndex--;

	tblObj.deleteRow(i - 1);
	
	manageRow(i);
	
	findTotal();
	
}

function manageRow(rowNo) {

	var applicationTable = document.getElementById("tableScroll");
	
	var tblObj = document.getElementById(applicationTable.id);

	for (var index = rowNo; index <= tblObj.rows.length; index++) {
		j = index + 1;
		
		var item_id = document.getElementById("item_id"+ j).value;
		var item_name = document.getElementById("item_name"+ j).value;
	   	var item_code = document.getElementById("item_code"+ j).value;
	    var item_qty = parseFloat(document.getElementById("item_qty"+ j).value);
	    var item_rate = parseFloat(document.getElementById("item_rate"+ j).value);
	    
	    var total_price_with_qty = item_qty*item_rate;

		var newRow = applicationTable.insertRow(index - 1);
		// alert(index);

		var newCell = newRow.insertCell(0);

		newCell.innerHTML = item_code.toUpperCase() + '<input id="item_code'
			+ index + '" type="hidden" name="Item_code" value="'
			+ item_code + '"><input id="item_name'
			+ index + '" type="hidden" name="Item_name" value="'
			+ item_name + '"><input id="item_id' + index
			+ '" type="hidden" name="Item_id" value="' + item_id
			+ '">';
		newCell.setAttribute("align", "center");

		var newCell = newRow.insertCell(1);

		newCell.innerHTML = item_rate + '<input id="item_rate'
			+ index
			+ '" type="hidden" name="Item_rate" value="'
			+ item_rate + '">';
		newCell.setAttribute("align", "center");
		
		
		var newCell = newRow.insertCell(2);

		newCell.innerHTML = item_qty + '<input id="item_qty'
			+ index
			+ '" type="hidden" name="Item_qty" value="'
			+ item_qty + '">';
		newCell.setAttribute("align", "center");
		
		
		var newCell = newRow.insertCell(3);

		newCell.innerHTML = total_price_with_qty + '<input id="total_price_with_qty'
			+ index
			+ '" type="hidden" name="Total_price_with_qty" value="'
			+ total_price_with_qty + '">';
		newCell.setAttribute("align", "center");


		newCell = newRow.insertCell(4);
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
					//  alert(check);

					if (check == "done") {

						var duplicate_value = findDuplicateCode();
						//alert(duplicate_value);
						if (duplicate_value == 0) {

							document.getElementById('item_id').value = document
								.getElementById('item_id_val').value;


							
							document.getElementById('item_name').value = document
								.getElementById('item_name_val').value;
								document.getElementById('item_rate').value = document
								.getElementById('item_price_val').value;
								
								
								document.getElementById('item_qty').focus();
							
							

					}	
					} else {

						clearTableFormValues();
						 document.getElementById('icode' + index).focus();
					}
					
					$('#rev').html("");

				}
			});
	}
}





function refreshItem() {
		
	//alert("Start");
	var flag = 'Sell';
	
	
		$.ajax({

			url : 'AjaxFolder/AjaxAvailItem.jsp',
			data : 'Flag='+ flag,
			type : 'post',
			success : function(msg) {

				//alert(msg);

				$('#browsers1').html(msg);
				

				

			}
		});
		

}





 function clearTableFormValues(){
	
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
}





function SubmitKot() {
	
	var items = document.getElementsByName("Item_id");
	//alert(items.length);
    
    if (items.length != 0) {
        
    
    
	var id = parseInt(document.getElementById("kot_id").value);
	var table_id = document.getElementById("table_id").value;
	var without_gst_amount = parseFloat(document.getElementById("without_gst_amount").value);
	var bill_date = document.getElementById("bill_date").value;
	var session_year = document.getElementById("session_year").value;
	var user_id_fk = parseInt(document.getElementById("user_id_fk").value);
	var manager_name =document.getElementById("manager_name").value;
	
	
	var item_id = [];
	var item_name = [];
	var item_code = [];
	var item_qty = [];
	var item_rate = [];


	for (var i = 0; i < items.length; i++) {
	    item_id.push(items[i].value);
	    item_name.push(document.getElementsByName("Item_name")[i].value);
	    item_code.push(document.getElementsByName("Item_code")[i].value);
	    item_qty.push(document.getElementsByName("Item_qty")[i].value);
	    item_rate.push(document.getElementsByName("Item_rate")[i].value);
	}


    $.ajax({
        url: 'AjaxFolder/AjaxKotOrderBill.jsp',
        data: 'id=' + id +
            '&table_id=' + table_id +
            '&without_gst_amount=' + without_gst_amount +
            '&bill_date=' + bill_date +
            '&session_year=' + session_year +
            '&user_id_fk=' + user_id_fk +
            '&manager_name=' + manager_name +
            '&item_id=' + item_id +
            '&item_name=' + item_name +
            '&item_code=' + item_code +
            '&item_qty=' + item_qty +
            '&item_rate=' + item_rate,
        type: 'post',
        success: function(msg) {
		//alert(msg);
            $('#ajax_response_design').html(msg);
            var check = document.getElementById("item_result").value;
            var kot_id_val = document.getElementById("order_id_val").value;
            if (check == "true") {
                changeClass("table-btn-" + table_id, "free-table", "book-table");
                openTable(table_id);
                
                window.location.href = "print_kot_bill.jsp?kot_id=" + kot_id_val;
            } else {
                alert("Item Not Inserted");
            }
            clearTableFormValues();
            $('#ajax_response_design').html("");
        }
    });
    
    }
    
    else{
	alert("No items found. Please add items before submitting.");
        
	}
}



function changeClass(div_id, add_class, remove_class) {
    var div = document.getElementById(div_id);
    
    if (div){
		if (remove_class) {
            div.classList.remove(remove_class);
        }
        
        if (add_class) {
            div.classList.add(add_class);
        }
        
        
    }
}


function SubmitBtmVisibility() {
	var billingCheckbox = document.getElementById("billing");
	var submitButton = document.getElementById("bill-submit");
		if (billingCheckbox.checked) {
			submitButton.classList.remove("d-none");
		} else {
			submitButton.classList.add("d-none");
		}
	}



function openTable(new_table_id) {
	 var old_table_id = document.getElementById("table_id").value;
	var old_button = document.getElementById("table-btn-" + old_table_id);
	
	if(old_table_id!=new_table_id){
  var new_button = document.getElementById("table-btn-" + new_table_id);
  
	$.ajax({
		url: 'AjaxDesign/GetMenuTableItemDesign.jsp',
		data: 'table_id=' + new_table_id
		+ '&flag=' + "kot" ,
		type: 'post',
		success: function(msg) {
			$('#ajax_response_design').html(msg);
			$('#table_id').val(new_table_id);
			var table_name = document.getElementById("table_name_val").value;
			var manager_name = document.getElementById("manager_name_val").value;
			$('#dv_tb_name').html(table_name);
			$('#table_name').val(table_name);
			$('#manager_name').val(manager_name);
			findTotal();
			//alert("done");
			$('#ajax_response_design').html("");
		}
	});
	
	if(old_table_id!=0){
		changeClass(old_button.id, "", "active");
	}
	changeClass(new_button.id, "active", "");
	
	}
	
}


function UpdateTableManager() {
	var table_id = parseInt(document.getElementById("table_id").value);
   	var manager_name = document.getElementById("manager_name").value;
    //alert(table_id+" "+manager_name);
    $.ajax({
        url: 'AjaxFolder/AjaxUpdateTableManager.jsp',
       data: 'table_id=' + table_id
       +'&manager_name=' + manager_name,
    	type: 'post',
        type: 'post',
        success: function (msg) {
	
		alert(msg);
		
            if (msg === "true") {
                //Success
                //alert("Table manager updated successfully.");
            } else {
                // Error
                //alert("Failed to update table manager.");
            }
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}


function refreshItem(flag) {
	var item_name = document.getElementById('item_name').value;

	$.ajax({

		url: 'AjaxFolder/Ajax_refresh.jsp',
		data: 'Flag=' + flag,
		type: 'post',
		success: function(msg) {

			//alert(msg);

			$('#browsers1').html(msg);
			if (item_name != "") {
				document.getElementById('item_name').value = "";
			}

		}
	});


}

document.querySelector("#item_code").addEventListener("change",(e)=>{
	document.querySelector("#item_qty").select();
});

