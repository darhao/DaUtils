package cc.darhao.dautils.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class DateUtil {
	
	/**
	 * 转换成通俗易懂的语言：例如2小时前，40分钟前，刚刚，2天前
	 * <br>具体如下：<br>
	 * 不超过10分钟：刚刚<br>
	 * 10分钟 ~ 59分钟：XX分钟前<br>
	 * 1小时 ~ 23小时：XX小时前<br>
	 * 1天 ~ 7天：XX天前<br>
	 * 8天 ~ 99天：MM-dd<br>
	 * 100天以上：yyyy-MM-dd<br>
	 */
	public static String toPopularString(Date date) {
		Date now = new Date();
		long difference = now.getTime() - date.getTime();
		if(difference < 10 * 60 * 1000) {
			return "刚刚";
		}else if(difference >= 10 * 60 * 1000 && difference < 60 * 60 * 1000 ){
			return difference / 60 / 1000 + "分钟前";
		}else if(difference >= 1 * 60 * 60 * 1000 && difference < 24 * 60 * 60 * 1000 ) {
			return difference / 60 / 60 / 1000 + "小时前";
		}else if(difference >= 1 * 24 * 60 * 60 * 1000 && difference < 8 * 24 * 60 * 60 * 1000 ) {
			return difference / 24 / 60 / 60 / 1000 + "天前";
		}else if(difference >= 8 * 60 * 60 * 1000 && difference < 100 * 24 * 60 * 60 * 1000 ) {
			return MMdd(date);
		}else if(difference >= 100 * 60 * 60 * 1000) {
			return yyyyMMdd(date);
		}else {
			throw new RuntimeException(date + "：无法被转换成通俗格式");
		}
	}
	
	public static String yyyyMMdd(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}	
		
	public static String MMdd(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
		return simpleDateFormat.format(date);
	}
	
	public static String yyyyMMddHHmmss(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	public static String HHmmss(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	
	public static Date yyyyMMdd(String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(date);
	}
	
	public static Date MMdd(String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
		return simpleDateFormat.parse(date);
	}
	
	public static Date yyyyMMddHHmmss(String date) throws ParseException{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.parse(date);
	}
	
	public static Date HHmmss(String date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		return simpleDateFormat.parse(date);
	}
	
}
