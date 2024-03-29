package com.springboot.relationship.data.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.relationship.data.dao.ProductDAO;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.ProductRepository;

@Component
public class ProductDAOImpl implements ProductDAO {

	private final ProductRepository productRepository;

	@Autowired
	public ProductDAOImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product insertProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		
		
		return savedProduct;
	}

	@Override
	public Product selectProduct(Long number) {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		return selectedProduct.get();
	}

	@Override
	public Product updateProductName(Long number, String name) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		Product updateProduct;
		if(selectedProduct.isPresent()) {//값을 가지고 있다면 true, 없다면 false
			Product product = selectedProduct.get();
			
			product.setName(name);
			product.setUpdatedAt(LocalDateTime.now());
			
			updateProduct = productRepository.save(product);
		}else {
			throw new Exception();
		}
		
		return updateProduct;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		Optional<Product> selectedProduct = productRepository.findById(number);
		
		if(selectedProduct.isPresent()) {
			Product product = selectedProduct.get();
			
			productRepository.delete(product);
		}else {
			throw new Exception();
		}
	}

}
