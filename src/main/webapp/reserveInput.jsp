<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
<%
MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
%>
<%
ReservationBean[][] reserve = mr.getReservations();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約入力画面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>会議室予約</h1>
	<hr>
	<h2>利用日</h2>
	<form action="<%=request.getContextPath()%>/ChangeDate" method="post">
		<input type="date" name="date" value="<%=mr.getDate()%>" /> <input
			type="submit" id="date" value="日付変更" />
	</form>
	<h2>予約可能時間(${Util.htmlSpecialChars(meetingRoom.user.name)})</h2>
	<table class="table background">
		<tr class="table">
			<th class="table">会議室名&bsol;時間帯</th>
			<%
			for (int i = 0; i < mr.getPeriod().length; i++) {
			%>
			<th class="table"><%=mr.getPeriod()[i]%></th>
			<%
			}
			%>
		</tr>

		<%
		for (int n = 0; n < mr.getRooms().length; n++) {
		%>
		<tr class="table">
			<th class="table"><%=Util.cropString(mr.getRooms()[n].getName())%></th>
			<%
			for (int i = 0; i < mr.getPeriod().length; i++) {
			%>
			<td class="table">
				<%
				if (reserve[n][i] == null) {
				%>
				<form action="<%=request.getContextPath()%>/ReserveCreate"
					method="post">
					<input type="hidden" name="roomId"
						value="<%=mr.getRooms()[n].getId()%>"> <input
						type="hidden" name="start" value="<%=mr.getPeriod()[i]%>">
					<input class="ybotan" type="submit" value="<%=mr.getPeriod()[i]%>">
				</form> <%
 } else {
 %>
				<button disabled>予約済</button> <%
 }
 %>
			</td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>
	<hr>
	<form action="menu.jsp">
		<input type="submit" class="yohaku" value="戻る">
	</form>
</body>
</html>