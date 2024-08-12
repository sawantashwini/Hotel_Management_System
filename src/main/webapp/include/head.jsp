<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.mysql.jdbc.exceptions.DeadlockTimeoutRollbackMarker"%>
<%@page import="com.dto.*"%>
<%@page import="com.service.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>

<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<!DOCTYPE html>
<html lang="en">

<%@include file="../UserTrack.jsp"%>

<%
ProjectService project_head_ser = new ProjectService();
ProjectDto project_head_dto = project_head_ser.getProjectInfoById(1, config, request);

UserService user_head_ser = new UserService();
UserDto user_head_dto = user_head_ser.getuserInfoById(user_id, config, request);
String current_date = LogFileService.changeFormateOfCurrentDate("yyyy-MM-dd");
String current_date_time = LogFileService.changeFormateOfCurrentDate("yyyy-MM-dd'T'HH:mm");

String activation_date = project_head_dto.getActivation_date();

if (activation_date == null) {

}
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

Date date1 = formatter.parse(activation_date);
Date date2 = formatter.parse(current_date);
Boolean expire = date1.before(date2);

if (expire) {
	response.sendRedirect("login.jsp?msg=expired");

}
%>
<head>



<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title><%=project_head_dto.getName()%></title>
<link href="ProjectImage/<%=project_head_dto.getId()%>.jpg" rel="icon" />
<meta content="" name="description">
<meta content="" name="keywords">

<!-- ======= css ======= -->
<%@include file="../include/css.jsp"%>
<!-- ======= css end======= -->
<style type="text/css">
.info-table tr, .info-table th, .info-table td {
	font-size: 15px;
	border: 1px solid rgba(5, 5, 5, 0.14);
}

.info-table th {
	padding-left: 20px;
	text-align: right;
	background-color: rgba(99, 66, 66, 0.08);
	border: none;
	color: #f03c02;
}


.info-table th:hover, .info-table td:hover {
	background-color: rgba(5, 5, 5, 0.04);
}
</style>

<%int menu_table=1;%>