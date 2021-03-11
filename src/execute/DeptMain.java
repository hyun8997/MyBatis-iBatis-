package execute;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.DeptVO;

public class DeptMain {
	public static void main(String[] args) {
		// JDBC에서 DBMS 연결하려면 Connection 객체 필요
		// mybatis에서도 connection 객체 대체 = SqlSession
		
		// 1. 설정 파일 읽어오기
		Reader r = null;
		
		// 2. DB 접근
		// Connection	======	SqlSession					ex) 자동차
		//						SqlSessionFactory			ex) 공장
		//						SqlSessionFactoryBuilder	ex) 인부
		SqlSession ss = null;
		
		
		
		try {
			r = Resources.getResourceAsReader("config/mapConfig.xml");
			
			// 인부 객체 생성
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			
			// 공장 객체 생성
			SqlSessionFactory factory = ssfb.build(r);
			
			// I/O 관련 클래스는 한 번 사용하고 나면 닫기 (권장)
			r.close();
			
			// 완성 객체 생성
			ss = factory.openSession(true);		//true : autocommit;
			
			//System.out.println("ss : "+ ss);
			
			// DB로부터 데이터를 가져오기
			List<DeptVO> list = ss.selectList("selectAllDept");	//이 아이디가 있는거 실행해서 리스트로 받아오기
			
			// 데이터를 꺼내서 출력
			for(DeptVO vo : list) {
				System.out.println(vo.getDeptno()+"\t"+vo.getDname()+"\t"+vo.getLoc());
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
