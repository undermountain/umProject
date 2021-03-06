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
	<ul class="nav nav-tabs">
		<li>&nbsp;</li>
		<li role="presentation"><%=model.getElement("dataindex").toHtml() %></li>
		<li role="presentation" class="active"><a>テーブル設定</a></li>
		<li role="presentation"><%=model.getElement("editindex").toHtml() %></li>
	</ul>

	<div id="body" class="container">
		<br>
		<div class="panel <%=model.getData("panel")%>">
			<div class="panel-heading">
				パブリックURL
			</div>
			<div class="panel-body">
				<%=model.getElement("publicurl").toHtml() %>
			</div>
			<div class="panel-footer">
				上記のURLからログイン不要の閲覧が可能です。　<%=model.getElement("disp").toHtml() %>
			</div>
		</div>
		<hr>
		<form method="post">
			<%=model.writeTokenInput() %>

			<div>
				<%=model.getField("nameold").toHtml() %>
				<div class="form-group">
					<label>
					<%=model.getField("name").displayName %>
					</label>
					<%=model.getField("name").toHtml() %>
					<%=model.getField("name").toErrorMessage() %>
					<div style="text-align:center;padding:10px;">
						<button type="submit">
							変更
						</button>
					</div>
				</div>
			</div>
		</form>

	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>