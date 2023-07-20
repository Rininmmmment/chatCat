<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.PostMutter,model.GetMutter,java.util.List,java.util.Map,java.util.Arrays" %>
<%@ page isELIgnored="false" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
Map<String, Integer> PostCountsEachBbsList = (Map<String, Integer>)request.getAttribute("postCountsEachBbsList");
int postedTimesSum = (int)request.getAttribute("postedTimesSum");
int level = (int)request.getAttribute("level");
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
				<h1><%=loginUser.getUserid()%>さん</h1>
			</div>
			<div>
				<h2>レベル<%=level %></h2>
				<p>総投稿回数 <%=postedTimesSum %> 回</p>
			</div>
			<div>
				<h2>投稿頻度</h2>
				<% 
				List<String> BbsList = Arrays.asList("home", "matatabi", "restaurant", "school", "clinic");
				List<String> DisplayBbsList = Arrays.asList("ホーム", "またたび広場", "レストラン", "学習室", "クリニックサービス");
				%>
				<% for(int i = 0; i < 5; i++){ %>
					<h3><%=DisplayBbsList.get(i) %></h3>
					<p><%=PostCountsEachBbsList.get(BbsList.get(i)) %> 回</p>
				<% } %>
			</div>
			

		</main>
		
		<%@include file = "aside.jsp" %>
	</div>

</body>
</html>