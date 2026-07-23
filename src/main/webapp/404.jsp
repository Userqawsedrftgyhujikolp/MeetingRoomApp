<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="bean.Util" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ページが見つかりません</title>
<style>
    @keyframes moves {
        0% { background-position: 0% 50%; }
        100% { background-position: 200% 50%; }
    }
    html, body {
    	background: linear-gradient(to right, #f00, #fa0, #ff0, #0f0, #0ff, #00f, #808, #f00) 0 / 200% 100%;
    	animation: 5s moves linear infinite;
    	text-align: center;
    	height: 100%;
        margin: 0;
        padding: 0;
    }
</style>

</head>
<body>
	<h1>404 not found</h1>
	<hr>
	<main style="margin: 100px auto;">
	<p>お探しのページは見つかりませんでした</p>
	<p><%=Util.htmlSpecialChars(request.getRequestURI()) %></p>
	</main>
	<hr>
	<a href="<%=request.getContextPath() %>/menu.jsp"><button>メニュー画面に戻る</button></a>
</body>
</html>