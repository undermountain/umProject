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

		<div class="row">
			<div class="col-lg-4 callout">
			    <h2><a href="/um/dbsiter/home/index">DBSiter</a></h2>
			    <p>いつでもどこでもアクセスできるオリジナルデータベースを作成できます。</p>
			</div><!-- col-lg-4 -->
			<div class="col-lg-4 callout">
				<h2>SNSiter</h2>
				<p>オリジナルのサウンドノベルを作成してWEBで公開できます。</p>
			</div><!-- col-lg-4 -->
			<div class="col-lg-4 callout">
				<h2>HPSiter</h2>
				<p>ホームページを簡単に作成できます。</p>
			</div>
		</div>

	</div>

<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>
