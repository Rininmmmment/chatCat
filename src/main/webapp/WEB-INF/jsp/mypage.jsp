<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.PostMutter,model.GetMutter,java.util.List" %>
<%@ page isELIgnored="false" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// アプリケーションスコープに保存されたつぶやきリストを取得
List<GetMutter> mutterList = (List<GetMutter>)request.getAttribute("mutterList");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>

<!DOCTYPE html>
<html>
<head>
<%@include file = "head.jsp" %>
<title>マイページ / chatCat</title>
</head>
<body>
	<%@include file = "header.jsp" %>
	
	<div class="flex-container">
		<%@include file = "menubar.jsp" %>
		
		<main>
			<div>
				<p><%=loginUser.getUserid()%>さん</p>
			</div>

		</main>
		
		<%@include file = "aside.jsp" %>
	</div>

</body>
</html>