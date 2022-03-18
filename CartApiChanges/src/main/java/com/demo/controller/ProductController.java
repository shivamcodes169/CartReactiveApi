package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Product;
import com.demo.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService prodService;
	
	@PostMapping("/")
	public Mono<Product> addProduct(@RequestBody Product product)
	{
		return prodService.saveProduct(product);
	}
	@GetMapping("/")
	public Flux<Product> getAll()
	{
		return prodService.findall();
	}
	
	@GetMapping("/getbyname/{name}")
	public Mono<Product> getByname(@PathVariable String name)
	{
		return  prodService.findByName(name);
	}
	@PutMapping("/{prodname}/{qty}/{price}")
	public Mono<Product> updateProd(@PathVariable String prodname,@PathVariable int qty,@PathVariable double price)
	{
		return prodService.updateProduct(prodname, qty, price);
	}
	@DeleteMapping("/{prodname}")
	public String deleteProd(@PathVariable String prodname)
	{
		prodService.deleteProduct(prodname);
		return prodname+"deleted";
	}
}
