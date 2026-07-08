package bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cl = Calendar.getInstance();
		this.date = sdf.format(cl.getTime());
		this.user = null;
		this.rooms = null;//RoomDAO.findAll();
	}

	//method
	public static void main(String args[]) {
		MeetingRoom MR = new MeetingRoom();
		System.out.println(MR.getDate());

	}

	private int roomIdIndex(String roomId) throws IndexOutOfBoundsException {
		int index = 0;
		boolean flg = false;
		try {
			for (int i = 0; rooms.length < i; i++) {
				if (roomId.equals(rooms[i].getName())) {
					index = i;
					flg = true;
				}
			}
			if (flg) {
				return index;
			} else {
				throw new IndexOutOfBoundsException();
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
	}
	private int startPeriod(String start) throws IndexOutOfBoundsException{
		int index = 0;
		boolean flg = false;
		try {
			for (int i = 0; rooms.length < i; i++) {
				if (start.equals(PERIOD[i])) {
					index = i;
					flg = true;
				}
			}
			if (flg) {
				return index;
			} else {
				throw new IndexOutOfBoundsException();
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}
	}

	public static String[] getPeriod() {
		return PERIOD;
	}
	
	public RoomBean[] getRooms() {
		return rooms;
	}
	
	public RoomBean getRoom(String roomId) {
		RoomBean roomForReturn = null;
		for(RoomBean room : rooms) {
			if(roomId.equals(room.getName())) {
				roomForReturn = room;
			}
		}
		return roomForReturn;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) throws IllegalArgumentException {
		if(date.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}")) {
		this.date = date;
		}else {
			throw new IllegalArgumentException();
		}
	}

	public UserBean getUser() {
		return user;
	}
	
	public boolean login(String id, String password) {
		UserBean user = UserDAO.certificate(id,password);
		if(user == null) {
			return false;
		}else {
			this.user = user;
			return true;
		}
	}
	
	public ReservationBean[][] getReservations(){
		
	}
	
	public ReservationBean createReservation(String roomId, String start) {
		
	}
	
	public void reserve(ReservationBean reservation) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		//現在時刻の取得
		Calendar cl = Calendar.getInstance();
		//予約リクエストの時刻の取得
		String reserveTime = reservation.getDate() + " " + reservation.getStart();
		if(cl.getTime().compareTo(sdf.parse(reserveTime)) > 0) {
			throw new Exception("時刻が過ぎているため予約できません");
			return;
		}
		if(ReservationDao.insert(reservation)) {
			return;
		}else {
			throw new Exception("既に予約されています");
		}
	}
	
	public void cancel(ReservationBean reservation) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		//現在時刻の取得
		Calendar cl = Calendar.getInstance();
		//予約リクエストの時刻の取得
		String reserveTime = reservation.getDate() + " " + reservation.getStart();
		if(cl.getTime().compareTo(sdf.parse(reserveTime)) > 0) {
			throw new Exception("時刻が過ぎているため予約できません");
			return;
		}
		if(ReservationDao.delete(reservation)) {
			return;
		}else {
			throw new Exception("既に予約されています");
		}
	}
	
	public String toString() {
//		private static final int INTERVAL = 60;//利用時間
//		private static final String[] PERIOD = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" };
//		String date;//利用日
//		private UserBean user;//利用者
//		private RoomBean[] rooms;//会議室
		return "MeetingRoom {\n\tINTERVAL}";
	}

}
