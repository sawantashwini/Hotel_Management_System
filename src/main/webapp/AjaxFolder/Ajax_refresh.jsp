<%@page import="java.util.ArrayList"%>
<%@page import="com.service.*"%>
<%@page import="com.dto.*"%>
<%
String flag = request.getParameter("Flag") == null ? "" : request.getParameter("Flag");
IngredientsItemService ser = new IngredientsItemService();

if (flag.equals("Ingredients")) {
	ArrayList<IngredientsDto> list1 = ser.getActiveIngredientsItemInfo(config, request);
	for (IngredientsDto dto : list1) {
%>
<option value="<%=dto.getName()%>" />
<%
}

}
MenuItemService menu_ser = new MenuItemService();

if (flag.equalsIgnoreCase("Menu_item")) {
ArrayList<MenuItemDto> menu_list = menu_ser.getActiveMenuItemInfo(config, request);
for (MenuItemDto menu_dto : menu_list) {
%>
<option value="<%=menu_dto.getItem_code()%>" />
<%
}
}
if (flag.equalsIgnoreCase("Menu")) {
%>
<option value="">Select Menu Item</option>
<%
ArrayList<MenuItemDto> me_list = menu_ser.getActiveMenuItemInfo(config, request);
for (MenuItemDto me_dto : me_list) {
%>
<option value="<%=me_dto.getItem_name()%>"><%=me_dto.getItem_name()%></option>
<%
}
}

MeasurementService measer_ser = new MeasurementService();

if (flag.equalsIgnoreCase("Measurement")) {

ArrayList<MeasurementDto> menu_list = measer_ser.getActiveMeasurementInfo(config, request);
%>
<option selected value="0">Select Measurement</option>

<%
for (MeasurementDto measur_dto : menu_list) {
%>
<option value="<%=measur_dto.getId()%>"><%=measur_dto.getName()%>
</option>
<%
}
}
%>


