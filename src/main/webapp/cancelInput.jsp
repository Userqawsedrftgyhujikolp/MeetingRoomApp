<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.MeetingRoom"%>
<%@ page import="bean.ReservationBean"%>
<%@ page import="bean.RoomBean"%>
<%@ page import="bean.Util"%>
<%
MeetingRoom mr = (MeetingRoom) session.getAttribute("meetingRoom");
%>
<%
ReservationBean[][] reserve = mr.getReservations();
%>
<%
RoomBean[] rb = mr.getRooms();
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<meta charset="UTF-8">
<title>キャンセル入力画面</title>
</head>
<body>

	<h1>会議室予約キャンセル</h1>
	<hr>
	<h2>利用日</h2>
	<form action="<%=request.getContextPath()%>/ChangeDate" method="post">
		<input type="date" name="date" value="<%=mr.getDate()%>"> <input
			type="hidden" name="page" value="cancelInput.jsp"> <input
			type="submit" value="日付変更">

	</form>

	<h2>
		キャンセル可能時間帯(<%=Util.htmlSpecialChars(mr.getUser().getName())%>)
	</h2>
	<table class="table background">
		<tr class="table">
			<th class="table">会議室名&bsol;時間帯</th>
			<%
			String[] jikan = MeetingRoom.getPeriod();
			for (int t = 0; t < jikan.length; t++) {
			%>
			<th class="table"><%=jikan[t]%></th>
			<%
			}
			%>
		</tr>
		<%
		for (int r = 0; r < reserve.length; r++) {
		%>
		<tr class="table">
			<td class="table"><%=Util.cropString(rb[r].getName())%></td>
			<%
			for (int p = 0; p < reserve[r].length; p++) {
			%>
			<td class="table">
				<%
				if (reserve[r][p] != null){
					if(reserve[r][p].getUserId().equals(mr.getUser().getId())) {
					%>
					<form action="<%=request.getContextPath()%>/CancelCreateServlet"
						method="post">
					<input type="hidden" name="roomId" value="<%=r%>"> <input
						type="hidden" name="time" value="<%=p%>"> <input class="ybotan"
						type="submit" value="済">
					</form> <%
 					}else{
 						%><input class="disabled" type="button" value="済" disabled><%
 					}
				}else { %> 
 				<input class="disabled" type="button" value="空" disabled>
				<%}%>
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
	<form action="menu.jsp" method="post">
		<input type="submit" class=yohaku value="戻る">
	</form>

</body>
</html>