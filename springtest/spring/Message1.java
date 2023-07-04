package spring;

//교재 속의 class SamsungTV implements TV 예제와 같다.
public class Message1 implements MessageInter {

	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("안녕"+name+"씨!");
	}

}
