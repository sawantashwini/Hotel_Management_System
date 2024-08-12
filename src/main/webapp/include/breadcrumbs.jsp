<link href="assets/css/style.css" rel="stylesheet">

<div class="horizontal">
	<div class="verticals ten offset-by-one">
		<%
		String currentHref = request.getRequestURI();

		// Define breadcrumb sets with items and corresponding href values
		String[][] breadcrumbSets = {
				{ "Employee", "Employee Report", "Salary", "Salary Report", "Attendance", "Attendance Report", "City Report" },
				{ "Income", "Income Report", "Income Report" },
				{ "Bank", "Bank Report", "Bank Cash Credit", "Bank Cash Credit Report", "Bank Transaction",
				"Bank Transaction Report" },
				{ "Customer", "Customer Report" },
				{ "Purchase", "Bill Report", "Bill Item Report", "Liquor Brand Stock", "Low Stock", "Item Report", "Dealer Report" },
				{  "Ingredients Stock", "Low Stock", "Inward", "Report", "Item Report", "Outward", "Report",
				"Item Report", "Reciepe Relation" },
				{ "Message", "Message Report" }, 
				{ "All Room", "Available Room", "Booked Room", "Room Bill" },
				{ "User", "User Report" },
				{ "Spend", "Spend Report", "Spend Head Report" }, { "Cash Report", "Online Report" },
				{ "Order", "KOT", "Pending Bills", "Complete Bills", "KOT Bills", "Table Report","Item wise Report" } };

		String[][] breadcrumbItemHrefs = {
				{"add_employee.jsp", "manage_employee.jsp", "pay_all_employee_salary.jsp", "manage_all_employee_salary.jsp",
				"employee_attendence_entry.jsp", "view_employee_attendance.jsp", "manage_city.jsp" , "add_city.jsp"},
				{ "add_income.jsp", "manage_income.jsp", "manage_income_head.jsp", "add_income_head.jsp" },
				{ "add_bank.jsp", "manage_bank.jsp", "add_bank_cash_credit.jsp", "manage_bank_cash_credit.jsp",
				"add_bank_transaction.jsp", "manage_bank_transaction.jsp" },
				{ "add_customer.jsp", "manage_customer.jsp", "manage_customer_account.jsp", "manage_customer_due.jsp", "manage_customer_all_orders_bills.jsp","manage_customer_all_bills.jsp" },
				{ "liquor_purchase.jsp", "liquor_purchase_report.jsp", "liquor_purchase_item_report.jsp",
				 "manage_liquor_category.jsp", "manage_liquor_category_requirements.jsp",
				 "manage_liquor_item.jsp", "manage_dealer.jsp", "manage_dealer_due.jsp" , 
				"manage_dealer_all_bill.jsp", "manage_dealer_account.jsp", "add_liquor_category.jsp", "add_liquor_item.jsp"},
				{ "manage_ingredients_item.jsp", "ingredients_requirement_items_report.jsp",
				"ingredients_purchase.jsp", "ingredients_purchase_report.jsp", "ingredients_purchase_item_report.jsp",
				"ingredients_sale.jsp", "ingredients_sale_report.jsp", "ingredients_sale_item_report.jsp",
				"reciepe_ingredients_relation.jsp", "add_ingredients_item.jsp" },
				{ "add_msg.jsp", "manage_msg.jsp" },
				{  "manage_room.jsp", "manage_room_available.jsp", "manage_room_booked.jsp",
				"manage_room_bills.jsp", "room_order.jsp", "room_billing.jsp", "add_room.jsp" },
				{ "add_user.jsp", "manage_user.jsp" }, { "add_spend.jsp", "manage_spend.jsp", "manage_spend_head.jsp" },
				{ "manage_cash_payment.jsp", "manage_online_payment.jsp" },
				{ "menu_item_table.jsp", "menu_kot_item_table.jsp", "manage_pending_bills.jsp", "manage_complete_bills.jsp",
				"manage_kot_bills.jsp", "manage_table.jsp","item_wise_report.jsp", "add_table.jsp"} };
		%>

		<ol class="breadcrumb breadcrumb-fill2 style4">
			<li><a href="index.jsp"><i class="fa fa-home"></i></a></li>
			<%
			for (int setIndex = 0; setIndex < breadcrumbSets.length; setIndex++) {
						String[] breadcrumbItems = breadcrumbSets[setIndex];
						String[] breadcrumbItemHref = breadcrumbItemHrefs[setIndex];
						
						for (int index_i = 0; index_i < breadcrumbItemHref.length; index_i++) {
							if (currentHref.contains(breadcrumbItemHref[index_i])) {
								for (int f = 0; f < breadcrumbItems.length; f++) {
			%>
			<li><a href="<%=breadcrumbItemHref[f]%>"><%=breadcrumbItems[f]%></a></li>
			<%
			}
			break;
			}
			}
			}
			%>
		</ol>


	</div>

</div>
