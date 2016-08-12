
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(request.getSession().getAttribute("confirm")!=null){
	%>
	<script type="text/javascript">
		if(window.confirm("<%=request.getSession().getAttribute("confirm") %>")){
			<%
			if(request.getSession().getAttribute("confirm_yes")!=null){
			%>
			location.href="<%=request.getSession().getAttribute("confirm_yes")%>";
			<%}%>
		}else{
			<%
			if(request.getSession().getAttribute("confirm_no")!=null){
			%>
			location.href="<%=request.getSession().getAttribute("confirm_no")%>";
			<%}%>
		}
	</script>
	<%
}
request.getSession().removeAttribute("confirm");
%>
