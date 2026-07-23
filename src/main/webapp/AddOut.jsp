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
	<h1>ようこそ、${Util.htmlSpecialChars(useradd.name)}さん</h1>
		ID：${useradd.id}<br>
		名前：${Util.htmlSpecialChars(useradd.name)}<br>
		パスワード：${Util.htmlSpecialChars(useradd.password)}<br>
		住所：${Util.htmlSpecialChars(useradd.address)}<br>
	<form action="<%= request.getContextPath()%>/menu.jsp" method="post">
    <input type="submit" value="完了">
    </form>
</body>
</html>