package bean;

import java.io.Serializable;

public class ReservationBean implements Serializable {
	//	フィールド
	private static final long serialVersionUID = 1L;
	private int id;
	private String roomId;
	private String date;
	private String start;
	private String end;
	private String userId;

	//	コンストラクタ
	//	直列化復元時に使用
	public ReservationBean() {
	}

	//	会議室予約情報を基に初期化
	public ReservationBean(int id, String roomId, String date, String start, String end, String userId) {
		this.id = id;
		this.roomId = roomId;
		this.date = date;
		this.start = start;
		this.end = end;
		this.userId = userId;
	}

	//	予約番号以外の会議室予約情報を基に初期化
	public ReservationBean(String roomId, String date, String start, String end, String userId) {
		this.date = date;
		this.start = start;
		this.end = end;
		this.userId = userId;
	}

	//	メソッド
	public String getDate() {
		return date;
	}

	public String getEnd() {
		return end;
	}

	public int getId() {
		return id;
	}

	public String getRoomId() {
		return roomId;
	}

	public String getStart() {
		return start;

	}

	public String getUserId() {
		return userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	//	toStringをオーバーライド　会議室予約の情報を文字列として返す
	@Override
	public String toString() {
		return "ReservationBean [id=" + id + ", roomId=" + roomId + ", date=" + date + ", start=" + start + ", end="
				+ end + ", userId=" + userId + ", toString()=" + super.toString() + "]";
	}
}
