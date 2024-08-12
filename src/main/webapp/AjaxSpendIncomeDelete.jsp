<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.service.DeleteSpendIncomeService"%>
<%
DeleteSpendIncomeService service = new DeleteSpendIncomeService();
boolean delstatus = false;
/* 
//Delete Spend Head
int spend_head_id = Integer.parseInt(request.getParameter("spend_head_id") == null ? "0" : request.getParameter("spend_head_id"));

if (spend_head_id > 0) {
	delstatus = service.deleteSpendHead(spend_head_id, request, config);
	if (delstatus == true) {
		response.sendRedirect("manage_spend_head.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_spend_head.jsp?msg=NoDel");
	}
} */

//Delete Spend
int spend_id = Integer.parseInt(request.getParameter("spend_id") == null ? "0" : request.getParameter("spend_id"));

if (spend_id > 0) {
	delstatus = service.deleteSpend(spend_id, request, config);
	if (delstatus == true) {
		response.sendRedirect("manage_spend.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_spend.jsp?msg=NoDel");
	}
}

/* //Delete income_head
int income_head_id = Integer.parseInt(request.getParameter("income_head_id") == null ? "0" : request.getParameter("income_head_id"));

if (income_head_id > 0) {
	delstatus = service.deleteIncomeHead(income_head_id, request, config);
	if (delstatus == true) {
		response.sendRedirect("manage_income_head.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_income_head.jsp?msg=NoDel");
	}
} */

//Delete income
int income_id = Integer.parseInt(request.getParameter("income_id") == null ? "0" : request.getParameter("income_id"));

if (income_id > 0) {
	delstatus =  service.deleteIncome(income_id, request, config);
	if (delstatus == true) {
		response.sendRedirect("manage_income.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_income.jsp?msg=NoDel");
	}
}

System.out.println(delstatus);
%>
<input type="hidden" id="delstatus" value="<%=delstatus%>" />
