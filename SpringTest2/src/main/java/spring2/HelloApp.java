package spring2;
//1.인터페이스를 이용 2.xml파일을 이용해서 객체관리 O
//스프링-> 자바 자체에서 제공되는 패키지가 없다. => 외부에서 다운로드 받아서 넣어야 함

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HelloApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//직접 new를 이용해서 객체생성X -> xml파일을 불러오는 구문
		 //1.xml파일의 위치를 불러오는 방법->절대경로
		Resource resource=new ClassPathResource("/spring2/initContext.xml");//ClassPathResource 상대경로를 통해서 불러옴
		//2.BeanFactory(빈즈공장)를 만들어서 객체를 얻어오기
		BeanFactory factory=new XmlBeanFactory(resource);//xml파일의 객체를 얻어서 정보를 얻어오는 역할
		//선이 그어지는 이유: 지금은 써도 괜찮은데 앞으로 없어질 기능
		
		//3.factory에서 getBean("불러올 객체를 가져올 id값");
		
		MessageBeanDI bean=(MessageBeanDI)factory.getBean("mBean");
		System.out.println("me=>"+bean);//주소값 호출
		bean.sayHello();
	}

}