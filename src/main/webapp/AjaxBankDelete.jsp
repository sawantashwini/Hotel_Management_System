<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@page import="com.service.* "%>


<%
//Lab Service Delete
DeleteBankService ser_main = new DeleteBankService();

int id = Integer.parseInt(request.getParameter("id") == null ? "0" : request.getParameter("id"));
String type = request.getParameter("type") == null ? "" : request.getParameter("type");
boolean delstatus = false;


//Common module

if (type.equals("Bank")) {
	delstatus = ser_main.deleteBank(id, request, config);
	if (delstatus == true) {
		response.sendRedirect("manage_bank.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_bank.jsp?msg=NoDel");
	}
}

int cash_id = Integer.parseInt(request.getParameter("cash_id_fk") == null ? "0" : request.getParameter("cash_id_fk"));
int online_id = Integer.parseInt(request.getParameter("online_id_fk") == null ? "0" : request.getParameter("online_id_fk"));
if (type.equals("Bank_Cash")) {
	delstatus = ser_main.deleteBankCashCredit(id,cash_id,  online_id, request, config);
	
	if (delstatus == true) {
		response.sendRedirect("manage_bank_cash_credit.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_bank_cash_credit.jsp?msg=NoDel");
	}
}

int deb_online_id = Integer.parseInt(request.getParameter("debit_online_id_fk") == null ? "0" : request.getParameter("debit_online_id_fk"));
int cred_online_id = Integer.parseInt(request.getParameter("credit_online_id_fk") == null ? "0" : request.getParameter("credit_online_id_fk"));

if (type.equals("Bank_Transaction")) {
	delstatus = ser_main.deleteBankTransaction(id, deb_online_id, cred_online_id,  request, config);
	
	if (delstatus == true) {
		response.sendRedirect("manage_bank_transaction.jsp?msg=YesDel");
	} else {
		response.sendRedirect("manage_bank_transaction.jsp?msg=NoDel");
	}
	
}
System.out.println(delstatus);
%>
<input type="hidden" id="delstatus" value="<%=delstatus%>" />