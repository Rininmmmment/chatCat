<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registation.css">
<title>会員登録 / chatCat</title>
</head>
<body>
	<header>
		<p>
			<img class="img-Logo" src="${pageContext.request.contextPath}/image/Logo.png">
		</p>
		<nav>
			<ul>
				<li>
					<a href="#"><img class="img-Info" src="${pageContext.request.contextPath}/image/Info.png"></a>
				</li>
				<li>
					<a href="#"><img class="img-Diagram" src="${pageContext.request.contextPath}/image/Diagram.png"></a>
				</li>
				<li class="li-img-Account">
					<a href="#"><img class="img-Account" src="${pageContext.request.contextPath}/image/Account.png"></a>
				</li>
			</ul>
			
		</nav>
	</header>
	
	<div class="registation-container">
		<form action="Registation" method="post">
			<input class="registation-input" type="text" name="userid" placeholder="ユーザーID"><br>
			<input class="registation-input" type="text" name="pass" placeholder="パスワード"><br>
			<input class="registation-submit" type="submit" value="会員登録">
		</form>
	</div>
</body>
</html>