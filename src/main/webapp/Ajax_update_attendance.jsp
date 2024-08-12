<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.*"%>
<%@page import="com.service.*"%>

<%
int id = (Integer.parseInt(request.getParameter("Id") == null ? "0" : request.getParameter("Id")));
int user_id_fk = (Integer.parseInt(request.getParameter("User_id_fk") == null ? "0" : request.getParameter("User_id_fk")));
int attendance_status = (Integer.parseInt(request.getParameter("Attendance_status") == null ? "0" : request.getParameter("Attendance_status")));

EmployeeService ser = new EmployeeService();

boolean b = ser.updateEmployeeAttendance(id, attendance_status, user_id_fk, request);
if (b) {
%>
<input type="hidden" id="status" value="done">
<%} else {%>
<input type="hidden" id="status" value="not_done">

<%}%>


