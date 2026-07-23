<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
int story = 5;
%>
<html>
<head>
<meta charset="UTF-8">
<title>会議室追加</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室追加</h1>
	<hr>
	追加する会議室の情報を入力してください
	<form action="<%=request.getContextPath()%>/RoomAdd" method="post">
		階層<select name="floor" requied>
			<%
			for (int i = 1; i <= story; i++) {
			%>
			<option value="<%=i%>"><%=i%>階
			</option>
			<%
			}
			%>
		</select><br> 会議室名<input type="text" name="name" maxlength="20" required><br>
		<hr>
		<input type="submit" value="追加">
	</form>
</body>
</html>