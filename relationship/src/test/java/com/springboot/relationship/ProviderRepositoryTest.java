package com.springboot.relationship;

import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import com.springboot.relationship.data.repository.ProductRepository;
import com.springboot.relationship.data.repository.ProviderRepository;

@SpringBootTest
public class ProviderRepositoryTest {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProviderRepository providerRepository;
	
	//@Test
	void relationshipTest1() {
		//테스트 데이터 생성
		Provider provider = new Provider();
		provider.setName("ㅇㅇ물산");
		
		providerRepository.save(provider);
		
		Product product1 = new Product();
		product1.setName("펜");
		product1.setPrice(2000);
		product1.setStock(100);
		product1.setProvider(provider);
		
		Product product2 = new Product();
		product2.setName("가방");
		product2.setPrice(20000);
		product2.setStock(200);
		product2.setProvider(provider);
		
		Product product3 = new Product();
		product3.setName("노트");
		product3.setPrice(3000);
		product3.setStock(100);
		product3.setProvider(provider);
		
		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		
		List<Product> products = providerRepository.findById(provider.getId()).get()
				.getProductList();
		
		for(Product product : products) {
			System.out.println(product + " ");
		}
		
	}
	
	@Test
	@Transactional
	void orphanRemovalTest() {
		Provider provider = savedProvider("새로운 공급업체");
		
		Product product1 = savedProduct("상품1", 1000, 1000);
		Product product2 = savedProduct("상품2", 1000, 1000);
		Product product3 = savedProduct("상품3", 1000, 1000);
		
		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);
		
		provider.getProductList().addAll(Lists.newArrayList(product1,product2,product3));
		
		providerRepository.saveAndFlush(provider);
		
		providerRepository.findAll().forEach(System.out::println);
		productRepository.findAll().forEach(System.out::println);
		
		Provider foundProvider = providerRepository.findById(1L).get();
		foundProvider.getProductList().remove(1);
		
		providerRepository.findAll().forEach(System.out::println);
		productRepository.findAll().forEach(System.out::println);
		
	}
	
	private Provider savedProvider(String name) {
		Provider  provider = new Provider();
		provider.setName(name);
		
		return provider;
	}
	
	private Product savedProduct(String name, Integer price, Integer stock) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		
		return product;
	}
}
