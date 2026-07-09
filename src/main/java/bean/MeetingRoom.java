package bean;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.List;

public class MeetingRoom {
	//Field
	private static final long serialVersionUID = 1L;//バージョン管理
	private static final int INTERVAL = 60;//利用時間
	private static final String[] PERIOD = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" };
	String date;//利用日
	private UserBean user;//利用者
	private RoomBean[] rooms;//会議室

	//constructor
	public MeetingRoom() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		this.date = sdf.format(cl.getTime());
		this.user = null;
		this.rooms = null;//RoomDAO.findAll();
	}

	//method
	public static void main(String args[]) {
		MeetingRoom MR = new MeetingRoom();
		System.out.println(MR.toString());

	}

	private int roomIdIndex(String roomId) throws IndexOutOfBoundsException {//会議室IDを入れ、配列のインデックス取得
		for (int i = 0; rooms.length > i; i++) {
			if (roomId.equals(rooms[i].getName())) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	private int startPeriod(String start) throws IndexOutOfBoundsException {//利用時刻を入れ、配列のインデックス取得
		for (int i = 0; PERIOD.length > i; i++) {
			if (start.equals(PERIOD[i])) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException();

	}

	public static String[] getPeriod() {//利用時刻取得
		return PERIOD;
	}

	public RoomBean[] getRooms() {//全ての会議室を取得
		return rooms;
	}

	public RoomBean getRoom(String roomId) {//会議室IDから会議室取得
		RoomBean roomForReturn = null;
		for (RoomBean room : rooms) {
			if (roomId.equals(room.getName())) {
				roomForReturn = room;
			}
		}
		return roomForReturn;
	}

	public String getDate() {////閲覧・利用する時刻取得
		return date;
	}

	public void setDate(String date) throws IllegalArgumentException {//閲覧・利用する時刻設定
		if (date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			this.date = date;
		} else {
			throw new IllegalArgumentException("正しくない日付が入力されました");
		}
	}

	public UserBean getUser() {//ログイン中のユーザー取得
		return user;
	}

	public boolean login(String id, String password) {//ログイン
		UserBean user = UserDAO.certificate(id, password);
		if (user == null) {
			return false;
		} else {
			this.user = user;
			return true;
		}
	}

	public ReservationBean[][] getReservations() {//会議室ごとの予約状況 ReservationBean[会議室][時間ごとの予約状況]
		ReservationBean[][] reserve = new ReservationBean[rooms.length][PERIOD.length];
		List<ReservationBean> reservFromDB = ReservationDAO.findByDate(this.date);
		//返ってきたリストを1つづつ配列の対応する場所に格納
		for (ReservationBean row : reservFromDB) {
			try {
				reserve[this.roomIdIndex(row.getRoomId())][this.startPeriod(row.getStart())] = row;
			} catch (IndexOutOfBoundsException e) {
				//会議室が定義外などの場合の処理
				System.out.println("例外が発生しました\n原因のReservationBean->" + row.toString() + "\n" + e);
				continue;
			}
		}
		return reserve;
	}

	public ReservationBean createReservation(String roomId, String start) {//予約情報生成
		try {
			DateTimeFormatter dTF = DateTimeFormatter.ofPattern("HH:mm");
			String end = LocalTime.parse(start, dTF).plusMinutes(INTERVAL).format(dTF);
			return new ReservationBean(roomId, this.date, start, end, this.user.getId());
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("正しくない時刻が入力されました");
		}
	}

	public void reserve(ReservationBean reservation) throws Exception {//予約実行
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//現在時刻の取得
		Calendar cl = Calendar.getInstance();
		//予約リクエストの時刻の取得
		String reserveTime = reservation.getDate() + " " + reservation.getStart();
		if (cl.getTime().compareTo(sdf.parse(reserveTime)) > 0) {
			throw new Exception("時刻が過ぎているため予約できません");
			return;
		}
		if (ReservationDao.insert(reservation)) {
			return;
		} else {
			throw new Exception("既に予約されています");
		}
	}

	public void cancel(ReservationBean reservation) throws Exception {//キャンセル実行
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//現在時刻の取得
		Calendar cl = Calendar.getInstance();
		//予約リクエストの時刻の取得
		String reserveTime = reservation.getDate() + " " + reservation.getStart();
		if (cl.getTime().compareTo(sdf.parse(reserveTime)) > 0) {
			throw new Exception("時刻が過ぎているため予約できません");
			return;
		}
		if (ReservationDao.delete(reservation)) {
			return;
		} else {
			throw new Exception("既に予約されています");
		}
	}

	public String toString() {//toString
		String period = "{";
		for (String str : PERIOD) {
			period += (str + ", ");
		}
		period += "}";
		String room;
		if (rooms == null) {
			room = null;
		}else {
			room = "{";
			for (RoomBean roomB : rooms) {
				
				room += (roomB.toString() + ", ");
			}
			room += "}";
		}
		
		return "MeetingRoom {\n\tINTERVAL:" + INTERVAL + "\n\tPERIOD:" + period + "\n\tdate:" + date + "\n\tuser:"
				+ user + "\n\tRoomBean:" + room + "\n}";
	}

}
