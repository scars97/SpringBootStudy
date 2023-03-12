package com.springboot.jpa;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.springboot.advanced_jpa.Advanced_JpaApplication;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.repository.ProductRepository;

@SpringBootTest(classes = Advanced_JpaApplication.class)
public class ProductRepositoryTest {

	@Autowired ProductRepository productRepository;
	
	@Test
	void sortingandPagingTest() {
		Product product1 = new Product();
		product1.setName("펜");
		product1.setPrice(1000);
		product1.setStock(100);
		product1.setCreatedAt(LocalDateTime.now());
		product1.setUpdatedAt(LocalDateTime.now());

		Product product2 = new Product();
		product2.setName("펜");
		product2.setPrice(1000);
		product2.setStock(100);
		product2.setCreatedAt(LocalDateTime.now());
		product2.setUpdatedAt(LocalDateTime.now());
		
		Product product3 = new Product();
		product3.setName("펜");
		product3.setPrice(1000);
		product3.setStock(100);
		product3.setCreatedAt(LocalDateTime.now());
		product3.setUpdatedAt(LocalDateTime.now());
		
		Product savedProduct1 = productRepository.save(product1);
		Product savedProduct2 = productRepository.save(product2);
		Product savedProduct3 = productRepository.save(product3);
		
		System.out.println(productRepository.findByName("펜", getSort()));
	}
	
	private Sort getSort() {
		return Sort.by(
				Order.by("price"),
				Order.by("stock")
				);
					
	}
}
