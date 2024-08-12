<%@page import="com.service.EmployeeService"%>

<%
String date = request.getParameter("attenddate");
System.out.println(date);
String chek = request.getParameter("chek"); 
int user_id = Integer.parseInt(request.getParameter("user_id")); 

System.out.println(chek);
EmployeeService ser = new EmployeeService();
boolean b = ser.employeeAttendanceInsert(chek, user_id, date, request);

%>
<input type="text" name="status_val" value="<%=b%>" id="status_val" />
