package patika.vms.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patika.vms.demo.CustomException.DuplicateResourceException;
import patika.vms.demo.CustomException.ResourceNotFoundException;
import patika.vms.demo.entities.Animal;
import patika.vms.demo.services.AnimalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable int id) {
        Optional<Animal> animal = animalService.getAnimalById(id);
        return animal.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null));
    }

    @GetMapping("/animal-name") //Veriyi almak için kullanılır. Sunucudan veri alırken kullanılır.
    public List<Animal> getAnimalsByName(@RequestParam String name) {
        return animalService.getAnimalsByName(name);
    }

    @GetMapping("/by-customer/{customerId}")
    public List<Animal> getAnimalsByCustomerId(@PathVariable int customerId) {
        return animalService.getAnimalsByCustomerId(customerId);
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        try {
            Animal savedAnimal = animalService.createAnimal(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAnimal);
        } catch (DuplicateResourceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable int id, @RequestBody Animal animal) {
        try {
            Animal updatedAnimal = animalService.updateAnimal(id, animal);
            return ResponseEntity.ok(updatedAnimal);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}") //Mevcut bir kaynağı silmek için kullanılır.
    public ResponseEntity<String> deleteAnimal(@PathVariable int id) {
        try {
            animalService.deleteAnimal(id);
            return ResponseEntity.ok("Animal deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal not found");
        }
    }
}
