<%@page import="common.web.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.base.FieldBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Model model=(Model)request.getAttribute("model");

%>
<jsp:include page="/_view/_common/prefix.jsp"></jsp:include>
	<h3 class="heading">
		<%=model.heading %>
	</h3>
	<ol class="breadcrumb">
		<li><a href="index">テーブル一覧</a></li>
		<li><%=model.getElement("back").toHtml() %></li>
		<li class="active"><%=model.heading %></li>
	</ol>

	<div id="body" class="container">

		<form method="post">
		<%=model.writeTokenInput() %>

		<%=model.InputTableToHtml() %>
		</form>
	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>