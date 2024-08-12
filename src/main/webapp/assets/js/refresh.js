/**
 * 
 */
 
 $(document).ready(function() {
	  $(".refresh-container").each(function() {
		    $(this).append('<div class="refresh-block"><a target="_blank" class="plus-add-icon" title="Add"><i class="fas fa-edit main-color"></i></a><a title="Refresh"><i class="fa fa-refresh main-color"></i></a></div>');
		  });
		});
		
$(document).ready(function() {
	  $(".refresh-container").each(function() {
	    var container = $(this);
	    container.find('a').eq(0).on('click', function() {
	      var url = container.data('add');
	      //alert(url);
	      window.open(url, '_blank');
	    });
	    container.find('a').eq(1).on('click', function() {
		  refreshFunction(container);
	      
	    });
	  });

	  function refreshFunction(container) {
		  var datalistId = container.find("datalist").attr("id");
		  var refreshIcon = container.find('.refresh-block a').eq(1).find('i');
		  refreshIcon.addClass('spin-animation');
		  //alert(datalistId);	
		  
		  		
		$('#'+datalistId).load(location.href + " #"+datalistId, function() {
				//alert("Refresh Done");
			});		   
		    
			setTimeout(function() {
			    refreshIcon.removeClass('spin-animation');
			  }, 1000); // Remove the class after 2 seconds 
		}

	});