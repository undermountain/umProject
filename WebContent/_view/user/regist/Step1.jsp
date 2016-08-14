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
	<div id="body" class="container">
		<p>
			下記を入力してください。
		</p>
		<%=model.errorMessageHtml() %>
		<form method="post">
			<%=model.writeTokenInput() %>
			<%=model.getElement("inputdiv").toHtml() %>
		</form>
	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>