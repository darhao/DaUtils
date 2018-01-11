package cc.darhao.dautils.api;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis辅助类
 * @author 沫熊工作室 <a href="http://www.darhao.cc">www.darhao.cc</a>
 */
public class MybatisHelper{

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
		InputStream inputStream = Resources.getResourceAsStream(confPath);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);
		SqlSession session = factory.openSession();
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
}


