package com.example.mongospringtrap.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "users")
public class User {

    @Id
    private int id;

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    private String username;
    private String password;
    @DBRef
    private List<Animal> animals;

    public void addAnimal(Animal animal){
        animals.add(animal);
    }

}
