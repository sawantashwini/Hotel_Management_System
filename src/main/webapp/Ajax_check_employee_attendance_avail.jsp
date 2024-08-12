<%@page import="java.util.ArrayList"%>
<%@page import="com.dto.*"%>
<%@page import="com.service.*"%>


<%
String date = (request.getParameter("date") == null ? "" : request.getParameter("date"));
EmployeeService ser = new EmployeeService();

if (!date.equalsIgnoreCase("")) {

	ArrayList<EmployeeDto> emp_list = ser.getEmployeeInfoAfterJoinDate(date, config, request);

	ArrayList<EmployeeDto> avai_emp_list = ser.getUnfillAttendanceInfo(date, request);
	int avil_id = 0;
	int index = 0;

	for (EmployeeDto dto : emp_list) {

		for (EmployeeDto avail_emp_dto : avai_emp_list) {

	if (dto.getId() == avail_emp_dto.getEmployee_id_fk()) {
		avil_id = dto.getId();
	}
		}

		if (avil_id == dto.getId()) {

		} else {
	index++;
%>

<tr class="class_row">
	<td><%=index%></td>
	<td style="text-align: left;"><input type="checkbox"
		value="<%=dto.getId()%>-1" name="chek" id="a<%=dto.getId() + "_1"%>"
		class="class_full_day" onclick="checkOrNot('<%=dto.getId()%>', '1');"
		style="margin-right: 1%;" />Full day<br> <input type="checkbox"
		value="<%=dto.getId()%>-2" name="chek" id="a<%=dto.getId() + "_2"%>"
		class="class_half_day" onclick="checkOrNot('<%=dto.getId()%>', '2');"
		style="margin-right: 1%;" />Half day<br> <input type="checkbox"
		value="<%=dto.getId()%>-3" name="chek" id="a<%=dto.getId() + "_3"%>"
		class="class_leave_day" onclick="checkOrNot('<%=dto.getId()%>', '3');"
		style="margin-right: 1%;" />Leave<br> <input type="checkbox"
		value="<%=dto.getId()%>-0" name="chek" id="a<%=dto.getId() + "_0"%>"
		class="class_absent_day"
		onclick="checkOrNot('<%=dto.getId()%>', '0');"
		style="margin-right: 1%;" />Absent</td>

	<td id="name-td"><%=dto.getName()%></td>
	<td><%=dto.getEmail_id()%></td>
	<td><%=dto.getMobile_no()%></td>
	<td><%=dto.getOther_no()%></td>
	<td><%=dto.getAddress()%></td>
	<td><%=dto.getCity_name()%></td>
	<td><%=dto.getSalary_per_month()%></td>
</tr>
<%
}
}
}

else {
%>
<input id="status_val" type="hidden" value="not_done" />

<%
}
%>



