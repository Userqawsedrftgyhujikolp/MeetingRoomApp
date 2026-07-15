package bean;

public class Util {
	private Util() {/*Utilのインスタンス化はさせません*/}

	public static String htmlSpecialChars(String str) {
		if (str != null) {
			str = str.replace("&", "&amp;");
			str = str.replace("<", "&lt;");
			str = str.replace(">", "&gt;");
			str = str.replace("\'", "&apos;");
			str = str.replace("\"", "&quot;");
		}
		return str;
	}

	public static String cropString(String str) {
		if(str == null) {
			return null;
		}
		if (str.length() <= 7) {
			return str;
		} else {
			return str.substring(0, 6) + "…";
		}
	}
}
