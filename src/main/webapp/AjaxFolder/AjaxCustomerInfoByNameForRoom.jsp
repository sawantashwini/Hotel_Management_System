<%@page import="com.service.CustomerService"%>
<%@page import="com.dto.CustomerDto"%>
<%
	String name = request.getParameter("name");


CustomerDto dto = new CustomerDto();

CustomerService ser = new CustomerService();

	dto = ser.getCustomerInfoByName(name, config, request);

	if (dto.getId() > 0) {
%>

<input id="check_cust" type="hidden" value="done" />

<input id="cust_id_val" type="hidden" value="<%=dto.getId()%>" />
<input id="cust_name_val" type="hidden" value="<%=dto.getName()%>" />
<input id="cust_mobile_val" type="hidden" value="<%=dto.getMobile_no()%>" />
<input id="cust_address_val" type="hidden" value="<%=dto.getAddress()%>" />
<input id="cust_gst_no_val" type="hidden" value="<%=dto.getGst_no()%>" />
<input id="company_name_val" type="hidden" value="<%=dto.getCompany_name()%>" />
<input id="cust_dob" type="hidden" value="<%=dto.getDob()%>" />
<input id="cust_doa" type="hidden" value="<%=dto.getDoa()%>" />


<%
	} else {
%>
<input id="check_cust" type="hidden" value="notdone" />
<%
	}
%>