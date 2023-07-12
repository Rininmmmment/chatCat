<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
	// セッションスコープからインスタンスを取得
	User u = (User)session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインOK / chatCat</title>
</head>
<body>
ようこそ<%= u.getUserid() %>さん
<a href="Home">掲示板を見る</a>
</body>
</html>