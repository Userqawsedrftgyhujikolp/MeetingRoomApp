<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "bean.MeetingRoom" %>
    <%@ page import = "bean.ReservationBean" %>
    <%@ page import = "bean.RoomBean" %>
    <% MeetingRoom mr = (MeetingRoom)session.getAttribute("meetingRoom"); %>
    <% ReservationBean[][] reserve = mr.getReservations(); %>
    <% RoomBean[] rb = mr.getRooms(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>キャンセル入力画面</title>
</head>
<body>

	<h1>会議室予約キャンセル</h1><br>
	<h2>利用日</h2>
	<form action = "<%= request.getComtextPath() %>/ChangeDate" method = "post">
	<input type = "date" name ="date" value = "<%= meetingRoom.getDate() %>">
	<input type = "hidden" name = "page" value ="cancelInput.jsp">
	<input type = "subnit" value = "日付変更"
	</form>
	<input type = "date" id = "date" value = "<%= mr.getDate() %>"/>
	
	<h2>キャンセル可能時間帯(<%= mr.getUser().getName() %>)</h2>
<table>
	<tr>
		<td>会議室名\時間帯</td>
		
	<% String [] jikan = MeetingRoom.getPeriod();
		for(int t = 0 ; t < jikan.length ; t++){ %>
			<th> <%= jikan[t] %> </th>
		<% }%>
	</tr>
	 <% for(int r = 0 ; r < reserve.length ; r++){%>
		<tr> 
		<th><%= rb[r].getName() %></th>
		<% for (int p = 0 ; p < reserve[r].length ; p++){ %>
			<td>
			<% if(rserve[r][p] != null){ %> 
			<form action = "<%= request.getContextPath() %>/CancelCreat.jsp" method = "post">
			<input type = "hidden" name = "roomId" value = "<%= rooms[r].getId() %>">
			<input type = "submit" name = "time" value = "<%= period[p] %>">
			</form>
			
			<% } else { %>
			<input type = "submit" value = "<%=period[p] %>" disabled>
			<% }%>
		</td>
		<% }%>
		</tr>
	<% } %>
	
	
	</table>
</body>
</html>