<%@page import="com.dto.ArrayOrderItemDto"%>
<%@page import="com.dto.PaymentDto"%>
<%@page import="com.dto.OrderBillDto"%>
<%@page import="com.service.OrderService"%>
<%@page import="com.service.IngredientsPurchaseService"%>

<%
    OrderBillDto dto = new OrderBillDto();

	dto.setId(Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
	dto.setStatus(request.getParameter("status") == null ? "Active" : request.getParameter("status"));
	dto.setTable_id(Integer.parseInt(request.getParameter("table_id") == null ? "0" : request.getParameter("table_id")));
	dto.setWithout_gst_amount(Float.parseFloat(request.getParameter("without_gst_amount") == null || request.getParameter("without_gst_amount") == "" ? "0.00" : request.getParameter("without_gst_amount")));
    dto.setBill_date(request.getParameter("bill_date") == null ? "" : request.getParameter("bill_date"));
    dto.setSession_year(request.getParameter("session_year") == null ? "" : request.getParameter("session_year"));
    dto.setUser_id_fk(Integer.parseInt(request.getParameter("user_id_fk") == null ? "0" : request.getParameter("user_id_fk")));
    dto.setManager_name(request.getParameter("manager_name") == null ? "" : request.getParameter("manager_name"));
    
    ArrayOrderItemDto arrdto = new ArrayOrderItemDto();

    String item_id = request.getParameter("item_id");
    String item_name = request.getParameter("item_name");
    String item_code = request.getParameter("item_code");
    String item_qty = request.getParameter("item_qty");
    String item_rate = request.getParameter("item_rate");
    
    arrdto.setItem_id_fk(item_id.split(","));
    arrdto.setItem_name(item_name.split(","));
    arrdto.setItem_code(item_code.split(","));
    arrdto.setItem_qty(item_qty.split(","));
    arrdto.setItem_rate(item_rate.split(","));
   
    
	OrderService ser = new OrderService();
	boolean b = false;
	int id=0;
	
	
	if(dto.getId()==0){
	id = ser.insertKotBill(dto, request, config);
	   if(id>0){
	      
	      b = ser.insertKotOrderBillItem(arrdto, id, dto.getTable_id(), request, config);
	      
	    }
	}
%>
<input type="hidden" id="item_result" value="<%=b%>">
<input type="hidden" id="order_id_val" value="<%=id%>">




