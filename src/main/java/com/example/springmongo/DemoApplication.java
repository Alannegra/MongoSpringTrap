package com.example.springmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


//////////////// GET ///////////////////

    // http://localhost:8080/users/13/password

//////////////// POST ///////////////////


   /*
   {
        "username": "Alan",
            "password": "contra",
            "animals":[
        {
            "id": 1,
                "petname": "Leon",
                "age": 56
        },
        {
            "id": 2,
                "petname": "Gallina",
                "age": 4
        }
        ]
    }
    */

//////////////// DELETE ///////////////////

    // http://localhost:8080/users/1

////////////// PATCH //////////////////////////



    /*
   [
        {
            "op":"replace",
            "path":"/password",
            "value":"parcheado"
        }
    ]
    */


    /*
    [
        {
            "op":"copy",
            "from":"/password",
            "path":"/username"
        }
    ]
    */


    /////////////// MONDONGO ///////////////////////

   // mongosh -u admin -p
   // use admin
   // use alandb
   // db.users.find()

}
