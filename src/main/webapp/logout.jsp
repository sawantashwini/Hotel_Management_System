<%
	/* session.removeAttribute("role");
	System.out.println(session.getAttribute("role")); */
	session.invalidate();

	response.sendRedirect("login.jsp");
%>