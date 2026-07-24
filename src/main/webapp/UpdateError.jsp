<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
<%UserBean u = (UserBean)request.getAttribute("userb"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報変更エラー</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/style.css">
</head>
<body>
<h1>ユーザー情報変更エラー</h1>
<hr>
	<h2>変更エラー</h2>
	<div class = "error" >${ error }</div>
<table>
	<tr>
		<th>名前</th>
		<td><%=Util.htmlSpecialChars(u.getName())%></td>
	</tr>
	<tr>
		<th>パスワード</th>
		<td><%=Util.htmlSpecialChars(u.getPassword())%></td>
	</tr>
	<tr>
		<th>住所</th>
		<td><%=Util.htmlSpecialChars(u.getAddress())%></td>
	</tr>
</table>
	<hr>
<form action="<%=request.getContextPath()%>/UpdateInput.jsp" method="post">
	<input type="submit" value="確認">
</form>

</body>
</html>