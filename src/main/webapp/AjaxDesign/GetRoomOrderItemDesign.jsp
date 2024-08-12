<%@page import="com.dto.TableDto"%>
<%@page import="com.service.TableService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.OrderItemDto"%>
<%@page import="com.service.RoomService"%>
<%@page import="com.service.LogFileService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // Get the room_id parameter from the request
    int bill_id_fk = Integer.parseInt(request.getParameter("bill_id_fk") == null ? "0" : request.getParameter("bill_id_fk"));
    

	RoomService ser = new RoomService();
	int i=0;
	ArrayList<OrderItemDto> list = ser.getRoomOrderItemInfoByRoomId(bill_id_fk, config, request);
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
		<td align="center"><%=LogFileService.changeFormate(dto.getOrder_date() , "yyyy-MM-dd", "dd-MM-yyyy")%>
			<input id="order_date" type="hidden" name="Order_date" value="<%=LogFileService.changeFormate(dto.getOrder_date() , "yyyy-MM-dd", "dd-MM-yyyy")%>">
		</td>
		<td>
			<i class="bi bi-trash main-color" onclick="deleteItem('<%=dto.getId()%>');"> </i>
		</td>
	</tr>
	
	
	
<%
	}

	
%>