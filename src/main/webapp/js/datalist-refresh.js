$(document).ready(function() {
	$(".refresh-container").each(function() {
		var container = $(this);
		var listtype = container.data('list');
		var dataListID = 'dataList_' + generateRandomID(8); // Generate a unique ID for the datalist
		container.append('<div class="refresh-block"><a target="_blank" class="plus-add-icon" title="Add"><i class="fas fa-edit main-color"></i></a><a title="Refresh"><i class="fa fa-refresh main-color"></i></a></div>');
		container.append('<datalist id="' + dataListID + '"></datalist>');
		var parentId = getParentId(container.data('parentlist'));
		var hiddenInput = container.find('input:hidden');
		var textInput = container.find('input:text');
		getDataList(listtype, dataListID, parentId, function() {
			

			// Check if text input value is not empty ("")
			if (textInput.val() !== "") {
				var thisValue = textInput.val();
				var thisId = $("#" + dataListID + " option[value='" + thisValue + "']").data("id");
				hiddenInput.val(thisId);
			}

			// Check if hidden input value is greater than 0
			if (parseInt(hiddenInput.val()) > 0) {
				var thisId = hiddenInput.val();
				//alert(thisId);
				var thisValue = $("#" + dataListID + " option[data-id='" + thisId + "']").val();
				//alert(thisValue);
				textInput.val(thisValue);
			}
		});





		// Add the dataListID to the list attribute of the text input box
		textInput.attr('list', dataListID);
		// Add onchange function to the text input box
		textInput.change(function() {
			var thisValue = $(this).val();
			var thisId = $("#" + dataListID + " option[value='" + thisValue + "']").data("id");

			if (typeof thisId === 'undefined') {
				this.value = '';
				container.find('input:hidden').val(0);
				warningAlert("Please Select Valid Detail.");
			} else {
				container.find('input:hidden').val(thisId);
			}
			refreshChild(listtype);
			//alert(thisId);
		});







	});
});

// Handle When Click on refreshBlock
$(document).ready(function() {
	$(".refresh-container").each(function() {
		var container = $(this);
		container.find('a').eq(0).on('click', function() {
			var url = container.data('add');
			//alert(url);
			//addMain(url);
			window.open(url,'_blank');
		});
		container.find('a').eq(1).on('click', function() {
			refreshFunction(container);
		});
	});
	function refreshFunction(container) {
		var listtype = container.data('list');
		var dataListID = container.find("datalist").attr("id");
		var refreshIcon = container.find('.refresh-block a').eq(1).find('i');
		refreshIcon.addClass('spin-animation');
		var parentId = getParentId(container.data('parentlist'));
		getDataList(listtype, dataListID, parentId, function() {
			setTimeout(function() {
				refreshIcon.removeClass('spin-animation');
			}, 1000); // Remove the class after 2 seconds 
		});

	}
});

function getParentId(parentlist) {
	var parentContainer = $("[data-list='" + parentlist + "']");
	var hiddenInputValue = parentContainer.find('input:hidden').val();
	//alert(hiddenInputValue);
	return hiddenInputValue;
}

function getDataList(type, dataListID, parentId, callback) {
	if (typeof parentId === "undefined") {
		parentId = 0;
	}

	$.ajax({
		url: 'AjaxFolder/AjaxDataList.jsp',
		data: 'type=' + type + '&parent_id=' + parentId,
		type: 'post',
		success: function(msg) {
			$('#' + dataListID).html(msg);
			if (typeof callback === 'function') {
				callback(); // Execute the callback function after the AJAX call is successful
			}
		},
		error: function(xhr, status, error) {
			console.error('Error:', error);
		}
	});
}


// Function to generate a random string of characters
function generateRandomID(length) {
	var result = '';
	var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var charactersLength = characters.length;
	for (var i = 0; i < length; i++) {
		result += characters.charAt(Math.floor(Math.random() * charactersLength));
	}
	return result;
}

function refreshChild(listType) {
	//alert(listType);
	var childContainer = $("[data-parentlist='" + listType + "']");
	var childListType = childContainer.data('list');
	var childDatalistId = childContainer.find("datalist").attr("id");
	//alert(childListType);

	var parentId = getParentId(listType);
	//alert(parentId);

	getDataList(childListType, childDatalistId, parentId, function() { });

}
function updateItemDetails(itemCodeInputID, container) {
	var itemCodeInput = document.getElementById(itemCodeInputID);

	// Check if the input element is found
	if (itemCodeInput) {
		var dataListID = itemCodeInput.getAttribute('list');
		var selectedOption = $("#" + dataListID + " option[value='" + itemCodeInput.value + "']");

		// Check if the option is found
		if (selectedOption.length > 0) {
			selectedOption.each(function() {
				var closestTr = $(this).closest('tr');
				var parentTr = $(this).parent('tr');

				// Check if the parent is a tr element or closest tr is outside the current selectedOption
				if (closestTr.length && closestTr[0] !== parentTr[0]) {
					$.each(this.dataset, function(dataAttrName, dataAttrValue) {
						// Check if an element with the corresponding ID exists on the page
						var $element = $('#' + dataAttrName);

						if ($element.length) {
							var duplicate_value = typeof findDuplicateItem === 'function' ? findDuplicateItem() : 0;

							if (duplicate_value == 0) {
								$element.val(dataAttrValue);
							}
						}
					});
				} else if (!closestTr.length) {
					
					$.each(this.dataset, function(dataAttrName, dataAttrValue) {
						
						// Check if an element with the corresponding ID exists on the page
						var $element = $('#' + dataAttrName);
						if ($element.length || $element.is(':hidden')) {
							if (closestInput.length && closestInput[0] !== parentInput[0]) {
								console.log("hello");
								$element.val(dataAttrValue);
							}
						}
					});
				}
			});
		} else {
			alert("Option not found for value: " + itemCodeInput.value);
			resetValues();
		}
	} else {
		alert("Input element not found with ID: " + itemCodeInputID);
	}
}




function getCommonIdPrefix(selectedOption) {
	var dataAttributeIds = Object.keys(selectedOption[0].dataset);
	var idPrefix = dataAttributeIds.reduce(function(prefix, id) {
		return prefix ? prefix.slice(0, Math.min(prefix.length, id.length)) : '';
	}, '');
	return idPrefix;
}

function resetValues() {
	var dataListID = $(itemCodeInput).attr('list');
	var selectedOption = $("#" + dataListID + " option[value='" + itemCodeInput.value + "']");
	

	// Reset values for all relevant input fields
	$('[id^="' + getCommonIdPrefix(selectedOption) + '"]').val('0');
	itemCodeInput.focus();
	// Add more lines for other input fields if needed
}

// Find and attach the onchange event to non-hidden inputs within the refresh-container
document.addEventListener('DOMContentLoaded', function() {
	var refreshContainers = document.querySelectorAll('.refresh-container');

	refreshContainers.forEach(function(container) {
		var inputs = container.querySelectorAll('input:not([type="hidden"])');

		inputs.forEach(function(input) {
			input.addEventListener('change', function() {
				updateItemDetails(input.id, container);
			});
		});
	});
});