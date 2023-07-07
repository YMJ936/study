package studentdb;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

//StudentJDBCTemplate -> StudentDAO와 역할이 동일하다
public class StudentJDBCTemplate implements StudentDAO {

	private DataSource ds;//Bean.xml에서 불러다 사용한다.
	//접속후 sql실행->pstmt와 역할이 비슷한 클래스가 스프링에도 존재한다. 아래의 JdbcTemplate이 그렇다
	private JdbcTemplate jt;//->이 객체를 통해서 query()(select), update()(insert,update,delete) 등의 메서드를 사용할 수 있다.
	
	@Override
	public void setDs(DataSource ds) {
		// TODO Auto-generated method stub
		this.ds=ds;
		System.out.println("ds=>"+ds);
		jt=new JdbcTemplate(ds);//JdbcTemplate(DB정보객체)
		System.out.println("setDs()호출됨(jt)=>"+jt);
	}

	@Override
	public void create(Integer id, String name, Integer age) {
		// TODO Auto-generated method stub
		//형식) jdbctemplate객체명.update(1.sql구문,2.입력받을 매개변수명)
		String sql="insert into student values(?,?,?)";
		jt.update(sql,id,name,age);
		System.out.println("생성된 레코드 id=>"+id+"name=>"+name+"age=>"+age);
	}

	@Override
	public Student getStudent(Integer id) {
		// TODO Auto-generated method stub
		String sql="select * from student where id=?";
		/*
		 * 형식) 반환값=jdbctemplate객체명.queryForObject
		 * 			(1.실행시킬 sql, 2.매개변수(배열표시),3.RowMapper객체명)
		 */
		//StudentMapper sm=new StudentMapper(); 이게 정석이나 아래처럼 익명객체를 사용할 수도 있다.
		Student st=jt.queryForObject(sql,new Object[] {id},new StudentMapper());
		return st;
	}

	@Override
	public List<Student> listStudents() {
		// TODO Auto-generated method stub
		String sql="select * from student";
		/*
		 * 형식) 반환값=jdbctemplate객체명.queryForList or .query
		 * 			(1.실행시킬 sql,2.RowMapper객체명)
		 */
		//StudentMapper sm=new StudentMapper(); 이게 정석이나 아래처럼 익명객체를 사용할 수도 있다.
		List<Student> sts=jt.query(sql,new StudentMapper());
		return sts;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		String sql="delete from student where id=?";
		jt.update(sql,id);
		System.out.println("생성된 레코드 id=>"+id);
	}

	@Override
	public void update(Integer id, Integer age) {
		// TODO Auto-generated method stub
		String sql="update student set age=? where id=?";
		//pstmt.setInt(1,age); pstmt.setInt(2,id); => pstmt일 경우
		jt.update(sql,id,age);//매개변수는 입력받은 순서대로 넣는다.
		System.out.println("생성된 레코드 id=>"+id+"age=>"+age);
	}
}
