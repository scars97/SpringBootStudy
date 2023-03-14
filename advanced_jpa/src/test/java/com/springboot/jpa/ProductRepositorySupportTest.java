package com.springboot.jpa;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.advanced_jpa.Advanced_JpaApplication;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.repository.support.ProductRepository;

@SpringBootTest(classes = Advanced_JpaApplication.class)
public class ProductRepositorySupportTest {

	@Autowired
	ProductRepository productRepositorySupport;
	
	@Test
	void findByNameTest() {
		List<Product> productList = productRepositorySupport.findByName("펜");
		
		for(Product product : productList) {
			System.out.println(product.getNumber());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getStock());
		}
	}
}
