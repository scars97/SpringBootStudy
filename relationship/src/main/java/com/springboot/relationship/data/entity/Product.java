package com.springboot.relationship.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter
@Table(name = "product")
@NoArgsConstructor //파라미터가 없는 생성자
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;
	
	@Column(nullable = false)
	private String name;
	                                                                                                      
	@Column(nullable = false)
	private Integer price;
	
	@Column(nullable = false)
	private Integer stock;
	
	@OneToOne(mappedBy = "product")//ProductDetail 관계에서 읽힘 당하는 역할. 연관관계 하인.
	@ToString.Exclude //순환참조 제거.
	private ProductDetail productDetail;
	
	@ManyToOne// Provider 관계에서 주인. 
	@JoinColumn(name = "provider_id")
	@ToString.Exclude
	private Provider provider;
	
	@ManyToMany
	@ToString.Exclude
	private List<Producer> producers = new ArrayList<>();
	
	public void addProducer(Producer producer) {
		this.producers.add(producer);
	}
}
