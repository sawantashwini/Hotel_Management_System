
<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

int user_id = (Integer) (session.getAttribute("user_id") == null ? 0 : session.getAttribute("user_id"));

if (user_id == 0) {

	response.sendRedirect("login.jsp");

}
%>




