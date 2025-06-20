package kr.co.sist.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisDAO {
	
	private static MyBatisDAO mbDAO;
	
	private static SqlSessionFactory ssf;
	
	private String configPath;
	
	private MyBatisDAO(String configPath) {
		org.apache.ibatis.logging.LogFactory.useLog4J2Logging();
		this.configPath = configPath;
	} //MyBatisDAO
	
	/**
	 * MyBatis Framework에서 연동할 DB의 설정을 가진 경로를 상대경로로 설정
	 * 예) 상대경로 : 패키지명/설정파일명.xml 로 설정
	 * 				(day0602/kr/co/sist/dao/mybatis-config.xml)
	 * @param configPath
	 * @return mbDAO
	 */
	public static MyBatisDAO getInstance(String configPath) {
		if( mbDAO == null ) {
			mbDAO = new MyBatisDAO(configPath);
		} //end if
		
		return mbDAO;
	} //getInstance
	
	private SqlSessionFactory getSqlSessionFactory() {
		if( ssf == null ) {
			
			try {
				// 1. 설정파일(MyBatis-config.xml)과 Stream으로 연결
				Reader reader =
						Resources.getResourceAsReader(configPath);
				
				// 2. MyBatis Framework 생성
				ssf = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException ie) {
				ie.printStackTrace();
			} //end try catch
			
		} //end if
		
		return ssf;
	} //getSqlSessionFactory
	
	/**
	 * autoCommit 이 해제된 상태의 MyBatis Handler 얻기
	 * @return ss
	 */
	public SqlSession getMyBatisHandler() {
		SqlSession ss = getMyBatisHandler( false );
		
		return ss;
	} //getMyBatisHandler
	
	/**
	 * autoCommit을 설정하는 MyBatis Handler 얻기
	 * @param autoCommitFlag true, false
	 * @return ss
	 */
	public SqlSession getMyBatisHandler(boolean autoCommitFlag) {
		SqlSession ss = getSqlSessionFactory().openSession(autoCommitFlag);
		
		return ss;
	} //getMyBatisHandler
	
} //class
