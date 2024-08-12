
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

					var duplicate_value = findDuplicateCode();

					if (duplicate_value == 0) {

						document.getElementById('item_id').value = document
							.getElementById('item_id_val').value;
						document.getElementById('sale_qty').value = document
							.getElementById('sale_qty_val').value;	
						document.getElementById('item_quantity').focus();

					}
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

function findDuplicateCode() {

	var arr = document.getElementsByName("Item_name");
	var ret = 0;

	for (var i = 1; i <= arr.length; i++) {

		if (document.getElementById("item_name").value.toUpperCase() == document
			.getElementById("item_name" + i).value.toUpperCase()) {

			// alert(document.getElementById("itemCode" + i).value);

			ret = 1;
			alert("Same Code");

			document.getElementById('item_name').value = "";
			document.getElementById('item_name').focus();
		}
	}
	return ret;
}