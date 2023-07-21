package com.board.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.board.domain.BoardCommand;
//유효성검사를 하는 방법=>Validator 인터페이스를 상속
public class BoardValidator implements Validator {
	
	//1.유효성검사를 할 클래스명(대상자를 지정해주는 메서드
	public boolean supports(Class<?> clazz) {
		//형식) return DTO클래스명 or VO클래스명.isAssignableFrom(clazz);
		//데이터는 BoardCommand를 통해 옮기기때문에
		return BoardCommand.class.isAssignableFrom(clazz);
	}
	//2.유효성검사를 해주는 메서드(1.유효성검사를할목표물(입력받을대상자 즉 DTO객체) 2.에러발생한정보를 저장할 에러객체명 )
	public void validate(Object target, Errors errors) {
		//입력하지 않았거나 공백을 체크해주는 메서드(1.에러객체명 2.적용시킬필드명 3.에러코드명
		//=> 정적메서드 rejectIfEmp~ 사용
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pwd","required");//암호에 대해 적용=>required.pwd
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"writer","required");//작성자
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title","required");//제목
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"content","required");//내용
	}

}
