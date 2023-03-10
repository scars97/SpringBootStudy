package com.springboot.test.controller;

import com.google.gson.Gson;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.ProductServiceImpl;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired private MockMvc mockMvc;
	
	@MockBean ProductServiceImpl productService;
	
	@Test
	@DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
	void getProductTest() throws Exception{
		
		given(productService.getProduct(123L)).willReturn(
				new ProductResponseDto(123L, "pen", 5000, 2000));
		
		String productId = "123";
		
		mockMvc.perform(
						get("/product?number=" + productId))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.number").exists())
						.andExpect(jsonPath("$.name").exists())
						.andExpect(jsonPath("$.price").exists())
						.andExpect(jsonPath("$.stock").exists())
						.andDo(print());
						
		verify(productService).getProduct(123L);
	}
	
	@Test
	@DisplayName("Product 데이터 생성 테스트")
	void createProductTest() throws Exception{
		//Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 가정 상황을 만들어 줌.
		given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
			.willReturn(new ProductResponseDto(12315L,"pen", 5000, 2000));
		
		//테스트에 필요한 객체 생성
		ProductDto productDto = ProductDto.builder()
				.name("pen")
				.price(5000)
				.stock(2000)
				.build();
		
		//자바 객체를 Json 문자열로 변환하거나 Json 문자열을 자바 객체로 변환하는 역할
		Gson gson = new Gson();
		String content = gson.toJson(productDto);
		
		//테스트 실행 코드
		mockMvc.perform(
						get("/product")
							.content(content)//RequestBody 값을 넘겨주기 위함.
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())//대응하는 값이 없으면 오류 발생.
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());
	
		verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));
	}
		
}
