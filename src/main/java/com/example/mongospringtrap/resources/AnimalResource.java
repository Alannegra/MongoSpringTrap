package com.example.mongospringtrap.resources;

import com.example.mongospringtrap.controller.AnimalController;
import com.example.mongospringtrap.model.Animal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AnimalResource.Animal_RESOURCE)
public class AnimalResource {
    public final static String Animal_RESOURCE = "/animals";

    AnimalController animalController;

    @Autowired
    public AnimalResource(AnimalController animalController) {
        this.animalController = animalController;
    }

    @GetMapping
    public List<Animal> animals(){
        return animalController.getAllAnimals();
    }

    @GetMapping("{id}")
    public Animal animals(@PathVariable("id") int id){
        return animalController.getAnimal(id);
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        animalController.añadir(animal);
    }

    @DeleteMapping("{id}")
    public void deleteAnimal(@PathVariable("id") int id){
        animalController.deleteById(id);
    }

    @PutMapping("{id}")
    public void putAnimal(@PathVariable("id") int id, @RequestBody Animal animal){
        animalController.actualizar(id,animal);
    }

    @PatchMapping("{id}")
    public void patchAnimal(@PathVariable("id") int id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        animalController.patchAnimal(id,patch);
    }
}
