package bean;

import java.io.Serializable;

public class UserBean implements Serializable{
	//フィールド
	private static final long serialVersionUID = 1l;
	private String address;
	private String id;
	private String name;
	private String password;
	private int admin;
	//コンストラクタ
	public UserBean( String id, String password, String name, String address) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.admin = 0;
	}
	public UserBean( String id, String password, String name, String address,int admin) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.admin = admin;
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
	public boolean isAdmin() {
		if(admin == 1) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "UserBean [address=" + address + ", id=" + id + ", name=" + name + ", password=" + password
				+ ", toString()=" + super.toString() + "]";
	}
	

}
