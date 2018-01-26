package cc.darhao.dautils.api;

/**
 * 字符串工具
 * <br>
 * <b>2018年1月19日</b>
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class StringUtil {

	/**
	 * 把一个字符串变成指定长度，不够则会在高位补零
	 * @param string 被处理的数字串
	 * @param length 需要变成多长
	 * @return
	 */
	public static String fixLength(String string, int length) throws NumberFormatException{
		if(string.length() >= length ) {
			string = string.substring(string.length() - length);
		}else if(string.length() < length){
			int len = string.length();
			for (int i = 0; i < length - len; i++) {
				string = "0" + string;
			}
		}
		return string;
	}
	
}
