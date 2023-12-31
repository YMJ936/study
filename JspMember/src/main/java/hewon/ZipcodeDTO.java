package hewon;
//DTO=>테이블 하나생성할때마다 DTO하나씩 작성
//DAO=>테이블을 만들때마다 무조건 작성X ->상황에 따라 작성 O
//ZipcodeDAO (X) ->메서드 하나

public class ZipcodeDTO {
	//                     우편번호   시,도  구,소도시 동,면리 나머지주소
	private String zipcode,area1,area2,area3,area4;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getArea3() {
		return area3;
	}

	public void setArea3(String area3) {
		this.area3 = area3;
	}

	public String getArea4() {
		return area4;
	}

	public void setArea4(String area4) {
		this.area4 = area4;
	}
}
