<%@page import="common.web.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="common.base.FieldBase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Model model=(Model)request.getAttribute("model");

%>

<jsp:include page="/_view/_common/prefix.jsp"></jsp:include>
<style>
	.table1 td{
		padding:10px 20px;
	}
</style>
	<h3 class="heading">
		<%=model.heading %>
	</h3>
	<div id="body" class="container">
		<form method="post">
			<h4>
				サイトに関して
			</h4>
			<div style="padding:10px;">
				<table class="table1">
					<tr>
						<th>
							サイト名
						</th>
						<td>
							UMサイト（ユーエムサイト）
						</td>
					</tr>
					<tr>
						<th>
							サイト製作者
						</th>
						<td>
							山下 裕貴
						</td>
					</tr>



				</table>
			</div>

		</form>
	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>
