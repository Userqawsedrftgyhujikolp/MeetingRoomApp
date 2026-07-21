package bean;

import java.sql.SQLException;
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
	
	/**
	 * 利用時間の一覧が入った配列を返します
	 * @return String[] 利用時刻の文字列が入った配列
	 */
	public static String[] getPeriod() {//利用時刻取得
		return PERIOD;
	}

	/**
	 * 会議室の一覧が入った取得を返します
	 * @return RoomBean[] 会議室(RoomBean型)が入った配列
	 */
	public RoomBean[] getRooms() {//全ての会議室を取得
		return rooms;
	}
	
	/**
	 * 引数として与えられた文字列と同じIDを持つ会議室を返します
	 * @param String roomId 取得したい会議室のID
	 * @return RoomBean IDが一致する会議室・見つからなければnull
	 */
	public RoomBean getRoom(String roomId) {//会議室IDから会議室取得
		RoomBean roomForReturn = null;
		for (RoomBean room : rooms) {
			if (roomId.equals(room.getId())) {
				roomForReturn = room;
			}
		}
		return roomForReturn;
	}
	
	/**
	 * 現在の設定されている利用日を返します
	 * @return String 現在の利用日（yyyy-MM-dd）
	 */
	public String getDate() {////閲覧・利用する時刻取得
		return date;
	}

	/**
	 * 利用する日付を設定します
	 * @param String date 設定したい日付（yyyy-MM-dd）
	 * @throws IllegalArgumentException yyyy-MM-dd形式以外の文字列が渡された場合
	 */
	public void setDate(String date) throws IllegalArgumentException {//閲覧・利用する時刻設定
		if (date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
			this.date = date;
		} else {
			throw new IllegalArgumentException("正しくない日付が入力されました");
		}
	}
	
	/**
	 * 現在ログインしているユーザーを返します
	 * @return UserBean 利用しているユーザー
	 */
	public UserBean getUser() {//ログイン中のユーザー取得
		return user;
	}

	/**
	 * 引数として与えられたIDとパスワードをデータベースと照合し一致するものが見つかった場合、そのユーザーを自身にセットします
	 * @param String id ユーザーID
	 * @param String password パスワード
	 * @return true ログイン成功 / false ログイン失敗
	 */
	public boolean login(String id, String password) {//ログイン
		UserBean user = UserDao.certificate(id, password);
		if (user == null) {
			return false;
		} else {
			this.user = user;
			return true;
		}
	}

	/**
	 * 現在の利用日の予約の一覧が入った配列を返します
	 * @return ReservationBean[][] 予約一覧[会議室][時刻]
	 */
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
	
	/**
	 * ログイン中のユーザーの未来の予約を返します
	 * @return
	 */
	public ReservationBean[] ConfirmReservation() {
		SimpleDateFormat sdf = new SimpleDateFormat("");
		Calendar cl = Calendar.getInstance();
		String time = sdf.format(cl.getTime());
		ReservationBean[] reserve;
		List<ReservationBean> reservFromDB;
		try {
			reservFromDB = ReservationDao.findById(this.user.getId(),date,time);
			reserve = new ReservationBean[reservFromDB.size()];
		} catch (Exception e) {
			System.err.println("MeetingRoom->getReservations(): ReservationDao.findByDate()にて例外をキャッチしました\n" + e);
			return null;
		}
		if (reservFromDB != null) {
			int i = 0;
			for(ReservationBean rb:reservFromDB) {
				reserve[i] = rb;
				i++;
			}
		}
		return reserve;
	}
	
	/**
	 * 引数として与えられた情報を基に予約情報を生成します
	 * @param String roomId 会議室ID
	 * @param String start 利用開始時刻
	 * @return ReservationBean 生成した予約情報
	 * @throws Exception startがHH:mm形式でない場合
	 */
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
	
	/**
	 * 引数として与えられた予約情報を基に、それをデータベースに登録します
	 * @param ReservationBean reservation 登録する予約情報
	 * @throws Exception 過去の時間に予約を入れようとした場合 / 日時と使用会議室が同じ予約が既にある場合
	 */
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

	/**
	 * 引数として与えられた予約情報を基に、それをデータベースから削除します
	 * @param ReservationBean reservation キャンセルする予約情報
	 * @throws Exception 過去の予約をキャンセルしようとした場合 / キャンセルする予約がない場合
	 */
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
			throw new Exception("キャンセルする予約がありません");
		}
	}
	
	/**
	 * ログイン中のユーザーの削除フラグを立てます
	 */
	public void deleteUser() {
		try {
			UserDao.deleteUser(user.getId());
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("MeetingRoom.deleteUser -> 例外をキャッチしました");
			e.printStackTrace();
		}
	}
	
	/**
	 * 引数として与えられた文字列を基にDBにユーザーを追加します
	 * @param pass パスワード
	 * @param name 名前
	 * @param address
	 * @return UserBean / 追加したユーザー
	 */
	public UserBean InsertUser(String pass , String name , String address) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		Calendar cl = Calendar.getInstance();
		String year = sdf.format(cl.getTime());
		String id = null;
		try {
			id = UserDao.GetMaxId(year);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	    int nextSeq;
	    if (id == null) {
	        nextSeq = 1;
	    } else {
	        nextSeq = Integer.parseInt(id) + 1;
	    }
	    String nextIdNum = String.format("%05d", nextSeq);
	    String userId = year+nextIdNum;
		UserBean user = new UserBean(userId,pass, name, address);
		try {
			UserDao.Insert(user);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("UserDao.Insert->SQLエラーが発生しました");
			return null;
		}
		return UserDao.certificate(userId, name);
	}
	/**
	 * 引数を基に会議室を追加します
	 * @param floor 会議室の階層
	 * @param name 会議室名
	 * @return RoomBean / 追加した会議室
	 */
	public RoomBean insertRoom(String floor, String name) {
		RoomBean room;
		try {
			room = RoomDao.insertRoom(floor, name);
			if(room != null) {
				return room;
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
