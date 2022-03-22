package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.CartService;


import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cart")
public class CartController {
	//first create user then add products
	@Autowired
	private CartService cartService;
	
	@PostMapping("/")
	public Mono<User> createUser(@Valid @RequestBody User user)
	{
		return cartService.createUser(user);
	}
	@GetMapping("/{name}")
	public Mono<User> getall( @PathVariable String name)
	{
		return cartService.getbyName(name);
	}
	
	@PutMapping("/addproduct/{name}/{prodname}")
	public  Mono<User> addOneProductToCart( @PathVariable String name,@PathVariable String prodname)
	{
		return cartService.addProductToCart(name, prodname,1);
	}
	@PutMapping("/addproduct/{name}/{prodname}/{qty}")
	public  Mono<User> addProductToCart(@PathVariable String name,@PathVariable String prodname,@PathVariable int qty)
	{
		return cartService.addProductToCart(name, prodname,qty);
	}
	@PostMapping("/buy/{name}")
	public Mono<User> checkOut(@PathVariable String name)
	{
		 return cartService.checkOutNow(name);
		
	}
	@DeleteMapping("/{name}/{prodname}")
	public Mono<User> deleteProd(@PathVariable String name,@PathVariable String prodname)
	{
		return cartService.deleteIt(name,prodname);
	}
}
