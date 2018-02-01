package cc.darhao.dautils.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis辅助类
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class MybatisHelper{

	
	/**
	 * 配置文件路径
	 */
	private static String confPath = "";
	
	private static byte[] configData;
	
	/**
	 * Session包装类
	 * @param <M>
	 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
	 */
	public class MybatisSession<M>{
		private SqlSession session;
		private M mapper;
		
		public MybatisSession(SqlSession session, M mapper) {
			this.session = session;
			this.mapper = mapper;
		}

		public SqlSession getSession() {
			return session;
		}
		
		public void commit() {
			session.commit();
		}
		
		public void rollback() {
			session.rollback();
		}

		public M getMapper() {
			return mapper;
		}
		
	}
	
	
	/**
	 * 获取MybatisSession对象
	 * @param confPath 相对classpath的文件名;
	 * @param mapperType mapper的类型
	 * @return 返回一个MybatisSession对象
	 * @throws IOException 文件不存在
	 */
	public static <T> MybatisSession<T> getMapper(String confPath ,Class<T> mapperType) throws IOException {
		initFactory(confPath);
		SqlSession session = new SqlSessionFactoryBuilder().build(new ByteArrayInputStream(configData)).openSession();
		T mapper = session.getMapper(mapperType);
		return new MybatisHelper().new MybatisSession<T>(session,mapper);
	}
	
	
	/**
	 * 获取MybatisSession对象,文件名固定为classpath下的mybatis-config.xml
	 * @param mapperType mapper的类型
	 * @return 返回一个MybatisSession对象
	 * @throws IOException 文件不存在
	 */
	public static <T> MybatisSession<T> getMapper(Class<T> mapperType) throws IOException {
		return getMapper("mybatis-config.xml", mapperType);
	}
	
	
	private static void initFactory(String confPath) throws IOException {
		if(!MybatisHelper.confPath.equals(confPath)) {
			MybatisHelper.confPath = confPath;
			InputStream inputStream = Resources.getResourceAsStream(confPath);
			MybatisHelper.configData = new byte[inputStream.available()];
			inputStream.read(MybatisHelper.configData);
		}
	}
	
	
}


