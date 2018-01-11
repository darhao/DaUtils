package cc.darhao.dautils.api;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 返回值JSON工具，返回错误时会自动打印ERROR级别日志
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class ResultUtil {

	private static Logger logger = LogManager.getLogger();

	private String result;

	public static ResultUtil succeed() {
		return new ResultUtil("succeed");
	}

	public static ResultUtil succeed(String result) {
		return new ResultUtil(result);
	}

	public static ResultUtil failed() {
		logger.error("failed");
		return new ResultUtil("failed");
	}

	public static ResultUtil failed(String result) {
		logger.error(result);
		return new ResultUtil(result);
	}
	
	public static ResultUtil failed(String result, Exception e) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(bos);
		e.printStackTrace(printStream);
		logger.error(new String(bos.toByteArray()));
		return new ResultUtil(result);
	}

	private ResultUtil(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
