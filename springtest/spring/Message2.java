package spring;

//교재 속의 lgTV implements TV와 같다.
public class Message2 implements MessageInter {

	@Override
	public void sayHello(String name) {
		// TODO Auto-generated method stub
		System.out.println("반가워요"+name+"님!");
	}

}
