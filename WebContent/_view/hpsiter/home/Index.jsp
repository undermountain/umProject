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
		<h4>オリジナルのホームページを簡単作成</h4>
		<hr class="under-h">
		<div class="indent">
			簡単に編集できるオリジナルの「ホームページ」を作成できます。
			<p>
				<a href="/um/hpsiter/page/commonedit">全ページ共通部分の作成はこちらから</a>
			</p>
			<p>
				<a href="/um/hpsiter/page/index">ページの作成はこちらから</a>
			</p>
		</div>
	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>
