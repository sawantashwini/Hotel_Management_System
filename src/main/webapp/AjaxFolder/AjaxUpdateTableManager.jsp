<%@page import="com.service.OrderService"%>
<%

	String manager_name = request.getParameter("manager_name") == null ? "Admin" : request.getParameter("manager_name");
	int table_id = Integer.parseInt(request.getParameter("table_id") == null ? "0" : request.getParameter("table_id"));
	
    OrderService ser = new OrderService();
    boolean b = false;
	
    b = ser.UpdateTableManager(table_id, manager_name, request, config);
  
    out.print(b);
%>

