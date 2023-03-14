package com.springboot.advanced_jpa.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.advanced_jpa.data.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	//find By
	Optional<Product> findByNumber(Long number);
	List<Product> findAllByName(String name);
	Product queryByNumber(Long number);
	
	//exists By 특정 데이터가 존재하는 지 확인하는 키워드
	boolean existsByNumber(Long number);
	
	//count By 조회쿼리 수행한 후 결과로 나온 레코드의 개수를 리턴
	long countByName(String name);
	
	//delete By, remove By
	void deleteByNumber(Long number);
	long removeByName(String name);
	
	//First<number>, Top<number>
	//한 번의 동작으로 여러 건을 조회할 때 사용. 단 건으로 조회할 때는 number 생략
	List<Product> findFirst5ByName(String name);
	List<Product> findTop10ByName(String name);
	
	//Is,Equals - findBy 메서드와 동일하게 동작
	Product findByNumberIs(Long number);
	Product findByNumberEquals(Long number);
	
	//(Is)Not - Not 키워드만 사용할 수도 있음.
	Product findByNumberIsNot(Long number);
	Product findByNumberNot(Long number);
	
	//Null,NotNull - Null인지 검사
	List<Product> findByUpdatedAtNull();
	List<Product> findByUpdatedAtIsNull();
	List<Product> findByUpdatedAtNotNull();
	List<Product> findByUpdatedAtIsNotNull();
	
	//And,Or - 여러 조건을 묶을 때 사용
	Product findByNumberAndName(Long number, String name);
	Product findByNumberOrName(Long number, String name);
	
	//GreaterThan,LessThan,Between
	//숫자나 datetime 칼럼을 대상을로 비교 연산에 사용할 수 있는 조건자 키워드
	//초과,미만의 개념으로 연산 수행, 경곗값 포함하려면 Equal 키워드 추가
	List<Product> findByPriceIsGreaterThan(Long price);
	List<Product> findByPriceGreaterThan(Long price);
	List<Product> findByPriceGreaterThanEqual(Long price);
	List<Product> findByPriceIsLessThan(Long price);
	List<Product> findByPriceLessThan(Long price);
	List<Product> findByPriceLessThanEqual(Long price);
	List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
	List<Product> findByPriceBetween(Long lowPrice, Long highPrice);
	
	//StartingWith,EndingWith,Containing,Like
	//일부 일치 여부를 확인하는 조건자 키워드.
	//SQL의 %와 같은 역할.
	List<Product> findByNameLike(String name);
	List<Product> findByNameIsLike(String name);
	
	List<Product> findByNameContains(String name);
	List<Product> findByNameContaining(String name);
	List<Product> findByNameIsContaining(String name);
	
	List<Product> findByNameStartsWith(String name);
	List<Product> findByNameStartingWith(String name);
	List<Product> findByNameIsStartingWith(String name);

	List<Product> findByNameEndsWith(String name);
	List<Product> findByNameEndingWith(String name);
	List<Product> findByNameIsEndingWith(String name);
	
	//Asc : 오름차순, Desc : 내림차순
	List<Product> findByNameOrderByNumberAsc(String name);
	List<Product> findByNameOrderByNumberDesc(String name);

	//매개변수를 활용한 쿼리 정렬
	List<Product> findByName(String name, Sort sort);
	
	//페이징 처리
	Page<Product> findByName(String name, Pageable pageable);
	
	//쿼리 처리
	@Query("select p from Product p where p.name = :name")
	List<Product> findByNameParam(@Param("name")String name);
	
	//특정 컬럼 추출
	@Query("select p.name, p.price, p.stock from Product p where p.name = :name")
	List<Product> findByNameParam2(@Param("name")String name);
}
