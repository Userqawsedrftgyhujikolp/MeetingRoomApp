<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<title>登録完了</title>
</head>
<body>
	<h1>ようこそ、${useradd.name}さん</h1>
		<label>名前：${useradd.name}</label><br>
		<label>ID：${useradd.password}</label><br>
		<label>住所：${useradd.address}</label><br>
	<form action="<%= request.getContextPath()%>/menu.jsp" method="post">
    <input type="submit" value="完了">
    </form>
</body>
</html>