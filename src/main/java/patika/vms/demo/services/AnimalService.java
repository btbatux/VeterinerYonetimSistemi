package patika.vms.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.vms.demo.CustomException.DuplicateResourceException;
import patika.vms.demo.CustomException.ResourceNotFoundException;
import patika.vms.demo.entities.Animal;
import patika.vms.demo.repo.AnimalRepository;
import java.util.List;
import java.util.Optional;


@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> getAnimalById(int id) {
        return animalRepository.findById(id);
    }

    public List<Animal> getAnimalsByName(String name) {
        return animalRepository.findByName(name);
    }

    public List<Animal> getAnimalsByCustomerId(int customerId) {
        return animalRepository.findByCustomer_Id(customerId);
    }


    public Animal createAnimal(Animal animal) {
        if (animalRepository.existsById(animal.getId())) {
            throw new DuplicateResourceException("Animal with id " + animal.getId() + " already exists.");
        }
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(int id, Animal animal) {
        if (!animalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Animal with id " + id + " not found.");
        }
        animal.setId(id);
        return animalRepository.save(animal);
    }




    public void deleteAnimal(int id) {
        if (!animalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Animal with id " + id + " not found.");
        }
        animalRepository.deleteById(id);
    }
}
