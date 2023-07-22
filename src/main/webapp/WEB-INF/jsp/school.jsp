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
<title>学習室 / chatCat</title>
</head>
<body>
	<%@include file = "header.jsp" %>
	
	<div class="flex-container">
		<%@include file = "menubar.jsp" %>
		
		<main>
			<form action="School" method="post">
				<p class="form-username"><%=loginUser.getUserid()%></p>
				<textarea id="textarea" name="text" maxlength="140" cols="60" rows="1" placeholder="猫ちゃんと一緒に勉強しない？"></textarea>
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
						<div class="name-container">
							<div>
								<p class="sec-username"><%=mutter.getUserid()%></p>
								<p class="p-datetime"><%=mutter.getDatetime() %></p>
							</div>
							<p><a href="#" class="fa-solid fa-ellipsis" style="color: #bbbbbb;"></a></p>
						</div>
						<p><%=mutter.getText()%></p>
					</section>
				</article>
				<div class="article-menu">
					<a href="#"><i class="fa-solid fa-trash fa-sm" style="color: #e03c39;"></i>削除する</a>
				</div>
				<% } %>
			</div>

		</main>
		
		<%@include file = "aside.jsp" %>
	</div>

</body>
</html>