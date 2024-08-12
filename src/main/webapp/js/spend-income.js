
//paidamount
function paidAmountEvent() {

	let cash_amount = parseFloat(document.getElementById("cash_amount").value);
	let online_amount = parseFloat(document
		.getElementById("online_amount").value);

	paid_amount = cash_amount + online_amount;
	document.getElementById("paid_amount").value = paid_amount
		.toFixed(2);
}
function refreshHead(flag) {
	var name = document.getElementById('name').value;
	$.ajax({

		url: 'AjaxDesign/AjaxRefreshAvailItem.jsp',
		data: 'Flag=' + flag,
		type: 'post',
		success: function(msg) {

			//alert(msg);

			$('#browsers').html(msg);
			
			if(name!=""){
				document.getElementById('name').value ="";
			}

		}
	});


}
function refreshBank() {
		
		//alert("Start");
		var flag = 'Bank';
		
		
			$.ajax({

				url : 'AjaxDesign/AjaxRefreshAvailItem.jsp',
				data : 'Flag='+ flag,
				type : 'post',
				success : function(msg) {

					//alert(msg);

					$('#bank_id_fk').html(msg);
					

					

				}
			});
			}

//Preview Image
function previewImage(event) {
	let reader = new FileReader();
	reader.onload = function() {
		let element = document.getElementById('preview-selected-image');
		element.src = reader.result;
	}
	reader.readAsDataURL(event.target.files[0]);
} 

//check spend head available or not
function checkSpendAvail() {

	var name = document.getElementById("name").value;

	if (name != "") {

		$.ajax({

			url: 'SpendIncomeHeadAvail.jsp',
			data: 'Name=' + name,
			success: function(msg) {

				//alert(msg);

				$('#result_spend').html(msg);

				var check = document.getElementById("check").value;
				//alert(check);

				if (check == "done") {

					document.getElementById('head_id_fk').value = document
						.getElementById('id_val').value;

				} else {

					alert("First add this in Spend head");
					document.getElementById("name").value = "";

				}

			}
		});
	} else {



	}
}

//Check income head avail or not
function checkIncomeAvail() {

	var name = document.getElementById("name").value;

	if (name != "") {

		$.ajax({

			url: 'SpendIncomeHeadAvail.jsp',
			data: 'Name=' + name,
			success: function(msg) {

				//alert(msg);

				$('#result_income').html(msg);

				var check1 = document.getElementById("check1").value;
				//alert(check);

				if (check1 == "done") {

					document.getElementById('head_id_fk').value = document
						.getElementById('income_id_val').value;

				} else {

					alert("First add this in Spend head");
					document.getElementById("name").value = "";

				}

			}
		});
	} else {



	}
}

function findNameById(head_id_fk) {

			if (head_id_fk != "") {

				$.ajax({

							url : 'AjaxNameById.jsp',
							data : 'Head_id_fk=' + head_id_fk,
							type : 'post',
							success : function(msg) {

								// alert(msg);

								$('#info').html(msg);

								var check = document.getElementById("check").value;
								//alert(check);

								if (check == "done") {

									document.getElementById('name').value = document
											.getElementById('name_val').value;

								} else {

									alert("Please Insert Right Id");
								}

							}
						});
			} else {

				alert("Please Insert Id");

			}
		}

//Download file script
var input = document.getElementById('file');
var link = document.getElementById('download');
let objectURL;

input.addEventListener('change', function() {
	if (objectURL) {
		// revoke the old object url to avoid using more memory than needed
		URL.revokeObjectURL(objectURL);
	}

	var file = this.files[0];
	objectURL = URL.createObjectURL(file);

	link.download = file.name; // this name is used when the user downloads the file
	link.href = objectURL;
});