package cc.darhao.dautils.api;

import java.util.UUID;

/**
 * 32位UUID生成器
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class UuidUtil {

	/**
	 * 获取32位随机串
	 * @return
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}

