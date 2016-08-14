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
		<%=model.errorMessageHtml() %>
		<form method="post">
			<%=model.writeTokenInput() %>
			<div class="center-block">
				<p>
					<span class="nowrap">
						ログインをしてください。
					</span>
					<span class="nowrap">
						新規登録は<a href="/um/user/regist/step1">こちらから</a>。
					</span>


				</p>

				<div class="input-group">
					<label>
						<%=model.getField("id").displayName %>
					</label>
						<%=model.getField("id").toHtml() %>
					<label>
						<%=model.getField("password").displayName %>
					</label>
						<%=model.getField("password").toHtml() %>

					<div style="text-align:center;padding:20px;">
						<button type="submit">
							ログイン
						</button>
					</div>

				</div>

			</div>
		</form>
	</div>
<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>