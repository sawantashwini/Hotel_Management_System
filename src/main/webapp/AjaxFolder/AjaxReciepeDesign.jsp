
<%@page import="com.service.ReciepeRelationService"%>
<%@page import="com.dto.ReciepeRelationDto"%>
<%@page import="java.util.ArrayList"%>
<%
String menu_name = request.getParameter("Menu_name") == null ? "" : request.getParameter("Menu_name");
ReciepeRelationService ser = new ReciepeRelationService();
ArrayList<ReciepeRelationDto> list = ser.getInfoByMenuName(menu_name, config, request);

int i = 1;

for (ReciepeRelationDto ing_dto : list) {
%>

<tr>
	<td><%=ing_dto.getIngredient_name()%> <input type="hidden"
		id="ingredient_name<%=i%>"  name="Ingredient_name"
		value="<%=ing_dto.getIngredient_name()%>"> <input type="hidden"
		id="ingredient_id_fk<%=i%>" name="Item_id_fk"
		value="<%=ing_dto.getIngredient_id_fk()%>"></td>
	<td><%=ing_dto.getReciepe_ratio()%> <input type="hidden"
		id="item_quantity<%=i%>" name="Item_quantity"
		value="<%=ing_dto.getReciepe_ratio()%>"></td>
	<td><i class="bi bi-trash main-color"
		onclick="deleteReciepe('<%=ing_dto.getId()%>','<%=ing_dto.getMenu_name()%>',);">
	</i></td>
</tr>

<%
i = i + 1;
}
%>
