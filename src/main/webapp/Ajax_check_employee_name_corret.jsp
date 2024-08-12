<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.*"%>
<%@page import="com.service.*"%>

<%
String name = (request.getParameter("Name") == null ? "" : request.getParameter("Name"));

EmployeeService ser = new EmployeeService();

if (!name.equalsIgnoreCase("")) {

	int employee_id = ser.checkEmployeeNameIsCorret(name, config, request);

	if (employee_id > 0) {
%>

<input id="employee_id" type="hidden" value="<%=employee_id%>" />

<%
} else {
%>

<input id="employee_id" type="hidden" value="0" />

<%
}

}
%>



