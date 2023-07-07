package studentdb;

//import java.sql.*; //Connection,Pre~
import javax.sql.DataSource;//DB연결할 때 사용
import java.util.List;//Select할 때 사용

public interface StudentDAO {
	
	//1.DB연결을 시켜주는 메서드(초기화)->DataSource 객체
	public void setDs(DataSource ds);//getConnection()~
	
	//2.insert		ex) create(Student st); - 이렇게 선언해도 괜찮다.
	public void create(Integer id,String name,Integer age);
	
	//3.select * from student where id=1;
	public Student getStudent(Integer id);
	
	//4.학생들 전체정보->select * from student
	public List<Student> listStudents();
	
	//5.학생정보삭제 ->delete from student where id=2;
	public void delete(Integer id);
	
	//6.학생정보 수정 ->update 테이블명 set 수정필드=값, where 조건식
	public void update(Integer id,Integer age);
	//ex) update(Student st)
}
