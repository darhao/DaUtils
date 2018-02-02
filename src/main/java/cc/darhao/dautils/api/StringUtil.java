package cc.darhao.dautils.api;

import java.util.ArrayList;
import java.util.List;

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
	
	
	/**
	 * 把一个字符串里面的空格全部去掉
	 * @param string 被处理的数字串
	 */
	public static String press(String string) {
		StringBuffer sb = new StringBuffer();
		String[] strings = string.split(" ");
		for (String string2 : strings) {
			sb.append(string2);
		}
		return sb.toString();
	}
	
	
	/**
	 * 把一个字符串按一定长度用空格分开
	 * @param string 被处理的数字串
	 */
	public static String stretch(String string, int length) {
		List<String> strings = new ArrayList<String>();
		for (int i = 0; i <= string.length() - length; i+=length) {
			strings.add(string.substring(i, i+length));
		}
		StringBuffer sb = new StringBuffer();
		for (String s: strings) {
			sb.append(s);
			sb.append(" ");
		}
		return sb.toString().trim();
	}
	
	
}
