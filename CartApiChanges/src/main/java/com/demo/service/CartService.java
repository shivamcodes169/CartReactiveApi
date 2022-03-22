package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.demo.exception.ErrorCode;
import com.demo.exception.SystemException;
import com.demo.model.Product;
import com.demo.model.User;
import com.demo.repository.CartRepository;
import com.demo.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private ProductRepository pRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	//create new user
	public Mono<User> createUser(User user)
	{
		
		return cartRepo.save(user);
		
	}
	
	public Flux<User> getALL()
	{
		
		return cartRepo.findAll();
		
		
	}
			

	
	public Mono<User> addProductToCart(String uname,String prodname,int newQty)
	{
		Mono<User> user=cartRepo.findByName(uname);
		User u=user.block();
		List<Product> products=u.getProducts();
		
		
		
		Query q=new Query();
		q.addCriteria(Criteria.where("prodname").is(prodname));
		Product p = mongoTemplate.findOne(q, Product.class);
			
		
				if(p.getQuantity()<newQty)
				{
					// add proper reply
					Mono.error(new RuntimeException("enter valid quantity"));
				}
		
		int flag=0;
		
		for(Product i:products)
		{
			if(i.getProdname().equals(prodname))
			{
				i.setQuantity(newQty);
				
				flag=1;
				break;
				
			}
			
		}
		
		if(flag==0)
		{
		p.setQuantity(newQty);
		products.add(p);
		
		}
		
		
		u.setTot_amt(computeTotalAmount( u));
		return cartRepo.save(u);
		
		
		
	}
	
	
	public Mono<User> checkOutNow(String name) {
		
		Mono<User> user=cartRepo.findByName(name);
		User u=user.block();
		List<Product> products=u.getProducts();
		for(Product p:products)
		{
			Query q=new Query();
			q.addCriteria(Criteria.where("prodname").is(p.getProdname()));
			Product prod = mongoTemplate.findOne(q, Product.class);
			Update update=new Update();
			update.set("quantity",prod.getQuantity()-p.getQuantity() );
			mongoTemplate.findAndModify(q, update, Product.class);
			
		}
		
		
		
		products.removeAll(products);
		u.setTot_amt(computeTotalAmount( u));
		return cartRepo.save(u);
		
		
	}
	
	 public double  computeTotalAmount(User c)
		{
			List<Product>products=c.getProducts();
			List<Double>amt= new ArrayList<>();
	        products.forEach(p -> {
				double price=p.getPrice();
				int quantity=p.getQuantity();
				amt.add(price*quantity);
			});
	        
			 double tot_amount = 0;
		        for (Double i : amt)
		        	tot_amount += i;
		        
		    
		     return tot_amount;
		}
	 
	public Mono<User> getbyName(String name) {
		
		return cartRepo.findByName(name)
				.hasElement()
				.flatMap((exist)->{
					if (Boolean.FALSE.equals(exist)){
                        return Mono.error(
                                SystemException.builder()
                                        .errorCode(ErrorCode.USER_NOT_FOUND)
                                        .message("user not found")
                                        .build()
                        );
					}
					return cartRepo.findByName(name);
					});
	}
	
	public Mono<User> deleteIt(String name, String prodname) {
		 Mono<User> cart=cartRepo.findByName(name);
		   User c= cart.block();
			List<Product>products=c.getProducts();
			for(Product p:products)
			{
				if (p.getProdname().equals(prodname)) {
	                products.remove(p);
	                break;
	                
	             }
			}
			
			c.setProducts(products);
			
			//set new tot_amt    
		    c.setTot_amt(computeTotalAmount( c));
			
		    
			return cartRepo.save(c);
	}
	

}
