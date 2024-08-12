<%@page import="com.service.LiquorPurchaseBillService"%>
<%@page import="com.dto.LiquorPurchaseBillDto"%>
<%@page import="com.service.IngredientsSaleService"%>
<%@page import="com.dto.IngredientsDto"%>
<%@page import="com.service.IngredientsPurchaseService"%>
<%
LiquorPurchaseBillDto dto = new LiquorPurchaseBillDto();

//Integer
dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

dto.setItem_id_fk(
		Integer.parseInt(request.getParameter("Item_id_fk") == null ? "0" : request.getParameter("Item_id_fk")));

dto.setBill_id_fk(
		Integer.parseInt(request.getParameter("Bill_id_fk") == null ? "0" : request.getParameter("Bill_id_fk")));

dto.setQuantity(Float.parseFloat(
		request.getParameter("Quantity") == null || request.getParameter("Quantity") == "" ? "0.00"
		: request.getParameter("Quantity")));

dto.setPrice(
		Float.parseFloat(request.getParameter("Price") == null || request.getParameter("Price") == "" ? "0.00"
		: request.getParameter("Price")));

dto.setTotal_price(
		Float.parseFloat(request.getParameter("Total_price") == null || request.getParameter("Total_price") == "" ? "0.00"
		: request.getParameter("Total_price")));

dto.setItem_name(request.getParameter("Item_name") == null ? "0" : request.getParameter("Item_name"));
dto.setItem_code(request.getParameter("Item_code") == null ? "0" : request.getParameter("Item_code"));


LiquorPurchaseBillService ser = new LiquorPurchaseBillService();
boolean b = false;

	if (dto.getId() == 0) {
		b = ser.insertOnePurchaseBillItem(dto, request, config);
	} else {
		b = ser.deleteOnePurchaseBillItem(dto.getId(), request, config);
	}

%>

<input id="item_result" type="hidden" value="<%=b%>" />
