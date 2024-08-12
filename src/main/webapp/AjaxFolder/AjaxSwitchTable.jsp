<%@page import="com.service.OrderService"%>
<%

    int old_table_id = Integer.parseInt(request.getParameter("old_table_id") == null ? "0" : request.getParameter("old_table_id"));
    int new_table_id = Integer.parseInt(request.getParameter("new_table_id") == null ? "0" : request.getParameter("new_table_id"));
    String manager_name = request.getParameter("manager_name") == null ? "Admin" : request.getParameter("manager_name");
    
    OrderService ser = new OrderService();
    boolean result = ser.switchTable(old_table_id, new_table_id,manager_name, request, config);
  
    
%>
<input type="hidden" id="switch_status" value="<%=result%>"/>


