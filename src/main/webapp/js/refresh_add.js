// function is used for Create select box with search
function initializeSelectizeCity() {

	// This code will run after the content is loaded
	$('#city_id_fk').selectize({
		sortField: 'text'
	});
	$("#city_id_fk-selectize").css({
		"margin-top": "5px"
	});
	$(".item").css({ position: "absolute" });
}
$(document).ready(function() {

	try {
		//initializeSelectizeCity();
		
	} catch (e) {
	}
	$('.add_link').click(function() {
		var i = $('.add_link').index(this);
		//alert(i);
		if ($('.add_link').eq(i).parent().attr('id') === 'menu_parent') {
			$(".add_link").eq(i).attr("href", "add_menu_item.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'ingredients_parent') {
			$(".add_link").eq(i).attr("href", "add_ingredients_item.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'bank_parent') {
			$(".add_link").eq(i).attr("href", "add_bank.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'city_parent') {
			$(".add_link").eq(i).attr("href", "add_city.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'dealer_parent') {
			$(".add_link").eq(i).attr("href", "add_dealer.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'liquor_parent') {
			$(".add_link").eq(i).attr("href", "add_liquor_item.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'measurement_parent') {
			$(".add_link").eq(i).attr("href", "add_measurement.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'income_parent') {
			$(".add_link").eq(i).attr("href", "add_income_head.jsp");
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'spend_parent') {
			$(".add_link").eq(i).attr("href", "add_spend_head.jsp"); 
		}
		if ($('.add_link').eq(i).parent().attr('id') === 'liquor_cat_parent') {
			$(".add_link").eq(i).attr("href", "add_liquor_category.jsp"); 
		}
	});

	$('.lock-refresh').click(function() {
		var i = $('.lock-refresh').index(this);
		//alert(i);

		if ($('.lock-refresh').eq(i).parent().attr('id') === 'menu_parent') {
			$('#browsers1').load(location.href + " #browsers1"); // Relative URL for browsers1
			$('#refresh_menu').load('reciepe_ingredients_relation.jsp #refresh_menu'); // Relative URL for refresh_menu
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'ingredients_parent') {
			$('#browsers1').load(location.href + " #browsers1");
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'dealer_parent') {
			$('#datalist_name').load(location.href + " #datalist_name");
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'liquor_parent') {
			$('#refresh_liquor').load(location.href + " #refresh_liquor");
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'liquor_cat_parent') {
			$('#refresh_liquor_cat').load(location.href + " #refresh_liquor_cat"); 
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'measurement_parent') {
			$('#refresh_measure').load(location.href + " #refresh_measure");
		}
		
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'bank_parent') {
			$('#refresh_bank').load(location.href + " #refresh_bank");

			try {
				$('#refresh_bank_second').load(location.href + " #refresh_bank_second");
			} catch (e) {
			}
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'city_parent') {
			$('#city_id_fk_refresh').load(location.href + " #city_id_fk_refresh", function() {
				 initializeSelectizeCity()
			});
		}

		if ($('.lock-refresh').eq(i).parent().attr('id') === 'income_parent') {
			$('#browsers').load(location.href + " #browsers");
		}
		if ($('.lock-refresh').eq(i).parent().attr('id') === 'spend_parent') {
			$('#browsers').load(location.href + " #browsers");
		}

		spin('.lock-refresh:eq(' + i + ')');
	});
});

function spin(clickedElement) {
	$(clickedElement).addClass('fa-spin');
	setTimeout(function() {
		$(clickedElement).removeClass('fa-spin');
	}, 300);
}

