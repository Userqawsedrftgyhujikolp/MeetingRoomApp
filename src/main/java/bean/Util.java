package bean;

public class Util {
	private Util() {}
	
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
		String shortStr = "";
		if(str != null) {
			
		}
		return shortStr = "";
	}
}
