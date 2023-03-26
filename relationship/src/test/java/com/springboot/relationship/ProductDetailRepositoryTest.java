package com.springboot.relationship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.ProductDetail;
import com.springboot.relationship.data.repository.ProductDetailRepository;
import com.springboot.relationship.data.repository.ProductRepository;

@SpringBootTest
public class ProductDetailRepositoryTest {

	@Autowired
	ProductDetailRepository productDetailRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void saveAndReadTest1() {
		Product product = new Product();
		product.setName("스프링 부트 JPA");
		product.setPrice(5000);
		product.setStock(500);
		productRepository.save(product);
		
		ProductDetail productDetail = new ProductDetail();
		productDetail.setProduct(product);
		productDetail.setDescription("스프링 부트 JPA와 함께 볼 수 있는 책.");
		productDetailRepository.save(productDetail);
		
		//생성한 데이터 조회
		System.out.println("saveProduct: " + productDetailRepository.findById(
				productDetail.getId()).get().getProduct());
		
		System.out.println("saveProductDetail: " + productDetailRepository.findById(
				productDetail.getId()).get());
	}
}