package com.springboot.jpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.Advanced_JpaApplication;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import com.springboot.advanced_jpa.data.repository.ProductRepository;

@SpringBootTest(classes = Advanced_JpaApplication.class)
public class ProductRepositoryTest {

	@Autowired ProductRepository productRepository;
	@Autowired JPAQueryFactory jpaQueryFactory;
	
	//@Test
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
		
		Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
		System.out.println("페이징 처리" + productPage.getContent());
	}
	
	private Sort getSort() {
		return Sort.by(
				Order.by("price"),
				Order.by("stock")
				);
					
	}
	
	@Test
	void queryDslTest() {
		QProduct qProduct =QProduct.product;
		
		List<String> productList = jpaQueryFactory
				.select(qProduct.name)
				.from(qProduct)
				.where(qProduct.name.eq("펜"))
				.orderBy(qProduct.price.asc())
				.fetch();
		
		for(String product : productList) {
			System.out.println("----------------");
			System.out.println();
			System.out.println("Product Name: " + product);
			System.out.println();
			System.out.println("----------------");
		}
	}
}
