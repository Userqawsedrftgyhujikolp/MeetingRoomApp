package bean;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.List;

import dao.ReservationDao;
import dao.RoomDao;
import dao.UserDao;

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
		try {
			this.rooms = RoomDao.findAll();
		} catch (Exception e) {
			System.err.println("MetingRoom->コンストラクタ : RoomDao.findAll()にて例外をキャッチしました\n" + e);
			this.rooms = null;
		}
	}

	//method

	private int roomIdIndex(String roomId) throws IndexOutOfBoundsException {//会議室IDを入れ、配列のインデックス取得
		for (int i = 0; rooms.length > i; i++) {
			if (roomId.equals(rooms[i].getId())) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException("配列に存在しない会議室IDが入力されました");
	}

	private int startPeriod(String start) throws IndexOutOfBoundsException {//利用時刻を入れ、配列のインデックス取得
		for (int i = 0; PERIOD.length > i; i++) {
			if (start.equals(PERIOD[i])) {
				return i;
			}
		}
		throw new IndexOutOfBoundsException("配列に存在しない時刻が入力されました");

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
			if (roomId.equals(room.getId())) {
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
		UserBean user = UserDao.certificate(id, password);
		if (user == null) {
			return false;
		} else {
			this.user = user;
			return true;
		}
	}

	public ReservationBean[][] getReservations() {//会議室ごとの予約状況 ReservationBean[会議室][時間ごとの予約状況]
		ReservationBean[][] reserve = new ReservationBean[rooms.length][PERIOD.length];
		List<ReservationBean> reservFromDB;
		try {
			reservFromDB = ReservationDao.findByDate(this.date);
		} catch (Exception e) {
			System.err.println("MeetingRoom->getReservations(): ReservationDao.findByDate()にて例外をキャッチしました\n" + e);
			return null;
		}
		//返ってきたリストを1つづつ配列の対応する場所に格納
		if (reservFromDB != null) {
			for (ReservationBean row : reservFromDB) {
				try {
					reserve[this.roomIdIndex(row.getRoomId())][this.startPeriod(row.getStart())] = row;
				} catch (IndexOutOfBoundsException e) {
					//会議室が定義外などの場合の処理
					System.err.println("例外が発生しました\n原因のReservationBean->" + row.toString() + "\n" + e);
					continue;
				}
			}
		}
		return reserve;
	}

	public ReservationBean createReservation(String roomId, String start) throws Exception {//予約情報生成
		if (this.user == null) {
			throw new Exception("未ログインです");
		}
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
			throw new Exception("時刻が過ぎているためキャンセルできません");
		}
		if (ReservationDao.delete(reservation)) {
			return;
		} else {
			throw new Exception("既にキャンセルされています");
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
		} else {
			room = "{\n";
			for (RoomBean roomB : rooms) {

				room += ("\t\t" + roomB.toString() + "\n");
			}
			room += "\t}";
		}

		return "MeetingRoom {\n\tINTERVAL:" + INTERVAL + "\n\tPERIOD:" + period + "\n\tdate:" + date + "\n\tuser:"
				+ user + "\n\tRoomBean:" + room + "\n}";
	}

}
