package com.springboot.advanced_jpa.data.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@Table(name = "product")
@NoArgsConstructor //파라미터가 없는 생성자
@AllArgsConstructor //전체 필드 파라미터가 있는 생성자
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private Integer price;
	
	@Column(nullable = false)
	private Integer stock;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	
}
