package com.demo.service;



import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.demo.exception.ErrorCode;
import com.demo.exception.SystemException;
import com.demo.model.Product;
import com.demo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	//product save
	public Mono<Product> saveProduct( Product prod)
	{
		
		return productRepo.save(prod);
	}
	
	//to find all products
	public Flux<Product> findall()
	{
		return productRepo.findAll();
	}
	
	//to find product info
	public Mono<Product> findByName(String prodname)
	{
		return productRepo.findByName(prodname);
	}
	
	//to update product
	public Mono<Product> updateProduct(String prodname,Integer qty,Double price)
	{
		Mono<Product> product=productRepo.findByName(prodname);
		Product p=product.block();
		p.setPrice(price);
		p.setQuantity(qty);
		return productRepo.save(p);
	}
	
	//to delete product check karna ek baar
	public void deleteProduct(String prodname)
	{
		
		Query query = new Query();
		query.addCriteria(Criteria.where("prodname").is(prodname));
		 mongoTemplate.remove(query, Product.class);
	}

}
