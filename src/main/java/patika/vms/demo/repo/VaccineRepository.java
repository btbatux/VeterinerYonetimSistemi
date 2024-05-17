package patika.vms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import patika.vms.demo.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {

    List<Vaccine> findByAnimal_Id(int animalId);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    List<Vaccine> findByAnimal_IdAndNameAndCode(int animalId, String name, String code);

}
