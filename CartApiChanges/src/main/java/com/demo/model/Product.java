package com.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("productInfo")
public class Product {
	@Id
	private String id;
	@Indexed(unique=true)
	private String prodname;
	private int quantity;
	private double price;

}
