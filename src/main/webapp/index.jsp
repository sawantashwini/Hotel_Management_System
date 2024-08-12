<%@include file="include/head.jsp"%>
<style type="text/css">
.container {
	margin-top: 1%;
	padding:3%;
	width: 100%;
	display: flex;
	/* grid-template-columns: repeat(auto-fill, minmax(140px, 2fr)); */
	/* Create a responsive grid */
	justify-items: center; /* Center the grid items horizontally */
	justify-content: center;
	background-color: white;
	border: 1px solid #f03c02;
	flex-wrap: wrap;
    flex-direction: row;
}

.block {
	flex: 0 0 20%;
	background-color: white;
	color: black;
	text-align: center;
	padding:30px;
	border-radius: 50%;
	width: 95%;
	border: 4px solid black;
	box-shadow: 3px 3px 5px black, 7px 7px 5px #f03c02;
	transition: all 0.3s;
	position: relative; /* Add relative positioning */
	margin: 1%; /* Add margin to control the gap */
	display: flex;
	justify-content: center; /* Horizontally center */
	align-items: center; /* Vertically center */
}

.block:hover, .block:hover  .fonty {
	transform: scale(110%);
	color: #f03c02;
}

.block  .fonty {
	color: #000;
	font-size: 15px;
	font-weight: 900;
}

.block i {
	color: #fff;
	font-size: 16px;
	line-height: 0;
		position: absolute;
	top: -36px;
	left: calc(50% - 31px);
	transition: 0.2s;
	border-radius: 50%;
	border: 6px solid #fff;
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
	width: 50px;
	height: 50px;
	background: #f03c02;
}
.block:hover i {
	background-color: white;
	color: #f03c02;
	border: 2px solid #f03c02;
}
</style>
</head>

<body>



	<!-- ======= Header ======= -->
	<%@include file="include/header.jsp"%>
	<!-- ======= Header end======= -->


	<!-- ======= Sidebar ======= -->
	<%@include file="include/sidebar.jsp"%>
	<!--  Sidebar End-->



	<main id="main" class="main">
		<div class="row justify-content-center">
			<div class="pagetitle col-lg-12 text-center">
				<h1>Order Module</h1>
			</div>
		</div>
		<div class="container" style="border-radius: 25px;">
			
			<div class="block">
				<a target="_blank" href="menu_item_table.jsp"> <i
					class="fa-solid fa-kitchen-set"></i>
				</a> <a target="_blank" class="fonty" href="menu_item_table.jsp">Order</a>
			</div>
			<div class="block">
				<a target="_blank" href="manage_pending_bills.jsp"> <i
					 class="fa-solid fa-file-invoice"></i>
				</a> <a target="_blank" class="fonty" href="manage_pending_bills.jsp">Pending Bills</a>
			</div>


			<div class="block">
				<a target="_blank" href="menu_kot_item_table.jsp"> <i
					class="fa-solid fa-bell-concierge"></i>
				</a> <a target="_blank" class="fonty" href="menu_kot_item_table.jsp">KOT Order</a>
			</div>

			<div class="block">
				<a target="_blank" href="manage_kot_bills.jsp">
				<i class="fa-brands fa-first-order"></i>
				</a> <a target="_blank" class="fonty"
					href="manage_kot_bills.jsp">Kot Order Bills</a>
			</div>
		</div>
		<br>
		
		<div class="row justify-content-center">
				<div class="pagetitle col-lg-12 text-center">
					<h1>Room Module</h1>
				</div>
			</div>
		<div class="container" style="border-radius: 25px;">
			
			<div class="block">
				<a target="_blank" href="manage_room_available.jsp"> <i
					class="ri-community-fill"></i>
				</a> <a target="_blank" class="fonty" href="manage_room_available.jsp">Available Room</a>
			</div>
			<div class="block">
				<a target="_blank" href="manage_room_booked.jsp"> <i
					class="ri-community-line"></i>
				</a> <a target="_blank" class="fonty" href="manage_room_booked.jsp">Booked Room</a>
			</div>


			<div class="block">
				<a target="_blank" href="manage_room_bills.jsp"> <i
					class="ri-hotel-fill"></i>
				</a> <a target="_blank" class="fonty" href="manage_room_bills.jsp">Room Bills</a>
			</div>
		</div>
	</main>
	<!-- End #main -->


	<br />
	<br />

	<!-- ======= Footer ======= -->
	<%@include file="include/footer.jsp"%>
	<!-- End Footer -->


</body>

</html>