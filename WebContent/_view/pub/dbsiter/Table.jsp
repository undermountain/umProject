<%@page import="common.web.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.base.FieldBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Model model=(Model)request.getAttribute("model");

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=session.getAttribute("_globalsitename") %> - <%=session.getAttribute("_sitename") %></title>
	<jsp:include page="/_view/_common/head.jsp"></jsp:include>
</head>
<body>
	<%=model.getElement("listTb").toHtml() %>
</body>
</html>