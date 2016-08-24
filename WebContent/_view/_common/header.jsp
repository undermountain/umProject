<%@page import="common.web.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%if(session.getAttribute("_header")==null){ %>
<nav class="navbar navbar-inverse ">
  <div class="container-fluid">
  <!-- ヘッダー部分 ================ -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/um/user/home/index" style="color:white;font-size:25px;font-family: impact;">UM</a>
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#nav_target">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
  <!-- 中央のナビゲーション部分 ================ -->
    <div class="collapse navbar-collapse" id="nav_target">
      <ul class="nav navbar-nav">
        <!-- リンクのみ -->
          <li><a href="/um/dbsiter/home/index">DBSiter</a></li>
          <li ><a href="/um/snsiter/home/index">SNSiter</a></li>
          <li><a href="/um/hpsiter/home/index">HPSiter</a></li>
          <li><a href="/um/pub/home/infomation">Infomation</a></li>
      </ul>
  <!-- 右寄せになる部分 ================ -->
      <ul class="nav navbar-nav navbar-right">
        <!-- リンクのみ -->
          <li><%=common.web.Auth.headerAuthDisp(request,"name","/um/user/info/login","/um/user/info/setting","/um/user/info/logoff") %></li>

      </ul>
    </div>
  </div>
</nav>
<%}else if(session.getAttribute("_header").equals("dbsiter")){ %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
  <!-- ヘッダー部分 ================ -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/um/user/home/index" style="color:white;font-size:25px;font-family: impact;">UM</a>
<a class="navbar-brand <%=Util.isActive(request, "/dbsiter/home/index")%>" href="/um/dbsiter/home/index" style="font-weight:bold;color:yellow;">DBSiter</a>
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#nav_target">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
  <!-- 中央のナビゲーション部分 ================ -->
    <div class="collapse navbar-collapse" id="nav_target">
      <ul class="nav navbar-nav">
        <!-- リンクのみ -->

		<li class="<%=Util.isActive(request, "/dbsiter/table/index")%>"><a href="/um/dbsiter/table/index">テーブル一覧</a></li>
		<li class="<%=Util.isActive(request, "/dbsiter/setting/index")%>"><a href="/um/dbsiter/setting/index">設定</a></li>
        <!-- Nav3 ドロップダウン
          <li class="dropdown">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Nav3 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">link 1</a></li>
              <li><a href="#">link 2</a></li>
              <li class="divider"></li>
              <li><a href="#">link3</a></li>
            </ul>
          </li>-->
        <!-- Nav4 ドロップダウン
          <li class="dropdown">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Nav4 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">link 1</a></li>
              <li><a href="#">link 2</a></li>
              <li class="divider"></li>
              <li><a href="#">link3</a></li>
            </ul>
          </li>-->
      </ul>
  <!-- 右寄せになる部分 ================ -->
      <ul class="nav navbar-nav navbar-right">
        <!-- リンクのみ -->
          <li><%=common.web.Auth.headerAuthDisp(request,"name","/um/user/info/login","/um/user/info/setting","/um/user/info/logoff") %></li>
        <!-- Nav6 ドロップダウン
          <li class="dropdown">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Nav6 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">link 1</a></li>
              <li><a href="#">link 2</a></li>
              <li class="divider"></li>
              <li><a href="#">link3</a></li>
            </ul>
          </li> -->
      </ul>
    </div>
  </div>
</nav>
<%}else if(session.getAttribute("_header").equals("hpsiter")){ %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
  <!-- ヘッダー部分 ================ -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/um/user/home/index" style="color:white;font-size:25px;font-family: impact;">UM</a>
<a class="navbar-brand <%=Util.isActive(request, "/hpsiter/home/index")%>" href="/um/hpsiter/home/index" style="font-weight:bold;color:yellow;">HPSiter</a>
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#nav_target">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
  <!-- 中央のナビゲーション部分 ================ -->
    <div class="collapse navbar-collapse" id="nav_target">
      <ul class="nav navbar-nav">
        <!-- リンクのみ -->

		<li class="<%=Util.isActive(request, "/dbsiter/table/index")%>"><a href="/um/hpsiter/page/index">ページ一覧</a></li>
		<li class="<%=Util.isActive(request, "/dbsiter/setting/index")%>"><a href="/um/hpsiter/common/index">共通ページ設定</a></li>
        <!-- Nav3 ドロップダウン
          <li class="dropdown">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Nav3 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">link 1</a></li>
              <li><a href="#">link 2</a></li>
              <li class="divider"></li>
              <li><a href="#">link3</a></li>
            </ul>
          </li>-->
        <!-- Nav4 ドロップダウン
          <li class="dropdown">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Nav4 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">link 1</a></li>
              <li><a href="#">link 2</a></li>
              <li class="divider"></li>
              <li><a href="#">link3</a></li>
            </ul>
          </li>-->
      </ul>
  <!-- 右寄せになる部分 ================ -->
      <ul class="nav navbar-nav navbar-right">
        <!-- リンクのみ -->
          <li><%=common.web.Auth.headerAuthDisp(request,"name","/um/user/info/login","/um/user/info/setting","/um/user/info/logoff") %></li>
        <!-- Nav6 ドロップダウン
          <li class="dropdown">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown">Nav6 <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="#">link 1</a></li>
              <li><a href="#">link 2</a></li>
              <li class="divider"></li>
              <li><a href="#">link3</a></li>
            </ul>
          </li> -->
      </ul>
    </div>
  </div>
</nav>
<%}else{ %>
<nav class="navbar navbar-inverse ">
  <div class="container-fluid">
  <!-- ヘッダー部分 ================ -->
    <div class="navbar-header">
      <a class="navbar-brand" href="/um/user/home/index" style="color:white;font-size:25px;font-family: impact;">UM</a>
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#nav_target">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
  <!-- 中央のナビゲーション部分 ================ -->
    <div class="collapse navbar-collapse" id="nav_target">
      <ul class="nav navbar-nav">
        <!-- リンクのみ -->
          <li><a href="/um/dbsiter/home/index">DBSiter</a></li>
          <li><a href="/um/snsiter/home/index">SNSiter</a></li>
          <li><a href="/um/hpsiter/home/index">HPSiter</a></li>
          <li><a href="/um/pub/home/infomation">Infomation</a></li>
      </ul>
  <!-- 右寄せになる部分 ================ -->
      <ul class="nav navbar-nav navbar-right">
        <!-- リンクのみ -->
          <li><%=common.web.Auth.headerAuthDisp(request,"name","/um/user/info/login","/um/user/info/setting","/um/user/info/logoff") %></li>

      </ul>
    </div>
  </div>
</nav>
<%} %>
