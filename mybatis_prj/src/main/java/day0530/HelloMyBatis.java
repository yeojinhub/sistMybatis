package day0530;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatis {
	public void useMyBatis() {
		
			try {
				//1. 설정용 xml과 연결
				Reader reader=Resources.getResourceAsReader("day0530/mybatis-config.xml");
				//2. MyBatis Framework 생성
//				SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
//				SqlSessionFactory ssf=ssfb.build(reader);
				
				SqlSessionFactory ssf= new SqlSessionFactoryBuilder().build(reader);
				//3.MyBatis Handler 얻기
				SqlSession ss=ssf.openSession();
				//4.Handler를 사용한 조작				
				//MyBatis Handler(ss)가 namespace와 id명을 결합하여 node를 찾고 parsing
//				List<Dept> list = ss.selectList("kr.co.sist.hello.selectAllDept");
				//MyBatis Handler(ss)가 namespace는 생략가능
				List<Dept> list = ss.selectList("selectAllDept");
				for(Dept dept : list) {
				System.out.println(dept.getDeptno() +"/"+ dept.getDname()+"/"+dept.getLoc());
				}
				//5.MyBatis 닫기
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}//useMyBatis
	
	public static void main(String[] args) {
		new HelloMyBatis().useMyBatis();
	}//main
}//class
