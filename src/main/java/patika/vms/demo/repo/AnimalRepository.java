package patika.vms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.vms.demo.entities.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByName(String name);

    List<Animal> findByCustomer_Id(int customerId);

}
