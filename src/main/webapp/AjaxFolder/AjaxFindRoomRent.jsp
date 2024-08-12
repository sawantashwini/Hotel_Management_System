<%@page import="com.service.*"%>
<%@page import="com.dto.*"%>
<%
String room_type = request.getParameter("Room_type");
String room_no = request.getParameter("Room_no");

RoomService pro_ser = new RoomService();
float room_rent = 0;
room_rent = pro_ser.getRoomInfoByRoomType(room_type, room_no, config, request);
if (!room_type.equals("")) {
%>

<input id="check_rent" type="hidden" value="done" />
<input id="room_rent_val" type="hidden" value="<%=room_rent%>" />


<%
}
 else if (room_type.equals("")) {
%>

<input id="check_rent" type="hidden" value="done" />
<input id="room_rent_val" type="hidden" value="0.0" />


<%
} else {
%>
<input id="check_rent" type="hidden" value="notdone" />
<%
}
%>
