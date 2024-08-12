<%@page import="com.service.IngredientsSaleService"%>
<%@page import="com.dto.IngredientsDto"%>
<%@page import="com.service.IngredientsPurchaseService"%>
<%
IngredientsDto dto = new IngredientsDto();

//Integer
dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

dto.setItem_id_fk(
		Integer.parseInt(request.getParameter("Item_id_fk") == null ? "0" : request.getParameter("Item_id_fk")));

dto.setBill_id_fk(
		Integer.parseInt(request.getParameter("Bill_id_fk") == null ? "0" : request.getParameter("Bill_id_fk")));

dto.setItem_quantity(Float.parseFloat(
		request.getParameter("Item_quantity") == null || request.getParameter("Item_quantity") == "" ? "0.00"
		: request.getParameter("Item_quantity")));

dto.setItem_amt(
		Float.parseFloat(request.getParameter("Item_amt") == null || request.getParameter("Item_amt") == "" ? "0.00"
		: request.getParameter("Item_amt")));

dto.setItem_rate(
		Float.parseFloat(request.getParameter("Item_rate") == null || request.getParameter("Item_rate") == "" ? "0.00"
		: request.getParameter("Item_rate")));

dto.setItem_name(request.getParameter("Item_name") == null ? "0" : request.getParameter("Item_name"));

String flag = request.getParameter("Flag") == null ? "0" : request.getParameter("Flag");

IngredientsPurchaseService ser = new IngredientsPurchaseService();
IngredientsSaleService sale_ser = new IngredientsSaleService();
boolean b = false;
if(flag.equals("Sale")){
	if (dto.getId() == 0) {
		b = sale_ser.insertOneIngredientsSaleBillItem(dto, request, config);
	} else {
		b = sale_ser.deleteOneIngredientsSaleBillItem(dto.getId(), dto.getItem_id_fk(),  dto.getItem_quantity(), request, config);
	}
}else{
	if (dto.getId() == 0) {
		b = ser.insertOneIngredientsPurchaseBillItem(dto, request, config);
	} else {
		b = ser.deleteOneIngredientsPurchaseBillItem(dto.getId(), dto.getItem_id_fk(), dto.getItem_quantity(), request, config);
	}
}

%>

<input id="item_result" type="hidden" value="<%=b%>" />
