<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="bean.MeetingRoom"%>
<%@page import="bean.ReservationBean"%>
<% MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");%>
<% ReservationBean[][] reserve = mr.getReservations();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約入力画面</title>
</head>
<body>
	<h1>会議室予約</h1>
	<br>
	<h2>利用日</h2>
	<form action="<%=request.getContextPath()%>/ChangeDate"method="post">
	<input type="date" name="date" value="<%=mr.getDate()%>" />
	<input type="submit" id="date" value="日付変更" />
	</form>
	<h2>
		予約可能時間(<%=mr.getUser().getName()%>)
	</h2>
</form>
<table>
	<tr>
		<th>会議室/時間帯</th>
		<% for(int i=0; i<mr.getPeriod().length; i++){ %>
			<th><%=mr.getPeriod()[i]%></th>
		<% } %>
	</tr>

	<% for(int n=0; n<mr.getRooms().length; n++){ %>
		<tr>
			<th><%=mr.getRooms()[n].getName()%></th>
			<% for(int i=0; i<mr.getPeriod().length; i++){ %>
				<td>
					<% if(reserve[n][i] == null){ %>
						<form action="<%=request.getContextPath()%>/ReserveCreate" method="post">
							<input type="hidden" name="roomId" value="<%=mr.getRooms()[n].getId()%>">
							<input type="hidden" name="start" value="<%=mr.getPeriod()[i]%>">
							<input type="submit" value="<%=mr.getPeriod()[i]%>">
						</form>
					<% }else{ %>
							<button disabled>予約済</button>
					<% } %>
				</td>
			<% } %>
		</tr>
	<% } %>
</table>
	<form action ="menu.jsp"><input type="submit" value="戻る"></form>
</body>
</html>