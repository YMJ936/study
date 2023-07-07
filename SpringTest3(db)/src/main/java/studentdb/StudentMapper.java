package studentdb;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/*
	RowMapper 인터페이스->select구문을 실행(mapRow()) 자동호출
								  (callBack method) DTO에 담아서 결과를 반환시켜주는 메서드
	RowMapper<Student>
					<BoardDTO> => <~> 이걸 쓴 이유: 자동으로 형변환시켜서 받으려고 제너릭을 준다.
 */
public class StudentMapper implements RowMapper<Student> {
	
	//1.ResultSet rs(테이블 정보), 2.검색된 레코드 갯수 
	//										갯수만큼 for문 돌려서 담아서 반환한다.
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("mapRow() 호출됨(rowNum)=>"+rowNum);
		//while(rs.next()) {담아주는 구문}
		Student st=new Student();
		st.setId(rs.getInt("id"));
		st.setName(rs.getNString("name"));//원래는 rs.getString(~) 이렇게 쓰지만 왼쪽도 가능하다는 걸 알려주기 위해 한번 써봄
		st.setAge(rs.getInt("age"));
		
		return st;//query()의 반환값으로 전달 받을 수 있다.
	}
}
