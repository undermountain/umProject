<%@page import="common.web.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.base.FieldBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Model model=(Model)request.getAttribute("model");
%>
<jsp:include page="/_view/_common/prefix.jsp"></jsp:include>

<link rel="stylesheet" href="/um/contents/jquery-ui.min.css">
<script type="text/javascript" src="/um/scripts/jquery-ui.min.js"></script>

	<h3 class="heading">
		<%=model.heading %>
	</h3>
	<ol class="breadcrumb">
		<li><%=model.getElement("back").toHtml() %></li>
		<li class="active"><%=model.heading %></li>
	</ol>
	<ul class="nav nav-tabs">
		<li>&nbsp;</li>
		<li role="presentation"><%=model.getElement("dataindex").toHtml() %></li>
		<li role="presentation"><%=model.getElement("edit").toHtml() %></li>
		<li role="presentation" class="active"><a>列編集</a></li>
	</ul>

	<div id="body" class="container">
		<br>
		<form method="post">
			<%=model.writeTokenInput() %>

			<div>
				<table>
					<tr>
						<td>
						<%=model.getElement("createlink").toHtml() %>
						</td>
						<td>
						<%=model.getElement("sort").toHtml() %>
						</td>
					<tr>
				</table>

				<%=model.getElement("list").toHtml() %>

			</div>
		</form>
	</div>

<script type="text/javascript">
$("#list tbody").sortable();
$("#list tbody").bind("sortstop",function(e,ui){

	var nos=$("#list tbody div.no");

	for(var i =0;i<nos.length;i++){
		$(nos.get(i)).children("span").text((i+1).toString());
		$(nos.get(i)).children("input").val(i.toString());
	}

	$("#sort").removeAttr("disabled");
});
</script>
<style type="text/css">
	#list tbody tr:hover{
		cursor:n-resize;
	}
</style>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>