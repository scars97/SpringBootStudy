package com.springboot.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpa.data.dto.ChangeProductNameDto;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ResponseHeader;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@ApiOperation(value = "상품 가져오기")
	@GetMapping()
	public ResponseEntity<ProductResponseDto> getProduct(
			@ApiParam(value = "상품 번호", required = true) @RequestParam Long number){
		ProductResponseDto productResponseDto = productService.getProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@ApiOperation(value = "상품 저장")
	@PostMapping()
	public ResponseEntity<ProductResponseDto> createProduct(
			@ApiParam(value = "상품 Dto 객체", required = true) @RequestBody ProductDto productDto){
		ProductResponseDto productResponseDto = productService.saveProduct(productDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@ApiOperation(value = "상품 정보 변경")
	@PutMapping()
	public ResponseEntity<ProductResponseDto> changeProductName(
			@ApiParam(value = "상품 변경 Dto 객체", required = true)
			@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception{
		ProductResponseDto productResponseDto = productService.changeProductName(
				changeProductNameDto.getNumber(),
				changeProductNameDto.getName());
		
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@ApiOperation(value = "상품 삭제")
	@DeleteMapping()
	public ResponseEntity<String> deleteProduct(
			@ApiParam(value = "상품 번호", required = true) 
			@RequestParam Long number) throws Exception{
		productService.deleteProduct(number);
		
		return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
	}
}
