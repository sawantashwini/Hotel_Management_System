

<%@page import="com.service.IngredientsItemService"%>
<%@page import="com.dto.IngredientsDto"%>
<%
String item_name = request.getParameter("item_name");

IngredientsDto dto = new IngredientsDto();

IngredientsItemService pro_ser = new IngredientsItemService();

dto = pro_ser.getIngredientsItemInfoByName(item_name.trim(), config, request);
System.out.println(dto.getId());
if (dto.getId() > 0) {
%>

<input id="check_item" type="hidden" value="done" />
<input id="item_id_val" type="hidden" value="<%=dto.getId()%>" />
<input id="sale_qty_val" type="hidden" value="<%=dto.getItem_quantity()%>" />


<%
} else {
%>
<input id="check_item" type="hidden" value="notdone" />
<%
}
%>