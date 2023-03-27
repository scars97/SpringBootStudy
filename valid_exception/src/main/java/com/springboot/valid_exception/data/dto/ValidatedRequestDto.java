package com.springboot.valid_exception.data.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.springboot.valid_exception.config.annotation.Telephone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidatedRequestDto {

	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@Telephone
	private String phoneNumber;
	
	@Min(value = 20) 
	@Max(value = 40)
	private int age;
	
	@Size(min = 0, max = 40)
	private String description;
	
	@Positive
	private int count;
	
	@AssertTrue
	private boolean booleanCheck;
}
