package day0616;

import java.util.HashMap;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisDAO;

public class SelectDAO7 {
	
	private static SelectDAO7 sDAO;

	private SelectDAO7() {

	} //SelectDAO6

	public static SelectDAO7 getInstance() {
		if (sDAO == null) {
			sDAO = new SelectDAO7();
		} // end if
		
		return sDAO;
	}// getInstance
	
	public void insertProc(cpEmp6DTO ceDTO) throws PersistenceException {
		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		ss.insert("kr.co.sist.day0616.procedureInsert", ceDTO);
		
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
	} //insertProc
	
	public void selectProc(HashMap<String, Object> hashmap) throws PersistenceException {
		SqlSession ss = MyBatisDAO.getInstance("kr/co/sist/dao/mybatis-config.xml")
				.getMyBatisHandler(); // auto commit X
		
		// 2.쿼리를 실행
		ss.selectOne("kr.co.sist.day0616.procedureSelect", hashmap);
		
		// 3. Transaction 처리
		// 4. MyBatis Handler 닫기
		ss.close();
		
	} //selectProc

	public static void main(String[] args) {

		try {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("maker", "현대"); 
			SelectDAO7.getInstance().selectProc(hm);
			System.out.println(hm);
			/*
			 cpEmp6DTO ceDTO = new cpEmp6DTO(); ceDTO.setEmpno(4); ceDTO.setEname("이여진");
			 ceDTO.setJob("개발자"); ceDTO.setSal(3400);
			 SelectDAO7.getInstance().insertProc(ceDTO); System.out.println(
			 ceDTO.getCnt() ); System.out.println( ceDTO.getMsg() );
			 */
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		} // end try catch
		
	} // main

} // class
