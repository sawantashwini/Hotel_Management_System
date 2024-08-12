<%@page import="com.service.OrderService"%>
<%@page import="com.dto.OrderBillDto"%>
<%
	String mobile_no = request.getParameter("mobile_no");


OrderBillDto dto = new OrderBillDto();

OrderService ser = new OrderService();

	dto = ser.getCustomerInfoByMobileNo(mobile_no, config, request);

	if (dto.getId() > 0) {
%>

<input id="check_cust" type="hidden" value="done" />

<input id="cust_id_val" type="hidden" value="<%=dto.getId()%>" />
<input id="cust_name_val" type="hidden" value="<%=dto.getCust_name()%>" />
<input id="cust_mob_no_val" type="hidden" value="<%=dto.getCust_mob_no()%>" />
<input id="cust_address_val" type="hidden" value="<%=dto.getCust_address()%>" />
<input id="cust_company_val" type="hidden" value="<%=dto.getCompany_name()%>" />
<input id="cust_gst_val" type="hidden" value="<%=dto.getGst_no()%>" />

<input id="cust_old_due_val" type="hidden" value="<%=dto.getOld_due_amount()%>" />

<input id="cust_dob" type="hidden" value="<%=dto.getDob()%>" />
<input id="cust_doa" type="hidden" value="<%=dto.getDoa()%>" />
<%
	} else {
%>
<input id="check_cust" type="hidden" value="notdone" />
<%
	}
%>