<%@page import="java.util.ArrayList"%>
<%@page import="com.service.EmployeeService"%>
<%@page import="com.dto.EmployeeDto"%>

<%
int id = (Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id")));
String month = request.getParameter("month") == null ? "0" : request.getParameter("month");
String year = request.getParameter("year") == null ? "0" : request.getParameter("year");

EmployeeService service = new EmployeeService();

ArrayList<EmployeeDto> list = service.getAttendenceInfoByEmp(id, month, year, request);
int full =0, half=0, leave=0, absent=0;
for (EmployeeDto dto : list) {
if(dto.getAttendance_status()==0){
	absent++;
}
if(dto.getAttendance_status()==1){
	full++;
}
if(dto.getAttendance_status()==2){
	half++;
}
if(dto.getAttendance_status()==3){
	leave++;
}
}

%>
<input id="full_day_value" type="hidden" value="<%=full%>" />
<input id="half_day_value" type="hidden" value="<%=half%>" />
<input id="leave_day_value" type="hidden" value="<%=leave%>" />
<input id="absent_day_value" type="hidden" value="<%=absent%>" />
