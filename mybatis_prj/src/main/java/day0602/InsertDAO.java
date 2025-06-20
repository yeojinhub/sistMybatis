package day0602;

import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisDAO;

public class InsertDAO {
	
	public void insertCpEmp7() {
		// MyBatis Handler (SqlSession)을 얻기
		SqlSession ss =
				MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml").getMyBatisHandler(true);
				// auto commit 가능
		
				// MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml").getMyBatisHandler();
				// auto commit 불가능
		
		// 실행
		int cnt = ss.insert("day0602.insertCpEmp7");
		System.out.println(cnt+"건 추가");
		
		// Transaction 처리
		/*
		 * if( cnt == 1 ) { ss.commit(); } //end if
		 */
		
		// 연결 끊기
		ss.close();
		
	} //insertCpEmp7
	
	public void insertCpEmpno( int empno ) {
		// MyBatis Handler (SqlSession)을 얻기
		SqlSession ss =
				MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml").getMyBatisHandler();
				// auto commit 불가능
				
		// MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml").getMyBatisHandler(true);
		// auto commit 가능
		
		// 실행
		int cnt = ss.insert("day0602.insertCpEmpno", empno);
		System.out.println(cnt+"건 추가");
		
		// Transaction 처리
		if( cnt == 1 ) {
			ss.commit();
		} //end if
		
		// 연결 끊기
		ss.close();
		
	} //insertCpEmpno
	
	public void insertCpDTO( CpEmpDTO ceDTO ) {
		// MyBatis Handler (SqlSession)을 얻기
		SqlSession ss =
				MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml").getMyBatisHandler();
		// auto commit 불가능
		
		// MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml").getMyBatisHandler(true);
		// auto commit 가능
		
		// 실행
		int cnt = ss.insert("day0602.insertCpDTO", ceDTO);
		System.out.println(cnt+"건 추가");
		
		// Transaction 처리
		if( cnt == 1 ) {
			ss.commit();
		} //end if
		
		// 연결 끊기
		ss.close();
		
	} //insertCpDTO
	
	public static void main(String[] args) {
		InsertDAO iDAO = new InsertDAO();
		// iDAO.insertCpEmp7();
		// iDAO.insertCpEmpno( 404 );
		CpEmpDTO cp = new CpEmpDTO(1234, 3100, 0, "이장훈", "QA", null);
		iDAO.insertCpDTO( cp );
	} //main
	
} //class
