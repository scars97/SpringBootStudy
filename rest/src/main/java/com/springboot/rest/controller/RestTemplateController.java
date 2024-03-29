package com.springboot.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.dto.MemberDto;
import com.springboot.rest.service.RestTemplateService;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {

	private final RestTemplateService restTemplateService;
	
	public RestTemplateController(RestTemplateService restTemplateService) {
		this.restTemplateService = restTemplateService;
	}
	
	@GetMapping
	public String getName() {
		return restTemplateService.getName();
	}
	
	@GetMapping("/path-variable")
	public String getNameWithPathVariable() {
		return restTemplateService.getNameWithPathVariable();
	}
	
	@GetMapping("/parameter")
	public String getNameWithParameter() {
		return restTemplateService.getNameWithParmeter();
	}
	
	@PostMapping
	public ResponseEntity<MemberDto> postDto(){
		return restTemplateService.postWithParamAndBody();
	}
	
	@PostMapping("/header")
	public ResponseEntity<MemberDto> postWithHeader(){
		return restTemplateService.postWithHeader();
	}
}
