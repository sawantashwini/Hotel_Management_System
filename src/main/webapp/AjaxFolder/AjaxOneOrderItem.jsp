<%@page import="com.dto.OrderItemDto"%>
<%@page import="com.service.OrderService"%>
<%@page import="com.service.IngredientsPurchaseService"%>
<%
OrderItemDto item_dto = new OrderItemDto();

item_dto.setId(Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
item_dto.setBill_id_fk(Integer.parseInt(request.getParameter("order_id") == null ? "0" : request.getParameter("order_id")));
item_dto.setTable_id(Integer.parseInt(request.getParameter("table_id") == null ? "0" : request.getParameter("table_id")));
item_dto.setItem_id_fk(Integer.parseInt(request.getParameter("item_id") == null ? "0" : request.getParameter("item_id")));
item_dto.setItem_name(request.getParameter("item_name") == null ? "" : request.getParameter("item_name"));
item_dto.setItem_code(request.getParameter("item_code") == null ? "" : request.getParameter("item_code"));
item_dto.setItem_qty(Float.parseFloat(request.getParameter("item_qty") == null || request.getParameter("item_qty") == "" ? "0.00" : request.getParameter("item_qty")));
item_dto.setItem_rate(Float.parseFloat(request.getParameter("item_rate") == null || request.getParameter("item_rate") == "" ? "0.00" : request.getParameter("item_rate")));

OrderService ser = new OrderService();
boolean b = false;
String table_status = "";

if(item_dto.getId() == 0){
	//System.out.println("Hello 1");
 b = ser.insertOneOrderItem(item_dto, request, config);
}
else{
	//System.out.println("Hello 2");
	table_status  = ser.deleteOneOrderItem(item_dto.getId(), request, config);
}
%>

<input id="item_result" type="hidden" value="<%=b%>" />
<input id="free_table_val" type="hidden" value="<%=table_status%>" />
