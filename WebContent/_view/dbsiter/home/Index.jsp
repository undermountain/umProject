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
		<h4>Web上にデータベースを作成</h4>
		<hr class="under-h">
		<div class="indent">
			インターネット上でいつでも編集・閲覧のできる「データベース」を作成できます。
			<p>
				<a href="/um/dbsiter/table/index">テーブルの作成はこちらから</a>
			</p>
		</div>
		<h4>作成したデータベースを別サイトで利用</h4>
		<hr class="under-h">
		<div class="indent">
			<a href="/um/dbsiter/table/index">テーブル一覧画面</a>から各テーブルの「テーブル編集」ボタンで遷移した画面に記載されている「パブリックURL」を利用して他サイトで作成・編集したテーブルを表示させることができます。<br>
			<br>
			<h5>HTML記述例</h5>
			<pre>
	<%=common.web.Util.htmlEncode("<iframe src=\"[パブリックURL]\"></iframe>") %></pre>
			※上記記述例は、高さ・横幅を調整していません。
		</div>
		<form method="post">

		</form>
	</div>
<br>
<jsp:include page="/_view/_common/suffix.jsp"></jsp:include>
