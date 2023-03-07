package com.springboot.api.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.dto.MemberDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/get-api")//공통 URL 설정
public class GetController {

	private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);
	
	@GetMapping("/hello")
	public String getHello() {
		LOGGER.info("getHello 메서드가 호출되었습니다.");
		return "Hello World";
	}
	
	@GetMapping(value = "/name")
	public String getName() {
		LOGGER.info("getName 메서드가 호출되었습니다.");
		return "Hello 성현";
	}
	
	@GetMapping("/variable1/{variable}")
	public String getVariable(@PathVariable String variable) {
		LOGGER.info("@Pathvariable을 통해 들어온 값 : {}",variable);
		return variable;
	}
	
//	@GetMapping("/request1")
//	public String getRequestParam1(@RequestParam String name,
//								   @RequestParam String email,
//								   @RequestParam String organization) {
//		return name + " " + email + " " + organization;
//	}
	//swagger 활용
	@ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
	@GetMapping("/request1")
	public String getRequestParam1(
			@ApiParam(value = "이름", required = true) @RequestParam String name,
			@ApiParam(value = "이메일", required = true) @RequestParam String email,
			@ApiParam(value = "회사", required = true) @RequestParam String organization) {
		return name + " " + email + " " + organization;
	}
	
	@GetMapping("/request2")
	public String getRequestParam(@RequestParam Map<String,String> param) {
		StringBuilder sb = new StringBuilder();
		
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	@GetMapping("/request3")
	public String getRequestParam3(MemberDto memberDto) {
		return memberDto.toString();
	}
}
