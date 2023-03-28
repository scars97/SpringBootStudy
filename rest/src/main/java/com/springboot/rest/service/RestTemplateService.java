package com.springboot.rest.service;

import java.net.URI;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rest.dto.MemberDto;

@Service
public class RestTemplateService {

	//Get 예제
	
	public String getName() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		return responseEntity.getBody();
	}
	
	public String getNameWithPathVariable() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/{name}")
				.encode()
				.build()
				.expand("Flature") //복수 값 넣어야할 경우 ,를 추가하여 구분.
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		return responseEntity.getBody();
	}
	
	public String getNameWithParmeter() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/param")
				.queryParam("name", "Flature")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		
		return responseEntity.getBody();
	}
	
	
	//Post 예제
	
	public ResponseEntity<MemberDto> postWithParamAndBody(){
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api")
				.queryParam("name", "Flature")
				.queryParam("email", "asf@wikibooks.co.kr")
				.queryParam("organization", "Wikibooks")
				.encode()
				.build()
				.toUri();
		
		MemberDto memberDto = new MemberDto();
		memberDto.setName("flature!!");
		memberDto.setEmail("asf@gmail.com");
		memberDto.setOrganization("Around Hub Studio");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);
		
		return responseEntity;
	}
	
	public ResponseEntity<MemberDto> postWithHeader(){
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/add-header")
				.encode()
				.build()
				.toUri();
		
		MemberDto memberDto = new MemberDto();
		memberDto.setName("flature");
		memberDto.setEmail("asf@wikibooks.co.kr");
		memberDto.setOrganization("Around Hub Studio");
		
		RequestEntity<MemberDto> requestEntity = RequestEntity
				.post(uri)
				.header("my-header", "Wikibooks API")
				.body(memberDto);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);
		
		return responseEntity;
	}
}
