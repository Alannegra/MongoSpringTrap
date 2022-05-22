package com.example.springmongo.controller;

import com.example.springmongo.model.Animal;
import com.example.springmongo.repositories.AnimalDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AnimalController {
    AnimalDAO animalDAO;


    @Autowired
    public AnimalController(AnimalDAO animalDAO) {
        this.animalDAO = animalDAO;
    }

    public List<Animal> getAllAnimals() {
        return animalDAO.findAll();
    }

    public void addAllAnimals(List<Animal> animals) {
        animalDAO.saveAll(animals);
    }

    public void añadir(Animal animal){
        if (!animalDAO.existsById(animal.getId())){
            animalDAO.save(animal);
        }
    }

    public Animal getAnimal(int id){
        return animalDAO.findById(id).get();
    }

    public void deleteById(int id){
        animalDAO.deleteById(id);
    }

    public void actualizar(int id, Animal animal) {
        Animal real = getAnimal(id);

        real.setPetname(animal.getPetname());
        real.setAge(animal.getAge());
        //real.setQuantity(animal.getQuantity());

        animalDAO.save(real);
    }

    public void actualizarTodo(List<Animal> animals){
        for (Animal p : getAllAnimals()){
            for (Animal pp: animals){
                if (p.getId() == pp.getId()){
                    p.setPetname(pp.getPetname());
                    p.setAge(pp.getAge());
                    //p.setQuantity(pp.getQuantity());
                    animalDAO.save(p);
                }
            }
        }
    }

    public void patchAnimal(int id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Animal animal = getAnimal(id);
        Animal animalPatched = applyPatch(patch, animal);

        animalDAO.save(animalPatched);

    }

    private Animal applyPatch(JsonPatch patch, Animal targetAnimal) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = patch.apply(objectMapper.convertValue(targetAnimal, JsonNode.class));
        return objectMapper.treeToValue(patched, Animal.class);
    }

}
