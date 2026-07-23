<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウントの変更</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>アカウントの変更</h1>
	<hr>
	<form methot="post">
		<formaction
			="<%=request.getContextPath()%>/UpdateServlet"
		method="post">
		<label>名前：<input type="text" name="name"
			value="${meetingRoom.user.name }" maxlength="10" placeholder="10文字以下"
			required></label>
		<br>
		<label>パスワード：<input type="password" name="password"
			value="${meetingRoom.user.password }" minlength="6" maxlength="10"
			placeholder="6～10文字" required></label>
		<br>
		<label>住所：<input type="text" name="address"
			value="${meetingRoom.user.address }" maxlength="30"
			placeholder="30文字以下" required></label>
		<hr>
		<input type="submit" value="更新"> <input type="submit"
			formaction="<%=request.getContextPath()%>/menu.jsp" value="戻る">
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