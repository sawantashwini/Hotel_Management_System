<%@page import="com.service.ReciepeRelationService"%>
<%@page import="com.dto.ReciepeRelationDto"%>
<%
ReciepeRelationDto dto = new ReciepeRelationDto();

//Integer
dto.setId(Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));

dto.setMenu_id_fk(
		Integer.parseInt(request.getParameter("Menu_id_fk") == null ? "0" : request.getParameter("Menu_id_fk")));
dto.setUser_id_fk(
		Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));

dto.setIngredient_id_fk(Integer
		.parseInt(request.getParameter("Ingredient_id_fk") == null ? "0" : request.getParameter("Ingredient_id_fk")));

dto.setReciepe_ratio(Float.parseFloat(
		request.getParameter("Reciepe_ratio") == null || request.getParameter("Reciepe_ratio") == "" ? "0.00"
		: request.getParameter("Reciepe_ratio")));

dto.setIngredient_name(request.getParameter("Ingredient_name") == null ? "" : request.getParameter("Ingredient_name"));

dto.setMenu_name(request.getParameter("Menu_name") == null ? "" : request.getParameter("Menu_name"));

dto.setStatus("Active");

ReciepeRelationService ser = new ReciepeRelationService();
boolean b = false;
if (dto.getId() == 0) {
	b = ser.insertInfo(dto, request, config);
} else {
	b = ser.deleteReciepeRelation(dto.getId(), request, config);
}
%>

<input id="reciepe_result" type="hidden" value="<%=b%>" />
