<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User,model.PostMutter,model.GetMutter,java.util.List,java.util.Map,java.util.Arrays" %>
<%@ page isELIgnored="false" %>
<%
// セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// 投稿分析情報を取得
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
				<p>にゃ〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜</p>
			</div>
			
			<div>
				<h2>投稿回数</h2>
				<% 
				List<String> BbsList = Arrays.asList("home", "matatabi", "restaurant", "school", "clinic");
				List<String> DisplayBbsList = Arrays.asList("ホーム", "またたび広場", "レストラン", "学習室", "クリニックサービス");
				%>
				<% for(int i = 0; i < 5; i++){ %>
					<h3><%=DisplayBbsList.get(i) %></h3>
					<p><%=PostCountsEachBbsList.get(BbsList.get(i)) %> 回</p>
				<% } %>
				<p>にゃ〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜</p>
			</div>
			
			<div>
				<h2>ねこランキング</h2>
				<p>にゃ〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜</p>
			</div>
			
			<div>
				<h2>お気に入り</h2>
				<p>にゃ〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜</p>
			</div>
			
			<div>
				<h2>メールアドレス</h2>
				<p>暇な時にメールするにゃ〜〜</p>
				<form action="Mypage" method="post">
					<input id="" name="email" maxlength="140" placeholder="Email">
					<p class=""><input class="" type="submit" value="設定"></p>
				</form>
			</div>
			
			<div>
				<h2>メール配信設定</h2>
				<a href="#">配信する</a>
				<a href="#">配信しない</a>
				<form action="AlterMailDeliveryPermission" method="post">
   					<label><input type="checkbox" name="whether_permit" value='permit'>メールを配信する</label>
   					<label><input type='hidden' name="whether_permit" value='refuse'>配信しない（非表示）</label>
					<input type="submit" value="変更する">
				</form>
			</div>
			
			<div>
				<div>
					<h2>退会する &#x1f622;</h2>
					<p>やめて！やめてにゃ〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜</p>
				</div>
				<div>
					<a href="#">退会する</a>
				</div>
				<div>
					<p>本当に退会しますか?</p>
					<a href="DeleteAccount">はい</a>
					<a href="#">やっぱやめる</a>
				</div>
			</div>

		</main>
		
		<%@include file = "aside.jsp" %>
	</div>

</body>
</html>