package bean;

public class Util {
	private Util() {/*Utilのインスタンス化はさせません*/}

	/**
	 * 引数として与えられた文字列に含まれる & < > ' " をHTMLの実態文字参照に置き換えます。
	 * 引数がnull値の場合は戻り値はnullとなります。
	 * @param String str 変化させたい文字列
	 * @return String 変換させた文字列
	 */
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
	
	/**
	 * 引数として与えられた文字列を7文字まで減らします
	 * 引数が6文字以下の場合はそのまま、7文字以上の場合は5文字目の後に"…"を入れ以降を削除します	 
	 * @param String str 短縮させたい文字列
	 * @return String 短縮後の文字列
	 */
	public static String cropString(String str) {
		int length = 6;
		if(str == null) {
			return null;
		}
		if (str.length() <= length) {
			return str;
		} else {
			return str.substring(0, (length -1)) + "…";
		}
	}
	
}
