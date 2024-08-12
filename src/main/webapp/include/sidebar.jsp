<!-- Sidebar Start  -->

<%
if (user_id > 0) {
%>

<aside id="sidebar" class="sidebar">
	<ul class="sidebar-nav" id="sidebar-nav">

		<!--Home Start -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="index.jsp"> <i class="fa fa-home" aria-hidden="true"></i> <span>HOME</span>
		</a></li>
		<!-- End Home  -->
		
		<%
		
		if (user_head_dto.getCustomer_module().equalsIgnoreCase("Yes")) {
		%>
		<!-- Logout Start -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="manage_customer.jsp"><i class="fa fa-user"></i> <span>CUSTOMER
					REPORT</span> </a></li>
		<!-- End Logout -->
		<%
		}
		%>
		<%
		if (user_head_dto.getOrder_module().equalsIgnoreCase("Yes")) {
		%>

		<!--Order Start -->
		<li class="nav-item" style="margin-top: 5px;"><a
			class="nav-link collapsed" data-bs-target="#menu-order"
			data-bs-toggle="collapse" href="#"> <i class="ri-restaurant-line"
				aria-hidden="true"></i><span>ORDER </span><i
				class="bi bi-chevron-down ms-auto"></i></a>

			<ul id="menu-order" class="collapse" data-bs-parent="#sidebar-nav">
				<li><a class="nav-item mt-1 nav-link collapsed"
					href="menu_item_table.jsp"> <i class="fa fa-sign-out"
						aria-hidden="true"></i> <span>ORDER</span>
				</a></li>

				<li><a class="nav-item mt-1 nav-link collapsed"
					href="menu_kot_item_table.jsp"> <i class="fa fa-sign-out"
						aria-hidden="true"></i> <span>KOT ORDER</span>
				</a></li>

				<%
				if (user_head_dto.getPending_bills_report().equalsIgnoreCase("Yes")) {
				%>

				<li><a class="nav-item mt-1 nav-link collapsed"
					href="manage_pending_bills.jsp"> <i class="fa fa-sign-out"
						aria-hidden="true"></i> <span>PENDING BILLS</span>
				</a></li>

				<%
				}
				if (user_head_dto.getComplete_bills_report().equalsIgnoreCase("Yes")) {
				%>

				<li><a class="nav-item mt-1 nav-link collapsed"
					href="manage_complete_bills.jsp"> <i class="fa fa-sign-out"
						aria-hidden="true"></i> <span>COMPLETE BILLS</span>
				</a></li>
				<%
				}
				if (user_head_dto.getKot_bills_report().equalsIgnoreCase("Yes")) {
				%>

				<li><a class="nav-item mt-1 nav-link collapsed"
					href="manage_kot_bills.jsp"> <i class="fa fa-sign-out"
						aria-hidden="true"></i> <span>KOT BILLS</span>
				</a></li>

				<%
				}
				%>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_table.jsp"> <i
						class="fas fa-table" aria-hidden="true"></i> <span>TABLE
							REPORT</span>
				</a></li>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_menu_item.jsp"> <i
						class="fa-solid fa-book" aria-hidden="true"></i> <span>MENU
							ITEM REPORT</span>
				</a></li>
					<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="item_wise_report.jsp"> <i
						class="fa-solid fa-book" aria-hidden="true"></i> <span>
							ITEM WISE REPORT</span>
				</a></li>
				<!-- End Table Item -->
			</ul></li>

		<!--Order End -->

		<%
		}
		if (user_head_dto.getRoom_module().equalsIgnoreCase("Yes")) {
		%>


		<!--Room Start -->
		<li id="room-nav" class="nav-item" style="margin-top: 5px;"><a
			class="nav-link collapsed" data-bs-target="#room-order"
			data-bs-toggle="collapse" href="#"> <i class="ri-hotel-line"
				aria-hidden="true"></i><span>ROOM </span><i
				class="bi bi-chevron-down ms-auto"></i></a>

			<ul id="room-order" class="collapse" data-bs-parent="#room-nav">

				<!-- Room Available Start -->
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_room_available.jsp"> <i
						class="ri-community-fill" aria-hidden="true"></i> <span>ROOM
							AVAILABLE</span>
				</a></li>
				<!-- Room Available End -->
				<%
				if (user_head_dto.getRoom_booked_report().equalsIgnoreCase("Yes")) {
				%>


				<!-- Room Booked Start -->
				<li class="nav-item"><a class="nav-link collapsed"
					href="manage_room_booked.jsp"> <i class="ri-community-line"
						aria-hidden="true"></i> <span>ROOM BOOKED</span>
				</a></li>
				<!-- Room Booked End -->
				<%
				}
				if (user_head_dto.getRoom_bill_report().equalsIgnoreCase("Yes")) {
				%>

				<!-- Room Bill Start -->
				<li class="nav-item"><a class="nav-link collapsed"
					href="manage_room_bills.jsp"> <i class="ri-hotel-fill"
						aria-hidden="true"></i> <span>ROOM BILLS</span>
				</a></li>
				<!-- Room billed End -->

				<%
				}
				%>

				<!-- Room Start -->
				<li class="nav-item"><a class="nav-link collapsed"
					href="manage_room.jsp"> <i class="fa fa-area-chart"
						aria-hidden="true"></i> <span>ROOM </span>
				</a></li>
				<!-- Room End -->

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_menu_item.jsp"> <i
						class="fa-solid fa-book" aria-hidden="true"></i> <span>MENU
							ITEM REPORT</span>
				</a></li>
				<!-- End Room  -->
			</ul></li>

		<!--Room End -->

		<%
		}
		if (user_head_dto.getIngredients_module().equalsIgnoreCase("Yes")) {
		%>





		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#ingredients" data-bs-toggle="collapse" href="#">
				<i class="fa-solid fa-pizza-slice"></i><span>INGREDIENTS</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="ingredients" class="collapse " data-bs-parent="#sidebar">
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" data-bs-target="#ing_stock_in"
					data-bs-toggle="collapse" href="#" data-bs-parent="#ingredients">
						<i class="fa fa-sign-out" aria-hidden="true"></i><span>INGREDIENTS
							IN</span><i class="bi bi-chevron-down ms-auto"></i>
				</a>
					<ul id="ing_stock_in" class="nav-content collapse">
						<li><a href="ingredients_purchase_report.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span>STOCK
									IN REPORT</span>
						</a></li>
						<li><a href="ingredients_purchase_item_report.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span> ITEM
									WISE REPORT</span>
						</a></li>
					</ul></li>




				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" data-bs-target="#ing_stock_out"
					data-bs-toggle="collapse" href="#" data-bs-parent="#ingredients">
						<i class="fa fa-sign-out" aria-hidden="true"></i><span>INGREDIENTS
							OUT</span><i class="bi bi-chevron-down ms-auto"></i>
				</a>
					<ul id="ing_stock_out" class="nav-content collapse">

						<li><a href="ingredients_sale_report.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span>STOCK
									OUT REPORT</span>
						</a></li>
						<li><a href="ingredients_sale_item_report.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span> ITEM
									WISE REPORT</span>
						</a></li>
					</ul></li>


				<!-- End Ingredients Item  -->


				<%
				if (user_head_dto.getReciepe_relation_module().equalsIgnoreCase("Yes")) {
				%>


				<li><a class="nav-item mt-1 nav-link collapsed"
					href="reciepe_ingredients_relation.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>RECIEPE
							RELATION</span>
				</a></li>
				<%
				}
				%>

				<!-- Ingredients Item Start -->
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" style="margin-top: 5px;"
					data-bs-target="#ing" data-bs-toggle="collapse" href="#"> <i
						class="fa-solid fa-bowl-rice"></i><span>INGREDIENTS ITEM</span> <i
						class="bi bi-chevron-down ms-auto"></i>
				</a>
					<ul id="ing" class="nav-content collapse" data-bs-parent="#master">
						<li><a href="manage_ingredients_item.jsp"> <i
								class="bi bi-circle"></i> <span>INGREDIENTS STOCK</span>
						</a></li>
						<li><a href="ingredients_requirement_items_report.jsp"> <i
								class="bi bi-circle"></i> <span>LOW STOCK</span>
						</a></li>


					</ul></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_measurement.jsp"> <i
						class="fa-solid fa-ruler" aria-hidden="true"></i> <span>MEASUREMENT
							REPORT</span>
				</a></li>


			</ul></li>
		<!-- End Ingredients -->
		<%
		}
		if (user_head_dto.getLiquor_module().equalsIgnoreCase("Yes")) {
		%>



		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#liquor" data-bs-toggle="collapse" href="#"> <i
				class="fas fa-cocktail"></i><span>LIQUOR</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="liquor" class="collapse " data-bs-parent="#sidebar">




				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" data-bs-target="#liq_pur"
					data-bs-toggle="collapse" href="#" data-bs-parent="#liquor"> <i
						class="fa fa-sign-out" aria-hidden="true"></i><span>LIQUOR
						 </span><i class="bi bi-chevron-down ms-auto"></i>
				</a>
					<ul id="liq_pur" class="nav-content collapse"
						data-bs-parent="#master">
						<li><a href="liquor_purchase.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span> PURCHASE</span>
						</a></li>
						<li><a href="liquor_purchase_report.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span>
									PURCHASE BILL</span>
						</a></li>
						<li><a href="liquor_purchase_item_report.jsp"> <i
								class="bi bi-circle" aria-hidden="true"></i> <span>ITEM
									WISE REPORT</span>
						</a></li>
					</ul></li>


				<!-- Liquor Category Start -->
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" style="margin-top: 5px;"
					data-bs-target="#liquor_cat" data-bs-parent="#liquor"
					data-bs-toggle="collapse" href="#"> <i class="fa fa-sign-out"
						aria-hidden="true"></i> <span>LIQUOR STOCKS </span> <i
						class="bi bi-chevron-down ms-auto"></i>
				</a>
					<ul id="liquor_cat" class="nav-content collapse"
						data-bs-parent="#master">
						<li><a href="manage_liquor_category.jsp"> <i
								class="bi bi-circle"></i> <span>BRAND STOCK</span>
						</a></li>
						<li><a href="manage_liquor_category_requirements.jsp"> <i
								class="bi bi-circle"></i> <span>LOW STOCK</span>
						</a></li>
					</ul></li>
				<!-- End Liquor Category  Item -->





				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_liquor_item.jsp"> <i
						class="fa-solid fa-martini-glass" aria-hidden="true"></i> <span>LIQUOR
							REPORT</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_dealer.jsp"> <i
						class="fa fa-users" aria-hidden="true"></i> <span>DEALER
							REPORT</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_measurement.jsp"> <i
						class="fa-solid fa-ruler" aria-hidden="true"></i> <span>MEASUREMENT
							REPORT</span>
				</a></li>

				<!-- End Menu Item -->

			</ul></li>



		<%
		}
		
		%>
		<!-- End Liquor -->

		<%-- <%
		}
		if (user_head_dto.getCustomer_module().equalsIgnoreCase("Yes")) {
		%>






		<!-- End Collection -->
		<li class="nav-item" style="margin-top: 5px;"><a
			class="nav-link collapsed" style="margin-top: 5px;"
			data-bs-target="#customer" data-bs-toggle="collapse" href="#"><i
				class="fa fa-user" aria-hidden="true"></i> <span>CUSTOMER</span> <i
				class="bi bi-chevron-down ms-auto"></i> </a>
			<ul id="customer" class="nav-content collapse"
				data-bs-parent="#sidebar-nav">
				<li><a href="add_customer.jsp"> <i class="bi bi-circle"></i>
						<span>ADD CUSTOMER</span>
				</a></li>
				<li><a href="manage_customer.jsp"> <i class="bi bi-circle"></i>
						<span>CUSTOMER REPORT</span>
				</a></li>
			</ul></li>

		<%
		}
		%> --%>
		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#spent" data-bs-toggle="collapse" href="#"> <i
				class="fa-solid fa-coins"></i><span>SPEND MODULE</span><i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="spent" class="collapse " data-bs-parent="#spent">
				<%
				if (user_head_dto.getSpend_module().equalsIgnoreCase("Yes")) {
				%>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="add_spend.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span> SPEND</span>
				</a></li>
				<%
				}
				%>
				<%
				if (user_head_dto.getSpend_report().equalsIgnoreCase("Yes")) {
				%>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_spend.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span> SPEND
							REPORT</span>
				</a></li>
				<%
				}
				%>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_spend_head.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>SPEND
							HEAD REPORT</span>
				</a></li>
			</ul></li>
		<!-- End spend Nav -->
		<!-- Start Income Nav -->

		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#income_nav" data-bs-toggle="collapse" href="#"><i
				class="fa-solid fa-hand-holding-dollar"></i><span>INCOME
					MODULE</span><i class="bi bi-chevron-down ms-auto"></i> </a>
			<ul id="income_nav" class="collapse " data-bs-parent="#sidebar-nav">
				<%
				if (user_head_dto.getIncome_module().equalsIgnoreCase("Yes")) {
				%>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="add_income.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i><span>INCOME</span>
				</a></li>
				<%
				}
				%>
				<%
				if (user_head_dto.getIncome_report().equalsIgnoreCase("Yes")) {
				%>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_income.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>INCOME
							REPORT</span>
				</a></li>
				<%
				}
				%>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_income_head.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>INCOME
							HEAD REPORT</span>
				</a></li>
			</ul></li>


		<!-- End Income Nav -->

		<%
		if (user_head_dto.getPayment_module().equalsIgnoreCase("Yes")) {
		%>

		<!-- Start Collection -->
		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#payment_nav" data-bs-toggle="collapse" href="#">
				<i class="fa-solid fa-indian-rupee-sign"></i> <span>PAYMENT
					COLLECTION</span> <i class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="payment_nav" class="collapse " data-bs-parent="#sidebar-nav">
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_cash_payment.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>CASH
							COLLECTION</span>
				</a></li>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_online_payment.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>ONLINE
							COLLECTION</span>
				</a></li>
			</ul></li>

		<%
		}
		%>
		<%
		if (user_head_dto.getEmployee_module().equals("Yes")) {
		%>
		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#employee" data-bs-toggle="collapse" href="#"> <i
				class="fas fa-users"></i><span>EMPLOYEE</span><i
				class="bi bi-chevron-down ms-auto"></i></a>

			<ul id="employee" class="collapse " data-bs-parent="#sidebar-nav">

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="add_employee.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>ADD
							EMPLOYEE</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_employee.jsp"> <i
						class="fa fa-sign-out" aria-hidden="true"></i> <span>
							EMPLOYEE REPORT</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="pay_all_employee_salary.jsp">
						<i class="fa fa-sign-out" aria-hidden="true"></i> <span>PAY
							SALARY</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_all_employee_salary.jsp">
						<i class="fa fa-sign-out" aria-hidden="true"></i> <span>
							SALARY REPORT</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="employee_attendence_entry.jsp">
						<i class="fa fa-sign-out" aria-hidden="true"></i> <span>ATTENDANCE
							ENTRY</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="view_employee_attendance.jsp">
						<i class="fa fa-sign-out" aria-hidden="true"></i> <span>ATTENDANCE
							REPORT</span>
				</a></li>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_city.jsp"> <i
						class="fa-solid fa-city" aria-hidden="true"></i> <span>CITY
							REPORT</span>
				</a></li>
			</ul></li>
		<%
		}
		%>
		<!-- End Employee Nav -->


		<%
		if (user_head_dto.getBank_module().equalsIgnoreCase("Yes")) {
		%>

		<!-- Bank Start -->
		<li class="nav-item"><a class="nav-link collapsed"
			data-bs-target="#bank_nav" data-bs-toggle="collapse" href="#"> <i
				class="fas fa-landmark"></i> <span>BANK</span> <i
				class="bi bi-chevron-down ms-auto"></i>
		</a>
			<ul id="bank_nav" class="collapse " data-bs-parent="#sidebar-nav">

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_bank.jsp"> <i
						class="fas fa-coins" aria-hidden="true"></i> <span>BANK
							REPORT</span>
				</a></li>

				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_bank_cash_credit.jsp">
						<i class="fas fa-money-check" aria-hidden="true"></i> <span>
							CASH CREDIT REPORT</span>
				</a></li>
				<li class="nav-item" style="margin-top: 5px;"><a
					class="nav-link collapsed" href="manage_bank_transaction.jsp">
						<i class="far fa-credit-card" aria-hidden="true"></i> <span>
							TRANSACTION REPORT</span>
				</a></li>

			</ul></li>
		<!-- End Bank -->


		<%
		}
		%>

		<!-- Logout Start -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="manage_user.jsp"><i class="fa-solid fa-circle-user"></i> <span>USER
					REPORT</span> </a></li>
		<!-- End Logout -->
<li class="nav-item"><a class="nav-link collapsed"
			href="customer_birthday_anneversary.jsp"><i class="fa-solid fa-circle-user"></i> <span>ANN. & BIRTHDAY
					REPORT</span> </a></li>
					
		<!-- Logout Start -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="logout.jsp"> <i class="fa fa-sign-out" aria-hidden="true"></i>
				<span>LOGOUT</span>
		</a></li>
		<!-- End Logout -->
	</ul>
</aside>
<!-- Sidebar End  -->
<%
}
%>

<div id="name_avail_resposnse" class="col-12" style="display: none;"></div>
