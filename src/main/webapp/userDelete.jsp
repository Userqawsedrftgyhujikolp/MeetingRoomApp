<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="bean.MeetingRoom"%>
    <%@ page import="bean.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会確認</title>
</head>
<body>
	<h1>ユーザー削除</h1>
	<hr>
	退会するとこのユーザーでログインすることができなくなります<br>
	退会しても宜しいですか？<br>
	<table>
		<tr>
			<th>ID</th>
			<td>${meetingRoom.user.id }</td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td>${meetingRoom.user.password }</td>
		</tr>
		<tr>
			<th>名前</th>
			<td>${meetingRoom.user.name }</td>
		</tr>
		<tr>
			<th>住所</th>
			<td>${meetingRoom.user.address }</td>
		</tr>
	</table>
	<input type="button" value="退会" class="submit">
	<script>
		function submit() {
			if (confirm("*最終確認*\n本当に退会しますか？")) {
				window.location.href = "<%=request.getContextPath()%>/UserDelConfirm";
			}
		}
		document.querySelector('.submit').addEventListener(`click`, submit);
		
	</script>
</body>
</html>