package com.example.mongospringtrap.repositories;

import com.example.mongospringtrap.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends MongoRepository<User, Integer> {

}
