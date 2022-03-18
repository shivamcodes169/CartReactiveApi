package com.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("userInfo")
public class User {
	@Id
	private String id;
	@Indexed(unique=true)
	private String uname;
	List<Product> products=new ArrayList<>();
	public double tot_amt;
}
