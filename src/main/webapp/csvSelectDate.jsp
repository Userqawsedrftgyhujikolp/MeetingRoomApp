<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.UserBean"%>
<%@ page import="bean.Util"%>
<%
MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
ReservationBean[][] reserv = mr.getReservations();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CSV出力</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>
<body>
	<h1>CSV出力</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/ChangeDate" method="post">
		<input type="date" name="date" value="<%=mr.getDate()%>"> <input
			type="submit" value="日付変更">
	</form>
	<h2>
		現在の指定日：<%=mr.getDate()%></h2>
	指定日の予約状況
	<table>
		<tr>
			<th>会議室&#92;時刻</th>
			<%
			for (String time : mr.getPeriod()) {
			%>
			<th><%=time%></th>
			<%
			}
			%>
		</tr>
		<%
		for (int i = 0; i < mr.getRooms().length; i++) {
		%>
		<tr>
			<th><%=mr.getRooms()[i].getName()%></th>
			<%
			for (ReservationBean reservation : reserv[i]) {
			%>
			<td>
				<%
				if (reservation != null) {
					out.print("済");
				} else {
					out.print("空");
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
	<form action="<%=request.getContextPath()%>/CsvExport" method="post">
		<input type="hidden" name="char" value="UTF-8"> <input
			type="submit" value="UTF-8で取得">
	</form>
	<form action="<%=request.getContextPath()%>/CsvExport" method="post">
		<input type="hidden" name="char" value="MS932"> <input
			type="submit" value="Shift-JISで取得">
	</form>
</body>
</html>