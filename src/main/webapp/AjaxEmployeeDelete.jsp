<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.service.DeleteEmployeeService"%>
<%
DeleteEmployeeService service = new DeleteEmployeeService();
boolean delstatus = false;

int return_id = Integer.parseInt(request.getParameter("return_id") == null ? "0" : request.getParameter("return_id"));
int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));


//Employee Delete

int employee_id = Integer.parseInt(request.getParameter("employee_id") == null ? "0"
		: request.getParameter("employee_id"));

if(employee_id>0){
	delstatus = service.deleteEmployee(employee_id, request,config);
	if (delstatus==true) {
		response.sendRedirect("../manage_employee.jsp?msg=YesDel&alert_title=Employee");
	} else {
		response.sendRedirect("../manage_employee.jsp?msg=NoDel&alert_title=Employee");
	}
}
int attendance_id = Integer.parseInt(request.getParameter("attendance_id") == null ? "0"
		: request.getParameter("attendance_id"));
String m = request.getParameter("m") == null ? "" : request.getParameter("m");
String y = request.getParameter("y") == null ? "" : request.getParameter("y");
String n = request.getParameter("n") == null ? "" : request.getParameter("n");
if(attendance_id>0&&id>0&&m!=""&&y!=""&&n!=""){
	delstatus = service.deleteEmployeeAttendance(attendance_id, request,config);
	if (delstatus==true) {
		response.sendRedirect("employee_attendence_report.jsp?msg=YesDel&id="+id+"&m="+m+"&y="+y+"&n="+n);
	} else {
		response.sendRedirect("employee_attendence_report.jsp?msg=NoDel&id="+id+"&m="+m+"&y="+y+"&n="+n);
	}
}

int employee_transaction_id = Integer.parseInt(request.getParameter("employee_transaction_id") == null ? "0"
		: request.getParameter("employee_transaction_id"));
if(employee_transaction_id>0&&id>0){
	delstatus = service.deleteEmployeeTransaction(employee_transaction_id, request,config);
	if (delstatus==true) {
		response.sendRedirect("manage_employee_account.jsp?msg=YesDel&id="+id);
	} else {
		response.sendRedirect("manage_employee_account.jsp?msg=NoDel&id="+id);
	}
}

int employee_salary_id = Integer.parseInt(request.getParameter("employee_salary_id") == null ? "0"
		: request.getParameter("employee_salary_id"));

if(employee_salary_id>0 && id>0){
	delstatus = service.deleteEmployeeSalary(employee_salary_id, request,config);
	if (delstatus==true) {
		response.sendRedirect("manage_employee_salary.jsp?msg=YesDel&id="+id);
	} else {
		response.sendRedirect("manage_employee_salary.jsp?msg=NoDel&id="+id);
	}
}

System.out.println(delstatus);
%>
<input type="hidden" id="delstatus" value="<%=delstatus%>" />
