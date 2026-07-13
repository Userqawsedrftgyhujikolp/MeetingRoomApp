<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>会議室予約</h1>
<h2>ログイン</h2>
    <form action="<%=request.getContextPath() %>/Login" method="post">
        利用者ID：<input type="text" name="userId"><br>
        パスワード：<input type="password" name="userPw"><br>
        <input type="submit" value="ログイン">
    </form>
</body>
</html>