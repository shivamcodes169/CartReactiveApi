package com.demo.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.User;


import reactor.core.publisher.Mono;

@Repository
public interface CartRepository extends ReactiveMongoRepository<User,String> {
	@Query("{uname:?0}")
	Mono<User> findByName(String name);

}
