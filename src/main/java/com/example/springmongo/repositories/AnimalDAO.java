package com.example.springmongo.repositories;

import com.example.springmongo.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDAO extends MongoRepository<Animal, Integer> {
}
