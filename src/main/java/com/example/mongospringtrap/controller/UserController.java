package com.example.mongospringtrap.controller;

import com.example.mongospringtrap.model.Animal;
import com.example.mongospringtrap.model.User;
import com.example.mongospringtrap.repositories.UserDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class UserController {
    UserDAO userDAO;
    AnimalController animalController;
    @Autowired
    public UserController(UserDAO userDAO, AnimalController animalController) {
        this.userDAO = userDAO;
        this.animalController = animalController;
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUser(int id) {
        return userDAO.findById(id).get();
    }

    public void addUser(User user) {
        List<Animal> animals = user.getAnimals();
        animalController.addAllAnimals(animals);
        userDAO.save(user);
    }

    public void deleteUser(int id) {
        User user = getUser(id);
        userDAO.delete(user);
    }

    public void putUser(User user, int id) {

        User real = getUser(id);
       // real.setName(user.getName());
        real.setUsername(user.getUsername());
        real.setPassword(user.getPassword());
        //real.setEmail(user.getEmail());


        List<Animal> animals = user.getAnimals();
        for (Animal p: real.getAnimals()){
            for (Animal pp: animals){
                if (p.getId() == pp.getId()){
                    p.setPetname(pp.getPetname());
                    p.setAge(pp.getAge());
                    //p.setQuantity(pp.getQuantity());
                }
            }
        }
        animalController.actualizarTodo(user.getAnimals());

        userDAO.save(real);
    }

    public void patchUser(int id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        User user = getUser(id);
        User userPatched = applyPatch(patch, user);

        if (user.getAnimals().size() == userPatched.getAnimals().size()){
            animalController.actualizarTodo(userPatched.getAnimals());
        }else if (user.getAnimals().size() < userPatched.getAnimals().size()){
            animalController.addAllAnimals(userPatched.getAnimals());
        }

        userDAO.save(userPatched);

    }

    private User applyPatch(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }

    public void addAnimal(Animal animal, int id) {
        User u = getUser(id);
        animalController.añadir(animal);
        Animal p = animalController.getAnimal(animal.getId());
        boolean encontrar = false;
        for (Animal pp : u.getAnimals()){
            if (pp.getId() == p.getId()) {
                encontrar = true;
                break;
            }
        }
        if (!encontrar){
            u.addAnimal(p);
        }
        userDAO.save(u);
    }

    public void deleteAnimalOnUser(int id, int index) {
        User u = getUser(id);
        u.getAnimals().remove(index);
        userDAO.save(u);
    }

}
