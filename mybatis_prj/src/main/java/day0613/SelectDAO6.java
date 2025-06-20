package day0613;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day0602.CpEmpDTO;
import kr.co.sist.dao.MyBatisDAO;
import kr.co.sist.domain.CpEmp;

public class SelectDAO6 {
	
	private static SelectDAO6 sDAO;

	private SelectDAO6() {

	} //SelectDAO6

	public static SelectDAO6 getInstance() {
		if (sDAO == null) {
			sDAO = new SelectDAO6();
		} // end if
		
		return sDAO;
	}// getInstance

	public List<CpEmp> dynamicChoose(int deptno) throws PersistenceException {
		List<CpEmp> list = null;

		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		list = ss.selectList("kr.co.sist.day0613.dynamicChoose", deptno);
		// 검색결과
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
		return list;
	} // dynamicChoose
	
	public List<CpEmp> dynamicForeach(Map<String, Object> map){
		List<CpEmp> list = null;

		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		list = ss.selectList("kr.co.sist.day0613.dynamicForeach", map);
		//<select id="dynamicForeach" resultType="" parameterMap="java.util.HashMap">
		// 검색결과
		
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
		return list;
	} //dynamicForeach
	
	public List<Integer> selectAllEmpno() throws PersistenceException {
		List<Integer> list = null;
		
		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		list = ss.selectList("kr.co.sist.day0613.selectAllEmpno");
		//<select id="dynamicForeach" resultType="" parameterMap="java.util.HashMap">
		// 검색결과
		
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
		return list;
	} //selectAllEmpno
	
	public CpEmp selectOneEmpno(int empno) throws PersistenceException {
		CpEmp empDomain = null;
		
		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		empDomain = ss.selectOne("kr.co.sist.day0613.selectOneEmp", empno);
		//<select id="dynamicForeach" resultType="" parameterMap="java.util.HashMap">
		// 검색결과
		empDomain.setEmpno(empno);
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
		return empDomain;
	} //selectOneEmpno
	
	public int dynamicSet(CpEmpDTO ceDTO) throws PersistenceException {
		int rowCnt = 0;
		
		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				//.getMyBatisHandler(); // auto commit X
				.getMyBatisHandler(true); // auto commit O
		
		// 2.쿼리를 실행
		rowCnt = ss.update("kr.co.sist.day0613.dynamicSet", ceDTO);
		//<select id="dynamicForeach" resultType="" parameterMap="java.util.HashMap">
		// 검색결과
		
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
		return rowCnt;
	} //dynamicSet
	
	public int transaction(TransactionDTO tDTO) throws PersistenceException {
		int cnt=0, cnt2=0;
		
		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		cnt = ss.insert("kr.co.sist.day0613.transaction", tDTO);
		cnt2 = ss.insert("kr.co.sist.day0613.transaction2", tDTO);
		
		// 검색결과
		if( cnt+cnt2 == 2 ) {
			// auto commit 해제된 상태이므로 if 타지않으면 commit 불가.
			ss.commit();
		} //end if
		
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
		return cnt+cnt2;
	} //transaction

	public static void main(String[] args) {

		try {
			//SelectDAO6.getInstance().dynamicChoose(2);
			//SelectDAO6.getInstance().selectAllEmpno();
			//SelectDAO6.getInstance().selectOneEmpno(63);
			//CpEmpDTO ce = new CpEmpDTO(23, 9000, 900, "삼장훈", "마법사", null);
			//SelectDAO6.getInstance().dynamicSet(ce);
			
			TransactionDTO tDTO = new TransactionDTO();
			tDTO.setName("강태삼");
			tDTO.setAddr("서울시 강남구");
			SelectDAO6.getInstance().transaction(tDTO);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end try catch
		
	} // main

} // class
