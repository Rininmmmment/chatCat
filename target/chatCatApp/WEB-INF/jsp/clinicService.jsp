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
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script src="https://kit.fontawesome.com/6c7cc9867d.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>

<script src="js/main.js"></script>

<title>クリニックサービス</title>
</head>
<body>
	<%@include file = "header.jsp" %>
	
	<div class="flex-container">
		<%@include file = "menubar.jsp" %>
		
		<main>
			<form action="Clinic" method="post">
				<p class="form-username"><%=loginUser.getUserid()%></p>
				<textarea id="textarea" name="text" maxlength="140" cols="60" rows="1" placeholder="ドクターに相談しよう"></textarea>
				<p class="form-submit-container"><input class="form-submit" type="submit" value="chat!"></p>
			</form>
						
			<div>
				<% if(errorMsg != null){ %>
				<p><%=errorMsg%></p>
				<% } %>
			</div>
			
			
			<div class="article-container">
				<% for(GetMutter mutter : mutterList){ %>
				<article>
					<section class="sec-article-imgbox">
						<img class="sec-img" src="${pageContext.request.contextPath}/image/<%=mutter.getImage() %>">
						<%-- <% System.out.println(mutter.getImage()); %> --%>
					</section>
					<section class="sec-article-txtbox">
						<p class="sec-username"><%=mutter.getUserid()%></p><p class="p-datetime"><%=mutter.getDatetime() %></p>
						<p><%=mutter.getText()%></p>
					</section>
				</article>
				<% } %>
			</div>

		</main>
		
		<%@include file = "aside.jsp" %>
	</div>

</body>
</html>