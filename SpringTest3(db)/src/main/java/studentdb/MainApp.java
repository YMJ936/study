package studentdb;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.xml파일을 메모리에 올리는 작업
		ApplicationContext context=new ClassPathXmlApplicationContext("/studentdb/Bean.xml");
		
		//2.xml->getBean(id or name)
		StudentJDBCTemplate st=(StudentJDBCTemplate)context.getBean("studentJDBCTemplate");
		
		//3.DB연동=->insert,update,delete,select->반환받아서 출력
		System.out.println("st=>"+st);//만약 null발생하면->Bean.xml을 확인해야 한다.
		
		st.create(1, "홍길동", 23);
		st.create(2, "테스트", 34);//원래 나이가 37이라면 -> 수정해야 함
		st.create(3, "박영진", 45);
		st.create(4, "임시", 22);
		st.create(5, "테스트2", 32);
		
		System.out.println("전체 데이터 검색중...");
		List<Student> sts=st.listStudents();
		for(Student re:sts) {//for(객체자료형 객체명:컬렉션객체명)
			System.out.println("id=>"+re.getId());//${re.id}
			System.out.println("name=>"+re.getName());
			System.out.println("age=>"+re.getAge());
		}
		//수정
		st.update(2, 37);//id가 2번인 데이터를 찾아서 37살로 변경
		//삭제
		System.out.println("삭제시킬 Record번호는 3번");
		st.delete(3);//st.update(3)=>내부적으로 update()호출
		//레코드 한개 => 5번 레코드
		Student re=st.getStudent(5);//select * from student where id=5;
		System.out.println("id=>"+re.getId());//${re.id}
		System.out.println("name=>"+re.getName());
		System.out.println("age=>"+re.getAge());
		
	}

}
