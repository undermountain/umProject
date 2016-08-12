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
		<li><%=model.getElement("back").toHtml() %></li>
		<li class="active"><%=model.heading %></li>
	</ol>
	<div id="body" class="container">
	<ul class="nav nav-tabs">
		<li role="presentation"><%=model.getElement("edit").toHtml() %></li>
		<li role="presentation" class="active"><a>列編集</a></li>
	</ul>
	<br>
		<%=model.writeTokenInput(request) %>

		<div>
			<%=model.getElement("createlink").toHtml() %><br>
			<br>

			<%=model.getElement("list").toHtml() %>

		</div>

	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>