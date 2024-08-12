<%@page import="com.service.DealerService"%>
<%@page import="com.dto.DealerDto"%>
<%
	String name = request.getParameter("name");


DealerDto dto = new DealerDto();

DealerService ser = new DealerService();

	dto = ser.getDealerInfoByName(name, config, request);

	if (dto.getId() > 0) {
%>

<input id="check_dealer" type="hidden" value="done" />

<input id="dealer_id_val" type="hidden" value="<%=dto.getId()%>" />
<input id="name_val" type="hidden" value="<%=dto.getName()%>" />
<input id="mobile_no_val" type="hidden" value="<%=dto.getMobile_no()%>" />
<input id="address_val" type="hidden" value="<%=dto.getAddress()%>" />
<input id="gst_no_val" type="hidden" value="<%=dto.getGst_no()%>" />


<%
	} else {
%>
<input id="check_dealer" type="hidden" value="notdone" />
<%
	}
%>