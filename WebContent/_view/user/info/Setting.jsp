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
			<%=model.writeTokenInput(request) %>
			<table>

				<tr>
					<th>
						<%=model.getField("password1").displayName %>
					</th>
					<td>
						<%=model.getField("password1").toHtml() %>
					</td>
				</tr>
				<tr>
					<th>
						<%=model.getField("password2").displayName %>
					</th>
					<td>
						<%=model.getField("password2").toHtml() %>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">
							登録
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>