<%@page import="com.service.MenuItemService"%>
<%@page import="com.dto.MenuItemDto"%>
<%
	String item_code = request.getParameter("item_code");


MenuItemDto dto = new MenuItemDto();

MenuItemService ser = new MenuItemService();

	dto = ser.getMenuItemInfoByCode(item_code.trim(), config, request);

	if (dto.getId() > 0) {
%>

<input id="check" type="hidden" value="done" />

<input id="item_id_val" type="hidden" value="<%=dto.getId()%>" />
<input id="item_name_val" type="hidden" value="<%=dto.getItem_name()%>" />
<input id="item_price_val" type="hidden" value="<%=dto.getItem_price()%>" />




<%
	} else {
%>
<input id="check" type="hidden" value="notdone" />
<%
	}
%>