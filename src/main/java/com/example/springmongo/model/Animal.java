package com.example.springmongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "animals")
public class Animal {

    @Id
    private int id;
    private String petname;
    private int age;

}
