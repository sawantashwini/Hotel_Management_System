

$('#menu_name').change(function() {
	var menu_name = document.getElementById("menu_name").value;
	if (menu_name != "") {
		document.getElementById('table_design').className = "";
		$.ajax({

			url: 'AjaxFolder/AjaxReciepeDesign.jsp',
			data: 'Menu_name=' + menu_name,
			type: 'post',
			success: function(msg) {
				//alert(msg);
				$('#tbody_design').html(msg);
			}
		});
	} else {

		//alert("Please Insert Name");
		document.getElementById('table_design').className = "d-none";

	}
});
function findDuplicateItem() {

	var arr = document.getElementsByName("Ingredient_name");
	var ret = 0;

	for (var i = 1; i <= arr.length; i++) {

		if (document.getElementById("ingredient_name").value.toUpperCase() == document
			.getElementById("ingredient_name" + i).value.toUpperCase()) {

			// alert(document.getElementById("itemCode" + i).value);

			ret = 1;
			alert("Same Code");

			document.getElementById('ingredient_name').value = "";
			document.getElementById('ingredient_name').focus();
		}
	}
	return ret;
}
function insertFull() {

	var menu_name = document.getElementById("menu_name").value;
	var menu_id_fk = Number(document.getElementById("menu_id_fk").value);
	var user_id_fk = Number(document.getElementById("user_id_fk").value);
	var ingredient_name = document.getElementById("ingredient_name").value;
	var ingredient_id_fk = Number(document.getElementById("ingredient_id_fk").value);
	var reciepe_ratio = parseFloat(document.getElementById("reciepe_ratio").value);


	if (menu_name != "" && ingredient_name != "") {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertReciepeRelation.jsp',
			data: 'Menu_name=' + menu_name
				+ '&Menu_id_fk=' + menu_id_fk
				+ '&User_id_fk=' + user_id_fk
				+ '&Ingredient_name=' + ingredient_name
				+ '&Ingredient_id_fk=' + ingredient_id_fk
				+ '&Reciepe_ratio=' + reciepe_ratio,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#result_insert_item_info').html(msg);

				var reciepe_result = document.getElementById("reciepe_result").value;
				// alert(reciepe_result);


				//alert("Item Inserted Successfully");

				if (reciepe_result == "true") {
					document.getElementById("ingredient_name").value = "";
					document.getElementById("reciepe_ratio").value = "0";

					$.ajax({

						url: 'AjaxFolder/AjaxReciepeDesign.jsp',
						data: 'Menu_name=' + menu_name,
						type: 'post',
						success: function(msg) {
							//alert(msg);
							$('#tbody_design').html(msg);
						}
					});

				} else {

					alert("Item Not Inserted");
				}

			}
		});
	} else {

		alert("Please Insert Item Name");

	}
}

function deleteReciepe(id, menu_name) {

	var msg = "Are You Sure! you want to delete this Item";
	if (confirm(msg) == true) {

		$.ajax({

			url: 'AjaxFolder/AjaxInsertReciepeRelation.jsp',
			data: 'Id=' + id,
			type: 'post',
			success: function(msg) {

				//alert(msg);

				$('#result_insert_item_info').html(msg);

				var reciepe_result = document.getElementById("reciepe_result").value;
				//alert(check);

				if (reciepe_result == "true") {

					//alert("Item Deleted Successfully");
					$.ajax({



						url: 'AjaxFolder/AjaxReciepeDesign.jsp',
						data: 'Menu_name=' + menu_name,
						type: 'post',
						success: function(msg) {
							//alert(msg);
							$('#tbody_design').html(msg);
						}
					});

				}
			}
		});

	} else {

		alert("Item Not Deleted");
	}

}

