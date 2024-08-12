<%@page import="com.dto.TableDto"%>
<%@page import="com.service.TableService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.OrderItemDto"%>
<%@page import="com.service.OrderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // Get the table_id parameter from the request
    int table_id = Integer.parseInt(request.getParameter("table_id") == null ? "0" : request.getParameter("table_id"));
    int order_id = Integer.parseInt(request.getParameter("order_id") == null ? "0" : request.getParameter("order_id"));
	    
String flag = request.getParameter("flag") == null ? "" : request.getParameter("flag");

	if(!flag.equals("kot")){
	OrderService ser = new OrderService();
	int i=0;
	ArrayList<OrderItemDto> list = ser.getOrderItemInfoByTableId(table_id,order_id, config, request);
	for (OrderItemDto dto : list) {
		i++;
		
%>



	<tr>
		<td align="center"><%=dto.getItem_code() %>
			<input id="item_code<%=i%>" type="hidden" name="Item_code" value="ItemCode1">
			<input id="item_name<%=i%>" type="hidden" name="Item_name" value="<%=dto.getItem_name() %>">
			<input id="item_id<%=i%>" type="hidden" name="Item_id" value="<%=dto.getItem_id_fk() %>">
		
		</td>
		
			
		
		<td align="center"><%=dto.getItem_rate() %>
			<input id="item_price<%=i%>" type="hidden" name="Item_price" value="<%=dto.getItem_rate() %>">
		</td>
		
		<td align="center"><%=dto.getItem_qty() %>
			<input id="item_qty<%=i%>" type="hidden" name="Item_qty" value="<%=dto.getItem_qty() %>">
		</td>
		<%float total_price=dto.getItem_rate()*dto.getItem_qty(); %>
		<td align="center"><%=total_price %>
			<input id="total_price_with_qty" type="hidden" name="Total_price_with_qty" value="<%=total_price%>">
		</td>
		
		<td>
			<i class="bi bi-trash main-color" onclick="deleteItem('<%=dto.getId()%>');"> </i>
		</td>
	</tr>
	
	
	
<%
	}
}
	TableService tb_ser = new TableService();
	TableDto tab_dto = tb_ser.getTableInfoById(table_id, config, request);
%>
<input id="table_name_val" type="hidden" value="<%=tab_dto.getName() %>">
<input id="manager_name_val" type="hidden" value="<%=tab_dto.getManager_name() %>">