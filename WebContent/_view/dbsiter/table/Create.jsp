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
		<form method="post">
			<%=model.writeTokenInput() %>
			<div class="form-group">
				<p>
					テーブルの名称を入力してください。
				</p>

				<label>
					<%=model.getField("name").displayName %>
				</label>
				<%=model.getField("name").toHtml() %>
				<%=model.getField("name").toErrorMessage() %>
				<div style="text-align:center;padding:20px;">
					<button type="submit">
						次へ
					</button>
				</div>
			</div>


		</form>
	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>