package com.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document("productInfo")
public class Product {
	@Id
	private String id;
	
 
	@NotEmpty(message="prodname cant be null")
	@Indexed(unique=true)
	private String prodname;
	
	@NotNull(message="price cant be null")
//	@NotEmpty(message="quantity cant be null")
	private Integer quantity;
	
//	@NotEmpty(message="price cant be null")
	@NotNull(message="price cant be null")
	private Double price;

}
