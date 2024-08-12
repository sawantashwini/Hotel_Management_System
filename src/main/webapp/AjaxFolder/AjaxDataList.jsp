<%@page import="com.dto.*"%>
<%@page import="com.service.*"%>
<%@page import="java.util.ArrayList"%>
<%
String list_type = request.getParameter("type") == null ? "" : request.getParameter("type");
int parent_id = Integer.parseInt(request.getParameter("parent_id") == null ? "0" : request.getParameter("parent_id"));
System.out.println("Type: " + list_type + ", Parent Id: " + parent_id);
if (list_type.equalsIgnoreCase("list_menu_item")) {
%>
<%
MenuItemService ser = new MenuItemService();
ArrayList<MenuItemDto> list = ser.getActiveMenuItemInfo(config, request);
for (MenuItemDto dto : list) {
%>
<option value="<%=dto.getItem_code()%>"
	data-item_name="<%=dto.getItem_name()%>"
	data-item_rate="<%=dto.getItem_price()%>"
	data-status="<%=dto.getStatus()%>" data-id="<%=dto.getId()%>" />
<%
}
}
if (list_type.equalsIgnoreCase("list_menu_item_food")) {
MenuItemService menu_ser = new MenuItemService();
ArrayList<MenuItemDto> menu_list = menu_ser.getActiveFoodItemInfo(config, request);
for (MenuItemDto menu_dto : menu_list) {
%>
<option value="<%=menu_dto.getItem_name()%>"
	data-id="<%=menu_dto.getId()%>">

	<%
	}
	}
	if (list_type.equalsIgnoreCase("list_dealer")) {
	%>

	<%
	DealerService service1 = new DealerService();
	ArrayList<DealerDto> list3 = service1.getDealerInfo(config, request);
	for (DealerDto dto_deal : list3) {
	%>

<option value="<%=dto_deal.getName()%>" data-id="<%=dto_deal.getId()%>" />
<%
}
}
if (list_type.equalsIgnoreCase("list_liquor_item")) {
%>
<%
MenuItemService ser_ing = new MenuItemService();
ArrayList<MenuItemDto> list_liq = ser_ing.getActiveMenuLiquorItemInfo(config, request);
for (MenuItemDto dto_liq : list_liq) {
%>
<option value="<%=dto_liq.getItem_code()%>"
	data-id="<%=dto_liq.getId()%>"
	data-item_name="<%=dto_liq.getItem_name()%>" />
<%
}
}
if (list_type.equalsIgnoreCase("list_income_head")) {
%>
<%
IncomeHeadService ser2 = new IncomeHeadService();
ArrayList<IncomeHeadDto> list2 = ser2.getActiveIncomeHeadInfo(config, request);
for (IncomeHeadDto IncomeHead_dto : list2) {
%>
<option data-id="<%=IncomeHead_dto.getId()%>"
	value="<%=IncomeHead_dto.getName()%>" />
<%
}

}
if (list_type.equalsIgnoreCase("list_spend_head")) {
%>
<%
SpendHeadService ser2 = new SpendHeadService();
ArrayList<SpendHeadDto> list2 = ser2.getActiveSpendHeadInfo(config, request);
for (SpendHeadDto SpendHead_dto : list2) {
%>
<option value="<%=SpendHead_dto.getName()%>"
	data-id="<%=SpendHead_dto.getId()%>" />
<%
}

}
if (list_type.equalsIgnoreCase("list_ingredients")) {
%>

<%
IngredientsItemService ser_ing = new IngredientsItemService();
ArrayList<IngredientsDto> list_ing = ser_ing.getIngredientsItemInfo(config, request);
for (IngredientsDto dto_ing : list_ing) {
%>
<option value="<%=dto_ing.getName()%>" data-id="<%=dto_ing.getId()%>" />
<%
}
}
if (list_type.equalsIgnoreCase("list_bank")) {
%>

<%
BankService ser1 = new BankService();

ArrayList<BankDto> list1 = ser1.getActiveBankInfo(config, request);

for (BankDto Bank_dto : list1) {
%>

<option value="<%=Bank_dto.getName()%>"
	data-credit_bank_id_fk="<%=Bank_dto.getId()%>"
	data-id="<%=Bank_dto.getId()%>"
	data-debit_bank_id_fk="<%=Bank_dto.getId()%>" />


<%
}
}
if (list_type.equalsIgnoreCase("list_city")) {
%>
<%
CityService city_ser = new CityService();
ArrayList<CityDto> city_list = city_ser.getActiveCityInfo(config, request);
for (CityDto city_dto : city_list) {
%>
<option data-id="<%=city_dto.getId()%>" value="<%=city_dto.getName()%>" />
<%
}
}
if (list_type.equalsIgnoreCase("list_measure")) {
%>
<%
MeasurementService meas_ser = new MeasurementService();

ArrayList<MeasurementDto> meas_list = meas_ser.getActiveMeasurementInfo(config, request);

for (MeasurementDto meas_dto : meas_list) {
%>
<option data-id="<%=meas_dto.getId()%>" value="<%=meas_dto.getName()%>" />

<%
}
}
if (list_type.equalsIgnoreCase("list_liquor_category")) {
%>
<%
LiquorCategoryService liq_ser = new LiquorCategoryService();
ArrayList<LiquorCategoryDto> liq_list = liq_ser.getActiveLiquorCategoryInfo(config, request);
for (LiquorCategoryDto liq_dto : liq_list) {
%>
<option data-id="<%=liq_dto.getId()%>" value="<%=liq_dto.getName()%>" />
<%
}
}
%>


<%
if (list_type.equalsIgnoreCase("list_customer")) {
	CustomerService service1 = new CustomerService();
	ArrayList<CustomerDto> list3 = service1.getCustomerInfo(config, request);
	for (CustomerDto dto_cust : list3) {
%>
<option value="<%=dto_cust.getName()%>" data-id="<%=dto_cust.getId()%>"
	data-cust_name="<%=dto_cust.getName()%>"
	data-cust_mobile="<%=dto_cust.getMobile_no()%>"
	data-cust_address="<%=dto_cust.getAddress()%>"
	data-cust_gst_no="<%=dto_cust.getGst_no()%>"
	data-company_name="<%=dto_cust.getCompany_name()%>" />
<%
}
}
if (list_type.equalsIgnoreCase("list_employee")) {
%>

<%
EmployeeService e_ser = new EmployeeService();
ArrayList<EmployeeDto> e_list = e_ser.getEmployeeName(config, request);
for (EmployeeDto e_dto : e_list) {
%>
<option value="<%=e_dto.getName()%>" data-id="<%=e_dto.getId()%>" />
<%
}
%>
<%
}
%>
