
	<div class="pagetitle" style="float: right;margin-right: 2%;">
		<%
		// Define the list of breadcrumb items and their corresponding href values
		String[][] addItemHrefs = { { "manage_city.jsp" }, { "manage_msg.jsp" }, { "manage_spend_head.jsp" },
				{ "manage_income_head.jsp" }, { "manage_user.jsp" }, { "manage_bank.jsp" }, { "anage_bank_cash_credit.jsp" },
				{ "manage_bank_transaction.jsp" }, { "manage_employee.jsp" }, { "view_employee_attendance.jsp" },
				{ "manage_income.jsp" }, { "manage_spend.jsp" }, { "manage_customer.jsp" }, { "manage_liquor_category.jsp" },
				{ "liquor_purchase_report.jsp" }, { "manage_liquor_item.jsp" }, { "manage_dealer.jsp" },
				{ "ingredients_purchase_report.jsp" }, { "manage_ingredients_item.jsp" }, { "ingredients_sale_report.jsp" },
				{ "manage_menu_item.jsp" }, { "manage_room.jsp" }, { "manage_table.jsp" }, { "manage_measurement.jsp" } };

		String[] addRichItemHrefs = { "add_city.jsp", "add_msg.jsp", "add_spend_head.jsp", "add_income_head.jsp",
				"add_user.jsp", "add_bank.jsp", "add_bank_cash_credit.jsp", "add_bank_transaction.jsp", "add_employee.jsp",
				"employee_attendence_entry.jsp", "add_income.jsp", "add_spend.jsp", "add_customer.jsp",
				"add_liquor_category.jsp", "liquor_purchase.jsp", "add_liquor_item.jsp", "add_dealer.jsp",
				"ingredients_purchase.jsp", "add_ingredients_item.jsp", "ingredients_sale.jsp", "add_menu_item.jsp",
				"add_room.jsp", "add_table.jsp", "add_measurement.jsp" };

		String[] addTitle = {"City", "Message", "Spend Head", "Income Head", "User", "Bank", "Bank Cash Credit",
				"Bank Transaction", "Employee", "Attendence Entry", "Income", "Spend", "Customer", "Liquor Brand",
				"Liquor Purchase", "Liquor Item", "Dealer", "Ingredients In", "Ingredients Item", "Ingredients Out",
				"Menu Item", "Room", "Table", "Measurement" };
		String currentURL = request.getRequestURI(); // You may need to adjust this based on your servlet container

		for (int j = 0; j < addItemHrefs.length; j++) {
			String addRichsItemHref = addRichItemHrefs[j];
			String addTitles = addTitle[j];
			String[] currentAddItemHrefs = addItemHrefs[j];

			for (String addItemHref : currentAddItemHrefs) {
				if (currentURL.contains(addItemHref)) {
			String fullURL = request.getContextPath() + "/" + addRichsItemHref;
		%>
		<button style="font-weight: 1000; padding: 5px 10px; right: 0;"
			onclick="window.location.href = '<%=fullURL%>';" type="submit"
			class="submit-btn btn4">
			<i class="fa-solid fa-circle-plus"></i>
			<%=addTitles%>
		</button>
		<%
		}
		}
		}
		%>


	</div>

<style>
.btn4:hover {
	border: 1px solid #f03f02;
	background-color: #fff;
	color: #f03f02;
}
</style>

