package com.example.mongospringtrap.repositories;

import com.example.mongospringtrap.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDAO extends MongoRepository<Animal, Integer> {
}
