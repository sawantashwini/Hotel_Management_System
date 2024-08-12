<%@page import="com.service.*"%>
<%@page import="com.dto.*"%>
<%
String name = request.getParameter("name");

MenuItemDto dto = new MenuItemDto();

MenuItemService ser = new MenuItemService();

dto = ser.getMenuItemInfoByName(name, config, request);

if (dto.getId() > 0) {
%>
<input id="check_menu" type="hidden" value="done" />
<input id="id_val" type="hidden" value="<%=dto.getId()%>" />
<input id="menu_name_val" type="hidden" value="<%=name%>" />

<%
} else {
%>
<input id="check_menu" type="hidden" value="notdone" />
<%
}
%>