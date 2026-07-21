package bean;

import java.io.Serializable;

public class RoomBean implements Serializable {
	//フィールド
	private static final long serialVersionUID = 1L;//バージョン管理
	private String id;
	private String name;

	//コンストラクタ
	public RoomBean() {
	}

	public RoomBean(String id, String name) {
		this.id = id;
		this.name = name;
	}
	//メソッド

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "RoomBean [id=" + id + ", name=" + name + ", toString()=" + super.toString() + "]";
	}

}
