package yu;

public class AppMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.Message1 클래스의 객체 -> sayHello()
		//Message1 me=new Message1(); //메서드를 호출하기 위해 객체를 생성한다. new-> 공간을 만들어라
		//프로그램을 개발하다가 중간에 설계 상의 문제로 인해서 소스를 변경하는 일이 비일비재하다.
		Message2 me=new Message2();
		me.sayHello("홍길동");
	}

}
