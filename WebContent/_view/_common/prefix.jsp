<%@page import="common.web.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.base.FieldBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="keywords" content="<%=session.getAttribute("_keywords") %>">
<meta name="description" content="<%=session.getAttribute("_description") %>">
<meta name="robots" content="<%=session.getAttribute("_robots") %>">

<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">

<title><%=session.getAttribute("_globalsitename") %> - <%=session.getAttribute("_sitename") %></title>
	<jsp:include page="/_view/_common/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/_view/_common/header.jsp"></jsp:include>
	<jsp:include page="/_view/_common/menu.jsp"></jsp:include>