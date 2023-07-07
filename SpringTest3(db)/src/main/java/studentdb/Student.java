package studentdb;

//String,int->Integer(래퍼클래스) =>DTO or VO에서 작성  ->래퍼클래스로 써야 하는 이유가 있는 것은 아님 래퍼클래스도 가능하다는 것을 보여주기 위함
public class Student {

	private Integer id;//학생번호
	private Integer age;//학생나이
	private String name;//학생명
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
