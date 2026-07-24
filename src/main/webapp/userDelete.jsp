<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="bean.MeetingRoom"%>
    <%@ page import="bean.UserBean"%>
    <%@ page import="bean.Util" %>
    <%
    MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会確認</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>ユーザー削除</h1>
	<hr>
	退会するとこのユーザーでログインすることができなくなります<br>
	退会しても宜しいですか？<br>
	<table class="margin">	
	<tr>
			<th>ID</th>
			<td>${meetingRoom.user.id }</td>
		</tr>
		<tr>
			<th>パスワード</th>
			<td><%=Util.htmlSpecialChars(mr.getUser().getPassword())%></td>
		</tr>
		<tr>
			<th>名前</th>
			<td><%=Util.htmlSpecialChars(mr.getUser().getName())%></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><%=Util.htmlSpecialChars(mr.getUser().getAddress())%></td>
		</tr>
	</table>
	<hr>
	<div class="button">
		<input type="button" value="退会" class="submit">
		<form action="<%=request.getContextPath()%>/UpdateInput.jsp">
		<input type="submit"value="戻る">
		</form>
	</div>
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