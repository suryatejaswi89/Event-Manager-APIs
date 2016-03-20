package com.events.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.events.model.Hall;

public interface HallRepository extends MongoRepository<Hall, String> {

}
