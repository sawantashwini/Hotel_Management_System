<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/jszip.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/pdfmake.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/vfs_fonts.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/buttons.html5.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/buttons.print.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/buttons.colVis.min.js"></script>
<!-- <script type="text/javascript"
	src="../../../../examples/resources/demo.js"></script>

<script type="text/javascript"
	src="datatables/Buttons-2.2.3/js/buttons.colVis.min.js"></script> -->
	
<!-- Merge and Customize Script Start -->
<script>
var page_title = document.getElementById('page_title').innerHTML;
var name = document.getElementById('name') ? document.getElementById('name').value  : "";
var first_date = document.getElementById('d1') ? document.getElementById('d1').value  : "";
var second_date = document.getElementById('d2') ? document.getElementById('d2').value  : "";
var bank_name = document.getElementById('bank_name') ? document.getElementById('bank_name').innerHTML  : "";
var balanceAmt = document.getElementById('pre_bal') ? document.getElementById('pre_bal').innerHTML : "";



/* var name = document.getElementById('name').innerHTML; */

function printfunction(selector) {
	if(first_date!=""&&second_date!=""&&balanceAmt!=""){
		return '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1><p style="text-align:center;"> From Date :'+ first_date + ' To Date: '+ second_date + ' Balance: ' + balanceAmt + '</p>';
	}
	else if(first_date!=""&&second_date!=""&&bank_name!=""&&balanceAmt!=""){
		return '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1><p style="text-align:center;"> From Date :'+ first_date + ' To Date: '+ second_date + ' Bank Name: ' + bank_name + 'Balance: ' + balanceAmt + '</p>';
	}
	else if(first_date!=""&& second_date!=""&& bank_name!=""){
		return '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1><p style="text-align:center;"> From Date :'+ first_date + ' To Date: '+ second_date + ' Bank Name: ' + bank_name + '</p>';
	}
	else if(first_date!=""&&second_date!=""&& name!=""){
		return '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1><p style="text-align:center;"> From Date :'+ first_date + ' To Date: '+ second_date + ' Name: '+ name +'</p>';
	}
	else if(first_date!=""&&second_date!=""){
		return '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1><p style="text-align:center;"> From Date :'+ first_date + ' To Date: '+ second_date + '</p>';
	}
	else if(page_title!=""){
		 return '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1>';
	}  
}


function printTitle(selector) {
    return page_title;
}

function printOtherfunction(selector) {
    if(first_date!=""&&second_date!=""&& balanceAmt!=""){
		return 'From Date: ' + first_date + ' To Date: ' + second_date +  balanceAmt;
	}
	else if(first_date!=""&&second_date!=""&& balanceAmt!=""&&bank_name!=""){
		return 'From Date: ' + first_date + ' To Date: ' + second_date + 'Bank Name: ' + bank_name + 'Balance: '+  balanceAmt;
	}
	else if(first_date!=""&&second_date!=""&&bank_name!=""){
		return 'From Date: ' + first_date + ' To Date: ' + second_date + 'Bank Name: ' + bank_name ;
	}
	else if(first_date!=""&&second_date!=""&& balanceAmt!=""&& name!=""){
		return 'From Date: ' + first_date + ' To Date: ' + second_date + 'Bank Name: ' + bank_name + 'Name: '+  name;
	}
	else if(first_date!=""&&second_date!=""){
		return 'From Date: ' + first_date + ' To Date: ' + second_date;
	}
}


function initializeDataTable(selector, scrollX, customMessageTopFunction) {
    $(document).ready(function () {
        var messageTop = "";

        if (customMessageTopFunction) {
            messageTop = customMessageTopFunction(selector);
        }

        $(selector).DataTable({
            dom: 'lBfrtip',
            buttons: [
                {
                    extend: 'print',
                    footer: true,
                    title: '',
                    messageTop: printfunction(selector),
                    exportOptions: {
                        columns: ':visible'
                    }
                },
                {
                    extend: 'excel',
                    footer: true,
                    title: printTitle(selector),
                    messageTop: messageTop,
                    exportOptions: {
                        columns: ':visible'
                    }
                },
                {
                    extend: 'pdf',
                    footer: true,
                    title: printTitle(selector),
                    messageTop: messageTop,
                    exportOptions: {
                        columns: ':visible'
                    }
                },
                'colvis',
            ],
            lengthMenu: [
                [20, 50, 100, 500, -1],
                ['20', '50', '100', '500', 'all']
            ],
            pagingType: 'full_numbers',
            responsive: true,
            scrollX: scrollX
        });
    });
}

initializeDataTable('#examples,#large_simple_table', true);
initializeDataTable('#examples-small,#small_simple_table', false );
initializeDataTable('#example,#large_table,#example1,#example2,#example3,#example4,#example5,#example6', true, printOtherfunction);
initializeDataTable('#example-small,#small_table', false, printOtherfunction);

</script>
<!-- Merge and Customize Script End -->
