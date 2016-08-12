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
		<li><%=model.getElement("back1").toHtml() %></li>
		<li><%=model.getElement("back2").toHtml() %></li>
		<li class="active"><%=model.heading %></li>
	</ol>
	<div id="body" class="container">
		<form method="post" enctype="multipart/form-data">
			<%=model.writeTokenInput(request) %>

			<%=model.getElement("inputtable").toHtml() %>
		</form>
	</div>
<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>