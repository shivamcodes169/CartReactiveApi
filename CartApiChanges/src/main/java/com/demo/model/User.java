package com.demo.model;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Document("userInfo")
public class User {
	@JsonIgnore
	@Id
	private String id;

	@Indexed(unique=true)
	@NotNull
	private String uname;
	
	@JsonIgnore
	List<Product> products=new ArrayList<>();
	
	@JsonIgnore
	private double tot_amt;
}
