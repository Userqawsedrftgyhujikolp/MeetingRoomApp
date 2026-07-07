package bean;

import java.io.Serializable;

public class UserBean implements Serializable{
	//フィールド
	private static final long serialVersionUID = 1l;
	private String address;
	private String id;
	private String name;
	private String password;
	//コンストラクタ
	public UserBean() {}
	public UserBean( String id, String password, String name, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
	}
	//メソッド
	public String getAddress() {
		return address;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "UserBean [address=" + address + ", id=" + id + ", name=" + name + ", password=" + password
				+ ", toString()=" + super.toString() + "]";
	}
	

}
