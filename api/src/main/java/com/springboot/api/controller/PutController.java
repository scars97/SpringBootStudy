package com.springboot.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.dto.MemberDto;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController { // 업데이트 개념

	@PutMapping("/member")
	public String postMember(@RequestBody Map<String, Object> putData) {
		StringBuilder sb = new StringBuilder();
		
		putData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	@PutMapping("/member1")
	public String postMemberDto1(@RequestBody MemberDto memberDto) {
		return memberDto.toString();
	}
	
	@PutMapping("/member2")
	public MemberDto postMemberDto2(@RequestBody MemberDto memberDto) {
		return memberDto;
	}
	
	@PutMapping("/member3")
	public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDto){
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(memberDto);
	}
}
