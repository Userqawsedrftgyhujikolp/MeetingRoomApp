<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウントの変更</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
<h1>アカウントの変更</h1>
	<form action="<%=request.getContextPath()%>/UpdateServlet" method="post">
		<label>名前：<input type="text" name="name" value="${Util.htmlSpecialChars(meetingRoom.user.name) }" maxlength="10" placeholder="10文字以下" required></label><br>
		<label>パスワード：<input type="text" name="password" value="${Util.htmlSpecialChars(meetingRoom.user.password) }" minlength="6" maxlength="10" placeholder="6～10文字" required></label><br>
		<label>住所：<input type="text" name="address" value="${Util.htmlSpecialChars(meetingRoom.user.address) }" maxlength="30" placeholder="30文字以下" required></label><br>
		<input type="submit" value="更新">
	</form>
	<form action="<%=request.getContextPath()%>/menu.jsp" method="post">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
<%-- <div class="button">
		<form method="post">
			<input type="submit"
				formaction="<%=request.getContextPath()%>/ReservationServlet"
				value="決定"> <input type="submit"
				formaction="<%=request.getContextPath()%>/menu.jsp"
				value="戻る">
		</form>
	</div> --%>