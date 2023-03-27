package com.springboot.valid_exception.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.valid_exception.data.dto.ProductDto;
import com.springboot.valid_exception.data.dto.ProductResponseDto;
import com.springboot.valid_exception.data.entity.Product;
import com.springboot.valid_exception.data.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepositoryTest) {
		this.productRepository = productRepositoryTest;
	}
	
	@Override
	public ProductResponseDto getProduct(Long number) {
		LOGGER.info("[getProduct] input number : {}", number);
		Product product = productRepository.findById(number).get();
		
		LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(),
				product.getName());	
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(product.getNumber());
		productResponseDto.setName(product.getName());
		productResponseDto.setPrice(product.getPrice());
		productResponseDto.setStock(product.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto saveProduct(ProductDto productDto) {
		LOGGER.info("[saveProduct] productDto : {}", productDto.toString());
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setCreateAt(LocalDateTime.now());
		product.setUpdateAt(LocalDateTime.now());
		
		Product savedProduct = productRepository.save(product);
		LOGGER.info("[saveProduct] saveProduct : {}", savedProduct);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(savedProduct.getNumber());
		productResponseDto.setName(savedProduct.getName());
		productResponseDto.setPrice(savedProduct.getPrice());
		productResponseDto.setStock(savedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public ProductResponseDto changeProductName(Long number, String name) throws Exception {
		Product foundProduct = productRepository.findById(number).get();
		foundProduct.setName(name);
		Product changedProduct = productRepository.save(foundProduct);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setNumber(changedProduct.getNumber());
		productResponseDto.setName(changedProduct.getName());
		productResponseDto.setPrice(changedProduct.getPrice());
		productResponseDto.setStock(changedProduct.getStock());
		
		return productResponseDto;
	}

	@Override
	public void deleteProduct(Long number) throws Exception {
		productRepository.deleteById(number);
	}

}
