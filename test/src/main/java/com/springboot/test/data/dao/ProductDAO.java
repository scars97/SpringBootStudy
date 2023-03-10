package com.springboot.test.data.dao;

import com.springboot.test.data.entity.Product;

public interface ProductDAO { //엔티티 타입 사용

	Product insertProduct(Product product);
	
	Product selectProduct(Long number);
	
	Product updateProductName(Long number, String name) throws Exception;
	
	void deleteProduct(Long number) throws Exception;
}
