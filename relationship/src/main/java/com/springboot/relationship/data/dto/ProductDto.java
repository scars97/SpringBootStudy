package com.springboot.relationship.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {

	private String name;
	private int price;
	private int stock;

	public ProductDto(String name, int price, int stock) {
		super();
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
}
