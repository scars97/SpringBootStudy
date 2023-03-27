package com.springboot.valid_exception.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface Telephone {

	//유효성 검사가 실패할 경우 반환되는 메시지
	String message() default "전화번호 형식이 일치하지 않습니다.";
	
	//유효성 검사를 사용하는 그룹으로 설정
	Class[] groups() default {};
	
	//사용자가 추가 정보를 위해 전달하는 값
	Class[] payload() default {};
}
