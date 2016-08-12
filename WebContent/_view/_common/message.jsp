
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="/um/scripts/jquery.cookie.js"></script>
<script type="text/javascript">
if (!navigator.cookieEnabled) {
	alert("ブラウザの設定にてクッキーの受け入れを有効にしてください。");
}

<%if(request.getSession().getAttribute("_setMsg")!=null){ %>
var cookies=$.cookie();
for(key in cookies){

	if(key.startsWith("_msg")){
		alert(cookies[key]);
		$.removeCookie(key);
	}
}

<%}%>

</script>