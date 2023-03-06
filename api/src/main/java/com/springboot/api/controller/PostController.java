package com.springboot.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.dto.MemberDto;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

	//Header에 json으로 설정해주고
	//Body에 입력하면 그 값을 받아 표현 
	
	@RequestMapping(value="/domain",method=RequestMethod.POST)
	public String postExample() {
		return "Hello Post API";
	}
	
	@PostMapping("/member") //RequestBody는 body 내용을 자동으로 매핑
	public String postMember(@RequestBody Map<String,Object> postData) {
		StringBuilder sb = new StringBuilder();
		
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	@PostMapping("/member2")
	public String postMemberDto(@RequestBody MemberDto memberDto) {
		return memberDto.toString();
	}
}
