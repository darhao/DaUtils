package cc.darhao.dautils.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文本文件工具类
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class TextFileUtil {

	/**
	 * 通过文件名获取一个字符串
	 * @param fileName 文件名
	 * @return 字符串
	 * @throws IOException 文件不存在
	 */
	public static String readFromFile(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
		String s = new String();
		StringBuffer stringBuffer = new StringBuffer();
		while((s = bufferedReader.readLine()) != null){
			stringBuffer.append(s);
		}
		bufferedReader.close();
		return stringBuffer.toString();
	}
	
	
	/**
	 * 通过文件名写一个字符串
	 * @param fileName 文件名
	 * @param data 字符串
	 * @throws IOException 文件不存在
	 */
	public static void writeToFile(String fileName, String data) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(fileName)));
		bufferedWriter.write(data);
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	
}
