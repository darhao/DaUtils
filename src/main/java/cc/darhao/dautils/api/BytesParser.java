package cc.darhao.dautils.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字节集解析器
 * <br>
 * <b>2017年12月29日</b>
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class BytesParser {
	
	
	/**
	 * 数组和集合转换
	 */
	public static byte[] cast(List<Byte> bytes) {
		byte[] bs = new byte[bytes.size()];
		for (int i = 0; i < bytes.size(); i++) {
			bs[i] = bytes.get(i);
		}
		return bs;
	}
	
	
	/**
	 * 数组和集合转换
	 */
	public static List<Byte> cast(byte[] bytes) {
		List<Byte> bs = new ArrayList<Byte>();
		for (int i = 0; i < bytes.length; i++) {
			bs.add(bytes[i]);
		}
		return bs;
	}
	
	
	/**
	 * 取字节的某一位的布尔值，从右边的0算起
	 */
	public static boolean getBit(byte a, int b) {
		if(b > 7) {
			throw new IllegalArgumentException("指定位的编号不能超过7");
		}
		return ((a >> b) & 0b00000001) == 1 ? true : false;
	}


	/**
	 *  设置字节的某一位的布尔值，从右边的0算起，返回被设置后的字节
	 */
	public static byte setBit(byte a, int b, boolean bool) {
		if(b > 7) {
			throw new IllegalArgumentException("指定位的编号不能超过7");
		}
		if(bool) {
			int temp = 0b00000001;
			temp <<= b;
			a |= temp;
		}else {
			int temp = 0b1111111101111111;
			temp >>= (7 - b);
			a &= temp;
		}
		return a;
	}


	/**
	 * 把字节集直观地（不按ASCII）转换成表示十六进制数值的字符串，中间用空格隔开
	 */
	public static String parseBytesToHexString(List<Byte> bs) {
		StringBuffer sb = new StringBuffer();
		for (Byte b1 : bs) {
			//转成无符号的整型
			int unSign = Byte.toUnsignedInt(b1);
			//转成16进制文本
			String string = Integer.toHexString(unSign);
			if(string.length() == 1) {
				string = "0" + string;
			}
			sb.append(string);
			sb.append(" ");
		}
		return sb.toString().toUpperCase().trim();
	}
	
	
	/**
	 * 把表示十六进制数值的字符串直观地（不按ASCII）转换成字节集，中间用空格隔开
	 */
	public static List<Byte> parseHexStringToBytes(String string) {
		List<Byte> bytes = new ArrayList<Byte>();
		String[] strings =  string.split(" ");
		for (String s : strings) {
			bytes.add((byte) Integer.parseInt(s, 16));
		}
		return bytes;
	}

	
	/**
	 * 把表示整数的字节集变成负的
	 */
	public static List<Byte> negativeIntegerBytes(List<Byte> bytes){
		int i = parseBytesToInteger(bytes);
		int j = - i;
		return parseIntegerToBytes(j);
	}


	/**
	 * 把1-4个字节集拼接成32位有符号整型，如果为负数，请把字节集高位用1填充至32位再传入
	 */
	public static int parseBytesToInteger(List<Byte> bytes) {
		if(bytes == null || bytes.isEmpty()) {
			throw new IllegalArgumentException("字节集不能为空");
		}
		int i = 0;
		for (int j = 0; j < bytes.size(); j++) {
			byte b1 = bytes.get(j).byteValue();
			//高位置零
			int temp = b1 & 0x000000FF;
			//按位或
			i |= temp;
			//最后一个字节不左移
			if(j != bytes.size() -1) {
				i <<= 8;
			}
		}
		return i;
	}
	
	
	/**
	 * 把32位有符号整型分解成1-4个字节集，如果为负数，将会把字节集高位用1填充至32位再返回
	 */
	public static List<Byte> parseIntegerToBytes(int i) {
		List<Byte> bytes = new ArrayList<Byte>();
		do {
			bytes.add((byte) i);
			i >>>= 8;
		}while(i != 0);
		//字节倒序
		Collections.reverse(bytes);
		return bytes;
	}
	
	
	/**
	 * 把数字字节集转换成某种进制的数字文本（不按ASCII，支持16、10、2进制）
	 */
	public static String parseBytesToXRadixString(List<Byte> bytes, int radix) {
		StringBuffer sb = new StringBuffer();
		for (Byte b : bytes) {
			switch (radix) {
			case 16:
				String hexString = Integer.toHexString(b).toUpperCase();
				hexString = StringUtil.fixLength(hexString, 2);
				sb.append(hexString);
				break;
			case 10:
				sb.append(Integer.toString(b));
				break;
			case 2:
				String binString = Integer.toBinaryString(b);
				binString = StringUtil.fixLength(binString, 8);
				sb.append(binString);
				break;
			default:
				break;
			}
			sb.append(" ");
		}
		return sb.toString().trim();
	}
	

	/**
	 * 把某种进制的数字文本转换成数字字节集（不按ASCII，支持16、10、2进制）
	 */
	public static List<Byte> parseXRadixStringToBytes(String text, int radix) {
		
		List<Byte> bytes = new ArrayList<Byte>();
		for (String byteString : text.split(" ")) {
			int i = Integer.valueOf(byteString, radix);
			bytes.add((byte) i);
		}
		return bytes;
	}
}
