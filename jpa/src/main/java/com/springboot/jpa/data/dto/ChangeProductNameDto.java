package com.springboot.jpa.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangeProductNameDto {

	private Long number;
	private String name;
	
	public ChangeProductNameDto(Long number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	public ChangeProductNameDto() {
		super();
	}
	
}
